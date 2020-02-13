extern crate serde;
use flate2::write::GzEncoder;
use flate2::Compression;
use parse_mediawiki_dump::Page;
use std::fs::File;
use std::io;
use std::io::{BufWriter, Write};
use unicode_segmentation::UnicodeSegmentation;

#[macro_use]
extern crate serde_derive;

mod settings;

use settings::Settings;

fn main() {
    let settings = Settings::new().unwrap();

    if settings.xml_dump.is_some() {
        let xml_dump = settings.xml_dump.as_ref().unwrap();
        // source: https://riptutorial.com/rust/example/8867/asref---asmut
        // fn open<P: AsRef<Path>>(path: P) -> Result<File>
        // This allows File.open() to accept not only Path,
        // but also OsStr, OsString, str, String, and PathBuf with implicit conversion
        // because these types all implement AsRef<Path>.
        let file = match std::fs::File::open(xml_dump) {
            Err(error) => {
                eprintln!("Failed to open input file: {}", error);
                std::process::exit(1);
            }
            Ok(file) => {
                println!("dictionnary-builder will use {}", xml_dump);
                std::io::BufReader::new(file)
            }
        };
        if xml_dump.ends_with(".bz2") {
            //parse(std::io::BufReader::new(bzip2::bufread::BzDecoder::new(
            //parse(std::io::BufReader::new(bzip2::bufread::MultiBzDecoder::new
            parse(
                std::io::BufReader::new(bzip2::bufread::BzDecoder::new(file)),
                &settings,
            );
        } else {
            parse(file, &settings);
        }
    } else {
        println!("dictionnary-builder will use stdin");
        parse(std::io::BufReader::new(io::stdin()), &settings);
    }
}

fn parse(source: impl std::io::BufRead, settings: &Settings) {
    let f_words = File::create(&settings.words_file).expect("Unable to create file");
    let f_excluded = File::create(&settings.excluded_words_file).expect("Unable to create file");
    let mut writer_words = BufWriter::new(f_words);
    let mut writer_excluded = BufWriter::new(f_excluded);
    let mut word_counter = 0;
    let mut excluded_counter = 0;
    let language = &settings.language;
    let language_short = &settings.language_short;
    let filters = vec![
        format!("=={}==", language),   //needed for English wiktionary
        format!("== {} ==", language), //neeeded for Nepali wiktionary
        //needed for French wiktionary
        format!("== {{langue|{}}} ==", language_short),
        format!("=={{langue|{}}} ==", language_short),
        format!("== {{langue|{}}}==", language_short),
        format!("=={{langue|{}}}==", language_short),
    ];
    for result in parse_mediawiki_dump::parse(source) {
        match result {
            Err(error) => {
                eprintln!("Error: {}", error);
                std::process::exit(1);
            }
            Ok(page) => {
                if build_dico(&page, &settings, &filters) == true {
                    writer_words
                        .write_all(page.title.as_bytes())
                        .expect("Unable to write data");
                    writer_words
                        .write_all("\n".as_bytes())
                        .expect("Unable to write data");
                    build_definition_file(&page, &settings);
                    word_counter += 1;
                } else {
                    writer_excluded
                        .write_all(page.title.as_bytes())
                        .expect("Unable to write data");
                    writer_excluded
                        .write_all("\n".as_bytes())
                        .expect("Unable to write data");
                    excluded_counter += 1;
                }
            }
        }
    }
    println!("total number of entries:{}", word_counter);
    println!("total number of removed entries:{}", excluded_counter);
}

fn filter(page: &Page, settings: &Settings, filters: &Vec<String>) -> bool {
    let title: &str = page.title.as_ref();
    if title.contains('/') || title.contains(':') {
        return false;
    }
    if !settings.expression && title.contains(' ') {
        return false;
    }
    if  filters.into_iter().any(|item| page.text.contains(item)) {
        return true;
    };
    false
}

fn build_dico(page: &Page, settings: &Settings, filters: &Vec<String>) -> bool {
    let title: &str = page.title.as_ref();
    let mut word = title.to_owned();
    let definition = filter(page, settings, filters);
    word.push('\n');
    if definition == true {
        true
    } else {
        false
    }
}

fn build_definition_file(page: &Page, settings: &Settings) {
    let definition: &str = page.text.as_ref();
    let definition = definition.to_owned();
    let title: &str = page.title.as_ref();
    let mut full_path = String::new();
    let location = locator(&title, &settings);
    std::fs::create_dir_all(&location).expect("Unable to create file");
    full_path.push_str(&location);
    full_path.push(std::path::MAIN_SEPARATOR);
    full_path.push_str(title);
    full_path.push_str(".gz");
    let f_definition = File::create(full_path).expect("Unable to create file");
    let mut writer_definition = BufWriter::new(f_definition);
    let mut e = GzEncoder::new(Vec::new(), Compression::default());
    e.write_all(definition.as_bytes()).unwrap();
    let compressed_bytes = e.finish().unwrap();
    writer_definition
        .write_all(&compressed_bytes)
        .expect("Unable to write data");
}

fn locator(word: &str, settings: &Settings) -> String {
    let unicode_vect = word.graphemes(true).collect::<Vec<&str>>();
    let root_directory = settings.root.as_ref();
    let mut located_full_path = String::new();
    if unicode_vect.len() == 1 {
        located_full_path.push_str(root_directory);
        located_full_path.push(std::path::MAIN_SEPARATOR);
        located_full_path.push_str(word);
    } else {
        located_full_path.push_str(root_directory);
        located_full_path.push(std::path::MAIN_SEPARATOR);
        located_full_path.push_str(unicode_vect[0]);
        located_full_path.push(std::path::MAIN_SEPARATOR);
        located_full_path.push_str(unicode_vect[1]);
    }
    return located_full_path;
}

# Dictionary builder [![OpenHub](http://www.openhub.net/p/dictionary-builder/widgets/project_thin_badge.gif)](https://www.openhub.net/p/dictionary-builder)
## About ##
This project allow you to build dictionaries based on [Wiktionary](http://www.wiktionary.org/) entries.   

Dictionary builder used to be a demonstration of advanced JAXB techniques to unmarshall very large xml document with very low memory footprint.   
The Java/JAXB implementation has been archived in [java-jaxb branch](https://github.com/newca12/dictionary-builder/tree/java-jaxb)

Then it was re-written with Scala and Akka Streams.  
The Scala/akka-stream implementation has been archived in [scala-akka-streams branch](https://github.com/newca12/dictionary-builder/tree/scala-akka-streams)

And now re-written with Rust.

The resulting dictionnary is exactly the same with the three implementations.
None of these implementations was designed to be use as a benchmark but nethertheless Rust results are breathtaking. See below.

dictionary-builder is an EDLA project.

The purpose of [edla.org](https://edla.org) is to promote the state of the art in various domains.

## How to use it ##

0. Rust [need to be installed](https://doc.rust-lang.org/book/ch01-01-installation.html) to generate an executable

1. Get a fresh wiktionary backup   
Choose your favorite language and download the dump containing the current versions of article content [here](http://download.wikimedia.org/backup-index.html)  
Example for the english dump:
http://dumps.wikimedia.org/enwiktionary/latest/enwiktionary-latest-pages-articles-multistream.xml.bz2

2. Uncompress the fresh downloaded dump somewhere (Take care you need up to 7 Gigas of free disk space)

3. Build the executable : cargo build --release  

4. Edit Setings.toml to indicate the language you choose, where the dump is located and last but not least where the dictionary should be generated.  
(With Windows systems PATHs need to be escaped for example `C:\\dico\\words` and take care you need at least 4G of free disk space to store your dictionary if you set `with_definition=true`)

5. Launch the program : ./target/release/dictionary-builder

6. Some results :  
From the English dictionary 918612 entries are generated in less than 2 minutes and 3.5 Gigas disk space are required for the dictionary.

That's it.

## Performance comparaison ##

Test were done on a modest i7-4600U CPU @ 2.10GHz with SSD.  
The results sound like a joke :

|| Rust  | Scala/akka streams | Java/JAXB |
| :---:| :---: | :---: | :---: |
| without definition| 37s  | 4min 47s  | 7min 36s |
| with definitions | 1min 53s  | 5min 46s  | 9min 1s |

Rust implementation outperform by far the others implementations and the icing on the cake : Rust use ten time less memory. :rocket:

## Developer Notes ##

Some words like for example `con` are [reserved](https://superuser.com/questions/86999/why-cant-i-name-a-folder-or-file-con-in-windows) in Windows system. but :
``` rust
File::create("con").expect("Unable to create file"); 
````
will not trig any error. (This is not specific to Rust, Java will not trig an exception either)

### License ###
© 2009-2023 Olivier ROLAND. Distributed under the GPLv3 License.


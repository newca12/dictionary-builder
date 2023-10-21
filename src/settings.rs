use config::{Config, ConfigError, File};

#[derive(Debug, Deserialize)]
pub struct Settings {
    pub root: String,
    pub words_file: String,
    pub excluded_words_file: String,
    pub xml_dump: Option<String>,
    pub with_definition: bool,
    pub expression: bool,
    pub language_filter: bool,
    pub language: String,
    pub language_short: String,
}

impl Settings {
    pub fn new(file_settings_path: &str) -> Result<Self, ConfigError> {
        let settings = Config::builder()
            .add_source(File::with_name(
                format!("{}Settings.toml", file_settings_path).as_str(),
            ))
            .build()?;
        settings.try_deserialize()
    }
}

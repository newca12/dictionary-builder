use config::ConfigError;

#[derive(Debug, Deserialize)]
pub struct Settings {
    pub root: String,
    pub words_file: String,
    pub excluded_words_file: String,
    pub xml_dump: Option<String>,
    pub expression: bool,
    pub language_filter: bool,
    pub language: String,
    pub language_short: String,
}

impl Settings {
    pub fn new() -> Result<Self, ConfigError> {
        let mut settings = config::Config::default();
        settings.merge(config::File::with_name("Settings")).unwrap();
        settings.try_into()
    }
}

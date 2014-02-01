# Dictionary builder [![Build Status](https://buildhive.cloudbees.com/job/newca12/job/dictionary-builder/badge/icon)](https://buildhive.cloudbees.com/job/newca12/job/dictionary-builder/) [![Ohloh](http://www.ohloh.net/p/dictionary-builder/widgets/project_thin_badge.gif)](https://www.ohloh.net/p/dictionary-builder)
## About ##
Dictionary builder is a demonstration of advanced JAXB techniques to unmarshall very large xml document with very low memory footprint. 
This project allow you to build dictionaries based on [Wiktionary](http://www.wiktionary.org/) entries.   

dictionary-builder is an EDLA project.

The purpose of [edla.org](http://www.edla.org) is to promote the state of the art in various domains.

## How to use it ##

1. Get a fresh wiktionary backup   
Choose your favorite language and download the dump containing the current versions of article content [here](http://download.wikimedia.org/backup-index.html)  
Example for the english dump:
http://dumps.wikimedia.org/enwiktionary/20130610/enwiktionary-20130610-pages-articles.xml.bz2

2. Uncompress the fresh downloaded dump somewhere

3. Edit dico.properties to indicate the language you choose, where the dump is located and last but not least where the dictionary should be generated.  
(Take care you need some free disk space to store your dictionary)  
(dico.properties is located here : dictionary-builder/src/main/resources/org/edla/dico/construct/dico.properties)

4. Build the project : mvn install  
(You need to rebuild the project each time you modify the dico.properties file)

5. Launch the program :  java -jar target/dictionary-builder.jar

6. Some results :  
From the English dictionary 422996 entries are generated in less than 15 min and 2 Gigas disk space are required for the dictionary.  
From the French dictionary 1205597 entries are generated in less than 15 min and 5 Gigas disk space are required for the dictionary.  

That's it.

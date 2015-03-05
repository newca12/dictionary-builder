# Dictionary builder [![Build Status](https://buildhive.cloudbees.com/job/newca12/job/dictionary-builder/badge/icon)](https://buildhive.cloudbees.com/job/newca12/job/dictionary-builder/) [![OpenHub](http://www.openhub.net/p/dictionary-builder/widgets/project_thin_badge.gif)](https://www.openhub.net/p/dictionary-builder)
## About ##
Dictionary builder is a demonstration of advanced JAXB techniques to unmarshall very large xml document with very low memory footprint. 
This project allow you to build dictionaries based on [Wiktionary](http://www.wiktionary.org/) entries.   

dictionary-builder is an EDLA project.

The purpose of [edla.org](http://www.edla.org) is to promote the state of the art in various domains.

## How to use it ##

1. Get a fresh wiktionary backup   
Choose your favorite language and download the dump containing the current versions of article content [here](http://download.wikimedia.org/backup-index.html)  
Example for the english dump:
http://dumps.wikimedia.org/enwiktionary/latest/enwiktionary-latest-pages-articles-multistream.xml.bz2

2. Uncompress the fresh downloaded dump somewhere
(Take care you need up to 5 Gigas of free disk space)

3. Edit dico.properties to indicate the language you choose, where the dump is located and last but not least where the dictionary should be generated.  
(Take care you need some free disk space to store your dictionary)  
(dico.properties is located here : dictionary-builder/src/main/resources/org/edla/dico/construct/dico.properties)  
**If your language is not with a latin alphabet you need to convert the language property to ISO 8859-1 with escape sequences.**  
**Example for Nepali, you need to set language=\u0928\u0947\u092A\u093E\u0932\u0940**

4. Build the project : mvn install  
(You need to rebuild the project each time you modify the dico.properties file)

5. Launch the program :  java -jar target/dictionary-builder.jar

6. Some results :  
From the English dictionary 478069 entries are generated in less than 20 min and 2 Gigas disk space are required for the dictionary.  
From the French dictionary 1205597 entries are generated in less than 30 min and 5 Gigas disk space are required for the dictionary.   
From the Nepali dictionary 1062 entries are generated in 3 seconds and 5 Megas disk space are required for the dictionary.  

That's it.

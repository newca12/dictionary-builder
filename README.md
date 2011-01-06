# Dictionary builder #
## About ##
Dictionary builder allow you to build dictionaries based on [Wiktionary](http://www.wiktionary.org/) entries.  
This project is also a demonstration of advanced JAXB techniques to unmarshall very large xml document.  

dictionary-builder is an EDLA project.

The purpose of [edla.org](http://www.edla.org) is to promote the state of the art in various domains.

## How to use it ##

1. Get a fresh wiktionary backup   
Choose your favorite language and download the dump containing the current versions of article content [here](http://download.wikimedia.org/backup-index.html)  
Example for the french dump:  
http://download.wikimedia.org/frwiktionary/20101108/frwiktionary-20101108-pages-articles.xml.bz2

2. Uncompress the fresh downloaded dump somewhere

3. Edit dico.properties to indicate the language you choose, where the dump is located and last but not least where the dictionary should be generated. (Take care you need some free disk space to store your dictionary)

4. Build the project : mvn install

5. Launch the program :  java -jar dictionary-builder.jar

That's it.
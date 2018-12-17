# Dictionary builder [![OpenHub](http://www.openhub.net/p/dictionary-builder/widgets/project_thin_badge.gif)](https://www.openhub.net/p/dictionary-builder)
## About ##
This project allow you to build dictionaries based on [Wiktionary](http://www.wiktionary.org/) entries.   

*Dictionary builder used to be a demonstration of advanced JAXB techniques to unmarshall very large xml document with very low memory footprint.   
The Java/JAXB implementation has been archived in [java-jaxb branch](https://github.com/newca12/dictionary-builder/tree/java-jaxb)*

The new implementation is now re-written with Scala and Akka Streams.  
The resulting dictionnary is exactly the same that the one generated with the Java/JAXB implementation.

dictionary-builder is an EDLA project.

The purpose of [edla.org](https://edla.org) is to promote the state of the art in various domains.

## How to use it ##

0. Java 8 or later is required 

1. Get a fresh wiktionary backup   
Choose your favorite language and download the dump containing the current versions of article content [here](http://download.wikimedia.org/backup-index.html)  
Example for the english dump:
http://dumps.wikimedia.org/enwiktionary/latest/enwiktionary-latest-pages-articles-multistream.xml.bz2

2. ~~Uncompress the fresh downloaded dump somewhere~~ 
~~(Take care you need up to 5 Gigas of free disk space)~~  
This is not needed anymore, the compressed dump will be decompressed on the fly.

3. Edit application.conf to indicate the language you choose, where the dump is located and last but not least where the dictionary should be generated.  
(Take care you need some free disk space to store your dictionary)  
(application.conf is located here : dictionary-builder/src/main/resources/application.conf)  
**Nota 1 : If you are using Windows you need to escape \ like this : `C:\\some_folder\\some_subfolder\\some_file`**   
**Or you can use / like this : C:/some_folder/some_subfolder/some_file**   
**Nota 2 : If your language is not with a latin alphabet you need to convert the language property to ISO 8859-1 with escape sequences.**  
**Example for Nepali, you need to set language=\u0928\u0947\u092A\u093E\u0932\u0940**  

4. Build the project : sbt assembly  
(You need to rebuild the project each time you modify the application.conf file)

5. Launch the program :  java -jar target/scala-2.12/dictionary-builder-assembly-2.0.0.jar

6. Some results :  
From the English dictionary 699828 entries are generated

That's it.

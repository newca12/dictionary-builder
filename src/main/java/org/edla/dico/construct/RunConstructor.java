package org.edla.dico.construct;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;

import org.edla.wikimediaschema.MediaWikiType;
import org.edla.wikimediaschema.PageType;
import org.xml.sax.XMLReader;

public class RunConstructor {

	public static void main(final String[] args) throws Exception {

		final DicoProperties dicoProperties = DicoProperties.getInstance();
		final DicoBuilder dicoBuilder = new DicoBuilder(dicoProperties);

		// final Dico dico = new Dico(dicoProperties.xmlFile);
		// dico.buildWordList(dicoProperties.wordsFile,dicoProperties.exclusFile);

		JAXBContext context = JAXBContext
				.newInstance("org.edla.wikimediaschema");

		Unmarshaller unmarshaller = context.createUnmarshaller();

		// page notification callback
		final MediaWikiType.Listener orderListener = new MediaWikiType.Listener() {
			public void handlePage(MediaWikiType mediaWikiType, PageType page) {
				try {
					dicoBuilder.buildDico(page);
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		};

		// install the callback on all MediaWikiType instances
		unmarshaller.setListener(new Unmarshaller.Listener() {
			public void beforeUnmarshal(Object target, Object parent) {
				if (target instanceof MediaWikiType) {
					((MediaWikiType) target).setPageListener(orderListener);
				}
			}

			public void afterUnmarshal(Object target, Object parent) {
				if (target instanceof MediaWikiType) {
					((MediaWikiType) target).setPageListener(null);
				}
			}
		});

		// create a new XML parser
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XMLReader reader = factory.newSAXParser().getXMLReader();
		reader.setContentHandler(unmarshaller.getUnmarshallerHandler());

		reader.parse(dicoProperties.xmlFile);
		dicoBuilder.endProcess();

	}

}

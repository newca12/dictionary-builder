/*
    Copyright (C) 2007-2015  Olivier ROLAND (olivier.roland@edla.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package org.edla.dico.construct;

import java.io.FileNotFoundException;
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

		try {
			reader.parse(dicoProperties.xmlFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("TIPS: check your config file dico.properties");
			System.exit(1);
		} catch (Exception UnmarshalException) {
			System.out.println(UnmarshalException.getMessage());
			System.out
					.println("Sorry but your wikimedia dump is probably too old.");
			System.out
					.println("If your wikimedia dump is up to date please fill out a problem report form");
			System.out
					.println("and submit it : https://github.com/newca12/dictionary-builder/issues");
			System.exit(1);
		}
		dicoBuilder.endProcess();

	}

}

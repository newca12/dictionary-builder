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

import java.io.BufferedWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.edla.tools.Locator;
import org.edla.wikimediaschema.PageType;
import org.edla.wikimediaschema.RevisionType;

public class DicoBuilder {

	private Locator locator;
	private BufferedWriter wordsWriter = null;
	private BufferedWriter exclusWriter = null;
	private String language;
	private String languageShort;
	private boolean expression;
	private boolean languageFilter;
	int exclus = 0;
	int wordCounter = 0;

	public DicoBuilder(final DicoProperties dicoProperties) {
		try {
			this.locator = Locator.getInstance();
			File folderExisting = new File(dicoProperties.root);
			if (!folderExisting.exists()) {
				System.out.println("Please, create the root folder "
						+ dicoProperties.root);
				System.out
						.println("and/or check/fix your config file dico.properties and try again.");
				System.exit(1);
			}
			wordsWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dicoProperties.wordsFile), StandardCharsets.UTF_8));
			exclusWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dicoProperties.exclusFile), StandardCharsets.UTF_8));
			language = dicoProperties.language;
			languageShort = dicoProperties.languageShort;
			expression = dicoProperties.expression;
			languageFilter = dicoProperties.languageFilter;
		} catch (final IOException e) {
			System.out.println(e.getMessage());
			System.out.println("TIPS: check your config file dico.properties");
			System.exit(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public void buildDico(PageType page) throws IOException {
		String word = page.getTitle();
		String definition = this.filtre(page, word);

		if (definition != null) {
			wordsWriter.write(word);
			wordsWriter.write('\n');
			// System.out.println("word"+word);
			// System.out.println("definition"+definition);
			wordCounter++;
			this.buildDefinitionFiles(word, definition);
		} else {
			exclusWriter.write(word);
			exclusWriter.write('\n');
			exclus++;
		}
	}

	private String filtre(final PageType p, final String word) {
		String definition = null;
		if (word.contains("/") || word.contains(":")) {
			return null;
		}
		if ((expression == false) && word.contains(" "))
			return null;
		//System.out.println("word:" + word);
		final List revisions = p.getRevisionOrUpload();
		for (int j = 0; j < revisions.size(); j++) {
			final RevisionType r = (RevisionType) revisions.get(j);
			definition = r.getText().getValue();
		}
		//System.out.println("======= definition =======");
		//System.out.println(definition);
		if ((!languageFilter && !definition.isEmpty())
				|| definition.contains("==" + language + "==") //needed for English wiktionary
				|| definition.contains("== " + language + " ==") //neeeded for Nepali wiktionary
				//needed for French wiktionary
				|| definition
						.contains("== {{langue|" + languageShort + "}} ==")
				|| definition.contains("== {{langue|" + languageShort + "}}==")
				|| definition.contains("=={{langue|" + languageShort + "}}==")
				|| definition.contains("=={{langue|" + languageShort + "}} =="))
			return definition;

		return null;
	}

	private void buildDefinitionFiles(final String word, final String definition) {

		final String directory = this.locator.locate(word);

		try {
			new File(directory).mkdirs();
			final BufferedOutputStream writer = new BufferedOutputStream(
					new GZIPOutputStream(new FileOutputStream(directory + "/"
							+ word + ".gz")));
			/*
			 * ZipOutputStream writer = new ZipOutputStream( new
			 * BufferedOutputStream(new FileOutputStream(directory + "/" + word
			 * + ".zip")));
			 */
			writer.write(definition.getBytes());

			// FileWriter writer = new FileWriter(directory+"/" + word);
			// writer.write(definition);
			writer.close();

		} catch (final IOException e) {
			System.out.println(word + " " + directory);
			e.printStackTrace();
		}
	}

	public void endProcess() throws IOException {
		wordsWriter.close();
		exclusWriter.close();
		System.out.println("total number of entries:" + wordCounter);
		System.out.println("total number of removed entries:" + exclus);
	}
}

/*
    Copyright (C) 2007-2012  Olivier ROLAND (olivier.roland@edla.org)

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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.edla.tools.Locator;
import org.edla.wikimediaschema.PageType;
import org.edla.wikimediaschema.RevisionType;

public class DicoBuilder {

	private Locator locator;
	private FileWriter wordsWriter = null;
	private FileWriter exclusWriter = null;
	private String language;
	private String languageShort;
	int exclus = 0;
	int wordCounter = 0;

	public DicoBuilder(final DicoProperties dicoProperties) {
		try {
			this.locator = Locator.getInstance();
			wordsWriter = new FileWriter(dicoProperties.wordsFile);
			exclusWriter = new FileWriter(dicoProperties.exclusFile);
			language = dicoProperties.language;
			languageShort = dicoProperties.languageShort;
		} catch (final IOException e) {
			e.printStackTrace();
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
		final List revisions = p.getRevisionOrUploadOrLogitem();
		for (int j = 0; j < revisions.size(); j++) {
			final RevisionType r = (RevisionType) revisions.get(j);
			definition = r.getText().getValue();
		}
		if (definition.contains("{{=" + languageShort + "=}}")
				|| definition.contains("==" + language + "==")) {
			return definition;
		}
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

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
	private FileWriter wordsWriter=null;
	private FileWriter exclusWriter=null;
	int exclus = 0;
	int wordCounter = 0;
	
	public DicoBuilder(final DicoProperties dicoProperties) {
	try {
		this.locator = Locator.getInstance();
		wordsWriter = new FileWriter(dicoProperties.wordsFile);
		exclusWriter = new FileWriter(dicoProperties.exclusFile);	
	} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buildDico(PageType page) throws IOException {
		String word = page.getTitle();
		String definition = this.filtre(page,word);
		if (definition != null) {
			wordsWriter.write(word);
			wordsWriter.write('\n');
			//System.out.println("word"+word);
			//System.out.println("definition"+definition);
			wordCounter ++;
			this.buildDefinitionFiles(word,definition);
		} else {
			exclusWriter.write(word);
			exclusWriter.write('\n');
			exclus ++;
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
		if (!definition.contains("{{=fr=}}")) {
			return null;
		}
		return definition;
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
			 * BufferedOutputStream(new FileOutputStream(directory + "/" + word +
			 * ".zip")));
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
		System.out.println("nb mots:" + wordCounter);
		System.out.println("nb exclus:" + exclus);
	}
}

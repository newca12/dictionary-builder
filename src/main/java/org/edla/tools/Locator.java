package org.edla.tools;

import org.edla.dico.construct.DicoProperties;

public class Locator {
	
	private DicoProperties dicoProperties;
	private static Locator instance;
	
	public static Locator getInstance() {
		if (null == Locator.instance) {
			Locator.instance = new Locator();
			
		}
		return Locator.instance;
	}	
	
	public String locate(final String word) {
		this.dicoProperties = DicoProperties.getInstance();
		final String rootDirectory = this.dicoProperties.root;
		String directory;
		if (word.length() == 1) {
			directory = rootDirectory + "/" + word;
		} else {
			directory = rootDirectory + "/" + word.charAt(0) + "/"
					+ word.charAt(1);
		}
		return directory;
	}
}

package org.edla.dico.construct;

import java.io.InputStream;
import java.util.Properties;

public class DicoProperties {

	public String root;

	public String xmlFile;

	public String wordsFile;

	public String exclusFile;

	public static DicoProperties getInstance() {
		if (null == DicoProperties.instance) {
			DicoProperties.instance = new DicoProperties();
		}
		return DicoProperties.instance;
	}

	private static DicoProperties instance;

	private DicoProperties() {
		/*
		 * final Properties prop = new Properties(); try { final FileInputStream
		 * in = new FileInputStream("/home/hack/dico.properties");
		 * prop.load(in); in.close(); } catch (final Exception e) {
		 * e.printStackTrace(); }
		 */
		Properties prop = new Properties();
		System.out.println(this.getClass().getPackage().getName()); 
		InputStream stream = this.getClass().getResourceAsStream(
				"dico.properties");
		try {
			prop.load(stream);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.root = prop.getProperty("root");
		this.wordsFile = prop.getProperty("wordsFile");
		this.exclusFile = prop.getProperty("exclusFile");
		this.xmlFile = prop.getProperty("xmlFile");

	}
}

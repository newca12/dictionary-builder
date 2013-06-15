/*
    Copyright (C) 2007-2013  Olivier ROLAND (olivier.roland@edla.org)

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

import java.io.InputStream;
import java.util.Properties;

public class DicoProperties {

	public String root;

	public String xmlFile;

	public String wordsFile;

	public String exclusFile;

	public String language;

	public String languageShort;

	public boolean expression;

	private Properties prop = new Properties();

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

		//System.out.println(this.getClass().getPackage().getName());
		System.out.println("Your dictionary is being built. Please wait.");
		InputStream stream = this.getClass().getResourceAsStream(
				"dico.properties");
		try {
			prop.load(stream);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.root = getPropertySafely("root");
		this.wordsFile = getPropertySafely("wordsFile");
		this.exclusFile = getPropertySafely("excludedWordsFile");
		this.xmlFile = getPropertySafely("xmlFile");
		this.language = getPropertySafely("language");
		this.languageShort = getPropertySafely("languageShort");
		this.expression = Boolean.getBoolean(getPropertySafely("expression"));
	}

	private String getPropertySafely(String key) {

		try {
			String property = prop.getProperty(key);
			if (property == null) {
				System.out.println("no entry for the property : " + key);
				System.out
						.println("add it to your config file dico.properties and try again.");
				System.exit(1);
			}
			return property;
		} catch (Exception e) {
			System.out.println("Cannot load your config file dico.properties");
			System.out
					.println("Check/fix your config file dico.properties and try again.");
			System.exit(1);
			return null;
		}
	}
}

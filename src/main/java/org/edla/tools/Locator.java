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

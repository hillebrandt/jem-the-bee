/**
    JEM, the BEE - Job Entry Manager, the Batch Execution Environment
    Copyright (C) 2012-2015   Alessandro Zambrini
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.pepstock.jem.node.resources.definition.engine.xml;

import com.thoughtworks.xstream.XStream;

/**
 * This class represent single check box 
 * in the resource templates <code>xml</code> file. <br>
 * That is, it is the representation of a field in which the user can select
 * an item.<br>
 *  
 * @see XStream
 * @author Alessandro Zambrini
 */
public class CheckBoxFieldTemplate extends SingleValueFieldTemplate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Corresponding tag in the resource templates <code>xml</code> file.
	 * @see XStream
	 */
	public static final String MAPPED_XML_TAG = "check-box-field";
	
	
	/**
	 * Returns the default selected value of the this field.
	 * @return the defaultValue
	 */
	public boolean isDefaultValue() {
		return Boolean.parseBoolean(super.getDefaultValue());
	}

	/**
	 * Sets the default selected value of this field.
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(boolean defaultValue) {
		super.setDefaultValue(String.valueOf(defaultValue));
	}
	
}

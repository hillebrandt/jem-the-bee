/**
    JEM, the BEE - Job Entry Manager, the Batch Execution Environment
    Copyright (C) 2012-2014   Andrea "Stock" Stocchero
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
package org.pepstock.jem.gwt.server.rest.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andrea "Stock" Stocchero
 * @version 2.1
 */
@XmlRootElement
public class GfsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

	private String item = null;
	
	private String pathName = null;

	/**
	 * Empty constructor
	 */
    public GfsRequest() {

    }

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the pathName
	 */
	public String getPathName() {
		return pathName;
	}

	/**
	 * @param pathName the pathName to set
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
    
	
	
}
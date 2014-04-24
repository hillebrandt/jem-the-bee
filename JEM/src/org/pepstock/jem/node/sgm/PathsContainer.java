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
package org.pepstock.jem.node.sgm;

import java.io.Serializable;

/**
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public class PathsContainer implements Serializable{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	private Path current = null;
	
	private Path old = null;

	/**
	 * 
	 */
    public PathsContainer() {

    }

	/**
	 * @return the current
	 */
	public Path getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Path current) {
		this.current = current;
	}

	/**
	 * @return the old
	 */
	public Path getOld() {
		return old;
	}

	/**
	 * @param old the old to set
	 */
	public void setOld(Path old) {
		this.old = old;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PathsContainer [current=" + current + ", old=" + old + "]";
	}

}

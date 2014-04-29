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

import org.pepstock.jem.log.MessageException;
import org.pepstock.jem.node.NodeMessage;

/**
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public class InvalidDatasetNameException extends MessageException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param messageInterface
	 * @param objects
	 */
	public InvalidDatasetNameException(Object... objects) {
		super(NodeMessage.JEMC251E, objects);
	}

}
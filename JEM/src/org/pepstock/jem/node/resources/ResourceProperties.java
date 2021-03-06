/**
    JEM, the BEE - Job Entry Manager, the Batch Execution Environment
    Copyright (C) 2012-2015   Andrea "Stock" Stocchero
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
package org.pepstock.jem.node.resources;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Wraps a MAP with all reource properties of a resource. This is MANDATORY to use JSON and to apply a specific JSON module to de/serialize the keys 
 * 
 * @author Andrea "Stock" Stocchero
 * @version 3.0
 */
public class ResourceProperties extends HashMap<String, ResourceProperty> implements Map<String, ResourceProperty>, Serializable{

	private static final long serialVersionUID = 1L;

}

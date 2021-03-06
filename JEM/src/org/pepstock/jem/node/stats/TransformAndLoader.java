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
    
 Linking JEM, the BEE statically or dynamically with other modules is making a combined work based on JEM, the BEE. 
 Thus, the terms and conditions of the GNU General Public License cover the whole combination.

 As a special exception, the copyright holders of JEM, the BEE give you permission to combine JEM, the BEE program with 
 free software programs or libraries that are released under the GNU LGPL and with independent modules 
 that communicate with JEM, the BEE solely through the org.pepstock.jem.node.stats.TransformAndLoader interface. 
 You may copy and distribute such a system following the terms of the GNU GPL for JEM, the BEE and the licenses 
 of the other code concerned, provided that you include the source code of that other code when and as 
 the GNU GPL requires distribution of source code and provided that you do not modify the 
 org.pepstock.jem.node.stats.TransformAndLoader interface.

 Note that people who make modified versions of JEM, the BEE are not obligated to grant this special exception
 for their modified versions; it is their choice whether to do so. The GNU General Public License
 gives permission to release a modified version without this exception; this exception also makes it
 possible to release a modified version which carries forward this exception. If you modify the 
 org.pepstock.jem.node.stats.TransformAndLoader interface, this exception does not apply to your modified version of 
 JEM, the BEE, and you must remove this exception when you distribute your modified version.

 This exception is an additional permission under section 7 of the GNU General Public License, version 3
 (GPLv3)
     
*/
package org.pepstock.jem.node.stats;

import java.io.File;


/**
 * @author Andrea "Stock" Stocchero
 * @version 1.0	
 *
 */
public interface TransformAndLoader {
	
	/**
	 * 
	 * @param file
	 * @throws TransformAndLoaderException 
	 */
	void fileStarted(File file) throws TransformAndLoaderException;
	
	/**
	 * 
	 * @param sample
	 * @throws TransformAndLoaderException
	 */
	void loadSuccess(Sample sample) throws TransformAndLoaderException;
	
	/**
	 * 
	 * @param record 
	 * @param line 
	 * @param exception 
	 * @throws TransformAndLoaderException
	 */
	void loadFailed(String record, int line, Exception exception) throws TransformAndLoaderException;
	
	/**
	 * 
	 * @param file
	 * @throws TransformAndLoaderException 
	 */
	void fileEnded(File file) throws TransformAndLoaderException;

}
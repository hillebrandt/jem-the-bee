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
package org.pepstock.jem.junit.test.jppf;

import java.util.concurrent.Future;

import junit.framework.TestCase;

import org.pepstock.jem.commands.SubmitResult;
import org.pepstock.jem.junit.init.JemTestManager;

/**
 * 
 * @author Simone "Busy" Businaro
 * @version 1.4
 */
public class AntSubmit extends TestCase{
	
	
	/**
	 * Clean jobs created by the last junit run
	 * 
	 * @throws Exception
	 */
	public void testAntDatasource() throws Exception {
		Future<SubmitResult>future = JemTestManager.getSharedInstance()
				.submit(getJcl("ant/TEST_JPPF_ANT_Datasource.xml"), "ant", true,
						false);
		SubmitResult sr = future.get();
		assertEquals(sr.getRc(), 0);
	}
	
	/**
	 * Clean jobs created by the last junit run
	 * 
	 * @throws Exception
	 */
	public void testAntSimple() throws Exception {
		Future<SubmitResult>future = JemTestManager.getSharedInstance()
				.submit(getJcl("ant/TEST_JPPF_ANT_Simple.xml"), "ant", true,
						false);
		SubmitResult sr = future.get();
		assertEquals(sr.getRc(), 0);
	}
	
	/**
	 * Clean jobs created by the last junit run
	 * 
	 * @throws Exception
	 */
	public void testAntReader() throws Exception {
		Future<SubmitResult>future = JemTestManager.getSharedInstance()
				.submit(getJcl("ant/TEST_JPPF_ANT_Reader.xml"), "ant", true,
						false);
		SubmitResult sr = future.get();
		assertEquals(sr.getRc(), 0);
	}
	
	/**
	 * Clean jobs created by the last junit run
	 * 
	 * @throws Exception
	 */
	public void testAntWriter() throws Exception {
		Future<SubmitResult>future = JemTestManager.getSharedInstance()
				.submit(getJcl("ant/TEST_JPPF_ANT_Writer.xml"), "ant", true,
						false);
		SubmitResult sr = future.get();
		assertEquals(sr.getRc(), 0);
	}
	
	/**
	 * Clean jobs created by the last junit run
	 * 
	 * @throws Exception
	 */
	public void testAntReaderWriter() throws Exception {
		Future<SubmitResult>future = JemTestManager.getSharedInstance()
				.submit(getJcl("ant/TEST_JPPF_ANT_ReaderWriter.xml"), "ant", true,
						false);
		SubmitResult sr = future.get();
		assertEquals(sr.getRc(), 0);
	}
	
		
	/**
	 * 
	 * @param name
	 * @return
	 */
	private String getJcl(String name) {
		return this.getClass().getResource("jcls/" + name).toString();
	}



}

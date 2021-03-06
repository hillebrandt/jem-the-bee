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
package org.pepstock.jem.junit.test.antutils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Is the jnuit suite dedicated to test the ant utility provided by the
 * JEM-enterprise library and the ant utility provide by the JEM.
 * 
 * @author Simone "Busy" Businaro
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ Clean.class, CommonResourcesTask.class, StepJava.class, Wrapper.class, Procedure.class,
		ScriptTask.class, SortTask.class, 
		RolesTask.class, CertificateTask.class, CopyDataSet.class,
		GDGTask.class, NodeTask.class, Wait.class, Abend.class, Import.class })
public class AntUtilsSuite {

}

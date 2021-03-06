/**
    JEM, the BEE - Job Entry Manager, the Batch Execution Environment
    Copyright (C) 2012-2015  Marco "Fuzzo" Cuccato
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
package org.pepstock.jem.gwt.client.commons;

import com.google.gwt.user.cellview.client.SimplePager;

/**
 * Table pager with default style 
 * @author Marco "Fuzzo" Cuccato
 */
public class DefaultTablePager extends AbstractPager {

	static {
		DefaultTablePagerResources.INSTANCE.styles().ensureInjected();
	}

	/**
	 * Builds the pager, wrapping a {@link SimplePager}
	 * @param wrappedPager
	 */
	public DefaultTablePager(SimplePager wrappedPager) {
		super(wrappedPager);
		addStyleName(DefaultTablePagerResources.INSTANCE.styles().background());
	}
	
}
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
package org.pepstock.jem.gwt.client.panels.administration.gfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.jem.gwt.client.ResizeCapable;
import org.pepstock.jem.gwt.client.Sizes;
import org.pepstock.jem.gwt.client.charts.gflot.UsedFreePieChart;
import org.pepstock.jem.gwt.client.panels.administration.commons.AdminPanel;
import org.pepstock.jem.gwt.client.panels.administration.commons.Instances;
import org.pepstock.jem.gwt.client.panels.components.TableContainer;
import org.pepstock.jem.node.stats.FileSystemUtilization;
import org.pepstock.jem.node.stats.LightMemberSample;
import org.pepstock.jem.node.stats.LightMemberSampleComparator;
import org.pepstock.jem.node.stats.LightSample;

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Andrea "Stock" Stocchero
 * @author Marco "Fuzzo" Cuccato
 *
 */
public class OverviewPanel extends AdminPanel implements ResizeCapable {
	
	private UsedFreePieChart chart = new UsedFreePieChart();
	private TableContainer<LightMemberSample> gfs = new TableContainer<LightMemberSample>(new GfsTable());
	private ScrollPanel scroller = new ScrollPanel(gfs);
	private VerticalPanel entriesPanel = new VerticalPanel();
	
	/**
	 * @param fileSystemName file system name
	 */
	public OverviewPanel() {
		add(entriesPanel);
		add(scroller);
	}

	/**
	 * @param fsUtil 
	 * 
	 */
	public void load(FileSystemUtilization fsUtil) {
		chart.setUsedFreeData(fsUtil.getUsed(), fsUtil.getFree());
		LightMemberSample msample = Instances.getLastSample().getMembers().iterator().next();
    	List<LightMemberSample> list = new ArrayList<LightMemberSample>();
    	for (LightSample sample : Instances.getSamples()){
    		for (LightMemberSample membersample : sample.getMembers()){
    			if (membersample.getMemberKey().equalsIgnoreCase(msample.getMemberKey())){
    				list.add(membersample);
    			}
    		}
    	}
    	Collections.sort(list, new LightMemberSampleComparator());
    	GfsTable gTable = (GfsTable) gfs.getUnderlyingTable();
    	gTable.setFileSystemName(fsUtil.getName());
		gfs.getUnderlyingTable().setRowData(list);
		loadChart();
	}

	private void loadChart() {
		if (entriesPanel.getWidgetCount() == 0) {
			entriesPanel.add(chart.asWidget());
		}
    }

	/* (non-Javadoc)
	 * @see org.pepstock.jem.gwt.client.ResizeCapable#onResize(int, int)
	 */
    @Override
    public void onResize(int availableWidth, int availableHeight) {
    	super.onResize(availableWidth, availableHeight);
    	
    	int chartWidth = getWidth();
   	
		chart.setWidth(chartWidth);
		chart.setHeight(Sizes.CHART_HEIGHT);
		
	
		int height = getHeight() - Sizes.CHART_HEIGHT;
    	height = Math.max(height, 1);
		
		scroller.setHeight(Sizes.toString(height));
		scroller.setWidth(Sizes.toString(getWidth()));
    }

}
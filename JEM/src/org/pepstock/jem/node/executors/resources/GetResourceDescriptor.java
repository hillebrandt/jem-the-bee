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
package org.pepstock.jem.node.executors.resources;

import org.apache.shiro.subject.ExecutionException;
import org.pepstock.jem.node.Main;
import org.pepstock.jem.node.executors.DefaultExecutor;
import org.pepstock.jem.node.executors.ExecutorException;
import org.pepstock.jem.node.resources.definition.ResourceDefinition;
import org.pepstock.jem.node.resources.definition.ResourceDefinitionException;
import org.pepstock.jem.node.resources.definition.ResourceDescriptor;

/**
 * Returns the resource descriptor for a specific resource type.
 * 
 * @author Marco "Fuzzo" Cuccato
 * @version 1.4
 */
public class GetResourceDescriptor extends DefaultExecutor<ResourceDescriptor> {

	private static final long serialVersionUID = 1L;

	private String resourceType = null;
	
	/**
	 * Build the executor, with mandatory resource type
	 * @param resourceType the resource type
	 */
	public GetResourceDescriptor(String resourceType) {
		this.resourceType = resourceType;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.jem.node.executors.DefaultExecutor#execute()
	 */
	@Override
	public ResourceDescriptor execute() throws ExecutorException {
		try {
			// gets the resource definition
			ResourceDefinition definition = Main.RESOURCE_DEFINITION_MANAGER.getResourceDefinition(resourceType);
			// returns it
			return definition.getDescriptor();
		} catch (ResourceDefinitionException e) {
			// if here, the reource type doesn't exist
			throw new ExecutionException(e);
		} 
	}
}

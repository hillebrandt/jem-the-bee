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
package org.pepstock.jem.node.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.pepstock.jem.node.UpdateableItem;

/**
 * Entity of JEM which represents the role from security perspective with all permissions
 * and all related users
 * 
 * @author Andrea "Stock" Stocchero
 * @version 1.0
 *
 */
public class Role extends UpdateableItem implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
	private boolean removable = true;
	
	private List<String> permissions = new ArrayList<String>();

	private List<String> users = new ArrayList<String>();
	
	/**
	 * Creates a role with is name and if removable.
	 * The out-of-the-box roles are not removable. Only the custom ones.
	 * 
	 * @param name name of role
	 * @param removable <code>true</code> if the role is custom one and then removable 
	 */
	public Role(String name, boolean removable) {
		super.setName(name);
		this.removable = removable;
	}

	/**
	 * Creates a "removable" role 
	 * @param name name of role
	 * 
	 */
	public Role(String name) {
		this(name, true);
	}
	
	/**
	 * Empty constructor, used only for serialization purposes
	 */
	public Role(){
	}

	/**
	 * @return the removable
	 */
	public boolean isRemovable() {
		return removable;
	}

	/**
	 * @param removable the removable to set
	 */
	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	/**
	 * @return the permissions
	 */
	public List<String> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the users
	 */
	public List<String> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<String> users) {
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
	    return "Role [name=" + getName() + "]";
    }
}
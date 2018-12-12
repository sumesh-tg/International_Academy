/*
 * Copyright (C) 2016 100035
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.admin.master.userlogin.dao;

import java.util.Objects;

/**
 *
 * @author 100035
 */
public class RolePOJO {
   String roleId;
   String role;
   String roleText;

    /**
     *
     * @return
     */
    public String getRoleText() {
        return roleText;
    }

    /**
     *
     * @param roleText
     */
    public void setRoleText(String roleText) {
        this.roleText = roleText;
    }
   
    /**
     *
     * @return
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     *
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @param roleId
     * @param role
     */
    public RolePOJO(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    /**
     *
     */
    public RolePOJO() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.roleId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RolePOJO other = (RolePOJO) obj;
        if (!Objects.equals(this.roleId, other.roleId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return roleText;
    }
   
}

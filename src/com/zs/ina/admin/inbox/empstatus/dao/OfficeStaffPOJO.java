/*
 * Copyright (C) 2015 User04
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
package com.zs.ina.admin.inbox.empstatus.dao;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User04
 */
public class OfficeStaffPOJO {
    private final StringProperty username = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getUsername() {
        return username.get();
    }

    /**
     *
     * @param value
     */
    public void setUsername(String value) {
        username.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty usernameProperty() {
        return username;
    }

  //  String username, fullname;
    private final StringProperty fullname = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getFullname() {
        return fullname.get();
    }

    /**
     *
     * @param value
     */
    public void setFullname(String value) {
        fullname.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty fullnameProperty() {
        return fullname;
    }

    /**
     *
     */
    public OfficeStaffPOJO() {
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fullname != null ? fullname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfficeStaffPOJO other = (OfficeStaffPOJO) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        return true;
    }

}

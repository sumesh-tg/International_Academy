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
package com.zs.ina.search.colleges.visadocs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class VisadocBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty visadocId = new SimpleStringProperty();
    private final StringProperty collegeId = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();

    public String getVisadocId() {
        return visadocId.get();
    }

    public void setVisadocId(String value) {
        visadocId.set(value);
    }

    public StringProperty visadocIdProperty() {
        return visadocId;
    }
    
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getCollegeId() {
        return collegeId.get();
    }

    public void setCollegeId(String value) {
        collegeId.set(value);
    }

    public StringProperty collegeIdProperty() {
        return collegeId;
    }
    public String getRowId() {
        return rowId.get();
    }

    public void setRowId(String value) {
        rowId.set(value);
    }

    public StringProperty rowIdProperty() {
        return rowId;
    }

}

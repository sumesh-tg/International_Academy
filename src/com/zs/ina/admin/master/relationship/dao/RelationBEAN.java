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
package com.zs.ina.admin.master.relationship.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class RelationBEAN {
    private final StringProperty relationshipId = new SimpleStringProperty();
    private final StringProperty relationship = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRelationship() {
        return relationship.get();
    }

    /**
     *
     * @param value
     */
    public void setRelationship(String value) {
        relationship.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty relationshipProperty() {
        return relationship;
    }

    /**
     *
     * @return
     */
    public String getRelationshipId() {
        return relationshipId.get();
    }

    /**
     *
     * @param value
     */
    public void setRelationshipId(String value) {
        relationshipId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty relationshipIdProperty() {
        return relationshipId;
    }
    
}

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
package com.zs.ina.admin.master.methods.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class MethodBEAN {
    private final StringProperty methodId = new SimpleStringProperty();
    private final StringProperty methodName = new SimpleStringProperty();
    private final StringProperty methodType = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getMethodType() {
        return methodType.get();
    }

    /**
     *
     * @param value
     */
    public void setMethodType(String value) {
        methodType.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty methodTypeProperty() {
        return methodType;
    }

    /**
     *
     * @return
     */
    public String getMethodName() {
        return methodName.get();
    }

    /**
     *
     * @param value
     */
    public void setMethodName(String value) {
        methodName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty methodNameProperty() {
        return methodName;
    }

    /**
     *
     * @return
     */
    public String getMethodId() {
        return methodId.get();
    }

    /**
     *
     * @param value
     */
    public void setMethodId(String value) {
        methodId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty methodIdProperty() {
        return methodId;
    }
   
}

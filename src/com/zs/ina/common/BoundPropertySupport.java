/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.common;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class BoundPropertySupport {

    private final PropertyChangeSupport changeHandler;
    private final Map<ObservableValue, String> propertyNameMap;
    private final ChangeListener changeListener;

    public BoundPropertySupport(Object bean) {
        this.changeHandler = new PropertyChangeSupport(bean);
        this.propertyNameMap = new HashMap();
        this.changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String propertyName = BoundPropertySupport.this.propertyNameMap.get(observable);
                BoundPropertySupport.this.changeHandler.firePropertyChange(propertyName, oldValue, newValue);
            }
        };
    }

    public void raisePropertyChangeEventFor(Property property) {
        if (!this.propertyNameMap.containsKey(property)) {
            this.propertyNameMap.put(property, property.getName());
            property.addListener(this.changeListener);
        }
    }

    public void addChangeListener(PropertyChangeListener listener) {
        this.changeHandler.addPropertyChangeListener(listener);
    }

    public void removeChangeListener(PropertyChangeListener listener) {
        this.changeHandler.removePropertyChangeListener(listener);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.dipartment.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author shine
 */
public class DepartmentBEAN {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getDepartment() {
        return department.get();
    }

    /**
     *
     * @param value
     */
    public void setDepartment(String value) {
        department.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty departmentProperty() {
        return department;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id.get();
    }

    /**
     *
     * @param value
     */
    public void setId(String value) {
        id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty idProperty() {
        return id;
    }
    
}

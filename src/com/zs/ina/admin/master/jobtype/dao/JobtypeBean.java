/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.jobtype.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class JobtypeBean {
    private final StringProperty salary = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();

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

    /**
     *
     */
    public JobtypeBean() {
    }

    /**
     *
     * @return
     */
    public String getSalary() {
        return salary.get();
    }

    /**
     *
     * @param value
     */
    public void setSalary(String value) {
        salary.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty salaryProperty() {
        return salary;
    }
    
    
}

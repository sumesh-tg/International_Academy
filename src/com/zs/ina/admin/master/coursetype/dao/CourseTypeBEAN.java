/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.coursetype.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class CourseTypeBEAN {

    private final StringProperty courseType = new SimpleStringProperty();
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
     * @return
     */
    public String getCourseType() {
        return courseType.get();
    }

    /**
     *
     * @param value
     */
    public void setCourseType(String value) {
        courseType.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty courseTypeProperty() {
        return courseType;
    }

    @Override
    public String toString() {
        return "CourseTypeBEAN{" + "courseType=" + courseType + ", id=" + id + '}';
    }

}

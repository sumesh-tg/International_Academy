/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.migrationcategory.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class MigrationBEAN {

    private final StringProperty migrattionCategory = new SimpleStringProperty();
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
    public String getMigrattionCategory() {
        return migrattionCategory.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrattionCategory(String value) {
        migrattionCategory.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrattionCategoryProperty() {
        return migrattionCategory;
    }

    @Override
    public String toString() {
        return "MigrationBEAN{" + "migrattionCategory=" + migrattionCategory + ", id=" + id + '}';
    }

}

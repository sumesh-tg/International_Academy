/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.functionalarea.dao;

/**
 *
 * @author user
 */
public class FunctionalAreaPOJO {

    String operationArea, funtionalArea, level;
    int id;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getOperationArea() {
        return operationArea;
    }

    /**
     *
     * @param operationArea
     */
    public void setOperationArea(String operationArea) {
        this.operationArea = operationArea;
    }

    /**
     *
     * @return
     */
    public String getFuntionalArea() {
        return funtionalArea;
    }

    /**
     *
     * @param funtionalArea
     */
    public void setFuntionalArea(String funtionalArea) {
        this.funtionalArea = funtionalArea;
    }

    /**
     *
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "FunctionalAreaPOJO{" + "operationArea=" + operationArea + ", funtionalArea=" + funtionalArea + ", level=" + level + ", id=" + id + '}';
    }


}

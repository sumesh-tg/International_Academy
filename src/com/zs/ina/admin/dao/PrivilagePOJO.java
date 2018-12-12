/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.dao;

/**
 *
 * @author user
 */
public class PrivilagePOJO {

    String privilage;
    String privilageDesc;

    /**
     *
     */
    public PrivilagePOJO() {
    }

    /**
     *
     * @return
     */
    public String getPrivilage() {
        return privilage;
    }

    /**
     *
     * @param privilage
     */
    public void setPrivilage(String privilage) {
        this.privilage = privilage;
    }

    /**
     *
     * @return
     */
    public String getPrivilageDesc() {
        return privilageDesc;
    }

    /**
     *
     * @param privilageDesc
     */
    public void setPrivilageDesc(String privilageDesc) {
        this.privilageDesc = privilageDesc;
    }

    @Override
    public String toString() {
        return "PrivilagePOJO{" + "privilage=" + privilage + ", privilageDesc=" + privilageDesc + '}';
    }

}

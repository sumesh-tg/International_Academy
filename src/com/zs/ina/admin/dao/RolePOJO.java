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
public class RolePOJO {

    String role;
    String privilage;

    /**
     *
     */
    public RolePOJO() {
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "RolePOJO{" + "role=" + role + ", privilage=" + privilage + '}';
    }

}

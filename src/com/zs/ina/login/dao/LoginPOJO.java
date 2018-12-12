/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login.dao;

/**
 *
 * @author user
 */
public class LoginPOJO {

    private String userName;
    private String password;

    /**
     *
     */
    public LoginPOJO() {

    }

    /**
     *
     * @param userName
     * @param password
     */
    public LoginPOJO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", password=" + password + '}';
    }

}

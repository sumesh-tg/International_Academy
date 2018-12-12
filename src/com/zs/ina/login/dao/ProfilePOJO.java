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
public class ProfilePOJO {

    String id;
    String username, password, fname, lname;
    String role, email, phone, branch;

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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

    /**
     *
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     *
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @param lname
     */
    public void setLname(String lname) {
        this.lname = lname;
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
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "ProfilePOJO{" + "id=" + id + ", username=" + username + ", password=" + password + ", fname=" + fname + ", lname=" + lname + ", role=" + role + ", email=" + email + ", phone=" + phone + ", branch=" + branch + '}';
    }

}

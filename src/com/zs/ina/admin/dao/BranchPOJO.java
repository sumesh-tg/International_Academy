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
public class BranchPOJO {

    String branchName;
    String location;
    String address;
    String companyOwner;
    String branchOwner;

    /**
     *
     */
    public BranchPOJO() {
    }

    /**
     *
     * @return
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     *
     * @param branchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getCompanyOwner() {
        return companyOwner;
    }

    /**
     *
     * @param companyOwner
     */
    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    /**
     *
     * @return
     */
    public String getBranchOwner() {
        return branchOwner;
    }

    /**
     *
     * @param branchOwner
     */
    public void setBranchOwner(String branchOwner) {
        this.branchOwner = branchOwner;
    }

    @Override
    public String toString() {
        return "branchPOJO{" + "branchName=" + branchName + ", location=" + location + ", address=" + address + ", companyOwner=" + companyOwner + ", branchOwner=" + branchOwner + '}';
    }

}

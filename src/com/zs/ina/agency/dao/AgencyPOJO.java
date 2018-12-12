/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.agency.dao;

/**
 *
 * @author user
 */
public class AgencyPOJO {

    String userName, firstName, lastName;
    String branch, email;
    String phone, address;
    String userNameClmn, firstNameClmn, lastNameClmn, emailClmn, AddressClmn, branchClmn, phoneClmn;
    String agencyId;

    /**
     *
     * @return
     */
    public String getLastNameClmn() {
        return lastNameClmn;
    }

    /**
     *
     * @param lastNameClmn
     */
    public void setLastNameClmn(String lastNameClmn) {
        this.lastNameClmn = lastNameClmn;
    }

    /**
     *
     * @return
     */
    public String getEmailClmn() {
        return emailClmn;
    }

    /**
     *
     * @param emailClmn
     */
    public void setEmailClmn(String emailClmn) {
        this.emailClmn = emailClmn;
    }

    /**
     *
     * @return
     */
    public String getAddressClmn() {
        return AddressClmn;
    }

    /**
     *
     * @param AddressClmn
     */
    public void setAddressClmn(String AddressClmn) {
        this.AddressClmn = AddressClmn;
    }

    /**
     *
     * @return
     */
    public String getUserNameClmn() {
        return userNameClmn;
    }

    /**
     *
     * @return
     */
    public String getAgencyId() {
        return agencyId;
    }

    /**
     *
     * @param agencyId
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    /**
     *
     * @param userNameClmn
     */
    public void setUserNameClmn(String userNameClmn) {
        this.userNameClmn = userNameClmn;
    }

    /**
     *
     * @return
     */
    public String getFirstNameClmn() {
        return firstNameClmn;
    }

    /**
     *
     * @param firstNameClmn
     */
    public void setFirstNameClmn(String firstNameClmn) {
        this.firstNameClmn = firstNameClmn;
    }

    /**
     *
     * @return
     */
    public String getBranchClmn() {
        return branchClmn;
    }

    /**
     *
     * @param branchClmn
     */
    public void setBranchClmn(String branchClmn) {
        this.branchClmn = branchClmn;
    }

    /**
     *
     * @return
     */
    public String getPhoneClmn() {
        return phoneClmn;
    }

    /**
     *
     * @param phoneClmn
     */
    public void setPhoneClmn(String phoneClmn) {
        this.phoneClmn = phoneClmn;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
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

    @Override
    public String toString() {
        return "AgencyPOJO{" + "userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", branch=" + branch + ", email=" + email + ", phone=" + phone + ", address=" + address + '}';
    }

}

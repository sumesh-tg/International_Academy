/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.counselor.dao.model;

/**
 *
 * @author Administrator
 */
public class EnquiryCountsPOJO {
    String total;
    String assesed;
    String pending;
    String stared;
    String important;
    String registerd;
    String fakeCall;
    String wrongNumber;
    String followup;
    String msgcount;
    String appoinmentPending;
    String cancelled;

    /**
     *
     * @return
     */
    public String getMsgcount() {
        return msgcount;
    }

    /**
     *
     * @return
     */
    public String getCancelled() {
        return cancelled;
    }

    /**
     *
     * @param cancelled
     */
    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }

    /**
     *
     * @return
     */
    public String getAppoinmentPending() {
        return appoinmentPending;
    }

    /**
     *
     * @param appoinmentPending
     */
    public void setAppoinmentPending(String appoinmentPending) {
        this.appoinmentPending = appoinmentPending;
    }

    /**
     *
     * @param msgcount
     */
    public void setMsgcount(String msgcount) {
        this.msgcount = msgcount;
    }

    /**
     *
     * @return
     */
    public String getFollowup() {
        return followup;
    }

    /**
     *
     * @param followup
     */
    public void setFollowup(String followup) {
        this.followup = followup;
    }

    /**
     *
     * @return
     */
    public String getWrongNumber() {
        return wrongNumber;
    }

    /**
     *
     * @param wrongNumber
     */
    public void setWrongNumber(String wrongNumber) {
        this.wrongNumber = wrongNumber;
    }

    /**
     *
     * @return
     */
    public String getTotal() {
        return total;
    }

    /**
     *
     * @return
     */
    public String getRegisterd() {
        return registerd;
    }

    /**
     *
     * @return
     */
    public String getFakeCall() {
        return fakeCall;
    }

    /**
     *
     * @param fakeCall
     */
    public void setFakeCall(String fakeCall) {
        this.fakeCall = fakeCall;
    }

    /**
     *
     * @param registerd
     */
    public void setRegisterd(String registerd) {
        this.registerd = registerd;
    }

    /**
     *
     * @param total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     *
     * @return
     */
    public String getAssesed() {
        return assesed;
    }

    /**
     *
     * @param assesed
     */
    public void setAssesed(String assesed) {
        this.assesed = assesed;
    }

    /**
     *
     * @return
     */
    public String getPending() {
        return pending;
    }

    /**
     *
     * @param pending
     */
    public void setPending(String pending) {
        this.pending = pending;
    }

    /**
     *
     * @return
     */
    public String getStared() {
        return stared;
    }

    /**
     *
     * @param stared
     */
    public void setStared(String stared) {
        this.stared = stared;
    }

    /**
     *
     * @return
     */
    public String getImportant() {
        return important;
    }

    /**
     *
     * @param important
     */
    public void setImportant(String important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "EnquirySearchReport{" + "total=" + total + ", assesed=" + assesed + ", pending=" + pending + ", stared=" + stared + ", important=" + important + '}';
    }
    
}

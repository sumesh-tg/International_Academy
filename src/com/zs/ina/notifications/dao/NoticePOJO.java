/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.notifications.dao;

import java.util.Date;

/**
 *
 * @author zscomp1
 */
public class NoticePOJO {

    String id;
    String title, description, branch;
    Date expiration_date, created_date;

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
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     *
     * @return
     */
    public Date getExpiration_date() {
        return expiration_date;
    }

    /**
     *
     * @param expiration_date
     */
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    /**
     *
     * @return
     */
    public Date getCreated_date() {
        return created_date;
    }

    /**
     *
     * @param created_date
     */
    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "NoticePOJO{" + "id=" + id + ", title=" + title + ", description=" + description + ", branch=" + branch + ", expiration_date=" + expiration_date + ", created_date=" + created_date + '}';
    }

}

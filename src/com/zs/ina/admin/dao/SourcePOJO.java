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
public class SourcePOJO {

    int sourceId;
    String source, description;

    /**
     *
     * @return
     */
    public int getSourceId() {
        return sourceId;
    }

    /**
     *
     * @param sourceId
     */
    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    /**
     *
     * @return
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
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

    @Override
    public String toString() {
        return "SourcePOJO{" + "sourceId=" + sourceId + ", source=" + source + ", description=" + description + '}';
    }

}

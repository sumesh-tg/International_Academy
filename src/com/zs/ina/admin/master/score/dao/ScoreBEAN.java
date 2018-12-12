/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.score.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class ScoreBEAN {

    private final StringProperty score = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getId() {
        return id.get();
    }

    /**
     *
     * @param value
     */
    public void setId(String value) {
        id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty idProperty() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getScore() {
        return score.get();
    }

    /**
     *
     * @param value
     */
    public void setScore(String value) {
        score.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty scoreProperty() {
        return score;
    }

    @Override
    public String toString() {
        return "ScoreBEAN{" + "score=" + score + ", id=" + id + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.examboard.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class ExamBoardBean {
    private final StringProperty examBoard = new SimpleStringProperty();
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
     */
    public ExamBoardBean() {
    }

    /**
     *
     * @return
     */
    public String getExamBoard() {
        return examBoard.get();
    }

    /**
     *
     * @param value
     */
    public void setExamBoard(String value) {
        examBoard.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty examBoardProperty() {
        return examBoard;
    }

    
}

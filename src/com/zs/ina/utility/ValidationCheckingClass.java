/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.utility;

import javafx.geometry.Side;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ValidationCheckingClass {

    final static ContextMenu usernameValidator = new ContextMenu();

    /**
     *
     * @param comboBox
     * @param errorMsg
     * @return
     */
    public static boolean combolValidation(ComboBox comboBox, String errorMsg) {
        usernameValidator.hide();
        boolean f = true;
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem(errorMsg));
            usernameValidator.show(comboBox, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     * @param textField
     * @param errorMsg
     * @return
     */
    public static boolean textFieldValidation(TextField textField, String errorMsg) {
        usernameValidator.hide();
        boolean f = true;
        if (textField.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem(errorMsg));
            usernameValidator.show(textField, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     * @param datePicker
     * @param errorMsg
     * @return
     */
    public static boolean datePickerValidation(DatePicker datePicker, String errorMsg) {
        usernameValidator.hide();
        boolean f = true;
        if (datePicker.getValue() == null) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem(errorMsg));
            usernameValidator.show(datePicker, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     * @param textArea
     * @param errorMsg
     * @return
     */
    public static boolean textAreaValidation(TextArea textArea, String errorMsg) {
        usernameValidator.hide();
        boolean f = true;
        if (textArea.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem(errorMsg));
            usernameValidator.show(textArea, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }
}

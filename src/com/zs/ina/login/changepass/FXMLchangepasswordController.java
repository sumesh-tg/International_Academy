/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login.changepass;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.login.changepass.dao.ChangePasswordDAO;
import com.zs.ina.login.changepass.dao.ChangePasswordPOJO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLchangepasswordController implements Initializable, KeyListener {

    @FXML
    private TextField txtNew;
    @FXML
    private TextField txtNewCon;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtCurrent;
    ChangePasswordDAO passwordDAO;
    ChangePasswordPOJO passwordPOJO;
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuItem textCmd = new MenuItem("Colour Text");
        // TODO
        txtCurrent.setText(Context.getInstance().currentProfile().getProfilePOJO().getPassword());

    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        System.out.println("Change Password Clicked !");
        passwordDAO = new ChangePasswordDAO();
        passwordPOJO = new ChangePasswordPOJO();
        passwordPOJO.setCurPassword(txtCurrent.getText().trim());
        if (txtNew.getText() == null || txtCurrent.getText() == null || txtNewCon.getText() == null) {
            
            popupMessages.showError("All Fields Required", txtNew);
        } else {
            if (txtNew.getText().equalsIgnoreCase("") || txtCurrent.getText().equalsIgnoreCase("") || txtNewCon.getText().equalsIgnoreCase("")) {
                popupMessages.showError("All Fields Required", txtNew);
            } else {
                if (txtNew.getText().equalsIgnoreCase(txtNewCon.getText())) {
                    passwordPOJO.setNewPass(txtNew.getText().trim());
                    passwordPOJO.setRenewPass(txtNewCon.getText().trim());
                    int raw = passwordDAO.insert(passwordPOJO);
                    if (raw == 1) {
                        popupMessages.showSuccess("Saved Successfully", "Password Changed Successfully", txtNew);
                        Stage stage = (Stage) btnSave.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    popupMessages.showError("New Password And Confirm Password Are Not Equal", txtNew);
                }
            }
        }
    }

    private void clear() {
        txtCurrent.setText("");
        txtNew.setText("");
        txtNewCon.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @FXML
    private void handleBPressed(javafx.scene.input.KeyEvent event) {

    }

    @FXML
    private void handlehpreesss(javafx.scene.input.KeyEvent event) {
        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.R,
                KeyCombination.CONTROL_DOWN);
        if (keyComb1.match(event)) {

            System.out.println("Ctrl+R pressed");

        }
    }
}

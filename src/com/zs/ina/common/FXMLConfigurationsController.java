/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zsuser1
 */
public class FXMLConfigurationsController implements Initializable {

    @FXML
    private TextField txtServer;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtDb;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Label lblError;
    @FXML
    private Button btnClearData;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setText("");
        Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot
                .node("com.zs.ina.login.INALoginForm");
        if (myPrefs.get("server", "not found").equalsIgnoreCase("not found") || myPrefs.get("db", "not found").equalsIgnoreCase("not found")
                || myPrefs.get("user", "not found").equalsIgnoreCase("not found") || myPrefs.get("password", "not found").equalsIgnoreCase("not found")) {

        } else {
            txtServer.setText(myPrefs.get("server", "not found"));
            txtDb.setText(myPrefs.get("db", "not found"));
            txtPass.setText(myPrefs.get("password", "not found"));
            txtUser.setText(myPrefs.get("user", "not found"));
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                lblError.setText("");
                if (txtServer.getText() != null || txtDb.getText() != null || txtPass.getText() != null || txtUser.getText() != null) {
                    if (!txtServer.getText().equalsIgnoreCase("") || !txtDb.getText().equalsIgnoreCase("") || !txtPass.getText().equalsIgnoreCase("") || txtUser.getText().equalsIgnoreCase("")) {

                        /*============================ Not Working With Outside JAR =========================== */
                        //     ChangePropertiesFile.changePropertiesFile("DBPool.properties", "jdbc:mysql://" + txtServer.getText(), txtDb.getText(), txtUser.getText(), txtPass.getText());
                    /*========================== Store The Details in Java.util.prefs.preference =========================== */
                        myPrefs.put("server", txtServer.getText());
                        myPrefs.put("db", txtDb.getText());
                        myPrefs.put("user", txtUser.getText());
                        myPrefs.put("password", txtPass.getText());

                        /*========================== End Store The Details in Java.util.prefs.preference =========================== */
                        Stage stage = (Stage) btnSave.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    lblError.setText("All fields required !");
                }
            }
        });
        btnClearData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Preferences prefsRoot = Preferences.userRoot();
                    Preferences myPrefs = prefsRoot
                            .node("com.zs.ina.login.INALoginForm");
                    myPrefs.clear();
                    /* ====================== Clear Controls ====================== */
                    txtServer.clear();
                    txtDb.clear();
                    txtPass.clear();
                    txtUser.clear();
                } catch (BackingStoreException ex) {
                    Logger.getLogger(FXMLConfigurationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}

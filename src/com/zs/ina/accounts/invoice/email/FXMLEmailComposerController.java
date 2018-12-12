/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.accounts.invoice.email;

import com.zs.ina.common.email.MailMail;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLEmailComposerController implements Initializable {

    @FXML
    private ImageView imgClose;
    @FXML
    private HBox hboxCandidateName;
    @FXML
    private TextField txtToMail;
    @FXML
    private TextField txtCcMail;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtFromMail;
    @FXML
    private HTMLEditor htmEmailComposer;
    @FXML
    private CheckBox chkAttachInvoice;
    @FXML
    private TextField txtInvoiceFilename;
    @FXML
    private Hyperlink hyperAttachMore;
    @FXML
    private Button btnSaveLater;
    @FXML
    private Button btnSaveDraft;
    @FXML
    private Button btnSaveSend;
    @FXML
    private Button btnCancel;
    MailSentPOJO mailSentPOJO = new MailSentPOJO();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    MailMail mailMail = springAppContext.getBean(MailMail.class);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadButtonActions();
    }

    private void loadButtonActions() {

        /* ======================== Close Event ==================== */
        imgClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnCancel.fire();
            }
        });
        /* ======================== Show previouse stage ==================== */
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to cancel?", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnCancel.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    Stage stageClose = (Stage) imgClose.getScene().getWindow();
                    stageClose.close();
                }
            }
        });
        /* ======================== Send Email ==================== */
        btnSaveSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnSaveSend.setDisable(true);

                Task task = new Task() {
                    @Override
                    protected Boolean call() throws Exception {
                        mailSentPOJO = new MailSentPOJO();
                        mailSentPOJO.setTo(txtToMail.getText());
                        mailSentPOJO.setFrom(txtFromMail.getText());
                        mailSentPOJO.setSubject(txtSubject.getText());
                        mailSentPOJO.setCc(txtCcMail.getText());
                        mailSentPOJO.setMessage(htmEmailComposer.getHtmlText());
                        mailMail.sendMail(mailSentPOJO);
                        return true;
                    }
                };
                task.setOnSucceeded(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        btnSaveSend.setDisable(false);
                        Notifications.create()
                                .title("Invoice has been sent successfully!")
                                .text("Invoice has been sent  successfully!")
                                .showInformation();
                    }
                });
                task.setOnFailed(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        btnSaveSend.setDisable(false);
//                        Notifications.create()
//                                .title("Invoice has been sent successfully!")
//                                .text("Invoice has been sent  successfully!")
//                                .showInformation();
                    }
                });
                new Thread(task).start();
            }
        });
    }

}

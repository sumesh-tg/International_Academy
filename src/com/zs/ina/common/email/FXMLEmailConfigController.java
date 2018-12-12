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
package com.zs.ina.common.email;

import com.zs.ina.common.email.dao.MailSentDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLEmailConfigController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassWord;
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSMTP;
    @FXML
    private TextField txtTestMail;
    @FXML
    private Button btnSend;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    MailSentDAO mailSentDAO = springAppContext.getBean(MailSentDAO.class);
    MailSentPOJO mailSentPOJO = new MailSentPOJO();
    List<MailSentPOJO> mailSentPOJOs = new ArrayList<>();
    String signature = "";
    String messages = "";
    String emailSignature = "";
    String location = "";
    MailMail mailMail = springAppContext.getBean(MailMail.class);
    Map<String, String> mapEmailConfigShow = MailConfigDAO.retrieveEmailConfig();
    ShowPopupMessages showErrors = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Map<String, String> mapEmailConfigShow = MailConfigDAO.retrieveEmailConfig();
        txtEmail.setText(mapEmailConfigShow.get("email"));
        txtPassWord.setText(mapEmailConfigShow.get("pass"));
        txtSMTP.setText(mapEmailConfigShow.get("host"));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateEmailConfig()) {
                    Map<String, String> mapEmailConfig = new HashMap<>();
                    mapEmailConfig.put("email", txtEmail.getText());
                    mapEmailConfig.put("pass", txtPassWord.getText());
                    mapEmailConfig.put("host", txtSMTP.getText());
                    int done = MailConfigDAO.insertEmailConfig(mapEmailConfig);
                    if (done > 0) {
                        showPopupMessages.showSuccess("Email Configured Successfully!", "Email Configured Successfully!", btnSave);
                    }
                }
            }
        });
        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mailSentPOJO.setMessage("This is a test mail");
                mailSentPOJO.setSubject("Test Mail");
                mailSentPOJO.setFrom(mapEmailConfigShow.get("email"));
                mailSentPOJO.setTo(txtTestMail.getText().trim());

                Task testMail = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        mailMail.sendMail(mailSentPOJO);
                        return null;
                    }
                };
                new Thread(testMail).start();
                testMail.setOnSucceeded(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        showErrors.showSuccess("Sent Notification !", "Sent test mail Successfully !", btnSend);

                    }
                });
                new Thread(testMail).start();
            }
        });

    }

    /**
     *
     * @return
     */
    public boolean validateEmailConfig() {
        boolean flag = true;

        if (txtEmail.getText() == null || txtEmail.getText().equalsIgnoreCase("")) {
            showPopupMessages.showError("Please Enter Email Address", txtEmail);
            flag = false;
        } else if (mailValidation() == false) {
            flag = false;
            showPopupMessages.showError("Please Enter Valid Email Address", txtEmail);
        } else if (txtPassWord.getText() == null || txtPassWord.getText().equalsIgnoreCase("")) {
            flag = false;
            showPopupMessages.showError("Please Enter Password", txtPassWord);
        } else if (txtSMTP.getText() == null || txtSMTP.getText().equalsIgnoreCase("")) {
            showPopupMessages.showError("Please SMTP Server Name", txtPassWord);
            flag = false;
        }
        return flag;

    }

    /**
     *
     * @return
     */
    public Boolean mailValidation() {
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email1 = txtEmail.getText().trim();
        Boolean b = email1.matches(EMAIL_REGEX);
        return b;
    }
}

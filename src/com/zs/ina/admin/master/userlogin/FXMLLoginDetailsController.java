/*
 * Copyright (C) 2016 100035
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.admin.master.userlogin;

import com.zs.ina.admin.master.userlogin.dao.LoginBEAN;
import com.zs.ina.admin.master.userlogin.dao.LoginIMPL;
import com.zs.ina.admin.master.userlogin.dao.LoginSERVICE;
import com.zs.ina.admin.master.userlogin.dao.RolePOJO;
import com.zs.ina.common.ShowProgress;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.email.MailMail;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.common.email.dao.MailSentDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.notice.dao.BranchPOJO;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLLoginDetailsController implements Initializable {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<RolePOJO> cmbRole;
    @FXML
    private TextField txtPhoneNo;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtLastName;
    @FXML
    private ComboBox<BranchPOJO> cmbBranch;
    @FXML
    private TableView<LoginBEAN> tblUserLogin;
    @FXML
    private TableColumn<?, ?> colUserName;
    @FXML
    private TableColumn<?, ?> colFirstName;
    @FXML
    private TableColumn<?, ?> colLastName;
    @FXML
    private TableColumn<?, ?> colRole;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colPhoneNo;
    @FXML
    private TableColumn<?, ?> colBranch;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label lblNewUserTitle;
    @FXML
    private Button btnResetPassword;
    @FXML
    private HBox hboxUsername;
    @FXML
    private VBox vboxRoles;

    LoginBEAN loginBEAN = new LoginBEAN();
    LoginSERVICE loginSERVICE = new LoginSERVICE(new LoginIMPL());
    Logger logger = Logger.getLogger(FXMLLoginDetailsController.class);
    ObservableList<LoginBEAN> loginList = FXCollections.observableArrayList();
    ObservableList<BranchPOJO> branchList = FXCollections.observableArrayList();
    ObservableList<RolePOJO> roleList = FXCollections.observableArrayList();
    ObservableList<RolePOJO> roleEditList = FXCollections.observableArrayList();
    ObservableList<LoginBEAN> loginTableList = FXCollections.observableArrayList();
    ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
    ObservableList<String> privilagesList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    MailMail mailMail = springAppContext.getBean(MailMail.class);
    MailSentDAO mailSentDAO = springAppContext.getBean(MailSentDAO.class);
    List<MailSentPOJO> mailSentPOJOs = new ArrayList<>();
    String message = "";
    String emailSignature = "";
    ShowPopupMessages showSuccess = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ==================== init ================== */
        autoCompletion();
        branchList = loginSERVICE.retrieveBranches();
        cmbBranch.setItems(branchList);
        roleList = loginSERVICE.retrieveRoles();
        displayRoles(roleList);
        btnSave.setText("Save");
        bindAllData();
        viewLoginTable();
        btnResetPassword.disableProperty().bind(Bindings.equal(tblUserLogin.getSelectionModel().selectedIndexProperty(), -1));
        btnDelete.disableProperty().bind(Bindings.equal(tblUserLogin.getSelectionModel().selectedIndexProperty(), -1));

        cmbBranch.valueProperty().addListener(new ChangeListener<BranchPOJO>() {

            @Override
            public void changed(ObservableValue<? extends BranchPOJO> observable, BranchPOJO oldValue, BranchPOJO newValue) {
                if (newValue != null) {
                    loginBEAN.setBranchId(newValue.getBranchId());
                }
            }
        });
        cmbRole.valueProperty().addListener(new ChangeListener<RolePOJO>() {

            @Override
            public void changed(ObservableValue<? extends RolePOJO> observable, RolePOJO oldValue, RolePOJO newValue) {
                if (newValue != null) {
                    loginBEAN.setRoleId(newValue.getRoleId());

                }
            }
        });
        /* =========== save/update button ============ */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateAllFields()) {
                    if (loginBEAN.getLoginId() != null) {
                        String privilagesInString = privilagesList.toString();
                        String privilages = privilagesInString.replace("[", "");
                        privilages = privilages.replace("]", "");
                        loginBEAN.setPrivilages(privilages);
                        int result_update = loginSERVICE.updateLogin(loginBEAN);
                        MailSentPOJO mailSentPOJO = new MailSentPOJO();
                        mailSentPOJO.setEmailHead("USER CREATION");
                        mailSentPOJO.setEmailsubhead("UPDATE_USER_LOGIN");
                        if (result_update > 0) {
                            mailSentPOJOs.clear();
                            mailSentPOJOs = mailSentDAO.getEmailTemplateDetailsUsingSubhead(mailSentPOJO.getEmailsubhead());
                            if (mailSentPOJOs.size() > 0) {
                                for (MailSentPOJO mspojo : mailSentPOJOs) {
                                    if (message.equalsIgnoreCase("")) {
                                        message = message + mspojo.getSalutation();
                                        message = message.replace("[USER]", loginBEAN.getFirstName());
                                    }

                                    mailSentPOJO.setFrom(mspojo.getFrom());
                                    mailSentPOJO.setTo(loginBEAN.getEmail());
                                    mailSentPOJO.setSubject(mspojo.getSubject());
                                    message = message + mspojo.getContent();
                                    message = message.replace("[USERNAME]", loginBEAN.getUsername());
                                    message = message.replace("[EMAIL]", loginBEAN.getEmail());
                                    message = message.replace("[PHONE_NO]", loginBEAN.getPhoneNo());
                                    message = message.replace("[BRANCH]", cmbBranch.getSelectionModel().getSelectedItem().toString());
                                    message = message.replace("[DEFAULT_ROLE]", cmbRole.getSelectionModel().getSelectedItem().toString());
                                    message = message.replace("[ROLES_LIST]", cmbRole.getItems().toString());

                                    if (mspojo.getSignature() != null) {
                                        emailSignature = mspojo.getSignature();
                                    }
                                    if (mspojo.getSignature() == null) {
                                        emailSignature = "International Academy,2nd floor, Bright House,Karimpatta Cross Road,Pallimukku, Ernakulam Cochin (Kochi)-16";
                                    }

                                }
                                message = message.replace("null", "");
                                mailSentPOJO.setMessage(message + emailSignature);

                                Task taskUserCreation = new Task() {
                                    @Override
                                    protected Object call() throws Exception {
                                        mailMail.sendMail(mailSentPOJO);
                                        return null;
                                    }
                                };
                                taskUserCreation.setOnSucceeded(new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        showSuccess.showSuccess("Sent Notification !", "User Login Updation Notification sent successfully !", btnResetPassword);

                                    }
                                });
                                new Thread(taskUserCreation).start();
                            }
                            clearAllFields();
                            viewLoginTable();
                            checkList.clear();
                            vboxRoles.getChildren().clear();
                            displayRoles(roleList);
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Login details are updated", "Login Details updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Login details are not updated", "Login Details are not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                        }

                    } else {
                        String privilagesInString = privilagesList.toString();
                        String privilages = privilagesInString.replace("[", "");
                        privilages = privilages.replace("]", "");
                        loginBEAN.setPrivilages(privilages);
                        String password = UiiDGenerator.getUIID8();
                        loginBEAN.setPassword(password);
                        int result_insert = loginSERVICE.insertLogin(loginBEAN);
                        MailSentPOJO mailSentPOJO = new MailSentPOJO();
                        mailSentPOJO.setEmailHead("USER CREATION");
                        mailSentPOJO.setEmailsubhead("CREATE_USER_LOGIN");
                        if (result_insert > 0) {
                            mailSentPOJOs.clear();
                            mailSentPOJOs = mailSentDAO.getEmailTemplateDetailsUsingSubhead(mailSentPOJO.getEmailsubhead());
                            if (mailSentPOJOs.size() > 0) {
                                for (MailSentPOJO mspojo : mailSentPOJOs) {
                                    if (message.equalsIgnoreCase("")) {
                                        message = message + mspojo.getSalutation();
                                        message = message.replace("[USER]", loginBEAN.getFirstName());
                                    }

                                    mailSentPOJO.setFrom(mspojo.getFrom());
                                    mailSentPOJO.setTo(loginBEAN.getEmail());
                                    mailSentPOJO.setSubject(mspojo.getSubject());
                                    message = message + mspojo.getContent();
                                    message = message.replace("[USERNAME]", loginBEAN.getUsername());
                                    message = message.replace("[PASSWORD]", loginBEAN.getPassword());
                                    message = message.replace("[EMAIL]", loginBEAN.getEmail());
                                    message = message.replace("[PHONE_NO]", loginBEAN.getPhoneNo());
                                    message = message.replace("[BRANCH]", cmbBranch.getSelectionModel().getSelectedItem().toString());
                                    message = message.replace("[DEFAULT_ROLE]", cmbRole.getSelectionModel().getSelectedItem().toString());
                                    message = message.replace("[ROLES_LIST]", cmbRole.getItems().toString());

                                    if (mspojo.getSignature() != null) {
                                        emailSignature = mspojo.getSignature();
                                    }
                                    if (mspojo.getSignature() == null) {
                                        emailSignature = "International Academy,2nd floor, Bright House,Karimpatta Cross Road,Pallimukku, Ernakulam Cochin (Kochi)-16";
                                    }

                                }
                                message = message.replace("null", "");
                                mailSentPOJO.setMessage(message + emailSignature);
                                Task taskUserCreation = new Task() {
                                    @Override
                                    protected Object call() throws Exception {
                                        mailMail.sendMail(mailSentPOJO);
                                        return null;
                                    }
                                };
                                new Thread(taskUserCreation).start();
                                taskUserCreation.setOnSucceeded(new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        showSuccess.showSuccess("Sent Notification !", "User Creation Notification sent successfully !", btnResetPassword);

                                    }
                                });
                            }

                            clearAllFields();
                            viewLoginTable();
                            checkList.clear();
                            vboxRoles.getChildren().clear();
                            displayRoles(roleList);
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Login Details are saved", "Login Details saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Login Details are not saved", "Login Details are not saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                        }

                    }

                }

            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (loginBEAN.getUsername() == null || loginBEAN.getUsername().equals("")) {
                    showPopupMessages.showError("Enter username", txtUsername);
                    flag = false;
                } else if (loginBEAN.getFirstName() == null || loginBEAN.getFirstName().equals("")) {
                    showPopupMessages.showError("Enter first name", txtFirstName);
                    flag = false;
                } else if (loginBEAN.getLastName() == null || loginBEAN.getLastName().equals("")) {
                    showPopupMessages.showError("Enter last name", txtLastName);
                    flag = false;
                } else if (cmbRole.getSelectionModel().getSelectedIndex() == -1) {
                    showPopupMessages.showError("Select role", cmbRole);
                    flag = false;

                } else if (loginBEAN.getEmail() == null || loginBEAN.getEmail().equals("") || mailValidation() == false) {
                    showPopupMessages.showError("Enter valid email address", txtEmail);
                    flag = false;
                } else if (loginBEAN.getPhoneNo() == null || loginBEAN.getPhoneNo().equals("")) {
                    showPopupMessages.showError("Enter phone no", txtPhoneNo);
                    flag = false;
                } else if (loginBEAN.getPhoneNo().length() > 10 || loginBEAN.getPhoneNo().length() < 10) {
                    showPopupMessages.showError("Enter valid phone no", txtPhoneNo);
                    flag = false;
                } else if (cmbBranch.getSelectionModel().getSelectedIndex() == -1) {
                    showPopupMessages.showError("Select branch", cmbBranch);
                    flag = false;
                }

                return flag;

            }

            public Boolean mailValidation() {
                String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                String email1 = txtEmail.getText().trim();
                Boolean b = email1.matches(EMAIL_REGEX);
                return b;
            }

        });

        txtUsername.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,0-9,_]*"))) {
                        Bindings.unbindBidirectional(loginBEAN.usernameProperty(), txtUsername.textProperty());
                        txtUsername.setText(oldValue);
                        Bindings.bindBidirectional(loginBEAN.usernameProperty(), txtUsername.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(loginBEAN.usernameProperty(), txtUsername.textProperty());
                        txtUsername.setText(newValue);
                        Bindings.bindBidirectional(loginBEAN.usernameProperty(), txtUsername.textProperty());
                    }

                }
            }

        });

        txtFirstName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(loginBEAN.firstNameProperty(), txtFirstName.textProperty());
                        txtFirstName.setText(oldValue);
                        Bindings.bindBidirectional(loginBEAN.firstNameProperty(), txtFirstName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(loginBEAN.firstNameProperty(), txtFirstName.textProperty());
                        txtFirstName.setText(newValue);
                        Bindings.bindBidirectional(loginBEAN.firstNameProperty(), txtFirstName.textProperty());
                    }

                }
            }

        });
        txtLastName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(loginBEAN.lastNameProperty(), txtLastName.textProperty());
                        txtLastName.setText(oldValue);
                        Bindings.bindBidirectional(loginBEAN.lastNameProperty(), txtLastName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(loginBEAN.lastNameProperty(), txtLastName.textProperty());
                        txtLastName.setText(newValue);
                        Bindings.bindBidirectional(loginBEAN.lastNameProperty(), txtLastName.textProperty());
                    }

                }
            }

        });
        txtPhoneNo.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(loginBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                        txtPhoneNo.setText(oldValue);
                        Bindings.bindBidirectional(loginBEAN.phoneNoProperty(), txtPhoneNo.textProperty());

                    } else if (newValue.length() <= 10) {
                        txtPhoneNo.setText(newValue);
                    } else {
                        Bindings.unbindBidirectional(loginBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                        txtPhoneNo.setText(oldValue);
                        Bindings.bindBidirectional(loginBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                    }

                }
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int result_delete = loginSERVICE.deleteLogin(loginBEAN.getLoginId());
                if (result_delete > 0) {

                    clearAllFields();
                    viewLoginTable();
                    checkList.clear();
                    privilagesList.clear();
                    vboxRoles.getChildren().clear();
                    displayRoles(roleList);
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Login details deleted", "Login details deleted successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Notice details not deleted", "Notice not deleted successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                }
            }
        });

        btnResetPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String password = UiiDGenerator.getUIID8();
                loginBEAN.setPassword(password);
                int result_update_password = loginSERVICE.resetPassword(loginBEAN);
                MailSentPOJO mailSentPOJO = new MailSentPOJO();
                mailSentPOJO.setEmailHead("USER CREATION");
                mailSentPOJO.setEmailsubhead("RESET_PASSWORD");
                if (result_update_password > 0) {

                    mailSentPOJOs.clear();
                    mailSentPOJOs = mailSentDAO.getEmailTemplateDetailsUsingSubhead(mailSentPOJO.getEmailsubhead());
                    if (mailSentPOJOs.size() > 0) {
                        for (MailSentPOJO mspojo : mailSentPOJOs) {
                            if (message.equalsIgnoreCase("")) {
                                message = message + mspojo.getSalutation();
                                message = message.replace("[USER]", loginBEAN.getFirstName());
                            }
                            mailSentPOJO.setFrom(mspojo.getFrom());
                            mailSentPOJO.setTo(loginBEAN.getEmail());
                            mailSentPOJO.setSubject(mspojo.getSubject());
                            message = message + mspojo.getContent();
                            message = message.replace("[USERNAME]", loginBEAN.getUsername());
                            message = message.replace("[PASSWORD]", loginBEAN.getPassword());
                            if (mspojo.getSignature() != null) {
                                emailSignature = mspojo.getSignature();
                            }
                            if (mspojo.getSignature() == null) {
                                emailSignature = "International Academy,2nd floor, Bright House,Karimpatta Cross Road,Pallimukku, Ernakulam Cochin (Kochi)-16";
                            }

                        }
                        message = message.replace("null", "");
                        mailSentPOJO.setMessage(message + emailSignature);
                        Task taskResetPassword = new Task() {
                            @Override
                            protected Object call() throws Exception {
                                mailMail.sendMail(mailSentPOJO);
                                return null;
                            }
                        };
                        new Thread(taskResetPassword).start();
                        taskResetPassword.setOnSucceeded(new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                showSuccess.showSuccess("Sent Notification !", "Reset Password Notification sent Successfully !", btnResetPassword);

                            }
                        });
                        clearAllFields();
                        viewLoginTable();
                        checkList.clear();
                        privilagesList.clear();
                        vboxRoles.getChildren().clear();
                        displayRoles(roleList);
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("New Password send", "New Password sent successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                    }
                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Password not send", "Password not send successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3), txtLastName);

                }

            }
        }
        );

        btnClear.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {
                clearAllFields();
            }
        }
        );

        tblUserLogin.setRowFactory(
                new Callback<TableView<LoginBEAN>, TableRow<LoginBEAN>>() {

            @Override
            public TableRow<LoginBEAN> call(TableView<LoginBEAN> param
            ) {
                final TableRow<LoginBEAN> row = new TableRow<LoginBEAN>() {
                    @Override
                    protected void updateItem(LoginBEAN loginBEAN, boolean empty) {
                        super.updateItem(loginBEAN, empty);
                        if (loginBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                clearAllFields();
                                loginBEAN = new LoginBEAN();
                                loginBEAN.loginIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), loginBEAN);
                                bindAllData();

                                for (BranchPOJO bpojo : cmbBranch.getItems()) {
                                    if (bpojo.getBranch().equals(loginBEAN.getBranchId())) {
                                        cmbBranch.getSelectionModel().select(bpojo);
                                    }
                                }
                                checkList.clear();
                                privilagesList.clear();
                                vboxRoles.getChildren().clear();
                                String privilag = loginBEAN.getPrivilages();
                                roleEditList = loginSERVICE.retrieveRolesUsingPrivilages(privilag);

//Relaod
                                System.out.println("roleEditList EDIT LIST ==== " + roleEditList);
                                for (RolePOJO rolePOJO : roleList) {
                                    if (rolePOJO != null) {
                                        CheckBox chkRole = new CheckBox();
                                        chkRole.setText(rolePOJO.getRoleText());
                                        if (roleEditList.contains(rolePOJO)) {
                                            chkRole.setSelected(true);
                                            privilagesList.add(rolePOJO.getRoleId());
                                            cmbRole.getItems().add(rolePOJO);
                                        }
                                        chkRole.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                            }

                                        });

                                        chkRole.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                            @Override
                                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                                if (newValue) {
                                                    System.out.println("inside EDIT combo add");
                                                    privilagesList.add(rolePOJO.getRoleId());
                                                    cmbRole.getItems().add(rolePOJO);

                                                } else {
                                                    privilagesList.remove(rolePOJO.getRoleId());
                                                    cmbRole.getItems().remove(rolePOJO);
                                                }
                                            }
                                        });
                                        checkList.add(chkRole);
                                    }
                                }

                                vboxRoles.getChildren().addAll(checkList);
                                for (RolePOJO rpojo : cmbRole.getItems()) {
                                    if (rpojo.getRole().equals(loginBEAN.getRole())) {
                                        cmbRole.getSelectionModel().select(rpojo);
                                    }
                                }

                            }

                        }
                    }

                });
                return row;
            }

        }
        );

    }

    private void viewLoginTable() {
        loginTableList.clear();
        loginTableList = loginSERVICE.retrieveLogin();
        addLoginIntoTable(loginTableList);
    }

    private void addLoginIntoTable(ObservableList<LoginBEAN> loginTableList) {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("roleText"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        ObservableList<LoginBEAN> loginBEANs = FXCollections.observableList(loginTableList);
        FilteredList<LoginBEAN> filteredData = new FilteredList<LoginBEAN>(loginBEANs, login -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(login -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (login.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (login.getFirstName() == null) {
                    return false;
                } else if (login.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (login.getLastName() == null) {
                    return false;
                }else if (login.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (login.getRoleId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (login.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (login.getPhoneNo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (login.getBranchId() == null) {
                    return false;
                }else if (login.getBranchId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<LoginBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblUserLogin.comparatorProperty());
        tblUserLogin.setItems(sortedData);
    }

    /**
     *
     */
    public void bindAllData() {
        Bindings.bindBidirectional(txtUsername.textProperty(), loginBEAN.usernameProperty());
        Bindings.bindBidirectional(txtFirstName.textProperty(), loginBEAN.firstNameProperty());
        Bindings.bindBidirectional(txtLastName.textProperty(), loginBEAN.lastNameProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), loginBEAN.emailProperty());
        Bindings.bindBidirectional(txtPhoneNo.textProperty(), loginBEAN.phoneNoProperty());

    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        cmbBranch.getSelectionModel().clearSelection();
        checkList.clear();
        cmbRole.getItems().clear();
        loginBEAN = new LoginBEAN();
        bindAllData();
        loginBEAN.loginIdProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }
        });
        txtSearch.setText("");

    }

    private void autoCompletion() {
        List<String> allBranches = LoginIMPL.getAllUsernames();
        txtUsername = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtUsername, allBranches);
        txtUsername.setPrefWidth(285);
        hboxUsername.getChildren().remove(1);
        hboxUsername.getChildren().add(txtUsername);

    }

    private void displayRoles(ObservableList<RolePOJO> roleList) {

        for (RolePOJO rolePOJO : roleList) {
            if (rolePOJO != null) {
                CheckBox chkRole = new CheckBox();
                chkRole.setText(rolePOJO.getRoleText());
                chkRole.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                    }
                });
                chkRole.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            privilagesList.add(rolePOJO.getRoleId());
                            cmbRole.getItems().add(rolePOJO);

                        } else {
                            privilagesList.remove(rolePOJO.getRoleId());
                            cmbRole.getItems().remove(rolePOJO);
                        }
                    }
                });
                checkList.add(chkRole);
            }
        }
        vboxRoles.getChildren().addAll(checkList);
    }
}

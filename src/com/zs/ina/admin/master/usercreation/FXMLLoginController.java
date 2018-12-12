/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.usercreation;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.admin.dao.AdminDAO.LoginDetails;
import com.zs.ina.admin.dao.LoginPOJO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.email.E_MailSender;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.context.Context;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField userNameTxt;
    @FXML
    private ComboBox<?> roleCmb;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private ComboBox<?> branchCmb;
    ObservableList roles = FXCollections.observableArrayList();
    ObservableList branches = FXCollections.observableArrayList();
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private HBox userNameHbox;
    static String loginId = "";
    @FXML
    private Button deleteBtn;
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private TableView<LoginDetails> loginTableView;
    @FXML
    private TableColumn<LoginDetails, String> userNameColumn;
    @FXML
    private TableColumn<LoginDetails, String> firstNameColumn;
    @FXML
    private TableColumn<LoginDetails, String> lastNameColumn;
    @FXML
    private TableColumn<LoginDetails, String> roleColumn;
    @FXML
    private TableColumn<LoginDetails, String> emailColumn;
    @FXML
    private TableColumn<LoginDetails, String> phoneColumn;
    @FXML
    private TableColumn<LoginDetails, String> branchColumn;
    @FXML
    private TextField searchTxt;
    private ObservableList<AdminDAO.LoginDetails> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Button exelUploadbtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        getRoleDetails();
        getTableDetails();
        getAllTableDetails();
        addChangeEvents();
        List<String> privilage = AdminDAO.getAllRole();
        for (String s : privilage) {
            roles.add(s);
        }
        roleCmb.setItems(roles);
        roleCmb.getSelectionModel().selectFirst();

        List<String> branch = AdminDAO.getAllBranches();
        for (String s : branch) {
            branches.add(s);
        }
        branchCmb.setItems(branches);
        branchCmb.getSelectionModel().selectFirst();

        userNameTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (newPropertyValue) {
                                    usernameValidator.hide();
                                }
                            }
                });
        phoneTxt.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = phoneTxt.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9') || phoneTxt.getText().length() > 10) {
                        phoneTxt.setText(phoneTxt.getText().substring(0, phoneTxt.getText().length() - 1));
                    }
                }
            }

        });
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) throws MessagingException {
        if (loginFormValidation()) {
            LoginPOJO loginPOJO = new LoginPOJO();
            loginPOJO.setFirstName(firstNameTxt.getText());
            loginPOJO.setLastName(lastNameTxt.getText());
            loginPOJO.setUserName(userNameTxt.getText());
            loginPOJO.setEmail(emailTxt.getText());
            loginPOJO.setPhone(phoneTxt.getText());
            loginPOJO.setRole(roleCmb.getSelectionModel().getSelectedItem().toString());
            loginPOJO.setBranch(branchCmb.getSelectionModel().getSelectedItem().toString());
            String newLoginId = "id_" + UiiDGenerator.getUIID8();
            Context.getInstance().currentProfile().setLoginPOJO(loginPOJO);
            if (!loginId.equals("")) {
                int row = AdminDAO.updateLoginDetails(loginId);
                if (row > 0) {
                    clearFields();
                    getAllTableDetails();
                }
            } else {
                loginPOJO.setLoginId(newLoginId);
                int row = AdminDAO.insertLoginDetails();
                Task task = new Task() {

                    @Override
                    protected Object call() throws Exception {
                        MailSentPOJO mailSentPOJO = new MailSentPOJO();
                        mailSentPOJO.setTo(loginPOJO.getEmail());
                        mailSentPOJO.setFrom("ia.offer@gmail.com");
                        String message = "";
                        message = message + "Your username is :  " + loginPOJO.getUserName();
                        message = message + "\n Your password is :  " + AdminDAO.mailSend(newLoginId);
                        mailSentPOJO.setContent(message);
                        E_MailSender.sendMail(mailSentPOJO);
                        //    MailSendController.sendMail(loginPOJO, AdminDAO.mailSend(newLoginId));
                        return null;
                    }
                };
                if (row > 0) {
                    new Thread(task).start();
                    clearFields();
                    getAllTableDetails();
                }
            }
        }
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clearFields();
    }

    /**
     *
     */
    public void clearFields() {
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        userNameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        roleCmb.getSelectionModel().selectFirst();
        branchCmb.getSelectionModel().selectFirst();
        loginId = "";
        saveBtn.setText("Save");
        searchTxt.setText("");
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allUserNames = AdminDAO.getAllUserName();
        userNameTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(userNameTxt, allUserNames);
        userNameTxt.setPrefWidth(163);
        userNameHbox.getChildren().remove(0);
        userNameHbox.getChildren().add(userNameTxt);
    }

    /**
     *
     */
    public void getRoleDetails() {
        userNameTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (!userNameTxt.getText().equals("")) {
                                    List<LoginDetails> loginListList = AdminDAO.getLoginDetails(userNameTxt.getText());
                                    for (LoginDetails ld : loginListList) {
                                        loginId = ld.getLoginId();
                                        userNameTxt.setText(ld.getUserName());
                                        roles.remove(ld.getRole());
                                        roles.add(ld.getRole());
                                        roleCmb.setItems(roles);
                                        roleCmb.getSelectionModel().selectLast();
                                        firstNameTxt.setText(ld.getFirstName());
                                        lastNameTxt.setText(ld.getLastName());
                                        emailTxt.setText(ld.getEmail());
                                        phoneTxt.setText(ld.getPhone());
                                        branches.remove(ld.getBranch());
                                        branches.add(ld.getBranch());
                                        branchCmb.setItems(branches);
                                        branchCmb.getSelectionModel().selectLast();
                                    }
                                }
                            }
                });
        userNameTxt.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (userNameTxt.getText().equals("")) {
                    clearFields();
                }
            }
        }
        );

        userNameTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ke) {
                if (userNameTxt.getText().equals("")) {
                    clearFields();
                }
            }

        });
    }

    /**
     *
     */
    public void addChangeEvents() {
        lastNameTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    lastNameTxt.setText(newValue);
                } else {
                    lastNameTxt.setText(oldValue);
                }

            }
        });

        firstNameTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    firstNameTxt.setText(newValue);
                } else {
                    firstNameTxt.setText(oldValue);
                }

            }
        });

        phoneTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[0-9]*")) {
                    phoneTxt.setText(newValue);
                } else {
                    phoneTxt.setText(oldValue);
                }

            }
        });
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            AdminDAO.deleteLoginDetails(loginId);
            getAllTableDetails();
            clearFields();
        }
    }

    //Method for login form validation
    /**
     *
     * @return
     */
    public boolean loginFormValidation() {
        boolean f = true;
        if (userNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter user name! "));
            usernameValidator.show(userNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (firstNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter first name! "));
            usernameValidator.show(firstNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (lastNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter last name! "));
            usernameValidator.show(lastNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (emailTxt.getText().equals("") || mailValidation() == false) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter valid email id! "));
            usernameValidator.show(emailTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (phoneTxt.getText().equals("") || phoneTxt.getText().length() < 10) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter valid phone no! "));
            usernameValidator.show(phoneTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     * @return
     */
    public Boolean mailValidation() {
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email1 = emailTxt.getText().trim();
        Boolean b = email1.matches(EMAIL_REGEX);
        return b;
    }

    /**
     *
     */
    public void getTableDetails() {
        List<AdminDAO.LoginDetails> loginList = AdminDAO.getAllLoginDetails();
        userNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("userName"));
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role"));
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch"));
        masterSearchData.removeAll(masterSearchData);
        for (AdminDAO.LoginDetails ld : loginList) {
            masterSearchData.add(ld);
        }

        FilteredList<AdminDAO.LoginDetails> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getBranch().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                }
                return false; // Does not match.
            });
            SortedList<AdminDAO.LoginDetails> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(loginTableView.comparatorProperty());
            loginTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<AdminDAO.LoginDetails> loginList = AdminDAO.getAllLoginDetails();
        userNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("userName"));
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role"));
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch"));
        masterSearchData.removeAll(masterSearchData);
        for (LoginDetails s : loginList) {
            masterSearchData.add(s);
        }
        loginTableView.setItems(masterSearchData);
    }

    @FXML
    private void handleLoginTableView(MouseEvent event) {
        saveBtn.setText("Update");
        LoginDetails loginDetails = (LoginDetails) loginTableView.getSelectionModel().getSelectedItem();
        userNameTxt.setText(loginDetails.getUserName());
        firstNameTxt.setText(loginDetails.getFirstName());
        lastNameTxt.setText(loginDetails.getLastName());
        if (roleCmb.getItems().contains(loginDetails.getRole())) {
            roleCmb.getSelectionModel().clearAndSelect(roleCmb.getItems().indexOf(loginDetails.getRole()));
        }
        emailTxt.setText(loginDetails.getEmail());
        phoneTxt.setText(loginDetails.getPhone());
        if (branchCmb.getItems().contains(loginDetails.getBranch())) {
            branchCmb.getSelectionModel().clearAndSelect(branchCmb.getItems().indexOf(loginDetails.getBranch()));
        }
        loginId = loginDetails.getLoginId();
    }

    @FXML
    private void handleLoginTableViewKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        LoginDetails loginDetails = (LoginDetails) loginTableView.getSelectionModel().getSelectedItem();
        userNameTxt.setText(loginDetails.getUserName());
        firstNameTxt.setText(loginDetails.getFirstName());
        lastNameTxt.setText(loginDetails.getLastName());
        if (roleCmb.getItems().contains(loginDetails.getRole())) {
            roleCmb.getSelectionModel().clearAndSelect(roleCmb.getItems().indexOf(loginDetails.getRole()));
        }
        emailTxt.setText(loginDetails.getEmail());
        phoneTxt.setText(loginDetails.getPhone());
        if (branchCmb.getItems().contains(loginDetails.getBranch())) {
            branchCmb.getSelectionModel().clearAndSelect(branchCmb.getItems().indexOf(loginDetails.getBranch()));
        }
        loginId = loginDetails.getLoginId();
    }

    @FXML
    private void handleExeluploadBtn(ActionEvent event) {
//        ExcelUpload excelUpload = new ExcelUpload();
//        excelUpload.exelFileChooser("branch_tbl");
    }

}

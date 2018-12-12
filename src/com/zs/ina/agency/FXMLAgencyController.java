/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.agency;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.agency.dao.AgencyDAO;
import com.zs.ina.agency.dao.AgencyPOJO;
import com.zs.ina.context.Context;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLAgencyController implements Initializable {

    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private ComboBox<String> branchCmb;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextArea addressTxtArea;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    ObservableList branch = FXCollections.observableArrayList();
    // private TextField userNameTxt;
    static String agencyId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private TableView<AgencyPOJO> agencyTable;
    @FXML
    private TableColumn<AgencyPOJO, String> firstNameColumn;
    @FXML
    private TableColumn<AgencyPOJO, String> branchColumn;
    @FXML
    private TableColumn<AgencyPOJO, String> phoneColumn;
    @FXML
    private TextField searchTxt;
    private ObservableList<AgencyPOJO> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Button btnClose;
    @FXML
    private VBox vboxSearchAndTable;
    @FXML
    private TableColumn<?, ?> lastNameClmn;
    String CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
    @FXML
    private TextField userNameTxt;
    @FXML
    private Button deleteBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            btnClose.setVisible(false);
            setTableValues();
            serchValues();
        }
        //  autoCompletion();
        //  getRoleDetails();
        // usernameListner();
        setBranchComboValues();
        addChangeEvents();
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PopOver popOver = (PopOver) btnClose.getScene().getWindow();
                popOver.hide();
            }
        });
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        saveAndUpdatefn();
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        AgencyDAO.deleteAgency(agencyId);
        serchValues();
        searchTxt.setText("");
        clearScreen();
        setTableValues();
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        clearScreen();
    }

    /**
     *
     */
//    public void autoCompletion() {
//        List<String> allUserNames = AgencyDAO.getAllUsers();
//        userNameTxt = TextFields.createSearchField();
//        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(userNameTxt, allUserNames);
//        userNameTxt.setPrefWidth(218);
//        userNameHbox.getChildren().remove(0);
//        userNameHbox.getChildren().add(userNameTxt);
//    }
    /**
     *
     */
//    public void getRoleDetails() {
//        userNameTxt.focusedProperty().addListener(
//                new ChangeListener<Boolean>() {
//                    @Override
//                    public void changed(
//                            ObservableValue<? extends Boolean> arg0,
//                            Boolean oldPropertyValue, Boolean newPropertyValue) {
//                                if (!userNameTxt.getText().equals("")) {
//                                    List<AgencyDAO.AgencyDetails> agencyList = AgencyDAO.getAgencyDetails(userNameTxt.getText());
//                                    for (AgencyDAO.AgencyDetails a : agencyList) {
//                                        agencyId = a.getAgencyId();
//                                        userNameTxt.setText(a.getUserName());
//                                        firstNameTxt.setText(a.getFirstName());
//                                        lastNameTxt.setText(a.getLastName());
//                                        emailTxt.setText(a.getEmail());
//                                        phoneTxt.setText(a.getPhone());
//                                        branch.remove(a.getBranch());
//                                        branch.add(a.getBranch());
//                                        branchCmb.setItems(branch);
//                                        branchCmb.getSelectionModel().selectLast();
//                                        addressTxtArea.setText(a.getBranch());
//                                    }
//                                }
//                            }
//                });
//        
//        userNameTxt.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (userNameTxt.getText().equals("")) {
//                    clearScreen();
//                }
//            }
//        }
//        );
//        
//        userNameTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent ke) {
//                if (userNameTxt.getText().equals("")) {
//                    clearScreen();
//                }
//            }
//        });
//    }
    /**
     *
     */
    public void addChangeEvents() {
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

    //Method for login form validation
    /**
     *
     * @return
     */
    public boolean loginFormValidation() {
        boolean f = true;
//        if (userNameTxt.getText().equals("")) {
//            usernameValidator.getItems().clear();
//            usernameValidator.getItems().add(
//                    new MenuItem("Please enter user name! "));
//            usernameValidator.show(userNameTxt, Side.RIGHT, 10, 0);
//            f = false;
//        } else
        if (firstNameTxt.getText().equals("")) {
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
        }
//        else if (emailTxt.getText().equals("") || mailValidation() == false) {
//            usernameValidator.getItems().clear();
//            usernameValidator.getItems().add(
//                    new MenuItem("Please enter valid email id! "));
//            usernameValidator.show(emailTxt, Side.RIGHT, 10, 0);
//            f = false;
//        } else if (phoneTxt.getText().equals("") || phoneTxt.getText().length() < 10) {
//            usernameValidator.getItems().clear();
//            usernameValidator.getItems().add(
//                    new MenuItem("Please enter phone no! "));
//            usernameValidator.show(phoneTxt, Side.RIGHT, 10, 0);
//            f = false;
//        }
        return f;
    }

    //Method for clear fields
    /**
     *
     */
    public void clearScreen() {
//        userNameTxt.setText("");
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        addressTxtArea.setText("");
        if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_OFFICE")) {
            branchCmb.getSelectionModel().select(CUR_BRANCH);
        }
        if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            branchCmb.getSelectionModel().selectFirst();
        }
        agencyId = "";
        searchTxt.setText("");
        userNameTxt.setText("");

    }

    @FXML
    private void agencytableOnMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        AgencyPOJO branchDetails = (AgencyPOJO) agencyTable.getSelectionModel().getSelectedItem();
        // userNameTxt.setText(branchDetails.getUserNameClmn());
        if (branchCmb.getItems().contains(branchDetails.getBranchClmn())) {
            branchCmb.getSelectionModel().clearAndSelect(branchCmb.getItems().indexOf(branchDetails.getBranchClmn()));
        }
        firstNameTxt.setText(branchDetails.getFirstNameClmn());
        lastNameTxt.setText(branchDetails.getLastNameClmn());
        userNameTxt.setText(branchDetails.getUserNameClmn());
        emailTxt.setText(branchDetails.getEmailClmn());
        phoneTxt.setText(branchDetails.getPhoneClmn());
        addressTxtArea.setText(branchDetails.getAddressClmn());
        agencyId = branchDetails.getAgencyId();
    }

    @FXML
    private void agencytableOnKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        AgencyPOJO branchDetails = (AgencyPOJO) agencyTable.getSelectionModel().getSelectedItem();
        //  userNameTxt.setText(branchDetails.getUserNameClmn());
        if (branchCmb.getItems().contains(branchDetails.getBranchClmn())) {
            branchCmb.getSelectionModel().clearAndSelect(branchCmb.getItems().indexOf(branchDetails.getBranchClmn()));
        }
        firstNameTxt.setText(branchDetails.getFirstNameClmn());
        lastNameTxt.setText(branchDetails.getLastNameClmn());
        emailTxt.setText(branchDetails.getEmailClmn());
        phoneTxt.setText(branchDetails.getPhoneClmn());
        addressTxtArea.setText(branchDetails.getAddressClmn());
        agencyId = branchDetails.getAgencyId();
    }

    /**
     *
     */
    public void setTableValues() {
        List<AgencyPOJO> branchList = AgencyDAO.getAllBranchDetails();
//        userNameCoulmn.setCellValueFactory(
//                new PropertyValueFactory<>("userNameClmn"));

        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstNameClmn"));
        lastNameClmn.setCellValueFactory(
                new PropertyValueFactory<>("lastNameClmn"));
        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchClmn"));
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneClmn"));

        masterSearchData.removeAll(masterSearchData);

        for (AgencyPOJO bp : branchList) {
            masterSearchData.add(bp);
        }

        agencyTable.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        List<AgencyPOJO> branchList = AgencyDAO.getAllBranchDetails();
//        userNameCoulmn.setCellValueFactory(
//                new PropertyValueFactory<>("userNameClmn"));
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstNameClmn"));
        lastNameClmn.setCellValueFactory(
                new PropertyValueFactory<>("lastNameClmn"));
        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchClmn"));
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneClmn"));

        //  agencyTable.getItems().setAll(branchList);
        masterSearchData.removeAll(masterSearchData);
        for (AgencyPOJO bp : branchList) {
            masterSearchData.add(bp);
        }

        FilteredList<AgencyPOJO> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
//                if (enquiry.getUserNameClmn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches contact name.
//                } else 
                if (enquiry.getFirstNameClmn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getLastNameClmn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                } else if (enquiry.getBranchClmn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter phone.
                } else if (enquiry.getPhoneClmn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                }
//                else if (enquiry.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter phone.
//                }
                return false; // Does not match.
            });
            SortedList<AgencyPOJO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(agencyTable.comparatorProperty());
            agencyTable.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void setBranchComboValues() {
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        for (String s : branches) {
            branch.add(s);
        }
        branchCmb.setItems(branch);
        if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_OFFICE")) {
            branchCmb.getSelectionModel().select(CUR_BRANCH);
        }
        if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            branchCmb.getSelectionModel().selectFirst();
        }

    }

    /**
     *
     */
//    public void usernameListner() {
//        userNameTxt.focusedProperty().addListener(
//                new ChangeListener<Boolean>() {
//                    @Override
//                    public void changed(
//                            ObservableValue<? extends Boolean> arg0,
//                            Boolean oldPropertyValue, Boolean newPropertyValue) {
//                                if (newPropertyValue) {
//                                    usernameValidator.hide();
//                                }
//                            }
//                });
//    }
    private void saveAndUpdatefn() {
        if (loginFormValidation()) {
            AgencyPOJO agencyPOJO = new AgencyPOJO();
            //   agencyPOJO.setUserName(userNameTxt.getText());
            agencyPOJO.setFirstName(firstNameTxt.getText());
            agencyPOJO.setLastName(lastNameTxt.getText());
            agencyPOJO.setEmail(emailTxt.getText());
            agencyPOJO.setPhone(phoneTxt.getText());
            agencyPOJO.setAddress(addressTxtArea.getText());
            agencyPOJO.setBranch(branchCmb.getSelectionModel().getSelectedItem().toString());
            Context.getInstance().currentProfile().setAgencyPOJO(agencyPOJO);

            if (!agencyId.equals("")) {
                int row = AgencyDAO.updateAgencyDetails(agencyId);
                if (row > 0) {
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Agent Details are updated", "Agent Details updated successfully", notification);
                    trayNotification.setAnimation(Animations.POPUP);
                    trayNotification.showAndDismiss(Duration.seconds(3));
                    clearScreen();
                    if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                        setTableValues();
                    }
                    searchTxt.setText("");
                }

            } else {
                int row = AgencyDAO.agencyDetailsInsert();
                if (row > 0) {
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Agent details are saved", "Agent details saved successfully", notification);
                    trayNotification.setAnimation(Animations.POPUP);
                    trayNotification.showAndDismiss(Duration.seconds(3));
                    clearScreen();
                    if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                        setTableValues();
                    }
                    searchTxt.setText("");
                }
            }
        }
    }

    private void handleExelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("agencies_tbl");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAgencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

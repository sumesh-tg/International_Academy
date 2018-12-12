/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.branch;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.admin.dao.AdminDAO.BranchDetails;
import com.zs.ina.admin.dao.BranchPOJO;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.context.Context;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLBranchController implements Initializable {

    static String branchId = "";
    @FXML
    private TextField branchNameTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private TextArea addressTxtArea;
    @FXML
    private TextField companyOwnerTxt;
    @FXML
    private TextField branchOwnerTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private HBox branchNameHbox;

    @FXML
    private TableView<BranchDetails> branchTableView;
    @FXML
    private TableColumn<BranchDetails, String> branchColumn;
    @FXML
    private TableColumn<BranchDetails, String> locationColumn;
    @FXML
    private TableColumn<BranchDetails, String> companyOwnerColumn;
    @FXML
    private TableColumn<BranchDetails, String> branchOwnerColumn;
    @FXML
    private TableColumn<BranchDetails, String> addressColumn;
    @FXML
    private TableColumn<BranchDetails, Integer> idColumn;

    @FXML
    private TextField searchTxt;
    @FXML
    private Button deleteBtn;
    final ContextMenu usernameValidator = new ContextMenu();
    private ObservableList<BranchDetails> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        getBranchDetails();
        getAllTableDetails();
        getTableDetails();
        addChangeEvents();
        branchNameTxt.focusedProperty().addListener(
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
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        if (branchFormValidation()) {
            BranchPOJO branchPOJO = new BranchPOJO();
            branchPOJO.setBranchName(branchNameTxt.getText());
            branchPOJO.setLocation(locationTxt.getText());
            branchPOJO.setAddress(addressTxtArea.getText());
            branchPOJO.setCompanyOwner(companyOwnerTxt.getText());
            branchPOJO.setBranchOwner(branchOwnerTxt.getText());
            Context.getInstance().currentProfile().setBranchPOJO(branchPOJO);
            if (!branchId.equals("")) {
                AdminDAO.updateBranchDetails(branchId);
                clearFields();
                getAllTableDetails();
            } else {
                int row = AdminDAO.insertBranchDetails();
                if (row > 0) {
                    getAllTableDetails();
                    clearFields();
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
        branchNameTxt.setText("");
        locationTxt.setText("");
        addressTxtArea.setText("");
        companyOwnerTxt.setText("");
        branchOwnerTxt.setText("");
        branchId = "";
        saveBtn.setText("Save");
        searchTxt.setText("");
    }

    //Method for perform autocompletion in contact_number,contact_name and contact_name fields.
    /**
     *
     */
    public void autoCompletion() {
        List<String> allBranches = AdminDAO.getAllBranches();
        branchNameTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(branchNameTxt, allBranches);
        branchNameTxt.setPrefWidth(200);
        branchNameHbox.getChildren().remove(0);
        branchNameHbox.getChildren().add(branchNameTxt);
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    /**
     *
     */
    public void getBranchDetails() {
        branchNameTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (!branchNameTxt.getText().equals("")) {
                                    List<AdminDAO.BranchDetails> branchList = AdminDAO.getBranchDetails(branchNameTxt.getText());
                                    for (AdminDAO.BranchDetails s : branchList) {
                                        branchId = s.getBranchId();
                                        branchNameTxt.setText(s.getBranchName());
                                        locationTxt.setText(s.getLocation());
                                        addressTxtArea.setText(s.getAddress());
                                        companyOwnerTxt.setText(s.getCompanyOwner());
                                        branchOwnerTxt.setText(s.getBranchOwner());
                                    }
                                }
                            }
                });

        branchNameTxt.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
//                if (branchNameTxt.getText().equals("")) {
//                    clearFields();
//                }
            }
        }
        );

        branchNameTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ke) {
                if (branchNameTxt.getText().equals("")) {
                    clearFields();
                }
            }

        });
    }

    /**
     *
     */
    public void addChangeEvents() {
        branchNameTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    branchNameTxt.setText(newValue);
                } else {
                    branchNameTxt.setText(oldValue);
                }

            }
        });

        locationTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    locationTxt.setText(newValue);
                } else {
                    locationTxt.setText(oldValue);
                }

            }
        });

        companyOwnerTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    companyOwnerTxt.setText(newValue);
                } else {
                    companyOwnerTxt.setText(oldValue);
                }

            }
        });
        branchOwnerTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    branchOwnerTxt.setText(newValue);
                } else {
                    branchOwnerTxt.setText(oldValue);
                }

            }
        });
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            AdminDAO.deleteBranchDetails(branchId);
            getAllTableDetails();
            searchTxt.setText("");
            clearFields();
        }
    }

    //Method for Branch form validation
    /**
     *
     * @return
     */
    public boolean branchFormValidation() {
        boolean f = true;
        if (branchNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter branch name! "));
            usernameValidator.show(branchNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (locationTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter branch location! "));
            usernameValidator.show(locationTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (addressTxtArea.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter branch address! "));
            usernameValidator.show(addressTxtArea, Side.RIGHT, 10, 0);
            f = false;
        } else if (companyOwnerTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter company owner name! "));
            usernameValidator.show(companyOwnerTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (branchOwnerTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter branch owner name! "));
            usernameValidator.show(branchOwnerTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void getTableDetails() {
        List<BranchDetails> branchList = AdminDAO.getAllBranchDetails();
        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchName"));
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
        companyOwnerColumn.setCellValueFactory(
                new PropertyValueFactory<>("companyOwner"));
        branchOwnerColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchOwner"));
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address"));
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchId"));
        masterSearchData.removeAll(masterSearchData);
        for (BranchDetails bp : branchList) {
            masterSearchData.add(bp);
        }

        FilteredList<BranchDetails> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getBranchName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getLocation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  email.
                } else if (enquiry.getCompanyOwner().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter phone.
                } else if (enquiry.getBranchOwner().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  email.
                } else if (enquiry.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter phone.
                }
                return false; // Does not match.
            });
            SortedList<BranchDetails> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(branchTableView.comparatorProperty());
            branchTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<BranchDetails> branchList = AdminDAO.getAllBranchDetails();
        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchName"));
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
        companyOwnerColumn.setCellValueFactory(
                new PropertyValueFactory<>("companyOwner"));
        branchOwnerColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchOwner"));
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address"));
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("branchId"));
        masterSearchData.removeAll(masterSearchData);
        for (BranchDetails bp : branchList) {
            masterSearchData.add(bp);
        }
        branchTableView.setItems(masterSearchData);
    }

    @FXML
    private void handleBranchTableView(MouseEvent event) {
        saveBtn.setText("Update");
        BranchDetails branchDetails = (BranchDetails) branchTableView.getSelectionModel().getSelectedItem();
        branchNameTxt.setText(branchDetails.getBranchName());
        locationTxt.setText(branchDetails.getLocation());
        addressTxtArea.setText(branchDetails.getAddress());
        companyOwnerTxt.setText(branchDetails.getCompanyOwner());
        branchOwnerTxt.setText(branchDetails.getBranchOwner());
        branchId = branchDetails.getBranchId();
    }

    @FXML
    private void handleBranchTableViewKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        BranchDetails branchDetails = (BranchDetails) branchTableView.getSelectionModel().getSelectedItem();
        branchNameTxt.setText(branchDetails.getBranchName());
        locationTxt.setText(branchDetails.getLocation());
        addressTxtArea.setText(branchDetails.getAddress());
        companyOwnerTxt.setText(branchDetails.getCompanyOwner());
        branchOwnerTxt.setText(branchDetails.getBranchOwner());
        branchId = branchDetails.getBranchId();
    }

    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("branch_tbl");
    }

    @FXML
    private void handlebranchNameTxt(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "dd");
    }

}

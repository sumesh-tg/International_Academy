/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.privilege;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.admin.dao.AdminDAO.PrivilageDetails;
import com.zs.ina.admin.dao.PrivilagePOJO;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.context.Context;
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
public class FXMLPrivilagesController implements Initializable {

    @FXML
    private TextField privilageTxt;
    @FXML
    private TextArea descriptionTxtArea;
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private HBox privilageHbox;
    static String privilageId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private TableColumn<PrivilageDetails, String> privilegeColumn;
    @FXML
    private TableColumn<PrivilageDetails, String> descriptioncolumn;
    @FXML
    private TableColumn<PrivilageDetails, Integer> privilegeIdColumn;
    @FXML
    private TableView<AdminDAO.PrivilageDetails> privilegeTableView;
    private ObservableList<AdminDAO.PrivilageDetails> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private TextField searchTxt;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button exelUploadBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameValidator.setAutoHide(false);
        autoCompletion();
        getPrivilageDetails();
        getTableDetails();
        getAllTableDetails();
        addChangeEvents();
        privilageTxt.focusedProperty().addListener(
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
        if (privilageFormValidation()) {
            PrivilagePOJO privilagePOJO = new PrivilagePOJO();
            privilagePOJO.setPrivilage(privilageTxt.getText());
            privilagePOJO.setPrivilageDesc(descriptionTxtArea.getText());
            Context.getInstance().currentProfile().setPrivilagePOJO(privilagePOJO);
            if (!privilageId.equals("")) {
                AdminDAO.updatePrivilageDetails(privilageId);
                clearFields();
                getAllTableDetails();
            } else {
                int row = AdminDAO.insertPrivilageDetails();
                if (row > 0) {
                    clearFields();
                    getAllTableDetails();
                }
            }
        }
    }

    /**
     *
     */
    public void clearFields() {
        searchTxt.setText("");
        privilageTxt.setText("");
        descriptionTxtArea.setText("");
        privilageId = "";
        saveBtn.setText("Save");
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allPrivilages = AdminDAO.getAllPrivilages();
        privilageTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(privilageTxt, allPrivilages);
        privilageTxt.setPrefWidth(200);
        privilageHbox.getChildren().remove(0);
        privilageHbox.getChildren().add(privilageTxt);
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clearFields();
    }

    /**
     *
     */
    public void getPrivilageDetails() {
        privilageTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (!privilageTxt.getText().equals("")) {
                                    List<AdminDAO.PrivilageDetails> privilageList = AdminDAO.getPrivilageDetails(privilageTxt.getText());
                                    for (AdminDAO.PrivilageDetails p : privilageList) {
                                        privilageId = p.getPrivilageId();
                                        privilageTxt.setText(p.getPrivilageName());
                                        descriptionTxtArea.setText(p.getDescription());
                                    }
                                }
                            }
                });

        privilageTxt.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (privilageTxt.getText().equals("")) {
                    clearFields();
                }
            }
        }
        );

        privilageTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ke) {
                if (privilageTxt.getText().equals("")) {
                    clearFields();
                }
            }

        });
    }

    /**
     *
     */
    public void addChangeEvents() {
        privilageTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    privilageTxt.setText(newValue);
                } else {
                    privilageTxt.setText(oldValue);
                }

            }
        });
        descriptionTxtArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    descriptionTxtArea.setText(newValue);
                } else {
                    descriptionTxtArea.setText(oldValue);
                }

            }
        });
    }

    private void handleDeleteBtn(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            AdminDAO.deletePrivilageDetails(privilageId);
            getAllTableDetails();
            clearFields();
        }
    }

    //Method for privilage form validation

    /**
     *
     * @return
     */
    public boolean privilageFormValidation() {
        boolean f = true;
        if (privilageTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter privilage!"));
            usernameValidator.show(privilageTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void getTableDetails() {
        List<AdminDAO.PrivilageDetails> privilegeList = AdminDAO.getAllPrivilageDetails();
        privilegeColumn.setCellValueFactory(
                new PropertyValueFactory<>("privilageName"));
        descriptioncolumn.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        privilegeIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("privilageId"));
        masterSearchData.removeAll(masterSearchData);
        for (AdminDAO.PrivilageDetails pd : privilegeList) {
            masterSearchData.add(pd);
        }

        FilteredList<AdminDAO.PrivilageDetails> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getPrivilageName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                }
                return false; // Does not match.
            });
            SortedList<AdminDAO.PrivilageDetails> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(privilegeTableView.comparatorProperty());
            privilegeTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<AdminDAO.PrivilageDetails> privilegeList = AdminDAO.getAllPrivilageDetails();
        privilegeColumn.setCellValueFactory(
                new PropertyValueFactory<>("privilageName"));
        descriptioncolumn.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        privilegeIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("privilageId"));
        masterSearchData.removeAll(masterSearchData);
        for (PrivilageDetails bp : privilegeList) {
            masterSearchData.add(bp);
        }
        privilegeTableView.setItems(masterSearchData);
    }

    @FXML
    private void handlePrivilegeTableViewKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        PrivilageDetails privilageDetails = (PrivilageDetails) privilegeTableView.getSelectionModel().getSelectedItem();
        privilageTxt.setText(privilageDetails.getPrivilageName());
        descriptionTxtArea.setText(privilageDetails.getDescription());
        privilageId = privilageDetails.getPrivilageId();
    }

    @FXML
    private void handlePrivilegeTableView(MouseEvent event) {
        saveBtn.setText("Update");
        PrivilageDetails privilageDetails = (PrivilageDetails) privilegeTableView.getSelectionModel().getSelectedItem();
        privilageTxt.setText(privilageDetails.getPrivilageName());
        descriptionTxtArea.setText(privilageDetails.getDescription());
        privilageId = privilageDetails.getPrivilageId();
    }

    @FXML
    private void handledelete(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            AdminDAO.deletePrivilageDetails(privilageId);
            getTableDetails();
            searchTxt.setText("");
            clearFields();
        }
    }

    @FXML
    private void handleExelupload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("privilage_tbl");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPrivilagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

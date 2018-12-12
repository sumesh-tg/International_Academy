/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.role;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.admin.dao.AdminDAO.RoleDetails;
import com.zs.ina.admin.dao.RolePOJO;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class FXMLRoleController implements Initializable {

    static String roleId = "";
    @FXML
    private TextField roleTxt;
    @FXML
    private ComboBox<?> privilageCmb;
    ObservableList privilages = FXCollections.observableArrayList();
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private HBox roleHbox;
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private Label titleLbl;
    @FXML
    private TableView<RoleDetails> roleTableView;
    @FXML
    private TableColumn<RoleDetails, String> roleColumn;
    @FXML
    private TableColumn<RoleDetails, String> privilageColumn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableColumn<RoleDetails, Integer> roleIdColumn;
    private ObservableList<AdminDAO.RoleDetails> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Button btnExcelUpload;
    @FXML
    private Button deleteBtn;

    /**
     * Initializes the controll @FXML private Button deleteBtn; er class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        autoCompletion();
        getRoleDetails();
        getTableDetails();
        getAllTableDetails();
        addChangeEvents();
        List<String> privilage = AdminDAO.getAllPrivilages();

        for (String s : privilage) {
            privilages.add(s);
        }
        privilageCmb.setItems(privilages);
        privilageCmb.getSelectionModel().selectFirst();

        roleTxt.focusedProperty().addListener(
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
        if (roleFormValidation()) {
            RolePOJO rolePOJO = new RolePOJO();
            rolePOJO.setRole(roleTxt.getText());
            rolePOJO.setPrivilage(privilageCmb.getSelectionModel().getSelectedItem().toString());
            Context.getInstance().currentProfile().setRolePOJO(rolePOJO);
            if (!roleId.equals("")) {
                AdminDAO.updateRoleDetails(roleId);
                clearFields();
                getAllTableDetails();
            } else {
                int row = AdminDAO.insertRoleDetails();
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
    public void addChangeEvents() {
        roleTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    roleTxt.setText(newValue);
                } else {
                    roleTxt.setText(oldValue);
                }
            }
        });
    }

    /**
     *
     */
    public void clearFields() {
        searchTxt.setText("");
        privilageCmb.getSelectionModel().selectFirst();
        roleTxt.setText("");
        roleId = "";
        saveBtn.setText("Save");
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clearFields();
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allRoles = AdminDAO.getAllRole();
        roleTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(roleTxt, allRoles);
        roleTxt.setPrefWidth(211);
        roleHbox.getChildren().remove(0);
        roleHbox.getChildren().add(roleTxt);
    }

    /**
     *
     */
    public void getRoleDetails() {
        roleTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (!roleTxt.getText().equals("")) {
                                    List<AdminDAO.RoleDetails> roleList = AdminDAO.getRoleDetails(roleTxt.getText());
                                    for (AdminDAO.RoleDetails r : roleList) {
                                        roleId = r.getRoleId();
                                        privilages.remove(r.getPrivilage());
                                        privilages.add(r.getPrivilage());
                                        privilageCmb.setItems(privilages);
                                        privilageCmb.getSelectionModel().selectLast();
                                    }

                                }
                            }
                });
    }

    private void handleDeleteBtn(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            AdminDAO.deleteRoleDetails(roleId);
            getAllTableDetails();
            clearFields();
        }
    }

    //Method for role form validation

    /**
     *
     * @return
     */
    public boolean roleFormValidation() {
        boolean f = true;
        if (roleTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter role! "));
            usernameValidator.show(roleTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void getTableDetails() {
        List<AdminDAO.RoleDetails> roleList = AdminDAO.getAllRoleDetails();
        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role"));
        privilageColumn.setCellValueFactory(
                new PropertyValueFactory<>("privilage"));
        roleIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("roleId"));
        masterSearchData.removeAll(masterSearchData);
        for (AdminDAO.RoleDetails rd : roleList) {
            masterSearchData.add(rd);
        }

        FilteredList<AdminDAO.RoleDetails> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getPrivilage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                }
                return false; // Does not match.
            });
            SortedList<AdminDAO.RoleDetails> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(roleTableView.comparatorProperty());
            roleTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<AdminDAO.RoleDetails> roleList = AdminDAO.getAllRoleDetails();
        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role"));
        privilageColumn.setCellValueFactory(
                new PropertyValueFactory<>("privilage"));
        roleIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("roleId"));
        masterSearchData.removeAll(masterSearchData);
        for (RoleDetails s : roleList) {
            masterSearchData.add(s);
        }
        roleTableView.setItems(masterSearchData);
    }

    @FXML
    private void handleRoleTableViewKeyPressed(MouseEvent event) {
        saveBtn.setText("Update");
        RoleDetails roleDetails = (RoleDetails) roleTableView.getSelectionModel().getSelectedItem();
        roleTxt.setText(roleDetails.getRole());
        if (privilageCmb.getItems().contains(roleDetails.getPrivilage())) {
            privilageCmb.getSelectionModel().clearAndSelect(privilageCmb.getItems().indexOf(roleDetails.getPrivilage()));
        }
        roleId = roleDetails.getRoleId();
    }

    @FXML
    private void handleRoleTableView(MouseEvent event) {
        saveBtn.setText("Update");
        RoleDetails roleDetails = (RoleDetails) roleTableView.getSelectionModel().getSelectedItem();
        roleTxt.setText(roleDetails.getRole());
        if (privilageCmb.getItems().contains(roleDetails.getPrivilage())) {
            privilageCmb.getSelectionModel().clearAndSelect(privilageCmb.getItems().indexOf(roleDetails.getPrivilage()));
        }
        roleId = roleDetails.getRoleId();
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("role_tbl");
    }

    @FXML
    private void handledeleteBtn(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            AdminDAO.deleteRoleDetails(roleId);
            getTableDetails();
            searchTxt.setText("");
            clearFields();
        }
    }
}

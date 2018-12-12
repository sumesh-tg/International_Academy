/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.migrationcategory;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.migrationcategory.dao.MigrationBEAN;
import com.zs.ina.admin.master.migrationcategory.dao.MigrationDAO;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLMigrationCategoryController implements Initializable {

    @FXML
    private HBox migrationHBox;
    @FXML
    private TextField migrationTxt;
    @FXML
    private Button btnExcelUpload;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<MigrationBEAN> maigrationTbl;
    @FXML
    private TableColumn<?, ?> migrationClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private String migrationId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private ObservableList<MigrationBEAN> masterSearchData = FXCollections.observableArrayList();
    private MigrationBEAN migrationBEAN;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        setTableValues();
        serchValues();
        addChangeEvents();
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("mastertbl_migration_category");
        setTableValues();
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    /**
     *
     */
    public void addChangeEvents() {
        migrationTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    migrationTxt.setText(newValue);
                } else {
                    migrationTxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        migrationBEAN = new MigrationBEAN();
        migrationBEAN.setMigrattionCategory(migrationTxt.getText());
        migrationBEAN.setId(migrationId);
        if (validation()) {
            if (!migrationId.equals("")) {
                int row = MigrationDAO.updateMigrationCategory(migrationBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!MigrationDAO.checkDuplicateEntry(migrationTxt.getText())) {
                    int row = MigrationDAO.insertMigrationCategory(migrationBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Migration already inserted! "));
                    usernameValidator.show(migrationTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        MigrationDAO.deleteMigrationCategory(migrationBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleMigrationTbl(MouseEvent event) {
        btnSave.setText("Update");
        migrationBEAN = (MigrationBEAN) maigrationTbl.getSelectionModel().getSelectedItem();
        migrationTxt.setText(migrationBEAN.getMigrattionCategory());
        migrationId = migrationBEAN.getId();
    }

    @FXML
    private void handleMigrationTblReleased(KeyEvent event) {
        btnSave.setText("Update");
        migrationBEAN = (MigrationBEAN) maigrationTbl.getSelectionModel().getSelectedItem();
        migrationTxt.setText(migrationBEAN.getMigrattionCategory());
        migrationId = migrationBEAN.getId();
    }

    private void clear() {
        migrationTxt.setText("");
        migrationId = "";
        btnSave.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (migrationTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Migration Category field "));
            usernameValidator.show(migrationTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allMigrationCategories = MigrationDAO.getAllMigrationCategory();
        migrationTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(migrationTxt, allMigrationCategories);
        migrationTxt.setPrefWidth(200);
        migrationHBox.getChildren().remove(0);
        migrationHBox.getChildren().add(migrationTxt);
    }

    /**
     *
     */
    public void setTableValues() {
        ObservableList<MigrationBEAN> migrationBEANs = MigrationDAO.getMigrationCategoryDetails();
        migrationClmn.setCellValueFactory(new PropertyValueFactory<>("migrattionCategory"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (MigrationBEAN bp : migrationBEANs) {
            masterSearchData.add(bp);
        }
        maigrationTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<MigrationBEAN> migrationBEANs = MigrationDAO.getMigrationCategoryDetails();
        migrationClmn.setCellValueFactory(new PropertyValueFactory<>("migrattionCategory"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (MigrationBEAN bp : migrationBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<MigrationBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getMigrattionCategory().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<MigrationBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(maigrationTbl.comparatorProperty());
            maigrationTbl.setItems(sortedData);
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.country;

import com.zs.ina.admin.master.country.dao.CountryBEAN;
import com.zs.ina.admin.master.country.dao.CountryDAO;
import com.zs.ina.admin.master.course.FXMLCourseController;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLCountryController implements Initializable {

    @FXML
    private ComboBox<?> specificationCmb;
    @FXML
    private TextField countryNameTxt;
    @FXML
    private Button excelUploadBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<CountryBEAN> countryTbl;
    @FXML
    private TableColumn<?, ?> specificationClmn;
    @FXML
    private TableColumn<?, ?> countryClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    @FXML
    private Button saveBtn;
    private String countryId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    ObservableList specification = FXCollections.observableArrayList("Study", "Work", "Migration");
    private ObservableList<CountryBEAN> masterSearchData = FXCollections.observableArrayList();
    private CountryBEAN countryBEAN;
    @FXML
    private HBox countryNameHBox;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        specificationCmb.setItems(specification);
        setTableValues();
        serchValues();
    }

    @FXML
    private void handleExcelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_course_management");
            setTableValues();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        CountryDAO.deleteCountry(countryBEAN);
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        countryBEAN = new CountryBEAN();
        countryBEAN.setSpecification(specificationCmb.getSelectionModel().getSelectedItem().toString());
        countryBEAN.setCountryName(countryNameTxt.getText());
        countryBEAN.setId(countryId);
        if (validation()) {
            if (!countryId.equals("")) {
                int row = CountryDAO.updateCountry(countryBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!CountryDAO.checkDuplicateEntry(countryBEAN)) {
                    int row = CountryDAO.insertCountries(countryBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Country already inserted! "));
                    usernameValidator.show(countryNameTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handleCountryTblMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        countryBEAN = (CountryBEAN) countryTbl.getSelectionModel().getSelectedItem();
        countryNameTxt.setText(countryBEAN.getCountryName());
        specificationCmb.getSelectionModel().clearAndSelect(specificationCmb.getItems().indexOf(countryBEAN.getSpecification()));
        countryId = countryBEAN.getId();
    }

    @FXML
    private void handleCountryTblKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        countryBEAN = (CountryBEAN) countryTbl.getSelectionModel().getSelectedItem();
        countryNameTxt.setText(countryBEAN.getCountryName());
        specificationCmb.getSelectionModel().clearAndSelect(specificationCmb.getItems().indexOf(countryBEAN.getSpecification()));
        countryId = countryBEAN.getId();
    }

    private void clear() {
        countryNameTxt.setText("");
        specificationCmb.getSelectionModel().clearSelection();
        countryId = "";
        saveBtn.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (countryNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Country field "));
            usernameValidator.show(countryNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (specificationCmb.getSelectionModel().getSelectedIndex() < 0) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please select sepecification "));
            usernameValidator.show(specificationCmb, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void setTableValues() {
        List<CountryBEAN> countryBEANs = CountryDAO.getAllCountries();
        countryClmn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        specificationClmn.setCellValueFactory(new PropertyValueFactory<>("specification"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (CountryBEAN bp : countryBEANs) {
            masterSearchData.add(bp);
        }
        countryTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        List<CountryBEAN> countryBEANs = CountryDAO.getAllCountries();
        countryClmn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        specificationClmn.setCellValueFactory(new PropertyValueFactory<>("specification"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (CountryBEAN bp : countryBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<CountryBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getCountryName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (enquiry.getSpecification().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<CountryBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(countryTbl.comparatorProperty());
            countryTbl.setItems(sortedData);
        });
    }
}

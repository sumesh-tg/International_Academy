/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.institute;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.institute.dao.InstituteBean;
import com.zs.ina.admin.master.institute.dao.InstituteDAO;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLInstituteController implements Initializable {

    @FXML
    private HBox languageHBox;
    @FXML
    private TextField instituteTxt;
    @FXML
    private ComboBox<?> countryCmb;
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
    private TableView<InstituteBean> instituteTbl;
    @FXML
    private TableColumn<?, ?> instituteClmn;
    @FXML
    private TableColumn<?, ?> countryClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private ObservableList<InstituteBean> masterSearchData = FXCollections.observableArrayList();
    private InstituteBean instituteBean;
    private String languageId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private ObservableList countries = FXCollections.observableArrayList();

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
        countryComboInit();
        addChangeEvents();
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_institute");
            setTableValues();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInstituteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    /**
     *
     */
    public void addChangeEvents() {
        instituteTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    instituteTxt.setText(newValue);
                } else {
                    instituteTxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        instituteBean = new InstituteBean();
        instituteBean.setInstitute(instituteTxt.getText());
        instituteBean.setId(languageId);
        instituteBean.setCountry(countryCmb.getSelectionModel().getSelectedItem().toString());
        if (validation()) {
            if (!languageId.equals("")) {
                int row = InstituteDAO.updateInstitute(instituteBean);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!InstituteDAO.checkDuplicateEntry(instituteBean)) {
                    int row = InstituteDAO.insertLanguage(instituteBean);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Institute already inserted! "));
                    usernameValidator.show(instituteTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        InstituteDAO.deleteLanguage(instituteBean.getId());
        setTableValues();
        searchTxt.setText("");
        clear();

    }

    @FXML
    private void handleLanguageTbl(MouseEvent event) {
        btnSave.setText("Update");
        instituteBean = (InstituteBean) instituteTbl.getSelectionModel().getSelectedItem();
        instituteTxt.setText(instituteBean.getInstitute());
        countryCmb.getSelectionModel().clearAndSelect(countryCmb.getItems().indexOf(instituteBean.getCountry()));
        languageId = instituteBean.getId();
    }

    @FXML
    private void handleLanguageTblReleased(KeyEvent event) {
        btnSave.setText("Update");
        instituteBean = (InstituteBean) instituteTbl.getSelectionModel().getSelectedItem();
        instituteTxt.setText(instituteBean.getInstitute());
        countryCmb.getSelectionModel().clearAndSelect(countryCmb.getItems().indexOf(instituteBean.getCountry()));
        languageId = instituteBean.getId();
    }

    private void autoCompletion() {
        List<String> allLaguages = InstituteDAO.getAllInstitute();
        instituteTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(instituteTxt, allLaguages);
        instituteTbl.setPrefWidth(200);
        languageHBox.getChildren().remove(0);
        languageHBox.getChildren().add(instituteTxt);
    }

    private void setTableValues() {
        ObservableList<InstituteBean> instituteBeans = InstituteDAO.getInstituteDetails();
        instituteClmn.setCellValueFactory(new PropertyValueFactory<>("institute"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        countryClmn.setCellValueFactory(new PropertyValueFactory<>("country"));
        masterSearchData.removeAll(masterSearchData);
        for (InstituteBean bp : instituteBeans) {
            masterSearchData.add(bp);
        }
        instituteTbl.setItems(masterSearchData);
    }

    private void serchValues() {
        ObservableList<InstituteBean> instituteBeans = InstituteDAO.getInstituteDetails();
        instituteClmn.setCellValueFactory(new PropertyValueFactory<>("institute"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        countryClmn.setCellValueFactory(new PropertyValueFactory<>("country"));
        masterSearchData.removeAll(masterSearchData);
        for (InstituteBean bp : instituteBeans) {
            masterSearchData.add(bp);
        }
        FilteredList<InstituteBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getInstitute().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (enquiry.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<InstituteBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(instituteTbl.comparatorProperty());
            instituteTbl.setItems(sortedData);
        });
    }

    private boolean validation() {
        boolean f = true;
        if (instituteTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter languade field "));
            usernameValidator.show(instituteTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    private void clear() {
        instituteTxt.setText("");
        searchTxt.setText("");
        countryCmb.getSelectionModel().clearSelection();
        languageId = "";
        btnSave.setText("Save");
    }

    private void countryComboInit() {
        countries = InstituteDAO.getCountries();
        countryCmb.setItems(countries);
    }

}

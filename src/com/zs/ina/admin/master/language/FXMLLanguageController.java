/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.language;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.language.dao.LanguageBEAN;
import com.zs.ina.admin.master.language.dao.LanguageDAO;
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
public class FXMLLanguageController implements Initializable {

    @FXML
    private TextField txtLanguage;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private HBox languageHBox;
    @FXML
    private TableColumn<?, ?> languageClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    @FXML
    private TableView<LanguageBEAN> languageTbl;
    @FXML
    private TextField searchTxt;
    private String languageId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private LanguageBEAN languageBEAN;
    @FXML
    private Button btnDelete;
    private ObservableList<LanguageBEAN> masterSearchData = FXCollections.observableArrayList();
    private static int checkFlag = 0;
    @FXML
    private Button btnExcelUpload;

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
        excelUpload.exelFileChooser("mastertbl_language");
        setTableValues();
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        languageBEAN = new LanguageBEAN();
        languageBEAN.setLanguage(txtLanguage.getText());
        languageBEAN.setId(languageId);
        if (validation()) {
            if (!languageId.equals("")) {
                int row = LanguageDAO.updateLanguage(languageBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!LanguageDAO.checkDuplicateEntry(txtLanguage.getText())) {
                    int row = LanguageDAO.insertLanguage(languageBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Language already inserted! "));
                    usernameValidator.show(txtLanguage, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    /**
     *
     */
    public void addChangeEvents() {
        txtLanguage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    txtLanguage.setText(newValue);
                } else {
                    txtLanguage.setText(oldValue);
                }
            }
        });
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allLaguages = LanguageDAO.getAllLanguages();
        txtLanguage = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtLanguage, allLaguages);
        txtLanguage.setPrefWidth(200);
        languageHBox.getChildren().remove(0);
        languageHBox.getChildren().add(txtLanguage);
    }

    /**
     *
     */
    public void setTableValues() {
        ObservableList<LanguageBEAN> languageBEANs = LanguageDAO.getLanguagetails();
        languageClmn.setCellValueFactory(new PropertyValueFactory<>("language"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (LanguageBEAN bp : languageBEANs) {
            masterSearchData.add(bp);
        }
        languageTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<LanguageBEAN> languageBEANs = LanguageDAO.getLanguagetails();
        languageClmn.setCellValueFactory(new PropertyValueFactory<>("language"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (LanguageBEAN bp : languageBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<LanguageBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getLanguage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<LanguageBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(languageTbl.comparatorProperty());
            languageTbl.setItems(sortedData);
        });
    }

    @FXML
    private void handleLanguageTbl(MouseEvent event) {
        btnSave.setText("Update");
        languageBEAN = (LanguageBEAN) languageTbl.getSelectionModel().getSelectedItem();
        txtLanguage.setText(languageBEAN.getLanguage());
        languageId = languageBEAN.getId();
    }

    @FXML
    private void handleLanguageTblReleased(KeyEvent event) {
        btnSave.setText("Update");
        languageBEAN = (LanguageBEAN) languageTbl.getSelectionModel().getSelectedItem();
        txtLanguage.setText(languageBEAN.getLanguage());
        languageId = languageBEAN.getId();
    }

    private void clear() {
        txtLanguage.setText("");
        languageId = "";
        btnSave.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (txtLanguage.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter languade field "));
            usernameValidator.show(txtLanguage, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        LanguageDAO.deleteLanguage(languageBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }
}

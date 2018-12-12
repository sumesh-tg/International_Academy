/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.purpose;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.language.dao.LanguageDAO;
import com.zs.ina.admin.master.purpose.dao.PurposeDAO;
import com.zs.ina.admin.master.purpose.dao.PurposeIMPL;
import com.zs.ina.admin.master.purpose.dao.PurposeModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class FXMLPurposeController implements Initializable {

    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private HBox languageHBox;
    @FXML
    private TextField searchTxt;
    private String purposeId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private PurposeModel purposeModel;
    @FXML
    private Button btnDelete;
    private ObservableList<PurposeModel> masterSearchData = FXCollections.observableArrayList();
    private static int checkFlag = 0;
    @FXML
    private TextField txtPurpose;
    @FXML
    private TableView<PurposeModel> tblPurpose;
    PurposeDAO purposeDAO = new PurposeIMPL();
    @FXML
    private TableColumn<?, ?> clmPurposeId;
    @FXML
    private TableColumn<?, ?> clmPurpose;

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
        purposeModel = new PurposeModel();
        purposeModel.setPurpose(txtPurpose.getText());
        purposeModel.setPuposeId(purposeId);
        if (validation()) {
            if (!purposeId.equals("")) {
                int row = purposeDAO.upadatePurpose(purposeModel);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else if (!LanguageDAO.checkDuplicateEntry(txtPurpose.getText())) {
                int row = purposeDAO.insertPurpose(purposeModel);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                usernameValidator.getItems().clear();
                usernameValidator.getItems().add(
                        new MenuItem("Language already inserted! "));
                usernameValidator.show(txtPurpose, Side.RIGHT, 10, 0);
            }
        }
    }

    /**
     *
     */
    public void addChangeEvents() {
        txtPurpose.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    txtPurpose.setText(newValue);
                } else {
                    txtPurpose.setText(oldValue);
                }
            }
        });
    }

    /**
     *
     */
    public void autoCompletion() {
        ObservableList<PurposeModel> purposeList = purposeDAO.listPurpose();
        List<String> puposeList = new ArrayList<>();
        for (PurposeModel model : purposeList) {
            puposeList.add(model.getPurpose());
        }
        txtPurpose = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtPurpose, puposeList);
        txtPurpose.setPrefWidth(200);
        languageHBox.getChildren().remove(0);
        languageHBox.getChildren().add(txtPurpose);
    }

    /**
     *
     */
    public void setTableValues() {

        ObservableList<PurposeModel> purposeList = purposeDAO.listPurpose();

        clmPurposeId.setCellValueFactory(new PropertyValueFactory<>("puposeId"));
        clmPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        masterSearchData.removeAll(masterSearchData);
        for (PurposeModel model : purposeList) {
            masterSearchData.add(model);
        }
        tblPurpose.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<PurposeModel> purposeList = purposeDAO.listPurpose();
        clmPurposeId.setCellValueFactory(new PropertyValueFactory<>("puposeId"));
        clmPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        masterSearchData.removeAll(masterSearchData);
        for (PurposeModel model : purposeList) {
            masterSearchData.add(model);
        }
        FilteredList<PurposeModel> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getPurpose().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<PurposeModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblPurpose.comparatorProperty());
            tblPurpose.setItems(sortedData);
        });
    }

    @FXML
    private void handleLanguageTbl(MouseEvent event) {
        btnSave.setText("Update");
        purposeModel = (PurposeModel) tblPurpose.getSelectionModel().getSelectedItem();
        txtPurpose.setText(purposeModel.getPurpose());
        purposeId = purposeModel.getPuposeId();
    }

    @FXML
    private void handleLanguageTblReleased(KeyEvent event) {
        btnSave.setText("Update");
        purposeModel = (PurposeModel) tblPurpose.getSelectionModel().getSelectedItem();
        txtPurpose.setText(purposeModel.getPurpose());
        purposeId = purposeModel.getPuposeId();
    }

    private void clear() {
        txtPurpose.setText("");
        purposeId = "";
        btnSave.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (txtPurpose.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter languade field "));
            usernameValidator.show(txtPurpose, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        purposeDAO.deletePurpose(purposeModel);
        setTableValues();
        searchTxt.setText("");
        clear();
    }
}

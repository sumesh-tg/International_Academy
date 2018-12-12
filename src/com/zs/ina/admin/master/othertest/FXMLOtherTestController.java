/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.othertest;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.othertest.dao.OtherTestBEAN;
import com.zs.ina.admin.master.othertest.dao.OtherTestDAO;
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
public class FXMLOtherTestController implements Initializable {

    @FXML
    private TextField otherTestTxt;
    @FXML
    private Button excelBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<OtherTestBEAN> otherTstTbl;
    @FXML
    private TableColumn<?, ?> OtherTestClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private String otherTestId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private HBox otherTestHBox;
    private ObservableList<OtherTestBEAN> masterSearchData = FXCollections.observableArrayList();
    private OtherTestBEAN otherTestBEAN;

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
        excelUpload.exelFileChooser("mastertbl_other_test");
        setTableValues();
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        otherTestBEAN = new OtherTestBEAN();
        otherTestBEAN.setOtherTest(otherTestTxt.getText());
        otherTestBEAN.setId(otherTestId);
        if (validation()) {
            if (!otherTestId.equals("")) {
                int row = OtherTestDAO.updateOtherTest(otherTestBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!OtherTestDAO.checkDuplicateEntry(otherTestTxt.getText())) {
                    int row = OtherTestDAO.insertOtherTest(otherTestBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Other test already inserted! "));
                    usernameValidator.show(otherTestTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    /**
     *
     */
    public void addChangeEvents() {
        otherTestTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    otherTestTxt.setText(newValue);
                } else {
                    otherTestTxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        OtherTestDAO.deleteOtherTest(otherTestBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    private void clear() {
        otherTestTxt.setText("");
        otherTestId = "";
        saveBtn.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (otherTestTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Other test field "));
            usernameValidator.show(otherTestTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allOtherTests = OtherTestDAO.getAllOtherTest();
        otherTestTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(otherTestTxt, allOtherTests);
        otherTestTxt.setPrefWidth(200);
        otherTestHBox.getChildren().remove(0);
        otherTestHBox.getChildren().add(otherTestTxt);
    }

    /**
     *
     */
    public void setTableValues() {
        ObservableList<OtherTestBEAN> otherTestBEANs = OtherTestDAO.getOtherTestDetails();
        OtherTestClmn.setCellValueFactory(new PropertyValueFactory<>("otherTest"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (OtherTestBEAN bp : otherTestBEANs) {
            masterSearchData.add(bp);
        }
        otherTstTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<OtherTestBEAN> otherTestBEANs = OtherTestDAO.getOtherTestDetails();
        OtherTestClmn.setCellValueFactory(new PropertyValueFactory<>("otherTest"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (OtherTestBEAN bp : otherTestBEANs) {
            masterSearchData.add(bp);
        }

        FilteredList<OtherTestBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getOtherTest().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
                return false; // Does not match.
            });
            SortedList<OtherTestBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(otherTstTbl.comparatorProperty());
            otherTstTbl.setItems(sortedData);
        });
    }

    @FXML
    private void handleOtherTesrTblMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        otherTestBEAN = (OtherTestBEAN) otherTstTbl.getSelectionModel().getSelectedItem();
        otherTestTxt.setText(otherTestBEAN.getOtherTest());
        otherTestId = otherTestBEAN.getId();
    }

    @FXML
    private void handleOtherTestTblReleased(KeyEvent event) {
        saveBtn.setText("Update");
        otherTestBEAN = (OtherTestBEAN) otherTstTbl.getSelectionModel().getSelectedItem();
        otherTestTxt.setText(otherTestBEAN.getOtherTest());
        otherTestId = otherTestBEAN.getId();
    }

}

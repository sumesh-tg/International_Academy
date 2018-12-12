/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.source;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.admin.dao.SourcePOJO;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.context.Context;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLSourceController implements Initializable {

    @FXML
    private Pane mainPane;
    @FXML
    private Pane tablePane;
    @FXML
    private TableView<SourcePOJO> sourceTableView;
    @FXML
    private TableColumn<SourcePOJO, String> sourceColumn;
    @FXML
    private TableColumn<SourcePOJO, String> descriptionColoumn;
    @FXML
    private TextField searchTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField sourceTxt;
    @FXML
    private TextArea descriptionTxtArea;
    private ObservableList<SourcePOJO> masterSearchData = FXCollections.observableArrayList();

    static int sourceId = 0;
    @FXML
    private Button btnExcelUpload;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllTableDetails();
        getTableDetails();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        SourcePOJO sourcePOJO = new SourcePOJO();
        sourcePOJO.setSource(sourceTxt.getText());
        sourcePOJO.setDescription(descriptionTxtArea.getText());
        Context.getInstance().currentProfile().setSourcePOJO(sourcePOJO);
        if (sourceId > 0) {
            int row = AdminDAO.updateSourceDetails(sourceId);
            clearFields();
            getTableDetails();
        } else {
            int row = AdminDAO.insertSourceDetails();
            if (row > 0) {
                clearFields();
                getTableDetails();
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
        sourceTxt.setText("");
        descriptionTxtArea.setText("");
        sourceId = 0;
        saveBtn.setText("Save");
    }

    /**
     *
     */
    public void getTableDetails() {
        List<SourcePOJO> sourceList = AdminDAO.getAllSourceDetails();
        sourceColumn.setCellValueFactory(
                new PropertyValueFactory<>("source"));
        descriptionColoumn.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        masterSearchData.removeAll(masterSearchData);
        for (SourcePOJO s : sourceList) {
            masterSearchData.add(s);
        }
        FilteredList<SourcePOJO> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getSource().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (enquiry.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            SortedList<SourcePOJO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(sourceTableView.comparatorProperty());
            sourceTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<SourcePOJO> sourceList = AdminDAO.getAllSourceDetails();
        sourceColumn.setCellValueFactory(
                new PropertyValueFactory<>("source"));
        descriptionColoumn.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        masterSearchData.removeAll(masterSearchData);
        for (SourcePOJO s : sourceList) {
            masterSearchData.add(s);
        }
        sourceTableView.getItems().setAll(masterSearchData);
    }

    @FXML
    private void handlesSourceTableView(MouseEvent event) {
        saveBtn.setText("Update");
        SourcePOJO sourcePOJO = (SourcePOJO) sourceTableView.getSelectionModel().getSelectedItem();
        sourceTxt.setText(sourcePOJO.getSource());
        descriptionTxtArea.setText(sourcePOJO.getDescription());
        sourceId = sourcePOJO.getSourceId();
    }

    @FXML
    private void handleSourceTableViewKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        SourcePOJO sourcePOJO = (SourcePOJO) sourceTableView.getSelectionModel().getSelectedItem();
        sourceTxt.setText(sourcePOJO.getSource());
        descriptionTxtArea.setText(sourcePOJO.getDescription());
        sourceId = sourcePOJO.getSourceId();
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("source_tbl");
    }

}

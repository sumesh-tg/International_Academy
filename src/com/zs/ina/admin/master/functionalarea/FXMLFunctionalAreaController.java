/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.functionalarea;

import com.zs.ina.context.Context;
import com.zs.ina.admin.master.functionalarea.dao.FunctionalAreaDAO;
import com.zs.ina.admin.master.functionalarea.dao.FunctionalAreaPOJO;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLFunctionalAreaController implements Initializable {

    @FXML
    private Pane mainPane;
    @FXML
    private ComboBox<?> operationCmb;
    @FXML
    private TextField functionalTxt;
    @FXML
    private TextField levelTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TableColumn<FunctionalAreaPOJO, String> operationColumn;
    @FXML
    private TableColumn<FunctionalAreaPOJO, String> functionalColumn;
    @FXML
    private TableColumn<FunctionalAreaPOJO, String> levelColumn;
    @FXML
    private TableView<FunctionalAreaPOJO> functionalTableView;
    ObservableList operationalArea = FXCollections.observableArrayList("Study", "Work", "Migration", "Training");
    @FXML
    private TableColumn<FunctionalAreaPOJO, Integer> idColumn;

    static int functionalId = 0;
    @FXML
    private Pane tablePane;
    @FXML
    private TextField searchTxt;
    @FXML
    private ImageView searchImgView;
    private ObservableList<FunctionalAreaPOJO> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operationCmb.setItems(operationalArea);
        operationCmb.getSelectionModel().selectFirst();
        getFunctionalArea();
        getAllTableDetails();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        FunctionalAreaPOJO functionalAreaPOJO = new FunctionalAreaPOJO();
        functionalAreaPOJO.setOperationArea(operationCmb.getSelectionModel().getSelectedItem().toString());
        functionalAreaPOJO.setFuntionalArea(functionalTxt.getText());
        functionalAreaPOJO.setLevel(levelTxt.getText());

        Context.getInstance().currentProfile().setFunctionalAreaPOJO(functionalAreaPOJO);
        if (functionalId > 0) {
            int row = FunctionalAreaDAO.updateFunctionalArea(functionalId);
            if (row > 0) {
                clearFields();
                getFunctionalArea();
            }
        } else {
            int row = FunctionalAreaDAO.insertFunctionalArea();
            if (row > 0) {
                clearFields();
                getFunctionalArea();
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
        operationCmb.getSelectionModel().selectFirst();
        functionalTxt.setText("");
        levelTxt.setText("");
        functionalId = 0;
        saveBtn.setText("Save");
    }

    /**
     *
     */
    public void getFunctionalArea() {
        List<FunctionalAreaPOJO> functionalAreaList = FunctionalAreaDAO.getAgencyDetails();
        operationColumn.setCellValueFactory(
                new PropertyValueFactory<>("operationArea"));
        functionalColumn.setCellValueFactory(
                new PropertyValueFactory<>("funtionalArea"));
        levelColumn.setCellValueFactory(
                new PropertyValueFactory<>("level"));
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (FunctionalAreaPOJO fp : functionalAreaList) {
            masterSearchData.add(fp);
        }

        FilteredList<FunctionalAreaPOJO> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getOperationArea().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getFuntionalArea().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  email.
                } else if (enquiry.getLevel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter phone.
                }
                return false; // Does not match.
            });
            SortedList<FunctionalAreaPOJO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(functionalTableView.comparatorProperty());
            functionalTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<FunctionalAreaPOJO> functionalAreaList = FunctionalAreaDAO.getAgencyDetails();
        operationColumn.setCellValueFactory(
                new PropertyValueFactory<>("operationArea"));
        functionalColumn.setCellValueFactory(
                new PropertyValueFactory<>("funtionalArea"));
        levelColumn.setCellValueFactory(
                new PropertyValueFactory<>("level"));
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (FunctionalAreaPOJO fp : functionalAreaList) {
            masterSearchData.add(fp);
        }
        functionalTableView.getItems().setAll(masterSearchData);
    }

    @FXML
    private void handleFunctionalTableView(MouseEvent event) {
        saveBtn.setText("Update");
        FunctionalAreaPOJO functionalAreaPOJO = (FunctionalAreaPOJO) functionalTableView.getSelectionModel().getSelectedItem();
        operationalArea.remove(functionalAreaPOJO.getOperationArea());
        operationalArea.add(functionalAreaPOJO.getOperationArea());
        operationCmb.getSelectionModel().selectLast();
        functionalId = functionalAreaPOJO.getId();
        functionalTxt.setText(functionalAreaPOJO.getFuntionalArea());
        levelTxt.setText(functionalAreaPOJO.getLevel());
    }

    @FXML
    private void handleFunctionalTableViewKeyReleased(KeyEvent event) {
        FunctionalAreaPOJO functionalAreaPOJO = (FunctionalAreaPOJO) functionalTableView.getSelectionModel().getSelectedItem();
        operationalArea.remove(functionalAreaPOJO.getOperationArea());
        operationalArea.add(functionalAreaPOJO.getOperationArea());
        operationCmb.getSelectionModel().selectLast();
        functionalId = functionalAreaPOJO.getId();
        functionalTxt.setText(functionalAreaPOJO.getFuntionalArea());
        levelTxt.setText(functionalAreaPOJO.getLevel());
    }

}

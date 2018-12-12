/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.empstatus;

import com.zs.ina.enquiry.empstatus.dao.EmpStatusBEAN;
import com.zs.ina.enquiry.empstatus.dao.EmployeeStatusDAO;
import com.zs.ina.task.dao.TasksBEAN;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * FXML Controller class
 *
 * @author zscomp1
 */
public class FXMLEmployeStatusFullController implements Initializable {

    @FXML
    private TableView<EmpStatusBEAN> empStatusTable;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusName;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusPosition;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusStatus;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusContactNo;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusExtNo;
    @FXML
    private ImageView btnClose;
    @FXML
    private ComboBox<String> cmbBranch;
    @FXML
    private Button btnClose1;
    @FXML
    private TextField txtSearch;
    ObservableList<EmpStatusBEAN> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param empStatusList
     * @param branch
     * @param selectedbranch
     */
    public void initData(List<EmpStatusBEAN> empStatusList, ObservableList branch, String selectedbranch) {
        masterSearchData.addAll(empStatusList);
        cmbBranch.setItems(branch);
        if (cmbBranch.getItems().contains(selectedbranch)) {
            cmbBranch.getSelectionModel().clearAndSelect(cmbBranch.getItems().indexOf(selectedbranch));
        }
        empStatusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        empStatusPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        empStatusStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        empStatusContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        empStatusExtNo.setCellValueFactory(new PropertyValueFactory<>("ext_number"));
//        empStatusTable.getItems().setAll(empStatusList);
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    masterSearchData.clear();
                    txtSearch.clear();
                    List<EmpStatusBEAN> empStatusList = EmployeeStatusDAO.getEmpStatus(newValue);
                    masterSearchData.addAll(empStatusList);
                 //   empStatusTable.getItems().clear();
                 //   empStatusTable.getItems().setAll(empStatusList);
                }

            }
        });
        FilteredList<EmpStatusBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(emp -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (emp.getContact_number().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (emp.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (emp.getPosition().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<EmpStatusBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(empStatusTable.comparatorProperty());
        empStatusTable.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnClose1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }
        });
        btnClose.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }
        });
    }

}

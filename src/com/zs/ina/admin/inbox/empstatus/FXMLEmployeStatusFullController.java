/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.inbox.empstatus;

import com.zs.ina.admin.inbox.empstatus.dao.EmpStatusBEAN;
import com.zs.ina.admin.inbox.empstatus.dao.EmployeeStatusDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     * @param empStatusList
     * @param selectedbranch
     * @param branch
     */
    public void initData(List<EmpStatusBEAN> empStatusList, ObservableList branch, String selectedbranch) {
        cmbBranch.setItems(branch);
        if (cmbBranch.getItems().contains(selectedbranch)) {
            cmbBranch.getSelectionModel().clearAndSelect(cmbBranch.getItems().indexOf(selectedbranch));
        }
        empStatusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        empStatusPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        empStatusStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        empStatusContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        empStatusExtNo.setCellValueFactory(new PropertyValueFactory<>("ext_number"));
        empStatusTable.getItems().setAll(empStatusList);
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<EmpStatusBEAN> empStatusList = EmployeeStatusDAO.getEmpStatus(newValue);
                empStatusTable.getItems().clear();
                empStatusTable.getItems().setAll(empStatusList);

            }
        });
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

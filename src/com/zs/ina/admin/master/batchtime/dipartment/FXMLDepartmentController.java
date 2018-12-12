/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.batchtime.dipartment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author shine
 */
public class FXMLDepartmentController implements Initializable {
    @FXML
    private HBox departmentHbox;
    @FXML
    private TextField departmentTxt;
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
    private TableView<?> departmentTbl;
    @FXML
    private TableColumn<?, ?> departmentClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    @FXML
    private HBox hbox;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) {
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
    }

    @FXML
    private void handleCourseTypleTbl(MouseEvent event) {
    }

    @FXML
    private void handleCourseTypleTblReleased(KeyEvent event) {
    }
    
}

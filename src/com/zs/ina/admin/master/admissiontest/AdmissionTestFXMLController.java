/*
 * Copyright (C) 2016 100017
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.admin.master.admissiontest;

import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestBEAN;
import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterDAO;
import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterIMPL;
import com.zs.ina.admin.master.otherskills.dao.OtherSkills;
import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterIMPL;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestDAO;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author 100017
 */
public class AdmissionTestFXMLController implements Initializable {

    @FXML
    private TextField txtTest;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<AdmissionTestBEAN> tblAdmissionTest;
    @FXML
    private TableColumn<?, ?> clmnId;
    @FXML
    private TableColumn<?, ?> clmnTest;
    AdmissionTestBEAN admissionTest;
    AdmissionTestMasterDAO  admissionTestDAO;
    private ObservableList<AdmissionTestBEAN> masterSearchData = FXCollections.observableArrayList();
    final ContextMenu usernameValidator = new ContextMenu();
    private String admissionTestId ="";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        initSearchTable();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        if (validation()) {
            admissionTestDAO = new AdmissionTestMasterIMPL();
            admissionTest = new AdmissionTestBEAN();
            admissionTest.setId(admissionTestId);
            admissionTest.setTest(txtTest.getText());
            if (admissionTestId.equals("")) {
                if (admissionTestDAO.insertMasterAdmissionTest(admissionTest.getTest()) == 1) {
                    initTable();
                    clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "insertion Failed");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                }

            } else {
                if (admissionTestDAO.updatemasterAdmissionTest(admissionTest) == 1) {
                    initTable();
                    clear();
                    this.admissionTestId="";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "update Failed");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    this.admissionTestId="";
                }

            }
        } 
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        deleteAdmissionTest(admissionTestId);
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleTblMouseClick(MouseEvent event) {
        btnSave.setText("Update");
        admissionTest = (AdmissionTestBEAN) tblAdmissionTest.getSelectionModel().getSelectedItem();
        txtTest.setText(admissionTest.getTest());
        admissionTestId = admissionTest.getId();
    }

    @FXML
    private void handleTblKeyReleased(KeyEvent event) {
    }

    private void initTable() {
        admissionTestDAO = new AdmissionTestMasterIMPL();

        ObservableList<AdmissionTestBEAN> technicalBEANs = admissionTestDAO.getAdmissionTest();
        clmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnTest.setCellValueFactory(new PropertyValueFactory<>("test"));
        masterSearchData.removeAll(masterSearchData);
        for (AdmissionTestBEAN bp : technicalBEANs) {
            masterSearchData.add(bp);
        }
        tblAdmissionTest.setItems(masterSearchData);
    }

    private void initSearchTable() {
        admissionTestDAO = new AdmissionTestMasterIMPL();

        ObservableList<AdmissionTestBEAN> technicalBEANs = admissionTestDAO.getAdmissionTest();
        clmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnTest.setCellValueFactory(new PropertyValueFactory<>("test"));
        masterSearchData.removeAll(masterSearchData);
        for (AdmissionTestBEAN bp : technicalBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<AdmissionTestBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
                if (enquiry.getTest().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
                return false; // Does not match.
            });
            SortedList<AdmissionTestBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblAdmissionTest.comparatorProperty());
            tblAdmissionTest.setItems(sortedData);
        });
    }

    private void clear() {
        txtTest.setText("");
        txtSearch.setText("");
        btnSave.setText("Save");
    }

    private void deleteAdmissionTest(String admissionTestId) {
        if (admissionTestId.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select a row from the table");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stage.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
        } else {
            admissionTestDAO = new AdmissionTestMasterIMPL();
            if (admissionTestDAO.deleteMasterAdmissionTest(admissionTestId) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnSave.getScene().getWindow());
                alert.showAndWait();
                clear();
                initTable();
              this.admissionTestId ="";
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnSave.getScene().getWindow());
                alert.showAndWait();
                clear();
                this.admissionTestId ="";
            }
        }
    }

    private boolean validation() {
        if (txtTest.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter test field "));
            usernameValidator.show(txtTest, Side.RIGHT, 10, 0);
            return false;
        } else {
            return true;
        }
    }

}

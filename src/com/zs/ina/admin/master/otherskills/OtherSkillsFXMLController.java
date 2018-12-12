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
package com.zs.ina.admin.master.otherskills;

import com.zs.ina.admin.master.otherskills.dao.OtherSkills;
import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterDAO;
import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.ComboBox;
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
public class OtherSkillsFXMLController implements Initializable {

    @FXML
    private TextField txtSkill;
    @FXML
    private Button btnSkillAdd;
    @FXML
    private ComboBox<String> cmbSkill;
    @FXML
    private TextField txtType;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<OtherSkills> tblOtherSkills;
    @FXML
    private TableColumn<?, ?> clmnOtherId;
    @FXML
    private TableColumn<?, ?> clmnSkill;
    @FXML
    private TableColumn<?, ?> clmnType;
    OtherSkillsMasterDAO skillsMasterDAO;
    private ObservableList<OtherSkills> masterSearchData = FXCollections.observableArrayList();
    final ContextMenu usernameValidator = new ContextMenu();
    private ObservableList skillsList = FXCollections.observableArrayList();
    private String otherSkillId = "";
    OtherSkills otherSkills;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSkillCombo();
        initOtherSkillTable();
        initSearchTable();
    }

    @FXML
    private void handleAddSkill(ActionEvent event) {
        addSkill();
        initSkillCombo();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        saveOtherSkills();
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        deleteOtherSkills(otherSkillId);
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleTblMouseClick(MouseEvent event) {
        btnSave.setText("Update");
        otherSkills = (OtherSkills) tblOtherSkills.getSelectionModel().getSelectedItem();
        Bindings.bindBidirectional(cmbSkill.valueProperty(), otherSkills.skillProperty());
        txtType.setText(otherSkills.getType());
        otherSkillId = otherSkills.getOtherSkillsId();
    }

    @FXML
    private void handleTblKeyReleased(KeyEvent event) {
    }

    private void initOtherSkillTable() {
        skillsMasterDAO = new OtherSkillsMasterIMPL();

        ObservableList<OtherSkills> technicalBEANs = skillsMasterDAO.getOtherSkills();
        clmnOtherId.setCellValueFactory(new PropertyValueFactory<>("otherSkillsId"));
        clmnSkill.setCellValueFactory(new PropertyValueFactory<>("skill"));
        clmnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        masterSearchData.removeAll(masterSearchData);
        for (OtherSkills bp : technicalBEANs) {
            masterSearchData.add(bp);
        }

        tblOtherSkills.setItems(masterSearchData);
    }

    private void addSkill() {
        skillsMasterDAO = new OtherSkillsMasterIMPL();
        OtherSkills otherSkills = new OtherSkills();
        otherSkills.setSkill(txtSkill.getText());
        if (skillValidation()) {
            if (skillsMasterDAO.insertOtherSkills(otherSkills.getSkill()) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "skill saved successfully");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnSave.getScene().getWindow());
                alert.showAndWait();
                txtSkill.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "skill save failed");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnSave.getScene().getWindow());
                alert.showAndWait();
                txtSkill.setText("");
            }
        }
    }

    private boolean skillValidation() {
        if (txtSkill.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter skill field "));
            usernameValidator.show(txtSkill, Side.RIGHT, 10, 0);
            return false;
        } else {
            return true;
        }
    }

    private void clear() {
        txtSkill.setText("");
        txtType.setText("");
        cmbSkill.getSelectionModel().selectFirst();
        btnSave.setText("Save");
    }

    private void initSkillCombo() {
        skillsMasterDAO = new OtherSkillsMasterIMPL();
        skillsList.removeAll(skillsList);
        ObservableList<OtherSkills> skills = skillsMasterDAO.retriveSkills();
        for (OtherSkills bp : skills) {
            skillsList.add(bp.getSkill());
        }
        cmbSkill.setItems(skillsList);
    }

    private void saveOtherSkills() {
        if (validation()) {
            OtherSkills otherSkills = new OtherSkills();
            otherSkills.setSkill(cmbSkill.getSelectionModel().getSelectedItem().toString());
            otherSkills.setType(txtType.getText());
            otherSkills.setOtherSkillsId(otherSkillId);
            skillsMasterDAO = new OtherSkillsMasterIMPL();
            if (otherSkills.getOtherSkillsId().equals("")) {
                if (skillsMasterDAO.insertOtherSkills(otherSkills.getSkill(), otherSkills.getType()) == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Inserted");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    initOtherSkillTable();
                    clear();
                    this.otherSkillId = "";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Insertion Failed");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    clear();
                }
            } else {
                if (skillsMasterDAO.updateOtherSkills(otherSkills) == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    initOtherSkillTable();
                    clear();
                    this.otherSkillId = "";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Updateion Failed");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    initOtherSkillTable();
                    clear();
                }
            }
        }
    }

    private boolean validation() {
        if (cmbSkill.getSelectionModel().getSelectedIndex() < 0) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please select skill "));
            usernameValidator.show(cmbSkill, Side.RIGHT, 10, 0);
            return false;
        } else if (txtType.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter type "));
            usernameValidator.show(txtType, Side.RIGHT, 10, 0);
            return false;
        } else {
            return true;
        }
    }

    private void deleteOtherSkills(String otherSkillId) {
        skillsMasterDAO = new OtherSkillsMasterIMPL();
        if (skillsMasterDAO.deleteOtherSkill(otherSkillId) == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully deleted");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stage.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
            initOtherSkillTable();
            clear();
            this.otherSkillId = "";
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stage.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
        }
    }

    private void initSearchTable() {
        skillsMasterDAO = new OtherSkillsMasterIMPL();

        ObservableList<OtherSkills> technicalBEANs = skillsMasterDAO.getOtherSkills();
        clmnOtherId.setCellValueFactory(new PropertyValueFactory<>("otherSkillsId"));
        clmnSkill.setCellValueFactory(new PropertyValueFactory<>("skill"));
        clmnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        masterSearchData.removeAll(masterSearchData);
        for (OtherSkills bp : technicalBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<OtherSkills> filteredData = new FilteredList<>(masterSearchData, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
                if (enquiry.getSkill().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                }
                return false; // Does not match.
            });
            SortedList<OtherSkills> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblOtherSkills.comparatorProperty());
            tblOtherSkills.setItems(sortedData);
        });
    }

}

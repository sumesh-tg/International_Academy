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
package com.zs.ina.admin.master.technicalskills;

import com.zs.ina.admin.master.technicalskills.dao.TechnicalBean;
import com.zs.ina.admin.master.technicalskills.dao.TechnicalSkillsMasterDAO;
import com.zs.ina.admin.master.technicalskills.dao.TechnicalSkillsMasterIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class TechnicalSkillsFXMLController implements Initializable {

    @FXML
    private TextField txtTechnology;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<TechnicalBean> tblTechnicalSkills;
    @FXML
    private TableColumn<?, ?> clmnId;
    @FXML
    private TableColumn<?, ?> clmnTechnology;
    private ObservableList<TechnicalBean> masterSearchData = FXCollections.observableArrayList();
    TechnicalBean technicalBean;
    TechnicalSkillsMasterDAO masterDAO;
    final ContextMenu usernameValidator = new ContextMenu();
    private String technologyId="";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        technicalBean = new TechnicalBean();
        technicalBean.setTechnology(txtTechnology.getText());
        technicalBean.setTechnologyId(technologyId);
        saveSkills(technicalBean);
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        deleteSkill();
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleTblMouseClick(MouseEvent event) {
        btnSave.setText("Update");
        technicalBean = (TechnicalBean) tblTechnicalSkills.getSelectionModel().getSelectedItem();
        txtTechnology.setText(technicalBean.getTechnology());
        technologyId = technicalBean.getTechnologyId();
    }

    @FXML
    private void handleTblKeyReleased(KeyEvent event) {
    }

    private void initTable() {
        masterDAO = new TechnicalSkillsMasterIMPL();

        ObservableList<TechnicalBean> technicalBEANs = masterDAO.getTechnicalSkills();
        clmnId.setCellValueFactory(new PropertyValueFactory<>("technologyId"));
        clmnTechnology.setCellValueFactory(new PropertyValueFactory<>("technology"));
        masterSearchData.removeAll(masterSearchData);
        for (TechnicalBean bp : technicalBEANs) {
            masterSearchData.add(bp);
        }
        tblTechnicalSkills.setItems(masterSearchData);
    }

    private void saveSkills(TechnicalBean technicalBean) {
        masterDAO = new TechnicalSkillsMasterIMPL();
        if (validation()) {
            if (technicalBean.getTechnologyId().equals("")) {
                if (masterDAO.insertTechnicalSkills(technicalBean.getTechnology()) == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Technical Skill saved");

                    //alert.setHeaderText("International Accadamy");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    clear();
                    initTable();
                    this.technologyId="";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "save unsuccessful");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                }
            } else {
                if (masterDAO.updateTechnicalSkills(technologyId, technicalBean.getTechnology()) == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Technical Skill saved");

                    //alert.setHeaderText("International Accadamy");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    clear();
                    initTable();
                    this.technologyId="";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "save unsuccessful");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                }
            }
        }
    }

    private boolean validation() {
        if (txtTechnology.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Salary field "));
            usernameValidator.show(txtTechnology, Side.RIGHT, 10, 0);
            return false;
        } else {
            return true;
        }
    }

    private void clear() {
        txtTechnology.setText("");
        txtSearch.setText("");
        btnSave.setText("Save");
    }

    private void deleteSkill() {
        masterDAO = new TechnicalSkillsMasterIMPL();
        if (!technologyId.equals("")) {
            if (masterDAO.deleteTechnicalSkills(technologyId) == 1) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "selected entry successfully deleted ");

                    //alert.setHeaderText("International Accadamy");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    clear();
                    initTable();
                    technologyId="";
            } else {
                 Alert alert = new Alert(Alert.AlertType.ERROR, "Deletion unsuccessful");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    technologyId="";
            }
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING, "Please slect a technology from the table.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
        }
    }

}

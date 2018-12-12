/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.enquiry.search.editing;

import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.model.AssesmentMigrateBEAN;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLMigrationRequiredController implements Initializable {

    @FXML
    private Button btnSave;
    CounselorDetailsBEAN counselorDetailsBEAN;
    ObservableList profession = FXCollections.observableArrayList();
    InlineEditingDAO editingDAO = new InlineEditingIMPL();
    @FXML
    private GridPane gridMigration;
    List<AssesmentMigrateBEAN> listMigrateRequired = new ArrayList<>();
    String ENQUIRY_ID = null;
    CountryDAO countryDAO = new CountryIMPL();
    ObservableList migrateCategory = FXCollections.observableArrayList();
    ShowPopupMessages showAlerts = new ShowPopupMessages();
    ObservableList choiceObsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        profession = RetrieveStaticMasterDAO.getProfession();
        migrateCategory = RetrieveStaticMasterDAO.getMigrateCategories();
        /* ====================== Call Dynamic Rows ====================== */
        listMigrateRequired = ProgramSuggestedRequiredDAO.getMigrationDetails(ENQUIRY_ID);
        choiceObsList.clear();
        if (listMigrateRequired.size() > 0) {
            for (int i = 1; i <= listMigrateRequired.size() + 1; i++) {
                choiceObsList.add("" + i);
            }
            addMigrateGridRows();
        } else {
            choiceObsList.add("1");
            choiceObsList.add("2");
            AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
            listMigrateRequired.add(assesmentMigrateBEAN);
            addMigrateGridRows();
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* ====================== Save Migration ====================== */
                if (validateMasterMigrateData(listMigrateRequired) <= 0) {
                    for (AssesmentMigrateBEAN assesmentMigrateBEAN : listMigrateRequired) {
                        assesmentMigrateBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentMigrateBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.updateMigrateDetails(assesmentMigrateBEAN);
                        } else {
                            ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);

                        }
                    }
                }
                Context.getInstance().currentProfile().getPopOver().hide();

            }
        });
    }

    /**
     *
     */
    public void addMigrateGridRows() {
        gridMigration.getChildren().clear();
        for (AssesmentMigrateBEAN assesmentMigrateBEAN : listMigrateRequired) {
            assesmentMigrateBEAN.setMigrate("Migrate");
            assesmentMigrateBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox migrateCmb = new ComboBox();
            migrateCmb.setPromptText("Select");
            migrateCmb.setMinWidth(120);
            migrateCmb.setMaxWidth(120);
            ComboBox cmbMigrationCountry = new ComboBox(countryDAO.retrieveMasterAllCountries());
            cmbMigrationCountry.setMinWidth(180);
            cmbMigrationCountry.setPromptText("Select");
            ComboBox cmbMigrationLocation = new ComboBox();
            cmbMigrationLocation.setMinWidth(180);
            cmbMigrationLocation.setPromptText("Select Location");
            ComboBox migrateProfessionCmb = new ComboBox(profession);
            migrateProfessionCmb.setMinWidth(180);
            migrateProfessionCmb.setPromptText("Select");
            ComboBox migrateCategoryCmb = new ComboBox(migrateCategory);
            migrateCategoryCmb.setMinWidth(180);
            migrateCategoryCmb.setPromptText("Select");
            ComboBox cmbMigrateChoise = new ComboBox(choiceObsList);
            cmbMigrateChoise.setMinWidth(80);
            cmbMigrateChoise.setPromptText("Select");

            GridPane.setHgrow(migrateCmb, Priority.ALWAYS);
            GridPane.setHgrow(cmbMigrationCountry, Priority.ALWAYS);
            GridPane.setHgrow(cmbMigrationLocation, Priority.ALWAYS);
            GridPane.setHgrow(migrateProfessionCmb, Priority.ALWAYS);
            GridPane.setHgrow(migrateCategoryCmb, Priority.ALWAYS);
            migrateCmb.setMaxWidth(Double.MAX_VALUE);
            cmbMigrationCountry.setMaxWidth(Double.MAX_VALUE);
            cmbMigrationLocation.setMaxWidth(Double.MAX_VALUE);
            migrateProfessionCmb.setMaxWidth(Double.MAX_VALUE);
            migrateCategoryCmb.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            HBox hBoxButtons = new HBox(5);
            hBoxButtons.getChildren().addAll(btnPlus, btnClose);
            hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
            cmbMigrationCountry.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbMigrationLocation.setItems(location);

                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterMigrateData(listMigrateRequired) <= 0) {
                        if (assesmentMigrateBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateMigrateDetails(assesmentMigrateBEAN);
                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);
                        }
                        AssesmentMigrateBEAN emptyRequiredBEAN = new AssesmentMigrateBEAN();
                        listMigrateRequired.add(emptyRequiredBEAN);
                        addMigrateGridRows();
                        for (int i = 1; i <= listMigrateRequired.size() + 1; i++) {
                            if (!choiceObsList.contains("" + i)) {
                                choiceObsList.add("" + i);
                            }
                        }
                        gridMigration.requestFocus();
                    } else {
                        /* ====================== validation messages ====================== */
                        showAlerts.showError("All the fields required in migration required section !", btnSave);

                    }
                }
            }
            );

            btnClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event
                ) {
//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
//                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//                            stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
//                            stage.initOwner(workCmb.getScene().getWindow());
//                            alert.showAndWait();
//                            if (alert.getResult() == ButtonType.YES) {
//                                if (assesmentMigrateBEAN.getRowId() != null) {
//                                    EnquiryAssessmentPersonDAO.deleteMigrateDetails(assesmentMigrateBEAN.getRowId());
//                                    gridMigration.getChildren().clear();
//                                    masterMigrateList.clear();
//
//                                    List<AssesmentMigrateBEAN> migrateBEANList = EnquiryAssessmentPersonDAO.getMigrationDetails(ENQUIRY_ID);
//                                    if (migrateBEANList.size() > 0) {
//                                        addMigrateGridRows(migrateBEANList);
//                                    } else {
//                                        AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
//                                        List<AssesmentMigrateBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(assesmentMigrateBEAN);
//                                        addMigrateGridRows(emptyList);
//                                    }
//
//                                } else {
//                                    gridMigration.getChildren().clear();
//                                    masterMigrateList.clear();
//                                    List<AssesmentMigrateBEAN> migrateBEANList = EnquiryAssessmentPersonDAO.getMigrationDetails(ENQUIRY_ID);
//                                    if (migrateBEANList.size() > 0) {
//                                        addMigrateGridRows(migrateBEANList);
//                                    } else {
//                                        AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
//                                        List<AssesmentMigrateBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(assesmentMigrateBEAN);
//                                        addMigrateGridRows(emptyList);
//                                    }
//                                }
//                                gridMigration.requestFocus();
//                            }
//                            
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentMigrateBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteMigrateDetails(assesmentMigrateBEAN.getRowId());

                        }
                        listMigrateRequired.remove(assesmentMigrateBEAN);
                        if (listMigrateRequired.size() <= 0) {
                            AssesmentMigrateBEAN emptyRequiredBEAN = new AssesmentMigrateBEAN();
                            listMigrateRequired.add(emptyRequiredBEAN);
                        }
                        addMigrateGridRows();
                        gridMigration.requestFocus();
                    }

                }
            }
            );

            Bindings.bindBidirectional(migrateCmb.valueProperty(), assesmentMigrateBEAN.migrateProperty());
            Bindings.bindBidirectional(cmbMigrationCountry.valueProperty(), assesmentMigrateBEAN.countryProperty());
            Bindings.bindBidirectional(cmbMigrationLocation.valueProperty(), assesmentMigrateBEAN.locationProperty());
            Bindings.bindBidirectional(migrateProfessionCmb.valueProperty(), assesmentMigrateBEAN.professionProperty());
            Bindings.bindBidirectional(migrateCategoryCmb.valueProperty(), assesmentMigrateBEAN.categoryProperty());
//            Bindings.bindBidirectional(cmbMigrationApplicationType.valueProperty(), assesmentMigrateBEAN.applicationTypeProperty());
            Bindings.bindBidirectional(cmbMigrateChoise.valueProperty(), assesmentMigrateBEAN.choiceProperty());

//            masterMigrateList.add(assesmentMigrateBEAN);
            if (listMigrateRequired.indexOf(assesmentMigrateBEAN) == 0) {
                //   gridMigration.add(new Label("Program Level"), 0, 0);
                gridMigration.add(new Label("Profession"), 0, 0);
                gridMigration.add(new Label("Category"), 1, 0);
                gridMigration.add(new Label("Country"), 2, 0);
                gridMigration.add(new Label("Location/Province"), 3, 0);
                gridMigration.add(new Label("Choice"), 4, 0);
            }

            //     gridMigration.add(migrateCmb, 0, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            gridMigration.add(migrateProfessionCmb, 0, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            gridMigration.add(migrateCategoryCmb, 1, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            gridMigration.add(cmbMigrationCountry, 2, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            gridMigration.add(cmbMigrationLocation, 3, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            gridMigration.add(cmbMigrateChoise, 4, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            gridMigration.add(hBoxButtons, 5, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);

//            gridMigration.add(btnClose,
//                    5, ROW_COUNT_MIGRATE);
//            masterWorkExpList.add(workExperienceBEAN);
            // JOptionPane.showMessageDialog(null, masterOtherQualifyList.size());
        }

    }

    /**
     *
     * @param assesmentMigrateBEANs
     * @return
     */
    public int validateMasterMigrateData(List<AssesmentMigrateBEAN> assesmentMigrateBEANs) {
        int count = 0;
        for (AssesmentMigrateBEAN validateAssesmentMigrateBEAN : assesmentMigrateBEANs) {
            if (validateMigrateDetails(validateAssesmentMigrateBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param assesmentMigrateBEAN
     * @return
     */
    public String validateMigrateDetails(AssesmentMigrateBEAN assesmentMigrateBEAN) {

        if (assesmentMigrateBEAN.getMigrate() == null || assesmentMigrateBEAN.getCountry() == null || assesmentMigrateBEAN.getLocation() == null || assesmentMigrateBEAN.getProfession() == null
                || assesmentMigrateBEAN.getCategory() == null || assesmentMigrateBEAN.getChoice() == null) {
            return "All fields required !";
        }
        return "true";
    }

}

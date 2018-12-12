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
import com.zs.ina.assesment.model.AssesmentWorkBEAN;
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
public class FXMLWorkRequiredController implements Initializable {

    
    
    @FXML
    private Button btnSave;
    CounselorDetailsBEAN counselorDetailsBEAN;
    ObservableList profession = FXCollections.observableArrayList();
    InlineEditingDAO editingDAO = new InlineEditingIMPL();
    @FXML
    private GridPane gridWork;
    List<AssesmentWorkBEAN> listWorkRequired = new ArrayList<>();
    String ENQUIRY_ID = null;
    ShowPopupMessages showAlerts = new ShowPopupMessages();
    ObservableList choiceObsList = FXCollections.observableArrayList();
    CountryDAO countryDAO = new CountryIMPL();
    ObservableList currencyList = FXCollections.observableArrayList();
    ObservableList minSalaryList = FXCollections.observableArrayList();
    ObservableList maxSalaryList = FXCollections.observableArrayList();

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
        currencyList = RetrieveStaticMasterDAO.getCurrencies();
        minSalaryList = RetrieveStaticMasterDAO.getSalary();
        maxSalaryList = RetrieveStaticMasterDAO.getMaxSalary();

        listWorkRequired = ProgramSuggestedRequiredDAO.getWorkDetails(ENQUIRY_ID);
        choiceObsList.clear();
        if (listWorkRequired.size() > 0) {
            choiceObsList.clear();
            for (int i = 1; i <= listWorkRequired.size() + 1; i++) {
                choiceObsList.add("" + i);
            }
            addWorkGridRows();
        } else {
            AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
            listWorkRequired.add(assesmentWorkBEAN);
            choiceObsList.add("1");
            addWorkGridRows();
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* ====================== Save Work ====================== */
                if (validateMasterWorkDetails(listWorkRequired) <= 0) {
                    for (AssesmentWorkBEAN assesmentWorkBEAN : listWorkRequired) {
                        assesmentWorkBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentWorkBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.updateWorkDetails(assesmentWorkBEAN);
                        } else {
                            ProgramSuggestedRequiredDAO.insertWorkDetails(assesmentWorkBEAN);
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
    public void addWorkGridRows() {
        gridWork.getChildren().clear();
        for (AssesmentWorkBEAN assesmentWorkBEAN : listWorkRequired) {
            assesmentWorkBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox workDyCmb = new ComboBox();
            assesmentWorkBEAN.setWork("Work");
            workDyCmb.setMinWidth(100);
            workDyCmb.setPromptText("Select");
            ComboBox cmbWorkCountry = new ComboBox(countryDAO.retrieveMasterAllCountries());
            cmbWorkCountry.setMinWidth(100);
            cmbWorkCountry.setPromptText("Select");
            ComboBox cmbWorkLocation = new ComboBox();
            cmbWorkLocation.setMinWidth(100);
            cmbWorkLocation.setPromptText("Location");
            ComboBox workProfessionCmb = new ComboBox(profession);
            workProfessionCmb.setMinWidth(100);
            workProfessionCmb.setPromptText("Select");
            ComboBox cmbWorkSkills = new ComboBox(profession);
            cmbWorkSkills.setMinWidth(120);
            cmbWorkSkills.setPromptText("Select");
            ComboBox cmbWorkCurrency = new ComboBox(currencyList);
            cmbWorkCurrency.setMinWidth(50);

            cmbWorkCurrency.setPromptText("Select Currency");
            ComboBox CmbWorkMin = new ComboBox(minSalaryList);
            CmbWorkMin.setMinWidth(50);
            CmbWorkMin.setPromptText("Select");
            ComboBox cmbWorkMax = new ComboBox(maxSalaryList);
            cmbWorkMax.setMinWidth(50);
            cmbWorkMax.setPromptText("Select");
            ComboBox cmbEmpChoice = new ComboBox(choiceObsList);
            cmbEmpChoice.setMinWidth(90);
            cmbEmpChoice.setPromptText("Select");

            GridPane.setHgrow(workDyCmb, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkCountry, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkLocation, Priority.ALWAYS);
            GridPane.setHgrow(workProfessionCmb, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkCurrency, Priority.ALWAYS);
            GridPane.setHgrow(CmbWorkMin, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkMax, Priority.ALWAYS);
            GridPane.setHgrow(cmbEmpChoice, Priority.ALWAYS);
            workDyCmb.setMaxWidth(Double.MAX_VALUE);
            cmbWorkCountry.setMaxWidth(Double.MAX_VALUE);
            cmbWorkLocation.setMaxWidth(Double.MAX_VALUE);
            workProfessionCmb.setMaxWidth(Double.MAX_VALUE);
            cmbWorkCurrency.setMaxWidth(Double.MAX_VALUE);
            CmbWorkMin.setMaxWidth(Double.MAX_VALUE);
            cmbWorkMax.setMaxWidth(Double.MAX_VALUE);
            cmbEmpChoice.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            HBox hBoxSalary = new HBox();

            HBox hBoxButtons = new HBox();
            hBoxButtons.getChildren().addAll(btnPlus, btnClose);
            hBoxButtons.setAlignment(Pos.TOP_CENTER);
            cmbWorkCountry.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbWorkLocation.setItems(location);
                    cmbWorkLocation.getSelectionModel().selectFirst();

                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("study validate :: " + validateMasterWorkDetails(listWorkRequired));
                    if (validateMasterWorkDetails(listWorkRequired) <= 0) {
                        if (assesmentWorkBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateWorkDetails(assesmentWorkBEAN);
                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertWorkDetails(assesmentWorkBEAN);
                        }
                        AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
                        listWorkRequired.add(assesmentWorkBEAN);
                        addWorkGridRows();
                        for (int i = 1; i <= listWorkRequired.size() + 1; i++) {
                            if (!choiceObsList.contains("" + i)) {
                                choiceObsList.add("" + i);
                            }
                        }
                        gridWork.requestFocus();
                    } else {
                        /* ====================== validation messages ====================== */
                        showAlerts.showError("All the fields required in assessment work required section !", btnSave);
                    }
//                @Override
//                public void handle(ActionEvent event) {
//                    if (assesmentWorkBEAN.getRowId() != null) {
//                        if (validateWorkDetails(assesmentWorkBEAN).equalsIgnoreCase("true")) {
//                            if (validateMasterWorkDetails(masterWorkList) <= 0) {
//                                System.out.println("tes whaaa " + masterWorkList.size());
//                                AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
//                                List<AssesmentWorkBEAN> emptyList = new ArrayList<>();
//                                emptyList.add(assesmentWorkBEAN);
//                                addWorkGridRows(emptyList);
//                            }
//                        } else {
//                            lblErrorWorkng.setText("All fields required!");
//                            lblErrorWorkng.setStyle("-fx-text-fill:red");
//                            fadeOut(lblErrorWorkng, 3000.0);
//                        }
//                    } else if (validateWorkDetails(assesmentWorkBEAN).equalsIgnoreCase("true")) {
//                        EnquiryAssessmentPersonDAO.insertWorkDetails(ENQUIRY_ID, assesmentWorkBEAN);
//                        AssesmentWorkBEAN emptyWorkBEAN = new AssesmentWorkBEAN();
//                        List<AssesmentWorkBEAN> emptyList = new ArrayList<>();
//                        emptyList.add(emptyWorkBEAN);
//                        addWorkGridRows(emptyList);
//                    } else {
//                        lblErrorWorkng.setText("All fields required!");
//                        lblErrorWorkng.setStyle("-fx-text-fill:red");
//                        fadeOut(lblErrorWorkng, 3000.0);
//
//                    }
//                }
                }
            }
            );

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event
                ) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentWorkBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteWorkDetailsByid(assesmentWorkBEAN.getRowId());
//                                    gridWork.getChildren().clear();
                        }
//                                    masterWorkList.clear(); 
//                                    List<AssesmentWorkBEAN> workExpBEANList = EnquiryAssessmentPersonDAO.getWorkDetails(ENQUIRY_ID);
//                                    if (workExpBEANList.size() > 0) {
//                                        addWorkGridRows(workExpBEANList);
//                                    } 
//                                    else {
//                                        AssesmentWorkBEAN emptyAssesmentWorkBEAN = new AssesmentWorkBEAN();
//                                        List<AssesmentWorkBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(emptyAssesmentWorkBEAN);
//                                        addWorkGridRows(emptyList);
//                                    }
                        listWorkRequired.remove(assesmentWorkBEAN);
                        if (listWorkRequired.size() <= 0) {
                            AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
                            listWorkRequired.add(assesmentWorkBEAN);
                        }
                        addWorkGridRows();
                        gridWork.requestFocus();

//                                else {
//                                    gridWork.getChildren().clear();
//                                    masterWorkList.clear();
//                                    List<AssesmentWorkBEAN> workExpBEANList = EnquiryAssessmentPersonDAO.getWorkDetails(ENQUIRY_ID);
//                                    if (workExpBEANList.size() > 0) {
//                                        addWorkGridRows(workExpBEANList);
//                                    } else {
//                                        AssesmentWorkBEAN emptyWorkBEAN = new AssesmentWorkBEAN();
//                                        List<AssesmentWorkBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(emptyWorkBEAN);
//                                        addWorkGridRows(emptyList);
//                                    }
//                                }
                        gridWork.requestFocus();
                    }
                }
            }
            );

            hBoxSalary.setMinWidth(155);
            hBoxSalary.getChildren().addAll(cmbWorkCurrency, CmbWorkMin, cmbWorkMax);
            GridPane.setHgrow(hBoxSalary, Priority.ALWAYS);
            hBoxSalary.setMaxWidth(Double.MAX_VALUE);
            hBoxSalary.setSpacing(3);
            hBoxSalary.setAlignment(Pos.TOP_CENTER);

            Bindings.bindBidirectional(workProfessionCmb.valueProperty(), assesmentWorkBEAN.professionProperty());
            Bindings.bindBidirectional(workDyCmb.valueProperty(), assesmentWorkBEAN.workProperty());
            Bindings.bindBidirectional(cmbWorkCountry.valueProperty(), assesmentWorkBEAN.countryProperty());
            Bindings.bindBidirectional(cmbWorkCurrency.valueProperty(), assesmentWorkBEAN.currenyProperty());
            Bindings.bindBidirectional(CmbWorkMin.valueProperty(), assesmentWorkBEAN.min_salaryProperty());
            Bindings.bindBidirectional(cmbWorkMax.valueProperty(), assesmentWorkBEAN.max_salaryProperty());
            Bindings.bindBidirectional(cmbEmpChoice.valueProperty(), assesmentWorkBEAN.employer_choiceProperty());
            Bindings.bindBidirectional(cmbWorkLocation.valueProperty(), assesmentWorkBEAN.locationProperty());
            Bindings.bindBidirectional(cmbWorkSkills.valueProperty(), assesmentWorkBEAN.skillAreaProperty());
//            Bindings.bindBidirectional(cmbWorkChoice.valueProperty(), assesmentWorkBEAN.choiceProperty());
//            Bindings.bindBidirectional(txtMigrateComment.textProperty(), assesmentWorkBEAN.special_commentProperty());

            if (listWorkRequired.indexOf(assesmentWorkBEAN) == 0) {
                gridWork.add(new Label("Profession"), 0, 0);
                gridWork.add(new Label("Skill Area"), 1, 0);
                gridWork.add(new Label("Salary Per Month"), 2, 0);
                gridWork.add(new Label("Min"), 3, 0);
                gridWork.add(new Label("Max"), 4, 0);

                //    gridWork.add(new Label("Work"), 0, 0);
                gridWork.add(new Label("Country"), 5, 0);
                gridWork.add(new Label("Location"), 6, 0);
                gridWork.add(new Label("Choice"), 7, 0);

            }

            //    gridWork.add(workDyCmb, 0, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(workProfessionCmb, 0, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(cmbWorkSkills, 1, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(cmbWorkCurrency, 2, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(CmbWorkMin, 3, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(cmbWorkMax, 4, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);

            gridWork.add(cmbWorkCountry, 5, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(cmbWorkLocation, 6, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(cmbEmpChoice, 7, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            gridWork.add(hBoxButtons, 8, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
//            gridWork.add(hBoxButtons, 5, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
        }

    }

    /**
     *
     * @param validateAssesmentWorkBEANs
     * @return
     */
    public int validateMasterWorkDetails(List<AssesmentWorkBEAN> validateAssesmentWorkBEANs) {
        int count = 0;

        for (AssesmentWorkBEAN validateAssesmentWorkBEAN : validateAssesmentWorkBEANs) {
            if (validateWorkDetails(validateAssesmentWorkBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param assesmentWorkBEAN
     * @return
     */
    public String validateWorkDetails(AssesmentWorkBEAN assesmentWorkBEAN) {
        String s = "true";
        if (assesmentWorkBEAN.getWork() == null || assesmentWorkBEAN.getCountry() == null || assesmentWorkBEAN.getProfession() == null
                || assesmentWorkBEAN.getCurreny() == null || assesmentWorkBEAN.getMin_salary() == null || assesmentWorkBEAN.getMax_salary() == null
                || assesmentWorkBEAN.getEmployer_choice() == null || assesmentWorkBEAN.getLocation() == null) {
            s = "All fields required !";
        }
        return s;
    }
}

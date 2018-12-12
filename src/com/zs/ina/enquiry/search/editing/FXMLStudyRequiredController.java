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
import com.zs.ina.assesment.model.StudySuggestedRequiredBEAN;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.ina.login.INALoginForm;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLStudyRequiredController implements Initializable {

    @FXML
    private Button btnSave;
    ObservableList programLevelList = FXCollections.observableArrayList();
    CounselorDetailsBEAN counselorDetailsBEAN;
    InlineEditingDAO editingDAO = new InlineEditingIMPL();
    String ENQUIRY_ID = null;
    @FXML
    private GridPane gridStudy;
    ShowPopupMessages showAlerts = new ShowPopupMessages();
    CountryDAO countryDAO = new CountryIMPL();
    ObservableList programFieldList = FXCollections.observableArrayList();
    ObservableList choiceObsList = FXCollections.observableArrayList();
    List<StudySuggestedRequiredBEAN> listStudySuggestedRequired = new ArrayList<>();

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
        System.out.println("Testing study required pop data :: " + counselorDetailsBEAN.toString());
        initMasterData();
        listStudySuggestedRequired = ProgramSuggestedRequiredDAO.retrieveStudyDetails(ENQUIRY_ID);
        addStudyRequiredRows();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (cmbProgramLevel.getSelectionModel().getSelectedIndex() >= 0 && cmbProgramField.getSelectionModel().getSelectedIndex() >= 0) {
//                    studySuggestedRequiredBEAN = new StudySuggestedRequiredBEAN();
//                    studySuggestedRequiredBEAN.setEnquiry_id(counselorDetailsBEAN.getEnquiryID());
//                    counselorDetailsBEAN.setProgramLevel(cmbProgramLevel.getSelectionModel().getSelectedItem().toString());
//                    counselorDetailsBEAN.setProgramField(cmbProgramField.getSelectionModel().getSelectedItem().toString());
//                    counselorDetailsBEAN.setStudyRequired(cmbProgramLevel.getSelectionModel().getSelectedItem().toString() + "-" + cmbProgramField.getSelectionModel().getSelectedItem().toString());
//                    editingDAO.editStudyRequired(counselorDetailsBEAN);
//                }
                for (StudySuggestedRequiredBEAN requiredBEAN : listStudySuggestedRequired) {
                    if (validateStudyRequiredDetails(requiredBEAN).equalsIgnoreCase("true")) {
                        if (requiredBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateStudyDetails(requiredBEAN);
                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertStudyDetails(requiredBEAN);
                        }
                    }
                }
                Notification notification = Notifications.SUCCESS;
                TrayNotification tray = new TrayNotification("Study Required Details Updated!", "Study Required Details Updated successfully!", notification);
                tray.setAnimation(Animations.POPUP);
                tray.showAndDismiss(Duration.seconds(3), Context.getInstance().currentProfile().getPopOver().getOwnerNode());
                tray.setOnDismiss(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Context.getInstance().currentProfile().getPopOver().hide();
                    }
                });
                Context.getInstance().currentProfile().getPopOver().hide();
            }
        });
    }

    /**
     *
     */
    public void initMasterData() {
        List<String> programLevel = RetrieveStaticMasterDAO.getProgramLevels();
        for (String s : programLevel) {
            programLevelList.add(s);
        }
        choiceObsList.clear();
        for (int i = 1; i <= listStudySuggestedRequired.size() + 1; i++) {
            choiceObsList.add("" + i);
        }
    }

    /**
     *
     */
    public void addStudyRequiredRows() {
        gridStudy.getChildren().clear();
        for (StudySuggestedRequiredBEAN studySuggestedRequiredBEAN : listStudySuggestedRequired) {
//            lblErrorStudng.setText("");
            studySuggestedRequiredBEAN.setEnquiry_id(ENQUIRY_ID);
            studySuggestedRequiredBEAN.setStudy("Study");

            //Required Controls
            ComboBox cmbStudy = new ComboBox();
            cmbStudy.setMinWidth(100);
            cmbStudy.setPromptText("Select");
            ComboBox cmbCountryy = new ComboBox(countryDAO.retrieveMasterAllCountries());
            cmbCountryy.setMinWidth(100);
            cmbCountryy.setPromptText("Select");
            ComboBox cmbPrgmLevel = new ComboBox(programLevelList);
            cmbPrgmLevel.setMinWidth(100);
            cmbPrgmLevel.setPromptText("Select");
            ComboBox cmbPrgmField = new ComboBox(programFieldList);
            cmbPrgmField.setMinWidth(99);
            cmbPrgmField.setPromptText("Select");
            ComboBox cmbIntake = new ComboBox();
//            cmbIntake.setMinWidth(98);
            cmbIntake.setPromptText("Select");
            ComboBox cmbUniversity = new ComboBox();
//            cmbUniversity.setMinWidth(130);
            cmbUniversity.setPromptText("Select");
            ComboBox cmbStudyLocations = new ComboBox();
//            cmbStudyLocations.setMinWidth(120);
            cmbStudyLocations.setPromptText("Select");
            ComboBox cmbStudyCurency = new ComboBox();
            cmbStudyCurency.setMinWidth(50);
            cmbStudyCurency.setPromptText("Select");
            cmbStudyCurency.setMaxWidth(100);
            ComboBox cmbStudyAmnt = new ComboBox();
            cmbStudyAmnt.setMinWidth(50);
            cmbStudyAmnt.setPromptText("Select");
            ComboBox cmbSemFee = new ComboBox();
            cmbSemFee.setMinWidth(50);
            cmbSemFee.setPromptText("Select");
            ComboBox cmbProgmChoice = new ComboBox(choiceObsList);
            // cmbProgmChoice.setMinWidth(80);
            cmbProgmChoice.setPromptText("Select");

            GridPane.setHgrow(cmbPrgmLevel, Priority.ALWAYS);
            GridPane.setHgrow(cmbPrgmField, Priority.ALWAYS);
            GridPane.setHgrow(cmbStudyLocations, Priority.ALWAYS);
            GridPane.setHgrow(cmbCountryy, Priority.ALWAYS);
            GridPane.setHgrow(cmbProgmChoice, Priority.ALWAYS);

            cmbPrgmLevel.setMaxWidth(Double.MAX_VALUE);
            cmbPrgmField.setMaxWidth(Double.MAX_VALUE);
            cmbStudyLocations.setMaxWidth(Double.MAX_VALUE);
            cmbCountryy.setMaxWidth(Double.MAX_VALUE);
            cmbProgmChoice.setMaxWidth(Double.MAX_VALUE);

            ToggleGroup toggleGroupChild = new ToggleGroup();
            RadioButton childYes = new RadioButton();
            childYes.setText("Yes");
            childYes.setToggleGroup(toggleGroupChild);
            RadioButton childNo = new RadioButton();
            childNo.setText("No");
            childNo.setToggleGroup(toggleGroupChild);

            HBox hBoxChildRadio = new HBox(5);
            hBoxChildRadio.setAlignment(Pos.CENTER_LEFT);
            hBoxChildRadio.getChildren().addAll(childYes, childNo);

            ToggleGroup toggleGroupSpouse = new ToggleGroup();
            RadioButton spouseYes = new RadioButton();
            spouseYes.setText("Yes");

            spouseYes.setToggleGroup(toggleGroupSpouse);
            RadioButton spouseNo = new RadioButton();
            spouseNo.setText("No");
            spouseNo.setToggleGroup(toggleGroupSpouse);

            if (studySuggestedRequiredBEAN.getChildren_accompany() != null) {
                if (studySuggestedRequiredBEAN.getChildren_accompany().contains("Yes")) {
                    System.out.println("yes");
                    childYes.setSelected(true);
                } else if (studySuggestedRequiredBEAN.getChildren_accompany().contains("No")) {
                    childNo.setSelected(true);

                }
            }

            if (studySuggestedRequiredBEAN.getSpouse_accompany() != null) {
                if (studySuggestedRequiredBEAN.getSpouse_accompany().contains("Yes")) {
                    System.out.println("yes");
                    spouseYes.setSelected(true);
                } else if (studySuggestedRequiredBEAN.getSpouse_accompany().contains("No")) {
                    spouseNo.setSelected(true);

                }
            }
            toggleGroupSpouse.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                    if (newValue.toString().contains("Yes")) {
                        studySuggestedRequiredBEAN.setSpouse_accompany("Yes");
                    } else if (newValue.toString().contains("No")) {
                        studySuggestedRequiredBEAN.setSpouse_accompany("No");
                    }

                }
            });
            toggleGroupChild.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                    if (newValue.toString().contains("Yes")) {
                        studySuggestedRequiredBEAN.setChildren_accompany("Yes");
                    } else if (newValue.toString().contains("No")) {
                        studySuggestedRequiredBEAN.setChildren_accompany("No");
                    }

                }
            });

            HBox hBoxSpouseRadio = new HBox(5);
            hBoxSpouseRadio.setAlignment(Pos.CENTER_LEFT);
            hBoxSpouseRadio.getChildren().addAll(spouseYes, spouseNo);
            hBoxSpouseRadio.setMinWidth(100);
            hBoxChildRadio.setMinWidth(146);
            TextField txtStudyComents = new TextField();
            txtStudyComents.setMinWidth(134);
            txtStudyComents.setPromptText("Remarks");

            Button btnPlus = new Button();
            btnPlus.setText("");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
//            btnPlus.setPrefWidth(30);
            Button btnStudyClose = new Button();
            btnStudyClose.setText("");
            btnStudyClose.setPrefWidth(32);
            btnStudyClose.getStyleClass().add("close-button");

            HBox hBoxButtons = new HBox(5);
            hBoxButtons.getChildren().addAll(btnPlus, btnStudyClose);
            hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
            cmbPrgmLevel.valueProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                        ObservableList programFieldlList2 = FXCollections.observableArrayList();
                        for (String s : programField) {
                            programFieldlList2.add(s);
                        }
                        cmbPrgmField.setItems(programFieldlList2);
                        cmbPrgmField.getSelectionModel().selectFirst();
                    }
                }
            });
            cmbCountryy.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbStudyLocations.setItems(location);
                    cmbStudyLocations.getSelectionModel().selectFirst();

                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("study validate :: " + validateMasterStudyRequiredDetails(listStudySuggestedRequired));
                    if (validateMasterStudyRequiredDetails(listStudySuggestedRequired) <= 0) {
                        if (studySuggestedRequiredBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateStudyDetails(studySuggestedRequiredBEAN);

                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertStudyDetails(studySuggestedRequiredBEAN);
                        }
                        StudySuggestedRequiredBEAN emptyRequiredBEAN = new StudySuggestedRequiredBEAN();
                        listStudySuggestedRequired.add(emptyRequiredBEAN);
                        addStudyRequiredRows();
                        for (int i = 1; i <= listStudySuggestedRequired.size() + 1; i++) {
                            if (!choiceObsList.contains("" + i)) {
                                choiceObsList.add("" + i);
                            }
                        }
                        gridStudy.requestFocus();

                    } else {
                        /* ====================== validation messages ====================== */
                        //   showPopUpMessages("Please fill all the fields required in emquiry assessment study required section ..", cmbAge);
                        showAlerts.showError("All the fields required in assessment study required section !", gridStudy);
                    }

                }
            });

            btnStudyClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (studySuggestedRequiredBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteStudyDetails(studySuggestedRequiredBEAN.getRowId());

                        }
                        listStudySuggestedRequired.remove(studySuggestedRequiredBEAN);
                        if (listStudySuggestedRequired.size() <= 0) {
                            StudySuggestedRequiredBEAN emptyRequiredBEAN = new StudySuggestedRequiredBEAN();
                            listStudySuggestedRequired.add(emptyRequiredBEAN);
                        }
                        addStudyRequiredRows();
                        gridStudy.requestFocus();
//                        if (rowStudySuggestedRequiredList.size() > 1) {
//                            tabPaneProgramRequired.setPrefHeight(tabPaneProgramRequired.getHeight() - 20);
//                        }

                    }
                }
            }
            );

            //bind with bean
//            Bindings.bindBidirectional(cmbStudy.valueProperty(), studySuggestedRequiredBEAN.studyProperty());
            Bindings.bindBidirectional(cmbCountryy.valueProperty(), studySuggestedRequiredBEAN.countryProperty());
            Bindings.bindBidirectional(cmbPrgmLevel.valueProperty(), studySuggestedRequiredBEAN.program_levelProperty());
            Bindings.bindBidirectional(cmbPrgmField.valueProperty(), studySuggestedRequiredBEAN.program_fieldProperty());
//            Bindings.bindBidirectional(cmbIntake.valueProperty(), studySuggestedRequiredBEAN.intakeProperty());
//            Bindings.bindBidirectional(cmbUniversity.valueProperty(), studySuggestedRequiredBEAN.institutionProperty());
            Bindings.bindBidirectional(cmbStudyLocations.valueProperty(), studySuggestedRequiredBEAN.locationProperty());
//            Bindings.bindBidirectional(cmbStudyCurency.valueProperty(), studySuggestedRequiredBEAN.currencyProperty());
//            Bindings.bindBidirectional(cmbStudyAmnt.valueProperty(), studySuggestedRequiredBEAN.amountProperty());
//            Bindings.bindBidirectional(cmbSemFee.valueProperty(), studySuggestedRequiredBEAN.sem_feesProperty());
            Bindings.bindBidirectional(cmbProgmChoice.valueProperty(), studySuggestedRequiredBEAN.choiceProperty());
//            Bindings.bindBidirectional(txtStudyComents.textProperty(), studySuggestedRequiredBEAN.remarksProperty());

            HBox hBoxApeProgramFee = new HBox();

            hBoxApeProgramFee.getChildren()
                    .addAll(cmbStudyCurency, cmbStudyAmnt, cmbSemFee);
            hBoxApeProgramFee.setStyle(
                    "-fx-padding:2");
            if (listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) == 0) {
                gridStudy.add(new Label("Qualification Level"), 0, 0);
                gridStudy.add(new Label("Qualification Field"), 1, 0);
                gridStudy.add(new Label("Country"), 2, 0);
                gridStudy.add(new Label("Location"), 3, 0);
                gridStudy.add(new Label("Choice"), 4, 0);

            }
            gridStudy.add(cmbPrgmLevel, 0, listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbPrgmField, 1, listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbCountryy, 2, listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbStudyLocations, 3, listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) + 1);

            gridStudy.add(cmbProgmChoice, 4, listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) + 1);

            gridStudy.add(hBoxButtons, 5, listStudySuggestedRequired.indexOf(studySuggestedRequiredBEAN) + 1);

        }
    }

    /**
     *
     * @param validateStudyPrgmSugestedList
     * @return
     */
    public int validateMasterStudyRequiredDetails(List<StudySuggestedRequiredBEAN> validateStudyPrgmSugestedList) {
        int count = 0;
        for (StudySuggestedRequiredBEAN studySuggestedRequiredBEAN : validateStudyPrgmSugestedList) {
            if (validateStudyRequiredDetails(studySuggestedRequiredBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }

        return count;
    }

    /**
     *
     * @param validateRequiredBEAN
     * @return
     */
    public String validateStudyRequiredDetails(StudySuggestedRequiredBEAN validateRequiredBEAN) {
//        lblErrorStudng.setText("");
        if (validateRequiredBEAN.getChoice() == null || validateRequiredBEAN.getCountry() == null || validateRequiredBEAN.getLocation() == null || validateRequiredBEAN.getProgram_field() == null || validateRequiredBEAN.getProgram_level() == null) {
            return "All Fields Required!";
        }
        return "true";
    }
}

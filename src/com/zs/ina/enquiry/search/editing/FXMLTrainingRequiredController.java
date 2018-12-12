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


import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.model.AssesmentTrainingBEAN;
import com.zs.ina.common.ChangeDateFormat;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class FXMLTrainingRequiredController implements Initializable {

    @FXML
    private Button btnSave;
    CounselorDetailsBEAN counselorDetailsBEAN;
    ObservableList training = FXCollections.observableArrayList();
    InlineEditingDAO editingDAO = new InlineEditingIMPL();
    List<AssesmentTrainingBEAN> listTrainingRequired = new ArrayList<>();
    String ENQUIRY_ID = null;
    @FXML
    private GridPane gridTraining;
    ShowPopupMessages showAlerts = new ShowPopupMessages();
    ObservableList choiceObsList = FXCollections.observableArrayList();
    ObservableList baches = FXCollections.observableArrayList();
    ObservableList timing = FXCollections.observableArrayList();

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
        training = RetrieveStaticMasterDAO.getTraings();
        baches = RetrieveStaticMasterDAO.getBatches();
        List<String> timings = RetrieveStaticMasterDAO.getTimings();
        for (String s : timings) {
            timing.add(s);
        }
        /* ====================== Load trainig data ====================== */
        listTrainingRequired = ProgramSuggestedRequiredDAO.getTrainingDetails(ENQUIRY_ID);
        choiceObsList.clear();
        if (listTrainingRequired.size() > 0) {
            for (int i = 1; i <= listTrainingRequired.size() + 1; i++) {
                choiceObsList.add("" + i);
            }
            addTrainingGridRows();
        } else {
            choiceObsList.add("1");
            choiceObsList.add("2");
            AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
            listTrainingRequired.add(assesmentTrainingBEAN);
            addTrainingGridRows();
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateMasterTrainingData(listTrainingRequired) <= 0) {
                    for (AssesmentTrainingBEAN trainingBEAN : listTrainingRequired) {
                        trainingBEAN.setEnquiryId(ENQUIRY_ID);
                        if (trainingBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.updateTrainingDetails(trainingBEAN);
                        } else {
                            ProgramSuggestedRequiredDAO.insertTrainingDetails(trainingBEAN);
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
    public void addTrainingGridRows() {
        gridTraining.getChildren().clear();
        for (AssesmentTrainingBEAN assesmentTrainingBEAN : listTrainingRequired) {
            assesmentTrainingBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox trainingCmb = new ComboBox(training);
            trainingCmb.setMinWidth(100);
            trainingCmb.setPromptText("Select");
            ComboBox batchCmb = new ComboBox(baches);
            batchCmb.setMinWidth(120);
            batchCmb.setPromptText("Select");
            ComboBox timingCmb = new ComboBox(timing);
            timingCmb.setMinWidth(120);
            timingCmb.setPromptText("Select");

//            DatePicker fromDatePicker = new DatePicker();
            DatePicker fromDatePicker = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(fromDatePicker);
            fromDatePicker.setPromptText("Proposed Joining");
            ComboBox cmbTrainingChoice = new ComboBox(choiceObsList);
            cmbTrainingChoice.setMinWidth(80);
            cmbTrainingChoice.setPromptText("Select");
            GridPane.setHgrow(trainingCmb, Priority.ALWAYS);
            trainingCmb.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(fromDatePicker, Priority.ALWAYS);
            fromDatePicker.setPrefWidth(200);
            fromDatePicker.setMaxWidth(Double.MAX_VALUE);

//            ChangeDateFormat.datePickerDateFormatter(fromDatePicker);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//            if (assesmentTrainingBEAN.getFromDate() != null) {
//                LocalDate date = LocalDate.parse(assesmentTrainingBEAN.getFromDate(), formatter);
//                fromDatePicker.setValue(date);
//            }
//            fromDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
//
//                @Override
//                public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
//                    assesmentTrainingBEAN.setFromDate(newValue.toString());
//                }
//            });
//            txtTrainingRemarks.setText(assesmentTrainingBEAN.getRemmarks());
            Button btnPlus = new Button();
            btnPlus.setPrefWidth(32);
            btnPlus.setText(" ");
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setPrefWidth(32);
            btnClose.setText(" ");
            btnClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterTrainingData(listTrainingRequired) <= 0) {
                        if (assesmentTrainingBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.updateTrainingDetails(assesmentTrainingBEAN);
                            /* ====================== Update Training Required ====================== */
                        } else {
                            /* ====================== Insert Training Required ====================== */
                            ProgramSuggestedRequiredDAO.insertTrainingDetails(assesmentTrainingBEAN);

                        }
                        AssesmentTrainingBEAN emptyRequiredBEAN = new AssesmentTrainingBEAN();
                        listTrainingRequired.add(emptyRequiredBEAN);
                        addTrainingGridRows();
                        for (int i = 1; i <= listTrainingRequired.size() + 1; i++) {
                            if (!choiceObsList.contains("" + i)) {
                                choiceObsList.add("" + i);
                            }
                        }
                        gridTraining.requestFocus();

                    } else {
                        /* ====================== validation messages ====================== */
                        showAlerts.showError("All the fields required in assessment training required section !", btnSave);
                    }
//                    if (assesmentTrainingBEAN.getRowId() != null) {
//                        if (validateTrainingDetails(assesmentTrainingBEAN).equalsIgnoreCase("true")) {
//
//                            if (validateMasterTrainingData(masterTrainingiList) <= 0) {
//                                AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
//                                List<AssesmentTrainingBEAN> emptyList = new ArrayList<>();
//                                emptyList.add(assesmentTrainingBEAN);
//                                addTrainingGridRows(emptyList);
//                            }
//                        } else {
//                            lblErrorTraining.setText("All fields required!");
//                            lblErrorTraining.setStyle("-fx-text-fill:red");
//                            fadeOut(lblErrorTraining, 3000.0);
//                        }
//                    } else if (validateTrainingDetails(assesmentTrainingBEAN).equalsIgnoreCase("true")) {
////                        assesmentTrainingBEAN.setFromDate(fromDatePicker.getValue().toString());
//                        assesmentTrainingBEAN.setToDate(toDatePicker.getValue().toString());
//                        assesmentTrainingBEAN.setRemmarks(txtTrainingRemarks.getText());
//                        masterTrainingiList.add(assesmentTrainingBEAN);
//                        EnquiryAssessmentPersonDAO.insertTrainingDetails(assesmentTrainingBEAN);
//                        AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
//                        List<AssesmentTrainingBEAN> emptyList = new ArrayList<>();
//                        emptyList.add(assesmentTrainingBEAN);
//                        addTrainingGridRows(emptyList);
//                    } else {
//                        lblErrorTraining.setText("All fields required!");
//                        lblErrorTraining.setStyle("-fx-text-fill:red");
//                        fadeOut(lblErrorTraining, 3000.0);
//
//                    }
                }
            }
            );

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentTrainingBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteTrainingDetails(assesmentTrainingBEAN.getRowId());

                        }
                        listTrainingRequired.remove(assesmentTrainingBEAN);
                        if (listTrainingRequired.size() <= 0) {
                            AssesmentTrainingBEAN emptyRequiredBEAN = new AssesmentTrainingBEAN();
                            listTrainingRequired.add(emptyRequiredBEAN);
                        }
                        addTrainingGridRows();
                        gridTraining.requestFocus();

//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
//                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
//                    stage.initOwner(workCmb.getScene().getWindow());
//                    alert.showAndWait();
//                    if (alert.getResult() == ButtonType.YES) {
//                        if (assesmentTrainingBEAN.getRowId() != null) {
//                            EnquiryAssessmentPersonDAO.deleteTrainingDetails(assesmentTrainingBEAN.getRowId());
//                            gridTraining.getChildren().clear();
//                            masterTrainingiList.clear();
//                            List<AssesmentTrainingBEAN> trainingBEANList = EnquiryAssessmentPersonDAO.getTrainingDetails(ENQUIRY_ID);
//                            if (trainingBEANList.size() > 0) {
//                                addTrainingGridRows(trainingBEANList);
//                            } else {
//                                AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
//                                List<AssesmentTrainingBEAN> emptyList = new ArrayList<>();
//                                emptyList.add(assesmentTrainingBEAN);
//                                addTrainingGridRows(emptyList);
//                            }
//
//                        } else {
//                            gridTraining.getChildren().clear();
//                            masterTrainingiList.clear();
//
//                            List<AssesmentTrainingBEAN> trainingBEANList = EnquiryAssessmentPersonDAO.getTrainingDetails(ENQUIRY_ID);
//                            if (trainingBEANList.size() > 0) {
//                                addTrainingGridRows(trainingBEANList);
//                            } else {
//                                AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
//                                List<AssesmentTrainingBEAN> emptyList = new ArrayList<>();
//                                emptyList.add(assesmentTrainingBEAN);
//                                addTrainingGridRows(emptyList);
//                            }
//                        }
//                        gridTraining.requestFocus();
//                    }
                    }

                }
            }
            );

            Bindings.bindBidirectional(trainingCmb.valueProperty(), assesmentTrainingBEAN.trainingProperty());
            Bindings.bindBidirectional(batchCmb.valueProperty(), assesmentTrainingBEAN.batchProperty());
            Bindings.bindBidirectional(timingCmb.valueProperty(), assesmentTrainingBEAN.timingProperty());
//            Bindings.bindBidirectional(cmbTrainingDuration.valueProperty(), assesmentTrainingBEAN.durationProperty());
            Bindings.bindBidirectional(cmbTrainingChoice.valueProperty(), assesmentTrainingBEAN.choiceProperty());
//            Bindings.bindBidirectional(txtTrainingRemarks.textProperty(), assesmentTrainingBEAN.remmarksProperty());
              Bindings.bindBidirectional(assesmentTrainingBEAN.fromDateProperty(), fromDatePicker.valueProperty());
              
//            masterTrainingiList.add(assesmentTrainingBEAN);
//            HBox dateHBox = new HBox(10);
//
//            dateHBox.setMinWidth(
//                    313);
//            dateHBox.setAlignment(Pos.CENTER_LEFT);
//            dateHBox.getChildren()
//                    .setAll(fromDatePicker, toDatePicker);

            HBox btnHBox = new HBox(5);

            btnHBox.getChildren()
                    .setAll(btnPlus, btnClose);
            if (listTrainingRequired.indexOf(assesmentTrainingBEAN) == 0) {
                gridTraining.add(new Label("Course"), 0, 0);
                gridTraining.add(new Label("Batch"), 1, 0);
                gridTraining.add(new Label("Timing"), 2, 0);
                gridTraining.add(new Label("Proposed Joining Date"), 3, 0);
                gridTraining.add(new Label("Choice"), 4, 0);

            }

            gridTraining.add(trainingCmb,
                    0, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            gridTraining.add(batchCmb,
                    1, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            gridTraining.add(timingCmb,
                    2, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            gridTraining.add(fromDatePicker,
                    3, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);

            gridTraining.add(cmbTrainingChoice,
                    4, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);

            gridTraining.add(btnHBox,
                    5, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);

        }

    }

    /**
     *
     * @param validateTrainingBEANs
     * @return
     */
    public int validateMasterTrainingData(List<AssesmentTrainingBEAN> validateTrainingBEANs) {
        int count = 0;
        for (AssesmentTrainingBEAN validateBEAN : validateTrainingBEANs) {
            if (validateTrainingDetails(validateBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;
    }

    /**
     *
     * @param assesmentTrainingBEAN
     * @return
     */
    public String validateTrainingDetails(AssesmentTrainingBEAN assesmentTrainingBEAN) {
        String s = "true";
        if (assesmentTrainingBEAN.getTraining() == null || assesmentTrainingBEAN.getBatch() == null || assesmentTrainingBEAN.getTiming() == null
                || assesmentTrainingBEAN.getChoice() == null || assesmentTrainingBEAN.getFromDate() == null) {
            s = "All fields required !";
        }
        return s;
    }

}

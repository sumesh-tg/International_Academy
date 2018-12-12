/*
 * Copyright (C) 2016 100018
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
package com.zs.ina.enquiry.search.editing;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.dao.LanguageTestCrudDAO;
import com.zs.ina.assesment.model.AssesmentLanguageTestBEAN;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
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
 * @author 100018
 */
public class FXMLLanguageTestController implements Initializable {

    @FXML
    private ComboBox<?> cmbScore;
    @FXML
    private ComboBox<?> cmbTest;
    @FXML
    private Button btnSave;
    @FXML
    private GridPane gridLangTest;
    String ENQUIRY_ID = null;
    CounselorDetailsBEAN counselorDetailsBEAN;
    ObservableList testScore = FXCollections.observableArrayList();
    ObservableList otherTest = FXCollections.observableArrayList();
    List<AssesmentLanguageTestBEAN> listLanguageTestBEANs = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        /* ====================== Load Master Data ====================== */
        List<String> testScores = RetrieveStaticMasterDAO.getTestScore();
        for (String s : testScores) {
            testScore.add(s);
        }
        otherTest = RetrieveStaticMasterDAO.getOtherTest();

        /* ====================== Initialize Language Test ====================== */
        listLanguageTestBEANs = LanguageTestCrudDAO.getTeritaryLanuageTestData(ENQUIRY_ID);
        if (listLanguageTestBEANs.size() > 0) {
            addTertiaryLanguageTestGridRows();
        } else {
            AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
            listLanguageTestBEANs.add(emptyBEAN);
            addTertiaryLanguageTestGridRows();
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : listLanguageTestBEANs) {
                    if (assesmentLanguageTestBEAN.getRowId() != null) {
                        LanguageTestCrudDAO.updateLanguageTestDetails(ENQUIRY_ID, assesmentLanguageTestBEAN);
                        counselorDetailsBEAN.setLanguageTest(assesmentLanguageTestBEAN.getLanguageTest() + " " + assesmentLanguageTestBEAN.getOverall());
                        System.out.println("test :: " + counselorDetailsBEAN.getLanguageTest());
                    } else if (validateSpouseAndTerteryLangTest(assesmentLanguageTestBEAN)) {
                        LanguageTestCrudDAO.insertLanguageTestDetails(assesmentLanguageTestBEAN);
                        counselorDetailsBEAN.setLanguageTest(assesmentLanguageTestBEAN.getLanguageTest() + " " + assesmentLanguageTestBEAN.getOverall());
                        System.out.println("test :: " + counselorDetailsBEAN.getLanguageTest());
                    }
                }
                Context.getInstance().currentProfile().getPopOver().hide();

            }
        });
    }

    /**
     *
     */
    public void addTertiaryLanguageTestGridRows() {
        gridLangTest.getChildren().clear();
        for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : listLanguageTestBEANs) {
            assesmentLanguageTestBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbTestOption = new ComboBox();
            cmbTestOption.setMinWidth(145);
            cmbTestOption.getStyleClass().add("button-service");
            cmbTestOption.setPromptText("Select");
            ComboBox cmbTestStatus = new ComboBox();
            cmbTestStatus.setMinWidth(145);
            cmbTestStatus.setPromptText("Select");
            cmbTestStatus.getStyleClass().add("button-service");
            ComboBox cmbSpouseReading = new ComboBox();
            cmbSpouseReading.setMinWidth(145);
            cmbSpouseReading.setPromptText("Reading");
            cmbSpouseReading.getStyleClass().add("button-service");
            ComboBox cmbSpouseWriting = new ComboBox();
            cmbSpouseWriting.setMinWidth(145);
            cmbSpouseWriting.getStyleClass().add("button-service");
            cmbSpouseWriting.setPromptText("Writing");
            ComboBox cmbSpouseSpeaking = new ComboBox();
            cmbSpouseSpeaking.setMinWidth(145);
            cmbSpouseSpeaking.getStyleClass().add("button-service");
            cmbSpouseSpeaking.setPromptText("Speaking");
            ComboBox cmbSpouseListening = new ComboBox();
            cmbSpouseListening.setMinWidth(145);
            cmbSpouseListening.getStyleClass().add("button-service");
            cmbSpouseListening.setPromptText("Listening");
            ComboBox cmbSpouseOverall = new ComboBox();
            cmbSpouseOverall.setMinWidth(145);
            cmbSpouseOverall.getStyleClass().add("button-service");
            cmbSpouseOverall.setPromptText("Overall");
            cmbTestOption.setMaxWidth(Double.MAX_VALUE);
            cmbTestStatus.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseReading.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseWriting.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseSpeaking.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseListening.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseOverall.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(cmbTestOption, Priority.ALWAYS);
            GridPane.setHgrow(cmbTestStatus, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseReading, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseWriting, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseSpeaking, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseListening, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseOverall, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText("");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterSpouseAndTerLangTest(listLanguageTestBEANs) <= 0) {

                        if (assesmentLanguageTestBEAN.getRowId() != null) {
                            /* ====================== Update ====================== */
                        } else {
                            /* ====================== Insert ====================== */
                        }
                        AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                        listLanguageTestBEANs.add(emptyBEAN);
                        addTertiaryLanguageTestGridRows();
                    }

                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentLanguageTestBEAN.getRowId() != null) {
                            LanguageTestCrudDAO.deleteTeritaryLanguageTestDetails(assesmentLanguageTestBEAN.getRowId());
                            listLanguageTestBEANs = LanguageTestCrudDAO.getTeritaryLanuageTestData((counselorDetailsBEAN.getEnquiryID()));
                            gridLangTest.getChildren().clear();
                            if (listLanguageTestBEANs.size() > 0) {
                                addTertiaryLanguageTestGridRows();
                            } else {
                                AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                                listLanguageTestBEANs.add(emptyBEAN);
                                addTertiaryLanguageTestGridRows();
                            }
                        } else {
                            listLanguageTestBEANs = LanguageTestCrudDAO.getTeritaryLanuageTestData((counselorDetailsBEAN.getEnquiryID()));
                            gridLangTest.getChildren().clear();
                            if (listLanguageTestBEANs.size() > 0) {
                                addTertiaryLanguageTestGridRows();
                            } else {
                                AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                                listLanguageTestBEANs.add(emptyBEAN);
                                addTertiaryLanguageTestGridRows();
                            }
                        }
                        gridLangTest.requestFocus();
                    }
                }
            });

            cmbTestOption.setItems(otherTest);
//            cmbTestStatus.setItems(testStatus);
            cmbSpouseReading.setItems(testScore);
            cmbSpouseWriting.setItems(testScore);
            cmbSpouseSpeaking.setItems(testScore);
            cmbSpouseListening.setItems(testScore);
            cmbSpouseOverall.setItems(testScore);

            Bindings.bindBidirectional(cmbTestOption.valueProperty(), assesmentLanguageTestBEAN.languageTestProperty());
            Bindings.bindBidirectional(cmbTestStatus.valueProperty(), assesmentLanguageTestBEAN.statusProperty());
            Bindings.bindBidirectional(cmbSpouseReading.valueProperty(), assesmentLanguageTestBEAN.readingProperty());
            Bindings.bindBidirectional(cmbSpouseWriting.valueProperty(), assesmentLanguageTestBEAN.writingProperty());
            Bindings.bindBidirectional(cmbSpouseSpeaking.valueProperty(), assesmentLanguageTestBEAN.speakingProperty());
            Bindings.bindBidirectional(cmbSpouseListening.valueProperty(), assesmentLanguageTestBEAN.listeningProperty());
            Bindings.bindBidirectional(cmbSpouseOverall.valueProperty(), assesmentLanguageTestBEAN.overallProperty());
            if (listLanguageTestBEANs.indexOf(assesmentLanguageTestBEAN) == 0) {
                gridLangTest.add(new Label("Test"), 0, 0);
                gridLangTest.add(new Label("Overall Score"), 1, 0);
            }
            gridLangTest.add(cmbTestOption, 0, listLanguageTestBEANs.indexOf(assesmentLanguageTestBEAN) + 1);
//            languageTestGrid.add(cmbTestStatus, 1, ROW_COUNT_LANGUAGE);
//            languageTestGrid.add(cmbSpouseReading, 2, ROW_COUNT_LANGUAGE);
//            languageTestGrid.add(cmbSpouseWriting, 3, ROW_COUNT_LANGUAGE);
//            languageTestGrid.add(cmbSpouseSpeaking, 4, ROW_COUNT_LANGUAGE);
//            languageTestGrid.add(cmbSpouseListening, 5, ROW_COUNT_LANGUAGE);
            gridLangTest.add(cmbSpouseOverall, 1, listLanguageTestBEANs.indexOf(assesmentLanguageTestBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridLangTest.add(hBox, 3, listLanguageTestBEANs.indexOf(assesmentLanguageTestBEAN) + 1);
//            languageTestGrid.add(btnClose, 8, ROW_COUNT_LANGUAGE);
        }
    }

    /**
     *
     * @param languageTestBEAN
     * @return
     */
    public boolean validateSpouseAndTerteryLangTest(AssesmentLanguageTestBEAN languageTestBEAN) {
        boolean f = true;
        if (languageTestBEAN.getLanguageTest() == null) {
            f = false;
        } //        else if (languageTestBEAN.getStatus() == null) {
        //            f = false;
        //        } else if (languageTestBEAN.getReading() == null) {
        //            f = false;
        //        } else if (languageTestBEAN.getWriting() == null) {
        //            f = false;
        //        } else if (languageTestBEAN.getSpeaking() == null) {
        //            f = false;
        //        } else if (languageTestBEAN.getListening() == null) {
        //            f = false;
        else if (languageTestBEAN.getOverall() == null) {
            f = false;
        }
        return f;
    }

    /**
     *
     * @param masterLanguageTestBEANList
     * @return
     */
    public int validateMasterSpouseAndTerLangTest(List<AssesmentLanguageTestBEAN> masterLanguageTestBEANList) {
        int count = 0;
        for (AssesmentLanguageTestBEAN languageTestBEAN : masterLanguageTestBEANList) {
            if (validateSpouseAndTerteryLangTest(languageTestBEAN)) {

            } else {
                count++;
            }
        }
        return count;

    }

}

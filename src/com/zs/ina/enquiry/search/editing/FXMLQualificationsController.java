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
import static com.zs.ina.assesment.FXMLAssesmentFormController.teritaryFieldCombos;
import static com.zs.ina.assesment.FXMLAssesmentFormController.teritaryLevelCombos;
import com.zs.ina.assesment.dao.QualificationsCrudDAO;
import com.zs.ina.assesment.model.AssesmentTertiaryBEAN;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 100018
 */
public class FXMLQualificationsController implements Initializable {

    @FXML
    private Button btnSave;
    ObservableList programLevelList = FXCollections.observableArrayList();
    CounselorDetailsBEAN counselorDetailsBEAN;
    @FXML
    private GridPane gridTertiaryQualifications;
    String ENQUIRY_ID = null;
    ObservableList university = FXCollections.observableArrayList();
    List<AssesmentTertiaryBEAN> listTertiaryBEAN = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        System.out.println("Testing pop data :: " + counselorDetailsBEAN.toString());
        List<String> programLevel = RetrieveStaticMasterDAO.getProgramLevels();
        university = RetrieveStaticMasterDAO.getUniversities();
        for (String s : programLevel) {
            programLevelList.add(s);
        }

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : listTertiaryBEAN) {
                    if (assesmentTertiaryBEAN.getRowId() != null) {
                        QualificationsCrudDAO.updateTeritaryQualifications(assesmentTertiaryBEAN);
                        counselorDetailsBEAN.setQualification(assesmentTertiaryBEAN.getTertieryLevel() + " " + assesmentTertiaryBEAN.getTertieryField());
                    } else if (validateTertiaryQualifications(assesmentTertiaryBEAN).equals("true")) {
                        QualificationsCrudDAO.insertTeritaryQualifications(assesmentTertiaryBEAN);
                        counselorDetailsBEAN.setQualification(assesmentTertiaryBEAN.getTertieryLevel() + " " + assesmentTertiaryBEAN.getTertieryField());
                    }
                }
                Context.getInstance().currentProfile().getPopOver().hide();
            }
        });
        initQualification();
    }

    /**
     *
     */
    public void initQualification() {
        listTertiaryBEAN = QualificationsCrudDAO.retrieveTeritaryQualifications((counselorDetailsBEAN.getEnquiryID()));
        if (listTertiaryBEAN.size() > 0) {
            addTertiaryGridRows();
        } else {
            AssesmentTertiaryBEAN tertiaryBEAN = new AssesmentTertiaryBEAN();
            listTertiaryBEAN.add(tertiaryBEAN);
            addTertiaryGridRows();
        }
    }

    /**
     *
     */
    public void addTertiaryGridRows() {
        gridTertiaryQualifications.getChildren().clear();
        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : listTertiaryBEAN) {
            assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox teritaryQualiFieldCmb = new ComboBox();
            teritaryQualiFieldCmb.setPromptText("Select");
            teritaryQualiFieldCmb.getStyleClass().add("button-service");
            teritaryQualiFieldCmb.setMinWidth(162);
            ComboBox teritaryQualiLevelCmb = new ComboBox();
            teritaryQualiLevelCmb.setPromptText("Select");
//            teritaryQualiLevelCmb.setMinWidth(203);
            teritaryQualiLevelCmb.getStyleClass().add("button-service");
            ComboBox cmbUniversity = new ComboBox(university);
//            cmbUniversity.setMinWidth(159);
            cmbUniversity.setPromptText("Select");
            cmbUniversity.getStyleClass().add("button-service");
            teritaryQualiFieldCmb.setMaxWidth(Double.MAX_VALUE);
            teritaryQualiLevelCmb.setMaxWidth(Double.MAX_VALUE);
            cmbUniversity.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(teritaryQualiFieldCmb, Priority.ALWAYS);
            GridPane.setHgrow(teritaryQualiLevelCmb, Priority.ALWAYS);
            GridPane.setHgrow(cmbUniversity, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
//            btnPlus.getStylesheets().add(this.getClass().getResource("fxmlassesmentform.css").toExternalForm());
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    if (validateMasterTertiaryQualifications(listTertiaryBEAN) <= 0) {
                        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : listTertiaryBEAN) {
                            if (assesmentTertiaryBEAN.getRowId() != null) {
                                QualificationsCrudDAO.updateTeritaryQualifications(assesmentTertiaryBEAN);
                                counselorDetailsBEAN.setQualification(assesmentTertiaryBEAN.getTertieryLevel() + " " + assesmentTertiaryBEAN.getTertieryField());
                            } else if (validateTertiaryQualifications(assesmentTertiaryBEAN).equals("true")) {
                                QualificationsCrudDAO.insertTeritaryQualifications(assesmentTertiaryBEAN);
                                counselorDetailsBEAN.setQualification(assesmentTertiaryBEAN.getTertieryLevel() + " " + assesmentTertiaryBEAN.getTertieryField());
                            }
                        }
                        AssesmentTertiaryBEAN tertiaryBEAN = new AssesmentTertiaryBEAN();
                        listTertiaryBEAN.add(tertiaryBEAN);
                        addTertiaryGridRows();
                    }else{
                        System.out.println("All Fields Required !!!! ");
                    }
                    gridTertiaryQualifications.requestFocus();
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
                        if (assesmentTertiaryBEAN.getRowId() != null) {
                            QualificationsCrudDAO.deleteTeritaryQualifications(assesmentTertiaryBEAN.getRowId());
                            listTertiaryBEAN = QualificationsCrudDAO.retrieveTeritaryQualifications((counselorDetailsBEAN.getEnquiryID()));
                            gridTertiaryQualifications.getChildren().clear();
                            teritaryFieldCombos.clear();
                            teritaryLevelCombos.clear();
                            if (listTertiaryBEAN.size() > 0) {
                                addTertiaryGridRows();
                            } else {
                                AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
                                listTertiaryBEAN.add(emptyBEAN);
                                addTertiaryGridRows();
                            }
                        } else {
                            listTertiaryBEAN = QualificationsCrudDAO.retrieveTeritaryQualifications((counselorDetailsBEAN.getEnquiryID()));
                            gridTertiaryQualifications.getChildren().clear();
                            teritaryFieldCombos.clear();
                            teritaryLevelCombos.clear();
                            if (listTertiaryBEAN.size() > 0) {
                                addTertiaryGridRows();
                            } else {
                                AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
                                listTertiaryBEAN.add(emptyBEAN);
                                addTertiaryGridRows();
                            }
                        }
                        gridTertiaryQualifications.requestFocus();
                    }
                }

            });

            teritaryQualiLevelCmb.setItems(programLevelList);
            teritaryQualiLevelCmb.valueProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                        ObservableList programFieldlList2 = FXCollections.observableArrayList();
                        for (String s : programField) {
                            programFieldlList2.add(s);
                        }
                        teritaryQualiFieldCmb.setItems(programFieldlList2);
                        teritaryQualiFieldCmb.getSelectionModel().selectFirst();
                    }
                }
            });

            //  System.out.println("test other" + qualificationBEAN.toString());
            teritaryFieldCombos.add(teritaryQualiFieldCmb);
            teritaryLevelCombos.add(teritaryQualiLevelCmb);


            /* ====================== Add Titles ====================== */
            if (listTertiaryBEAN.indexOf(assesmentTertiaryBEAN) == 0) {
                gridTertiaryQualifications.add(new Label("Level"), 0, 0);
                gridTertiaryQualifications.add(new Label("Field"), 1, 0);
//                gridTertiaryQualifications.add(new Label("University"), 2, 0);
            }
            Bindings.bindBidirectional(teritaryLevelCombos.get(listTertiaryBEAN.indexOf(assesmentTertiaryBEAN)).valueProperty(), assesmentTertiaryBEAN.tertieryLevelProperty());
            Bindings.bindBidirectional(teritaryFieldCombos.get(listTertiaryBEAN.indexOf(assesmentTertiaryBEAN)).valueProperty(), assesmentTertiaryBEAN.tertieryFieldProperty());
            Bindings.bindBidirectional(cmbUniversity.valueProperty(), assesmentTertiaryBEAN.universityProperty());

            gridTertiaryQualifications.add(teritaryLevelCombos.get(listTertiaryBEAN.indexOf(assesmentTertiaryBEAN)), 0, listTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
            gridTertiaryQualifications.add(teritaryFieldCombos.get(listTertiaryBEAN.indexOf(assesmentTertiaryBEAN)), 1, listTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
//            gridTertiaryQualifications.add(cmbUniversity, 2, ROW_COUNT_TERITARY_QUAL + 1);
            gridTertiaryQualifications.add(btnPlus, 2, listTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
//            gridTertiaryQualifications.add(btnClose, 3, listTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
            // JOptionPane.showMessageDialog(null, masterOtherQualifyList.size());
        }

    }

    /**
     *
     * @param validateTertiaryBEAN
     * @return
     */
    public String validateTertiaryQualifications(AssesmentTertiaryBEAN validateTertiaryBEAN) {
        String s = "true";
        if (validateTertiaryBEAN.getTertieryField() == null || validateTertiaryBEAN.getTertieryLevel() == null) {
            s = "All fields required in Tertiary Qualifications!";
        }
        return s;
    }

    /**
     *
     * @param masterTertiaryBEANList
     * @return
     */
    public int validateMasterTertiaryQualifications(List<AssesmentTertiaryBEAN> masterTertiaryBEANList) {
        int count = 0;
        for (AssesmentTertiaryBEAN validateTertiaryBEAN : masterTertiaryBEANList) {
            if (validateTertiaryQualifications(validateTertiaryBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }

        }
        return count;

    }
}

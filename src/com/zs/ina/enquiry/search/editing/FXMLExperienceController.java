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
import com.zs.ina.assesment.dao.WorkExperienceDAO;
import com.zs.ina.assesment.model.WorktExperienceBEAN;
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
public class FXMLExperienceController implements Initializable {

    @FXML
    private GridPane gridWorkExperience;
    @FXML
    private Button btnSave;
    String ENQUIRY_ID = null;
    CounselorDetailsBEAN counselorDetailsBEAN;
    ObservableList profession = FXCollections.observableArrayList("Software Eng");
    ObservableList duration = FXCollections.observableArrayList();
    List<WorktExperienceBEAN> workExpBEANList = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        profession = RetrieveStaticMasterDAO.getProfession();
        duration = RetrieveStaticMasterDAO.getDuration();
        /* ====================== Initialize Work Experience ====================== */
        workExpBEANList = WorkExperienceDAO.getWorkExperience(ENQUIRY_ID);
        if (workExpBEANList.size() > 0) {
            addWorkExperienceGridRows();
        } else {
            WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
            workExpBEANList.add(emptyExperienceBEAN);
            addWorkExperienceGridRows();
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (WorktExperienceBEAN worktExperienceBEAN : workExpBEANList) {

                    if (worktExperienceBEAN.getRowId() != null) {
                        /* ====================== Update ====================== */
                        if (validateWorkExperience(worktExperienceBEAN).equals("true")) {
                            WorkExperienceDAO.updateWorkExperience(worktExperienceBEAN);
                            // counselorDetailsBEAN.setExperience(worktExperienceBEAN.getProfession() + " " + worktExperienceBEAN.getDuration());
                        }
                    } else /* ====================== Insert ====================== */ if (validateWorkExperience(worktExperienceBEAN).equals("true")) {
                        WorkExperienceDAO.insertWorkExperience(ENQUIRY_ID, worktExperienceBEAN);
                        //  counselorDetailsBEAN.setExperience(worktExperienceBEAN.getProfession() + " " + worktExperienceBEAN.getDuration());
                    }
                }
                Context.getInstance().currentProfile().getPopOver().hide();
            }
        }
        );
    }

    /**
     *
     */
    public void addWorkExperienceGridRows() {
        gridWorkExperience.getChildren().clear();
        for (WorktExperienceBEAN workExperienceBEAN : workExpBEANList) {
            workExperienceBEAN.setEnquiryId(ENQUIRY_ID);
//            lblErrorWrkExp.setText("");
            ComboBox cmbProfession = new ComboBox(profession);
            cmbProfession.setMinWidth(203);
            cmbProfession.getStyleClass().add("button-service");
            cmbProfession.setPromptText("Select");

            ComboBox cmbDuration = new ComboBox(duration);
            cmbDuration.setMinWidth(162);
            cmbDuration.setPromptText("Select");
            cmbDuration.getStyleClass().add("button-service");

            cmbProfession.setMaxWidth(Double.MAX_VALUE);
            cmbDuration.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbProfession, Priority.ALWAYS);
            GridPane.setHgrow(cmbDuration, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    if (validateMasterWorkexperience(workExpBEANList) <= 0) {

                        if (workExperienceBEAN.getRowId() != null) {
                            /* ====================== Update ====================== */
                            WorkExperienceDAO.updateWorkExperience(workExperienceBEAN);

                        } else {
                            /* ====================== Insert ====================== */
                            WorkExperienceDAO.insertWorkExperience(ENQUIRY_ID, workExperienceBEAN);
                        }
                        WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
                        workExpBEANList.add(emptyExperienceBEAN);
                        addWorkExperienceGridRows();
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
                        if (workExperienceBEAN.getRowId() != null) {
                            WorkExperienceDAO.deleteWrkExpDetailsByid(workExperienceBEAN.getRowId());
                            gridWorkExperience.getChildren().clear();
                            workExpBEANList = WorkExperienceDAO.getWorkExperience(ENQUIRY_ID);
                            if (workExpBEANList.size() > 0) {
                                addWorkExperienceGridRows();
                            } else {
                                WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
                                workExpBEANList.add(emptyExperienceBEAN);
                                addWorkExperienceGridRows();
                            }
                        } else {
                            gridWorkExperience.getChildren().clear();
                            workExpBEANList = WorkExperienceDAO.getWorkExperience(ENQUIRY_ID);
                            if (workExpBEANList.size() > 0) {
                                addWorkExperienceGridRows();
                            } else {
                                WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
                                workExpBEANList.add(emptyExperienceBEAN);
                                addWorkExperienceGridRows();
                            }
                        }
                        gridWorkExperience.requestFocus();
                    }
                }
            });

            if (workExpBEANList.indexOf(workExperienceBEAN) == 0) {
                gridWorkExperience.add(new Label("Profession"), 0, 0);
                gridWorkExperience.add(new Label("Duration"), 1, 0);
            }

            Bindings.bindBidirectional(cmbProfession.valueProperty(), workExperienceBEAN.professionProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), workExperienceBEAN.durationProperty());

            gridWorkExperience.add(cmbProfession, 0, workExpBEANList.indexOf(workExperienceBEAN) + 1);
            gridWorkExperience.add(cmbDuration, 1, workExpBEANList.indexOf(workExperienceBEAN) + 1);
            gridWorkExperience.add(btnPlus, 2, workExpBEANList.indexOf(workExperienceBEAN) + 1);
//            gridWorkExperience.add(btnClose, 3, workExpBEANList.indexOf(workExperienceBEAN) + 1);
        }

    }

    /**
     *
     * @param masterWorkExpBEANList
     * @return
     */
    public int validateMasterWorkexperience(List<WorktExperienceBEAN> masterWorkExpBEANList) {
        int count = 0;
        for (WorktExperienceBEAN validateExperienceBEAN : masterWorkExpBEANList) {
            if (validateWorkExperience(validateExperienceBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }

        }
        return count;

    }

    /**
     *
     * @param validateExperienceBEAN
     * @return
     */
    public String validateWorkExperience(WorktExperienceBEAN validateExperienceBEAN) {
        String s = "true";
        if (validateExperienceBEAN.getDuration() == null || validateExperienceBEAN.getProfession() == null) {
            s = "All fields required !";
        }
        return s;

    }
}

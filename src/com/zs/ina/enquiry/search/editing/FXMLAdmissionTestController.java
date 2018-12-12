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

import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterDAO;
import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterIMPL;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestBEAN;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestDAO;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestIMPL;
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
public class FXMLAdmissionTestController implements Initializable {

    @FXML
    private GridPane gridAdmissionTest;
    @FXML
    private Button btnSave;
    AdmissionTestDAO admissionTestDAO = new AdmissionTestIMPL();
    CounselorDetailsBEAN counselorDetailsBEAN;
    String ENQUIRY_ID = null;
    AdmissionTestMasterDAO masterAdmissionTestDAO = new AdmissionTestMasterIMPL();
    ObservableList testScore = FXCollections.observableArrayList();
    ObservableList otherTest = FXCollections.observableArrayList();
    List<AdmissionTestBEAN> listAdmissionTestBEANs = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        List<String> testScores = RetrieveStaticMasterDAO.getTestScore();
        for (String s : testScores) {
            testScore.add(s);
        }
        /* ====================== Initialize Admission test ====================== */
        listAdmissionTestBEANs = admissionTestDAO.retrieveAdmissionTest(ENQUIRY_ID);
        if (listAdmissionTestBEANs.size() > 0) {
            addAdmissionTestGridRows();
        } else {
            AdmissionTestBEAN emptyAdmissionTestBEAN = new AdmissionTestBEAN();
            listAdmissionTestBEANs.add(emptyAdmissionTestBEAN);
            addAdmissionTestGridRows();
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEANs) {
                    if (admissionTestBEAN.getRowId() != null) {
                        admissionTestDAO.updateAdmissionTest(admissionTestBEAN);
                        System.out.println("test admission test :: " + admissionTestBEAN.toString());
                        counselorDetailsBEAN.setAdmissionTest(admissionTestBEAN.getTest() + " " + admissionTestBEAN.getOverAllScore());
                    } else if (validateAdmissionTest(admissionTestBEAN).equals("true")) {
                        admissionTestDAO.insertAdmissionTest(admissionTestBEAN);
                        counselorDetailsBEAN.setAdmissionTest(admissionTestBEAN.getTest() + " " + admissionTestBEAN.getOverAllScore());
                    }
                }
                Context.getInstance().currentProfile().getPopOver().hide();
            }
        });
    }

    /**
     *
     */
    public void addAdmissionTestGridRows() {
        gridAdmissionTest.getChildren().clear();
        for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEANs) {
            admissionTestBEAN.setEnquiryId(ENQUIRY_ID);

            ComboBox cmbAdmTest = new ComboBox(masterAdmissionTestDAO.retrieveAdmissionTest());
            ComboBox cmbOverAlll = new ComboBox(testScore);
            GridPane.setHgrow(cmbAdmTest, Priority.ALWAYS);
            GridPane.setHgrow(cmbOverAlll, Priority.ALWAYS);

            cmbAdmTest.setMaxWidth(Double.MAX_VALUE);
            cmbOverAlll.setMaxWidth(Double.MAX_VALUE);

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
                    if (validateAdmissionTest(listAdmissionTestBEANs) <= 0) {
                        if (admissionTestBEAN.getRowId() != null) {
                            admissionTestDAO.updateAdmissionTest(admissionTestBEAN);
                        } else {
                            admissionTestDAO.insertAdmissionTest(admissionTestBEAN);

                        }
                        AdmissionTestBEAN emptyAdmissionTestBEAN = new AdmissionTestBEAN();
//                      List<LanguageSkillsBEAN> listEmptySkillsBEANs = new ArrayList<>();
                        listAdmissionTestBEANs.add(emptyAdmissionTestBEAN);
                        addAdmissionTestGridRows();
                    } else {
                        //   showErrors.showError("All Fields Required In Admission Test!", gridAdmissionTest);
                    }
                    gridAdmissionTest.requestFocus();

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
                        if (admissionTestBEAN.getRowId() != null) {
                            admissionTestDAO.deleteAdmissionTest(admissionTestBEAN.getRowId());
                        }
                        listAdmissionTestBEANs.remove(admissionTestBEAN);
                        if (listAdmissionTestBEANs.size() <= 0) {
                            AdmissionTestBEAN emptyBEAN = new AdmissionTestBEAN();
                            listAdmissionTestBEANs.add(emptyBEAN);
                        }
                        addAdmissionTestGridRows();
                    }
                    gridAdmissionTest.requestFocus();

                }
            });

            /* ====================== Close and Plus Button Events ====================== */
            if (listAdmissionTestBEANs.indexOf(admissionTestBEAN) == 0) {
                gridAdmissionTest.add(new Label("Admission Test"), 0, 0);
                gridAdmissionTest.add(new Label("Overall Score"), 1, 0);

            }

            Bindings.bindBidirectional(cmbAdmTest.valueProperty(), admissionTestBEAN.testProperty());
            Bindings.bindBidirectional(cmbOverAlll.valueProperty(), admissionTestBEAN.overAllScoreProperty());
            //  Bindings.bindBidirectional(checkCmbProficiency.checkModelProperty(), languageSkillsBEAN.languageProperty());

            gridAdmissionTest.add(cmbAdmTest, 0, listAdmissionTestBEANs.indexOf(admissionTestBEAN) + 1);
            gridAdmissionTest.add(cmbOverAlll, 1, listAdmissionTestBEANs.indexOf(admissionTestBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAdmissionTest.add(hBox, 2, listAdmissionTestBEANs.indexOf(admissionTestBEAN) + 1);

        }
    }

    /**
     *
     * @param validateListAdmissionTest
     * @return
     */
    public int validateAdmissionTest(List<AdmissionTestBEAN> validateListAdmissionTest) {
        int count = 0;
        for (AdmissionTestBEAN admissionTestBEAN : validateListAdmissionTest) {
            if (validateAdmissionTest(admissionTestBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param validateAdmissionTestBEAN
     * @return
     */
    public String validateAdmissionTest(AdmissionTestBEAN validateAdmissionTestBEAN) {
        if (validateAdmissionTestBEAN.getTest() == null || validateAdmissionTestBEAN.getOverAllScore() == null) {
            return "All Fields Required!";
        }
        return "true";
    }
}

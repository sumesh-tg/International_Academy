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
package com.zs.ina.assesment.preview;

import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryDAO;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryIMPl;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryPOJO;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestBEAN;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestDAO;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestIMPL;
import com.zs.ina.assesment.children.dao.ChildrenDAO;
import com.zs.ina.assesment.dao.LanguageTestCrudDAO;
import com.zs.ina.assesment.dao.PersonalCrudDAO;
import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.dao.QualificationsCrudDAO;
import com.zs.ina.assesment.dao.SpouseCrudDAO;
import com.zs.ina.assesment.dao.WorkExperienceDAO;
import com.zs.ina.assesment.langskills.dao.LanguageSkillsBEAN;
import com.zs.ina.assesment.langskills.dao.LanguageSkillsDAO;
import com.zs.ina.assesment.langskills.dao.LanguageSkillsIMPL;
import com.zs.ina.assesment.model.AssesmentLanguageTestBEAN;
import com.zs.ina.assesment.model.AssesmentMigrateBEAN;
import com.zs.ina.assesment.model.AssesmentPlusTwoBEAN;
import com.zs.ina.assesment.model.SpouseDetailsBEAN;
import com.zs.ina.assesment.model.AssesmentSpouseExpBEAN;
import com.zs.ina.assesment.model.AssesmentSsslcBEAN;
import com.zs.ina.assesment.model.AssesmentTertiaryBEAN;
import com.zs.ina.assesment.model.AssesmentTrainingBEAN;
import com.zs.ina.assesment.model.AssesmentWorkBEAN;
import com.zs.ina.assesment.model.AssessmentChildBEAN;
import com.zs.ina.assesment.model.AssessmentPersonBEAN;
import com.zs.ina.assesment.model.WorktExperienceBEAN;
import com.zs.ina.assesment.model.StudySuggestedRequiredBEAN;
import com.zs.ina.assesment.otherskills.dao.OtherSkillBEAN;
import com.zs.ina.assesment.otherskills.dao.OtherSkillsDAO;
import com.zs.ina.assesment.otherskills.dao.OtherSkillsIMPL;
import com.zs.ina.assesment.plus2.dao.Assesmentplus2DAO;
import com.zs.ina.assesment.sslc.dao.AssessmentSSLCDAO;
import com.zs.ina.assesment.technical.dao.TechnicalSkilllsIMPL;
import com.zs.ina.assesment.technical.dao.TechnicalSkillsBEAN;
import com.zs.ina.assesment.technical.dao.TechnicalSkillsDAO;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author sumesh
 */
public class FXMLAssessmentPreviewController implements Initializable {

    @FXML
    private TableView<ForwardHistoryPOJO> tblFollowUpStatus;
    @FXML
    private Button btnPrint;
    @FXML
    private AnchorPane anchorMain;
    @FXML
    private VBox vboxStudyRequired;
    @FXML
    private VBox vboxWorkRequired;
    @FXML
    private VBox vboxMigrationRequired;
    @FXML
    private VBox vboxTrainingRequired;
    @FXML
    private VBox vboxWorkExperience;
    @FXML
    private VBox vboxLanguageSkill;
    @FXML
    private GridPane gridTestAndSkills;
    @FXML
    private VBox vboxSpouseQualification;
    @FXML
    private VBox vboxSpouseWorkExperience;
    @FXML
    private VBox vboxSpouseChilRelative;
    @FXML
    private Label lblPersonalEdit;
    @FXML
    private Label lblPhoneNumber1;
    @FXML
    private Label lblDistricts;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAge;
    @FXML
    private Label lblGender;
    @FXML
    private Label lblState;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblPhoneNumber2;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblApplicationType;
    CounselorDetailsBEAN counselorDetailsBEAN;
    String ENQUIRY_ID;
    @FXML
    private GridPane gridStudyRequired;
    @FXML
    private VBox vboxSpouseDetails;
    @FXML
    private VBox vboxSpouseTitle;
    @FXML
    private VBox vboxSpouseAge;
    @FXML
    private Label lblPreviewTitle;
    @FXML
    private GridPane gridWorkRequired;
    @FXML
    private GridPane gridMigrationRequired;
    @FXML
    private GridPane gridTrainingRequired;
    @FXML
    private GridPane gridQualifications;
    @FXML
    private GridPane gridWorkExperience;
    @FXML
    private GridPane gridLanguageSkills;
    @FXML
    private TableColumn<?, ?> clmStudyRequired;
    @FXML
    private TableColumn<?, ?> clmStatus;
    @FXML
    private TableColumn<?, ?> clmRemarks;
    @FXML
    private TableColumn<?, ?> clmDate;
    @FXML
    private Label lblSpouseAge;
    @FXML
    private Label lblSpouseAgeEdit;
    @FXML
    private GridPane gridSpouseQualification;
    @FXML
    private GridPane gridSpouseWorkExperience;
    @FXML
    private GridPane gridSpouseTestChild;

    LanguageSkillsDAO languageSkillsDAO = new LanguageSkillsIMPL();
    AdmissionTestDAO admissionTestDAO = new AdmissionTestIMPL();
    ForwardHistoryDAO forwardHistoryDAO = new ForwardHistoryIMPl();
    TechnicalSkillsDAO technicalSkillsDAO = new TechnicalSkilllsIMPL();
    OtherSkillsDAO otherSkillsDAO = new OtherSkillsIMPL();
    @FXML
    private AnchorPane anchorPanePreview;
    ObservableList<Label> obsLabelEdits = FXCollections.observableArrayList();
    @FXML
    private VBox vboxBtnPrint;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = new CounselorDetailsBEAN();
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        /* ======================== Show Personal Details ==================== */
        
        showPersonalDetails();
        /* ======================== Show Study Required Details ==================== */
        showStudyRequiredDetails();
        /* ======================== Show Work Required Details ==================== */
        showWorkRequiredDetails();
        /* ======================== Show Training Required Details ==================== */
        showMigrationRequiredDetails();
        /* ======================== Show Training Required Details ==================== */
        showTrainigRequiredDetails();
        /* ====================== Show Qualification Details ====================== */
        showQualificationDetails();
        /* ====================== Show Work Experience ====================== */
        showWorkExperience();
        /* ====================== Show Language Skills ====================== */
        showLanguageSkills();
        /* ====================== Show Language Test,Admsion Test,Technical Skill,Other Skill ====================== */
        showTestAndSkills();
        /* ====================== Show Spouse,Relative and Children Details ====================== */
        showSpouseChildrenRelative();
        /* ====================== Show Assment And Follow Up Status Table ====================== */
        showFollowUpStatusTable();
        /* ====================== Show or hide features based on tab ====================== */
        showHideFeatures(Context.getInstance().currentProfile().getCurrentTab(), false);

        btnPrint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* ====================== Hide Edit Labels Temporarily ====================== */
                for (Label lblEdits : obsLabelEdits) {
                    lblEdits.setVisible(false);
                }
                vboxBtnPrint.setVisible(false);
                vboxBtnPrint.setManaged(true);
                WritableImage image = anchorPanePreview.snapshot(new SnapshotParameters(), null);
                /* ====================== Show edit labels again ====================== */
                for (Label lblEdits : obsLabelEdits) {
                    lblEdits.setVisible(true);
                }
                vboxBtnPrint.setVisible(true);
                vboxBtnPrint.setManaged(true);
                ImageView printImage = new ImageView(image);
                /* ====================== Save Image ====================== */
                File file = new File("chart.png");

                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (IOException e) {
                    // TODO: handle exception here
                }
                print(printImage);
            }
        });
    }

    /**
     *
     */
    public void showSpouseChildrenRelative() {
        gridSpouseQualification.getChildren().clear();
        SpouseDetailsBEAN spouseDetailsBEAN = SpouseCrudDAO.getSpouseDetails(ENQUIRY_ID);
        System.out.println("Spouse Details :: " + spouseDetailsBEAN.toString());
        lblSpouseAge.setText("Spouse Age : " + spouseDetailsBEAN.getSpouseAge());
        List<AssesmentTertiaryBEAN> assesmentTertiaryBEANs = SpouseCrudDAO.getSpouseQualDetails((ENQUIRY_ID));
        Label lblQualification = new Label("Qualification Level");
        Label lblBoardUniv = new Label("Qualification Field");
        Label lblMediumInstruction = new Label("Medium of Instruction");
        lblQualification.getStyleClass().add("pr_title");
        lblBoardUniv.getStyleClass().add("pr_title");
        lblMediumInstruction.getStyleClass().add("pr_title");
        gridSpouseQualification.add(lblQualification, 0, 0);
        gridSpouseQualification.add(lblBoardUniv, 1, 0);
//        gridSpouseQualification.add(lblMediumInstruction, 2, 0);

//        gridSpouseQualification.add(new Label("10th"), 0, 1);
//        gridSpouseQualification.add(new Label(spouseDetailsBEAN.getSslcBoard()), 1, 1);
//        gridSpouseQualification.add(new Label(spouseDetailsBEAN.getSslcMedium()), 2, 1);
//
//        gridSpouseQualification.add(new Label("12th"), 0, 2);
//        gridSpouseQualification.add(new Label(spouseDetailsBEAN.getPlusTwoBoard()), 1, 2);
//        gridSpouseQualification.add(new Label(spouseDetailsBEAN.getPlusTwoField()), 2, 2);
        
        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : assesmentTertiaryBEANs) {
            gridSpouseQualification.add(new Label(assesmentTertiaryBEAN.getTertieryLevel()), 0, assesmentTertiaryBEANs.indexOf(assesmentTertiaryBEAN) + 3);
            gridSpouseQualification.add(new Label(assesmentTertiaryBEAN.getTertieryField()), 1, assesmentTertiaryBEANs.indexOf(assesmentTertiaryBEAN) + 3);

        }
        /* ====================== Add Edit Button ====================== */
        Label lblEdit = new Label("Edit");
        lblEdit.getStyleClass().add("pr_title");
        lblEdit.setUnderline(true);
        lblEdit.setCursor(Cursor.HAND);
        lblEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("spouse_quali");
                Stage stage = (Stage) gridSpouseTestChild.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblEdit);
        lblSpouseAgeEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("spouse_details");
                Stage stage = (Stage) gridSpouseTestChild.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblSpouseAgeEdit);
        gridQualifications.add(lblEdit, 3, 0);

        /* ====================== Work Experience ====================== */
        List<AssesmentSpouseExpBEAN> assesmentSpouseExpBEANs = SpouseCrudDAO.getSpouseExpDetails(ENQUIRY_ID);
        gridSpouseWorkExperience.getChildren().clear();
        Label lblWorkExperience = new Label("Work Experience");
        Label lblSkillArea = new Label("Skill Area");
        Label lblDuration = new Label("Duration ");
        lblWorkExperience.getStyleClass().add("pr_title");
        lblSkillArea.getStyleClass().add("pr_title");
        lblDuration.getStyleClass().add("pr_title");
        gridSpouseWorkExperience.add(lblWorkExperience, 0, 0);
        gridSpouseWorkExperience.add(lblSkillArea, 1, 0);
        gridSpouseWorkExperience.add(lblDuration, 2, 0);
        for (AssesmentSpouseExpBEAN assesmentSpouseExpBEAN : assesmentSpouseExpBEANs) {
            gridSpouseWorkExperience.add(new Label(assesmentSpouseExpBEAN.getProfession()), 0, assesmentSpouseExpBEANs.indexOf(assesmentSpouseExpBEAN) + 1);
            gridSpouseWorkExperience.add(new Label(assesmentSpouseExpBEAN.getProfession()), 1, assesmentSpouseExpBEANs.indexOf(assesmentSpouseExpBEAN) + 1);
            gridSpouseWorkExperience.add(new Label(assesmentSpouseExpBEAN.getDuration()), 2, assesmentSpouseExpBEANs.indexOf(assesmentSpouseExpBEAN) + 1);

        }
        Label lblSpouseEdit = new Label("Edit");
        lblSpouseEdit.getStyleClass().add("pr_title");
        lblSpouseEdit.setUnderline(true);
        lblSpouseEdit.setCursor(Cursor.HAND);
        lblSpouseEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("spouse_exp");
                Stage stage = (Stage) gridSpouseTestChild.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblSpouseEdit);
        gridSpouseWorkExperience.add(lblSpouseEdit, 3, 0);
        /* ====================== Language Test ====================== */
        gridSpouseTestChild.getChildren().clear();
        List<AssesmentLanguageTestBEAN> assesmentLanguageTestBEANs = SpouseCrudDAO.getSpouseLanuageTestDetails(ENQUIRY_ID);
        for (AssesmentLanguageTestBEAN languageTestBEAN : assesmentLanguageTestBEANs) {
            Label lblLanTest = new Label("Language Test");
            lblLanTest.getStyleClass().add("pr_title");
            Label lblOverAll = new Label("Overall Score");
            lblOverAll.getStyleClass().add("pr_title");
            gridSpouseTestChild.add(lblLanTest, 0, assesmentLanguageTestBEANs.indexOf(languageTestBEAN));
            gridSpouseTestChild.add(new Label(languageTestBEAN.getLanguageTest()), 1, assesmentLanguageTestBEANs.indexOf(languageTestBEAN));
            gridSpouseTestChild.add(lblOverAll, 2, assesmentLanguageTestBEANs.indexOf(languageTestBEAN));
            gridSpouseTestChild.add(new Label(languageTestBEAN.getOverall()), 3, assesmentLanguageTestBEANs.indexOf(languageTestBEAN));

        }
        Label lblSpouLangEdit = new Label("Edit");
        lblSpouLangEdit.getStyleClass().add("pr_title");
        lblSpouLangEdit.setUnderline(true);
        lblSpouLangEdit.setCursor(Cursor.HAND);
        lblSpouLangEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("spouse_lang");
                Stage stage = (Stage) gridSpouseTestChild.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblSpouLangEdit);
        gridSpouseTestChild.add(lblSpouLangEdit, 4, 0);
        /* ====================== Child Details ====================== */
        List<AssessmentChildBEAN> childBEANList = ChildrenDAO.retriveChildrenDetails(ENQUIRY_ID);
        for (AssessmentChildBEAN assessmentChildBEAN : childBEANList) {
            Label lblChildDetails = new Label("Child Details");
            lblChildDetails.getStyleClass().add("pr_title");
            Label lblAge = new Label("Age");
            lblAge.getStyleClass().add("pr_title");
            gridSpouseTestChild.add(lblChildDetails, 0, assesmentLanguageTestBEANs.size() + childBEANList.indexOf(assessmentChildBEAN));
            gridSpouseTestChild.add(new Label(assessmentChildBEAN.getName()), 1, assesmentLanguageTestBEANs.size() + childBEANList.indexOf(assessmentChildBEAN));
            gridSpouseTestChild.add(lblAge, 2, assesmentLanguageTestBEANs.size() + childBEANList.indexOf(assessmentChildBEAN));
            gridSpouseTestChild.add(new Label(assessmentChildBEAN.getAge()), 3, assesmentLanguageTestBEANs.size() + childBEANList.indexOf(assessmentChildBEAN));

        }
        Label lblSpouChEdit = new Label("Edit");
        lblSpouChEdit.getStyleClass().add("pr_title");
        lblSpouChEdit.setUnderline(true);
        lblSpouChEdit.setCursor(Cursor.HAND);
        lblSpouChEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("spouse_child");
                Stage stage = (Stage) gridSpouseTestChild.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblSpouChEdit);
        gridSpouseTestChild.add(lblSpouChEdit, 4, assesmentLanguageTestBEANs.size());
        /* ====================== Relative Details ====================== */
    }

    /**
     *
     */
    public void showFollowUpStatusTable() {
        List<ForwardHistoryPOJO> listForwardHistoryPOJO = forwardHistoryDAO.retriveForwardHistory(ENQUIRY_ID);
        System.out.println("Test :: " + listForwardHistoryPOJO.toString());
        switch (Context.getInstance().currentProfile().getCurrentTab()) {
            case "study":
                clmStudyRequired.setCellValueFactory(
                        new PropertyValueFactory<>("study_required"));
                break;
            case "training":
                clmStudyRequired.setCellValueFactory(
                        new PropertyValueFactory<>("training_required"));
                break;
            case "work":
                clmStudyRequired.setCellValueFactory(
                        new PropertyValueFactory<>("work_required"));
                break;
            case "migration":
                clmStudyRequired.setCellValueFactory(
                        new PropertyValueFactory<>("migration_required"));
                break;
        }

        clmStatus.setCellValueFactory(
                new PropertyValueFactory<>("current_status"));
        clmRemarks.setCellValueFactory(
                new PropertyValueFactory<>("remarks"));
        clmDate.setCellValueFactory(
                new PropertyValueFactory<>("assigned_date"));
        tblFollowUpStatus.getItems().addAll(listForwardHistoryPOJO);
    }

    /**
     *
     */
    public void showTestAndSkills() {
        gridTestAndSkills.getChildren().clear();
        List<AssesmentLanguageTestBEAN> listAssesmentLanguageTestBEAN = LanguageTestCrudDAO.getLanguageTestDetails(ENQUIRY_ID);
        List<AdmissionTestBEAN> listAdmissionTestBEAN = admissionTestDAO.retrieveAdmissionTest(ENQUIRY_ID);
        /* ====================== Show Based On Tab Selection ====================== */
        switch (Context.getInstance().currentProfile().getCurrentTab()) {
            case "study":
            case "training":
                /* ====================== Language Test & Admission Test ====================== */
                for (AssesmentLanguageTestBEAN languageTestBEAN : listAssesmentLanguageTestBEAN) {
                    Label lblLanTest = new Label("Language Test");
                    lblLanTest.getStyleClass().add("pr_title");
                    Label lblOverAll = new Label("Overall Score");
                    lblOverAll.getStyleClass().add("pr_title");
                    gridTestAndSkills.add(lblLanTest, 0, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(new Label(languageTestBEAN.getLanguageTest()), 1, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(lblOverAll, 2, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(new Label(languageTestBEAN.getOverall()), 3, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                }
                Label lblEdit = new Label("Edit");
                lblEdit.getStyleClass().add("pr_title");
                lblEdit.setUnderline(true);
                lblEdit.setCursor(Cursor.HAND);
                lblEdit.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        Context.getInstance().currentProfile().setPreviewCurrentEdit("lang_test");
                        Stage stage = (Stage) gridTestAndSkills.getScene().getWindow();
                        stage.close();
                    }
                });
                /* ====================== Add Edit Label TO Global ====================== */
                obsLabelEdits.add(lblEdit);
                gridTestAndSkills.add(lblEdit, 4, 0);
                /* ====================== Admission Test ====================== */
                for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEAN) {
                    Label lblAdmTest = new Label("Admission Test");
                    lblAdmTest.getStyleClass().add("pr_title");
                    Label lblAdmOverAll = new Label("Overall Score");
                    lblAdmOverAll.getStyleClass().add("pr_title");
                    gridTestAndSkills.add(lblAdmTest, 0, listAssesmentLanguageTestBEAN.size() + listAdmissionTestBEAN.indexOf(admissionTestBEAN));
                    gridTestAndSkills.add(new Label(admissionTestBEAN.getTest()), 1, listAssesmentLanguageTestBEAN.size() + listAdmissionTestBEAN.indexOf(admissionTestBEAN));
                    gridTestAndSkills.add(lblAdmOverAll, 2, listAssesmentLanguageTestBEAN.size() + listAdmissionTestBEAN.indexOf(admissionTestBEAN));
                    gridTestAndSkills.add(new Label(admissionTestBEAN.getOverAllScore()), 3, listAssesmentLanguageTestBEAN.size() + listAdmissionTestBEAN.indexOf(admissionTestBEAN));
                }
                Label lblAdmEdit = new Label("Edit");
                lblAdmEdit.getStyleClass().add("pr_title");
                lblAdmEdit.setUnderline(true);
                lblAdmEdit.setCursor(Cursor.HAND);
                lblAdmEdit.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        Context.getInstance().currentProfile().setPreviewCurrentEdit("adm_test");
                        Stage stage = (Stage) gridTestAndSkills.getScene().getWindow();
                        stage.close();
                    }
                });
                /* ====================== Add Edit Label TO Global ====================== */
                obsLabelEdits.add(lblAdmEdit);
                gridTestAndSkills.add(lblAdmEdit, 4, listAdmissionTestBEAN.size());
                break;
            case "work":
                /* ====================== Language Test ====================== */
                for (AssesmentLanguageTestBEAN languageTestBEAN : listAssesmentLanguageTestBEAN) {
                    Label lblLanTest = new Label("Language Test");
                    lblLanTest.getStyleClass().add("pr_title");
                    Label lblOverAll = new Label("Overall Score");
                    lblOverAll.getStyleClass().add("pr_title");
                    gridTestAndSkills.add(lblLanTest, 0, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(new Label(languageTestBEAN.getLanguageTest()), 1, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(lblOverAll, 2, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(new Label(languageTestBEAN.getOverall()), 3, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                }
                Label lblLangEdit = new Label("Edit");
                lblLangEdit.getStyleClass().add("pr_title");
                lblLangEdit.setUnderline(true);
                lblLangEdit.setCursor(Cursor.HAND);
                lblLangEdit.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        Context.getInstance().currentProfile().setPreviewCurrentEdit("lang_test");
                        Stage stage = (Stage) gridTestAndSkills.getScene().getWindow();
                        stage.close();
                    }
                });
                /* ====================== Add Edit Label TO Global ====================== */
                obsLabelEdits.add(lblLangEdit);
                gridTestAndSkills.add(lblLangEdit, 4, 0);
                /* ====================== End Language Test ====================== */
                /* ====================== Technical Skills ====================== */
                List<TechnicalSkillsBEAN> listTechnicalSkillsBEAN = technicalSkillsDAO.retrieveTechnicalSkills(ENQUIRY_ID);
                for (TechnicalSkillsBEAN technicalSkillsBEAN : listTechnicalSkillsBEAN) {
                    Label lblTechSkill = new Label("Technical Skill");
                    lblTechSkill.getStyleClass().add("pr_title");
                    gridTestAndSkills.add(lblTechSkill, 0, listAssesmentLanguageTestBEAN.size() + listTechnicalSkillsBEAN.indexOf(technicalSkillsBEAN));
                    gridTestAndSkills.add(new Label(technicalSkillsBEAN.getTechnology() + "-" + technicalSkillsBEAN.getKnowledgeLevel()), 1, listAssesmentLanguageTestBEAN.size() + listTechnicalSkillsBEAN.indexOf(technicalSkillsBEAN));

                }
                Label lblTechEdit = new Label("Edit");
                lblTechEdit.getStyleClass().add("pr_title");
                lblTechEdit.setUnderline(true);
                lblTechEdit.setCursor(Cursor.HAND);
                lblTechEdit.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        Context.getInstance().currentProfile().setPreviewCurrentEdit("tech_skill");
                        Stage stage = (Stage) gridTestAndSkills.getScene().getWindow();
                        stage.close();
                    }
                });
                /* ====================== Add Edit Label TO Global ====================== */
                obsLabelEdits.add(lblTechEdit);
                gridTestAndSkills.add(lblTechEdit, 4, listAssesmentLanguageTestBEAN.size());
                /* ====================== End Technical Skills ====================== */
                /* ====================== Other Skill ====================== */
                List<OtherSkillBEAN> listOtherSkillBEAN = otherSkillsDAO.retrieveOtherSkills(ENQUIRY_ID);
                for (OtherSkillBEAN otherSkillBEAN : listOtherSkillBEAN) {

                    Label lblTechSkill = new Label("Other Skill");
                    lblTechSkill.getStyleClass().add("pr_title");
                    gridTestAndSkills.add(lblTechSkill, 0, listAssesmentLanguageTestBEAN.size() + listTechnicalSkillsBEAN.size() + listOtherSkillBEAN.indexOf(otherSkillBEAN));
                    gridTestAndSkills.add(new Label(otherSkillBEAN.getOtherSkill() + "-" + otherSkillBEAN.getCategory()), 1, listAssesmentLanguageTestBEAN.size() + listTechnicalSkillsBEAN.size() + listOtherSkillBEAN.indexOf(otherSkillBEAN));

                }
                Label lblOtherEdit = new Label("Edit");
                lblOtherEdit.getStyleClass().add("pr_title");
                lblOtherEdit.setUnderline(true);
                lblOtherEdit.setCursor(Cursor.HAND);
                lblOtherEdit.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        Context.getInstance().currentProfile().setPreviewCurrentEdit("other_skill");
                        Stage stage = (Stage) gridTestAndSkills.getScene().getWindow();
                        stage.close();
                    }
                });
                /* ====================== Add Edit Label TO Global ====================== */
                obsLabelEdits.add(lblOtherEdit);
                gridTestAndSkills.add(lblOtherEdit, 4, listAssesmentLanguageTestBEAN.size() + listTechnicalSkillsBEAN.size());
                /* ====================== End Other Skill ====================== */
                break;
            case "migration":
                /* ====================== Language Test ====================== */
                for (AssesmentLanguageTestBEAN languageTestBEAN : listAssesmentLanguageTestBEAN) {
                    Label lblLanTest = new Label("Language Test");
                    lblLanTest.getStyleClass().add("pr_title");
                    Label lblOverAll = new Label("Overall Score");
                    lblOverAll.getStyleClass().add("pr_title");
                    gridTestAndSkills.add(lblLanTest, 0, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(new Label(languageTestBEAN.getLanguageTest()), 1, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(lblOverAll, 2, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                    gridTestAndSkills.add(new Label(languageTestBEAN.getOverall()), 3, listAssesmentLanguageTestBEAN.indexOf(languageTestBEAN));
                }
                Label lblMigraEdit = new Label("Edit");
                lblMigraEdit.getStyleClass().add("pr_title");
                lblMigraEdit.setUnderline(true);
                lblMigraEdit.setCursor(Cursor.HAND);
                lblMigraEdit.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        Context.getInstance().currentProfile().setPreviewCurrentEdit("lang_test");
                        Stage stage = (Stage) gridTestAndSkills.getScene().getWindow();
                        stage.close();
                    }
                });
                /* ====================== Add Edit Label TO Global ====================== */
                obsLabelEdits.add(lblMigraEdit);
                gridTestAndSkills.add(lblMigraEdit, 4, 0);
                /* ====================== End Language Test ====================== */
                break;

            default:
                System.out.println("Not Working Hiding feature");
        }

    }

    /**
     *
     */
    public void showLanguageSkills() {
        gridLanguageSkills.getChildren().clear();

        List<LanguageSkillsBEAN> listLanguageBEAN = languageSkillsDAO.retrieveLanguageSkills(ENQUIRY_ID);
        Label lblLanguageSkill = new Label("Language Skill");
        Label lblProficiency = new Label("Proficiency");
        lblProficiency.getStyleClass().add("pr_title");
        lblLanguageSkill.getStyleClass().add("pr_title");

        gridLanguageSkills.add(lblLanguageSkill, 0, 0);
        gridLanguageSkills.add(lblProficiency, 1, 0);
        for (LanguageSkillsBEAN languageSkillsBEAN : listLanguageBEAN) {
            gridLanguageSkills.add(new Label(languageSkillsBEAN.getLanguage()), 0, listLanguageBEAN.indexOf(languageSkillsBEAN) + 1);
            gridLanguageSkills.add(new Label(languageSkillsBEAN.getProficiency().replace("[", "").replace("]", "")), 1, listLanguageBEAN.indexOf(languageSkillsBEAN) + 1);

        }
        Label lblEdit = new Label("Edit");
        lblEdit.getStyleClass().add("pr_title");
        lblEdit.setUnderline(true);
        lblEdit.setCursor(Cursor.HAND);
        lblEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("lang_skill");
                Stage stage = (Stage) gridLanguageSkills.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblEdit);
        gridLanguageSkills.add(lblEdit, 2, 0);

    }

    /**
     *
     */
    public void showWorkExperience() {
        List<WorktExperienceBEAN> workExpBEANList = WorkExperienceDAO.getWorkExperience(ENQUIRY_ID);
        gridWorkExperience.getChildren().clear();
        Label lblWorkExperience = new Label("Work Experience");
        Label lblSkillArea = new Label("Skill Area");
        Label lblDuration = new Label("Duration ");
        lblWorkExperience.getStyleClass().add("pr_title");
        lblSkillArea.getStyleClass().add("pr_title");
        lblDuration.getStyleClass().add("pr_title");
        gridWorkExperience.add(lblWorkExperience, 0, 0);
        gridWorkExperience.add(lblSkillArea, 1, 0);
        gridWorkExperience.add(lblDuration, 2, 0);

        for (WorktExperienceBEAN assessmentExperienceBEAN : workExpBEANList) {
            gridWorkExperience.add(new Label(assessmentExperienceBEAN.getProfession()), 0, workExpBEANList.indexOf(assessmentExperienceBEAN) + 1);
            gridWorkExperience.add(new Label(assessmentExperienceBEAN.getProfession()), 1, workExpBEANList.indexOf(assessmentExperienceBEAN) + 1);
            gridWorkExperience.add(new Label(assessmentExperienceBEAN.getDuration()), 2, workExpBEANList.indexOf(assessmentExperienceBEAN) + 1);

        }
        Label lblEdit = new Label("Edit");
        lblEdit.getStyleClass().add("pr_title");
        lblEdit.setUnderline(true);
        lblEdit.setCursor(Cursor.HAND);
        lblEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("work_exp");
                Stage stage = (Stage) gridWorkExperience.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblEdit);
        gridWorkExperience.add(lblEdit, 3, 0);
    }

    /**
     *
     */
    public void showQualificationDetails() {
        AssesmentSsslcBEAN assesmentSsslcBEAN = AssessmentSSLCDAO.getSslcDetails(ENQUIRY_ID);
        AssesmentPlusTwoBEAN assesmentPlusTwoBEAN = Assesmentplus2DAO.getPlusDetails(ENQUIRY_ID);
        List<AssesmentTertiaryBEAN> assesmentTertiaryBEANList = QualificationsCrudDAO.retrieveTeritaryQualifications((counselorDetailsBEAN.getEnquiryID()));
        gridQualifications.getChildren().clear();
        Label lblQualification = new Label("Qualification");
        Label lblBoardUniv = new Label("Board/University");
        Label lblMediumInstruction = new Label("Medium / Field Of Study");
        lblQualification.getStyleClass().add("pr_title");
        lblBoardUniv.getStyleClass().add("pr_title");
        lblMediumInstruction.getStyleClass().add("pr_title");
        gridQualifications.add(lblQualification, 0, 0);
        gridQualifications.add(lblBoardUniv, 1, 0);
        gridQualifications.add(lblMediumInstruction, 2, 0);
        gridQualifications.add(new Label("10th"), 0, 1);
        gridQualifications.add(new Label(assesmentSsslcBEAN.getSslcBoard()), 1, 1);
        gridQualifications.add(new Label(assesmentSsslcBEAN.getSslcMedium()), 2, 1);

        gridQualifications.add(new Label("12th"), 0, 2);
        gridQualifications.add(new Label(assesmentPlusTwoBEAN.getPlusTwoBoard()), 1, 2);
        gridQualifications.add(new Label(assesmentPlusTwoBEAN.getPlusTwoMedium()), 2, 2);

        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : assesmentTertiaryBEANList) {
            gridQualifications.add(new Label(assesmentTertiaryBEAN.getTertieryLevel() + "-" + assesmentTertiaryBEAN.getTertieryField()), 0, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 3);
            gridQualifications.add(new Label(assesmentTertiaryBEAN.getUniversity()), 1, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 3);
        }

        /* ====================== Add Edit Button ====================== */
        Label lblEdit = new Label("Edit");
        lblEdit.getStyleClass().add("pr_title");
        lblEdit.setUnderline(true);
        lblEdit.setCursor(Cursor.HAND);
        lblEdit.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("qualifications");
                Stage stage = (Stage) gridQualifications.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblEdit);
        gridQualifications.add(lblEdit, 3, 0);
    }

    /**
     *
     * @param tab
     * @param hideShow
     */
    public void showHideFeatures(String tab, boolean hideShow) {
        switch (tab) {
            case "study":
                vboxWorkRequired.setVisible(hideShow);
                vboxWorkRequired.setManaged(hideShow);
                vboxMigrationRequired.setVisible(hideShow);
                vboxMigrationRequired.setManaged(hideShow);
                vboxTrainingRequired.setVisible(hideShow);
                vboxTrainingRequired.setManaged(hideShow);
                if (counselorDetailsBEAN.getApplicationType() != null) {
                    if (counselorDetailsBEAN.getApplicationType().equalsIgnoreCase("Single") || counselorDetailsBEAN.getApplicationType().equalsIgnoreCase("Divorced")) {
                        vboxSpouseDetails.setVisible(hideShow);
                        vboxSpouseDetails.setManaged(hideShow);
                    }
                }
                vboxLanguageSkill.setVisible(hideShow);
                vboxLanguageSkill.setManaged(hideShow);
                lblPreviewTitle.setText("Enquiry Assessment - Study");

                break;
            case "work":
                vboxStudyRequired.setVisible(hideShow);
                vboxStudyRequired.setManaged(hideShow);
                vboxMigrationRequired.setVisible(hideShow);
                vboxMigrationRequired.setManaged(hideShow);
                vboxTrainingRequired.setVisible(hideShow);
                vboxTrainingRequired.setManaged(hideShow);
                vboxSpouseDetails.setVisible(hideShow);
                vboxSpouseDetails.setManaged(hideShow);
                lblPreviewTitle.setText("Enquiry Assessment - Work");

                break;
            case "migration":
                vboxStudyRequired.setVisible(hideShow);
                vboxStudyRequired.setManaged(hideShow);
                vboxWorkRequired.setVisible(hideShow);
                vboxWorkRequired.setManaged(hideShow);
                vboxTrainingRequired.setVisible(hideShow);
                vboxTrainingRequired.setManaged(hideShow);
                vboxLanguageSkill.setVisible(hideShow);
                vboxLanguageSkill.setManaged(hideShow);
                lblPreviewTitle.setText("Enquiry Assessment - Migration");

                break;
            case "training":
                vboxStudyRequired.setVisible(hideShow);
                vboxStudyRequired.setManaged(hideShow);
                vboxWorkRequired.setVisible(hideShow);
                vboxWorkRequired.setManaged(hideShow);
                vboxMigrationRequired.setVisible(hideShow);
                vboxMigrationRequired.setManaged(hideShow);
                vboxSpouseDetails.setVisible(hideShow);
                vboxSpouseDetails.setManaged(hideShow);
                vboxLanguageSkill.setVisible(hideShow);
                vboxLanguageSkill.setManaged(hideShow);
                lblPreviewTitle.setText("Enquiry Assessment - Training");

                break;
            default:
                System.out.println("Not Working Hiding feature");
        }

    }

    /**
     *
     */
    public void showStudyRequiredDetails() {
        List<StudySuggestedRequiredBEAN> studySuggestedRequiredList = ProgramSuggestedRequiredDAO.retrieveStudyDetails(ENQUIRY_ID);
        gridStudyRequired.getChildren().clear();
        for (StudySuggestedRequiredBEAN studySuggestedRequiredBEAN : studySuggestedRequiredList) {
            Label lblTitle = new Label("Qualification Level & Field:");
            Label lblContryLocation = new Label("Country & Location:");
            Label lblChoice = new Label("Choice:");
            Label lblEdit = new Label("Edit");

            lblTitle.getStyleClass().add("pr_title");
            lblContryLocation.getStyleClass().add("pr_title");
            lblChoice.getStyleClass().add("pr_title");
            lblEdit.getStyleClass().add("pr_title");

            lblEdit.setUnderline(true);
            lblEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    Context.getInstance().currentProfile().setPreviewCurrentEdit("study");
                    Stage stage = (Stage) gridMigrationRequired.getScene().getWindow();
                    stage.close();
                }
            });
            /* ====================== Add Edit Label TO Global ====================== */
            obsLabelEdits.add(lblEdit);
            Label lblLevelField = new Label(studySuggestedRequiredBEAN.getProgram_level() + "-" + studySuggestedRequiredBEAN.getProgram_field());
            Label lblCountryLocation = new Label(studySuggestedRequiredBEAN.getCountry() + "-" + studySuggestedRequiredBEAN.getLocation());
            lblLevelField.getStyleClass().add("pr_data");
            lblCountryLocation.getStyleClass().add("pr_data");
            gridStudyRequired.add(lblTitle, 0, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
            gridStudyRequired.add(new Label(studySuggestedRequiredBEAN.getProgram_level() + "-" + studySuggestedRequiredBEAN.getProgram_field()), 1, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
            gridStudyRequired.add(lblContryLocation, 2, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
            gridStudyRequired.add(new Label(studySuggestedRequiredBEAN.getCountry() + "-" + studySuggestedRequiredBEAN.getLocation()), 3, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
            gridStudyRequired.add(lblChoice, 4, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
            gridStudyRequired.add(new Label("" + (studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1)), 5, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
            gridStudyRequired.add(lblEdit, 6, studySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN));
        }
    }

    /**
     *
     */
    public void showWorkRequiredDetails() {
        List<AssesmentWorkBEAN> workDetailList = ProgramSuggestedRequiredDAO.getWorkDetails(ENQUIRY_ID);
        gridWorkRequired.getChildren().clear();
        for (AssesmentWorkBEAN assesmentWorkBEAN : workDetailList) {
            Label lblTitle = new Label("Profession & Skill Area:");
            Label lblSalaryMonth = new Label("Salary Per Month:");
            Label lblContryLocation = new Label("Country & Location:");
            Label lblChoice = new Label("Choice:");
            Label lblEdit = new Label("Edit");

            lblTitle.getStyleClass().add("pr_title");
            lblContryLocation.getStyleClass().add("pr_title");
            lblSalaryMonth.getStyleClass().add("pr_title");
            lblChoice.getStyleClass().add("pr_title");
            lblEdit.getStyleClass().add("pr_title");

            lblEdit.setUnderline(true);
            lblEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    Context.getInstance().currentProfile().setPreviewCurrentEdit("work");
                    Stage stage = (Stage) gridMigrationRequired.getScene().getWindow();
                    stage.close();
                }
            });
            /* ====================== Add Edit Label TO Global ====================== */
            obsLabelEdits.add(lblEdit);
            gridWorkRequired.add(lblTitle, 0, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(new Label(assesmentWorkBEAN.getProfession() + "-" + assesmentWorkBEAN.getProfession()), 1, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(lblSalaryMonth, 2, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(new Label(assesmentWorkBEAN.getCurreny() + "-" + assesmentWorkBEAN.getMax_salary()), 3, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(lblContryLocation, 4, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(new Label(assesmentWorkBEAN.getCountry() + "-" + assesmentWorkBEAN.getLocation()), 5, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(lblChoice, 6, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(new Label(assesmentWorkBEAN.getChoice()), 7, workDetailList.indexOf(assesmentWorkBEAN));
            gridWorkRequired.add(lblEdit, 8, workDetailList.indexOf(assesmentWorkBEAN));
        }
    }

    /**
     *
     */
    public void showMigrationRequiredDetails() {
        List<AssesmentMigrateBEAN> migrateDetailsList = ProgramSuggestedRequiredDAO.getMigrationDetails(ENQUIRY_ID);
        gridMigrationRequired.getChildren().clear();
        for (AssesmentMigrateBEAN assesmentMigrateBEAN : migrateDetailsList) {
            Label lblProfession = new Label("Profession:");
            Label lblCategoryCountry = new Label("Category & Country:");
            Label lblChoice = new Label("Choice:");
            Label lblEdit = new Label("Edit");

            lblProfession.getStyleClass().add("pr_title");
            lblCategoryCountry.getStyleClass().add("pr_title");
            lblChoice.getStyleClass().add("pr_title");
            lblEdit.getStyleClass().add("pr_title");

            lblEdit.setUnderline(true);
            lblEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    Context.getInstance().currentProfile().setPreviewCurrentEdit("migration");
                    Stage stage = (Stage) gridMigrationRequired.getScene().getWindow();
                    stage.close();
                }
            });
            /* ====================== Add Edit Label TO Global ====================== */
            obsLabelEdits.add(lblEdit);
            gridMigrationRequired.add(lblProfession, 0, migrateDetailsList.indexOf(assesmentMigrateBEAN));
            gridMigrationRequired.add(new Label(assesmentMigrateBEAN.getProfession()), 1, migrateDetailsList.indexOf(assesmentMigrateBEAN));
            gridMigrationRequired.add(lblCategoryCountry, 2, migrateDetailsList.indexOf(assesmentMigrateBEAN));
            gridMigrationRequired.add(new Label(assesmentMigrateBEAN.getCategory() + "-" + assesmentMigrateBEAN.getCountry()), 3, migrateDetailsList.indexOf(assesmentMigrateBEAN));
            gridMigrationRequired.add(lblChoice, 4, migrateDetailsList.indexOf(assesmentMigrateBEAN));
            gridMigrationRequired.add(new Label(assesmentMigrateBEAN.getChoice()), 5, migrateDetailsList.indexOf(assesmentMigrateBEAN));
            gridMigrationRequired.add(lblEdit, 6, migrateDetailsList.indexOf(assesmentMigrateBEAN));
        }
    }

    /**
     *
     */
    public void showTrainigRequiredDetails() {
        List<AssesmentTrainingBEAN> trainingDetailList = ProgramSuggestedRequiredDAO.getTrainingDetails(ENQUIRY_ID);
        gridTrainingRequired.getChildren().clear();
        for (AssesmentTrainingBEAN assesmentTrainingBEAN : trainingDetailList) {
            Label lblCourse = new Label("Course:");
            Label lblBatch = new Label("Batch:");
            Label lblTiming = new Label("Timing:");
            Label lblPropJoinDate = new Label("Proposed Joining Date:");
            Label lblChoice = new Label("Choice:");
            Label lblEdit = new Label("Edit");

            lblCourse.getStyleClass().add("pr_title");
            lblBatch.getStyleClass().add("pr_title");
            lblTiming.getStyleClass().add("pr_title");
            lblPropJoinDate.getStyleClass().add("pr_title");
            lblChoice.getStyleClass().add("pr_title");
            lblEdit.getStyleClass().add("pr_title");

            lblEdit.setUnderline(true);
            lblEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    Context.getInstance().currentProfile().setPreviewCurrentEdit("training");
                    Stage stage = (Stage) gridTrainingRequired.getScene().getWindow();
                    stage.close();
                }
            });
            /* ====================== Add Edit Label TO Global ====================== */
            obsLabelEdits.add(lblEdit);
            gridTrainingRequired.add(lblCourse, 0, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(new Label(assesmentTrainingBEAN.getTraining()), 1, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(lblBatch, 2, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(new Label(assesmentTrainingBEAN.getBatch()), 3, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(lblTiming, 4, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(new Label(assesmentTrainingBEAN.getTiming()), 5, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(lblPropJoinDate, 6, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(new Label(assesmentTrainingBEAN.getFromDate().toString()), 7, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(lblChoice, 8, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(new Label(assesmentTrainingBEAN.getChoice()), 9, trainingDetailList.indexOf(assesmentTrainingBEAN));
            gridTrainingRequired.add(lblEdit, 10, trainingDetailList.indexOf(assesmentTrainingBEAN));
        }
    }

    /**
     *
     */
    public void showPersonalDetails() {
        lblName.setText(counselorDetailsBEAN.getContactName());
        lblAge.setText(counselorDetailsBEAN.getDob());
        lblGender.setText(counselorDetailsBEAN.getCounselorBranch());
        lblApplicationType.setText(counselorDetailsBEAN.getApplicationType());

        lblDistricts.setText(counselorDetailsBEAN.getDistrict());
        lblState.setText(counselorDetailsBEAN.getState());
        lblCountry.setText(counselorDetailsBEAN.getCountry());

        lblPhoneNumber1.setText(counselorDetailsBEAN.getContactNumber1());
        lblPhoneNumber2.setText(counselorDetailsBEAN.getContactNumber2());
        lblEmail.setText(counselorDetailsBEAN.getContactEmail());
        lblPersonalEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Context.getInstance().currentProfile().setPreviewCurrentEdit("person");
                Stage stage = (Stage) lblName.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Show Addtional Personal Info ====================== */
        AssessmentPersonBEAN assessmentPersonBEAN = PersonalCrudDAO.getPersonalDetails(ENQUIRY_ID);
        lblAge.setText(assessmentPersonBEAN.getAge());
        lblGender.setText(assessmentPersonBEAN.getGender());
        lblApplicationType.setText(assessmentPersonBEAN.getMaritalStatus());
        /* ====================== Add Edit Label TO Global ====================== */
        obsLabelEdits.add(lblPersonalEdit);
    }

    /**
     *
     * @param node
     */
    public void print(final Node node) {
        Node printNode = node;
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / printNode.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / printNode.getBoundsInParent().getHeight();
        printNode.getTransforms().add(new Scale(scaleX, scaleY));
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(printNode);
            if (success) {
                job.endJob();
            }
        }
    }
}

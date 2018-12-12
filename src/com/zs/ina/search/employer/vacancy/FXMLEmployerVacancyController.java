/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.search.employer.vacancy;

import com.jfoenix.controls.JFXDatePicker;
import com.zs.ina.admin.master.documents.dao.DocumentPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.search.common.bean.AccomBEAN;
import com.zs.ina.search.common.bean.CoursesBEAN;
import com.zs.ina.search.common.bean.DocumentReqBEAN;
import com.zs.ina.search.common.bean.ExperienceReqBEAN;
import com.zs.ina.search.common.bean.LanguageTestBEAN;
import com.zs.ina.search.common.bean.OtherSkillBEAN;
import com.zs.ina.search.common.bean.TechnicalSkillBEAN;
import com.zs.ina.search.employer.dao.EmployerBEAN;
import com.zs.ina.search.employer.dao.EmployerDAO;
import com.zs.ina.search.employer.dao.EmployerVacancyDAO;
import com.zs.ina.search.master.common.GlobalSearchMasterData;
import com.zs.ina.search.master.common.SearchConstants;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jidefx.utils.converter.DefaultObjectConverter;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAcademicReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAccomodationDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyDocumentReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyExperienceReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyLanguageTestDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyOtherSkillDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyTechnicalReqDAO;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEmployerVacancyController implements Initializable {

    @FXML
    private ComboBox<EmployerBEAN> cmbEmployerName;
    @FXML
    private Button btnSave;
    @FXML
    private GridPane gridExperience;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private ComboBox<String> cmbAgeFrom;
    @FXML
    private ComboBox<String> cmbAgeTo;
    @FXML
    private ComboBox<String> cmbMaritalStatus;
    @FXML
    private ComboBox<String> cmbSslcBoard;
    @FXML
    private ComboBox<String> cmbSslcMedium;
    @FXML
    private ComboBox<String> cmbPlustTwo;
    @FXML
    private ComboBox<String> cmbPlustTwoMedium;
    @FXML
    private GridPane gridAcademicReq;
    @FXML
    private ComboBox<String> cmbEngBoard;
    @FXML
    private ComboBox<String> cmbEngDuration;
    @FXML
    private GridPane gridLangTest;
    @FXML
    private GridPane gridTechnicalReq;
    @FXML
    private GridPane gridOtherSkill;
    @FXML
    private GridPane gridDocumentsReq;
    @FXML
    private GridPane gridAccommodation;
    @FXML
    private ComboBox<String> cmbJobTitle;
    @FXML
    private TextField txtVacancyCount;
    @FXML
    private TextArea txtArDodDescription;
    @FXML
    private ComboBox<String> cmbSalaryCurrency;
    @FXML
    private ComboBox<String> cmbSalaryFrom;
    @FXML
    private ComboBox<String> cmbSalaryTo;
    @FXML
    private Button btnClear;
    @FXML
    private JFXDatePicker dpExpiryDate;
    /* ======================== Coding ==================== */
    GlobalSearchMasterData masterData = new GlobalSearchMasterData();
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    EmployerVacancyDAO employerVacancyDAO = springAppContext.getBean(EmployerVacancyDAO.class);
    EmployerDAO employerDAO = springAppContext.getBean(EmployerDAO.class);
    EmployerBEAN employerBEAN = new EmployerBEAN();
    String VACANCY_ID = null;
    String EMPLOYER_ID = null;

    /* ======================== Dynamic Control BEANS ==================== */
    VacancyAcademicReqDAO vacancyAcademicReqDAO = springAppContext.getBean(VacancyAcademicReqDAO.class);
    VacancyLanguageTestDAO languageTestDAO = springAppContext.getBean(VacancyLanguageTestDAO.class);
    VacancyTechnicalReqDAO vacancyTechnicalReqDAO = springAppContext.getBean(VacancyTechnicalReqDAO.class);
    VacancyOtherSkillDAO vacancyOtherSkillDAO = springAppContext.getBean(VacancyOtherSkillDAO.class);
    VacancyDocumentReqDAO vacancyDocumentReqDAO = springAppContext.getBean(VacancyDocumentReqDAO.class);
    VacancyAccomodationDAO vacancyAccomodationDAO = springAppContext.getBean(VacancyAccomodationDAO.class);
    VacancyExperienceReqDAO vacancyExperienceReqDAO = springAppContext.getBean(VacancyExperienceReqDAO.class);

    /* ======================== Dyanamic Control Lists ==================== */
    ObservableList<CoursesBEAN> listAcademicReq = FXCollections.observableArrayList();
    ObservableList<LanguageTestBEAN> listLanguageTests = FXCollections.observableArrayList();
    ObservableList<TechnicalSkillBEAN> listTechnicalReq = FXCollections.observableArrayList();
    ObservableList<OtherSkillBEAN> listOtherSkillReq = FXCollections.observableArrayList();
    ObservableList<DocumentReqBEAN> listDocumentsReq = FXCollections.observableArrayList();
    ObservableList<AccomBEAN> listAccomodationReq = FXCollections.observableArrayList();
    ObservableList<ExperienceReqBEAN> listExperienceReq = FXCollections.observableArrayList();

    /* ======================== Dynamic Control Validation Supports ==================== */
    ObservableList<ValidationSupport> ObservValidateAcademicReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateLanguageTests = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateTechnicalReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateOtherSkillReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateDocumentsReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateAccomodationReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateExperienceReq = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        initializeMasterData();
//        bindVacancyBasics();
//        createDecorations();
//        buttonListners();
//        addAdditionalFeatures();
//        /* ======================== Dynamic Controls ==================== */
//        initAcademicRequirements();
//        initLanguageTestReq();
//        initTechnicalReq();
//        initOtherSkillReq();
//        initDocumentReq();
//        initAccomodationReq();
//        initExperienceReq();
    }

    /**
     * For Loading Data From A Row Factory
     *
     * @param employerEditVacancyBEAN instance employer bean required as
     * parameter
     */
    public void initData(EmployerBEAN employerEditVacancyBEAN) {
        BeanUtils.copyProperties(employerEditVacancyBEAN, employerBEAN);
        VACANCY_ID = employerEditVacancyBEAN.getVacancyId();
        EMPLOYER_ID = employerEditVacancyBEAN.getEmployerId();
        initializeMasterData();
        bindVacancyBasics();
        createDecorations();
        buttonListners();
        addAdditionalFeatures();
        /* ======================== Dynamic Controls ==================== */
        initAcademicRequirements();
        initLanguageTestReq();
        initTechnicalReq();
        initOtherSkillReq();
        initDocumentReq();
        initAccomodationReq();
        initExperienceReq();
        /* ======================== Change Date Format ==================== */
        ChangeDateFormat.datePickerDateFormatter(dpExpiryDate);
    }

    /**
     *
     */
    public void initAcademicRequirements() {

        listAcademicReq = vacancyAcademicReqDAO.retrieveAcademicRequirements(VACANCY_ID);
        if (listAcademicReq.size() > 0) {
            addDynaAcademicRequirementRows();
        } else {
            CoursesBEAN coursesBEAN = new CoursesBEAN();
            listAcademicReq.add(coursesBEAN);
            addDynaAcademicRequirementRows();
        }

    }

    /**
     *
     */
    public void addDynaAcademicRequirementRows() {

        gridAcademicReq.getChildren().clear();
        ObservValidateAcademicReq.clear();
        for (CoursesBEAN academicRequirementsBEAN : listAcademicReq) {
            academicRequirementsBEAN.setVacancyId(VACANCY_ID);

            ComboBox<String> cmbProgramLevel = new ComboBox();
            cmbProgramLevel.setPromptText("Select Program Level");
            ComboBoxJumpToChar.jumpToChar(cmbProgramLevel);
            ObservableList<String> listProgramLevel = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getProgramLevels());
            cmbProgramLevel.getItems().addAll(listProgramLevel);

            ComboBox cmbProgramField = new ComboBox();
            cmbProgramField.setPromptText("Select Program Field");
            ComboBoxJumpToChar.jumpToChar(cmbProgramField);

            ComboBox cmbDuration = new ComboBox(RetrieveStaticMasterDAO.getDuration());
            cmbDuration.setPromptText("Select Duration");
            ComboBoxJumpToChar.jumpToChar(cmbDuration);

            GridPane.setHgrow(cmbProgramLevel, Priority.NEVER);
            GridPane.setHgrow(cmbProgramField, Priority.NEVER);
            GridPane.setHgrow(cmbDuration, Priority.NEVER);

            cmbProgramLevel.setMaxWidth(Double.MAX_VALUE);
            cmbProgramField.setMaxWidth(Double.MAX_VALUE);
            cmbDuration.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationAcademicReqSupport = new ValidationSupport();
            validationAcademicReqSupport.registerValidator(cmbProgramLevel, Validator.createEmptyValidator("Program Level Required"));
            validationAcademicReqSupport.registerValidator(cmbProgramField, Validator.createEmptyValidator("Program Field Required"));
            validationAcademicReqSupport.registerValidator(cmbDuration, Validator.createEmptyValidator("Duration Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            ObservValidateAcademicReq.add(validationAcademicReqSupport);

            /* ======================== Program Level Change Event  ==================== */
            cmbProgramLevel.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        // cmbServiceLocation.getSelectionModel().selectFirst();
                        Task< ObservableList> taskLoadState = new Task<ObservableList>() {
                            @Override
                            protected ObservableList call() throws Exception {
                                ObservableList<String> listProgramFields = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getProgramFields(newValue));
                                return listProgramFields;
                            }
                        };
                        taskLoadState.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                            @Override
                            public void handle(WorkerStateEvent event) {
                                cmbProgramField.setItems(taskLoadState.getValue());

                            }
                        });
                        taskLoadState.run();
                    }
                }
            });
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationAcademicReqSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationAcademicReqSupport.isInvalid()) {
                        academicRequirementsBEAN.setVacancyId(VACANCY_ID);
                        if (academicRequirementsBEAN.getRowId() != null) {
                            vacancyAcademicReqDAO.updateAcademicRequirements(academicRequirementsBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            academicRequirementsBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = vacancyAcademicReqDAO.insertAcademicRequirements(academicRequirementsBEAN);
                        }
                        CoursesBEAN emptyBEAN = new CoursesBEAN();
                        listAcademicReq.add(emptyBEAN);
                        addDynaAcademicRequirementRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridAcademicReq);
//                    }
                    gridAcademicReq.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (academicRequirementsBEAN.getRowId() != null) {
                            vacancyAcademicReqDAO.deleteAcademicRequirements(academicRequirementsBEAN.getRowId());
                        }
                        listAcademicReq.remove(academicRequirementsBEAN);
                        if (listAcademicReq.size() > 0) {
                            addDynaAcademicRequirementRows();
                        } else {
                            CoursesBEAN emptyBEAN = new CoursesBEAN();
                            listAcademicReq.add(emptyBEAN);
                            addDynaAcademicRequirementRows();
                        }
                        ObservValidateAcademicReq.remove(validationAcademicReqSupport);
                        gridAcademicReq.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listAcademicReq.indexOf(academicRequirementsBEAN) == 0) {
                gridAcademicReq.add(new Label("Program Level"), 0, 0);
                gridAcademicReq.add(new Label("Program Field"), 1, 0);
                gridAcademicReq.add(new Label("Duration"), 2, 0);
            }
            Bindings.bindBidirectional(cmbProgramLevel.valueProperty(), academicRequirementsBEAN.levelProperty());
            Bindings.bindBidirectional(cmbProgramField.valueProperty(), academicRequirementsBEAN.fieldProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), academicRequirementsBEAN.durationProperty());

            gridAcademicReq.add(cmbProgramLevel, 0, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);
            gridAcademicReq.add(cmbProgramField, 1, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);
            gridAcademicReq.add(cmbDuration, 2, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAcademicReq.add(hBox, 3, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initLanguageTestReq() {

        listLanguageTests = languageTestDAO.retrieveLanguageTest(VACANCY_ID);
        if (listLanguageTests.size() > 0) {
            addDynaLanguageTestRows();
        } else {
            LanguageTestBEAN languageTestBEAN = new LanguageTestBEAN();
            listLanguageTests.add(languageTestBEAN);
            addDynaLanguageTestRows();
        }

    }

    /**
     *
     */
    public void addDynaLanguageTestRows() {

        gridLangTest.getChildren().clear();
        ObservValidateLanguageTests.clear();
        for (LanguageTestBEAN languageTestBEAN : listLanguageTests) {
            languageTestBEAN.setVacancyId(VACANCY_ID);

            ComboBox<String> cmbLangTest = new ComboBox();
            cmbLangTest.setPromptText("Test");
            ComboBoxJumpToChar.jumpToChar(cmbLangTest);
            ObservableList<String> listTests = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getOtherTest());
            cmbLangTest.getItems().addAll(listTests);

            ComboBox cmbReading = new ComboBox(RetrieveStaticMasterDAO.getScore());
            cmbReading.setPromptText("Score");
            ComboBoxJumpToChar.jumpToChar(cmbReading);

            ComboBox cmbListening = new ComboBox(RetrieveStaticMasterDAO.getScore());
            cmbListening.setPromptText("Score");
            ComboBoxJumpToChar.jumpToChar(cmbListening);

            ComboBox cmbWriting = new ComboBox(RetrieveStaticMasterDAO.getScore());
            cmbWriting.setPromptText("Score");
            ComboBoxJumpToChar.jumpToChar(cmbWriting);

            ComboBox cmbSpeaking = new ComboBox(RetrieveStaticMasterDAO.getScore());
            cmbSpeaking.setPromptText("Score");
            ComboBoxJumpToChar.jumpToChar(cmbSpeaking);

            GridPane.setHgrow(cmbLangTest, Priority.NEVER);
            GridPane.setHgrow(cmbReading, Priority.NEVER);
            GridPane.setHgrow(cmbWriting, Priority.NEVER);
            GridPane.setHgrow(cmbListening, Priority.NEVER);
            GridPane.setHgrow(cmbSpeaking, Priority.NEVER);

            cmbLangTest.setMaxWidth(Double.MAX_VALUE);
            cmbReading.setMaxWidth(Double.MAX_VALUE);
            cmbWriting.setMaxWidth(Double.MAX_VALUE);
            cmbListening.setMaxWidth(Double.MAX_VALUE);
            cmbSpeaking.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationLanguageTestSupport = new ValidationSupport();
            validationLanguageTestSupport.registerValidator(cmbLangTest, Validator.createEmptyValidator("Language Test Required"));
            validationLanguageTestSupport.registerValidator(cmbReading, Validator.createEmptyValidator("Reading Score Required"));
            validationLanguageTestSupport.registerValidator(cmbWriting, Validator.createEmptyValidator("Writing Score Rquired"));
            validationLanguageTestSupport.registerValidator(cmbListening, Validator.createEmptyValidator("Listening Score Rquired"));
            validationLanguageTestSupport.registerValidator(cmbSpeaking, Validator.createEmptyValidator("Speaking Score Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            ObservValidateLanguageTests.add(validationLanguageTestSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationLanguageTestSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationLanguageTestSupport.isInvalid()) {
                        languageTestBEAN.setVacancyId(VACANCY_ID);
                        if (languageTestBEAN.getRowId() != null) {
                            languageTestDAO.updateLanguageTest(languageTestBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            languageTestBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = languageTestDAO.insertLanguageTest(languageTestBEAN);
                        }
                        LanguageTestBEAN emptyBEAN = new LanguageTestBEAN();
                        listLanguageTests.add(emptyBEAN);
                        addDynaLanguageTestRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridLangTest);
//                    }
                    gridLangTest.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (languageTestBEAN.getRowId() != null) {
                            languageTestDAO.deleteLanguageTest(languageTestBEAN.getRowId());
                        }
                        listLanguageTests.remove(languageTestBEAN);
                        if (listLanguageTests.size() > 0) {
                            addDynaLanguageTestRows();
                        } else {
                            LanguageTestBEAN emptyBEAN = new LanguageTestBEAN();
                            listLanguageTests.add(emptyBEAN);
                            addDynaLanguageTestRows();
                        }
                        ObservValidateLanguageTests.remove(validationLanguageTestSupport);
                        gridLangTest.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listLanguageTests.indexOf(languageTestBEAN) == 0) {
                gridLangTest.add(new Label("Language Test"), 0, 0);
                gridLangTest.add(new Label("Reading"), 1, 0);
                gridLangTest.add(new Label("Listening"), 2, 0);
                gridLangTest.add(new Label("Writing"), 3, 0);
                gridLangTest.add(new Label("Speaking"), 4, 0);
            }
            Bindings.bindBidirectional(cmbLangTest.valueProperty(), languageTestBEAN.languageTestProperty());
            Bindings.bindBidirectional(cmbReading.valueProperty(), languageTestBEAN.readingProperty());
            Bindings.bindBidirectional(cmbWriting.valueProperty(), languageTestBEAN.writingProperty());
            Bindings.bindBidirectional(cmbListening.valueProperty(), languageTestBEAN.listeningProperty());
            Bindings.bindBidirectional(cmbSpeaking.valueProperty(), languageTestBEAN.speakingProperty());

            gridLangTest.add(cmbLangTest, 0, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLangTest.add(cmbReading, 1, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLangTest.add(cmbListening, 2, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLangTest.add(cmbWriting, 3, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLangTest.add(cmbSpeaking, 4, listLanguageTests.indexOf(languageTestBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridLangTest.add(hBox, 5, listLanguageTests.indexOf(languageTestBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initTechnicalReq() {

        listTechnicalReq = vacancyTechnicalReqDAO.retrieveTechnicalRequirements(VACANCY_ID);
        if (listTechnicalReq.size() > 0) {
            addDynaTechnicalReqRows();
        } else {
            TechnicalSkillBEAN skillBEAN = new TechnicalSkillBEAN();
            listTechnicalReq.add(skillBEAN);
            addDynaTechnicalReqRows();
        }

    }

    /**
     *
     */
    public void addDynaTechnicalReqRows() {

        gridTechnicalReq.getChildren().clear();
        ObservValidateTechnicalReq.clear();
        for (TechnicalSkillBEAN technicalSkillBEAN : listTechnicalReq) {
            technicalSkillBEAN.setVacancyId(VACANCY_ID);

            ComboBox<String> cmbTechnology = new ComboBox(masterData.retrieveTechnicalSkills());
            cmbTechnology.setPromptText("Select Technology");
            ComboBoxJumpToChar.jumpToChar(cmbTechnology);

            ComboBox cmbKnowledgeLevel = new ComboBox(SearchConstants.getObsKnowledgeLevel());
            cmbKnowledgeLevel.setPromptText("Select Knowledge Level");
            ComboBoxJumpToChar.jumpToChar(cmbKnowledgeLevel);

            GridPane.setHgrow(cmbTechnology, Priority.NEVER);
            GridPane.setHgrow(cmbKnowledgeLevel, Priority.NEVER);

            cmbTechnology.setMaxWidth(Double.MAX_VALUE);
            cmbKnowledgeLevel.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationTechnicalReqSupport = new ValidationSupport();
            validationTechnicalReqSupport.registerValidator(cmbTechnology, Validator.createEmptyValidator("Technology Required"));
            validationTechnicalReqSupport.registerValidator(cmbKnowledgeLevel, Validator.createEmptyValidator("Knowledge Level Required"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            ObservValidateTechnicalReq.add(validationTechnicalReqSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationTechnicalReqSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationTechnicalReqSupport.isInvalid()) {
                        technicalSkillBEAN.setVacancyId(VACANCY_ID);
                        if (technicalSkillBEAN.getRowId() != null) {
                            vacancyTechnicalReqDAO.updateTechnicalRequirements(technicalSkillBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            technicalSkillBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = vacancyTechnicalReqDAO.insertTechnicalRequirements(technicalSkillBEAN);
                        }
                        TechnicalSkillBEAN emptyBEAN = new TechnicalSkillBEAN();
                        listTechnicalReq.add(emptyBEAN);
                        addDynaTechnicalReqRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridTechnicalReq);
//                    }
                    gridTechnicalReq.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (technicalSkillBEAN.getRowId() != null) {
                            vacancyTechnicalReqDAO.delteteTechnicalRequirements(technicalSkillBEAN.getRowId());
                        }
                        listTechnicalReq.remove(technicalSkillBEAN);
                        if (listTechnicalReq.size() > 0) {
                            addDynaTechnicalReqRows();
                        } else {
                            TechnicalSkillBEAN emptyBEAN = new TechnicalSkillBEAN();
                            listTechnicalReq.add(emptyBEAN);
                            addDynaTechnicalReqRows();
                        }
                        ObservValidateTechnicalReq.remove(validationTechnicalReqSupport);
                        gridTechnicalReq.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            Bindings.bindBidirectional(cmbTechnology.valueProperty(), technicalSkillBEAN.technologyProperty());
            Bindings.bindBidirectional(cmbKnowledgeLevel.valueProperty(), technicalSkillBEAN.knowledgeLevelProperty());

            gridTechnicalReq.add(new Label("Technology"), 0, listTechnicalReq.indexOf(technicalSkillBEAN) + 1);
            gridTechnicalReq.add(cmbTechnology, 1, listTechnicalReq.indexOf(technicalSkillBEAN) + 1);
            gridTechnicalReq.add(new Label("Knowledge Level"), 2, listTechnicalReq.indexOf(technicalSkillBEAN) + 1);
            gridTechnicalReq.add(cmbKnowledgeLevel, 3, listTechnicalReq.indexOf(technicalSkillBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridTechnicalReq.add(hBox, 4, listTechnicalReq.indexOf(technicalSkillBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initOtherSkillReq() {

        listOtherSkillReq = vacancyOtherSkillDAO.retrieveOtherSkills(VACANCY_ID);
        if (listOtherSkillReq.size() > 0) {
            addDynaOtherSkillReqRows();
        } else {
            OtherSkillBEAN skillBEAN = new OtherSkillBEAN();
            listOtherSkillReq.add(skillBEAN);
            addDynaOtherSkillReqRows();
        }
    }

    /**
     *
     */
    public void addDynaOtherSkillReqRows() {

        gridOtherSkill.getChildren().clear();
        ObservValidateOtherSkillReq.clear();
        for (OtherSkillBEAN otherlSkillBEAN : listOtherSkillReq) {
            otherlSkillBEAN.setVacancyId(VACANCY_ID);

            ComboBox<String> cmbOtherSkill = new ComboBox(masterData.retrieveOtherSkills());
            cmbOtherSkill.setPromptText("Select Skill");
            ComboBoxJumpToChar.jumpToChar(cmbOtherSkill);

            ComboBox cmbSkillType = new ComboBox(masterData.retrieveOtherSkillsType());
            cmbSkillType.setPromptText("Select Skill Type");
            ComboBoxJumpToChar.jumpToChar(cmbSkillType);

            GridPane.setHgrow(cmbOtherSkill, Priority.NEVER);
            GridPane.setHgrow(cmbSkillType, Priority.NEVER);

            cmbOtherSkill.setMaxWidth(Double.MAX_VALUE);
            cmbSkillType.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationOtherSkillSupport = new ValidationSupport();
            validationOtherSkillSupport.registerValidator(cmbOtherSkill, Validator.createEmptyValidator("Other Skill Required"));
            validationOtherSkillSupport.registerValidator(cmbSkillType, Validator.createEmptyValidator("Skill Type Required"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            ObservValidateOtherSkillReq.add(validationOtherSkillSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationOtherSkillSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationOtherSkillSupport.isInvalid()) {
                        otherlSkillBEAN.setVacancyId(VACANCY_ID);
                        if (otherlSkillBEAN.getRowId() != null) {
                            vacancyOtherSkillDAO.updateOtherSkills(otherlSkillBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            otherlSkillBEAN.setRowId("ot_" + UiiDGenerator.getUIID8());
                            boolean done = vacancyOtherSkillDAO.insertOtherSkills(otherlSkillBEAN);
                        }
                        OtherSkillBEAN emptyBEAN = new OtherSkillBEAN();
                        listOtherSkillReq.add(emptyBEAN);
                        addDynaOtherSkillReqRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridOtherSkill);
//                    }
                    gridOtherSkill.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (otherlSkillBEAN.getRowId() != null) {
                            vacancyOtherSkillDAO.deleteOtherSkills(otherlSkillBEAN.getRowId());
                        }
                        listOtherSkillReq.remove(otherlSkillBEAN);
                        if (listOtherSkillReq.size() > 0) {
                            addDynaOtherSkillReqRows();
                        } else {
                            OtherSkillBEAN emptyBEAN = new OtherSkillBEAN();
                            listOtherSkillReq.add(emptyBEAN);
                            addDynaOtherSkillReqRows();
                        }
                        ObservValidateOtherSkillReq.remove(validationOtherSkillSupport);
                        gridOtherSkill.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            Bindings.bindBidirectional(cmbOtherSkill.valueProperty(), otherlSkillBEAN.otherSkillProperty());
            Bindings.bindBidirectional(cmbSkillType.valueProperty(), otherlSkillBEAN.otherTypeProperty());

            gridOtherSkill.add(new Label("Skill"), 0, listOtherSkillReq.indexOf(otherlSkillBEAN));
            gridOtherSkill.add(cmbSkillType, 1, listOtherSkillReq.indexOf(otherlSkillBEAN));
            gridOtherSkill.add(new Label("Skill Type"), 2, listOtherSkillReq.indexOf(otherlSkillBEAN));
            gridOtherSkill.add(cmbOtherSkill, 3, listOtherSkillReq.indexOf(otherlSkillBEAN));

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridOtherSkill.add(hBox, 4, listOtherSkillReq.indexOf(otherlSkillBEAN));

        }

    }

    /**
     *
     */
    public void initDocumentReq() {

        listDocumentsReq = vacancyDocumentReqDAO.retrieveDocumentsReq(VACANCY_ID);
        if (listDocumentsReq.size() > 0) {
            addDynaDocumentsReqRows();
        } else {
            DocumentReqBEAN skillBEAN = new DocumentReqBEAN();
            listDocumentsReq.add(skillBEAN);
            addDynaDocumentsReqRows();
        }
    }

    /**
     *
     */
    public void addDynaDocumentsReqRows() {

        gridDocumentsReq.getChildren().clear();
        ObservValidateDocumentsReq.clear();
        for (DocumentReqBEAN documentReqBEAN : listDocumentsReq) {
            documentReqBEAN.setVacancyId(VACANCY_ID);

            ComboBox<DocumentPOJO> cmbDocumentName = new ComboBox();
            cmbDocumentName.setPromptText("Document Name");
            ComboBoxJumpToChar.jumpToChar(cmbDocumentName);
            cmbDocumentName.getItems().addAll(masterData.retreiveDocument());

            TextArea txtDocDescription = new TextArea();
            txtDocDescription.setPromptText("Description");

            GridPane.setHgrow(cmbDocumentName, Priority.NEVER);
            GridPane.setHgrow(txtDocDescription, Priority.NEVER);

            cmbDocumentName.setMaxWidth(Double.MAX_VALUE);
            txtDocDescription.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationDocumentReqSupport = new ValidationSupport();
            validationDocumentReqSupport.registerValidator(cmbDocumentName, Validator.createEmptyValidator("Document Name Required"));
            validationDocumentReqSupport.registerValidator(txtDocDescription, Validator.createEmptyValidator("Description Required"));
            ObservValidateDocumentsReq.add(validationDocumentReqSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationDocumentReqSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationDocumentReqSupport.isInvalid()) {
                        documentReqBEAN.setVacancyId(VACANCY_ID);
                        if (documentReqBEAN.getRowId() != null) {
                            vacancyDocumentReqDAO.updateDocumentsReq(documentReqBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            documentReqBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = vacancyDocumentReqDAO.insertDocumentsReq(documentReqBEAN);
                        }
                        DocumentReqBEAN emptyBEAN = new DocumentReqBEAN();
                        listDocumentsReq.add(emptyBEAN);
                        addDynaDocumentsReqRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridDocumentsReq);
//                    }
                    gridDocumentsReq.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (documentReqBEAN.getRowId() != null) {
                            vacancyDocumentReqDAO.deleteDocumentsReq(documentReqBEAN.getRowId());
                        }
                        listDocumentsReq.remove(documentReqBEAN);
                        if (listDocumentsReq.size() > 0) {
                            addDynaDocumentsReqRows();
                        } else {
                            DocumentReqBEAN emptyBEAN = new DocumentReqBEAN();
                            listDocumentsReq.add(emptyBEAN);
                            addDynaDocumentsReqRows();
                        }
                        ObservValidateDocumentsReq.remove(validationDocumentReqSupport);
                        gridDocumentsReq.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listDocumentsReq.indexOf(documentReqBEAN) == 0) {
                gridDocumentsReq.add(new Label("Document Name"), 0, 0);
                gridDocumentsReq.add(new Label("Description"), 1, 0);
            }
            StringConverter converter = new DefaultObjectConverter<DocumentPOJO>() {

                @Override
                public String toString(DocumentPOJO documentPOJO) {
                    if (documentPOJO != null) {
                        return "" + documentPOJO.getDocumentId();
                    } else {
                        return "";
                    }
                }
            };
            for (DocumentPOJO pojo : cmbDocumentName.getItems()) {
                if (pojo != null) {
                    String s = "" + pojo.getDocumentId();
                    if (s.equalsIgnoreCase(documentReqBEAN.getDocumentId())) {
                        cmbDocumentName.getSelectionModel().select(pojo);
                    }
                }
            }
            Bindings.bindBidirectional(documentReqBEAN.documentIdProperty(), cmbDocumentName.valueProperty(), converter);
            Bindings.bindBidirectional(txtDocDescription.textProperty(), documentReqBEAN.documentDescriptionProperty());

            gridDocumentsReq.add(cmbDocumentName, 0, listDocumentsReq.indexOf(documentReqBEAN) + 1);
            gridDocumentsReq.add(txtDocDescription, 1, listDocumentsReq.indexOf(documentReqBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridDocumentsReq.add(hBox, 2, listDocumentsReq.indexOf(documentReqBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initAccomodationReq() {
        listAccomodationReq = vacancyAccomodationDAO.retrieveAccomodation(VACANCY_ID);
        if (listAccomodationReq.size() > 0) {
            addDynaAccomodationReqRows();
        } else {
            AccomBEAN accomBEAN = new AccomBEAN();
            listAccomodationReq.add(accomBEAN);
            addDynaAccomodationReqRows();
        }
    }

    /**
     *
     */
    public void addDynaAccomodationReqRows() {

        gridAccommodation.getChildren().clear();
        ObservValidateAccomodationReq.clear();
        for (AccomBEAN accomodationReqBEAN : listAccomodationReq) {
            accomodationReqBEAN.setVacancyId(VACANCY_ID);

            ComboBox<String> cmbAccomodation = new ComboBox();
            cmbAccomodation.setPromptText("Select Accomodation");
            ComboBoxJumpToChar.jumpToChar(cmbAccomodation);
            cmbAccomodation.getItems().addAll(masterData.retrieveAccomodation());

            GridPane.setHgrow(cmbAccomodation, Priority.NEVER);

            cmbAccomodation.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationAccomodationReqSupport = new ValidationSupport();
            validationAccomodationReqSupport.registerValidator(cmbAccomodation, Validator.createEmptyValidator("Document Name Required"));
            ObservValidateAccomodationReq.add(validationAccomodationReqSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationAccomodationReqSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationAccomodationReqSupport.isInvalid()) {
                        accomodationReqBEAN.setVacancyId(VACANCY_ID);
                        if (accomodationReqBEAN.getRowId() != null) {
                            vacancyAccomodationDAO.updateAccomodation(accomodationReqBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            accomodationReqBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = vacancyAccomodationDAO.insertAccomodation(accomodationReqBEAN);
                        }
                        AccomBEAN emptyBEAN = new AccomBEAN();
                        listAccomodationReq.add(emptyBEAN);
                        addDynaAccomodationReqRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridAccommodation);
//                    }
                    gridAccommodation.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (accomodationReqBEAN.getRowId() != null) {
                            vacancyAccomodationDAO.deleteAccomodation(accomodationReqBEAN.getRowId());
                        }
                        listAccomodationReq.remove(accomodationReqBEAN);
                        if (listAccomodationReq.size() > 0) {
                            addDynaAccomodationReqRows();
                        } else {
                            AccomBEAN emptyBEAN = new AccomBEAN();
                            listAccomodationReq.add(emptyBEAN);
                            addDynaAccomodationReqRows();
                        }
                        ObservValidateAccomodationReq.remove(validationAccomodationReqSupport);
                        gridAccommodation.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listAccomodationReq.indexOf(accomodationReqBEAN) == 0) {
                gridAccommodation.add(new Label("Accomadation"), 0, 0);
            }
            
            Bindings.bindBidirectional(cmbAccomodation.valueProperty(), accomodationReqBEAN.accomodationProperty());

            gridAccommodation.add(cmbAccomodation, 0, listAccomodationReq.indexOf(accomodationReqBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAccommodation.add(hBox, 1, listAccomodationReq.indexOf(accomodationReqBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initExperienceReq() {

        listExperienceReq = vacancyExperienceReqDAO.retrieveExperience(VACANCY_ID);
        if (listExperienceReq.size() > 0) {
            addDynaExperienceReqRows();
        } else {
            ExperienceReqBEAN erbean = new ExperienceReqBEAN();
            listExperienceReq.add(erbean);
            addDynaExperienceReqRows();
        }
    }

    /**
     *
     */
    public void addDynaExperienceReqRows() {

        gridExperience.getChildren().clear();
        ObservValidateExperienceReq.clear();
        for (ExperienceReqBEAN experienceRequirementsBEAN : listExperienceReq) {
            experienceRequirementsBEAN.setVacancyId(VACANCY_ID);

            ComboBox<String> cmbProfession = new ComboBox();
            cmbProfession.setPromptText("Select Profession");
            ComboBoxJumpToChar.jumpToChar(cmbProfession);
            cmbProfession.getItems().addAll(RetrieveStaticMasterDAO.getProfession());

            ComboBox<String> cmbDuration = new ComboBox(RetrieveStaticMasterDAO.getDuration());
            cmbDuration.setPromptText("Select Duration");
            ComboBoxJumpToChar.jumpToChar(cmbDuration);

            GridPane.setHgrow(cmbProfession, Priority.NEVER);
            GridPane.setHgrow(cmbDuration, Priority.NEVER);

            cmbProfession.setMaxWidth(Double.MAX_VALUE);
            cmbDuration.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationExpReqSupport = new ValidationSupport();
            validationExpReqSupport.registerValidator(cmbProfession, Validator.createEmptyValidator("Profession Required"));
            validationExpReqSupport.registerValidator(cmbDuration, Validator.createEmptyValidator("Duration Required"));
          
            ObservValidateExperienceReq.add(validationExpReqSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationExpReqSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationExpReqSupport.isInvalid()) {
                        experienceRequirementsBEAN.setVacancyId(VACANCY_ID);
                        if (experienceRequirementsBEAN.getRowId() != null) {
                            vacancyExperienceReqDAO.updateExperience(experienceRequirementsBEAN);
                        } else if (VACANCY_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            experienceRequirementsBEAN.setRowId("ex_" + UiiDGenerator.getUIID8());
                            boolean done = vacancyExperienceReqDAO.insertExperience(experienceRequirementsBEAN);
                        }
                        ExperienceReqBEAN emptyBEAN = new ExperienceReqBEAN();
                        listExperienceReq.add(emptyBEAN);
                        addDynaExperienceReqRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridExperience);
//                    }
                    gridExperience.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (experienceRequirementsBEAN.getRowId() != null) {
                            vacancyExperienceReqDAO.deleteExperience(experienceRequirementsBEAN.getRowId());
                        }
                        listExperienceReq.remove(experienceRequirementsBEAN);
                        if (listExperienceReq.size() > 0) {
                            addDynaExperienceReqRows();
                        } else {
                            ExperienceReqBEAN emptyBEAN = new ExperienceReqBEAN();
                            listExperienceReq.add(emptyBEAN);
                            addDynaExperienceReqRows();
                        }
                        ObservValidateExperienceReq.remove(validationExpReqSupport);
                        gridExperience.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listExperienceReq.indexOf(experienceRequirementsBEAN) == 0) {
                gridExperience.add(new Label("Profession"), 0, 0);
                gridExperience.add(new Label("Duration"), 1, 0);
            }
            Bindings.bindBidirectional(cmbProfession.valueProperty(), experienceRequirementsBEAN.professionProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), experienceRequirementsBEAN.experinceDurationProperty());

            gridExperience.add(cmbProfession, 0, listExperienceReq.indexOf(experienceRequirementsBEAN) + 1);
            gridExperience.add(cmbDuration, 1, listExperienceReq.indexOf(experienceRequirementsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridExperience.add(hBox, 2, listExperienceReq.indexOf(experienceRequirementsBEAN) + 1);

        }

    }

    /**
     *
     */
    public void addAdditionalFeatures() {
        ComboBoxJumpToChar.jumpToChar(cmbAgeFrom);
        ComboBoxJumpToChar.jumpToChar(cmbAgeTo);
//        ComboBoxJumpToChar.jumpToChar(cmbEmployerName);
        ComboBoxJumpToChar.jumpToChar(cmbEngBoard);
        ComboBoxJumpToChar.jumpToChar(cmbEngDuration);
        ComboBoxJumpToChar.jumpToChar(cmbGender);
        ComboBoxJumpToChar.jumpToChar(cmbJobTitle);
        ComboBoxJumpToChar.jumpToChar(cmbMaritalStatus);
        ComboBoxJumpToChar.jumpToChar(cmbPlustTwo);
        ComboBoxJumpToChar.jumpToChar(cmbPlustTwoMedium);
        ComboBoxJumpToChar.jumpToChar(cmbSalaryCurrency);
        ComboBoxJumpToChar.jumpToChar(cmbSalaryFrom);
        ComboBoxJumpToChar.jumpToChar(cmbSalaryTo);
        ComboBoxJumpToChar.jumpToChar(cmbSslcBoard);
        ComboBoxJumpToChar.jumpToChar(cmbSslcMedium);

    }

    private void initializeMasterData() {
        List<String> ageList = RetrieveStaticMasterDAO.getAllages();
        ObservableList listAges = FXCollections.observableArrayList(ageList);
        cmbAgeFrom.getItems().addAll(listAges);
        cmbAgeTo.getItems().addAll(listAges);
        cmbJobTitle.getItems().addAll(masterData.retriveJobTitles());
        cmbGender.getItems().addAll(SearchConstants.getListGender());
        cmbMaritalStatus.getItems().addAll(SearchConstants.getListMaritalStatus());
        cmbSslcBoard.getItems().addAll(RetrieveStaticMasterDAO.getExamBoards());
        cmbPlustTwo.getItems().addAll(RetrieveStaticMasterDAO.getExamBoards());
        cmbEngBoard.getItems().addAll(RetrieveStaticMasterDAO.getExamBoards());
        cmbSslcMedium.getItems().addAll(RetrieveStaticMasterDAO.getLanguage());
        cmbPlustTwoMedium.getItems().addAll(RetrieveStaticMasterDAO.getLanguage());
        cmbEngDuration.getItems().addAll(RetrieveStaticMasterDAO.getDuration());
        cmbSalaryFrom.getItems().addAll(RetrieveStaticMasterDAO.getSalary());
        cmbSalaryTo.getItems().addAll(RetrieveStaticMasterDAO.getMaxSalary());
        cmbSalaryCurrency.getItems().addAll(RetrieveStaticMasterDAO.getCurrencies());
        /* ======================== Load All Existing Employer Informations ==================== */
        ObservableList listEmployer = employerDAO.retrieveEmployerBasics();
        ObservableList<EmployerBEAN> listAutoCompletion = FXCollections.observableArrayList();
        listEmployer.forEach((employerOriginalBEAN) -> {
            EmployerBEAN autoCompletionBEAN1 = (EmployerBEAN) employerOriginalBEAN;
            EmployerBEAN autoCompletionBEAN = new EmployerBEAN() {
                @Override
                public String toString() {
                    return autoCompletionBEAN1.getEmployer();
                }
            };
            BeanUtils.copyProperties(autoCompletionBEAN1, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        });
        cmbEmployerName.getItems().addAll(listAutoCompletion);
        if (EMPLOYER_ID != null && !EMPLOYER_ID.equalsIgnoreCase("")) {
            for (EmployerBEAN ebean : cmbEmployerName.getItems()) {
                if (ebean.getEmployerId().equalsIgnoreCase(EMPLOYER_ID)) {
                    cmbEmployerName.getSelectionModel().select(ebean);
                    cmbEmployerName.setDisable(true);
                }
            }
        }

    }

    private void bindVacancyBasics() {
        StringConverter converter = new DefaultObjectConverter<EmployerBEAN>() {

            @Override
            public String toString(EmployerBEAN empBEAN) {
                if (empBEAN != null) {
                    return empBEAN.getEmployerId();
                } else {
                    return "";
                }
            }

        };
        Bindings.bindBidirectional(employerBEAN.employerIdProperty(), cmbEmployerName.valueProperty(), converter);
        Bindings.bindBidirectional(cmbJobTitle.valueProperty(), employerBEAN.jobProperty());
        Bindings.bindBidirectional(txtVacancyCount.textProperty(), employerBEAN.noOfVacanciesProperty());
        Bindings.bindBidirectional(cmbGender.valueProperty(), employerBEAN.genderProperty());
        Bindings.bindBidirectional(cmbAgeFrom.valueProperty(), employerBEAN.ageFromProperty());
        Bindings.bindBidirectional(cmbAgeTo.valueProperty(), employerBEAN.ageToProperty());
        Bindings.bindBidirectional(cmbMaritalStatus.valueProperty(), employerBEAN.maritalStatusProperty());
        Bindings.bindBidirectional(cmbSslcBoard.valueProperty(), employerBEAN.sslcBoardProperty());
        Bindings.bindBidirectional(cmbSslcMedium.valueProperty(), employerBEAN.sslcMediumProperty());
        Bindings.bindBidirectional(cmbPlustTwo.valueProperty(), employerBEAN.plus2BoardProperty());
        Bindings.bindBidirectional(cmbPlustTwoMedium.valueProperty(), employerBEAN.plus2MediumProperty());
        Bindings.bindBidirectional(cmbSalaryCurrency.valueProperty(), employerBEAN.currencyProperty());
        Bindings.bindBidirectional(cmbSalaryFrom.valueProperty(), employerBEAN.salaryFromAmountProperty());
        Bindings.bindBidirectional(cmbSalaryTo.valueProperty(), employerBEAN.salaryToAmountProperty());
        Bindings.bindBidirectional(cmbEngBoard.valueProperty(), employerBEAN.engMediumBoardProperty());
        Bindings.bindBidirectional(cmbEngDuration.valueProperty(), employerBEAN.engMediumDurationProperty());
        Bindings.bindBidirectional(dpExpiryDate.valueProperty(), employerBEAN.expiryDateProperty());
    }

    private void createDecorations() {
        validationSupport.registerValidator(cmbEmployerName, Validator.createEmptyValidator("Employer Name Required"));
        validationSupport.registerValidator(cmbJobTitle, Validator.createEmptyValidator("Job Title Required"));
        validationSupport.registerValidator(txtVacancyCount, Validator.createEmptyValidator("Vacancy Count Required"));
        validationSupport.registerValidator(cmbGender, Validator.createEmptyValidator("Gender Required"));
        validationSupport.registerValidator(cmbAgeFrom, Validator.createEmptyValidator("Age From Required"));
        validationSupport.registerValidator(cmbAgeTo, Validator.createEmptyValidator("Age To Required"));
        validationSupport.registerValidator(cmbMaritalStatus, Validator.createEmptyValidator("Marital Status Required"));
        validationSupport.registerValidator(cmbSalaryCurrency, Validator.createEmptyValidator("Currency Required"));
        validationSupport.registerValidator(cmbSalaryFrom, Validator.createEmptyValidator("Salary From Required"));
        validationSupport.registerValidator(cmbSalaryTo, Validator.createEmptyValidator("Salary To Required"));
    }

    private void buttonListners() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isAccademicReqValid = false;
                boolean isExperienceReqValid = false;
                boolean isLanguageTestReqValid = false;
                boolean isTechnicalReqValid = false;
                boolean isOtherReqValid = false;
                boolean isDocumentReqValid = false;
                boolean isAccomodationReqValid = false;
                System.out.println("Test Info :: " + employerBEAN.toString());
                /* ======================== Validate Basic Info ==================== */
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }
                /* ======================== Validate Academic Requirement ==================== */
                for (ValidationSupport support : ObservValidateAcademicReq) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isAccademicReqValid = support.isInvalid();
                    }
                }
                /* ======================== validate Experience Requiremetn ==================== */
                for (ValidationSupport support : ObservValidateExperienceReq) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isExperienceReqValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Language Test  ==================== */
                for (ValidationSupport support : ObservValidateLanguageTests) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isLanguageTestReqValid = support.isInvalid();
                    }
                }
                /* ======================== Vallidate Technical Requirements ==================== */
                for (ValidationSupport support : ObservValidateTechnicalReq) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isTechnicalReqValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Other Skills ==================== */
                for (ValidationSupport support : ObservValidateOtherSkillReq) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isOtherReqValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Document Requirements ==================== */
                for (ValidationSupport support : ObservValidateDocumentsReq) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isDocumentReqValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Accomodation ==================== */
                for (ValidationSupport support : ObservValidateAccomodationReq) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isAccomodationReqValid = support.isInvalid();
                    }
                }
                /* ======================== Insert Basic Info ==================== */
                if (!validationSupport.isInvalid()) {
                    if (employerBEAN.getVacancyId() == null || employerBEAN.getVacancyId().equalsIgnoreCase("")) {
                        /* ======================== Generate Employer Id ==================== */
                        VACANCY_ID = "vy_" + UiiDGenerator.getUIID8();
                        employerBEAN.setVacancyId(VACANCY_ID);
                        boolean done = employerVacancyDAO.insertEmployerVacancy(employerBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Employer Vacancy Details Saved Successfully!")
                                    .text("Employer Vacancy Details Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Employer Vacancy Details Save Failed!")
                                    .text("Employer Vacancy Details Save Failed!")
                                    .showError();
                            employerBEAN.setVacancyId(null);
                        }
                    } else {
                        boolean done = employerVacancyDAO.updateEmployerVacancy(employerBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Updated Successfully!")
                                    .text("Employer Vacancy Details Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Updation Failed!")
                                    .text("Employer Vacancy Details Update Failed!")
                                    .showError();
                        }
                    }
                }
                /* ======================== 1. Save Academic Details ==================== */
                if (!isAccademicReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Academic Details are Valid Now");
                    for (CoursesBEAN requirementsBEAN : listAcademicReq) {
                        if (requirementsBEAN.getRowId() == null || requirementsBEAN.getRowId().equalsIgnoreCase("")) {
                            requirementsBEAN.setVacancyId(VACANCY_ID);
                            requirementsBEAN.setRowId("ac_" + UiiDGenerator.getUIID8());
                            vacancyAcademicReqDAO.insertAcademicRequirements(requirementsBEAN);
                        } else {
                            vacancyAcademicReqDAO.updateAcademicRequirements(requirementsBEAN);
                        }
                    }
                }
                /* ======================== 2. Save Experience ==================== */
                if (!isExperienceReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Experience Details are Valid Now");
                    for (ExperienceReqBEAN experienceReqBEAN : listExperienceReq) {
                        if (experienceReqBEAN.getRowId() == null || experienceReqBEAN.getRowId().equalsIgnoreCase("")) {
                            experienceReqBEAN.setVacancyId(VACANCY_ID);
                            experienceReqBEAN.setRowId("ex_" + UiiDGenerator.getUIID8());
                            vacancyExperienceReqDAO.insertExperience(experienceReqBEAN);
                        } else {
                            vacancyExperienceReqDAO.updateExperience(experienceReqBEAN);
                        }
                    }
                }
                /* ======================== 3. Save Language Test ==================== */
                if (!isLanguageTestReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Experience Details are Valid Now");
                    for (LanguageTestBEAN languageTestBEAN : listLanguageTests) {
                        if (languageTestBEAN.getRowId() == null || languageTestBEAN.getRowId().equalsIgnoreCase("")) {
                            languageTestBEAN.setVacancyId(VACANCY_ID);
                            languageTestBEAN.setRowId("ln_" + UiiDGenerator.getUIID8());
                            languageTestDAO.insertLanguageTest(languageTestBEAN);
                        } else {
                            languageTestDAO.updateLanguageTest(languageTestBEAN);
                        }
                    }
                }
                /* ======================== 4. Save Technical Requirements ==================== */
                if (!isTechnicalReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Experience Details are Valid Now");
                    for (TechnicalSkillBEAN technicalSkillBEAN : listTechnicalReq) {
                        if (technicalSkillBEAN.getRowId() == null || technicalSkillBEAN.getRowId().equalsIgnoreCase("")) {
                            technicalSkillBEAN.setVacancyId(VACANCY_ID);
                            technicalSkillBEAN.setRowId("ln_" + UiiDGenerator.getUIID8());
                            vacancyTechnicalReqDAO.insertTechnicalRequirements(technicalSkillBEAN);
                        } else {
                            vacancyTechnicalReqDAO.updateTechnicalRequirements(technicalSkillBEAN);
                        }
                    }
                }
                /* ======================== 5. Save OtherSkill Requirements ==================== */
                if (!isOtherReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Other Skills Details are Valid Now");
                    for (OtherSkillBEAN otherSkillBEAN : listOtherSkillReq) {
                        if (otherSkillBEAN.getRowId() == null || otherSkillBEAN.getRowId().equalsIgnoreCase("")) {
                            otherSkillBEAN.setVacancyId(VACANCY_ID);
                            otherSkillBEAN.setRowId("ln_" + UiiDGenerator.getUIID8());
                            vacancyOtherSkillDAO.insertOtherSkills(otherSkillBEAN);
                        } else {
                            vacancyOtherSkillDAO.updateOtherSkills(otherSkillBEAN);
                        }
                    }
                }
                /* ======================== 6. Save Document Details ==================== */
                if (!isDocumentReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Document Details are Valid Now");
                    for (DocumentReqBEAN documentReqBEAN : listDocumentsReq) {
                        if (documentReqBEAN.getRowId() == null || documentReqBEAN.getRowId().equalsIgnoreCase("")) {
                            documentReqBEAN.setVacancyId(VACANCY_ID);
                            documentReqBEAN.setRowId("ln_" + UiiDGenerator.getUIID8());
                            vacancyDocumentReqDAO.insertDocumentsReq(documentReqBEAN);
                        } else {
                            vacancyDocumentReqDAO.updateDocumentsReq(documentReqBEAN);
                        }
                    }
                }
                /* ======================== 7. Save Accomodation Requirements ==================== */
                if (!isAccomodationReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Accomodation Details are Valid Now");
                    for (AccomBEAN accomBEAN : listAccomodationReq) {
                        if (accomBEAN.getRowId() == null || accomBEAN.getRowId().equalsIgnoreCase("")) {
                            accomBEAN.setVacancyId(VACANCY_ID);
                            accomBEAN.setRowId("ln_" + UiiDGenerator.getUIID8());
                            vacancyAccomodationDAO.insertAccomodation(accomBEAN);
                        } else {
                            vacancyAccomodationDAO.updateAccomodation(accomBEAN);
                        }
                    }
                }
            }
        });
        /* ======================== Clear Button Action ==================== */
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EMPLOYER_ID = employerBEAN.getEmployerId();
                VACANCY_ID = null;
                employerBEAN = new EmployerBEAN();
                bindVacancyBasics();
                /* ======================== Dynamic Controls ==================== */
                initAcademicRequirements();
                initLanguageTestReq();
                initTechnicalReq();
                initOtherSkillReq();
                initDocumentReq();
                initAccomodationReq();
                initExperienceReq();
            }
        });
    }

}

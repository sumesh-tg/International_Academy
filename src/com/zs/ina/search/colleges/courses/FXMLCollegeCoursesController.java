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
package com.zs.ina.search.colleges.courses;

import com.zs.ina.admin.master.documents.dao.DocumentPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.TextAreaGrowShrink;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.search.colleges.campuses.dao.CampusDAO;
import com.zs.ina.search.colleges.campuses.dao.CoursesDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseAcademicReqDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseAdmissionTestDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseDocumentReqDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseExperienceReqDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseLanguageTestDAO;
import com.zs.ina.search.colleges.dao.CourseReqmtBasicDAO;
import com.zs.ina.search.colleges.dao.EduProviderBEAN;
import com.zs.ina.search.colleges.dao.EduProviderDAO;
import com.zs.ina.search.common.bean.AdmissionBEAN;
import com.zs.ina.search.common.bean.CampusBEAN;
import com.zs.ina.search.common.bean.CoursesBEAN;
import com.zs.ina.search.common.bean.DocumentReqBEAN;
import com.zs.ina.search.common.bean.ExperienceReqBEAN;
import com.zs.ina.search.common.bean.LanguageTestBEAN;
import com.zs.ina.search.master.common.GlobalSearchMasterData;
import com.zs.ina.search.master.common.SearchConstants;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLCollegeCoursesController implements Initializable {

    @FXML
    private ComboBox<EduProviderBEAN> cmbCollege;
    @FXML
    private ComboBox<EduProviderBEAN> cmbCourse;
    @FXML
    private ComboBox<String> cmbAgeFrom;
    @FXML
    private ComboBox<String> cmbAgeTo;
    @FXML
    private ComboBox<String> cmbSSLCBoard;
    @FXML
    private ComboBox<String> cmbSSLCMedium;
    @FXML
    private ComboBox<String> cmbPlusTwoMedium;
    @FXML
    private ComboBox<String> cmbPlusTwoBoard;
    @FXML
    private GridPane gridAcademicReqmts;
    @FXML
    private ComboBox<String> cmbEnglishMediumBoard;
    @FXML
    private ComboBox<String> cmbEnglishMediumDuration;
    @FXML
    private GridPane gridLanguageTest;
    @FXML
    private GridPane gridAdmissionTest;
    @FXML
    private ComboBox<String> cmbProfession;
    @FXML
    private GridPane gridFees;
    @FXML
    private ComboBox<String> cmbIntake;
    @FXML
    private TextArea txtarCareer;
    @FXML
    private TextArea txtarAdvantages;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private ComboBox<String> cmbCurrency;
    @FXML
    private ComboBox<String> cmbMaritalStatus;
    @FXML
    private GridPane gridExperience;
    @FXML
    private ComboBox<String> cmbApplicationFee;
    @FXML
    private ComboBox<String> cmbAnnualFee;
    @FXML
    private ComboBox<String> cmbSemesterFee;
    @FXML
    private ComboBox<String> cmbOtherFee;
    @FXML
    private GridPane gridDocuments;
    @FXML
    private VBox vboxCampuses;


    /* ======================== Coding ==================== */
    GlobalSearchMasterData masterData = new GlobalSearchMasterData();
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    EduProviderDAO eduProviderDAO = springAppContext.getBean(EduProviderDAO.class);
    CourseReqmtBasicDAO courseReqmtBasicDAO = springAppContext.getBean(CourseReqmtBasicDAO.class);
    CampusDAO campusDAO = springAppContext.getBean(CampusDAO.class);
    ObservableList<CampusBEAN> campusList = FXCollections.observableArrayList();
    ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
    ObservableList<String> courseCampusList = FXCollections.observableArrayList();
    EduProviderBEAN eduProviderBEAN = new EduProviderBEAN();
    CoursesBEAN coursesBEAN = new CoursesBEAN();
    CoursesDAO coursesDAO = springAppContext.getBean(CoursesDAO.class);

    String COURSE_ID = null;
    String COLLEGE_ID = null;

    /* ======================== Dynamic Control BEANS ==================== */
    CourseAcademicReqDAO courseAcademicReqDAO = springAppContext.getBean(CourseAcademicReqDAO.class);
    CourseLanguageTestDAO courseLanguageTestDAO = springAppContext.getBean(CourseLanguageTestDAO.class);
    CourseAdmissionTestDAO courseAdmissionTestDAO = springAppContext.getBean(CourseAdmissionTestDAO.class);
    CourseExperienceReqDAO courseExperienceReqDAO = springAppContext.getBean(CourseExperienceReqDAO.class);
    CourseDocumentReqDAO courseDocumentReqDAO = springAppContext.getBean(CourseDocumentReqDAO.class);

    /* ======================== Dyanamic Control Lists ==================== */
    ObservableList<CoursesBEAN> listAcademicReq = FXCollections.observableArrayList();
    ObservableList<LanguageTestBEAN> listLanguageTests = FXCollections.observableArrayList();
    ObservableList<AdmissionBEAN> listAdmissionTests = FXCollections.observableArrayList();
    ObservableList<ExperienceReqBEAN> listExperienceReq = FXCollections.observableArrayList();
    ObservableList<DocumentReqBEAN> listDocumentsReq = FXCollections.observableArrayList();

    /* ======================== Dynamic Control Validation Supports ==================== */
    ObservableList<ValidationSupport> ObservValidateAcademicReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateLanguageTests = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateAdmissionTests = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateExperienceReq = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateDocumentsReq = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(EduProviderBEAN eduProviderBEANs) {

        BeanUtils.copyProperties(eduProviderBEANs, eduProviderBEAN);
        if (eduProviderBEAN.getCollegeId() != null && eduProviderBEAN.getCourseId() != null) {
            COURSE_ID = eduProviderBEAN.getCourseId();
            COLLEGE_ID = eduProviderBEAN.getCollegeId();
        }

        initializeMasterData();
        bindCourseBasics();
        createDecorations();
        addButtonListners();
        addAdditionalFeatures();
        /* ======================== Auto Grow Shrink Text Area ==================== */
        TextAreaGrowShrink.makeGrow(txtarAdvantages);
        TextAreaGrowShrink.makeGrow(txtarCareer);
        /* ======================== Dynamic Controls ==================== */
        initAcademicRequirements();
        initLanguageTestReq();
        initAdmissionTestReq();
        initExperienceReq();
        initDocumentReq();

    }

    public void initializeMasterData() {
        List<String> ageList = RetrieveStaticMasterDAO.getAllages();
        ObservableList listAges = FXCollections.observableArrayList(ageList);
        cmbGender.getItems().addAll(SearchConstants.getListGender());
        cmbAgeFrom.getItems().addAll(listAges);
        cmbAgeTo.getItems().addAll(listAges);
        cmbMaritalStatus.getItems().addAll(SearchConstants.getListMaritalStatus());
        cmbSSLCBoard.getItems().addAll(RetrieveStaticMasterDAO.getExamBoards());
        cmbSSLCMedium.getItems().addAll(RetrieveStaticMasterDAO.getLanguage());
        cmbPlusTwoBoard.getItems().addAll(RetrieveStaticMasterDAO.getExamBoards());
        cmbPlusTwoMedium.getItems().addAll(RetrieveStaticMasterDAO.getLanguage());
        cmbEnglishMediumBoard.getItems().addAll(RetrieveStaticMasterDAO.getExamBoards());
        cmbEnglishMediumDuration.getItems().addAll(RetrieveStaticMasterDAO.getDuration());
        cmbCurrency.getItems().addAll(RetrieveStaticMasterDAO.getCurrencies());
        cmbIntake.getItems().addAll(masterData.retrieveIntakes());
        cmbApplicationFee.getItems().addAll(RetrieveStaticMasterDAO.getSalary());
        cmbAnnualFee.getItems().addAll(RetrieveStaticMasterDAO.getSalary());
        cmbSemesterFee.getItems().addAll(RetrieveStaticMasterDAO.getSalary());
        cmbOtherFee.getItems().addAll(RetrieveStaticMasterDAO.getSalary());

//        /* ======================== Load All Existing Employer Informations ==================== */
        ObservableList listColleges = eduProviderDAO.retrieveEduProviderBasics();
        ObservableList<EduProviderBEAN> listAutoCompletion = FXCollections.observableArrayList();
        listColleges.forEach((eduProviderBEANs) -> {
            EduProviderBEAN originalBEAN = (EduProviderBEAN) eduProviderBEANs;
            EduProviderBEAN autoCompletionBEAN = new EduProviderBEAN() {
                @Override
                public String toString() {
                    return originalBEAN.getCollege();
                }
            };
            BeanUtils.copyProperties(originalBEAN, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        });
        cmbCollege.getItems().addAll(listAutoCompletion);
        if (COLLEGE_ID != null && !COLLEGE_ID.equalsIgnoreCase("")) {
            for (EduProviderBEAN ebean : cmbCollege.getItems()) {
                if (ebean.getCollegeId().equalsIgnoreCase(COLLEGE_ID)) {
                    cmbCollege.getSelectionModel().select(ebean);
                    cmbCollege.setDisable(true);
                    ObservableList<EduProviderBEAN> coursesList = eduProviderDAO.retrieveEduProviderCourses(COLLEGE_ID);
                    if (coursesList.size() > 0) {
                        ObservableList<EduProviderBEAN> listAutoCompletions = FXCollections.observableArrayList();
                        coursesList.forEach((eduProviderBEANss) -> {
                            EduProviderBEAN originBEAN = (EduProviderBEAN) eduProviderBEANss;
                            EduProviderBEAN autoCompletionBEANs = new EduProviderBEAN() {
                                @Override
                                public String toString() {
                                    return originBEAN.getCourseLevel() + " " + originBEAN.getCourseField();
                                }
                            };
                            BeanUtils.copyProperties(originBEAN, autoCompletionBEANs);
                            listAutoCompletions.add(autoCompletionBEANs);
                        });
                        cmbCourse.getItems().addAll(listAutoCompletions);

                    }
                 
                }
            }
        }

        cmbCollege.valueProperty().addListener(new ChangeListener<EduProviderBEAN>() {
            @Override
            public void changed(ObservableValue<? extends EduProviderBEAN> observable, EduProviderBEAN oldValue, EduProviderBEAN newValue) {
                if (newValue != null) {
                    cmbCourse.getItems().clear();
                    ObservableList<EduProviderBEAN> coursesList = eduProviderDAO.retrieveEduProviderCourses(newValue.getCollegeId());
                    if (coursesList.size() > 0) {
                        ObservableList<EduProviderBEAN> listAutoCompletions = FXCollections.observableArrayList();
                        coursesList.forEach((eduProviderBEANss) -> {
                            EduProviderBEAN originBEAN = (EduProviderBEAN) eduProviderBEANss;
                            EduProviderBEAN autoCompletionBEANs = new EduProviderBEAN() {
                                @Override
                                public String toString() {
                                    return originBEAN.getCourseLevel() + " " + originBEAN.getCourseField();
                                }
                            };
                            BeanUtils.copyProperties(originBEAN, autoCompletionBEANs);
                            listAutoCompletions.add(autoCompletionBEANs);
                        });
                        cmbCourse.getItems().addAll(listAutoCompletions);

                    }
                }
            }
        });
        if (COURSE_ID != null && !COURSE_ID.equalsIgnoreCase("")&& COLLEGE_ID != null && !COLLEGE_ID.equalsIgnoreCase("") ) {
            for (EduProviderBEAN ebean : cmbCourse.getItems()) {
                if (ebean.getCourseId().equalsIgnoreCase(COURSE_ID)) {
                    cmbCourse.getSelectionModel().select(ebean);
                    cmbCourse.setDisable(true);
          
                }
            }
                           clearCampusCheckBoxes();
                         System.out.println("COLLEGE_ID inside COURSE_ID != null ==="+COLLEGE_ID);
                    campusList = campusDAO.retrieveCampuses(COLLEGE_ID);

                    for (CampusBEAN campusBEAN : campusList) {
                        if (campusBEAN != null) {
                            CheckBox chkCampusName = new CheckBox();
                            chkCampusName.setText(campusBEAN.getCampus());
                            if (eduProviderBEAN.getCampuses() != null) {
                                for (String campus : eduProviderBEAN.getCampuses().split(",")) {
                                    if (campus != null) {
                                        campus = campus.trim();
                                        System.out.println("campusBEAN.getRowId() inside COURSE_ID != null ===" + campusBEAN.getRowId());
                                        System.out.println("campusBEAN.getCAMPUS() inside COURSE_ID != null ===" + campus);
                                        if (campusBEAN.getRowId().equalsIgnoreCase(campus)) {
                                            System.out.println("EQUAL inside COURSE_ID != null");
                                            chkCampusName.setSelected(true);
                                            courseCampusList.add(campusBEAN.getRowId());

                                        }
                                    }

                                }
                            }

                            chkCampusName.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    if (newValue) {
                                        System.out.println("inside CHECK selection");
                                        courseCampusList.add(campusBEAN.getRowId());
                                    } else {
                                        courseCampusList.remove(campusBEAN.getRowId());
                                    }
                                }
                            });
                            checkList.add(chkCampusName);
                        }

                    }
                    vboxCampuses.getChildren().addAll(checkList); 
        }

        cmbCourse.valueProperty().addListener(new ChangeListener<EduProviderBEAN>() {
            @Override
            public void changed(ObservableValue<? extends EduProviderBEAN> observable, EduProviderBEAN oldValue, EduProviderBEAN newValue) {
                if (newValue != null) {

                    eduProviderBEAN = new EduProviderBEAN();
                    eduProviderBEAN.setCourseId(newValue.getCourseId());
                    eduProviderBEAN = courseReqmtBasicDAO.retrieveCourseReqmtBasics(newValue.getCourseId());
                    COURSE_ID = eduProviderBEAN.getCourseId();
                    System.out.println("eduProviderBEAN.getCAAAAAAA ===" + eduProviderBEAN.getCampuses());
                    System.out.println("eduProviderBEAN.COLLEGE_id  ===" + newValue.getCollegeId());
                    clearCampusCheckBoxes();
                    campusList = campusDAO.retrieveCampuses(newValue.getCollegeId());

                    for (CampusBEAN campusBEAN : campusList) {
                        if (campusBEAN != null) {
                            CheckBox chkCampusName = new CheckBox();
                            chkCampusName.setText(campusBEAN.getCampus());
                            if (eduProviderBEAN.getCampuses() != null) {
                                for (String campus : eduProviderBEAN.getCampuses().split(",")) {
                                    if (campus != null) {
                                        campus = campus.trim();
                                        System.out.println("campusBEAN.getRowId() ===" + campusBEAN.getRowId());
                                        System.out.println("campusBEAN.getCAMPUS() ===" + campus);
                                        if (campusBEAN.getRowId().equalsIgnoreCase(campus)) {
                                            System.out.println("EQUAL");
                                            chkCampusName.setSelected(true);
                                            courseCampusList.add(campusBEAN.getRowId());

                                        }
                                    }

                                }
                            }

                            chkCampusName.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    if (newValue) {
                                        System.out.println("inside CHECK selection");
                                        courseCampusList.add(campusBEAN.getRowId());
                                    } else {
                                        courseCampusList.remove(campusBEAN.getRowId());
                                    }
                                }
                            });
                            checkList.add(chkCampusName);
                        }

                    }
                    vboxCampuses.getChildren().addAll(checkList);

                    bindCourseBasics();
                    /* ======================== Auto Grow Shrink Text Area ==================== */
                    TextAreaGrowShrink.makeGrow(txtarAdvantages);
                    TextAreaGrowShrink.makeGrow(txtarCareer);
                    /* ======================== Dynamic Controls ==================== */
                    initAcademicRequirements();
                    initLanguageTestReq();
                    initAdmissionTestReq();
                    initExperienceReq();
                    initDocumentReq();

                }
            }

        });

    }

    public void clearCampusCheckBoxes() {
        campusList.clear();
        checkList.clear();
        courseCampusList.clear();
        vboxCampuses.getChildren().clear();

    }

    public void bindCourseBasics() {
        StringConverter converterCollege = new DefaultObjectConverter<EduProviderBEAN>() {

            @Override
            public String toString(EduProviderBEAN eduProviderBEAN) {
                if (eduProviderBEAN != null) {
                    return eduProviderBEAN.getCollegeId();
                } else {
                    return "";
                }
            }

        };
        Bindings.bindBidirectional(eduProviderBEAN.collegeIdProperty(), cmbCollege.valueProperty(), converterCollege);
        StringConverter converterCourse = new DefaultObjectConverter<EduProviderBEAN>() {

            @Override
            public String toString(EduProviderBEAN eduProviderBEAN) {
                if (eduProviderBEAN != null) {
                    return eduProviderBEAN.getCourseId();
                } else {
                    return "";
                }
            }
        };
        Bindings.bindBidirectional(eduProviderBEAN.courseIdProperty(), cmbCourse.valueProperty(), converterCourse);
        Bindings.bindBidirectional(cmbGender.valueProperty(), eduProviderBEAN.genderProperty());
        Bindings.bindBidirectional(cmbAgeFrom.valueProperty(), eduProviderBEAN.ageFromProperty());
        Bindings.bindBidirectional(cmbAgeTo.valueProperty(), eduProviderBEAN.ageToProperty());
        Bindings.bindBidirectional(cmbMaritalStatus.valueProperty(), eduProviderBEAN.maritalStatusProperty());
        Bindings.bindBidirectional(cmbSSLCBoard.valueProperty(), eduProviderBEAN.sslcBoardProperty());
        Bindings.bindBidirectional(cmbSSLCMedium.valueProperty(), eduProviderBEAN.sslcMediumProperty());
        Bindings.bindBidirectional(cmbPlusTwoBoard.valueProperty(), eduProviderBEAN.plus2BoardProperty());
        Bindings.bindBidirectional(cmbPlusTwoMedium.valueProperty(), eduProviderBEAN.plus2MediumProperty());
        Bindings.bindBidirectional(cmbEnglishMediumBoard.valueProperty(), eduProviderBEAN.engMediumBoradProperty());
        Bindings.bindBidirectional(cmbEnglishMediumDuration.valueProperty(), eduProviderBEAN.engMeduimDurationProperty());
        Bindings.bindBidirectional(cmbCurrency.valueProperty(), eduProviderBEAN.feeCurrencyProperty());
        Bindings.bindBidirectional(cmbApplicationFee.valueProperty(), eduProviderBEAN.applicationFeeProperty());
        Bindings.bindBidirectional(cmbAnnualFee.valueProperty(), eduProviderBEAN.annualFeeProperty());
        Bindings.bindBidirectional(cmbSemesterFee.valueProperty(), eduProviderBEAN.semesterFeeProperty());
        Bindings.bindBidirectional(cmbOtherFee.valueProperty(), eduProviderBEAN.otherFeeProperty());
        Bindings.bindBidirectional(cmbIntake.valueProperty(), eduProviderBEAN.intakeProperty());
        Bindings.bindBidirectional(txtarCareer.textProperty(), eduProviderBEAN.careerProperty());
        Bindings.bindBidirectional(txtarAdvantages.textProperty(), eduProviderBEAN.advantageProperty());

    }

    public void createDecorations() {
        validationSupport.registerValidator(cmbCollege, Validator.createEmptyValidator("College Name Required"));
        validationSupport.registerValidator(cmbGender, Validator.createEmptyValidator("Gender Required"));
        validationSupport.registerValidator(cmbAgeFrom, Validator.createEmptyValidator("Age From Required"));
        validationSupport.registerValidator(cmbAgeTo, Validator.createEmptyValidator("Age To Required"));
        validationSupport.registerValidator(cmbMaritalStatus, Validator.createEmptyValidator("Marital Status Required"));
//        validationSupport.registerValidator(cmbSSLCBoard, Validator.createEmptyValidator("SSLC Borad Required"));
//        validationSupport.registerValidator(cmbSSLCMedium, Validator.createEmptyValidator("SSLC Medium Required"));
//        validationSupport.registerValidator(cmbPlusTwoBoard, Validator.createEmptyValidator("Plus Two Board Required"));
//        validationSupport.registerValidator(cmbPlusTwoMedium, Validator.createEmptyValidator("Plus Two Medium Required"));
//        validationSupport.registerValidator(cmbEnglishMediumBoard, Validator.createEmptyValidator("English Medium Borad Required"));
//        validationSupport.registerValidator(cmbEnglishMediumDuration, Validator.createEmptyValidator("English Medium Duration Required"));
        validationSupport.registerValidator(cmbCurrency, Validator.createEmptyValidator("Currency Required"));
        validationSupport.registerValidator(cmbApplicationFee, Validator.createEmptyValidator("Application Fee Required"));
        validationSupport.registerValidator(cmbAnnualFee, Validator.createEmptyValidator("Annual Fee Required"));
        validationSupport.registerValidator(cmbSemesterFee, Validator.createEmptyValidator("Semester Fee Required"));
        validationSupport.registerValidator(cmbOtherFee, Validator.createEmptyValidator("Other Fee Required"));
        validationSupport.registerValidator(cmbIntake, Validator.createEmptyValidator("Intake Required"));

    }

    public void addButtonListners() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isAcademicReqValid = false;
                boolean isLanguageTestReqValid = false;
                boolean isAdmissionTestReqValid = false;
                boolean isExperienceReqValid = false;
                boolean isDocumentReqValid = false;
                System.out.println("Test Info :: " + eduProviderBEAN.toString());
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
                        isAcademicReqValid = support.isInvalid();
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
                /* ======================== Vallidate Admission Test Requirements ==================== */
                for (ValidationSupport support : ObservValidateAdmissionTests) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isAdmissionTestReqValid = support.isInvalid();
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

                /* ======================== Set Campus Ids ==================== */
//                    coursesBEAN.setCourseId(eduProviderBEAN.getCourseId());
//                    int dones = coursesDAO.updateCampusList(coursesBEAN);
//                    if (dones > 0) {
//                        Notifications.create()
//                                .title("Assigned Successfully!")
//                                .text("Campuses assigned to Course Successfully!")
//                                .showInformation();
//                    } else {
//                        Notifications.create()
//                                .title("Assigning Failed!")
//                                .text("Campus assignment Failed!")
//                                .showError();
//                    }
//                }

                /* ======================== Insert Basic Info ==================== */
                if (!validationSupport.isInvalid()) {
                    System.out.println("COURSE_ID COURSE_ID ======= " + eduProviderBEAN.getCourseId());
                    if (eduProviderBEAN.getRowId() == null || eduProviderBEAN.getRowId().equalsIgnoreCase("")) {

                        COURSE_ID = eduProviderBEAN.getCourseId();
                        COLLEGE_ID = eduProviderBEAN.getCollegeId();
                        if (courseCampusList != null) {
                            String campuseIdsInString = courseCampusList.toString();
                            String campuses = campuseIdsInString.replace("[", "");
                            campuses = campuses.replace("]", "");
                            campuses = campuses.trim();
                            System.out.println("courseCampusList inside SAVE =====" + campuses);
                            eduProviderBEAN.setCampuses(campuses);
                        }

                        eduProviderBEAN.setRowId("cr_" + UiiDGenerator.getUIID8());
                        boolean done = courseReqmtBasicDAO.insertCourseReqmtBasics(eduProviderBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Saved Successfully!")
                                    .text("Course Requirement Details Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Save Failed!")
                                    .text("Course Requirement Details Save Failed!")
                                    .showError();
                            eduProviderBEAN.setRowId(null);
                        }
                    } else {
                        if (courseCampusList != null) {
                            String campuseIdsInString = courseCampusList.toString();
                            String campuses = campuseIdsInString.replace("[", "");
                            campuses = campuses.replace("]", "");
                            eduProviderBEAN.setCampuses(campuses);
                        }

                        boolean done = courseReqmtBasicDAO.updateCourseReqmtBasics(eduProviderBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Updated Successfully!")
                                    .text("Course Requirement Details Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Updation Failed!")
                                    .text("Course Requirement Details Updation Failed!")
                                    .showError();
                        }
                    }
                    /* ======================== Generate Employer Id ==================== */

                }
                /* ======================== 1. Save Academic Details ==================== */
                if (!isAcademicReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Academic Details are Valid Now");
                    for (CoursesBEAN requirementsBEAN : listAcademicReq) {
                        if (requirementsBEAN.getRowId() == null || requirementsBEAN.getRowId().equalsIgnoreCase("")) {
                            requirementsBEAN.setCourseId(eduProviderBEAN.getCourseId());
                            requirementsBEAN.setRowId("cac_" + UiiDGenerator.getUIID8());
                            courseAcademicReqDAO.insertAcademicRequirements(requirementsBEAN);
                        } else {
                            courseAcademicReqDAO.updateAcademicRequirements(requirementsBEAN);
                        }
                    }
                }
                /* ======================== 2. Save Language Test ==================== */
                if (!isLanguageTestReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Language Details are Valid Now");
                    for (LanguageTestBEAN languageTestBEAN : listLanguageTests) {
                        if (languageTestBEAN.getRowId() == null || languageTestBEAN.getRowId().equalsIgnoreCase("")) {
                            languageTestBEAN.setCourseId(eduProviderBEAN.getCourseId());
                            languageTestBEAN.setRowId("cln_" + UiiDGenerator.getUIID8());
                            courseLanguageTestDAO.insertLanguageTest(languageTestBEAN);
                        } else {
                            courseLanguageTestDAO.updateLanguageTest(languageTestBEAN);
                        }
                    }
                }

                /* ======================== 3. Save Admission Test Requirements ==================== */
                if (!isAdmissionTestReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Admission Details are Valid Now");
                    for (AdmissionBEAN admissionBEAN : listAdmissionTests) {
                        if (admissionBEAN.getRowId() == null || admissionBEAN.getRowId().equalsIgnoreCase("")) {
                            admissionBEAN.setCourseId(eduProviderBEAN.getCourseId());
                            admissionBEAN.setRowId("cad_" + UiiDGenerator.getUIID8());
                            courseAdmissionTestDAO.insertAdmissionTest(admissionBEAN);
                        } else {
                            courseAdmissionTestDAO.updateAdmissionTest(admissionBEAN);
                        }
                    }
                }
                /* ======================== 4. Save Experience ==================== */
                if (!isExperienceReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Experience Details are Valid Now");
                    for (ExperienceReqBEAN experienceReqBEAN : listExperienceReq) {
                        if (experienceReqBEAN.getRowId() == null || experienceReqBEAN.getRowId().equalsIgnoreCase("")) {
                            experienceReqBEAN.setCourseId(eduProviderBEAN.getCourseId());
                            experienceReqBEAN.setRowId("cex_" + UiiDGenerator.getUIID8());
                            courseExperienceReqDAO.insertExperience(experienceReqBEAN);
                        } else {
                            courseExperienceReqDAO.updateExperience(experienceReqBEAN);
                        }
                    }
                }

                /* ======================== 5. Save Document Details ==================== */
                if (!isDocumentReqValid && !validationSupport.isInvalid()) {
                    System.out.println("Document Details are Valid Now");
                    for (DocumentReqBEAN documentReqBEAN : listDocumentsReq) {
                        if (documentReqBEAN.getRowId() == null || documentReqBEAN.getRowId().equalsIgnoreCase("")) {
                            documentReqBEAN.setCourseId(eduProviderBEAN.getCourseId());
                            documentReqBEAN.setRowId("cdo_" + UiiDGenerator.getUIID8());
                            courseDocumentReqDAO.insertDocumentsReq(documentReqBEAN);
                        } else {
                            courseDocumentReqDAO.updateDocumentsReq(documentReqBEAN);
                        }
                    }
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                COURSE_ID = null;
                //    COLLEGE_ID = null;
                eduProviderBEAN = new EduProviderBEAN();
                cmbCollege.getSelectionModel().clearSelection();
                cmbCourse.getSelectionModel().clearSelection();
                clearCampusCheckBoxes();
                bindCourseBasics();
                /* ======================== Auto Grow Shrink Text Area ==================== */
                TextAreaGrowShrink.makeGrow(txtarAdvantages);
                TextAreaGrowShrink.makeGrow(txtarCareer);
                /* ======================== Dynamic Controls ==================== */
                initAcademicRequirements();
                initLanguageTestReq();
                initAdmissionTestReq();
                initExperienceReq();
                initDocumentReq();

            }
        });
    }

    public void addAdditionalFeatures() {

        ComboBoxJumpToChar.jumpToChar(cmbGender);
        ComboBoxJumpToChar.jumpToChar(cmbAgeFrom);
        ComboBoxJumpToChar.jumpToChar(cmbAgeTo);
        ComboBoxJumpToChar.jumpToChar(cmbMaritalStatus);
        ComboBoxJumpToChar.jumpToChar(cmbSSLCBoard);
        ComboBoxJumpToChar.jumpToChar(cmbSSLCMedium);
        ComboBoxJumpToChar.jumpToChar(cmbPlusTwoBoard);
        ComboBoxJumpToChar.jumpToChar(cmbPlusTwoMedium);
        ComboBoxJumpToChar.jumpToChar(cmbEnglishMediumBoard);
        ComboBoxJumpToChar.jumpToChar(cmbEnglishMediumDuration);
        ComboBoxJumpToChar.jumpToChar(cmbIntake);

    }

    public void initAcademicRequirements() {
        listAcademicReq = courseAcademicReqDAO.retrieveAcademicRequirements(COURSE_ID);
        if (listAcademicReq.size() > 0) {
            addDynaAcademicRequirementRows();
        } else {
            CoursesBEAN coursesBEAN = new CoursesBEAN();
            listAcademicReq.add(coursesBEAN);
            addDynaAcademicRequirementRows();
        }

    }

    public void addDynaAcademicRequirementRows() {

        gridAcademicReqmts.getChildren().clear();
        ObservValidateAcademicReq.clear();
        for (CoursesBEAN academicRequirementsBEAN : listAcademicReq) {
            academicRequirementsBEAN.setCourseId(COURSE_ID);

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
            validationAcademicReqSupport.registerValidator(cmbDuration, Validator.createEmptyValidator("Program Field Duration Rquired"));
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
                        academicRequirementsBEAN.setCourseId(COURSE_ID);
                        if (academicRequirementsBEAN.getRowId() != null) {
                            courseAcademicReqDAO.updateAcademicRequirements(academicRequirementsBEAN);
                        } else if (COURSE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            academicRequirementsBEAN.setRowId("ac_" + UiiDGenerator.getUIID8());
                            boolean done = courseAcademicReqDAO.insertAcademicRequirements(academicRequirementsBEAN);
                        }
                        CoursesBEAN emptyBEAN = new CoursesBEAN();
                        listAcademicReq.add(emptyBEAN);
                        addDynaAcademicRequirementRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridAcademicReqmts);
//                    }
                    gridAcademicReqmts.requestFocus();
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
                            courseAcademicReqDAO.deleteAcademicRequirements(academicRequirementsBEAN.getRowId());
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
                        gridAcademicReqmts.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listAcademicReq.indexOf(academicRequirementsBEAN) == 0) {
                gridAcademicReqmts.add(new Label("Program Level"), 0, 0);
                gridAcademicReqmts.add(new Label("Program Field"), 1, 0);
                gridAcademicReqmts.add(new Label("Duration"), 2, 0);
            }
            Bindings.bindBidirectional(cmbProgramLevel.valueProperty(), academicRequirementsBEAN.levelProperty());
            Bindings.bindBidirectional(cmbProgramField.valueProperty(), academicRequirementsBEAN.fieldProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), academicRequirementsBEAN.durationProperty());

            gridAcademicReqmts.add(cmbProgramLevel, 0, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);
            gridAcademicReqmts.add(cmbProgramField, 1, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);
            gridAcademicReqmts.add(cmbDuration, 2, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAcademicReqmts.add(hBox, 3, listAcademicReq.indexOf(academicRequirementsBEAN) + 1);

        }

    }

    public void initLanguageTestReq() {
        listLanguageTests = courseLanguageTestDAO.retrieveLanguageTest(COURSE_ID);
        if (listLanguageTests.size() > 0) {
            addDynaLanguageTestRows();
        } else {
            LanguageTestBEAN languageTestBEAN = new LanguageTestBEAN();
            listLanguageTests.add(languageTestBEAN);
            addDynaLanguageTestRows();
        }

    }

    public void addDynaLanguageTestRows() {

        gridLanguageTest.getChildren().clear();
        ObservValidateLanguageTests.clear();
        for (LanguageTestBEAN languageTestBEAN : listLanguageTests) {
            languageTestBEAN.setCourseId(COURSE_ID);

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
                        languageTestBEAN.setCourseId(COURSE_ID);
                        if (languageTestBEAN.getRowId() != null) {
                            courseLanguageTestDAO.updateLanguageTest(languageTestBEAN);
                        } else if (COURSE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            languageTestBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = courseLanguageTestDAO.insertLanguageTest(languageTestBEAN);
                        }
                        LanguageTestBEAN emptyBEAN = new LanguageTestBEAN();
                        listLanguageTests.add(emptyBEAN);
                        addDynaLanguageTestRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridLangTest);
//                    }
                    gridLanguageTest.requestFocus();
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
                            courseLanguageTestDAO.deleteLanguageTest(languageTestBEAN.getRowId());
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
                        gridLanguageTest.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listLanguageTests.indexOf(languageTestBEAN) == 0) {
                gridLanguageTest.add(new Label("Language Test"), 0, 0);
                gridLanguageTest.add(new Label("Reading"), 1, 0);
                gridLanguageTest.add(new Label("Listening"), 2, 0);
                gridLanguageTest.add(new Label("Writing"), 3, 0);
                gridLanguageTest.add(new Label("Speaking"), 4, 0);
            }
            Bindings.bindBidirectional(cmbLangTest.valueProperty(), languageTestBEAN.languageTestProperty());
            Bindings.bindBidirectional(cmbReading.valueProperty(), languageTestBEAN.readingProperty());
            Bindings.bindBidirectional(cmbWriting.valueProperty(), languageTestBEAN.writingProperty());
            Bindings.bindBidirectional(cmbListening.valueProperty(), languageTestBEAN.listeningProperty());
            Bindings.bindBidirectional(cmbSpeaking.valueProperty(), languageTestBEAN.speakingProperty());

            gridLanguageTest.add(cmbLangTest, 0, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLanguageTest.add(cmbReading, 1, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLanguageTest.add(cmbListening, 2, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLanguageTest.add(cmbWriting, 3, listLanguageTests.indexOf(languageTestBEAN) + 1);
            gridLanguageTest.add(cmbSpeaking, 4, listLanguageTests.indexOf(languageTestBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridLanguageTest.add(hBox, 5, listLanguageTests.indexOf(languageTestBEAN) + 1);

        }

    }

    public void initAdmissionTestReq() {
        listAdmissionTests.clear();
        listAdmissionTests = courseAdmissionTestDAO.retrieveAdmissionTest(COURSE_ID);
        if (listAdmissionTests.size() > 0) {
            addDynaAdmissionTestRows();
        } else {
            AdmissionBEAN admissionBEAN = new AdmissionBEAN();
            listAdmissionTests.add(admissionBEAN);
            addDynaAdmissionTestRows();
        }

    }

    public void addDynaAdmissionTestRows() {

        gridAdmissionTest.getChildren().clear();
        ObservValidateAdmissionTests.clear();
        for (AdmissionBEAN admissionBEAN : listAdmissionTests) {
            admissionBEAN.setCourseId(COURSE_ID);

            ComboBox<String> cmbAdmissionTest = new ComboBox();
            cmbAdmissionTest.setPromptText("Admission Test");
            ComboBoxJumpToChar.jumpToChar(cmbAdmissionTest);
            ObservableList<String> listTests = FXCollections.observableArrayList(masterData.retrieveAdmissionTest());
            cmbAdmissionTest.getItems().addAll(listTests);

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

            GridPane.setHgrow(cmbAdmissionTest, Priority.NEVER);
            GridPane.setHgrow(cmbReading, Priority.NEVER);
            GridPane.setHgrow(cmbWriting, Priority.NEVER);
            GridPane.setHgrow(cmbListening, Priority.NEVER);
            GridPane.setHgrow(cmbSpeaking, Priority.NEVER);

            cmbAdmissionTest.setMaxWidth(Double.MAX_VALUE);
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
            ValidationSupport validationAdmissionTestSupport = new ValidationSupport();
            validationAdmissionTestSupport.registerValidator(cmbAdmissionTest, Validator.createEmptyValidator("Admission Test Required"));
            validationAdmissionTestSupport.registerValidator(cmbReading, Validator.createEmptyValidator("Reading Score Required"));
            validationAdmissionTestSupport.registerValidator(cmbWriting, Validator.createEmptyValidator("Writing Score Rquired"));
            validationAdmissionTestSupport.registerValidator(cmbListening, Validator.createEmptyValidator("Listening Score Rquired"));
            validationAdmissionTestSupport.registerValidator(cmbSpeaking, Validator.createEmptyValidator("Speaking Score Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            ObservValidateAdmissionTests.add(validationAdmissionTestSupport);
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationAdmissionTestSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationAdmissionTestSupport.isInvalid()) {
                        admissionBEAN.setCourseId(COURSE_ID);
                        if (admissionBEAN.getRowId() != null) {
                            courseAdmissionTestDAO.updateAdmissionTest(admissionBEAN);
                        } else if (COURSE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            admissionBEAN.setRowId("adm_" + UiiDGenerator.getUIID8());
                            boolean done = courseAdmissionTestDAO.insertAdmissionTest(admissionBEAN);
                        }
                        AdmissionBEAN emptyBEAN = new AdmissionBEAN();
                        listAdmissionTests.add(emptyBEAN);
                        addDynaAdmissionTestRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridLangTest);
//                    }
                    gridAdmissionTest.requestFocus();
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
                        if (admissionBEAN.getRowId() != null) {
                            courseAdmissionTestDAO.deleteAdmissionTest(admissionBEAN.getRowId());
                        }
                        listAdmissionTests.remove(admissionBEAN);
                        if (listAdmissionTests.size() > 0) {
                            addDynaAdmissionTestRows();
                        } else {
                            AdmissionBEAN emptyBEAN = new AdmissionBEAN();
                            listAdmissionTests.add(emptyBEAN);
                            addDynaAdmissionTestRows();
                        }
                        ObservValidateAdmissionTests.remove(validationAdmissionTestSupport);
                        gridAdmissionTest.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listAdmissionTests.indexOf(admissionBEAN) == 0) {
                gridAdmissionTest.add(new Label("Admission Test"), 0, 0);
                gridAdmissionTest.add(new Label("Reading"), 1, 0);
                gridAdmissionTest.add(new Label("Listening"), 2, 0);
                gridAdmissionTest.add(new Label("Writing"), 3, 0);
                gridAdmissionTest.add(new Label("Speaking"), 4, 0);
            }
            Bindings.bindBidirectional(cmbAdmissionTest.valueProperty(), admissionBEAN.admissionTestProperty());
            Bindings.bindBidirectional(cmbReading.valueProperty(), admissionBEAN.readingProperty());
            Bindings.bindBidirectional(cmbWriting.valueProperty(), admissionBEAN.writingProperty());
            Bindings.bindBidirectional(cmbListening.valueProperty(), admissionBEAN.listeningProperty());
            Bindings.bindBidirectional(cmbSpeaking.valueProperty(), admissionBEAN.speakingProperty());

            gridAdmissionTest.add(cmbAdmissionTest, 0, listAdmissionTests.indexOf(admissionBEAN) + 1);
            gridAdmissionTest.add(cmbReading, 1, listAdmissionTests.indexOf(admissionBEAN) + 1);
            gridAdmissionTest.add(cmbListening, 2, listAdmissionTests.indexOf(admissionBEAN) + 1);
            gridAdmissionTest.add(cmbWriting, 3, listAdmissionTests.indexOf(admissionBEAN) + 1);
            gridAdmissionTest.add(cmbSpeaking, 4, listAdmissionTests.indexOf(admissionBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAdmissionTest.add(hBox, 5, listAdmissionTests.indexOf(admissionBEAN) + 1);

        }

    }

    public void initExperienceReq() {
        listExperienceReq.clear();
        listExperienceReq = courseExperienceReqDAO.retrieveExperience(COURSE_ID);
        if (listExperienceReq.size() > 0) {
            addDynaExperienceReqRows();
        } else {
            ExperienceReqBEAN erbean = new ExperienceReqBEAN();
            listExperienceReq.add(erbean);
            addDynaExperienceReqRows();
        }
    }

    public void addDynaExperienceReqRows() {

        gridExperience.getChildren().clear();
        ObservValidateExperienceReq.clear();
        for (ExperienceReqBEAN experienceRequirementsBEAN : listExperienceReq) {
            experienceRequirementsBEAN.setCourseId(COURSE_ID);

            ComboBox<String> cmbProfession = new ComboBox(RetrieveStaticMasterDAO.getProfession());
            cmbProfession.setPromptText("Select Profession");
            ComboBoxJumpToChar.jumpToChar(cmbProfession);

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
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
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
                        experienceRequirementsBEAN.setVacancyId(COURSE_ID);
                        if (experienceRequirementsBEAN.getRowId() != null) {
                            courseExperienceReqDAO.updateExperience(experienceRequirementsBEAN);
                        } else if (COURSE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            experienceRequirementsBEAN.setRowId("ex_" + UiiDGenerator.getUIID8());
                            boolean done = courseExperienceReqDAO.insertExperience(experienceRequirementsBEAN);
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
                            courseExperienceReqDAO.deleteExperience(experienceRequirementsBEAN.getRowId());
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

    public void initDocumentReq() {
        listDocumentsReq.clear();
        listDocumentsReq = courseDocumentReqDAO.retrieveDocumentsReq(COURSE_ID);
        if (listDocumentsReq.size() > 0) {
            addDynaDocumentsReqRows();
        } else {
            DocumentReqBEAN documentReqBEAN = new DocumentReqBEAN();
            listDocumentsReq.add(documentReqBEAN);
            addDynaDocumentsReqRows();
        }
    }

    public void addDynaDocumentsReqRows() {

        gridDocuments.getChildren().clear();
        ObservValidateDocumentsReq.clear();
        for (DocumentReqBEAN documentReqBEAN : listDocumentsReq) {
            documentReqBEAN.setCourseId(COURSE_ID);

            ComboBox<DocumentPOJO> cmbDocumentName = new ComboBox();
            cmbDocumentName.setPromptText("Document Name");
            ComboBoxJumpToChar.jumpToChar(cmbDocumentName);
            cmbDocumentName.getItems().addAll(masterData.retreiveDocument());

            TextArea txtDocDescription = new TextArea();
            txtDocDescription.setPromptText("Description");
            TextAreaGrowShrink.makeGrow(txtDocDescription);

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
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
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
                        documentReqBEAN.setCourseId(COURSE_ID);
                        if (documentReqBEAN.getRowId() != null) {
                            courseDocumentReqDAO.updateDocumentsReq(documentReqBEAN);
                        } else if (COURSE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            documentReqBEAN.setRowId("dc_" + UiiDGenerator.getUIID8());
                            boolean done = courseDocumentReqDAO.insertDocumentsReq(documentReqBEAN);
                        }
                        DocumentReqBEAN emptyBEAN = new DocumentReqBEAN();
                        listDocumentsReq.add(emptyBEAN);
                        addDynaDocumentsReqRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridDocumentsReq);
//                    }
                    gridDocuments.requestFocus();
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
                            courseDocumentReqDAO.deleteDocumentsReq(documentReqBEAN.getRowId());
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
                        gridDocuments.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listDocumentsReq.indexOf(documentReqBEAN) == 0) {
                gridDocuments.add(new Label("Document Name"), 0, 0);
                gridDocuments.add(new Label("Description"), 1, 0);
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

            gridDocuments.add(cmbDocumentName, 0, listDocumentsReq.indexOf(documentReqBEAN) + 1);
            gridDocuments.add(txtDocDescription, 1, listDocumentsReq.indexOf(documentReqBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridDocuments.add(hBox, 2, listDocumentsReq.indexOf(documentReqBEAN) + 1);

        }

    }
    

}

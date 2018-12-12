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
package com.zs.ina.search.colleges;

import com.zs.ina.admin.master.documents.dao.DocumentPOJO;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.locations.dao.LocationsDAO;
import com.zs.ina.admin.master.locations.dao.LocationsIMPL;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.TextAreaGrowShrink;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.search.common.bean.AccomBEAN;
import com.zs.ina.search.colleges.accomodation.dao.AccomDAO;
import com.zs.ina.search.common.bean.CampusBEAN;
import com.zs.ina.search.colleges.campuses.dao.CampusDAO;
import com.zs.ina.search.common.bean.CoursesBEAN;
import com.zs.ina.search.colleges.campuses.dao.CoursesDAO;
import com.zs.ina.search.common.bean.ContactsBEAN;
import com.zs.ina.search.colleges.contacts.EduContactDAO;
import com.zs.ina.search.colleges.dao.EduProviderBEAN;
import com.zs.ina.search.colleges.dao.EduProviderDAO;
import com.zs.ina.search.common.bean.ProcessFeeBEAN;
import com.zs.ina.search.colleges.processfee.ProcessFeeDAO;
import com.zs.ina.search.colleges.visadocs.VisadocBEAN;
import com.zs.ina.search.colleges.visadocs.VisadocDAO;
import com.zs.ina.search.master.common.GlobalSearchMasterData;
import com.zs.ina.search.master.common.SearchConstants;
import com.zs.ina.search.master.university.dao.UniversityBEAN;
import com.zs.ina.search.master.university.dao.UniversityDAO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import jidefx.utils.converter.DefaultObjectConverter;
import org.apache.log4j.Logger;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.validation.Severity;
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
public class FXMLCollegeRegistrationController implements Initializable {

    @FXML
    private ComboBox<UniversityBEAN> cmbUniversity;
    @FXML
    private TextArea txtarDescription;
    @FXML
    private TextArea txtarRemarks;
    @FXML
    private TextArea txtarAddress;
    @FXML
    private TextField txtWebsite;
    @FXML
    private TextField txtCommission;
    @FXML
    private ComboBox<String> cmbCurrencyType;
    @FXML
    private ComboBox<String> cmbInstitutionType;
    @FXML
    private ComboBox<String> cmbAgencyStatus;
    @FXML
    private DatePicker dpContractFrom;
    @FXML
    private DatePicker dpContractTo;
    @FXML
    private TextField txtContractBy;
    @FXML
    private TextField txtRenewalBy;
    @FXML
    private GridPane gridOfferedCourses;

    @FXML
    private ComboBox<String> cmbFeeType;
    @FXML
    private TextField txtAmount;
    @FXML
    private GridPane gridAccomodation;
    @FXML
    private GridPane gridContacts;
    @FXML
    private GridPane gridCampuses;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtTieUpName;
    @FXML
    private GridPane gridProcessingFee;
    @FXML
    private GridPane gridVisaDocuments;
    @FXML
    private TextField txtCollegeName;
    @FXML
    private HBox hboxCollegeName;
    @FXML
    private ImageView imgLogoPreview;
    @FXML
    private Button btnUploadLogo;
    @FXML
    private Button btnClear;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    static Logger logger = Logger.getLogger(FXMLCollegeRegistrationController.class);
    EduProviderBEAN eduProviderBEAN = new EduProviderBEAN();
    GlobalSearchMasterData masterData = new GlobalSearchMasterData();
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    EduProviderDAO eduProviderDAO = springAppContext.getBean(EduProviderDAO.class);
    CoursesDAO coursesDAO = springAppContext.getBean(CoursesDAO.class);
    ProcessFeeDAO processFeeDAO = springAppContext.getBean(ProcessFeeDAO.class);
    VisadocDAO visadocDAO = springAppContext.getBean(VisadocDAO.class);
    AccomDAO accomDAO = springAppContext.getBean(AccomDAO.class);
    EduContactDAO eduContactDAO = springAppContext.getBean(EduContactDAO.class);
    CampusDAO campusesDAO = springAppContext.getBean(CampusDAO.class);
    UniversityDAO universityDAO = springAppContext.getBean(UniversityDAO.class);
    CountryDAO countryDAO = new CountryIMPL();
    LocationsDAO locationsDAO = new LocationsIMPL();

    String COLLEGE_ID = null;
    long PHOTO_MAX_SIZE = 1024 * 1024;
    List<String> programFields = null;

    /* ======================== Dynamic Control Lists ==================== */
    ObservableList<CoursesBEAN> listCourses = FXCollections.observableArrayList();
    ObservableList<ProcessFeeBEAN> listProcessFees = FXCollections.observableArrayList();
    ObservableList<VisadocBEAN> listVisadocs = FXCollections.observableArrayList();
    ObservableList<AccomBEAN> listAccomodations = FXCollections.observableArrayList();
    ObservableList<ContactsBEAN> listEduContacts = FXCollections.observableArrayList();
    ObservableList<CampusBEAN> listCampuses = FXCollections.observableArrayList();

    /* ======================== Dynamic Controls Validation Support ==================== */
    ObservableList<ValidationSupport> observValidateCourses = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateProcessingFee = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateVisaDocs = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateAccomodations = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateEduContacts = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateCampuses = FXCollections.observableArrayList();
    @FXML
    private ComboBox<?> cmbCurrency;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeMasterData();
        autoCompletion();
        bindEducationalProviderBasics();
        addButtonListners();
        addAdditionalFeatures();

        /* ======================== Dynamic Controls ==================== */
        initDynaCoursesOffered();
        initDynaProcessingFees();
        initDynaVisaDocuments();
        initDynaAccomodations();
        initDynaContactDetails();
        initDynaCampusesUnderEduProvider();

        /* ======================== Validation ==================== */
        makeMandaoryDecorations();
        validationListners();

    }

    public void initDynaCoursesOffered() {
        listCourses = coursesDAO.retrieveCourses(COLLEGE_ID);
        if (listCourses.size() > 0) {
            addDynaCoursesOffered();
        } else {
            CoursesBEAN coursesBEAN = new CoursesBEAN();
            listCourses.add(coursesBEAN);
            addDynaCoursesOffered();
        }
    }

    public void addDynaCoursesOffered() {
        gridOfferedCourses.getChildren().clear();
        observValidateCourses.clear();
        for (CoursesBEAN coursesBEAN : listCourses) {
            coursesBEAN.setCollegeId(COLLEGE_ID);

            ComboBox cmbLevel = new ComboBox();
            cmbLevel.setPromptText("Level");
            ComboBoxJumpToChar.jumpToChar(cmbLevel);

            ComboBox cmbField = new ComboBox();
            cmbField.setPromptText("Field");
            ComboBoxJumpToChar.jumpToChar(cmbField);

            ComboBox cmbDuration = new ComboBox(masterData.retrieveSubClass());
            cmbDuration.setPromptText("Duration");
            ComboBoxJumpToChar.jumpToChar(cmbDuration);

            GridPane.setHgrow(cmbDuration, Priority.NEVER);
            GridPane.setHgrow(cmbField, Priority.NEVER);
            GridPane.setHgrow(cmbLevel, Priority.NEVER);

            cmbLevel.setMaxWidth(Double.MAX_VALUE);
            cmbField.setMaxWidth(Double.MAX_VALUE);
            cmbDuration.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Populate combos dynamically ==================== */
            cmbLevel.getItems().addAll(RetrieveStaticMasterDAO.getProgramLevels());
            cmbLevel.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        cmbField.getItems().addAll(RetrieveStaticMasterDAO.getProgramFields(newValue.toString()));
                    }
                }
            });

//            if (programFields.isEmpty()) {
//
//            } else {
//                cmbField.getItems().addAll(programFields);
//
//            }
            cmbDuration.getItems().addAll(RetrieveStaticMasterDAO.getDuration());

            /* ======================== Validation Support ==================== */
            ValidationSupport validationCourseSupport = new ValidationSupport();
            validationCourseSupport.registerValidator(cmbLevel, Validator.createEmptyValidator("Level required"));
            validationCourseSupport.registerValidator(cmbField, Validator.createEmptyValidator("Field required"));
            validationCourseSupport.registerValidator(cmbDuration, Validator.createEmptyValidator("Course Duration required"));
            observValidateCourses.add(validationCourseSupport);
            coursesBEAN.setUniversityId(eduProviderBEAN.getUniversityId());
            System.out.println("UNIVERSITY ====== " + coursesBEAN.getUniversityId());
            /* ======================== Button Events ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationCourseSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationCourseSupport.isInvalid()) {
                        coursesBEAN.setCollegeId(COLLEGE_ID);
                        if (coursesBEAN.getCourseId() != null) {
                            System.out.println("UNIVERSITY updation ====== " + coursesBEAN.getUniversityId());
                            coursesDAO.updateCourses(coursesBEAN);
                        } else if (COLLEGE_ID != null) {
                            coursesBEAN.setCourseId("cou_" + UiiDGenerator.getUIID8());
                            System.out.println("UNIVERSITY insertion ====== " + coursesBEAN.getUniversityId());
                            int done = coursesDAO.insertCourses(coursesBEAN);
                        }
                        CoursesBEAN emptyBEAN = new CoursesBEAN();
                        listCourses.add(emptyBEAN);
                        addDynaCoursesOffered();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridFees);
//                    }
                    gridOfferedCourses.requestFocus();
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
                        if (coursesBEAN.getCourseId() != null) {
                            coursesDAO.deleteCourse(coursesBEAN.getCourseId());
                        }
                        listCourses.remove(coursesBEAN);
                        if (listCourses.size() > 0) {
                            addDynaCoursesOffered();
                        } else {
                            CoursesBEAN emptyBEAN = new CoursesBEAN();
                            listCourses.add(emptyBEAN);
                            addDynaCoursesOffered();
                        }
                        gridOfferedCourses.requestFocus();
                        ObservValidateProcessingFee.remove(validationCourseSupport);
                    }
                }
            });

            if (listCourses.indexOf(coursesBEAN) == 0) {
                gridOfferedCourses.add(new Label("Level"), 0, 0);
                gridOfferedCourses.add(new Label("Field"), 1, 0);
                gridOfferedCourses.add(new Label("Duration"), 2, 0);
            }

            /* ======================== Bind With Bean ==================== */
            Bindings.bindBidirectional(cmbLevel.valueProperty(), coursesBEAN.levelProperty());
            Bindings.bindBidirectional(cmbField.valueProperty(), coursesBEAN.fieldProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), coursesBEAN.durationProperty());
            /* ======================== Add To Grid ==================== */
            gridOfferedCourses.add(cmbLevel, 0, listCourses.indexOf(coursesBEAN) + 1);
            gridOfferedCourses.add(cmbField, 1, listCourses.indexOf(coursesBEAN) + 1);
            gridOfferedCourses.add(cmbDuration, 2, listCourses.indexOf(coursesBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridOfferedCourses.add(hBox, 3, listCourses.indexOf(coursesBEAN) + 1);
        }
    }

    public void initDynaProcessingFees() {
        listProcessFees = processFeeDAO.retrieveProcessFee(COLLEGE_ID);
        if (listProcessFees.size() > 0) {
            addDynaProcessingFee();
        } else {
            ProcessFeeBEAN feeBEAN = new ProcessFeeBEAN();
            listProcessFees.add(feeBEAN);
            addDynaProcessingFee();
        }
    }

    public void addDynaProcessingFee() {
        gridProcessingFee.getChildren().clear();
        ObservValidateProcessingFee.clear();
        for (ProcessFeeBEAN processFeeBEAN : listProcessFees) {
            processFeeBEAN.setCollegeId(COLLEGE_ID);

            ComboBox cmbFeeType = new ComboBox();
            cmbFeeType.setPromptText("Fee Type");
            ComboBoxJumpToChar.jumpToChar(cmbFeeType);

            ComboBox cmbCurrency = new ComboBox();
            cmbCurrency.setPromptText("Currency");
            ComboBoxJumpToChar.jumpToChar(cmbCurrency);

            TextField txtAmount = new TextField();
            txtAmount.setPromptText("Amount");

            GridPane.setHgrow(txtAmount, Priority.NEVER);
            GridPane.setHgrow(cmbFeeType, Priority.NEVER);
            GridPane.setHgrow(cmbCurrency, Priority.NEVER);

            txtAmount.setMaxWidth(Double.MAX_VALUE);
            cmbFeeType.setMaxWidth(Double.MAX_VALUE);
            cmbCurrency.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Populate combo boxes currency and fee type ==================== */
            cmbFeeType.getItems().addAll(masterData.retrieveFeeType());
            cmbCurrency.getItems().addAll(RetrieveStaticMasterDAO.getCurrencies());

            /* ======================== Validation Support ==================== */
            ValidationSupport validationFeeSupport = new ValidationSupport();
            validationFeeSupport.registerValidator(cmbFeeType, Validator.createEmptyValidator("Fee Type required"));
            validationFeeSupport.registerValidator(cmbCurrency, Validator.createEmptyValidator("Currency required"));
            validationFeeSupport.registerValidator(txtAmount, Validator.createEmptyValidator("Amount required"));
            ObservValidateProcessingFee.add(validationFeeSupport);
            txtAmount.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(processFeeBEAN.amountProperty(), txtAmount.textProperty());
                            txtAmount.setText(oldValue);
                            Bindings.bindBidirectional(processFeeBEAN.amountProperty(), txtAmount.textProperty());
                        } else {
                            Bindings.unbindBidirectional(processFeeBEAN.amountProperty(), txtAmount.textProperty());
                            txtAmount.setText(newValue);
                            Bindings.bindBidirectional(processFeeBEAN.amountProperty(), txtAmount.textProperty());
                        }
                    }
                }
            });
            /* ======================== Button Events ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationFeeSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationFeeSupport.isInvalid()) {
                        processFeeBEAN.setCollegeId(COLLEGE_ID);
                        if (processFeeBEAN.getRowId() != null) {
                            processFeeDAO.updateProcessFee(processFeeBEAN);
                        } else if (COLLEGE_ID != null) {
                            processFeeBEAN.setRowId("fe_" + UiiDGenerator.getUIID8());
                            int done = processFeeDAO.insertProcessFee(processFeeBEAN);
                        }
                        ProcessFeeBEAN emptyBEAN = new ProcessFeeBEAN();
                        listProcessFees.add(emptyBEAN);
                        addDynaProcessingFee();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridFees);
//                    }
                    gridProcessingFee.requestFocus();
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
                        if (processFeeBEAN.getRowId() != null) {
                            processFeeDAO.deleteCampuses(processFeeBEAN.getRowId());
                        }
                        listProcessFees.remove(processFeeBEAN);
                        if (listProcessFees.size() > 0) {
                            addDynaProcessingFee();
                        } else {
                            ProcessFeeBEAN emptyBEAN = new ProcessFeeBEAN();
                            listProcessFees.add(emptyBEAN);
                            addDynaProcessingFee();
                        }
                        gridProcessingFee.requestFocus();
                        ObservValidateProcessingFee.remove(validationFeeSupport);
                    }
                }
            });

            if (listProcessFees.indexOf(processFeeBEAN) == 0) {
                gridProcessingFee.add(new Label("Fee Type"), 0, 0);
                gridProcessingFee.add(new Label("Currency"), 1, 0);
                gridProcessingFee.add(new Label("Amount"), 2, 0);
            }

            /* ======================== Bind With Bean ==================== */
            Bindings.bindBidirectional(cmbFeeType.valueProperty(), processFeeBEAN.feeTypeProperty());
            Bindings.bindBidirectional(cmbCurrency.valueProperty(), processFeeBEAN.currencyProperty());
            Bindings.bindBidirectional(txtAmount.textProperty(), processFeeBEAN.amountProperty());
            /* ======================== Add To Grid ==================== */
            gridProcessingFee.add(cmbFeeType, 0, listProcessFees.indexOf(processFeeBEAN) + 1);
            gridProcessingFee.add(cmbCurrency, 1, listProcessFees.indexOf(processFeeBEAN) + 1);
            gridProcessingFee.add(txtAmount, 2, listProcessFees.indexOf(processFeeBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridProcessingFee.add(hBox, 3, listProcessFees.indexOf(processFeeBEAN) + 1);
        }
    }

    public void initDynaVisaDocuments() {
        listVisadocs = visadocDAO.retrieveVisaDocuments(COLLEGE_ID);
        if (listVisadocs.size() > 0) {
            addDynaVisaDocuments();
        } else {
            VisadocBEAN visadocBEAN = new VisadocBEAN();
            listVisadocs.add(visadocBEAN);
            addDynaVisaDocuments();
        }
    }

    public void addDynaVisaDocuments() {
        gridVisaDocuments.getChildren().clear();
        observValidateVisaDocs.clear();
        for (VisadocBEAN visadocBEAN : listVisadocs) {
            visadocBEAN.setCollegeId(COLLEGE_ID);

            ComboBox<DocumentPOJO> cmbDocument = new ComboBox();
            cmbDocument.setPromptText("Document Name");
            ComboBoxJumpToChar.jumpToChar(cmbDocument);
            cmbDocument.getItems().addAll(masterData.retreiveDocument());

            TextArea txtDescription = new TextArea();
            txtDescription.setPromptText("Description");
            TextAreaGrowShrink.makeGrow(txtDescription);

            GridPane.setHgrow(cmbDocument, Priority.NEVER);
            GridPane.setHgrow(txtDescription, Priority.NEVER);

            cmbDocument.setMaxWidth(Double.MAX_VALUE);
            txtDescription.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");


            /* ======================== Validation Support ==================== */
            ValidationSupport validationVisaSupport = new ValidationSupport();
            validationVisaSupport.registerValidator(cmbDocument, Validator.createEmptyValidator("Document required"));
            validationVisaSupport.registerValidator(txtDescription, Validator.createEmptyValidator("Description required"));
            observValidateVisaDocs.add(validationVisaSupport);

            /* ======================== Button Events ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationVisaSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationVisaSupport.isInvalid()) {
                        visadocBEAN.setCollegeId(COLLEGE_ID);
                        if (visadocBEAN.getRowId() != null) {
                            visadocDAO.updateVisaDocuments(visadocBEAN);
                        } else if (COLLEGE_ID != null) {
                            visadocBEAN.setRowId("vi_" + UiiDGenerator.getUIID8());
                            int done = visadocDAO.insertVisaDocuments(visadocBEAN);
                        }
                        VisadocBEAN emptyBEAN = new VisadocBEAN();
                        listVisadocs.add(emptyBEAN);
                        addDynaVisaDocuments();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridFees);
//                    }
                    gridVisaDocuments.requestFocus();
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
                        if (visadocBEAN.getRowId() != null) {
                            visadocDAO.deleteVisaDocument(visadocBEAN.getRowId());
                        }
                        listVisadocs.remove(visadocBEAN);
                        if (listVisadocs.size() > 0) {
                            addDynaVisaDocuments();
                        } else {
                            VisadocBEAN emptyBEAN = new VisadocBEAN();
                            listVisadocs.add(emptyBEAN);
                            addDynaVisaDocuments();
                        }
                        gridVisaDocuments.requestFocus();
                        observValidateVisaDocs.remove(validationVisaSupport);
                    }
                }
            });

            if (listVisadocs.indexOf(visadocBEAN) == 0) {
                gridVisaDocuments.add(new Label("Document Name"), 0, 0);
                gridVisaDocuments.add(new Label("Description"), 1, 0);
            }

            /* ======================== Bind With Bean ==================== */
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

            for (DocumentPOJO documentPOJO : cmbDocument.getItems()) {
                if (documentPOJO != null) {
                    String docId = documentPOJO.getDocumentId() + "";
                    if (docId.equalsIgnoreCase(visadocBEAN.getVisadocId())) {
                        cmbDocument.getSelectionModel().select(documentPOJO);
                    }
                }
            }

            Bindings.bindBidirectional(visadocBEAN.visadocIdProperty(), cmbDocument.valueProperty(), converter);
            Bindings.bindBidirectional(txtDescription.textProperty(), visadocBEAN.descriptionProperty());
            /* ======================== Add To Grid ==================== */
            gridVisaDocuments.add(cmbDocument, 0, listVisadocs.indexOf(visadocBEAN) + 1);
            gridVisaDocuments.add(txtDescription, 1, listVisadocs.indexOf(visadocBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridVisaDocuments.add(hBox, 2, listVisadocs.indexOf(visadocBEAN) + 1);
        }
    }

    public void initDynaAccomodations() {
        listAccomodations = accomDAO.retrieveAccomodation(COLLEGE_ID);
        if (listAccomodations.size() > 0) {
            addDynaAccomodation();
        } else {
            AccomBEAN accomBEAN = new AccomBEAN();
            listAccomodations.add(accomBEAN);
            addDynaAccomodation();
        }
    }

    public void addDynaAccomodation() {
        gridAccomodation.getChildren().clear();
        observValidateAccomodations.clear();
        for (AccomBEAN accomBEAN : listAccomodations) {
            accomBEAN.setCollegeId(COLLEGE_ID);

            ComboBox cmbAccomodation = new ComboBox(masterData.retrieveAccomodation());
            cmbAccomodation.setPromptText("Accomodation");
            ComboBoxJumpToChar.jumpToChar(cmbAccomodation);

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
            ValidationSupport validationAccomodationSupport = new ValidationSupport();
            validationAccomodationSupport.registerValidator(cmbAccomodation, Validator.createEmptyValidator("Accomodation required"));
            observValidateAccomodations.add(validationAccomodationSupport);

            /* ======================== Button Events ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationAccomodationSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationAccomodationSupport.isInvalid()) {
                        accomBEAN.setRowId(COLLEGE_ID);
                        if (accomBEAN.getRowId() != null) {
                            accomDAO.updateAccomodation(accomBEAN);
                        } else if (COLLEGE_ID != null) {
                            accomBEAN.setRowId("acc_" + UiiDGenerator.getUIID8());
                            int done = accomDAO.insertAccomodation(accomBEAN);
                        }
                        AccomBEAN emptyBEAN = new AccomBEAN();
                        listAccomodations.add(emptyBEAN);
                        addDynaAccomodation();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridFees);
//                    }
                    gridAccomodation.requestFocus();
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
                        if (accomBEAN.getRowId() != null) {
                            accomDAO.deleteAccomodation(accomBEAN.getRowId());
                        }
                        listAccomodations.remove(accomBEAN);
                        if (listAccomodations.size() > 0) {
                            addDynaAccomodation();
                        } else {
                            AccomBEAN emptyBEAN = new AccomBEAN();
                            listAccomodations.add(emptyBEAN);
                            addDynaAccomodation();
                        }
                        gridAccomodation.requestFocus();
                        ObservValidateProcessingFee.remove(validationAccomodationSupport);
                    }
                }
            });

            if (listAccomodations.indexOf(accomBEAN) == 0) {
                gridAccomodation.add(new Label("Accomodation"), 0, 0);

            }

            /* ======================== Bind With Bean ==================== */
            Bindings.bindBidirectional(cmbAccomodation.valueProperty(), accomBEAN.accomodationProperty());
            /* ======================== Add To Grid ==================== */
            gridAccomodation.add(cmbAccomodation, 0, listAccomodations.indexOf(accomBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAccomodation.add(hBox, 1, listAccomodations.indexOf(accomBEAN) + 1);
        }
    }

    public void initDynaContactDetails() {
        listEduContacts.clear();
        listEduContacts = eduContactDAO.retrieveContacts(COLLEGE_ID);
        if (listEduContacts.size() > 0) {
            addDynaContactDetailsRows();
        } else {
            ContactsBEAN contactsBEAN = new ContactsBEAN();
            listEduContacts.add(contactsBEAN);
            addDynaContactDetailsRows();
        }
    }

    public void addDynaContactDetailsRows() {
        gridContacts.getChildren().clear();
        ObservValidateEduContacts.clear();
        for (ContactsBEAN eduContactBEAN : listEduContacts) {
            eduContactBEAN.setCollegeId(COLLEGE_ID);

            TextField txtContactName = new TextField();
            txtContactName.setPromptText("Contact Name");

            TextField txtDepartment = new TextField();
            txtDepartment.setPromptText("Department");

            TextField txtStdIsd = new TextField();
            txtStdIsd.setPromptText("STD / ISD");

            TextField txtPhoneNo = new TextField();
            txtPhoneNo.setPromptText("Phone Number");

            TextField txtEmail = new TextField();
            txtEmail.setPromptText("Email");

            GridPane.setHgrow(txtContactName, Priority.NEVER);
            GridPane.setHgrow(txtDepartment, Priority.NEVER);
            GridPane.setHgrow(txtStdIsd, Priority.NEVER);
            GridPane.setHgrow(txtPhoneNo, Priority.NEVER);
            GridPane.setHgrow(txtEmail, Priority.NEVER);

            txtContactName.setMaxWidth(Double.MAX_VALUE);
            txtDepartment.setMaxWidth(Double.MAX_VALUE);
            txtStdIsd.setMaxWidth(Double.MAX_VALUE);
            txtPhoneNo.setMaxWidth(Double.MAX_VALUE);
            txtEmail.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            ValidationSupport validationContactSupport = new ValidationSupport();
            validationContactSupport.registerValidator(txtContactName, Validator.createEmptyValidator("Contact Name Required"));
            validationContactSupport.registerValidator(txtDepartment, Validator.createEmptyValidator("Department Required"));
            validationContactSupport.registerValidator(txtStdIsd, Validator.createEmptyValidator("STD/ISD code Rquired"));
            validationContactSupport.registerValidator(txtPhoneNo, Validator.createEmptyValidator("Phone Number Required"));
            validationContactSupport.registerValidator(txtEmail, Validator.createEmptyValidator("Email Required"));
                        validationContactSupport.registerValidator(txtEmail, Validator.createRegexValidator("Valid Email Required", "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Severity.ERROR));

            ObservValidateEduContacts.add(validationContactSupport);

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    ValidationResult result = validationContactSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationContactSupport.isInvalid()) {
                        eduContactBEAN.setCollegeId(COLLEGE_ID);
                        if (eduContactBEAN.getRowId() != null) {
                            eduContactDAO.updateContacts(eduContactBEAN);
                        } else if (COLLEGE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            eduContactBEAN.setRowId("con_" + UiiDGenerator.getUIID8());
                            int done = eduContactDAO.insertContacts(eduContactBEAN);
                        }
                        ContactsBEAN emptyBEAN = new ContactsBEAN();
                        listEduContacts.add(emptyBEAN);
                        addDynaContactDetailsRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridContacts);
//                    }
                    gridContacts.requestFocus();

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
                        if (eduContactBEAN.getRowId() != null) {
                            eduContactDAO.deleteContactDetails(eduContactBEAN.getRowId());
                        }
                        listEduContacts.remove(eduContactBEAN);
                        if (listEduContacts.size() > 0) {
                            addDynaContactDetailsRows();
                        } else {
                            ContactsBEAN emptyBEAN = new ContactsBEAN();
                            listEduContacts.add(emptyBEAN);
                            addDynaContactDetailsRows();
                        }
                        gridContacts.requestFocus();
                        ObservValidateEduContacts.remove(validationContactSupport);

                    }
                }
            });

            if (listEduContacts.indexOf(eduContactBEAN) == 0) {
                gridContacts.add(new Label("Contact Name"), 0, 0);
                gridContacts.add(new Label("Department"), 1, 0);
                gridContacts.add(new Label("STD / ISD"), 2, 0);
                gridContacts.add(new Label("Phone No"), 3, 0);
                gridContacts.add(new Label("Email"), 4, 0);
            }

            /* ======================== Bind With Bean ==================== */
            txtContactName.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                            Bindings.unbindBidirectional(eduContactBEAN.contactNameProperty(), txtContactName.textProperty());
                            txtContactName.setText(oldValue);
                            Bindings.bindBidirectional(eduContactBEAN.contactNameProperty(), txtContactName.textProperty());
                        } else {
                            Bindings.unbindBidirectional(eduContactBEAN.contactNameProperty(), txtContactName.textProperty());
                            txtContactName.setText(newValue);
                            Bindings.bindBidirectional(eduContactBEAN.contactNameProperty(), txtContactName.textProperty());
                        }
                    }
                }
            });
            txtDepartment.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                            Bindings.unbindBidirectional(eduContactBEAN.departmentProperty(), txtDepartment.textProperty());
                            txtDepartment.setText(oldValue);
                            Bindings.bindBidirectional(eduContactBEAN.departmentProperty(), txtDepartment.textProperty());
                        } else {
                            Bindings.unbindBidirectional(eduContactBEAN.departmentProperty(), txtDepartment.textProperty());
                            txtDepartment.setText(newValue);
                            Bindings.bindBidirectional(eduContactBEAN.departmentProperty(), txtDepartment.textProperty());
                        }
                    }
                }
            });
            txtStdIsd.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(eduContactBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(eduContactBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                        } else {
                            Bindings.unbindBidirectional(eduContactBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                            txtStdIsd.setText(newValue);
                            Bindings.bindBidirectional(eduContactBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                        }
                    }
                }
            });
            txtPhoneNo.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(eduContactBEAN.phoneProperty(), txtPhoneNo.textProperty());
                            txtPhoneNo.setText(oldValue);
                            Bindings.bindBidirectional(eduContactBEAN.phoneProperty(), txtPhoneNo.textProperty());
                        } else {
                            Bindings.unbindBidirectional(eduContactBEAN.phoneProperty(), txtPhoneNo.textProperty());
                            txtPhoneNo.setText(newValue);
                            Bindings.bindBidirectional(eduContactBEAN.phoneProperty(), txtPhoneNo.textProperty());
                        }
                    }
                }
            });
            Bindings.bindBidirectional(txtPhoneNo.textProperty(), eduContactBEAN.phoneProperty());
            Bindings.bindBidirectional(txtEmail.textProperty(), eduContactBEAN.emailProperty());
            Bindings.bindBidirectional(txtContactName.textProperty(), eduContactBEAN.contactNameProperty());
            Bindings.bindBidirectional(txtDepartment.textProperty(), eduContactBEAN.departmentProperty());
            Bindings.bindBidirectional(txtStdIsd.textProperty(), eduContactBEAN.stdIsdProperty());

            gridContacts.add(txtContactName, 0, listEduContacts.indexOf(eduContactBEAN) + 1);
            gridContacts.add(txtDepartment, 1, listEduContacts.indexOf(eduContactBEAN) + 1);
            gridContacts.add(txtStdIsd, 2, listEduContacts.indexOf(eduContactBEAN) + 1);
            gridContacts.add(txtPhoneNo, 3, listEduContacts.indexOf(eduContactBEAN) + 1);
            gridContacts.add(txtEmail, 4, listEduContacts.indexOf(eduContactBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridContacts.add(hBox, 5, listEduContacts.indexOf(eduContactBEAN) + 1);

        }

    }

    public void initDynaCampusesUnderEduProvider() {
        listCampuses.clear();
        listCampuses = campusesDAO.retrieveCampuses(COLLEGE_ID);
        if (listCampuses.size() > 0) {
            addDynamicCampuses();
        } else {
            CampusBEAN campusBEAN = new CampusBEAN();
            listCampuses.add(campusBEAN);
            addDynamicCampuses();
        }

    }

    public void addDynamicCampuses() {
        gridCampuses.getChildren().clear();
        ObservValidateCampuses.clear();
        for (CampusBEAN campusBEAN : listCampuses) {
            campusBEAN.setCollegeId(COLLEGE_ID);

            TextField txtCampusName = new TextField();
            txtCampusName.setPromptText("Campus Name");

            ComboBox cmbCountry = new ComboBox(countryDAO.retrieveAllCountries());
            cmbCountry.setPromptText("Country");

            ComboBox cmbLocation = new ComboBox();
            cmbLocation.setPromptText("Location");

            TextArea txtContactAddrs = new TextArea();
            txtContactAddrs.setPromptText("Contact Address");
            TextAreaGrowShrink.makeGrow(txtContactAddrs);
            GridPane.setHgrow(txtCampusName, Priority.NEVER);
            GridPane.setHgrow(cmbCountry, Priority.NEVER);
            GridPane.setHgrow(cmbLocation, Priority.NEVER);
            GridPane.setHgrow(txtContactAddrs, Priority.NEVER);

            txtCampusName.setMaxWidth(Double.MAX_VALUE);
            cmbCountry.setMaxWidth(Double.MAX_VALUE);
            cmbLocation.setMaxWidth(Double.MAX_VALUE);
            txtContactAddrs.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");
            /* ======================== Combo Events ==================== */
            cmbCountry.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        // cmbServiceLocation.getSelectionModel().selectFirst();
                        Task< ObservableList> taskLoadState = new Task<ObservableList>() {
                            @Override
                            protected ObservableList call() throws Exception {
                                return locationsDAO.retrievLocationsByCountry(newValue.toString());
                            }
                        };
                        taskLoadState.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                            @Override
                            public void handle(WorkerStateEvent event) {
                                cmbLocation.setItems(taskLoadState.getValue());
                            }
                        });
                        taskLoadState.run();
                    }
                }
            });
            /* ======================== Validation Support ==================== */
            ValidationSupport validationCampusSupport = new ValidationSupport();
            validationCampusSupport.registerValidator(txtCampusName, Validator.createEmptyValidator("Campus Name Required!"));
            validationCampusSupport.registerValidator(cmbCountry, Validator.createEmptyValidator("Country Required!"));
            validationCampusSupport.registerValidator(cmbLocation, Validator.createEmptyValidator("Location Rquired!"));
            validationCampusSupport.registerValidator(txtContactAddrs, Validator.createEmptyValidator("Address Required!"));
            ObservValidateCampuses.add(validationCampusSupport);

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationCampusSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationCampusSupport.isInvalid()) {
                        campusBEAN.setCollegeId(COLLEGE_ID);
                        if (campusBEAN.getCollegeId() != null) {
                            campusesDAO.updateCampuses(campusBEAN);
                        } else if (COLLEGE_ID != null) {
                            campusBEAN.setRowId("camp_" + UiiDGenerator.getUIID8());
                            int done = campusesDAO.insertCampuses(campusBEAN);
                        }
                        CampusBEAN emptyBEAN = new CampusBEAN();
                        listCampuses.add(emptyBEAN);
                        addDynamicCampuses();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridBranches);
//                    }
                    gridCampuses.requestFocus();
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
                        if (campusBEAN.getRowId() != null) {
                            campusesDAO.deleteCampuses(campusBEAN.getRowId());
                        }
                        listCampuses.remove(campusBEAN);
                        if (listCampuses.size() > 0) {
                            addDynamicCampuses();
                        } else {
                            CampusBEAN emptyBEAN = new CampusBEAN();
                            listCampuses.add(emptyBEAN);
                            addDynamicCampuses();
                        }
                        ObservValidateCampuses.remove(validationCampusSupport);
                        gridCampuses.requestFocus();
                    }
                }
            });

            if (listCampuses.indexOf(campusBEAN) == 0) {
                gridCampuses.add(new Label("Campus Name"), 0, 0);
                gridCampuses.add(new Label("Country"), 1, 0);
                gridCampuses.add(new Label("Location"), 2, 0);
                gridCampuses.add(new Label("Contact Address"), 3, 0);
            }
            txtCampusName.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                            Bindings.unbindBidirectional(campusBEAN.campusProperty(), txtCampusName.textProperty());
                            txtCampusName.setText(oldValue);
                            Bindings.bindBidirectional(campusBEAN.campusProperty(), txtCampusName.textProperty());
                        } else {
                            Bindings.unbindBidirectional(campusBEAN.campusProperty(), txtCampusName.textProperty());
                            txtCampusName.setText(newValue);
                            Bindings.bindBidirectional(campusBEAN.campusProperty(), txtCampusName.textProperty());
                        }
                    }
                }
            });
            Bindings.bindBidirectional(cmbCountry.valueProperty(), campusBEAN.countryProperty());
            Bindings.bindBidirectional(cmbLocation.valueProperty(), campusBEAN.locationProperty());
            Bindings.bindBidirectional(txtContactAddrs.textProperty(), campusBEAN.addressProperty());
            Bindings.bindBidirectional(txtCampusName.textProperty(), campusBEAN.campusProperty());

            gridCampuses.add(txtCampusName, 0, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(cmbCountry, 1, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(cmbLocation, 2, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(txtContactAddrs, 3, listCampuses.indexOf(campusBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridCampuses.add(hBox, 4, listCampuses.indexOf(campusBEAN) + 1);
        }

    }

    /**
     *
     */
    public void bindEducationalProviderBasics() {

        StringConverter converter = new DefaultObjectConverter<UniversityBEAN>() {

            @Override
            public String toString(UniversityBEAN universityBEAN) {
                if (universityBEAN != null) {
                    return universityBEAN.getUniversityId();
                } else {
                    return "";
                }
            }

        };
        for (UniversityBEAN universityBEAN : cmbUniversity.getItems()) {
            if (universityBEAN.getUniversityId().equalsIgnoreCase(eduProviderBEAN.getUniversityId())) {
                cmbUniversity.getSelectionModel().select(universityBEAN);
            }
        }
        Bindings.bindBidirectional(eduProviderBEAN.universityIdProperty(), cmbUniversity.valueProperty(), converter);
        Bindings.bindBidirectional(txtCollegeName.textProperty(), eduProviderBEAN.collegeProperty());
        Bindings.bindBidirectional(txtarDescription.textProperty(), eduProviderBEAN.descriptionProperty());
        Bindings.bindBidirectional(txtarRemarks.textProperty(), eduProviderBEAN.remarksProperty());
        Bindings.bindBidirectional(txtarAddress.textProperty(), eduProviderBEAN.addressProperty());
        Bindings.bindBidirectional(txtWebsite.textProperty(), eduProviderBEAN.websiteProperty());
        Bindings.bindBidirectional(txtCommission.textProperty(), eduProviderBEAN.commissionProperty());
        Bindings.bindBidirectional(cmbCurrencyType.valueProperty(), eduProviderBEAN.currencyProperty());
        Bindings.bindBidirectional(cmbInstitutionType.valueProperty(), eduProviderBEAN.institutionTypeProperty());
        Bindings.bindBidirectional(cmbAgencyStatus.valueProperty(), eduProviderBEAN.agencyStatusProperty());
        Bindings.bindBidirectional(txtTieUpName.textProperty(), eduProviderBEAN.tieUpNameProperty());
        Bindings.bindBidirectional(dpContractFrom.valueProperty(), eduProviderBEAN.contractFromProperty());
        Bindings.bindBidirectional(dpContractTo.valueProperty(), eduProviderBEAN.contractToProperty());
        Bindings.bindBidirectional(txtContractBy.textProperty(), eduProviderBEAN.contractByProperty());
        Bindings.bindBidirectional(txtRenewalBy.textProperty(), eduProviderBEAN.renewalByProperty());
    }

    /**
     *
     */
    public void makeMandaoryDecorations() {
        /* ========================  Validation Support ==================== */
        validationSupport.registerValidator(txtCollegeName, Validator.createEmptyValidator("College Name required"));
        validationSupport.registerValidator(txtCommission, Validator.createEmptyValidator("Commission required"));
        validationSupport.registerValidator(cmbUniversity, Validator.createEmptyValidator("University required"));
        validationSupport.registerValidator(cmbInstitutionType, Validator.createEmptyValidator("Institution Type required"));
        validationSupport.registerValidator(cmbCurrencyType, Validator.createEmptyValidator("Currency Type required"));
        validationSupport.registerValidator(cmbAgencyStatus, Validator.createEmptyValidator("Agency Status required"));
        validationSupport.registerValidator(dpContractFrom, Validator.createEmptyValidator("Date required"));
        validationSupport.registerValidator(dpContractTo, Validator.createEmptyValidator("Date required"));
        validationSupport.registerValidator(txtContractBy, Validator.createEmptyValidator("Contract By required"));
        validationSupport.registerValidator(txtWebsite, Validator.createEmptyValidator("Website required"));
        validationSupport.registerValidator(txtRenewalBy, Validator.createEmptyValidator("Date required"));
        validationSupport.registerValidator(txtTieUpName, Validator.createEmptyValidator("TieUp required"));

    }

    /**
     *
     */
    public void addButtonListners() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isCoursesValid = false;
                boolean isProcessingFeeValid = false;
                boolean isVisaDocsValid = false;
                boolean isAccomodationsValid = false;
                boolean isContactDetailsValid = false;
                boolean isCampusesValid = false;
                /* ======================== Validate Basic Info ==================== */
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }

                /* ======================== Validate Courses ==================== */
                for (ValidationSupport support : observValidateCourses) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isCoursesValid = support.isInvalid();
                    }
                }

                /* ======================== Validate Processing Fee ==================== */
                for (ValidationSupport support : ObservValidateProcessingFee) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isProcessingFeeValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Visa Documents ==================== */
                for (ValidationSupport support : observValidateVisaDocs) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isVisaDocsValid = support.isInvalid();
                    }
                }
                /* ======================== Validate  Accomodations ==================== */
                for (ValidationSupport support : observValidateAccomodations) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isAccomodationsValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Contacts ==================== */
                for (ValidationSupport support : ObservValidateEduContacts) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isContactDetailsValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Campuses ==================== */
                for (ValidationSupport support : ObservValidateCampuses) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isCampusesValid = support.isInvalid();
                    }
                }

                System.out.println("Test Validation Valid :: " + validationSupport.isInvalid());
                /* ======================== Insert Basic Info ==================== */
                if (!validationSupport.isInvalid()) {
                    if (eduProviderBEAN.getCollegeId() == null || eduProviderBEAN.getCollegeId().equalsIgnoreCase("")) {
                        /* ======================== Generate Employer Id ==================== */
                        COLLEGE_ID = "cl_" + UiiDGenerator.getUIID8();
                        eduProviderBEAN.setCollegeId(COLLEGE_ID);
                        System.out.println("COLLEGE_IDCOLLEGE_IDCOLLEGE_ID  ======== " + COLLEGE_ID);
                        int done = eduProviderDAO.insertEduProviderBasics(eduProviderBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Saved Successfully!")
                                    .text("Educational Provider Details Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Save Failed!")
                                    .text("Educational Provider Details Save Failed!")
                                    .showError();
                            eduProviderBEAN.setCollegeId(null);
                        }
                    } else {
                        int done = eduProviderDAO.updateEduProviderBasics(eduProviderBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Updated Successfully!")
                                    .text("Educational Provider Details Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Updation Failed!")
                                    .text("Educational Provider Details Updation Failed!")
                                    .showError();
                        }
                    }
                }
                /* ======================== Save Course Details ==================== */
                if (!isCoursesValid && !validationSupport.isInvalid()) {
                    System.out.println("Contact Details are Valid Now");
                    for (CoursesBEAN coursesBEAN : listCourses) {
                        if (coursesBEAN.getCourseId() == null || coursesBEAN.getCourseId().equalsIgnoreCase("")) {
                            coursesBEAN.setUniversityId(eduProviderBEAN.getUniversityId());
                            coursesBEAN.setCollegeId(COLLEGE_ID);
                            coursesBEAN.setCourseId("crs_" + UiiDGenerator.getUIID8());
                            coursesDAO.insertCourses(coursesBEAN);
                        } else {
                            coursesDAO.updateCourses(coursesBEAN);
                        }
                    }
                }
                /* ======================== Save Procesing Fee Details ==================== */
                if (!isProcessingFeeValid && !validationSupport.isInvalid()) {
                    System.out.println("Processing Fee Details are Valid Now");
                    for (ProcessFeeBEAN processFeeBEAN : listProcessFees) {
                        if (processFeeBEAN.getRowId() == null || processFeeBEAN.getRowId().equalsIgnoreCase("")) {
                            processFeeBEAN.setCollegeId(COLLEGE_ID);
                            processFeeBEAN.setRowId("pf_" + UiiDGenerator.getUIID8());
                            processFeeDAO.insertProcessFee(processFeeBEAN);
                        } else {
                            processFeeDAO.updateProcessFee(processFeeBEAN);
                        }
                    }
                }
                /* ======================== Save Visa Document Details ==================== */
                if (!isVisaDocsValid && !validationSupport.isInvalid()) {
                    System.out.println("Visa Docs Details are Valid Now");
                    for (VisadocBEAN visadocBEAN : listVisadocs) {
                        if (visadocBEAN.getRowId() == null || visadocBEAN.getRowId().equalsIgnoreCase("")) {
                            visadocBEAN.setCollegeId(COLLEGE_ID);
                            visadocBEAN.setRowId("vr_" + UiiDGenerator.getUIID8());
                            visadocDAO.insertVisaDocuments(visadocBEAN);
                        } else {
                            visadocDAO.updateVisaDocuments(visadocBEAN);
                        }
                    }
                }
                /* ======================== Save Accomodation Details ==================== */
                if (!isAccomodationsValid && !validationSupport.isInvalid()) {
                    System.out.println("Accomdn Details are Valid Now");
                    for (AccomBEAN accomBEAN : listAccomodations) {
                        if (accomBEAN.getRowId() == null || accomBEAN.getRowId().equalsIgnoreCase("")) {
                            accomBEAN.setCollegeId(COLLEGE_ID);
                            accomBEAN.setRowId("ac_" + UiiDGenerator.getUIID8());
                            accomDAO.insertAccomodation(accomBEAN);
                        } else {
                            accomDAO.updateAccomodation(accomBEAN);
                        }
                    }
                }
                /* ======================== Save Contact Details ==================== */
                if (!isContactDetailsValid && !validationSupport.isInvalid()) {
                    System.out.println("Contact Is Valid Now");
                    for (ContactsBEAN eduContactBEAN : listEduContacts) {
                        if (eduContactBEAN.getRowId() == null || eduContactBEAN.getRowId().equalsIgnoreCase("")) {
                            eduContactBEAN.setCollegeId(COLLEGE_ID);
                            eduContactBEAN.setRowId("cn_" + UiiDGenerator.getUIID8());
                            eduContactDAO.insertContacts(eduContactBEAN);
                        } else {
                            eduContactDAO.updateContacts(eduContactBEAN);
                        }
                    }
                }
                /* ======================== Save Campuses ==================== */
                if (!isCampusesValid && !validationSupport.isInvalid()) {
                    System.out.println("Campuses Is Valid Now");
                    for (CampusBEAN campusBEAN : listCampuses) {
                        if (campusBEAN.getRowId() == null || campusBEAN.getRowId().equalsIgnoreCase("")) {
                            campusBEAN.setCollegeId(COLLEGE_ID);
                            campusBEAN.setRowId("ca_" + UiiDGenerator.getUIID8());
                            campusesDAO.insertCampuses(campusBEAN);
                        } else {
                            campusesDAO.updateCampuses(campusBEAN);
                        }
                    }
                }
            }
        });
        /* ======================== Picture Upload Button ==================== */
        btnUploadLogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose a logo");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                File selectedFile = fileChooser.showOpenDialog(btnUploadLogo.getScene().getWindow());
                if (selectedFile != null) {
                    try {

                        //  documentPhotoBEAN.setEnquiryId(documentVerifyBEAN.getEnquiryId());
                        BufferedImage originalImage = ImageIO.read(selectedFile);
                        /* ======================== Resize Image Before Upload ==================== */
                        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                        BufferedImage resizedImage = new BufferedImage(200, 200, type);
                        Graphics2D g = resizedImage.createGraphics();
                        g.drawImage(originalImage, 0, 0, 200, 200, null);
                        g.dispose();

                        if (selectedFile.length() > PHOTO_MAX_SIZE) {
                            popupMessages.showError("Image with size less than 1 MB is only acceptable ! Try again !", btnSave);
                        } else {
                            Image image = SwingFXUtils.toFXImage(resizedImage, null);
                            imgLogoPreview.setImage(image);
                            imgLogoPreview.setFitWidth(120);
                            imgLogoPreview.setFitHeight(180);
                            imgLogoPreview.setPreserveRatio(true);
                            imgLogoPreview.setSmooth(true);
                            imgLogoPreview.setCache(true);

                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(resizedImage, "png", baos);
                            InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
                            baos.flush();
//                                    photoFlag++;;
                            eduProviderBEAN.setInputStream(inputStream);
                            baos.close();
                            System.out.println("Added New Pic :: ");
                        }

                    } catch (IOException ex) {
                        logger.log(org.apache.log4j.Priority.FATAL, ex, ex);
                    }
                }

            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                COLLEGE_ID = null;
                eduProviderBEAN = new EduProviderBEAN();
                bindEducationalProviderBasics();
                imgLogoPreview.setImage(null);
                imgLogoPreview.resize(60, 60);
                imgLogoPreview.setManaged(true);
                cmbUniversity.getSelectionModel().clearSelection();
                /* ======================== Auto Grow Shrink Text Area ==================== */
                TextAreaGrowShrink.makeGrow(txtarRemarks);
                TextAreaGrowShrink.makeGrow(txtarDescription);
                TextAreaGrowShrink.makeGrow(txtarAddress);

                /* ======================== Dynamic Controls ==================== */
                initDynaCoursesOffered();
                initDynaProcessingFees();
                initDynaVisaDocuments();
                initDynaAccomodations();
                initDynaContactDetails();
                initDynaCampusesUnderEduProvider();

            }
        });

    }

    /**
     *
     */
    public void initializeMasterData() {
        cmbUniversity.getItems().addAll(universityDAO.retreiveUniversities());
        cmbAgencyStatus.getItems().addAll(SearchConstants.getListAgencyStatus());
        cmbInstitutionType.getItems().addAll(SearchConstants.getListEmployerType());
        cmbCurrencyType.getItems().addAll(RetrieveStaticMasterDAO.getCurrencies());

    }

    public boolean validateEduContacts(ObservableList<ContactsBEAN> listValidate) {
        return true;
    }

    public void addAdditionalFeatures() {
        ChangeDateFormat.datePickerDateFormatter(dpContractFrom);
        ChangeDateFormat.datePickerDateFormatter(dpContractTo);
        ComboBoxJumpToChar.jumpToChar(cmbAgencyStatus);
        ComboBoxJumpToChar.jumpToChar(cmbInstitutionType);
        ComboBoxJumpToChar.jumpToChar(cmbCurrencyType);
        /* ======================== Auto Grow Shrink Text Area ==================== */
        TextAreaGrowShrink.makeGrow(txtarRemarks);
        TextAreaGrowShrink.makeGrow(txtarDescription);
        TextAreaGrowShrink.makeGrow(txtarAddress);

    }

    public boolean validateCampuses(ObservableList<CampusBEAN> listCampuses) {
        return true;
    }

    public void validationListners() {
        txtCollegeName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(eduProviderBEAN.collegeProperty(), txtCollegeName.textProperty());
                        txtCollegeName.setText(oldValue);
                        Bindings.bindBidirectional(eduProviderBEAN.collegeProperty(), txtCollegeName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(eduProviderBEAN.collegeProperty(), txtCollegeName.textProperty());
                        txtCollegeName.setText(newValue);
                        Bindings.bindBidirectional(eduProviderBEAN.collegeProperty(), txtCollegeName.textProperty());
                    }

                }
            }

        });
        dpContractFrom.setPromptText("From");
            dpContractTo.setPromptText("To");
            dpContractFrom.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactory
                    = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(dpContractFrom.getValue().plusDays(1))) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };
            dpContractTo.setDayCellFactory(dayCellFactory);
            dpContractTo.setValue(dpContractFrom.getValue().plusDays(1));

        txtWebsite.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                                        if (!(newValue.matches("[a-zA-Z,.,/,:,0-9]*"))) {
                        Bindings.unbindBidirectional(eduProviderBEAN.websiteProperty(), txtWebsite.textProperty());
                        txtWebsite.setText(oldValue);
                        Bindings.bindBidirectional(eduProviderBEAN.websiteProperty(), txtWebsite.textProperty());
                    } else {
                        Bindings.unbindBidirectional(eduProviderBEAN.websiteProperty(), txtWebsite.textProperty());
                        txtWebsite.setText(newValue);
                        Bindings.bindBidirectional(eduProviderBEAN.websiteProperty(), txtWebsite.textProperty());
                    }

                }
            }

        });
        txtTieUpName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(eduProviderBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                        txtTieUpName.setText(oldValue);
                        Bindings.bindBidirectional(eduProviderBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(eduProviderBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                        txtTieUpName.setText(newValue);
                        Bindings.bindBidirectional(eduProviderBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                    }
                }
            }
        });
        txtCommission.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*")) || newValue.length() > 2) {
                        Bindings.unbindBidirectional(eduProviderBEAN.commissionProperty(), txtCommission.textProperty());
                        txtCommission.setText(oldValue);
                        Bindings.bindBidirectional(eduProviderBEAN.commissionProperty(), txtCommission.textProperty());
                    } else {
                        Bindings.unbindBidirectional(eduProviderBEAN.commissionProperty(), txtCommission.textProperty());
                        txtCommission.setText(newValue);
                        Bindings.bindBidirectional(eduProviderBEAN.commissionProperty(), txtCommission.textProperty());
                    }
                }
            }
        });

        txtContractBy.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(eduProviderBEAN.contractByProperty(), txtContractBy.textProperty());
                        txtContractBy.setText(oldValue);
                        Bindings.bindBidirectional(eduProviderBEAN.contractByProperty(), txtContractBy.textProperty());
                    } else {
                        Bindings.unbindBidirectional(eduProviderBEAN.contractByProperty(), txtContractBy.textProperty());
                        txtContractBy.setText(newValue);
                        Bindings.bindBidirectional(eduProviderBEAN.contractByProperty(), txtContractBy.textProperty());
                    }

                }
            }

        });
        txtRenewalBy.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(eduProviderBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                        txtRenewalBy.setText(oldValue);
                        Bindings.bindBidirectional(eduProviderBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                    } else {
                        Bindings.unbindBidirectional(eduProviderBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                        txtRenewalBy.setText(newValue);
                        Bindings.bindBidirectional(eduProviderBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                    }

                }
            }

        });
        /* ======================== Disable Tie Up Name + Custom Validation Using Controlfx ValidationSupport ==================== */
        cmbAgencyStatus.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    /* ======================== Custom Validation ==================== */
                    if (newValue.equalsIgnoreCase("Direct")) {
                        txtTieUpName.clear();
                        txtTieUpName.setDisable(true);
                        Validator<String> validator = new Validator<String>() {
                            @Override
                            public ValidationResult apply(Control control, String value) {
                                return ValidationResult.fromMessageIf(control, "Tie Up Name Required!", Severity.ERROR, false);
                            }
                        };
                        validationSupport.registerValidator(txtTieUpName, validator);
                    } else {
                        txtTieUpName.setDisable(false);
                        Validator<String> validator = new Validator<String>() {
                            @Override
                            public ValidationResult apply(Control control, String value) {
                                boolean condition = true;
                                TextField txtTieUp = (TextField) control;
                                if (txtTieUp.getText() == null || txtTieUp.getText().equalsIgnoreCase("")) {

                                } else {
                                    condition = false;
                                }
                                return ValidationResult.fromMessageIf(control, "Tie Up Name Required!", Severity.ERROR, condition);
                            }
                        };
                        validationSupport.registerValidator(txtTieUpName, validator);
                    }
                }
            }
        });
    }

    public void autoCompletion() {

        /* ======================== Create Autocompletion Data By Overriding @toString ==================== */
        ObservableList<EduProviderBEAN> listEduProviderAuto = eduProviderDAO.retrieveEduProviderBasics();
        ObservableList<EduProviderBEAN> listAutoCompletion = FXCollections.observableArrayList();
        for (EduProviderBEAN autoBEAN : listEduProviderAuto) {
            EduProviderBEAN autoCompletionBEAN = new EduProviderBEAN() {
                @Override
                public String toString() {
                    return autoBEAN.getCollege();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        }
        System.out.println("test auto completion list :: " + listAutoCompletion.toString());
        hboxCollegeName.getChildren().remove(txtCollegeName);
        txtCollegeName = TextFields.createClearableTextField();
        AutoCompletionBinding<EduProviderBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtCollegeName, listAutoCompletion);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<EduProviderBEAN>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<EduProviderBEAN> event) {
                eduProviderBEAN = new EduProviderBEAN();
                BeanUtils.copyProperties(event.getCompletion(), eduProviderBEAN);
                bindEducationalProviderBasics();

                loadEduProviderAllData(event.getCompletion().getCollegeId());
                System.out.println("Test AutoCompletion :: " + event.getCompletion().toString());
                System.out.println("All Data AutoCompletion :: " + eduProviderBEAN.toString());
            }
        });
        HBox.setHgrow(txtCollegeName, Priority.ALWAYS);
        hboxCollegeName.getChildren().add(txtCollegeName);

    }

    public void loadEduProviderAllData(String collegeId) {
        COLLEGE_ID = collegeId;
        /* ======================== Dynamic Controls ==================== */
        initDynaCoursesOffered();
        initDynaProcessingFees();
        initDynaVisaDocuments();
        initDynaAccomodations();
        initDynaContactDetails();
        initDynaCampusesUnderEduProvider();
        /* ======================== Refresh Validation ==================== */
//        validationSupport = new ValidationSupport();
//        makeMandaoryDecorations();
        /* ======================== Load Logo ==================== */
        imgLogoPreview.setImage(null);
        if (eduProviderBEAN.getInputStream() != null) {
            try {
                BufferedImage bufferedImg = ImageIO.read(eduProviderBEAN.getInputStream());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (bufferedImg != null) {
                            Image imagee = SwingFXUtils.toFXImage(bufferedImg, null);
                            imgLogoPreview.setImage(imagee);
                        }
                    }
                });
                imgLogoPreview.setFitWidth(120);
                imgLogoPreview.setFitHeight(180);
                imgLogoPreview.setPreserveRatio(true);
                imgLogoPreview.setSmooth(true);
                imgLogoPreview.setCache(true);
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
    }
}

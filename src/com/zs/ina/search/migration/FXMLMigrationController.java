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
package com.zs.ina.search.migration;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.search.master.common.GlobalSearchMasterData;
import com.zs.ina.search.master.common.SearchConstants;
import com.zs.ina.search.migration.dao.MigrationBasicBEAN;
import com.zs.ina.search.migration.dao.MigrationBasicDAO;
import com.zs.ina.search.migration.dynamics.AgePointsDAO;
import com.zs.ina.search.migration.dynamics.ApplicantAbilityEnglishDAO;
import com.zs.ina.search.migration.dynamics.MigrationSkillsBEAN;
import com.zs.ina.search.migration.dynamics.EducationalSkillsDAO;
import com.zs.ina.search.migration.dynamics.EducationalSkillsWithIeltsDAO;
import com.zs.ina.search.migration.dynamics.FactorsDAO;
import com.zs.ina.search.migration.dynamics.OverseasExperienceDAO;
import com.zs.ina.search.migration.dynamics.SpouseAbilityEnglishDAO;
import com.zs.ina.search.migration.dynamics.SpouseEducationSkillsDAO;
import java.net.URL;
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
import javafx.scene.Node;
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
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLMigrationController implements Initializable {

    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<String> cmbSubClass;
    @FXML
    private ComboBox<String> cmbOccupationCat;
    @FXML
    private GridPane gridEduSkills;
    @FXML
    private GridPane gridEduSkillsWithIELTS;
    @FXML
    private GridPane gridOverseasExp;
    @FXML
    private GridPane gridAge;
    @FXML
    private GridPane gridSpouseEdu;
    @FXML
    private GridPane gridSpouseIELTS;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private GridPane gridAbilityEnglish;
    @FXML
    private GridPane gridFactors;
    private String MIG_PROVIDER_ID = null;
    private String CUR_USERNAME = null;
    private String CUR_BRANCH = null;
    /* ======================== Spring Context ==================== */
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    MigrationBasicBEAN migrationBasicBEAN = new MigrationBasicBEAN();
    /* ======================== Migration Beans ==================== */
    MigrationBasicDAO migrationBasicDAO = springAppContext.getBean(MigrationBasicDAO.class);
    EducationalSkillsDAO educationalSkillsDAO = springAppContext.getBean(EducationalSkillsDAO.class);
    EducationalSkillsWithIeltsDAO educationalSkillsWithIeltsDAO = springAppContext.getBean(EducationalSkillsWithIeltsDAO.class);
    OverseasExperienceDAO overseasExperienceDAO = springAppContext.getBean(OverseasExperienceDAO.class);
    AgePointsDAO agePointsDAO = springAppContext.getBean(AgePointsDAO.class);
    ApplicantAbilityEnglishDAO applicantAbilityEnglishDAO = springAppContext.getBean(ApplicantAbilityEnglishDAO.class);
    SpouseAbilityEnglishDAO spouseAbilityEnglishDAO = springAppContext.getBean(SpouseAbilityEnglishDAO.class);
    FactorsDAO factorsDAO = springAppContext.getBean(FactorsDAO.class);
    SpouseEducationSkillsDAO spouseEducationSkillsDAO = springAppContext.getBean(SpouseEducationSkillsDAO.class);
    /* ======================== Validation Support ==================== */
    ValidationSupport validationSupport = new ValidationSupport();
    /* ======================== Other Objects ==================== */
    GlobalSearchMasterData masterData = new GlobalSearchMasterData();
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    /* ======================== Dynamic Control Lists ==================== */
    ObservableList<MigrationSkillsBEAN> listEducationalSkills = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listEducationalSkillsIelts = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listOverseasExperience = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listAgePoints = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listEnglishAbility = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listSpouseEnglishAbility = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listFactors = FXCollections.observableArrayList();
    ObservableList<MigrationSkillsBEAN> listSpouseEduSkills = FXCollections.observableArrayList();
    /* ======================== Dynamic Control Validation ==================== */
    ObservableList<ValidationSupport> observValidateEducationalSkills = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateEducationalSkillWidIelts = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateOverseasExperience = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateAgePoints = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateEnglishAbility = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateSpouseEnglishAbility = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateFactors = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateSpouseEduSkills = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initData(MigrationBasicBEAN basicBEAN) {
        BeanUtils.copyProperties(basicBEAN, migrationBasicBEAN);
        MIG_PROVIDER_ID = migrationBasicBEAN.getMigraProviderId();
        /* ======================== Initialize Global Variebles ==================== */
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        /* ======================== Invoke Other methods ==================== */
        initializeMasterData();
        bindBasicInfo();
        makeMandaoryDecorations();
        addAdditionalFeatures();
        addButtonListners();
        /* ======================== Dynamic Grids ==================== */
        initEducationalSkills();
        initEducationalSkillsWithIELTS();
        initOverseasExperience();
        initAgePoints();
        initEnglishAbility();
        initSpouseEnglishAbility();
        initFactors();
        initSpouseEducationSkills();
        /* ======================== Load Existing Info (:: Updation ) ==================== */
        loadExistingData();
        /* ======================== Disable Country,Subclass ==================== */
        if (MIG_PROVIDER_ID != null && !MIG_PROVIDER_ID.equalsIgnoreCase("")) {
            cmbCountry.setDisable(true);
            cmbCountry.getStyleClass().remove("button-service");
            cmbSubClass.getStyleClass().remove("button-service");
            cmbSubClass.setDisable(true);
        }

    }

    public void loadExistingData() {
        cmbSubClass.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (cmbCountry.getSelectionModel().getSelectedIndex() > -1) {
                        String _choosenCountry = cmbCountry.getSelectionModel().getSelectedItem();
                        MigrationBasicBEAN basicBEAN = migrationBasicDAO.retrieveBasicInfo(_choosenCountry, newValue);
                        BeanUtils.copyProperties(basicBEAN, migrationBasicBEAN);
//                        migrationBasicBEAN.setCountry(_choosenCountry);
                        bindBasicInfo();
                        MIG_PROVIDER_ID = migrationBasicBEAN.getMigraProviderId();
                        initEducationalSkills();
                        initEducationalSkillsWithIELTS();
                        initOverseasExperience();
                        initAgePoints();
                        initEnglishAbility();
                        initSpouseEnglishAbility();
                        initFactors();
                        initSpouseEducationSkills();
                    }
                }
            }
        });
        cmbCountry.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbSubClass.getSelectionModel().getSelectedIndex() > -1) {
                    MigrationBasicBEAN basicBEAN = migrationBasicDAO.retrieveBasicInfo(newValue, cmbSubClass.getSelectionModel().getSelectedItem());
                    BeanUtils.copyProperties(basicBEAN, migrationBasicBEAN);
                    bindBasicInfo();
                    MIG_PROVIDER_ID = migrationBasicBEAN.getMigraProviderId();
                    initEducationalSkills();
                    initEducationalSkillsWithIELTS();
                    initOverseasExperience();
                    initAgePoints();
                    initEnglishAbility();
                    initSpouseEnglishAbility();
                    initFactors();
                    initSpouseEducationSkills();
                }
            }
        });
    }

    public void initSpouseEducationSkills() {
        listSpouseEduSkills = spouseEducationSkillsDAO.retrieveEducationSkills(MIG_PROVIDER_ID);
        if (listSpouseEduSkills.size() > 0) {
            addDynaSpouseEducationSkills();
        } else {
            MigrationSkillsBEAN coursesBEAN = new MigrationSkillsBEAN();
            listSpouseEduSkills.add(coursesBEAN);
            addDynaSpouseEducationSkills();
        }
    }

    public void addDynaSpouseEducationSkills() {

        gridSpouseEdu.getChildren().clear();
        observValidateSpouseEduSkills.clear();
        for (MigrationSkillsBEAN spouseEduSkillsBEAN : listSpouseEduSkills) {
            spouseEduSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox<String> cmbProgramLevel = new ComboBox();
            cmbProgramLevel.setPromptText("Select Program Level");
            ComboBoxJumpToChar.jumpToChar(cmbProgramLevel);
            ObservableList<String> listProgramLevel = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getProgramLevels());
            cmbProgramLevel.getItems().addAll(listProgramLevel);

            ComboBox cmbProgramField = new ComboBox();
            cmbProgramField.setPromptText("Select Program Field");
            ComboBoxJumpToChar.jumpToChar(cmbProgramField);

            TextField txtSelectionPoint = new TextField();
            txtSelectionPoint.setPromptText("Select Duration");

            TextField txtEligibilityPoint = new TextField();
            txtEligibilityPoint.setPromptText("Select Duration");

            GridPane.setHgrow(cmbProgramLevel, Priority.NEVER);
            GridPane.setHgrow(cmbProgramField, Priority.NEVER);
            GridPane.setHgrow(txtSelectionPoint, Priority.NEVER);
            GridPane.setHgrow(txtEligibilityPoint, Priority.NEVER);

            cmbProgramLevel.setMaxWidth(Double.MAX_VALUE);
            cmbProgramField.setMaxWidth(Double.MAX_VALUE);
            txtSelectionPoint.setMaxWidth(Double.MAX_VALUE);
            txtEligibilityPoint.setMaxWidth(Double.MAX_VALUE);

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
            validationAcademicReqSupport.registerValidator(txtSelectionPoint, Validator.createEmptyValidator("Selection Point Rquired"));
            validationAcademicReqSupport.registerValidator(txtEligibilityPoint, Validator.createEmptyValidator("Eligibility Point Rquired"));
            observValidateSpouseEduSkills.add(validationAcademicReqSupport);
            txtEligibilityPoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(spouseEduSkillsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                        txtEligibilityPoint.setText(oldValue);
                        Bindings.bindBidirectional(spouseEduSkillsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(spouseEduSkillsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                        txtEligibilityPoint.setText(newValue);
                        Bindings.bindBidirectional(spouseEduSkillsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                    }
                }
            });

            txtSelectionPoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(spouseEduSkillsBEAN.selectPointSpouseProperty(), txtSelectionPoint.textProperty());
                        txtSelectionPoint.setText(oldValue);
                        Bindings.bindBidirectional(spouseEduSkillsBEAN.selectPointSpouseProperty(), txtSelectionPoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(spouseEduSkillsBEAN.selectPointSpouseProperty(), txtSelectionPoint.textProperty());
                        txtSelectionPoint.setText(newValue);
                        Bindings.bindBidirectional(spouseEduSkillsBEAN.selectPointSpouseProperty(), txtSelectionPoint.textProperty());
                    }
                }
            });
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
                        spouseEduSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (spouseEduSkillsBEAN.getRowId() != null) {
                            spouseEducationSkillsDAO.updateEducationSkills(spouseEduSkillsBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            spouseEduSkillsBEAN.setRowId("en_" + UiiDGenerator.getUIID8());
                            boolean done = spouseEducationSkillsDAO.insertEducationSkills(spouseEduSkillsBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listSpouseEduSkills.add(emptyBEAN);
                        addDynaSpouseEducationSkills();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridSpouseEdu);
//                    }
                    gridSpouseEdu.requestFocus();
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
                        if (spouseEduSkillsBEAN.getRowId() != null) {
                            spouseEducationSkillsDAO.deleteEducationSkills(spouseEduSkillsBEAN.getRowId());
                        }
                        listSpouseEduSkills.remove(spouseEduSkillsBEAN);
                        if (listSpouseEduSkills.size() > 0) {
                            addDynaSpouseEducationSkills();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listSpouseEduSkills.add(emptyBEAN);
                            addDynaSpouseEducationSkills();
                        }
                        observValidateSpouseEduSkills.remove(validationAcademicReqSupport);
                        gridSpouseEdu.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listSpouseEduSkills.indexOf(spouseEduSkillsBEAN) == 0) {
                gridSpouseEdu.add(new Label("Program Level"), 0, 0);
                gridSpouseEdu.add(new Label("Program Field"), 1, 0);
                gridSpouseEdu.add(new Label("Selection Point"), 2, 0);
                gridSpouseEdu.add(new Label("Eligibility Point"), 3, 0);
            }
            Bindings.bindBidirectional(cmbProgramLevel.valueProperty(), spouseEduSkillsBEAN.educationLevelProperty());
            Bindings.bindBidirectional(cmbProgramField.valueProperty(), spouseEduSkillsBEAN.educationFieldProperty());
            Bindings.bindBidirectional(txtSelectionPoint.textProperty(), spouseEduSkillsBEAN.selectPointSingleProperty());
            Bindings.bindBidirectional(txtEligibilityPoint.textProperty(), spouseEduSkillsBEAN.eligibilityPointProperty());

            gridSpouseEdu.add(cmbProgramLevel, 0, listSpouseEduSkills.indexOf(spouseEduSkillsBEAN) + 1);
            gridSpouseEdu.add(cmbProgramField, 1, listSpouseEduSkills.indexOf(spouseEduSkillsBEAN) + 1);
            gridSpouseEdu.add(txtSelectionPoint, 2, listSpouseEduSkills.indexOf(spouseEduSkillsBEAN) + 1);
            gridSpouseEdu.add(txtEligibilityPoint, 3, listSpouseEduSkills.indexOf(spouseEduSkillsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridSpouseEdu.add(hBox, 4, listSpouseEduSkills.indexOf(spouseEduSkillsBEAN) + 1);

        }
    }

    public void initFactors() {
        listFactors = factorsDAO.retrieveFactors(MIG_PROVIDER_ID);
        if (listFactors.size() > 0) {
            addDynaFactors();
        } else {
            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
            listFactors.add(emptyBEAN);
            addDynaFactors();
        }
    }

    public void addDynaFactors() {

        gridFactors.getChildren().clear();
        observValidateFactors.clear();
        for (MigrationSkillsBEAN factorsBEAN : listFactors) {
            factorsBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            TextArea txtFactor = new TextArea();
            txtFactor.setPromptText("Select Program Level");

            TextField txtPointSingle = new TextField();
            txtPointSingle.setPromptText("Point");

            TextField txtPointSpouse = new TextField();
            txtPointSpouse.setPromptText("Point");

            TextField txtEligibilityPoint = new TextField();
            txtEligibilityPoint.setPromptText("Select Duration");

            GridPane.setHgrow(txtFactor, Priority.NEVER);
            GridPane.setHgrow(txtPointSingle, Priority.NEVER);
            GridPane.setHgrow(txtEligibilityPoint, Priority.NEVER);
            GridPane.setHgrow(txtPointSpouse, Priority.NEVER);

            txtFactor.setMaxWidth(Double.MAX_VALUE);
            txtPointSingle.setMaxWidth(Double.MAX_VALUE);
            txtEligibilityPoint.setMaxWidth(Double.MAX_VALUE);
            txtPointSpouse.setMaxWidth(Double.MAX_VALUE);

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
            validationAcademicReqSupport.registerValidator(txtFactor, Validator.createEmptyValidator("Factor Required"));
            validationAcademicReqSupport.registerValidator(txtPointSingle, Validator.createEmptyValidator("Selection Point Required"));
            validationAcademicReqSupport.registerValidator(txtEligibilityPoint, Validator.createEmptyValidator("Eligibility Point Rquired"));
            validationAcademicReqSupport.registerValidator(txtPointSpouse, Validator.createEmptyValidator("Selection Point With Spouse Rquired"));
            observValidateFactors.add(validationAcademicReqSupport);
            txtEligibilityPoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(factorsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                        txtEligibilityPoint.setText(oldValue);
                        Bindings.bindBidirectional(factorsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(factorsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                        txtEligibilityPoint.setText(newValue);
                        Bindings.bindBidirectional(factorsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                    }
                }
            });
            txtPointSingle.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(factorsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                        txtPointSingle.setText(oldValue);
                        Bindings.bindBidirectional(factorsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(factorsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                        txtPointSingle.setText(newValue);
                        Bindings.bindBidirectional(factorsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                    }
                }
            });
            txtPointSpouse.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(factorsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                        txtPointSpouse.setText(oldValue);
                        Bindings.bindBidirectional(factorsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(factorsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                        txtPointSpouse.setText(newValue);
                        Bindings.bindBidirectional(factorsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
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
                        factorsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (factorsBEAN.getRowId() != null) {
                            factorsDAO.updateFactors(factorsBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            factorsBEAN.setRowId("fa_" + UiiDGenerator.getUIID8());
                            boolean done = factorsDAO.insertFactors(factorsBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listFactors.add(emptyBEAN);
                        addDynaFactors();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridFactors);
//                    }
                    gridFactors.requestFocus();
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
                        if (factorsBEAN.getRowId() != null) {
                            factorsDAO.deleteFactors(factorsBEAN.getRowId());
                        }
                        listFactors.remove(factorsBEAN);
                        if (listFactors.size() > 0) {
                            addDynaFactors();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listFactors.add(emptyBEAN);
                            addDynaFactors();
                        }
                        observValidateFactors.remove(validationAcademicReqSupport);
                        gridFactors.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listFactors.indexOf(factorsBEAN) == 0) {
                gridFactors.add(new Label("Factor"), 0, 0);
                gridFactors.add(new Label("Selection Point"), 1, 0);
                gridFactors.add(new Label("Selection Point With Spouse"), 2, 0);
                gridFactors.add(new Label("Eligibility Point"), 3, 0);
            }
            Bindings.bindBidirectional(txtFactor.textProperty(), factorsBEAN.factorProperty());
            Bindings.bindBidirectional(txtPointSingle.textProperty(), factorsBEAN.selectPointSingleProperty());
            Bindings.bindBidirectional(txtEligibilityPoint.textProperty(), factorsBEAN.eligibilityPointProperty());
            Bindings.bindBidirectional(txtPointSpouse.textProperty(), factorsBEAN.selectPointSpouseProperty());

            gridFactors.add(txtFactor, 0, listFactors.indexOf(factorsBEAN) + 1);
            gridFactors.add(txtPointSingle, 1, listFactors.indexOf(factorsBEAN) + 1);
            gridFactors.add(txtEligibilityPoint, 2, listFactors.indexOf(factorsBEAN) + 1);
            gridFactors.add(txtPointSpouse, 3, listFactors.indexOf(factorsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridFactors.add(hBox, 4, listFactors.indexOf(factorsBEAN) + 1);

        }

    }

    public void initSpouseEnglishAbility() {
        listSpouseEnglishAbility = spouseAbilityEnglishDAO.retrieveSpouseEnglishAbility(MIG_PROVIDER_ID);
        if (listSpouseEnglishAbility.size() > 0) {
            addDynaSpouseEnglishAbility();
        } else {
            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
            listSpouseEnglishAbility.add(emptyBEAN);
            addDynaSpouseEnglishAbility();
        }
    }

    public void addDynaSpouseEnglishAbility() {

        gridSpouseIELTS.getChildren().clear();
        observValidateSpouseEnglishAbility.clear();
        for (MigrationSkillsBEAN spouseAbilityEnglishBEAN : listSpouseEnglishAbility) {
            spouseAbilityEnglishBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox cmbLanguageTest = new ComboBox(RetrieveStaticMasterDAO.getOtherTest());
            cmbLanguageTest.setPromptText("Select Program Field");
            ComboBoxJumpToChar.jumpToChar(cmbLanguageTest);

            TextField txtReadingScore = new TextField();
            txtReadingScore.setPromptText("Score");

            TextField txtListeningScore = new TextField();
            txtListeningScore.setPromptText("Score");

            TextField txtWritingScore = new TextField();
            txtWritingScore.setPromptText("Score");

            TextField txtSpeakingScore = new TextField();
            txtSpeakingScore.setPromptText("Score");

            ComboBox<String> cmbPointType = new ComboBox(SearchConstants.getObsPointType());
            cmbPointType.setPromptText("Select Application Type");
            ComboBoxJumpToChar.jumpToChar(cmbPointType);

            TextField txtPoint = new TextField();
            txtPoint.setPromptText("Enter Point");

            GridPane.setHgrow(cmbLanguageTest, Priority.NEVER);
            GridPane.setHgrow(txtReadingScore, Priority.NEVER);
            GridPane.setHgrow(txtListeningScore, Priority.NEVER);
            GridPane.setHgrow(txtWritingScore, Priority.NEVER);
            GridPane.setHgrow(txtSpeakingScore, Priority.NEVER);
            GridPane.setHgrow(cmbPointType, Priority.NEVER);
            GridPane.setHgrow(txtPoint, Priority.NEVER);

            cmbLanguageTest.setMaxWidth(Double.MAX_VALUE);
            txtReadingScore.setMaxWidth(Double.MAX_VALUE);
            txtListeningScore.setMaxWidth(Double.MAX_VALUE);
            txtWritingScore.setMaxWidth(Double.MAX_VALUE);
            txtSpeakingScore.setMaxWidth(Double.MAX_VALUE);
            cmbPointType.setMaxWidth(Double.MAX_VALUE);
            txtPoint.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationSpouseAbilityEnglishSupport = new ValidationSupport();
            validationSpouseAbilityEnglishSupport.registerValidator(cmbLanguageTest, Validator.createEmptyValidator("Language Test Required"));
            validationSpouseAbilityEnglishSupport.registerValidator(txtReadingScore, Validator.createEmptyValidator("Reading Score Rquired"));
            validationSpouseAbilityEnglishSupport.registerValidator(txtListeningScore, Validator.createEmptyValidator("Listening Score Rquired"));
            validationSpouseAbilityEnglishSupport.registerValidator(txtWritingScore, Validator.createEmptyValidator("Writing Score Rquired"));
            validationSpouseAbilityEnglishSupport.registerValidator(txtSpeakingScore, Validator.createEmptyValidator("Speaking Score Rquired"));
            validationSpouseAbilityEnglishSupport.registerValidator(cmbPointType, Validator.createEmptyValidator("Point Type Rquired"));
            validationSpouseAbilityEnglishSupport.registerValidator(txtPoint, Validator.createEmptyValidator("Point Rquired"));
            observValidateSpouseEnglishAbility.add(validationSpouseAbilityEnglishSupport);
            /* ======================== Validation Listners ==================== */
            txtReadingScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                        txtReadingScore.setText(oldValue);
                        Bindings.bindBidirectional(spouseAbilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                            txtReadingScore.setText(newValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                            txtReadingScore.setText(oldValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtListeningScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                        txtListeningScore.setText(oldValue);
                        Bindings.bindBidirectional(spouseAbilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                            txtListeningScore.setText(newValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                            txtListeningScore.setText(oldValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtWritingScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                        txtWritingScore.setText(oldValue);
                        Bindings.bindBidirectional(spouseAbilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                            txtWritingScore.setText(newValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                            txtWritingScore.setText(oldValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtSpeakingScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                        txtSpeakingScore.setText(oldValue);
                        Bindings.bindBidirectional(spouseAbilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                            txtSpeakingScore.setText(newValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                            txtSpeakingScore.setText(oldValue);
                            Bindings.bindBidirectional(spouseAbilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtPoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                        txtPoint.setText(oldValue);
                        Bindings.bindBidirectional(spouseAbilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(spouseAbilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                        txtPoint.setText(newValue);
                        Bindings.bindBidirectional(spouseAbilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                    }
                }
            });
            txtReadingScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtReadingScore.getText() == null || txtReadingScore.getText().equalsIgnoreCase("")) {
                        } else {
                            txtReadingScore.setText("" + Float.parseFloat(txtReadingScore.getText()));
                        }
                    }
                }
            });
            txtListeningScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtListeningScore.getText() == null || txtListeningScore.getText().equalsIgnoreCase("")) {

                        } else {
                            txtListeningScore.setText("" + Float.parseFloat(txtListeningScore.getText()));
                        }
                    }
                }
            });
            txtWritingScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtWritingScore.getText() == null || txtWritingScore.getText().equalsIgnoreCase("")) {

                        } else {
                            txtWritingScore.setText("" + Float.parseFloat(txtWritingScore.getText()));
                        }
                    }
                }
            });
            txtSpeakingScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtSpeakingScore.getText() == null || txtSpeakingScore.getText().equalsIgnoreCase("")) {

                        } else {
                            txtSpeakingScore.setText("" + Float.parseFloat(txtSpeakingScore.getText()));
                        }
                    }
                }
            });
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationSpouseAbilityEnglishSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationSpouseAbilityEnglishSupport.isInvalid()) {
                        spouseAbilityEnglishBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (spouseAbilityEnglishBEAN.getRowId() != null) {
                            spouseAbilityEnglishDAO.updateSpouseEnglishAbility(spouseAbilityEnglishBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            spouseAbilityEnglishBEAN.setRowId("en_" + UiiDGenerator.getUIID8());
                            boolean done = spouseAbilityEnglishDAO.insertSpouseEnglishAbility(spouseAbilityEnglishBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listSpouseEnglishAbility.add(emptyBEAN);
                        addDynaSpouseEnglishAbility();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridSpouseIELTS);
//                    }
                    gridSpouseIELTS.requestFocus();
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
                        if (spouseAbilityEnglishBEAN.getRowId() != null) {
                            spouseAbilityEnglishDAO.deleteSpouseEnglishAbility(spouseAbilityEnglishBEAN.getRowId());
                        }
                        listSpouseEnglishAbility.remove(spouseAbilityEnglishBEAN);
                        if (listSpouseEnglishAbility.size() > 0) {
                            addDynaSpouseEnglishAbility();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listSpouseEnglishAbility.add(emptyBEAN);
                            addDynaSpouseEnglishAbility();
                        }
                        observValidateSpouseEnglishAbility.remove(validationSpouseAbilityEnglishSupport);
                        gridSpouseIELTS.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) == 0) {
                gridSpouseIELTS.add(new Label("Language Test"), 0, 0);
                gridSpouseIELTS.add(new Label("Reading Score"), 1, 0);
                gridSpouseIELTS.add(new Label("Listening Score"), 2, 0);
                gridSpouseIELTS.add(new Label("Writing Score"), 3, 0);
                gridSpouseIELTS.add(new Label("Speaking Score"), 4, 0);
                gridSpouseIELTS.add(new Label("Point Type"), 5, 0);
                gridSpouseIELTS.add(new Label("Point"), 6, 0);
            }

            Bindings.bindBidirectional(cmbLanguageTest.valueProperty(), spouseAbilityEnglishBEAN.languagTestProperty());
            Bindings.bindBidirectional(txtReadingScore.textProperty(), spouseAbilityEnglishBEAN.readingScoreProperty());
            Bindings.bindBidirectional(txtListeningScore.textProperty(), spouseAbilityEnglishBEAN.listeingScoreProperty());
            Bindings.bindBidirectional(txtWritingScore.textProperty(), spouseAbilityEnglishBEAN.writingScoreProperty());
            Bindings.bindBidirectional(txtSpeakingScore.textProperty(), spouseAbilityEnglishBEAN.speakingScoreProperty());
            Bindings.bindBidirectional(cmbPointType.valueProperty(), spouseAbilityEnglishBEAN.pointTypeProperty());
            Bindings.bindBidirectional(txtPoint.textProperty(), spouseAbilityEnglishBEAN.pointProperty());

            gridSpouseIELTS.add(cmbLanguageTest, 0, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);
            gridSpouseIELTS.add(txtReadingScore, 1, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);
            gridSpouseIELTS.add(txtListeningScore, 2, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);
            gridSpouseIELTS.add(txtWritingScore, 3, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);
            gridSpouseIELTS.add(txtSpeakingScore, 4, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);
            gridSpouseIELTS.add(cmbPointType, 5, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);
            gridSpouseIELTS.add(txtPoint, 6, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridSpouseIELTS.add(hBox, 7, listSpouseEnglishAbility.indexOf(spouseAbilityEnglishBEAN) + 1);

        }
    }

    public void initEnglishAbility() {
        listEnglishAbility = applicantAbilityEnglishDAO.retrieveAbilityEnglish(MIG_PROVIDER_ID);
        if (listEnglishAbility.size() > 0) {
            addDynaEnglishAbility();
        } else {
            MigrationSkillsBEAN coursesBEAN = new MigrationSkillsBEAN();
            listEnglishAbility.add(coursesBEAN);
            addDynaEnglishAbility();
        }
    }

    public void addDynaEnglishAbility() {
        gridAbilityEnglish.getChildren().clear();
        observValidateEnglishAbility.clear();
        for (MigrationSkillsBEAN abilityEnglishBEAN : listEnglishAbility) {
            abilityEnglishBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox<String> cmbApplicationType = new ComboBox(SearchConstants.getListApplicationType());
            cmbApplicationType.setPromptText("Select Application Type");
            ComboBoxJumpToChar.jumpToChar(cmbApplicationType);

            ComboBox cmbLanguageTest = new ComboBox(RetrieveStaticMasterDAO.getOtherTest());
            cmbLanguageTest.setPromptText("Select Program Field");
            ComboBoxJumpToChar.jumpToChar(cmbLanguageTest);

            TextField txtReadingScore = new TextField();
            txtReadingScore.setPromptText("Score");

            TextField txtListeningScore = new TextField();
            txtListeningScore.setPromptText("Score");

            TextField txtWritingScore = new TextField();
            txtWritingScore.setPromptText("Score");

            TextField txtSpeakingScore = new TextField();
            txtSpeakingScore.setPromptText("Score");

            ComboBox<String> cmbPointType = new ComboBox(SearchConstants.getObsPointType());
            cmbPointType.setPromptText("Select Application Type");
            ComboBoxJumpToChar.jumpToChar(cmbPointType);

            TextField txtPoint = new TextField();
            txtPoint.setPromptText("Enter Point");

            GridPane.setHgrow(cmbApplicationType, Priority.NEVER);
            GridPane.setHgrow(cmbLanguageTest, Priority.NEVER);
            GridPane.setHgrow(txtReadingScore, Priority.NEVER);
            GridPane.setHgrow(txtListeningScore, Priority.NEVER);
            GridPane.setHgrow(txtWritingScore, Priority.NEVER);
            GridPane.setHgrow(txtSpeakingScore, Priority.NEVER);
            GridPane.setHgrow(cmbPointType, Priority.NEVER);
            GridPane.setHgrow(txtPoint, Priority.NEVER);

            cmbApplicationType.setMaxWidth(Double.MAX_VALUE);
            cmbLanguageTest.setMaxWidth(Double.MAX_VALUE);
            txtReadingScore.setMaxWidth(Double.MAX_VALUE);
            txtListeningScore.setMaxWidth(Double.MAX_VALUE);
            txtWritingScore.setMaxWidth(Double.MAX_VALUE);
            txtSpeakingScore.setMaxWidth(Double.MAX_VALUE);
            cmbPointType.setMaxWidth(Double.MAX_VALUE);
            txtPoint.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationAbilityEnglishSupport = new ValidationSupport();
            validationAbilityEnglishSupport.registerValidator(cmbApplicationType, Validator.createEmptyValidator("Application Type Required"));
            validationAbilityEnglishSupport.registerValidator(cmbLanguageTest, Validator.createEmptyValidator("Language Test Required"));
            validationAbilityEnglishSupport.registerValidator(txtReadingScore, Validator.createEmptyValidator("Reading Score Rquired"));
            validationAbilityEnglishSupport.registerValidator(txtListeningScore, Validator.createEmptyValidator("Listening Score Rquired"));
            validationAbilityEnglishSupport.registerValidator(txtWritingScore, Validator.createEmptyValidator("Writing Score Rquired"));
            validationAbilityEnglishSupport.registerValidator(txtSpeakingScore, Validator.createEmptyValidator("Speaking Score Rquired"));
            validationAbilityEnglishSupport.registerValidator(cmbPointType, Validator.createEmptyValidator("Point Type Rquired"));
            validationAbilityEnglishSupport.registerValidator(txtPoint, Validator.createEmptyValidator("Point Rquired"));
            observValidateEnglishAbility.add(validationAbilityEnglishSupport);

            /* ======================== Validation Listners ==================== */
            txtReadingScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(abilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                        txtReadingScore.setText(oldValue);
                        Bindings.bindBidirectional(abilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(abilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                            txtReadingScore.setText(newValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(abilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                            txtReadingScore.setText(oldValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.readingScoreProperty(), txtReadingScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtListeningScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(abilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                        txtListeningScore.setText(oldValue);
                        Bindings.bindBidirectional(abilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(abilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                            txtListeningScore.setText(newValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(abilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                            txtListeningScore.setText(oldValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.listeingScoreProperty(), txtListeningScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtWritingScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(abilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                        txtWritingScore.setText(oldValue);
                        Bindings.bindBidirectional(abilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(abilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                            txtWritingScore.setText(newValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(abilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                            txtWritingScore.setText(oldValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.writingScoreProperty(), txtWritingScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtSpeakingScore.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(abilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                        txtSpeakingScore.setText(oldValue);
                        Bindings.bindBidirectional(abilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(abilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                            txtSpeakingScore.setText(newValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(abilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                            txtSpeakingScore.setText(oldValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.speakingScoreProperty(), txtSpeakingScore.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtPoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(abilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                        txtPoint.setText(oldValue);
                        Bindings.bindBidirectional(abilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(abilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                            txtPoint.setText(newValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.pointProperty(), txtPoint.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(abilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                            txtPoint.setText(oldValue);
                            Bindings.bindBidirectional(abilityEnglishBEAN.pointProperty(), txtPoint.textProperty());
                            //  exception.printStackTrace();
                        }
                    }
                }
            });
            txtReadingScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtReadingScore.getText() == null || txtReadingScore.getText().equalsIgnoreCase("")) {
                        } else {
                            txtReadingScore.setText("" + Float.parseFloat(txtReadingScore.getText()));
                        }
                    }
                }
            });
            txtListeningScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtListeningScore.getText() == null || txtListeningScore.getText().equalsIgnoreCase("")) {

                        } else {
                            txtListeningScore.setText("" + Float.parseFloat(txtListeningScore.getText()));
                        }
                    }
                }
            });
            txtWritingScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtWritingScore.getText() == null || txtWritingScore.getText().equalsIgnoreCase("")) {

                        } else {
                            txtWritingScore.setText("" + Float.parseFloat(txtWritingScore.getText()));
                        }
                    }
                }
            });
            txtSpeakingScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtSpeakingScore.getText() == null || txtSpeakingScore.getText().equalsIgnoreCase("")) {

                        } else {
                            txtSpeakingScore.setText("" + Float.parseFloat(txtSpeakingScore.getText()));
                        }
                    }
                }
            });
            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationAbilityEnglishSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationAbilityEnglishSupport.isInvalid()) {
                        abilityEnglishBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (abilityEnglishBEAN.getRowId() != null) {
                            applicantAbilityEnglishDAO.updateAbilityEnglish(abilityEnglishBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            abilityEnglishBEAN.setRowId("ab_" + UiiDGenerator.getUIID8());
                            boolean done = applicantAbilityEnglishDAO.insertAbilityEnglish(abilityEnglishBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listEnglishAbility.add(emptyBEAN);
                        addDynaEnglishAbility();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridAbilityEnglish);
//                    }
                    gridAbilityEnglish.requestFocus();
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
                        if (abilityEnglishBEAN.getRowId() != null) {
                            applicantAbilityEnglishDAO.deleteAbilityEnglish(abilityEnglishBEAN.getRowId());
                        }
                        listEnglishAbility.remove(abilityEnglishBEAN);
                        if (listEnglishAbility.size() > 0) {
                            addDynaEnglishAbility();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listEnglishAbility.add(emptyBEAN);
                            addDynaEnglishAbility();
                        }
                        observValidateEnglishAbility.remove(validationAbilityEnglishSupport);
                        gridAbilityEnglish.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listEnglishAbility.indexOf(abilityEnglishBEAN) == 0) {
                gridAbilityEnglish.add(new Label("Application Type"), 0, 0);
                gridAbilityEnglish.add(new Label("Language Test"), 1, 0);
                gridAbilityEnglish.add(new Label("Reading Score"), 2, 0);
                gridAbilityEnglish.add(new Label("Listening Score"), 3, 0);
                gridAbilityEnglish.add(new Label("Writing Score"), 4, 0);
                gridAbilityEnglish.add(new Label("Speaking Score"), 5, 0);
                gridAbilityEnglish.add(new Label("Point Type"), 6, 0);
                gridAbilityEnglish.add(new Label("Point"), 7, 0);
            }
            Bindings.bindBidirectional(cmbApplicationType.valueProperty(), abilityEnglishBEAN.applicationTypeProperty());
            Bindings.bindBidirectional(cmbLanguageTest.valueProperty(), abilityEnglishBEAN.languagTestProperty());
            Bindings.bindBidirectional(txtReadingScore.textProperty(), abilityEnglishBEAN.readingScoreProperty());
            Bindings.bindBidirectional(txtListeningScore.textProperty(), abilityEnglishBEAN.listeingScoreProperty());
            Bindings.bindBidirectional(txtWritingScore.textProperty(), abilityEnglishBEAN.writingScoreProperty());
            Bindings.bindBidirectional(txtSpeakingScore.textProperty(), abilityEnglishBEAN.speakingScoreProperty());
            Bindings.bindBidirectional(cmbPointType.valueProperty(), abilityEnglishBEAN.pointTypeProperty());
            Bindings.bindBidirectional(txtPoint.textProperty(), abilityEnglishBEAN.pointProperty());

            gridAbilityEnglish.add(cmbApplicationType, 0, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(cmbLanguageTest, 1, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(txtReadingScore, 2, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(txtListeningScore, 3, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(txtWritingScore, 4, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(txtSpeakingScore, 5, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(cmbPointType, 6, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);
            gridAbilityEnglish.add(txtPoint, 7, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAbilityEnglish.add(hBox, 8, listEnglishAbility.indexOf(abilityEnglishBEAN) + 1);

        }
    }

    public void initAgePoints() {
        listAgePoints = agePointsDAO.retrieveAgePoints(MIG_PROVIDER_ID);
        if (listAgePoints.size() > 0) {
            addDynaAgePoints();
        } else {
            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
            listAgePoints.add(emptyBEAN);
            addDynaAgePoints();
        }
    }

    public void addDynaAgePoints() {

        gridAge.getChildren().clear();
        observValidateAgePoints.clear();
        for (MigrationSkillsBEAN agePointsBEAN : listAgePoints) {
            agePointsBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox<String> cmbAge = new ComboBox();
            cmbAge.setPromptText("Select Age");
            ComboBoxJumpToChar.jumpToChar(cmbAge);
            ObservableList<String> listAges = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getAllages());
            cmbAge.getItems().addAll(listAges);

            TextField txtSelctionPointSingle = new TextField();
            txtSelctionPointSingle.setPromptText("Point");

            TextField txtSelctionPointSpouse = new TextField();
            txtSelctionPointSpouse.setPromptText("Point With Spouse");

            TextField txtEligiblePoint = new TextField();
            txtEligiblePoint.setPromptText("Select Eligibility Point");

            GridPane.setHgrow(cmbAge, Priority.NEVER);
            GridPane.setHgrow(txtSelctionPointSingle, Priority.NEVER);
            GridPane.setHgrow(txtSelctionPointSpouse, Priority.NEVER);
            GridPane.setHgrow(txtEligiblePoint, Priority.NEVER);

            cmbAge.setMaxWidth(Double.MAX_VALUE);
            txtSelctionPointSingle.setMaxWidth(Double.MAX_VALUE);
            txtSelctionPointSpouse.setMaxWidth(Double.MAX_VALUE);
            txtEligiblePoint.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationAgeSupport = new ValidationSupport();
            validationAgeSupport.registerValidator(cmbAge, Validator.createEmptyValidator("Age Required"));
            validationAgeSupport.registerValidator(txtSelctionPointSingle, Validator.createEmptyValidator("Selection Point Single Required"));
            validationAgeSupport.registerValidator(txtSelctionPointSpouse, Validator.createEmptyValidator("Selection Point Spouse Rquired"));
            validationAgeSupport.registerValidator(txtEligiblePoint, Validator.createEmptyValidator("Eligibility Point Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            observValidateAgePoints.add(validationAgeSupport);
            txtSelctionPointSingle.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(agePointsBEAN.selectPointSingleProperty(), txtSelctionPointSingle.textProperty());
                        txtSelctionPointSingle.setText(oldValue);
                        Bindings.bindBidirectional(agePointsBEAN.selectPointSingleProperty(), txtSelctionPointSingle.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(agePointsBEAN.selectPointSingleProperty(), txtSelctionPointSingle.textProperty());
                        txtSelctionPointSingle.setText(newValue);
                        Bindings.bindBidirectional(agePointsBEAN.selectPointSingleProperty(), txtSelctionPointSingle.textProperty());
                    }
                }
            });
            txtSelctionPointSpouse.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(agePointsBEAN.selectPointSpouseProperty(), txtSelctionPointSpouse.textProperty());
                        txtSelctionPointSpouse.setText(oldValue);
                        Bindings.bindBidirectional(agePointsBEAN.selectPointSpouseProperty(), txtSelctionPointSpouse.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(agePointsBEAN.selectPointSpouseProperty(), txtSelctionPointSpouse.textProperty());
                        txtSelctionPointSpouse.setText(newValue);
                        Bindings.bindBidirectional(agePointsBEAN.selectPointSpouseProperty(), txtSelctionPointSpouse.textProperty());
                    }
                }
            });
            txtEligiblePoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(agePointsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                        txtEligiblePoint.setText(oldValue);
                        Bindings.bindBidirectional(agePointsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(agePointsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                        txtEligiblePoint.setText(newValue);
                        Bindings.bindBidirectional(agePointsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                    }
                }
            });

            /* ======================== Button Actions ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationAgeSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationAgeSupport.isInvalid()) {
                        agePointsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (agePointsBEAN.getRowId() != null) {
                            agePointsDAO.updateAgePoints(agePointsBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            agePointsBEAN.setRowId("ag_" + UiiDGenerator.getUIID8());
                            boolean done = agePointsDAO.insertAgePoints(agePointsBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listAgePoints.add(emptyBEAN);
                        addDynaAgePoints();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridAge);
//                    }
                    gridAge.requestFocus();
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
                        if (agePointsBEAN.getRowId() != null) {
                            agePointsDAO.deleteAgePoints(agePointsBEAN.getRowId());
                        }
                        listAgePoints.remove(agePointsBEAN);
                        if (listAgePoints.size() > 0) {
                            addDynaAgePoints();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listAgePoints.add(emptyBEAN);
                            addDynaAgePoints();
                        }
                        observValidateAgePoints.remove(validationAgeSupport);
                        gridAge.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listAgePoints.indexOf(agePointsBEAN) == 0) {
                gridAge.add(new Label("Age"), 0, 0);
                gridAge.add(new Label("Selection Point Single"), 1, 0);
                gridAge.add(new Label("Selection Point Spouse"), 2, 0);
                gridAge.add(new Label("Eligibility Point"), 3, 0);
            }
            Bindings.bindBidirectional(cmbAge.valueProperty(), agePointsBEAN.ageProperty());
            Bindings.bindBidirectional(txtSelctionPointSingle.textProperty(), agePointsBEAN.selectPointSingleProperty());
            Bindings.bindBidirectional(txtSelctionPointSpouse.textProperty(), agePointsBEAN.selectPointSpouseProperty());
            Bindings.bindBidirectional(txtEligiblePoint.textProperty(), agePointsBEAN.eligibilityPointProperty());

            gridAge.add(cmbAge, 0, listAgePoints.indexOf(agePointsBEAN) + 1);
            gridAge.add(txtSelctionPointSingle, 1, listAgePoints.indexOf(agePointsBEAN) + 1);
            gridAge.add(txtSelctionPointSpouse, 2, listAgePoints.indexOf(agePointsBEAN) + 1);
            gridAge.add(txtEligiblePoint, 3, listAgePoints.indexOf(agePointsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAge.add(hBox, 4, listAgePoints.indexOf(agePointsBEAN) + 1);

        }
    }

    public void initOverseasExperience() {
        listOverseasExperience = overseasExperienceDAO.retrieveOverseasExperience(MIG_PROVIDER_ID);
        if (listOverseasExperience.size() > 0) {
            addDynaOverseasExperienceRows();
        } else {
            MigrationSkillsBEAN coursesBEAN = new MigrationSkillsBEAN();
            listOverseasExperience.add(coursesBEAN);
            addDynaOverseasExperienceRows();
        }
    }

    public void addDynaOverseasExperienceRows() {

        gridOverseasExp.getChildren().clear();
        observValidateOverseasExperience.clear();
        for (MigrationSkillsBEAN oversearExperienceBEAN : listOverseasExperience) {
            oversearExperienceBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox<String> cmbExperience = new ComboBox();
            cmbExperience.setPromptText("Select Program Level");
            ComboBoxJumpToChar.jumpToChar(cmbExperience);
            cmbExperience.getItems().addAll(RetrieveStaticMasterDAO.getDuration());

            ComboBox cmbIeltsScore = new ComboBox(RetrieveStaticMasterDAO.getScore());
            cmbIeltsScore.setPromptText("Select Score ");
            ComboBoxJumpToChar.jumpToChar(cmbIeltsScore);

            TextField txtEligiblePoint = new TextField();
            txtEligiblePoint.setPromptText("Point");

            GridPane.setHgrow(cmbExperience, Priority.NEVER);
            GridPane.setHgrow(cmbIeltsScore, Priority.NEVER);
            GridPane.setHgrow(txtEligiblePoint, Priority.NEVER);

            cmbExperience.setMaxWidth(Double.MAX_VALUE);
            cmbIeltsScore.setMaxWidth(Double.MAX_VALUE);
            txtEligiblePoint.setMaxWidth(Double.MAX_VALUE);

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
            validationAcademicReqSupport.registerValidator(cmbExperience, Validator.createEmptyValidator("Experience Required"));
            validationAcademicReqSupport.registerValidator(cmbIeltsScore, Validator.createEmptyValidator("IELTS Score Required"));
            validationAcademicReqSupport.registerValidator(txtEligiblePoint, Validator.createEmptyValidator("Eligibility Point Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            observValidateOverseasExperience.add(validationAcademicReqSupport);

            txtEligiblePoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(oversearExperienceBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                        txtEligiblePoint.setText(oldValue);
                        Bindings.bindBidirectional(oversearExperienceBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(oversearExperienceBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                        txtEligiblePoint.setText(newValue);
                        Bindings.bindBidirectional(oversearExperienceBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
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
                        oversearExperienceBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (oversearExperienceBEAN.getRowId() != null) {
                            overseasExperienceDAO.updateOverseasExperience(oversearExperienceBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            oversearExperienceBEAN.setRowId("ex_" + UiiDGenerator.getUIID8());
                            boolean done = overseasExperienceDAO.insertOverseasExperience(oversearExperienceBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listOverseasExperience.add(emptyBEAN);
                        addDynaOverseasExperienceRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridOverseasExp);
//                    }
                    gridOverseasExp.requestFocus();
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
                        if (oversearExperienceBEAN.getRowId() != null) {
                            overseasExperienceDAO.deleteOverseasExperience(oversearExperienceBEAN.getRowId());
                        }
                        listOverseasExperience.remove(oversearExperienceBEAN);
                        if (listOverseasExperience.size() > 0) {
                            addDynaOverseasExperienceRows();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listOverseasExperience.add(emptyBEAN);
                            addDynaOverseasExperienceRows();
                        }
                        observValidateOverseasExperience.remove(validationAcademicReqSupport);
                        gridOverseasExp.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listOverseasExperience.indexOf(oversearExperienceBEAN) == 0) {
                gridOverseasExp.add(new Label("Experience"), 0, 0);
                gridOverseasExp.add(new Label("IELTS Score"), 1, 0);
                gridOverseasExp.add(new Label("Eligibility Point"), 2, 0);
            }
            Bindings.bindBidirectional(cmbExperience.valueProperty(), oversearExperienceBEAN.experienceProperty());
            Bindings.bindBidirectional(cmbIeltsScore.valueProperty(), oversearExperienceBEAN.ieltsScoreProperty());
            Bindings.bindBidirectional(txtEligiblePoint.textProperty(), oversearExperienceBEAN.eligibilityPointProperty());

            gridOverseasExp.add(cmbExperience, 0, listOverseasExperience.indexOf(oversearExperienceBEAN) + 1);
            gridOverseasExp.add(cmbIeltsScore, 1, listOverseasExperience.indexOf(oversearExperienceBEAN) + 1);
            gridOverseasExp.add(txtEligiblePoint, 2, listOverseasExperience.indexOf(oversearExperienceBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridOverseasExp.add(hBox, 3, listOverseasExperience.indexOf(oversearExperienceBEAN) + 1);

        }
    }

    public void initEducationalSkillsWithIELTS() {
        listEducationalSkillsIelts = educationalSkillsWithIeltsDAO.retrieveEducationalSkillsWidIelts(MIG_PROVIDER_ID);
        if (listEducationalSkillsIelts.size() > 0) {
            addDynaEducationalSkillsWithIELTSRows();
        } else {
            MigrationSkillsBEAN coursesBEAN = new MigrationSkillsBEAN();
            listEducationalSkillsIelts.add(coursesBEAN);
            addDynaEducationalSkillsWithIELTSRows();
        }
    }

    public void addDynaEducationalSkillsWithIELTSRows() {

        gridEduSkillsWithIELTS.getChildren().clear();
        observValidateEducationalSkillWidIelts.clear();
        for (MigrationSkillsBEAN eduSkillIeltsBEAN : listEducationalSkillsIelts) {
            eduSkillIeltsBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox<String> cmbProgramLevel = new ComboBox();
            cmbProgramLevel.setPromptText("Select Program Level");
            ComboBoxJumpToChar.jumpToChar(cmbProgramLevel);
            ObservableList<String> listProgramLevel = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getProgramLevels());
            cmbProgramLevel.getItems().addAll(listProgramLevel);

            ComboBox cmbProgramField = new ComboBox();
            cmbProgramField.setPromptText("Select Program Field");
            ComboBoxJumpToChar.jumpToChar(cmbProgramField);

            ComboBox cmbIeltsScore = new ComboBox(RetrieveStaticMasterDAO.getScore());
            cmbIeltsScore.setPromptText("Select Duration");
            ComboBoxJumpToChar.jumpToChar(cmbIeltsScore);

            TextField txtEligibilityPoint = new TextField();
            txtEligibilityPoint.setPromptText("Select Duration");

            GridPane.setHgrow(cmbProgramLevel, Priority.NEVER);
            GridPane.setHgrow(cmbProgramField, Priority.NEVER);
            GridPane.setHgrow(cmbIeltsScore, Priority.NEVER);
            GridPane.setHgrow(txtEligibilityPoint, Priority.NEVER);

            cmbProgramLevel.setMaxWidth(Double.MAX_VALUE);
            cmbProgramField.setMaxWidth(Double.MAX_VALUE);
            cmbIeltsScore.setMaxWidth(Double.MAX_VALUE);
            txtEligibilityPoint.setMaxWidth(Double.MAX_VALUE);

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
            validationAcademicReqSupport.registerValidator(cmbIeltsScore, Validator.createEmptyValidator("IELTS Score Rquired"));
            validationAcademicReqSupport.registerValidator(txtEligibilityPoint, Validator.createEmptyValidator("Eligibility Point Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            observValidateEducationalSkillWidIelts.add(validationAcademicReqSupport);
//    txtPointSingle.textProperty().addListener(new ChangeListener<String>() {
//
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    if (newValue == null || newValue.equalsIgnoreCase("")) {
//
//                    } else if (!(newValue.matches("[0-9]*"))) {
//                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
//                        txtPointSingle.setText(oldValue);
//                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
//                    } else {
//                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
//                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
//                        txtPointSingle.setText(newValue);
//                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
//                    }
//                }
//            });
//            txtPointSpouse.textProperty().addListener(new ChangeListener<String>() {
//
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    if (newValue == null || newValue.equalsIgnoreCase("")) {
//
//                    } else if (!(newValue.matches("[0-9]*"))) {
//                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
//                        txtPointSpouse.setText(oldValue);
//                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
//                    } else {
//                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
//                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
//                        txtPointSpouse.setText(newValue);
//                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
//                    }
//                }
//            });
            txtEligibilityPoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(eduSkillIeltsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                        txtEligibilityPoint.setText(oldValue);
                        Bindings.bindBidirectional(eduSkillIeltsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(eduSkillIeltsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                        txtEligibilityPoint.setText(newValue);
                        Bindings.bindBidirectional(eduSkillIeltsBEAN.eligibilityPointProperty(), txtEligibilityPoint.textProperty());
                    }
                }
            });
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
                        eduSkillIeltsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (eduSkillIeltsBEAN.getRowId() != null) {
                            educationalSkillsWithIeltsDAO.updateEducationalSkillsWidIelts(eduSkillIeltsBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            eduSkillIeltsBEAN.setRowId("sk_" + UiiDGenerator.getUIID8());
                            boolean done = educationalSkillsWithIeltsDAO.insertEducationalSkillsWidIelts(eduSkillIeltsBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listEducationalSkillsIelts.add(emptyBEAN);
                        addDynaEducationalSkillsWithIELTSRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridEduSkillsWithIELTS);
//                    }
                    gridEduSkillsWithIELTS.requestFocus();
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
                        if (eduSkillIeltsBEAN.getRowId() != null) {
                            educationalSkillsWithIeltsDAO.deleteEducationalSkillsWidIelts(eduSkillIeltsBEAN.getRowId());
                        }
                        listEducationalSkillsIelts.remove(eduSkillIeltsBEAN);
                        if (listEducationalSkillsIelts.size() > 0) {
                            addDynaEducationalSkillsWithIELTSRows();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listEducationalSkillsIelts.add(emptyBEAN);
                            addDynaEducationalSkillsWithIELTSRows();
                        }
                        observValidateEducationalSkillWidIelts.remove(validationAcademicReqSupport);
                        gridEduSkillsWithIELTS.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listEducationalSkillsIelts.indexOf(eduSkillIeltsBEAN) == 0) {
                gridEduSkillsWithIELTS.add(new Label("Program Level"), 0, 0);
                gridEduSkillsWithIELTS.add(new Label("Program Field"), 1, 0);
                gridEduSkillsWithIELTS.add(new Label("IELTS Score"), 2, 0);
                gridEduSkillsWithIELTS.add(new Label("Eligibility Point"), 3, 0);
            }
            Bindings.bindBidirectional(cmbProgramLevel.valueProperty(), eduSkillIeltsBEAN.educationLevelProperty());
            Bindings.bindBidirectional(cmbProgramField.valueProperty(), eduSkillIeltsBEAN.educationFieldProperty());
            Bindings.bindBidirectional(cmbIeltsScore.valueProperty(), eduSkillIeltsBEAN.ieltsScoreProperty());
            Bindings.bindBidirectional(txtEligibilityPoint.textProperty(), eduSkillIeltsBEAN.eligibilityPointProperty());

            gridEduSkillsWithIELTS.add(cmbProgramLevel, 0, listEducationalSkillsIelts.indexOf(eduSkillIeltsBEAN) + 1);
            gridEduSkillsWithIELTS.add(cmbProgramField, 1, listEducationalSkillsIelts.indexOf(eduSkillIeltsBEAN) + 1);
            gridEduSkillsWithIELTS.add(cmbIeltsScore, 2, listEducationalSkillsIelts.indexOf(eduSkillIeltsBEAN) + 1);
            gridEduSkillsWithIELTS.add(txtEligibilityPoint, 3, listEducationalSkillsIelts.indexOf(eduSkillIeltsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridEduSkillsWithIELTS.add(hBox, 4, listEducationalSkillsIelts.indexOf(eduSkillIeltsBEAN) + 1);

        }
    }

    public void initEducationalSkills() {

        listEducationalSkills = educationalSkillsDAO.retrieveEducationalSkills(MIG_PROVIDER_ID);
        if (listEducationalSkills.size() > 0) {
            addDynaEducationalSkillsRows();
        } else {
            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
            listEducationalSkills.add(emptyBEAN);
            addDynaEducationalSkillsRows();
        }
    }

    public void addDynaEducationalSkillsRows() {

        gridEduSkills.getChildren().clear();
        observValidateEducationalSkills.clear();
        for (MigrationSkillsBEAN educationalSkillsBEAN : listEducationalSkills) {
            educationalSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);

            ComboBox<String> cmbProgramLevel = new ComboBox();
            cmbProgramLevel.setPromptText("Select Program Level");
            ComboBoxJumpToChar.jumpToChar(cmbProgramLevel);
            ObservableList<String> listProgramLevel = FXCollections.observableArrayList(RetrieveStaticMasterDAO.getProgramLevels());
            cmbProgramLevel.getItems().addAll(listProgramLevel);

            ComboBox cmbProgramField = new ComboBox();
            cmbProgramField.setPromptText("Select Program Field");
            ComboBoxJumpToChar.jumpToChar(cmbProgramField);

            TextField txtPointSingle = new TextField();
            txtPointSingle.setPromptText("Point");

            TextField txtPointSpouse = new TextField();
            txtPointSingle.setPromptText("Point");

            TextField txtEligiblePoint = new TextField();
            txtPointSingle.setPromptText("Point");

            GridPane.setHgrow(cmbProgramLevel, Priority.NEVER);
            GridPane.setHgrow(cmbProgramField, Priority.NEVER);
            GridPane.setHgrow(txtPointSingle, Priority.NEVER);
            GridPane.setHgrow(txtPointSpouse, Priority.NEVER);
            GridPane.setHgrow(txtEligiblePoint, Priority.NEVER);

            cmbProgramLevel.setMaxWidth(Double.MAX_VALUE);
            cmbProgramField.setMaxWidth(Double.MAX_VALUE);
            txtPointSingle.setMaxWidth(Double.MAX_VALUE);
            txtPointSpouse.setMaxWidth(Double.MAX_VALUE);
            txtEligiblePoint.setMaxWidth(Double.MAX_VALUE);

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
            validationAcademicReqSupport.registerValidator(txtPointSingle, Validator.createEmptyValidator("Selection Point Single Rquired"));
            validationAcademicReqSupport.registerValidator(txtPointSpouse, Validator.createEmptyValidator("Selection Point Spouse Rquired"));
            validationAcademicReqSupport.registerValidator(txtEligiblePoint, Validator.createEmptyValidator("Eligible Point Rquired"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            observValidateEducationalSkills.add(validationAcademicReqSupport);
            txtPointSingle.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                        txtPointSingle.setText(oldValue);
                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                        txtPointSingle.setText(newValue);
                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSingleProperty(), txtPointSingle.textProperty());
                    }
                }
            });
            txtPointSpouse.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                        txtPointSpouse.setText(oldValue);
                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                        txtPointSpouse.setText(newValue);
                        Bindings.bindBidirectional(educationalSkillsBEAN.selectPointSpouseProperty(), txtPointSpouse.textProperty());
                    }
                }
            });
            txtEligiblePoint.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(educationalSkillsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                        txtEligiblePoint.setText(oldValue);
                        Bindings.bindBidirectional(educationalSkillsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                    } else {
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(educationalSkillsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                        txtEligiblePoint.setText(newValue);
                        Bindings.bindBidirectional(educationalSkillsBEAN.eligibilityPointProperty(), txtEligiblePoint.textProperty());
                    }
                }
            });
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
                        educationalSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        if (educationalSkillsBEAN.getRowId() != null) {
                            educationalSkillsDAO.updateEducationalSkills(educationalSkillsBEAN);
                        } else if (MIG_PROVIDER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            educationalSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            boolean done = educationalSkillsDAO.insertEducationalSkills(educationalSkillsBEAN);
                        }
                        MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                        listEducationalSkills.add(emptyBEAN);
                        addDynaEducationalSkillsRows();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridEduSkills);
//                    }
                    gridEduSkills.requestFocus();
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
                        if (educationalSkillsBEAN.getRowId() != null) {
                            educationalSkillsDAO.deleteEducationalSkills(educationalSkillsBEAN.getRowId());
                        }
                        listEducationalSkills.remove(educationalSkillsBEAN);
                        if (listEducationalSkills.size() > 0) {
                            addDynaEducationalSkillsRows();
                        } else {
                            MigrationSkillsBEAN emptyBEAN = new MigrationSkillsBEAN();
                            listEducationalSkills.add(emptyBEAN);
                            addDynaEducationalSkillsRows();
                        }
                        observValidateEducationalSkills.remove(validationAcademicReqSupport);
                        gridEduSkills.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listEducationalSkills.indexOf(educationalSkillsBEAN) == 0) {
                gridEduSkills.add(new Label("Program Level"), 0, 0);
                gridEduSkills.add(new Label("Program Field"), 1, 0);
                gridEduSkills.add(new Label("Selection Point Single"), 2, 0);
                gridEduSkills.add(new Label("Selection Point Spouse"), 3, 0);
                gridEduSkills.add(new Label("Eligibility Point "), 4, 0);

            }
            Bindings.bindBidirectional(cmbProgramLevel.valueProperty(), educationalSkillsBEAN.educationLevelProperty());
            Bindings.bindBidirectional(cmbProgramField.valueProperty(), educationalSkillsBEAN.educationFieldProperty());
            Bindings.bindBidirectional(txtPointSingle.textProperty(), educationalSkillsBEAN.selectPointSingleProperty());
            Bindings.bindBidirectional(txtPointSpouse.textProperty(), educationalSkillsBEAN.selectPointSpouseProperty());
            Bindings.bindBidirectional(txtEligiblePoint.textProperty(), educationalSkillsBEAN.eligibilityPointProperty());

            gridEduSkills.add(cmbProgramLevel, 0, listEducationalSkills.indexOf(educationalSkillsBEAN) + 1);
            gridEduSkills.add(cmbProgramField, 1, listEducationalSkills.indexOf(educationalSkillsBEAN) + 1);
            gridEduSkills.add(txtPointSingle, 2, listEducationalSkills.indexOf(educationalSkillsBEAN) + 1);
            gridEduSkills.add(txtPointSpouse, 3, listEducationalSkills.indexOf(educationalSkillsBEAN) + 1);
            gridEduSkills.add(txtEligiblePoint, 4, listEducationalSkills.indexOf(educationalSkillsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridEduSkills.add(hBox, 5, listEducationalSkills.indexOf(educationalSkillsBEAN) + 1);

        }

    }

    public void addButtonListners() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isEducationSkillValid = false;
                boolean isEducationSkillWidIELTSValid = false;
                boolean isOverseasExperienceValid = false;
                boolean isAgePointsValid = false;
                boolean isAbilityInEnglishValid = false;
                boolean isFactorsValid = false;
                boolean isSpouseEducationValid = false;
                boolean isSpouseAbilityEnglishValid = false;

                /* ======================== Validate Basic Info ==================== */
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }
                /* ======================== 1. Validate Education Skills ==================== */
                for (ValidationSupport support : observValidateEducationalSkills) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isEducationSkillValid = support.isInvalid();
                    }
                }
                /* ======================== 2. Validate Education Skills  With IELTS ==================== */
                for (ValidationSupport support : observValidateEducationalSkillWidIelts) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isEducationSkillWidIELTSValid = support.isInvalid();
                    }
                }
                /* ======================== 3. Validate Overseas Experience ==================== */
                for (ValidationSupport support : observValidateOverseasExperience) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isOverseasExperienceValid = support.isInvalid();
                    }
                }
                /* ======================== 4. Validate Age Points ==================== */
                for (ValidationSupport support : observValidateAgePoints) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isAgePointsValid = support.isInvalid();
                    }
                }
                /* ======================== 5. Validate Ability In English ==================== */
                for (ValidationSupport support : observValidateEnglishAbility) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isAbilityInEnglishValid = support.isInvalid();
                    }
                }
                /* ======================== 6. Validate Factors ==================== */
                for (ValidationSupport support : observValidateFactors) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isFactorsValid = support.isInvalid();
                    }
                }
                /* ======================== 7. Validate Spouse Education ==================== */
                for (ValidationSupport support : observValidateSpouseEduSkills) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isSpouseEducationValid = support.isInvalid();
                    }
                }
                /* ======================== 8. Validate Spouse Ability English Based On IELTS ==================== */
                for (ValidationSupport support : observValidateSpouseEnglishAbility) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isSpouseAbilityEnglishValid = support.isInvalid();
                    }
                }
                /* ======================== Save Basic Info ==================== */
                if (!validationSupport.isInvalid()) {

                    if (migrationBasicBEAN.getMigraProviderId() == null || migrationBasicBEAN.getMigraProviderId().equalsIgnoreCase("")) {
                        /* ======================== Generate Employer Id ==================== */
                        MIG_PROVIDER_ID = "pv_" + UiiDGenerator.getUIID8();
                        migrationBasicBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                        migrationBasicBEAN.setCreatedUser(CUR_USERNAME);
                        boolean done = migrationBasicDAO.insertBasicInfo(migrationBasicBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Migration Provider Details Saved Successfully!")
                                    .text("Migration Provider Details Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Migration Provider Details Save Failed!")
                                    .text("Migration Provider Details Save Failed!")
                                    .showError();
                            migrationBasicBEAN.setMigraProviderId(null);
                        }
                    } else {
                        migrationBasicBEAN.setUpdatedUser(CUR_USERNAME);
                        boolean done = migrationBasicDAO.updateBasicInfo(migrationBasicBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Migration Provider Details Updated Successfully!")
                                    .text("Migration Provider Details Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Migration Provider Details Update Failed!")
                                    .text("Migration Provider Details Update Failed!")
                                    .showError();
                        }
                    }

                }
                /* ======================== 1 Save Education Skills ==================== */
                if (!isEducationSkillValid && !validationSupport.isInvalid()) {
                    System.out.println("Save Education Skills");
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listEducationalSkills) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            educationalSkillsDAO.insertEducationalSkills(migrationSkillsBEAN);
                        } else {
                            educationalSkillsDAO.updateEducationalSkills(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 2 Save Education Skills  With IELTS ==================== */
                if (!isEducationSkillWidIELTSValid && !validationSupport.isInvalid()) {
                    System.out.println("Save Education Skills");
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listEducationalSkillsIelts) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            educationalSkillsWithIeltsDAO.insertEducationalSkillsWidIelts(migrationSkillsBEAN);
                        } else {
                            educationalSkillsWithIeltsDAO.updateEducationalSkillsWidIelts(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 3 Save Overseas Experience ==================== */
                if (!isOverseasExperienceValid && !validationSupport.isInvalid()) {
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listOverseasExperience) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ov_" + UiiDGenerator.getUIID8());
                            overseasExperienceDAO.insertOverseasExperience(migrationSkillsBEAN);
                        } else {
                            overseasExperienceDAO.updateOverseasExperience(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 4 Save Age Points ==================== */
                if (!isAgePointsValid && !validationSupport.isInvalid()) {
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listAgePoints) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ag_" + UiiDGenerator.getUIID8());
                            agePointsDAO.insertAgePoints(migrationSkillsBEAN);
                        } else {
                            agePointsDAO.updateAgePoints(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 5 Save Ability In English ==================== */
                if (!isAbilityInEnglishValid && !validationSupport.isInvalid()) {
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listEnglishAbility) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            applicantAbilityEnglishDAO.insertAbilityEnglish(migrationSkillsBEAN);
                        } else {
                            applicantAbilityEnglishDAO.updateAbilityEnglish(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 6 Save Factors ==================== */
                if (!isFactorsValid && !validationSupport.isInvalid()) {
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listFactors) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            factorsDAO.insertFactors(migrationSkillsBEAN);
                        } else {
                            factorsDAO.updateFactors(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 7 Save Spouse Education Skills ==================== */
                if (!isSpouseEducationValid && !validationSupport.isInvalid()) {
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listSpouseEduSkills) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            spouseEducationSkillsDAO.insertEducationSkills(migrationSkillsBEAN);
                        } else {
                            spouseEducationSkillsDAO.updateEducationSkills(migrationSkillsBEAN);
                        }
                    }
                }
                /* ======================== 8 Save Spouse Ability English With IELTS ==================== */
                if (!isSpouseAbilityEnglishValid && !validationSupport.isInvalid()) {
                    for (MigrationSkillsBEAN migrationSkillsBEAN : listSpouseEnglishAbility) {
                        if (migrationSkillsBEAN.getRowId() == null || migrationSkillsBEAN.getRowId().equalsIgnoreCase("")) {
                            migrationSkillsBEAN.setMigraProviderId(MIG_PROVIDER_ID);
                            migrationSkillsBEAN.setRowId("ed_" + UiiDGenerator.getUIID8());
                            spouseAbilityEnglishDAO.insertSpouseEnglishAbility(migrationSkillsBEAN);
                        } else {
                            spouseAbilityEnglishDAO.updateSpouseEnglishAbility(migrationSkillsBEAN);
                        }
                    }
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MIG_PROVIDER_ID = null;
                migrationBasicBEAN = new MigrationBasicBEAN();
                bindBasicInfo();
                initEducationalSkills();
                initEducationalSkillsWithIELTS();
                initOverseasExperience();
                initAgePoints();
                initEnglishAbility();
                initSpouseEnglishAbility();
                initFactors();
                initSpouseEducationSkills();

            }
        });
    }

    public void addAdditionalFeatures() {
        ComboBoxJumpToChar.jumpToChar(cmbCountry);
        ComboBoxJumpToChar.jumpToChar(cmbSubClass);
        ComboBoxJumpToChar.jumpToChar(cmbOccupationCat);
    }

    public void makeMandaoryDecorations() {
        /* ======================== Validation Support ==================== */
        validationSupport.registerValidator(cmbCountry, Validator.createEmptyValidator("Country Required"));
        validationSupport.registerValidator(cmbSubClass, Validator.createEmptyValidator("Sub Class Required"));
        validationSupport.registerValidator(cmbOccupationCat, Validator.createEmptyValidator("Occupation Category Required"));
    }

    public void bindBasicInfo() {
        Bindings.bindBidirectional(cmbCountry.valueProperty(), migrationBasicBEAN.countryProperty());
        Bindings.bindBidirectional(cmbSubClass.valueProperty(), migrationBasicBEAN.subClassProperty());
        Bindings.bindBidirectional(cmbOccupationCat.valueProperty(), migrationBasicBEAN.ocupationCatProperty());
    }

    public void initializeMasterData() {
        cmbCountry.getItems().addAll(masterData.retrieveAllCountries());
        cmbSubClass.getItems().addAll(masterData.retrieveSubClass());
        cmbOccupationCat.getItems().addAll(masterData.retrieveOccupationCategory());
    }

}

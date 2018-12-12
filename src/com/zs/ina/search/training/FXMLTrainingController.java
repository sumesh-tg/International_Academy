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
package com.zs.ina.search.training;

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
import com.zs.ina.search.common.bean.CampusBEAN;
import com.zs.ina.search.common.bean.ContactsBEAN;
import com.zs.ina.search.common.bean.ProcessFeeBEAN;
import com.zs.ina.search.master.common.GlobalSearchMasterData;
import com.zs.ina.search.master.common.SearchConstants;
import com.zs.ina.search.master.university.dao.UniversityBEAN;
import com.zs.ina.search.master.university.dao.UniversityDAO;
import com.zs.ina.search.training.dao.TrainingBEAN;
import com.zs.ina.search.training.dao.TrainingDAO;
import com.zs.ina.search.training.dynamics.TrainCampusDAO;
import com.zs.ina.search.training.dynamics.TrainContactDAO;
import com.zs.ina.search.training.dynamics.TrainInstallmentBEAN;
import com.zs.ina.search.training.dynamics.TrainProcessFeeDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;
import org.controlsfx.validation.ValidationSupport;
import org.springframework.context.ApplicationContext;
import com.zs.ina.search.training.dynamics.TrainInstallmentDAO;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jidefx.utils.converter.DefaultObjectConverter;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLTrainingController implements Initializable {
    
    @FXML
    private TextArea txtArDesc;
    @FXML
    private TextArea txtArRemarks;
    @FXML
    private ComboBox<String> cmbDuration;
    @FXML
    private ComboBox<UniversityBEAN> cmbAffiliation;
    @FXML
    private ComboBox<String> cmbCourseMode;
    @FXML
    private TextField txtExamFee;
    @FXML
    private ComboBox<String> cmbProviderType;
    @FXML
    private ComboBox<String> cmbAgencyStatus;
    @FXML
    private TextField txtTieUpName;
    @FXML
    private TextField txtCommission;
    @FXML
    private DatePicker dpContractFrom;
    @FXML
    private DatePicker dpContractTo;
    @FXML
    private TextField txtRenewalBy;
    @FXML
    private TextField txtCourseFee;
    @FXML
    private ToggleGroup grpPaymentMode;
    @FXML
    private GridPane gridInstallment;
    @FXML
    private GridPane gridCampuses;
    @FXML
    private GridPane gridContacts;
    @FXML
    private GridPane gridProcessingFee;
    @FXML
    private TextField txtTrainingCourse;
    @FXML
    private Button btnSave;
    
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    static Logger logger = Logger.getLogger(FXMLTrainingController.class);
    TrainingBEAN trainingBEAN = new TrainingBEAN();
    GlobalSearchMasterData masterData = new GlobalSearchMasterData();
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    
    TrainingDAO trainingDAO = springAppContext.getBean(TrainingDAO.class);
    TrainInstallmentDAO trainInstallmentDAO = springAppContext.getBean(TrainInstallmentDAO.class);
    TrainCampusDAO trainCampusDAO = springAppContext.getBean(TrainCampusDAO.class);
    TrainContactDAO trainContactDAO = springAppContext.getBean(TrainContactDAO.class);
    TrainProcessFeeDAO trainProcessFeeDAO = springAppContext.getBean(TrainProcessFeeDAO.class);
    
    UniversityDAO universityDAO = springAppContext.getBean(UniversityDAO.class);
    CountryDAO countryDAO = new CountryIMPL();
    LocationsDAO locationsDAO = new LocationsIMPL();
    
    String TRAINING_ID = null;


    /* ======================== Dynamic Control Lists ==================== */
    ObservableList<TrainInstallmentBEAN> listTrainInstallments = FXCollections.observableArrayList();
    ObservableList<CampusBEAN> listCampuses = FXCollections.observableArrayList();
    ObservableList<ContactsBEAN> listTrainContacts = FXCollections.observableArrayList();
    ObservableList<ProcessFeeBEAN> listTrainProcessFees = FXCollections.observableArrayList();

    /* ======================== Dynamic Controls Validation Support ==================== */
    ObservableList<ValidationSupport> observValidateInstallments = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateCampuses = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateTrainContacts = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> observValidateTrainProcessingFee = FXCollections.observableArrayList();
    @FXML
    private TextField txtWebsite;
    @FXML
    private TextField txtContractBy;
    @FXML
    private RadioButton rdInstallment;
    @FXML
    private RadioButton rdSingle;
    @FXML
    private Label lblPaymentMode;
    @FXML
    private HBox hboxTrainingCourse;
    @FXML
    private Button btnClear;

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
        bindTrainingCourseBasics();
        addButtonListners();
        addAdditionalFeatures();

//        /* ======================== Dynamic Controls ==================== */
        initDynaProcessingFees();
        initDynaInstallments();
        initDynaContactDetails();
        initDynaCampusesOfferCourses();
//        /* ======================== Validation ==================== */
        makeMandaoryDecorations();
        validationListners();
    }
    
    public void initializeMasterData() {
        cmbDuration.getItems().addAll(RetrieveStaticMasterDAO.getDuration());
        cmbCourseMode.getItems().addAll(SearchConstants.getListCourseMode());
        cmbAffiliation.getItems().addAll(universityDAO.retreiveUniversities());
        cmbProviderType.getItems().addAll(SearchConstants.getListEmployerType());
        cmbAgencyStatus.getItems().addAll(SearchConstants.getListAgencyStatus());
        
    }
    
    public void bindTrainingCourseBasics() {
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
        for (UniversityBEAN universityBEAN : cmbAffiliation.getItems()) {
            if (universityBEAN.getUniversityId().equalsIgnoreCase(universityBEAN.getUniversityId())) {
                cmbAffiliation.getSelectionModel().select(universityBEAN);
            }
        }
        
        Bindings.bindBidirectional(trainingBEAN.affiliationProperty(), cmbAffiliation.valueProperty(), converter);
        Bindings.bindBidirectional(txtTrainingCourse.textProperty(), trainingBEAN.trainingCourseProperty());
        Bindings.bindBidirectional(txtWebsite.textProperty(), trainingBEAN.websiteProperty());
        Bindings.bindBidirectional(txtArDesc.textProperty(), trainingBEAN.descriptionProperty());
        Bindings.bindBidirectional(txtArRemarks.textProperty(), trainingBEAN.remarksProperty());
        Bindings.bindBidirectional(cmbDuration.valueProperty(), trainingBEAN.durationProperty());
        Bindings.bindBidirectional(cmbCourseMode.valueProperty(), trainingBEAN.courseModeProperty());
        Bindings.bindBidirectional(txtExamFee.textProperty(), trainingBEAN.examFeeProperty());
        Bindings.bindBidirectional(cmbProviderType.valueProperty(), trainingBEAN.providerTypeProperty());
        Bindings.bindBidirectional(cmbAgencyStatus.valueProperty(), trainingBEAN.agencyStatusProperty());
        Bindings.bindBidirectional(txtTieUpName.textProperty(), trainingBEAN.tieUpNameProperty());
        Bindings.bindBidirectional(txtCommission.textProperty(), trainingBEAN.commissionProperty());
        Bindings.bindBidirectional(dpContractFrom.valueProperty(), trainingBEAN.contractFromProperty());
        Bindings.bindBidirectional(dpContractTo.valueProperty(), trainingBEAN.contractToProperty());
        Bindings.bindBidirectional(txtContractBy.textProperty(), trainingBEAN.contractByProperty());
        Bindings.bindBidirectional(txtRenewalBy.textProperty(), trainingBEAN.renewalByProperty());
        Bindings.bindBidirectional(txtCourseFee.textProperty(), trainingBEAN.courseFeeProperty());
        if (trainingBEAN.getPaymentMode() != null) {
            if (trainingBEAN.getPaymentMode().equalsIgnoreCase("Installment")) {
                rdInstallment.setSelected(true);
                trainingBEAN.setPaymentMode("Installment");
//                initDynaInstallments();
            } else if (trainingBEAN.getPaymentMode().equalsIgnoreCase("Single")) {
                trainingBEAN.setPaymentMode("Single");
                rdSingle.setSelected(true);
            }
        }
        
    }
    
    public void addAdditionalFeatures() {
        
        ChangeDateFormat.datePickerDateFormatter(dpContractFrom);
        ChangeDateFormat.datePickerDateFormatter(dpContractTo);
        ComboBoxJumpToChar.jumpToChar(cmbAgencyStatus);
        ComboBoxJumpToChar.jumpToChar(cmbAffiliation);
        ComboBoxJumpToChar.jumpToChar(cmbDuration);
        /* ======================== Auto Grow Shrink Text Area ==================== */
        TextAreaGrowShrink.makeGrow(txtArDesc);
        TextAreaGrowShrink.makeGrow(txtArRemarks);
    }
    
    public void initDynaContactDetails() {
        listTrainContacts.clear();
        listTrainContacts = trainContactDAO.retrieveContacts(TRAINING_ID);
        if (listTrainContacts.size() > 0) {
            addDynaContactDetailsRows();
        } else {
            ContactsBEAN contactsBEAN = new ContactsBEAN();
            listTrainContacts.add(contactsBEAN);
            addDynaContactDetailsRows();
        }
    }
    
    public void addDynaContactDetailsRows() {
        gridContacts.getChildren().clear();
        observValidateTrainContacts.clear();
        for (ContactsBEAN trainingBEAN : listTrainContacts) {
            trainingBEAN.setTrainingId(TRAINING_ID);
            
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
            
            observValidateTrainContacts.add(validationContactSupport);
            
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    ValidationResult result = validationContactSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationContactSupport.isInvalid()) {
                        trainingBEAN.setTrainingId(TRAINING_ID);
                        if (trainingBEAN.getRowId() != null) {
                            trainContactDAO.updateContacts(trainingBEAN);
                        } else if (TRAINING_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            trainingBEAN.setRowId("tco_" + UiiDGenerator.getUIID8());
                            boolean done = trainContactDAO.insertContacts(trainingBEAN);
                        }
                        ContactsBEAN emptyBEAN = new ContactsBEAN();
                        listTrainContacts.add(emptyBEAN);
                        addDynaContactDetailsRows();
                    }
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
                        if (trainingBEAN.getRowId() != null) {
                            trainContactDAO.deleteContactDetails(trainingBEAN.getRowId());
                        }
                        listTrainContacts.remove(trainingBEAN);
                        if (listTrainContacts.size() > 0) {
                            addDynaContactDetailsRows();
                        } else {
                            ContactsBEAN emptyBEAN = new ContactsBEAN();
                            listTrainContacts.add(emptyBEAN);
                            addDynaContactDetailsRows();
                        }
                        gridContacts.requestFocus();
                        observValidateTrainContacts.remove(validationContactSupport);
                        
                    }
                }
            });
            
            if (listTrainContacts.indexOf(trainingBEAN) == 0) {
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
                            Bindings.unbindBidirectional(trainingBEAN.contactNameProperty(), txtContactName.textProperty());
                            txtContactName.setText(oldValue);
                            Bindings.bindBidirectional(trainingBEAN.contactNameProperty(), txtContactName.textProperty());
                        } else {
                            Bindings.unbindBidirectional(trainingBEAN.contactNameProperty(), txtContactName.textProperty());
                            txtContactName.setText(newValue);
                            Bindings.bindBidirectional(trainingBEAN.contactNameProperty(), txtContactName.textProperty());
                        }
                    }
                }
            });
            txtDepartment.textProperty().addListener(new ChangeListener<String>() {
                
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                            Bindings.unbindBidirectional(trainingBEAN.departmentProperty(), txtDepartment.textProperty());
                            txtDepartment.setText(oldValue);
                            Bindings.bindBidirectional(trainingBEAN.departmentProperty(), txtDepartment.textProperty());
                        } else {
                            Bindings.unbindBidirectional(trainingBEAN.departmentProperty(), txtDepartment.textProperty());
                            txtDepartment.setText(newValue);
                            Bindings.bindBidirectional(trainingBEAN.departmentProperty(), txtDepartment.textProperty());
                        }
                    }
                }
            });
            txtStdIsd.textProperty().addListener(new ChangeListener<String>() {
                
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(trainingBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(trainingBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                        } else {
                            Bindings.unbindBidirectional(trainingBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                            txtStdIsd.setText(newValue);
                            Bindings.bindBidirectional(trainingBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                        }
                    }
                }
            });
            txtPhoneNo.textProperty().addListener(new ChangeListener<String>() {
                
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(trainingBEAN.phoneProperty(), txtPhoneNo.textProperty());
                            txtPhoneNo.setText(oldValue);
                            Bindings.bindBidirectional(trainingBEAN.phoneProperty(), txtPhoneNo.textProperty());
                        } else {
                            Bindings.unbindBidirectional(trainingBEAN.phoneProperty(), txtPhoneNo.textProperty());
                            txtPhoneNo.setText(newValue);
                            Bindings.bindBidirectional(trainingBEAN.phoneProperty(), txtPhoneNo.textProperty());
                        }
                    }
                }
            });
            Bindings.bindBidirectional(txtPhoneNo.textProperty(), trainingBEAN.phoneProperty());
            Bindings.bindBidirectional(txtEmail.textProperty(), trainingBEAN.emailProperty());
            Bindings.bindBidirectional(txtContactName.textProperty(), trainingBEAN.contactNameProperty());
            Bindings.bindBidirectional(txtDepartment.textProperty(), trainingBEAN.departmentProperty());
            Bindings.bindBidirectional(txtStdIsd.textProperty(), trainingBEAN.stdIsdProperty());
            
            gridContacts.add(txtContactName, 0, listTrainContacts.indexOf(trainingBEAN) + 1);
            gridContacts.add(txtDepartment, 1, listTrainContacts.indexOf(trainingBEAN) + 1);
            gridContacts.add(txtStdIsd, 2, listTrainContacts.indexOf(trainingBEAN) + 1);
            gridContacts.add(txtPhoneNo, 3, listTrainContacts.indexOf(trainingBEAN) + 1);
            gridContacts.add(txtEmail, 4, listTrainContacts.indexOf(trainingBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridContacts.add(hBox, 5, listTrainContacts.indexOf(trainingBEAN) + 1);
            
        }
        
    }
    
    public void initDynaCampusesOfferCourses() {
        listCampuses.clear();
        listCampuses = trainCampusDAO.retrieveTrainingCampuses(TRAINING_ID);
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
        observValidateCampuses.clear();
        for (CampusBEAN campusBEAN : listCampuses) {
            campusBEAN.setTrainingId(TRAINING_ID);
            
            TextField txtCampusName = new TextField();
            txtCampusName.setPromptText("Campus Name");
            
            ComboBox cmbCountry = new ComboBox(countryDAO.retrieveAllCountries());
            cmbCountry.setPromptText("Country");
            
            ComboBox cmbLocation = new ComboBox();
            cmbLocation.setPromptText("Location");
            
            TextArea txtContactAddrs = new TextArea();
            txtContactAddrs.setPromptText("Contact Address");
            TextArea txtAccomodation = new TextArea();
            txtContactAddrs.setPromptText("Contact Address");
            TextAreaGrowShrink.makeGrow(txtContactAddrs);
            TextAreaGrowShrink.makeGrow(txtAccomodation);
            GridPane.setHgrow(txtCampusName, Priority.NEVER);
            GridPane.setHgrow(cmbCountry, Priority.NEVER);
            GridPane.setHgrow(cmbLocation, Priority.NEVER);
            GridPane.setHgrow(txtContactAddrs, Priority.NEVER);
            GridPane.setHgrow(txtAccomodation, Priority.NEVER);
            
            txtCampusName.setMaxWidth(Double.MAX_VALUE);
            cmbCountry.setMaxWidth(Double.MAX_VALUE);
            cmbLocation.setMaxWidth(Double.MAX_VALUE);
            txtContactAddrs.setMaxWidth(Double.MAX_VALUE);
            txtAccomodation.setMaxWidth(Double.MAX_VALUE);
            
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
            observValidateCampuses.add(validationCampusSupport);
            
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
                        campusBEAN.setTrainingId(TRAINING_ID);
                        if (campusBEAN.getCollegeId() != null) {
                            trainCampusDAO.updateTrainingCampuses(campusBEAN);
                        } else if (TRAINING_ID != null) {
                            campusBEAN.setRowId("camp_" + UiiDGenerator.getUIID8());
                            boolean done = trainCampusDAO.insertTrainingCampuses(campusBEAN);
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
                            trainCampusDAO.deleteTrainingCampuses(campusBEAN.getRowId());
                        }
                        listCampuses.remove(campusBEAN);
                        if (listCampuses.size() > 0) {
                            addDynamicCampuses();
                        } else {
                            CampusBEAN emptyBEAN = new CampusBEAN();
                            listCampuses.add(emptyBEAN);
                            addDynamicCampuses();
                        }
                        observValidateTrainProcessingFee.remove(validationCampusSupport);
                        gridCampuses.requestFocus();
                    }
                }
            });
            
            if (listCampuses.indexOf(campusBEAN) == 0) {
                gridCampuses.add(new Label("Campus Name"), 0, 0);
                gridCampuses.add(new Label("Country"), 1, 0);
                gridCampuses.add(new Label("Location"), 2, 0);
                gridCampuses.add(new Label("Contact Address"), 3, 0);
                gridCampuses.add(new Label("Accomodation"), 4, 0);
                
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
            Bindings.bindBidirectional(txtAccomodation.textProperty(), campusBEAN.accomodationProperty());
            
            gridCampuses.add(txtCampusName, 0, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(cmbCountry, 1, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(cmbLocation, 2, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(txtContactAddrs, 3, listCampuses.indexOf(campusBEAN) + 1);
            gridCampuses.add(txtAccomodation, 4, listCampuses.indexOf(campusBEAN) + 1);
            
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridCampuses.add(hBox, 5, listCampuses.indexOf(campusBEAN) + 1);
        }
        
    }
    
    public void initDynaProcessingFees() {
        listTrainProcessFees = trainProcessFeeDAO.retrieveProcessFee(TRAINING_ID);
        if (listTrainProcessFees.size() > 0) {
            addDynaProcessingFee();
        } else {
            ProcessFeeBEAN feeBEAN = new ProcessFeeBEAN();
            listTrainProcessFees.add(feeBEAN);
            addDynaProcessingFee();
        }
    }
    
    public void addDynaProcessingFee() {
        gridProcessingFee.getChildren().clear();
        observValidateTrainProcessingFee.clear();
        for (ProcessFeeBEAN processFeeBEAN : listTrainProcessFees) {
            processFeeBEAN.setTrainingId(TRAINING_ID);
            
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
            observValidateTrainProcessingFee.add(validationFeeSupport);
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
                        processFeeBEAN.setTrainingId(TRAINING_ID);
                        if (processFeeBEAN.getRowId() != null) {
                            trainProcessFeeDAO.updateProcessFee(processFeeBEAN);
                        } else if (TRAINING_ID != null) {
                            processFeeBEAN.setRowId("tfe_" + UiiDGenerator.getUIID8());
                            boolean done = trainProcessFeeDAO.insertProcessFee(processFeeBEAN);
                        }
                        ProcessFeeBEAN emptyBEAN = new ProcessFeeBEAN();
                        listTrainProcessFees.add(emptyBEAN);
                        addDynaProcessingFee();
                    }
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
                            trainProcessFeeDAO.deleteCampuses(processFeeBEAN.getRowId());
                        }
                        listTrainProcessFees.remove(processFeeBEAN);
                        if (listTrainProcessFees.size() > 0) {
                            addDynaProcessingFee();
                        } else {
                            ProcessFeeBEAN emptyBEAN = new ProcessFeeBEAN();
                            listTrainProcessFees.add(emptyBEAN);
                            addDynaProcessingFee();
                        }
                        gridProcessingFee.requestFocus();
                        observValidateTrainProcessingFee.remove(validationFeeSupport);
                    }
                }
            });
            
            if (listTrainProcessFees.indexOf(processFeeBEAN) == 0) {
                gridProcessingFee.add(new Label("Fee Type"), 0, 0);
                gridProcessingFee.add(new Label("Currency"), 1, 0);
                gridProcessingFee.add(new Label("Amount"), 2, 0);
            }

            /* ======================== Bind With Bean ==================== */
            Bindings.bindBidirectional(cmbFeeType.valueProperty(), processFeeBEAN.feeTypeProperty());
            Bindings.bindBidirectional(cmbCurrency.valueProperty(), processFeeBEAN.currencyProperty());
            Bindings.bindBidirectional(txtAmount.textProperty(), processFeeBEAN.amountProperty());
            /* ======================== Add To Grid ==================== */
            gridProcessingFee.add(cmbFeeType, 0, listTrainProcessFees.indexOf(processFeeBEAN) + 1);
            gridProcessingFee.add(cmbCurrency, 1, listTrainProcessFees.indexOf(processFeeBEAN) + 1);
            gridProcessingFee.add(txtAmount, 2, listTrainProcessFees.indexOf(processFeeBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridProcessingFee.add(hBox, 3, listTrainProcessFees.indexOf(processFeeBEAN) + 1);
        }
    }
    
    public void initDynaInstallments() {
        listTrainInstallments = trainInstallmentDAO.retrieveInstallment(TRAINING_ID);
        if (listTrainInstallments.size() > 0) {
            addDynaInstallments();
        } else {
            TrainInstallmentBEAN emptyBEAN = new TrainInstallmentBEAN();
            listTrainInstallments.add(emptyBEAN);
            addDynaInstallments();
        }
    }
    
    public void addDynaInstallments() {
        gridInstallment.getChildren().clear();
        observValidateInstallments.clear();
        for (TrainInstallmentBEAN installmentBEAN : listTrainInstallments) {
            installmentBEAN.setTrainingId(TRAINING_ID);
            Label lblInstallmentNo = new Label();
            lblInstallmentNo.setAlignment(Pos.CENTER_RIGHT);
            ComboBox cmbInstallDuration = new ComboBox();
            cmbInstallDuration.setPromptText("Installment Duration");
            ComboBoxJumpToChar.jumpToChar(cmbInstallDuration);
            cmbInstallDuration.getItems().addAll(RetrieveStaticMasterDAO.getDuration());
            TextField txtAmount = new TextField();
            txtAmount.setPromptText("Amount");
            
            GridPane.setHgrow(cmbInstallDuration, Priority.NEVER);
            GridPane.setHgrow(txtAmount, Priority.NEVER);
            lblInstallmentNo.setMaxWidth(Double.MAX_VALUE);
            txtAmount.setMaxWidth(Double.MAX_VALUE);
            cmbInstallDuration.setMaxWidth(Double.MAX_VALUE);
            
            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            /* ======================== Validation Support ==================== */
            ValidationSupport validationInstallDurationSupport = new ValidationSupport();
            validationInstallDurationSupport.registerValidator(cmbInstallDuration, Validator.createEmptyValidator("Installment Duration required"));
            validationInstallDurationSupport.registerValidator(txtAmount, Validator.createEmptyValidator("Amount required"));
            observValidateInstallments.add(validationInstallDurationSupport);
            txtAmount.textProperty().addListener(new ChangeListener<String>() {
                
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(installmentBEAN.amountProperty(), txtAmount.textProperty());
                            txtAmount.setText(oldValue);
                            Bindings.bindBidirectional(installmentBEAN.amountProperty(), txtAmount.textProperty());
                        } else {
                            Bindings.unbindBidirectional(installmentBEAN.amountProperty(), txtAmount.textProperty());
                            txtAmount.setText(newValue);
                            Bindings.bindBidirectional(installmentBEAN.amountProperty(), txtAmount.textProperty());
                        }
                    }
                }
            });
            /* ======================== Button Events ==================== */
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationInstallDurationSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationInstallDurationSupport.isInvalid()) {
                        installmentBEAN.setTrainingId(TRAINING_ID);
                        if (installmentBEAN.getRowId() != null) {
                            trainInstallmentDAO.updateInstallment(installmentBEAN);
                        } else if (TRAINING_ID != null) {
                            installmentBEAN.setRowId("tfe_" + UiiDGenerator.getUIID8());
                            boolean done = trainInstallmentDAO.insertInstallment(installmentBEAN);
                        }
                        TrainInstallmentBEAN emptyBEAN = new TrainInstallmentBEAN();
                        listTrainInstallments.add(emptyBEAN);
                        addDynaInstallments();
                    }
                    gridInstallment.requestFocus();
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
                        if (installmentBEAN.getRowId() != null) {
                            trainProcessFeeDAO.deleteCampuses(installmentBEAN.getRowId());
                        }
                        listTrainInstallments.remove(installmentBEAN);
                        if (listTrainInstallments.size() > 0) {
                            addDynaInstallments();
                        } else {
                            TrainInstallmentBEAN emptyBEAN = new TrainInstallmentBEAN();
                            listTrainInstallments.add(emptyBEAN);
                            addDynaInstallments();
                        }
                        gridInstallment.requestFocus();
                        observValidateTrainProcessingFee.remove(validationInstallDurationSupport);
                    }
                }
            });
            
            if (listTrainInstallments.indexOf(installmentBEAN) == 0) {
                 Label lblInstallmentNoHead = new Label();
            lblInstallmentNoHead.setAlignment(Pos.CENTER_RIGHT);
                gridInstallment.add(lblInstallmentNoHead, 0, 0);
                gridInstallment.add(new Label("Duration"), 1, 0);
                gridInstallment.add(new Label("Amount"), 2, 0);
            }

            /* ======================== Bind With Bean ==================== */
            Bindings.bindBidirectional(cmbInstallDuration.valueProperty(), installmentBEAN.durationProperty());
            Bindings.bindBidirectional(txtAmount.textProperty(), installmentBEAN.amountProperty());
            /* ======================== Add To Grid ==================== */
            lblInstallmentNo.setText(listTrainInstallments.indexOf(installmentBEAN) + 1 + ".");
            gridInstallment.add(lblInstallmentNo, 0, listTrainInstallments.indexOf(installmentBEAN) + 1);
            gridInstallment.add(cmbInstallDuration, 1, listTrainInstallments.indexOf(installmentBEAN) + 1);
            gridInstallment.add(txtAmount, 2, listTrainInstallments.indexOf(installmentBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridInstallment.add(hBox, 3, listTrainInstallments.indexOf(installmentBEAN) + 1);
        }
    }
    
    public void validationListners() {
        txtTrainingCourse.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.trainingCourseProperty(), txtTrainingCourse.textProperty());
                        txtTrainingCourse.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.trainingCourseProperty(), txtTrainingCourse.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.trainingCourseProperty(), txtTrainingCourse.textProperty());
                        txtTrainingCourse.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.trainingCourseProperty(), txtTrainingCourse.textProperty());
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
        
        txtWebsite.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,.,/,:,0-9]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.websiteProperty(), txtWebsite.textProperty());
                        txtWebsite.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.websiteProperty(), txtWebsite.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.websiteProperty(), txtWebsite.textProperty());
                        txtWebsite.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.websiteProperty(), txtWebsite.textProperty());
                    }
                    
                }
            }
            
        });
        txtTieUpName.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                        txtTieUpName.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                        txtTieUpName.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.tieUpNameProperty(), txtTieUpName.textProperty());
                    }
                }
            }
        });
        txtCommission.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*")) || newValue.length() > 2) {
                        Bindings.unbindBidirectional(trainingBEAN.commissionProperty(), txtCommission.textProperty());
                        txtCommission.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.commissionProperty(), txtCommission.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.commissionProperty(), txtCommission.textProperty());
                        txtCommission.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.commissionProperty(), txtCommission.textProperty());
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
        
        txtExamFee.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.examFeeProperty(), txtExamFee.textProperty());
                        txtExamFee.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.examFeeProperty(), txtExamFee.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.examFeeProperty(), txtExamFee.textProperty());
                        txtExamFee.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.examFeeProperty(), txtExamFee.textProperty());
                    }
                }
            }
        });
        txtCourseFee.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.courseFeeProperty(), txtCourseFee.textProperty());
                        txtCourseFee.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.courseFeeProperty(), txtCourseFee.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.courseFeeProperty(), txtCourseFee.textProperty());
                        txtCourseFee.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.courseFeeProperty(), txtCourseFee.textProperty());
                    }
                }
            }
        });
        txtContractBy.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.contractByProperty(), txtContractBy.textProperty());
                        txtContractBy.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.contractByProperty(), txtContractBy.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.contractByProperty(), txtContractBy.textProperty());
                        txtContractBy.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.contractByProperty(), txtContractBy.textProperty());
                    }
                    
                }
            }
            
        });
        txtRenewalBy.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(trainingBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                        txtRenewalBy.setText(oldValue);
                        Bindings.bindBidirectional(trainingBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                    } else {
                        Bindings.unbindBidirectional(trainingBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                        txtRenewalBy.setText(newValue);
                        Bindings.bindBidirectional(trainingBEAN.renewalByProperty(), txtRenewalBy.textProperty());
                    }
                    
                }
            }
            
        });
        grpPaymentMode.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (grpPaymentMode.getSelectedToggle() != null) {
                    System.out.println("newValue TTO ====" + newValue.toString());
                    if (rdInstallment.isSelected()) {
                        trainingBEAN.setPaymentMode("Installment");
                        //   gridInstallment.setDisable(false);
                        //    initDynaInstallments();
                    } else if (rdSingle.isSelected()) {
                        trainingBEAN.setPaymentMode("Single");
                    }
                }
                System.out.println("PAYMENT MODE ====" + trainingBEAN.getPaymentMode());
            }
        });
//        grpPaymentMode.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            @Override
//            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//                if (newValue != null) {
//                      RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
//            System.out.println("Selected Radio Button - "+chk.getText());
//                 //   System.out.println("setPaymentMode ==== " + grpPaymentMode.getSelectedToggle().getUserData().toString());
//                   // trainingBEAN.setPaymentMode(grpPaymentMode.getSelectedToggle().getUserData().toString());
//                    System.out.println("getPaymentMode ==== "+trainingBEAN.getPaymentMode());
//                }
//            }
//        });

    }
    
    public void addButtonListners() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                boolean isInstallmentsValid = false;
                boolean isCampusesValid = false;
                boolean isContactDetailsValid = false;
                boolean isProcessingFeeValid = false;
                /* ======================== Validate Basic Info ==================== */
                
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }

                /* ======================== Validate Installments ==================== */
                for (ValidationSupport support : observValidateInstallments) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isInstallmentsValid = support.isInvalid();
                    }
                }

                /* ======================== Validate Campuses Offering Training Course ==================== */
                for (ValidationSupport support : observValidateCampuses) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isCampusesValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Contact Details ==================== */
                for (ValidationSupport support : observValidateTrainContacts) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isContactDetailsValid = support.isInvalid();
                    }
                }
                /* ======================== Validate  Accomodations ==================== */
                for (ValidationSupport support : observValidateTrainProcessingFee) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isProcessingFeeValid = support.isInvalid();
                    }
                }
                System.out.println("Test Validation Valid :: " + validationSupport.isInvalid());
                /* ======================== Insert Basic Info ==================== */
                if (!validationSupport.isInvalid()) {
                    if (trainingBEAN.getTrainingId() == null || trainingBEAN.getTrainingId().equalsIgnoreCase("")) {
                        /* ======================== Generate Employer Id ==================== */
                        TRAINING_ID = "tr_" + UiiDGenerator.getUIID8();
                        trainingBEAN.setTrainingId(TRAINING_ID);
                        System.out.println("TRAINING_ID TRAINING_ID  ======== " + TRAINING_ID);
                        boolean done = trainingDAO.insertTrainingCourseBasics(trainingBEAN);
                        if (done) {
                            btnClear.fire();
                            Notifications.create()
                                    .title("Saved Successfully!")
                                    .text("Training Course Details Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Save Failed!")
                                    .text("Training Course Details Save Failed!")
                                    .showError();
                            trainingBEAN.setTrainingId(null);
                        }
                    } else {
                        boolean done = trainingDAO.updateTrainingCourseBasics(trainingBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Updated Successfully!")
                                    .text("Training Course Details Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Updation Failed!")
                                    .text("Training Course Details Updation Failed!")
                                    .showError();
                        }
                    }
                }
                /* ======================== Save Installment Details ==================== */
                if (!isInstallmentsValid && !validationSupport.isInvalid()) {
                    System.out.println("Contact Details are Valid Now");
                    for (TrainInstallmentBEAN installmentBEAN : listTrainInstallments) {
                        if (installmentBEAN.getRowId() == null || installmentBEAN.getRowId().equalsIgnoreCase("")) {
                            installmentBEAN.setTrainingId(TRAINING_ID);
                            installmentBEAN.setRowId("tin_" + UiiDGenerator.getUIID8());
                            trainInstallmentDAO.insertInstallment(installmentBEAN);
                        } else {
                            trainInstallmentDAO.updateInstallment(installmentBEAN);
                        }
                    }
                }
                /* ======================== Save Campus Details ==================== */
                if (!isCampusesValid && !validationSupport.isInvalid()) {
                    System.out.println("Processing Fee Details are Valid Now");
                    for (CampusBEAN campusBEAN : listCampuses) {
                        if (campusBEAN.getRowId() == null || campusBEAN.getRowId().equalsIgnoreCase("")) {
                            campusBEAN.setTrainingId(TRAINING_ID);
                            campusBEAN.setRowId("tcm_" + UiiDGenerator.getUIID8());
                            trainCampusDAO.insertTrainingCampuses(campusBEAN);
                        } else {
                            trainCampusDAO.updateTrainingCampuses(campusBEAN);
                        }
                    }
                }
                /* ======================== Save Contact Details ==================== */
                if (!isContactDetailsValid && !validationSupport.isInvalid()) {
                    System.out.println("Visa Docs Details are Valid Now");
                    for (ContactsBEAN contactsBEAN : listTrainContacts) {
                        if (contactsBEAN.getRowId() == null || contactsBEAN.getRowId().equalsIgnoreCase("")) {
                            contactsBEAN.setTrainingId(TRAINING_ID);
                            contactsBEAN.setRowId("tco_" + UiiDGenerator.getUIID8());
                            trainContactDAO.insertContacts(contactsBEAN);
                        } else {
                            trainContactDAO.updateContacts(contactsBEAN);
                        }
                    }
                }
                /* ======================== Save Processing Fee Details ==================== */
                if (!isProcessingFeeValid && !validationSupport.isInvalid()) {
                    System.out.println("Accomdn Details are Valid Now");
                    for (ProcessFeeBEAN processFeeBEAN : listTrainProcessFees) {
                        if (processFeeBEAN.getRowId() == null || processFeeBEAN.getRowId().equalsIgnoreCase("")) {
                            processFeeBEAN.setTrainingId(TRAINING_ID);
                            processFeeBEAN.setRowId("tpf_" + UiiDGenerator.getUIID8());
                            trainProcessFeeDAO.insertProcessFee(processFeeBEAN);
                        } else {
                            trainProcessFeeDAO.updateProcessFee(processFeeBEAN);
                        }
                    }
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TRAINING_ID = null;
                trainingBEAN = new TrainingBEAN();
                bindTrainingCourseBasics();

                /* ======================== Auto Grow Shrink Text Area ==================== */
                TextAreaGrowShrink.makeGrow(txtArDesc);
                TextAreaGrowShrink.makeGrow(txtArRemarks);

                /* ======================== Dynamic Controls ==================== */
                initDynaProcessingFees();
                initDynaInstallments();
                initDynaContactDetails();
                initDynaCampusesOfferCourses();
                if (rdInstallment.isSelected() || rdSingle.isSelected()) {
                    rdInstallment.setSelected(false);
                    rdSingle.setSelected(false);
                }
                
            }
        });
        
    }
    
    public void makeMandaoryDecorations() {
        validationSupport.registerValidator(txtTrainingCourse, Validator.createEmptyValidator("Training Course required"));
        validationSupport.registerValidator(txtCommission, Validator.createEmptyValidator("Commission required"));
        validationSupport.registerValidator(cmbAffiliation, Validator.createEmptyValidator("University required"));
        validationSupport.registerValidator(cmbProviderType, Validator.createEmptyValidator("Course Provider Type required"));
        validationSupport.registerValidator(cmbCourseMode, Validator.createEmptyValidator("Course Mode required"));
        validationSupport.registerValidator(cmbAgencyStatus, Validator.createEmptyValidator("Agency Status required"));
        validationSupport.registerValidator(txtExamFee, Validator.createEmptyValidator("Exam Fee required"));
        validationSupport.registerValidator(dpContractFrom, Validator.createEmptyValidator("From Date required"));
        validationSupport.registerValidator(dpContractTo, Validator.createEmptyValidator("To Date required"));
        validationSupport.registerValidator(txtContractBy, Validator.createEmptyValidator("Contract By required"));
        validationSupport.registerValidator(cmbDuration, Validator.createEmptyValidator("Duration required"));
        validationSupport.registerValidator(txtRenewalBy, Validator.createEmptyValidator("Date required"));
    }
    
    public void autoCompletion() {

        /* ======================== Create Autocompletion Data By Overriding @toString ==================== */
        ObservableList<TrainingBEAN> listTrainingCourseAuto = trainingDAO.retrieveTrainingCourseBasics();
        ObservableList<TrainingBEAN> listAutoCompletion = FXCollections.observableArrayList();
        for (TrainingBEAN autoBEAN : listTrainingCourseAuto) {
            TrainingBEAN autoCompletionBEAN = new TrainingBEAN() {
                @Override
                public String toString() {
                    return autoBEAN.getTrainingCourse();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        }
        System.out.println("test auto completion list :: " + listAutoCompletion.toString());
        hboxTrainingCourse.getChildren().remove(txtTrainingCourse);
        txtTrainingCourse = TextFields.createClearableTextField();
        AutoCompletionBinding<TrainingBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtTrainingCourse, listAutoCompletion);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<TrainingBEAN>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<TrainingBEAN> event) {
                trainingBEAN = new TrainingBEAN();
                BeanUtils.copyProperties(event.getCompletion(), trainingBEAN);
                bindTrainingCourseBasics();
                
                loadTrainingCourseAllData(event.getCompletion().getTrainingId());
                System.out.println("Test AutoCompletion :: " + event.getCompletion().toString());
                System.out.println("All Data AutoCompletion :: " + trainingBEAN.toString());
            }
            
        });
        HBox.setHgrow(txtTrainingCourse, Priority.ALWAYS);
        hboxTrainingCourse.getChildren().add(txtTrainingCourse);
        
    }
    
    public void loadTrainingCourseAllData(String trainingId) {
        TRAINING_ID = trainingId;
        /* ======================== Dynamic Controls ==================== */
        initDynaProcessingFees();
        initDynaInstallments();
        initDynaContactDetails();
        initDynaCampusesOfferCourses();
        
    }
}

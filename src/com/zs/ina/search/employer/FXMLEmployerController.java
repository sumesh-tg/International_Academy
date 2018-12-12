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
package com.zs.ina.search.employer;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.TextAreaGrowShrink;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.search.employer.branch.dao.EmployerBranchBEAN;
import com.zs.ina.search.employer.branch.dao.EmployerBranchDAO;
import com.zs.ina.search.employer.contact.dao.EmployerContactsBEAN;
import com.zs.ina.search.employer.contact.dao.EmployerContactsDAO;
import com.zs.ina.search.employer.dao.EmployerBEAN;
import com.zs.ina.search.employer.dao.EmployerDAO;
import com.zs.ina.search.employer.fee.dao.EmployerProcessingFeeBEAN;
import com.zs.ina.search.employer.fee.dao.EmployerProcessingFeeDAO;
import com.zs.ina.search.master.common.GlobalSearchMasterData;
import com.zs.ina.search.master.common.SearchConstants;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
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
import javax.imageio.ImageIO;
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
 * Employer Controller Class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLEmployerController implements Initializable {

    @FXML
    private TextField txtEmployerName;
    @FXML
    private TextField txtCommission;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextArea txtRemarks;
    @FXML
    private TextArea txtEmplyrAddress;
    @FXML
    private Button btnUploadLogo;
    @FXML
    private TextField txtWebsite;
    @FXML
    private ComboBox<String> cmbCurrencyType;
    @FXML
    private ComboBox<String> cmbEmplyrType;
    @FXML
    private ComboBox<String> cmbAgencyStatus;
    @FXML
    private TextField txtTieUpName;
    @FXML
    private TextField txtContractBy;
    @FXML
    private TextField txtRenewalBy;
    @FXML
    private GridPane gridBranches;
    @FXML
    private GridPane gridContacts;
    @FXML
    private GridPane gridFees;
    @FXML
    private DatePicker dpContractFrom;
    @FXML
    private DatePicker dpContractTo;
    @FXML
    private Button btnSave;
    @FXML
    private ImageView imgLogoPreview;
    @FXML
    private HBox hboxEmployerName;
    @FXML
    private Button btnClear;

    static Logger logger = Logger.getLogger(FXMLEmployerController.class);
    String EMPLOYER_ID = null;
    long PHOTO_MAX_SIZE = 1024 * 1024;

    EmployerBEAN employerBEAN = new EmployerBEAN();
    GlobalSearchMasterData masterData = new GlobalSearchMasterData();
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Employer Beans ==================== */
    EmployerDAO employerDAO = springAppContext.getBean(EmployerDAO.class);
    EmployerContactsDAO employerContactsDAO = springAppContext.getBean(EmployerContactsDAO.class);
    EmployerBranchDAO employerBranchDAO = springAppContext.getBean(EmployerBranchDAO.class);
    EmployerProcessingFeeDAO employerProcessingFeeDAO = springAppContext.getBean(EmployerProcessingFeeDAO.class);


    /* ======================== Dynamic Control Lists ==================== */
    ObservableList<EmployerContactsBEAN> listHeadContacts = FXCollections.observableArrayList();
    ObservableList<EmployerBranchBEAN> listEmployerBranches = FXCollections.observableArrayList();
    ObservableList<EmployerProcessingFeeBEAN> listEmployerProcessingFee = FXCollections.observableArrayList();
    /* ======================== Dynamic Controls Validation Support ==================== */
    ObservableList<ValidationSupport> ObservValidateProcessingFee = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateHeadContacts = FXCollections.observableArrayList();
    ObservableList<ValidationSupport> ObservValidateBranches = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url Optional
     * @param rb Optional
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeMasterData();
        /* ======================== Auto Completion ==================== */
        autoCompletion();

        bindEmployerBasics();
        addButtonListners();
        addAdditionalFeatures();

        /* ======================== Dynamic Controls ==================== */
        initDynaContactDetails();
        initDynaBranchesUnderHeadOffice();
        initDynaProcessingFees();
        /* ======================== Validations ==================== */
        makeMandaoryDecorations();
        validationListners();
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

    /**
     * Method for Change listners for validation purpose
     */
    public void validationListners() {
        txtCommission.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*")) || newValue.length() > 2) {
                        Bindings.unbindBidirectional(employerBEAN.commissionProperty(), txtCommission.textProperty());
                        txtCommission.setText(oldValue);
                        Bindings.bindBidirectional(employerBEAN.commissionProperty(), txtCommission.textProperty());
                    } else {
                        Bindings.unbindBidirectional(employerBEAN.commissionProperty(), txtCommission.textProperty());
                        txtCommission.setText(newValue);
                        Bindings.bindBidirectional(employerBEAN.commissionProperty(), txtCommission.textProperty());
                    }
                }
            }
        });
        txtWebsite.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,.,/,:,0-9]*"))) {
                        Bindings.unbindBidirectional(employerBEAN.websiteProperty(), txtWebsite.textProperty());
                        txtWebsite.setText(oldValue);
                        Bindings.bindBidirectional(employerBEAN.websiteProperty(), txtWebsite.textProperty());
                    } else {
                        Bindings.unbindBidirectional(employerBEAN.websiteProperty(), txtWebsite.textProperty());
                        txtWebsite.setText(newValue);
                        Bindings.bindBidirectional(employerBEAN.websiteProperty(), txtWebsite.textProperty());
                    }
                }
            }
        });
        txtTieUpName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z]*"))) {
                        Bindings.unbindBidirectional(employerBEAN.tieupNameProperty(), txtTieUpName.textProperty());
                        txtTieUpName.setText(oldValue);
                        Bindings.bindBidirectional(employerBEAN.tieupNameProperty(), txtTieUpName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(employerBEAN.tieupNameProperty(), txtTieUpName.textProperty());
                        txtTieUpName.setText(newValue);
                        Bindings.bindBidirectional(employerBEAN.tieupNameProperty(), txtTieUpName.textProperty());
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

    }

    /**
     * Method For AutoCompletion
     */
    public void autoCompletion() {
        /* ======================== Create Autocompletion Data By Overriding @toString ==================== */
        ObservableList<EmployerBEAN> listEmployerAuto = employerDAO.retrieveEmployerBasics();
        ObservableList<EmployerBEAN> listAutoCompletion = FXCollections.observableArrayList();
        for (EmployerBEAN autoBEAN : listEmployerAuto) {
            EmployerBEAN autoCompletionBEAN = new EmployerBEAN() {
                @Override
                public String toString() {
                    return autoBEAN.getEmployer();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        }
        System.out.println("test auto completion list :: " + listAutoCompletion.toString());

        AutoCompletionBinding<EmployerBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtEmployerName, listAutoCompletion);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<EmployerBEAN>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<EmployerBEAN> event) {
                employerBEAN = new EmployerBEAN();
                BeanUtils.copyProperties(event.getCompletion(), employerBEAN);
                bindEmployerBasics();
                loadAllEmployerData(event.getCompletion().getEmployerId());
                System.out.println("Test AutoCompletion :: " + event.getCompletion().toString());
                System.out.println("All Data AutoCompletion :: " + employerBEAN.toString());
            }
        });
        HBox.setHgrow(txtEmployerName, Priority.ALWAYS);
        hboxEmployerName.getChildren().remove(txtEmployerName);
        hboxEmployerName.getChildren().add(txtEmployerName);
    }

    /**
     * Mehod For Loading data after autocompletion
     *
     * @param employerId requires employerid as the parameter
     */
    public void loadAllEmployerData(String employerId) {
        EMPLOYER_ID = employerId;
        /* ======================== Dynamic Controls ==================== */
        initDynaContactDetails();
        initDynaBranchesUnderHeadOffice();
        initDynaProcessingFees();
        /* ======================== Refresh Validation ==================== */
//        validationSupport = new ValidationSupport();
//        makeMandaoryDecorations();
        /* ======================== Load Logo ==================== */
        imgLogoPreview.setImage(null);
        if (employerBEAN.getInputStream() != null) {
            try {
                BufferedImage bufferedImg = ImageIO.read(employerBEAN.getInputStream());
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

    /**
     * Initializing process fee data for dynamic processing fee controls
     */
    public void initDynaProcessingFees() {
        listEmployerProcessingFee = employerProcessingFeeDAO.retrieveProcessingFee(EMPLOYER_ID);
        if (listEmployerProcessingFee.size() > 0) {
            addDynaProcessingFee();
        } else {
            EmployerProcessingFeeBEAN feeBEAN = new EmployerProcessingFeeBEAN();
            listEmployerProcessingFee.add(feeBEAN);
            addDynaProcessingFee();
        }
    }

    /**
     * Add processing fee controls to grid you can add 'n' number or processing
     * fee rows
     */
    public void addDynaProcessingFee() {
        gridFees.getChildren().clear();
        ObservValidateProcessingFee.clear();
        for (EmployerProcessingFeeBEAN employerProcessingFeeBEAN : listEmployerProcessingFee) {
            employerProcessingFeeBEAN.setEmployerId(EMPLOYER_ID);

            TextField txtAmount = new TextField();
            txtAmount.setPromptText("Amount");

            ComboBox cmbFeeType = new ComboBox(masterData.retrieveFeeType());
            cmbFeeType.setPromptText("Fee Type");
            ComboBoxJumpToChar.jumpToChar(cmbFeeType);

            ComboBox cmbCurrency = new ComboBox(masterData.retrieveSubClass());
            cmbCurrency.setPromptText("Currency");
            ComboBoxJumpToChar.jumpToChar(cmbCurrency);

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
                        if (!(newValue.matches("[0-9.]*"))) {
                            Bindings.unbindBidirectional(employerProcessingFeeBEAN.amountProperty(), txtAmount.textProperty());
                            txtAmount.setText(oldValue);
                            Bindings.bindBidirectional(employerProcessingFeeBEAN.amountProperty(), txtAmount.textProperty());
                        } else {
                            Bindings.unbindBidirectional(employerProcessingFeeBEAN.amountProperty(), txtAmount.textProperty());
                            txtAmount.setText(newValue);
                            Bindings.bindBidirectional(employerProcessingFeeBEAN.amountProperty(), txtAmount.textProperty());
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
                        employerProcessingFeeBEAN.setEmployerId(EMPLOYER_ID);
                        if (employerProcessingFeeBEAN.getProcessingFeeId() != null) {
                            employerProcessingFeeDAO.updateProcessingFee(employerProcessingFeeBEAN);
                        } else if (EMPLOYER_ID != null) {
                            employerProcessingFeeBEAN.setProcessingFeeId("fe_" + UiiDGenerator.getUIID8());
                            boolean done = employerProcessingFeeDAO.insertProcessingFee(employerProcessingFeeBEAN);
                        }
                        EmployerProcessingFeeBEAN emptyBEAN = new EmployerProcessingFeeBEAN();
                        listEmployerProcessingFee.add(emptyBEAN);
                        addDynaProcessingFee();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridFees);
//                    }
                    gridFees.requestFocus();
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
                        if (employerProcessingFeeBEAN.getProcessingFeeId() != null) {
                            employerProcessingFeeDAO.deleteProcessingFee(employerProcessingFeeBEAN.getProcessingFeeId());
                        }
                        listEmployerProcessingFee.remove(employerProcessingFeeBEAN);
                        if (listEmployerProcessingFee.size() > 0) {
                            addDynaProcessingFee();
                        } else {
                            EmployerProcessingFeeBEAN emptyBEAN = new EmployerProcessingFeeBEAN();
                            listEmployerProcessingFee.add(emptyBEAN);
                            addDynaProcessingFee();
                        }
                        gridFees.requestFocus();
                        ObservValidateProcessingFee.remove(validationFeeSupport);
                    }
                }
            });

            if (listEmployerProcessingFee.indexOf(employerProcessingFeeBEAN) == 0) {
                gridFees.add(new Label("Fee Type"), 0, 0);
                gridFees.add(new Label("Currency"), 1, 0);
                gridFees.add(new Label("Amount"), 2, 0);
            }

            /* ======================== Bind With Bean ==================== */
            Bindings.bindBidirectional(cmbFeeType.valueProperty(), employerProcessingFeeBEAN.processingFeeProperty());
            Bindings.bindBidirectional(cmbCurrency.valueProperty(), employerProcessingFeeBEAN.currencyProperty());
            Bindings.bindBidirectional(txtAmount.textProperty(), employerProcessingFeeBEAN.amountProperty());
            /* ======================== Add To Grid ==================== */
            gridFees.add(cmbFeeType, 0, listEmployerProcessingFee.indexOf(employerProcessingFeeBEAN) + 1);
            gridFees.add(cmbCurrency, 1, listEmployerProcessingFee.indexOf(employerProcessingFeeBEAN) + 1);
            gridFees.add(txtAmount, 2, listEmployerProcessingFee.indexOf(employerProcessingFeeBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridFees.add(hBox, 3, listEmployerProcessingFee.indexOf(employerProcessingFeeBEAN) + 1);
        }
    }

    /**
     * Initialize Branches Data for dynamic controls
     */
    public void initDynaBranchesUnderHeadOffice() {
        listEmployerBranches = employerBranchDAO.retrieveEmpBranch(EMPLOYER_ID);
        if (listEmployerBranches.size() > 0) {
            addDynaEmployerBranches();
        } else {
            EmployerBranchBEAN branchBEAN = new EmployerBranchBEAN();
            listEmployerBranches.add(branchBEAN);
            addDynaEmployerBranches();
        }
    }

    /**
     * Add all branches controls to grid
     */
    public void addDynaEmployerBranches() {
        gridBranches.getChildren().clear();
        ObservValidateBranches.clear();

        for (EmployerBranchBEAN employerBranchBEAN : listEmployerBranches) {
            employerBranchBEAN.setEmployerId(EMPLOYER_ID);

            TextField txtBranchName = new TextField();
            txtBranchName.setPromptText("Branch Name");

            ComboBox cmbCountry = new ComboBox(masterData.retrieveAllCountries());
            cmbCountry.setPromptText("Country");
            ComboBoxJumpToChar.jumpToChar(cmbCountry);

            ComboBox cmbLocation = new ComboBox();
            cmbLocation.setPromptText("Location");
            ComboBoxJumpToChar.jumpToChar(cmbLocation);

            TextArea txtContactAddr = new TextArea();
            txtContactAddr.setPromptText("Contact Address");
            TextAreaGrowShrink.makeGrow(txtContactAddr);
            GridPane.setHgrow(txtBranchName, Priority.NEVER);
            GridPane.setHgrow(cmbCountry, Priority.NEVER);
            GridPane.setHgrow(cmbLocation, Priority.NEVER);
            GridPane.setHgrow(txtContactAddr, Priority.NEVER);

            txtBranchName.setMaxWidth(Double.MAX_VALUE);
            cmbCountry.setMaxWidth(Double.MAX_VALUE);
            cmbLocation.setMaxWidth(Double.MAX_VALUE);
            txtContactAddr.setMaxWidth(Double.MAX_VALUE);

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
                                return masterData.retrievLocationsByCountry(newValue.toString());
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
            ValidationSupport validationBranchSupport = new ValidationSupport();
            validationBranchSupport.registerValidator(txtBranchName, Validator.createEmptyValidator("Branch Name Required!"));
            validationBranchSupport.registerValidator(cmbCountry, Validator.createEmptyValidator("Country Required!"));
            validationBranchSupport.registerValidator(cmbLocation, Validator.createEmptyValidator("Location Rquired!"));
            validationBranchSupport.registerValidator(txtContactAddr, Validator.createEmptyValidator("Address Required!"));
            ObservValidateProcessingFee.add(validationBranchSupport);

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationBranchSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationBranchSupport.isInvalid()) {
                        employerBranchBEAN.setEmployerId(EMPLOYER_ID);
                        if (employerBranchBEAN.getEmpBranchId() != null) {
                            employerBranchDAO.updateEmpBranch(employerBranchBEAN);
                        } else if (EMPLOYER_ID != null) {
                            employerBranchBEAN.setEmpBranchId("br_" + UiiDGenerator.getUIID8());
                            boolean done = employerBranchDAO.insertEmpBranch(employerBranchBEAN);
                        }
                        EmployerBranchBEAN emptyBEAN = new EmployerBranchBEAN();
                        listEmployerBranches.add(emptyBEAN);
                        addDynaEmployerBranches();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Child Details!", gridBranches);
//                    }
                    gridBranches.requestFocus();
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
                        if (employerBranchBEAN.getEmpBranchId() != null) {
                            employerBranchDAO.deleteEmpBranch(employerBranchBEAN.getEmpBranchId());
                        }
                        listEmployerBranches.remove(employerBranchBEAN);
                        if (listEmployerBranches.size() > 0) {
                            addDynaEmployerBranches();
                        } else {
                            EmployerBranchBEAN emptyBEAN = new EmployerBranchBEAN();
                            listEmployerBranches.add(emptyBEAN);
                            addDynaEmployerBranches();
                        }
                        ObservValidateProcessingFee.remove(validationBranchSupport);
                        gridBranches.requestFocus();
                    }
                }
            });

            if (listEmployerBranches.indexOf(employerBranchBEAN) == 0) {
                gridBranches.add(new Label("Branch Name"), 0, 0);
                gridBranches.add(new Label("Country"), 1, 0);
                gridBranches.add(new Label("Location"), 2, 0);
                gridBranches.add(new Label("Contact Address"), 3, 0);
            }

            Bindings.bindBidirectional(txtBranchName.textProperty(), employerBranchBEAN.empBranchNameProperty());
            Bindings.bindBidirectional(cmbCountry.valueProperty(), employerBranchBEAN.countryProperty());
            Bindings.bindBidirectional(cmbLocation.valueProperty(), employerBranchBEAN.locationProperty());
            Bindings.bindBidirectional(txtContactAddr.textProperty(), employerBranchBEAN.contactAddressProperty());

            gridBranches.add(txtBranchName, 0, listEmployerBranches.indexOf(employerBranchBEAN) + 1);
            gridBranches.add(cmbCountry, 1, listEmployerBranches.indexOf(employerBranchBEAN) + 1);
            gridBranches.add(cmbLocation, 2, listEmployerBranches.indexOf(employerBranchBEAN) + 1);
            gridBranches.add(txtContactAddr, 3, listEmployerBranches.indexOf(employerBranchBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridBranches.add(hBox, 4, listEmployerBranches.indexOf(employerBranchBEAN) + 1);

        }

    }

    /**
     * Initialize all contact details
     */
    public void initDynaContactDetails() {
        listHeadContacts = employerContactsDAO.retrieveContactDetails(EMPLOYER_ID);
        if (listHeadContacts.size() > 0) {
            addDynaContactDetailsRows();
        } else {
            EmployerContactsBEAN contactsBEAN = new EmployerContactsBEAN();
            listHeadContacts.add(contactsBEAN);
            addDynaContactDetailsRows();
        }
    }

    /**
     * Add all contact details to grid
     */
    public void addDynaContactDetailsRows() {
        gridContacts.getChildren().clear();
        ObservValidateHeadContacts.clear();
        for (EmployerContactsBEAN employerContactsBEAN : listHeadContacts) {
            employerContactsBEAN.setEmployerId(EMPLOYER_ID);

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

            /* ======================== Validation Support ==================== */
            ValidationSupport validationContactSupport = new ValidationSupport();
            validationContactSupport.registerValidator(txtContactName, Validator.createEmptyValidator("Contact Name Required"));
            validationContactSupport.registerValidator(txtDepartment, Validator.createEmptyValidator("Department Required"));
            validationContactSupport.registerValidator(txtStdIsd, Validator.createEmptyValidator("STD/ISD code Rquired"));
            validationContactSupport.registerValidator(txtPhoneNo, Validator.createEmptyValidator("Phone Number Required"));
//            validationSupport.registerValidator(txtPhoneNo, (Control c, String newValue)
//                    -> ValidationResult.fromErrorIf(txtPhoneNo, "Invalid Phone Number", newValue.length() <= 10));
            validationContactSupport.registerValidator(txtEmail, Validator.createRegexValidator("Valid Email Required", "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Severity.ERROR));
            ObservValidateHeadContacts.add(validationContactSupport);
            txtPhoneNo.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(employerContactsBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                            txtPhoneNo.setText(oldValue);
                            Bindings.bindBidirectional(employerContactsBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                        } else {
                            Bindings.unbindBidirectional(employerContactsBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                            txtPhoneNo.setText(newValue);
                            Bindings.bindBidirectional(employerContactsBEAN.phoneNoProperty(), txtPhoneNo.textProperty());
                        }
                    }
                }
            });
            txtStdIsd.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[0-9]*"))) {
                            Bindings.unbindBidirectional(employerContactsBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(employerContactsBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                        } else {
                            Bindings.unbindBidirectional(employerContactsBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                            txtStdIsd.setText(newValue);
                            Bindings.bindBidirectional(employerContactsBEAN.stdIsdProperty(), txtStdIsd.textProperty());
                        }
                    }
                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    /* ======================== Validate Support Contacts ==================== */
                    ValidationResult result = validationContactSupport.getValidationResult();
                    for (ValidationMessage msg : result.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (!validationContactSupport.isInvalid()) {
                        employerContactsBEAN.setEmployerId(EMPLOYER_ID);
                        if (employerContactsBEAN.getEmployerContactId() != null) {
                            employerContactsDAO.updateContactDetails(employerContactsBEAN);
                        } else if (EMPLOYER_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            employerContactsBEAN.setEmployerContactId("cn_" + UiiDGenerator.getUIID8());
                            boolean done = employerContactsDAO.insertContactDetails(employerContactsBEAN);
                        }
                        EmployerContactsBEAN emptyBEAN = new EmployerContactsBEAN();
                        listHeadContacts.add(emptyBEAN);
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
                        if (employerContactsBEAN.getEmployerContactId() != null) {
                            employerContactsDAO.deleteContactDetails(employerContactsBEAN.getEmployerContactId());
                        }
                        listHeadContacts.remove(employerContactsBEAN);
                        if (listHeadContacts.size() > 0) {
                            addDynaContactDetailsRows();
                        } else {
                            EmployerContactsBEAN emptyBEAN = new EmployerContactsBEAN();
                            listHeadContacts.add(emptyBEAN);
                            addDynaContactDetailsRows();
                        }
                        ObservValidateHeadContacts.remove(validationContactSupport);
                        gridContacts.requestFocus();

                    }
                }
            });

            if (listHeadContacts.indexOf(employerContactsBEAN) == 0) {
                gridContacts.add(new Label("Contact Name"), 0, 0);
                gridContacts.add(new Label("Department"), 1, 0);
                gridContacts.add(new Label("STD / ISD"), 2, 0);
                gridContacts.add(new Label("Phone No"), 3, 0);
                gridContacts.add(new Label("Email"), 4, 0);
            }

            Bindings.bindBidirectional(txtContactName.textProperty(), employerContactsBEAN.contactNameProperty());
            Bindings.bindBidirectional(txtDepartment.textProperty(), employerContactsBEAN.departmentProperty());
            Bindings.bindBidirectional(txtStdIsd.textProperty(), employerContactsBEAN.stdIsdProperty());
            Bindings.bindBidirectional(txtPhoneNo.textProperty(), employerContactsBEAN.phoneNoProperty());
            Bindings.bindBidirectional(txtEmail.textProperty(), employerContactsBEAN.emailProperty());

            gridContacts.add(txtContactName, 0, listHeadContacts.indexOf(employerContactsBEAN) + 1);
            gridContacts.add(txtDepartment, 1, listHeadContacts.indexOf(employerContactsBEAN) + 1);
            gridContacts.add(txtStdIsd, 2, listHeadContacts.indexOf(employerContactsBEAN) + 1);
            gridContacts.add(txtPhoneNo, 3, listHeadContacts.indexOf(employerContactsBEAN) + 1);
            gridContacts.add(txtEmail, 4, listHeadContacts.indexOf(employerContactsBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridContacts.add(hBox, 5, listHeadContacts.indexOf(employerContactsBEAN) + 1);

        }

    }

    /**
     * Add additional Features such as jumb to character for combobox,Shrink
     * expand textarea accoding to its content
     */
    public void addAdditionalFeatures() {
        ChangeDateFormat.datePickerDateFormatter(dpContractFrom);
        ChangeDateFormat.datePickerDateFormatter(dpContractTo);
        ComboBoxJumpToChar.jumpToChar(cmbAgencyStatus);
        ComboBoxJumpToChar.jumpToChar(cmbEmplyrType);
        ComboBoxJumpToChar.jumpToChar(cmbCurrencyType);
        /* ======================== Auto Grow Shrink Text Area ==================== */
        TextAreaGrowShrink.makeGrow(txtRemarks);
        TextAreaGrowShrink.makeGrow(txtDescription);
        TextAreaGrowShrink.makeGrow(txtEmplyrAddress);
    }

    /**
     * Inintialize All Combobox Data
     */
    public void initializeMasterData() {
        cmbAgencyStatus.getItems().addAll(SearchConstants.getListAgencyStatus());
        cmbEmplyrType.getItems().addAll(SearchConstants.getListEmployerType());
        cmbCurrencyType.getItems().addAll(RetrieveStaticMasterDAO.getCurrencies());

    }

    /**
     * All Listners used in this form
     */
    public void addButtonListners() {
        /* ======================== Save Button  ==================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isProcessingFeeValid = false;
                boolean isBranchesValid = false;
                boolean isContactDetailsValid = false;
                /* ======================== Validate Basic Info ==================== */
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
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
                /* ======================== Validate Branches ==================== */
                for (ValidationSupport support : ObservValidateBranches) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isBranchesValid = support.isInvalid();
                    }
                }
                /* ======================== Validate Branches ==================== */
                for (ValidationSupport support : ObservValidateHeadContacts) {
                    ValidationResult results = support.getValidationResult();
                    for (ValidationMessage msg : results.getMessages()) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                        break;
                    }
                    if (support.isInvalid()) {
                        isContactDetailsValid = support.isInvalid();
                    }
                }
                System.out.println("Test Validation Valid :: " + validationSupport.isInvalid());
                /* ======================== Insert Basic Info ==================== */
                if (!validationSupport.isInvalid()) {
                    if (employerBEAN.getEmployerId() == null || employerBEAN.getEmployerId().equalsIgnoreCase("")) {
                        /* ======================== Generate Employer Id ==================== */
                        EMPLOYER_ID = "ey_" + UiiDGenerator.getUIID8();
                        employerBEAN.setEmployerId(EMPLOYER_ID);
                        int done = employerDAO.insertEmployerBasics(employerBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Employer Details Saved Successfully!")
                                    .text("Employer Details Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Employer Details Save Failed!")
                                    .text("Employer Details Save Failed!")
                                    .showError();
                            employerBEAN.setEmployerId(null);
                        }
                    } else {
                        int done = employerDAO.updateEmployerBasics(employerBEAN);
                        if (done > 0) {
                            String _employer_if_edited = employerBEAN.getEmployer();
                            /* ======================== Reset Autocompletion ==================== */
                            autoCompletion();
                            txtEmployerName.setText(_employer_if_edited);
                            Notifications.create()
                                    .title("Employer Details Updated Successfully!")
                                    .text("Employer Details Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Employer Details Update Failed!")
                                    .text("Employer Details Update Failed!")
                                    .showError();
                        }
                    }
                }
                /* ======================== Save Contacts Details ==================== */
                if (!isContactDetailsValid && !validationSupport.isInvalid()) {
                    System.out.println("Contact Details are Valid Now");
                    for (EmployerContactsBEAN contactsBEAN : listHeadContacts) {
                        if (contactsBEAN.getEmployerContactId() == null || contactsBEAN.getEmployerContactId().equalsIgnoreCase("")) {
                            contactsBEAN.setEmployerId(EMPLOYER_ID);
                            contactsBEAN.setEmployerContactId("cn_" + UiiDGenerator.getUIID8());
                            employerContactsDAO.insertContactDetails(contactsBEAN);
                        } else {
                            employerContactsDAO.updateContactDetails(contactsBEAN);
                        }
                    }
                }
                /* ======================== Save Branches Under Head Office ==================== */
                if (!isBranchesValid && !validationSupport.isInvalid()) {
                    System.out.println("Branches Is Valid Now");
                    for (EmployerBranchBEAN branchBEAN : listEmployerBranches) {
                        if (branchBEAN.getEmpBranchId() == null || branchBEAN.getEmpBranchId().equalsIgnoreCase("")) {
                            branchBEAN.setEmployerId(EMPLOYER_ID);
                            branchBEAN.setEmpBranchId("br_" + UiiDGenerator.getUIID8());
                            employerBranchDAO.insertEmpBranch(branchBEAN);
                        } else {
                            employerBranchDAO.updateEmpBranch(branchBEAN);
                        }
                    }
                }
                /* ======================== Save Processing Fee ==================== */
                if (!isProcessingFeeValid && !validationSupport.isInvalid()) {
                    System.out.println("Processing Fee Is Valid Now");
                    for (EmployerProcessingFeeBEAN processingFeeBEAN : listEmployerProcessingFee) {
                        if (processingFeeBEAN.getProcessingFeeId() == null || processingFeeBEAN.getProcessingFeeId().equalsIgnoreCase("")) {
                            processingFeeBEAN.setEmployerId(EMPLOYER_ID);
                            processingFeeBEAN.setProcessingFeeId("fe_" + UiiDGenerator.getUIID8());
                            employerProcessingFeeDAO.insertProcessingFee(processingFeeBEAN);
                        } else {
                            employerProcessingFeeDAO.updateProcessingFee(processingFeeBEAN);
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
                            employerBEAN.setInputStream(inputStream);
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
                EMPLOYER_ID = null;
                employerBEAN = new EmployerBEAN();
                bindEmployerBasics();
                imgLogoPreview.setImage(null);
                imgLogoPreview.resize(60, 60);
                imgLogoPreview.setManaged(true);
                initDynaBranchesUnderHeadOffice();
                initDynaContactDetails();
                initDynaProcessingFees();
                TextAreaGrowShrink.makeGrow(txtRemarks);
                TextAreaGrowShrink.makeGrow(txtDescription);
                TextAreaGrowShrink.makeGrow(txtEmplyrAddress);
            }
        });
    }

    /**
     * This method will bind all the controls provided in the Basic Section Of
     * Employer Form
     */
    public void bindEmployerBasics() {
        Bindings.bindBidirectional(txtEmployerName.textProperty(), employerBEAN.employerProperty());
        Bindings.bindBidirectional(txtCommission.textProperty(), employerBEAN.commissionProperty());
        Bindings.bindBidirectional(txtDescription.textProperty(), employerBEAN.descriptionProperty());
        Bindings.bindBidirectional(txtRemarks.textProperty(), employerBEAN.remarksProperty());
        Bindings.bindBidirectional(txtEmplyrAddress.textProperty(), employerBEAN.addressProperty());
        Bindings.bindBidirectional(cmbCurrencyType.valueProperty(), employerBEAN.currencyUsedProperty());
        Bindings.bindBidirectional(cmbEmplyrType.valueProperty(), employerBEAN.employerTypeProperty());
        Bindings.bindBidirectional(cmbAgencyStatus.valueProperty(), employerBEAN.agencyStatusProperty());
        Bindings.bindBidirectional(txtTieUpName.textProperty(), employerBEAN.tieupNameProperty());
        Bindings.bindBidirectional(dpContractFrom.valueProperty(), employerBEAN.contractFromProperty());
        Bindings.bindBidirectional(dpContractTo.valueProperty(), employerBEAN.contractToProperty());
        Bindings.bindBidirectional(txtContractBy.textProperty(), employerBEAN.contarctByProperty());
        Bindings.bindBidirectional(txtRenewalBy.textProperty(), employerBEAN.renewalByProperty());
        Bindings.bindBidirectional(txtWebsite.textProperty(), employerBEAN.websiteProperty());
    }

    /**
     * This method will decorate all the mandatory fields in the form
     */
    public void makeMandaoryDecorations() {
        /* ========================  Validation Support ==================== */
        validationSupport.registerValidator(txtCommission, Validator.createEmptyValidator("Commission Required"));
        validationSupport.registerValidator(txtEmployerName, Validator.createEmptyValidator("Employer Name Required"));
        validationSupport.registerValidator(cmbEmplyrType, Validator.createEmptyValidator("Employer Type Required"));
        validationSupport.registerValidator(cmbCurrencyType, Validator.createEmptyValidator("Currency Type Required"));
        validationSupport.registerValidator(cmbAgencyStatus, Validator.createEmptyValidator("Agency Status Required"));
//        validationSupport.registerValidator(txtTieUpName, true, Validator.createEmptyValidator("Tied Up Name Required"));
        validationSupport.registerValidator(dpContractFrom, Validator.createEmptyValidator("Contract From Required"));
        validationSupport.registerValidator(dpContractTo, Validator.createEmptyValidator("Contract to Required"));
        validationSupport.registerValidator(txtContractBy, Validator.createEmptyValidator("Contract By Required"));
        validationSupport.registerValidator(txtWebsite, Validator.createEmptyValidator("Website Required"));
//        validationSupport.registerValidator(txtWebsite, Validator.createRegexValidator("Website URL Not Valid","/^[a-z](?:[-a-z0-9\\+\\.])*:(?:\\/\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:])*@)?(?:\\[(?:(?:(?:[0-9a-f]{1,4}:){6}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|::(?:[0-9a-f]{1,4}:){5}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){4}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[0-9a-f]{1,4}:[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){3}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,2}[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){2}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,3}[0-9a-f]{1,4})?::[0-9a-f]{1,4}:(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,4}[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,5}[0-9a-f]{1,4})?::[0-9a-f]{1,4}|(?:(?:[0-9a-f]{1,4}:){0,6}[0-9a-f]{1,4})?::)|v[0-9a-f]+[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:]+)\\]|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}|(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=@])*)(?::[0-9]*)?(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@]))*)*|\\/(?:(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@]))*)*)?|(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@]))*)*|(?!(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@])))(?:\\?(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@])|[\\x{E000}-\\x{F8FF}\\x{F0000}-\\x{FFFFD}\\x{100000}-\\x{10FFFD}\\/\\?])*)?(?:\\#(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}!\\$&'\\(\\)\\*\\+,;=:@])|[\\/\\?])*)?$/i",Severity.ERROR));
        validationSupport.validationResultProperty().addListener(new ChangeListener<ValidationResult>() {
            @Override
            public void changed(ObservableValue<? extends ValidationResult> observable, ValidationResult oldValue, ValidationResult newValue) {
                System.out.println("Validate Listner :: " + newValue.getMessages());
            }
        });

    }

}

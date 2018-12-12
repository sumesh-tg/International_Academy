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
package com.zs.ina.accounts.invoice.newinvoice;

import com.jfoenix.controls.JFXDatePicker;
import com.zs.ina.accounts.constants.InvoiceConstants;
import com.zs.ina.accounts.invoice.dao.InvoiceBEAN;
import com.zs.ina.accounts.invoice.dao.InvoiceDAO;
import com.zs.ina.accounts.invoice.dao.InvoiceItemsBEAN;
import com.zs.ina.accounts.invoice.dao.InvoiceItemsDAO;
import com.zs.ina.accounts.master.dao.MasterAccountDataService;
import com.zs.ina.accounts.master.dao.MasterTaxBEAN;
import com.zs.ina.accounts.master.dao.PaymentTermsBEAN;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.enquiry.dao.EnquiryDetailsSearchPOJO;
import com.zs.ina.login.INALoginForm;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import jidefx.utils.converter.DefaultObjectConverter;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
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
public class FXMLNewInvoiceController implements Initializable {

    @FXML
    private ImageView imgClose;
    @FXML
    private Button btnSaveDraft;
    @FXML
    private TextField txtCandidateName;
    @FXML
    private TextField txtInvoiceNumber;
    @FXML
    private TextField txtReferenceNumber;
    @FXML
    private JFXDatePicker dpInvoiceDate;
    @FXML
    private ComboBox<PaymentTermsBEAN> cmbTerms;
    @FXML
    private JFXDatePicker dpDueDate;
    @FXML
    private GridPane gridItems;
    @FXML
    private TextArea txtCustomerNotes;
    @FXML
    private TextArea txtTermsConditions;
    @FXML
    private Button btnSaveSend;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSaveLater;
    @FXML
    private TextField txtAdjustments;
    @FXML
    private Label lblSubTotal;
    @FXML
    private Label lblTax;
    @FXML
    private Label lblTotal;
    @FXML
    private CheckBox chkMailId;
    @FXML
    private HBox hboxCandidateName;
    @FXML
    private Label lblRowTotal;
    /* ======================== Coding ==================== */
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ValidationSupport validationSupport = new ValidationSupport();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Dynamic Beans ==================== */
    InvoiceDAO invoiceDAO = springAppContext.getBean(InvoiceDAO.class);
    InvoiceItemsDAO invoiceItemsDAO = springAppContext.getBean(InvoiceItemsDAO.class);
    /* ======================== Dynamic Control Lists ==================== */
    ObservableList<InvoiceItemsBEAN> listInvoiceItem = FXCollections.observableArrayList();
    /* ======================== Validation Support ==================== */
    ObservableList<ValidationSupport> observValidateInvoiceItems = FXCollections.observableArrayList();

    private String INVOICE_ID = null;
    InvoiceBEAN invoiceBEAN = new InvoiceBEAN();
    List<EnquiryDetailsSearchPOJO> enquiryListDeSerialize = new ArrayList<>();
    /* ======================== Object For Retrieve Master Data Service ==================== */
    MasterAccountDataService masterAccountData = new MasterAccountDataService();
    /* ======================== Invoice Variebles ==================== */
    double SUB_TOTAL = 0;
    double TAX_TOTAL = 0;
    double ADJUSTMENT = 0;
    double TOTAL = 0;
    private String CUR_USERNAME = null;
    private String CUR_ROLE = null;
    private String CUR_BRANCH = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();
        /* ======================== De-Serialize All the enquiry details ==================== */
        deSerializeEnquiryData();
        /* ======================== Close Event ==================== */
        imgClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) imgClose.getScene().getWindow();
                stage.close();
            }
        });
        loadMasterData();
        initDynamicItems();
        bindAllInvoiceDetails();
        loadButtonActions();
        addValidationSupport();
        changeEvents();
        /* ======================== Set DatePicker Format ==================== */
        ChangeDateFormat.datePickerDateFormatter(dpDueDate);
        ChangeDateFormat.datePickerDateFormatter(dpInvoiceDate);
        /* ======================== Initialize Invoice Date ==================== */
        dpInvoiceDate.setValue(LocalDate.now());
        /* ======================== Disable Due Date Picker ==================== */
        dpDueDate.setDisable(true);

    }

    public void changeEvents() {
        /* ======================== Adjustment Change Events ==================== */
        txtAdjustments.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calculateRowAmount();
                if (newValue != null) {
                    if (!(newValue.matches("[0-9,+,-]*"))) {
                        Bindings.unbindBidirectional(invoiceBEAN.adjustmentProperty(), txtAdjustments.textProperty());
                        txtAdjustments.setText(oldValue);
                        Bindings.bindBidirectional(invoiceBEAN.adjustmentProperty(), txtAdjustments.textProperty());
                    } else {
                        Bindings.unbindBidirectional(invoiceBEAN.adjustmentProperty(), txtAdjustments.textProperty());
                        txtAdjustments.setText(newValue);
                        Bindings.bindBidirectional(invoiceBEAN.adjustmentProperty(), txtAdjustments.textProperty());
                    }
                }
            }
        });
        /* ======================== Payment Terms Change Events ==================== */
        cmbTerms.valueProperty().addListener(new ChangeListener<PaymentTermsBEAN>() {
            @Override
            public void changed(ObservableValue<? extends PaymentTermsBEAN> observable, PaymentTermsBEAN oldValue, PaymentTermsBEAN newValue) {
                if (newValue != null) {
                    if (newValue.getNumberOfDays() != null) {
                        LocalDate dateInvoiceFrom = dpInvoiceDate.getValue();
                        if (dateInvoiceFrom != null) {
                            dpDueDate.setValue(dateInvoiceFrom.plusDays(Integer.parseInt(newValue.getNumberOfDays())));
                        }
                    }
                }
            }
        });
        /* ======================== Change due date when invoice date changed ==================== */
        dpInvoiceDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    if (cmbTerms.getSelectionModel().getSelectedIndex() > -1) {
                        dpDueDate.setValue(newValue.plusDays(Integer.parseInt(cmbTerms.getSelectionModel().getSelectedItem().getNumberOfDays())));
                    }
                }
            }
        });
    }

    public void deSerializeEnquiryData() {
        Task taskSerialize = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    FileInputStream fileIn = new FileInputStream("Enquiry_details.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    enquiryListDeSerialize = (List<EnquiryDetailsSearchPOJO>) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                return enquiryListDeSerialize;
            }
        };
        taskSerialize.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("All enquiry details are retrieved from Enquiry_details.ser file... ");
                System.out.println("test data :: " + enquiryListDeSerialize.size());
                hboxCandidateName.getChildren().remove(txtCandidateName);
                txtCandidateName = TextFields.createClearableTextField();
                validationSupport.registerValidator(txtCandidateName, Validator.createEmptyValidator("Please choose a candidate!"));
                /* ======================== Auto Completion On CandidateName ==================== */
                AutoCompletionBinding<EnquiryDetailsSearchPOJO> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtCandidateName, enquiryListDeSerialize);
                autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<EnquiryDetailsSearchPOJO>>() {
                    @Override
                    public void handle(AutoCompletionBinding.AutoCompletionEvent<EnquiryDetailsSearchPOJO> event) {
                        System.out.println("Test AutoCompletion :: " + event.getCompletion().toString());
                    }
                });
                HBox.setHgrow(txtCandidateName, Priority.ALWAYS);
                txtCandidateName.setMaxWidth(Double.MAX_VALUE);
                hboxCandidateName.getChildren().add(txtCandidateName);
            }
        });
        new Thread(taskSerialize).start();
    }

    public void loadButtonActions() {
        btnSaveSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    if (msg.getTarget() != null && msg.getText() != null) {
                        popupMessages.showError(msg.getText(), msg.getTarget());
                    }
                    break;
                }
                if (!validationSupport.isInvalid()) {
                    invoiceBEAN.setBranch(CUR_BRANCH);
                    invoiceBEAN.setCreatedUser(CUR_USERNAME);
                    invoiceBEAN.setUpdatedUser(CUR_USERNAME);
                    if (invoiceBEAN.getInvoiceIid() == null || invoiceBEAN.getInvoiceIid().equalsIgnoreCase("")) {
                        /* ======================== Generate New Invoice Id ==================== */
                        INVOICE_ID = "inv_" + UiiDGenerator.getUIID8();
                        invoiceBEAN.setInvoiceIid(INVOICE_ID);
                        boolean done = invoiceDAO.insertInvoiceMain(invoiceBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("New invoice has been created successfully!")
                                    .text("New invoice has been created successfully!")
                                    .showInformation();
                            sendComposeEmail();
                        } else {
                            Notifications.create()
                                    .title("Invoice creation failed!")
                                    .text("Invoice creation failed!")
                                    .showError();
                            invoiceBEAN.setInvoiceIid(null);
                        }
                    } else {
                        boolean done = invoiceDAO.updateInvoiceMain(invoiceBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Invoice updated successfully!")
                                    .text("Invoice updated successfully!")
                                    .showInformation();
                            sendComposeEmail();
                        } else {
                            Notifications.create()
                                    .title("Invoice updation failed!")
                                    .text("Invoice updation failed!")
                                    .showError();
                            invoiceBEAN.setInvoiceIid(null);
                        }
                    }
                }

            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to cancel invoice?", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnCancel.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    Stage stageClose = (Stage) imgClose.getScene().getWindow();
                    stageClose.close();
                }
            }
        });
    }

    public void sendComposeEmail() {
        /* ======================== Load Email Composer ==================== */

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/invoice/email/FXMLEmailComposer.fxml"));
//                    root.getStylesheets().add("/com/zs/ina/enquiry/search/fxmlenquirysearch.css");
            Scene secondScene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setTitle("ADD/EDIT INVOICE ITEMS");
            secondStage.setScene(secondScene);
            secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.initOwner(btnCancel.getScene().getWindow().getScene().getWindow());
            secondStage.initStyle(StageStyle.UNDECORATED);
            /* ======================== Close Parent Window ==================== */
            Stage stageParent = (Stage) btnSaveSend.getScene().getWindow();
            stageParent.hide();
            secondStage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    stageParent.show();
                }
            });
            secondStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
            // logger.info(ex.getMessage());
        }
    }

    public void addValidationSupport() {
//        validationSupport.registerValidator(txtCandidateName, Validator.createEmptyValidator("Please choose a candidate!"));
        validationSupport.registerValidator(txtInvoiceNumber, Validator.createEmptyValidator("Invoice Number Required!"));
        validationSupport.registerValidator(txtReferenceNumber, Validator.createEmptyValidator("Reference Number Required!"));
        validationSupport.registerValidator(dpDueDate, Validator.createEmptyValidator("Due Date Required!"));
        validationSupport.registerValidator(dpInvoiceDate, Validator.createEmptyValidator("Invoice Date Required!"));

    }

    public void bindAllInvoiceDetails() {
        StringConverter converterTerms = new DefaultObjectConverter<PaymentTermsBEAN>() {
            @Override
            public String toString(PaymentTermsBEAN paymentTermsBean) {
                if (paymentTermsBean != null) {
                    return paymentTermsBean.getNumberOfDays();
                } else {
                    return "";
                }
            }
        };

        Bindings.bindBidirectional(txtInvoiceNumber.textProperty(), invoiceBEAN.invoiceNumberProperty());
        Bindings.bindBidirectional(txtInvoiceNumber.textProperty(), invoiceBEAN.invoiceNumberProperty());
        Bindings.bindBidirectional(txtReferenceNumber.textProperty(), invoiceBEAN.referenceNumberProperty());
        Bindings.bindBidirectional(dpDueDate.valueProperty(), invoiceBEAN.dueDateProperty());
        Bindings.bindBidirectional(dpInvoiceDate.valueProperty(), invoiceBEAN.invoiceCreatedDateProperty());
        Bindings.bindBidirectional(invoiceBEAN.termsProperty(), cmbTerms.valueProperty(), converterTerms);
        Bindings.bindBidirectional(lblSubTotal.textProperty(), invoiceBEAN.subTotalProperty());
        Bindings.bindBidirectional(lblTax.textProperty(), invoiceBEAN.taxTotalProperty());
        Bindings.bindBidirectional(lblTotal.textProperty(), invoiceBEAN.totalProperty());
        Bindings.bindBidirectional(txtAdjustments.textProperty(), invoiceBEAN.adjustmentProperty());
        Bindings.bindBidirectional(txtCustomerNotes.textProperty(), invoiceBEAN.notesProperty());
        Bindings.bindBidirectional(txtTermsConditions.textProperty(), invoiceBEAN.tersAndConditionsProperty());
    }

    public void loadMasterData() {
        /* ======================== Overrride ToString Method ==================== */
        ObservableList<PaymentTermsBEAN> listPaymentTerms = masterAccountData.retrievePaymentTerms();
        PaymentTermsBEAN termsBEAN = new PaymentTermsBEAN();
        termsBEAN.setTermsId("0");
        termsBEAN.setNumberOfDays("0");
        termsBEAN.setIsDelete("0");
        termsBEAN.setShowItAs("DUE ON DATE");
        listPaymentTerms.add(termsBEAN);
        ObservableList<PaymentTermsBEAN> listRecreateToString = FXCollections.observableArrayList();
        listPaymentTerms.forEach((employerOriginalBEAN) -> {
            PaymentTermsBEAN autoCompletionBEAN1 = (PaymentTermsBEAN) employerOriginalBEAN;
            PaymentTermsBEAN autoCompletionBEAN = new PaymentTermsBEAN() {
                @Override
                public String toString() {
                    return autoCompletionBEAN1.getShowItAs();
                }
            };
            BeanUtils.copyProperties(autoCompletionBEAN1, autoCompletionBEAN);
            listRecreateToString.add(autoCompletionBEAN);
        });

        /* ======================== Add Terms ( Due date is calculated based on due date ) ==================== */
        cmbTerms.getItems().addAll(listRecreateToString);
    }

    public void initDynamicItems() {
        listInvoiceItem = invoiceItemsDAO.retrieveInvoiceItem(getINVOICE_ID());
        if (listInvoiceItem.size() > 0) {
            addDynaInvoiceItems();
        } else {
            InvoiceItemsBEAN invoiceItemsBEAN = new InvoiceItemsBEAN();
            listInvoiceItem.add(invoiceItemsBEAN);
            addDynaInvoiceItems();
        }
    }

    public void addDynaInvoiceItems() {
        gridItems.getChildren().clear();
        observValidateInvoiceItems.clear();
        SUB_TOTAL = 0;
        for (InvoiceItemsBEAN invoiceItemsBEAN : listInvoiceItem) {
            invoiceItemsBEAN.setInvoiceId(INVOICE_ID);

            TextField txtItemDetails = new TextField("Item");
            TextField txtQuantity = new TextField("Quantity");
            TextField txtRate = new TextField("Rate");

            HBox hBoxDiscount = new HBox();
            TextField txtDiscount = new TextField();

            ComboBox<InvoiceConstants.Discounts> cmbDiscountTerms = new ComboBox();
            ComboBoxJumpToChar.jumpToChar(cmbDiscountTerms);
            ComboBox<MasterTaxBEAN> cmbTax = new ComboBox();

            ImageView imgClose = new ImageView(new Image(FXMLNewInvoiceController.class.getResourceAsStream("clear.png")));
            Label lblTaxClose = new Label();
            lblTaxClose.setGraphic(imgClose);
            lblTaxClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    cmbTax.getSelectionModel().clearSelection();
                    calculateRowAmount();
                }
            });
            HBox hBoxTax = new HBox();

            hBoxTax.getChildren().addAll(cmbTax, lblTaxClose);

            Label lblTotalAmount = new Label("0.00");
            HBox.setHgrow(cmbDiscountTerms, Priority.ALWAYS);
            HBox.setHgrow(txtDiscount, Priority.ALWAYS);
            HBox.setHgrow(hBoxDiscount, Priority.ALWAYS);

            cmbDiscountTerms.setMaxWidth(Double.MAX_VALUE);
            cmbDiscountTerms.setMinWidth(60);
            txtDiscount.setMaxWidth(Double.MAX_VALUE);
            hBoxDiscount.setMaxWidth(Double.MAX_VALUE);

            hBoxDiscount.getChildren().addAll(txtDiscount, cmbDiscountTerms);
            cmbDiscountTerms.getItems().addAll(InvoiceConstants.Discounts.values());
            cmbDiscountTerms.valueProperty().addListener(new ChangeListener<InvoiceConstants.Discounts>() {
                @Override
                public void changed(ObservableValue<? extends InvoiceConstants.Discounts> observable, InvoiceConstants.Discounts oldValue, InvoiceConstants.Discounts newValue) {
                    if (newValue != null) {
                        switch (newValue) {
                            case PERCENTAGE:
                                invoiceItemsBEAN.setDiscountType("PERCENTAGE");
                                calculateRowAmount();
                                break;
                            case RUPEESE:
                                invoiceItemsBEAN.setDiscountType("RUPEESE");
                                calculateRowAmount();
                                break;
                            default:
                                System.out.println("DEFAULT DISCOUNT TERMS :: ");
                                break;
                        }
                    }
                }
            });
            cmbDiscountTerms.getSelectionModel().selectFirst();

            /* ======================== Override ToString Method ==================== */
            ObservableList<MasterTaxBEAN> listTax = masterAccountData.retrieveTax();
            ObservableList<MasterTaxBEAN> listTaxRecreateToString = FXCollections.observableArrayList();
            listTax.forEach((employerOriginalBEAN) -> {
                MasterTaxBEAN autoCompletionBEAN1 = (MasterTaxBEAN) employerOriginalBEAN;
                MasterTaxBEAN autoCompletionBEAN = new MasterTaxBEAN() {
                    @Override
                    public String toString() {
                        return autoCompletionBEAN1.getTaxName();
                    }
                };
                BeanUtils.copyProperties(autoCompletionBEAN1, autoCompletionBEAN);
                listTaxRecreateToString.add(autoCompletionBEAN);
            });

            cmbTax.getItems().addAll(listTaxRecreateToString);

            cmbTax.valueProperty().addListener(new ChangeListener<MasterTaxBEAN>() {
                @Override
                public void changed(ObservableValue<? extends MasterTaxBEAN> observable, MasterTaxBEAN oldValue, MasterTaxBEAN newValue) {
                    if (newValue != null) {
                        invoiceItemsBEAN.setTaxPercentage(newValue.getRate());
                        invoiceItemsBEAN.setTaxId(newValue.getTaxId());
                    }
                    calculateRowAmount();
                }
            });

            for (MasterTaxBEAN mtbean : cmbTax.getItems()) {
                if (mtbean.getRate().equalsIgnoreCase(invoiceItemsBEAN.getRate())) {
                    cmbTax.getSelectionModel().select(mtbean);
                }
            }

            GridPane.setHgrow(txtItemDetails, Priority.NEVER);
            GridPane.setHgrow(txtQuantity, Priority.NEVER);
            GridPane.setHgrow(txtRate, Priority.NEVER);
            GridPane.setHgrow(cmbTax, Priority.NEVER);
            GridPane.setHgrow(hBoxDiscount, Priority.NEVER);
            GridPane.setHgrow(lblTotalAmount, Priority.NEVER);

            txtItemDetails.setMaxWidth(Double.MAX_VALUE);
            txtQuantity.setMaxWidth(Double.MAX_VALUE);
            txtRate.setMaxWidth(Double.MAX_VALUE);
            cmbTax.setMaxWidth(Double.MAX_VALUE);
            hBoxDiscount.setMaxWidth(Double.MAX_VALUE);
            lblTotalAmount.setMaxWidth(Double.MAX_VALUE);
            lblTotalAmount.setAlignment(Pos.CENTER);

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
            validationAcademicReqSupport.registerValidator(txtItemDetails, Validator.createEmptyValidator("Item Details Required"));
            validationAcademicReqSupport.registerValidator(txtQuantity, Validator.createEmptyValidator("Quantity Required"));
            validationAcademicReqSupport.registerValidator(txtRate, Validator.createEmptyValidator("Rate Required"));

            observValidateInvoiceItems.add(validationAcademicReqSupport);

            /* ======================== Validation Change Events ==================== */
            txtQuantity.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*")) || newValue.length() < 0) {
                        Bindings.unbindBidirectional(invoiceItemsBEAN.quantityProperty(), txtQuantity.textProperty());
                        txtQuantity.setText(oldValue);
                        Bindings.bindBidirectional(invoiceItemsBEAN.quantityProperty(), txtQuantity.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(invoiceItemsBEAN.quantityProperty(), txtQuantity.textProperty());
                            txtQuantity.setText(newValue);
                            Bindings.bindBidirectional(invoiceItemsBEAN.quantityProperty(), txtQuantity.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(invoiceItemsBEAN.quantityProperty(), txtQuantity.textProperty());
                            txtQuantity.setText(oldValue);
                            Bindings.bindBidirectional(invoiceItemsBEAN.quantityProperty(), txtQuantity.textProperty());
                            //  exception.printStackTrace();
                        }
                    }

                }
            });
            txtRate.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(invoiceItemsBEAN.rateProperty(), txtRate.textProperty());
                        txtRate.setText(oldValue);
                        Bindings.bindBidirectional(invoiceItemsBEAN.rateProperty(), txtRate.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(invoiceItemsBEAN.rateProperty(), txtRate.textProperty());
                            txtRate.setText(newValue);
                            Bindings.bindBidirectional(invoiceItemsBEAN.rateProperty(), txtRate.textProperty());
                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(invoiceItemsBEAN.rateProperty(), txtRate.textProperty());
                            txtRate.setText(oldValue);
                            Bindings.bindBidirectional(invoiceItemsBEAN.rateProperty(), txtRate.textProperty());
                            //  exception.printStackTrace();
                        }
                    }

                }
            });
            txtDiscount.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    if (newValue == null || newValue.equalsIgnoreCase("")) {

                    } else if (!(newValue.matches("[0-9,.]*"))) {
                        Bindings.unbindBidirectional(invoiceItemsBEAN.discountAmountProperty(), txtDiscount.textProperty());
                        txtDiscount.setText(oldValue);
                        Bindings.bindBidirectional(invoiceItemsBEAN.discountAmountProperty(), txtDiscount.textProperty());
                    } else {
                        /* ======================== Parse to float ==================== */
                        try {
                            Float.parseFloat(newValue);
                            // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                            Bindings.unbindBidirectional(invoiceItemsBEAN.discountAmountProperty(), txtDiscount.textProperty());
                            txtDiscount.setText(newValue);
                            Bindings.bindBidirectional(invoiceItemsBEAN.discountAmountProperty(), txtDiscount.textProperty());

                        } catch (NumberFormatException exception) {
                            Bindings.unbindBidirectional(invoiceItemsBEAN.discountAmountProperty(), txtDiscount.textProperty());
                            txtDiscount.setText(oldValue);
                            Bindings.bindBidirectional(invoiceItemsBEAN.discountAmountProperty(), txtDiscount.textProperty());
                            //  exception.printStackTrace();
                        }
                    }

                }
            });
            /* ======================== Convert All Values to Float ==================== */
            txtQuantity.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtQuantity.getText() == null || txtQuantity.getText().equalsIgnoreCase("")) {
                        } else {
                            txtQuantity.setText("" + Float.parseFloat(txtQuantity.getText()));
                            /* ======================== Calculate Row Amount ==================== */
                            calculateRowAmount();
                        }
                    }
                }
            });
            txtRate.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtRate.getText() == null || txtRate.getText().equalsIgnoreCase("")) {
                        } else {
                            txtRate.setText("" + Float.parseFloat(txtRate.getText()));
                            /* ======================== Calculating Row Amount ==================== */
                            calculateRowAmount();
                        }
                    }
                }
            });
            txtDiscount.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (txtDiscount.getText() == null || txtDiscount.getText().equalsIgnoreCase("")) {
                        } else {
                            txtDiscount.setText("" + Float.parseFloat(txtDiscount.getText()));
                            /* ======================== Calculate Row Amount ==================== */
                            calculateRowAmount();
                        }
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
                        invoiceItemsBEAN.setInvoiceId(INVOICE_ID);
                        if (invoiceItemsBEAN.getLineId() != null) {
                            invoiceItemsDAO.updateInvoiceItem(invoiceItemsBEAN);
                        } else if (INVOICE_ID != null) {
                            /* ======================== Generate Contact_Id ==================== */
                            invoiceItemsBEAN.setLineId("ad_" + UiiDGenerator.getUIID8());
                            boolean done = invoiceItemsDAO.insertInvoiceItem(invoiceItemsBEAN);
                            if (!done) {
                                invoiceItemsBEAN.setLineId(null);
                            }
                        }
                        InvoiceItemsBEAN emptyBEAN = new InvoiceItemsBEAN();
                        listInvoiceItem.add(emptyBEAN);
                        addDynaInvoiceItems();
                    }
//                    else {
//                        popupMessages.showError("All Fields Required In Contact Details!", gridItems);
//                    }
                    gridItems.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnCancel.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (invoiceItemsBEAN.getLineId() != null) {
                            invoiceItemsDAO.deleteInvoiceItem(invoiceItemsBEAN.getLineId());
                        }
                        listInvoiceItem.remove(invoiceItemsBEAN);
                        if (listInvoiceItem.size() > 0) {
                            addDynaInvoiceItems();
                        } else {
                            InvoiceItemsBEAN emptyBEAN = new InvoiceItemsBEAN();
                            listInvoiceItem.add(emptyBEAN);
                            addDynaInvoiceItems();
                        }
                        observValidateInvoiceItems.remove(validationAcademicReqSupport);
                        gridItems.requestFocus();

                    }
                }
            });
            /* ======================== Add All Controls To Grid ==================== */
            if (listInvoiceItem.indexOf(invoiceItemsBEAN) == 0) {
                gridItems.add(new Label("ITEM DETAILS"), 0, 0);
                gridItems.add(new Label("QUANTITY"), 1, 0);
                gridItems.add(new Label("RATE"), 2, 0);
                gridItems.add(new Label("DISCOUNT"), 3, 0);
                gridItems.add(new Label("TAX"), 4, 0);
                gridItems.add(new Label("AMOUNT"), 5, 0);
            }

//            StringConverter converterTax = new DefaultObjectConverter<MasterTaxBEAN>() {
//                @Override
//                public String toString(MasterTaxBEAN masterTaxBEAN) {
//                    if (masterTaxBEAN != null) {
//                        return masterTaxBEAN.getRate();
//                    } else {
//                        return "";
//                    }
//                }
//            };
            Bindings.bindBidirectional(txtItemDetails.textProperty(), invoiceItemsBEAN.itemNameProperty());
            Bindings.bindBidirectional(txtQuantity.textProperty(), invoiceItemsBEAN.quantityProperty());
            Bindings.bindBidirectional(txtRate.textProperty(), invoiceItemsBEAN.rateProperty());
//            Bindings.bindBidirectional(invoiceItemsBEAN.taxPercentageProperty(), cmbTax.valueProperty(), converterTax);
            Bindings.bindBidirectional(txtDiscount.textProperty(), invoiceItemsBEAN.discountAmountProperty());
            Bindings.bindBidirectional(lblTotalAmount.textProperty(), invoiceItemsBEAN.itemTotalProperty());

            calculateRowAmount();

            gridItems.add(txtItemDetails, 0, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);
            gridItems.add(txtQuantity, 1, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);
            gridItems.add(txtRate, 2, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);
            gridItems.add(hBoxDiscount, 3, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);
            gridItems.add(hBoxTax, 4, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);
            gridItems.add(lblTotalAmount, 5, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridItems.add(hBox, 6, listInvoiceItem.indexOf(invoiceItemsBEAN) + 1);
        }
    }

    public void calculateRowAmount() {
        SUB_TOTAL = 0;
        TAX_TOTAL = 0;
        TOTAL = 0;
        for (InvoiceItemsBEAN itemsBEAN : listInvoiceItem) {
            if (itemsBEAN.getQuantity() == null || itemsBEAN.getRate() == null || itemsBEAN.getRate().equalsIgnoreCase("") || itemsBEAN.getQuantity().equalsIgnoreCase("")) {
            } else {

                double rowSum = Double.parseDouble(itemsBEAN.getQuantity()) * Double.parseDouble(itemsBEAN.getRate());
                /* ======================== Calculate Tax Before Discount (if any) ==================== */
                if (itemsBEAN.getTaxPercentage() == null || itemsBEAN.getTaxPercentage().equalsIgnoreCase("")) {
                } else {
                    TAX_TOTAL = TAX_TOTAL + ((rowSum * Double.parseDouble(itemsBEAN.getTaxPercentage())) / 100);
                }
                /* ======================== deduct discounts ==================== */
                double discoundPerRow = 0;
                if (itemsBEAN.getDiscountAmount() != null) {
                    discoundPerRow = Double.parseDouble(itemsBEAN.getDiscountAmount());
                }

                switch (itemsBEAN.getDiscountType()) {
                    case "PERCENTAGE":
                        rowSum = rowSum - ((rowSum * discoundPerRow) / 100);
                        break;
                    case "RUPEESE":
                        rowSum = rowSum - discoundPerRow;
                        break;
                    default:
                        System.out.println("DEFAULT DISCOUNT ::");
                        break;
                }

                SUB_TOTAL = SUB_TOTAL + rowSum;
                lblSubTotal.setText("" + SUB_TOTAL);
                itemsBEAN.setItemTotal("" + rowSum);
            }
        }
        /* ======================== Show Tax (If Any) ==================== */
        lblTax.setText("" + TAX_TOTAL);
        double adjustment = 0;
        if (txtAdjustments.getText() == null || txtAdjustments.getText().equalsIgnoreCase("") ) {
        } else {
            try {
                adjustment = Double.parseDouble(txtAdjustments.getText());
            } catch (Exception es) {
                es.getMessage();
            }
        }
        TOTAL = SUB_TOTAL + TAX_TOTAL + adjustment;
        Double truncatedDouble = BigDecimal.valueOf(TOTAL)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        lblTotal.setText(truncatedDouble + "");
    }

    public String getINVOICE_ID() {
        return INVOICE_ID;
    }

    public void setINVOICE_ID(String INVOICE_ID) {
        this.INVOICE_ID = INVOICE_ID;
    }
}

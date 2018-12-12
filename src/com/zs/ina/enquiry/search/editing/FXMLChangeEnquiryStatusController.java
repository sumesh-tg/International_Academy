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

import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryDAO;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryIMPl;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryPOJO;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.email.E_MailSender;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jfxtras.scene.control.LocalTimePicker;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author 100018
 */
public class FXMLChangeEnquiryStatusController implements Initializable {

    static Logger logger = Logger.getLogger(FXMLChangeEnquiryStatusController.class);
    ShowPopupMessages showErrors = new ShowPopupMessages();

    @FXML
    private ComboBox<EnquirySatusPOJO> cmbEnquiryStatus;
    @FXML
    private Button btnSave;
    CounselorDetailsBEAN counselorDetailsBEAN;
    String ENQUIRY_ID = null;
    @FXML
    private TextField txtReason;
    @FXML
    private Label lblReason;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNotification;
    @FXML
    private Label lblEnquiryStatus;
    ForwardHistoryDAO forwardHistoryDAO = new ForwardHistoryIMPl();
    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();
    private String CUR_USERNAME = null;
    private String CUR_BRANCH = null;
    @FXML
    private DatePicker datePickAppDate;
    @FXML
    private HBox hboxAppTime;
    LocalTimePicker localTimePicker = new LocalTimePicker();
    @FXML
    private Label lblTime;
    boolean ENABLE = true;
    @FXML
    private ComboBox<String> cmbHour;
    @FXML
    private ComboBox<String> cmbMinute;
    @FXML
    private ComboBox<String> cmbAmOrPm;
    int HOUR = 0;
    int MINUTE = 0;
    String AM_PM = "AM";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        hboxAppTime.getChildren().add(localTimePicker);
        ChangeDateFormat.datePickerDateFormatter(datePickAppDate);
        datePickAppDate.setDayCellFactory(dayCellFactory);
        ObservableList<EnquirySatusPOJO> enquirySatusList = RetrieveStaticMasterDAO.retrieveEnquiryAppStatus();
        cmbEnquiryStatus.setItems(enquirySatusList);
        /* ====================== Status Changes  ====================== */
        cmbEnquiryStatus.valueProperty().addListener(new ChangeListener<EnquirySatusPOJO>() {

            @Override
            public void changed(ObservableValue<? extends EnquirySatusPOJO> observable, EnquirySatusPOJO oldValue, EnquirySatusPOJO newValue) {
                if (newValue != null) {
                    clearForm();
                    counselorDetailsBEAN.setStatus(newValue.getId());
                    counselorDetailsBEAN.setCompletionflag(newValue.getProcessCompletionStatus());

                    if (newValue.getDateReasonEnable().equalsIgnoreCase("0")) {
                        txtReason.setDisable(true);
                        datePickAppDate.setDisable(true);
                        localTimePicker.setDisable(true);

                        lblReason.setDisable(true);
                        lblDate.setDisable(true);
                        lblTime.setDisable(true);

                        ENABLE = false;
                    } else {
                        txtReason.setDisable(false);
                        datePickAppDate.setDisable(false);
                        localTimePicker.setDisable(false);

                        lblReason.setDisable(false);
                        lblDate.setDisable(false);
                        lblTime.setDisable(false);

                        ENABLE = true;
                    }
                }
            }
        });
        for (EnquirySatusPOJO espojo : cmbEnquiryStatus.getItems()) {
            if (counselorDetailsBEAN.getStatus().equalsIgnoreCase(espojo.getId())) {
                cmbEnquiryStatus.getSelectionModel().select(espojo);
            }
        }
        System.out.println("Check Data :: " + counselorDetailsBEAN.toString());
        lblNotification.setText("");
        Text txtTitle = new Text("Change Enquiry Status Of " + counselorDetailsBEAN.getContactName());
        txtTitle.setStyle("-fx-fill:white");
        lblEnquiryStatus.setText("");
        lblEnquiryStatus.setGraphic(txtTitle);

        /* ====================== Save Enquiry Status ====================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (ENABLE) {
                    if (validateStatusChange()) {
                        saveEnquiryStatus();
                    }
                } else {
                    saveEnquiryStatus();
                }
            }
        });
        //lblEnquiryStatus.setText(lblEnquiryStatus.getText() + " " + counselorDetailsBEAN.getContactName());
        //    Bindings.bindBidirectional(cmbEnquiryStatus.valueProperty(), counselorDetailsBEAN.statusProperty());
        Bindings.bindBidirectional(txtReason.textProperty(), counselorDetailsBEAN.remarksProperty());
        String pattern = "yyyy-MM-dd";
        String timePattern = "HH:mm:ss";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        StringConverter<LocalDate> localDateConverter = new LocalDateStringConverter(formatter, null);
        StringConverter<LocalTime> localTimeConverter = new LocalTimeStringConverter(timeFormatter, null);
        Bindings.bindBidirectional(counselorDetailsBEAN.appointmentDateProperty(), datePickAppDate.valueProperty(), localDateConverter);
        Bindings.bindBidirectional(counselorDetailsBEAN.appointmentTimeProperty(), localTimePicker.localTimeProperty(), localTimeConverter);
        /* ======================== New Time ==================== */
        for (int i = 1; i <= 12; i++) {
            cmbHour.getItems().add("" + String.format("%02d", i));
        }
        for (int i = 1; i <= 59; i++) {
            cmbMinute.getItems().add("" + String.format("%02d", i));
        }
        cmbAmOrPm.getItems().add("AM");
        cmbAmOrPm.getItems().add("PM");
        cmbAmOrPm.getSelectionModel().selectFirst();
        cmbHour.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    HOUR = Integer.parseInt(newValue);
                    if (HOUR == 12) {
                        HOUR = 0;
                    }
                    if (AM_PM.equalsIgnoreCase("AM")) {
                        localTimePicker.setLocalTime(LocalTime.of(HOUR, MINUTE));
                    } else {
                        localTimePicker.setLocalTime(LocalTime.of(HOUR + 12, MINUTE));
                    }
                }
            }
        });
        cmbMinute.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    MINUTE = Integer.parseInt(newValue);
                    if (AM_PM.equalsIgnoreCase("AM")) {
                        localTimePicker.setLocalTime(LocalTime.of(HOUR, MINUTE));
                    } else {
                        localTimePicker.setLocalTime(LocalTime.of(HOUR + 12, MINUTE));
                    }
                }
            }
        });
        cmbAmOrPm.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    AM_PM = newValue;
                    if (AM_PM.equalsIgnoreCase("AM")) {
                        localTimePicker.setLocalTime(LocalTime.of(HOUR, MINUTE));
                    } else {
                        localTimePicker.setLocalTime(LocalTime.of(HOUR + 12, MINUTE));
                    }
                }
            }
        });
        //     localTimePicker.setLocalTime(null);
        ComboBoxJumpToChar.jumpToChar(cmbHour);
        ComboBoxJumpToChar.jumpToChar(cmbMinute);
        ComboBoxJumpToChar.jumpToChar(cmbAmOrPm);
    }

    /**
     *
     */
    public void clearForm() {
        txtReason.clear();
        localTimePicker.setLocalTime(null);
    }
    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        @Override
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
            };
        }
    };

    /**
     *
     */
    public void saveEnquiryStatus() {

        System.out.println("Set App Date : " + counselorDetailsBEAN.getAppointmentDate());
        System.out.println("Set App Time : " + counselorDetailsBEAN.getAppointmentTime());
        ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
        historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
        historyPOJO.setAssigned_branch(CUR_BRANCH);
        historyPOJO.setAssigned_by(CUR_USERNAME);
        if (ENABLE) {
            historyPOJO.setAssigned_date(counselorDetailsBEAN.getAppointmentDate() + " " + counselorDetailsBEAN.getAppointmentTime());
        }
        historyPOJO.setEnquiry_id(counselorDetailsBEAN.getEnquiryID());
        historyPOJO.setAssigned_to(counselorDetailsBEAN.getEnquiryAssignedTo());
        historyPOJO.setAssigned_to_branch(counselorDetailsBEAN.getBracnch());
        historyPOJO.setHolded_by(counselorDetailsBEAN.getEnquiryAssignedTo());
        historyPOJO.setPurpose("Assessment Pending");
        if (txtReason.getText() == null) {
            historyPOJO.setRemarks("");
        } else {
            historyPOJO.setRemarks(txtReason.getText());
        }
        if (counselorDetailsBEAN.getStatus() != null) {
            historyPOJO.setCurrent_status(counselorDetailsBEAN.getStatus());
        } else {
            historyPOJO.setCurrent_status("Assessment Pending");
        }
        historyPOJO.setCompletionFlag(counselorDetailsBEAN.getCompletionflag());
        historyPOJO.setImportantFlag(counselorDetailsBEAN.getImportant());
        historyPOJO.setStudy_required(counselorDetailsBEAN.getStudyRequired());
        historyPOJO.setWork_required(counselorDetailsBEAN.getWorkRequired());
        historyPOJO.setMigration_required(counselorDetailsBEAN.getMigrationRequired());
        historyPOJO.setTraining_required(counselorDetailsBEAN.getTrainingRequired());
        System.out.println("Historyyyy :::::::::: " + historyPOJO.toString());
        forwardHistoryDAO.forwardEnquiry(historyPOJO);
        /* ====================== Update Enquiry Status Table ====================== */
        int done = inlineEditingDAO.updateAppointmentStatus(counselorDetailsBEAN);
        if (done > 0) {
            Notification notification = Notifications.SUCCESS;
            TrayNotification tray = new TrayNotification("Enquiry Status Changed!", "Enquiry status has been updated successfully!", notification);
            tray.setAnimation(Animations.POPUP);
            tray.showAndDismiss(Duration.seconds(3), Context.getInstance().currentProfile().getPopOver().getOwnerNode());
            tray.setOnDismiss(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Context.getInstance().currentProfile().getPopOver().hide();
                }
            });
        } else {
            Notification notification = Notifications.ERROR;
            TrayNotification tray = new TrayNotification("Enquiry Status Change failed!", "Enquiry status change unsuccessfull! Try again!", notification);
            tray.setAnimation(Animations.POPUP);
            tray.showAndDismiss(Duration.seconds(3), Context.getInstance().currentProfile().getPopOver().getOwnerNode());
            tray.setOnDismiss(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Context.getInstance().currentProfile().getPopOver().hide();
                }
            });
        }

        /* ====================== send an email about the status change  ====================== */
        if (cmbEnquiryStatus.getSelectionModel().getSelectedIndex() > -1) {
            System.out.println("Test Email Content :: " + cmbEnquiryStatus.getSelectionModel().getSelectedItem().getEmailBody());
            System.out.println("Test Email Content :: " + cmbEnquiryStatus.getSelectionModel().getSelectedItem().getSubject());
            /* ====================== Send Email  ====================== */
            if (cmbEnquiryStatus.getSelectionModel().getSelectedItem().getEmailBody() != null) {
                if (!cmbEnquiryStatus.getSelectionModel().getSelectedItem().getEmailBody().equalsIgnoreCase("")) {
                    Task task = new Task() {

                        @Override
                        protected Object call() {

                            try {
                                /* ====================== Load Auto Completion Data In New Thread ====================== */
                                MailSentPOJO mailSentPOJO = new MailSentPOJO();
                                mailSentPOJO.setTo(counselorDetailsBEAN.getContactEmail());
                                mailSentPOJO.setFrom("ia.offer@gmail.com");
                                if (cmbEnquiryStatus.getSelectionModel().getSelectedItem().getSubject() == null) {
                                    mailSentPOJO.setSubject("International Academy:Info");
                                } else {
                                    mailSentPOJO.setSubject(cmbEnquiryStatus.getSelectionModel().getSelectedItem().getSubject());
                                }
                                /* ====================== Compose Email ====================== */
                                String message = cmbEnquiryStatus.getSelectionModel().getSelectedItem().getEmailBody();
                                message = message.replace("[name]", counselorDetailsBEAN.getContactName());
                                message = message.replace("[email]", counselorDetailsBEAN.getContactEmail());
                                message = message.replace("[phone_no]", counselorDetailsBEAN.getContactNumber1());
                                message = message.replace("[program_required]", "Study : "
                                        + counselorDetailsBEAN.getStudyRequired() + "\n Work : " + counselorDetailsBEAN.getWorkRequired()
                                        + "\n Migration : " + counselorDetailsBEAN.getMigrationRequired()
                                        + "\n Training : " + counselorDetailsBEAN.getTrainingRequired());
                                mailSentPOJO.setContent(message);
                                if (mailSentPOJO.getTo() != null) {
                                    E_MailSender.sendMail(mailSentPOJO);
                                }

                            } catch (Exception ex) {
                                logger.error(ex.toString());
                            }
                            return true;
                        }
                    };
                    new Thread(task).start();
                }
                /* ====================== End Send Email  ====================== */
            }
        }

    }

    /**
     *
     * @return
     */
    public boolean validateStatusChange() {
        boolean flag = true;
        if (counselorDetailsBEAN.getAppointmentDate() == null) {
            showErrors.showError("Please choose follow up date", datePickAppDate);
            flag = false;
        } else if (counselorDetailsBEAN.getAppointmentDate().equalsIgnoreCase("")) {
            showErrors.showError("Please choose follow up date", datePickAppDate);
            flag = false;
        } else if (counselorDetailsBEAN.getAppointmentTime() == null) {
            showErrors.showError("Please choose follow up time", localTimePicker);
            flag = false;
        } else if (counselorDetailsBEAN.getAppointmentTime().equalsIgnoreCase("")) {
            showErrors.showError("Please choose follow up time", localTimePicker);
            flag = false;
        } else if (counselorDetailsBEAN.getRemarks() == null) {
            showErrors.showError("Please enter reason", txtReason);
            flag = false;
        } else if (counselorDetailsBEAN.getRemarks().equalsIgnoreCase("")) {
            showErrors.showError("Please enter reason", txtReason);
            flag = false;
        }
        return flag;
    }
}

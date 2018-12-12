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
package com.zs.ina.registration.documents;

import com.zs.ina.admin.master.documents.dao.DocumentIMPL;
import com.zs.ina.admin.master.documents.dao.DocumentSERVICE;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.email.E_MailSender;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.common.email.dao.MailSentDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.registration.documents.dao.DocumentPhotoBEAN;
import com.zs.ina.registration.documents.dao.DocumentVerifyBEAN;
import com.zs.ina.registration.documents.dao.DocumentVerifyDAO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.springframework.context.ApplicationContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLDocumentsVerifyController implements Initializable {

    CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
    MailSentPOJO mailSentPOJO = new MailSentPOJO();
    MailSentPOJO mailSentPOJOnew = new MailSentPOJO();
    List<MailSentPOJO> mailSentPOJOs = new ArrayList<>();
    List<DocumentVerifyBEAN> documentVerifyBEANs = FXCollections.observableArrayList();
    DocumentPhotoBEAN documentPhotoBEAN = new DocumentPhotoBEAN();
    ObservableList<String> programList = FXCollections.observableArrayList();
    private final ObservableList statuses = FXCollections.observableArrayList("Valid", "Invalid", "Defected");
    DocumentSERVICE documentSERVICE = new DocumentSERVICE(new DocumentIMPL());
    Logger logger = Logger.getLogger(FXMLDocumentsVerifyController.class);
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    @FXML
    private ComboBox<String> cmbProgramReqd;
    @FXML
    private GridPane gridDocumentsForRegn;
    @FXML
    private Button btnDocSave;
    @FXML
    private Button btnNofifyUser;
    @FXML
    private Button btnNotificationLog;
    @FXML
    private Button btnProceed;
    @FXML
    private Label lblNotificationsCount;
    @FXML
    private Button btnCountNotifications;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    DocumentVerifyDAO documentVerifyDAO = springAppContext.getBean(DocumentVerifyDAO.class);
    MailSentDAO mailSentDAO = springAppContext.getBean(MailSentDAO.class);

    /**
     *
     */
    public int validateNo = 0;
    String CURRENT_TAB = null;
    long PHOTO_MAX_SIZE = 1024 * 1024;
    String program = "";
    String ENQUIRY_ID = "";
    String message = "";
    int photoFlag = 0;
    int notificationsCount = 0;
    String signature = "";
    PopOver popOver = new PopOver();
    Label lblNotificationLastSentDates;
    ComboBox cmbStatusPhoto;

    StringBuilder sbValid = new StringBuilder();
    StringBuilder sbInvalid = new StringBuilder();
    StringBuilder sbDefected = new StringBuilder();
    List<MailSentPOJO> validDocs = new ArrayList<>();
    List<MailSentPOJO> invalidDocs = new ArrayList<>();
    List<MailSentPOJO> defectedDocs = new ArrayList<>();
    List<MailSentPOJO> logList = new ArrayList<>();
    int countValidDocs = 0;
    int countInvalidDocs = 0;
    int countDefectedDocs = 0;
    int query_insert = 0;
    int query_update = 0;
    int query_insert_photo = 0;
    int query_update_photo = 0;

    String lastSentDate;
    Button btnPhotoUpload;
    ImageView passportSizePhotoView;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CURRENT_TAB = Context.getInstance().currentProfile().getCurrentTab();

    }

    /**
     *
     * @param validateDocumentVerifyBEAN
     * @return
     */
    public boolean validateDocumentVerifications(DocumentVerifyBEAN validateDocumentVerifyBEAN) {
        boolean flag = true;

        if (validateDocumentVerifyBEAN.getStatus().equalsIgnoreCase("Valid")) {
            flag = true;
        } else if (validateDocumentVerifyBEAN.getStatus().equalsIgnoreCase("Invalid") && validateDocumentVerifyBEAN.getFollowupDate() == null || validateDocumentVerifyBEAN.getRemarks() == null) {
            validateNo++;
            flag = false;
        } else if (validateDocumentVerifyBEAN.getStatus().equalsIgnoreCase("Defected") && validateDocumentVerifyBEAN.getFollowupDate() == null || validateDocumentVerifyBEAN.getRemarks() == null) {
            validateNo++;
            flag = false;
        }

        return flag;
    }

    /**
     *
     * @param counselorDetailsBEAN
     */
    public void initData(CounselorDetailsBEAN counselorDetailsBEAN) {
        programList.addAll("Study", "Work", "Migrate", "Training");
        cmbProgramReqd.setItems(programList);
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        cmbProgramReqd.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    try {
                        cmbProgramReqd.setDisable(true);
                        program = newValue;
                        initDynamicDocumentsForRegn();
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(FXMLDocumentsVerifyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        Context.getInstance().currentProfile().documentVerifyDisplayProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    switch (newValue) {
                        case "study":
                            cmbProgramReqd.getSelectionModel().select(0);
                            break;
                        case "work":
                            cmbProgramReqd.getSelectionModel().select(1);
                            break;
                        case "migration":
                            cmbProgramReqd.getSelectionModel().select(2);
                            break;
                        case "training":
                            cmbProgramReqd.getSelectionModel().select(3);
                            break;
                        default:
                            System.out.println("not workingggf");

                    }
                }
            }
        });

        switch (Context.getInstance().currentProfile().getCurrentTab()) {
            case "study":
                cmbProgramReqd.getSelectionModel().select(0);
                break;
            case "work":
                cmbProgramReqd.getSelectionModel().select(1);
                break;
            case "migration":
                cmbProgramReqd.getSelectionModel().select(2);
                break;
            case "training":
                cmbProgramReqd.getSelectionModel().select(3);
                break;
            default:
                System.out.println("not workingggf");

        }
        btnDocSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validateNo = 0;
                for (DocumentVerifyBEAN documentVerifyBEAN : documentVerifyBEANs) {
                    if (documentVerifyBEAN.getStatus() != null) {
                        if (validateDocumentVerifications(documentVerifyBEAN)) {
                            if (documentVerifyBEAN.getDocVerifyId() != null) {
                                query_update = documentVerifyDAO.updateDocumentVerfications(documentVerifyBEAN);
                                popupMessages.showSuccess("Updated Successfully !", "Document Updated Successfully !", cmbProgramReqd);

                            } else {
                                if (documentVerifyBEAN.getStatus().equalsIgnoreCase("Valid")) {
                                    documentVerifyBEAN.setFollowupDate(LocalDate.now());
                                }
                                System.out.println("INSERT=======DOC");
                                query_insert = documentVerifyDAO.insertDocumentVerfications(documentVerifyBEAN);
                            }
                        }
                    }
                }
                if (validateNo > 0) {
                    popupMessages.showError("All Fields of the selected document required ! Try again !", cmbProgramReqd);
                } else {
                    popupMessages.showSuccess("Saved Successfully !", "Document Saved Successfully !", cmbProgramReqd);
                }
                if (documentPhotoBEAN.getPhotoId() != null && photoFlag > 0) {
                    query_update_photo = documentVerifyDAO.updatePhotoDocumentVerfication(documentPhotoBEAN);
                } else if (photoFlag > 0) {
                    query_insert_photo = documentVerifyDAO.insertPhotoDocumentVerfication(documentPhotoBEAN);
                }

            }

        }
        );
        btnNofifyUser.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {

                documentVerifyBEANs = documentVerifyDAO.retrieveDocumentVerfications(program, ENQUIRY_ID);

                for (DocumentVerifyBEAN documentVerifyBEANvalid : documentVerifyBEANs) {
                    if (documentVerifyBEANvalid.getStatus() != null) {
                        if (documentVerifyBEANvalid.getStatus().equalsIgnoreCase("Valid")) {
                            countValidDocs++;
                        } else if (documentVerifyBEANvalid.getStatus().equalsIgnoreCase("Invalid")) {
                            countInvalidDocs++;
                        } else if (documentVerifyBEANvalid.getStatus().equalsIgnoreCase("Defected")) {
                            countDefectedDocs++;
                        }
                    }
                }

                if (countInvalidDocs > 0) {
                    sbInvalid.append("<html>");
                    sbInvalid.append("<head>");
                    sbInvalid.append("</head>");
                    sbInvalid.append("<table>");
                    sbInvalid.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Document");
                    sbInvalid.append("</th>");
                    sbInvalid.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Submission Date");
                    sbInvalid.append("</th>");
                    sbInvalid.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Remarks");
                    sbInvalid.append("</th>");
                    for (DocumentVerifyBEAN documentVerifyBEANinvalid : documentVerifyBEANs) {
                        if (documentVerifyBEANinvalid.getStatus() != null) {
                            if (documentVerifyBEANinvalid.getStatus().equalsIgnoreCase("Invalid")) {
                                sbInvalid.append("<tr>");
                                sbInvalid.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + documentVerifyBEANinvalid.getDocumentName());
                                sbInvalid.append("</td>");
                                sbInvalid.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + documentVerifyBEANinvalid.getFollowupDate());
                                sbInvalid.append("</td>");
                                sbInvalid.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + documentVerifyBEANinvalid.getRemarks());
                                sbInvalid.append("</td>");
                                sbInvalid.append("</tr>");

                            }
                        }
                    }
                    sbInvalid.append("</table>");
                    sbInvalid.append("</body>");
                    sbInvalid.append("</html>");
                }

                if (countDefectedDocs > 0) {
                    sbDefected.append("<html>");
                    sbDefected.append("<head>");
                    sbDefected.append("</head>");
                    sbDefected.append("<table>");
                    sbDefected.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Document");
                    sbDefected.append("</th>");
                    sbDefected.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Submission Date");
                    sbDefected.append("</th>");
                    sbDefected.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Remarks");
                    sbDefected.append("</th>");
                    for (DocumentVerifyBEAN documentVerifyBEANdefected : documentVerifyBEANs) {
                        if (documentVerifyBEANdefected.getStatus() != null) {
                            if (documentVerifyBEANdefected.getStatus().equalsIgnoreCase("Defected")) {
                                sbDefected.append("<tr>");
                                sbDefected.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + documentVerifyBEANdefected.getDocumentName());
                                sbDefected.append("</td>");
                                sbDefected.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + documentVerifyBEANdefected.getFollowupDate());
                                sbDefected.append("</td>");
                                sbDefected.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + documentVerifyBEANdefected.getRemarks());
                                sbDefected.append("</td>");
                                sbDefected.append("</tr>");
                            }
                        }
                    }
                    sbDefected.append("</table>");
                    sbDefected.append("</body>");
                    sbDefected.append("</html>");
                }
                mailSentPOJO.setEnquiryId(ENQUIRY_ID);
                mailSentPOJO.setEmailHead("DOCUMENT");
                mailSentPOJO.setTo(counselorDetailsBEAN.getContactEmail());
                String photoStatus = cmbStatusPhoto.getSelectionModel().getSelectedItem().toString();
                mailSentPOJOs = mailSentDAO.getEmailTemplateDetails(mailSentPOJO.getEmailHead());
                for (MailSentPOJO mspojo : mailSentPOJOs) {
                    if (message.equalsIgnoreCase("")) {
                        message = message + mspojo.getSalutation();
                        message = message.replace("[USER]", counselorDetailsBEAN.getContactName());
                    }
                    mailSentPOJO.setFrom(mspojo.getFrom());
                    mailSentPOJO.setSubject(mspojo.getSubject());
                    if (mspojo.getEmailsubhead().equalsIgnoreCase("DOCUMENT_INVALID") && countInvalidDocs > 0) {
                        message = message + mspojo.getContent();
                        message = message.replace("[DOCUMENT_INVALID]", sbInvalid);
                    }
                    if (mspojo.getEmailsubhead().equalsIgnoreCase("DOCUMENT_DEFECTED") && countDefectedDocs > 0) {
                        message = message + mspojo.getContent();
                        message = message.replace("[DOCUMENT_DEFECTED]", sbDefected);
                    }
                    if (mspojo.getEmailsubhead().equalsIgnoreCase("DOCUMENT_PHOTO") && (photoStatus.equalsIgnoreCase("Invalid") || photoStatus.equalsIgnoreCase("Defected"))) {
                        message = message + mspojo.getContent();
                        message = message.replace("[VALID_INVALID_OR_DEFECTED]", photoStatus);
                    }
                    signature = mspojo.getSignature();
                }

                mailSentPOJO.setMessage(message + signature);

                int result_notification_insert = mailSentDAO.logNotification(mailSentPOJO);

                if (result_notification_insert > 0) {
                    Task task = new Task() {
                        @Override
                        protected Object call() throws Exception {
                            E_MailSender.sendMail(mailSentPOJO);
                            return null;
                        }
                    };
                    new Thread(task).start();
                    popupMessages.showSuccess("Sent Notification !", "Sent Notification Successfully !", cmbProgramReqd);
                    displayCountInvitations();
                    displayNotificationLastDate();
                }

            }
        }
        );
        

        btnNotificationLog.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {
                try {

                    if (popOver.isShowing() || popOver.isDetached()) {
                        popOver.hide();
                    }
                    popOver = new PopOver();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/registration/documents/FXMLLogDocumentVerification.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLLogDocumentVerificationController controller = (FXMLLogDocumentVerificationController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(true);
                    popOver.setTitle("Document Verification Log");
                   // popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_RIGHT);
                    popOver.show(cmbProgramReqd.getParent().getScene().getWindow(), cmbProgramReqd.getParent().getLayoutX(), cmbProgramReqd.getParent().getLayoutY() + 77);
                    controller.initData(popOver, counselorDetailsBEAN);
                    popOver.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {

                        }
                    });
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        }
        );
    }

    /**
     *
     * @throws IOException
     */
    public void initDynamicDocumentsForRegn() throws IOException {

        documentVerifyBEANs = documentVerifyDAO.retrieveDocumentVerfications(program, ENQUIRY_ID);
        documentPhotoBEAN = documentVerifyDAO.retrievePhotoDocumentVerfications(ENQUIRY_ID);

        displayCountInvitations();

        if (documentVerifyBEANs.size() > 0) {
            addDocumentsForRegnGridRows();
        }

    }

    /**
     *
     * @throws IOException
     */
    public void addDocumentsForRegnGridRows() throws IOException {
        gridDocumentsForRegn.getChildren().clear();
        for (DocumentVerifyBEAN documentVerifyBEAN : documentVerifyBEANs) {
            documentVerifyBEAN.setEnquiryId(ENQUIRY_ID);

            Label lblDocument = new Label(documentVerifyBEAN.getDocumentName());
            lblDocument.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(lblDocument, Priority.ALWAYS);

            ComboBox cmbStatus = new ComboBox(statuses);
            ComboBoxJumpToChar.jumpToChar(cmbStatus);
            cmbStatus.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbStatus, Priority.ALWAYS);

            DatePicker dpFollowUp = new DatePicker();
            dpFollowUp.setMaxWidth(Double.MAX_VALUE);
            ChangeDateFormat.datePickerDateFormatter(dpFollowUp);
            GridPane.setHgrow(dpFollowUp, Priority.ALWAYS);

            TextField txtRemarks = new TextField();
            txtRemarks.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(txtRemarks, Priority.ALWAYS);

            cmbStatus.setPromptText("Select Status");
            txtRemarks.setPromptText("Remarks");
            dpFollowUp.setDisable(true);
            txtRemarks.setDisable(true);

            if (documentVerifyBEANs.indexOf(documentVerifyBEAN) == 0) {
                Label lblPhoto = new Label("Passport Size Photograph");
                lblPhoto.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(lblPhoto, Priority.ALWAYS);
                cmbStatusPhoto = new ComboBox(statuses);
                ComboBoxJumpToChar.jumpToChar(cmbStatusPhoto);
                cmbStatusPhoto.setMaxWidth(Double.MAX_VALUE);

                GridPane.setHgrow(cmbStatusPhoto, Priority.ALWAYS);
                btnPhotoUpload = new Button();
                btnPhotoUpload.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(btnPhotoUpload, Priority.ALWAYS);

                btnPhotoUpload.setText("Upload");
                btnPhotoUpload.setDisable(true);

                Label lblNotificationLastSentDate = new Label("Notification Last Sent Date");
                lblNotificationLastSentDate.setAlignment(Pos.CENTER);
                lblNotificationLastSentDate.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(lblNotificationLastSentDate, Priority.ALWAYS);

                gridDocumentsForRegn.add(lblPhoto, 0, 0);
                gridDocumentsForRegn.add(cmbStatusPhoto, 1, 0);
                gridDocumentsForRegn.add(btnPhotoUpload, 2, 0);
                gridDocumentsForRegn.add(lblNotificationLastSentDate, 5, 0);
                displayNotificationLastDate();

                Label lblDocStatus = new Label("Status");
                lblDocStatus.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(lblDocStatus, Priority.ALWAYS);
                Label lblSubmissionDate = new Label("Submission Date");
                lblSubmissionDate.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(lblSubmissionDate, Priority.ALWAYS);
                Label lblRemarks = new Label("Remarks");
                lblRemarks.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(lblRemarks, Priority.ALWAYS);
                gridDocumentsForRegn.add(lblDocStatus, 1, 1);
                gridDocumentsForRegn.add(lblSubmissionDate, 2, 1);
                gridDocumentsForRegn.add(lblRemarks, 3, 1);
                passportSizePhotoView = new ImageView();
                int size = documentVerifyBEANs.size();
                gridDocumentsForRegn.add(passportSizePhotoView, 4, 0, 1, size);
                cmbStatusPhoto.setPromptText("Select Status");
                Bindings.bindBidirectional(cmbStatusPhoto.valueProperty(), documentPhotoBEAN.photoStatusProperty());
                if (documentPhotoBEAN.getPhotoId() != null) {

                    if (documentPhotoBEAN.getInputStream() != null && documentPhotoBEAN.getPhotoStatus() != null) {
                        if (documentPhotoBEAN.getPhotoStatus().equalsIgnoreCase("Valid")) {
                            cmbStatusPhoto.getSelectionModel().select(0);
                            btnPhotoUpload.setDisable(false);
                            BufferedImage bufferedImg = ImageIO.read(documentPhotoBEAN.getInputStream());
                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    if (bufferedImg != null) {
                                        Image imagee = SwingFXUtils.toFXImage(bufferedImg, null);
                                        passportSizePhotoView.setImage(imagee);
                                    }

                                }
                            });

                            passportSizePhotoView.setFitWidth(120);
                            passportSizePhotoView.setFitHeight(180);
                            passportSizePhotoView.setPreserveRatio(true);
                            passportSizePhotoView.setSmooth(true);
                            passportSizePhotoView.setCache(true);
                        }
                    }
                }

                cmbStatusPhoto.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (newValue != null) {

                            if (newValue.equalsIgnoreCase("Valid")) {
                                btnPhotoUpload.setDisable(false);
                            } else if (newValue.equalsIgnoreCase("Invalid")) {
                                btnPhotoUpload.setDisable(true);
                            } else if (newValue.equalsIgnoreCase("Defected")) {
                                btnPhotoUpload.setDisable(true);
                            }
                            documentPhotoBEAN.setPhotoStatus(newValue);
                        }

                    }
                });

                btnPhotoUpload.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Open Resource File");
                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                        Stage stage = (Stage) btnPhotoUpload.getScene().getWindow();
                        File selectedFile = fileChooser.showOpenDialog(btnPhotoUpload.getScene().getWindow());
                        if (selectedFile != null) {
                            try {
                                documentPhotoBEAN.setEnquiryId(documentVerifyBEAN.getEnquiryId());
                                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                                System.out.println("selectedFile   ===========" + selectedFile.length());
                                if (selectedFile.length() > PHOTO_MAX_SIZE) {
                                    popupMessages.showError("Passport Size photo with size less than 1 MB is only acceptable ! Try again !", btnDocSave);
                                } else {
                                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                                    passportSizePhotoView.setImage(image);
                                    passportSizePhotoView.setFitWidth(120);
                                    passportSizePhotoView.setFitHeight(180);
                                    passportSizePhotoView.setPreserveRatio(true);
                                    passportSizePhotoView.setSmooth(true);
                                    passportSizePhotoView.setCache(true);

                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    ImageIO.write(bufferedImage, "png", baos);
                                    InputStream is = new ByteArrayInputStream(baos.toByteArray());
                                    baos.flush();
                                    photoFlag++;
                                    documentPhotoBEAN.setInputStream(is);
                                    baos.close();
                                }

                            } catch (IOException ex) {
                                java.util.logging.Logger.getLogger(FXMLDocumentsVerifyController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                });

            }
            cmbStatus.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (newValue.equalsIgnoreCase("Valid")) {
                            dpFollowUp.setValue(null);
                            dpFollowUp.setDisable(true);
                            txtRemarks.setText("");
                            txtRemarks.setDisable(true);
                            documentVerifyBEAN.setStatus("Valid");
                        } else if (newValue.equalsIgnoreCase("Invalid")) {
                            dpFollowUp.setDisable(false);
                            txtRemarks.setDisable(false);
                            documentVerifyBEAN.setStatus("Invalid");
                        } else if (newValue.equalsIgnoreCase("Defected")) {
                            dpFollowUp.setDisable(false);
                            txtRemarks.setDisable(false);
                            documentVerifyBEAN.setStatus("Defected");
                        }
                    }

                }
            });
            dpFollowUp.valueProperty().addListener(new ChangeListener<LocalDate>() {

                @Override
                public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                    if (newValue != null) {
                        documentVerifyBEAN.setFollowupDate(newValue);
                    }
                }
            });

            final Callback<DatePicker, DateCell> dayCellFactory
                    = new Callback<DatePicker, DateCell>() {
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
            dpFollowUp.setDayCellFactory(dayCellFactory);
            gridDocumentsForRegn.add(lblDocument, 0, documentVerifyBEANs.indexOf(documentVerifyBEAN) + 2);
            gridDocumentsForRegn.add(cmbStatus, 1, documentVerifyBEANs.indexOf(documentVerifyBEAN) + 2);
            gridDocumentsForRegn.add(dpFollowUp, 2, documentVerifyBEANs.indexOf(documentVerifyBEAN) + 2);
            gridDocumentsForRegn.add(txtRemarks, 3, documentVerifyBEANs.indexOf(documentVerifyBEAN) + 2);

            Bindings.bindBidirectional(lblDocument.textProperty(), documentVerifyBEAN.documentNameProperty());
            Bindings.bindBidirectional(cmbStatus.valueProperty(), documentVerifyBEAN.statusProperty());
            Bindings.bindBidirectional(dpFollowUp.valueProperty(), documentVerifyBEAN.followupDateProperty());
            Bindings.bindBidirectional(txtRemarks.textProperty(), documentVerifyBEAN.remarksProperty());

        }

    }

    /**
     *
     */
    public void displayCountInvitations() {
        logList = documentVerifyDAO.retrieveLogDetails(ENQUIRY_ID);
        System.out.println("SIZE OF THE LIST logList ===="+logList.size());
        for (MailSentPOJO mailSentPOJO : logList) {
            if (mailSentPOJO.getSendDate()!= null) {
                lastSentDate = mailSentPOJO.getSendDate();
                break;
            }
        }
        btnCountNotifications.setText(logList.size()+"");

    }

    /**
     *
     */
    public void displayNotificationLastDate() {
        gridDocumentsForRegn.getChildren().remove(lblNotificationLastSentDates);
        lblNotificationLastSentDates = new Label();
        lblNotificationLastSentDates.setAlignment(Pos.CENTER);
        lblNotificationLastSentDates.setText("");
        lblNotificationLastSentDates.setText(lastSentDate);
        lblNotificationLastSentDates.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(lblNotificationLastSentDates, Priority.ALWAYS);
        gridDocumentsForRegn.add(lblNotificationLastSentDates, 5, 1);
    }

}

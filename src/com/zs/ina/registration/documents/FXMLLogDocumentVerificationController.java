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
package com.zs.ina.registration.documents;

import com.zs.ina.common.email.E_MailSender;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.common.email.dao.MailSentDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.registration.documents.dao.DocumentVerifyDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLLogDocumentVerificationController implements Initializable {

    PopOver popOver = new PopOver();
    String CUR_USERNAME = "";
    String CUR_BRANCH = "";
    String ENQUIRY_ID = "";

    Logger logger = Logger.getLogger(FXMLLogDocumentVerificationController.class);
    List<MailSentPOJO> logDocumentsTableList = new ArrayList<>();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    DocumentVerifyDAO documentVerifyDAO = springAppContext.getBean(DocumentVerifyDAO.class);
    MailSentDAO mailSentDAO = springAppContext.getBean(MailSentDAO.class);
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    @FXML
    private Button btnClose;
    @FXML
    private TableView<MailSentPOJO> tblLogDocument;
    @FXML
    private TableColumn<MailSentPOJO, MailSentPOJO> colSubject;
    @FXML
    private TableColumn<MailSentPOJO, MailSentPOJO> colContent;
    @FXML
    private TableColumn<?, ?> colSendDate;
    @FXML
    private TableColumn<MailSentPOJO, MailSentPOJO> colEmail;
    @FXML
    private TableColumn<MailSentPOJO, MailSentPOJO> colResend;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();

        /* =================== Close button ================== */
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                popOver.hide();
            }
        });
    }

    /**
     *
     * @param popOver
     * @param counselorDetailsBEAN
     */
    public void initData(PopOver popOver, CounselorDetailsBEAN counselorDetailsBEAN) {
        this.popOver = popOver;
        popOver.setAutoHide(false);
        logDocumentsTableList.clear();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        logDocumentsTableList = documentVerifyDAO.retrieveLogDetails(ENQUIRY_ID);
        viewLogTable(logDocumentsTableList);
    }

    /**
     *
     * @param logDocumentsTableList
     */
    public void viewLogTable(List<MailSentPOJO> logDocumentsTableList) {
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colSendDate.setCellValueFactory(new PropertyValueFactory<>("sendDate"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("to"));

        colContent.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MailSentPOJO, MailSentPOJO>, ObservableValue<MailSentPOJO>>() {
            @Override
            public ObservableValue<MailSentPOJO> call(TableColumn.CellDataFeatures<MailSentPOJO, MailSentPOJO> param) {
                return new SimpleObjectProperty(param.getValue());
            }
        });
        colContent.setCellFactory(new Callback<TableColumn<MailSentPOJO, MailSentPOJO>, TableCell<MailSentPOJO, MailSentPOJO>>() {

            @Override
            public TableCell<MailSentPOJO, MailSentPOJO> call(TableColumn<MailSentPOJO, MailSentPOJO> param) {

                TableCell<MailSentPOJO, MailSentPOJO> cell = new TableCell<MailSentPOJO, MailSentPOJO>() {
                    Button btnViewContent = new Button("View Mail Content");
                    VBox vboxMailContent = new VBox();
                    HBox hboxbtn = new HBox();
                    HBox hboxemail = new HBox();
                    WebView browser = new WebView();
                    WebEngine webEngine = browser.getEngine();

                    @Override
                    public void updateItem(MailSentPOJO mailSentPOJO, boolean empty) {
                        super.updateItem(mailSentPOJO, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            hboxbtn.getChildren().add(btnViewContent);
                            hboxemail.getChildren().add(browser);
                            browser.setPrefHeight(0);
                            vboxMailContent.getChildren().add(hboxbtn);
                            vboxMailContent.getChildren().add(hboxemail);
                            setGraphic(vboxMailContent);
                            btnViewContent.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    if (btnViewContent.getText().equalsIgnoreCase("Hide Mail Content")) {
                                        browser.setPrefHeight(0);
                                        webEngine.loadContent(mailSentPOJO.getMessage());
                                        setGraphic(vboxMailContent);
                                        btnViewContent.setText("View Mail Content");
                                    } else {

                                        browser.setPrefHeight(200);
                                        webEngine.loadContent(mailSentPOJO.getMessage());
                                        setGraphic(vboxMailContent);
                                        btnViewContent.setText("Hide Mail Content");
                                    }

                                }
                            });

                        }
                    }
                };
                return cell;
            }
        });

        colResend.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MailSentPOJO, MailSentPOJO>, ObservableValue<MailSentPOJO>>() {
            @Override
            public ObservableValue<MailSentPOJO> call(TableColumn.CellDataFeatures<MailSentPOJO, MailSentPOJO> param) {
                return new SimpleObjectProperty(param.getValue());
            }
        });
        colResend.setCellFactory(new Callback<TableColumn<MailSentPOJO, MailSentPOJO>, TableCell<MailSentPOJO, MailSentPOJO>>() {
            @Override
            public TableCell<MailSentPOJO, MailSentPOJO> call(TableColumn<MailSentPOJO, MailSentPOJO> param) {

                TableCell<MailSentPOJO, MailSentPOJO> cell = new TableCell<MailSentPOJO, MailSentPOJO>() {
                    Button btnResend = new Button("Resend");

                    @Override
                    public void updateItem(MailSentPOJO mailSentPOJO, boolean empty) {
                        super.updateItem(mailSentPOJO, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnResend);
                            btnResend.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
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
                                        popupMessages.showSuccess("Sent Notification !", "Sent Notification Successfully !", btnClose);
                                    }
                                }
                            });

                        }
                    }
                };
                return cell;
            }
        }
        );
        tblLogDocument.getStyleClass().add("-fx-alignment: CENTER-RIGHT");
        tblLogDocument.getItems().addAll(logDocumentsTableList);
    }
}

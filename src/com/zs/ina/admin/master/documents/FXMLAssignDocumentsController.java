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
package com.zs.ina.admin.master.documents;

import com.zs.ina.admin.master.documents.dao.DocumentBEAN;
import com.zs.ina.admin.master.documents.dao.DocumentIMPL;
import com.zs.ina.admin.master.documents.dao.DocumentSERVICE;
import com.zs.ina.admin.master.payment.FXMLHeadsAssignController;
import com.zs.ina.admin.master.payment.FXMLSingleHeadAssignController;
import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import com.zs.ina.admin.master.payment.dao.PaymentIMPL;
import com.zs.ina.admin.master.payment.dao.PaymentSERVICE;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLAssignDocumentsController implements Initializable {

    @FXML
    private ComboBox<String> cmbProgramReqd;
    @FXML
    private ListView<DocumentBEAN> listViewDocuments;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClearAll;

    DocumentBEAN documentBEAN = new DocumentBEAN();
    DocumentSERVICE documentSERVICE = new DocumentSERVICE(new DocumentIMPL());
    Logger logger = Logger.getLogger(FXMLAssignDocumentsController.class);
    ObservableList<String> programList = FXCollections.observableArrayList();
    ObservableList<DocumentBEAN> masterDocumentsList = FXCollections.observableArrayList();
    ObservableList<DocumentBEAN> documentsAssignedList = FXCollections.observableArrayList();
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearAllFields();
        btnClearAll.disableProperty().bind(Bindings.equal(cmbProgramReqd.getSelectionModel().selectedIndexProperty(), -1));

        cmbProgramReqd.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    masterDocumentsList.clear();
                    documentsAssignedList.clear();
                    documentBEAN.setProgram(newValue);
                    documentsAssignedList = documentSERVICE.retrieveAssignedDocuments(newValue);
                    addDocumentsIntoListView(documentsAssignedList);

                }

            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test All masterDocumentsList" + masterDocumentsList.toString());
                int result_insert;
                int result_delete;
//                if (validateDocumentsIdList()) {
                String programReqd = cmbProgramReqd.getSelectionModel().getSelectedItem();
                result_delete = documentSERVICE.deleteAssignedDocument(programReqd);
                result_insert = documentSERVICE.insertAssignedDocument(masterDocumentsList, programReqd);
                if (result_insert > 0) {
                    masterDocumentsList.clear();
                    documentsAssignedList.clear();
                    popupMessages.showSuccess("Document Assigned", "Document Assigned successfully", cmbProgramReqd);
                } else {
                    popupMessages.showError("Document Assigning Failed ! Try again !", cmbProgramReqd);
                }

                //  }
            }

        });
        btnClearAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();
            }
        });

    }

    /**
     *
     * @param documentsAssignedList
     */
    public void addDocumentsIntoListView(ObservableList<DocumentBEAN> documentsAssignedList) {
        listViewDocuments.getItems().clear();
        listViewDocuments.setCellFactory(new Callback<ListView<DocumentBEAN>, ListCell<DocumentBEAN>>() {

            @Override
            public ListCell<DocumentBEAN> call(ListView<DocumentBEAN> param) {

                final ListCell<DocumentBEAN> cell = new ListCell<DocumentBEAN>() {
                    @Override
                    protected void updateItem(DocumentBEAN documentBEAN, boolean empty) {
                        super.updateItem(documentBEAN, empty);
                        if (documentBEAN != null) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/documents/FXMLSingleDocument.fxml"));
                                Parent root = (Parent) loader.load();
                                FXMLSingleDocumentController controller = (FXMLSingleDocumentController) loader.getController();
                                controller.passDocumentDetails(documentBEAN, masterDocumentsList);
                                setGraphic(root);
                            } catch (IOException sqle) {
                                logger.error(sqle.toString());
                                sqle.printStackTrace();
                            }

                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }

        });
        listViewDocuments.getItems().addAll(documentsAssignedList);

    }

    /**
     *
     */
    public void clearAllFields() {
        programList.clear();
        cmbProgramReqd.getItems().clear();
        masterDocumentsList.clear();
        documentsAssignedList.clear();
        listViewDocuments.getItems().clear();
        cmbProgramReqd.setPromptText("Select");
        listViewDocuments.setPlaceholder(new Label("Select program first"));
        programList.addAll("Study", "Work", "Migrate", "Training");
        cmbProgramReqd.setItems(programList);
    }

}

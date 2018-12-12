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
package com.zs.ina.admin.master.emailtemplate.signature;

import com.zs.ina.admin.master.emailtemplate.dao.EmailTempBEAN;
import com.zs.ina.admin.master.emailtemplate.signature.dao.SignatureBEAN;
import com.zs.ina.admin.master.emailtemplate.signature.dao.SignatureDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.common.jsons.JsonParsing;
import com.zs.ina.context.Context;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSalutationSignController implements Initializable {

    @FXML
    private HTMLEditor htmlSignature;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private ComboBox<String> cmbEmailHead;
    @FXML
    private TableView<SignatureBEAN> tblSignature;
    @FXML
    private TableColumn<?, ?> colEmailHead;
    @FXML
    private TextField txtSearch;

    SignatureBEAN signatureBEAN = new SignatureBEAN();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    SignatureDAO signatureDAO = springAppContext.getBean(SignatureDAO.class);
    Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    List<SignatureBEAN> listEmailSignatureTable = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        map = JsonParsing.parseJsonToMap("src/email_heads.json");

        cmbEmailHead.getItems().addAll(map.keySet());
        bindAllData();
        viewEmailSignatureTable();
        htmlSignature.disableProperty().bind(Bindings.equal(cmbEmailHead.getSelectionModel().selectedIndexProperty(), -1));

        cmbEmailHead.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                     htmlSignature.setHtmlText("");
                    for (SignatureBEAN signatureBEANs : listEmailSignatureTable) {
                        if (signatureBEANs.getEmailHead().equalsIgnoreCase(newValue)) {
                            BeanUtils.copyProperties(signatureBEANs, signatureBEAN);
                            htmlSignature.setHtmlText(signatureBEAN.getSignature());
                            bindAllData();

                        }
                    }
                }

            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                signatureBEAN.setSignature(htmlSignature.getHtmlText());
                if (validateAllFields()) {

                    if (signatureBEAN.getSignatureId() != null) {
                        int result_update = signatureDAO.updateEmailSignature(signatureBEAN);
                        if (result_update > 0) {

                            clearAllFields();
                            viewEmailSignatureTable();
                            showPopupMessages.showSuccess("Email Signature Updated", "Email Signature Updated successfully", btnClear);
                        } else {
                            showPopupMessages.showError("Email Signature Updation Failed ! Try again !", btnClear);
                        }
                    } else {
                        int result_update = signatureDAO.insertEmailSignature(signatureBEAN);
                        if (result_update > 0) {

                            clearAllFields();
                            viewEmailSignatureTable();
                            showPopupMessages.showSuccess("Email Signature Saved", "Email Signature Saved Successfully", btnClear);
                        } else {
                            showPopupMessages.showError("Email Signature Saving Failed ! Try again !", btnClear);
                        }
                    }
                }
            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (signatureBEAN.getEmailHead() == null || signatureBEAN.getEmailHead().equals("")) {
                    showPopupMessages.showError("Select Email Head", cmbEmailHead);
                    flag = false;
                } else if (signatureBEAN.getSignature() == null || signatureBEAN.getSignature().equals("")) {
                    showPopupMessages.showError("Enter Mail Signature", htmlSignature);
                    flag = false;
                }
                return flag;
            }
        });
        tblSignature.setRowFactory(new Callback<TableView<SignatureBEAN>, TableRow<SignatureBEAN>>() {

            @Override
            public TableRow<SignatureBEAN> call(TableView<SignatureBEAN> param) {
                final TableRow<SignatureBEAN> row = new TableRow<SignatureBEAN>() {
                    @Override
                    protected void updateItem(SignatureBEAN signatureBEAN, boolean empty) {
                        super.updateItem(signatureBEAN, empty);
                        if (signatureBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                signatureBEAN = new SignatureBEAN();
                                signatureBEAN.signatureIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), signatureBEAN);
                                htmlSignature.setHtmlText(signatureBEAN.getSignature());
                                bindAllData();
                            }

                        }
                    }

                });
                return row;
            }

        });

        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearAllFields();
            }
        });
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        htmlSignature.setHtmlText("");

        tblSignature.getSelectionModel().clearSelection();
        cmbEmailHead.getSelectionModel().clearSelection();
        txtSearch.setText("");
        signatureBEAN = new SignatureBEAN();
        bindAllData();
        signatureBEAN.signatureIdProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }
        });
    }

    /**
     *
     */
    public void bindAllData() {
        Bindings.bindBidirectional(cmbEmailHead.valueProperty(), signatureBEAN.emailHeadProperty());
    }

    /**
     *
     */
    public void viewEmailSignatureTable() {

        listEmailSignatureTable.clear();
        listEmailSignatureTable = signatureDAO.retreiveEmailSignature();
        addSignaturesIntoTable(listEmailSignatureTable);
    }

    /**
     *
     * @param listEmailTemplatesTable
     */
    public void addSignaturesIntoTable(List<SignatureBEAN> listEmailTemplatesTable) {

        colEmailHead.setCellValueFactory(new PropertyValueFactory<>("emailHead"));

        ObservableList<SignatureBEAN> signatureBEANs = FXCollections.observableList(listEmailTemplatesTable);
        FilteredList<SignatureBEAN> filteredData = new FilteredList<SignatureBEAN>(signatureBEANs, head -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(head -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (head.getEmailHead().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<SignatureBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblSignature.comparatorProperty());
        tblSignature.setItems(sortedData);
    }
}

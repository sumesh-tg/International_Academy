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
import com.zs.ina.common.error.ShowPopupMessages;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLDocumentsController implements Initializable {

    @FXML
    private TableView<DocumentBEAN> tblDocuments;
    @FXML
    private TextField txtDocument;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;

    DocumentBEAN documentBEAN = new DocumentBEAN();
    DocumentSERVICE documentSERVICE = new DocumentSERVICE(new DocumentIMPL());
    Logger logger = Logger.getLogger(FXMLDocumentsController.class);
    ObservableList<DocumentBEAN> documentsTableList = FXCollections.observableArrayList();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    @FXML
    private TableColumn<?, ?> colDocument;
    @FXML
    private HBox hboxDocument;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        bindAllData();
        viewDocumentsTable();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateDocumentName()) {
                    if (documentBEAN.getDocumentId() != null) {
                        if (documentSERVICE.updateDocument(documentBEAN) > 0) {
                            clearAllFields();
                            viewDocumentsTable();
                            popupMessages.showSuccess("Updated Successfully !", "Document Updated Successfully !", txtDocument);

                        } else {
                            popupMessages.showError("Updation Failed ! Try again !", txtDocument);

                        }
                    } else {
                        int result_insert = documentSERVICE.insertDocument(documentBEAN);
                        if (result_insert > 0) {
                            clearAllFields();
                            viewDocumentsTable();
                            popupMessages.showSuccess("Saved Successfully !", "Document Saved Successfully !", txtDocument);

                        } else {
                            popupMessages.showError("Save Failed ! Try again !", txtDocument);

                        }

                    }
                }
            }
        });

        txtDocument.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,0-9,\\s]*"))) {
                        Bindings.unbindBidirectional(documentBEAN.documentProperty(), txtDocument.textProperty());
                        txtDocument.setText(oldValue);
                        Bindings.bindBidirectional(documentBEAN.documentProperty(), txtDocument.textProperty());
                    } else {
                        Bindings.unbindBidirectional(documentBEAN.documentProperty(), txtDocument.textProperty());
                        txtDocument.setText(newValue);
                        Bindings.bindBidirectional(documentBEAN.documentProperty(), txtDocument.textProperty());
                    }

                }
            }

        });
        tblDocuments.setRowFactory(new Callback<TableView<DocumentBEAN>, TableRow<DocumentBEAN>>() {

            @Override
            public TableRow<DocumentBEAN> call(TableView<DocumentBEAN> param) {
                final TableRow<DocumentBEAN> row = new TableRow<DocumentBEAN>() {
                    @Override
                    protected void updateItem(DocumentBEAN documentBEAN, boolean empty) {
                        super.updateItem(documentBEAN, empty);
                        if (documentBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                documentBEAN = new DocumentBEAN();
                                documentBEAN.documentIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), documentBEAN);
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
    public void bindAllData() {
        Bindings.bindBidirectional(txtDocument.textProperty(), documentBEAN.documentProperty());
    }

    /**
     *
     * @return
     */
    public boolean validateDocumentName() {
        boolean flag = true;
        if (documentBEAN.getDocument() == null || documentBEAN.getDocument().equalsIgnoreCase("")) {
            popupMessages.showError("Enter Document Name", txtDocument);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        documentBEAN.documentIdProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }
        });
        documentBEAN = new DocumentBEAN();
        bindAllData();

        txtSearch.setText("");

    }

    /**
     *
     */
    public void viewDocumentsTable() {
        // tblDocuments.getItems().clear();
        documentsTableList.clear();
        documentsTableList = documentSERVICE.retrieveDocuments();
        addDocumentsIntoTable(documentsTableList);
    }

    /**
     *
     * @param documentsTableList
     */
    public void addDocumentsIntoTable(ObservableList<DocumentBEAN> documentsTableList) {

        colDocument.setCellValueFactory(new PropertyValueFactory<>("document"));
        ObservableList<DocumentBEAN> documentBEANs = FXCollections.observableList(documentsTableList);
        FilteredList<DocumentBEAN> filteredData = new FilteredList<DocumentBEAN>(documentBEANs, documents -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(documents -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (documents.getDocument().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<DocumentBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblDocuments.comparatorProperty());
        tblDocuments.setItems(sortedData);
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allDocuments = DocumentIMPL.getAllDocuments();
        txtDocument = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtDocument, allDocuments);
        txtDocument.setPrefWidth(218);
        hboxDocument.getChildren().remove(1);
        hboxDocument.getChildren().add(txtDocument);
    }
}

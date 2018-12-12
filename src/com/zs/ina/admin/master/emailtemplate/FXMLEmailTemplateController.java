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
package com.zs.ina.admin.master.emailtemplate;

import com.zs.ina.admin.master.emailtemplate.dao.EmailTempBEAN;
import com.zs.ina.admin.master.emailtemplate.dao.EmailTempDAO;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;
import org.springframework.beans.BeanUtils;
//import org.apache.poi.ss.usermodel.Hyperlink;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEmailTemplateController implements Initializable {
    
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtFrom;
    @FXML
    private ComboBox<String> cmbHead;
    @FXML
    private ComboBox<String> cmbSubhead;
    @FXML
    private HTMLEditor htmlTemplate;
    @FXML
    private TableView<EmailTempBEAN> tblEmailTemplates;
    @FXML
    private TableColumn<?, ?> colHead;
    @FXML
    private TableColumn<?, ?> colSubhead;
    @FXML
    private TableColumn<?, ?> colSubject;
    @FXML
    private TableColumn<?, ?> colFrom;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private VBox vboxHyperEmailLinks;
    @FXML
    private GridPane gridEmailDetails;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtSalutation;
    
    EmailTempBEAN emailTempBEAN = new EmailTempBEAN();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    EmailTempDAO emailTempDAO = springAppContext.getBean(EmailTempDAO.class);
    Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    List<EmailTempBEAN> listEmailTemplatesTable = new ArrayList<>();
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        map = JsonParsing.parseJsonToMap("src/email_heads.json");
        txtSalutation.setEditable(false);
        cmbHead.getItems().addAll(map.keySet());
//        for (String key : map.keySet()) {
//            Map<String, List<String>> mapInner = map.get(key);
//         //   cmbHead.getItems().addAll(map.keySet());
//            for (String keyInner : mapInner.keySet()) {
//                List<String> listTag = mapInner.get(keyInner);
//                cmbSubhead.getItems().addAll(listTag);
//                for (String s : listTag) {
//                    System.out.println("Test Single Data :: " + s);
//                  //  cmbSubhead.getItems().add(s);
//                }
//                System.out.println("Test Inner Data :: " + mapInner.get(keyInner));
//            }
//        }
        bindAllData();
        viewEmailTemplateTable();
        cmbSubhead.disableProperty().bind(Bindings.equal(cmbHead.getSelectionModel().selectedIndexProperty(), -1));
        
        cmbHead.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    cmbSubhead.getItems().clear();
                    vboxHyperEmailLinks.getChildren().clear();
                    Map<String, List<String>> mapInner = map.get(newValue);
                    cmbSubhead.getItems().addAll(mapInner.keySet());
                    emailTempBEAN.setHead(newValue);
                }
                
            }
        });
        
        txtSubject.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(emailTempBEAN.subjectProperty(), txtSubject.textProperty());
                        txtSubject.setText(oldValue);
                        Bindings.bindBidirectional(emailTempBEAN.subjectProperty(), txtSubject.textProperty());
                    } else {
                        Bindings.unbindBidirectional(emailTempBEAN.subjectProperty(), txtSubject.textProperty());
                        txtSubject.setText(newValue);
                        Bindings.bindBidirectional(emailTempBEAN.subjectProperty(), txtSubject.textProperty());
                    }
                    
                }
            }
            
        });
        
        cmbSubhead.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    emailTempBEAN.setSubhead(newValue);
                    vboxHyperEmailLinks.getChildren().clear();
                    Map<String, List<String>> mapInner = map.get(cmbHead.getSelectionModel().getSelectedItem());
                    List<String> listEmailLinks = mapInner.get(newValue);
                    for (String link : listEmailLinks) {
                        Hyperlink hyperlink = new Hyperlink();
                        hyperlink.setText(link);
                        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                htmlTemplate.setHtmlText(htmlTemplate.getHtmlText() + hyperlink.getText());
                            }
                        });
                        vboxHyperEmailLinks.getChildren().add(hyperlink);
                    }
                    
                    for (EmailTempBEAN emailTempBEANs : listEmailTemplatesTable) {
                        if (emailTempBEANs.getSubhead().equalsIgnoreCase(newValue)) {
                            BeanUtils.copyProperties(emailTempBEANs, emailTempBEAN);
                            htmlTemplate.setHtmlText(emailTempBEAN.getContents());
                            bindAllData();
                            
                        }
                    }
                }
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                emailTempBEAN.setContents(htmlTemplate.getHtmlText());
                if (validateAllFields()) {
                    
                    if (emailTempBEAN.getTemplateId() != null) {
                        int result_update = emailTempDAO.updateEmailTemplate(emailTempBEAN);
                        if (result_update > 0) {
                            
                            clearAllFields();
                            viewEmailTemplateTable();
                            showPopupMessages.showSuccess("Email Template Updated", "Email Template Updated successfully", btnClear);
                        } else {
                            showPopupMessages.showError("Email Template Updation Failed ! Try again !", btnClear);
                        }
                    } else {
                        int result_update = emailTempDAO.insertEmailTemplate(emailTempBEAN);
                        if (result_update > 0) {
                            
                            clearAllFields();
                            viewEmailTemplateTable();
                            showPopupMessages.showSuccess("Email Template Saved", "Email Template Saved Successfully", btnClear);
                        } else {
                            showPopupMessages.showError("Email Template Saving Failed ! Try again !", btnClear);
                        }
                    }
                }
            }
            
            private boolean validateAllFields() {
                boolean flag = true;
                if (emailTempBEAN.getHead() == null || emailTempBEAN.getHead().equals("")) {
                    showPopupMessages.showError("Select Email Head", cmbHead);
                    flag = false;
                } else if (emailTempBEAN.getSubhead() == null || emailTempBEAN.getSubhead().equals("")) {
                    showPopupMessages.showError("Select Email Subhead", cmbSubhead);
                    flag = false;
                } else if (emailTempBEAN.getSubject() == null || emailTempBEAN.getSubject().equals("")) {
                    showPopupMessages.showError("Enter Email Subject", txtSubject);
                    flag = false;
                } else if (emailTempBEAN.getFromMail() == null || emailTempBEAN.getFromMail().equals("") || mailValidation() == false) {
                    showPopupMessages.showError("Enter Mail From", txtFrom);
                    flag = false;
                } else if (emailTempBEAN.getContents() == null || emailTempBEAN.getContents().equals("")) {
                    showPopupMessages.showError("Enter Mail Content", htmlTemplate);
                    flag = false;
                }
                
                return flag;
            }
            
            public Boolean mailValidation() {
                String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                String email1 = txtFrom.getText().trim();
                Boolean b = email1.matches(EMAIL_REGEX);
                return b;
            }
        });
        tblEmailTemplates.setRowFactory(new Callback<TableView<EmailTempBEAN>, TableRow<EmailTempBEAN>>() {
            
            @Override
            public TableRow<EmailTempBEAN> call(TableView<EmailTempBEAN> param) {
                final TableRow<EmailTempBEAN> row = new TableRow<EmailTempBEAN>() {
                    @Override
                    protected void updateItem(EmailTempBEAN emailTempBEAN, boolean empty) {
                        super.updateItem(emailTempBEAN, empty);
                        if (emailTempBEAN != null) {
                            
                        }
                    }
                    
                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                emailTempBEAN = new EmailTempBEAN();
                                emailTempBEAN.templateIdProperty().addListener(new ChangeListener<String>() {
                                    
                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });
                                
                                BeanUtils.copyProperties(row.getItem(), emailTempBEAN);
                                htmlTemplate.setHtmlText(emailTempBEAN.getContents());
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
        htmlTemplate.setHtmlText("");
        
        tblEmailTemplates.getSelectionModel().clearSelection();
        cmbHead.getSelectionModel().clearSelection();
        cmbSubhead.getSelectionModel().clearSelection();
        vboxHyperEmailLinks.getChildren().clear();
        txtSearch.setText("");
        emailTempBEAN = new EmailTempBEAN();
        bindAllData();
        emailTempBEAN.templateIdProperty().addListener(new ChangeListener<String>() {
            
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
        Bindings.bindBidirectional(cmbHead.valueProperty(), emailTempBEAN.headProperty());
        Bindings.bindBidirectional(cmbSubhead.valueProperty(), emailTempBEAN.subheadProperty());
        Bindings.bindBidirectional(txtSubject.textProperty(), emailTempBEAN.subjectProperty());
        Bindings.bindBidirectional(txtFrom.textProperty(), emailTempBEAN.fromMailProperty());
    //    Bindings.bindBidirectional(txtSalutation.textProperty(), emailTempBEAN.salutationProperty());
        emailTempBEAN.setSalutation(txtSalutation.getText());
        
    }
    
    /**
     *
     */
    public void viewEmailTemplateTable() {
        
        listEmailTemplatesTable.clear();
        listEmailTemplatesTable = emailTempDAO.retreiveEmailTemplates();
        addTemplatesIntoTable(listEmailTemplatesTable);
    }
    
    /**
     *
     * @param listEmailTemplatesTable
     */
    public void addTemplatesIntoTable(List<EmailTempBEAN> listEmailTemplatesTable) {
        
        colHead.setCellValueFactory(new PropertyValueFactory<>("head"));
        colSubhead.setCellValueFactory(new PropertyValueFactory<>("subhead"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("fromMail"));
        
        ObservableList<EmailTempBEAN> emailTempBEANs = FXCollections.observableList(listEmailTemplatesTable);
        FilteredList<EmailTempBEAN> filteredData = new FilteredList<EmailTempBEAN>(emailTempBEANs, templates -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(templates -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (templates.getHead().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (templates.getSubhead().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (templates.getSubject().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (templates.getFromMail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<EmailTempBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblEmailTemplates.comparatorProperty());
        tblEmailTemplates.setItems(sortedData);
        
    }
    
}

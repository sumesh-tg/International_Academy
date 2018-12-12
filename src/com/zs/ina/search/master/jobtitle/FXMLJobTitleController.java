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
package com.zs.ina.search.master.jobtitle;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.jobtitle.dao.JobTitleBEAN;
import com.zs.ina.search.master.jobtitle.dao.JobTitleDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
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
public class FXMLJobTitleController implements Initializable {

    @FXML
    private HBox timingHbox;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<JobTitleBEAN> tblJobTitle;
    @FXML
    private TableColumn<JobTitleBEAN, JobTitleBEAN> colJobTitle;
    @FXML
    private TableColumn<JobTitleBEAN, JobTitleBEAN> colId;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    JobTitleDAO jobTitleDAO = springAppContext.getBean(JobTitleDAO.class);
    @FXML
    private TextField txtJobTitle;
    JobTitleBEAN jobTitleBEAN = new JobTitleBEAN();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ValidationSupport validationSupport = new ValidationSupport();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableData();
        Bindings.bindBidirectional(txtJobTitle.textProperty(), jobTitleBEAN.jobTitleProperty());
        btnDelete.disableProperty().bind(Bindings.equal(tblJobTitle.getSelectionModel().selectedIndexProperty(), -1));

        /* ======================== Save Details ==================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateJobTitle()) {
                    if (jobTitleBEAN.getId() != null) {
                        int done = jobTitleDAO.updateJobTitle(jobTitleBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Job Title Updated!")
                                    .text("Job Title Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Job Title Updation Failed !")
                                    .showError();
                        }
                    } else {
                        int done = jobTitleDAO.insertJobTitle(jobTitleBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Job Title Saved!")
                                    .text("Job Title Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Job Title Saving Failed !")
                                    .showError();
                        }
                    }
                    txtJobTitle.clear();
                    jobTitleBEAN = new JobTitleBEAN();
                    loadTableData();
                    Bindings.bindBidirectional(txtJobTitle.textProperty(), jobTitleBEAN.jobTitleProperty());
                } else {
                    popupMessages.showError("All fields required!", btnSave);

                }
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (jobTitleBEAN.getId() != null) {
                    int done = jobTitleDAO.deleteJobTitle(jobTitleBEAN.getId());
                    if (done > 0) {
                        Notifications.create()
                                .title("Job Title Deleted!")
                                .text("Job Title Deleted Successfully!")
                                //    .owner(btnSave)
                                .showInformation();
                    }
                    jobTitleBEAN = new JobTitleBEAN();
                    loadTableData();
                    Bindings.bindBidirectional(txtJobTitle.textProperty(), jobTitleBEAN.jobTitleProperty());

                }
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtJobTitle.clear();
                btnSave.setText("Save");
                jobTitleBEAN = new JobTitleBEAN();
                Bindings.bindBidirectional(txtJobTitle.textProperty(), jobTitleBEAN.jobTitleProperty());
            }
        });
        /* ======================== Table Row Factory ==================== */
        tblJobTitle.setRowFactory(new Callback<TableView<JobTitleBEAN>, TableRow<JobTitleBEAN>>() {
            @Override
            public TableRow<JobTitleBEAN> call(TableView<JobTitleBEAN> param) {
                final TableRow<JobTitleBEAN> row = new TableRow<JobTitleBEAN>() {
                    @Override
                    protected void updateItem(JobTitleBEAN jobTitleBean, boolean empty) {
                        super.updateItem(jobTitleBean, empty);
                        if (jobTitleBean != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                jobTitleBEAN = new JobTitleBEAN();
                                jobTitleBEAN.idProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), jobTitleBEAN);
                                Bindings.bindBidirectional(txtJobTitle.textProperty(), jobTitleBEAN.jobTitleProperty());

                            }

                        }
                    }

                });
                return row;
            }
        });

    }

    /**
     *
     * @return
     */
    public boolean validateJobTitle() {
        boolean flag = false;
        if (jobTitleBEAN.getJobTitle() != null && !jobTitleBEAN.getJobTitle().equalsIgnoreCase("")) {
            flag = true;
        }
        return flag;
    }

    /**
     *
     */
    public void loadTableData() {
        ObservableList<JobTitleBEAN> listJobTitles = jobTitleDAO.retrieveJobTitles();
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colJobTitle.setCellValueFactory(
                new PropertyValueFactory<>("jobTitle"));
        FilteredList<JobTitleBEAN> filteredData = new FilteredList<JobTitleBEAN>(listJobTitles, jobTitles -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(accomodations -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (accomodations.getJobTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<JobTitleBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblJobTitle.comparatorProperty());
        tblJobTitle.setItems(sortedData);
        //   tblJobTitle.setItems(listJobTitles);
        /* ======================== Auto Completion For Job Titles ==================== */

        timingHbox.getChildren().remove(txtJobTitle);
        txtJobTitle = TextFields.createClearableTextField();
        timingHbox.getChildren().add(txtJobTitle);
        AutoCompletionBinding<JobTitleBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtJobTitle, listJobTitles);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<JobTitleBEAN>>() {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<JobTitleBEAN> event) {
                BeanUtils.copyProperties(event.getCompletion(), jobTitleBEAN);
                jobTitleBEAN.idProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (newValue != null) {
                            btnSave.setText("Update");
                        } else {
                            btnSave.setText("Save");
                        }
                    }

                });
                Bindings.bindBidirectional(txtJobTitle.textProperty(), jobTitleBEAN.jobTitleProperty());
                System.out.println("Test Values :: " + jobTitleBEAN.getId() + jobTitleBEAN.getJobTitle());
            }
        });
                /* ========================  Validation Support ==================== */

        validationSupport.registerValidator(txtJobTitle, Validator.createEmptyValidator("Job Title is required"));
        validationSupport.validationResultProperty().addListener(new ChangeListener<ValidationResult>() {
            @Override
            public void changed(ObservableValue<? extends ValidationResult> observable, ValidationResult oldValue, ValidationResult newValue) {
                System.out.println("Validate Listner :: " + newValue.getMessages());
            }
        });
    }

}

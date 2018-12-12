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
package com.zs.ina.search.master.university;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.common.SearchConstants;
import com.zs.ina.search.master.university.dao.UniversityBEAN;
import com.zs.ina.search.master.university.dao.UniversityDAO;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLUniversityController implements Initializable {

    @FXML
    private TextField txtUniversity;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<UniversityBEAN> tblUniversity;
    @FXML
    private TableColumn<?, ?> colUniversity;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private ComboBox<String> cmbType;
    @FXML
    private Button btnDelete;
     @FXML
    private HBox hboxUniversity;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    UniversityDAO universityDAO = springAppContext.getBean(UniversityDAO.class);
    UniversityBEAN universityBEAN = new UniversityBEAN();
    Logger logger = Logger.getLogger(FXMLUniversityController.class);
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    List<UniversityBEAN> listUniversityTable = new ArrayList<>();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        cmbType.getItems().addAll(SearchConstants.getListEmployerType());
        bindAllData();
        viewUniversityTable();
        btnDelete.disableProperty().bind(Bindings.equal(tblUniversity.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (universityBEAN.getUniversityId() != null) {
                        int result_update = universityDAO.updateUniversity(universityBEAN);
                        if (result_update > 0) {
                            viewUniversityTable();
                            clearAllFields();
                            showPopupMessages.showSuccess("Updated Successfully !", "University updated Successfully !", btnSave);
                        } else {
                            showPopupMessages.showError("Updation Failed ! Try again !", btnSave);
                        }
                    } else {
                        int result_insert = universityDAO.insertUniversity(universityBEAN);
                        if (result_insert > 0) {
                            viewUniversityTable();
                            clearAllFields();
                            showPopupMessages.showSuccess("Saved Successfully !", "University saved Successfully !", btnSave);
                        } else {
                            showPopupMessages.showError("Save Failed ! Try again !", btnSave);
                        }
                    }

                }
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (universityBEAN.getUniversityId() != null) {
                    int result_delete = universityDAO.deleteUniversity(universityBEAN);
                    if (result_delete > 0) {
                        viewUniversityTable();
                        clearAllFields();
                        showPopupMessages.showSuccess("Deleted Successfully !", "University Deleted Successfully !", btnSave);
                    } else {
                        showPopupMessages.showError("Deletion Failed ! Try again !", btnSave);
                    }

                }
            }
        });
        tblUniversity.setRowFactory(new Callback<TableView<UniversityBEAN>, TableRow<UniversityBEAN>>() {
            @Override
            public TableRow<UniversityBEAN> call(TableView<UniversityBEAN> param) {
                final TableRow<UniversityBEAN> row = new TableRow<UniversityBEAN>() {
                    @Override
                    protected void updateItem(UniversityBEAN universityBEAN, boolean empty) {
                        super.updateItem(universityBEAN, empty);
                        if (universityBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                universityBEAN = new UniversityBEAN();
                                universityBEAN.universityIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), universityBEAN);
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
        txtUniversity.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(universityBEAN.universityProperty(), txtUniversity.textProperty());
                        txtUniversity.setText(oldValue);
                        Bindings.bindBidirectional(universityBEAN.universityProperty(), txtUniversity.textProperty());
                    } else {
                        Bindings.unbindBidirectional(universityBEAN.universityProperty(), txtUniversity.textProperty());
                        txtUniversity.setText(newValue);
                        Bindings.bindBidirectional(universityBEAN.universityProperty(), txtUniversity.textProperty());
                    }

                }
            }

        });

    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtUniversity.textProperty(), universityBEAN.universityProperty());
        Bindings.bindBidirectional(cmbType.valueProperty(), universityBEAN.typeProperty());
    }

    /**
     *
     */
    public void viewUniversityTable() {
        listUniversityTable.clear();
        listUniversityTable = universityDAO.retreiveUniversities();
        addUniversityIntoTable(listUniversityTable);
    }

    public void addUniversityIntoTable(List<UniversityBEAN> listAccomodationsTable) {
        colUniversity.setCellValueFactory(new PropertyValueFactory<>("university"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<UniversityBEAN> universityBEANs = FXCollections.observableList(listAccomodationsTable);
        FilteredList<UniversityBEAN> filteredData = new FilteredList<UniversityBEAN>(universityBEANs, university -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(university -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (university.getUniversity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (university.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<UniversityBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblUniversity.comparatorProperty());
        tblUniversity.setItems(sortedData);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        universityBEAN = new UniversityBEAN();
        cmbType.getSelectionModel().clearSelection();
        bindAllData();
        universityBEAN.universityIdProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }

        });
        txtSearch.setText("");

    }

    /**
     *
     * @return
     */
    public boolean validateAllFields() {
        boolean flag = true;
        if (universityBEAN.getUniversity() == null || universityBEAN.getUniversity().equals("")) {
            showPopupMessages.showError("Enter University", txtUniversity);
            flag = false;
        }
        else if (universityBEAN.getType()== null || universityBEAN.getType().equals("")) {
            showPopupMessages.showError("Select Type", txtUniversity);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void autoCompletion() {
        /* ======================== Create Autocompletion Data By Overriding @toString ==================== */
        ObservableList<UniversityBEAN> listUniversitiesAuto = universityDAO.retreiveUniversities();
        ObservableList<UniversityBEAN> listAutoCompletion = FXCollections.observableArrayList();
        for (UniversityBEAN autoBEAN : listUniversitiesAuto) {
            UniversityBEAN autoCompletionBEAN = new UniversityBEAN() {
                @Override
                public String toString() {
                    return autoBEAN.getUniversity();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        }
        System.out.println("test auto completion list :: " + listAutoCompletion.toString());
        hboxUniversity.getChildren().remove(txtUniversity);
        txtUniversity = TextFields.createClearableTextField();
        AutoCompletionBinding<UniversityBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtUniversity, listAutoCompletion);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<UniversityBEAN>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<UniversityBEAN> event) {
                universityBEAN = new UniversityBEAN();
                BeanUtils.copyProperties(event.getCompletion(), universityBEAN);
                bindAllData();
            }

        });
        HBox.setHgrow(txtUniversity, Priority.ALWAYS);
         txtUniversity.setPrefWidth(261);
        hboxUniversity.getChildren().add(txtUniversity);

    }


}

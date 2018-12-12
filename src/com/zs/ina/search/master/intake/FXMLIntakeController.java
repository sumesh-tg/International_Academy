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
package com.zs.ina.search.master.intake;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.intake.dao.IntakeBEAN;
import com.zs.ina.search.master.intake.dao.IntakeDAO;
import java.net.URL;
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
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLIntakeController implements Initializable {

    @FXML
    private TableView<IntakeBEAN> tblIntake;
    @FXML
    private TableColumn<?, ?> colIntake;
    @FXML
    private TextField txtIntake;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private HBox hboxIntake;
    @FXML
    private Button btnDelete;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    IntakeDAO intakeDAO = springAppContext.getBean(IntakeDAO.class);
    IntakeBEAN intakeBEAN = new IntakeBEAN();
    Logger logger = Logger.getLogger(FXMLIntakeController.class);
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ObservableList<IntakeBEAN> listIntakesTable = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        bindAllData();
        viewIntakeTable();
        btnDelete.disableProperty().bind(Bindings.equal(tblIntake.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (intakeBEAN.getIntakeId() != null) {
                        int result_update = intakeDAO.updateIntake(intakeBEAN);
                        if (result_update > 0) {

                            viewIntakeTable();
                            clearAllFields();
                            popupMessages.showSuccess("Updated Successfully !", "Intake Updated Successfully !", btnSave);

                        } else {
                            popupMessages.showError("Updation Failed ! Try again !", btnSave);
                        }

                    } else {
                        int result_insert = intakeDAO.insertIntake(intakeBEAN);
                        if (result_insert > 0) {
                            viewIntakeTable();
                            clearAllFields();
                            popupMessages.showSuccess("Saved Successfully !", "Intake Saved Successfully !", btnSave);

                        } else {
                            popupMessages.showError("Save Operation Failed ! Try again !", btnSave);
                        }

                    }

                }
            }
        });
        tblIntake.setRowFactory(new Callback<TableView<IntakeBEAN>, TableRow<IntakeBEAN>>() {

            @Override
            public TableRow<IntakeBEAN> call(TableView<IntakeBEAN> param) {
                final TableRow<IntakeBEAN> row = new TableRow<IntakeBEAN>() {
                    @Override
                    protected void updateItem(IntakeBEAN intakeBEAN, boolean empty) {
                        super.updateItem(intakeBEAN, empty);
                        if (intakeBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                intakeBEAN = new IntakeBEAN();
                                intakeBEAN.intakeIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), intakeBEAN);
                                bindAllData();

                            }

                        }
                    }

                });
                return row;
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (intakeBEAN.getIntakeId() != null) {
                    int result_delete = intakeDAO.deleteIntake(intakeBEAN);
                    if (result_delete > 0) {
                        viewIntakeTable();
                        clearAllFields();
                        popupMessages.showSuccess("Deleted Successfully !", "Intake Deleted Successfully !", btnSave);
                    } else {
                        popupMessages.showError("Deletion Failed ! Try again !", btnSave);
                    }

                }
            }
        });

        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();

            }
        });
        txtIntake.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(intakeBEAN.intakeProperty(), txtIntake.textProperty());
                        txtIntake.setText(oldValue);
                        Bindings.bindBidirectional(intakeBEAN.intakeProperty(), txtIntake.textProperty());
                    } else {
                        Bindings.unbindBidirectional(intakeBEAN.intakeProperty(), txtIntake.textProperty());
                        txtIntake.setText(newValue);
                        Bindings.bindBidirectional(intakeBEAN.intakeProperty(), txtIntake.textProperty());
                    }

                }
            }

        });

    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtIntake.textProperty(), intakeBEAN.intakeProperty());

    }

    /**
     *
     */
    public void viewIntakeTable() {
        listIntakesTable.clear();
        listIntakesTable = intakeDAO.retrieveIntakes();
        addIntakesIntoTable(listIntakesTable);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        intakeBEAN = new IntakeBEAN();
        bindAllData();
        intakeBEAN.intakeIdProperty().addListener(new ChangeListener<String>() {

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
        if (intakeBEAN.getIntake() == null || intakeBEAN.getIntake().equals("")) {
            popupMessages.showError("Enter intake", txtIntake);
            flag = false;
        }
        return flag;
    }

//    public void autoCompletion() {
//        List<String> allSourceNames = AccomodationIMPL.getAllAccomodations();
//        txtIntake = TextFields.createSearchField();
//        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtIntake, allSourceNames);
//        txtIntake.setPrefWidth(218);
//        hboxIntake.getChildren().remove(1);
//        hboxIntake.getChildren().add(txtIntake);
//    }
    private void addIntakesIntoTable(ObservableList<IntakeBEAN> listIntakesTable) {

        colIntake.setCellValueFactory(new PropertyValueFactory<>("intake"));

        ObservableList<IntakeBEAN> intakeBEANs = FXCollections.observableList(listIntakesTable);
        FilteredList<IntakeBEAN> filteredData = new FilteredList<IntakeBEAN>(intakeBEANs, intakes -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(intakes -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (intakes.getIntake().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<IntakeBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblIntake.comparatorProperty());
        tblIntake.setItems(sortedData);

    }
}

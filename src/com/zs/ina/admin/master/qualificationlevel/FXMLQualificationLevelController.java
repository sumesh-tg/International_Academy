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
package com.zs.ina.admin.master.qualificationlevel;

import com.zs.ina.admin.master.methods.dao.MethodBEAN;
import com.zs.ina.admin.master.qualificationlevel.dao.LevelBEAN;
import com.zs.ina.admin.master.qualificationlevel.dao.LevelIMPL;
import com.zs.ina.admin.master.qualificationlevel.dao.LevelSERVICE;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.admin.master.sources.dao.SourceIMPL;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
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
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLQualificationLevelController implements Initializable {

    @FXML
    private HBox hboxQualificnLevel;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<LevelBEAN> tblLevel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    LevelBEAN levelBEAN = new LevelBEAN();
    LevelSERVICE levelSERVICE = new LevelSERVICE(new LevelIMPL());
    Logger logger = Logger.getLogger(FXMLQualificationLevelController.class);
    ObservableList<LevelBEAN> levelTableList = FXCollections.observableArrayList();

    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    @FXML
    private TextField txtProgramLevel;
    @FXML
    private TableColumn<?, ?> colProgramLevel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewProgramLevelTable();
        bindAllData();
        autoCompletion();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateAllFields()) {
                    if (levelBEAN.getProgramLevelId() != null) {

                        int result_update = levelSERVICE.updateProgramLevel(levelBEAN);
                        if (result_update > 0) {

                            clearAllFields();
                            viewProgramLevelTable();

                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Qualification Level updated", "Qualification Level updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtSearch);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Qualification Level is not updated", "Qualification Level is not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtSearch);

                        }

                    } else {

                        int result_insert = levelSERVICE.insertProgramLevel(levelBEAN);
                        if (result_insert > 0) {
                            clearAllFields();
                            viewProgramLevelTable();

                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Qualification Level saved", "Qualification Level is saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtSearch);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Qualification Level not saved", "Qualification Level not saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtSearch);

                        }

                    }

                }

            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (levelBEAN.getProgramLevel() == null || levelBEAN.getProgramLevel().equals("")) {
                    showPopupMessages.showError("Enter Qualification Level", txtProgramLevel);
                    flag = false;
                }
                return flag;

            }

        });

        txtProgramLevel.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(levelBEAN.programLevelProperty(), txtProgramLevel.textProperty());
                        txtProgramLevel.setText(oldValue);
                        Bindings.bindBidirectional(levelBEAN.programLevelProperty(), txtProgramLevel.textProperty());
                    } else {
                        Bindings.unbindBidirectional(levelBEAN.programLevelProperty(), txtProgramLevel.textProperty());
                        txtProgramLevel.setText(newValue);
                        Bindings.bindBidirectional(levelBEAN.programLevelProperty(), txtProgramLevel.textProperty());
                    }

                }
            }

        });
        tblLevel.setRowFactory(new Callback<TableView<LevelBEAN>, TableRow<LevelBEAN>>() {

            @Override
            public TableRow<LevelBEAN> call(TableView<LevelBEAN> param) {
                final TableRow<LevelBEAN> row = new TableRow<LevelBEAN>() {
                    @Override
                    protected void updateItem(LevelBEAN levelBEAN, boolean empty) {
                        super.updateItem(levelBEAN, empty);
                        if (levelBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                levelBEAN = new LevelBEAN();
                                levelBEAN.programLevelIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), levelBEAN);
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

    private void bindAllData() {
        Bindings.bindBidirectional(txtProgramLevel.textProperty(), levelBEAN.programLevelProperty());
    }

    private void viewProgramLevelTable() {
        levelTableList.clear();
        levelTableList = levelSERVICE.retrieveProgramLevels();
        addLevelsIntoTable(levelTableList);
    }

    private void addLevelsIntoTable(ObservableList<LevelBEAN> levelTableList) {

        colProgramLevel.setCellValueFactory(new PropertyValueFactory<>("programLevel"));
        ObservableList<LevelBEAN> levelBEANs = FXCollections.observableList(levelTableList);
        FilteredList<LevelBEAN> filteredData = new FilteredList<LevelBEAN>(levelBEANs, level -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(level -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (level.getProgramLevel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<LevelBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblLevel.comparatorProperty());
        tblLevel.setItems(sortedData);
    }

    private void clearAllFields() {
        btnSave.setText("Save");
       // txtProgramLevel.setText("");
        levelBEAN = new LevelBEAN();
        bindAllData();
        levelBEAN.programLevelIdProperty().addListener(new ChangeListener<String>() {

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

    private void autoCompletion() {
      
        List<String> allProgramLevels = RetrieveStaticMasterDAO.getAllProgramLevels();
        txtProgramLevel = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtProgramLevel, allProgramLevels);
        txtProgramLevel.setPrefWidth(218);
        hboxQualificnLevel.getChildren().remove(1);
        hboxQualificnLevel.getChildren().add(txtProgramLevel);
    }

}

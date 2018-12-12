/*
 * Copyright ZoftSolutions(C) 2016 100018
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
package com.zs.ina.admin.master.counselorlimit;

import com.zs.ina.admin.master.counselorlimit.dao.CounselorLimitDAO;
import com.zs.ina.admin.master.counselorlimit.dao.CounselorLimitPOJO;
import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author 100018
 */
public class FXMLSetCounselorLimitController implements Initializable {
    
    @FXML
    private ComboBox<String> cmbBranch;
    @FXML
    private ComboBox<CounselorsListPOJO> cmbCounselors;
    @FXML
    private TextField txtEnqLimit;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<CounselorLimitPOJO> tblEnquiryLimit;
    @FXML
    private TableColumn<CounselorLimitPOJO, CounselorLimitPOJO> clmBranch;
    @FXML
    private TableColumn<CounselorLimitPOJO, CounselorLimitPOJO> clmCounselor;
    @FXML
    private TableColumn<CounselorLimitPOJO, CounselorLimitPOJO> clmEnqLimit;
    CounselorLimitPOJO counselorLimitPOJO = new CounselorLimitPOJO();
    ObservableList<String> branch = FXCollections.observableArrayList();
    private ObservableList<CounselorLimitPOJO> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Slider sliderLimit;
    @FXML
    private ComboBox<String> cmbUnit;
    ObservableList<String> obsUnit = FXCollections.observableArrayList("Days", "Hour", "Minutes");
    @FXML
    private Button btnTimeSave;
    @FXML
    private Label lblTimeLimit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllCounselorLimits();
        addTableEvents();
        addBindings();
        initializeComboBoxes();
        addButtonEvents();
        sliderLimit.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null) {
                    lblTimeLimit.setText("" + newValue.intValue());
                }
            }
        });
        sliderLimit.setValue(10);
        Map<String, String> mapTime = CounselorLimitDAO.retrieveEnquiryHoldTime();
        if (mapTime.get("unit") != null) {
            cmbUnit.getSelectionModel().clearAndSelect(cmbUnit.getItems().indexOf(mapTime.get("unit")));
        }
        if (mapTime.get("time") != null) {
            sliderLimit.setValue(Double.parseDouble(mapTime.get("time")));
        }
    }

    /**
     *
     */
    public void clearAllControls() {
        
        cmbBranch.getSelectionModel().clearSelection();
        cmbCounselors.getSelectionModel().clearSelection();
        txtSearch.setText("");
        counselorLimitPOJO.setId(null);
        counselorLimitPOJO.setCounselorName(null);
        counselorLimitPOJO.setEnquiry_limit(null);
        btnSave.setText("Save");
        loadAllCounselorLimits();
        
    }

    /**
     *
     */
    public void addButtonEvents() {
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearAllControls();
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (counselorLimitPOJO.getId() != null) {
//Update
                    CounselorLimitDAO.updateCounselorLimit(counselorLimitPOJO);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated Successfully !", ButtonType.OK);
                    Stage stageConfirm = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageConfirm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageConfirm.initOwner(btnTimeSave.getScene().getWindow());
                    alert.showAndWait();
                    clearAllControls();
                    
                } else {
//        Insert    
                    CounselorLimitDAO.insertCounselorLimit(counselorLimitPOJO);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully !", ButtonType.OK);
                    Stage stageConfirm = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageConfirm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageConfirm.initOwner(btnTimeSave.getScene().getWindow());
                    alert.showAndWait();
                    clearAllControls();
                    
                }
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete", ButtonType.YES, ButtonType.NO);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnDelete.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    if (counselorLimitPOJO.getId() != null) {
//                   Delete
                        CounselorLimitDAO.deleteCounselorLimit(counselorLimitPOJO);
                        clearAllControls();
                    }
                }
            }
        });

        /* ========================== Save Action For Save Time Limit ==========================  */
        btnTimeSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (lblTimeLimit.getText() == null || lblTimeLimit.getText().equals("")) {
                    
                } else {
                    CounselorLimitDAO.setEnquiryHoldTime(lblTimeLimit.getText(), cmbUnit.getSelectionModel().getSelectedItem());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully !", ButtonType.OK);
                    Stage stageConfirm = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageConfirm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageConfirm.initOwner(btnTimeSave.getScene().getWindow());
                    alert.showAndWait();
                }
            }
        });
    }

    /**
     *
     */
    public void initializeComboBoxes() {
        cmbUnit.setItems(obsUnit);
        cmbUnit.getSelectionModel().selectFirst();
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        for (String s : branches) {
            branch.add(s);
        }
        cmbBranch.setItems(branch);
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    ObservableList assign = FXCollections.observableArrayList();
                    ObservableList<CounselorsListPOJO> assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(newValue);
                    cmbCounselors.setItems(assignedCounselors);
                }
            }
        });
        cmbCounselors.valueProperty().addListener(new ChangeListener<CounselorsListPOJO>() {
            @Override
            public void changed(ObservableValue<? extends CounselorsListPOJO> observable, CounselorsListPOJO oldValue, CounselorsListPOJO newValue) {
                if (newValue != null) {
                    CounselorLimitPOJO clpojo = CounselorLimitDAO.getLimitById(newValue.getUsername());
                    if (clpojo.getId() != null) {
                        counselorLimitPOJO = CounselorLimitDAO.getLimitById(newValue.getUsername());
                        addBindings();
                        btnSave.setText("Update");
                    } else {
                        btnSave.setText("Save");
                    }
                    System.out.println("test change events :: " + counselorLimitPOJO.toString());
                }
            }
        });
    }

    /**
     *
     */
    public void addBindings() {
        Bindings.bindBidirectional(cmbBranch.valueProperty(), counselorLimitPOJO.branchProperty());
   //     Bindings.bindBidirectional(cmbCounselors.valueProperty(), counselorLimitPOJO.counselorNameProperty());
        counselorLimitPOJO.counselorNameProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (CounselorsListPOJO listPOJO : cmbCounselors.getItems()) {
                        if (listPOJO.getUsername().equalsIgnoreCase(newValue)) {
                            cmbCounselors.getSelectionModel().select(listPOJO);
                        }
                    }
                }
            }
        });
        
        Bindings.bindBidirectional(txtEnqLimit.textProperty(), counselorLimitPOJO.enquiry_limitProperty());
    }

    /**
     *
     */
    public void addTableEvents() {
        tblEnquiryLimit.setRowFactory(new Callback<TableView<CounselorLimitPOJO>, TableRow<CounselorLimitPOJO>>() {
            @Override
            public TableRow<CounselorLimitPOJO> call(TableView<CounselorLimitPOJO> param) {
                return new TableRow<CounselorLimitPOJO>() {
                    {
                        setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                counselorLimitPOJO = (CounselorLimitPOJO) tblEnquiryLimit.getSelectionModel().getSelectedItem();
                                System.out.println("test click :: " + counselorLimitPOJO.toString());
                                addBindings();
                                btnSave.setText("Update");
                            }
                            
                        });
                    }
                    
                };
            }
        });
    }

    /**
     *
     */
    public void loadAllCounselorLimits() {
        
        List<CounselorLimitPOJO> counselorLimitList = CounselorLimitDAO.getAllData();
        clmBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        clmCounselor.setCellValueFactory(new PropertyValueFactory<>("counselorName"));
        clmEnqLimit.setCellValueFactory(new PropertyValueFactory<>("enquiry_limit"));
        
        masterSearchData.removeAll(masterSearchData);
        for (CounselorLimitPOJO limitPOJO : counselorLimitList) {
            masterSearchData.add(limitPOJO);
        }
        tblEnquiryLimit.setItems(masterSearchData);
        FilteredList<CounselorLimitPOJO> filteredData = new FilteredList<>(masterSearchData, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(predicate -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (predicate.getBranch().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches contact name.
                }
                if (predicate.getCounselorName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches contact name.
                }
                if (predicate.getEnquiry_limit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches contact name.
                }
                return false;
            });
            SortedList<CounselorLimitPOJO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblEnquiryLimit.comparatorProperty());
            tblEnquiryLimit.setItems(sortedData);
        });
    }
    
}

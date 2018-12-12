/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.qualification;

import com.zs.ina.admin.master.qualification.dao.QualificationBean;
import com.zs.ina.admin.master.qualification.dao.QualificationDao;
import com.zs.ina.context.Context;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLQualificationController implements Initializable {

    @FXML
    private ComboBox<?> qualificationLevelCombo;
    @FXML
    private TextField QualificationFieldCombo;
    @FXML
    private Button clearbtn;
    @FXML
    private Button savebtn;
    @FXML
    private TableColumn<?, ?> qualificationLevelClmn;
    @FXML
    private TableColumn<?, ?> QualificationFieldClmn;

    private ObservableList levelList;
    QualificationBean qualificationBean;
    @FXML
    private TableView<QualificationBean> qualificationFieldTable;
    @FXML
    private TextField searchTxt;
    static String qualificationId = "";
    @FXML
    private TableColumn<?, ?> qualificatioIdClmn;
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane mainAnchor;
    private ObservableList<QualificationBean> masterSearchData = FXCollections.observableArrayList();

    ;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboInit();
        tableInitMain();
        tableInit();
        addChangeEvents();
    }

    private void comboInit() {
        levelList = QualificationDao.getQualificationLevel();
        qualificationLevelCombo.setItems(levelList);

    }

    @FXML
    private void clearHandle(ActionEvent event) {

        clear();
    }

    @FXML
    private void saveHandle(ActionEvent event) {
        qualificationBean = new QualificationBean();
        qualificationBean.setQualificationLevel(qualificationLevelCombo.getSelectionModel().getSelectedItem().toString());
        qualificationBean.setQualificationField(QualificationFieldCombo.getText());
        Context.getInstance().currentProfile().setQualificationBean(qualificationBean);
        if (validation()) {
            if (!qualificationId.equals("")) {
                int row = QualificationDao.update(qualificationId);
                if (row > 0) {
                    tableInitMain();
                    clear();
                }
            } else {
                if (!QualificationDao.checkDuplicateEntry(qualificationBean)) {
                    int row = QualificationDao.insert();
                    if (row > 0) {
                        tableInitMain();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Qualification already inserted! "));
                    usernameValidator.show(QualificationFieldCombo, Side.RIGHT, 10, 0);
                }
            }
        }

    }

    private void clear() {
        qualificationLevelCombo.getSelectionModel().selectFirst();
        qualificationLevelCombo.promptTextProperty().set("Select");
        QualificationFieldCombo.setText("");
        searchTxt.setText("");
        qualificationId = "";
        savebtn.setText("Save");
    }

    private void tableInit() {
        List<QualificationBean> branchList = QualificationDao.getQualificationFieldDetails();
        qualificatioIdClmn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        qualificationLevelClmn.setCellValueFactory(
                new PropertyValueFactory<>("qualificationLevel"));
        QualificationFieldClmn.setCellValueFactory(
                new PropertyValueFactory<>("qualificationField"));
        masterSearchData.removeAll(masterSearchData);
        for (QualificationBean bp : branchList) {
            masterSearchData.add(bp);
        }

        FilteredList<QualificationBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
                if (enquiry.getQualificationLevel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                } else if (enquiry.getQualificationField().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches  email.
                }
//                else if (enquiry.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter phone.
//                }
                return false; // Does not match.
            });
            SortedList<QualificationBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(qualificationFieldTable.comparatorProperty());
            qualificationFieldTable.setItems(sortedData);
        });

    }

    /**
     *
     */
    public void addChangeEvents() {
        QualificationFieldCombo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    QualificationFieldCombo.setText(newValue);
                } else {
                    QualificationFieldCombo.setText(oldValue);
                }

            }
        });
    }

    @FXML
    private void qualFieldTblMouseClicked(MouseEvent event) {
        savebtn.setText("Update");
        QualificationBean qualificationBean = (QualificationBean) qualificationFieldTable.getSelectionModel().getSelectedItem();
        qualificationLevelCombo.getSelectionModel().clearAndSelect(qualificationLevelCombo.getItems().indexOf(qualificationBean.getQualificationLevel()));
        QualificationFieldCombo.setText(qualificationBean.getQualificationField());
        qualificationId = qualificationBean.getId();
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (QualificationFieldCombo.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter qualification field "));
            usernameValidator.show(QualificationFieldCombo, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        int row = QualificationDao.delete(qualificationId);
        if (row > 0) {

            tableInitMain();
            searchTxt.setText("");
            clear();
        }
    }

    private void tableInitMain() {
        List<QualificationBean> branchList = QualificationDao.getQualificationFieldDetails();
        qualificatioIdClmn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        qualificationLevelClmn.setCellValueFactory(
                new PropertyValueFactory<>("qualificationLevel"));
        QualificationFieldClmn.setCellValueFactory(
                new PropertyValueFactory<>("qualificationField"));
        masterSearchData.removeAll(masterSearchData);
        for (QualificationBean bp : branchList) {
            masterSearchData.add(bp);
        }

        qualificationFieldTable.setItems(masterSearchData);

    }
}

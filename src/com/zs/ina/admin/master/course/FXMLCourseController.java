/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.course;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.course.dao.CourseBean;
import com.zs.ina.admin.master.course.dao.CourseDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLCourseController implements Initializable {

    @FXML
    private TextField salaryTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<CourseBean> salaryTbl;
    @FXML
    private TableColumn<?, ?> salaryClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    @FXML
    private HBox languageHBox;
    private ObservableList<CourseBean> masterSearchData = FXCollections.observableArrayList();
    private CourseBean languageBEAN;
    private String languageId = "";
    final ContextMenu usernameValidator = new ContextMenu();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        setTableValues();
        serchValues();
        addChangeEvents();
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_course_management");
            setTableValues();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    /**
     *
     */
    public void addChangeEvents() {
        salaryTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    salaryTxt.setText(newValue);
                } else {
                    salaryTxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        languageBEAN = new CourseBean();
        languageBEAN.setSalary(salaryTxt.getText());
        languageBEAN.setId(languageId);
        if (validation()) {
            if (!languageId.equals("")) {
                int row = CourseDAO.updateSalary(languageBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!CourseDAO.checkDuplicateEntry(salaryTxt.getText())) {
                    int row = CourseDAO.insertSalary(languageBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Course already inserted! "));
                    usernameValidator.show(salaryTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        CourseDAO.deleteSalary(languageBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleLanguageTbl(MouseEvent event) {
        saveBtn.setText("Update");
        languageBEAN = (CourseBean) salaryTbl.getSelectionModel().getSelectedItem();
        salaryTxt.setText(languageBEAN.getSalary());
        languageId = languageBEAN.getId();
    }

    @FXML
    private void handleLanguageTblReleased(KeyEvent event) {
        saveBtn.setText("Update");
        languageBEAN = (CourseBean) salaryTbl.getSelectionModel().getSelectedItem();
        salaryTxt.setText(languageBEAN.getSalary());
        languageId = languageBEAN.getId();
    }

    private void autoCompletion() {
        List<String> allLaguages = CourseDAO.getAllSalries();
        salaryTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(salaryTxt, allLaguages);
        salaryTxt.setPrefWidth(200);
        languageHBox.getChildren().remove(1);
        languageHBox.getChildren().add(salaryTxt);
    }

    private void setTableValues() {
        ObservableList<CourseBean> languageBEANs = CourseDAO.getSalaryDetails();
        salaryClmn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (CourseBean bp : languageBEANs) {
            masterSearchData.add(bp);
        }
        salaryTbl.setItems(masterSearchData);
    }

    private void serchValues() {
        ObservableList<CourseBean> languageBEANs = CourseDAO.getSalaryDetails();
        salaryClmn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (CourseBean bp : languageBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<CourseBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getSalary().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<CourseBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(salaryTbl.comparatorProperty());
            salaryTbl.setItems(sortedData);
        });
    }

    private boolean validation() {
        boolean f = true;
        if (salaryTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Course Field "));
            usernameValidator.show(salaryTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    private void clear() {
        salaryTxt.setText("");
        searchTxt.setText("");
        languageId = "";
        saveBtn.setText("Save");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.coursetype;

import com.zs.ina.admin.master.coursetype.dao.CourseTypeBEAN;
import com.zs.ina.admin.master.coursetype.dao.CourseTypeDAO;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import java.net.URL;
import java.sql.SQLException;
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
 * @author user
 */
public class FXMLCourseTypeController implements Initializable {

    @FXML
    private TextField courseTypeTxt;
    @FXML
    private Button btnExcelUpload;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<CourseTypeBEAN> courseTypleTbl;
    @FXML
    private TableColumn<?, ?> courseTypleClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private String courseTypeId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private ObservableList<CourseTypeBEAN> masterSearchData = FXCollections.observableArrayList();
    private CourseTypeBEAN courseTypeBEAN;
    @FXML
    private HBox courseTypeHBox;

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
    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("mastertbl_course_type");
        setTableValues();
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        courseTypeBEAN = new CourseTypeBEAN();
        courseTypeBEAN.setCourseType(courseTypeTxt.getText());
        courseTypeBEAN.setId(courseTypeId);
        if (validation()) {
            if (!courseTypeId.equals("")) {
                int row = CourseTypeDAO.updateCourseType(courseTypeBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!CourseTypeDAO.checkDuplicateEntry(courseTypeTxt.getText())) {
                    int row = CourseTypeDAO.insertCourseType(courseTypeBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Course type already inserted! "));
                    usernameValidator.show(courseTypeTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        CourseTypeDAO.deleteCourseType(courseTypeBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleCourseTypleTbl(MouseEvent event) {
        btnSave.setText("Update");
        courseTypeBEAN = (CourseTypeBEAN) courseTypleTbl.getSelectionModel().getSelectedItem();
        courseTypeTxt.setText(courseTypeBEAN.getCourseType());
        courseTypeId = courseTypeBEAN.getId();
    }

    @FXML
    private void handleCourseTypleTblReleased(KeyEvent event) {
        btnSave.setText("Update");
        courseTypeBEAN = (CourseTypeBEAN) courseTypleTbl.getSelectionModel().getSelectedItem();
        courseTypeTxt.setText(courseTypeBEAN.getCourseType());
        courseTypeId = courseTypeBEAN.getId();
    }

    private void clear() {
        courseTypeTxt.setText("");
        courseTypeId = "";
        btnSave.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (courseTypeTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Course Type field "));
            usernameValidator.show(courseTypeTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void addChangeEvents() {
        courseTypeTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    courseTypeTxt.setText(newValue);
                } else {
                    courseTypeTxt.setText(oldValue);
                }
            }
        });
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allCourseTypes = CourseTypeDAO.getAllCourseType();
        courseTypeTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(courseTypeTxt, allCourseTypes);
        courseTypeTxt.setPrefWidth(200);
        courseTypeHBox.getChildren().remove(0);
        courseTypeHBox.getChildren().add(courseTypeTxt);
    }

    /**
     *
     */
    public void setTableValues() {
        ObservableList<CourseTypeBEAN> courseTypeBEANs = CourseTypeDAO.getCourseTypeDetails();
        courseTypleClmn.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (CourseTypeBEAN bp : courseTypeBEANs) {
            masterSearchData.add(bp);
        }
        courseTypleTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<CourseTypeBEAN> courseTypeBEANs = CourseTypeDAO.getCourseTypeDetails();
        courseTypleClmn.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (CourseTypeBEAN bp : courseTypeBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<CourseTypeBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getCourseType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<CourseTypeBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(courseTypleTbl.comparatorProperty());
            courseTypleTbl.setItems(sortedData);
        });
    }
}

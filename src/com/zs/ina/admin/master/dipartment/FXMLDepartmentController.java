/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.dipartment;

import com.zs.ina.admin.master.coursetype.dao.CourseTypeBEAN;
import com.zs.ina.admin.master.coursetype.dao.CourseTypeDAO;
import com.zs.ina.admin.master.dipartment.dao.DepartmentBEAN;
import com.zs.ina.admin.master.dipartment.dao.DepartmentDAO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
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
 * @author shine
 */
public class FXMLDepartmentController implements Initializable {

    @FXML
    private TextField departmentTxt;
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
    private TableView<DepartmentBEAN> departmentTbl;
    @FXML
    private TableColumn<?, ?> departmentClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private DepartmentBEAN departmentBean;
    final ContextMenu usernameValidator = new ContextMenu();
    private String departmentID = "";
    private ObservableList<DepartmentBEAN> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private HBox departmentHbox;

    /**
     * Initializes the controller class.
     * @param url default
     * @param rb default
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
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        departmentTxt.setText("");
        searchTxt.setText("");
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        departmentBean = new DepartmentBEAN();
        departmentBean.setDepartment(departmentTxt.getText());
        departmentBean.setId(departmentID);
        if (validation()) {
            if (!departmentID.equals("")) {
                int row = DepartmentDAO.updateDepartment(departmentBean);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!DepartmentDAO.checkDuplicateEntry(departmentTxt.getText())) {
                    int row = DepartmentDAO.insert(departmentBean);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Department already inserted! "));
                    usernameValidator.show(departmentTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        DepartmentDAO.delete(departmentBean.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleCourseTypleTbl(MouseEvent event) {
         btnSave.setText("Update");
       departmentBean = (DepartmentBEAN) departmentTbl.getSelectionModel().getSelectedItem();
        departmentTxt.setText(departmentBean.getDepartment());
        departmentID = departmentBean.getId();
    }

    @FXML
    private void handleCourseTypleTblReleased(KeyEvent event) {
    }

    private boolean validation() {
        boolean f = true;
        if (departmentTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter Course Type field "));
            usernameValidator.show(departmentTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    private void clear() {
        departmentTxt.setText("");
        searchTxt.setText("");
    }

    private void setTableValues() {
        ObservableList<DepartmentBEAN> departmentBEANS = DepartmentDAO.getDepartmentDetails();
        departmentClmn.setCellValueFactory(new PropertyValueFactory<>("department"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (DepartmentBEAN bp : departmentBEANS) {
            masterSearchData.add(bp);
        }
        departmentTbl.setItems(masterSearchData);
    }

    private void autoCompletion() {
        List<String> allDepartment = RetrieveStaticMasterDAO.getAllDepartment();
        departmentTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(departmentTxt, allDepartment);
        departmentTxt.setPrefWidth(200);
        departmentHbox.getChildren().remove(0);
        departmentHbox.getChildren().add(departmentTxt);
    }

    private void serchValues() {
        ObservableList<DepartmentBEAN> departmentBEANS = DepartmentDAO.getDepartmentDetails();
        departmentClmn.setCellValueFactory(new PropertyValueFactory<>("department"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (DepartmentBEAN bp : departmentBEANS) {
            masterSearchData.add(bp);
        }
        FilteredList<DepartmentBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<DepartmentBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(departmentTbl.comparatorProperty());
            departmentTbl.setItems(sortedData);
        });
    }

    private void addChangeEvents() {
        departmentTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    departmentTxt.setText(newValue);
                } else {
                    departmentTxt.setText(oldValue);
                }
            }
        });
    }

}

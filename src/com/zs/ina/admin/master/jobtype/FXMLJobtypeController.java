/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.jobtype;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.jobtype.dao.JobtypeBean;
import com.zs.ina.admin.master.jobtype.dao.JobTypeDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FXMLJobtypeController implements Initializable {

    @FXML
    private TextField salaryTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<JobtypeBean> salaryTbl;
    @FXML
    private TableColumn<?, ?> salaryClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    @FXML
    private HBox languageHBox;
    private ObservableList<JobtypeBean> masterSearchData = FXCollections.observableArrayList();
    private JobtypeBean languageBEAN;
    private String  languageId = "";
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
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_salary");
            setTableValues();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLJobtypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        languageBEAN = new JobtypeBean();
        languageBEAN.setSalary(salaryTxt.getText());
        languageBEAN.setId(languageId);
        if (validation()) {
            if (!languageId.equals("")) {
                int row = JobTypeDAO.updateSalary(languageBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!JobTypeDAO.checkDuplicateEntry(salaryTxt.getText())) {
                    int row = JobTypeDAO.insertSalary(languageBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Job type already inserted! "));
                    usernameValidator.show(salaryTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        JobTypeDAO.deleteSalary(languageBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleLanguageTbl(MouseEvent event) {
        saveBtn.setText("Update");
        languageBEAN = (JobtypeBean) salaryTbl.getSelectionModel().getSelectedItem();
        salaryTxt.setText(languageBEAN.getSalary());
        languageId = languageBEAN.getId();
    }

    @FXML
    private void handleLanguageTblReleased(KeyEvent event) {
        saveBtn.setText("Update");
        languageBEAN = (JobtypeBean) salaryTbl.getSelectionModel().getSelectedItem();
        salaryTxt.setText(languageBEAN.getSalary());
        languageId = languageBEAN.getId();
    }

    private void autoCompletion() {
        List<String> allLaguages = JobTypeDAO.getAllSalries();
        salaryTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(salaryTxt, allLaguages);
        salaryTxt.setPrefWidth(200);
        languageHBox.getChildren().remove(0);
        languageHBox.getChildren().add(salaryTxt);
    }

    private void setTableValues() {
        ObservableList<JobtypeBean> languageBEANs = JobTypeDAO.getSalaryDetails();
        salaryClmn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (JobtypeBean bp : languageBEANs) {
            masterSearchData.add(bp);
        }
        salaryTbl.setItems(masterSearchData);
    }

    private void serchValues() {
        ObservableList<JobtypeBean> languageBEANs = JobTypeDAO.getSalaryDetails();
        salaryClmn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (JobtypeBean bp : languageBEANs) {
            masterSearchData.add(bp);
        }
        FilteredList<JobtypeBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
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
            SortedList<JobtypeBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(salaryTbl.comparatorProperty());
            salaryTbl.setItems(sortedData);
        });
    }

    private boolean validation() {
        boolean f = true;
        if (salaryTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter job type field "));
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

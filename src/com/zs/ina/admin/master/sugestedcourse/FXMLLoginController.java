/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.sugestedcourse;

import com.zs.ina.admin.master.sugestedcourse.dao.SuggestedCourseDAO;
import com.zs.ina.admin.master.sugestedcourse.dao.SuggestedCoursePOJO;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLLoginController implements Initializable {

    private TextField firstNameTxt;
    private TextField lastNameTxt;
    private TextField userNameTxt;
    private ComboBox<?> roleCmb;
    private TextField emailTxt;
    private TextField phoneTxt;
    private ComboBox<?> branchCmb;
    ObservableList roles = FXCollections.observableArrayList();
    ObservableList branches = FXCollections.observableArrayList();
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private HBox userNameHbox;
    static String courseId = "";
    @FXML
    private Button deleteBtn;
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private TableView<SuggestedCoursePOJO> loginTableView;
    @FXML
    private TextField searchTxt;
    private ObservableList<SuggestedCoursePOJO> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Button exelUploadbtn;
    @FXML
    private ComboBox<?> countryCmb;
    @FXML
    private TextField locationTxt;
    @FXML
    private ComboBox<?> universityCmb;
    @FXML
    private ComboBox<?> levelCmb;
    @FXML
    private ComboBox<?> courseCmb;
    @FXML
    private TableColumn<SuggestedCoursePOJO, String> countryColumn;
    @FXML
    private TableColumn<SuggestedCoursePOJO, String> locationColumn;
    @FXML
    private TableColumn<SuggestedCoursePOJO, String> universityColumn;
    @FXML
    private TableColumn<SuggestedCoursePOJO, String> levelColumn;
    @FXML
    private TableColumn<SuggestedCoursePOJO, String> courseColumn;
    ObservableList country = FXCollections.observableArrayList();
    ObservableList university = FXCollections.observableArrayList();
    ObservableList level = FXCollections.observableArrayList();
    ObservableList course = FXCollections.observableArrayList();
    ObservableList prpgramLevels = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTableDetails();
        getAllTableDetails();
        addChangeEvents();
        initCombo();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) throws MessagingException {
        SuggestedCoursePOJO coursePOJO = new SuggestedCoursePOJO();
        coursePOJO.setCountry(countryCmb.getSelectionModel().getSelectedItem().toString());
        coursePOJO.setUniversity(universityCmb.getSelectionModel().getSelectedItem().toString());
        coursePOJO.setLevel(levelCmb.getSelectionModel().getSelectedItem().toString());
        coursePOJO.setCourse(courseCmb.getSelectionModel().getSelectedItem().toString());
        coursePOJO.setLocation(locationTxt.getText());
        if (!courseId.equals("")) {
            int row = SuggestedCourseDAO.updateSuggestedCourse(courseId, coursePOJO);
            clearFields();
            getAllTableDetails();
        } else {
            int row = SuggestedCourseDAO.insertSourceDetails(coursePOJO);
            if (row > 0) {
                clearFields();
                getAllTableDetails();
            }
        }
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clearFields();
    }

    /**
     *
     */
    public void clearFields() {
        universityCmb.getSelectionModel().clearSelection();
        countryCmb.getSelectionModel().clearSelection();
        locationTxt.setText("");
        levelCmb.getSelectionModel().clearSelection();
        courseCmb.getSelectionModel().clearSelection();
        courseId = "";
        saveBtn.setText("Save");
    }

    /**
     *
     */
    public void addChangeEvents() {
        locationTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    locationTxt.setText(newValue);
                } else {
                    locationTxt.setText(oldValue);
                }

            }
        });

    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        int opction = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        if (opction == 0) {
            SuggestedCourseDAO.deleteSuggestedCourse(courseId);
            getAllTableDetails();
            clearFields();
        }
    }

    //Method for login form validation

    /**
     *
     * @return
     */
    public boolean loginFormValidation() {
        boolean f = true;
        if (userNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter user name! "));
            usernameValidator.show(userNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (firstNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter first name! "));
            usernameValidator.show(firstNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (lastNameTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter last name! "));
            usernameValidator.show(lastNameTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (phoneTxt.getText().equals("") || phoneTxt.getText().length() < 10) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter valid phone no! "));
            usernameValidator.show(phoneTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void initCombo() {
        List<String> countries = SuggestedCourseDAO.getCountries();
        for (String s : countries) {
            country.add(s);
        }
        countryCmb.setItems(country);

        List<String> universites = SuggestedCourseDAO.getUniversity();
        for (String s : universites) {
            university.add(s);
        }
        universityCmb.setItems(university);
        List<String> levels = SuggestedCourseDAO.getProgramLevel();
        for (String s : levels) {
            level.add(s);
        }
        levelCmb.setItems(level);
        List<String> courses = SuggestedCourseDAO.getCourse();
        for (String s : courses) {
            course.add(s);
        }
        courseCmb.setItems(course);

    }

    /**
     *
     */
    public void getTableDetails() {
        List<SuggestedCoursePOJO> sourceList = SuggestedCourseDAO.getSuggestedCourse();
        countryColumn.setCellValueFactory(
                new PropertyValueFactory<>("country"));
        universityColumn.setCellValueFactory(
                new PropertyValueFactory<>("university"));
        levelColumn.setCellValueFactory(
                new PropertyValueFactory<>("level"));
        courseColumn.setCellValueFactory(
                new PropertyValueFactory<>("course"));
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
        masterSearchData.removeAll(masterSearchData);
        for (SuggestedCoursePOJO s : sourceList) {
            masterSearchData.add(s);
        }
        FilteredList<SuggestedCoursePOJO> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (enquiry.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (enquiry.getUniversity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (enquiry.getLevel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (enquiry.getCourse().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            SortedList<SuggestedCoursePOJO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(loginTableView.comparatorProperty());
            loginTableView.setItems(sortedData);
        });
    }

    /**
     *
     */
    public void getAllTableDetails() {
        List<SuggestedCoursePOJO> sourceList = SuggestedCourseDAO.getSuggestedCourse();
        countryColumn.setCellValueFactory(
                new PropertyValueFactory<>("country"));
        universityColumn.setCellValueFactory(
                new PropertyValueFactory<>("university"));
        levelColumn.setCellValueFactory(
                new PropertyValueFactory<>("level"));
        courseColumn.setCellValueFactory(
                new PropertyValueFactory<>("course"));
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
        masterSearchData.removeAll(masterSearchData);
        for (SuggestedCoursePOJO s : sourceList) {
            masterSearchData.add(s);
        }
        loginTableView.getItems().setAll(masterSearchData);
    }

    @FXML
    private void handleLoginTableView(MouseEvent event) {
        saveBtn.setText("Update");
        SuggestedCoursePOJO coursePOJO = (SuggestedCoursePOJO) loginTableView.getSelectionModel().getSelectedItem();
        countryCmb.getSelectionModel().clearAndSelect(countryCmb.getItems().indexOf(coursePOJO.getCountry()));
        universityCmb.getSelectionModel().clearAndSelect(universityCmb.getItems().indexOf(coursePOJO.getUniversity()));
        levelCmb.getSelectionModel().clearAndSelect(levelCmb.getItems().indexOf(coursePOJO.getLevel()));
        courseCmb.getSelectionModel().clearAndSelect(courseCmb.getItems().indexOf(coursePOJO.getCourse()));
        locationTxt.setText(coursePOJO.getLocation());
        courseId = coursePOJO.getCourseId();
    }

    @FXML
    private void handleLoginTableViewKeyReleased(KeyEvent event) {
        saveBtn.setText("Update");
        SuggestedCoursePOJO coursePOJO = (SuggestedCoursePOJO) loginTableView.getSelectionModel().getSelectedItem();
        countryCmb.getSelectionModel().clearAndSelect(countryCmb.getItems().indexOf(coursePOJO.getCountry()));
        universityCmb.getSelectionModel().clearAndSelect(universityCmb.getItems().indexOf(coursePOJO.getUniversity()));
        levelCmb.getSelectionModel().clearAndSelect(levelCmb.getItems().indexOf(coursePOJO.getLevel()));
        courseCmb.getSelectionModel().clearAndSelect(countryCmb.getItems().indexOf(coursePOJO.getCourse()));
        locationTxt.setText(coursePOJO.getLocation());
        courseId = coursePOJO.getCourseId();
    }

    @FXML
    private void handleExeluploadBtn(ActionEvent event) {
//        ExcelUpload excelUpload = new ExcelUpload();
//        excelUpload.exelFileChooser("branch_tbl");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.suggestedCourse;

import com.zs.ina.admin.master.institute.dao.InstituteDAO;
import com.zs.ina.assesment.FXMLAssesmentFormController;
import com.zs.ina.assesment.suggestedCourse.dao.SuggestedCourseDAO;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLSuggestedCourseController implements Initializable {

//    public  void setRegNo(int ENQUIRY_ID) {
//       id=ENQUIRY_ID;
//        JOptionPane.showConfirmDialog(null, id);
//    }
    String ENQUIRY_ID;
    @FXML
    private Button btnSubmit;
    @FXML
    private ComboBox<?> cmbCountry;
    @FXML
    private ComboBox<?> cmbLocation;
    @FXML
    private ComboBox<?> cmbUniversity;
    @FXML
    private ComboBox<?> cmbLevel;
    private ObservableList country = FXCollections.observableArrayList();
    CounselorDetailsBEAN counselorDetailsBEAN;
  private ObservableList location=FXCollections.observableArrayList();
    ObservableList  university=FXCollections.observableArrayList();
    ObservableList     level=FXCollections.observableArrayList();
    @FXML
    private Label lblSuggestedCourse;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        counselorDetailsBEAN = new CounselorDetailsBEAN();
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
       // JOptionPane.showMessageDialog(null, ENQUIRY_ID);
        countryCombo();
        cmbCountry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
          

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                //JOptionPane.showMessageDialog(null, cmbCountry.getSelectionModel().getSelectedItem().toString());
                String[] parts = cmbCountry.getSelectionModel().getSelectedItem().toString().split(",");
                String value = parts[0];
                locationcombo(value);
            }

            private void locationcombo(String value) {
                List<String> locations = SuggestedCourseDAO.getLocation(value);
                for (String s : locations) {
                    location.add(s);
                }
                cmbLocation.setItems(location);
            }

        });
        cmbLocation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
           universityCombo(cmbLocation.getSelectionModel().getSelectedItem().toString());
            }

            private void universityCombo(String value) {
               List<String> universities = SuggestedCourseDAO.getUniversities(value);
                for (String s : universities) {
                    university.add(s);
                }
                cmbUniversity.setItems(university);
            }
            
        });
        cmbUniversity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
           levetCombo(cmbUniversity.getSelectionModel().getSelectedItem().toString());
            }

            private void levetCombo(String value) {
                List<String> levels = SuggestedCourseDAO.getLevels(value);
                for (String s : levels) {
                    level.add(s);
                }
                cmbLevel.setItems(level);
            }
        });

    }

    @FXML
    private void handleSubmitBtn(ActionEvent event) {
        String[] parts = cmbCountry.getSelectionModel().getSelectedItem().toString().split(",");
                String country = parts[0];
        String location=cmbLocation.getSelectionModel().getSelectedItem().toString();
        String university=cmbUniversity.getSelectionModel().getSelectedItem().toString();
        String level=cmbLevel.getSelectionModel().getSelectedItem().toString();
        lblSuggestedCourse.setText(SuggestedCourseDAO.getCourse(country,location,university,level));
    }

    private void countryCombo() {
        List<String> countries = SuggestedCourseDAO.getCountry(ENQUIRY_ID);
        for (String s : countries) {
            country.add(s);
        }
        cmbCountry.setItems(country);
    }

}

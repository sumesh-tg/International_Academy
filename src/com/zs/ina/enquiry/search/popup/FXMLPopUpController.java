/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search.popup;

import com.zs.ina.context.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLPopUpController implements Initializable {
    
    @FXML
    private Label contactDetailsLbl;
    @FXML
    private Label contactNameLbl;
    @FXML
    private Label programListLbl;
    @FXML
    private Label enquirySourceLbl;
    @FXML
    private Label branchLbl;
    @FXML
    private Label statusLbl;
    @FXML
    private Label remarksLbl;
    @FXML
    private Label dateLbl;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
    }
    
    /**
     *
     */
    public void initData() {
        contactNameLbl.setText("Contact Details of " + Context.getInstance().currentProfile().getEnquirySearchPOJO().getContactName());
        contactDetailsLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getContactNumber() + "\n"
                + Context.getInstance().currentProfile().getEnquirySearchPOJO().getContactEmail());
        programListLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getListOfPrograms());
        enquirySourceLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getEnquirySource());
        System.out.println("BRANCH::>" + Context.getInstance().currentProfile().getEnquirySearchPOJO().getEnquiryBracnch());
        branchLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getCounselorBranch());
        statusLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getStatus());
        remarksLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getRemarks());
        dateLbl.setText(Context.getInstance().currentProfile().getEnquirySearchPOJO().getEdate());
    }
    
}

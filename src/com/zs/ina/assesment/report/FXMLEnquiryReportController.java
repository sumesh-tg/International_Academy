/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.report;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.report.dao.AssesmentReportDAO;
import static com.zs.ina.assesment.report.dao.AssesmentReportDAO.getAssesmentEnquiry;
import static com.zs.ina.assesment.report.dao.AssesmentReportDAO.getNotAssesdEnquiry;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLEnquiryReportController implements Initializable {

    @FXML
    private Pane reportPane;
    @FXML
    private BarChart<String, Number> reportBarChart;
    @FXML
    private RadioButton todayRadioBtn;
    @FXML
    private RadioButton weekRadioBtn;
    @FXML
    private RadioButton monthRadioBtn;
    XYChart.Series series1;
    XYChart.Series series2;
    CategoryAxis xAxis;
    NumberAxis yAxis;
    @FXML
    private ComboBox<?> branchCmb;
    @FXML
    private ComboBox<?> counselorCmb;
    ObservableList branch = FXCollections.observableArrayList();
    ObservableList counselors = FXCollections.observableArrayList();
    static int period = 0;
    static String branchName = "All Branch";
    static String counselorName = "";
    @FXML
    private ToggleGroup radioCounselorGroup;
    @FXML
    private NumberAxis numAxis;
    @FXML
    private CategoryAxis catAxis;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        todayRadioBtn.setSelected(true);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        reportBarChart.setTitle("Assesment Report");
        catAxis.setLabel("Branches");
        numAxis.setLabel("Percentage");
        series1.setName("Not assesed enquiry");
        List<AssesmentReportDAO.Count> AssesdCountList = getAssesmentEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : AssesdCountList) {
            series1.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        series2.setName("Assessed Enquiry");
        List<AssesmentReportDAO.Count> NotAssesdCountList = getNotAssesdEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : NotAssesdCountList) {
            series2.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        reportBarChart.getData().addAll(series1, series2);
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        for (String s : branches) {
            branch.add(s);
        }
        branch.add("All Branch");
        branchCmb.setItems(branch);
        branchCmb.getSelectionModel().selectLast();
        period = 0;
    }

    @FXML
    private void handleTodayRadioBtn(MouseEvent event) {
        todayRadioBtn.setSelected(true);
        weekRadioBtn.setSelected(false);
        monthRadioBtn.setSelected(false);
        period = 0;
        reportBarChart.getData().removeAll(series1, series2);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        series1.setName("Not assesd enquiry");
        series2.setName("Assessed Enquiry");
        List<AssesmentReportDAO.Count> AssesdCountList = getAssesmentEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : AssesdCountList) {
            series1.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));

        }
        List<AssesmentReportDAO.Count> NotAssesdCountList = getNotAssesdEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : NotAssesdCountList) {
            series2.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        reportBarChart.getData().addAll(series1, series2);
    }

    @FXML
    private void handleWeekRadioBtn(MouseEvent event) {
        weekRadioBtn.setSelected(true);
        todayRadioBtn.setSelected(false);
        monthRadioBtn.setSelected(false);
        period = 7;
        reportBarChart.getData().removeAll(series1, series2);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        series1.setName("Not assesd enquiry");
        series2.setName("Assessed Enquiry");
        List<AssesmentReportDAO.Count> AssesdCountList = getAssesmentEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : AssesdCountList) {
            series1.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));

        }
        List<AssesmentReportDAO.Count> NotAssesdCountList = getNotAssesdEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : NotAssesdCountList) {
            series2.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        reportBarChart.getData().addAll(series1, series2);
    }

    @FXML
    private void handleMonthRadioBtn(MouseEvent event) {
        monthRadioBtn.setSelected(true);
        todayRadioBtn.setSelected(false);
        weekRadioBtn.setSelected(false);
        period = 30;
        reportBarChart.getData().removeAll(series1, series2);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        series1.setName("Not assesd enquiry");
        series2.setName("Assessed Enquiry");
        List<AssesmentReportDAO.Count> AssesdCountList = getAssesmentEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : AssesdCountList) {
            series1.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));

        }
        List<AssesmentReportDAO.Count> NotAssesdCountList = getNotAssesdEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : NotAssesdCountList) {
            series2.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        reportBarChart.getData().addAll(series1, series2);
    }

    @FXML
    private void handleBranchCmb(ActionEvent event) {
        counselorCmb.getItems().removeAll(counselors);
        List<String> counselorList = AssesmentReportDAO.getCounselors(branchCmb.getSelectionModel().getSelectedItem().toString());
        for (String s : counselorList) {
            counselors.add(s);
        }
        branchName = branchCmb.getSelectionModel().getSelectedItem().toString();
        counselorCmb.setItems(counselors);
        reportBarChart.getData().removeAll(series1, series2);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        series1.setName("Not assesd enquiry");
        series2.setName("Assessed Enquiry");
        System.out.println("Actiooo");
        List<AssesmentReportDAO.Count> AssesdCountList = getAssesmentEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : AssesdCountList) {
            series1.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));

        }
        List<AssesmentReportDAO.Count> NotAssesdCountList = getNotAssesdEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : NotAssesdCountList) {
            series2.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        reportBarChart.getData().addAll(series1, series2);

    }

    @FXML
    private void handlecounselorCmb(ActionEvent event) {
        counselorName = counselorCmb.getSelectionModel().getSelectedItem().toString();
        reportBarChart.getData().removeAll(series1, series2);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        series1.setName("Not assesd enquiry");
        series2.setName("Assessed Enquiry");
        System.out.println("Actiooo");
        List<AssesmentReportDAO.Count> AssesdCountList = getAssesmentEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : AssesdCountList) {
            series1.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));

        }
        List<AssesmentReportDAO.Count> NotAssesdCountList = getNotAssesdEnquiry(period, branchName, counselorName);
        for (AssesmentReportDAO.Count c : NotAssesdCountList) {
            series2.getData().add(new XYChart.Data(c.getBranch(), c.getCount()));
        }
        reportBarChart.getData().addAll(series1, series2);
    }

}

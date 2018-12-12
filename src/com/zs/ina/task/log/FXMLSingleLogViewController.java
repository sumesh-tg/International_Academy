/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
package com.zs.ina.task.log;

import com.zs.ina.common.TimeAgoFunctions;
import com.zs.ina.task.dao.TasksBEAN;
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLSingleLogViewController implements Initializable {

    @FXML
    private VBox vboxCircle;
    @FXML
    private Circle circle;
    @FXML
    private Label lblBigLetter;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblRemark;
    @FXML
    private Label lblUserName;
    List<String> listColors = new ArrayList<>();
    @FXML
    private Label lblSubStatusChange;
    @FXML
    private Label lblTimeAgo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     *
     * @param tasksBEAN
     */
    public void initData(TasksBEAN tasksBEAN) {
        listColors.add("#104fa2");
        listColors.add("#990000");
        listColors.add("#9900ff");
        listColors.add("#009999");
        listColors.add("#0C6B75");
        listColors.add("#195214");
        listColors.add("#5F7523");
        listColors.add("#943625");

        lblUserName.setText(tasksBEAN.getUpdatedFullname());
        lblRemark.setText(tasksBEAN.getRemarks());
        lblRemark.setWrapText(true);
        lblSubStatusChange.setWrapText(true);
        lblRemark.setTextAlignment(TextAlignment.JUSTIFY);
        lblDate.setText(tasksBEAN.getUpdatedDate());
        lblSubStatusChange.setText("");
        lblTimeAgo.setText("");
        lblTimeAgo.setText(TimeAgoFunctions.toRelativeAgo(Long.parseLong(tasksBEAN.getTimeAgo())));
        if (tasksBEAN.getUpdatedFullname() != null) {
            if (tasksBEAN.getUpdatedFullname().length() > 0) {
                lblBigLetter.setText(tasksBEAN.getUpdatedFullname().substring(0, 1));
            }
        }
        circle.setFill(Paint.valueOf(tasksBEAN.getColor()));
        System.out.println("Single Log :: " + tasksBEAN.toString());

        /* ======================== Checking enquiry related task ==================== */
        if (tasksBEAN.getAssessmentSubStatus() != null) {
            if (!tasksBEAN.getAssessmentSubStatus().equalsIgnoreCase("")) {
                lblSubStatusChange.setText("" + tasksBEAN.getUpdatedFullname()+ " changed assessment status to " + tasksBEAN.getAssessmentSubStatus());
            }
        }

    }

    /**
     *
     * @param list
     * @return
     */
    public String getRandomList(List<String> list) {
        //0-4
        int index = ThreadLocalRandom.current().nextInt(list.size());
        System.out.println("\nIndex :" + index);
        return list.get(index);

    }

}

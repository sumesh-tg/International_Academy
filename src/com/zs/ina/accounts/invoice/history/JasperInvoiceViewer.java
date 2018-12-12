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
package com.zs.ina.accounts.invoice.history;

import com.zs.ina.admin.reports.JRViewerFxMode;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class JasperInvoiceViewer extends Application {

    private JasperPrint jasperPrint = new JasperPrint();
    private JRViewerFxMode printMode;
    private String fxmlPath;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("B4 Loader Start inner..............." + fxmlPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            System.out.println("Start inner..............." + fxmlPath);
            primaryStage.setTitle("International Academy Reports");
            primaryStage.show();
            FXMLInvoiceHistoryViewerController viewerFxController = (FXMLInvoiceHistoryViewerController) loader.getController();
            viewerFxController.setJasperPrint(jasperPrint);
            viewerFxController.show();
        } catch (IOException ex) {
            System.out.println("Start...............");
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param jasperPrint
     * @param printMode
     * @param primaryStage
     * @param fxmlPath
     */
    public JasperInvoiceViewer(JasperPrint jasperPrint, JRViewerFxMode printMode, Stage primaryStage, String fxmlPath) {
        this.jasperPrint = jasperPrint;
        this.printMode = printMode;
        this.fxmlPath = fxmlPath;
        System.out.println("JRViewerFX ...");
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

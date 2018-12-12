package com.zs.ina.admin.reports;

import com.zs.ina.admin.reports.view.FXMLReportViewerController;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 */
/**
 * @author Michael Grecol
 * @project JasperViewerFx
 * @filename JRViewerFx.java
 * @date Mar 23, 2015
 */
public class JRViewerFx extends Application {

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
            FXMLReportViewerController viewerFxController = (FXMLReportViewerController) loader.getController();
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
    public JRViewerFx(JasperPrint jasperPrint, JRViewerFxMode printMode, Stage primaryStage, String fxmlPath) {
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

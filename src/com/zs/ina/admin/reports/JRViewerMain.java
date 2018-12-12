/**
 *
 */
package com.zs.ina.admin.reports;

import com.zs.ina.admin.reports.view.FXMLReportViewerController;
import java.sql.Connection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * @author Michael Grecol
 * @project JasperViewerFx
 * @filename JRViewerMain.java
 * @date Mar 23, 2015
 */
public class JRViewerMain extends Application {

    /**
     *
     */
    public static String[] prgArgs;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        prgArgs = args;
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JasperPrint jasperPrint = null;
        try {
            Connection con = DBPool.getInstance().getConn();
//            String s = JasperCompileManager.compileReportToFile("/com/zs/ina/admin/reports/view/Blank_A4_Landscape.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(FXMLReportViewerController.class.getResourceAsStream("International_Academy.jrxml"));
            jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), con);

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
        JRViewerFx viewer = new JRViewerFx(jasperPrint, JRViewerFxMode.REPORT_VIEW, primaryStage,"/com/zs/ina/admin/reports/view/FXMLReportViewer.fxml");
        viewer.start(primaryStage);

    }
}

/*
 * Copyright (C) 2016 100035
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.accounts;

import com.zs.ina.accounts.constants.InvoiceConstants;
import com.zs.ina.admin.master.userlogin.dao.RolePOJO;
import com.zs.ina.admin.reports.JRViewerFxMode;
import com.zs.ina.accounts.invoice.history.JasperInvoiceViewer;
import com.zs.ina.admin.reports.view.FXMLReportViewerController;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLAccountsInboxController implements Initializable {

    @FXML
    private Label lblUserdata;
    @FXML
    private ComboBox<RolePOJO> cmbSwitchUser;
    @FXML
    private Button btnSwitchUserGo;
    @FXML
    private VBox vboxHyperlinks;
    @FXML
    private Hyperlink hyperInbox;
    @FXML
    private Hyperlink hyperAllInvoice;
    @FXML
    private HBox hboxSubView;
    @FXML
    private TableView<?> tblAccountsInbox;
    static Logger logger = Logger.getLogger(FXMLAccountsInboxController.class);
    @FXML
    private Label lblMainTitle;
    @FXML
    private Label lblLogout;
    @FXML
    private Button btnAddMasterForms;
    PopOver popOverMenu = new PopOver();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllHyperlinkEvents();
        buttonActions();
        addLogout();
    }

    public void buttonActions() {
        btnAddMasterForms.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                ObservableList<SortType> _all_sort_types = FXCollections.observableArrayList(SortType.values());
                VBox vBoxSortType = new VBox();
                Label label = new Label("MASTER FORMS");
                label.setUnderline(true);
                vBoxSortType.getChildren().add(label);
                vBoxSortType.setStyle("-fx-padding:5;-fx-spacing:5");
                for (InvoiceConstants.MasterForms sortType : InvoiceConstants.MasterForms.values()) {
                    switch (sortType) {
                        case INVOICE_ITEM:
                            Hyperlink hyperlinkInvoiceItem = new Hyperlink("Add/Update Invoice Item");
                            hyperlinkInvoiceItem.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/master/items/FXMLInvoiceItems.fxml"));
//                    root.getStylesheets().add("/com/zs/ina/enquiry/search/fxmlenquirysearch.css");
                                        Scene secondScene = new Scene(root);
                                        Stage secondStage = new Stage();
                                        secondStage.setTitle("ADD/EDIT INVOICE ITEMS");
                                        secondStage.setScene(secondScene);
                                        secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                        secondStage.initModality(Modality.APPLICATION_MODAL);
                                        secondStage.initOwner(btnAddMasterForms.getScene().getWindow());
//                                        secondStage.initStyle(StageStyle.UNDECORATED);
                                        secondStage.show();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                        logger.info(ex.getMessage());
                                    }
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkInvoiceItem);
                            break;
                        case TAX:
                            Hyperlink hyperlinkTax = new Hyperlink("Add/Update Tax");
                            hyperlinkTax.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/master/tax/FXMLMasterTax.fxml"));
//                    root.getStylesheets().add("/com/zs/ina/enquiry/search/fxmlenquirysearch.css");
                                        Scene secondScene = new Scene(root);
                                        Stage secondStage = new Stage();
                                        secondStage.setTitle("ADD/EDIT TAX");
                                        secondStage.setScene(secondScene);
                                        secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                        secondStage.initModality(Modality.APPLICATION_MODAL);
                                        secondStage.initOwner(btnAddMasterForms.getScene().getWindow());
//                                        secondStage.initStyle(StageStyle.UNDECORATED);
                                        secondStage.show();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                        logger.info(ex.getMessage());
                                    }
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkTax);
                            break;
                        case PAYMENT_TERMS:
                            Hyperlink hyperPaymentTerms = new Hyperlink("Add/Update Payment Terms");
                            hyperPaymentTerms.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/master/paymentterms/FXMLPaymentTerms.fxml"));
                                        Scene secondScene = new Scene(root);
                                        Stage secondStage = new Stage();
                                        secondStage.setTitle("ADD/EDIT TAX");
                                        secondStage.setScene(secondScene);
                                        secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                        secondStage.initModality(Modality.APPLICATION_MODAL);
                                        secondStage.initOwner(btnAddMasterForms.getScene().getWindow());
//                                        secondStage.initStyle(StageStyle.UNDECORATED);
                                        secondStage.show();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                        logger.info(ex.getMessage());
                                    }
                                }
                            });
                            vBoxSortType.getChildren().add(hyperPaymentTerms);
                            break;
                        case CUSTOMER_NAME:
                            Hyperlink hyperPaymentTest = new Hyperlink("test jasper");
                            hyperPaymentTest.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    JasperPrint jasperPrint = null;
                                    String fxmlPath = "/com/zs/ina/accounts/invoice/history/FXMLInvoiceHistoryViewer.fxml";
//                                    String fxmlPath = "/com/zs/ina/admin/reports/view/FXMLReportViewer.fxml";
                                    Stage stageReports = new Stage();
                                    stageReports.setTitle("Reports");
                                    stageReports.initOwner(btnAddMasterForms.getScene().getWindow());
                                    Connection con = DBPool.getInstance().getConn();
                                    Map parameters = new HashMap();
                                    parameters.put("ReportTitle", "Report");
                                    parameters.put("Author", "Author varieble");
                                    parameters.put("branch", "Kollam");
                                    parameters.put("dyna_query", "Kollam");
                                    try {
                                        JasperReport jasperReport = JasperCompileManager.compileReport(FXMLReportViewerController.class.getResourceAsStream("report1.jrxml"));
                                        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);

                                    } catch (JRException e) {
                                        e.printStackTrace();
                                    } finally {
                                        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                                        ClosingResourcesInDB.closingAllResources(con, new Statement[]{}, new ResultSet[]{}, stackTraceElements);
                                    }
                                    JasperInvoiceViewer viewer = new JasperInvoiceViewer(jasperPrint, JRViewerFxMode.REPORT_VIEW, stageReports, fxmlPath);
                                    try {
                                        viewer.start(stageReports);
                                    } catch (Exception ex) {
                                        logger.error(ex.toString());
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            vBoxSortType.getChildren().add(hyperPaymentTest);
                            break;
                        case STATUS:
                            Hyperlink hyperlinkStatus = new Hyperlink("Status");
                            hyperlinkStatus.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkStatus);
                            break;
                        case DUE_DATE:
                            Hyperlink hyperlinkDueDate = new Hyperlink("Due Date");
                            hyperlinkDueDate.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkDueDate);
                            break;
                        case AMOUNT:
                            Hyperlink hyperlinkAmount = new Hyperlink("Amount");
                            hyperlinkAmount.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkAmount);
                            break;
                        case BALANCE_DUE:
                            Hyperlink hyperlinkBalanceDue = new Hyperlink("Balance Due");
                            hyperlinkBalanceDue.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkBalanceDue);
                            break;
                    }
                }
                if (popOverMenu.isShowing() || popOverMenu.isDetached()) {
                    popOverMenu.hide();
                }
                popOverMenu = new PopOver();
                popOverMenu.setContentNode(vBoxSortType);
                popOverMenu.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
                popOverMenu.show(btnAddMasterForms, 1);
//                popOver.show(btnShowMenu.getScene().getWindow(), btnShowMenu.getLayoutX(), btnShowMenu.getLayoutY() );
            }
        });
    }

    public void addLogout() {
        lblLogout.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage mainStage, currntStages;
                currntStages = (Stage) lblLogout.getScene().getWindow();
                mainStage = new Stage(StageStyle.DECORATED);
                currntStages.close();
                mainStage.setTitle("Login");

                Parent root = null;
                /* ====================== Clear Login Audit ====================== */
//                LoginFormDAO.deleteLoginAudit(CUR_USERNAME);
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/zs/ina/login/FXMLLogin.fxml"));
                    root.getStylesheets().add(getClass().getResource("/com/zs/ina/login/style.css").toExternalForm());

                } catch (Exception exception) {
                    logger.error(exception.getMessage());
                }
                synchronized (root) {
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ina_window_logo.png")));
                    mainStage.setTitle("International Academy");
                    mainStage.setScene(scene);
                }
                mainStage.initStyle(StageStyle.UNDECORATED);
                mainStage.show();
//                notifyTimer.stop();

            }
        });
    }

    public void loadAllHyperlinkEvents() {
        hyperAllInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/invoice/FXMLAllInvoices.fxml"));
                    HBox.setHgrow(root, Priority.ALWAYS);
                    ObservableList<Node> _all_nodes = hboxSubView.getChildren();
                    hboxSubView.getChildren().removeAll(_all_nodes);
                    hboxSubView.getChildren().add(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        hyperInbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Node> _all_nodes = hboxSubView.getChildren();
                hboxSubView.getChildren().removeAll(_all_nodes);
                hboxSubView.getChildren().add(tblAccountsInbox);
            }
        });
    }
}

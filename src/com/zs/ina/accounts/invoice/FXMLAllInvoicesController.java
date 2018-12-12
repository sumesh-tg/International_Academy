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
package com.zs.ina.accounts.invoice;

import com.jfoenix.controls.JFXComboBox;
import com.zs.ina.accounts.invoice.dao.InvoiceBEAN;
import com.zs.ina.accounts.constants.InvoiceConstants.SortType;
import com.zs.ina.accounts.constants.InvoiceConstants.Status;
import com.zs.ina.accounts.invoice.dao.InvoiceDAO;
import com.zs.ina.accounts.invoice.dao.InvoiceItemsDAO;
import com.zs.ina.accounts.invoice.history.JasperInvoiceViewer;
import com.zs.ina.accounts.master.dao.PaymentTermsBEAN;
import com.zs.ina.admin.reports.JRViewerFxMode;
import com.zs.ina.admin.reports.view.FXMLReportViewerController;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.validation.ValidationSupport;
import org.springframework.context.ApplicationContext;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLAllInvoicesController implements Initializable {

    @FXML
    private JFXComboBox<Status> cmbInvoiceFilters;
    @FXML
    private Button btnNewInvoice;
    @FXML
    private Button btnShowMenu;
    @FXML
    private StackPane stackInvoiceMain;
    private MaskerPane masker = new MaskerPane();
    ProgressIndicator loadingProgressIndicator = new ProgressBar(-1);
    @FXML
    private TableColumn<InvoiceBEAN, String> colDate;
    @FXML
    private TableColumn<InvoiceBEAN, String> colInvoiceNo;
    @FXML
    private TableColumn<InvoiceBEAN, String> colOrderNo;
    @FXML
    private TableColumn<InvoiceBEAN, String> colCustomerName;
    @FXML
    private TableColumn<InvoiceBEAN, String> colStatus;
    @FXML
    private TableColumn<InvoiceBEAN, String> colDueDate;
    @FXML
    private TableColumn<InvoiceBEAN, String> colAmount;
    @FXML
    private TableColumn<InvoiceBEAN, String> colBalanceDue;
    PopOver popOverMenu = new PopOver();
    @FXML
    private TableView<InvoiceBEAN> tblAllInvoices;
    static Logger logger = Logger.getLogger(FXMLAllInvoicesController.class);
    /* ======================== Coding ==================== */
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ValidationSupport validationSupport = new ValidationSupport();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Dynamic Beans ==================== */
    InvoiceDAO invoiceDAO = springAppContext.getBean(InvoiceDAO.class);
    InvoiceItemsDAO invoiceItemsDAO = springAppContext.getBean(InvoiceItemsDAO.class);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ======================== Set Filter Drop Down Details ==================== */
        loadFilters();
        loadButtonEvents();
        btnShowMenu.disableProperty().bind(popOverMenu.showingProperty());
        loadAllInvoices();
    }

    public void loadAllInvoices() {
        ObservableList<InvoiceBEAN> listInvoice = invoiceDAO.retrieveInvoiceAll();
        colDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().invoiceCreatedDateProperty();
            }
        });
        colInvoiceNo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().invoiceNumberProperty();
            }
        });
        colOrderNo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().referenceNumberProperty();
            }
        });
        colStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().invoiceStatusProperty();
            }
        });
        colDueDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().dueDateProperty();
            }
        });
        colAmount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().totalProperty();
            }
        });
        colBalanceDue.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().balanceProperty();
            }
        });
        colCustomerName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceBEAN, String> param) {
                return param.getValue().invoiceIidProperty();
            }
        });
        tblAllInvoices.getItems().addAll(listInvoice);
        System.out.println("Test List Size :: " + listInvoice.size());
        /* ======================== Row Factory For All Invoices ==================== */
        tblAllInvoices.setRowFactory(new Callback<TableView<InvoiceBEAN>, TableRow<InvoiceBEAN>>() {
            @Override
            public TableRow<InvoiceBEAN> call(TableView<InvoiceBEAN> param) {
                final TableRow<InvoiceBEAN> row = new TableRow<InvoiceBEAN>() {
                    @Override
                    protected void updateItem(InvoiceBEAN tbean, boolean empty) {
                        super.updateItem(tbean, empty);
                        if (!empty) {

                        }
                    }
                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Clicked On The Row ::: ");

                        JasperPrint jasperPrint = null;
                        String fxmlPath = "/com/zs/ina/accounts/invoice/history/FXMLInvoiceHistoryViewer.fxml";
//                                    String fxmlPath = "/com/zs/ina/admin/reports/view/FXMLReportViewer.fxml";
                        Stage stageReports = new Stage();
                        stageReports.setTitle("Reports");
                        stageReports.initOwner(btnNewInvoice.getScene().getWindow());
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
                return row;
            }
        });
    }

    Service<Void> service = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws InterruptedException {
                    updateMessage("Initializing Server");
                    updateProgress(0, 10);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
//                                loadingProgressIndicator.setProgress(getProgress());
                            masker.setProgress(0);
                        }
                    });
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(300);
                        /* ======================== DO BACKGROUND WORK HERE ==================== */
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
//                                loadingProgressIndicator.setProgress(getProgress());
                                masker.setProgress(getProgress());
                            }
                        });
                        updateProgress(i + 1, 10);
                        updateMessage("Loading Invoices...");
                    }
                    updateMessage("Finished");
                    return null;
                }
            };
        }
    };

    private void loadFilters() {
        stackInvoiceMain.getChildren().add(masker);
        masker.setVisible(false);
        cmbInvoiceFilters.setItems(FXCollections.observableArrayList(Status.values()));

        cmbInvoiceFilters.valueProperty().addListener(new ChangeListener<Status>() {
            @Override
            public void changed(ObservableValue<? extends Status> observable, Status oldValue, Status newValue) {
                System.out.println("Test combo Change :: " + newValue.name());
//                ProgressDialog progDiag = new ProgressDialog(service);
//                progDiag.setTitle("Filter Invoices");
//                progDiag.initOwner(btnNewInvoice.getScene().getWindow());
//                progDiag.setHeaderText("Loading "+newValue.name()+" Invoices");
//                progDiag.initModality(Modality.WINDOW_MODAL);
                masker.setVisible(true);
                if (!service.isRunning()) {
                    service.reset();
                    service.restart();
                } else {
                    service.start();
                }
            }
        });
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                masker.setVisible(false);
            }
        });
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                masker.setVisible(false);
            }
        });
    }

    private void loadButtonEvents() {
        btnShowMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                ObservableList<SortType> _all_sort_types = FXCollections.observableArrayList(SortType.values());
                VBox vBoxSortType = new VBox();
                Label label = new Label("SORT TYPE");
                label.setUnderline(true);
                vBoxSortType.getChildren().add(label);
                vBoxSortType.setStyle("-fx-padding:5;-fx-spacing:5");
                for (SortType sortType : SortType.values()) {
                    switch (sortType) {
                        case CREATED_TIME:
                            Hyperlink hyperlinkDate = new Hyperlink("Created Date");
                            hyperlinkDate.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colDate.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colDate);
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkDate);
                            break;
                        case INVOICE:
                            Hyperlink hyperlinkInvoice = new Hyperlink("Invoice#");
                            hyperlinkInvoice.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colInvoiceNo.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colInvoiceNo);
                                    tblAllInvoices.sort();
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkInvoice);
                            break;
                        case ORDER_NUMBER:
                            Hyperlink hyperlinkOrderNumber = new Hyperlink("Order Number");
                            hyperlinkOrderNumber.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colOrderNo.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colOrderNo);
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkOrderNumber);
                            break;
                        case CUSTOMER_NAME:
                            Hyperlink hyperlinkCustomerName = new Hyperlink("Customer Name");
                            hyperlinkCustomerName.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colCustomerName.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colCustomerName);
                                    tblAllInvoices.sort();
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkCustomerName);
                            break;
                        case STATUS:
                            Hyperlink hyperlinkStatus = new Hyperlink("Status");
                            hyperlinkStatus.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colStatus.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colStatus);
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkStatus);
                            break;
                        case DUE_DATE:
                            Hyperlink hyperlinkDueDate = new Hyperlink("Due Date");
                            hyperlinkDueDate.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colDueDate.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colDueDate);
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkDueDate);
                            break;
                        case AMOUNT:
                            Hyperlink hyperlinkAmount = new Hyperlink("Amount");
                            hyperlinkAmount.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colAmount.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colAmount);
                                }
                            });
                            vBoxSortType.getChildren().add(hyperlinkAmount);
                            break;
                        case BALANCE_DUE:
                            Hyperlink hyperlinkBalanceDue = new Hyperlink("Balance Due");
                            hyperlinkBalanceDue.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    colBalanceDue.setSortType(TableColumn.SortType.ASCENDING);
                                    tblAllInvoices.getSortOrder().add(colBalanceDue);
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
                popOverMenu.show(btnShowMenu, 1);
//                popOver.show(btnShowMenu.getScene().getWindow(), btnShowMenu.getLayoutX(), btnShowMenu.getLayoutY() );
            }
        });
        btnNewInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/invoice/newinvoice/FXMLNewInvoice.fxml"));
                    Scene secondScene = new Scene(root, 780, 635);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Enquiry Reports ");
                    secondStage.setScene(secondScene);
                    secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    secondStage.initModality(Modality.WINDOW_MODAL);
                    secondStage.initOwner(btnShowMenu.getScene().getWindow());
                    secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    logger.log(Priority.ERROR, ex.getMessage());
                }
            }
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.mainView;

import com.zs.ina.admin.master.userlogin.dao.LoginBEAN;
import com.zs.ina.admin.master.userlogin.dao.LoginIMPL;
import com.zs.ina.admin.master.userlogin.dao.LoginSERVICE;
import com.zs.ina.admin.master.userlogin.dao.RolePOJO;
import com.zs.ina.admin.reports.JRViewerFx;
import com.zs.ina.admin.reports.JRViewerFxMode;
import com.zs.ina.admin.reports.view.FXMLReportViewerController;
import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.login.dao.LoginFormDAO;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class MasterTableFXMLController implements Initializable {

    @FXML
    private Hyperlink inbox;

    @FXML
    private Hyperlink branch;
    @FXML
    private Hyperlink loginDetails;
    @FXML
    private Hyperlink noticeBoard;
    @FXML
    private Hyperlink agency;
    @FXML
    private Hyperlink course;
    @FXML
    private Hyperlink qualificationDuration;
    @FXML
    private Hyperlink qualificationField;
    @FXML
    private Hyperlink experiance;
    @FXML
    private Hyperlink institute;
    @FXML
    private Hyperlink jobType;
    @FXML
    private Hyperlink profession;
    @FXML
    private Hyperlink salary;
    @FXML
    private Hyperlink language;
    @FXML
    private Hyperlink otherTest;
    @FXML
    private Hyperlink score;
    @FXML
    private Hyperlink timing;
    @FXML
    private Hyperlink examBoard;
    @FXML
    private Hyperlink migrationCategory;
    @FXML
    private Hyperlink batchTiming;
    @FXML
    private Hyperlink courseType;
    Stage stage;
    private FXMLLoader fxmlLoader;
    private Parent root1;
    @FXML
    private Label lblLogout;
    @FXML
    private Hyperlink universityLink;
    @FXML
    private Hyperlink suggestedCourseLink;
    @FXML
    private Hyperlink hyperCountryLocations;
    @FXML
    private Hyperlink hyperEnqForwardPurpose;
    @FXML
    private Hyperlink hprTechnicalSkills;
    @FXML
    private Hyperlink hprOtherSkills;
    @FXML
    private Hyperlink hptAdmissionTest;
    @FXML
    private Hyperlink hyperViewReports;

    @FXML
    private Hyperlink hyperViewTasks;
    static Logger logger = Logger.getLogger(MasterTableFXMLController.class);
    @FXML
    private Hyperlink hyperEnquiryStatus;
    @FXML
    private Label hyperBranch;
    @FXML
    private Hyperlink hyperConfigEmail;
    @FXML
    private Hyperlink hyperAddSource;
    @FXML
    private Hyperlink hyperAddMethod;
    @FXML
    private Hyperlink hyperLocations;
    @FXML
    private Hyperlink hyperPrivilages;
    @FXML
    private Hyperlink hyperRole;
    @FXML
    private Hyperlink hyperSetEnquiryLimit;
    @FXML
    private Hyperlink hyperSetRefreshSeconds;
    @FXML
    private Hyperlink hyperQualificnLevel;
    @FXML
    private Hyperlink hyperRelationship;
    @FXML
    private Hyperlink hyperStdCodes;
    @FXML
    private Hyperlink hyperIsdCodes;
    @FXML
    private Hyperlink hyperAge;
    @FXML
    private Hyperlink hyperPaymentHead;
    @FXML
    private Hyperlink hyperPaymentAmount;
    @FXML
    private Hyperlink hyperMakeMandatory;
    @FXML
    private Hyperlink hyperAddDocument;
    @FXML
    private Hyperlink hyperAssignDocuments;
    @FXML
    private Hyperlink hyperEmailTemplate;
    @FXML
    private Hyperlink hyperEmailSignature;
    @FXML
    private ComboBox<RolePOJO> cmbSwitchUser;
    @FXML
    private Button btnSwitchUserGo;
    @FXML
    private Hyperlink hyperAccomodation;
    @FXML
    private Hyperlink hyperJobTitle;
    @FXML
    private Hyperlink hyperCurrency;
    @FXML
    private Hyperlink hyperFeeType;
    @FXML
    private Hyperlink hyperAddIntake;
    @FXML
    private Hyperlink hyperSubClass;
    @FXML
    private Hyperlink hyperOccCategory;
    @FXML
    private Hyperlink hyperTrainingDuration;
    private String CUR_BRANCH = null;
    private String CUR_USERNAME = null;
    private String CUR_ROLE = null;
    ObservableList<RolePOJO> rolesList = FXCollections.observableArrayList();
    LoginSERVICE loginSERVICE = new LoginSERVICE(new LoginIMPL());
    LoginBEAN loginBEAN = new LoginBEAN();
    String currentRole = "";
    @FXML
    private Hyperlink hyperSearchHome;
    @FXML
    private Hyperlink hyperAddCountry;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();

        lblLogout.setText("Log Out (" + CUR_USERNAME + ")");
        hyperBranch.setText(CUR_BRANCH + " ( Branch )");
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        addSettingsHyperlinksEvents();
        loadCountryLocationsForm();
        addEnquiryForwardData();
        addTasksView();
        addLogout();
        addEmailConfig();
        addStdCodes();
        System.out.println("USER ROLE ADMIN PAGE========" + CUR_ROLE);

        /* ======================== Get all roles of current user ==================== */
        loginBEAN = loginSERVICE.retrieveSingleUserPrivilage(CUR_USERNAME);
        String privilag = loginBEAN.getPrivilages();
        System.out.println("PRIVILAGE =========" + privilag);
        rolesList = loginSERVICE.retrieveRolesUsingPrivilages(privilag);
        for (RolePOJO rolePOJO : rolesList) {
            cmbSwitchUser.getItems().add(rolePOJO);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));

        cmbSwitchUser.valueProperty().addListener(new ChangeListener<RolePOJO>() {
            @Override
            public void changed(ObservableValue<? extends RolePOJO> observable, RolePOJO oldValue, RolePOJO newValue) {
                if (newValue != null) {
                    currentRole = newValue.getRole();

                }
            }
        });

        /* ======================== Go Button (Switch to another user) ==================== */
        btnSwitchUserGo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
                CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
                CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();

                if (currentRole.equalsIgnoreCase("ROLE_OFFICE")) {
                    try {
                        Context.getInstance().currentProfile().getProfilePOJO().setRole("ROLE_OFFICE");
                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/enquiry/EnquiryMainView.fxml"));
                        Scene scene = new Scene(root1);
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        logger.error(ex.toString());
                    }
                } else if (currentRole.equalsIgnoreCase("ROLE_ADMIN")) {
                    try {
                        Context.getInstance().currentProfile().getProfilePOJO().setRole("ROLE_ADMIN");
                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/mainView/masterTableFXML.fxml"));
                        Scene scene = new Scene(root1);
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        logger.error(ex.toString());
                    }
                } else if (currentRole.equalsIgnoreCase("ROLE_COUNSELOR")) {
                    try {
                        Context.getInstance().currentProfile().getProfilePOJO().setRole("ROLE_COUNSELOR");
                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/counselor/FXMLCounselorMainView.fxml"));
                        Scene scene = new Scene(root1);
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        logger.error(ex.toString());
                    }
                } else if (currentRole.equalsIgnoreCase("ROLE_ACCOUNTANT")) {
                    try {
                        Context.getInstance().currentProfile().getProfilePOJO().setRole("ROLE_ACCOUNTANT");
                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/FXMLAccountsInbox.fxml"));
                        Scene scene = new Scene(root1);
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        logger.error(ex.toString());
                    }
                }
                Stage oldStage = (Stage) hyperAddSource.getScene().getWindow();
                oldStage.close();
            }
        });
        /* ======================== Add Age Reset ==================== */
        hyperAge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/age/FXMLAddAge.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE/RESET AGE");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });
        addEmployerMigrationSettings();
    }


    /**
     *
     */
    public void addEmployerMigrationSettings() {
        hyperSearchHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/home/FXMLSearchHome.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("SEARCH HOME");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setMaximized(true);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }

        });
        hyperJobTitle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/jobtitle/FXMLJobTitle.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE JOB TITLES");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }

        });
        hyperCurrency.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
//                    D:\SUMESH\CURRENT_IA\MODULE2\International_Academy\src\com\zs\ina\search\master\currency\FXMLCurrencyRemote.fxml
                //    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/currency/FXMLCurrency.fxml"));
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/currency/FXMLCurrencyRemote.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE CURRENCIES ");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        hyperFeeType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/feetype/FXMLFeeType.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE FEE TYPES ");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        hyperSubClass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/subclass/FXMLSubClass.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE SUB CLASSES ");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        hyperOccCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/occupation/category/FXMLOccupationCategory.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE OCCUPATION CATEGORY");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

    }

    /**
     *
     */
    public void addStdCodes() {
        hyperStdCodes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/stdisdcodes/FXMLStdCodes.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE STD CODES");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });
        hyperIsdCodes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/stdisdcodes/FXMLIsdCodes.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("ADD/REMOVE ISD CODES");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });

    }

    /**
     *
     */
    public void addEmailConfig() {
        hyperConfigEmail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/common/email/FXMLEmailConfig.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("Configure SMTP");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setResizable(false);
                    stageInbox.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });
    }

    /**
     *
     */
    private void addTasksView() {
        hyperViewTasks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stageInbox = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/task/FXMLTaskFullView.fxml"));
                    Scene scene = new Scene(root1);
                    stageInbox.setTitle("Task View");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.initStyle(StageStyle.UNDECORATED);
                    stageInbox.initOwner(branch.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     *
     */
    public void addEnquiryForwardData() {
        hyperEnqForwardPurpose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stageForward = new Stage();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/purpose/FXMLPurpose.fxml"));
                    root1 = (Parent) fxmlLoader.load();
                    stageForward.setTitle("Country & Locations ");
                    stageForward.initModality(Modality.APPLICATION_MODAL);
                    stageForward.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageForward.setScene(new Scene(root1));
                    stageForward.setResizable(false);
                    stageForward.initOwner(hyperCountryLocations.getScene().getWindow());
                    stageForward.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
    }

    /**
     *
     */
    public void loadCountryLocationsForm() {
        hyperCountryLocations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageLocation = new Stage();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/locations/FXMLLocations.fxml"));
                    root1 = (Parent) fxmlLoader.load();
                    stageLocation.setTitle("Country & Locations ");
                    stageLocation.initModality(Modality.APPLICATION_MODAL);
                    stageLocation.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageLocation.setScene(new Scene(root1));
                    stageLocation.setResizable(false);
                    stageLocation.initOwner(hyperCountryLocations.getScene().getWindow());
                    stageLocation.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();

                }

            }
        });
    }

    /**
     *
     */
    public void addSettingsHyperlinksEvents() {
        /* ====================== View Reports Hyperlink ====================== */
        hyperViewReports.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JasperPrint jasperPrint = null;
                String fxmlPath = "/com/zs/ina/admin/reports/view/FXMLReportViewer.fxml";
                Stage stageReports = new Stage();
                stageReports.setTitle("Reports");
                stageReports.initOwner(hyperViewReports.getScene().getWindow());
                Connection con = DBPool.getInstance().getConn();
                Map parameters = new HashMap();
                parameters.put("ReportTitle", "Report");
                parameters.put("Author", CUR_USERNAME);
                parameters.put("branch", CUR_BRANCH);
                parameters.put("dyna_query", CUR_BRANCH);

                try {
                    JasperReport jasperReport = JasperCompileManager.compileReport(FXMLReportViewerController.class.getResourceAsStream("IA_Test_Data_Report.jrxml"));
                    jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);

                } catch (JRException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                } finally {
                    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                    ClosingResourcesInDB.closingAllResources(con, new Statement[]{}, new ResultSet[]{}, stackTraceElements);
                }
                JRViewerFx viewer = new JRViewerFx(jasperPrint, JRViewerFxMode.REPORT_VIEW, stageReports, fxmlPath);
                try {
                    viewer.start(stageReports);
                } catch (Exception ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
         hyperAddDocument.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/documents/FXMLDocuments.fxml"));
                    Stage stageAddStatus = new Stage();
                    stageAddStatus.setTitle("Add New Document");
                    stageAddStatus.initModality(Modality.APPLICATION_MODAL);
                    stageAddStatus.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddStatus.setScene(new Scene(root));
                    stageAddStatus.setResizable(false);
                    stageAddStatus.initOwner(hyperEnquiryStatus.getScene().getWindow());
                    stageAddStatus.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperAddCountry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/country/FXMLCountrySearch.fxml"));
                    Stage stageAddCountry = new Stage();
                    stageAddCountry.setTitle("Add/Edit Country");
                    stageAddCountry.initModality(Modality.APPLICATION_MODAL);
                    stageAddCountry.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddCountry.setScene(new Scene(root));
                    stageAddCountry.setResizable(false);
                    stageAddCountry.initOwner(hyperEnquiryStatus.getScene().getWindow());
                    stageAddCountry.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperEmailTemplate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/emailtemplate/FXMLEmailTemplate.fxml"));
                    Stage stageEmailTemplate = new Stage();
                    stageEmailTemplate.setTitle("Create Email Template");
                    stageEmailTemplate.initModality(Modality.APPLICATION_MODAL);
                    stageEmailTemplate.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageEmailTemplate.setScene(new Scene(root));
                    stageEmailTemplate.setResizable(false);
                    stageEmailTemplate.initOwner(hyperEmailTemplate.getScene().getWindow());
                    stageEmailTemplate.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperAccomodation.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/accomodation/FXMLAccomodation.fxml"));
                    Stage stageAccomodation = new Stage();
                    stageAccomodation.setTitle("Add Accomodations");
                    stageAccomodation.initModality(Modality.APPLICATION_MODAL);
                    stageAccomodation.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAccomodation.setScene(new Scene(root));
                    stageAccomodation.setResizable(false);
                    stageAccomodation.initOwner(hyperAccomodation.getScene().getWindow());
                    stageAccomodation.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperAddIntake.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/intake/FXMLIntake.fxml"));
                    Stage stageIntake = new Stage();
                    stageIntake.setTitle("Add Intake");
                    stageIntake.initModality(Modality.APPLICATION_MODAL);
                    stageIntake.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageIntake.setScene(new Scene(root));
                    stageIntake.setResizable(false);
                    stageIntake.initOwner(hyperAddIntake.getScene().getWindow());
                    stageIntake.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        hyperEmailSignature.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/emailtemplate/signature/FXMLSalutationSign.fxml"));
                    Stage stageEmailTemplate = new Stage();
                    stageEmailTemplate.setTitle("Create Email Signature");
                    stageEmailTemplate.initModality(Modality.APPLICATION_MODAL);
                    stageEmailTemplate.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageEmailTemplate.setScene(new Scene(root));
                    stageEmailTemplate.setResizable(false);
                    stageEmailTemplate.initOwner(hyperEmailSignature.getScene().getWindow());
                    stageEmailTemplate.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperEnquiryStatus.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/enquirystatus/FXMLEnquiryStatus.fxml"));
                    Stage stageAddStatus = new Stage();
                    stageAddStatus.setTitle("Add Enquiry Status");
                    stageAddStatus.initModality(Modality.APPLICATION_MODAL);
                    stageAddStatus.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddStatus.setScene(new Scene(root));
                    stageAddStatus.setResizable(false);
                    stageAddStatus.initOwner(hyperEnquiryStatus.getScene().getWindow());
                    stageAddStatus.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperTrainingDuration.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/duration/FXMLCourseDuration.fxml"));
                    Stage stageTrainingDuration = new Stage();
                    stageTrainingDuration.setTitle("Add Training Course Duration");
                    stageTrainingDuration.initModality(Modality.APPLICATION_MODAL);
                    stageTrainingDuration.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageTrainingDuration.setScene(new Scene(root));
                    stageTrainingDuration.setResizable(false);
                    stageTrainingDuration.initOwner(hyperTrainingDuration.getScene().getWindow());
                    stageTrainingDuration.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        hyperAssignDocuments.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/documents/FXMLAssignDocuments.fxml"));
                    Stage stageAddStatus = new Stage();
                    stageAddStatus.setTitle("Assign Document Heads To Program");
                    stageAddStatus.initModality(Modality.APPLICATION_MODAL);
                    stageAddStatus.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddStatus.setScene(new Scene(root));
                    stageAddStatus.setResizable(false);
                    stageAddStatus.initOwner(hyperAssignDocuments.getScene().getWindow());
                    stageAddStatus.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperPaymentAmount.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/payment/FXMLHeadsAssign.fxml"));
                    Stage stageAddStatus = new Stage();
                    stageAddStatus.setTitle("Add Payment Amount");
                    stageAddStatus.initModality(Modality.APPLICATION_MODAL);
                    stageAddStatus.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddStatus.setScene(new Scene(root));
                    stageAddStatus.setResizable(false);
                    stageAddStatus.initOwner(hyperEnquiryStatus.getScene().getWindow());
                    stageAddStatus.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperMakeMandatory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/payment/FXMLMakeMandatory.fxml"));
                    Stage stageHeadMandatory = new Stage();
                    stageHeadMandatory.setTitle("Make Payment Head Mandatory For Enquiry Status");
                    stageHeadMandatory.initModality(Modality.APPLICATION_MODAL);
                    stageHeadMandatory.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageHeadMandatory.setScene(new Scene(root));
                    stageHeadMandatory.setResizable(false);
                    stageHeadMandatory.initOwner(hyperEnquiryStatus.getScene().getWindow());
                    stageHeadMandatory.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperRelationship.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage stageRelation = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/relationship/FXMLRelationship.fxml"));
                    Scene scene = new Scene(root1);
                    stageRelation.setTitle("New Relationship");
                    stageRelation.initModality(Modality.APPLICATION_MODAL);
                    stageRelation.setResizable(false);
                    stageRelation.initOwner(hyperConfigEmail.getScene().getWindow());
                    stageRelation.setScene(scene);
                    stageRelation.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageRelation.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });
        hyperQualificnLevel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/qualificationlevel/FXMLQualificationLevel.fxml"));
                    Stage stageAddStatus = new Stage();
                    stageAddStatus.setTitle("Add Qualification Level");
                    stageAddStatus.initModality(Modality.APPLICATION_MODAL);
                    stageAddStatus.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddStatus.setScene(new Scene(root));
                    stageAddStatus.setResizable(false);
                    stageAddStatus.initOwner(hyperQualificnLevel.getScene().getWindow());
                    stageAddStatus.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperSetRefreshSeconds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stageRefreshSeconds = new Stage();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/threaddelay/FXMLThreadDelay.fxml"));
                    root1 = (Parent) fxmlLoader.load();
                    stageRefreshSeconds.setTitle("Refresh Seconds");
                    stageRefreshSeconds.initModality(Modality.APPLICATION_MODAL);
                    stageRefreshSeconds.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageRefreshSeconds.setScene(new Scene(root1));
                    stageRefreshSeconds.setResizable(false);
                    stageRefreshSeconds.initOwner(hyperSetEnquiryLimit.getScene().getWindow());
                    stageRefreshSeconds.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperAddSource.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/sources/FXMLSources.fxml"));
                    Stage stageAddSource = new Stage();
                    stageAddSource.setTitle("Add Source");
                    stageAddSource.initModality(Modality.APPLICATION_MODAL);
                    stageAddSource.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddSource.setScene(new Scene(root));
                    stageAddSource.setResizable(false);
                    stageAddSource.initOwner(hyperAddSource.getScene().getWindow());
                    stageAddSource.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperAddMethod.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/methods/FXMLMethods.fxml"));
                    Stage stageAddMethod = new Stage();
                    stageAddMethod.setTitle("Add Method");
                    stageAddMethod.initModality(Modality.APPLICATION_MODAL);
                    stageAddMethod.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddMethod.setScene(new Scene(root));
                    stageAddMethod.setResizable(false);
                    stageAddMethod.initOwner(hyperAddMethod.getScene().getWindow());
                    stageAddMethod.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        hyperSetEnquiryLimit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/configpool/FXMLConfigPool.fxml"));
                    Stage stageLimit = new Stage();
                    stageLimit.setTitle("Set Enquiry Limit");
                    stageLimit.initModality(Modality.APPLICATION_MODAL);
                    stageLimit.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageLimit.setScene(new Scene(root));
                    stageLimit.setResizable(false);
                    stageLimit.initOwner(hyperSetEnquiryLimit.getScene().getWindow());
                    stageLimit.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperPaymentHead.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/payment/FXMLPaymentHeads.fxml"));
                    Stage stageLimit = new Stage();
                    stageLimit.setTitle("Add Payment Head");
                    stageLimit.initModality(Modality.APPLICATION_MODAL);
                    stageLimit.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageLimit.setScene(new Scene(root));
                    stageLimit.setResizable(false);
                    stageLimit.initOwner(hyperPaymentHead.getScene().getWindow());
                    stageLimit.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        hyperLocations.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/locations/country/FXMLCountry.fxml"));
                    Stage stageLocations = new Stage();
                    stageLocations.setTitle("Add Locations");
                    stageLocations.initModality(Modality.APPLICATION_MODAL);
                    stageLocations.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageLocations.setScene(new Scene(root));
                    stageLocations.setResizable(false);
                    stageLocations.initOwner(hyperLocations.getScene().getWindow());
                    stageLocations.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void handleInbox(ActionEvent event) {

        try {

            Stage stageInbox = new Stage();
            Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/inbox/FXMLAdminInboxView.fxml"));
            Scene scene = new Scene(root1);
            stageInbox.setTitle("Admin inbox");
            stageInbox.initModality(Modality.APPLICATION_MODAL);
            stageInbox.initOwner(branch.getScene().getWindow());
            stageInbox.setScene(scene);
            stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageInbox.setMaximized(true);
            stageInbox.show();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }

    }

    /**
     *
     */
    public void addLogout() {

        lblLogout.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage mainStage, currntStages;
                currntStages = (Stage) lblLogout.getScene().getWindow();
                mainStage = new Stage(StageStyle.DECORATED);
                currntStages.close();
                mainStage.setTitle("Login");
                LoginFormDAO.deleteLoginAudit(CUR_USERNAME);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/zs/ina/login/FXMLLogin.fxml"));
                    root.getStylesheets().add(getClass().getResource("/com/zs/ina/login/style.css").toExternalForm());

                } catch (Exception exception) {
                    logger.error(exception.toString());
                }
                synchronized (root) {
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ina_window_logo.png")));
                    mainStage.setTitle("International Academy");
                    mainStage.setScene(scene);
                    mainStage.setResizable(false);
                }
                mainStage.initStyle(StageStyle.UNDECORATED);
                mainStage.show();
            }
        });

    }

    @FXML
    private void handleBranch(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/branch/FXMLBranch.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageBranch = new Stage();
            stageBranch.setTitle("Branch");
            stageBranch.initModality(Modality.APPLICATION_MODAL);
            stageBranch.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageBranch.setScene(new Scene(root1));
            stageBranch.setResizable(false);
            stageBranch.initOwner(hyperBranch.getScene().getWindow());
            stageBranch.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handlePrivilage(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/privilege/FXMLPrivilages.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stagePrivilage = new Stage();
            stagePrivilage.setTitle("Privilage");
            stagePrivilage.initModality(Modality.APPLICATION_MODAL);
            stagePrivilage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stagePrivilage.setScene(new Scene(root1));
            stagePrivilage.setResizable(false);
            stagePrivilage.initOwner(hyperPrivilages.getScene().getWindow());
            stagePrivilage.show();

        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleRole(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/role/FXMLRole.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageRole = new Stage();
            stageRole.setTitle("Role");
            stageRole.initModality(Modality.APPLICATION_MODAL);
            stageRole.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageRole.setScene(new Scene(root1));
            stageRole.setResizable(false);
            stageRole.initOwner(hyperRole.getScene().getWindow());

            stageRole.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleLoginDetails(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/userlogin/FXMLLoginDetails.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage loginDetailsStage = new Stage();
            loginDetailsStage.setTitle("Login Details");
            loginDetailsStage.initModality(Modality.APPLICATION_MODAL);
            loginDetailsStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            loginDetailsStage.setScene(new Scene(root1));
            loginDetailsStage.setResizable(false);
            loginDetailsStage.setTitle("View / Create Users");
            loginDetailsStage.initOwner(hyperLocations.getScene().getWindow());
            loginDetailsStage.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void noticeBoardHandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/notice/FXMLNotice.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage noticeStage = new Stage();
            noticeStage.setTitle("Notice Board");
            noticeStage.initModality(Modality.APPLICATION_MODAL);
            noticeStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            noticeStage.setScene(new Scene(root1));
            noticeStage.setResizable(false);
            noticeStage.initOwner(hprOtherSkills.getScene().getWindow());
            noticeStage.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void agencyhandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/agency/FXMLAgency.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageAgency = new Stage();
            stageAgency.setTitle("Agency");
            stageAgency.initModality(Modality.APPLICATION_MODAL);
            stageAgency.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageAgency.setScene(new Scene(root1));
            stageAgency.setResizable(false);
            stageAgency.initOwner(agency.getScene().getWindow());

            stageAgency.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCourse(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/course/FXMLCourse.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageCourse = new Stage();
            stageCourse.setResizable(false);
            stageCourse.setTitle("Course");
            stageCourse.initModality(Modality.APPLICATION_MODAL);
            stageCourse.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageCourse.setScene(new Scene(root1));
            stageCourse.initOwner(hyperLocations.getScene().getWindow());
            stageCourse.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleQualificationDuration(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/qualificationduration/FXMLQualificationDuration.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageQualDuration = new Stage();
            stageQualDuration.setTitle("Qualification Duration");
            stageQualDuration.initModality(Modality.APPLICATION_MODAL);
            stageQualDuration.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageQualDuration.setScene(new Scene(root1));
            stageQualDuration.setResizable(false);
            stageQualDuration.initOwner(hyperLocations.getScene().getWindow());
            stageQualDuration.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void qualifiationFieldhandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/qualification/FXMLQualification.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageQualificn = new Stage();
            stageQualificn.setTitle("Qualification");
            stageQualificn.initModality(Modality.APPLICATION_MODAL);
            stageQualificn.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageQualificn.setScene(new Scene(root1));
            stageQualificn.setResizable(false);
            stageQualificn.initOwner(hyperLocations.getScene().getWindow());
            stageQualificn.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCountry(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/country/FXMLCountry.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageCountry = new Stage();
            stageCountry.setTitle("Country");
            stageCountry.initModality(Modality.APPLICATION_MODAL);
            stageCountry.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageCountry.setScene(new Scene(root1));
            stageCountry.setResizable(false);
            stageCountry.initOwner(hyperLocations.getScene().getWindow());
            stageCountry.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleExperiance(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/experience/FXMLExperience.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageExperience = new Stage();
            stageExperience.setTitle("Experience Managment");
            stageExperience.initModality(Modality.APPLICATION_MODAL);
            stageExperience.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageExperience.setScene(new Scene(root1));
            stageExperience.setResizable(false);
            stageExperience.initOwner(hyperLocations.getScene().getWindow());
            stageExperience.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleinstitute(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/institute/FXMLInstitute.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageInstitute = new Stage();
            stageInstitute.setTitle("Institute");
            stageInstitute.initModality(Modality.APPLICATION_MODAL);
            stageInstitute.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageInstitute.setScene(new Scene(root1));
            stageInstitute.setResizable(false);
            stageInstitute.initOwner(hyperLocations.getScene().getWindow());
            stageInstitute.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleJobType(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/jobtype/FXMLJobtype.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageJobType = new Stage();
            stageJobType.setTitle("Job Type Management");
            stageJobType.initModality(Modality.APPLICATION_MODAL);
            stageJobType.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageJobType.setScene(new Scene(root1));
            stageJobType.setResizable(false);
            stageJobType.initOwner(hyperLocations.getScene().getWindow());
            stageJobType.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void professionHandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/profession/FXMLProfession.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageProfession = new Stage();
            stageProfession.setTitle("Profession");
            stageProfession.initModality(Modality.APPLICATION_MODAL);
            stageProfession.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageProfession.setScene(new Scene(root1));
            stageProfession.setResizable(false);
            stageProfession.initOwner(hyperLocations.getScene().getWindow());
            stageProfession.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void salaryhandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/salary/FXMLSalary.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageSalary = new Stage();
            stageSalary.setTitle("Salary Management");
            stageSalary.initModality(Modality.APPLICATION_MODAL);
            stageSalary.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageSalary.setScene(new Scene(root1));
            stageSalary.setResizable(false);
            stageSalary.initOwner(hyperLocations.getScene().getWindow());
            stageSalary.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void languageHandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/language/FXMLLanguage.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageLanguage = new Stage();
            stageLanguage.setTitle("Language");
            stageLanguage.initModality(Modality.APPLICATION_MODAL);
            stageLanguage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageLanguage.setScene(new Scene(root1));
            stageLanguage.setResizable(false);
            stageLanguage.initOwner(hyperLocations.getScene().getWindow());
            stageLanguage.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void otherTestHandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/othertest/FXMLOtherTest.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageOtherTest = new Stage();
            stageOtherTest.setTitle("Other Test");
            stageOtherTest.initModality(Modality.APPLICATION_MODAL);
            stageOtherTest.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageOtherTest.setScene(new Scene(root1));
            stageOtherTest.setResizable(false);
            stageOtherTest.initOwner(hyperLocations.getScene().getWindow());
            stageOtherTest.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void scoreHandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/score/FXMLScore.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageScore = new Stage();
            stageScore.setTitle("Score");
            stageScore.initModality(Modality.APPLICATION_MODAL);
            stageScore.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageScore.setScene(new Scene(root1));
            stageScore.setResizable(false);
            stageScore.initOwner(hyperLocations.getScene().getWindow());
            stageScore.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void timinghandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/timing/FXMLTiming.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageTiming = new Stage();
            stageTiming.setTitle("Timing");
            stageTiming.initModality(Modality.APPLICATION_MODAL);
            stageTiming.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageTiming.setScene(new Scene(root1));
            stageTiming.setResizable(false);
            stageTiming.initOwner(hyperLocations.getScene().getWindow());
            stageTiming.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handlemigrationCategory(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/migrationcategory/FXMLMigrationCategory.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageEmigration = new Stage();
            stageEmigration.setTitle("Migration Category");
            stageEmigration.initModality(Modality.APPLICATION_MODAL);
            stageEmigration.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageEmigration.setScene(new Scene(root1));
            stageEmigration.setResizable(false);
            stageEmigration.initOwner(hyperLocations.getScene().getWindow());
            stageEmigration.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBatchTiming(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/batchtime/FXMLBatchTiming.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageEmigration = new Stage();
            stageEmigration.setTitle("Batch Timing");
            stageEmigration.initModality(Modality.APPLICATION_MODAL);
            stageEmigration.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageEmigration.setScene(new Scene(root1));
            stageEmigration.setResizable(false);
            stageEmigration.initOwner(hyperLocations.getScene().getWindow());
            stageEmigration.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCourseType(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/coursetype/FXMLCourseType.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageCourseType = new Stage();
            stageCourseType.setTitle("Course Type");
            stageCourseType.initModality(Modality.APPLICATION_MODAL);
            stageCourseType.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageCourseType.setScene(new Scene(root1));
            stageCourseType.setResizable(false);
            stageCourseType.initOwner(hyperLocations.getScene().getWindow());
            stageCourseType.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleExamBoard(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/examboard/FXMLExamBoard.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageExamBoard = new Stage();
            stageExamBoard.setTitle("Exam Board");
            stageExamBoard.initModality(Modality.APPLICATION_MODAL);
            stageExamBoard.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageExamBoard.setScene(new Scene(root1));
            stageExamBoard.setResizable(false);
            stageExamBoard.initOwner(hyperLocations.getScene().getWindow());
            stageExamBoard.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }

    }

    @FXML
    private void handleUniversityLink(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/master/university/FXMLUniversity.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageUniversityLink = new Stage();
            stageUniversityLink.setTitle("University");
            stageUniversityLink.initModality(Modality.APPLICATION_MODAL);
            stageUniversityLink.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageUniversityLink.setScene(new Scene(root1));
            stageUniversityLink.setResizable(false);
            stageUniversityLink.initOwner(hyperLocations.getScene().getWindow());
            stageUniversityLink.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleSuggestedCourseLink(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/sugestedcourse/FXMLLogin.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageSuggestedCourse = new Stage();
            stageSuggestedCourse.setTitle("University");
            stageSuggestedCourse.initModality(Modality.APPLICATION_MODAL);
            stageSuggestedCourse.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageSuggestedCourse.setScene(new Scene(root1));
            stageSuggestedCourse.setResizable(false);
            stageSuggestedCourse.initOwner(hyperLocations.getScene().getWindow());
            stageSuggestedCourse.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleTechnicalSkills(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/technicalskills/technicalSkillsFXML.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageTechnicalSkills = new Stage();
            stageTechnicalSkills.setTitle("Technical Skills");
            stageTechnicalSkills.initModality(Modality.APPLICATION_MODAL);
            stageTechnicalSkills.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageTechnicalSkills.setScene(new Scene(root1));
            stageTechnicalSkills.setResizable(false);
            stageTechnicalSkills.initOwner(hyperLocations.getScene().getWindow());
            stageTechnicalSkills.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleOtherSkills(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/otherskills/OtherSkillsFXML.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageOtherSkills = new Stage();
            stageOtherSkills.setTitle("Other Skills");
            stageOtherSkills.initModality(Modality.APPLICATION_MODAL);
            stageOtherSkills.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageOtherSkills.setScene(new Scene(root1));
            stageOtherSkills.setResizable(false);
            stageOtherSkills.initOwner(hyperLocations.getScene().getWindow());
            stageOtherSkills.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    private void admissionTesthandle(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/admissiontest/AdmissionTestFXML.fxml"));
            root1 = (Parent) fxmlLoader.load();
            Stage stageAdmissionTest = new Stage();
            stageAdmissionTest.setTitle("Admission Test");
            stageAdmissionTest.initModality(Modality.APPLICATION_MODAL);
            stageAdmissionTest.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stageAdmissionTest.setScene(new Scene(root1));
            stageAdmissionTest.setResizable(false);
            stageAdmissionTest.initOwner(hyperLocations.getScene().getWindow());
            stageAdmissionTest.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }


}

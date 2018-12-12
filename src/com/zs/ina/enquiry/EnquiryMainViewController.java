/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry;

import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryDAO;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryIMPl;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryPOJO;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.locations.dao.LocationsDAO;
import com.zs.ina.admin.master.locations.dao.LocationsIMPL;
import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.model.AssesmentMigrateBEAN;
import com.zs.ina.assesment.model.AssesmentTrainingBEAN;
import com.zs.ina.assesment.model.AssesmentWorkBEAN;
import com.zs.ina.assesment.model.WorktExperienceBEAN;
import com.zs.ina.assesment.model.StudySuggestedRequiredBEAN;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.common.ShowProgress;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.ContactNumberBEAN;
import com.zs.ina.enquiry.dao.ContactNumbersDAO;
import com.zs.ina.enquiry.dao.ContactNumbersIMPL;
import com.zs.ina.admin.master.retrieve.MasterCountryStateDistrictDAO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.admin.master.userlogin.dao.LoginBEAN;
import com.zs.ina.admin.master.userlogin.dao.LoginIMPL;
import com.zs.ina.admin.master.userlogin.dao.LoginSERVICE;
import com.zs.ina.admin.master.userlogin.dao.RolePOJO;
import com.zs.ina.agency.FXMLAgencyController;
import com.zs.ina.common.inbox.retrieve.InboxCountsBEAN;
import com.zs.ina.assesment.model.AssessmentStatusBEAN;
import com.zs.ina.assesment.status.dao.AssessmentStatusDAO;
import com.zs.ina.assesment.status.dao.AssessmentStatusIMPL;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.email.MailMail;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.common.email.dao.MailSentDAO;
import com.zs.ina.common.inbox.commonpool.CommonPoolService;
import com.zs.ina.common.inbox.retrieve.InboxRetrievalService;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.enquiry.dao.EnquiryDetailsSearchPOJO;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO.StaffCountPOJO;
import com.zs.ina.enquiry.dao.PhoneSearchPOJO;
import com.zs.ina.enquiry.dao.ServicesBEAN;
import com.zs.ina.enquiry.dao.ServicesDAO;
import com.zs.ina.enquiry.dao.ServicesIMPL;
import com.zs.ina.enquiry.empstatus.dao.EmpStatusBEAN;
import com.zs.ina.enquiry.empstatus.dao.EmployeeStatusDAO;
import com.zs.ina.enquiry.followup.FXMLFollowUpFulllviewController;
import com.zs.ina.enquiry.search.dao.CounselorWorkCount;
import com.zs.ina.enquiry.search.dao.EnquirySearchAssetsPOJO;
import com.zs.ina.enquiry.search.dao.EnquirySearchDAO;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.notifications.dao.NoticePOJO;
import com.zs.ina.notifications.dao.NotificationsDAO;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.scene.control.LocalDateTimePicker;
import jfxtras.scene.control.LocalTimePicker;
import jidefx.scene.control.hints.ListDataIntelliHints;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.w3c.dom.Element;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.reminder.FXMLRemainderController;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderBEAN;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderDAO;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderIMPL;
import com.zs.ina.enquiry.empstatus.FXMLEmployeStatusFullController;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.ina.login.dao.LoginFormDAO;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.time.LocalDate;
import java.util.Set;
import javafx.application.Platform;
import javafx.concurrent.Service;
import static javafx.concurrent.Worker.State.SCHEDULED;
import javafx.scene.control.DateCell;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * FXML Controller class
 *
 * @author sumesh
 */
public class EnquiryMainViewController implements Initializable {

    static Logger logger = Logger.getLogger(EnquiryMainViewController.class);
    String[] namePhone = new String[2];
    private ObservableList<CounselorWorkCount> masterDataCounselor = FXCollections.observableArrayList();
    @FXML
    private TableView<CounselorWorkCount> tblCounselorStatus;
    @FXML
    private TableColumn<CounselorWorkCount, ?> clmCounselorName;
    @FXML
    private TableColumn<CounselorWorkCount, ?> clmPending;
    @FXML
    private TableColumn<CounselorWorkCount, ?> clmAssesed;
    @FXML
    private ComboBox<String> cmbSource;
    @FXML
    private ComboBox<String> cmbBranch;
    @FXML
    private ComboBox<CounselorsListPOJO> cmbAssign;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField txtStdIsd;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextField txtContactEmail;
    @FXML
    private TextField txtContactName;
    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<String> cmbState;
    @FXML
    private ComboBox<String> cmbDistrict;
    @FXML
    private VBox vboxCalendar;
    PopOver popOver = new PopOver();
    ObservableList<String> duration = FXCollections.observableArrayList();

    /**
     *
     */
    final public String BRAND_NAME = "INA";
    Timeline notifyTimer;
    private String CUR_USERNAME = null;
    private String CUR_BRANCH = null;
    ObservableList sassign = FXCollections.observableArrayList();
    ObservableList branch = FXCollections.observableArrayList();
    ObservableList source = FXCollections.observableArrayList();
    ObservableList country = FXCollections.observableArrayList();
    ObservableList state = FXCollections.observableArrayList();
    ObservableList sample = FXCollections.observableArrayList();
    List<String> words = new ArrayList<>();
    List<String> districtWords = new ArrayList<>();
    List<EnquiryDetailsSearchPOJO> enquiryList = new ArrayList<>();
    CheckBox[] chkCounselor = new CheckBox[100];
    ObservableList courseLevels = FXCollections.observableArrayList();
    ObservableList districs = FXCollections.observableArrayList();
    ObservableList functionalAreas = FXCollections.observableArrayList();
    LocalTimePicker localTimePicker;
    List<String> counseleorNames = new ArrayList<>();
    static Task sendData;
    final int dataCount = 1;
    static LocalTime localTime;
    PopOver popuser = new PopOver();
    ObservableList stateData = FXCollections.observableArrayList();
    ObservableList districtData = FXCollections.observableArrayList();
    final Rating rating = new Rating();
    ObservableList obsEnquiryMethods = FXCollections.observableArrayList("Local", "Direct", "Walk in");
    final ContextMenu passValidator = new ContextMenu();
    EnquiryDetailsSearchPOJO detailsSearchPOJO = new EnquiryDetailsSearchPOJO();

    String today;
    static int PRIORITY = 0;
    @FXML
    private HBox hboxEmail;
    @FXML
    private Button cmbEnquiryDetails;
    @FXML
    private TableView<StaffCountPOJO> tblEnquiryEntryStat;
    @FXML
    private TableColumn<StaffCountPOJO, ?> clmEstatName;
    @FXML
    private TableColumn<StaffCountPOJO, ?> clmEstatStatus;
    @FXML
    private TableColumn<StaffCountPOJO, ?> clmEstatFake;
    @FXML
    private VBox vboxNoticeBoard;
    Map<String, Integer> counterMap = new HashMap<>();
    @FXML
    private Label lblUserdata;
    @FXML
    private Label lblLogout;
    LocalDateTimePicker dateTimePicker;
    ObservableList studyFunctionalAreas = FXCollections.observableArrayList();
    ObservableList workFunctionalAreas = FXCollections.observableArrayList();
    ObservableList migrationFunctionalAreas = FXCollections.observableArrayList();
    ObservableList TraingingFunctionalAreas = FXCollections.observableArrayList();
    ObservableList studyCourseLevel = FXCollections.observableArrayList();
    ObservableList workCourseLevel = FXCollections.observableArrayList();
    ObservableList migrationCourseLevel = FXCollections.observableArrayList();
    ObservableList trainingCourseLevel = FXCollections.observableArrayList();
    ObservableList programFieldList = FXCollections.observableArrayList();

    /* =========== for cmbAssign ============*/
    Map<String, String> mapCommonPoolConfig = new HashMap<>();
    Map<String, String> mapFlagEnquiryStatus = new HashMap<>();

    @FXML
    private ImageView starImageView;
    @FXML
    private Label lblMessageHeader;
    private int TIME_CALC;
    /// auto completion lists
    List<String> conatctNumbers = new ArrayList<>();
    List<String> contactEmails = new ArrayList<>();
    List<String> contactNames = new ArrayList<>();
    List<PhoneSearchPOJO> listContactnumbers = new ArrayList<>();
    @FXML
    private Button btnAppointmentSchedule;
    @FXML
    private ComboBox<String> empStatusBranchCombo;
    @FXML
    private TableView<EmpStatusBEAN> empStatusTable;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusName;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusPosition;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusStatus;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusContactNo;
    @FXML
    private TableColumn<EmpStatusBEAN, ?> empStatusExtNo;
    String selectedBranch = null;
    EnquirySearchAssetsPOJO searchAssetsPOJO = new EnquirySearchAssetsPOJO();
    @FXML
    private TextField txtSearchUpdate;
    @FXML
    private ComboBox<String> cmbMethod;
    List<String> stdCodes = new ArrayList<>();
    @FXML
    private Accordion leftAccordion;
    @FXML
    private TitledPane tPanEnqEntryStatus;
    @FXML
    private TitledPane tPaneNoticeBoard;
    @FXML
    private TextField txtContactNumber2;
    String searchCountry = "";
    String searchState = "";
    String searchDistrict = "";
    String searchProgramLevel = "";
    @FXML
    private TextField txtStdIsd2;
    @FXML
    private ComboBox<String> cmbDepartment;
    ObservableList department = FXCollections.observableArrayList();
    @FXML
    private TextField txtRemarks;
    String CURR_COUNSELOR = null;
    int CONTACT_LIMIT = 4;
    boolean ENABLE_FOLLOWUP = true;
    /*============================= Contact Dynamic Controlls ==================================== */
    @FXML
    private Button btnContactPlus;
    @FXML
    private Button btnContactClose;
    @FXML
    private GridPane gridContactNumber;
    /* ======================== Contact List of bean ==================== */
    List<ContactNumberBEAN> contactNumberBEANList = new ArrayList<>();
    @FXML
    private GridPane gridServices;
    @FXML
    private GridPane gridTraining;
    @FXML
    private GridPane gridAdministration;
    @FXML
    private Button btnEnquirySubmit;
    @FXML
    private Label lblAppStatus;
    @FXML
    private HBox hboxRemarks;

    /* ======================== Trainig Dynamic list bean ==================== */
    List<AssesmentTrainingBEAN> trainingBeanList = new ArrayList<>();
    ObservableList obsListTraining = FXCollections.observableArrayList();
    ObservableList obsListBatches = FXCollections.observableArrayList();

    /* ======================== Services list ==================== */
    List<ServicesBEAN> servicesBEANList = new ArrayList<>();
    ObservableList obsListServices = FXCollections.observableArrayList("Study", "Work", "Migrate");
    ObservableList obsListcountry = FXCollections.observableArrayList("Australia", "Canada", "India", "Ireland", "Spain", "UAE", "UK", "Ukrain", "USA");
    LocationsDAO locationsDAO = new LocationsIMPL();
    CountryDAO countryDAO = new CountryIMPL();
    /* ======================== Assess Now Option ==================== */
    Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = new HashMap<>();
    CounselorDetailsBEAN enquiryDetailsBEAN = new CounselorDetailsBEAN();
    @FXML
    private Button btnEnquiryStatusFull;
    @FXML
    private Button btnAssessNow;
    @FXML
    private Label lblTitleEnqReport;
    @FXML
    private Label lblTitleFollowUp;
    @FXML
    private Label lblTitleAppPending;
    ForwardHistoryDAO forwardHistoryDAO = new ForwardHistoryIMPl();
    ShowPopupMessages showErrors = new ShowPopupMessages();
    ContactNumbersDAO contactNumbersDAO = new ContactNumbersIMPL();
    String message = "";
    String service = "";
    private Executor executor;
    @FXML
    private Button cmbCreateTask;
    @FXML
    private Button cmbViewLog;
    AssessmentStatusDAO assessmentStatusDAO = new AssessmentStatusIMPL();
    @FXML
    private Button btnSpotAssessment;
    @FXML
    private Label lblMessageHeader1;
    @FXML
    private Tooltip dsfd;
    @FXML
    private ImageView imgviewFullEmpStatus;
    @FXML
    private ImageView imgviewFullEmpEntry;
    @FXML
    private Button btnEnquiryReport;
    @FXML
    private Button btnAppointmentPending;
    @FXML
    private Button btnFollowUpPending;
    @FXML
    private Button btnSpotRegistration;
    @FXML
    private ComboBox<RolePOJO> cmbSwitchUser;
    @FXML
    private Button btnSwitchUserGo;

    private String CUR_ROLE;
    private int folloupsize;
    ReminderDAO reminderDAO = new ReminderIMPL();
    Service serviceReminder = null;
    Stage reminderStage = new Stage();
    ObservableList<ReminderBEAN> listReminders = FXCollections.observableArrayList();
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    Service serviceStaffCounts = null;
    Service serviceCounselorStatusCounts = null;
    Service serviceNotice = null;
    Service serviceEmployeeStatusCounts = null;
    List<EmpStatusBEAN> empStatusList = new ArrayList<>();
    ObservableList<EnquirySatusPOJO> obsEnquiryStatus = FXCollections.observableArrayList();
    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();
    List<Task<List<PhoneSearchPOJO>>> tasksPhoneAllRunning = new ArrayList<>();
    List<Task<List<EnquiryDetailsSearchPOJO>>> tasksSearchAllRunning = new ArrayList<>();
    Map<String, ObservableList<String>> mapEnquiryMethod = new HashMap<>();
    String DEFAULT_ENQUIRY_METHOD = "";
    @FXML
    private Hyperlink hyperAddAgent;

    /* ========== email send ===== */
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    MailSentDAO mailSentDAO = springAppContext.getBean(MailSentDAO.class);
    MailSentPOJO mailSentPOJO = new MailSentPOJO();
    List<MailSentPOJO> mailSentPOJOs = new ArrayList<>();
    String signature = "";
    String messages = "";
    String emailSignature = "";
    String location = "";
    StringBuilder sbProgramReqd = new StringBuilder();
    MailMail mailMail = springAppContext.getBean(MailMail.class);
    StringBuilder sbTrainingReqd = new StringBuilder();
    StringBuilder sbProgramReqdUpdt = new StringBuilder();
    StringBuilder sbTrainingReqdUpdt = new StringBuilder();

    ObservableList<RolePOJO> rolesList = FXCollections.observableArrayList();
    LoginSERVICE loginSERVICE = new LoginSERVICE(new LoginIMPL());
    LoginBEAN loginBEAN = new LoginBEAN();
    String currentRole = "";

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
                Stage oldStage = (Stage) btnEnquirySubmit.getScene().getWindow();
                oldStage.close();
            }
        });
        /* ======================== Set Jump To Char (items view based on keypress) ==================== */
        ComboBoxJumpToChar.jumpToChar(cmbState);

        ComboBoxJumpToChar.jumpToChar(cmbDistrict);

        ComboBoxJumpToChar.jumpToChar(cmbCountry);

        ComboBoxJumpToChar.jumpToChar(cmbDepartment);

        ComboBoxJumpToChar.jumpToChar(cmbBranch);

        ComboBoxJumpToChar.jumpToChar(cmbMethod);

        ComboBoxJumpToChar.jumpToChar(cmbSource);

        ComboBoxJumpToChar.jumpToChar(empStatusBranchCombo);

        /* ========================  create executor that uses daemon threads: ==================== */
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        /* ======================== Bind All Enquiry Controls ==================== */

        bindAllEnquiryControls();
        enquiryDetailsBEAN.setStaffBranch(CUR_BRANCH);
        enquiryDetailsBEAN.setStaffUsername(CUR_USERNAME);
        cmbBranch.getSelectionModel().select(CUR_BRANCH);
        /* ======================== End Bind All Enquiry Controls ==================== */

        lblUserdata.setText(CUR_BRANCH + "( Branch )");
        lblLogout.setText("Log Out (" + CUR_USERNAME + ")");
        //   addCounselorStatus();
//        clockVBox.getChildren().addAll(makeAnalogueClock(), new DigitalClock());
        initializeComboBoxes();
        addComboBoxEvents();
        searchAssetsPOJO.setDistrics(districs);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        today = dateFormat.format(date);
        leftAccordion.setExpandedPane(tPaneNoticeBoard);
//        leftAccordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
//
//            @Override
//            public void changed(ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) {
//                if (newValue != null) {
//                    System.out.println("Accordion Expanded Property");
//                    boolean isExpanded = false;
//                    for (TitledPane pane : leftAccordion.getPanes()) {
//                        if (!pane.isExpanded()) {
//                            isExpanded = true;
//                        }
//                    }
//                    if (isExpanded) {
//                        leftAccordion.setExpandedPane(tPaneNoticeBoard);
//                    }
//                }
//            }
//        });

        passValidator.setAutoHide(false);
        autocompleteMobileCodes();
        autocompleteLandPhoneCodes();
        addChangeEvents();
        addNoticeWindow();
        addLogout();
        initializeAutocompletionData();
        autoCompletion();
        addGlobalTimer();
        addFollowUpRegister();
        selectedBranch = CUR_BRANCH;
//        addEmpStatusLoader(CUR_BRANCH);
        addAppointmentSchedule();
        stdCodes = RetrieveStaticMasterDAO.getStdCodes();
        /* ========================== Hiding Application Status and  Remarks Area  ======================== */
        // hboxStatusRemarks.setVisible(false);
        /* ======================== Set Follow Up DateTimePicker ==================== */

 /* ========================== End Hiding Application Status and  Remarks Area  ======================== */
 /* ======================== Start Dynamic Contact Numbers ==================== */
        initDynamicContactControlls();
        /* ======================== End Dynamic Contact Numbers ====================     */
 /* ======================== Start Dynamic Service Controls ==================== */
        initDynamicServiceControls();
        /* ======================== End Dynamic Service Controls ==================== */

 /* ======================== Start Dynamic Training Details ==================== */
        initDynamicTrainingControls();
        /* ======================== End Dynamic Training Details ==================== */

 /* ======================== Add Enquiry Submit ==================== */
        submitEnquiryForm();

        /* ======================== Hide Application Status and Remarks ==================== */
        showHideStatusRemarksArea(true);
        /* ====================== Automatic Align Graphics of  Titled Pane  ====================== */
//        Node titleRegion = tpaneEnquiryReport.lookup(".title");
//        javafx.geometry.Insets padding =vboxCalendar.getPadding();
//        double graphicWidth = tpaneEnquiryReport.getGraphic().getLayoutBounds().getWidth();
//        double arrowWidth = titleRegion.lookup(".arrow-button").getLayoutBounds().getWidth();
//        double labelWidth = titleRegion.lookup(".text").getLayoutBounds().getWidth();
//        double nodesWidth = graphicWidth + padding.getLeft() + padding.getRight() + arrowWidth + labelWidth;
//        tpaneEnquiryReport.graphicTextGapProperty().bind(tpaneEnquiryReport.widthProperty().subtract(nodesWidth));

        /* ====================== Show Enquiry Report ====================== */
        btnEnquiryReport.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    notifyTimer.pause();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/enquiry/search/FXMLEnquirySearch.fxml"));
                    root1.getStylesheets().add("/com/zs/ina/enquiry/search/fxmlenquirysearch.css");
                    Scene secondScene = new Scene(root1, 1015, 400);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Enquiry Reports ");
                    secondStage.setScene(secondScene);
                    secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    secondStage.initModality(Modality.APPLICATION_MODAL);
                    secondStage.initOwner(txtStdIsd.getScene().getWindow());
                    secondStage.initStyle(StageStyle.UNIFIED);
                    secondStage.setMaximized(true);
                    secondStage.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            notifyTimer.play();
                            /* ========================  create executor that uses daemon threads: ==================== */
                            executor = Executors.newCachedThreadPool(runnable -> {
                                Thread t = new Thread(runnable);
                                t.setDaemon(true);
                                return t;
                            });
                            /* ====================== Reload Staff Status ====================== */
                        }
                    });
                    secondStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    logger.error(ex.toString());
                }

            }

        });

        /* ====================== Change Cursor Left Side Panel ====================== */
//        btnFollowUpFullView.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
//        btnEnquiryReportFull.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
        btnEnquiryStatusFull.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
//        btnAppointPending.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
        //      btnLeftMsgFullView.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
        //  btnEmpStatusFull.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
        /* ======================== Create Or Show Tasks ==================== */
        addUserTasks();
        /* ====================== Bind the view of create task btn ====================== */
        cmbCreateTask.disableProperty().bind(enquiryDetailsBEAN.enquiryIDProperty().isEmpty());
        /* ====================== Add Spot Assessment ====================== */
        addSpotAssessmentRegistration();
        /* ====================== Add Reminder ====================== */
        addRemindersServices();
        /* ======================== Add Service For Reloading All Data ==================== */
        addStaffCountServices();
        /* ======================== Add Service For Loading All Counselor Counts ==================== */
        addEmployeeStatusServices(CUR_BRANCH);
        /* ======================== Load Counselor Status Service ==================== */
        addCounselorStatusService();
        /* ======================== Load Notice Service ==================== */
        addNoticeService();
        lblAppStatus.setText("");
        addAgencyOption();
    }

    /**
     *
     */
    public void addAgencyOption() {
        hyperAddAgent.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    popOver = new PopOver();
                    Context.getInstance().currentProfile().setPopOver(popOver);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/agency/FXMLAgencyOffice.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLAgencyController controller = (FXMLAgencyController) loader.getController();
                    Stage stageAgency = new Stage();
                    stageAgency.setTitle("Agency");
                    stageAgency.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAgency.setScene(new Scene(root));
                    stageAgency.setResizable(false);
                    stageAgency.initOwner(txtRemarks.getScene().getWindow());
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.prefWidth(200);
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(root);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(true);
                    popOver.setDetached(true);
                    popOver.setTitle("Add New Agent");
                    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
                    popOver.show(hyperAddAgent, hyperAddAgent.localToScreen(0, 0).getX() - 2, hyperAddAgent.localToScreen(0, 0).getY() + 10);
                    popOver.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            loadcmbSource();
                        }

                    });
                } catch (IOException ex) {
                    ex.printStackTrace();
                    logger.error(ex);
                }

            }
        });
    }
    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        @Override
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
            };
        }
    };

    /**
     *
     */
    public void addNoticeService() {

        serviceNotice = new Service<  Map<String, Integer>>() {
            @Override
            protected Task createTask() {
                return new Task<  Map<String, Integer>>() {
                    @Override
                    protected Map<String, Integer> call() throws InterruptedException {
                        updateMessage("Searching for reminders....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(300);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return NotificationsDAO.checkCounter(CUR_USERNAME);
                    }
                };
            }
        };
        serviceNotice.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                switch (newValue) {
                    case SCHEDULED:
                        break;
                    case READY:
                    case RUNNING:
                        System.out.println("Running  ...");
                        break;
                    case SUCCEEDED:
                        System.out.println("Succededd");
                        System.out.println("Counselor data :: " + serviceNotice.getValue());
                        Map<String, Integer> counterMap = (Map<String, Integer>) serviceNotice.getValue();
                        if (counterMap != null) {
                            if (counterMap.get("notice") != null) {
                                if ((counterMap.get("notice") > 0)) {
                                    vboxNoticeBoard.getChildren().clear();
                                    addNoticeWindow();
                                    NotificationsDAO.resetCounter(CUR_USERNAME, "notice");
                                }
                            }
                        }

                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        serviceNotice.start();

    }

    /**
     *
     */
    public void addCounselorStatusService() {

        serviceCounselorStatusCounts = new Service<List<CounselorWorkCount>>() {
            @Override
            protected Task createTask() {
                return new Task<List<CounselorWorkCount>>() {
                    @Override
                    protected List<CounselorWorkCount> call() throws InterruptedException {
                        updateMessage("Searching for reminders....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(300);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return EnquirySearchDAO.getCounselorWorkDetails(CUR_BRANCH);
                    }
                };
            }
        };
        serviceCounselorStatusCounts.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                switch (newValue) {
                    case SCHEDULED:
                        break;
                    case READY:
                    case RUNNING:
                        System.out.println("Running  ...");
                        break;
                    case SUCCEEDED:
                        System.out.println("Succededd");
                        System.out.println("Counselor data :: " + serviceCounselorStatusCounts.getValue());
                        List<CounselorWorkCount> resList = (List<CounselorWorkCount>) serviceCounselorStatusCounts.getValue();
                        loadCounselorStatus(resList);
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        serviceCounselorStatusCounts.start();
    }

    /**
     *
     * @param resList
     */
    public void loadCounselorStatus(List<CounselorWorkCount> resList) {
        System.out.println("Test :: " + resList.toString());
        tblCounselorStatus.getItems().clear();
        for (CounselorWorkCount pojo : resList) {
            masterDataCounselor.add(pojo);
        }
        clmCounselorName.setCellValueFactory(
                new PropertyValueFactory<>("cname"));

        clmPending.setCellValueFactory(
                new PropertyValueFactory<>("pendingCount"));
        clmAssesed.setCellValueFactory(
                new PropertyValueFactory<>("assesed"));

        tblCounselorStatus.getItems().setAll(resList);
        //make equal width columns
        tblCounselorStatus.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newWidth = newValue.doubleValue() / 4;
                clmCounselorName.setPrefWidth(newWidth);
                clmAssesed.setPrefWidth(newWidth);
                clmPending.setPrefWidth(newWidth);
            }
        });
    }

    /**
     *
     * @param branchName
     */
    public void addEmployeeStatusServices(String branchName) {
        if (empStatusBranchCombo.getItems().contains(branchName)) {
            empStatusBranchCombo.getSelectionModel().clearAndSelect(empStatusBranchCombo.getItems().indexOf(branchName));
        }
        empStatusBranchCombo.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedBranch = newValue;
                //  addEmpStatusLoader(newValue);
                empStatusList = EmployeeStatusDAO.getEmpStatus(selectedBranch);
                loadEmployeeCounts(empStatusList);
            }
        });
        serviceEmployeeStatusCounts = new Service< List<EmpStatusBEAN>>() {
            @Override
            protected Task createTask() {
                return new Task< List<EmpStatusBEAN>>() {
                    @Override
                    protected List<EmpStatusBEAN> call() throws InterruptedException {
                        updateMessage("Searching for reminders....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(300);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return EmployeeStatusDAO.getEmpStatus(selectedBranch);
                    }
                };
            }
        };
        serviceEmployeeStatusCounts.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                switch (newValue) {
                    case SCHEDULED:
                        break;
                    case READY:
                    case RUNNING:
                        System.out.println("Running  ...");
                        break;
                    case SUCCEEDED:
                        System.out.println("Succededd");
                        System.out.println("test data :: " + serviceEmployeeStatusCounts.getValue());
                        empStatusList = (List<EmpStatusBEAN>) serviceEmployeeStatusCounts.getValue();
                        loadEmployeeCounts(empStatusList);
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        serviceEmployeeStatusCounts.start();

        imgviewFullEmpStatus.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (empStatusList.size() > 0) {

                    StackPane secondaryLayout = new StackPane();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/empstatus/FXMLEmployeStatusFull.fxml"));
                    try {
                        Parent root = (Parent) loader.load();
                        FXMLEmployeStatusFullController controller = (FXMLEmployeStatusFullController) loader.getController();
                        controller.initData(empStatusList, branch, selectedBranch);
                        secondaryLayout.getChildren().add(root);
                        Scene secondScene = new Scene(secondaryLayout, 1000, 413);
                        Stage secondStage = new Stage();
                        secondStage.setTitle("Employee Status");
                        secondStage.setScene(secondScene);
                        secondStage.initStyle(StageStyle.UNDECORATED);
                        secondStage.initModality(Modality.WINDOW_MODAL);
                        secondStage.initOwner(lblLogout.getScene().getWindow());
                        secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                            @Override
                            public void handle(WindowEvent event) {
                                DBPool.getInstance().CloseConnections();
                                /* ========================  create executor that uses daemon threads: ==================== */
                                executor = Executors.newCachedThreadPool(runnable -> {
                                    Thread t = new Thread(runnable);
                                    t.setDaemon(true);
                                    return t;
                                });
                            }
                        }
                        );
                        secondStage.show();

                    } catch (IOException exception) {
                        exception.printStackTrace();
                        logger.error(exception.getMessage());

                    }

                }
            }
        });
    }

    /**
     *
     * @param empStatusList
     */
    public void loadEmployeeCounts(List<EmpStatusBEAN> empStatusList) {
        empStatusTable.getItems().clear();
        empStatusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        empStatusPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        empStatusStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        empStatusContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        empStatusExtNo.setCellValueFactory(new PropertyValueFactory<>("ext_number"));
        empStatusTable.getItems().setAll(empStatusList);

    }

    /**
     *
     */
    public void addStaffCountServices() {
        serviceStaffCounts = new Service< List<StaffCountPOJO>>() {
            @Override
            protected Task createTask() {
                return new Task<List<StaffCountPOJO>>() {
                    @Override
                    protected List<StaffCountPOJO> call() throws InterruptedException {
                        updateMessage("Searching for reminders....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(300);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return EnquiryDetailsDAO.getStaffEnquiryCount(CUR_BRANCH);
                    }
                };
            }
        };
        serviceStaffCounts.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                switch (newValue) {
                    case SCHEDULED:
                        break;
                    case READY:
                    case RUNNING:
                        System.out.println("Running  ...");
                        break;
                    case SUCCEEDED:
                        System.out.println("Succededd");
                        System.out.println("test data :: " + serviceStaffCounts.getValue());
                        List<StaffCountPOJO> countList = (List<StaffCountPOJO>) serviceStaffCounts.getValue();
                        addStaffCounts(countList);
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        serviceStaffCounts.start();
    }

    /**
     *
     */
    public void addRemindersServices() {
        serviceReminder = new Service<ObservableList<ReminderBEAN>>() {

            @Override
            protected Task createTask() {
                return new Task<ObservableList<ReminderBEAN>>() {
                    @Override
                    protected ObservableList<ReminderBEAN> call() throws InterruptedException {
                        updateMessage("Searching for reminders....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(300);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return reminderDAO.retrieveReminder(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                    }
                };
            }
        };
        serviceReminder.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                switch (newValue) {
                    case SCHEDULED:
                        break;
                    case READY:
                    case RUNNING:
                        System.out.println("Running  ...");
                        break;
                    case SUCCEEDED:
                        System.out.println("Succededd");
                        System.out.println("test data :: " + serviceReminder.getValue());
                        ObservableList<ReminderBEAN> listTempReminder = (ObservableList<ReminderBEAN>) serviceReminder.getValue();
                        if (listTempReminder.size() > 0) {
                            listReminders.clear();
                            listReminders = listTempReminder;
                            System.out.println("test data :: " + listReminders.toString());
                            loadRemindersForm();
                        }
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        /* ====================== Start Reminder Service ====================== */
        serviceReminder.start();
    }

    /**
     *
     */
    public void loadRemindersForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/reminder/FXMLRemainder.fxml"));
            Parent root = (Parent) loader.load();
            FXMLRemainderController controller = (FXMLRemainderController) loader.getController();
            controller.passReminderTodayEvents(listReminders);
            Scene scene = new Scene(root);
            reminderStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            reminderStage.setTitle("Reminder");
            if (!reminderStage.isShowing()) {
                reminderStage = new Stage();
                reminderStage.setScene(scene);
                reminderStage.initStyle(StageStyle.UTILITY);
                reminderStage.setAlwaysOnTop(true);
                reminderStage.show();
            }

        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void addSpotAssessmentRegistration() {
        /* ======================== Spot Assessment  ==================== */
        btnSpotAssessment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                notifyTimer.pause();
                try {
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("training");
                    }
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("migration");
                    }
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("work");
                    }
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("study");
                    }
                    if (Context.getInstance().currentProfile().getCurrentTab() == null) {
                        /* ====================== For Default Tab ====================== */
                        Context.getInstance().currentProfile().setCurrentTab("study");
                    }
                    /* ====================== Create New Bean ====================== */
                    CounselorDetailsBEAN spotDetailsBEAN = new CounselorDetailsBEAN();
                    BeanUtils.copyProperties(enquiryDetailsBEAN, spotDetailsBEAN);
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(spotDetailsBEAN);
                    StackPane secondaryLayout = new StackPane();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/assesment/FXMLAssesmentForm.fxml"));
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout);
                    Stage stageForAssessForm = new Stage();
                    stageForAssessForm.setTitle("Spot Assessment Form ");
                    stageForAssessForm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageForAssessForm.setScene(secondScene);
                    stageForAssessForm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageForAssessForm.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            notifyTimer.playFromStart();
                            /* ========================  create executor that uses daemon threads: ==================== */
                            executor = Executors.newCachedThreadPool(runnable -> {
                                Thread t = new Thread(runnable);
                                t.setDaemon(true);
                                return t;
                            });
                        }
                    }
                    );
                    stageForAssessForm.setMaximized(true);
                    stageForAssessForm.initModality(Modality.APPLICATION_MODAL);
                    stageForAssessForm.initOwner(btnSpotAssessment.getScene().getWindow());
                    stageForAssessForm.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        /* ======================== Spot Registration ==================== */
        btnSpotRegistration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                notifyTimer.pause();
                try {
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("training");
                    }
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("migration");
                    }
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("work");
                    }
                    if (enquiryDetailsBEAN.getStudy() != null) {
                        Context.getInstance().currentProfile().setCurrentTab("study");
                    }
                    if (Context.getInstance().currentProfile().getCurrentTab() == null) {
                        /* ====================== For Default Tab ====================== */
                        Context.getInstance().currentProfile().setCurrentTab("study");
                    }
                    /* ====================== Create New Bean ====================== */
                    CounselorDetailsBEAN spotDetailsBEAN = new CounselorDetailsBEAN();
                    BeanUtils.copyProperties(enquiryDetailsBEAN, spotDetailsBEAN);
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(spotDetailsBEAN);
                    StackPane secondaryLayout = new StackPane();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/registration/FXMLRegistrationForm.fxml"));
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout);
                    Stage stageForAssessForm = new Stage();
                    stageForAssessForm.setTitle("Spot Registration Form");
                    stageForAssessForm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageForAssessForm.setScene(secondScene);
                    stageForAssessForm.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageForAssessForm.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            notifyTimer.playFromStart();
                            /* ========================  create executor that uses daemon threads: ==================== */
                            executor = Executors.newCachedThreadPool(runnable -> {
                                Thread t = new Thread(runnable);
                                t.setDaemon(true);
                                return t;
                            });
                        }
                    }
                    );
                    stageForAssessForm.setMaximized(true);
                    stageForAssessForm.initModality(Modality.APPLICATION_MODAL);
                    stageForAssessForm.initOwner(btnSpotAssessment.getScene().getWindow());
                    stageForAssessForm.show();
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });

    }

    private void addUserTasks() {
        cmbCreateTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                notifyTimer.pause();
                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/FXMLTaskFullView.fxml"));
                try {
                    /* ======================== Add Bean TO Context ==================== */
                    Context.getInstance().currentProfile().setCounselorDetailsTaskBEAN(enquiryDetailsBEAN);
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Task Details");
                    secondStage.setScene(secondScene);
                    secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.initModality(Modality.WINDOW_MODAL);
                    secondStage.initOwner(lblLogout.getScene().getWindow());
                    secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            notifyTimer.play();
                            /* ========================  create executor that uses daemon threads: ==================== */
                            executor = Executors.newCachedThreadPool(runnable -> {
                                Thread t = new Thread(runnable);
                                t.setDaemon(true);
                                return t;
                            });
                        }
                    }
                    );
                    System.out.println("Test a enquiry :: " + enquiryDetailsBEAN.toString());
                    secondStage.show();

                } catch (IOException exception) {
                    exception.printStackTrace();
                    logger.error(exception.getMessage());

                }

            }
        });
        cmbEnquiryDetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/FXMLTaskFullView.fxml"));
                try {
                    /* ======================== Add Bean TO Context ==================== */
                    Context.getInstance().currentProfile().setCounselorDetailsTaskBEAN(new CounselorDetailsBEAN());
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Task Details");
                    secondStage.setScene(secondScene);
                    secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.initModality(Modality.WINDOW_MODAL);
                    secondStage.initOwner(lblLogout.getScene().getWindow());
                    secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            /* ========================  create executor that uses daemon threads: ==================== */
                            executor = Executors.newCachedThreadPool(runnable -> {
                                Thread t = new Thread(runnable);
                                t.setDaemon(true);
                                return t;
                            });
                        }
                    }
                    );
                    System.out.println("Test a enquiry :: " + enquiryDetailsBEAN.toString());
                    secondStage.show();

                } catch (IOException exception) {
                    exception.printStackTrace();
                    logger.error(exception.getMessage());

                }

            }
        });
    }

    /**
     *
     */
    public void bindAllEnquiryControls() {
        System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++313" + enquiryDetailsBEAN.getEnquiryAssignedTo());

        Bindings.bindBidirectional(enquiryDetailsBEAN.contactNameProperty(), txtContactName.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.contactEmailProperty(), txtContactEmail.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.countryProperty(), cmbCountry.valueProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.stateProperty(), cmbState.valueProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.districtProperty(), cmbDistrict.valueProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.remarksProperty(), txtRemarks.textProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.enquiryMethodProperty(), cmbMethod.valueProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.enquirySourceProperty(), cmbSource.valueProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.bracnchProperty(), cmbBranch.valueProperty());
        Bindings.bindBidirectional(enquiryDetailsBEAN.departmentProperty(), cmbDepartment.valueProperty());

        /* ========================== Check Counselor Inbox Limits  ======================== */
        cmbCountry.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {

                    Task<List<String>> taskLoadAddressState = new Task<List<String>>() {
                        @Override
                        protected List<String> call() throws Exception {
                            return MasterCountryStateDistrictDAO.getAllStates(newValue.toString());
                        }
                    };
                    taskLoadAddressState.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                        @Override
                        public void handle(WorkerStateEvent event) {
                            ObservableList states = FXCollections.observableArrayList();

                            for (String s : taskLoadAddressState.getValue()) {
                                states.add(s);
                            }
                            cmbState.setItems(states);
                        }
                    });
                    taskLoadAddressState.run();
                }
            }
        });
        System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++916" + enquiryDetailsBEAN.getEnquiryAssignedTo());

//        cmbBranch.valueProperty().addListener(new ChangeListener() {
//
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++456" + enquiryDetailsBEAN.getEnquiryAssignedTo());
//
//                if (newValue != null) {
//                    System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++123" + enquiryDetailsBEAN.getEnquiryAssignedTo());
//
//                    Task<ObservableList<CounselorsListPOJO>> taskLoadCounselors = new Task<ObservableList<CounselorsListPOJO>>() {
//                        @Override
//                        protected ObservableList<CounselorsListPOJO> call() throws Exception {
//                            return RetrieveStaticMasterDAO.getAllCounselors(newValue.toString());
//                        }
//                    };
//                    taskLoadCounselors.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//
//                        @Override
//                        public void handle(WorkerStateEvent event) {
//                            ObservableList<CounselorsListPOJO> counselorsList = FXCollections.observableArrayList();
//
//                            for (CounselorsListPOJO counselor : taskLoadCounselors.getValue()) {
//                                counselorsList.add(counselor);
//                            }
//                            cmbAssign.getItems().clear();
//                            cmbAssign.setItems(counselorsList);
//                            System.out.println("counselorsListUUU=====" + counselorsList.toString());
//                            System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++" + enquiryDetailsBEAN.getEnquiryAssignedTo());
//                            for (CounselorsListPOJO clpojo : cmbAssign.getItems()) {
//                                System.out.println("COUUUUUUU+++======" + clpojo.getUsername());
//                                if (clpojo.getUsername().equalsIgnoreCase(enquiryDetailsBEAN.getEnquiryAssignedTo())) {
//                                    cmbAssign.getSelectionModel().select(clpojo);
//                                }
//                            }
//                        }
//                    });
//                    new Thread(taskLoadCounselors).start();
//                    //  taskLoadCounselors.run();
//                }
//            }
//        });
        cmbBranch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++456" + enquiryDetailsBEAN.getEnquiryAssignedTo());

                if (newValue != null) {
                    System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++123" + enquiryDetailsBEAN.getEnquiryAssignedTo());

                    Task<ObservableList<CounselorsListPOJO>> taskLoadCounselors = new Task<ObservableList<CounselorsListPOJO>>() {
                        @Override
                        protected ObservableList<CounselorsListPOJO> call() throws Exception {
                            return RetrieveStaticMasterDAO.getAllCounselors(newValue.toString());
                        }
                    };
                    taskLoadCounselors.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                        @Override
                        public void handle(WorkerStateEvent event) {
                            ObservableList<CounselorsListPOJO> counselorsList = FXCollections.observableArrayList();

                            for (CounselorsListPOJO counselor : taskLoadCounselors.getValue()) {
                                counselorsList.add(counselor);
                            }
                            cmbAssign.getItems().clear();
                            cmbAssign.setItems(counselorsList);
                            System.out.println("counselorsListUUU=====" + counselorsList.toString());
                            System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()+++++++" + enquiryDetailsBEAN.getEnquiryAssignedTo());
                            for (CounselorsListPOJO clpojo : cmbAssign.getItems()) {
                                System.out.println("COUUUUUUU+++======" + clpojo.getUsername());
                                if (clpojo.getUsername().equalsIgnoreCase(enquiryDetailsBEAN.getEnquiryAssignedTo())) {
                                    cmbAssign.getSelectionModel().select(clpojo);
                                }
                            }
                        }
                    });
                    new Thread(taskLoadCounselors).start();
                    //  taskLoadCounselors.run();
                }

            }
        });

        cmbAssign.valueProperty().addListener(new ChangeListener<CounselorsListPOJO>() {

            @Override
            public void changed(ObservableValue<? extends CounselorsListPOJO> observable, CounselorsListPOJO oldValue, CounselorsListPOJO newValue) {

                if (newValue != null) {
                    enquiryDetailsBEAN.setEnquiryAssignedTo(newValue.getUsername());

                    Task<Map<String, String>> taskCommonPoolConfig = new Task<Map<String, String>>() {
                        @Override
                        protected Map<String, String> call() throws Exception {
                            return CommonPoolService.retrieveCommonPoolConfig(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);

                        }
                    };

                    taskCommonPoolConfig.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                        @Override
                        public void handle(WorkerStateEvent event) {
                            mapCommonPoolConfig = taskCommonPoolConfig.getValue();

                        }
                    });
                    Task<Map<String, String>> taskFlagEnquiryStatus = new Task<Map<String, String>>() {
                        @Override
                        protected Map<String, String> call() throws Exception {
                            return CommonPoolService.checkFlagEnquiryStatus(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);

                        }
                    };

                    taskFlagEnquiryStatus.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                        @Override
                        public void handle(WorkerStateEvent event) {
                            mapFlagEnquiryStatus = taskFlagEnquiryStatus.getValue();

                        }
                    });
                    Task<Boolean> taskFinal = new Task<Boolean>() {
                        @Override
                        protected Boolean call() throws Exception {
                            taskCommonPoolConfig.run();
                            taskFlagEnquiryStatus.run();
                            return true;
                        }
                    };
                    taskFinal.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                        @Override
                        public void handle(WorkerStateEvent event) {
                            long flagLimitConfig = Long.parseLong(mapCommonPoolConfig.get("flag_limit"));
                            long flagLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("flag_limit"));
                            long enquiryLimitConfig = Long.parseLong(mapCommonPoolConfig.get("enquiry_limit"));
                            long enquiryLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("enquiry_limit"));
                            if (enquiryLimitConfig == 0 || enquiryLimitStatus < enquiryLimitConfig) {
                                System.out.println("He has unlimited enquiries ");
                            } else {
                                cmbAssign.getSelectionModel().clearSelection();
                                //      showErrors.showError("This user has been reached maximum no. of enquiries ! Please try to assign someone else", cmbAssign);
                                Notification notification = Notifications.WARNING;
                                TrayNotification tray = new TrayNotification("This user has been reached maximum no. of enquiries  !", "Please try to assign someone else !", notification);
                                tray.setAnimation(Animations.POPUP);
                                tray.showAndDismiss(Duration.seconds(3), btnAssessNow);
                            }

                        }
                    });

                }

            }
        });

        enquiryDetailsBEAN.statusProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (EnquirySatusPOJO statusPOJO : obsEnquiryStatus) {
                        if (statusPOJO.getId() != null) {
                            if (statusPOJO.getId().equalsIgnoreCase(newValue)) {
                                lblAppStatus.setText(statusPOJO.getStatus());
                            }
                        }
                    }
                }
            }
        ;
    }

    );
    }

    /**
     *
     */
    public void submitEnquiryForm() {
        btnEnquirySubmit.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("inside   btnEnquirySubmit ENTER ");
//                  Event.fireEvent(btnEnquirySubmit, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
//                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
//                true, true, true, true, true, true, null));
                    btnEnquirySubmit.fire();
                }

            }
        });
        btnEnquirySubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (ValidateEnquiryForm() && (validateServiceList(servicesBEANList) || validateTrainingData(trainingBeanList))) {

                    /* =================================== Assessment Beans ===================================== */
                    StudySuggestedRequiredBEAN studySuggestedRequiredBEAN = new StudySuggestedRequiredBEAN();
                    AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
                    WorktExperienceBEAN workExperienceBEAN = new WorktExperienceBEAN();
                    AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
                    String generated_enquiry_id = "";
                    if (enquiryDetailsBEAN.getEnquiryID() == null || enquiryDetailsBEAN.getEnquiryID().equals("")) {
                        if (!DEFAULT_ENQUIRY_METHOD.equals(enquiryDetailsBEAN.getEnquiryMethod())) {
                            enquiryDetailsBEAN.setEnquiryAssignedTo("Not Assigned");
                        }
                        if (enquiryDetailsBEAN.getStaffBranch().length() > 4) {
                            generated_enquiry_id = enquiryDetailsBEAN.getStaffBranch().substring(0, 3).toLowerCase() + "_" + UiiDGenerator.getUIID8();;
                        } else {
                            generated_enquiry_id = "enq_" + UiiDGenerator.getUIID8();

                        }
                        enquiryDetailsBEAN.setEnquiryID(generated_enquiry_id);
                        studySuggestedRequiredBEAN.setEnquiry_id(generated_enquiry_id);
                        workExperienceBEAN.setEnquiryId(generated_enquiry_id);
                        /* ======================== Set Rating  ==================== */
                        enquiryDetailsBEAN.setRating("" + PRIORITY);
                        int row = EnquiryDetailsDAO.insertEnquiryIntoDB(enquiryDetailsBEAN);
                        System.out.println("Test Enquiry Assign :: " + enquiryDetailsBEAN.getEnquiryAssignedTo());
                        if (row == 1) {
                            /* ====================== Save Contac Numbers ====================== */
                            if (validateContactList(contactNumberBEANList)) {
                                for (ContactNumberBEAN numberBEAN : contactNumberBEANList) {
                                    if (numberBEAN.getRowId() != null) {
                                        contactNumbersDAO.updateContactNumber(numberBEAN);
                                    } else {
                                        numberBEAN.setEnquiryId(generated_enquiry_id);
                                        contactNumbersDAO.insertContactNumber(numberBEAN);
                                    }
                                }
                            }
                            /*========================= Insert Training Details ================================ */
                            if (validateTrainingData(trainingBeanList)) {
                                //      sbTrainingReqd.setLength(0);
                                sbTrainingReqd = new StringBuilder();
                                sbTrainingReqd.append("<html>");
                                sbTrainingReqd.append("<head>");
                                sbTrainingReqd.append("</head>");
                                sbTrainingReqd.append("<table>");
                                sbTrainingReqd.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Training");
                                sbTrainingReqd.append("</th>");
                                sbTrainingReqd.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Joining Date");
                                sbTrainingReqd.append("</th>");
                                sbTrainingReqd.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Batch");
                                sbTrainingReqd.append("</th>");
                                for (AssesmentTrainingBEAN trainingBEAN : trainingBeanList) {
                                    trainingBEAN.setEnquiryId(generated_enquiry_id);
                                    trainingBEAN.setFromDate(trainingBEAN.getFromDate());
                                    ProgramSuggestedRequiredDAO.insertTrainingDetails(trainingBEAN);
                                    service = service + "<br>Prefered Training Details & Proposed Joining Date " + trainingBEAN.getTraining() + " & " + trainingBEAN.getFromDate();
                                    sbTrainingReqd.append("<tr>");
                                    sbTrainingReqd.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + trainingBEAN.getTraining());
                                    sbTrainingReqd.append("</td>");
                                    sbTrainingReqd.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + trainingBEAN.getFromDate());
                                    sbTrainingReqd.append("</td>");
                                    sbTrainingReqd.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + trainingBEAN.getBatch());
                                    sbTrainingReqd.append("</td>");
                                    sbTrainingReqd.append("</tr>");
                                }
                                sbTrainingReqd.append("</table>");
                                sbTrainingReqd.append("</body>");
                                sbTrainingReqd.append("</html>");
                            }
                            /*========================= Insert Services Details ================================ */
                            if (validateServiceList(servicesBEANList)) {
                                //      sbProgramReqd.setLength(0);
                                sbProgramReqd = new StringBuilder();
                                sbProgramReqd.append("<html>");
                                sbProgramReqd.append("<head>");
                                sbProgramReqd.append("</head>");
                                sbProgramReqd.append("<table>");
                                sbProgramReqd.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Program");
                                sbProgramReqd.append("</th>");
                                sbProgramReqd.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Country");
                                sbProgramReqd.append("</th>");
                                sbProgramReqd.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Location");
                                sbProgramReqd.append("</th>");
                                for (ServicesBEAN servicesBEAN : servicesBEANList) {
                                    if (servicesBEAN.getService() != null) {

                                        sbProgramReqd.append("<tr>");
                                        sbProgramReqd.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + servicesBEAN.getService());
                                        sbProgramReqd.append("</td>");
                                        sbProgramReqd.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + servicesBEAN.getCountry());
                                        sbProgramReqd.append("</td>");
                                        sbProgramReqd.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + servicesBEAN.getLocation());
                                        sbProgramReqd.append("</td>");
                                        sbProgramReqd.append("</tr>");

                                        switch (servicesBEAN.getService()) {

                                            case "Study":
                                                studySuggestedRequiredBEAN = new StudySuggestedRequiredBEAN();
                                                studySuggestedRequiredBEAN.setEnquiry_id(generated_enquiry_id);
                                                studySuggestedRequiredBEAN.setStudy("Study");
                                                studySuggestedRequiredBEAN.setLocation(servicesBEAN.getLocation());
                                                studySuggestedRequiredBEAN.setCountry(servicesBEAN.getCountry());
                                                ProgramSuggestedRequiredDAO.insertStudyDetails(studySuggestedRequiredBEAN);
                                                service = service + "<br>Prefered Country & Location for Study " + servicesBEAN.getCountry() + " & " + servicesBEAN.getLocation();
                                                break;
                                            case "Work":
                                                assesmentWorkBEAN = new AssesmentWorkBEAN();
                                                assesmentWorkBEAN.setEnquiryId(generated_enquiry_id);
                                                assesmentWorkBEAN.setWork("Work");
                                                assesmentWorkBEAN.setLocation(servicesBEAN.getLocation());
                                                assesmentWorkBEAN.setCountry(servicesBEAN.getCountry());
                                                ProgramSuggestedRequiredDAO.insertWorkDetails(generated_enquiry_id, assesmentWorkBEAN);
                                                service = service + "<br>Prefered Country & Location for Work " + servicesBEAN.getCountry() + " & " + servicesBEAN.getLocation();
                                                break;
                                            case "Migrate":
                                                assesmentMigrateBEAN = new AssesmentMigrateBEAN();
                                                assesmentMigrateBEAN.setEnquiryId(generated_enquiry_id);
                                                assesmentMigrateBEAN.setMigrate("Migrate");
                                                assesmentMigrateBEAN.setCountry(servicesBEAN.getCountry());
                                                ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);
                                                service = service + "<br>Prefered Country & Location for Migration " + servicesBEAN.getCountry() + " & " + servicesBEAN.getLocation();
                                                break;
                                            default:
                                                System.out.println("Service Insertion Failed");
                                                break;

                                        }
                                    }
                                }
                                sbProgramReqd.append("</table>");
                                sbProgramReqd.append("</body>");
                                sbProgramReqd.append("</html>");
                            }

                            /* ====================== Add a default status ====================== */
                            AssessmentStatusBEAN assessmentStatusBEAN = new AssessmentStatusBEAN();
                            assessmentStatusBEAN.setEnquiryId(generated_enquiry_id);
                            assessmentStatusBEAN.setAssessmentStatus("1");
                            assessmentStatusBEAN.setPriority("" + PRIORITY);
                            assessmentStatusBEAN.setBranch(enquiryDetailsBEAN.getBracnch());
                            assessmentStatusBEAN.setAppointmentDate(LocalDate.now());
                            assessmentStatusBEAN.setCounselor(enquiryDetailsBEAN.getEnquiryAssignedTo());
                            assessmentStatusBEAN.setRemarks("");
                            assessmentStatusBEAN.setReference("Assessment Pending");
                            assessmentStatusDAO.insertAssessmentStatus(assessmentStatusBEAN);
                            /*========================= Insert History Details of counselor assign ================================ */ ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                            historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                            historyPOJO.setAssigned_branch(CUR_BRANCH);
                            historyPOJO.setAssigned_by(CUR_USERNAME);
                            historyPOJO.setAssigned_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());
                            historyPOJO.setEnquiry_id(generated_enquiry_id);
                            historyPOJO.setAssigned_to(enquiryDetailsBEAN.getEnquiryAssignedTo());
                            historyPOJO.setAssigned_to_branch(enquiryDetailsBEAN.getBracnch());
                            historyPOJO.setHolded_by("enquiry");
                            historyPOJO.setPurpose("Assessment Pending");
                            historyPOJO.setRemarks(enquiryDetailsBEAN.getRemarks());
                            if (enquiryDetailsBEAN.getStatus() != null) {
                                historyPOJO.setCurrent_status(enquiryDetailsBEAN.getStatus());
                            } else {
                                historyPOJO.setCurrent_status("Assessment Pending");
                            }
                            historyPOJO.setStudy_required(enquiryDetailsBEAN.getStudyRequired());
                            historyPOJO.setWork_required(enquiryDetailsBEAN.getWorkRequired());
                            historyPOJO.setMigration_required(enquiryDetailsBEAN.getMigrationRequired());
                            historyPOJO.setTraining_required(enquiryDetailsBEAN.getTrainingRequired());
                            System.out.println("Historyyyy :::::::::: " + historyPOJO.toString());
                            forwardHistoryDAO.forwardEnquiry(historyPOJO);
                            /*========================= The End ================================ */

 /* ====================== Send Email  ====================== */
                            if (enquiryDetailsBEAN.getContactEmail() != null) {
                                MailSentPOJO mailSentPOJO = new MailSentPOJO();
                                mailSentPOJO.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                mailSentPOJO.setEmailHead("ENQUIRY");
                                mailSentPOJO.setEmailsubhead("ENQUIRY_ADD");
                                mailSentPOJOs = mailSentDAO.getEmailTemplateDetailsUsingSubhead(mailSentPOJO.getEmailsubhead());
                                if (mailSentPOJOs.size() > 0) {
                                    for (MailSentPOJO mspojo : mailSentPOJOs) {
                                        if (messages.equalsIgnoreCase("")) {
                                            messages = messages + mspojo.getSalutation();
                                            messages = messages.replace("[USER]", enquiryDetailsBEAN.getContactName());
                                        }

                                        mailSentPOJO.setFrom(mspojo.getFrom());
                                        mailSentPOJO.setTo(enquiryDetailsBEAN.getContactEmail());

                                        mailSentPOJO.setSubject(mspojo.getSubject());
                                        messages = messages + mspojo.getContent();
                                        messages = messages.replace("[EMAIL]", enquiryDetailsBEAN.getContactEmail());
                                        messages = messages.replace("[PHONE]", enquiryDetailsBEAN.getContactNumber1());
                                        location = enquiryDetailsBEAN.getDistrict() + "," + enquiryDetailsBEAN.getState() + "," + enquiryDetailsBEAN.getCountry();
                                        messages = messages.replace("[LOCATION]", location);
                                        messages = messages.replace("[BRANCH]", enquiryDetailsBEAN.getBracnch());

                                        if (enquiryDetailsBEAN.getEnquiryAssignedTo() != null) {
                                            if (enquiryDetailsBEAN.getEnquiryAssignedTo().equalsIgnoreCase("Not Assigned")) {
                                                messages = messages.replace("[COUNSELOR]", enquiryDetailsBEAN.getEnquiryAssignedTo());
                                            } else {
                                                messages = messages.replace("[COUNSELOR]", cmbAssign.getSelectionModel().getSelectedItem().toString());
                                            }
                                        }
                                        if (mspojo.getSignature() != null) {
                                            emailSignature = mspojo.getSignature();
                                        }
                                        if (mspojo.getSignature() == null) {
                                            emailSignature = "International Academy,2nd floor, Bright House,Karimpatta Cross Road,Pallimukku, Ernakulam Cochin (Kochi)-16";
                                        }

                                    }
                                    messages = messages.replace("[PROGRAM/TRAINING_LIST_IN_TABLE_FORMAT]", sbProgramReqd.append(sbTrainingReqd));
                                    messages = messages.replace("null", "");
                                    mailSentPOJO.setMessage(messages + emailSignature);
                                    int result_notification_insert = mailSentDAO.logNotification(mailSentPOJO);

                                    if (result_notification_insert > 0) {
//                              
                                        Task taskEnquiryAdd = new Task() {
                                            @Override
                                            protected Object call() throws Exception {
                                                mailMail.sendMail(mailSentPOJO);
                                                return null;
                                            }
                                        };
                                        new Thread(taskEnquiryAdd).start();
                                        taskEnquiryAdd.setOnSucceeded(new EventHandler() {
                                            @Override
                                            public void handle(Event event) {
                                                showErrors.showSuccess("Sent Notification !", "Sent Enquiry Add Notification Successfully !", btnEnquirySubmit);

                                            }
                                        });
                                        // taskEnquiryAdd.run();
                                        new Thread(taskEnquiryAdd).start();
                                        initializeAutocompletionData();
                                        ShowProgress showProgress = new ShowProgress();
                                        showProgress.showProgressIndicator(clearBtn, "Saved", "Enquiry saved!", "An enquiry has been added successfully!");
                                        clearScreen();
                                        autoCompletion();
                                        if (!serviceStaffCounts.isRunning()) {
                                            serviceStaffCounts.reset();
                                            serviceStaffCounts.restart();
                                        }
                                    }
                                }
                            }
                            /* ===================== Clear Bean ====================== */
                            enquiryDetailsBEAN = new CounselorDetailsBEAN();
                            bindAllEnquiryControls();
                            enquiryDetailsBEAN.setStaffBranch(CUR_BRANCH);
                            enquiryDetailsBEAN.setStaffUsername(CUR_USERNAME);
                            cmbBranch.getSelectionModel().select(CUR_BRANCH);
                            System.out.println("Check Null Bean :: " + enquiryDetailsBEAN.toString());
                        }
                    } else {
                        System.out.println("Current Enquiry ID567=====");
                        /* ============================================================================== */
 /* ====================== Enquiry Id Not null ( Updation ) ====================== */
 /* =============================================================== */
 /*========================= Update Training Details ================================ */
                        if (validateTrainingData(trainingBeanList)) {
                            System.out.println("INSIDE UPDATE enquiry 5================");
                            //     sbTrainingReqdUpdt.setLength(0);
                            sbTrainingReqdUpdt = new StringBuilder();
                            sbTrainingReqdUpdt.append("<html>");
                            sbTrainingReqdUpdt.append("<head>");
                            sbTrainingReqdUpdt.append("</head>");
                            sbTrainingReqdUpdt.append("<table>");
                            sbTrainingReqdUpdt.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Training");
                            sbTrainingReqdUpdt.append("</th>");
                            sbTrainingReqdUpdt.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Proposed Joining");
                            sbTrainingReqdUpdt.append("</th>");
                            sbTrainingReqdUpdt.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Batch");
                            sbTrainingReqdUpdt.append("</th>");
                            for (AssesmentTrainingBEAN trainingBEAN : trainingBeanList) {
                                System.out.println("INSIDE UPDATE enquiry 6================");
                                trainingBEAN.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                trainingBEAN.setFromDate(trainingBEAN.getFromDate());
                                if (trainingBEAN.getRowId() != null) {
                                    System.out.println("INSIDE UPDATE enquiry 7================");
                                    ProgramSuggestedRequiredDAO.updateTrainingDetails(trainingBEAN);
                                } else {
                                    System.out.println("INSIDE UPDATE enquiry 8================");
                                    ProgramSuggestedRequiredDAO.insertTrainingDetails(trainingBEAN);
                                }
                                service = service + "<br>Prefered Training Details & Proposed Joining Date " + trainingBEAN.getTraining() + " & " + trainingBEAN.getFromDate();
                                sbTrainingReqdUpdt.append("<tr>");
                                sbTrainingReqdUpdt.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + trainingBEAN.getTraining());
                                sbTrainingReqdUpdt.append("</td>");
                                sbTrainingReqdUpdt.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + trainingBEAN.getFromDate());
                                sbTrainingReqdUpdt.append("</td>");
                                sbTrainingReqdUpdt.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + trainingBEAN.getBatch());
                                sbTrainingReqdUpdt.append("</td>");
                                sbTrainingReqdUpdt.append("</tr>");
                            }
                            System.out.println("INSIDE UPDATE enquiry 9================");
                            sbTrainingReqdUpdt.append("</table>");
                            sbTrainingReqdUpdt.append("</body>");
                            sbTrainingReqdUpdt.append("</html>");
                        }
                        /*========================= Update Services Details ================================ */
//             
                        if (validateServiceList(servicesBEANList)) {
                            //  sbProgramReqdUpdt.setLength(0);
                            System.out.println("INSIDE UPDATE enquiry 1================");
                            sbProgramReqdUpdt = new StringBuilder();
                            sbProgramReqdUpdt.append("<html>");
                            sbProgramReqdUpdt.append("<head>");
                            sbProgramReqdUpdt.append("</head>");
                            sbProgramReqdUpdt.append("<table>");
                            sbProgramReqdUpdt.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Program");
                            sbProgramReqdUpdt.append("</th>");
                            sbProgramReqdUpdt.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Country");
                            sbProgramReqdUpdt.append("</th>");
                            sbProgramReqdUpdt.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: center;\"> Location");
                            sbProgramReqdUpdt.append("</th>");
                            for (ServicesBEAN servicesBEAN : servicesBEANList) {
                                System.out.println("INSIDE UPDATE enquiry 2================");
                                if (servicesBEAN.getService() != null) {
                                    System.out.println("INSIDE UPDATE enquiry 3================");
                                    sbProgramReqdUpdt.append("<tr>");
                                    sbProgramReqdUpdt.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + servicesBEAN.getService());
                                    sbProgramReqdUpdt.append("</td>");
                                    sbProgramReqdUpdt.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + servicesBEAN.getCountry());
                                    sbProgramReqdUpdt.append("</td>");
                                    sbProgramReqdUpdt.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + servicesBEAN.getLocation());
                                    sbProgramReqdUpdt.append("</td>");
                                    sbProgramReqdUpdt.append("</tr>");
                                    switch (servicesBEAN.getService()) {
                                        case "Study":
                                            studySuggestedRequiredBEAN = new StudySuggestedRequiredBEAN();
                                            studySuggestedRequiredBEAN.setEnquiry_id(enquiryDetailsBEAN.getEnquiryID());
                                            studySuggestedRequiredBEAN.setStudy("Study");
                                            studySuggestedRequiredBEAN.setLocation(servicesBEAN.getLocation());
                                            studySuggestedRequiredBEAN.setCountry(servicesBEAN.getCountry());
                                            if (servicesBEAN.getId() != null) {
                                                ProgramSuggestedRequiredDAO.updateStudyDetails(studySuggestedRequiredBEAN);
                                            } else {
                                                ProgramSuggestedRequiredDAO.insertStudyDetails(studySuggestedRequiredBEAN);
                                            }
                                            System.out.println("Study Bean :: " + studySuggestedRequiredBEAN.toString());
                                            break;
                                        case "Work":
                                            assesmentWorkBEAN = new AssesmentWorkBEAN();
                                            assesmentWorkBEAN.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                            assesmentWorkBEAN.setWork("Work");
                                            assesmentWorkBEAN.setLocation(servicesBEAN.getLocation());
                                            assesmentWorkBEAN.setCountry(servicesBEAN.getCountry());
                                            if (servicesBEAN.getId() != null) {
                                                ProgramSuggestedRequiredDAO.updateWorkDetails(assesmentWorkBEAN);
                                            } else {
                                                ProgramSuggestedRequiredDAO.insertWorkDetails(enquiryDetailsBEAN.getEnquiryID(), assesmentWorkBEAN);
                                            }
                                            break;
                                        case "Migrate":
                                            assesmentMigrateBEAN = new AssesmentMigrateBEAN();
                                            assesmentMigrateBEAN.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                            assesmentMigrateBEAN.setMigrate("Migrate");
                                            assesmentMigrateBEAN.setCountry(servicesBEAN.getCountry());
                                            assesmentMigrateBEAN.setLocation(servicesBEAN.getLocation());
                                            if (servicesBEAN.getId() != null) {
                                                ProgramSuggestedRequiredDAO.updateMigrateDetails(assesmentMigrateBEAN);
                                            } else {
                                                ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);
                                            }
                                            break;
                                        default:
                                            System.out.println("Service Updation Failed");

                                    }

                                }
                            }
                            sbProgramReqdUpdt.append("</table>");
                            sbProgramReqdUpdt.append("</body>");
                            sbProgramReqdUpdt.append("</html>");
                            System.out.println("INSIDE UPDATE enquiry 4================");
                        }

                        /* ====================== Update Contact Numbers ====================== */
                        if (validateContactList(contactNumberBEANList)) {
                            for (ContactNumberBEAN numberBEAN : contactNumberBEANList) {
                                System.out.println("Contact Numbers :: " + numberBEAN.toString());
                                if (numberBEAN.getRowId() != null) {
                                    numberBEAN.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                    contactNumbersDAO.updateContactNumber(numberBEAN);
                                } else {
                                    numberBEAN.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                    contactNumbersDAO.insertContactNumber(numberBEAN);
                                }
                            }
                        }
                        /* ====================== End  Update Contact Numbers ====================== */

                        if (cmbAssign.getSelectionModel().getSelectedIndex() >= 0) {
                            enquiryDetailsBEAN.setEnquiryAssignedTo(cmbAssign.getSelectionModel().getSelectedItem().getUsername());
                            if (CURR_COUNSELOR == null || !CURR_COUNSELOR.equals(enquiryDetailsBEAN.getEnquiryAssignedTo())) {
                                /*========================= Not req. For updation  Insert History Details of counselor assign ================================ */
                                ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                                historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                                historyPOJO.setAssigned_branch(CUR_BRANCH);
                                historyPOJO.setAssigned_by(CUR_USERNAME);
                                if (ENABLE_FOLLOWUP) {

                                }
                                historyPOJO.setEnquiry_id(enquiryDetailsBEAN.getEnquiryID());
                                historyPOJO.setAssigned_to(enquiryDetailsBEAN.getEnquiryAssignedTo());
                                historyPOJO.setAssigned_to_branch(enquiryDetailsBEAN.getBracnch());
                                historyPOJO.setHolded_by(CURR_COUNSELOR);
                                historyPOJO.setRemarks(enquiryDetailsBEAN.getRemarks());
                                if (enquiryDetailsBEAN.getStatus() != null) {
                                    historyPOJO.setCurrent_status(enquiryDetailsBEAN.getStatus());
                                } else {
                                    historyPOJO.setCurrent_status("Assessment Pending");
                                }
                                historyPOJO.setStudy_required(enquiryDetailsBEAN.getStudyRequired());
                                historyPOJO.setWork_required(enquiryDetailsBEAN.getWorkRequired());
                                historyPOJO.setMigration_required(enquiryDetailsBEAN.getMigrationRequired());
                                historyPOJO.setTraining_required(enquiryDetailsBEAN.getTrainingRequired());
                                System.out.println("Historyyyy :::::::::: " + historyPOJO.toString());
                                forwardHistoryDAO.forwardEnquiry(historyPOJO);
                                /*========================= The End ================================ */

                            }
                            if (enquiryDetailsBEAN.getContactEmail() != null) {
                                MailSentPOJO mailSentPOJO = new MailSentPOJO();
                                mailSentPOJO.setEnquiryId(enquiryDetailsBEAN.getEnquiryID());
                                mailSentPOJO.setTo(enquiryDetailsBEAN.getContactEmail());
                                mailSentPOJO.setEmailHead("ENQUIRY");
                                mailSentPOJO.setEmailsubhead("ENQUIRY_UPDATE");
                                mailSentPOJOs.clear();
                                mailSentPOJOs = mailSentDAO.getEmailTemplateDetailsUsingSubhead(mailSentPOJO.getEmailsubhead());
                                messages = "";
                                emailSignature = "";
                                if (mailSentPOJOs.size() > 0) {
                                    for (MailSentPOJO mspojo : mailSentPOJOs) {
                                        if (messages.equalsIgnoreCase("")) {
                                            messages = messages + mspojo.getSalutation();
                                            messages = messages.replace("[USER]", enquiryDetailsBEAN.getContactName());
                                        }
                                        mailSentPOJO.setFrom(mspojo.getFrom());
                                        mailSentPOJO.setSubject(mspojo.getSubject());
                                        messages = messages + mspojo.getContent();
                                        messages = messages.replace("[EMAIL]", enquiryDetailsBEAN.getContactEmail());
                                        messages = messages.replace("[PHONE]", enquiryDetailsBEAN.getContactNumber1());
                                        location = enquiryDetailsBEAN.getDistrict() + "," + enquiryDetailsBEAN.getState() + "," + enquiryDetailsBEAN.getCountry();
                                        messages = messages.replace("[LOCATION]", location);
                                        messages = messages.replace("[BRANCH]", enquiryDetailsBEAN.getBracnch());

                                        if (enquiryDetailsBEAN.getEnquiryAssignedTo() != null) {
                                            if (enquiryDetailsBEAN.getEnquiryAssignedTo().equalsIgnoreCase("Not Assigned")) {
                                                messages = messages.replace("[COUNSELOR]", enquiryDetailsBEAN.getEnquiryAssignedTo());
                                            } else {
                                                messages = messages.replace("[COUNSELOR]", cmbAssign.getSelectionModel().getSelectedItem().toString());
                                            }
                                        }
                                        if (mspojo.getSignature() != null) {
                                            emailSignature = mspojo.getSignature();
                                        }
                                        if (mspojo.getSignature() == null) {
                                            emailSignature = "International Academy,2nd floor, Bright House,Karimpatta Cross Road,Pallimukku, Ernakulam Cochin (Kochi)-16";
                                        }

                                    }
                                    messages = messages.replace("[PROGRAM/TRAINING_LIST_IN_TABLE_FORMAT]", sbProgramReqdUpdt.append(sbTrainingReqdUpdt));
                                    messages = messages.replace("null", "");
                                    mailSentPOJO.setMessage(messages + emailSignature);
                                    int result_notification_inserts = mailSentDAO.logNotification(mailSentPOJO);

                                    if (result_notification_inserts > 0) {
//                              
                                        Task taskEnquiryUpdate = new Task() {
                                            @Override
                                            protected Object call() throws Exception {
                                                mailMail.sendMail(mailSentPOJO);
                                                return null;
                                            }
                                        };
                                        new Thread(taskEnquiryUpdate).start();

                                        taskEnquiryUpdate.setOnSucceeded(new EventHandler() {
                                            @Override
                                            public void handle(Event event) {
                                                showErrors.showSuccess("Sent Notification !", "Sent Enquiry Add Notification Successfully !", btnEnquirySubmit);

                                            }
                                        });
                                        //      taskEnquiryUpdate.run();
                                        initializeAutocompletionData();
                                        ShowProgress showProgress = new ShowProgress();
                                        showProgress.showProgressIndicator(clearBtn, "Saved", "Enquiry saved!", "An enquiry has been added successfully!");
                                        clearScreen();
                                        autoCompletion();
                                        if (!serviceStaffCounts.isRunning()) {
                                            serviceStaffCounts.reset();
                                            serviceStaffCounts.restart();
                                        }
                                    }
                                }
                            }
                        }
                        /* ======================== Set Rating  ==================== */
                        enquiryDetailsBEAN.setRating("" + PRIORITY);
                        System.out.println("Test Enquiry Assign Update :: " + enquiryDetailsBEAN.getEnquiryAssignedTo());
                        int rows = EnquiryDetailsDAO.updateEnquiryIntoDB(enquiryDetailsBEAN);
                        if (rows > 0) {
                            inlineEditingDAO.editRemarks(enquiryDetailsBEAN);
                            /* ======================== Reload Auto completion data Combo boxes ==================== */

                            initializeAutocompletionData();
                            ShowProgress showProgress = new ShowProgress();
                            showProgress.showProgressIndicator(clearBtn, "Updated");
                            clearScreen();
                            autoCompletion();
                        }
                    }

                } else {
                    System.out.println("Please fill atleast one enquiry");

                    if (ValidateEnquiryForm()) {
                        showErrors.showError("Please Fill Atleast One Program Or Service", gridServices);

                    }

                }
                /* ======================== reload counts right side panel ==================== */

                addFollowUpRegister();

            }
        });
        /**
         *
         */
    }

    /**
     *
     */
    public void initDynamicTrainingControls() {
        obsListTraining = RetrieveStaticMasterDAO.getTraings();
        obsListBatches = RetrieveStaticMasterDAO.getBatches();

        AssesmentTrainingBEAN emptyTrainingBEAN = new AssesmentTrainingBEAN();
        trainingBeanList.add(emptyTrainingBEAN);
        addTrainigGridRows();
    }

    /**
     *
     * @param validateTrainingBEANList
     * @return
     */
    public boolean validateTrainingData(List<AssesmentTrainingBEAN> validateTrainingBEANList) {
        return validateTrainingBEANList.stream().noneMatch((trainingBEAN) -> (trainingBEAN.getTraining() == null || trainingBEAN.getBatch() == null || trainingBEAN.getFromDate() == null));
    }

    /**
     *
     */
    public void addTrainigGridRows() {
        gridTraining.getChildren().clear();
        for (AssesmentTrainingBEAN trainingBean : trainingBeanList) {
            ComboBox cmbTrainig = new ComboBox(obsListTraining);
            cmbTrainig.setPromptText("Select Training");
            cmbTrainig.getStyleClass().add("button-service");
            ComboBoxJumpToChar.jumpToChar(cmbTrainig);

            DatePicker dateProposedJoining = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(dateProposedJoining);
            dateProposedJoining.setPromptText("Proposed Joining Date");
            dateProposedJoining.getStyleClass().add("button-service");
            dateProposedJoining.setDayCellFactory(dayCellFactory);

            ComboBox cmbBatch = new ComboBox(obsListBatches);
            cmbBatch.setPromptText("Batch");
            cmbBatch.getStyleClass().add("button-service");
            ComboBoxJumpToChar.jumpToChar(cmbBatch);

            Button btnDftPlus = new Button();
            btnDftPlus.getStyleClass().add("plus-button");
            btnDftPlus
                    .setCursor(new ImageCursor(new Image(INALoginForm.class
                            .getResourceAsStream("images/cursor_green.png"))));
            Button btnDftClose = new Button();
            btnDftClose.getStyleClass().add("close-button");
            btnDftClose
                    .setCursor(new ImageCursor(new Image(INALoginForm.class
                            .getResourceAsStream("images/cursor_red.png"))));
            HBox boxDft = new HBox();
            btnDftPlus.setFocusTraversable(false);
            btnDftClose.setFocusTraversable(false);
            boxDft.getChildren().addAll(btnDftPlus, btnDftClose);
            boxDft.setSpacing(3);
            boxDft.setAlignment(Pos.CENTER_RIGHT);

            cmbTrainig.setMaxWidth(Double.MAX_VALUE);
            dateProposedJoining.setMaxWidth(Double.MAX_VALUE);
            cmbBatch.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(cmbTrainig, Priority.ALWAYS);
            GridPane.setHgrow(dateProposedJoining, Priority.ALWAYS);
            GridPane.setHgrow(cmbBatch, Priority.ALWAYS);

            /* ======================== Button Events ==================== */
            btnDftPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (validateTrainingData(trainingBeanList)) {
                        AssesmentTrainingBEAN emptyBean = new AssesmentTrainingBEAN();
                        trainingBeanList.add(emptyBean);
                        addTrainigGridRows();
                    } else {
                        showErrors.showError("All fields required !", gridTraining);
                    }

                    System.out.println("Test :: " + trainingBeanList.toString());
                }
            });
            btnDftClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnEnquirySubmit.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        System.out.println("Current Bean :: " + trainingBean.toString());
                        if (trainingBean.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteTrainingDetails(trainingBean.getRowId());
                        }
                        trainingBeanList.remove(trainingBean);
                        if (trainingBeanList.size() < 1) {
                            AssesmentTrainingBEAN nullBean = new AssesmentTrainingBEAN();
                            trainingBeanList.add(nullBean);
                            addTrainigGridRows();
                        } else {
                            addTrainigGridRows();
                        }
                    }
                }
            });

            /* ======================== Bindings ==================== */
            Bindings.bindBidirectional(cmbTrainig.valueProperty(), trainingBean.trainingProperty());
            Bindings.bindBidirectional(dateProposedJoining.valueProperty(), trainingBean.fromDateProperty());
            Bindings.bindBidirectional(cmbBatch.valueProperty(), trainingBean.batchProperty());

            /* ======================== Add to grid ==================== */
            if (trainingBeanList.indexOf(trainingBean) == 0) {
                Label lblTraining = new Label("Training");
                Text txtMandatory = new Text("*");
                txtMandatory.setStyle("-fx-fill:red");
                lblTraining.setGraphic(txtMandatory);
                lblTraining.setContentDisplay(ContentDisplay.RIGHT);
                gridTraining.add(lblTraining, 0, trainingBeanList.indexOf(trainingBean));
            }
            gridTraining.add(cmbTrainig, 1, trainingBeanList.indexOf(trainingBean));
            gridTraining.add(dateProposedJoining, 2, trainingBeanList.indexOf(trainingBean));
            gridTraining.add(cmbBatch, 3, trainingBeanList.indexOf(trainingBean));
            gridTraining.add(boxDft, 4, trainingBeanList.indexOf(trainingBean));
        }
    }

    /**
     *
     */
    public void initDynamicServiceControls() {
        ServicesBEAN emptyBEAN = new ServicesBEAN();
        servicesBEANList.add(emptyBEAN);
        addServiceGridRows(servicesBEANList);
    }

    /**
     *
     * @param servicesBEANList
     * @return
     */
    public boolean validateServiceList(List<ServicesBEAN> servicesBEANList) {
//  with location validation       return servicesBEANList.stream().noneMatch((servicesBEAN) -> (servicesBEAN.getCountry() == null || servicesBEAN.getLocation() == null || servicesBEAN.getService() == null));
        return servicesBEANList.stream().noneMatch((servicesBEAN) -> (servicesBEAN.getCountry() == null || servicesBEAN.getService() == null));
    }

    /**
     *
     * @param servicesRowBEANList
     */
    public void addServiceGridRows(List<ServicesBEAN> servicesRowBEANList) {
        gridServices.getChildren().clear();
        for (ServicesBEAN servicesBEAN : servicesRowBEANList) {

            /* ======================== Controls ==================== */
            ComboBox cmbServices = new ComboBox(obsListServices);
            cmbServices.setPromptText("Select Program");
            cmbServices.getStyleClass().add("button-service");
            ComboBoxJumpToChar.jumpToChar(cmbServices);

            ComboBox cmbServiceCountry = new ComboBox(countryDAO.retrieveAllCountries());
            cmbServiceCountry.setPromptText("Country ");
            cmbServiceCountry.getStyleClass().add("button-service");
            ComboBoxJumpToChar.jumpToChar(cmbServiceCountry);

            ComboBox cmbServiceLocation = new ComboBox();
            cmbServiceLocation.setPromptText("Location");
            cmbServiceLocation.getStyleClass().add("button-service");
            ComboBoxJumpToChar.jumpToChar(cmbServiceLocation);

            Button btnDftPlus = new Button();
            btnDftPlus.getStyleClass().add("plus-button");
            btnDftPlus
                    .setCursor(new ImageCursor(new Image(INALoginForm.class
                            .getResourceAsStream("images/cursor_green.png"))));
            Button btnDftClose = new Button();
            btnDftClose.getStyleClass().add("close-button");
            btnDftClose
                    .setCursor(new ImageCursor(new Image(INALoginForm.class
                            .getResourceAsStream("images/cursor_red.png"))));
            HBox boxDft = new HBox();
            btnDftPlus.setFocusTraversable(false);
            btnDftClose.setFocusTraversable(false);
            boxDft.getChildren().addAll(btnDftPlus, btnDftClose);
            boxDft.setSpacing(3);
            boxDft.setAlignment(Pos.CENTER_RIGHT);

            cmbServices.setMaxWidth(Double.MAX_VALUE);
            cmbServiceCountry.setMaxWidth(Double.MAX_VALUE);
            cmbServiceLocation.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(cmbServices, Priority.ALWAYS);
            GridPane.setHgrow(cmbServiceCountry, Priority.ALWAYS);
            GridPane.setHgrow(cmbServiceLocation, Priority.ALWAYS);

            /* ======================== Button Events ==================== */
            cmbServiceCountry.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        // cmbServiceLocation.getSelectionModel().selectFirst();
                        Task< ObservableList> taskLoadState = new Task<ObservableList>() {
                            @Override
                            protected ObservableList call() throws Exception {
                                return locationsDAO.retrievLocationsByCountry(newValue.toString());
                            }
                        };
                        taskLoadState.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                            @Override
                            public void handle(WorkerStateEvent event) {
                                cmbServiceLocation.setItems(taskLoadState.getValue());

                            }
                        });
                        taskLoadState.run();
                    }
                }
            });
            btnDftPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (validateServiceList(servicesBEANList)) {
                        ServicesBEAN emptyBEAN = new ServicesBEAN();
                        servicesBEANList.add(emptyBEAN);
                        addServiceGridRows(servicesBEANList);
                    } else {
                        showErrors.showError("All fields required !", gridServices);
                        //  showPopUpMessages();
                    }
                }
            });

            btnDftClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnEnquirySubmit.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        System.out.println("Current Bean :: " + servicesBEAN.toString());
                        if (servicesBEAN.getService() != null && servicesBEAN.getId() != null) {
                            switch (servicesBEAN.getService()) {
                                case "Study":
                                    ProgramSuggestedRequiredDAO.deleteStudyDetails(servicesBEAN.getId());
                                    break;
                                case "Work":
                                    ProgramSuggestedRequiredDAO.deleteWorkDetailsByid(servicesBEAN.getId());
                                    break;
                                case "Migrate":
                                    ProgramSuggestedRequiredDAO.deleteMigrateDetails(servicesBEAN.getId());
                                    break;
                                default:
                                    System.out.println("Service Insertion Failed");
                            }
                        }

                        servicesBEANList.remove(servicesBEAN);
                        if (servicesBEANList.size() < 1) {
                            ServicesBEAN emptyBEAN = new ServicesBEAN();
                            servicesBEANList.add(emptyBEAN);
                            addServiceGridRows(servicesBEANList);
                        } else {
                            addServiceGridRows(servicesBEANList);

                        }

                    }
                }
            });
            /* ======================== Bindings ==================== */
            Bindings.bindBidirectional(cmbServices.valueProperty(), servicesBEAN.serviceProperty());
            Bindings.bindBidirectional(cmbServiceCountry.valueProperty(), servicesBEAN.countryProperty());
            Bindings.bindBidirectional(cmbServiceLocation.valueProperty(), servicesBEAN.locationProperty());
            /* ======================== Add to grid ==================== */
            if (servicesRowBEANList.indexOf(servicesBEAN) == 0) {
                Label lblProgram = new Label("Program");
                Text txtMandatory = new Text("*");
                txtMandatory.setStyle("-fx-fill:red");
                lblProgram.setGraphic(txtMandatory);
                lblProgram.setContentDisplay(ContentDisplay.RIGHT);
                gridServices.add(lblProgram, 0, servicesRowBEANList.indexOf(servicesBEAN));
            }
            gridServices.add(cmbServices, 1, servicesRowBEANList.indexOf(servicesBEAN));
            gridServices.add(cmbServiceCountry, 2, servicesRowBEANList.indexOf(servicesBEAN));
            gridServices.add(cmbServiceLocation, 3, servicesRowBEANList.indexOf(servicesBEAN));
            gridServices.add(boxDft, 4, servicesRowBEANList.indexOf(servicesBEAN));
        }
    }

    /**
     *
     */
    public void initDynamicContactControlls() {
        ContactNumberBEAN bEAN = new ContactNumberBEAN();
        addContactGridRows();
    }

    /**
     *
     * @param contactNumberBEANs
     * @return
     */
    public boolean validateContactList(List<ContactNumberBEAN> contactNumberBEANs) {
        boolean flag = true;

        System.out.println("enquiry bean :: " + enquiryDetailsBEAN.toString());
        if (enquiryDetailsBEAN.getStdcode1() == null || enquiryDetailsBEAN.getStdcode2() == null
                || enquiryDetailsBEAN.getContactNumber1() == null || enquiryDetailsBEAN.getContactNumber2() == null
                || enquiryDetailsBEAN.getStdcode1().equals("") || enquiryDetailsBEAN.getStdcode2().equals("")
                || enquiryDetailsBEAN.getContactNumber1().equals("") || enquiryDetailsBEAN.getContactNumber2().equals("")) {
            flag = false;
        }
        System.out.println("test 1 :: " + flag);
        for (ContactNumberBEAN numberBEAN : contactNumberBEANs) {
            if ((numberBEAN.getContactNumber1() == null || numberBEAN.getContactNumber2() == null || numberBEAN.getStdIsd1() == null || numberBEAN.getStdIsd2() == null)) {
                flag = false;
            }
        }
        System.out.println("test 2 :: " + flag);

        return flag;
    }

    /**
     *
     */
    public void addContactGridRows() {
        gridContactNumber.getChildren().clear();
        /* ======================== Add Default Row ==================== */
        Button btnDftPlus = new Button();
        btnDftPlus.getStyleClass().add("plus-button");
        btnDftPlus
                .setCursor(new ImageCursor(new Image(INALoginForm.class
                        .getResourceAsStream("images/cursor_green.png"))));
        Button btnDftClose = new Button();
        btnDftClose.getStyleClass().add("close-button");
        btnDftClose
                .setCursor(new ImageCursor(new Image(INALoginForm.class
                        .getResourceAsStream("images/cursor_red.png"))));
        HBox boxDft = new HBox();
        btnDftPlus.setFocusTraversable(false);
        btnDftClose.setFocusTraversable(false);
        boxDft.getChildren().addAll(btnDftPlus, btnDftClose);
        boxDft.setSpacing(3);

        Label lblContactNo = new Label("Contact Number");
        Text txtMandatory = new Text("*");
        txtMandatory.setStyle("-fx-fill:red");
        lblContactNo.setGraphic(txtMandatory);
        lblContactNo.setContentDisplay(ContentDisplay.RIGHT);

        gridContactNumber.add(lblContactNo, 0, 0);
        gridContactNumber.add(txtStdIsd, 1, 0);
        gridContactNumber.add(txtContactNumber, 2, 0);
        gridContactNumber.add(txtStdIsd2, 3, 0);
        gridContactNumber.add(txtContactNumber2, 4, 0);
        gridContactNumber.add(boxDft, 5, 0);

        btnDftPlus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (contactNumberBEANList.size() < CONTACT_LIMIT) {
                    if (validateContactList(contactNumberBEANList)) {
                        ContactNumberBEAN emptyBEAN = new ContactNumberBEAN();
                        contactNumberBEANList.add(emptyBEAN);
                        System.out.println("test data number :: " + contactNumberBEANList.toString());
                        addContactGridRows();
                    } else {
                        showErrors.showError("All fields required !", gridContactNumber);
                    }
                }
            }
        });

        btnDftClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage
                        .getIcons().add(new Image(INALoginForm.class
                                .getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnEnquirySubmit.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {

                }
            }
        });

        /* ======================== End Default Row ==================== */
        for (ContactNumberBEAN contactNumberBEAN : contactNumberBEANList) {
            TextField txtDynStdIsd1 = new TextField();
            txtDynStdIsd1.setPromptText("STD/ISD");
            txtDynStdIsd1.getStyleClass().add("txt-style");
            TextField txtDynStdIsd2 = new TextField();
            txtDynStdIsd2.setPromptText("STD/ISD");
            txtDynStdIsd2.getStyleClass().add("txt-style");
            TextField txtDynContactNumber1 = new TextField();
            txtDynContactNumber1.getStyleClass().add("txt-style");
            txtDynContactNumber1.setPromptText("Contact Number" + (contactNumberBEANList.indexOf(contactNumberBEAN) + 3));
            TextField txtDynContactNumber2 = new TextField();
            txtDynContactNumber2.getStyleClass().add("txt-style");
            txtDynContactNumber2.setPromptText("Contact Number" + (contactNumberBEANList.indexOf(contactNumberBEAN) + 4));

            /* ======================== Buttons ==================== */
            Button btnDynPlus = new Button();
            btnDynPlus.getStyleClass().add("plus-button");
            btnDynPlus
                    .setCursor(new ImageCursor(new Image(INALoginForm.class
                            .getResourceAsStream("images/cursor_green.png"))));
            Button btnDynClose = new Button();
            btnDynClose.getStyleClass().add("close-button");
            btnDynClose
                    .setCursor(new ImageCursor(new Image(INALoginForm.class
                            .getResourceAsStream("images/cursor_red.png"))));
            HBox box = new HBox();
            box.getChildren().addAll(btnDynPlus, btnDynClose);
            btnDynPlus.setFocusTraversable(false);
            btnDynClose.setFocusTraversable(false);
            box.setSpacing(3);
            /* ======================== Button Events ==================== */
 /* ======================== End Button Events ==================== */
            btnDynPlus.setOnAction((ActionEvent event) -> {
                if (contactNumberBEANList.size() < CONTACT_LIMIT) {
                    if (validateContactList(contactNumberBEANList)) {
                        ContactNumberBEAN emptyBEAN = new ContactNumberBEAN();
                        contactNumberBEANList.add(emptyBEAN);
                        System.out.println("test data number :: " + contactNumberBEANList.toString());
                        addContactGridRows();
                    } else {
                        showErrors.showError("All fields required !", gridContactNumber);
                    }
                }
            });

            btnDynClose.setOnAction((ActionEvent event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage
                        .getIcons().add(new Image(INALoginForm.class
                                .getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnEnquirySubmit.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    contactNumberBEANList.remove(contactNumberBEAN);
                    if (contactNumberBEAN.getRowId() != null) {
                        contactNumbersDAO.deleteContactNumber(contactNumberBEAN.getRowId());
                    }
                    addContactGridRows();
                }
                System.out.println("Test Bean :: " + contactNumberBEAN.toString());
            });
            /* ====================== Input Validations ====================== */
            txtDynContactNumber1.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {

                        if (newValue.matches("[0-9]*")) {
                            if (newValue.length() >= 10) {
                                Bindings.unbindBidirectional(contactNumberBEAN.contactNumber1Property(), txtDynContactNumber1.textProperty());
                                txtDynContactNumber1.setText(oldValue);
                                Bindings.bindBidirectional(contactNumberBEAN.contactNumber1Property(), txtDynContactNumber1.textProperty());

                            } else {
                                txtDynContactNumber1.setText(newValue);
                            }

                        } else {
                            Bindings.unbindBidirectional(contactNumberBEAN.contactNumber1Property(), txtDynContactNumber1.textProperty());
                            txtDynContactNumber1.setText(oldValue);
                            Bindings.bindBidirectional(contactNumberBEAN.contactNumber1Property(), txtDynContactNumber1.textProperty());

                        }
                    }
                }
            });
            txtDynContactNumber2.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {

                        if (newValue.matches("[0-9]*")) {
                            if (newValue.length() >= 10) {
                                Bindings.unbindBidirectional(contactNumberBEAN.contactNumber2Property(), txtDynContactNumber2.textProperty());
                                txtDynContactNumber2.setText(oldValue);
                                Bindings.bindBidirectional(contactNumberBEAN.contactNumber2Property(), txtDynContactNumber2.textProperty());

                            } else {
                                txtDynContactNumber2.setText(newValue);
                            }

                        } else {
                            Bindings.unbindBidirectional(contactNumberBEAN.contactNumber2Property(), txtDynContactNumber2.textProperty());
                            txtDynContactNumber2.setText(oldValue);
                            Bindings.bindBidirectional(contactNumberBEAN.contactNumber2Property(), txtDynContactNumber2.textProperty());

                        }
                    }
                }
            });
            AutoCompletionBinding<String> autoCompletionBindingStdCodes1 = TextFields.bindAutoCompletion(txtDynStdIsd1, stdCodes);
            AutoCompletionBinding<String> autoCompletionBindingStdCodes2 = TextFields.bindAutoCompletion(txtDynStdIsd2, stdCodes);
            autoCompletionBindingStdCodes2.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    autoCompletionBindingStdCodes2.setUserInput(null);
                }
            });
            autoCompletionBindingStdCodes1.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    autoCompletionBindingStdCodes1.setUserInput(null);
                }
            });
            txtDynStdIsd1.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        String stdArray[] = null;
                        stdArray = newValue.split("-");
                        if (!stdArray[0].equals("")) {
                            txtDynStdIsd1.setText(stdArray[0]);
                            if (txtDynStdIsd1.getText().length() >= 9) {
                                Bindings.unbindBidirectional(contactNumberBEAN.stdIsd1Property(), txtDynStdIsd1.textProperty());
                                txtDynStdIsd1.setText(oldValue);
                                Bindings.bindBidirectional(contactNumberBEAN.stdIsd1Property(), txtDynStdIsd1.textProperty());
                            } else if (stdArray[0].matches("[0-9,+]*")) {
                                txtDynStdIsd1.setText(stdArray[0]);
                            } else {
                                Bindings.unbindBidirectional(contactNumberBEAN.stdIsd1Property(), txtDynStdIsd1.textProperty());
                                txtDynStdIsd1.setText(oldValue);
                                Bindings.bindBidirectional(contactNumberBEAN.stdIsd1Property(), txtDynStdIsd1.textProperty());
                            }
                        }
                    }
                }
            });
            txtDynStdIsd2.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        String stdArray[] = null;
                        stdArray = newValue.split("-");
                        if (!stdArray[0].equals("")) {
                            txtDynStdIsd2.setText(stdArray[0]);
                            if (txtDynStdIsd2.getText().length() >= 9) {
                                Bindings.unbindBidirectional(contactNumberBEAN.stdIsd2Property(), txtDynStdIsd2.textProperty());
                                txtDynStdIsd2.setText(oldValue);
                                Bindings.bindBidirectional(contactNumberBEAN.stdIsd2Property(), txtDynStdIsd2.textProperty());
                            } else if (stdArray[0].matches("[0-9,+]*")) {
                                txtDynStdIsd2.setText(stdArray[0]);
                            } else {
                                Bindings.unbindBidirectional(contactNumberBEAN.stdIsd2Property(), txtDynStdIsd2.textProperty());
                                txtDynStdIsd2.setText(oldValue);
                                Bindings.bindBidirectional(contactNumberBEAN.stdIsd2Property(), txtDynStdIsd2.textProperty());
                            }
                        }
                    }
                }
            });
            /* ======================== Binding ==================== */
            Bindings.bindBidirectional(txtDynStdIsd1.textProperty(), contactNumberBEAN.stdIsd1Property());
            Bindings.bindBidirectional(txtDynStdIsd2.textProperty(), contactNumberBEAN.stdIsd2Property());
            Bindings.bindBidirectional(txtDynContactNumber1.textProperty(), contactNumberBEAN.contactNumber1Property());
            Bindings.bindBidirectional(txtDynContactNumber2.textProperty(), contactNumberBEAN.contactNumber2Property());
            gridContactNumber.add(new Label("Contact Numbers"), 0, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynStdIsd1, 1, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynContactNumber1, 2, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynStdIsd2, 3, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynContactNumber2, 4, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(box, 5, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
//            gridContactNumber.add(btnDynClose, 6, contactNumberBEANList.indexOf(contactNumberBEAN));
            System.out.println("INDEX OF BEAN " + contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
        }
    }

    /**
     *
     * @param branchName
     */
    public void addEmpStatusLoader(String branchName) {
        if (empStatusBranchCombo.getItems().contains(branchName)) {
            empStatusBranchCombo.getSelectionModel().clearAndSelect(empStatusBranchCombo.getItems().indexOf(branchName));
        }
        empStatusBranchCombo.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedBranch = newValue;
                addEmpStatusLoader(newValue);
            }
        });

    }

    /**
     *
     */
    public void addAppointmentSchedule() {

        ObservableList<AppointmentScheduleBEAN> appointmentTableList = appointmentScheduleSERVICE.retrieveApponitmentsAccepted(CUR_USERNAME, CUR_BRANCH);

        //   tPanappoinments.setText("Appointment Pending ( " + appointmentList.size() + " )");
        lblTitleAppPending.setText("Appointment Pending ( " + appointmentTableList.size() + " )");
        //full view
        btnAppointmentPending.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //  notifyTimer.pause();
                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/FXMLAgendaCalendar.fxml"));
                try {
                    Parent root = (Parent) loader.load();
//                        FXMLAppointmensFullviewController controller = (FXMLAppointmensFullviewController) loader.getController();
//                        controller.initData(appointmentList);
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout, 1000, 413);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Appointment Scheduler");
                    secondStage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ia_logo.png")));
                    secondStage.setScene(secondScene);
                    secondStage.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            //   notifyTimer.play();
                        }
                    });
//                        secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.initModality(Modality.WINDOW_MODAL);
                    secondStage.initOwner(lblLogout.getScene().getWindow());
                    secondStage.setMaximized(true);
                    secondStage.show();
                } catch (IOException exception) {
                    exception.printStackTrace();
                    logger.error(exception.getMessage());

                }

            }
        });

        //add new scheduling
        btnAppointmentSchedule.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //  notifyTimer.pause();
                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/FXMLAgendaCalendar.fxml"));
                try {
                    Parent root = (Parent) loader.load();
//                        FXMLAppointmensFullviewController controller = (FXMLAppointmensFullviewController) loader.getController();
//                        controller.initData(appointmentList);
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout, 1000, 413);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Appointment Scheduler");
                    secondStage.setScene(secondScene);
                    secondStage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ia_logo.png")));
//                        secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.initModality(Modality.WINDOW_MODAL);
                    secondStage.initOwner(lblLogout.getScene().getWindow());
                    secondStage.setMaximized(true);
                    secondStage.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            DBPool.getInstance().CloseConnections();
                            //  notifyTimer.pause();
                        }
                    });
                    secondStage.show();
                } catch (IOException exception) {
                    exception.printStackTrace();
                    logger.error(exception.getMessage());

                }

            }
        });
    }

    /**
     *
     */
    public void addFollowUpRegister() {
        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCountsFollowup(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);

        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            if (inboxCountsBEAN.getStatusId() != null) {
                if (inboxCountsBEAN.getStatusId().equals("-1")) {
                    lblTitleFollowUp.setText("Follow Up Pending ( " + inboxCountsBEAN.getTotalCount() + " )");
                    folloupsize = Integer.parseInt(inboxCountsBEAN.getTotalCount());
                }
            }
        }

        btnFollowUpPending.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (folloupsize > 0) {
                    notifyTimer.pause();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/followup/FXMLFollowUpFulllview.fxml"));
                    try {
                        Parent root = (Parent) loader.load();
                        System.out.println("Check Folllow Click!");
                        FXMLFollowUpFulllviewController controller = (FXMLFollowUpFulllviewController) loader.getController();
                        //   controller.initData(followuplist);
                        Scene secondScene = new Scene(root);
                        Stage secondStage = new Stage();
                        secondStage.setTitle("Follow Up Register");
                        secondStage.setScene(secondScene);
                        secondStage
                                .getIcons().add(new Image(INALoginForm.class
                                        .getResourceAsStream("images/ia_logo.png")));
                        secondStage.initStyle(StageStyle.UNDECORATED);
                        secondStage.initModality(Modality.WINDOW_MODAL);
                        secondStage.initOwner(lblLogout.getScene().getWindow());
                        secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                            @Override
                            public void handle(WindowEvent event) {
                                DBPool.getInstance().CloseConnections();
                                notifyTimer.play();
                                /* ========================  create executor that uses daemon threads: ==================== */
                                executor = Executors.newCachedThreadPool(runnable -> {
                                    Thread t = new Thread(runnable);
                                    t.setDaemon(true);
                                    return t;
                                });
                            }
                        }
                        );
                        secondStage.setMaximized(true);
                        secondStage.show();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                        logger.error(exception.getMessage());

                    }
                }
            }
        });

    }

    /**
     *
     */
    //Method for initialize comboboxes in study,work,migration and training
    /**
     *
     */
    public void addComboBoxEvents() {
        cmbMethod.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {
                    if (newValue.equalsIgnoreCase(DEFAULT_ENQUIRY_METHOD)) {
                        cmbAssign.disableProperty().set(false);
                        cmbAssign.getSelectionModel().clearSelection();
                        cmbDepartment.getSelectionModel().clearSelection();
                        cmbDepartment.disableProperty().set(false);
                    } else {
                        cmbAssign.disableProperty().set(true);
                        cmbAssign.getSelectionModel().clearSelection();
                        cmbDepartment.getSelectionModel().clearSelection();
                        cmbDepartment.disableProperty().set(true);
                    }

                }

            }
        });
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

                Parent root = null;
                /* ====================== Clear Login Audit ====================== */
                LoginFormDAO.deleteLoginAudit(CUR_USERNAME);
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
                notifyTimer.stop();

            }
        });
    }

    /**
     *
     */
    public void addNoticeWindow() {
        // create a window with title "My Window"
        Window w = new Window("Notice Board");
        // set the window position to 10,10 (coordinates inside canvas)
        w.setLayoutX(10);
        w.setLayoutY(10);
        // define the initial window size
        w.setPrefSize(300, 400);
        // either to the left
        //       w.getLeftIcons().add(new CloseIcon(w));
        // .. or to the right
        w.getRightIcons().add(new MinimizeIcon(w));
        w.setTitleBarStyleClass("custom-window-titlebar");
        w.setStyle("-fx-border-color:#3C73C7");
        // add some content
        //  List<NewOffersPOJO> noticelist = NotificationsDAO.getOffers();
        List<NoticePOJO> noticeslist = NotificationsDAO.getNotices(CUR_BRANCH);
        NotificationsDAO.resetCounter(CUR_USERNAME, "notice");
        //    NotificationsDAO.resetCounter(CUR_USERNAME, "offer");
        int count = 0;

        URL notifyofrURLHTML = getClass().getResource("webview/notifications.html");
        WebView notifyofrWebView = new WebView();
        WebEngine notifyofrWebEngine = notifyofrWebView.getEngine();
        notifyofrWebEngine.load(notifyofrURLHTML.toExternalForm());
        // js controling
        notifyofrWebEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue, final Worker.State oldState, final Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    for (NoticePOJO npojo : noticeslist) {
                        notifyofrWebEngine.executeScript("updateNotice('" + npojo.getTitle() + "','" + npojo.getDescription() + "','" + npojo.getCreated_date() + "','" + npojo.getExpiration_date() + "')");

                        Element noticediv = (Element) notifyofrWebEngine.executeScript("document.getElementById('noticediv')");
                        noticediv.setAttribute("style", "display: block");
                    }
                }
            }
        });

        w.getContentPane().getChildren().add(notifyofrWebView);

        vboxNoticeBoard.getChildren().add(w);

    }

    /**
     *
     */
    public void addGlobalTimer() {
        TIME_CALC = 0;
        notifyTimer = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TIME_CALC++;

                //load autocompletion data every 2 minute
                if (TIME_CALC > 1 * 24) {
                    DBPool.getInstance().CloseConnections();
                    if (!serviceStaffCounts.isRunning()) {
                        serviceStaffCounts.reset();
                        serviceStaffCounts.restart();
                    }
                    if (!serviceEmployeeStatusCounts.isRunning()) {
                        serviceEmployeeStatusCounts.reset();
                        serviceEmployeeStatusCounts.restart();
                    }
                    if (!serviceCounselorStatusCounts.isRunning()) {
                        serviceCounselorStatusCounts.reset();
                        serviceCounselorStatusCounts.restart();
                    }
//                    List<StaffCountPOJO> countList = EnquiryDetailsDAO.getStaffEnquiryCount(CUR_BRANCH);
//                    addStaffCounts(countList);
                    // initializeAutocompletionData();
                    //      addEmpStatusLoader(selectedBranch);
                    TIME_CALC = 0;
                }
                /* ====================== Check Reminder For every half a minute ====================== */
                if (TIME_CALC > 0) {
                    if (TIME_CALC / 6 == 1) {
                        if (!serviceReminder.isRunning()) {
                            serviceReminder.reset();
                            serviceReminder.restart();
                        }
                        if (!serviceNotice.isRunning()) {
                            serviceNotice.reset();
                            serviceNotice.restart();
                        }
                    }

                }
            }
        }));
        notifyTimer.setCycleCount(Timeline.INDEFINITE);
        notifyTimer.play();

    }

    /**
     *
     * @param countList
     */
    public void addStaffCounts(List<StaffCountPOJO> countList) {
        tblEnquiryEntryStat.getItems().clear();
        for (StaffCountPOJO countPOJO : countList) {
            if (countPOJO.getUsername().equalsIgnoreCase(CUR_USERNAME)) {
                lblTitleEnqReport.setText("");
                lblTitleEnqReport.setText("Enquiry Report (Today " + countPOJO.getCount() + ")");
                break;
            }
        }

        clmEstatName.setCellValueFactory(
                new PropertyValueFactory<>("fullname"));
        clmEstatStatus.setCellValueFactory(
                new PropertyValueFactory<>("count"));
        clmEstatFake.setCellValueFactory(
                new PropertyValueFactory<>("fake"));
        tblEnquiryEntryStat.getItems().setAll(countList);
    }

    /**
     *
     */
    public void addChangeEvents() {

        /* ======================== An Event for enquiry id change  ==================== */
        enquiryDetailsBEAN.enquiryIDProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    System.out.println("id changed noww");
                    btnEnquirySubmit.setText("Update");
                    showHideStatusRemarksArea(true);
                } else {
                    btnEnquirySubmit.setText("Add Enquiry");
                    System.out.println("id changed noww");
                }

            }
        });
//        txtContactNumber = new TextField() {
//            @Override
//            public void replaceText(int start, int end, String text) {
//                if (text.matches("[0-9]*")) {
//                    super.replaceText(start, end, text);
//                }
//            }
//
//            @Override
//            public void replaceSelection(String text) {
//                if (!text.matches("[0-9]*")) {
//                    super.replaceSelection(text);
//                }
//            }
//        };
//        txtContactNumber.getStyleClass().add("searchIcon");
        txtContactNumber2.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if (txtStdIsd2.getText() != null && txtContactNumber2.getText() != null) {
                        if ((txtStdIsd2.getText().startsWith("+") && txtContactNumber2.getText().length() >= 11) || txtContactNumber2.getText().startsWith("0")) {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                            txtContactNumber2.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());

                        } else if (txtStdIsd2.getText().startsWith("0") && txtContactNumber2.getText().length() >= 8) {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                            txtContactNumber2.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                        } else if (newValue.matches("[0-9]*")) {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                            txtContactNumber2.setText(newValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());

//                        clearScreen2();
                        } else {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                            txtContactNumber2.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                        }
                    }
                }
            }
        });

    }

    /**
     *
     */
    public void initializeComboBoxes() {
        /* ======================== Load Enquiry Method with default ==================== */
        mapEnquiryMethod = RetrieveStaticMasterDAO.retrieveEnquiryMethod();
        Set<String> keys = mapEnquiryMethod.keySet();
        if (keys.isEmpty()) {
            DEFAULT_ENQUIRY_METHOD = "Direct";
        }
        for (String key : keys) {
            obsEnquiryMethods.clear();
            obsEnquiryMethods = mapEnquiryMethod.get(key);
            DEFAULT_ENQUIRY_METHOD = key;
            System.out.println("Default Enquiry Method :: " + key);
        }
        /* ======================== Load Enquiry Application Status ==================== */
        obsEnquiryStatus = RetrieveStaticMasterDAO.retrieveEnquiryAppStatus();

        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        for (String s : branches) {
            branch.add(s);
        }
        cmbBranch.setItems(branch);
//        if (cmbBranch.getItems().contains(CUR_BRANCH)) {
//            cmbBranch.getSelectionModel().clearAndSelect(cmbBranch.getItems().indexOf(CUR_BRANCH));
//        }
        empStatusBranchCombo.setItems(branch);
        loadcmbSource();

        List<String> departments = RetrieveStaticMasterDAO.getAllDepartment();
        for (String departmenttt : departments) {
            department.add(departmenttt);
        }
        cmbDepartment.setItems(department);
        ObservableList<CounselorsListPOJO> sourceAssign = RetrieveStaticMasterDAO.getAllCounselors(CUR_BRANCH);
        cmbAssign.setItems(sourceAssign);

        cmbCountry.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    ObservableList states = FXCollections.observableArrayList();
                    List<String> state = MasterCountryStateDistrictDAO.getAllStates(newValue);

                    for (String s : state) {
                        states.add(s);
                    }
                    cmbState.setItems(states);
                }
            }
        });

        cmbCountry.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    // cmbServiceLocation.getSelectionModel().selectFirst();
                    Task<List<String>> taskLoadAddressState = new Task<List<String>>() {
                        @Override
                        protected List<String> call() throws Exception {
                            return MasterCountryStateDistrictDAO.getAllStates(newValue.toString());
                        }
                    };
                    taskLoadAddressState.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                        @Override
                        public void handle(WorkerStateEvent event) {
                            ObservableList states = FXCollections.observableArrayList();

                            for (String s : taskLoadAddressState.getValue()) {
                                states.add(s);
                            }
                            cmbState.setItems(states);
                        }
                    });
                    taskLoadAddressState.run();
                }
            }
        });

        cmbState.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    // cmbServiceLocation.getSelectionModel().selectFirst();
                    Task<List<String>> taskLoadAddressDistricts = new Task<List<String>>() {
                        @Override
                        protected List<String> call() throws Exception {
                            return MasterCountryStateDistrictDAO.getAllDistricts(newValue.toString());
                        }
                    };
                    taskLoadAddressDistricts.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                            ObservableList districts = FXCollections.observableArrayList();

                            for (String s : taskLoadAddressDistricts.getValue()) {
                                districts.add(s);
                            }
                            cmbDistrict.setItems(districts);
                            cmbDistrict.getSelectionModel().selectFirst();
                        }
                    });
                    taskLoadAddressDistricts.run();
                }
            }
        });

        List<String> countrylist = MasterCountryStateDistrictDAO.getAllCountries();
        for (String c : countrylist) {
            country.add(c);
        }
        cmbCountry.setItems(country);
        for (String c : cmbCountry.getItems()) {
            if (c != null) {
                if (c.toUpperCase().equalsIgnoreCase("INDIA")) {
                    cmbCountry.getSelectionModel().select(c);
                }
            }
        }

        for (String s : cmbState.getItems()) {
            if (s != null) {
                if (s.toUpperCase().equalsIgnoreCase("KERALA")) {
                    cmbState.getSelectionModel().select(s);
                }
            }
        }

//        cmbPhoneType.setItems(contact);
//        cmbPhoneType.getSelectionModel().selectFirst();
        cmbMethod.setItems(obsEnquiryMethods);

        if (!DEFAULT_ENQUIRY_METHOD.equals(cmbMethod.getSelectionModel().getSelectedItem())) {
//            cmbAssign.disableProperty().set(true);
            cmbAssign.setDisable(true);
            cmbDepartment.setDisable(true);
        } else {
//            cmbAssign.disableProperty().set(false);
            cmbAssign.setDisable(false);
            cmbDepartment.setDisable(false);
            cmbAssign.getSelectionModel().clearSelection();
            cmbDepartment.getSelectionModel().clearSelection();

        }

    }

    /**
     *
     */
    public void addCounselorStatus() {
        List<CounselorWorkCount> resList = new ArrayList<>();
        resList = EnquirySearchDAO.getCounselorWorkDetails(CUR_BRANCH);
        for (CounselorWorkCount pojo : resList) {
            masterDataCounselor.add(pojo);
        }
        clmCounselorName.setCellValueFactory(
                new PropertyValueFactory<>("cname"));

        clmPending.setCellValueFactory(
                new PropertyValueFactory<>("pendingCount"));
        clmAssesed.setCellValueFactory(
                new PropertyValueFactory<>("assesed"));

        tblCounselorStatus.getItems().setAll(resList);
        //make equal width columns
        tblCounselorStatus.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newWidth = newValue.doubleValue() / 4;
                clmCounselorName.setPrefWidth(newWidth);
                clmAssesed.setPrefWidth(newWidth);
                clmPending.setPrefWidth(newWidth);
            }
        });

    }

    /**
     *
     */
    public void initializeAutocompletionData() {
        conatctNumbers.clear();
        contactEmails.clear();
        contactNames.clear();
//        conatctNumbers = EnquiryDetailsDAO.getAllContactNumbers();
        listContactnumbers = EnquiryDetailsDAO.searchPhoneNumbers("");
        contactEmails = EnquiryDetailsDAO.getAllContactEmails();
        contactNames = EnquiryDetailsDAO.getAllContactNames();
        enquiryList.clear();
        enquiryList = EnquiryDetailsDAO.patternSearchEnquiryDetails("");

    }

    /**
     *
     */
    public void autoCompletion() {
        /* ========================  create executor that uses daemon threads: ==================== */
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        // txtContactNumber = TextFields.createSearchField();
        txtContactEmail.setPromptText("Enter Email-ID");
        //  AutoCompletionBinding<String> autoCompletionBindingNames = TextFields.bindAutoCompletion(txtContactName, contactNames);
        /* ======================== Autocompletion For Email Id's ==================== */
        ObservableList<EnquiryDetailsSearchPOJO> autoCompletionEmailList = FXCollections.observableArrayList();
        for (EnquiryDetailsSearchPOJO autoBEAN : enquiryList) {
            EnquiryDetailsSearchPOJO autoCompletionBEAN = new EnquiryDetailsSearchPOJO() {
                @Override
                public String toString() {
                    return autoBEAN.getContactMail();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            autoCompletionEmailList.add(autoCompletionBEAN);
        }
        AutoCompletionBinding<EnquiryDetailsSearchPOJO> autoCompletionBindingEmail = TextFields.bindAutoCompletion(txtContactEmail, autoCompletionEmailList);

        /* ======================== Autocompletion For Contact Names ==================== */
        ObservableList<EnquiryDetailsSearchPOJO> autoCompletionNamesList = FXCollections.observableArrayList();
        for (EnquiryDetailsSearchPOJO autoBEAN : enquiryList) {
            EnquiryDetailsSearchPOJO autoCompletionBEAN = new EnquiryDetailsSearchPOJO() {
                @Override
                public String toString() {
                    return autoBEAN.getConatctName();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            autoCompletionNamesList.add(autoCompletionBEAN);
        }
        AutoCompletionBinding<EnquiryDetailsSearchPOJO> autoCompletionBindingNames = TextFields.bindAutoCompletion(txtContactName, autoCompletionNamesList);

        /* ======================== Auto Completion For Contact Numbers ==================== */
        ObservableList<EnquiryDetailsSearchPOJO> autoCompletionPhoneList = FXCollections.observableArrayList();
        for (EnquiryDetailsSearchPOJO autoBEAN : enquiryList) {
            EnquiryDetailsSearchPOJO autoCompletionBEAN = new EnquiryDetailsSearchPOJO() {
                @Override
                public String toString() {
                    return autoBEAN.getContactNumber() + " - " + autoBEAN.getContact_number2();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            autoCompletionPhoneList.add(autoCompletionBEAN);
        }
        AutoCompletionBinding<EnquiryDetailsSearchPOJO> autoCompletionBindingPhone = TextFields.bindAutoCompletion(txtContactNumber, autoCompletionPhoneList);
        autoCompletionBindingPhone.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<EnquiryDetailsSearchPOJO>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<EnquiryDetailsSearchPOJO> event) {
                if (event.getCompletion() != null) {
                    if (event.getCompletion().getEnquiryId() != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ServicesDAO servicesDAO = new ServicesIMPL();
                                    EnquiryDetailsDAO.getEnquiryById(event.getCompletion().getEnquiryId(), enquiryDetailsBEAN);
                                    System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()789====" + enquiryDetailsBEAN.getEnquiryAssignedTo());
                                    bindAllEnquiryControls();
                                    contactNumberBEANList = contactNumbersDAO.retrieveAllContactNumbers(event.getCompletion().getEnquiryId());
                                    initDynamicContactControlls();
                                    CURR_COUNSELOR = detailsSearchPOJO.getAssign();
                                    servicesBEANList.clear();
                                    servicesBEANList = servicesDAO.getAllServices(event.getCompletion().getEnquiryId());
                                    if (servicesBEANList.size() > 0) {
                                        addServiceGridRows(servicesBEANList);
                                    } else {
                                        initDynamicServiceControls();
                                    }
                                    trainingBeanList.clear();
                                    trainingBeanList = ProgramSuggestedRequiredDAO.getTrainingDetails(event.getCompletion().getEnquiryId());
                                    if (trainingBeanList.size() > 0) {
                                        addTrainigGridRows();
                                    } else {
                                        initDynamicTrainingControls();
                                    }
//                            txtContactNumber.textProperty().addListener(changeListenerPhone);
                                } catch (NullPointerException ne) {
                                    System.out.println("Null Inside Platform run");
                                    ne.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        });
        /* ======================== Auto Completion For Commom Search Box ==================== */
        ObservableList<EnquiryDetailsSearchPOJO> autoCompletionSearchList = FXCollections.observableArrayList();
        for (EnquiryDetailsSearchPOJO autoBEAN : enquiryList) {
            EnquiryDetailsSearchPOJO autoCompletionBEAN = new EnquiryDetailsSearchPOJO() {
                @Override
                public String toString() {
                    return autoBEAN.getConatctName() + " " + autoBEAN.getContactNumber() + " " + autoBEAN.getContactMail();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            autoCompletionSearchList.add(autoCompletionBEAN);
        }
        AutoCompletionBinding<EnquiryDetailsSearchPOJO> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtSearchUpdate, autoCompletionSearchList);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<EnquiryDetailsSearchPOJO>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<EnquiryDetailsSearchPOJO> event) {
                if (event.getCompletion() != null) {
                    if (event.getCompletion().getEnquiryId() != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ServicesDAO servicesDAO = new ServicesIMPL();
                                    EnquiryDetailsDAO.getEnquiryById(event.getCompletion().getEnquiryId(), enquiryDetailsBEAN);
                                    System.out.println("enquiryDetailsBEAN.getEnquiryAssignedTo()789====" + enquiryDetailsBEAN.getEnquiryAssignedTo());
                                    bindAllEnquiryControls();
                                    contactNumberBEANList = contactNumbersDAO.retrieveAllContactNumbers(event.getCompletion().getEnquiryId());
                                    initDynamicContactControlls();
                                    CURR_COUNSELOR = detailsSearchPOJO.getAssign();
                                    servicesBEANList.clear();
                                    servicesBEANList = servicesDAO.getAllServices(event.getCompletion().getEnquiryId());
                                    if (servicesBEANList.size() > 0) {
                                        addServiceGridRows(servicesBEANList);
                                    } else {
                                        initDynamicServiceControls();
                                    }
                                    trainingBeanList.clear();
                                    trainingBeanList = ProgramSuggestedRequiredDAO.getTrainingDetails(event.getCompletion().getEnquiryId());
                                    if (trainingBeanList.size() > 0) {
                                        addTrainigGridRows();
                                    } else {
                                        initDynamicTrainingControls();
                                    }
//                            txtContactNumber.textProperty().addListener(changeListenerPhone);
                                } catch (NullPointerException ne) {
                                    System.out.println("Null Inside Platform run");
                                    ne.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        });
        //  txtContactName.autosize();
        txtContactName.setPromptText("Enter Name");
        //txtSearchUpdate = TextFields.createSearchField();
        /* ======================== Change Event for contact numbers ==================== */

        ChangeListener<String> changeListenerPhone = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    ServicesDAO servicesDAO = new ServicesIMPL();
                    if ((txtStdIsd.getText().startsWith("+") && txtContactNumber.getText().length() >= 11) || txtContactNumber.getText().startsWith("0")) {
                        Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                        txtContactNumber.setText(oldValue);
                        Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                    } else if (txtStdIsd.getText().startsWith("0") && txtContactNumber.getText().length() >= 8) {
                        Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                        txtContactNumber.setText(oldValue);
                        Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                    } else if (newValue.matches("[0-9]*")) {
                        txtContactNumber.setText(newValue);
//                        if (!newValue.equalsIgnoreCase("")) {
//                            for (Task< List<PhoneSearchPOJO>> taskPhoneOne : tasksPhoneAllRunning) {
//                                taskPhoneOne.cancel();
//                            }
//                            Task< List<PhoneSearchPOJO>> taskPhonenSearch = new Task<List<PhoneSearchPOJO>>() {
//                                @Override
//                                protected List<PhoneSearchPOJO> call() throws Exception {
//                                    return EnquiryDetailsDAO.searchPhoneNumbers(newValue);
//                                }
//                            };
//                            tasksPhoneAllRunning.add(taskPhonenSearch);
//                            taskPhonenSearch.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//                                @Override
//                                public void handle(WorkerStateEvent event) {
//                                    System.out.println("Success");
//                                    if (!listContactnumbers.containsAll(taskPhonenSearch.getValue())) {
//                                        System.out.println("Not contains");
//                                        System.out.println("Test :: " + listContactnumbers.toString());
//                                        listContactnumbers = taskPhonenSearch.getValue();
//                                        hintsContactNumbers.updateHints(listContactnumbers);
//                                        hintsContactNumbers.showHints();
//                                    }
//                                }
//                            });
//                            executor.execute(taskPhonenSearch);
//                            /* ====================== Check Existing Number in autocompletion list ====================== */
//                            if (hintsContactNumbers.getCompletionList().contains(newValue)) {
//                                System.out.println("No Already Exists");
//                            }
//                            if (hintsContactNumbers.getSelectedHint() != null) {
//                                System.out.println("Selected Phone Number " + hintsContactNumbers.getSelectedHint());
//                                PhoneSearchPOJO phoneSearchPOJO = (PhoneSearchPOJO) hintsContactNumbers.getSelectedHint();
//
//                                /* ======================== Load data from db ==================== */
//                                if (phoneSearchPOJO.getEnquiryId() != null) {
//                                    cmbBranch.getSelectionModel().clearSelection();
//                                    EnquiryDetailsDAO.getEnquiryById(phoneSearchPOJO.getEnquiryId(), enquiryDetailsBEAN);
//                                    contactNumberBEANList = contactNumbersDAO.retrieveAllContactNumbers(phoneSearchPOJO.getEnquiryId());
//                                    initDynamicContactControlls();
//                                    CURR_COUNSELOR = enquiryDetailsBEAN.getEnquiryAssignedTo();
//                                    servicesBEANList.clear();
//                                    servicesBEANList = servicesDAO.getAllServices(phoneSearchPOJO.getEnquiryId());
//                                    if (servicesBEANList.size() > 0) {
//                                        addServiceGridRows(servicesBEANList);
//                                    } else {
//                                        initDynamicServiceControls();
//                                    }
//                                    trainingBeanList.clear();
//                                    trainingBeanList = ProgramSuggestedRequiredDAO.getTrainingDetails(phoneSearchPOJO.getEnquiryId());
//                                    if (trainingBeanList.size() > 0) {
//                                        addTrainigGridRows();
//                                    } else {
//                                        initDynamicTrainingControls();
//                                    }
//                                }
//                                //      hintsContactNumbers.updateHints(listContactnumbers);
//                            }
//
//                        }

                    } else {
                        Bindings.unbindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                        txtContactNumber.setText(oldValue);
                        Bindings.bindBidirectional(enquiryDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                    }
                }
            }
        };

        /* ======================== Contact Number Auto Completion ==================== */
        txtContactNumber.textProperty().addListener(changeListenerPhone);

    }

    /**
     *
     * @param val
     */
    public void showHideStatusRemarksArea(boolean val) {
        /* ====================== Show Application Status And Remarks ====================== */
        hboxRemarks.setVisible(val);
        hboxRemarks.setManaged(val);
        lblAppStatus.setVisible(val);
        lblAppStatus.setManaged(val);
        btnAssessNow.setVisible(false);
        btnAssessNow.setManaged(false);
    }

    /**
     *
     */
    public void autocompleteLandPhoneCodes() {
        stdCodes = RetrieveStaticMasterDAO.getStdCodes();
        //  txtStdIsd = TextFields.createSearchField();
        //  txtStdIsd.prefWidth(108);
        txtStdIsd.setPromptText("STD/ISD Code");
        //  stdCodeHbox.getChildren().remove(0);
        //  stdCodeHbox.getChildren().add(txtStdIsd);

    }

    /**
     *
     */
    public void autocompleteMobileCodes() {
        stdCodes = RetrieveStaticMasterDAO.getMobCodes();
        //  txtStdIsd = TextFields.createSearchField();
        ListDataIntelliHints hintsStdCodes = new ListDataIntelliHints(txtStdIsd, stdCodes);
        hintsStdCodes.setCaseSensitive(false);
        ListDataIntelliHints hintsStdCodes2 = new ListDataIntelliHints(txtStdIsd2, stdCodes);
        hintsStdCodes2.setCaseSensitive(false);

        //  txtStdIsd.autosize();
        //    txtStdIsd.setPromptText("Country Code");
        //    stdCodeHbox.getChildren().remove(0);
        //   stdCodeHbox.getChildren().add(txtStdIsd);
        // txtStdIsd.setText("+91");
        txtStdIsd.setPromptText("Country code");
        txtStdIsd2.setPromptText("Country code");

        txtStdIsd.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String stdArray[] = null;
                    stdArray = newValue.split("-");
                    if (!stdArray[0].equals("")) {
                        txtStdIsd.setText(stdArray[0]);
                        if (txtStdIsd.getText().length() >= 9) {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                        } else if (stdArray[0].matches("[0-9,+]*")) {
                            txtStdIsd.setText(stdArray[0]);
                        } else {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                        }
                    }
                }
            }
        });

        txtStdIsd2.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String stdArray[] = null;
                    stdArray = newValue.split("-");
                    if (!stdArray[0].equals("")) {
                        txtStdIsd2.setText(stdArray[0]);
                        if (txtStdIsd2.getText().length() >= 9) {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                            txtStdIsd2.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                        } else if (stdArray[0].matches("[0-9,+]*")) {
                            txtStdIsd2.setText(stdArray[0]);
                        } else {
                            Bindings.unbindBidirectional(enquiryDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                            txtStdIsd2.setText(oldValue);
                            Bindings.bindBidirectional(enquiryDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                        }
                    }
                }
            }
        });

    }

    /**
     *
     * @param node
     * @param msg
     */
    /**
     *
     */
    public void showPopUpMessages() {
        final Popup popup = new Popup();
        popup.setX(300);
        popup.setY(200);
        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
        popup.show(gridServices.getScene().getWindow(), gridServices.getScene().getWindow().getX(), gridServices.getScene().getWindow().getY());
        popup.setAutoHide(true);
    }

    /**
     *
     * @param node
     * @param msg
     * @param k
     */
    /**
     *
     * @return
     */
    public boolean ValidateEnquiryForm() {
        changeToDefaultBorder();
        //  usernameValidator.hide();
        boolean f = true;

        if (enquiryDetailsBEAN.getContactName() == null || enquiryDetailsBEAN.getContactName().equalsIgnoreCase("")) {
            showErrors.showError("Please Enter Contact Name !", txtContactName);
//            showValdatorMsg(txtContactName, "Please Enter Contact Name !");
            f = false;
        } else if (enquiryDetailsBEAN.getContactEmail() != null) {
            if (!enquiryDetailsBEAN.getContactEmail().equalsIgnoreCase("")) {
                if (mailValidation() == false) {
                    showErrors.showError("Please Enter Valid Email  !", txtContactEmail);
                    f = false;
                }

            }

        } else if (enquiryDetailsBEAN.getStdcode1() == null || enquiryDetailsBEAN.getStdcode1().equalsIgnoreCase("")) {
//            showValdatorMsg(txtStdIsd, "Please Enter Std Code !");
            showErrors.showError("Please Enter Std Code !", txtStdIsd);
            f = false;
        } else if (enquiryDetailsBEAN.getContactNumber1() == null || enquiryDetailsBEAN.getContactNumber1().equalsIgnoreCase("") || enquiryDetailsBEAN.getContactNumber1().length() < 10) {
//            showValdatorMsg(txtContactNumber, "Please Enter Contact Number !");
            showErrors.showError("Please enter a valid Contact Number !", txtContactNumber);
            System.out.println("Testing Contact Numberrrr :: " + enquiryDetailsBEAN.getContactNumber1());

            f = false;
        } else if (enquiryDetailsBEAN.getDistrict() == null || enquiryDetailsBEAN.getDistrict().equalsIgnoreCase("")) {
//            showValdatorMsg(cmbDistrict, "Please Choose District !");
            showErrors.showError("Please Choose District !", cmbDistrict);
            f = false;
        } else if (enquiryDetailsBEAN.getState() == null || enquiryDetailsBEAN.getState().equalsIgnoreCase("")) {
//            showValdatorMsg(cmbState, "Please Choose State !");
            showErrors.showError("Please Choose State !", cmbState);
            f = false;
        } else if (enquiryDetailsBEAN.getCountry() == null || enquiryDetailsBEAN.getCountry().equalsIgnoreCase("")) {
//            showValdatorMsg(cmbCountry, "Please Choose Country !");
            showErrors.showError("Please Choose Country !", cmbCountry);
            f = false;
        } else if (enquiryDetailsBEAN.getEnquiryMethod() == null || enquiryDetailsBEAN.getEnquiryMethod().equalsIgnoreCase("")) {
//            showValdatorMsg(cmbMethod, "Please Choose Enquiry Method !");
            showErrors.showError("Please Choose Enquiry Method !", cmbMethod);
            f = false;
        } else if (enquiryDetailsBEAN.getEnquirySource() == null || enquiryDetailsBEAN.getEnquirySource().equalsIgnoreCase("")) {
//            showValdatorMsg(cmbSource, "Please Choose Enquiry Source !");
            showErrors.showError("Please Choose Enquiry Source !", cmbSource);
            f = false;
        } else if (enquiryDetailsBEAN.getEnquiryMethod().equalsIgnoreCase(DEFAULT_ENQUIRY_METHOD)) {
            if (enquiryDetailsBEAN.getEnquiryAssignedTo() == null || enquiryDetailsBEAN.getEnquiryAssignedTo().equalsIgnoreCase("") || enquiryDetailsBEAN.getEnquiryAssignedTo().equalsIgnoreCase("Not assigned")) {
//                showValdatorMsg(cmbAssign, "Please Choose Counselor !");
                showErrors.showError("Please Choose Counselor !", cmbAssign);
                f = false;
            } else if (enquiryDetailsBEAN.getDepartment() == null || enquiryDetailsBEAN.getDepartment().equalsIgnoreCase("")) {
//            showValdatorMsg(cmbDepartment, "Please Choose Department !");
                showErrors.showError("Please Choose Department !", cmbDepartment);
                f = false;
            }
        } else if (enquiryDetailsBEAN.getBracnch() == null || enquiryDetailsBEAN.getBracnch().equalsIgnoreCase("")) {
            showErrors.showError("Please Choose Branch !", cmbBranch);
            f = false;
        } else if (validateServiceList(servicesBEANList) == false && validateTrainingData(trainingBeanList) == false) {
//            showValdatorMsg(gridServices, "Please Fil Atleast One Service Or Training !");
            showErrors.showError("Please Fill Atleast One Service Or Training !", gridServices);
            System.out.println("Both are false");
            f = false;
        }

        return f;

    }

    /**
     *
     * @return
     */
    public Boolean mailValidation() {
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email1 = txtContactEmail.getText().trim();
        Boolean b = email1.matches(EMAIL_REGEX);
        return b;
    }

    /**
     *
     */
    public void changeToDefaultBorder() {
        txtStdIsd.setStyle("-fx-border-color:#0000");
        txtContactEmail.setStyle("-fx-border-color:#0000");
        txtContactNumber.setStyle("-fx-border-color:#0000");
        txtContactName.setStyle("-fx-border-color:#0000");

        cmbAssign.setStyle("-fx-border-color:#0000");
        cmbSource.setStyle("-fx-border-color:#0000");
        cmbDistrict.setStyle("-fx-border-color:#0000");
        cmbMethod.setStyle("-fx-border-color:#0000");
        cmbState.setStyle("-fx-border-color:#0000");
    }

    /**
     *
     */
    public void clearScreen() {
        txtStdIsd.setStyle("-fx-border-color:#0000");
        txtContactEmail.setStyle("-fx-border-color:#0000");
        txtContactNumber.setStyle("-fx-border-color:#0000");
        txtContactName.setStyle("-fx-border-color:#0000");
        cmbAssign.setStyle("-fx-border-color:#0000");
        cmbSource.setStyle("-fx-border-color:#0000");
        cmbDepartment.setStyle("-fx-border-color:#0000");
        rating.setRating(0);
        txtContactNumber.clear();
        txtContactEmail.clear();
        txtContactName.clear();
        rating.setRating(0);
        PRIORITY = 0;
        btnEnquirySubmit.setText("Add Enquiry");
        cmbAssign.getSelectionModel().clearSelection();
        cmbAssign.setDisable(false);
        cmbBranch.setDisable(false);
        txtContactNumber2.clear();
        txtStdIsd2.clear();
        txtRemarks.clear();
        if (cmbSource.getSelectionModel().isEmpty()) {

        } else {
            cmbSource.getSelectionModel().clearAndSelect(0);
        }

        clearAllComboBoxes();

        starImageView
                .setImage(new Image(EnquiryMainViewController.class
                        .getResourceAsStream("images/starw.png")));
        /*==================== Clear Dynamic Control Details ======================== */
        trainingBeanList.clear();
        initDynamicTrainingControls();
        servicesBEANList.clear();
        initDynamicServiceControls();
        contactNumberBEANList.clear();
        initDynamicContactControlls();
        showHideStatusRemarksArea(true);
        txtSearchUpdate.setText("");
        /* ===================== Clear Bean ====================== */
        enquiryDetailsBEAN = new CounselorDetailsBEAN();
        enquiryDetailsBEAN.setEnquiryID(null);
        /* ======================== An Event for enquiry id change  ==================== */
        enquiryDetailsBEAN.enquiryIDProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    System.out.println("id changed noww");
                    btnEnquirySubmit.setText("Update");
                    showHideStatusRemarksArea(true);
                } else {
                    btnEnquirySubmit.setText("Add Enquiry");
                    System.out.println("id changed noww");
                }
            }
        });
        bindAllEnquiryControls();
        enquiryDetailsBEAN.setStaffBranch(CUR_BRANCH);
        enquiryDetailsBEAN.setStaffUsername(CUR_USERNAME);
        cmbBranch.getSelectionModel().select(CUR_BRANCH);
        popuser.hide();
        System.out.println("Check Null Bean Clear Screen1 :: " + enquiryDetailsBEAN.toString());
        /* ========================  create executor that uses daemon threads: ==================== */
//        executor = Executors.newCachedThreadPool(runnable -> {
//            Thread t = new Thread(runnable);
//            t.setDaemon(true);
//            return t;
//        });
        cmbCreateTask.disableProperty().unbind();
        cmbCreateTask.setDisable(false);
        cmbCreateTask.disableProperty().bind(enquiryDetailsBEAN.enquiryIDProperty().isEmpty());
        lblAppStatus.setText("");
    }

    /**
     *
     */
    public void clearAllComboBoxes() {
//        if (!cmbSource.getSelectionModel().isEmpty()) {
//            cmbSource.getSelectionModel().clearSelection();
//        }
        cmbDistrict.setStyle("-fx-border-color:#0000");
        cmbMethod.setStyle("-fx-border-color:#0000");
        cmbState.setStyle("-fx-border-color:#0000");
        if (!cmbDepartment.getSelectionModel().isEmpty()) {
            cmbDepartment.getSelectionModel().clearSelection();
        }
        if (!cmbSource.getSelectionModel().isEmpty()) {
            cmbSource.getSelectionModel().clearSelection();
        }
        if (!cmbDistrict.getSelectionModel().isEmpty()) {
            cmbDistrict.getSelectionModel().clearSelection();
        }
        if (!cmbMethod.getSelectionModel().isEmpty()) {
            cmbMethod.getSelectionModel().clearSelection();
        }

        for (String c : cmbCountry.getItems()) {
            if (c != null) {
                if (c.toUpperCase().equalsIgnoreCase("INDIA")) {
                    cmbCountry.getSelectionModel().select(c);
                }
            }
        }

        for (String s : cmbState.getItems()) {
            if (s != null) {
                if (s.toUpperCase().equalsIgnoreCase("KERALA")) {
                    cmbState.getSelectionModel().select(s);
                }
            }
        }

    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clearScreen();

    }

    @FXML
    private void handleStarImageView(MouseEvent event) {
        if (PRIORITY == 0) {
            PRIORITY = 1;
            starImageView
                    .setImage(new Image(EnquiryMainViewController.class
                            .getResourceAsStream("images/stary.png")));
        } else {
            PRIORITY = 0;
            starImageView
                    .setImage(new Image(EnquiryMainViewController.class
                            .getResourceAsStream("images/starw.png")));
        }
    }

    private void loadcmbSource() {
        cmbSource.getItems().clear();
        List<String> sources = RetrieveStaticMasterDAO.getAllSources();
        for (String s : sources) {
            source.add(s);
        }
        cmbSource.setItems(source);
    }
}

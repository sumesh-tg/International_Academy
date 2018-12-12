/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.counselor;

import com.zs.ina.admin.inbox.forward.FXMLForwardController;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryDAO;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryIMPl;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryPOJO;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.counselor.dao.model.SearchQueryBEAN;
import com.zs.ina.counselor.empstatus.FXMLEmployeStatusFullController;
import com.zs.ina.counselor.empstatus.dao.EmpStatusBEAN;
import com.zs.ina.counselor.empstatus.dao.EmployeeStatusDAO;
import com.zs.ina.counselor.empstatus.dao.OfficeStaffPOJO;
import com.zs.ina.admin.master.retrieve.MasterCountryStateDistrictDAO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.admin.master.userlogin.dao.LoginBEAN;
import com.zs.ina.admin.master.userlogin.dao.LoginIMPL;
import com.zs.ina.admin.master.userlogin.dao.LoginSERVICE;
import com.zs.ina.admin.master.userlogin.dao.RolePOJO;
import com.zs.ina.common.EffectsClass;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.inbox.commonpool.CommonPoolService;
import com.zs.ina.common.inbox.retrieve.InboxCountsBEAN;
import com.zs.ina.common.inbox.retrieve.InboxRetrievalService;
import com.zs.ina.common.inbox.search.InboxSearchService;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.reminder.FXMLRemainderController;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderBEAN;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderDAO;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderIMPL;
import com.zs.ina.enquiry.search.EditAddressCell;
import com.zs.ina.enquiry.search.EditAdmissionTest;
import com.zs.ina.enquiry.search.EditBatchCell;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.notifications.dao.NoticePOJO;
import com.zs.ina.notifications.dao.NotificationsDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.scene.control.LocalDateTimePicker;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import org.w3c.dom.Element;
import com.zs.ina.enquiry.search.EditContactNumbers;
import com.zs.ina.enquiry.search.EditCountryLocation;
import com.zs.ina.enquiry.search.EditDateOfJoin;
import com.zs.ina.enquiry.search.EditEnquiryStatusCell;
import com.zs.ina.enquiry.search.EditExperience;
import com.zs.ina.enquiry.search.EditLanguageTest;
import com.zs.ina.enquiry.search.EditMigrationRequiredCell;
import com.zs.ina.enquiry.search.EditQualifications;
import com.zs.ina.enquiry.search.EditStudyRequiredCell;
import com.zs.ina.enquiry.search.EditTrainingRequiredCell;
import com.zs.ina.enquiry.search.EditWorkRequiredCell;
import com.zs.ina.login.dao.LoginFormDAO;
import com.zs.ina.login.dao.ProfilePOJO;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.util.Calendar;
import java.util.Collections;
import java.util.Set;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import org.springframework.context.ApplicationContext;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * FXML Controller class
 *
 * @author zscomp1
 */
public class FXMLCounselorMainViewController implements Initializable {

    static Logger logger = Logger.getLogger(FXMLCounselorMainViewController.class);
    @FXML
    private ComboBox<String> cmbSource;
    @FXML
    private Button btnSearchSubmit;

    PopOver pop = new PopOver();
    PopOver popEnquiryData = new PopOver();
    int folloUpCount = 0;
    private String CUR_USERNAME = null;
    private String CUR_ROLE = null;
    private String CUR_BRANCH = null;
    ObservableList districs = FXCollections.observableArrayList();
    ObservableList source = FXCollections.observableArrayList();
    ObservableList country = FXCollections.observableArrayList();
    ObservableList state = FXCollections.observableArrayList();
    ObservableList programLevel = FXCollections.observableArrayList();
    ObservableList programField = FXCollections.observableArrayList();
    ObservableList programLevelList = FXCollections.observableArrayList();
    ObservableList duration = FXCollections.observableArrayList();
    ObservableList profession = FXCollections.observableArrayList();
    ObservableList intakeObsList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    ObservableList enquiryfor = FXCollections.observableArrayList("Study", "Work", "Migration", "Training");
    private TableColumn<?, ?> clmEnquirySource;
    @FXML
    private ImageView imgViewClearName;
    @FXML
    private AnchorPane leftAnchorPane;
    @FXML
    private ComboBox<String> cmbStatus;
    @FXML
    private HBox commonHboox;
    @FXML
    private Hyperlink lblMonth;
    @FXML
    private Hyperlink lblWeek;
    @FXML
    private Hyperlink lblYesterday;
    @FXML
    private Hyperlink lblToday;
    @FXML
    private Label lblResetAll;
    @FXML
    private Hyperlink hyperTotalEnq;
    @FXML
    private Hyperlink hyperAssessed;
    @FXML
    private Hyperlink hyperRegistered;
    @FXML
    private Hyperlink hyperAsspending;
    @FXML
    private Hyperlink hyperCancelled;
    @FXML
    private Hyperlink hyperFakeCall;
    @FXML
    private Hyperlink hyperWrongCall;
    @FXML
    private Hyperlink hyperFollowUp;
    @FXML
    private Hyperlink hyperStarred;
    @FXML
    private ComboBox<String> cmbDistrict;
    @FXML
    private ComboBox<String> cmbBranch;
    @FXML
    private ComboBox<String> cmbPrgmLevel;
    @FXML
    private ComboBox<String> cmbProgramFld;
    @FXML
    private ComboBox<String> cmbEnquiryMethod;
    @FXML
    private ImageView imgViewClearDistrict;
    @FXML
    private ImageView imgViewClearBranch;
    @FXML
    private ImageView imgViewClearPrgmLevel;
    @FXML
    private ImageView imgViewClearPrgmField;
    @FXML
    private ImageView imgViewClearEnqryStats;
    ObservableList sassign = FXCollections.observableArrayList();
    SearchQueryBEAN searchQueryBEAN = new SearchQueryBEAN();
    ObservableList enquiryMethods = FXCollections.observableArrayList("Local", "Direct", "Walk in");
    @FXML
    private Hyperlink hyperMsgPending;
    @FXML
    private VBox vboxNoticeBoard;
    Timeline notifyTimer;
    private int TIME_CALC;
    private int TIME_DELAY;
    private String ACTIVE_TAB;
    Map<String, Integer> counterMap = new HashMap<>();
    LocalDateTimePicker dateTimePicker;
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
    @FXML
    private ComboBox<String> empStatusBranchCombo;
    ObservableList branch = FXCollections.observableArrayList();
    String selectedBranch = null;
    @FXML
    private ImageView imgviewEmpStatusFull;
    @FXML
    private Label lblLogout;
    @FXML
    private Label lblUserdata;
    @FXML
    private VBox vboxHyperlinks;
    @FXML
    private Hyperlink hyperAppointmnet;
    List<CounselorDetailsBEAN> registeredInboxList = new ArrayList<>();
    @FXML
    private TitledPane tPanEmpStatus;
    @FXML
    private TitledPane tPanNoticeBoard;
    @FXML
    private Accordion accordionLeft;
    Stage stageAppointment = new Stage();
    @FXML
    private Tab tabPrimaryStudy;
    @FXML
    private Tab tabPrimaryWork;
    @FXML
    private Tab tabPrimaryMigration;
    @FXML
    private Tab tabPrimaryTraining;
    @FXML
    private TabPane tabPanePrimaryInbox;

    private final PopOver popmsg = new PopOver();
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private DatePicker datePickDOB;
    @FXML
    private ComboBox<String> cmbState;
    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<String> cmbQualifiLevel;
    @FXML
    private ComboBox<String> cmbQualifiField;
    @FXML
    private ComboBox<String> cmbLanguageTest;
    @FXML
    private ComboBox<String> cmbLanguageScore;
    @FXML
    private ComboBox<String> cmbProfession;
    @FXML
    private ComboBox<String> cmbDuration;
    @FXML
    private ComboBox<String> cmbIntakeSrch;
    @FXML
    private ComboBox<String> cmbInstitution;
    @FXML
    private ComboBox<String> cmbCountryPgmReq;
    @FXML
    private ComboBox<String> cmbLocation;
    @FXML
    private ComboBox<CounselorsListPOJO> cmbAssignBy;
    @FXML
    private ImageView imgViewClearPhone;
    @FXML
    private ImageView imgViewClearPassport;
    @FXML
    private ImageView imgViewClearDob;
    @FXML
    private ImageView imgViewClearState;
    @FXML
    private ImageView imgViewClearCountry;
    @FXML
    private ImageView imgViewClearQualiLevel;
    @FXML
    private ImageView imgViewClearQualiField;
    @FXML
    private ImageView imgViewClearLangTest;
    @FXML
    private ImageView imgViewClearScore;
    @FXML
    private ImageView imgViewClearProfesion;
    @FXML
    private ImageView imgViewClearDuration;
    @FXML
    private ImageView imgViewClearIntake;
    @FXML
    private ImageView imgViewClearInstitution;
    @FXML
    private ImageView imgViewClearCountryPrgm;
    @FXML
    private ImageView imgViewClearLocation;
    @FXML
    private ImageView imgViewClearEnqMethod;
    @FXML
    private ImageView imgViewClearAssignedBy;
    @FXML
    private ImageView imgViewClearEnqrySorce;
    @FXML
    private TextField txtPassportNo;
    @FXML
    private TitledPane titiledPaneSearch;
    final Popup popup = new Popup();
    PopOver popForward = new PopOver();
    @FXML
    private StackPane stackPaneInbox;
    @FXML
    private Label lblEnquiryReportTitle;
    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();
    int GLOBAL_STAR_STATUS = 0;
    /* ======================== Store Checked Enquiries  ==================== */
    static List<CounselorDetailsBEAN> listCheckedEnquiries = new ArrayList<>();
    @FXML
    private StackPane stackPaneMain;
    /* ====================== Show Progress Indicator ====================== */
    Region regionOverlay = new Region();
    ProgressIndicator progressIndicator = new ProgressIndicator();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    CountryDAO countryDAO = new CountryIMPL();
    private Button btnFollowUpFullview;
    @FXML
    private Button btnTaskView;
    @FXML
    private Button btnAppointmentPending;
    @FXML
    private Label lblTitleAppPending;
    @FXML
    private Button btnFollowUpPending;
    @FXML
    private Label lblTitleFollowUp;
    ReminderDAO reminderDAO = new ReminderIMPL();
    Service serviceReminder = null;
    Service serviceHyperLinkCounts = null;
    Service serviceCommonPool = null;
    Service serviceCheckExpiredEnquiry = null;
    Service serviceFolloupCounts = null;
    Stage reminderStage = new Stage();
    ObservableList<ReminderBEAN> listReminders = FXCollections.observableArrayList();
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    @FXML
    private Hyperlink hyperCommonPool;
    boolean IS_COMMON_POOL = false;
    ObservableList<InboxCountsBEAN> listInboxCounts = FXCollections.observableArrayList();
    ForwardHistoryDAO forwardHistoryDAO = new ForwardHistoryIMPl();
    /* ======================== Store Active HyperLink ==================== */
    List<Hyperlink> listHyperlinkActive = new ArrayList<>();
    Map<String, ObservableList<String>> mapEnquiryMethod = new HashMap<>();
    /* ====================== Inbox Lists ====================== */
    List<CounselorDetailsBEAN> primaryInboxStudyList = new ArrayList<>();
    List<CounselorDetailsBEAN> primaryInboxWorkList = new ArrayList<>();
    List<CounselorDetailsBEAN> primaryInboxMigrationList = new ArrayList<>();
    List<CounselorDetailsBEAN> primaryInboxTrainingList = new ArrayList<>();
    /* ====================== Store Current Table Instances ====================== */
    Map<String, TableView<CounselorDetailsBEAN>> mapCurrentTables = new HashMap<>();
    boolean IS_EXPIRED = false;
    @FXML
    private Button btnSpotAssessment;
    @FXML
    private Button btnSpotRegistration;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    @FXML
    private ComboBox<RolePOJO> cmbSwitchUser;
    @FXML
    private Button btnSwitchUserGo;

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
        /* ======================== Test Spring  ==================== */
        ProfilePOJO profilePOJO = springAppContext.getBean(ProfilePOJO.class);
        System.out.println("Test Spring Context Profile :: " + profilePOJO.toString());
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();
        TIME_DELAY = RetrieveStaticMasterDAO.retrieveThreadDelayInt();
        ACTIVE_TAB = "primary";
        lblUserdata.setText("" + CUR_USERNAME + "(" + CUR_BRANCH + ")");
        regionOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
        progressIndicator.setMaxSize(150, 150);
        //   addDatatoTable();
        initComboBoxes();
//        resetSearchWithImgView();
        addClearOnControls();
        hideAllClearImages();
        addClearImageEvents();
        // resetAll();
        bindWithBean();
        addSearchFilters();
        initObservLists();
        selectedBranch = CUR_BRANCH;
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
                Stage oldStage = (Stage) btnTaskView.getScene().getWindow();
                oldStage.close();
            }
        });

        /* =============> Loading all inbox study,work,migration,training enquiries <============ */
        //    Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = CounselorDetailsDAO.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME);
        // addAllInboxData(primaryInboxMap.get("all"));
        //   loadInboxTabsEnquiries(primaryInboxMap);
        tabPanePrimaryInbox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {

            if (tabPanePrimaryInbox.getSelectionModel().getSelectedIndex() > 0) {
//                lblInboxName.setText("Inbox - " + newValue.getText());
            } else {
                //   lblInboxName.setText("Inbox");
            }
//            lblShowingReports.setText("");
            getAllHyperlinkCounts();

        });

        /* =============> Add tab events <============ */
        getAllHyperlinkCounts();
        /* =============> Add inbox click events <============ */
        // addInboxEvents();
        /* =============> Add left accordion data  <============ */
        accordionLeft.setExpandedPane(tPanNoticeBoard);

        addmessageLoader();
        addAppointmentSchedule();
        addFollowUpRegister();
        addNoticeWindow();
        addEmpStatusLoader(CUR_BRANCH);
        addLogout();
        /* ============================ Change Date Formats  ================================    */
        changeDateFormats();
        ChangeDateFormat.datePickerDateFormatter(datePickDOB);

        /* ============================ Reset Label Event  ================================    */
        lblResetAll.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                resetAll();
            }
        });
        /* ============================ Add Global Timer  ================================    */
        addGlobalTimer();
        /* ============================ Hide Search Accordion  ================================    */
        titiledPaneSearch.setExpanded(false);
        titiledPaneSearch.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    titiledPaneSearch.setPadding(new Insets(63, 0, 0, 0));
                } else {
                    titiledPaneSearch.setPadding(new Insets(0, 0, 0, 0));
                }
            }
        });
        /* ============================ Change Date format of DOB  ================================    */
        ChangeDateFormat.datePickerDateFormatter(datePickDOB);
        /* ======================== Load Common Pool Data As Default ==================== */
 /* ======================== Add Title ==================== */
        lblEnquiryReportTitle.setText("");
        /* ======================== Fires MousEvent Of lblToday ==================== */
        Event.fireEvent(hyperCommonPool, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null));
//        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = CounselorDetailsDAO.getCommonPoolEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
//        loadInboxTabsEnquiries(primaryInboxMap);
        /* ======================== bind Enquiry Report Title ==================== */
        stackPaneInbox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null) {
                    //   lblEnquiryReportTitle.setStyle("-fx-padding:"+newValue.doubleValue()/10);
                }
            }
        });
        addReminders();
        addHyperLinkService();
        //  addCommonPoolService();
        checkExpiredEnquiriesService();
        addFollowUpService();
        /* ======================== Spot Assessment & Registration ==================== */
        addSpotAssessmentRegistration();
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

                    /* ====================== For Default Tab ====================== */
                    Context.getInstance().currentProfile().setCurrentTab("study");
                    /* ====================== Create New Bean ====================== */
                    CounselorDetailsBEAN spotDetailsBEAN = new CounselorDetailsBEAN();
                    spotDetailsBEAN.setCountry("India");
                    spotDetailsBEAN.setState("Kerala");
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

                    /* ====================== For Default Tab ====================== */
                    Context.getInstance().currentProfile().setCurrentTab("study");
                    /* ====================== Create New Bean ====================== */
                    CounselorDetailsBEAN spotDetailsBEAN = new CounselorDetailsBEAN();
                    spotDetailsBEAN.setCountry("India");
                    spotDetailsBEAN.setState("Kerala");
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

    /**
     *
     */
    public void updateInboxService() {

    }

    /**
     *
     */
    public void addFollowUpService() {

        serviceFolloupCounts = new Service<ObservableList<InboxCountsBEAN>>() {

            @Override
            protected Task createTask() {
                return new Task<ObservableList<InboxCountsBEAN>>() {
                    @Override
                    protected ObservableList<InboxCountsBEAN> call() throws InterruptedException {
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(300);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return InboxRetrievalService.retrieveInboxCountsFollowup(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                    }
                };
            }
        };
        serviceFolloupCounts.stateProperty().addListener(new ChangeListener<Worker.State>() {

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
                        ObservableList<InboxCountsBEAN> listInboxCounts = (ObservableList<InboxCountsBEAN>) serviceFolloupCounts.getValue();
                        initTodayFollowUpCount(listInboxCounts);
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        /* ====================== Start Hyperlink Counts Service ====================== */
        serviceFolloupCounts.start();

    }

    /**
     *
     */
    public void checkExpiredEnquiriesService() {

        serviceCheckExpiredEnquiry = new Service<Boolean>() {

            @Override
            protected Task createTask() {
                return new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws InterruptedException {
                        updateMessage("Searching for hyperlink Counts....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(500);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return CommonPoolService.updateExpiredToNotAssigned(CUR_USERNAME, CUR_BRANCH);
                        // return CommonPoolService.updateExpiredToHistoryNotAssigned(CUR_USERNAME, CUR_BRANCH);
                    }
                };
            }
        };
        serviceCheckExpiredEnquiry.stateProperty().addListener(new ChangeListener<Worker.State>() {

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
                        System.out.println("Test Service Expiry :: " + serviceCheckExpiredEnquiry.getValue());
                        IS_EXPIRED = (boolean) serviceCheckExpiredEnquiry.getValue();
                        CommonPoolService.updateExpiredToHistoryNotAssigned(CUR_USERNAME, CUR_BRANCH);
                        if (IS_EXPIRED) {
                            if (!IS_COMMON_POOL) {
                                TableView<CounselorDetailsBEAN> tblExpiryStudyInbox = mapCurrentTables.get("study");
                                TableView<CounselorDetailsBEAN> tblExpiryWorkInbox = mapCurrentTables.get("work");
                                TableView<CounselorDetailsBEAN> tblExpiryMigrationInbox = mapCurrentTables.get("migration");
                                TableView<CounselorDetailsBEAN> tblExpiryTrainingInbox = mapCurrentTables.get("training");
                                /* ====================== Remove Expired Study Enquiries ====================== */
                                if (tblExpiryStudyInbox.getItems() != null) {
                                    for (CounselorDetailsBEAN expiryBEAN : tblExpiryStudyInbox.getItems()) {
                                        Task<Boolean> taskExpiry = new Task<Boolean>() {

                                            @Override
                                            protected Boolean call() throws Exception {
                                                return CommonPoolService.checkExpiredEnquiry(CUR_USERNAME, CUR_BRANCH, CUR_ROLE, expiryBEAN.getEnquiryID());
                                            }
                                        };

                                        taskExpiry.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                                            @Override
                                            public void handle(WorkerStateEvent event) {

                                                if (!taskExpiry.getValue()) {
                                                    tblExpiryStudyInbox.getItems().remove(expiryBEAN);
                                                    int c = 0;
                                                    for (TableColumn column : tblExpiryStudyInbox.getColumns()) {
                                                        c++;
                                                        if (c == 2) {
                                                            refreshPriorityCell(column);
                                                        }
                                                        if (c == 3) {
                                                            //    refreshFlagyCell(column);
                                                        }
                                                    }
                                                }

                                            }
                                        });

                                        new Thread(taskExpiry).start();
                                    }
                                }
                                /* ====================== Remove Expired Enquiries From Work ====================== */
                                if (tblExpiryWorkInbox.getItems() != null) {
                                    for (CounselorDetailsBEAN expiryBEAN : tblExpiryWorkInbox.getItems()) {
                                        Task<Boolean> taskExpiry = new Task<Boolean>() {

                                            @Override
                                            protected Boolean call() throws Exception {
                                                return CommonPoolService.checkExpiredEnquiry(CUR_USERNAME, CUR_BRANCH, CUR_ROLE, expiryBEAN.getEnquiryID());
                                            }
                                        };

                                        taskExpiry.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                                            @Override
                                            public void handle(WorkerStateEvent event) {
                                                if (!taskExpiry.getValue()) {
                                                    tblExpiryWorkInbox.getItems().remove(expiryBEAN);
                                                    int c = 0;
                                                    for (TableColumn column : tblExpiryWorkInbox.getColumns()) {
                                                        c++;
                                                        if (c == 2) {
                                                            refreshPriorityCell(column);
                                                        }
                                                        if (c == 3) {
                                                            //    refreshFlagyCell(column);
                                                        }
                                                    }

                                                }
                                            }
                                        });

                                        new Thread(taskExpiry).start();
                                    }
                                }
                                /* ====================== Remove Expired Enquiries From Migration ====================== */
                                if (tblExpiryMigrationInbox.getItems() != null) {
                                    for (CounselorDetailsBEAN expiryBEAN : tblExpiryMigrationInbox.getItems()) {
                                        Task<Boolean> taskExpiry = new Task<Boolean>() {

                                            @Override
                                            protected Boolean call() throws Exception {
                                                return CommonPoolService.checkExpiredEnquiry(CUR_USERNAME, CUR_BRANCH, CUR_ROLE, expiryBEAN.getEnquiryID());
                                            }
                                        };

                                        taskExpiry.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                                            @Override
                                            public void handle(WorkerStateEvent event) {
                                                if (!taskExpiry.getValue()) {

                                                    tblExpiryMigrationInbox.getItems().remove(expiryBEAN);
                                                    int c = 0;
                                                    for (TableColumn column : tblExpiryMigrationInbox.getColumns()) {
                                                        c++;
                                                        if (c == 2) {
                                                            refreshPriorityCell(column);
                                                        }
                                                        if (c == 3) {
                                                            //    refreshFlagyCell(column);
                                                        }
                                                    }
                                                }
                                            }
                                        });

                                        new Thread(taskExpiry).start();
                                    }
                                }
                                /* ====================== Remove Expired Training Enquiries ====================== */
                                if (tblExpiryTrainingInbox.getItems() != null) {
                                    for (CounselorDetailsBEAN expiryBEAN : tblExpiryTrainingInbox.getItems()) {
                                        Task<Boolean> taskExpiry = new Task<Boolean>() {

                                            @Override
                                            protected Boolean call() throws Exception {
                                                return CommonPoolService.checkExpiredEnquiry(CUR_USERNAME, CUR_BRANCH, CUR_ROLE, expiryBEAN.getEnquiryID());
                                            }
                                        };

                                        taskExpiry.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                                            @Override
                                            public void handle(WorkerStateEvent event) {
                                                if (!taskExpiry.getValue()) {

                                                    tblExpiryTrainingInbox.getItems().remove(expiryBEAN);
                                                    int c = 0;
                                                    for (TableColumn column : tblExpiryTrainingInbox.getColumns()) {
                                                        c++;
                                                        if (c == 2) {
                                                            refreshPriorityCell(column);
                                                        }
                                                        if (c == 3) {
                                                            //    refreshFlagyCell(column);
                                                        }
                                                    }
                                                }
                                            }
                                        });

                                        new Thread(taskExpiry).start();
                                    }
                                }
                                getAllHyperlinkCounts();
                            }
                            IS_EXPIRED = false;
                        }
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        /* ====================== Start Hyperlink Counts Service ====================== */
        serviceCheckExpiredEnquiry.start();
    }

    /**
     *
     */
    public void addCommonPoolService() {

        serviceCommonPool = new Service<ObservableList<InboxCountsBEAN>>() {

            @Override
            protected Task createTask() {
                return new Task< Map<String, List<CounselorDetailsBEAN>>>() {
                    @Override
                    protected Map<String, List<CounselorDetailsBEAN>> call() throws InterruptedException {
                        updateMessage("Searching for hyperlink Counts....");
                        updateProgress(0, 10);
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(500);
                            updateProgress(i + 1, 10);
                        }
                        updateMessage("Found them.");
                        return InboxRetrievalService.getCommonPoolEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
                    }
                };
            }
        };
        serviceCommonPool.stateProperty().addListener(new ChangeListener<Worker.State>() {

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
                        System.out.println("test data :: " + serviceCommonPool.getValue());
                        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = (Map<String, List<CounselorDetailsBEAN>>) serviceCommonPool.getValue();
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        /* ====================== Start Hyperlink Counts Service ====================== */
        serviceCommonPool.start();

    }

    /**
     *
     */
    public void addHyperLinkService() {

        serviceHyperLinkCounts = new Service<ObservableList<InboxCountsBEAN>>() {

            @Override
            protected Task createTask() {
                return new Task<ObservableList<InboxCountsBEAN>>() {
                    @Override
                    protected ObservableList<InboxCountsBEAN> call() throws InterruptedException {
                        return InboxRetrievalService.retrieveInboxCounts(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                    }
                };
            }
        };
        serviceHyperLinkCounts.stateProperty().addListener(new ChangeListener<Worker.State>() {

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
                        ObservableList<InboxCountsBEAN> listInboxCounts = (ObservableList<InboxCountsBEAN>) serviceHyperLinkCounts.getValue();
                        loadHyperlinkcounts(listInboxCounts);
                        autoUpdateCurrentHyperLinkData();
                        break;
                    case CANCELLED:
                    case FAILED:
                        break;
                }
            }
        });
        /* ====================== Start Hyperlink Counts Service ====================== */
        serviceHyperLinkCounts.start();
    }

    /**
     *
     */
    public void autoUpdateCurrentHyperLinkData() {
        if (!IS_COMMON_POOL) {
            /* ====================== Testing Refreshing Data ====================== */
            System.out.println("TESTING CURRENT HYPERLINK :: " + Context.getInstance().currentProfile().getInboxCountsBEAN().toString());
            InboxCountsBEAN countsBEAN = new InboxCountsBEAN();
            countsBEAN = Context.getInstance().currentProfile().getInboxCountsBEAN();
            if (countsBEAN.getTableDiff() != null) {
                if (countsBEAN.getTableDiff().equalsIgnoreCase("A")) {
                    Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.retrieveAppStatusData(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, countsBEAN.getStatusId());
                    autoUpdateAllTables(primaryInboxMap);
                } else if (countsBEAN.getTableDiff().equalsIgnoreCase("T")) {
                    Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.retrieveTaskStatusData(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, countsBEAN.getStatusId());
                    autoUpdateAllTables(primaryInboxMap);

                } else if (countsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                    /* ====================== For Showing Total Enquiries ====================== */
                    Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
                    autoUpdateAllTables(primaryInboxMap);

                } else if (countsBEAN.getStatusId().equalsIgnoreCase("-2")) {
                    /* ====================== For Showing Starred Enquiries ====================== */
                    Map<String, List<CounselorDetailsBEAN>> primaryDataMap = InboxRetrievalService.retrieveStarredEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, "1", "rating");
                    autoUpdateAllTables(primaryDataMap);
//                                    loadInboxTabsEnquiries(primaryDataMap);
//                            retrieveHyperlinkData(resultList);
                }
            }
        } else {
            Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.getCommonPoolEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
            autoUpdateAllTables(primaryInboxMap);
        }
    }

    /**
     *
     * @param primaryInboxMap
     */
    public void autoUpdateAllTables(Map<String, List<CounselorDetailsBEAN>> primaryInboxMap) {

        /* ====================== For Reloading ALl Enquiries ====================== */
        List<String> keys = primaryInboxMap.entrySet().stream().sorted((left, right)
                -> Integer.compare(left.getValue().size(), right.getValue().size())).map(entry -> entry.getKey()).collect(Collectors.toList());
        TableView<CounselorDetailsBEAN> tblExpiryStudyInbox = mapCurrentTables.get("study");
        TableView<CounselorDetailsBEAN> tblExpiryWorkInbox = mapCurrentTables.get("work");
        TableView<CounselorDetailsBEAN> tblExpiryMigrationInbox = mapCurrentTables.get("migration");
        TableView<CounselorDetailsBEAN> tblExpiryTrainingInbox = mapCurrentTables.get("training");
        for (String key : keys) {

            switch (key) {
                case "study":
                    primaryInboxStudyList.clear();
                    primaryInboxStudyList = primaryInboxMap.get("study");
                    for (CounselorDetailsBEAN newDetailsBEAN : primaryInboxStudyList) {
                        if (tblExpiryStudyInbox.getItems() != null) {

                            boolean flag = false;
                            for (CounselorDetailsBEAN detailsBEAN : tblExpiryStudyInbox.getItems()) {
                                if (newDetailsBEAN.getEnquiryID().equalsIgnoreCase(detailsBEAN.getEnquiryID())) {
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                Platform.runLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        tblExpiryStudyInbox.getItems().add(newDetailsBEAN);
                                        int c = 0;
                                        for (TableColumn column : tblExpiryStudyInbox.getColumns()) {
                                            c++;
                                            if (c == 2) {
                                                refreshPriorityCell(column);
                                            }
                                            if (c == 3) {
                                                //    refreshFlagyCell(column);
                                            }
                                        }
                                    }
                                });
                                flag = false;
                            }

                        }
                    }
                    break;
                case "work":
                    primaryInboxWorkList.clear();
                    primaryInboxWorkList = primaryInboxMap.get("work");
                    for (CounselorDetailsBEAN newDetailsBEAN : primaryInboxWorkList) {
                        if (tblExpiryWorkInbox.getItems() != null) {

                            boolean flag = false;
                            for (CounselorDetailsBEAN detailsBEAN : tblExpiryWorkInbox.getItems()) {
                                if (newDetailsBEAN.getEnquiryID().equalsIgnoreCase(detailsBEAN.getEnquiryID())) {
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                Platform.runLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        tblExpiryWorkInbox.getItems().add(newDetailsBEAN);
                                        int c = 0;
                                        for (TableColumn column : tblExpiryWorkInbox.getColumns()) {
                                            c++;
                                            if (c == 2) {
                                                refreshPriorityCell(column);
                                            }
                                            if (c == 3) {
                                                //    refreshFlagyCell(column);
                                            }
                                        }
                                    }
                                });
                                flag = false;
                            }
                        }
                    }
                    break;
                case "migration":
                    primaryInboxMigrationList.clear();
                    primaryInboxMigrationList = primaryInboxMap.get("migration");
                    for (CounselorDetailsBEAN newDetailsBEAN : primaryInboxMigrationList) {
                        if (tblExpiryMigrationInbox.getItems() != null) {
                            boolean flag = false;
                            for (CounselorDetailsBEAN detailsBEAN : tblExpiryMigrationInbox.getItems()) {
                                if (newDetailsBEAN.getEnquiryID().equalsIgnoreCase(detailsBEAN.getEnquiryID())) {
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                Platform.runLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        tblExpiryMigrationInbox.getItems().add(newDetailsBEAN);
                                        int c = 0;
                                        for (TableColumn column : tblExpiryMigrationInbox.getColumns()) {
                                            c++;
                                            if (c == 2) {
                                                refreshPriorityCell(column);
                                            }
                                            if (c == 3) {
                                                //    refreshFlagyCell(column);
                                            }
                                        }
                                    }
                                });
                                flag = false;
                            }
                        }
                    }
                    break;
                case "training":
                    primaryInboxTrainingList.clear();
                    primaryInboxTrainingList = primaryInboxMap.get("training");
                    for (CounselorDetailsBEAN newDetailsBEAN : primaryInboxTrainingList) {
                        if (tblExpiryTrainingInbox.getItems() != null) {
                            boolean flag = false;
                            for (CounselorDetailsBEAN detailsBEAN : tblExpiryTrainingInbox.getItems()) {
                                if (newDetailsBEAN.getEnquiryID().equalsIgnoreCase(detailsBEAN.getEnquiryID())) {
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                Platform.runLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        tblExpiryTrainingInbox.getItems().add(newDetailsBEAN);
                                        int c = 0;
                                        for (TableColumn column : tblExpiryTrainingInbox.getColumns()) {
                                            c++;
                                            if (c == 2) {
                                                refreshPriorityCell(column);
                                            }
                                            if (c == 3) {
                                                //    refreshFlagyCell(column);
                                            }
                                        }
                                    }
                                });
                                flag = false;
                            }
                        }
                    }
                    break;
            }

        }

    }

    /**
     *
     * @param listInboxCounts
     */
    public void loadHyperlinkcounts(ObservableList<InboxCountsBEAN> listInboxCounts) {
        vboxHyperlinks.getChildren().clear();
        ObservableList<InboxCountsBEAN> listInboxCountsNotAssigned = InboxRetrievalService.retrieveInboxCounts("Not Assigned", CUR_BRANCH, CUR_ROLE);
        if (listInboxCountsNotAssigned.size() > 0) {
            for (InboxCountsBEAN inboxCountsBEAN : listInboxCountsNotAssigned) {
                String[] splitArray = inboxCountsBEAN.getConcatCount().split(",");
                hyperCommonPool.setText("Common Pool " + "(" + splitArray[tabPanePrimaryInbox.getSelectionModel().getSelectedIndex()] + ")");
                break;
            }
        } else {
            hyperCommonPool.setText("Common Pool (0)");
            lblEnquiryReportTitle.setText("Common Pool");
        }
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            String[] splitArray = inboxCountsBEAN.getConcatCount().split(",");
            Hyperlink hyperlink = new Hyperlink(inboxCountsBEAN.getStatus() + "(" + splitArray[tabPanePrimaryInbox.getSelectionModel().getSelectedIndex()] + ")");
            hyperlink.setWrapText(true);
            VBox.setVgrow(hyperlink, Priority.ALWAYS);
            hyperlink.setMaxWidth(Double.MAX_VALUE);
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    IS_COMMON_POOL = false;
                    listCheckedEnquiries.clear();
                    listHyperlinkActive.clear();
                    listHyperlinkActive.add(hyperlink);
                    lblEnquiryReportTitle.setText(inboxCountsBEAN.getStatus());
                    if (inboxCountsBEAN.getTableDiff() != null) {
                        if (inboxCountsBEAN.getTableDiff().equalsIgnoreCase("A")) {
                            Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.retrieveAppStatusData(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, inboxCountsBEAN.getStatusId());
                            loadInboxTabsEnquiries(primaryInboxMap);
                        } else if (inboxCountsBEAN.getTableDiff().equalsIgnoreCase("T")) {
                            Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.retrieveTaskStatusData(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, inboxCountsBEAN.getStatusId());
                            loadInboxTabsEnquiries(primaryInboxMap);
                        } else if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                            /* ====================== For Showing Total Enquiries ====================== */
                            Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
                            loadInboxTabsEnquiries(primaryInboxMap);
                        } else if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-2")) {
                            /* ====================== For Showing Starred Enquiries ====================== */
                            Map<String, List<CounselorDetailsBEAN>> primaryDataMap = InboxRetrievalService.retrieveStarredEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, "1", "rating");
                            loadInboxTabsEnquiries(primaryDataMap);
//                            retrieveHyperlinkData(resultList);
                        }
                    }
                    /* ====================== Set Bean On Context ====================== */
                    Context.getInstance().currentProfile().setInboxCountsBEAN(inboxCountsBEAN);
                }
            });
            vboxHyperlinks.getChildren().add(hyperlink);
        }
    }

    /**
     *
     */
    public void addReminders() {
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
                        lblTitleAppPending.setText("");
                        ObservableList<AppointmentScheduleBEAN> appointmentTableList = appointmentScheduleSERVICE.retrieveApponitmentsAccepted(CUR_USERNAME, CUR_BRANCH);
                        lblTitleAppPending.setText("Appointment Pending ( " + appointmentTableList.size() + " )");
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
            reminderStage
                    .getIcons().add(new Image(INALoginForm.class
                            .getResourceAsStream("images/ia_logo.png")));
            reminderStage.setTitle(
                    "Reminder");
            if (!reminderStage.isShowing()) {
                reminderStage = new Stage();
                reminderStage.setScene(scene);
                reminderStage.setAlwaysOnTop(true);
                reminderStage.show();
            }

        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }
    //A global timer

    /**
     *
     */
    public void addGlobalTimer() {

        TIME_CALC = 0;
        notifyTimer = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("notification timer is runnung now for " + Context.getInstance().currentProfile().getProfilePOJO().getRole());
                TIME_CALC = TIME_CALC + 5;
                //load autocompletion data every 2 minute
                if (TIME_CALC > TIME_DELAY) {
                    /* ======================== Check Expired Enquiry Service ==================== */
                    if (serviceCheckExpiredEnquiry != null) {
                        if (!serviceCheckExpiredEnquiry.isRunning()) {
                            serviceCheckExpiredEnquiry.reset();
                            serviceCheckExpiredEnquiry.restart();
                        }
                    } else {
                        serviceCheckExpiredEnquiry.start();
                    }
                    /* ======================== Update HyperLink Counts ==================== */
                    if (serviceHyperLinkCounts != null) {
                        if (!serviceHyperLinkCounts.isRunning()) {
                            serviceHyperLinkCounts.reset();
                            serviceHyperLinkCounts.restart();
                        }
                    } else {
                        serviceHyperLinkCounts.start();
                    }
                    /* ======================== Update FollowUp Counts ==================== */
                    if (serviceFolloupCounts != null) {
                        if (!serviceFolloupCounts.isRunning()) {
                            serviceFolloupCounts.reset();
                            serviceFolloupCounts.restart();
                        }
                    } else {
                        serviceFolloupCounts.start();
                    }

                }
                /* ====================== Check Reminder For every half a minute ====================== */
                if (TIME_CALC > TIME_DELAY / 3) {
                    if (!serviceReminder.isRunning()) {
                        serviceReminder.reset();
                        serviceReminder.restart();
                    }
                    /* ====================== Load Notice every 1/3 minutes ====================== */
                    counterMap = NotificationsDAO.checkCounter(CUR_USERNAME);
                    if (counterMap.get("notice") != null) {
                        if ((counterMap.get("notice") > 0)) {
                            vboxNoticeBoard.getChildren().clear();
                            addNoticeWindow();
                            NotificationsDAO.resetCounter(CUR_USERNAME, "notice");
                        }
                    }
                }
                if (TIME_CALC > TIME_DELAY) {
                    TIME_CALC = 0;
                }
            }
        }));
        notifyTimer.setCycleCount(Timeline.INDEFINITE);
        notifyTimer.play();

        /* ====================== Checking Enquiry Expiration =======================      */
 /* ====================== End Checking Enquiry Expiration =======================      */
    }
    //employee status

    /**
     *
     * @param branchName
     */
    public void addEmpStatusLoader(String branchName) {
        if (empStatusBranchCombo.getItems().contains(branchName)) {
            empStatusBranchCombo.getSelectionModel().clearAndSelect(empStatusBranchCombo.getItems().indexOf(branchName));
        }

        List<EmpStatusBEAN> empStatusList = EmployeeStatusDAO.getEmpStatus(branchName);
        empStatusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        empStatusPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        empStatusStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        empStatusContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        empStatusExtNo.setCellValueFactory(new PropertyValueFactory<>("ext_number"));
        empStatusTable.getItems().setAll(empStatusList);
        imgviewEmpStatusFull.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (empStatusList.size() > 0) {

                    StackPane secondaryLayout = new StackPane();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/counselor/empstatus/FXMLEmployeStatusFull.fxml"));
                    try {
                        Parent root = (Parent) loader.load();
                        FXMLEmployeStatusFullController controller = (FXMLEmployeStatusFullController) loader.getController();
                        controller.initData(empStatusList, branch, branchName);
                        secondaryLayout.getChildren().add(root);
                        Scene secondScene = new Scene(secondaryLayout, 1000, 413);
                        Stage secondStage = new Stage();
                        secondStage.setTitle("Set Your Appointments");
                        secondStage.setScene(secondScene);
                        secondStage.initStyle(StageStyle.UNDECORATED);
                        secondStage.initModality(Modality.WINDOW_MODAL);
                        secondStage.initOwner(btnAppointmentPending.getScene().getWindow());
                        secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                            @Override
                            public void handle(WindowEvent event) {
                                //  addAppointmentSchedule();
                            }
                        });

                        secondStage.show();

                    } catch (IOException exception) {
                        exception.printStackTrace();
                        logger.error(exception.toString());
                    }

                }
            }
        });
    }
    //add notice

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
        w.setPrefSize(300, 340);
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

//         notifyTimer = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("notification counter is runnung now for " + Context.getInstance().currentProfile().getProfilePOJO().getRole());
//                counterMap = NotificationsDAO.checkCounter(CUR_USERNAME);
//                System.out.println("map :: "+counterMap.get("notice"));
//                if ((counterMap.get("notice") > 0)) {
//                    vboxNoticeBoard.getChildren().clear();
//                    addNoticeWindow();
//                    NotificationsDAO.resetCounter(CUR_USERNAME, "notice");
//                }
//                //message checker
//                List<MessageBoxPOJO> checkmessageList = MessageBoxDAO.retrieveMessages(CUR_USERNAME, CUR_BRANCH);
//                if (SIZE_MSGLIST != checkmessageList.size()) {
//                    // vboxInbox.getChildren().clear();
//                    addmessageLoader();
//                }
//
//                //end message checker
//            }
//        }));
//        notifyTimer.setCycleCount(Timeline.INDEFINITE);
//        notifyTimer.play();
        vboxNoticeBoard.getChildren().add(w);

    }
    //add follow up register

    /**
     *
     */
    public void addFollowUpRegister() {
        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCountsFollowup(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            // hyperFollowUpToday.setText("Follow Up (Today "+inboxCountsBEAN.getTotalCount()+")");
            if (inboxCountsBEAN.getStatusId() != null) {
                if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                    lblTitleFollowUp.setText("");
                    lblTitleFollowUp.setText("Follow Up Pending ( Today " + inboxCountsBEAN.getTotalCount() + " )");
                    folloUpCount = Integer.parseInt(inboxCountsBEAN.getTotalCount());
                }
            }
        }

        btnFollowUpPending.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/followup/FXMLFollowUpFulllview.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                    com.zs.ina.enquiry.followup.FXMLFollowUpFulllviewController controller = (com.zs.ina.enquiry.followup.FXMLFollowUpFulllviewController) loader.getController();
                    //   controller.initData(followuplist);
                    Scene secondScene = new Scene(root);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Set Your Reminder");
                    secondStage.setScene(secondScene);
                    secondStage.initStyle(StageStyle.UNDECORATED);
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

    }
    //add appointmentsw

    /**
     *
     */
    public void addAppointmentSchedule() {

        ObservableList<AppointmentScheduleBEAN> appointmentTableList = appointmentScheduleSERVICE.retrieveApponitmentsAccepted(CUR_USERNAME, CUR_BRANCH);
        lblTitleAppPending.setText("Appointment Pending ( " + appointmentTableList.size() + " )");
        btnAppointmentPending.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/FXMLAgendaCalendar.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout, 1000, 413);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Set Your Reminder");
                    secondStage.setScene(secondScene);
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

    }

    /**
     *
     */
    public void addmessageLoader() {
        btnTaskView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/FXMLTaskFullView.fxml"));
                try {
                    Parent root = (Parent) loader.load();
//                    FXMLEmployeStatusFullController controller = (FXMLEmployeStatusFullController) loader.getController();
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
                            //  addAppointmentSchedule();
                        }
                    });

                    secondStage.show();

                } catch (IOException exception) {
                    exception.printStackTrace();
                    logger.error(exception.toString());
                }

            }
        });
    }

    /**
     *
     */
    public void changeDateFormats() {
        String pattern = "yyyy-MM-dd";

        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

//        datePickFrom.setConverter(converter);
//        datePickFrom.setPromptText(pattern.toLowerCase());
//
//        datePickTo.setConverter(converter);
//        datePickTo.setPromptText(pattern.toLowerCase());
    }

    /**
     *
     */
    public void initObservLists() {
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        for (String s : branches) {
            branch.add(s);
        }
        branch.add("All");

    }

    /**
     *
     * @param message
     * @param node
     */
    public void showPopUpMessages(String message, Node node) {
        popup.setX(300);
        popup.setY(200);
        Label lblMessage = new Label(message);
        lblMessage.setStyle("-fx-text-fill:red;-fx-background-color:green");
        popup.getContent().addAll(lblMessage);
        popup.show(node.getScene().getWindow(), node.localToScreen(0, 0).getX() - 6, node.localToScreen(0, 0).getY() + 30);
        popup.setAutoHide(true);
    }

    /**
     *
     * @param tableName
     * @param primaryInboxTabList
     * @param tabName
     */
    public void addInboxTabData(TableView<CounselorDetailsBEAN> tableName, List<CounselorDetailsBEAN> primaryInboxTabList, String tabName) {
        //add check box
        //check cell

        if (stackPaneMain.getChildren().contains(regionOverlay)) {
            stackPaneMain.getChildren().remove(regionOverlay);
        }
        if (stackPaneMain.getChildren().contains(progressIndicator)) {
            stackPaneMain.getChildren().remove(progressIndicator);
        }
        stackPaneMain.getChildren().addAll(regionOverlay, progressIndicator);

        // tableName.getItems().clear();
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmChkMark = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("");
        clmChkMark.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN>, ObservableValue<CounselorDetailsBEAN>>() {

            @Override
            public ObservableValue<CounselorDetailsBEAN> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        });
        clmChkMark.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> p) {
                return new CheckBoxTableCell();
            }
        });
        clmChkMark.setSortable(false);
        /* ======================== Global Check Box Controls ==================== */
        CheckBox checkBoxGlobal = new CheckBox();
        checkBoxGlobal.setPrefWidth(12);
        checkBoxGlobal.setPrefHeight(12);
        checkBoxGlobal.setMaxWidth(12);
        Label lblDummy = new Label("_");
        lblDummy.setStyle("-fx-text-fill:transparent");
        HBox hBoxGlobal = new HBox(lblDummy, checkBoxGlobal);
        hBoxGlobal.setStyle("-fx-padding:2px");
        clmChkMark.setGraphic(hBoxGlobal);
        checkBoxGlobal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkBoxGlobal.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to select all !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(tableName.getScene().getWindow());
                    alert.showAndWait();

                    if (alert.getResult()
                            == ButtonType.YES) {
                        clmChkMark.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

                            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> p) {
                                return new CheckBoxTableCell(true);
                            }
                        });
                        listCheckedEnquiries.clear();
                        for (CounselorDetailsBEAN cdbean : primaryInboxTabList) {
                            listCheckedEnquiries.add(cdbean);
                        }
                    } else {
                        checkBoxGlobal.setSelected(false);
                    }
                } else {

                    clmChkMark.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

                        public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> p) {
                            return new CheckBoxTableCell(false);
                        }
                    });
                    listCheckedEnquiries.clear();

                }

            }
        });
        /* ======================== End Global Controls ==================== */
 /* ======================== Add Enquiry Hold flag ==================== */
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabFlag = new TableColumn<>("Flag");
        clmTabFlag.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN>, ObservableValue<CounselorDetailsBEAN>>() {

            @Override
            public ObservableValue<CounselorDetailsBEAN> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        });
        if (!IS_COMMON_POOL) {
            clmTabFlag.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                    return new FlagCell();
                }
            });
            clmTabFlag.setSortable(false);
        }
        /* ======================== End Enquiry Hold Flag ==================== */
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabPriority = new TableColumn<>("");

        clmTabPriority.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN>, ObservableValue<CounselorDetailsBEAN>>() {

            @Override
            public ObservableValue<CounselorDetailsBEAN> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        });
        clmTabPriority.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                return new PriorityCell();
            }
        });
        clmTabPriority.setSortable(false);
        /* ======================== Global Priority ==================== */
        ImageView imageViewGlobal = new ImageView();
        Image imgGlobalYellowStar = new Image(FXMLCounselorMainViewController.class
                .getResourceAsStream("stary.png"));
        Image imgGlobalWhiteStar = new Image(FXMLCounselorMainViewController.class
                .getResourceAsStream("starw.png"));

        imageViewGlobal.setImage(imgGlobalWhiteStar);

        imageViewGlobal.setFitWidth(
                16);
        imageViewGlobal.setFitHeight(
                16);

        Button btnGlobalStar = new Button();

        btnGlobalStar.setGraphic(imageViewGlobal);

        btnGlobalStar.setStyle(
                "-fx-background-color:transperant");
        btnGlobalStar.setPrefHeight(
                16);
        btnGlobalStar.setMaxHeight(
                16);
        clmTabPriority.setGraphic(btnGlobalStar);

        btnGlobalStar.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will set priority for all enquiries. Do you want to continue? ", ButtonType.YES, ButtonType.CANCEL);

                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage
                        .getIcons().add(new Image(INALoginForm.class
                                .getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(tableName.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {

                    if (GLOBAL_STAR_STATUS == 0) {
                        imageViewGlobal.setImage(imgGlobalWhiteStar);
                        for (CounselorDetailsBEAN cdbean : primaryInboxTabList) {
                            cdbean.setRating("1");
                        }
                        clmTabPriority.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

                            @Override
                            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {

                                return new PriorityCell();

                            }
                        });
                        GLOBAL_STAR_STATUS = 1;
                        inlineEditingDAO.updateStarStatus(primaryInboxTabList);
                    } else {
                        imageViewGlobal.setImage(imgGlobalYellowStar);
                        for (CounselorDetailsBEAN cdbean : primaryInboxTabList) {
                            cdbean.setRating("0");
                        }
                        clmTabPriority.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {
                            @Override
                            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                                return new PriorityCell();
                            }
                        });
                        GLOBAL_STAR_STATUS = 0;
                        inlineEditingDAO.updateStarStatus(primaryInboxTabList);
                    }
                }
            }
        }
        );

        /* ======================== End Global Priority ==================== */
        Text text = new Text("No enquiries to show !");

        text.setStyle(
                "-fx-fill:red");
        Pane hBox = new Pane();

        hBox.getChildren()
                .addAll(imageViewGlobal, text);
        ImageView imageViewPlaceHolder = new ImageView(new Image(FXMLCounselorMainViewController.class
                .getResourceAsStream("images/no_enq_show.png")));

        tableName.setPlaceholder(imageViewPlaceHolder);

        tableName.setEditable(
                true);
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabSno = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Sno");

//        clmSno.setCellValueFactory(
//                new PropertyValueFactory<>("count"));
        clmTabSno.setCellFactory(
                new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param
            ) {
                return new NumberedCell();
            }

        }
        );
        TableColumn clmTabCname = new TableColumn("Name");

        clmTabCname.setCellValueFactory(
                new PropertyValueFactory<>("contactName"));
//        clmTabCname.setCellFactory(TextFieldTableCell.forTableColumn());
//        clmTabCname.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> t) {
//                ((CounselorDetailsBEAN) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow())).setContactName(t.getNewValue());
//                ((CounselorDetailsBEAN) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow())).setLastUpdate(LocalDate.now().toString());
//                inlineEditingDAO.updateEnquiryIntoDB((CounselorDetailsBEAN) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow()));
//                System.out.println("committed ");
//            }
//        });

        TableColumn<CounselorDetailsBEAN, String> clmTabPhone = new TableColumn("Contact No");

        clmTabPhone.setCellValueFactory(
                new PropertyValueFactory<>("contactNumber1"));
        if (!IS_COMMON_POOL) {
            clmTabPhone.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {
                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditContactNumbers();
                }
            });
            clmTabPhone.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    // Context.getInstance().currentProfile().setCurrentTab("study");

                }
            });

            clmTabPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));
                    tableName.getColumns().get(0).setVisible(false);
                    tableName.getColumns().get(0).setVisible(true);
                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
//         if (t.getNewValue() != null) {
//                    if ((t.getNewValue().length() == 10) && (t.getNewValue().matches("[0-9]*"))) {
//                        ((CounselorDetailsBEAN) t.getTableView().getItems().get(t.getTablePosition().getRow())).setContactNumber1(t.getNewValue());
//                        ((CounselorDetailsBEAN) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())).setLastUpdate(LocalDate.now().toString());
//                        inlineEditingDAO.updateEnquiryIntoDB((CounselorDetailsBEAN) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow()));
//                        System.out.println("tets valuee :: " + t.getNewValue());
//                    } else {
//                        ((CounselorDetailsBEAN) t.getTableView().getItems().get(t.getTablePosition().getRow())).setContactNumber1(t.getOldValue());
//                    }
//                } else {
//
//                    ((CounselorDetailsBEAN) t.getTableView().getItems().get(t.getTablePosition().getRow())).setContactNumber1(t.getOldValue());
//
//                }
        TableColumn<CounselorDetailsBEAN, String> clmTabAddress = new TableColumn<CounselorDetailsBEAN, String>("Address");

        clmTabAddress.setCellValueFactory(
                new PropertyValueFactory<>("district"));
        if (!IS_COMMON_POOL) {

            clmTabAddress.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {
                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditAddressCell();
                }
            });
            clmTabAddress.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));

                }
            });
            clmTabAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabApplicationStatus = new TableColumn<CounselorDetailsBEAN, String>("Status");

        clmTabApplicationStatus.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param
            ) {

                StringProperty enquiryStatus = new SimpleStringProperty();
                for (EnquirySatusPOJO statusPOJO : RetrieveStaticMasterDAO.retrieveEnquiryAppStatus()) {
                    if (statusPOJO != null) {
                        if (statusPOJO.getId().equalsIgnoreCase(param.getValue().getStatus())) {
                            enquiryStatus.setValue(statusPOJO.getStatus());
                            break;
                        }
                    }
                }
                return enquiryStatus;
            }
        ;
        }
    );
    if (!IS_COMMON_POOL) {

            //  clmTabApplicationStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            clmTabApplicationStatus.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {
                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditEnquiryStatusCell();
                }
            });
            clmTabApplicationStatus.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));

                }
            });
            clmTabApplicationStatus.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    System.out.println("Committed Enquiry Status");
                    /* ======================== Reload Follow Up Status ==================== */
                    initTodayFollowUpCount();
                    /* ====================== reload status after modification ====================== */
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    tableName.getColumns().get(0).setVisible(false);
                    tableName.getColumns().get(0).setVisible(true);
                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                    if (serviceHyperLinkCounts != null) {
                        if (!serviceHyperLinkCounts.isRunning()) {
                            serviceHyperLinkCounts.reset();
                            serviceHyperLinkCounts.restart();
                        }
                    } else {
                        serviceHyperLinkCounts.start();
                    }
                }
            });
        }
        TableColumn clmTabRemarks = new TableColumn("Remarks");
        if (!IS_COMMON_POOL) {

            clmTabRemarks.setCellFactory(TextFieldTableCell.forTableColumn());
            clmTabRemarks.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> t) {
                    if (t.getNewValue() != null) {
                        ((CounselorDetailsBEAN) t.getTableView().getItems().get(t.getTablePosition().getRow())).setRemarks(t.getNewValue());
                        inlineEditingDAO.editRemarks(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    }
                }
            });
        }

        clmTabRemarks.setCellValueFactory(
                new PropertyValueFactory<>("remarks"));

        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabEnquiryBy = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Assinged By");

        clmTabEnquiryBy.setCellValueFactory(
                new PropertyValueFactory<>("enquiryAssignedBy"));

        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabDate = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Date");

        clmTabDate.setCellValueFactory(
                new PropertyValueFactory<>("edate"));

        TableColumn<CounselorDetailsBEAN, String> clmTabQualification = new TableColumn<CounselorDetailsBEAN, String>("Qualification");

        clmTabQualification.setCellValueFactory(
                new PropertyValueFactory<>("qualification"));
        if (!IS_COMMON_POOL) {

            clmTabQualification.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditQualifications();
                }
            });
            clmTabQualification.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    Context.getInstance().currentProfile().setCurrentTab("study");

                }
            });
            clmTabQualification.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabLanguageTest = new TableColumn<CounselorDetailsBEAN, String>("Language Test");

        clmTabLanguageTest.setCellValueFactory(
                new PropertyValueFactory<>("languageTest"));
        if (!IS_COMMON_POOL) {

            clmTabLanguageTest.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditLanguageTest();
                }
            });
            clmTabLanguageTest.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    Context.getInstance().currentProfile().setCurrentTab("study");

                }
            });
            clmTabLanguageTest.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabAdmissionTest = new TableColumn<CounselorDetailsBEAN, String>("Admission Test");

        clmTabAdmissionTest.setCellValueFactory(
                new PropertyValueFactory<>("admissionTest"));
        if (!IS_COMMON_POOL) {

            clmTabAdmissionTest.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditAdmissionTest();
                }
            });
            clmTabAdmissionTest.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    Context.getInstance().currentProfile().setCurrentTab("study");

                }
            });
            clmTabAdmissionTest.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabExperience = new TableColumn<CounselorDetailsBEAN, String>("Experience");

        clmTabExperience.setCellValueFactory(
                new PropertyValueFactory<>("experience"));
        if (!IS_COMMON_POOL) {

            clmTabExperience.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditExperience();
                }
            });
            clmTabExperience.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                }
            });
            clmTabExperience.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));
                    System.out.println("Committed Experience Cell");
                    System.out.println("Reloaded Value :: " + event.getTableView().getItems().get(event.getTablePosition().getRow()).getExperience());
                    System.out.println("Reloaded Value :: " + event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID());
                    System.out.println("Reloaded Value :: " + event.getTableView().getItems().get(event.getTablePosition().getRow()).getContactName());
                    System.out.println("From DB :: " + InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()).getExperience());
                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        /* =============================================  Only For Study =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabStudyRequired = new TableColumn<>("Study Required");

        clmTabStudyRequired.setMaxWidth(
                600);
        clmTabStudyRequired.setPrefWidth(
                138);
        clmTabStudyRequired.setMinWidth(
                10);
        clmTabStudyRequired.setCellValueFactory(
                new PropertyValueFactory<>("studyRequired"));
        if (!IS_COMMON_POOL) {

            clmTabStudyRequired.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditStudyRequiredCell();

                }
            });

            clmTabStudyRequired.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                }
            });
            clmTabStudyRequired.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));
                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabStudyCountryLocation = new TableColumn<CounselorDetailsBEAN, String>("Country & Location");

        clmTabStudyCountryLocation.setCellValueFactory(
                new PropertyValueFactory<>("studyCountryLocation"));
        if (!IS_COMMON_POOL) {

            clmTabStudyCountryLocation.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditCountryLocation();

                }
            });

            clmTabStudyCountryLocation.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    Context.getInstance().currentProfile().setCurrentTab("study");

                }
            });
            clmTabStudyCountryLocation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        /* =============================================  Only For Work =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabWorkRequired = new TableColumn<>("Work Required");

        clmTabWorkRequired.setMaxWidth(
                600);
        clmTabWorkRequired.setPrefWidth(
                138);
        clmTabWorkRequired.setMinWidth(
                10);
        clmTabWorkRequired.setCellValueFactory(
                new PropertyValueFactory<>("workRequired"));
        if (!IS_COMMON_POOL) {

            clmTabWorkRequired.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditWorkRequiredCell();

                }
            });

            clmTabWorkRequired.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));

                }
            });
            clmTabWorkRequired.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabWorkCountryLocation = new TableColumn<CounselorDetailsBEAN, String>("Country & Location");

        clmTabWorkCountryLocation.setCellValueFactory(
                new PropertyValueFactory<>("workCountryLocation"));
        if (!IS_COMMON_POOL) {

            clmTabWorkCountryLocation.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditCountryLocation();

                }
            });

            clmTabWorkCountryLocation.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    Context.getInstance().currentProfile().setCurrentTab("work");
                }
            });
            clmTabWorkCountryLocation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabSalaryExpected = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Salary Expected");

        clmTabSalaryExpected.setCellValueFactory(
                new PropertyValueFactory<>("salaryExpected"));

        /* =============================================  Only For Migration =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabMigrationRequired = new TableColumn<>("Migration Required");

        clmTabMigrationRequired.setMaxWidth(
                600);
        clmTabMigrationRequired.setPrefWidth(
                138);
        clmTabMigrationRequired.setMinWidth(
                10);
        clmTabMigrationRequired.setCellValueFactory(
                new PropertyValueFactory<>("migrationRequired"));
        if (!IS_COMMON_POOL) {

            clmTabMigrationRequired.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditMigrationRequiredCell();

                }
            });

            clmTabMigrationRequired.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                }
            });
            clmTabMigrationRequired.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });

        }
        TableColumn<CounselorDetailsBEAN, String> clmTabMigrationCountryLocation = new TableColumn<CounselorDetailsBEAN, String>("Country & Location");

        clmTabMigrationCountryLocation.setCellValueFactory(
                new PropertyValueFactory<>("migrateCountryLocation"));
        if (!IS_COMMON_POOL) {

            clmTabMigrationCountryLocation.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditCountryLocation();

                }
            });

            clmTabMigrationCountryLocation.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    Context.getInstance().currentProfile().setCurrentTab("migration");
                }
            });
            clmTabMigrationCountryLocation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabApplicationType = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Application Type");

        clmTabApplicationType.setCellValueFactory(
                new PropertyValueFactory<>("applicationType"));


        /* =============================================  Only For Training =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabTrainingRequired = new TableColumn<>("Training Required");

        clmTabTrainingRequired.setMaxWidth(
                600);
        clmTabTrainingRequired.setPrefWidth(
                138);
        clmTabTrainingRequired.setMinWidth(
                10);
        clmTabTrainingRequired.setCellValueFactory(
                new PropertyValueFactory<>("trainingRequired"));
        if (!IS_COMMON_POOL) {

            clmTabTrainingRequired.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditTrainingRequiredCell();

                }
            });

            clmTabTrainingRequired.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));

                }
            });
            clmTabTrainingRequired.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabJoiningDate = new TableColumn<>("Joining Date");
        if (!IS_COMMON_POOL) {

            clmTabJoiningDate.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditDateOfJoin();
                }
            });
            clmTabJoiningDate.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> t) {

                    ((CounselorDetailsBEAN) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setJoiningDate(t.getNewValue());
                    System.out.println("Committed !!");
                    inlineEditingDAO.updateTrainingJoiningDate(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    t.getTableView().getItems().set(t.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, t.getTableView().getItems().get(t.getTablePosition().getRow()).getEnquiryID()));
                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        TableColumn<CounselorDetailsBEAN, String> clmTabBatch = new TableColumn<>("Batch & Timing");

        clmTabBatch.setCellValueFactory(
                new PropertyValueFactory<>("batch"));
        if (!IS_COMMON_POOL) {

            clmTabBatch.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditBatchCell();

                }
            });

            clmTabBatch.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));

                }
            });
            clmTabBatch.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));
                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }

        clmTabJoiningDate.setCellValueFactory(
                new PropertyValueFactory<>("joiningDate"));
        TableColumn<CounselorDetailsBEAN, String> clmTabTrainingCountryLocation = new TableColumn<>("Country & Location");

        clmTabTrainingCountryLocation.setCellValueFactory(
                new PropertyValueFactory<>("trainingCountryLocation"));
        if (!IS_COMMON_POOL) {

            clmTabTrainingCountryLocation.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, String>, TableCell<CounselorDetailsBEAN, String>>() {

                @Override
                public TableCell<CounselorDetailsBEAN, String> call(TableColumn<CounselorDetailsBEAN, String> param) {
                    return new EditCountryLocation();

                }
            });

            clmTabTrainingCountryLocation.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(event.getTableView().getItems().get(event.getTablePosition().getRow()));

                }
            });
            clmTabTrainingCountryLocation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                    event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));

                    refreshPriorityCell(clmTabPriority);
                    refreshFlagyCell(clmTabFlag);
                }
            });
        }
        /* =============================================  End =========================================== */
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabForward = new TableColumn<>("Forward For");

        clmTabForward.setCellValueFactory(
                new PropertyValueFactory<>("forwardFor"));
        // Add editing icon 
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabEdit = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Edit");

        clmTabEdit.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN>, ObservableValue<CounselorDetailsBEAN>>() {

            @Override
            public ObservableValue<CounselorDetailsBEAN> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN> edit
            ) {
                return new ReadOnlyObjectWrapper(edit.getValue());

            }
        }
        );
        clmTabEdit.setCellFactory(
                new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param
            ) {
                return new TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>() {
                    ImageView editIconView = new ImageView();
                    Image editIcon = new Image(FXMLCounselorMainViewController.class
                            .getResourceAsStream("edit.png"));
                    final HBox hboxEditIcon = new HBox();

                    //   Tooltip tooltip = new Tooltip();
                    {
                        hboxEditIcon.getChildren().add(editIconView);
                        hboxEditIcon.setAlignment(Pos.CENTER);
                        hboxEditIcon
                                .setCursor(new ImageCursor(new Image(INALoginForm.class
                                        .getResourceAsStream("images/cursor_hand_red.png"))));
                        setGraphic(hboxEditIcon);
                    }

                    @Override
                    public void updateItem(final CounselorDetailsBEAN cpojo, boolean empty) {
                        super.updateItem(cpojo, empty);
                        if (!empty) {
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            if (cpojo != null) {
                                editIconView.setImage(editIcon);
                                editIconView.setFitHeight(24);
                                editIconView.setFitWidth(26);
                                hboxEditIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

                                    @Override
                                    public void handle(MouseEvent event) {
                                        System.out.println("test image click " + cpojo.toString());
                                        /* =============> Load perspective enquiry to  assessment <============ */

                                        if (event.getButton().equals(MouseButton.PRIMARY)) {

                                            //  if (event.getClickCount() == 2) {
                                            try {
                                                // CounselorDetailsBEAN counselorDetailsBEAN = (CounselorDetailsBEAN) tblPrimaryInbox.getSelectionModel().getSelectedItem();
                                                Context.getInstance().currentProfile().setCounselorDetailsBEAN(cpojo);
                                                StackPane secondaryLayout = new StackPane();
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/assesment/FXMLAssesmentForm.fxml"));
                                                Parent root = (Parent) loader.load();
                                                secondaryLayout.getChildren().add(root);
                                                Scene secondScene = new Scene(root);
                                                Stage secondStage = new Stage();
                                                secondStage.setTitle("Assessment form of " + cpojo.getContactName());
                                                secondStage
                                                        .getIcons().add(new Image(INALoginForm.class
                                                                .getResourceAsStream("images/ia_logo.png")));
                                                secondStage.setScene(secondScene);
                                                Screen screen = Screen.getPrimary();
                                                Rectangle2D bounds = screen.getVisualBounds();
                                                secondStage
                                                        .getIcons().add(new Image(INALoginForm.class
                                                                .getResourceAsStream("images/ia_logo.png")));
                                                // close event
                                                secondStage.setOnHidden(new EventHandler<WindowEvent>() {

                                                    @Override
                                                    public void handle(WindowEvent event) {
                                                        DBPool.getInstance().CloseConnections();
                                                        resetAll();
                                                    }
                                                });
                                                secondStage.setMaximized(true);
                                                secondStage.initModality(Modality.APPLICATION_MODAL);
                                                secondStage.initOwner(btnSearchSubmit.getScene().getWindow());
                                                secondStage.showAndWait();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                                logger.error(ex.toString());
                                            }
                                        }

                                    }
                                });
                            }
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }

                };

            }
        }
        );

        // load all tables
        tableName.getColumns()
                .clear();
        tableName.setCursor(Cursor.DEFAULT);

        tableName.setTableMenuButtonVisible(
                true);

        /* ====================== old backups ====================== */
//        switch (tabName) {
//            case "study":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabStudyRequired, clmTabStudyCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//                break;
//            case "work":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabWorkRequired, clmTabWorkCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//
//                break;
//            case "migration":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabMigrationRequired, clmTabMigrationCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//
//                break;
//            case "training":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabTrainingRequired, clmTabJoiningDate, clmTabBatch, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//                break;
//        }
        /* ====================== end backups ====================== */
        switch (tabName) {
            case "study":
                if (!IS_COMMON_POOL) {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabFlag, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabStudyRequired, clmTabStudyCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                } else {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabStudyRequired, clmTabStudyCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                }
                break;
            case "work":
                if (!IS_COMMON_POOL) {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabFlag, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabExperience, clmTabWorkRequired, clmTabSalaryExpected, clmTabWorkCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                } else {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabExperience, clmTabWorkRequired, clmTabSalaryExpected, clmTabWorkCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                }
                break;
            case "migration":
                if (!IS_COMMON_POOL) {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabFlag, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabMigrationRequired, clmTabApplicationType, clmTabMigrationCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                } else {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabMigrationRequired, clmTabApplicationType, clmTabMigrationCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                }
                break;
            case "training":
                if (!IS_COMMON_POOL) {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabFlag, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabTrainingRequired, clmTabJoiningDate, clmTabBatch, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                } else {
                    tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabTrainingRequired, clmTabJoiningDate, clmTabBatch, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
                }
                break;
        }

        //  tableName.getStyleClass().addAll("tblpinbox", "mylistview");
        /* ====================== Load Data To Tableview And Show Progress ====================== */
        Task<ObservableList<CounselorDetailsBEAN>> taskProgress = new GetEnquiryLoadTask(primaryInboxTabList);

        progressIndicator.progressProperty()
                .bind(taskProgress.progressProperty());
        regionOverlay.visibleProperty()
                .bind(taskProgress.runningProperty());
        progressIndicator.visibleProperty()
                .bind(taskProgress.runningProperty());
        tableName.itemsProperty()
                .bind(taskProgress.valueProperty());
        new Thread(taskProgress)
                .start();
        /* ======================== test row factory ==================== */
//        tableName.getItems().setAll(primaryInboxTabList);
        tableName.setRowFactory(new Callback<TableView<CounselorDetailsBEAN>, TableRow<CounselorDetailsBEAN>>() {
            @Override
            public TableRow<CounselorDetailsBEAN> call(TableView<CounselorDetailsBEAN> param) {
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem itemOpenEnquiry = new MenuItem("Open Assesment Form");
                final MenuItem itemOpenRegistration = new MenuItem("Open Registration Form");
                final MenuItem itemForwardEnquiry = new MenuItem("Forward Enquiries");
                final MenuItem itemLockEnquiry = new MenuItem("Lock Enquiry");
                final TableRow<CounselorDetailsBEAN> row = new TableRow<CounselorDetailsBEAN>() {
                    @Override
                    protected void updateItem(CounselorDetailsBEAN detailsBEAN, boolean empty) {
                        super.updateItem(detailsBEAN, empty);
                        if (!empty) {
                            if (detailsBEAN.getReadflag() != null) {
                                setTooltip(new Tooltip("Double click a cell to edit \n Press 'Enter key' to finish editing"));
                                if (detailsBEAN.getReadflag().equals("0")) {
                                    getStyleClass().remove("highlightOldEnquiryRow");
                                    getStyleClass().add("highlighNewEnquiryRow");

                                } else {
                                    getStyleClass().remove("highlighNewEnquiryRow");
                                    getStyleClass().add("highlightOldEnquiryRow");
                                }
                            } else {
                                getStyleClass().remove("highlightOldEnquiryRow");
                                getStyleClass().add("highlighNewEnquiryRow");
                            }
                        } else {
                            getStyleClass().remove("highlighNewEnquiryRow");
                            getStyleClass().add("highlightOldEnquiryRow");
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                itemOpenEnquiry.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            /* ======================== set which assessment category ==================== */

                            switch (tabName) {
                                case "study":
                                    Context.getInstance().currentProfile().setCurrentTab("study");
                                    break;
                                case "work":
                                    Context.getInstance().currentProfile().setCurrentTab("work");
                                    break;
                                case "migration":
                                    Context.getInstance().currentProfile().setCurrentTab("migration");
                                    break;
                                case "training":
                                    Context.getInstance().currentProfile().setCurrentTab("training");
                                    break;
                                default:
                                    System.out.println("Something went wrong !");
                            }
                            /* ======================== End set which assessment category ==================== */

                            Context.getInstance().currentProfile().setCounselorDetailsBEAN(row.getItem());
                            StackPane secondaryLayout = new StackPane();
                            Pane myPane = null;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/assesment/FXMLAssesmentForm.fxml"));
                            Parent root = (Parent) loader.load();
                            secondaryLayout.getChildren().add(root);
                            Scene secondScene = new Scene(secondaryLayout);
                            Stage secondStage = new Stage();
                            secondStage.setTitle("Assessment form of " + row.getItem().getContactName());
                            secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                            secondStage.setScene(secondScene);
                            secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                            secondStage.setOnHidden(new EventHandler<WindowEvent>() {

                                @Override
                                public void handle(WindowEvent event) {
                                    DBPool.getInstance().CloseConnections();
                                    resetAll();
                                }
                            });
                            //        stageAppointment.initStyle(StageStyle.UNDECORATED);
                            secondStage.setMaximized(true);
                            secondStage.initModality(Modality.APPLICATION_MODAL);
                            secondStage.initOwner(btnSearchSubmit.getScene().getWindow());
                            secondStage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            logger.error(ex.toString());
                        }

                    }
                });
                /* ======================== Open Registration Form ==================== */
                itemOpenRegistration.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            /* ======================== set which assessment category ==================== */

                            switch (tabName) {
                                case "study":
                                    Context.getInstance().currentProfile().setCurrentTab("study");
                                    break;
                                case "work":
                                    Context.getInstance().currentProfile().setCurrentTab("work");
                                    break;
                                case "migration":
                                    Context.getInstance().currentProfile().setCurrentTab("migration");
                                    break;
                                case "training":
                                    Context.getInstance().currentProfile().setCurrentTab("training");
                                    break;
                                default:
                                    System.out.println("Something went wrong !");
                            }
                            /* ======================== End set which assessment category ==================== */

                            Context.getInstance().currentProfile().setCounselorDetailsBEAN(row.getItem());
                            StackPane secondaryLayout = new StackPane();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/registration/FXMLRegistrationForm.fxml"));
                            Parent root = (Parent) loader.load();
                            secondaryLayout.getChildren().add(root);
                            Scene secondScene = new Scene(secondaryLayout);
                            Stage secondStage = new Stage();
                            secondStage.setTitle("Registration form of " + row.getItem().getContactName());
                            secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                            secondStage.setScene(secondScene);
                            secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                            secondStage.setOnHidden(new EventHandler<WindowEvent>() {

                                @Override
                                public void handle(WindowEvent event) {
                                    DBPool.getInstance().CloseConnections();
                                    resetAll();
                                }
                            });
                            //        stageAppointment.initStyle(StageStyle.UNDECORATED);
                            secondStage.setMaximized(true);
                            secondStage.initModality(Modality.APPLICATION_MODAL);
                            secondStage.initOwner(btnSearchSubmit.getScene().getWindow());
                            secondStage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            logger.error(ex.toString());
                        }

                    }
                });
                /* ======================== Forward Enquiry ==================== */
                itemForwardEnquiry.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Pane myPane = new Pane();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/inbox/forward/FXMLForward.fxml"));
                        try {
                            Parent root = (Parent) loader.load();
                            // System.out.println(loader.getController());
                            FXMLForwardController controller = (FXMLForwardController) loader.getController();
                            if (listCheckedEnquiries.size() > 0) {
                                controller.forwardMultipleEnquiries(listCheckedEnquiries, popForward, tableName, CUR_ROLE);
                            } else {
                                controller.initData(row.getItem(), popForward);
                            }
                            myPane.getChildren().add(root);
                            popForward.setContentNode(myPane);
                            popForward.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
                            popForward.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
                            popForward.setArrowSize(12.0);
                            popForward.setHideOnEscape(true);
                            popForward.setAutoFix(true);
                            popForward.setAutoHide(true);
                            popForward.setTitle("Forward Enquiry Of " + row.getItem().getContactName());
//                            popForward.setOnAutoHide(new EventHandler<Event>() {
//
//                                @Override
//                                public void handle(Event event) {
//                                    tableName.getColumns().get(0).setVisible(false);
//                                    tableName.getColumns().get(0).setVisible(true);
//                                    refreshPriorityCell(clmTabPriority);
////                                    Event.fireEvent(lblMonth, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
////                                            0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
////                                            true, true, true, true, true, true, null));
//                                }
//                            });
                            popForward.setOnHidden(new EventHandler<WindowEvent>() {

                                @Override
                                public void handle(WindowEvent event) {
                                    reloadTableWithProgress(Context.getInstance().currentProfile().getInboxCountsBEAN());
                                    int c = 0;
                                    for (TableColumn column : tableName.getColumns()) {
                                        c++;
                                        if (c == 2) {
                                            refreshPriorityCell(column);
                                        }
                                        if (c == 3) {
                                            refreshFlagyCell(column);
                                        }
                                    }
                                    if (serviceHyperLinkCounts != null) {
                                        if (!serviceHyperLinkCounts.isRunning()) {
                                            serviceHyperLinkCounts.reset();
                                            serviceHyperLinkCounts.restart();
                                        }
                                    } else {
                                        serviceHyperLinkCounts.start();
                                    }

                                    /* ======================== Refresh All Data To Common Pool ==================== */
 /* ======================== Fires MousEvent Of lblToday ==================== */
//                                            Event.fireEvent(hyperCommonPool, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
//                                                            0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
//                                                            true, true, true, true, true, true, null));
                                    listCheckedEnquiries.clear();
                                }
                            });

                        } catch (IOException exception) {
                            exception.printStackTrace();
                            logger.error(exception.toString());
                        }
                        if (popForward.isShowing()) {
                            popForward.hide();
                            popForward.show(row, row.localToScreen(0, 0).getX() - 6, row.localToScreen(0, 0).getY() + 30, Duration.millis(100));

                        } else {
                            popForward.show(row, row.localToScreen(0, 0).getX() - 6, row.localToScreen(0, 0).getY() + 30, Duration.millis(500));
                        }
                    }
                });
                /* ======================== Add To Commom Pool ==================== */
                itemLockEnquiry.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Map<String, String> mapCommonPoolConfig = CommonPoolService.retrieveCommonPoolConfig(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                        Map<String, String> mapFlagEnquiryStatus = CommonPoolService.checkFlagEnquiryStatus(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                        long flagLimitConfig = Long.parseLong(mapCommonPoolConfig.get("flag_limit"));
                        long flagLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("flag_limit"));
                        long enquiryLimitConfig = Long.parseLong(mapCommonPoolConfig.get("enquiry_limit"));
                        long enquiryLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("enquiry_limit"));
//                        if (flagLimitConfig == 0 || flagLimitConfig < enquiryLimitConfig) {
//                            System.out.println("User can flag");
//                        }
                        if (enquiryLimitConfig == 0 || enquiryLimitStatus < enquiryLimitConfig) {
                            if (CommonPoolService.verifyEnquiryLocking(CUR_USERNAME, CUR_BRANCH, row.getItem().getEnquiryID())) {
                                int done = CommonPoolService.lockEnquiry(CUR_USERNAME, CUR_BRANCH, CUR_ROLE, row.getItem());
                                if (done > 0) {
                                    /* ======================== Add History Row For Self Assign ==================== */
                                    ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                                    historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                                    historyPOJO.setAssigned_branch(CUR_BRANCH);
                                    historyPOJO.setAssigned_by("Self Assigned");
                                    historyPOJO.setAssigned_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());
                                    historyPOJO.setEnquiry_id(row.getItem().getEnquiryID());
                                    historyPOJO.setAssigned_to(CUR_USERNAME);
//                                    row.getItem().setEnquiryAssignedTo(CUR_USERNAME);
                                    historyPOJO.setAssigned_to_branch(CUR_BRANCH);
                                    historyPOJO.setHolded_by(row.getItem().getEnquiryAssignedTo());
                                    historyPOJO.setPurpose(row.getItem().getReference());
                                    historyPOJO.setRemarks(row.getItem().getRemarks());
                                    if (row.getItem().getStatus() != null) {
                                        historyPOJO.setCurrent_status(row.getItem().getStatus());
                                    } else {
                                        historyPOJO.setCurrent_status("Assessment Pending");
                                    }
                                    historyPOJO.setCompletionFlag(row.getItem().getCompletionflag());
                                    historyPOJO.setStudy_required(row.getItem().getStudyRequired());
                                    historyPOJO.setWork_required(row.getItem().getWorkRequired());
                                    historyPOJO.setMigration_required(row.getItem().getMigrationRequired());
                                    historyPOJO.setTraining_required(row.getItem().getTrainingRequired());
                                    forwardHistoryDAO.forwardEnquiry(historyPOJO);
                                }
                                int index = row.indexProperty().get();
                                row.getStyleClass().remove("highlighNewEnquiryRow");
                                row.getStyleClass().add("highlightOldEnquiryRow");
                                row.setGraphic(new Label("This enquiry has been moved to your inbox"));
                                EffectsClass.blinkingAndFading(row);
                                PauseTransition delay = new PauseTransition(Duration.seconds(3.5));
                                delay.setOnFinished((ActionEvent event1) -> {
                                    /* ======================== Refresh Table ==================== */
                                    tableName.getItems().remove(index);
                                    int c = 0;
                                    for (TableColumn column : tableName.getColumns()) {
                                        c++;
                                        if (c == 2) {
                                            refreshPriorityCell(column);
                                        }
                                        if (c == 3) {
                                            //    refreshFlagyCell(column);
                                        }
                                    }

                                });
                                delay.play();
                                if (serviceHyperLinkCounts != null) {
                                    if (!serviceHyperLinkCounts.isRunning()) {
                                        serviceHyperLinkCounts.reset();
                                        serviceHyperLinkCounts.restart();
                                    }
                                } else {
                                    serviceHyperLinkCounts.start();
                                }
                            } else {
                                /* ======================== Remove Already Locked Enquiry ==================== */
                                int index = row.indexProperty().get();
                                tableName.getItems().remove(index);
                                int c = 0;
                                for (TableColumn column : tableName.getColumns()) {
                                    c++;
                                    if (c == 2) {
                                        refreshPriorityCell(column);
                                    }
                                    if (c == 3) {
                                        //    refreshFlagyCell(column);
                                    }
                                }
                                Notification notification = Notifications.WARNING;
                                TrayNotification tray = new TrayNotification("Already locked !", "This enquiry locked! Please refresh to see latest enquiries !", notification);
                                tray.setAnimation(Animations.POPUP);
                                tray.showAndDismiss(Duration.seconds(3), btnSearchSubmit);
                                tray.setOnDismiss(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        int c = 0;
                                        for (TableColumn column : tableName.getColumns()) {
                                            c++;
                                            if (c == 2) {
                                                refreshPriorityCell(column);
                                            }
                                            if (c == 3) {
                                                //    refreshFlagyCell(column);
                                            }
                                        }
                                    }
                                });
                            }/* ======================== End verify locking ==================== */

                        } else {
                            showPopupMessages.showWarning("Your pending enquiry limit reached !", "Please complete all pending enquiries !", row);
                        }

                        /* ======================== Refresh All Columns ==================== */
//                        int c = 0;
//                        for (TableColumn column : tableName.getColumns()) {
//                            c++;
//                            if (c == 2) {
//                                refreshPriorityCell(column);
//                            }
//                            if (c == 3) {
//                                refreshFlagyCell(column);
//                            }
//                        }
                    }
                });
                if (IS_COMMON_POOL) {
                    contextMenu.getItems().addAll(itemLockEnquiry);
                    row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                if (event.getClickCount() == 2) {
                                    Map<String, String> mapCommonPoolConfig = CommonPoolService.retrieveCommonPoolConfig(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                                    Map<String, String> mapFlagEnquiryStatus = CommonPoolService.checkFlagEnquiryStatus(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                                    long flagLimitConfig = Long.parseLong(mapCommonPoolConfig.get("flag_limit"));
                                    long flagLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("flag_limit"));
                                    long enquiryLimitConfig = Long.parseLong(mapCommonPoolConfig.get("enquiry_limit"));
                                    long enquiryLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("enquiry_limit"));
//                        if (flagLimitConfig == 0 || flagLimitConfig < enquiryLimitConfig) {
//                            System.out.println("User can flag");
//                        }
                                    if (enquiryLimitConfig == 0 || enquiryLimitStatus < enquiryLimitConfig) {
                                        if (CommonPoolService.verifyEnquiryLocking(CUR_USERNAME, CUR_BRANCH, row.getItem().getEnquiryID())) {
                                            int done = CommonPoolService.lockEnquiry(CUR_USERNAME, CUR_BRANCH, CUR_ROLE, row.getItem());
                                            if (done > 0) {
                                                /* ======================== Add History Row For Self Assign ==================== */
                                                ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                                                historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                                                historyPOJO.setAssigned_branch(CUR_BRANCH);
                                                historyPOJO.setAssigned_by("Self Assigned");
                                                historyPOJO.setAssigned_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());
                                                historyPOJO.setEnquiry_id(row.getItem().getEnquiryID());
                                                historyPOJO.setAssigned_to(CUR_USERNAME);
//                                    row.getItem().setEnquiryAssignedTo(CUR_USERNAME);
                                                historyPOJO.setAssigned_to_branch(CUR_BRANCH);
                                                historyPOJO.setHolded_by(row.getItem().getEnquiryAssignedTo());
                                                historyPOJO.setPurpose(row.getItem().getReference());
                                                historyPOJO.setRemarks(row.getItem().getRemarks());
                                                if (row.getItem().getStatus() != null) {
                                                    historyPOJO.setCurrent_status(row.getItem().getStatus());
                                                } else {
                                                    historyPOJO.setCurrent_status("Assessment Pending");
                                                }
                                                historyPOJO.setCompletionFlag(row.getItem().getCompletionflag());
                                                historyPOJO.setStudy_required(row.getItem().getStudyRequired());
                                                historyPOJO.setWork_required(row.getItem().getWorkRequired());
                                                historyPOJO.setMigration_required(row.getItem().getMigrationRequired());
                                                historyPOJO.setTraining_required(row.getItem().getTrainingRequired());
                                                forwardHistoryDAO.forwardEnquiry(historyPOJO);
                                            }
                                            int index = row.indexProperty().get();
                                            row.getStyleClass().remove("highlighNewEnquiryRow");
                                            row.getStyleClass().add("highlightOldEnquiryRow");
                                            row.setGraphic(new Label("This enquiry has been moved to your inbox"));
                                            EffectsClass.blinkingAndFading(row);
                                            PauseTransition delay = new PauseTransition(Duration.seconds(3.5));
                                            delay.setOnFinished((ActionEvent event1) -> {
                                                /* ======================== Refresh Table ==================== */
                                                try {
                                                    tableName.getItems().remove(index);
                                                } catch (IndexOutOfBoundsException e) {
                                                    System.out.println("Exception Occured While removing row !!!!!!!!!!!!!!!");
                                                }
                                                int c = 0;
                                                for (TableColumn column : tableName.getColumns()) {
                                                    c++;
                                                    if (c == 2) {
                                                        refreshPriorityCell(column);
                                                    }
                                                    if (c == 3) {
                                                        //    refreshFlagyCell(column);
                                                    }
                                                }

                                            });
                                            delay.play();
                                            if (serviceHyperLinkCounts != null) {
                                                if (!serviceHyperLinkCounts.isRunning()) {
                                                    serviceHyperLinkCounts.reset();
                                                    serviceHyperLinkCounts.restart();
                                                }
                                            } else {
                                                serviceHyperLinkCounts.start();
                                            }
                                        } else {
                                            /* ======================== Remove Already Locked Enquiry ==================== */
                                            int index = row.indexProperty().get();
                                            tableName.getItems().remove(index);
                                            int c = 0;
                                            for (TableColumn column : tableName.getColumns()) {
                                                c++;
                                                if (c == 2) {
                                                    refreshPriorityCell(column);
                                                }
                                                if (c == 3) {
                                                    //    refreshFlagyCell(column);
                                                }
                                            }
                                            Notification notification = Notifications.WARNING;
                                            TrayNotification tray = new TrayNotification("Already locked !", "This enquiry locked! Please refresh to see latest enquiries !", notification);
                                            tray.setAnimation(Animations.POPUP);
                                            tray.showAndDismiss(Duration.seconds(3), btnSearchSubmit);
                                            tray.setOnDismiss(new EventHandler<ActionEvent>() {

                                                @Override
                                                public void handle(ActionEvent event) {
                                                    int c = 0;
                                                    for (TableColumn column : tableName.getColumns()) {
                                                        c++;
                                                        if (c == 2) {
                                                            refreshPriorityCell(column);
                                                        }
                                                        if (c == 3) {
                                                            //    refreshFlagyCell(column);
                                                        }
                                                    }
                                                }
                                            });
                                        }/* ======================== End verify locking ==================== */

                                    } else {
                                        showPopupMessages.showWarning("Your pending enquiry limit reached !", "Please complete all pending enquiries !", row);
                                    }

                                    /* ======================== Refresh All Columns ==================== */
//                        int c = 0;
//                        for (TableColumn column : tableName.getColumns()) {
//                            c++;
//                            if (c == 2) {
//                                refreshPriorityCell(column);
//                            }
//                            if (c == 3) {
//                                refreshFlagyCell(column);
//                            }
//                        }
                                }
                            }
                        }
                    });
                } else {
                    contextMenu.getItems().addAll(itemOpenEnquiry, itemForwardEnquiry, itemOpenRegistration);
                }
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu));
                return row;
            }
        }
        );
        tableName.applyCss();
        int c = 0;
        for (TableColumn column
                : tableName.getColumns()) {
            c++;
            if (c != 1 && c != 2) {
                column.setPrefWidth(113);
                column.setMinWidth(113);
                if (!IS_COMMON_POOL) {

                    if (c == 3) {
                        column.setPrefWidth(32);
                        column.setMinWidth(32);
                        column.setMaxWidth(32);
                        column.getStyleClass().add("borderLess");
                        column.getStyleClass().add("alignLeft");
                    }
                    if (c == 4) {
                        column.setPrefWidth(95);
                        column.setMinWidth(95);
                        column.setMaxWidth(95);
                        column.getStyleClass().add("borderLess");
                        column.getStyleClass().add("alignLeft");
                    }
                } else if (c == 3) {
                    column.setPrefWidth(95);
                    column.setMinWidth(95);
                    column.setMaxWidth(95);
                    column.getStyleClass().add("borderLess");
                    column.getStyleClass().add("alignLeft");
                }
            } else {
                column.setMaxWidth(20);
                column.getStyleClass().add("borderLess");
            }
        }
        /* ==== edit column width ==== */

        clmTabPhone.setPrefWidth(
                85);
        clmTabPhone.setMinWidth(
                85);
        clmTabPhone.setMaxWidth(
                200);
        /* ==== edit column width ==== */
        clmTabEdit.setPrefWidth(
                50);
        clmTabEdit.setMinWidth(
                50);
        clmTabEdit.setMaxWidth(
                50);


        /* ======================== Refresh Data Without Progress Bar ==================== */
    }

    /**
     *
     * @param clmTabPriority
     */
    public void refreshPriorityCell(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabPriority) {
        clmTabPriority.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                return new PriorityCell();
            }
        });
    }

    /**
     *
     * @param clmTabFlag
     */
    public void refreshFlagyCell(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabFlag) {
        clmTabFlag.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                return new FlagCell();
            }
        });
    }

    private void initTodayFollowUpCount() {

        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCountsFollowup(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
        if (listInboxCounts.size() < 0) {
        } else {
            lblTitleFollowUp.setText("");
            lblTitleFollowUp.setText("Follow Up Pending (Today 0)");

        }
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            if (inboxCountsBEAN.getStatusId() != null) {
                if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                    lblTitleFollowUp.setText("");
                    lblTitleFollowUp.setText("Follow Up Pending (Today " + inboxCountsBEAN.getTotalCount() + ")");
                    // folloupsize = Integer.parseInt(inboxCountsBEAN.getTotalCount());

                }
            } else {
                lblTitleFollowUp.setText("");
                lblTitleFollowUp.setText("Follow Up Pending (Today 0)");
            }
        }
        if (listInboxCounts.size() <= 0) {
            lblTitleFollowUp.setText("Follow Up Pending (Today 0)");
        }
    }

    private void initTodayFollowUpCount(ObservableList<InboxCountsBEAN> listInboxCounts) {
        if (listInboxCounts.size() < 0) {

        } else {
            lblTitleFollowUp.setText("");
            lblTitleFollowUp.setText("Follow Up Pending (Today 0)");
        }
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            if (inboxCountsBEAN.getStatusId() != null) {
                if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                    lblTitleFollowUp.setText("");
                    lblTitleFollowUp.setText("Follow Up Pending (Today " + inboxCountsBEAN.getTotalCount() + ")");
                    // folloupsize = Integer.parseInt(inboxCountsBEAN.getTotalCount());
                }
            } else {
                lblTitleFollowUp.setText("");
                lblTitleFollowUp.setText("Follow Up Pending (Today 0)");
            }
        }
        if (listInboxCounts.size() <= 0) {
            lblTitleFollowUp.setText("Follow Up Pending (Today 0)");

        }
    }

    /**
     *
     */
    public class GetEnquiryLoadTask extends Task<ObservableList<CounselorDetailsBEAN>> {

        List<CounselorDetailsBEAN> enquiriesList = new ArrayList<>();

        private GetEnquiryLoadTask(List<CounselorDetailsBEAN> eList) {
            enquiriesList = eList;
        }

        @Override
        protected ObservableList<CounselorDetailsBEAN> call() throws Exception {

            ObservableList<CounselorDetailsBEAN> enquiriesObsList = FXCollections.observableArrayList();
            for (CounselorDetailsBEAN counselorDetailsBEAN : enquiriesList) {
                enquiriesObsList.add(counselorDetailsBEAN);
            }
            for (int i = 0; i <= 200; i++) {
                updateProgress(i, 200);
                Thread.sleep(3);
            }
            return enquiriesObsList;
        }
    }

    /* ===================================================================== */
 /* ========================= Numbered Cell ============================= */
 /* ===================================================================== */
    /**
     *
     */
    public class NumberedCell extends TableCell {

        @Override
        protected void updateItem(Object object, boolean selected) {
            if (!selected) {
                setText(String.valueOf(getIndex() + 1));
            }
        }
    }

    /**
     *
     * @param <S>
     * @param <T>
     */
    public static class CheckBoxTableCell extends TableCell {

        private final CheckBox checkBox;
//        private ObservableValue<T> ov;
        boolean check = false;

        /**
         *
         */
        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);

            setAlignment(Pos.CENTER);
            setGraphic(checkBox);
        }

        /**
         *
         * @param b
         */
        public CheckBoxTableCell(boolean b) {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);
            check = b;
            setAlignment(Pos.CENTER);
            setGraphic(checkBox);
        }

        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(checkBox);
                this.checkBox.setSelected(check);
                CounselorDetailsBEAN cpojo = (CounselorDetailsBEAN) item;

                this.checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        //   CounselorDetailsBEAN cpojo = (CounselorDetailsBEAN) item;
                        if (newValue) {
                            listCheckedEnquiries.add(cpojo);
                        } else {
                            listCheckedEnquiries.remove(cpojo);
                        }
                    }
                });
//                System.out.println("tes :: " + item.toString());
//                if (ov instanceof BooleanProperty) {
//                    checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
//                }
//                ov = getTableColumn().getCellObservableValue(getIndex());
//                if (ov instanceof BooleanProperty) {
//                    checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
//                }
            }
        }
    }

    /**
     *
     * @param active_inbox
     */
    public void hideHyperlinkCounts(String active_inbox) {
        if (active_inbox.equals("primary")) {
            vboxHyperlinks.getChildren().removeAll(vboxHyperlinks.getChildren());
            vboxHyperlinks.getChildren().addAll(hyperTotalEnq, hyperRegistered, hyperAssessed, hyperAsspending, hyperStarred, hyperFakeCall, hyperWrongCall, hyperMsgPending, hyperFollowUp, hyperAppointmnet, hyperCancelled);

        }

    }

    /**
     *
     * @param active_inbox
     */
    public void getAllHyperlinkCounts() {
        /* ======================== load common pool enquiries ==================== */
        hyperCommonPool.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                IS_COMMON_POOL = true;
                listCheckedEnquiries.clear();
                listHyperlinkActive.clear();
                listHyperlinkActive.add(hyperCommonPool);
                lblEnquiryReportTitle.setText("Common Pool");
                /* ====================== For Showing Total Enquiries ====================== */
                Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.getCommonPoolEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
                loadInboxTabsEnquiries(primaryInboxMap);
            }
        });
        /* ====================== load hyperlinks dynamically ====================== */
        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCounts(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
        ObservableList<InboxCountsBEAN> listInboxCountsNotAssigned = InboxRetrievalService.retrieveInboxCounts("Not Assigned", CUR_BRANCH, CUR_ROLE);
        if (listInboxCountsNotAssigned.size() > 0) {
            for (InboxCountsBEAN inboxCountsBEAN : listInboxCountsNotAssigned) {
                String[] splitArray = inboxCountsBEAN.getConcatCount().split(",");
                hyperCommonPool.setText("Common Pool " + "(" + splitArray[tabPanePrimaryInbox.getSelectionModel().getSelectedIndex()] + ")");
                break;
            }
        } else {
            hyperCommonPool.setText("Common Pool (0)");
        }
        vboxHyperlinks.getChildren().clear();
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            String[] splitArray = inboxCountsBEAN.getConcatCount().split(",");
            Hyperlink hyperlink = new Hyperlink(inboxCountsBEAN.getStatus() + "(" + splitArray[tabPanePrimaryInbox.getSelectionModel().getSelectedIndex()] + ")");
            hyperlink.setWrapText(true);
            VBox.setVgrow(hyperlink, Priority.ALWAYS);
            hyperlink.setMaxWidth(Double.MAX_VALUE);
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    IS_COMMON_POOL = false;
                    listCheckedEnquiries.clear();
                    listHyperlinkActive.clear();
                    listHyperlinkActive.add(hyperlink);
                    lblEnquiryReportTitle.setText(inboxCountsBEAN.getStatus());
                    reloadTableWithProgress(inboxCountsBEAN);
                    /* ====================== Set Bean On Context ====================== */
                    Context.getInstance().currentProfile().setInboxCountsBEAN(inboxCountsBEAN);
                }
            });
            vboxHyperlinks.getChildren().add(hyperlink);
        }
    }

    /**
     *
     * @param inboxCountsBEAN
     */
    public void reloadTableWithProgress(InboxCountsBEAN inboxCountsBEAN) {
        if (inboxCountsBEAN.getTableDiff() != null) {
            if (inboxCountsBEAN.getTableDiff().equalsIgnoreCase("A")) {
                Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.retrieveAppStatusData(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, inboxCountsBEAN.getStatusId());
                loadInboxTabsEnquiries(primaryInboxMap);
            } else if (inboxCountsBEAN.getTableDiff().equalsIgnoreCase("T")) {
                Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.retrieveTaskStatusData(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, inboxCountsBEAN.getStatusId());
                loadInboxTabsEnquiries(primaryInboxMap);
            } else if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                /* ====================== For Showing Total Enquiries ====================== */
                Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
                Map map = Collections.synchronizedMap(primaryInboxMap);
                loadInboxTabsEnquiries(map);
            } else if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-2")) {
                /* ====================== For Showing Starred Enquiries ====================== */
                Map<String, List<CounselorDetailsBEAN>> primaryDataMap = InboxRetrievalService.retrieveStarredEnquiries(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, "1", "rating");
                loadInboxTabsEnquiries(primaryDataMap);
//                            retrieveHyperlinkData(resultList);
            }
        }
    }

    /**
     *
     */
    public void addSearchFilters() {
        lblToday.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                searchQueryBEAN.setDateFrom(LocalDate.now().toString());
                searchQueryBEAN.setDateTo(LocalDate.now().toString());
                searchEnquiries(ACTIVE_TAB);
                Label lblInfo = new Label("Showing Today's Enquiries");
                lblInfo.setStyle("-fx-text-fill:red");
//                lblInboxName.setGraphic(lblInfo);
//                lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
            }
        });
        lblYesterday.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                searchQueryBEAN.setDateFrom(LocalDate.now().minusDays(1).toString());
                searchQueryBEAN.setDateTo(LocalDate.now().minusDays(1).toString());
                searchEnquiries(ACTIVE_TAB);
                Label lblInfo = new Label("Showing Yesterday's Enquiries");
                lblInfo.setStyle("-fx-text-fill:red");
//                lblInboxName.setGraphic(lblInfo);
//                lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
            }
        });
        lblMonth.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                searchQueryBEAN.setDateFrom(LocalDate.now().minusDays(LocalDate.now().getDayOfMonth()).toString());
                searchQueryBEAN.setDateTo(LocalDate.now().toString());
                searchEnquiries(ACTIVE_TAB);
                Label lblInfo = new Label("Showing This Month's Enquiries");
                lblInfo.setStyle("-fx-text-fill:red");
//                lblInboxName.setGraphic(lblInfo);
//                lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
            }
        });
        lblWeek.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                searchQueryBEAN.setDateFrom(LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue()).toString());
                searchQueryBEAN.setDateTo(LocalDate.now().toString());
                searchEnquiries(ACTIVE_TAB);
                Label lblInfo = new Label("Showing This Week's Enquiries");
                lblInfo.setStyle("-fx-text-fill:red");
//                /.setGraphic(lblInfo);
//                lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
            }
        });
    }

    /**
     *
     */
    public void bindWithBean() {
        Bindings.bindBidirectional(searchQueryBEAN.nameProperty(), txtName.textProperty());
        Bindings.bindBidirectional(searchQueryBEAN.phoneProperty(), txtPhone.textProperty());
        Bindings.bindBidirectional(searchQueryBEAN.passportNumProperty(), txtPassportNo.textProperty());
        Bindings.bindBidirectional(searchQueryBEAN.dobProperty(), datePickDOB.getEditor().textProperty());
        Bindings.bindBidirectional(searchQueryBEAN.districtProperty(), cmbDistrict.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.stateProperty(), cmbState.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.countryProperty(), cmbCountry.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.qualificationLevelProperty(), cmbQualifiLevel.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.qualificationFieldProperty(), cmbQualifiField.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.languageTestProperty(), cmbLanguageTest.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.scoreProperty(), cmbLanguageScore.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.professionProperty(), cmbProfession.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.durationProperty(), cmbDuration.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.programLevelProperty(), cmbPrgmLevel.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.programFieldProperty(), cmbProgramFld.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.intakeProperty(), cmbIntakeSrch.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.institutionProperty(), cmbInstitution.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.countryPrgmRequiredProperty(), cmbCountryPgmReq.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.locationPrgmRequiredProperty(), cmbLocation.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.applicationStatusProperty(), cmbStatus.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.enquiryMethodProperty(), cmbEnquiryMethod.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.enquirySourceProperty(), cmbSource.valueProperty());
        Bindings.bindBidirectional(searchQueryBEAN.branchProperty(), cmbBranch.valueProperty());

    }

    /**
     *
     */
    public void hideAllClearImages() {
        imgViewClearBranch.setVisible(false);
        imgViewClearDistrict.setVisible(false);
        imgViewClearEnqryStats.setVisible(false);
        imgViewClearName.setVisible(false);
        imgViewClearPrgmField.setVisible(false);
        imgViewClearPrgmLevel.setVisible(false);
        imgViewClearPhone.setVisible(false);
        imgViewClearPassport.setVisible(false);
        imgViewClearDob.setVisible(false);
        imgViewClearState.setVisible(false);
        imgViewClearCountry.setVisible(false);
        imgViewClearQualiLevel.setVisible(false);
        imgViewClearQualiField.setVisible(false);
        imgViewClearLangTest.setVisible(false);
        imgViewClearScore.setVisible(false);
        imgViewClearProfesion.setVisible(false);
        imgViewClearDuration.setVisible(false);
        imgViewClearIntake.setVisible(false);
        imgViewClearInstitution.setVisible(false);
        imgViewClearCountryPrgm.setVisible(false);
        imgViewClearLocation.setVisible(false);
        imgViewClearEnqMethod.setVisible(false);
        imgViewClearAssignedBy.setVisible(false);
        imgViewClearEnqrySorce.setVisible(false);
    }

    /**
     *
     */
    public void resetAll() {
        hideAllClearImages();
        txtName.setText("");
        txtPassportNo.setText("");
        txtPhone.setText("");
        datePickDOB.getEditor().clear();

        cmbState.getSelectionModel().clearSelection();;
        cmbBranch.getSelectionModel().clearSelection();;
        cmbDistrict.getSelectionModel().clearSelection();
        cmbEnquiryMethod.getSelectionModel().clearSelection();
        cmbPrgmLevel.getSelectionModel().clearSelection();
        cmbQualifiLevel.getSelectionModel().clearSelection();
        cmbQualifiField.getSelectionModel().clearSelection();
        cmbLanguageTest.getSelectionModel().clearSelection();
        cmbLanguageScore.getSelectionModel().clearSelection();
        cmbProfession.getSelectionModel().clearSelection();
        cmbDuration.getSelectionModel().clearSelection();
        cmbProgramFld.getSelectionModel().clearSelection();
        cmbIntakeSrch.getSelectionModel().clearSelection();
        cmbInstitution.getSelectionModel().clearSelection();
        cmbCountryPgmReq.getSelectionModel().clearSelection();
        cmbLocation.getSelectionModel().clearSelection();
        cmbSource.getSelectionModel().clearSelection();
        cmbStatus.getSelectionModel().clearSelection();
        cmbAssignBy.getSelectionModel().clearSelection();
        //     lblShowingReports.setText("");
        //   lblInboxName.setGraphic(null);
        //reset combo
        searchQueryBEAN.setAssignedTo(null);
        searchQueryBEAN.setAssignedBy(null);
        searchQueryBEAN.setEnquiryMethod(null);
        searchQueryBEAN.setApplicationStatus(null);
        searchQueryBEAN.setProgramField(null);
        searchQueryBEAN.setProgramLevel(null);
        searchQueryBEAN.setEnquirySource(null);
        searchQueryBEAN.setBranch(null);
        searchQueryBEAN.setState(null);
        searchQueryBEAN.setDistrict(null);
        searchQueryBEAN.setCountry(null);
        //reset data
        /* =============> Load primary inbox enquiries <============ */
        initComboBoxes();

        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxRetrievalService.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME, CUR_ROLE);
        loadInboxTabsEnquiries(primaryInboxMap);
        /* =============> Reset Hyperlinks <============ */
        if (serviceHyperLinkCounts != null) {
            if (!serviceHyperLinkCounts.isRunning()) {
                serviceHyperLinkCounts.reset();
                serviceHyperLinkCounts.restart();
            }
        } else {
            serviceHyperLinkCounts.start();
        }
        titiledPaneSearch.setExpanded(false);

    }

    /**
     *
     * @param primaryInboxMap
     */
    public void loadInboxTabsEnquiries(Map<String, List<CounselorDetailsBEAN>> primaryInboxMap) {
        /* ====================== Sort HashMap Ascending Order ====================== */
        synchronized (primaryInboxMap) {
            List<String> keys = primaryInboxMap.entrySet()
                    .stream()
                    .sorted((left, right)
                            -> Integer.compare(left.getValue().size(), right.getValue().size()))
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList());

            for (String key : keys) {
                System.out.println(key);
                switch (key) {
                    case "study":
                        primaryInboxStudyList = primaryInboxMap.get("study");
                        TableView<CounselorDetailsBEAN> tblPrimaryStudyInbox = new TableView<>();
                        mapCurrentTables.put(key, tblPrimaryStudyInbox);
                        addInboxTabData(tblPrimaryStudyInbox, primaryInboxStudyList, "study");
                        tabPrimaryStudy.setContent(tblPrimaryStudyInbox);

                        break;
                    case "work":
                        primaryInboxWorkList = primaryInboxMap.get("work");
                        TableView<CounselorDetailsBEAN> tblPrimaryWorkInbox = new TableView<>();
                        mapCurrentTables.put(key, tblPrimaryWorkInbox);
                        addInboxTabData(tblPrimaryWorkInbox, primaryInboxWorkList, "work");
                        tabPrimaryWork.setContent(tblPrimaryWorkInbox);

                        break;
                    case "migration":
                        primaryInboxMigrationList = primaryInboxMap.get("migration");
                        TableView<CounselorDetailsBEAN> tblPrimaryMirationInbox = new TableView<>();
                        mapCurrentTables.put(key, tblPrimaryMirationInbox);
                        addInboxTabData(tblPrimaryMirationInbox, primaryInboxMigrationList, "migration");
                        tabPrimaryMigration.setContent(tblPrimaryMirationInbox);

                        break;
                    case "training":
                        primaryInboxTrainingList = primaryInboxMap.get("training");
                        TableView<CounselorDetailsBEAN> tblPrimaryTrainingInbox = new TableView<>();
                        mapCurrentTables.put(key, tblPrimaryTrainingInbox);
                        addInboxTabData(tblPrimaryTrainingInbox, primaryInboxTrainingList, "training");
                        tabPrimaryTraining.setContent(tblPrimaryTrainingInbox);

                        break;
                }
            }
            tabPanePrimaryInbox.setCursor(Cursor.CLOSED_HAND);
        }
    }

    /**
     *
     */
    public void addClearImageEvents() {
        imgViewClearName.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                txtName.clear();
                imgViewClearName.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });

        imgViewClearPhone.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                txtPhone.clear();
                imgViewClearPhone.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearPassport.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                txtPassportNo.clear();
                imgViewClearPassport.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearDob.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                datePickDOB.getEditor().clear();
                imgViewClearDob.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearState.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbState.getSelectionModel().clearSelection();
                imgViewClearState.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearCountry.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbCountry.getSelectionModel().clearSelection();
//                if (cmbCountry.getItems().contains("India")) {
//                    cmbCountry.getSelectionModel().clearAndSelect(cmbCountry.getItems().indexOf("India"));
//                } else {
//                    cmbCountry.getSelectionModel().selectFirst();
//                }
                imgViewClearCountry.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearQualiLevel.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbQualifiLevel.getSelectionModel().clearSelection();
                imgViewClearQualiLevel.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearQualiField.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbQualifiField.getSelectionModel().clearSelection();
                imgViewClearQualiField.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearLangTest.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbLanguageTest.getSelectionModel().clearSelection();
                imgViewClearLangTest.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearScore.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbLanguageScore.getSelectionModel().clearSelection();
                imgViewClearScore.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearProfesion.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbProfession.getSelectionModel().clearSelection();
                imgViewClearProfesion.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearDuration.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbDuration.getSelectionModel().clearSelection();
                imgViewClearDuration.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearIntake.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbIntakeSrch.getSelectionModel().clearSelection();
                imgViewClearIntake.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearInstitution.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbInstitution.getSelectionModel().clearSelection();
                imgViewClearInstitution.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearCountryPrgm.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbCountryPgmReq.getSelectionModel().clearSelection();
                imgViewClearCountryPrgm.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearLocation.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbLocation.getSelectionModel().clearSelection();
                imgViewClearCountryPrgm.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearEnqMethod.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbEnquiryMethod.getSelectionModel().clearSelection();
                imgViewClearCountryPrgm.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearAssignedBy.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbAssignBy.getSelectionModel().clearSelection();
                imgViewClearCountryPrgm.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearEnqrySorce.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbSource.getSelectionModel().clearSelection();
                imgViewClearCountryPrgm.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });

        imgViewClearBranch.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbBranch.getSelectionModel().clearSelection();
                searchEnquiries(ACTIVE_TAB);
            }
        });

        imgViewClearDistrict.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbDistrict.getSelectionModel().clearSelection();
                searchEnquiries(ACTIVE_TAB);

            }
        });

        imgViewClearPrgmLevel.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbPrgmLevel.getSelectionModel().clearSelection();
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearPrgmField.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbProgramFld.getSelectionModel().clearSelection();
                searchEnquiries(ACTIVE_TAB);

            }
        });

        imgViewClearEnqryStats.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbStatus.getSelectionModel().clearSelection();
                searchEnquiries(ACTIVE_TAB);

            }
        });

    }

    /**
     *
     */
    public void addClearOnControls() {

        txtName.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if ((txtName.textProperty().get().length() < 0) || (txtName.textProperty().get().equals(""))) {
                    imgViewClearName.setVisible(false);
                } else if (txtName.textProperty().get().length() >= -1) {
                    imgViewClearName.setVisible(true);

                }

            }
        });

        txtPhone.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if ((txtPhone.textProperty().get().length() < 0) || (txtPhone.textProperty().get().equals(""))) {
                    imgViewClearPhone.setVisible(false);
                } else if (txtPhone.textProperty().get().length() > -1) {
                    imgViewClearPhone.setVisible(true);

                }

            }
        });

        txtPassportNo.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if ((txtPassportNo.textProperty().get().length() < 0) || (txtPassportNo.textProperty().get().equals(""))) {
                    imgViewClearPassport.setVisible(false);
                } else if (txtPassportNo.textProperty().get().length() > -1) {
                    imgViewClearPassport.setVisible(true);

                }

            }
        });

        datePickDOB.getEditor().setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if ((datePickDOB.getEditor().textProperty().get().length() < 0) || (datePickDOB.getEditor().textProperty().get().equals(""))) {
                    imgViewClearDob.setVisible(false);
                } else if (datePickDOB.getEditor().textProperty().get().length() > -1) {
                    imgViewClearDob.setVisible(true);

                }

            }
        });
        datePickDOB.valueProperty().addListener(new ChangeListener<LocalDate>() {

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if ((datePickDOB.getEditor().textProperty().get().length() < 0) || (datePickDOB.getEditor().textProperty().get().equals(""))) {
                    imgViewClearDob.setVisible(false);
                } else if (datePickDOB.getEditor().textProperty().get().length() > -1) {
                    imgViewClearDob.setVisible(true);

                }

            }
        });


        /*     =============== Address ============== */
        cmbDistrict.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbDistrict.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearDistrict.setVisible(true);
                } else {
                    imgViewClearDistrict.setVisible(false);
                }
            }
        });
        cmbState.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbState.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearState.setVisible(true);
                } else {
                    imgViewClearState.setVisible(false);
                }
            }
        });
        cmbCountry.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbCountry.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearCountry.setVisible(true);
                } else {
                    imgViewClearCountry.setVisible(false);
                }
            }
        });
        cmbQualifiLevel.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbQualifiLevel.getSelectionModel().getSelectedIndex() > -1) {

                    imgViewClearQualiLevel.setVisible(true);

                } else {
                    imgViewClearQualiLevel.setVisible(false);

                }
            }
        });
        cmbQualifiField.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbQualifiField.getSelectionModel().getSelectedIndex() > -1) {

                    imgViewClearQualiField.setVisible(true);

                } else {
                    imgViewClearQualiField.setVisible(false);

                }
            }
        });
        cmbLanguageTest.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbLanguageTest.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearLangTest.setVisible(true);
                } else {
                    imgViewClearLangTest.setVisible(false);
                }
            }
        });
        cmbLanguageScore.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbLanguageScore.getSelectionModel().getSelectedIndex() > -1) {

                    imgViewClearScore.setVisible(true);

                } else {
                    imgViewClearScore.setVisible(false);

                }
            }
        });

        /* ================  Work Experience ===================== */
        cmbProfession.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbProfession.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearProfesion.setVisible(true);
                } else {
                    imgViewClearProfesion.setVisible(false);

                }
            }
        });
        cmbDuration.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbDuration.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearDuration.setVisible(true);
                } else {
                    imgViewClearDuration.setVisible(false);

                }
            }
        });
        cmbIntakeSrch.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbIntakeSrch.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearIntake.setVisible(true);
                } else {
                    imgViewClearIntake.setVisible(false);

                }
            }
        });

        cmbInstitution.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbInstitution.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearInstitution.setVisible(true);
                } else {
                    imgViewClearInstitution.setVisible(false);

                }
            }
        });
        cmbCountryPgmReq.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbCountryPgmReq.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearCountryPrgm.setVisible(true);
                } else {
                    imgViewClearCountryPrgm.setVisible(false);

                }
            }
        });
        cmbLocation.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbLocation.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearLocation.setVisible(true);
                } else {
                    imgViewClearLocation.setVisible(false);

                }
            }
        });
        //branch
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbBranch.getSelectionModel().getSelectedIndex() > -1) {
                    if (cmbBranch.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        imgViewClearBranch.setVisible(false);
                    } else {
                        imgViewClearBranch.setVisible(true);
                    }
                } else {
                    imgViewClearBranch.setVisible(false);

                }
            }
        });

        cmbDistrict.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbDistrict.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearDistrict.setVisible(true);
                } else {
                    imgViewClearDistrict.setVisible(false);

                }
            }
        });

        cmbEnquiryMethod.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbEnquiryMethod.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearEnqMethod.setVisible(true);
                } else {
                    imgViewClearEnqMethod.setVisible(false);
                }
            }
        });
        ///test
        cmbAssignBy.setCellFactory(new Callback<ListView<CounselorsListPOJO>, ListCell<CounselorsListPOJO>>() {

            @Override
            public ListCell<CounselorsListPOJO> call(ListView<CounselorsListPOJO> param) {
                return new ListCell<CounselorsListPOJO>() {
                    @Override
                    protected void updateItem(CounselorsListPOJO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getEnquiryAssignedBy());
                        }
                    }
                };
            }
        });

        cmbAssignBy.setItems(RetrieveStaticMasterDAO.getAllCounselors(null));

        /// end test
        cmbAssignBy.valueProperty().addListener(new ChangeListener<CounselorsListPOJO>() {

            @Override
            public void changed(ObservableValue<? extends CounselorsListPOJO> observable, CounselorsListPOJO oldValue, CounselorsListPOJO newValue) {

                if (cmbAssignBy.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearAssignedBy.setVisible(true);
                    searchQueryBEAN.setAssignedBy(cmbAssignBy.getSelectionModel().getSelectedItem().getUsername());

                } else {
                    imgViewClearAssignedBy.setVisible(false);

                }

            }
        });
        cmbPrgmLevel.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbPrgmLevel.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearPrgmLevel.setVisible(true);
                } else {
                    imgViewClearPrgmLevel.setVisible(false);

                }
            }
        });
        cmbProgramFld.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbProgramFld.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearPrgmField.setVisible(true);
                } else {
                    imgViewClearPrgmField.setVisible(false);

                }
            }
        });
        cmbSource.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbSource.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearEnqrySorce.setVisible(true);
                } else {
                    imgViewClearEnqrySorce.setVisible(false);

                }
            }
        });
        cmbStatus.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbStatus.getSelectionModel().getSelectedIndex() > -1) {
                    imgViewClearEnqryStats.setVisible(true);
                } else {
                    imgViewClearEnqryStats.setVisible(false);

                }
            }
        });
//        datePickFrom.valueProperty().addListener(new ChangeListener<Object>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
//                if (datePickFrom.getEditor().getText().equals("")) {
//                    imgViewClearDateFrom.setVisible(true);
//                } else {
//                    imgViewClearDateFrom.setVisible(false);
//                }
//            }
//        });
//        datePickTo.valueProperty().addListener(new ChangeListener<Object>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
//                if (datePickTo.getEditor().getText().equals("")) {
//                    imgViewClearDateTo.setVisible(true);
//                } else {
//                    imgViewClearDateTo.setVisible(false);
//                }
//            }
//        });

    }

    class OfficeStaffCell extends ListCell<OfficeStaffPOJO> {

        @Override
        protected void updateItem(OfficeStaffPOJO item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getFullname());
            }
        }
    }

    /**
     *
     */
    public void initComboBoxes() {

        cmbBranch.setItems(branch);
//        if (cmbBranch.getItems().contains(CUR_BRANCH)) {
//            cmbBranch.getSelectionModel().clearAndSelect(cmbBranch.getItems().indexOf(CUR_BRANCH));
//        }
        empStatusBranchCombo.setItems(branch);
        List<String> district = MasterCountryStateDistrictDAO.getAllDistricts("kerala");
        districs.clear();
        for (String s : district) {
            districs.add(s);
        }
        cmbDistrict.setItems(districs);
        List<String> countrylist = MasterCountryStateDistrictDAO.getAllCountries();
        country.clear();
        for (String s : countrylist) {
            country.add(s);
        }
        cmbCountry.setItems(country);
        cmbCountry.getSelectionModel().selectFirst();
        List<String> statelist = MasterCountryStateDistrictDAO.getAllStates(cmbCountry.getSelectionModel().getSelectedItem());
        state.clear();
        for (String s : statelist) {
            state.add(s);
        }
        System.out.println("Check State Value :: " + state.toString());
        cmbState.setItems(state);
//        if (cmbState.getItems().contains("Kerala")) {
//            cmbState.getSelectionModel().clearAndSelect(cmbState.getItems().indexOf("Kerala"));
//        } else {
//            cmbState.getSelectionModel().selectFirst();
//        }

        cmbState.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    List<String> district2 = MasterCountryStateDistrictDAO.getAllDistricts(cmbState.getSelectionModel().getSelectedItem());
                    districs.clear();
                    for (String s : district2) {
                        districs.add(s);
                    }
                    cmbDistrict.setItems(districs);
                }
            }
        });

        cmbStatus.setItems(RetrieveStaticMasterDAO.retrieveEnquiryStatus());
        List<String> sources = RetrieveStaticMasterDAO.getAllSources();
        source.clear();
        for (String s : sources) {
            source.add(s);
        }
        cmbSource.setItems(source);

        //functional area and level
        List<String> programLevel2 = RetrieveStaticMasterDAO.getProgramLevels();
        programLevelList.clear();
        for (String s : programLevel2) {
            programLevelList.add(s);
        }
        cmbPrgmLevel.setItems(programLevelList);

        //    cmbPrgmLevel.getSelectionModel().selectFirst();
        cmbPrgmLevel.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    List<String> programFields = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                    programField.clear();
                    for (String s : programFields) {
                        programField.add(s);
                    }
                    cmbProgramFld.setItems(programField);
                    cmbProgramFld.getSelectionModel().clearSelection();

                }
            }
        });
//        List<String> sourceAssign = EnquiryDetailsDAO.getAllCounselors(CUR_BRANCH);
//        for (String s : sourceAssign) {
//            sassign.add(s);
//        }
        sassign.add(CUR_USERNAME);
//        cmbAssign.setItems(sassign);
        //Changing branch event
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList assign = FXCollections.observableArrayList();
                if (newValue != null) {
                    ObservableList<CounselorsListPOJO> assignedCounselors = FXCollections.observableArrayList();
                    if (newValue.equalsIgnoreCase("All")) {
                        assignedCounselors = RetrieveStaticMasterDAO.getAllUsersByBranch(null);
                    } else {
                        assignedCounselors = RetrieveStaticMasterDAO.getAllUsersByBranch(newValue);
                    }
                    cmbAssignBy.setItems(assignedCounselors);
                }

            }
        });
        // cmbEnquiryFor.setItems(enquiryfor);

        empStatusBranchCombo.setItems(branch);
        empStatusBranchCombo.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedBranch = newValue;
                empStatusTable.getItems().clear();
                addEmpStatusLoader(newValue);
            }
        });
//        cmbEnquiryFor.setItems(enquiryfor);
        /* ======================== Load Enquiry Method with default ==================== */
        mapEnquiryMethod = RetrieveStaticMasterDAO.retrieveEnquiryMethod();
        Set<String> keys = mapEnquiryMethod.keySet();
        for (String key : keys) {
            enquiryMethods.clear();
            enquiryMethods = mapEnquiryMethod.get(key);
        }
        cmbEnquiryMethod.setItems(enquiryMethods);
        /* ============================ Qualification Combo Data ============================== */
        List<String> programLevel = RetrieveStaticMasterDAO.getProgramLevels();
        programLevelList.clear();
        for (String s : programLevel) {
            programLevelList.add(s);
        }
        cmbQualifiLevel.setItems(programLevelList);
        cmbQualifiLevel.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {
                    List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                    ObservableList programFieldlList2 = FXCollections.observableArrayList();
                    for (String s : programField) {
                        programFieldlList2.add(s);
                    }
                    cmbQualifiField.setItems(programFieldlList2);
                    cmbQualifiField.getSelectionModel().clearSelection();
                }

            }
        });
        /* ============================ End Qualification Combo Data ============================== */
 /* ============================ WorkExperience Combo Data ============================== */
        profession = RetrieveStaticMasterDAO.getProfession();
        duration = RetrieveStaticMasterDAO.getDuration();
        cmbProfession.setItems(profession);
        cmbDuration.setItems(duration);

        /* ============================ End WorkExperience Combo Data ============================== */
 /* ============================ Program Required Combo Data ============================== */
        cmbIntakeSrch.setItems(intakeObsList);
        ObservableList universities = RetrieveStaticMasterDAO.getUniversities();
        cmbInstitution.setItems(universities);
        List<String> countryList = MasterCountryStateDistrictDAO.getAllCountries();
        country.clear();
        for (String s : countryList) {
            country.add(s);
        }
        cmbCountryPgmReq.setItems(countryDAO.retrieveMasterAllCountries());
        cmbCountryPgmReq.valueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {

                if (newValue != null) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbLocation.setItems(location);
                    cmbLocation.getSelectionModel().selectFirst();
                }

            }
        });
        /* ============================ End Program Required Combo Data ============================== */
 /* ============================  Language Test Score Combo Data ============================== */
        cmbLanguageTest.setItems(RetrieveStaticMasterDAO.getOtherTest());
        List<String> testScores = RetrieveStaticMasterDAO.getTestScore();
        ObservableList testScore = FXCollections.observableArrayList();
        for (String s : testScores) {
            testScore.add(s);
        }
        cmbLanguageScore.setItems(testScore);
        /* ============================  End Language Test Score Combo Data ============================== */

    }

    /* ===================================================================== */
 /* ========================= Priority Cell ============================= */
 /* ===================================================================== */
    /**
     *
     */
    public class PriorityCell extends TableCell {

        final VBox priorityvBox1 = new VBox();
        final HBox hboxPriority = new HBox();
        ImageView starimageView = new ImageView();
        ImageView importantimageView = new ImageView();
        Image yellowStarImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("stary.png"));
        Image whiteStarImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("starw.png"));
        Image yellowImrtantImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("impy.png"));
        Image whiteImportantImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("impw.png"));

        //   Tooltip tooltip = new Tooltip();
        {
            hboxPriority.getChildren().addAll(starimageView);
            hboxPriority.setAlignment(Pos.CENTER);
            setGraphic(hboxPriority);
        }

        @Override
        protected void updateItem(Object object, boolean empty) {
            super.updateItem(object, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                if (object != null) {
                    CounselorDetailsBEAN cpojo = (CounselorDetailsBEAN) object;
                    if (cpojo.getRating() == null || cpojo.getRating().equals("0")) {
                        starimageView.setImage(whiteStarImage);
                        starimageView.setFitWidth(16);
                        starimageView.setFitHeight(16);
                    } else {
                        starimageView.setImage(yellowStarImage);
                        starimageView.setFitWidth(16);
                        starimageView.setFitHeight(16);
                    }
                    starimageView.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
                    starimageView.setOnMouseClicked(new EventHandler<Event>() {

                        @Override
                        public void handle(Event event) {
                            if (cpojo.getRating() == null && (cpojo.getRating().equals(""))) {
                            } else {
                                List<CounselorDetailsBEAN> counselorDetailsUpdateRating = new ArrayList<>();
                                counselorDetailsUpdateRating.add(cpojo);
                                int i = Integer.parseInt(cpojo.getRating());
                                i ^= 1;
                                cpojo.setRating("" + i);
                                inlineEditingDAO.updateStarStatus(counselorDetailsUpdateRating);
                                if (cpojo.getRating().equals("0")) {
                                    starimageView.setImage(whiteStarImage);
                                    starimageView.setFitWidth(16);
                                    starimageView.setFitHeight(16);
                                } else {
                                    starimageView.setImage(yellowStarImage);
                                    starimageView.setFitWidth(16);
                                    starimageView.setFitHeight(16);
                                }
                                /* ====================== Reload Counts ====================== */
                                if (serviceHyperLinkCounts != null) {
                                    if (!serviceHyperLinkCounts.isRunning()) {
                                        serviceHyperLinkCounts.reset();
                                        serviceHyperLinkCounts.restart();
                                    }
                                } else {
                                    serviceHyperLinkCounts.start();
                                }
                            }
                        }
                    });
//                    if (cpojo.getImportant().equals("0")) {
//                        importantimageView.setImage(whiteImportantImage);
//                        importantimageView.setFitWidth(20);
//                        importantimageView.setFitHeight(16);
//                    } else {
//                        importantimageView.setImage(yellowImrtantImage);
//                        importantimageView.setFitWidth(20);
//                        importantimageView.setFitHeight(16);
//                    }
                }
            } else {
                setText(null);
                setGraphic(null);
            }
        }
    }

    /**
     *
     */
    public class FlagCell extends TableCell {

        final HBox hboxFlag = new HBox();
        ImageView imageViewFlag = new ImageView();
        Image yellowImrtantImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("impy.png"));
        Image whiteImportantImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("impw.png"));

        //   Tooltip tooltip = new Tooltip();
        {
            hboxFlag.getChildren().addAll(imageViewFlag);
            hboxFlag.setAlignment(Pos.CENTER);
            setGraphic(hboxFlag);
        }

        @Override
        protected void updateItem(Object object, boolean empty) {
            super.updateItem(object, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                if (object != null) {
                    CounselorDetailsBEAN cpojo = (CounselorDetailsBEAN) object;
                    if (cpojo.getImportant() == null || cpojo.getImportant().equals("0")) {
                        imageViewFlag.setImage(whiteImportantImage);
                        imageViewFlag.setFitWidth(24);
                        imageViewFlag.setFitHeight(16);
                    } else {
                        imageViewFlag.setImage(yellowImrtantImage);
                        imageViewFlag.setFitWidth(24);
                        imageViewFlag.setFitHeight(16);
                    }
                    imageViewFlag.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
                    imageViewFlag.setOnMouseClicked(new EventHandler<Event>() {

                        @Override
                        public void handle(Event event) {
                            Map<String, String> mapCommonPoolConfig = CommonPoolService.retrieveCommonPoolConfig(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                            Map<String, String> mapFlagEnquiryStatus = CommonPoolService.checkFlagEnquiryStatus(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                            long flagLimitConfig = Long.parseLong(mapCommonPoolConfig.get("flag_limit"));
                            long flagLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("flag_limit"));

                            System.out.println("Flag Config :: " + flagLimitConfig);
                            System.out.println("Flag Status :: " + flagLimitStatus);
                            System.out.println("Flag important :: " + cpojo.getImportant());
                            if (cpojo.getImportant() == null || (cpojo.getImportant().equals(""))) {
                                cpojo.setImportant("0");
                            }
                            if (flagLimitConfig == 0 || flagLimitStatus < flagLimitConfig || cpojo.getImportant().equalsIgnoreCase("1")) {

                                List<CounselorDetailsBEAN> counselorDetailsUpdateFlag = new ArrayList<>();
                                counselorDetailsUpdateFlag.add(cpojo);
                                int i = Integer.parseInt(cpojo.getImportant());
                                i ^= 1;
                                cpojo.setImportant("" + i);
                                inlineEditingDAO.updateImportantFlag(counselorDetailsUpdateFlag);
                                if (cpojo.getImportant().equalsIgnoreCase("0")) {
                                    imageViewFlag.setImage(whiteImportantImage);
                                    imageViewFlag.setFitWidth(24);
                                    imageViewFlag.setFitHeight(16);
                                } else {
                                    imageViewFlag.setImage(yellowImrtantImage);
                                    imageViewFlag.setFitWidth(24);
                                    imageViewFlag.setFitHeight(16);
                                }
                            } else {
                                showPopupMessages.showWarning("Your flag limit reached !", "Your flag limit reached !", imageViewFlag);
                            }

                            /* ====================== Reload Counts ====================== */
                            if (serviceHyperLinkCounts != null) {
                                if (!serviceHyperLinkCounts.isRunning()) {
                                    serviceHyperLinkCounts.reset();
                                    serviceHyperLinkCounts.restart();
                                }
                            } else {
                                serviceHyperLinkCounts.start();
                            }
                        }
                    });
//                    if (cpojo.getImportant().equals("0")) {
//                        importantimageView.setImage(whiteImportantImage);
//                        importantimageView.setFitWidth(20);
//                        importantimageView.setFitHeight(16);
//                    } else {
//                        importantimageView.setImage(yellowImrtantImage);
//                        importantimageView.setFitWidth(20);
//                        importantimageView.setFitHeight(16);
//                    }
                }
            } else {
                setText(null);
                setGraphic(null);
            }
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
                }

                // stage.setTitle("Home page";
                synchronized (root) {
//            stage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    // scene.getStylesheets().add(getClass().getResource("style.css".toExternalForm());
                    mainStage.setScene(scene);

                    mainStage
                            .getIcons().add(new Image(INALoginForm.class
                                    .getResourceAsStream("images/ina_window_logo.png")));
                    mainStage.setTitle(
                            "International Academy");
                    mainStage.setScene(scene);
                }
                mainStage.initStyle(StageStyle.UNDECORATED);
                mainStage.show();
//                notifyTimer.setCycleCount(0);
                notifyTimer.stop();
            }
        });

    }

    /**
     *
     * @param activeInbox
     */
    public void searchEnquiries(String activeInbox) {
        String dynamic_query = "";
//        if (cmbEnquiryFor.getSelectionModel().getSelectedIndex() >= 0) {
//            String _s = cmbEnquiryFor.getSelectionModel().getSelectedItem();
//            dynamic_query = "AND ed." + _s.toLowerCase(Locale.ENGLISH) + "<>'' order by " + _s.toLowerCase(Locale.ENGLISH) + "  asc";
//        }
        if (cmbLanguageTest.getSelectionModel().getSelectedIndex() >= 0) {
            String _s = cmbLanguageTest.getSelectionModel().getSelectedItem();
            dynamic_query = " AND asl.test like '" + _s.toLowerCase(Locale.ENGLISH) + "%'";
        }
        if (cmbLanguageScore.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + "AND asl.overall LIKE '%" + cmbLanguageScore.getSelectionModel().getSelectedItem() + "%'";

        }
        if (cmbProfession.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND aae.profession LIKE '%" + cmbProfession.getSelectionModel().getSelectedItem() + "%'";

        }
        if (cmbDuration.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND  aae.duration LIKE '%" + cmbDuration.getSelectionModel().getSelectedItem() + "%'";
        }
        /* ========================= Qualification Search ============================ */
        if (cmbQualifiLevel.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND  asq.teriary_quali_level LIKE '%" + cmbQualifiLevel.getSelectionModel().getSelectedItem() + "%'";
        }
        if (cmbQualifiField.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND asq.teriary_quali_field LIKE '%" + cmbQualifiField.getSelectionModel().getSelectedItem() + "%'";
        }
        /* ========================= Program Required Search ============================ */
        if (cmbPrgmLevel.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND eas.program_level LIKE '%" + cmbPrgmLevel.getSelectionModel().getSelectedItem() + "%'";
        }
        if (cmbProgramFld.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND eas.program_field LIKE '%" + cmbProgramFld.getSelectionModel().getSelectedItem() + "%'";
        }
        if (cmbIntakeSrch.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND eas.intake LIKE '%" + cmbIntakeSrch.getSelectionModel().getSelectedItem() + "%'";
        }
        if (cmbInstitution.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND eas.institution LIKE '%" + cmbInstitution.getSelectionModel().getSelectedItem() + "%'";
        }
        if (cmbCountryPgmReq.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND eas.country LIKE '%" + cmbCountryPgmReq.getSelectionModel().getSelectedItem() + "%'";
        }
        if (cmbLocation.getSelectionModel().getSelectedIndex() >= 0) {
            dynamic_query = dynamic_query + " AND eas.location LIKE '%" + cmbLocation.getSelectionModel().getSelectedItem() + "%'";
        }
        if (txtPassportNo.getText() == null || txtPassportNo.getText().equals("")) {
        } else {
            dynamic_query = dynamic_query + " AND ep.passport like '%" + txtPassportNo.getText() + "' ";
        }
        if (datePickDOB.getValue() != null) {
            dynamic_query = dynamic_query + " AND ep.dob like '%" + datePickDOB.getValue() + "'";
        }
        /* ========================= End Program Required Search ============================ */
        if (activeInbox.equalsIgnoreCase("primary")) {
            System.out.println("Test Search Values :: " + searchQueryBEAN.toString());
            System.out.println("Test Dynamic Query :: " + dynamic_query);
            /* ======================== Search Common Pool  ==================== */
            if (IS_COMMON_POOL) {
                Map<String, List<CounselorDetailsBEAN>> primarySearchMap = InboxSearchService.getPrimarySearch(searchQueryBEAN, CUR_BRANCH, CUR_USERNAME, "COMMON_POOL_COUNSELOR", dynamic_query);
                loadInboxTabsEnquiries(primarySearchMap);
            } else {
                Map<String, List<CounselorDetailsBEAN>> primarySearchMap = InboxSearchService.getPrimarySearch(searchQueryBEAN, CUR_BRANCH, CUR_USERNAME, CUR_ROLE, dynamic_query);
                loadInboxTabsEnquiries(primarySearchMap);
            }
        }

    }

    @FXML
    private void handleBtnSearchAction(ActionEvent event) {

//        switch (tabInbox.getSelectionModel().getSelectedIndex()) {
//            case 0:
//                if (tabPanePrimaryInbox.getSelectionModel().getSelectedIndex() > 0) {
//                    //   lblInboxName.setText("Inbox - " + tabPanePrimaryInbox.getSelectionModel().getSelectedItem().getText());
//                } else {
//                    //   lblInboxName.setText("Inbox  ");
//                }
//                break;
//        }
        Label lblInfo = new Label("Showing Search Results...");
        //   checkBeanIsEmpty(searchQueryBEAN, lblInfo);
        lblInfo.setStyle("-fx-text-fill:red");
        //  lblInboxName.setGraphic(lblInfo);
        //   lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
        //  lblShowingReports.setText("");
        titiledPaneSearch.setExpanded(false);
        searchEnquiries(ACTIVE_TAB);
    }

}

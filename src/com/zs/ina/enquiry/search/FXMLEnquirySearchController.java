/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search;

import com.zs.ina.admin.inbox.forward.FXMLForwardController;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.dao.CounselorDetailsDAO;
import com.zs.ina.counselor.dao.model.SearchQueryBEAN;
import com.zs.ina.admin.master.retrieve.MasterCountryStateDistrictDAO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.inbox.retrieve.InboxCountsBEAN;
import com.zs.ina.common.inbox.retrieve.InboxRetrievalService;
import com.zs.ina.common.inbox.search.InboxSearchService;
import com.zs.ina.enquiry.followup.FXMLFollowUpFulllviewController;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.login.dao.LoginFormDAO;
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
import java.util.Set;
import java.util.stream.Collectors;
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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import jfxtras.scene.control.LocalDateTimePicker;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 * FXML Controller class
 *
 * @author zscomp1
 */
public class FXMLEnquirySearchController implements Initializable {

    static Logger logger = Logger.getLogger(FXMLEnquirySearchController.class);

    @FXML
    private ComboBox<String> cmbSource;
    @FXML
    private Button btnSearchSubmit;

    PopOver pop = new PopOver();
    PopOver popEnquiryData = new PopOver();

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
    ObservableList assesment_status = FXCollections.observableArrayList("Register Now", "Call Not Picking",
            "Call Attended & Call Later", "Not Completed Call Later", "Will Contact Future",
            "Not Interested", "Fake Caller", "Number Not Exist", "Confirm Soon", "Cancelled", "Will Walk in");
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

    /**
     *
     */
    final public String BRAND_NAME = "INA";

    /**
     *
     */
    final public double CLOCK_RADIUS = 80;
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

    private int SIZE_MSGLIST = 0;
    Timeline notifyTimer;
    private int TIME_CALC;
    private String ACTIVE_TAB;

    Map<String, Integer> counterMap = new HashMap<>();
    LocalDateTimePicker dateTimePicker;

    ObservableList branch = FXCollections.observableArrayList();
    String selectedBranch = null;
    @FXML
    private Label lblLogout;
    @FXML
    private Label lblUserdata;

    @FXML
    private VBox vboxHyperlinks;
    @FXML
    private Hyperlink hyperAppointmnet;
    private TabPane tabInbox;
    List<CounselorDetailsBEAN> registeredInboxList = new ArrayList<>();
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
    int GLOBAL_STAR_STATUS = 0;
    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();
    /* ======================== Store Checked Enquiries  ==================== */
    static List<CounselorDetailsBEAN> listCheckedEnquiries = new ArrayList<>();
    @FXML
    private StackPane stackPaneMain;
    /* ====================== Show Progress Indicator ====================== */
    Region regionOverlay = new Region();
    ProgressIndicator progressIndicator = new ProgressIndicator();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    CountryDAO countryDAO = new CountryIMPL();
    @FXML
    private Hyperlink hyperFollowUpToday;
    private int folloupsize = 0;
    Map<String, ObservableList<String>> mapEnquiryMethod = new HashMap<>();

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
        /// =======>>> testingggg <<<=======
        initObservLists();
        selectedBranch = CUR_BRANCH;

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

        /* =============> Load primary inbox counts <============ */
        getAllHyperlinkCounts();
        initTodayFollowUpCount();
        /* =============> Add tab events <============ */
 /* =============> Add inbox click events <============ */
        // addInboxEvents();
        /* =============> Add left accordion data  <============ */

        addLogout();
        /* ============================ Change Date Formats  ================================    */
        changeDateFormats();
        /* ============================ Reset Label Event  ================================    */
        lblResetAll.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                resetAll();
            }
        });
        /* ============================ Add Global Timer  ================================    */
 /* ============================ Hide Search Accordion  ================================    */
        titiledPaneSearch.setExpanded(false);
        /* ============================ Change Date format of DOB  ================================    */
        ChangeDateFormat.datePickerDateFormatter(datePickDOB);
        /* ======================== Fires MousEvent Of lblToday ==================== */
//        Event.fireEvent(lblToday, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
//                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
//                true, true, true, true, true, true, null));

        /* ======================== bind Enquiry Report Title ==================== */
 /* ======================== Load All The Data ==================== */
        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = CounselorDetailsDAO.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME);
        loadInboxTabsEnquiries(primaryInboxMap);
        stackPaneInbox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null) {
                    //   lblEnquiryReportTitle.setStyle("-fx-padding:"+newValue.doubleValue()/10);
                }
            }
        });
        /* ====================== Show Follow Up ====================== */
        hyperFollowUpToday.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (folloupsize > 0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/followup/FXMLFollowUpFulllview.fxml"));
                    try {
                        Parent root = (Parent) loader.load();
                        System.out.println("Check Folllow Click!");
                        FXMLFollowUpFulllviewController controller = (FXMLFollowUpFulllviewController) loader.getController();
                        //   controller.initData(followuplist);
                        Scene secondScene = new Scene(root);
                        Stage secondStage = new Stage();
                        secondStage.setTitle("Set Your Reminder");
                        secondStage.setScene(secondScene);
                        secondStage.initStyle(StageStyle.UNDECORATED);
                        secondStage.initModality(Modality.WINDOW_MODAL);
                        secondStage.initOwner(lblLogout.getScene().getWindow());
                        secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                            @Override
                            public void handle(WindowEvent event) {
                                /* ========================  create executor that uses daemon threads: ==================== */
//                            executor = Executors.newCachedThreadPool(runnable -> {
//                                Thread t = new Thread(runnable);
//                                t.setDaemon(true);
//                                return t;
//                            });
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

        tableName.getItems().clear();
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
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(tableName.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
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
        Image imgGlobalYellowStar = new Image(FXMLEnquirySearchController.class.getResourceAsStream("stary.png"));
        Image imgGlobalWhiteStar = new Image(FXMLEnquirySearchController.class.getResourceAsStream("starw.png"));
        imageViewGlobal.setImage(imgGlobalWhiteStar);
        imageViewGlobal.setFitWidth(16);
        imageViewGlobal.setFitHeight(16);

        Button btnGlobalStar = new Button();

        btnGlobalStar.setGraphic(imageViewGlobal);
        btnGlobalStar.setStyle("-fx-background-color:transperant");
        btnGlobalStar.setPrefHeight(16);
        btnGlobalStar.setMaxHeight(16);
        clmTabPriority.setGraphic(btnGlobalStar);
        btnGlobalStar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will set priority for all enquiries. Do you want to continue? ", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
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
        });

        /* ======================== End Global Priority ==================== */
        Text text = new Text("No enquiries to show !");
        text.setStyle("-fx-fill:red");
        Pane hBox = new Pane();
        hBox.getChildren().addAll(imageViewGlobal, text);
        ImageView imageViewPlaceHolder = new ImageView(new Image(FXMLEnquirySearchController.class.getResourceAsStream("images/no_enq_show.png")));
        tableName.setPlaceholder(imageViewPlaceHolder);
        tableName.setEditable(true);
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabSno = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Sno");

//        clmSno.setCellValueFactory(
//                new PropertyValueFactory<>("count"));
        clmTabSno.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                return new NumberedCell();
            }

        });
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
        clmTabPhone.setCellValueFactory(new PropertyValueFactory<>("contactNumber1"));
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
            }
        });

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

        clmTabAddress.setCellValueFactory(new PropertyValueFactory<>("district"));
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
            }
        });

        TableColumn<CounselorDetailsBEAN, String> clmTabApplicationStatus = new TableColumn<CounselorDetailsBEAN, String>("Status");

        clmTabApplicationStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param) {

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
        });
        
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
                getAllHyperlinkCounts();
            }
        });
        TableColumn clmTabRemarks = new TableColumn("Remarks");
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
        clmTabRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabEnquiryTo = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Assined To");

        clmTabEnquiryTo.setCellValueFactory(new PropertyValueFactory<>("CounselorName"));

        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabEnquiryBy = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Assined By");

        clmTabEnquiryBy.setCellValueFactory(new PropertyValueFactory<>("enquiryAssignedBy"));

        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabDate = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Date");

        clmTabDate.setCellValueFactory(new PropertyValueFactory<>("edate"));

        TableColumn<CounselorDetailsBEAN, String> clmTabQualification = new TableColumn<CounselorDetailsBEAN, String>("Qualification");

        clmTabQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
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
            }
        });

        TableColumn<CounselorDetailsBEAN, String> clmTabLanguageTest = new TableColumn<CounselorDetailsBEAN, String>("Language Test");

        clmTabLanguageTest.setCellValueFactory(new PropertyValueFactory<>("languageTest"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, String> clmTabAdmissionTest = new TableColumn<CounselorDetailsBEAN, String>("Admission Test");

        clmTabAdmissionTest.setCellValueFactory(new PropertyValueFactory<>("admissionTest"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, String> clmTabExperience = new TableColumn<CounselorDetailsBEAN, String>("Experience");

        clmTabExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
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
                Context.getInstance().currentProfile().setCurrentTab("study");

            }
        });
        clmTabExperience.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CounselorDetailsBEAN, String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<CounselorDetailsBEAN, String> event) {
                event.getTableView().getItems().set(event.getTablePosition().getRow(), InboxRetrievalService.retrieveEnquiryById(CUR_BRANCH, CUR_USERNAME, CUR_ROLE, event.getTableView().getItems().get(event.getTablePosition().getRow()).getEnquiryID()));
                refreshPriorityCell(clmTabPriority);
            }
        });

        /* =============================================  Only For Study =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabStudyRequired = new TableColumn<>("Study Required");
        clmTabStudyRequired.setMaxWidth(600);
        clmTabStudyRequired.setPrefWidth(138);
        clmTabStudyRequired.setMinWidth(10);
        clmTabStudyRequired.setCellValueFactory(new PropertyValueFactory<>("studyRequired"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, String> clmTabStudyCountryLocation = new TableColumn<CounselorDetailsBEAN, String>("Country & Location");
        clmTabStudyCountryLocation.setCellValueFactory(new PropertyValueFactory<>("studyCountryLocation"));
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
            }
        });
        /* =============================================  Only For Work =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabWorkRequired = new TableColumn<>("Work Required");
        clmTabWorkRequired.setMaxWidth(600);
        clmTabWorkRequired.setPrefWidth(138);
        clmTabWorkRequired.setMinWidth(10);
        clmTabWorkRequired.setCellValueFactory(new PropertyValueFactory<>("workRequired"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, String> clmTabWorkCountryLocation = new TableColumn<CounselorDetailsBEAN, String>("Country & Location");
        clmTabWorkCountryLocation.setCellValueFactory(new PropertyValueFactory<>("workCountryLocation"));
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
            }
        });

        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabSalaryExpected = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Salary Expected");
        clmTabSalaryExpected.setCellValueFactory(new PropertyValueFactory<>("salaryExpected"));

        /* =============================================  Only For Migration =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabMigrationRequired = new TableColumn<>("Migration Required");
        clmTabMigrationRequired.setMaxWidth(600);
        clmTabMigrationRequired.setPrefWidth(138);
        clmTabMigrationRequired.setMinWidth(10);
        clmTabMigrationRequired.setCellValueFactory(new PropertyValueFactory<>("migrationRequired"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, String> clmTabMigrationCountryLocation = new TableColumn<CounselorDetailsBEAN, String>("Country & Location");
        clmTabMigrationCountryLocation.setCellValueFactory(new PropertyValueFactory<>("migrateCountryLocation"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabApplicationType = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Application Type");
        clmTabApplicationType.setCellValueFactory(new PropertyValueFactory<>("applicationType"));


        /* =============================================  Only For Training =========================================== */
        TableColumn<CounselorDetailsBEAN, String> clmTabTrainingRequired = new TableColumn<>("Training Required");
        clmTabTrainingRequired.setMaxWidth(600);
        clmTabTrainingRequired.setPrefWidth(138);
        clmTabTrainingRequired.setMinWidth(10);
        clmTabTrainingRequired.setCellValueFactory(new PropertyValueFactory<>("trainingRequired"));
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
            }
        });
        TableColumn<CounselorDetailsBEAN, String> clmTabJoiningDate = new TableColumn<>("Joining Date");
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
            }
        });

        TableColumn<CounselorDetailsBEAN, String> clmTabBatch = new TableColumn<>("Batch & Timing");
        clmTabBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
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
            }
        });
        clmTabJoiningDate.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));
        TableColumn<CounselorDetailsBEAN, String> clmTabTrainingCountryLocation = new TableColumn<>("Country & Location");
        clmTabTrainingCountryLocation.setCellValueFactory(new PropertyValueFactory<>("trainingCountryLocation"));
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
            }
        });
        /* =============================================  End =========================================== */
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabForward = new TableColumn<>("Forward For");
        clmTabForward.setCellValueFactory(new PropertyValueFactory<>("forwardFor"));
        // Add editing icon 
        TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmTabEdit = new TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Edit");

        clmTabEdit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN>, ObservableValue<CounselorDetailsBEAN>>() {

            @Override
            public ObservableValue<CounselorDetailsBEAN> call(TableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN> edit) {
                return new ReadOnlyObjectWrapper(edit.getValue());

            }
        });
        clmTabEdit.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                return new TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>() {
                    ImageView editIconView = new ImageView();
                    Image editIcon = new Image(FXMLEnquirySearchController.class
                            .getResourceAsStream("edit.png"));
                    final HBox hboxEditIcon = new HBox();

                    //   Tooltip tooltip = new Tooltip();
                    {
                        hboxEditIcon.getChildren().add(editIconView);
                        hboxEditIcon.setAlignment(Pos.CENTER);
                        hboxEditIcon.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_hand_red.png"))));
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
                                                Scene secondScene = new Scene(secondaryLayout);
                                                Stage secondStage = new Stage();
                                                secondStage.setTitle("Assessment form of " + cpojo.getContactName());
                                                secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                                secondStage.setScene(secondScene);
                                                Screen screen = Screen.getPrimary();
                                                Rectangle2D bounds = screen.getVisualBounds();
                                                secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                                // close event
                                                secondStage.setOnHidden(new EventHandler<WindowEvent>() {

                                                    @Override
                                                    public void handle(WindowEvent event) {
                                                        DBPool.getInstance().CloseConnections();
                                                        resetAll();
                                                    }
                                                });
                                                //        secondStage.initStyle(StageStyle.UNDECORATED);
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
        });

        // load all tables
        tableName.getColumns().clear();
        tableName.setCursor(Cursor.DEFAULT);
        tableName.setTableMenuButtonVisible(true);
        /* ====================== for enquiry search only old backups ====================== */

        switch (tabName) {
            case "study":
                tableName.getColumns().addAll(clmTabPriority, clmTabCname, clmTabPhone, clmTabStudyRequired, clmTabStudyCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryTo, clmTabForward, clmTabAddress, clmTabDate);
                break;
            case "work":
                tableName.getColumns().addAll(clmTabPriority, clmTabCname, clmTabPhone, clmTabWorkRequired, clmTabWorkCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryTo, clmTabForward, clmTabAddress, clmTabDate);

                break;
            case "migration":
                tableName.getColumns().addAll(clmTabPriority, clmTabCname, clmTabPhone, clmTabMigrationRequired, clmTabMigrationCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryTo, clmTabForward, clmTabAddress, clmTabDate);

                break;
            case "training":
                tableName.getColumns().addAll(clmTabPriority, clmTabCname, clmTabPhone, clmTabTrainingRequired, clmTabJoiningDate, clmTabBatch, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryTo, clmTabForward, clmTabAddress, clmTabDate);
                break;
        }
        /* ======================common for admin,couselor end backups ====================== */
//        switch (tabName) {
//            case "study":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabStudyRequired, clmTabStudyCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//                break;
//            case "work":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabExperience, clmTabWorkRequired, clmTabSalaryExpected, clmTabWorkCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//
//                break;
//            case "migration":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabMigrationRequired, clmTabApplicationType, clmTabMigrationCountryLocation, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//
//                break;
//            case "training":
//                tableName.getColumns().addAll(clmChkMark, clmTabPriority, clmTabCname, clmTabPhone, clmTabQualification, clmTabLanguageTest, clmTabAdmissionTest, clmTabExperience, clmTabTrainingRequired, clmTabJoiningDate, clmTabBatch, clmTabApplicationStatus, clmTabRemarks, clmTabEnquiryBy, clmTabForward, clmTabAddress, clmTabDate);
//                break;
//        }

        //  tableName.getStyleClass().addAll("tblpinbox", "mylistview");

        /* ======================== test row factory ==================== */
        //     tableName.getItems().setAll(primaryInboxTabList);
        tableName.setRowFactory(new Callback<TableView<CounselorDetailsBEAN>, TableRow<CounselorDetailsBEAN>>() {
            @Override
            public TableRow<CounselorDetailsBEAN> call(TableView<CounselorDetailsBEAN> param) {
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem itemOpenEnquiry = new MenuItem("Open Assesment Details");
                final MenuItem itemForwardEnquiry = new MenuItem("Forward Enquiries");
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
                            logger.error(ex.toString());
                        }

                    }
                });
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
                                //  EffectsClass.fadeOut(row, 500.0);
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
//                            popForward.setOnHiding(new EventHandler<Event>() {
//
//                                @Override
//                                public void handle(Event event) {
//                                    Event.fireEvent(lblMonth, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
//                                            0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
//                                            true, true, true, true, true, true, null));
//                                }
//                            });

                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        if (popForward.isShowing()) {
                            popForward.hide();
                            popForward.show(row, row.localToScreen(0, 0).getX() - 6, row.localToScreen(0, 0).getY() + 30, Duration.millis(100));

                        } else {
                            popForward.show(row, row.localToScreen(0, 0).getX() - 6, row.localToScreen(0, 0).getY() + 30, Duration.millis(500));

                        }

                    }
                });
                contextMenu.getItems().addAll(itemOpenEnquiry);
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu));
                return row;
            }
        });
        tableName.applyCss();
        int c = 0;
        for (TableColumn column : tableName.getColumns()) {
            c++;
            if (c != 1) {
                column.setPrefWidth(119);
                column.setMinWidth(119);
                if (c == 2) {
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
        clmTabPhone.setPrefWidth(85);
        clmTabPhone.setMinWidth(85);
        clmTabPhone.setMaxWidth(200);
        /* ==== edit column width ==== */
        clmTabEdit.setPrefWidth(50);
        clmTabEdit.setMinWidth(50);
        clmTabEdit.setMaxWidth(50);
        /* ====================== Load Data To Tableview And Show Progress ====================== */
        Task<ObservableList<CounselorDetailsBEAN>> taskProgress = new GetEnquiryLoadTask(primaryInboxTabList);
        progressIndicator.progressProperty().bind(taskProgress.progressProperty());
        regionOverlay.visibleProperty().bind(taskProgress.runningProperty());
        progressIndicator.visibleProperty().bind(taskProgress.runningProperty());
        tableName.itemsProperty().bind(taskProgress.valueProperty());
        new Thread(taskProgress).start();

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

    private void initTodayFollowUpCount() {
        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCountsFollowup(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            System.out.println("test follow Up :: " + inboxCountsBEAN.toString());
            if (inboxCountsBEAN.getStatusId() != null) {
                if (inboxCountsBEAN.getStatusId().equals("-1")) {
                    hyperFollowUpToday.setText("Follow Up (Today " + inboxCountsBEAN.getTotalCount() + ")");
                    folloupsize = Integer.parseInt(inboxCountsBEAN.getTotalCount());
                }
            }
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
            for (int i = 0; i < 200; i++) {
                updateProgress(i, 200);
                Thread.sleep(3);
            }
            ObservableList<CounselorDetailsBEAN> enquiriesObsList = FXCollections.observableArrayList();
            for (CounselorDetailsBEAN counselorDetailsBEAN : enquiriesList) {
                enquiriesObsList.add(counselorDetailsBEAN);
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
        Image yellowStarImage = new Image(FXMLEnquirySearchController.class
                .getResourceAsStream("stary.png"));
        Image whiteStarImage = new Image(FXMLEnquirySearchController.class.getResourceAsStream("starw.png"));
        Image yellowImrtantImage = new Image(FXMLEnquirySearchController.class.getResourceAsStream("impy.png"));
        Image whiteImportantImage = new Image(FXMLEnquirySearchController.class.getResourceAsStream("impw.png"));

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
                                getAllHyperlinkCounts();

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
//class for adding check boxes

    /**
     *
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
                            System.out.println("testing checkbox test :: " + cpojo.toString());
                            listCheckedEnquiries.add(cpojo);
                            System.out.println("test size :: " + listCheckedEnquiries.size());
                            System.out.println("test data :: " + listCheckedEnquiries.toString());
                        } else {
                            listCheckedEnquiries.remove(cpojo);
                            System.out.println("test size :: " + listCheckedEnquiries.size());
                            System.out.println("test data :: " + listCheckedEnquiries.toString());

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
        /* ====================== load hyperlinks dynamically ====================== */
        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCounts(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
        System.out.println("Testing Counts :: " + listInboxCounts.toString());
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
                    System.out.println("test hyp click" + inboxCountsBEAN);
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
                        }
                    }
                }
            });
            vboxHyperlinks.getChildren().add(hyperlink);
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
                //        lblInboxName.setGraphic(lblInfo);
                //       lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
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
                //     lblInboxName.setGraphic(lblInfo);
                //      lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
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
                //     lblInboxName.setGraphic(lblInfo);
                //      lblInboxName.setContentDisplay(ContentDisplay.RIGHT);
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

        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = CounselorDetailsDAO.getPrimaryInbox(CUR_BRANCH, CUR_USERNAME);

        loadInboxTabsEnquiries(primaryInboxMap);
        /* =============> Reset Hyperlinks <============ */
        getAllHyperlinkCounts();
        titiledPaneSearch.setExpanded(false);

    }

    /**
     *
     * @param primaryInboxMap
     */
    public void loadInboxTabsEnquiries(Map<String, List<CounselorDetailsBEAN>> primaryInboxMap) {
        /* ====================== Sort HashMap Ascending Order ====================== */
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
                    List<CounselorDetailsBEAN> primaryInboxStudyList = primaryInboxMap.get("study");
                    TableView<CounselorDetailsBEAN> tblPrimaryStudyInbox = new TableView<>();
                    addInboxTabData(tblPrimaryStudyInbox, primaryInboxStudyList, "study");
                    tabPrimaryStudy.setContent(tblPrimaryStudyInbox);

                    break;
                case "work":
                    List<CounselorDetailsBEAN> primaryInboxWorkList = primaryInboxMap.get("work");
                    TableView<CounselorDetailsBEAN> tblPrimaryWorkInbox = new TableView<>();
                    addInboxTabData(tblPrimaryWorkInbox, primaryInboxWorkList, "work");

                    tabPrimaryWork.setContent(tblPrimaryWorkInbox);

                    break;
                case "migration":
                    List<CounselorDetailsBEAN> primaryInboxMigrationList = primaryInboxMap.get("migration");
                    TableView<CounselorDetailsBEAN> tblPrimaryMirationInbox = new TableView<>();
                    addInboxTabData(tblPrimaryMirationInbox, primaryInboxMigrationList, "migration");
                    tabPrimaryMigration.setContent(tblPrimaryMirationInbox);

                    break;
                case "training":
                    List<CounselorDetailsBEAN> primaryInboxTrainingList = primaryInboxMap.get("training");
                    TableView<CounselorDetailsBEAN> tblPrimaryTrainingInbox = new TableView<>();
                    addInboxTabData(tblPrimaryTrainingInbox, primaryInboxTrainingList, "training");
                    tabPrimaryTraining.setContent(tblPrimaryTrainingInbox);

                    break;
            }
        }
        tabPanePrimaryInbox.setCursor(Cursor.CLOSED_HAND);

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
//                if (cmbState.getItems().contains("Kerala")) {
//                    cmbState.getSelectionModel().clearAndSelect(cmbState.getItems().indexOf("Kerala"));
//                } else {
//                    cmbState.getSelectionModel().selectFirst();
//                }
                imgViewClearState.setVisible(false);
                searchEnquiries(ACTIVE_TAB);

            }
        });
        imgViewClearCountry.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                cmbCountry.getSelectionModel().clearSelection();
                if (cmbCountry.getItems().contains("India")) {
                    cmbCountry.getSelectionModel().clearAndSelect(cmbCountry.getItems().indexOf("India"));
                } else {
                    cmbCountry.getSelectionModel().selectFirst();
                }
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

    /**
     *
     */
    public void initComboBoxes() {

        cmbBranch.setItems(branch);
//        if (cmbBranch.getItems().contains(CUR_BRANCH)) {
//            cmbBranch.getSelectionModel().clearAndSelect(cmbBranch.getItems().indexOf(CUR_BRANCH));
//        }
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
        List<String> statelist = MasterCountryStateDistrictDAO.getAllStates(cmbCountry.getSelectionModel().getSelectedItem().toString());
        state.clear();
        for (String s : statelist) {
            state.add(s);
        }
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
                    List<String> district2 = MasterCountryStateDistrictDAO.getAllDistricts(cmbState.getSelectionModel().getSelectedItem().toString());
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
                    cmbProgramFld.getSelectionModel().selectFirst();

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
                if (newValue != null) {
                     cmbAssignBy.getItems().clear();
                    ObservableList<CounselorsListPOJO> assignedCounselors = FXCollections.observableArrayList();
                    if (newValue.equalsIgnoreCase("All")) {
                        assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(null);
                    } else {
                        assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(newValue);
                    }
                    cmbAssignBy.setItems(assignedCounselors);
                }

            }
        });
        // cmbEnquiryFor.setItems(enquiryfor);

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
                    mainStage.setTitle("International Academy");
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

            Map<String, List<CounselorDetailsBEAN>> primarySearchMap = InboxSearchService.getPrimarySearch(searchQueryBEAN, CUR_BRANCH, CUR_USERNAME, CUR_ROLE, dynamic_query);
            loadInboxTabsEnquiries(primarySearchMap);
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

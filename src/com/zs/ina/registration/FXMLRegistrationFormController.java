/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.registration;

import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryDAO;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryIMPl;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryPOJO;
import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterDAO;
import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterIMPL;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterDAO;
import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterIMPL;
import com.zs.ina.admin.master.purpose.dao.PurposeDAO;
import com.zs.ina.admin.master.purpose.dao.PurposeIMPL;
import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.admin.master.technicalskills.dao.TechnicalSkillsMasterDAO;
import com.zs.ina.admin.master.technicalskills.dao.TechnicalSkillsMasterIMPL;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestBEAN;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestDAO;
import com.zs.ina.assesment.admission.test.dao.AdmissionTestIMPL;
import com.zs.ina.assesment.model.AssesmentLanguageTestBEAN;
import com.zs.ina.assesment.model.AssesmentMigrateBEAN;
import com.zs.ina.assesment.model.AssesmentPlusTwoBEAN;
import com.zs.ina.assesment.model.SpouseDetailsBEAN;
import com.zs.ina.assesment.model.AssesmentSpouseExpBEAN;
import com.zs.ina.assesment.model.AssesmentSsslcBEAN;
import com.zs.ina.assesment.model.AssesmentTertiaryBEAN;
import com.zs.ina.assesment.model.AssesmentTrainingBEAN;
import com.zs.ina.assesment.model.AssesmentWorkBEAN;
import com.zs.ina.assesment.model.AssessmentChildBEAN;
import com.zs.ina.assesment.model.WorktExperienceBEAN;
import com.zs.ina.assesment.model.AssessmentStatusBEAN;
import com.zs.ina.assesment.model.AssessmentPersonBEAN;
import com.zs.ina.assesment.model.StudySuggestedRequiredBEAN;
import com.zs.ina.assesment.children.dao.ChildrenDAO;
import com.zs.ina.assesment.dao.AssesmentDAO;
import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.langskills.dao.LanguageSkillsBEAN;
import com.zs.ina.assesment.langskills.dao.LanguageSkillsDAO;
import com.zs.ina.assesment.langskills.dao.LanguageSkillsIMPL;
import com.zs.ina.assesment.otherskills.dao.OtherSkillBEAN;
import com.zs.ina.assesment.otherskills.dao.OtherSkillsDAO;
import com.zs.ina.assesment.otherskills.dao.OtherSkillsIMPL;
import com.zs.ina.assesment.relative.dao.RelativeDAO;
import com.zs.ina.assesment.relative.dao.RelativeIMPL;
import com.zs.ina.assesment.relative.dao.RelativeBEAN;
import com.zs.ina.assesment.status.dao.AssessmentStatusDAO;
import com.zs.ina.assesment.status.dao.AssessmentStatusIMPL;
import com.zs.ina.assesment.technical.dao.TechnicalSkilllsIMPL;
import com.zs.ina.assesment.technical.dao.TechnicalSkillsBEAN;
import com.zs.ina.assesment.technical.dao.TechnicalSkillsDAO;
import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.common.ScrollPaneFocus;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.common.ShowProgress;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.FXMLErrorViewerController;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.ContactNumberBEAN;
import com.zs.ina.enquiry.dao.ContactNumbersDAO;
import com.zs.ina.enquiry.dao.ContactNumbersIMPL;
import com.zs.ina.admin.master.retrieve.MasterCountryStateDistrictDAO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.dao.LanguageTestCrudDAO;
import com.zs.ina.assesment.dao.PersonalCrudDAO;
import com.zs.ina.assesment.dao.QualificationsCrudDAO;
import com.zs.ina.assesment.dao.SpouseCrudDAO;
import com.zs.ina.assesment.dao.WorkExperienceDAO;
import com.zs.ina.assesment.plus2.dao.Assesmentplus2DAO;
import com.zs.ina.assesment.sslc.dao.AssessmentSSLCDAO;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.enquiry.dao.PhoneSearchPOJO;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.registration.documents.FXMLDocumentsVerifyController;
import com.zs.ina.registration.invoice.FXMLInvoiceController;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import jfxtras.scene.control.LocalTimePicker;
import jidefx.scene.control.hints.ListDataIntelliHints;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLRegistrationFormController implements Initializable {

    Stage stage;
    List<Integer> qId = new ArrayList<>();
    @FXML
    private RadioButton maleRadioBtn;
    @FXML
    private ComboBox<?> spouseProfessionCmb;
    @FXML
    private ComboBox<?> spousQualificationCmb;
    @FXML
    private ComboBox<?> spouseQualificationFieldCmb;
    @FXML
    private ComboBox<?> durationCmb;
    @FXML
    private HBox hboxTimePicker;
    ObservableList state = FXCollections.observableArrayList();
    ObservableList district = FXCollections.observableArrayList();
    ObservableList salary = FXCollections.observableArrayList();
    @FXML
    private ComboBox<EnquirySatusPOJO> cmbAssStatus;
    @FXML
    private ComboBox<?> programLevelCmb;
    @FXML
    private ComboBox<?> programFieldCmb;
    @FXML
    private ComboBox<?> intakeCmb;
    LocalTimePicker timePickerAppointmentTime;
    static LocalTime LOCAL_TIME, CURRENT_TIME;
    ObservableList branch = FXCollections.observableArrayList();
    ObservableList source = FXCollections.observableArrayList();
    ObservableList timing = FXCollections.observableArrayList();
    ObservableList institution = FXCollections.observableArrayList();
    ObservableList obsExamRepeatAbsent = FXCollections.observableArrayList("Repeat", "Absent");
    ObservableList obsModeOfExam = FXCollections.observableArrayList("Semester", "Single", "Yearly");
    ObservableList age = FXCollections.observableArrayList();
    ObservableList applicationList = FXCollections.observableArrayList();
    ObservableList sassign = FXCollections.observableArrayList();
    private ObservableList<TextField> childNameTxts = FXCollections.observableArrayList();
    private ObservableList<ComboBox> cmbChildGenders = FXCollections.observableArrayList();
    private final ObservableList gender = FXCollections.observableArrayList("Male", "Female");
    private final ObservableList children = FXCollections.observableArrayList("1st Child", "2nd Child", "3rd Child", "4th Child", "5th Child", "6th Child", "7th Child");
    // private final ObservableList proficiency = FXCollections.observableArrayList("Speak", "Read", "Write");
    private ObservableList<TextField> childAgeTxts = FXCollections.observableArrayList();

    ObservableList dummy = FXCollections.observableArrayList("1", "2");
    ObservableList country = FXCollections.observableArrayList();
    ObservableList programLevel = FXCollections.observableArrayList("Batchelor", "Master", "PhD/Doctorate", "Prep Course", "Language Course", "Short Course");
    ObservableList sslcStatus = FXCollections.observableArrayList("Pass", "Fail");

    @FXML
    private ComboBox<?> universityStudyCombo;
    @FXML
    private ComboBox<String> sslcBoardCombo;
    @FXML
    private ComboBox<String> mediumCombo;
    @FXML
    private ComboBox<String> plus2BoardCombo;
    @FXML
    private ComboBox<String> plus2MediumCombo;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView starimageView;
    static int PRIORITY_RATING = 0;
    CounselorDetailsBEAN counselorDetailsBEAN;
    @FXML
    private Label lblAssesmentTitle;
    ObservableList programFieldList = FXCollections.observableArrayList();
    ObservableList spouseProgramFieldList = FXCollections.observableArrayList();
    ObservableList otherQualProgramFieldList = FXCollections.observableArrayList();
    ObservableList programLevelList = FXCollections.observableArrayList();
    ObservableList duration = FXCollections.observableArrayList();
    ObservableList profession = FXCollections.observableArrayList("Software Eng");
    ObservableList baches = FXCollections.observableArrayList();
    ObservableList training = FXCollections.observableArrayList();
    ObservableList score = FXCollections.observableArrayList();
    ObservableList otherTest = FXCollections.observableArrayList();
    ObservableList locationList = FXCollections.observableArrayList("India");
    ObservableList migrateCategory = FXCollections.observableArrayList();
    private ObservableList exam_boards = FXCollections.observableArrayList();
    private ObservableList language = FXCollections.observableArrayList();
    private ObservableList language_proficiency = FXCollections.observableArrayList("Yes", "No");
    private final ObservableList obsKnowledgeLevel = FXCollections.observableArrayList("Advanced", "Average", "Excellent", "Competitive", "Moderate");
    @FXML
    private ComboBox<?> childGenderCmb;
    String ENQUIRY_ID;
    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<String> cmbAge;
    @FXML
    private GridPane spouseQualGrid;
    ForwardHistoryDAO forwardHistoryDAO = new ForwardHistoryIMPl();
    String CURR_COUNSELOR = null;

    /**
     *
     */
    public static ObservableList<ComboBox> spouseFieldCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseLevelCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseProfessionCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> durationCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> testOptionCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> testStatusCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseReadingCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseWritingCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseSpeakingCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseListeningCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> spouseOverallCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> teritaryFieldCombos = FXCollections.observableArrayList();

    /**
     *
     */
    public static ObservableList<ComboBox> teritaryLevelCombos = FXCollections.observableArrayList();

    ObservableList testStatus = FXCollections.observableArrayList("Pass", "Fail");
    ObservableList testScore = FXCollections.observableArrayList();
    ObservableList relationShipList = FXCollections.observableArrayList("Father", "Mother", "Wife", "Husband", "Grand Mother", "Grand Father", "Son", "Daughter", "Mother-in-law", "Father-in-law", "Others");
    ObservableList studyList = FXCollections.observableArrayList("Study");
    ObservableList workList = FXCollections.observableArrayList("Work");
    ObservableList migrationList = FXCollections.observableArrayList("Migration");
    ObservableList trainingList = FXCollections.observableArrayList("Training");
    ObservableList currencyList = FXCollections.observableArrayList();
    ObservableList minSalaryList = FXCollections.observableArrayList();
    ObservableList maxSalaryList = FXCollections.observableArrayList();
    ObservableList industryList = FXCollections.observableArrayList();
    @FXML
    private GridPane spouseWorkExpGrid;
    @FXML
    private GridPane spouseLangTestGrid;
    @FXML
    private ComboBox<?> cmbTestOption;

    @FXML
    private ComboBox<?> cmbSpouseOverall;
    @FXML
    private Button btnSpouseSave;
    @FXML
    private ComboBox<String> cmbSpouseAge;
    @FXML
    private GridPane gridChildren;
    @FXML
    private ComboBox<?> cmbWorkCurrency;
    @FXML
    private ComboBox<?> CmbWorkMin;
    @FXML
    private ComboBox<?> cmbWorkMax;
    @FXML
    private ComboBox<?> cmbTQLevel;
    @FXML
    private ComboBox<?> cmbTQField;
    @FXML
    private GridPane gridWorkExp;
    @FXML
    private ComboBox<?> cmbCommenTestStatus;
    @FXML
    private GridPane languageTestGrid;
    @FXML
    private GridPane gridTertiaryQualifications;
//    private Button btnProgramSuggestedSave;
    @FXML
    private ComboBox<?> cmbCommenTestOption;
    @FXML
    private Button btnSaveQualiExperience;
    @FXML
    private GridPane gridStudy;
    @FXML
    private GridPane workGrid;
    @FXML
    private GridPane migrationGrid;
    @FXML
    private GridPane trainingGrid;
    ObservableList intakeObsList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    /* ################### ==== Dummy data for approximate program fees ==== ############### */
    ObservableList approxiAmountObsList = FXCollections.observableArrayList("1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000", "11000", "12000");
    ObservableList approxiFeeTypeObsList = FXCollections.observableArrayList("Smester", "Annual", "Total", "4000", "5000", "6000", "7000", "8000", "9000", "10000", "11000", "12000");
//    private Button btnMigrationSave;
    ObservableList choiceObsList = FXCollections.observableArrayList();
    ObservableList choiceObsListWork = FXCollections.observableArrayList();
    ObservableList choiceObsListMigrate = FXCollections.observableArrayList();
    ObservableList choiceObsListTraining = FXCollections.observableArrayList();
    @FXML
    private AnchorPane spousePanel;
    @FXML
    private DatePicker datePickDob;
    @FXML
    private ToggleGroup groupGender;
    @FXML
    private TextField txtStdIsd;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextField txtStdIsd2;
    @FXML
    private TextField txtContactNumber2;
    @FXML
    private Button btnContactPlus;
    @FXML
    private Button btnContactClose;
    @FXML
    private GridPane gridContactNumber;
    int CONTACT_LIMIT = 4;
    @FXML
    private TextField txtContactEmail;
    @FXML
    private ComboBox<String> cmbDistrict;
    @FXML
    private ComboBox<String> cmbState;
    @FXML
    private ComboBox<String> cmbApplicationType;
    @FXML
    private RadioButton femaleRadioBtn;
    @FXML
    private Button btnAssessmentSave;
    @FXML
    private ComboBox<CounselorsListPOJO> cmbAssignedCounselor;
    @FXML
    private GridPane gridRelativeDetails;

    @FXML
    private GridPane gridLanguageSkills;
    @FXML
    private GridPane gridTechnicalDetails;
    @FXML
    private GridPane gridOtherSkills;
    @FXML
    private GridPane gridAdmissionTest;
    @FXML
    private TabPane tabPaneProgramRequired;
    @FXML
    private Button btnAssessmentPreview;
    @FXML
    private Label lblLanguageSkills;
    @FXML
    private Label lblTechnicalSkills;
    @FXML
    private Label lblOtherSkills;
    @FXML
    private Label lblAdmissionTest;
    private String CUR_USERNAME = null;
    private String CUR_BRANCH = null;
    private String CURRENT_TAB = null;

    @FXML
    private ComboBox<String> cmbForwardFor;
    @FXML
    private TextField txtRemarks;
    @FXML
    private ComboBox<String> cmbDepartment;
    @FXML
    private ComboBox<String> cmbAssignedBranch;
    @FXML
    private DatePicker datePickerAppointment;
    @FXML
    private ScrollPane scrollPaneMain;
    @FXML
    private AnchorPane anchorQualification;
    SpouseDetailsBEAN spouseDetailsBEAN = new SpouseDetailsBEAN();
    AssessmentStatusBEAN assessmentStatusBEAN = new AssessmentStatusBEAN();
    RelativeDAO relativeDAO = new RelativeIMPL();
    LanguageSkillsDAO languageSkillsDAO = new LanguageSkillsIMPL();
    TechnicalSkillsDAO technicalSkillsDAO = new TechnicalSkilllsIMPL();
    TechnicalSkillsMasterDAO masterTechSkillsDAO = new TechnicalSkillsMasterIMPL();
    OtherSkillsMasterDAO masterOtherSkillsDAO = new OtherSkillsMasterIMPL();
    OtherSkillsDAO otherSkillsDAO = new OtherSkillsIMPL();
    AdmissionTestMasterDAO masterAdmissionTestDAO = new AdmissionTestMasterIMPL();
    AdmissionTestDAO admissionTestDAO = new AdmissionTestIMPL();
    AssessmentStatusDAO assessmentStatusDAO = new AssessmentStatusIMPL();
    ShowProgress showProgress = new ShowProgress();
    final Popup popup = new Popup();
    ShowPopupMessages showAlerts = new ShowPopupMessages();
    PurposeDAO purposeDAO = new PurposeIMPL();
    List<String> stdCodes = new ArrayList<>();
    List<String> stdCodes2 = new ArrayList<>();
    ContactNumbersDAO contactNumbersDAO = new ContactNumbersIMPL();
    AssessmentPersonBEAN AssessmentPersonBEAN = new AssessmentPersonBEAN();
    /* ====================== Dynamic Control Lists For Global Access ====================== */
    ObservableList<StudySuggestedRequiredBEAN> listStudySuggestedRequired = FXCollections.observableArrayList();
    List<ContactNumberBEAN> listContactNumberBEAN = new ArrayList<>();
    List<AssesmentWorkBEAN> listWorkRequired = new ArrayList<>();
    List<AssesmentMigrateBEAN> listMigrateRequired = new ArrayList<>();
    List<AssesmentTrainingBEAN> listTrainingRequired = new ArrayList<>();
    AssesmentSsslcBEAN assesmentSslcBEAN = new AssesmentSsslcBEAN();
    AssesmentPlusTwoBEAN assesmentPlusTwoBEAN = new AssesmentPlusTwoBEAN();
    List<AssesmentTertiaryBEAN> assesmentTertiaryBEANList = new ArrayList<>();
    List<WorktExperienceBEAN> listWorkExpBEAN = new ArrayList<>();
    List<AssesmentLanguageTestBEAN> listLanguageTestBEAN = new ArrayList<>();
    List<AdmissionTestBEAN> listAdmissionTestBEAN = new ArrayList<>();
    List<LanguageSkillsBEAN> languageSkillsBEANs = new ArrayList<>();
    List<TechnicalSkillsBEAN> listTechnicalSkillsBEAN = new ArrayList<>();
    List<OtherSkillBEAN> listOtherSkillBEAN = new ArrayList<>();
    List<AssesmentTertiaryBEAN> listAssesmentSpouseTertiaryBEAN = new ArrayList<>();
    List<AssesmentSpouseExpBEAN> listAssesmentSpouseExpBEAN = new ArrayList<>();
    List<AssesmentLanguageTestBEAN> listAssesmentLanguageTest = new ArrayList<>();
    List<AssessmentChildBEAN> childBEANList = new ArrayList<>();
    List<RelativeBEAN> listRelativeBEAN = new ArrayList<>();
    @FXML
    private Button btnSavePersonal;
    @FXML
    private Button btnSaveProgramRequired;
    @FXML
    private Button btnSaveAssessmentStatus;
    static Logger logger = Logger.getLogger(FXMLRegistrationFormController.class);
    CountryDAO countryDAO = new CountryIMPL();
    private Executor executor;
    List<PhoneSearchPOJO> listContactnumbers = new ArrayList<>();
    Task< List<PhoneSearchPOJO>> taskPhonenSearch;
    @FXML
    private Button btnCreateNewTask;
    ObservableList department = FXCollections.observableArrayList();
    @FXML
    private TextField txtPreviousName;
    @FXML
    private TextField txtPassportNo;
    @FXML
    private TextField txtHouseNoOrName;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtPostOrPin;
    ListDataIntelliHints hintsContactNumbers = null;
    @FXML
    private AnchorPane anchorStudy;
    @FXML
    private ComboBox<?> cmbStudyCountry;
    @FXML
    private AnchorPane anchorDocumentVerify;
    @FXML
    private VBox vboxDocVerify;
    @FXML
    private AnchorPane anchorInvoice;
    @FXML
    private VBox vboxInvoice;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cmbSpousePlusTwo;
    @FXML
    private Button btnSearchProgram;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = new CounselorDetailsBEAN();
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        ENQUIRY_ID = counselorDetailsBEAN.getEnquiryID();
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CURR_COUNSELOR = counselorDetailsBEAN.getEnquiryAssignedTo();
        CURRENT_TAB = Context.getInstance().currentProfile().getCurrentTab();
        /* ====================== Bind All Data  ====================== */
        PRIORITY_RATING = 0;

        /* ======================== Set Jump To Char (items view based on keypress) ==================== */
        ComboBoxJumpToChar.jumpToChar(cmbApplicationType);
        ComboBoxJumpToChar.jumpToChar(cmbDistrict);
        ComboBoxJumpToChar.jumpToChar(cmbState);
        ComboBoxJumpToChar.jumpToChar(cmbCountry);
        ComboBoxJumpToChar.jumpToChar(cmbDepartment);
        ComboBoxJumpToChar.jumpToChar(cmbAge);
        ComboBoxJumpToChar.jumpToChar(cmbAssStatus);
        ComboBoxJumpToChar.jumpToChar(cmbForwardFor);
        ComboBoxJumpToChar.jumpToChar(cmbAssignedCounselor);
        ComboBoxJumpToChar.jumpToChar(cmbAssignedBranch);
        ComboBoxJumpToChar.jumpToChar(sslcBoardCombo);
        ComboBoxJumpToChar.jumpToChar(mediumCombo);
        ComboBoxJumpToChar.jumpToChar(plus2BoardCombo);
        ComboBoxJumpToChar.jumpToChar(plus2MediumCombo);
        ComboBoxJumpToChar.jumpToChar(cmbAssStatus);
        ComboBoxJumpToChar.jumpToChar(cmbAssignedCounselor);
        ComboBoxJumpToChar.jumpToChar(cmbSpousePlusTwo);

        /* ========================  create executor that uses daemon threads: ==================== */
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        ObservableList maritalStatus = FXCollections.observableArrayList("Single", "Married", "Divorced", "With Spouse", "Spouse+Child");
        cmbApplicationType.setItems(maritalStatus);
//        cmbApplicationType.getSelectionModel().selectFirst();
        assignComboValues();
        cmbAssStatus.setItems(RetrieveStaticMasterDAO.retrieveEnquiryStatus());
        // ComboBoxJumpToChar.jumpToChar(cmbAssStatus);
        timePickerAppointmentTime = new LocalTimePicker(null);
        hboxTimePicker.getChildren().add(timePickerAppointmentTime);
        hboxTimePicker.setStyle("-fx-border:none");
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        for (String s : branches) {
            branch.add(s);
        }
        cmbAssignedBranch.setItems(branch);
        //  assignedBranchCmb.getSelectionModel().selectFirst();
        List<String> sources = RetrieveStaticMasterDAO.getAllSources();
        for (String s : sources) {
            source.add(s);
        }
        List<String> departments = RetrieveStaticMasterDAO.getAllDepartment();
        for (String departmenttt : departments) {
            department.add(departmenttt);
        }
        cmbDepartment.setItems(department);
        //   cmbDepartment.getSelectionModel().selectFirst();
//        List<String> sourceAssign = EnquiryDetailsDAO.getAllCounselors(Context.getInstance().currentProfile().getProfilePOJO().getBranch());
//        for (String s : sourceAssign) {
//            sassign.add(s);
//        }
//        assignedCounsellorCmb.setItems(sassign);

//        anchorPane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
        bindAllControls();
        initData();
        numberValidations();
        CURRENT_TIME = timePickerAppointmentTime.getLocalTime();
        spousQualificationCmb.valueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                spouseProgramFieldList.removeAll(spouseProgramFieldList);
                for (String s : programField) {
                    spouseProgramFieldList.add(s);
                }
                spouseQualificationFieldCmb.setItems(spouseProgramFieldList);
                spouseQualificationFieldCmb.getSelectionModel().selectFirst();
            }
        });
        /* ==== universty master data ====== */
        institution = RetrieveStaticMasterDAO.getUniversities();

        // <<-----------
        //    assesmentSaveBtnActions();
        initDynamicSpouseTertieryControls();
        initDynamicSpouseExperience();
        initSpouseOtherTestControls();
        initSpouseDetails();
        initDynamicChildrenControls();
        initWorkExperianceDetails();
        initTeritaryLanguageTestControls();
        initTertiaryQualifications();
        initAssesmentStatus();
        initSslcPlusTwoDetails();
        /* ======================== Show Or Hide Required Features Based on Current inbox tab ==================== */
        showHideFeatures(CURRENT_TAB, false);
        switch (CURRENT_TAB) {
            case "study":
                tabPaneProgramRequired.getSelectionModel().select(0);
                break;
            case "work":
                tabPaneProgramRequired.getSelectionModel().select(1);
                break;
            case "migration":
                tabPaneProgramRequired.getSelectionModel().select(2);
                break;
            case "training":
                tabPaneProgramRequired.getSelectionModel().select(3);
                break;
            default:
                System.out.println("not workingggf");

        }
        initStudyRequiredDetails();
        initWorkDetails();
        initMigrateDetails();
        initTrainingDetails();

//        if (EnquiryAssessmentPersonDAO.checkEnquiryIdInPersonalDetails(ENQUIRY_ID)) {
        initPersonalDetails();
//        }
        /* ============== Change Date Formats ======================*/
        ChangeDateFormat.datePickerDateFormatter(datePickDob);
        ChangeDateFormat.datePickerDateFormatter(datePickerAppointment);
        /* ====================== Dynamic Contact Numbers ====================== */
        initDynamicContactControlls();
        /* ====================== Dynamic relative details ====================== */
        initDynamicRelativeDetails();
        /* ====================== Dynamic Language Skills ====================== */
        initDynamicLangSkills();
        /* ====================== Dynamic Technical Skills ====================== */
        initDynamicaTechSkills();
        /* ====================== Dynamic Controls For Other Skills ====================== */
        initDynamicOtherSkills();
        /* ====================== Dynamic Controls For Admission Test ====================== */
        initDynamicAdmissiontest();
        /* ======================== Show Preview  ==================== */
        showPreview();
        /* ======================== Show Hide Features Based On Tab ==================== */
        showHideFeatures();
        /* ========================================================================= */
 /* ====================== Save All Assessment Detials ====================== */
 /* ========================================================================= */
        saveAllAssessmentDetails();
        /* ========================================================================= */
 /* ====================== End Save All Assessment Detials ================== */
 /* ========================================================================= */
        saveButtonsForIndividualSection();

        /* ====================== Load Task ====================== */
        btnCreateNewTask.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                StackPane secondaryLayout = new StackPane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/FXMLTaskFullView.fxml"));
                try {
                    /* ======================== Add Bean TO Context ==================== */
                    Context.getInstance().currentProfile().setCounselorDetailsTaskBEAN(counselorDetailsBEAN);
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Task Details");
                    secondStage.setScene(secondScene);
                    secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.initModality(Modality.WINDOW_MODAL);
                    secondStage.initOwner(btnAssessmentPreview.getScene().getWindow());
                    secondStage.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
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
                    logger.error(exception.getStackTrace());

                }

            }
        });
        /* ======================== Add Verify Documents Section ==================== */
        verifyDocuments();

        /* ======================== PassPort NO Validation ==================== */
        changeListenersPersonal();

        invoiceGeneration();

        datePickerAppointment.setEditable(false);
        //  datePickerAppointment.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
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
        datePickerAppointment.setDayCellFactory(dayCellFactory);
        /* ==================== Program Based Search Button ================= */
            btnSearchProgram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
            try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/pgmandqual/FXMLProgramBasedSearchOptions.fxml"));
                    Stage stageAddCountry = new Stage();
                    stageAddCountry.setTitle("Search Based On Programs");
                    stageAddCountry.initModality(Modality.APPLICATION_MODAL);
                    stageAddCountry.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageAddCountry.setScene(new Scene(root));
                    stageAddCountry.setResizable(true);
                    stageAddCountry.initOwner(btnSearchProgram.getScene().getWindow());
                    stageAddCountry.show();
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
    public void changeListenersPersonal() {
        txtName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.contactNameProperty(), txtName.textProperty());
                        txtName.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.contactNameProperty(), txtName.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.contactNameProperty(), txtName.textProperty());
                        txtName.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.contactNameProperty(), txtName.textProperty());
                    }

                }
            }

        });
        txtPreviousName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.previousNameProperty(), txtPreviousName.textProperty());
                        txtPreviousName.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.previousNameProperty(), txtPreviousName.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.previousNameProperty(), txtPreviousName.textProperty());
                        txtPreviousName.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.previousNameProperty(), txtPreviousName.textProperty());
                    }

                }
            }

        });
        txtPassportNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,0-9]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.passportNoProperty(), txtPassportNo.textProperty());
                        txtPassportNo.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.passportNoProperty(), txtPassportNo.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.passportNoProperty(), txtPassportNo.textProperty());
                        txtPassportNo.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.passportNoProperty(), txtPassportNo.textProperty());
                    }

                }
            }

        });
        txtHouseNoOrName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,0-9,\\s,/]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.houseNameProperty(), txtHouseNoOrName.textProperty());
                        txtHouseNoOrName.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.houseNameProperty(), txtHouseNoOrName.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.houseNameProperty(), txtHouseNoOrName.textProperty());
                        txtHouseNoOrName.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.houseNameProperty(), txtHouseNoOrName.textProperty());
                    }

                }
            }

        });
        txtStreet.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.streetProperty(), txtStreet.textProperty());
                        txtStreet.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.streetProperty(), txtStreet.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.streetProperty(), txtStreet.textProperty());
                        txtStreet.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.streetProperty(), txtStreet.textProperty());
                    }

                }
            }

        });
        txtPlace.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.placeProperty(), txtPlace.textProperty());
                        txtPlace.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.placeProperty(), txtPlace.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.placeProperty(), txtPlace.textProperty());
                        txtPlace.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.placeProperty(), txtPlace.textProperty());
                    }

                }
            }

        });
        txtPostOrPin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,0-9,\\s,/]*"))) {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.postOfficeProperty(), txtPostOrPin.textProperty());
                        txtPostOrPin.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.postOfficeProperty(), txtPostOrPin.textProperty());
                    } //                   
                    else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.postOfficeProperty(), txtPostOrPin.textProperty());
                        txtPostOrPin.setText(newValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.postOfficeProperty(), txtPostOrPin.textProperty());
                    }

                }
            }

        });
    }

    /**
     *
     */
    public void invoiceGeneration() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/registration/invoice/FXMLInvoice.fxml"));
        try {
            /* ======================== Add Bean TO Context ==================== */
            Context.getInstance().currentProfile().setCounselorDetailsTaskBEAN(counselorDetailsBEAN);
            Parent root = (Parent) loader.load();
            FXMLInvoiceController controller = (FXMLInvoiceController) loader.getController();
            controller.initData(counselorDetailsBEAN);
            vboxInvoice.getChildren().remove(0);
            vboxInvoice.getChildren().add(root);
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.error(exception.getStackTrace());
        }
    }

    /**
     *
     */
    public void checkWhetherOneProgramSelected() {

        //  if (listStudySuggestedRequired.size() <= 1 && listWorkRequired.size() <= 1 && listMigrateRequired.size() <= 1 && listTrainingRequired.size() <= 1) {
        if (validateMasterStudyRequiredDetails(listStudySuggestedRequired) <= 0 || validateMasterWorkDetails(listWorkRequired) <= 0 || validateMasterMigrateData(listMigrateRequired) <= 0 || validateMasterTrainingData(listTrainingRequired) <= 0) {

        } else {
            Notification notification = Notifications.WARNING;
            TrayNotification tray = new TrayNotification("Program Not Selected!", "Select atleast one program !", notification);
            tray.setAnimation(Animations.POPUP);
            tray.showAndDismiss(Duration.seconds(7));
        }
        //  }

    }

    /**
     *
     */
    public void verifyDocuments() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/registration/documents/FXMLDocumentsVerify.fxml"));
        try {
            /* ======================== Add Bean TO Context ==================== */
            Context.getInstance().currentProfile().setCounselorDetailsTaskBEAN(counselorDetailsBEAN);
            Parent root = (Parent) loader.load();
            FXMLDocumentsVerifyController controller = (FXMLDocumentsVerifyController) loader.getController();
            controller.initData(counselorDetailsBEAN);
            vboxDocVerify.getChildren().remove(0);
            vboxDocVerify.getChildren().add(root);
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.error(exception.getStackTrace());
        }
    }

    /**
     *
     * @return
     */
    public boolean validateSslcPlusTwo() {
        boolean flag = true;
        if (assesmentSslcBEAN.getSslcBoard() == null || assesmentSslcBEAN.getSslcBoard().equalsIgnoreCase("")
                || assesmentSslcBEAN.getSslcMedium() == null || assesmentSslcBEAN.getSslcMedium().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, sslcBoardCombo);
            showAlerts.showError("All Fields Required In SSLC Details!", sslcBoardCombo);
        } else if (assesmentPlusTwoBEAN.getPlusTwoBoard() == null || assesmentPlusTwoBEAN.getPlusTwoBoard().equalsIgnoreCase("")
                || assesmentPlusTwoBEAN.getPlusTwoMedium() == null || assesmentPlusTwoBEAN.getPlusTwoMedium().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, plus2BoardCombo);
            showAlerts.showError("All Fields Required In PLUS2 Details!", plus2BoardCombo);
        }
        return flag;
    }

    /**
     *
     */
    public void saveButtonsForIndividualSection() {
        btnSavePersonal.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                /* ====================== For Spot Admission ====================== */
                if (ENQUIRY_ID == null || ENQUIRY_ID.equalsIgnoreCase("")) {

                    if (counselorDetailsBEAN.getEnquiryMethod() == null) {
                        counselorDetailsBEAN.setEnquiryMethod("Spot Registration");
                    }
                    if (counselorDetailsBEAN.getBracnch() == null) {
                        counselorDetailsBEAN.setBracnch(CUR_BRANCH);
                    }
                    if (counselorDetailsBEAN.getEnquiryAssignedTo() == null) {
                        counselorDetailsBEAN.setEnquiryAssignedTo("Not Assigned");
                    }
                    if (validatePersonalDetails() && validateContactList(listContactNumberBEAN)) {

                        ENQUIRY_ID = "enq_spot_reg_" + UiiDGenerator.getUIID8();
                        counselorDetailsBEAN.setEnquiryID(ENQUIRY_ID);
                        int row = EnquiryDetailsDAO.insertEnquiryIntoDB(counselorDetailsBEAN);
                        if (row > 0) {
                            assessmentStatusBEAN.setEnquiryId(ENQUIRY_ID);
                            assessmentStatusBEAN.setAssessmentStatus("1");
                            assessmentStatusBEAN.setPriority("1");
                            assessmentStatusBEAN.setReference("Assessment Pending");
                            assessmentStatusDAO.insertAssessmentStatus(assessmentStatusBEAN);
                        }
                    }
                    /* ====================== Add a default status ====================== */
 /* ======================== causes null insertion ==================== */
                    //    AssessmentStatusBEAN assessmentStatusBEAN = new AssessmentStatusBEAN();

                }

                /* ====================== Save Personal Info ====================== */
                if (validatePersonalDetails() && validateContactList(listContactNumberBEAN)) {
                    if (counselorDetailsBEAN.getRowId() != null) {
                        PersonalCrudDAO.updatePersonalDetails(counselorDetailsBEAN);
                    } else {
                        PersonalCrudDAO.insertPersonalDetails(counselorDetailsBEAN);
                    }
                    /* ====================== Save Contact Numbers ====================== */
                    for (ContactNumberBEAN numberBEAN : listContactNumberBEAN) {
                        if (numberBEAN.getRowId() != null) {
                            contactNumbersDAO.updateContactNumber(numberBEAN);
                        } else {
                            numberBEAN.setEnquiryId(ENQUIRY_ID);
                            contactNumbersDAO.insertContactNumber(numberBEAN);
                        }
                    }
                    checkWhetherOneProgramSelected();
                    showAlerts.showSuccess("Saved Successfully!", "Personal Details Saved!", txtName);
                }
            }
        });
        btnSaveAssessmentStatus.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /* ====================== For Spot Admission ====================== */
                if (ENQUIRY_ID == null || ENQUIRY_ID.equalsIgnoreCase("")) {

                    if (counselorDetailsBEAN.getEnquiryMethod() == null) {
                        counselorDetailsBEAN.setEnquiryMethod("Spot Registration");
                    }
                    if (counselorDetailsBEAN.getBracnch() == null) {
                        counselorDetailsBEAN.setBracnch(CUR_BRANCH);
                    }
                    if (counselorDetailsBEAN.getEnquiryAssignedTo() == null) {
                        counselorDetailsBEAN.setEnquiryAssignedTo("Not Assigned");
                    }
                    if (validatePersonalDetails()) {
                        ENQUIRY_ID = "enq_spot_reg_" + UiiDGenerator.getUIID8();
                        counselorDetailsBEAN.setEnquiryID(ENQUIRY_ID);
                        int row = EnquiryDetailsDAO.insertEnquiryIntoDB(counselorDetailsBEAN);
                    }
                }
                /* ====================== Save Assessment Status ====================== */

                int done = 0;
                if (validatePersonalDetails()) {
                    if (validateAssessmentStatus()) {
                        assessmentStatusBEAN.setPriority("" + PRIORITY_RATING);
                        if (assessmentStatusBEAN.getStatusId() != null) {

                            done = assessmentStatusDAO.updateAssessmentStatus(assessmentStatusBEAN);
                        } else {

                            assessmentStatusBEAN.setEnquiryId(ENQUIRY_ID);
                            done = assessmentStatusDAO.insertAssessmentStatus(assessmentStatusBEAN);
                        }
                        checkWhetherOneProgramSelected();
                        showAlerts.showSuccess("Saved Successfully!", "Assessment Status Saved!", txtRemarks);
                        if (done > 0) {
                            /*========================= Insert History Details of counselor assign ================================ */
                            ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                            historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                            historyPOJO.setAssigned_branch(CUR_BRANCH);
                            historyPOJO.setAssigned_by(CUR_USERNAME);
                            historyPOJO.setAssigned_date(assessmentStatusBEAN.getAppointmentDate() + " " + assessmentStatusBEAN.getAppointmentTime());
                            historyPOJO.setEnquiry_id(counselorDetailsBEAN.getEnquiryID());
                            historyPOJO.setAssigned_to(counselorDetailsBEAN.getEnquiryAssignedTo());
                            historyPOJO.setAssigned_to_branch(counselorDetailsBEAN.getBracnch());
                            historyPOJO.setHolded_by(CURR_COUNSELOR);
                            historyPOJO.setRemarks(assessmentStatusBEAN.getRemarks());
                            if (counselorDetailsBEAN.getStatus() != null) {
                                historyPOJO.setCurrent_status(counselorDetailsBEAN.getStatus());
                            } else {
                                historyPOJO.setCurrent_status("Assessment Pending");
                            }
                            historyPOJO.setStudy_required(counselorDetailsBEAN.getStudyRequired());
                            historyPOJO.setWork_required(counselorDetailsBEAN.getWorkRequired());
                            historyPOJO.setMigration_required(counselorDetailsBEAN.getMigrationRequired());
                            historyPOJO.setTraining_required(counselorDetailsBEAN.getTrainingRequired());
                            forwardHistoryDAO.forwardEnquiry(historyPOJO);
                            /*========================= The End ================================ */
                        }
                    }
                    /* ====================== End Save Assessment Status ====================== */
                }
            }
        });
        btnSaveProgramRequired.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /* ====================== For Spot Admission ====================== */
                if (ENQUIRY_ID == null || ENQUIRY_ID.equalsIgnoreCase("")) {

                    if (counselorDetailsBEAN.getEnquiryMethod() == null) {
                        counselorDetailsBEAN.setEnquiryMethod("Spot Registration");
                    }
                    if (counselorDetailsBEAN.getBracnch() == null) {
                        counselorDetailsBEAN.setBracnch(CUR_BRANCH);
                    }
                    if (counselorDetailsBEAN.getEnquiryAssignedTo() == null) {
                        counselorDetailsBEAN.setEnquiryAssignedTo("Not Assigned");
                    }
                    if (validatePersonalDetails() && validateContactList(listContactNumberBEAN)) {
                        ENQUIRY_ID = "enq_spot_reg_" + UiiDGenerator.getUIID8();
                        counselorDetailsBEAN.setEnquiryID(ENQUIRY_ID);
                        int row = EnquiryDetailsDAO.insertEnquiryIntoDB(counselorDetailsBEAN);
                    }
                }
                /* ====================== Save Study ====================== */
                //     checkWhetherOneProgramSelected();
                //   if (listStudySuggestedRequired.size() <= 1 && listWorkRequired.size() <= 1 && listMigrateRequired.size() <= 1 && listTrainingRequired.size() <= 1) {

                if (validateMasterStudyRequiredDetails(listStudySuggestedRequired) <= 0 || validateMasterWorkDetails(listWorkRequired) <= 0 || validateMasterMigrateData(listMigrateRequired) <= 0 || validateMasterTrainingData(listTrainingRequired) <= 0) {
                    if (validatePersonalDetails() && validateContactList(listContactNumberBEAN)) {

                        if (validateMasterStudyRequiredDetails(listStudySuggestedRequired) <= 0) {
                            for (StudySuggestedRequiredBEAN suggestedRequiredBEAN : listStudySuggestedRequired) {
                                suggestedRequiredBEAN.setEnquiry_id(ENQUIRY_ID);
                                if (suggestedRequiredBEAN.getRowId() != null) {
                                    ProgramSuggestedRequiredDAO.updateStudyDetails(suggestedRequiredBEAN);
                                } else {
                                    ProgramSuggestedRequiredDAO.insertStudyDetails(suggestedRequiredBEAN);

                                }
                            }
                        }
                        /* ====================== Save Work ====================== */
                        if (validateMasterWorkDetails(listWorkRequired) <= 0) {
                            for (AssesmentWorkBEAN assesmentWorkBEAN : listWorkRequired) {
                                assesmentWorkBEAN.setEnquiryId(ENQUIRY_ID);
                                if (assesmentWorkBEAN.getRowId() != null) {
                                    ProgramSuggestedRequiredDAO.updateWorkDetails(assesmentWorkBEAN);
                                } else {
                                    ProgramSuggestedRequiredDAO.insertWorkDetails(assesmentWorkBEAN);
                                }
                            }
                        }
                        /* ====================== Save Migration ====================== */
                        if (validateMasterMigrateData(listMigrateRequired) <= 0) {
                            for (AssesmentMigrateBEAN assesmentMigrateBEAN : listMigrateRequired) {
                                assesmentMigrateBEAN.setEnquiryId(ENQUIRY_ID);
                                if (assesmentMigrateBEAN.getRowId() != null) {
                                    ProgramSuggestedRequiredDAO.updateMigrateDetails(assesmentMigrateBEAN);
                                } else {
                                    ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);

                                }
                            }
                        }
                        /* ====================== Save Training ====================== */

                        if (validateMasterTrainingData(listTrainingRequired) <= 0) {
                            for (AssesmentTrainingBEAN trainingBEAN : listTrainingRequired) {
                                trainingBEAN.setEnquiryId(ENQUIRY_ID);
                                if (trainingBEAN.getRowId() != null) {
                                    ProgramSuggestedRequiredDAO.updateTrainingDetails(trainingBEAN);
                                } else {
                                    ProgramSuggestedRequiredDAO.insertTrainingDetails(trainingBEAN);
                                }
                            }

                        }
                        showAlerts.showSuccess("Saved Successfully!", "Program Required Details Are Saved!", tabPaneProgramRequired);
                    }
                } else if (validatePersonalDetails() && validateContactList(listContactNumberBEAN)) {
                    Notification notification = Notifications.WARNING;
                    TrayNotification tray = new TrayNotification("Program Not Selected!", "Select atleast one program !", notification);
                    tray.setAnimation(Animations.POPUP);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            }
            //  }

        });

        btnSpouseSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean checkPersonValid = false;
                /* ====================== For Spot Admission ====================== */
                if (ENQUIRY_ID == null || ENQUIRY_ID.equalsIgnoreCase("")) {

                    if (counselorDetailsBEAN.getEnquiryMethod() == null) {
                        counselorDetailsBEAN.setEnquiryMethod("Spot Registration");
                    }
                    if (validatePersonalDetails()) {
                        ENQUIRY_ID = "enq_spot_reg_" + UiiDGenerator.getUIID8();
                        counselorDetailsBEAN.setEnquiryID(ENQUIRY_ID);
                        int row = EnquiryDetailsDAO.insertEnquiryIntoDB(counselorDetailsBEAN);
                    }
                }
                if (validatePersonalDetails()) {
                    if (validateSpouseDetails(spouseDetailsBEAN)) {
                        /* ====================== Save Spouse Details Only for migration enquiries ====================== */
                        spouseDetailsBEAN.setEnquiryId(ENQUIRY_ID);
                        if (CURRENT_TAB.equalsIgnoreCase("migration") || CURRENT_TAB.equalsIgnoreCase("study")) {
                            if (spouseDetailsBEAN.getRowId() != null) {
                                SpouseCrudDAO.updateSpouseDetails(spouseDetailsBEAN);
                            } else {
                                spouseDetailsBEAN.setEnquiryId(ENQUIRY_ID);
                                SpouseCrudDAO.insertSpouseDetails(spouseDetailsBEAN);
                            }
                        }
                        /* ====================== All Spouse Dyanmic Data ====================== */
                        if (listMigrateRequired.size() > 0) {
                            /* ====================== Qualification ====================== */
                            if (validateMasterSpouseQualification(listAssesmentSpouseTertiaryBEAN) <= 0) {
                                for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : listAssesmentSpouseTertiaryBEAN) {
                                    if (assesmentTertiaryBEAN.getRowId() != null) {
                                        SpouseCrudDAO.updateSpouseQualDetails(assesmentTertiaryBEAN);
                                    } else {
                                        assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
                                        SpouseCrudDAO.insertSpouseQualDetails(assesmentTertiaryBEAN);
                                    }
                                }
                            }
                            /* ====================== Spouse Work Experience ====================== */
                            if (validateMasterSpouseExperience(listAssesmentSpouseExpBEAN) <= 0) {
                                for (AssesmentSpouseExpBEAN assesmentSpouseExpBEAN : listAssesmentSpouseExpBEAN) {
                                    assesmentSpouseExpBEAN.setEnquiryId(ENQUIRY_ID);
                                    if (assesmentSpouseExpBEAN.getRowId() != null) {
                                        SpouseCrudDAO.updateSpouseExpDetails(assesmentSpouseExpBEAN);

                                    } else {
                                        SpouseCrudDAO.insertSpouseExpDetails(assesmentSpouseExpBEAN);
                                    }
                                }
                            }
                            /* ====================== Spouse Language Test ====================== */
                            if (validateMasterSpouseAndTerLangTest(listAssesmentLanguageTest) <= 0) {
                                for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : listAssesmentLanguageTest) {
                                    assesmentLanguageTestBEAN.setEnquiryId(ENQUIRY_ID);
                                    if (assesmentLanguageTestBEAN.getRowId() != null) {
                                        SpouseCrudDAO.updateSpouseLanguageTestDetails(assesmentLanguageTestBEAN);
                                    } else {
                                        assesmentLanguageTestBEAN.setEnquiryId(ENQUIRY_ID);
                                        SpouseCrudDAO.insertSpouseLanguageTestDetails(assesmentLanguageTestBEAN);
                                    }
                                }
                            }
                            /* ====================== Child Details ====================== */
                            if (validateMasterChildren(childBEANList) <= 0) {

                                for (AssessmentChildBEAN assessmentChildBEAN : childBEANList) {
                                    assessmentChildBEAN.setEnquiry_id(ENQUIRY_ID);
                                    if (assessmentChildBEAN.getRow_id() != null) {
                                        ChildrenDAO.updateChildrenDetails(assessmentChildBEAN);
                                    } else {
                                        ChildrenDAO.insertChildrenDetails(assessmentChildBEAN);
                                    }
                                }
                            }
                            /* ====================== Relative Details ====================== */
                            if (validateMasterRelative(listRelativeBEAN) <= 0) {
                                for (RelativeBEAN relativeBEAN : listRelativeBEAN) {
                                    relativeBEAN.setEnquiryId(ENQUIRY_ID);
                                    if (relativeBEAN.getRowId() != null) {
                                        relativeDAO.updateRelativeDetails(relativeBEAN);
                                    } else {
                                        relativeDAO.insertRelativeDetails(relativeBEAN);
                                    }
                                }
                            }
                        }
                        showAlerts.showSuccess("Saved Successfully!", "Spouse,Children And Other Details Are Saved!", spousePanel);
                    }
                }
            }
        });
        btnSaveQualiExperience.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /* ====================== For Spot Admission ====================== */
                if (ENQUIRY_ID == null || ENQUIRY_ID.equalsIgnoreCase("")) {

                    if (counselorDetailsBEAN.getEnquiryMethod() == null) {
                        counselorDetailsBEAN.setEnquiryMethod("Spot Registration");
                    }
                    if (counselorDetailsBEAN.getBracnch() == null) {
                        counselorDetailsBEAN.setBracnch(CUR_BRANCH);
                    }
                    if (counselorDetailsBEAN.getEnquiryAssignedTo() == null) {
                        counselorDetailsBEAN.setEnquiryAssignedTo("Not Assigned");
                    }
                    if (validatePersonalDetails() && validateContactList(listContactNumberBEAN) && validateSslcPlusTwo()) {
                        ENQUIRY_ID = "enq_spot_reg_" + UiiDGenerator.getUIID8();
                        counselorDetailsBEAN.setEnquiryID(ENQUIRY_ID);
                        int row = EnquiryDetailsDAO.insertEnquiryIntoDB(counselorDetailsBEAN);
                    }
                }
                boolean validation = true;
                /* ====================== Save SSLC,PLUS2, Teritary Qualification ====================== */
                //   if (listStudySuggestedRequired.size() <= 1 && listWorkRequired.size() <= 1 && listMigrateRequired.size() <= 1 && listTrainingRequired.size() <= 1) 
                //       {

                if (validatePersonalDetails() && validateContactList(listContactNumberBEAN)) {
                    if (validateSslcPlusTwo()) {

                        if (assesmentSslcBEAN.getRowId() != null) {
                            assesmentSslcBEAN.setEnquiryId(ENQUIRY_ID);
                            AssessmentSSLCDAO.updateSslcDetails(assesmentSslcBEAN);
                        } else {
                            assesmentSslcBEAN.setEnquiryId(ENQUIRY_ID);
                            AssessmentSSLCDAO.insertSslcDetails(assesmentSslcBEAN);
                        }
                        if (assesmentPlusTwoBEAN.getRowId() != null) {
                            assesmentPlusTwoBEAN.setEnquiryId(ENQUIRY_ID);
                            Assesmentplus2DAO.updatePlusTwoDetails(assesmentPlusTwoBEAN);
                        } else {
                            assesmentPlusTwoBEAN.setEnquiryId(ENQUIRY_ID);
                            Assesmentplus2DAO.insertPlusTwoDetails(assesmentPlusTwoBEAN);
                        }
                    } else {
                        validation = false;
                    }

                    if (validateMasterTertiaryQualifications(assesmentTertiaryBEANList) <= 0) {
                        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : assesmentTertiaryBEANList) {
                            assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
                            if (assesmentTertiaryBEAN.getRowId() != null) {
                                QualificationsCrudDAO.updateTeritaryQualifications(assesmentTertiaryBEAN);
                            } else {
                                QualificationsCrudDAO.insertTeritaryQualifications(assesmentTertiaryBEAN);
                            }
                        }
                    } else {
                        validation = false;
                    }

                    /* ====================== End SSLC,PLUS2, Teritary Qualification ====================== */
 /* ====================== Save Work Experience ====================== */
                    if (validateMasterWorkexperience(listWorkExpBEAN) <= 0) {
                        for (WorktExperienceBEAN experienceBEAN : listWorkExpBEAN) {
                            experienceBEAN.setEnquiryId(ENQUIRY_ID);
                            if (experienceBEAN.getRowId() != null) {
                                WorkExperienceDAO.updateWorkExperience(experienceBEAN);
                            } else {
                                WorkExperienceDAO.insertWorkExperience(ENQUIRY_ID, experienceBEAN);
                            }
                        }
                    } else {
                        validation = false;
                    }

                    /* ====================== End Work Experience ====================== */
 /* ====================== Save Language Test ====================== */
                    if (validateMasterLanguageTest(listLanguageTestBEAN) <= 0) {
                        for (AssesmentLanguageTestBEAN languageTestBEAN : listLanguageTestBEAN) {
                            languageTestBEAN.setEnquiryId(ENQUIRY_ID);
                            if (languageTestBEAN.getRowId() != null) {
                                LanguageTestCrudDAO.updateLanguageTestDetails(ENQUIRY_ID, languageTestBEAN);
                            } else {
                                LanguageTestCrudDAO.insertLanguageTestDetails(languageTestBEAN);
                            }
                        }
                    } else {
                        validation = false;
                    }

                    /* ====================== End Language Test ====================== */
                    if (listStudySuggestedRequired.size() > 0) {
                        /* ====================== Admission Test ====================== */
                        if (validateAdmissionTest(listAdmissionTestBEAN) <= 0) {
                            for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEAN) {
                                admissionTestBEAN.setEnquiryId(ENQUIRY_ID);
                                if (admissionTestBEAN.getRowId() != null) {
                                    admissionTestDAO.updateAdmissionTest(admissionTestBEAN);
                                } else {
                                    admissionTestDAO.insertAdmissionTest(admissionTestBEAN);

                                }
                            }
                        } else {
                            validation = false;
                        }
                    }
                    if (listWorkRequired.size() > 0) {
                        /* ====================== Language Skill ====================== */
                        if (validateLanguageSkill(languageSkillsBEANs) <= 0) {
                            for (LanguageSkillsBEAN languageSkillsBEAN : languageSkillsBEANs) {
                                languageSkillsBEAN.setEnquiryId(ENQUIRY_ID);
                                if (languageSkillsBEAN.getRowId() != null) {
                                    languageSkillsDAO.updateLanguageSkills(languageSkillsBEAN);
                                } else {
                                    languageSkillsDAO.insertLanguageSkills(languageSkillsBEAN);
                                }
                            }

                        } else {
                            System.out.println("validation = false  INSIDE ELSE CASE OF validateLanguageSkill");
                            validation = false;
                        }
                        /* ====================== Technical Skill ====================== */
                        if (validateTechnicalSkills(listTechnicalSkillsBEAN) <= 0) {
                            for (TechnicalSkillsBEAN technicalSkillsBEAN : listTechnicalSkillsBEAN) {
                                technicalSkillsBEAN.setEnquiryId(ENQUIRY_ID);
                                if (technicalSkillsBEAN.getRowId() != null) {
                                    technicalSkillsDAO.updateTechnicalSkills(technicalSkillsBEAN);
                                } else {
                                    technicalSkillsDAO.insertTechnicalSkills(technicalSkillsBEAN);
                                }
                            }

                        } else {
                            validation = false;
                        }
                        /* ====================== Other Skill ====================== */
                        if (validateOtherSkills(listOtherSkillBEAN) <= 0) {
                            for (OtherSkillBEAN otherSkillBEAN : listOtherSkillBEAN) {
                                otherSkillBEAN.setEnquiryId(ENQUIRY_ID);
                                if (otherSkillBEAN.getRowId() != null) {
                                    otherSkillsDAO.updateOtherSkills(otherSkillBEAN);
                                } else {
                                    otherSkillsDAO.insertOtherSkills(otherSkillBEAN);
                                }
                            }
                        } else {
                            //   showAlerts.showSuccess("Saved Successfully!", "Qualification,Work Experience And Other Details Are Saved!", spousePanel);
                            validation = false;
                        }
                    }

                    if (validateSslcPlusTwo()) {

                        checkWhetherOneProgramSelected();
                        showAlerts.showSuccess("Saved Successfully!", "Qualification,Work Experience And Other Details Are Saved!", sslcBoardCombo);
                    }
                }
            }
        }
        );

    }

    /**
     *
     * @param tab
     */
    public void selectTab(String tab) {
        switch (tab) {
            case "study":
                tabPaneProgramRequired.getSelectionModel().select(0);
                break;
            case "work":
                tabPaneProgramRequired.getSelectionModel().select(1);
                break;
            case "migration":
                tabPaneProgramRequired.getSelectionModel().select(2);
                break;
            case "training":
                tabPaneProgramRequired.getSelectionModel().select(3);
                break;
            default:
                System.out.println("not workinggg");

        }
    }

    /**
     *
     */
    public void saveAllAssessmentDetails() {
        btnAssessmentSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validatePersonalDetails() && validateContactList(listContactNumberBEAN) && validateSslcPlusTwo() && validateAssessmentStatus()) {
                    /* ====================== Save Personal Details ====================== */
                    if (counselorDetailsBEAN.getRowId() != null) {
                        PersonalCrudDAO.updatePersonalDetails(counselorDetailsBEAN);
                    } else {
                        PersonalCrudDAO.insertPersonalDetails(counselorDetailsBEAN);
                    }
                    /* ====================== End Save Personal Details ====================== */
 /* ====================== Save Contact Details ====================== */
                    if (validateContactList(listContactNumberBEAN)) {
                        for (ContactNumberBEAN numberBEAN : listContactNumberBEAN) {
                            if (numberBEAN.getRowId() != null) {
                                contactNumbersDAO.updateContactNumber(numberBEAN);
                            } else {
                                numberBEAN.setEnquiryId(ENQUIRY_ID);
                                contactNumbersDAO.insertContactNumber(numberBEAN);
                            }
                            /* ====================== Remove Not Existing ====================== */
                        }
                    }

                    /* ====================== End Save Contact Details ====================== */
 /* ====================== Save Program Required Details ====================== */
 /* ====================== Save Study ====================== */
                    if (validateMasterStudyRequiredDetails(listStudySuggestedRequired) <= 0) {
                        for (StudySuggestedRequiredBEAN suggestedRequiredBEAN : listStudySuggestedRequired) {
                            suggestedRequiredBEAN.setEnquiry_id(ENQUIRY_ID);
                            if (suggestedRequiredBEAN.getRowId() != null) {
                                ProgramSuggestedRequiredDAO.updateStudyDetails(suggestedRequiredBEAN);
                            } else {
                                ProgramSuggestedRequiredDAO.insertStudyDetails(suggestedRequiredBEAN);

                            }
                        }
                    }
                    /* ====================== Save Work ====================== */
                    if (validateMasterWorkDetails(listWorkRequired) <= 0) {
                        for (AssesmentWorkBEAN assesmentWorkBEAN : listWorkRequired) {
                            assesmentWorkBEAN.setEnquiryId(ENQUIRY_ID);
                            if (assesmentWorkBEAN.getRowId() != null) {
                                ProgramSuggestedRequiredDAO.updateWorkDetails(assesmentWorkBEAN);
                            } else {
                                ProgramSuggestedRequiredDAO.insertWorkDetails(assesmentWorkBEAN);
                            }
                        }
                    }
                    /* ====================== Save Migration ====================== */
                    if (validateMasterMigrateData(listMigrateRequired) <= 0) {
                        for (AssesmentMigrateBEAN assesmentMigrateBEAN : listMigrateRequired) {
                            assesmentMigrateBEAN.setEnquiryId(ENQUIRY_ID);
                            if (assesmentMigrateBEAN.getRowId() != null) {
                                ProgramSuggestedRequiredDAO.updateMigrateDetails(assesmentMigrateBEAN);
                            } else {
                                ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);

                            }
                        }
                    }
                    /* ====================== Save Training ====================== */

                    if (validateMasterTrainingData(listTrainingRequired) <= 0) {
                        for (AssesmentTrainingBEAN trainingBEAN : listTrainingRequired) {
                            trainingBEAN.setEnquiryId(ENQUIRY_ID);
                            if (trainingBEAN.getRowId() != null) {
                                ProgramSuggestedRequiredDAO.updateTrainingDetails(trainingBEAN);
                            } else {
                                ProgramSuggestedRequiredDAO.insertTrainingDetails(trainingBEAN);
                            }
                        }

                    }
                    /* ====================== End Save Program Required Details ====================== */
 /* ====================== Save SSLC,PLUS2, Teritary Qualification ====================== */
                    if (validateSslcPlusTwo()) {
                        if (assesmentSslcBEAN.getRowId() != null) {
                            AssessmentSSLCDAO.updateSslcDetails(assesmentSslcBEAN);
                        } else {
                            assesmentSslcBEAN.setEnquiryId(ENQUIRY_ID);
                            AssessmentSSLCDAO.insertSslcDetails(assesmentSslcBEAN);
                        }
                        if (assesmentPlusTwoBEAN.getRowId() != null) {
                            Assesmentplus2DAO.updatePlusTwoDetails(assesmentPlusTwoBEAN);
                        } else {
                            assesmentPlusTwoBEAN.setEnquiryId(ENQUIRY_ID);
                            Assesmentplus2DAO.insertPlusTwoDetails(assesmentPlusTwoBEAN);
                        }
                    }
                    if (validateMasterTertiaryQualifications(assesmentTertiaryBEANList) <= 0) {
                        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : assesmentTertiaryBEANList) {
                            assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
                            if (assesmentTertiaryBEAN.getRowId() != null) {
                                QualificationsCrudDAO.updateTeritaryQualifications(assesmentTertiaryBEAN);
                            } else {
                                QualificationsCrudDAO.insertTeritaryQualifications(assesmentTertiaryBEAN);
                            }
                        }
                    }

                    /* ====================== End SSLC,PLUS2, Teritary Qualification ====================== */
 /* ====================== Save Work Experience ====================== */
                    if (validateMasterWorkexperience(listWorkExpBEAN) <= 0) {
                        for (WorktExperienceBEAN experienceBEAN : listWorkExpBEAN) {
                            experienceBEAN.setEnquiryId(ENQUIRY_ID);
                            if (experienceBEAN.getRowId() != null) {
                                WorkExperienceDAO.updateWorkExperience(experienceBEAN);
                            } else {
                                WorkExperienceDAO.insertWorkExperience(ENQUIRY_ID, experienceBEAN);
                            }
                        }
                    }

                    /* ====================== End Work Experience ====================== */
 /* ====================== Save Assessment Status ====================== */
                    assessmentStatusBEAN.setPriority("" + PRIORITY_RATING);
                    if (assessmentStatusBEAN.getStatusId() != null) {
                        assessmentStatusDAO.updateAssessmentStatus(assessmentStatusBEAN);
                    } else {
                        assessmentStatusBEAN.setEnquiryId(ENQUIRY_ID);
                        assessmentStatusDAO.insertAssessmentStatus(assessmentStatusBEAN);
                    }

                    /* ====================== End Save Assessment Status ====================== */
 /* ====================== Save Language Test Details ====================== */
                    if (validateMasterSpouseAndTerLangTest(listLanguageTestBEAN) <= 0) {
                        for (AssesmentLanguageTestBEAN languageTestBEAN : listLanguageTestBEAN) {
                            languageTestBEAN.setEnquiryId(ENQUIRY_ID);
                            if (languageTestBEAN.getRowId() != null) {
                                LanguageTestCrudDAO.updateLanguageTestDetails(ENQUIRY_ID, languageTestBEAN);
                            } else {
                                LanguageTestCrudDAO.insertLanguageTestDetails(languageTestBEAN);
                            }
                        }
                    }
                    /* ====================== End Language Test Details ====================== */

 /* ============================================ */ /* ============================================ */
 /* ====================== Save Data Required Only for particular tab ====================== */
 /* ============================================ */ /* ============================================ */

                    if (listStudySuggestedRequired.size() > 0) {
                        /* ====================== Admission Test ====================== */
                        if (validateAdmissionTest(listAdmissionTestBEAN) <= 0) {
                            for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEAN) {
                                admissionTestBEAN.setEnquiryId(ENQUIRY_ID);
                                if (admissionTestBEAN.getRowId() != null) {
                                    admissionTestDAO.updateAdmissionTest(admissionTestBEAN);
                                } else {
                                    admissionTestDAO.insertAdmissionTest(admissionTestBEAN);

                                }
                            }
                        }

                    }
                    if (listWorkRequired.size() > 0) {
                        /* ====================== Language Skill ====================== */
                        if (validateLanguageSkill(languageSkillsBEANs) <= 0) {
                            for (LanguageSkillsBEAN languageSkillsBEAN : languageSkillsBEANs) {
                                languageSkillsBEAN.setEnquiryId(ENQUIRY_ID);
                                if (languageSkillsBEAN.getRowId() != null) {
                                    languageSkillsDAO.updateLanguageSkills(languageSkillsBEAN);
                                } else {
                                    languageSkillsDAO.insertLanguageSkills(languageSkillsBEAN);
                                }
                            }

                        }
                        /* ====================== Technical Skill ====================== */
                        if (validateTechnicalSkills(listTechnicalSkillsBEAN) <= 0) {
                            for (TechnicalSkillsBEAN technicalSkillsBEAN : listTechnicalSkillsBEAN) {
                                technicalSkillsBEAN.setEnquiryId(ENQUIRY_ID);
                                if (technicalSkillsBEAN.getRowId() != null) {
                                    technicalSkillsDAO.updateTechnicalSkills(technicalSkillsBEAN);
                                } else {
                                    technicalSkillsDAO.insertTechnicalSkills(technicalSkillsBEAN);

                                }
                            }

                        }
                        /* ====================== Other Skill ====================== */
                        if (validateOtherSkills(listOtherSkillBEAN) <= 0) {
                            for (OtherSkillBEAN otherSkillBEAN : listOtherSkillBEAN) {
                                otherSkillBEAN.setEnquiryId(ENQUIRY_ID);
                                if (otherSkillBEAN.getRowId() != null) {
                                    otherSkillsDAO.updateOtherSkills(otherSkillBEAN);
                                } else {
                                    otherSkillsDAO.insertOtherSkills(otherSkillBEAN);
                                }
                            }
                        }
                    }
                    /* ====================== Save Spouse Details Only for migration enquiries ====================== */
                    spouseDetailsBEAN.setEnquiryId(ENQUIRY_ID);
                    if (CURRENT_TAB.equalsIgnoreCase("migration")) {
                        if (spouseDetailsBEAN.getRowId() != null) {
                            SpouseCrudDAO.updateSpouseDetails(spouseDetailsBEAN);
                        } else {
                            SpouseCrudDAO.insertSpouseDetails(spouseDetailsBEAN);
                        }
                    }

                    /* ====================== End Save Spouse Details ====================== */
                    if (listMigrateRequired.size() > 0) {
                        /* ====================== All Spouse Dyanmic Data ====================== */
 /* ====================== Qualification ====================== */
                        if (validateMasterSpouseQualification(listAssesmentSpouseTertiaryBEAN) <= 0) {
                            for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : listAssesmentSpouseTertiaryBEAN) {
                                assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
                                if (assesmentTertiaryBEAN.getRowId() != null) {
                                    SpouseCrudDAO.updateSpouseQualDetails(assesmentTertiaryBEAN);
                                } else {
                                    SpouseCrudDAO.insertSpouseQualDetails(assesmentTertiaryBEAN);
                                }
                            }
                        }
                        /* ====================== Spouse Work Experience ====================== */
                        if (validateMasterSpouseExperience(listAssesmentSpouseExpBEAN) <= 0) {
                            for (AssesmentSpouseExpBEAN assesmentSpouseExpBEAN : listAssesmentSpouseExpBEAN) {
                                assesmentSpouseExpBEAN.setEnquiryId(ENQUIRY_ID);
                                if (assesmentSpouseExpBEAN.getRowId() != null) {
                                    SpouseCrudDAO.updateSpouseExpDetails(assesmentSpouseExpBEAN);

                                } else {
                                    SpouseCrudDAO.insertSpouseExpDetails(assesmentSpouseExpBEAN);
                                }
                            }
                        }
                        /* ====================== Spouse Language Test ====================== */
                        if (validateMasterSpouseAndTerLangTest(listAssesmentLanguageTest) <= 0) {
                            for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : listAssesmentLanguageTest) {
                                assesmentLanguageTestBEAN.setEnquiryId(ENQUIRY_ID);
                                if (assesmentLanguageTestBEAN.getRowId() != null) {
                                    SpouseCrudDAO.updateSpouseLanguageTestDetails(assesmentLanguageTestBEAN);
                                } else {
                                    SpouseCrudDAO.insertSpouseLanguageTestDetails(assesmentLanguageTestBEAN);
                                }
                            }
                        }
                        /* ====================== Child Details ====================== */
                        if (validateMasterChildren(childBEANList) <= 0) {

                            for (AssessmentChildBEAN assessmentChildBEAN : childBEANList) {
                                assessmentChildBEAN.setEnquiry_id(ENQUIRY_ID);
                                if (assessmentChildBEAN.getRow_id() != null) {
                                    ChildrenDAO.updateChildrenDetails(assessmentChildBEAN);
                                } else {
                                    ChildrenDAO.insertChildrenDetails(assessmentChildBEAN);
                                }
                            }
                        }
                        /* ====================== Relative Details ====================== */
                        if (validateMasterRelative(listRelativeBEAN) <= 0) {
                            for (RelativeBEAN relativeBEAN : listRelativeBEAN) {
                                relativeBEAN.setEnquiryId(ENQUIRY_ID);

                                if (relativeBEAN.getRowId() != null) {
                                    relativeDAO.updateRelativeDetails(relativeBEAN);
                                } else {
                                    relativeDAO.insertRelativeDetails(relativeBEAN);
                                }
                            }
                        }
                    }
                    if (listTrainingRequired.size() > 0) {
                        /* ====================== Language Skill ====================== */
                        if (validateLanguageSkill(languageSkillsBEANs) <= 0) {
                            for (LanguageSkillsBEAN languageSkillsBEAN : languageSkillsBEANs) {
                                languageSkillsBEAN.setEnquiryId(ENQUIRY_ID);
                                if (languageSkillsBEAN.getRowId() != null) {
                                    languageSkillsDAO.updateLanguageSkills(languageSkillsBEAN);
                                } else {
                                    languageSkillsDAO.insertLanguageSkills(languageSkillsBEAN);
                                }
                            }

                        }
                        /* ====================== Admission Test ====================== */
                        if (validateAdmissionTest(listAdmissionTestBEAN) <= 0) {
                            for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEAN) {
                                admissionTestBEAN.setEnquiryId(ENQUIRY_ID);
                                if (admissionTestBEAN.getRowId() != null) {
                                    admissionTestDAO.updateAdmissionTest(admissionTestBEAN);
                                } else {
                                    admissionTestDAO.insertAdmissionTest(admissionTestBEAN);

                                }
                            }
                        }
                    }
                    /* ====================== Save Readflag,Updated Date,Completion Flag ====================== */
                    AssesmentDAO.setReadFlag(counselorDetailsBEAN.getEnquiryID());
                    AssesmentDAO.updateModifiedDate(ENQUIRY_ID);
                    AssesmentDAO.setCompletionFlag(ENQUIRY_ID, assessmentStatusBEAN.getAssessmentStatus());
                    /* ====================== Show The Progress Indicator ====================== */
                    showProgress.showProgressIndicator(cmbAge, "Saved");
                    /*========================= Insert History Details of counselor assign ================================ */
                    ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                    historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                    historyPOJO.setAssigned_branch(CUR_BRANCH);
                    historyPOJO.setAssigned_by(CUR_USERNAME);
                    historyPOJO.setAssigned_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());
                    historyPOJO.setEnquiry_id(counselorDetailsBEAN.getEnquiryID());
                    historyPOJO.setAssigned_to(counselorDetailsBEAN.getEnquiryAssignedTo());
                    historyPOJO.setAssigned_to_branch(counselorDetailsBEAN.getBracnch());
                    historyPOJO.setHolded_by(CURR_COUNSELOR);
                    historyPOJO.setRemarks(counselorDetailsBEAN.getRemarks());
                    if (counselorDetailsBEAN.getStatus() != null) {
                        historyPOJO.setCurrent_status(counselorDetailsBEAN.getStatus());
                    } else {
                        historyPOJO.setCurrent_status("Assessment Pending");
                    }
                    historyPOJO.setStudy_required(counselorDetailsBEAN.getStudyRequired());
                    historyPOJO.setWork_required(counselorDetailsBEAN.getWorkRequired());
                    historyPOJO.setMigration_required(counselorDetailsBEAN.getMigrationRequired());
                    historyPOJO.setTraining_required(counselorDetailsBEAN.getTrainingRequired());
                    forwardHistoryDAO.forwardEnquiry(historyPOJO);
                    /*========================= The End ================================ */
                }
            }
        });
    }

    /**
     *
     */
    public void showHideFeatures() {
        tabPaneProgramRequired.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null) {
                    switch (newValue.intValue()) {
                        case 0:
                            showHideFeatures("training", true);
                            showHideFeatures("migration", true);
                            showHideFeatures("work", true);
                            showHideFeatures("study", false);
                            lblAssesmentTitle.setText("Registration Form - Study");
                            Context.getInstance().currentProfile().setDocumentVerifyDisplay("study");
                            break;
                        case 1:
                            showHideFeatures("study", true);
                            showHideFeatures("training", true);
                            showHideFeatures("migration", true);
                            showHideFeatures("work", false);
                            lblAssesmentTitle.setText("Registration Form - Work");
                            tabPaneProgramRequired.getSelectionModel().select(1);
                            Context.getInstance().currentProfile().setDocumentVerifyDisplay("work");

                            break;

                        case 2:
                            showHideFeatures("study", true);
                            showHideFeatures("training", true);
                            showHideFeatures("work", true);
                            showHideFeatures("migration", false);
                            lblAssesmentTitle.setText("Registration Form - Migration");
                            Context.getInstance().currentProfile().setDocumentVerifyDisplay("migration");
                            break;
                        case 3:
                            showHideFeatures("study", true);
                            showHideFeatures("work", true);
                            showHideFeatures("migration", true);
                            showHideFeatures("training", false);
                            lblAssesmentTitle.setText("Registration Form - Training");
                            tabPaneProgramRequired.getSelectionModel().select(3);
                            Context.getInstance().currentProfile().setDocumentVerifyDisplay("training");
                            break;
                        default:
                            System.out.println("Not working switching tab");

                    }
                    tabPaneProgramRequired.autosize();

                }
            }
        });
    }

    /**
     *
     * @param tab
     * @param hideShow
     */
    public void showHideFeatures(String tab, boolean hideShow) {
        switch (tab) {
            case "study":
                lblLanguageSkills.setVisible(hideShow);
                lblLanguageSkills.setManaged(hideShow);
                gridLanguageSkills.setVisible(hideShow);
                gridLanguageSkills.setManaged(hideShow);

                lblTechnicalSkills.setVisible(hideShow);
                lblTechnicalSkills.setManaged(hideShow);
                gridTechnicalDetails.setVisible(hideShow);
                gridTechnicalDetails.setManaged(hideShow);

                lblOtherSkills.setVisible(hideShow);
                lblOtherSkills.setManaged(hideShow);
                gridOtherSkills.setVisible(hideShow);
                gridOtherSkills.setManaged(hideShow);

//                spousePanel.setVisible(hideShow);
//                spousePanel.setManaged(hideShow);
                break;
            case "work":
                lblAdmissionTest.setVisible(hideShow);
                lblAdmissionTest.setManaged(hideShow);
                gridAdmissionTest.setVisible(hideShow);
                gridAdmissionTest.setManaged(hideShow);

                spousePanel.setVisible(hideShow);
                spousePanel.setManaged(hideShow);
                break;
            case "migration":
                lblLanguageSkills.setVisible(hideShow);
                lblLanguageSkills.setManaged(hideShow);
                gridLanguageSkills.setVisible(hideShow);
                gridLanguageSkills.setManaged(hideShow);

                lblTechnicalSkills.setVisible(hideShow);
                lblTechnicalSkills.setManaged(hideShow);
                gridTechnicalDetails.setVisible(hideShow);
                gridTechnicalDetails.setManaged(hideShow);

                lblOtherSkills.setVisible(hideShow);
                lblOtherSkills.setManaged(hideShow);
                gridOtherSkills.setVisible(hideShow);
                gridOtherSkills.setManaged(hideShow);

                lblAdmissionTest.setVisible(hideShow);
                lblAdmissionTest.setManaged(hideShow);
                gridAdmissionTest.setVisible(hideShow);
                gridAdmissionTest.setManaged(hideShow);
                break;
            case "training":
                lblTechnicalSkills.setVisible(hideShow);
                lblTechnicalSkills.setManaged(hideShow);
                gridTechnicalDetails.setVisible(hideShow);
                gridTechnicalDetails.setManaged(hideShow);

                lblOtherSkills.setVisible(hideShow);
                lblOtherSkills.setManaged(hideShow);
                gridOtherSkills.setVisible(hideShow);
                gridOtherSkills.setManaged(hideShow);

                spousePanel.setVisible(hideShow);
                spousePanel.setManaged(hideShow);
                break;
            default:
                System.out.println("Not Working Hiding feature");
        }

    }

    /**
     *
     */
    public void showPreview() {

        btnAssessmentPreview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //  if (event.getClickCount() == 2) {
                try {
                    // CounselorDetailsBEAN counselorDetailsBEAN = (CounselorDetailsBEAN) tblPrimaryInbox.getSelectionModel().getSelectedItem();
                    Context.getInstance().currentProfile().setCounselorDetailsBEAN(counselorDetailsBEAN);
                    StackPane secondaryLayout = new StackPane();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/registration/preview/FXMLRegistrationPreview.fxml"));
                    Parent root = (Parent) loader.load();
                    secondaryLayout.getChildren().add(root);
                    Scene secondScene = new Scene(secondaryLayout);
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Registration Preview Of " + counselorDetailsBEAN.getContactName());
                    secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    secondStage.setScene(secondScene);
                    secondStage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    // close event
                    secondStage.setOnHidden(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            if (Context.getInstance().currentProfile().getPreviewCurrentEdit() != null) {
                                switch (Context.getInstance().currentProfile().getPreviewCurrentEdit()) {
                                    case "study":
                                    case "migration":
                                    case "work":
                                    case "training":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, tabPaneProgramRequired);
                                        selectTab(Context.getInstance().currentProfile().getPreviewCurrentEdit());
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "person":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, txtName);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "qualifications":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, anchorQualification);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "work_exp":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, gridWorkExp);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "lang_skill":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, gridLanguageSkills);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "lang_test":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, languageTestGrid);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "other_skill":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, gridOtherSkills);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "tech_skill":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, gridTechnicalDetails);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "adm_test":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, gridAdmissionTest);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "spouse_child":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, gridChildren);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "spouse_lang":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, spouseLangTestGrid);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "spouse_exp":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, spouseWorkExpGrid);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "spouse_quali":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, spouseQualGrid);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    case "spouse_details":
                                        ScrollPaneFocus.requestFocus(scrollPaneMain, cmbSpouseAge);
                                        Context.getInstance().currentProfile().setPreviewCurrentEdit("");
                                        break;
                                    default:
                                        System.out.println("Not for editing");

                                }
                            }
                        }
                    });
                    //        secondStage.initStyle(StageStyle.UNDECORATED);
                    secondStage.setMaximized(true);
                    secondStage.initModality(Modality.APPLICATION_MODAL);
                    secondStage.initOwner(btnAssessmentPreview.getScene().getWindow());
                    secondStage.showAndWait();
                } catch (IOException ex) {
                    logger.fatal(ex.getStackTrace());
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     *
     */
    public void initDynamicAdmissiontest() {
        listAdmissionTestBEAN = admissionTestDAO.retrieveAdmissionTest(ENQUIRY_ID);
        if (listAdmissionTestBEAN.size() > 0) {
            addAdmissionTestGridRows();
        } else {
            AdmissionTestBEAN emptyAdmissionTestBEAN = new AdmissionTestBEAN();
            listAdmissionTestBEAN.add(emptyAdmissionTestBEAN);
            addAdmissionTestGridRows();
        }
    }

    /**
     *
     */
    public void addAdmissionTestGridRows() {
        gridAdmissionTest.getChildren().clear();
        for (AdmissionTestBEAN admissionTestBEAN : listAdmissionTestBEAN) {
            admissionTestBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbAdmTest = new ComboBox(masterAdmissionTestDAO.retrieveAdmissionTest());
            cmbAdmTest.setPromptText("Select");

            ComboBox cmbSpouseReading = new ComboBox(testScore);
//          cmbSpouseReading.setMinWidth(145);
            cmbSpouseReading.setPromptText("Reading");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseReading);
            cmbSpouseReading.getStyleClass().add("button-service");

            ComboBox cmbSpouseWriting = new ComboBox(testScore);
//            cmbSpouseWriting.setMinWidth(145);
            cmbSpouseWriting.getStyleClass().add("button-service");
            cmbSpouseWriting.setPromptText("Writing");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseWriting);

            ComboBox cmbSpouseSpeaking = new ComboBox(testScore);
//            cmbSpouseSpeaking.setMinWidth(145);
            cmbSpouseSpeaking.getStyleClass().add("button-service");
            cmbSpouseSpeaking.setPromptText("Speaking");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseSpeaking);

            ComboBox cmbSpouseListening = new ComboBox(testScore);
//            cmbSpouseListening.setMinWidth(145);
            cmbSpouseListening.getStyleClass().add("button-service");
            cmbSpouseListening.setPromptText("Listening");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseListening);
            ComboBox cmbOverAlll = new ComboBox(testScore);
            cmbOverAlll.setPromptText("Select");

            GridPane.setHgrow(cmbSpouseReading, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseWriting, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseSpeaking, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpouseListening, Priority.ALWAYS);
            GridPane.setHgrow(cmbAdmTest, Priority.ALWAYS);
            GridPane.setHgrow(cmbOverAlll, Priority.ALWAYS);

            ComboBoxJumpToChar.jumpToChar(cmbAdmTest);
            ComboBoxJumpToChar.jumpToChar(cmbOverAlll);

            cmbAdmTest.setMaxWidth(Double.MAX_VALUE);
            cmbOverAlll.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseReading.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseWriting.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseSpeaking.setMaxWidth(Double.MAX_VALUE);
            cmbSpouseListening.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateAdmissionTest(listAdmissionTestBEAN) <= 0) {
                        admissionTestBEAN.setEnquiryId(ENQUIRY_ID);
                        if (admissionTestBEAN.getRowId() != null) {
                            admissionTestDAO.updateAdmissionTest(admissionTestBEAN);
                        } else {
                            admissionTestDAO.insertAdmissionTest(admissionTestBEAN);

                        }
                        AdmissionTestBEAN emptyAdmissionTestBEAN = new AdmissionTestBEAN();
//                      List<LanguageSkillsBEAN> listEmptySkillsBEANs = new ArrayList<>();
                        listAdmissionTestBEAN.add(emptyAdmissionTestBEAN);
                        addAdmissionTestGridRows();
                    } else {
                        showAlerts.showError("All Fields Required In Admission Test!", gridAdmissionTest);
                    }
                    gridAdmissionTest.requestFocus();

                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (admissionTestBEAN.getRowId() != null) {
                            admissionTestDAO.deleteAdmissionTest(admissionTestBEAN.getRowId());
                        }
                        listAdmissionTestBEAN.remove(admissionTestBEAN);
                        if (listAdmissionTestBEAN.size() <= 0) {
                            AdmissionTestBEAN emptyBEAN = new AdmissionTestBEAN();
                            listAdmissionTestBEAN.add(emptyBEAN);
                        }
                        addAdmissionTestGridRows();
                    }
                    gridAdmissionTest.requestFocus();

                }
            });

            /* ====================== Close and Plus Button Events ====================== */
            if (listAdmissionTestBEAN.indexOf(admissionTestBEAN) == 0) {
                gridAdmissionTest.add(new Label("Admission Test"), 0, 0);
                gridAdmissionTest.add(new Label("Reading"), 1, 0);
                gridAdmissionTest.add(new Label("Writing"), 2, 0);
                gridAdmissionTest.add(new Label("Speaking"), 3, 0);
                gridAdmissionTest.add(new Label("Listening"), 4, 0);
                gridAdmissionTest.add(new Label("Overall Score"), 5, 0);

            }

            Bindings.bindBidirectional(cmbAdmTest.valueProperty(), admissionTestBEAN.testProperty());
            Bindings.bindBidirectional(cmbOverAlll.valueProperty(), admissionTestBEAN.overAllScoreProperty());
            Bindings.bindBidirectional(cmbSpouseReading.valueProperty(), admissionTestBEAN.readingProperty());
            Bindings.bindBidirectional(cmbSpouseWriting.valueProperty(), admissionTestBEAN.writingProperty());
            Bindings.bindBidirectional(cmbSpouseSpeaking.valueProperty(), admissionTestBEAN.speakingProperty());
            Bindings.bindBidirectional(cmbSpouseListening.valueProperty(), admissionTestBEAN.listeningProperty());

            gridAdmissionTest.add(cmbAdmTest, 0, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);
            gridAdmissionTest.add(cmbSpouseReading, 1, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);
            gridAdmissionTest.add(cmbSpouseWriting, 2, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);
            gridAdmissionTest.add(cmbSpouseSpeaking, 3, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);
            gridAdmissionTest.add(cmbSpouseListening, 4, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);
            gridAdmissionTest.add(cmbOverAlll, 5, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridAdmissionTest.add(hBox, 6, listAdmissionTestBEAN.indexOf(admissionTestBEAN) + 1);

        }
    }

    /**
     *
     */
    public void initDynamicOtherSkills() {
        listOtherSkillBEAN = otherSkillsDAO.retrieveOtherSkills(ENQUIRY_ID);
        if (listOtherSkillBEAN.size() > 0) {
            addOtherSkillsGridRows();
        } else {
            OtherSkillBEAN emptyOtherSkillBEAN = new OtherSkillBEAN();
            listOtherSkillBEAN.add(emptyOtherSkillBEAN);
            addOtherSkillsGridRows();
        }
    }

    /**
     *
     */
    public void addOtherSkillsGridRows() {
        gridOtherSkills.getChildren().clear();
        for (OtherSkillBEAN otherSkillBEAN : listOtherSkillBEAN) {
            otherSkillBEAN.setEnquiryId(ENQUIRY_ID);

            ComboBox cmbOtheSkill = new ComboBox(masterOtherSkillsDAO.retrieveOtherSkills());
            ComboBox cmbType = new ComboBox(masterOtherSkillsDAO.retrieveOtherSkillsType());
            GridPane.setHgrow(cmbOtheSkill, Priority.ALWAYS);
            GridPane.setHgrow(cmbType, Priority.ALWAYS);

            cmbOtheSkill.setMaxWidth(Double.MAX_VALUE);
            cmbType.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateOtherSkills(listOtherSkillBEAN) <= 0) {
                        otherSkillBEAN.setEnquiryId(ENQUIRY_ID);
                        if (otherSkillBEAN.getRowId() != null) {
                            otherSkillsDAO.updateOtherSkills(otherSkillBEAN);
                        } else {
                            otherSkillsDAO.insertOtherSkills(otherSkillBEAN);

                        }
                        OtherSkillBEAN emptySkillsBEAN = new OtherSkillBEAN();
//                      List<LanguageSkillsBEAN> listEmptySkillsBEANs = new ArrayList<>();
                        listOtherSkillBEAN.add(emptySkillsBEAN);
                        addOtherSkillsGridRows();
                        gridOtherSkills.requestFocus();
                    } else {
                        showAlerts.showError("All Fields Required In Other Skills!", gridOtherSkills);
                    }
                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (otherSkillBEAN.getRowId() != null) {
                            otherSkillsDAO.deleteOtherSkills(otherSkillBEAN.getRowId());
                        }
                        listOtherSkillBEAN.remove(otherSkillBEAN);
                        if (listOtherSkillBEAN.size() <= 0) {
                            OtherSkillBEAN emptyBEAN = new OtherSkillBEAN();
                            listOtherSkillBEAN.add(emptyBEAN);
                        }
                        addOtherSkillsGridRows();
                        gridOtherSkills.requestFocus();
                    }
                }
            });

            /* ====================== Close and Plus Button Events ====================== */
            if (listOtherSkillBEAN.indexOf(otherSkillBEAN) == 0) {
                gridOtherSkills.add(new Label("Skill"), 0, 0);
                gridOtherSkills.add(new Label("Type"), 1, 0);

            }

            Bindings.bindBidirectional(cmbOtheSkill.valueProperty(), otherSkillBEAN.otherSkillProperty());
            Bindings.bindBidirectional(cmbType.valueProperty(), otherSkillBEAN.categoryProperty());

            gridOtherSkills.add(cmbOtheSkill, 0, listOtherSkillBEAN.indexOf(otherSkillBEAN) + 1);
            gridOtherSkills.add(cmbType, 1, listOtherSkillBEAN.indexOf(otherSkillBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridOtherSkills.add(hBox, 2, listOtherSkillBEAN.indexOf(otherSkillBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initDynamicaTechSkills() {
        listTechnicalSkillsBEAN = technicalSkillsDAO.retrieveTechnicalSkills(ENQUIRY_ID);
        if (listTechnicalSkillsBEAN.size() > 0) {
            addTechnicalSkillsGridRows();
        } else {
            TechnicalSkillsBEAN emptySkillsBEAN = new TechnicalSkillsBEAN();
            listTechnicalSkillsBEAN.add(emptySkillsBEAN);
            addTechnicalSkillsGridRows();
        }
    }

    /**
     *
     */
    public void addTechnicalSkillsGridRows() {
        gridTechnicalDetails.getChildren().clear();
        for (TechnicalSkillsBEAN skillsBEAN : listTechnicalSkillsBEAN) {
            skillsBEAN.setEnquiryId(ENQUIRY_ID);

            ComboBox cmbTechnology = new ComboBox(masterTechSkillsDAO.retrieveTechnicalSkills());
            ComboBox cmbKnowledgeLevel = new ComboBox(obsKnowledgeLevel);
            GridPane.setHgrow(cmbTechnology, Priority.ALWAYS);
            GridPane.setHgrow(cmbKnowledgeLevel, Priority.ALWAYS);

            cmbTechnology.setMaxWidth(Double.MAX_VALUE);
            cmbKnowledgeLevel.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateTechnicalSkills(listTechnicalSkillsBEAN) <= 0) {
                        skillsBEAN.setEnquiryId(ENQUIRY_ID);
                        if (skillsBEAN.getRowId() != null) {
                            technicalSkillsDAO.updateTechnicalSkills(skillsBEAN);
                        } else {
                            technicalSkillsDAO.insertTechnicalSkills(skillsBEAN);

                        }
                        TechnicalSkillsBEAN emptySkillsBEAN = new TechnicalSkillsBEAN();
//                      List<LanguageSkillsBEAN> listEmptySkillsBEANs = new ArrayList<>();
                        listTechnicalSkillsBEAN.add(emptySkillsBEAN);
                        addTechnicalSkillsGridRows();
                        gridTechnicalDetails.requestFocus();
                    } else {
                        showAlerts.showError("All Fields Required In Technical Skills!", gridTechnicalDetails);
                    }
                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (skillsBEAN.getRowId() != null) {
                            technicalSkillsDAO.deleteTechnicalSkills(skillsBEAN.getRowId());
                        }
                        listTechnicalSkillsBEAN.remove(skillsBEAN);
                        if (listTechnicalSkillsBEAN.size() <= 0) {
                            TechnicalSkillsBEAN emptyBEAN = new TechnicalSkillsBEAN();
                            listTechnicalSkillsBEAN.add(emptyBEAN);
                        }
                        addTechnicalSkillsGridRows();
                        gridTechnicalDetails.requestFocus();
                    }

                }
            });

            /* ====================== Close and Plus Button Events ====================== */
            if (listTechnicalSkillsBEAN.indexOf(skillsBEAN) == 0) {
                gridTechnicalDetails.add(new Label("Technology"), 0, 0);
                gridTechnicalDetails.add(new Label("Knowledge Level"), 1, 0);

            }

            Bindings.bindBidirectional(cmbTechnology.valueProperty(), skillsBEAN.technologyProperty());
            Bindings.bindBidirectional(cmbKnowledgeLevel.valueProperty(), skillsBEAN.knowledgeLevelProperty());

            gridTechnicalDetails.add(cmbTechnology, 0, listTechnicalSkillsBEAN.indexOf(skillsBEAN) + 1);
            gridTechnicalDetails.add(cmbKnowledgeLevel, 1, listTechnicalSkillsBEAN.indexOf(skillsBEAN) + 1);

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridTechnicalDetails.add(hBox, 2, listTechnicalSkillsBEAN.indexOf(skillsBEAN) + 1);

        }

    }

    /**
     *
     */
    public void initDynamicLangSkills() {
        languageSkillsBEANs = languageSkillsDAO.retrieveLanguageSkills(ENQUIRY_ID);
        if (languageSkillsBEANs.size() > 0) {
            addLanguageSkillsGridRows();
        } else {
            LanguageSkillsBEAN emptyBEAN = new LanguageSkillsBEAN();
            languageSkillsBEANs.add(emptyBEAN);
            addLanguageSkillsGridRows();

        }
    }

    /**
     *
     */
    public void addLanguageSkillsGridRows() {
        gridLanguageSkills.getChildren().clear();
        for (LanguageSkillsBEAN languageSkillsBEAN : languageSkillsBEANs) {
            languageSkillsBEAN.setEnquiryId(ENQUIRY_ID);

            ComboBox cmbLanguage = new ComboBox(language);
            ComboBoxJumpToChar.jumpToChar(cmbLanguage);
            cmbLanguage.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbLanguage, Priority.ALWAYS);
            ComboBox cmbProficiencyRead = new ComboBox(language_proficiency);
            ComboBoxJumpToChar.jumpToChar(cmbProficiencyRead);
            cmbProficiencyRead.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbProficiencyRead, Priority.ALWAYS);
            ComboBox cmbProficiencyWrite = new ComboBox(language_proficiency);
            ComboBoxJumpToChar.jumpToChar(cmbProficiencyWrite);
            cmbProficiencyWrite.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbProficiencyWrite, Priority.ALWAYS);
            ComboBox cmbProficiencySpeak = new ComboBox(language_proficiency);
            ComboBoxJumpToChar.jumpToChar(cmbProficiencySpeak);
            cmbProficiencySpeak.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbProficiencySpeak, Priority.ALWAYS);

            cmbLanguage.setPromptText("Select");
            cmbProficiencyRead.setPromptText("Select");
            cmbProficiencyWrite.setPromptText("Select");
            cmbProficiencySpeak.setPromptText("Select");

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnLangSkillClose = new Button();

            btnLangSkillClose.setText(" ");
            btnLangSkillClose.setPrefWidth(32);
            btnLangSkillClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateLanguageSkill(languageSkillsBEANs) <= 0) {
                        System.out.println("languageSkillsBEAN.setProficiency()========" + languageSkillsBEAN.getProficiency());
                        languageSkillsBEAN.setEnquiryId(ENQUIRY_ID);
                        if (languageSkillsBEAN.getRowId() != null) {
                            languageSkillsDAO.updateLanguageSkills(languageSkillsBEAN);
                        } else {
                            languageSkillsDAO.insertLanguageSkills(languageSkillsBEAN);
                        }
                        LanguageSkillsBEAN emptySkillsBEAN = new LanguageSkillsBEAN();
                        languageSkillsBEANs.add(emptySkillsBEAN);
                        addLanguageSkillsGridRows();
                        gridLanguageSkills.requestFocus();
                    } else {
                        showAlerts.showError("All Fields Required In Language Skill Details!", gridLanguageSkills);
                    }
                }
            }
            );
            btnLangSkillClose.setOnAction(
                    new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event
                ) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (languageSkillsBEAN.getRowId() != null) {
                            languageSkillsDAO.deleteLanguageSkills(languageSkillsBEAN.getRowId());
                        }
                        languageSkillsBEANs.remove(languageSkillsBEAN);
                        if (languageSkillsBEANs.size() <= 0) {
                            LanguageSkillsBEAN emptyBEAN = new LanguageSkillsBEAN();
                            languageSkillsBEANs.add(emptyBEAN);
                        }
                        addLanguageSkillsGridRows();
                        gridLanguageSkills.requestFocus();

                    }
                }
            }
            );

            /* ====================== Close and Plus Button Events ====================== */
            if (languageSkillsBEANs.indexOf(languageSkillsBEAN) == 0) {
                gridLanguageSkills.add(new Label("Language"), 0, 0);
                gridLanguageSkills.add(new Label("Read"), 1, 0);
                gridLanguageSkills.add(new Label("Write"), 2, 0);
                gridLanguageSkills.add(new Label("Speak"), 3, 0);

            }

            Bindings.bindBidirectional(cmbLanguage.valueProperty(), languageSkillsBEAN.languageProperty());

            gridLanguageSkills.add(cmbLanguage, 0, languageSkillsBEANs.indexOf(languageSkillsBEAN) + 1);
            gridLanguageSkills.add(cmbProficiencyRead, 1, languageSkillsBEANs.indexOf(languageSkillsBEAN) + 1);
            gridLanguageSkills.add(cmbProficiencyWrite, 2, languageSkillsBEANs.indexOf(languageSkillsBEAN) + 1);
            gridLanguageSkills.add(cmbProficiencySpeak, 3, languageSkillsBEANs.indexOf(languageSkillsBEAN) + 1);
            Bindings.bindBidirectional(cmbProficiencyRead.valueProperty(), languageSkillsBEAN.readProperty());
            Bindings.bindBidirectional(cmbProficiencySpeak.valueProperty(), languageSkillsBEAN.speakProperty());
            Bindings.bindBidirectional(cmbProficiencyWrite.valueProperty(), languageSkillsBEAN.writeProperty());

            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnLangSkillClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridLanguageSkills.add(hBox, 4, languageSkillsBEANs.indexOf(languageSkillsBEAN) + 1);

        }
    }

    /**
     *
     */
    public void initDynamicRelativeDetails() {

        listRelativeBEAN = relativeDAO.retrieveRelativeDetails(ENQUIRY_ID);
        gridRelativeDetails.getChildren().clear();
        if (listRelativeBEAN.size() > 0) {
            addRelationGridRows();
        } else {
            RelativeBEAN emptyBEAN = new RelativeBEAN();
            listRelativeBEAN.add(emptyBEAN);
            addRelationGridRows();
        }

    }

    /**
     *
     */
    public void addRelationGridRows() {
        gridRelativeDetails.getChildren().clear();
        for (RelativeBEAN relativeBEAN : listRelativeBEAN) {
            relativeBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbRelativCountry = new ComboBox(country);
            cmbRelativCountry.setMinWidth(120);
            cmbRelativCountry.setPromptText("Country");
            ComboBoxJumpToChar.jumpToChar(cmbRelativCountry);
            ComboBox cmbRelation = new ComboBox(relationShipList);
            cmbRelation.setMinWidth(120);
            cmbRelation.setPromptText("Relation");
            ComboBoxJumpToChar.jumpToChar(cmbRelation);
            GridPane.setHgrow(cmbRelativCountry, Priority.ALWAYS);
            GridPane.setHgrow(cmbRelation, Priority.ALWAYS);
            cmbRelativCountry.setMaxWidth(Double.MAX_VALUE);
            cmbRelation.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnRelativeClose = new Button();
            btnRelativeClose.setText(" ");
            btnRelativeClose.setPrefWidth(32);
            btnRelativeClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterRelative(listRelativeBEAN) <= 0) {
                        relativeBEAN.setEnquiryId(ENQUIRY_ID);
                        if (relativeBEAN.getRowId() != null) {
                            relativeDAO.updateRelativeDetails(relativeBEAN);
                        } else {
                            relativeDAO.insertRelativeDetails(relativeBEAN);
                        }
                        RelativeBEAN emptyBEAN = new RelativeBEAN();
                        listRelativeBEAN.add(emptyBEAN);
                        addRelationGridRows();
                    } else {
                        /* ====================== Show Error ====================== */
                        showAlerts.showError("All Fields Required In Relative Details!", gridRelativeDetails);

                    }
                    gridRelativeDetails.requestFocus();
                }
            });

            btnRelativeClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (relativeBEAN.getRowId() != null) {
                            relativeDAO.deleteRelativeDetails(relativeBEAN.getRowId());
                        }
                        listRelativeBEAN.remove(relativeBEAN);
                        if (listRelativeBEAN.size() <= 0) {
                            RelativeBEAN emptyBEAN = new RelativeBEAN();
                            listRelativeBEAN.add(emptyBEAN);
                        }
                        addRelationGridRows();
                        gridRelativeDetails.requestFocus();
                    }
                }
            });

            if (listRelativeBEAN.indexOf(relativeBEAN) == 0) {
                gridRelativeDetails.add(new Label("Country Of Relation"), 0, 0);
                gridRelativeDetails.add(new Label("Relationship"), 1, 0);
            }

            Bindings.bindBidirectional(cmbRelativCountry.valueProperty(), relativeBEAN.countryOfRelativeProperty());
            Bindings.bindBidirectional(cmbRelation.valueProperty(), relativeBEAN.relationshipProperty());

            gridRelativeDetails.add(cmbRelativCountry, 0, listRelativeBEAN.indexOf(relativeBEAN) + 1);
            gridRelativeDetails.add(cmbRelation, 1, listRelativeBEAN.indexOf(relativeBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnRelativeClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridRelativeDetails.add(hBox, 2, listRelativeBEAN.indexOf(relativeBEAN) + 1);

        }

    }

    /**
     *
     */
    public void bindAllControls() {
        /* ====================== Personal Details ====================== */

        Bindings.bindBidirectional(txtName.textProperty(), counselorDetailsBEAN.contactNameProperty());
        Bindings.bindBidirectional(txtContactEmail.textProperty(), counselorDetailsBEAN.contactEmailProperty());
        Bindings.bindBidirectional(cmbCountry.valueProperty(), counselorDetailsBEAN.countryProperty());
        Bindings.bindBidirectional(cmbState.valueProperty(), counselorDetailsBEAN.stateProperty());
        Bindings.bindBidirectional(cmbDistrict.valueProperty(), counselorDetailsBEAN.districtProperty());

        Bindings.bindBidirectional(txtStdIsd.textProperty(), counselorDetailsBEAN.stdcode1Property());
        Bindings.bindBidirectional(txtStdIsd2.textProperty(), counselorDetailsBEAN.stdcode2Property());
        Bindings.bindBidirectional(txtContactNumber.textProperty(), counselorDetailsBEAN.contactNumber1Property());
        Bindings.bindBidirectional(txtContactNumber2.textProperty(), counselorDetailsBEAN.contactNumber2Property());
        Bindings.bindBidirectional(txtPreviousName.textProperty(), counselorDetailsBEAN.previousNameProperty());
        Bindings.bindBidirectional(txtHouseNoOrName.textProperty(), counselorDetailsBEAN.houseNameProperty());
        Bindings.bindBidirectional(txtStreet.textProperty(), counselorDetailsBEAN.streetProperty());
        Bindings.bindBidirectional(txtPlace.textProperty(), counselorDetailsBEAN.placeProperty());
        Bindings.bindBidirectional(txtPostOrPin.textProperty(), counselorDetailsBEAN.postOfficeProperty());
        Bindings.bindBidirectional(txtPassportNo.textProperty(), counselorDetailsBEAN.passportNoProperty());


        /* ====================== Assessment Status Control's Bindings ====================== */
        cmbAssignedCounselor.valueProperty().addListener(new ChangeListener<CounselorsListPOJO>() {

            @Override
            public void changed(ObservableValue<? extends CounselorsListPOJO> observable, CounselorsListPOJO oldValue, CounselorsListPOJO newValue) {
                if (newValue != null) {
                    counselorDetailsBEAN.setEnquiryAssignedTo(newValue.getUsername());
                    assessmentStatusBEAN.setCounselor(newValue.getUsername());
                }
            }
        ;
        });
        counselorDetailsBEAN.enquiryAssignedToProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (CounselorsListPOJO clpojo : cmbAssignedCounselor.getItems()) {
                        if (clpojo.getUsername().equalsIgnoreCase(newValue)) {
                            cmbAssignedCounselor.getSelectionModel().select(clpojo);
                        }
                    }
                }
            }
        ;
        });

        Bindings.bindBidirectional(cmbAssignedBranch.valueProperty(), counselorDetailsBEAN.bracnchProperty());
        cmbAssStatus.setItems(RetrieveStaticMasterDAO.retrieveEnquiryAppStatus());

        cmbAssStatus.valueProperty().addListener(new ChangeListener<EnquirySatusPOJO>() {

            @Override
            public void changed(ObservableValue<? extends EnquirySatusPOJO> observable, EnquirySatusPOJO oldValue, EnquirySatusPOJO newValue) {
                if (newValue != null) {
                    counselorDetailsBEAN.setStatus(newValue.getId());
                    assessmentStatusBEAN.setAssessmentStatus(newValue.getId());
                }
            }
        ;
    }

    );
        /* ====================== Bind All Spouse Details ====================== */
    }

    /**
     *
     */
    public void initDynamicContactControlls() {
        listContactNumberBEAN = contactNumbersDAO.retrieveAllContactNumbers(ENQUIRY_ID);
        addContactGridRows();
    }

    /**
     *
     */
    public void addContactGridRows() {
        gridContactNumber.getChildren().clear();
        /* ======================== Add Default Row ==================== */
        Button btnDftPlus = new Button();
        btnDftPlus.getStyleClass().add("plus-button");
        btnDftPlus.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_green.png"))));
        Button btnDftClose = new Button();
        btnDftClose.getStyleClass().add("close-button");
        btnDftClose.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_red.png"))));
        HBox boxDft = new HBox();
        boxDft.getChildren().addAll(btnDftPlus, btnDftClose);
        boxDft.setSpacing(3);

        Label lblContactNo = new Label("Contact Number");
        Text txtMandatory = new Text("*");
        txtMandatory.setStyle("-fx-fill:red");
        lblContactNo.setGraphic(txtMandatory);
        lblContactNo.setContentDisplay(ContentDisplay.RIGHT);

        gridContactNumber.add(txtStdIsd, 0, 0);
        gridContactNumber.add(txtContactNumber, 1, 0);
        gridContactNumber.add(txtStdIsd2, 2, 0);
        gridContactNumber.add(txtContactNumber2, 3, 0);
        gridContactNumber.add(boxDft, 4, 0);
        gridContactNumber.add(txtContactEmail, 5, 0);

        btnDftPlus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (listContactNumberBEAN.size() < CONTACT_LIMIT) {
                    if (validateContactList(listContactNumberBEAN)) {
                        ContactNumberBEAN emptyBEAN = new ContactNumberBEAN();
                        listContactNumberBEAN.add(emptyBEAN);
                        addContactGridRows();
                    } else {
                        System.out.println("Failed Contact Validation");

                    }
                }
            }
        });

        btnDftClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnAssessmentSave.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                }
            }
        });

        /* ======================== End Default Row ==================== */
        for (ContactNumberBEAN contactNumberBEAN : listContactNumberBEAN) {
            TextField txtDynStdIsd1 = new TextField();
            txtDynStdIsd1.setPromptText("STD/ISD");
            txtDynStdIsd1.getStyleClass().add("txt-style");
            TextField txtDynStdIsd2 = new TextField();
            txtDynStdIsd2.setPromptText("STD/ISD");
            txtDynStdIsd2.getStyleClass().add("txt-style");
            TextField txtDynContactNumber1 = new TextField();
            txtDynContactNumber1.getStyleClass().add("txt-style");
            txtDynContactNumber1.setPromptText("Contact Number" + (listContactNumberBEAN.indexOf(contactNumberBEAN) + 3));
            TextField txtDynContactNumber2 = new TextField();
            txtDynContactNumber2.getStyleClass().add("txt-style");
            txtDynContactNumber2.setPromptText("Contact Number" + (listContactNumberBEAN.indexOf(contactNumberBEAN) + 4));

            /* ======================== Buttons ==================== */
            Button btnDynPlus = new Button();
            btnDynPlus.getStyleClass().add("plus-button");
            btnDynPlus.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_green.png"))));
            Button btnDynClose = new Button();
            btnDynClose.getStyleClass().add("close-button");
            btnDynClose.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_red.png"))));
            HBox box = new HBox();
            box.getChildren().addAll(btnDynPlus, btnDynClose);
            box.setSpacing(3);
            /* ======================== Button Events ==================== */
 /* ======================== End Button Events ==================== */
            btnDynPlus.setOnAction((ActionEvent event) -> {
                if (listContactNumberBEAN.size() < CONTACT_LIMIT) {
                    if (validateContactList(listContactNumberBEAN)) {
                        ContactNumberBEAN emptyBEAN = new ContactNumberBEAN();
                        listContactNumberBEAN.add(emptyBEAN);
                        addContactGridRows();
                        txtDynStdIsd1.requestFocus();
                    } else {
                        //   showErrors.showError("Please fill all the fields required in contact numbers \n Then press '+' to add more !", txtName);
                    }
                }
            });

            btnDynClose.setOnAction((ActionEvent event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnAssessmentSave.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    listContactNumberBEAN.remove(contactNumberBEAN);
                    contactNumbersDAO.deleteContactNumber(contactNumberBEAN.getRowId());
                    addContactGridRows();
                }
            });
            /* ====================== Input Validations ====================== */
            txtDynContactNumber1.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {

                        if (newValue.matches("[0-9]*")) {
                            if (newValue.length() > 10) {
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
                            if (newValue.length() > 10) {
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
            ListDataIntelliHints autoCompletionDyanaStdCodes1 = new ListDataIntelliHints(txtDynStdIsd1, stdCodes);
            autoCompletionDyanaStdCodes1.setCaseSensitive(false);
            ListDataIntelliHints autoCompletionDyanaStdCodes2 = new ListDataIntelliHints(txtDynStdIsd2, stdCodes2);
            autoCompletionDyanaStdCodes2.setCaseSensitive(false);
            autoCompletionDyanaStdCodes2.setShowHintsDelay(Duration.ZERO);
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
            gridContactNumber.add(txtDynStdIsd1, 0, listContactNumberBEAN.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynContactNumber1, 1, listContactNumberBEAN.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynStdIsd2, 2, listContactNumberBEAN.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynContactNumber2, 3, listContactNumberBEAN.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(box, 4, listContactNumberBEAN.indexOf(contactNumberBEAN) + 1);
        }
    }

    /**
     *
     * @param contactNumberBEANs
     * @return
     */
    public boolean validateContactList(List<ContactNumberBEAN> contactNumberBEANs) {
        boolean flag = true;

        if (counselorDetailsBEAN.getStdcode1() == null || counselorDetailsBEAN.getStdcode2() == null
                || counselorDetailsBEAN.getContactNumber1() == null || counselorDetailsBEAN.getContactNumber2() == null
                || counselorDetailsBEAN.getStdcode1().equals("") || counselorDetailsBEAN.getStdcode2().equals("")
                || counselorDetailsBEAN.getContactNumber1().equals("") || counselorDetailsBEAN.getContactNumber2().equals("")) {
            flag = false;
            showAlerts.showError("Please fill all the fields required in contact numbers \n Then press '+' to add more !", txtName);
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtContactNumber);

        }
        for (ContactNumberBEAN numberBEAN : contactNumberBEANs) {
            if ((numberBEAN.getContactNumber1() == null || numberBEAN.getContactNumber2() == null || numberBEAN.getStdIsd1() == null || numberBEAN.getStdIsd2() == null)) {
                flag = false;
                showAlerts.showError("Please fill all the fields required in contact numbers \n Then press '+' to add more !", txtName);
                ScrollPaneFocus.requestFocus(scrollPaneMain, txtContactNumber);
            }
        }

        return flag;
    }

    /**
     *
     * @param node
     * @param ms
     */
    public void fadeOut(Node node, Double ms) {
        FadeTransition fadeTransition
                = new FadeTransition(Duration.millis(ms), node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void numberValidations() {
        hintsContactNumbers = new ListDataIntelliHints(txtContactNumber, listContactnumbers);
        hintsContactNumbers.setCaseSensitive(false);
        hintsContactNumbers.setShowHintsDelay(Duration.ZERO);
        txtContactNumber.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if (newValue.matches("[0-9]*")) {
                        if (newValue.length() > 10) {
                            Bindings.unbindBidirectional(counselorDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                            txtContactNumber.setText(oldValue);
                            Bindings.bindBidirectional(counselorDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                        } else {
                            txtContactNumber.setText(newValue);
                            if (!newValue.equalsIgnoreCase("")) {
                                taskPhonenSearch = new Task<List<PhoneSearchPOJO>>() {
                                    @Override
                                    protected List<PhoneSearchPOJO> call() throws Exception {
                                        return EnquiryDetailsDAO.searchPhoneNumbers(newValue);
                                    }
                                };

//                                taskPhonenSearch.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//                                    @Override
//                                    public void handle(WorkerStateEvent event) {
//                                        System.out.println("Success");
//                                        if (!listContactnumbers.containsAll(taskPhonenSearch.getValue())) {
//                                            System.out.println("Not contains");
//                                            System.out.println("Test :: " + listContactnumbers.toString());
//                                            listContactnumbers = taskPhonenSearch.getValue();
//                                            hintsContactNumbers.updateHints(listContactnumbers);
//                                            hintsContactNumbers.showHints();
//                                        }
//                                    }
//                                });
                                executor.execute(taskPhonenSearch);
                                if (hintsContactNumbers.getSelectedHint() != null) {
                                    System.out.println("Selected Phone Number " + hintsContactNumbers.getSelectedHint());
                                    PhoneSearchPOJO phoneSearchPOJO = (PhoneSearchPOJO) hintsContactNumbers.getSelectedHint();
                                    /* ======================== Load data from db ==================== */
                                    if (phoneSearchPOJO.getEnquiryId() != null) {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Exists ! Do you want load?", ButtonType.YES, ButtonType.CANCEL);
                                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                        stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                        stage.initOwner(btnAssessmentSave.getScene().getWindow());
                                        alert.showAndWait();
//                                        if (alert.getResult() == ButtonType.YES) {
//                                            ENQUIRY_ID = phoneSearchPOJO.getEnquiryId();
//                                            System.out.println("Test New Id :: " + phoneSearchPOJO.getEnquiryId());
//                                            counselorDetailsBEAN.setEnquiryID(ENQUIRY_ID);
//                                            System.out.println("Test New Id :: " + phoneSearchPOJO.getEnquiryId());
//                                            initPersonalDetails();
//                                            bindAllControls();
//                                            hintsContactNumbers = new ListDataIntelliHints(txtContactNumber, listContactnumbers);
//                                        }
                                    }
                                }

                            }
                        }

                    } else {
                        Bindings.unbindBidirectional(counselorDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                        txtContactNumber.setText(oldValue);
                        Bindings.bindBidirectional(counselorDetailsBEAN.contactNumber1Property(), txtContactNumber.textProperty());

                    }
                }
            }
        });
        txtName.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if (newValue.matches("[A-Za-z\\s]*")) {
                        txtName.setText(newValue);
                    } else {
                        txtName.setText(oldValue);
                    }
                }
            }
        });

        txtContactNumber2.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.matches("[0-9]*")) {
                        txtContactNumber2.setText(newValue);
                        if (txtContactNumber2.getText().length() > 10) {
                            txtContactNumber2.setText(oldValue);
                        }
                    } else {
                        txtContactNumber2.setText(oldValue);
                    }
                }
            }
        });

    }

    /**
     *
     */
    public void initData() {
        if (CURRENT_TAB != null) {
            lblAssesmentTitle.setText("Registration Form - " + CURRENT_TAB.substring(0, 1).toUpperCase() + CURRENT_TAB.substring(1));
        }
//        if (cmbApplicationType.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Single")) {
//            spousePanel.setDisable(true);
//            childPane.setDisable(true);
//        } else {
//            spousePanel.setDisable(false);
//            childPane.setDisable(false);
//        }
        if (counselorDetailsBEAN.getRating() != null) {
            if (counselorDetailsBEAN.getRating().equals("0")) {
                PRIORITY_RATING = 1;
                starimageView.setImage(new Image(FXMLRegistrationFormController.class.getResourceAsStream("stary.png")));
            } else {
                PRIORITY_RATING = 0;
                starimageView.setImage(new Image(FXMLRegistrationFormController.class.getResourceAsStream("starw.png")));
            }
        }
        listContactnumbers = EnquiryDetailsDAO.searchPhoneNumbers("");
        List<String> countryList = MasterCountryStateDistrictDAO.getAllCountries();
        country.clear();
        for (String s : countryList) {
            country.add(s);
        }
        ObservableList observableListCountry = FXCollections.observableArrayList();
        for (String s : countryList) {
            observableListCountry.add(s);
        }
        cmbCountry.setItems(observableListCountry);
        stdCodes = RetrieveStaticMasterDAO.getStdCodes();
        stdCodes2.addAll(stdCodes);
        List<String> stateList = MasterCountryStateDistrictDAO.getAllStates(counselorDetailsBEAN.getCountry());
        for (String s : stateList) {
            state.add(s);
        }
        cmbState.setItems(state);
        cmbState.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    ObservableList districts = FXCollections.observableArrayList();
                    List<String> district = MasterCountryStateDistrictDAO.getAllDistricts(newValue);
                    for (String s : district) {
                        districts.add(s);
                    }
                    cmbDistrict.setItems(districts);
                    cmbDistrict.getSelectionModel().selectFirst();
                }
            }
        });
        List<String> timings = RetrieveStaticMasterDAO.getTimings();
        for (String s : timings) {
            timing.add(s);
        }
//<<---------Add Institutions to cmbInstitution----------->>
        currencyList = RetrieveStaticMasterDAO.getCurrencies();
        cmbWorkCurrency.setItems(currencyList);
        //<----Add Min and Max salary to comboboxes-------->>
        minSalaryList = RetrieveStaticMasterDAO.getSalary();
        CmbWorkMin.setItems(minSalaryList);

        maxSalaryList = RetrieveStaticMasterDAO.getMaxSalary();
        cmbWorkMax.setItems(maxSalaryList);

        List<String> salarys = RetrieveStaticMasterDAO.getSalary();

        List<String> ageList = RetrieveStaticMasterDAO.getAllages();
        for (String s : ageList) {
            age.add(s);
        }

        //<--------------------Method for add study,work,migration and training combo------------------------>
        cmbAge.setItems(age);
        cmbAge.getSelectionModel().selectFirst();
        cmbSpouseAge.setItems(age);
        cmbSpouseAge.getSelectionModel().selectFirst();

        cmbState.getSelectionModel().clearAndSelect(cmbState.getItems().indexOf(counselorDetailsBEAN.getState()));
        List<String> districtList = MasterCountryStateDistrictDAO.getAllDistricts(counselorDetailsBEAN.getState());
        for (String s : districtList) {
            district.add(s);
        }
        cmbDistrict.setItems(district);
        cmbAssignedBranch.getSelectionModel().clearAndSelect(cmbAssignedBranch.getItems().indexOf(counselorDetailsBEAN.getBracnch()));
        // cmbDepartment.getSelectionModel().clearAndSelect(cmbDepartment.getItems().indexOf(counselorDetailsBEAN.getDepartment()));

    }

    /*=================Method initialize personal details===============*/
    /**
     *
     */
    public void initPersonalDetails() {
        AssessmentPersonBEAN = PersonalCrudDAO.getPersonalDetails(ENQUIRY_ID);
        PersonalCrudDAO.getPersonalDetails(counselorDetailsBEAN);
        cmbApplicationType.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.equals("Single") || newValue.equalsIgnoreCase("Divorced")) {
                        spousePanel.setDisable(true);
                    } else {
                        spousePanel.setDisable(false);
                    }

                }
            }
        });
        Bindings.bindBidirectional(AssessmentPersonBEAN.nameProperty(), counselorDetailsBEAN.contactNameProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.emailProperty(), counselorDetailsBEAN.contactEmailProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.phoneProperty(), counselorDetailsBEAN.contactNumber1Property());
        Bindings.bindBidirectional(AssessmentPersonBEAN.phone2Property(), counselorDetailsBEAN.contactNumber2Property());
        Bindings.bindBidirectional(AssessmentPersonBEAN.stdCode1Property(), counselorDetailsBEAN.stdcode1Property());
        Bindings.bindBidirectional(AssessmentPersonBEAN.stdCode2Property(), counselorDetailsBEAN.stdcode2Property());
        Bindings.bindBidirectional(AssessmentPersonBEAN.stateProperty(), counselorDetailsBEAN.stateProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.districtProperty(), counselorDetailsBEAN.districtProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.countryProperty(), counselorDetailsBEAN.countryProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.countryProperty(), counselorDetailsBEAN.countryProperty());

        Bindings.bindBidirectional(cmbAge.valueProperty(), counselorDetailsBEAN.ageProperty());
        Bindings.bindBidirectional(cmbApplicationType.valueProperty(), counselorDetailsBEAN.maritalStatusProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.previousNameProperty(), counselorDetailsBEAN.previousNameProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.houseNameProperty(), counselorDetailsBEAN.houseNameProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.streetProperty(), counselorDetailsBEAN.streetProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.placeProperty(), counselorDetailsBEAN.placeProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.postOfficeProperty(), counselorDetailsBEAN.postOfficeProperty());
        Bindings.bindBidirectional(AssessmentPersonBEAN.passportNoProperty(), counselorDetailsBEAN.passportNoProperty());

        Bindings.bindBidirectional(cmbAge.valueProperty(), counselorDetailsBEAN.ageProperty());

        if (counselorDetailsBEAN.getGender() != null) {
            if (counselorDetailsBEAN.getGender().equalsIgnoreCase("Male")) {
                maleRadioBtn.setSelected(true);
            } else if (counselorDetailsBEAN.getGender().equalsIgnoreCase("Female")) {
                femaleRadioBtn.setSelected(true);

            }
        }
        groupGender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    if (newValue.toString().contains("Male")) {
                        counselorDetailsBEAN.setGender("Male");
                    } else if (newValue.toString().contains("Female")) {
                        counselorDetailsBEAN.setGender("Female");
                    }
                }
            }
        });
//        txtPassportNo.setText(enquiryAssessmentPersonPOJO.getPassportNo());
        if (counselorDetailsBEAN.getDob() != null) {
            datePickDob.setValue(LocalDate.parse(counselorDetailsBEAN.getDob()));
            ObservableValue<String> years = new ReadOnlyObjectWrapper<String>("" + Math.abs(LocalDate.now().until(LocalDate.parse(counselorDetailsBEAN.getDob()), ChronoUnit.YEARS)));
        }
        datePickDob.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    counselorDetailsBEAN.setDob(newValue.toString());
                    ObservableValue<String> years = new ReadOnlyObjectWrapper<String>("" + Math.abs(LocalDate.now().until(newValue, ChronoUnit.YEARS)));
                }
            }
        });

        /* ====================== Auto Completion of std/isd codes ====================== */
        ListDataIntelliHints autoCompletionPrimeStdCodes1 = new ListDataIntelliHints(txtStdIsd, stdCodes);
        autoCompletionPrimeStdCodes1.setCaseSensitive(false);
        ListDataIntelliHints autoCompletionPrimeStdCodes2 = new ListDataIntelliHints(txtStdIsd2, stdCodes2);
        autoCompletionPrimeStdCodes2.setCaseSensitive(false);

        /* ====================== Input Validations ====================== */
        txtStdIsd.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String stdArray[] = null;
                    stdArray = newValue.split("-");
                    if (!stdArray[0].equals("")) {
                        txtStdIsd.setText(stdArray[0]);
                        if (txtStdIsd.getText().length() >= 9) {
                            Bindings.unbindBidirectional(counselorDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(counselorDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                        } else if (stdArray[0].matches("[0-9,+]*")) {
                            txtStdIsd.setText(stdArray[0]);
                        } else {
                            Bindings.unbindBidirectional(counselorDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(counselorDetailsBEAN.stdcode1Property(), txtStdIsd.textProperty());
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
                            Bindings.unbindBidirectional(counselorDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                            txtStdIsd2.setText(oldValue);
                            Bindings.bindBidirectional(counselorDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                        } else if (stdArray[0].matches("[0-9,+]*")) {
                            txtStdIsd2.setText(stdArray[0]);
                        } else {
                            Bindings.unbindBidirectional(counselorDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                            txtStdIsd2.setText(oldValue);
                            Bindings.bindBidirectional(counselorDetailsBEAN.stdcode2Property(), txtStdIsd2.textProperty());
                        }
                    }
                }
            }
        });

    }

    //Method for initialize assesment status
    /**
     *
     */
    public void initAssesmentStatus() {
        assessmentStatusBEAN = assessmentStatusDAO.retrieveAssessmentStatus(ENQUIRY_ID);
        assessmentStatusBEAN.setEnquiryId(ENQUIRY_ID);

        /* ====================== Set Priority ====================== */
        if (assessmentStatusBEAN.getPriority() != null) {
            if (assessmentStatusBEAN.getPriority().equals("0")) {
                PRIORITY_RATING = 10;
                starimageView.setImage(new Image(FXMLRegistrationFormController.class.getResourceAsStream("stary.png")));
            } else {
                PRIORITY_RATING = 0;
                starimageView.setImage(new Image(FXMLRegistrationFormController.class.getResourceAsStream("starw.png")));
            }
        } else {
            PRIORITY_RATING = 10;
            starimageView.setImage(new Image(FXMLRegistrationFormController.class.getResourceAsStream("stary.png")));
        }
        /* ====================== Branch Combo Change Event ====================== */
        cmbAssignedBranch.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    ObservableList<CounselorsListPOJO> assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(newValue);
                    cmbAssignedCounselor.setItems(assignedCounselors);
//                    if (cmbAssignedCounselor.getItems().contains(counselorDetailsBEAN.getEnquiryAssignedTo())) {
//                        cmbAssignedCounselor.getSelectionModel().clearAndSelect(cmbAssignedCounselor.getItems().indexOf(counselorDetailsBEAN.getEnquiryAssignedTo()));
//                    }
                }
            }
        });

        ObservableList<CounselorsListPOJO> assignedCounselors = FXCollections.observableArrayList();
        if (counselorDetailsBEAN.getBracnch() != null) {
            assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(counselorDetailsBEAN.getBracnch());
            cmbAssignedCounselor.setItems(assignedCounselors);
        }
        for (CounselorsListPOJO clpojo : cmbAssignedCounselor.getItems()) {
            if (clpojo.getUsername().equalsIgnoreCase(counselorDetailsBEAN.getEnquiryAssignedTo())) {
                cmbAssignedCounselor.getSelectionModel().select(clpojo);
            }
        }
        /* ====================== Set Appointment Date & Time ====================== */
        if (counselorDetailsBEAN.getAppointmentDate() != null) {
            datePickerAppointment.setValue(LocalDate.parse(counselorDetailsBEAN.getAppointmentDate()));
        }
        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss.SSS").toFormatter();
        if (counselorDetailsBEAN.getAppointmentTime() != null) {
            if (counselorDetailsBEAN.getAppointmentTime().equalsIgnoreCase("")) {
                try {
                    timePickerAppointmentTime.setLocalTime(LocalTime.parse(counselorDetailsBEAN.getAppointmentTime(), parseFormat));
                } catch (DateTimeParseException dateTimeParseException) {
                    logger.error(dateTimeParseException.getMessage());
                    dateTimeParseException.printStackTrace();
                }
            }
        }
        datePickerAppointment.valueProperty().addListener(new ChangeListener<LocalDate>() {

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    assessmentStatusBEAN.setAppointmentDate(newValue);
                }
            }
        });
        timePickerAppointmentTime.localTimeProperty().addListener(new ChangeListener<LocalTime>() {

            @Override
            public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue, LocalTime newValue) {
                if (newValue != null) {
                    assessmentStatusBEAN.setAppointmentTime(newValue);
                }
            }
        });
        /* ====================== Forward For Change Event ====================== */

 /* ====================== Bind Assessment Status Controlls ====================== */
        Bindings.bindBidirectional(cmbForwardFor.valueProperty(), assessmentStatusBEAN.forwardForProperty());
        Bindings.bindBidirectional(assessmentStatusBEAN.branchProperty(), counselorDetailsBEAN.bracnchProperty());
        Bindings.bindBidirectional(assessmentStatusBEAN.counselorProperty(), counselorDetailsBEAN.enquiryAssignedToProperty());
        Bindings.bindBidirectional(txtRemarks.textProperty(), assessmentStatusBEAN.remarksProperty());
        Bindings.bindBidirectional(cmbDepartment.valueProperty(), assessmentStatusBEAN.departmentProperty());
        Bindings.bindBidirectional(datePickerAppointment.valueProperty(), assessmentStatusBEAN.appointmentDateProperty());
        Bindings.bindBidirectional(timePickerAppointmentTime.localTimeProperty(), assessmentStatusBEAN.appointmentTimeProperty());

        for (EnquirySatusPOJO statusPOJO : cmbAssStatus.getItems()) {
            if (statusPOJO != null) {
                if (statusPOJO.getId().equalsIgnoreCase(assessmentStatusBEAN.getAssessmentStatus())) {
                    cmbAssStatus.getSelectionModel().select(statusPOJO);
                }
            }
        }
        /* ====================== Set Department ====================== */
        for (String dep : cmbDepartment.getItems()) {
            if (dep.equalsIgnoreCase(counselorDetailsBEAN.getDepartment())) {
                cmbDepartment.getSelectionModel().select(dep);
            }
        }

        /* ====================== End Bind Assessment Status Controlls ====================== */
    }

    /**
     *
     */
    public void assignComboValues() {
        cmbForwardFor.setItems(purposeDAO.getPurpose());
        childGenderCmb.setItems(gender);
        industryList = RetrieveStaticMasterDAO.getIndustries();
        applicationList = RetrieveStaticMasterDAO.getApplicationType();

        List<String> programLevel = RetrieveStaticMasterDAO.getProgramLevels();

        for (String s : programLevel) {
            programLevelList.add(s);
        }
        programLevelCmb.setItems(programLevelList);
        programLevelCmb.valueProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                    programFieldList.clear();
                    for (String s : programField) {
                        programFieldList.add(s);
                    }
                    programFieldCmb.setItems(programFieldList);

                }
            }
        });
        List<String> intake = RetrieveStaticMasterDAO.getIntake();
        ObservableList intakeList = FXCollections.observableArrayList();
        for (String s : intake) {
            intakeList.add(s);
        }
        intakeCmb.setItems(intakeList);
        List<String> testScores = RetrieveStaticMasterDAO.getTestScore();
        for (String s : testScores) {
            testScore.add(s);
        }
        ObservableList universities = RetrieveStaticMasterDAO.getUniversities();
        universityStudyCombo.setItems(universities);
        migrateCategory = RetrieveStaticMasterDAO.getMigrateCategories();
        profession = RetrieveStaticMasterDAO.getProfession();
        ObservableList workCategory = RetrieveStaticMasterDAO.getMigrateCategories();
        training = RetrieveStaticMasterDAO.getTraings();
        baches = RetrieveStaticMasterDAO.getBatches();
        duration = RetrieveStaticMasterDAO.getDuration();
        score = RetrieveStaticMasterDAO.getScore();
        otherTest = RetrieveStaticMasterDAO.getOtherTest();
        exam_boards = RetrieveStaticMasterDAO.getExamBoards();
        sslcBoardCombo.setItems(exam_boards);
        language = RetrieveStaticMasterDAO.getLanguage();
        mediumCombo.setItems(language);
        plus2BoardCombo.setItems(exam_boards);
        plus2MediumCombo.setItems(language);
        cmbSpousePlusTwo.setItems(exam_boards);
        spousQualificationCmb.setItems(programLevelList);
        spousQualificationCmb.valueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                    spouseProgramFieldList.removeAll(spouseProgramFieldList);
//                programFieldList.removeAll(programFieldList);
                    for (String s : programField) {
                        spouseProgramFieldList.add(s);
                    }
                    spouseQualificationFieldCmb.setItems(spouseProgramFieldList);
                    spouseQualificationFieldCmb.getSelectionModel().selectFirst();
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        spouseProfessionCmb.setItems(profession);

        durationCmb.setItems(duration);

    }

    /**
     *
     * @return
     */
    public Boolean mailValidation() {
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email1 = counselorDetailsBEAN.getContactEmail().trim();
        Boolean b = email1.matches(EMAIL_REGEX);
        return b;
    }

    /**
     *
     * @return
     */
    public boolean validatePersonalDetails() {
        boolean flag = true;
        if (counselorDetailsBEAN.getContactName() == null || counselorDetailsBEAN.getContactName().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtName);
            showAlerts.showError("Please Enter Name!", txtName);

        } else if (counselorDetailsBEAN.getPreviousName() == null || counselorDetailsBEAN.getPreviousName().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtPreviousName);
            showAlerts.showError("Please Enter Previous Name!", txtPreviousName);
        } else if (counselorDetailsBEAN.getMaritalStatus() == null || counselorDetailsBEAN.getMaritalStatus().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbApplicationType);
            showAlerts.showError("Please Choose Application Status!", cmbApplicationType);
        } else if (groupGender.getSelectedToggle() == null) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, maleRadioBtn);
            showAlerts.showError("Please Choose Gender!", maleRadioBtn);
        } else if (counselorDetailsBEAN.getAge() == null || counselorDetailsBEAN.getAge().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbAge);
            showAlerts.showError("Please Choose Age!", cmbAge);
        } else if (counselorDetailsBEAN.getDob() == null || counselorDetailsBEAN.getDob().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, datePickDob);
            showAlerts.showError("Please Choose DOB!", datePickDob);
        } else if (counselorDetailsBEAN.getPassportNo() == null || counselorDetailsBEAN.getPassportNo().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtPassportNo);
            showAlerts.showError("Please Enter Passport No!", txtPassportNo);
        } else if (counselorDetailsBEAN.getHouseName() == null || counselorDetailsBEAN.getHouseName().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtHouseNoOrName);
            showAlerts.showError("Please Enter House Name!", txtHouseNoOrName);
        } else if (counselorDetailsBEAN.getStreet() == null || counselorDetailsBEAN.getStreet().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtStreet);
            showAlerts.showError("Please Enter Street!", txtStreet);
        } else if (counselorDetailsBEAN.getPlace() == null || counselorDetailsBEAN.getPlace().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtPlace);
            showAlerts.showError("Please Enter Place!", txtPlace);
        } else if (counselorDetailsBEAN.getPostOffice() == null || counselorDetailsBEAN.getPostOffice().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtPostOrPin);
            showAlerts.showError("Please Enter Post Office!", txtPostOrPin);
        } else if (counselorDetailsBEAN.getDistrict() == null || counselorDetailsBEAN.getDistrict().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbDistrict);
            showAlerts.showError("Please Choose District!", cmbDistrict);
        } else if (counselorDetailsBEAN.getState() == null || counselorDetailsBEAN.getState().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbState);
            showAlerts.showError("Please Choose State!", cmbState);
        } else if (counselorDetailsBEAN.getStdcode1() == null || counselorDetailsBEAN.getStdcode1().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtStdIsd);
            showAlerts.showError("Please Enter Std/Isd Code!", txtStdIsd);
        } else if (counselorDetailsBEAN.getCountry() == null || counselorDetailsBEAN.getCountry().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbCountry);
            showAlerts.showError("Please Choose Country!", cmbCountry);
        } else if (counselorDetailsBEAN.getContactNumber1() == null || counselorDetailsBEAN.getContactNumber1().equalsIgnoreCase("")) {
            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtContactNumber);
            showAlerts.showError("Please Enter Contact Number!", txtContactNumber);
        } else if (counselorDetailsBEAN.getContactNumber1() != null) {
            if (counselorDetailsBEAN.getContactNumber1().length() < 10) {
                flag = false;
                ScrollPaneFocus.requestFocus(scrollPaneMain, txtContactNumber);
                showAlerts.showError("Please Enter Valid Contact Number!", txtContactNumber);
            }
        } else if (counselorDetailsBEAN.getContactEmail() == null || counselorDetailsBEAN.getContactEmail().equalsIgnoreCase("") || mailValidation() == false) {

            flag = false;
            ScrollPaneFocus.requestFocus(scrollPaneMain, txtContactEmail);
            showAlerts.showError("Please Enter Valid Email!", txtContactEmail);
        }

        return flag;
    }

    @FXML
    private void handleMouseClicked(MouseEvent event) {
        if (PRIORITY_RATING == 0) {
            PRIORITY_RATING = 1;
            starimageView
                    .setImage(new Image(FXMLRegistrationFormController.class
                            .getResourceAsStream("stary.png")));
        } else {
            PRIORITY_RATING = 0;
            starimageView
                    .setImage(new Image(FXMLRegistrationFormController.class
                            .getResourceAsStream("starw.png")));
        }
    }

    @FXML
    private void handleassignedBranchCmb(ActionEvent event) {
//        ObservableList assign = FXCollections.observableArrayList();
//        List<String> assignedCounselors = EnquiryDetailsDAO.getAllCounselors(assignedBranchCmb.getSelectionModel().getSelectedItem().toString());
//
//        for (String s : assignedCounselors) {
//            assign.add(s);
//        }
//        assignedCounsellorCmb.setItems(assign);
//        assignedCounsellorCmb.getSelectionModel().selectFirst();
    }

//    @FXML
//    private void handlePickerDate(ActionEvent event) {
//        if (datePickerAppointment.getValue() != null) {
//            if (datePickerAppointment.getValue().isBefore(LocalDate.now())) {
//                datePickerAppointment.setValue(null);
//            } else if (datePickerAppointment.getValue() != null) {
//                //     a = EnquiryAssessmentPersonDAO.checkAssesmentDate(Date.valueOf(datePickerAppointment.getValue()));
////                if (a < 0) {
////                    datePickerAppointment.setValue(null);
////                } else {
////                }
//            }
//        }
//    }
    private void handlecmbCountry(ActionEvent event) {
        ObservableList states = FXCollections.observableArrayList();
        List<String> state = MasterCountryStateDistrictDAO.getAllStates(cmbCountry.getSelectionModel().getSelectedItem().toString());

        for (String s : state) {
            states.add(s);
        }
        cmbState.setItems(states);
        cmbState.getSelectionModel().selectFirst();
    }


    /*========================= Add Program suggested or required details ==========================*/
    /**
     *
     */
    public void initStudyRequiredDetails() {
        gridStudy.getChildren().clear();
        choiceObsList.clear();
        listStudySuggestedRequired.addAll(ProgramSuggestedRequiredDAO.retrieveStudyDetails(ENQUIRY_ID));
        for (int i = 1; i <= listStudySuggestedRequired.size() + 1; i++) {
            if (!choiceObsList.contains("" + i)) {
                choiceObsList.add("" + i);
            }
        }
        if (listStudySuggestedRequired.size() > 0) {
            addStudyRequiredRows(listStudySuggestedRequired);
        } else {
            StudySuggestedRequiredBEAN emptyRequiredBEAN = new StudySuggestedRequiredBEAN();
            listStudySuggestedRequired.add(emptyRequiredBEAN);
            addStudyRequiredRows(listStudySuggestedRequired);
        }

        // save action of Study Required Details
//        btnStudyPrgmSave.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                if (masterStudyPrgmSugestedList.size() > 0) {
//                    for (StudySuggestedRequiredBEAN studySuggestedRequiredBEAN : masterStudyPrgmSugestedList) {
//                        if (studySuggestedRequiredBEAN.getRowId() != null) {
//                            if (validateStudyRequiredDetails(studySuggestedRequiredBEAN).equalsIgnoreCase("true")) {
//                                ProgramSuggestedRequiredDAO.updateStudyDetails(studySuggestedRequiredBEAN);
//
//                            }
//                        } else if (validateStudyRequiredDetails(studySuggestedRequiredBEAN).equalsIgnoreCase("true")) {
//                            ProgramSuggestedRequiredDAO.insertStudyDetails(studySuggestedRequiredBEAN);
//                        } //discard remaining rows
//                    }
//                }
//            }
//        });
        // save action of Study Required Details
//        btnWorkPrgmSave.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                if (masterWorkList.size() > 0) {
//                    for (AssesmentWorkBEAN assesmentWorkBEAN : masterWorkList) {
//                        if (assesmentWorkBEAN.getRowId() != null) {
//                            if (validateWorkDetails(assesmentWorkBEAN).equalsIgnoreCase("true")) {
//                                EnquiryAssessmentPersonDAO.updateWorkDetails(assesmentWorkBEAN);
//
//                            }
//                        } else if (validateWorkDetails(assesmentWorkBEAN).equalsIgnoreCase("true")) {
//                            EnquiryAssessmentPersonDAO.insertWorkDetails(ENQUIRY_ID, assesmentWorkBEAN);
//                        } //discard remaining rows
//                    }
//                    ShowProgress progress = new ShowProgress();
//                    progress.showProgressIndicator(maleRadioBtn, "Saved");
//                }
//            }
//        });
    }

    /*========================= validate Program suggested or required details ==========================*/
    /**
     *
     * @param validateRequiredBEAN
     * @return
     */
    public String validateStudyRequiredDetails(StudySuggestedRequiredBEAN validateRequiredBEAN) {
//        lblErrorStudng.setText("");
        if (validateRequiredBEAN.getChoice() == null || validateRequiredBEAN.getCountry() == null || validateRequiredBEAN.getLocation() == null || validateRequiredBEAN.getProgram_field() == null || validateRequiredBEAN.getProgram_level() == null || validateRequiredBEAN.getSem_fees() == null || validateRequiredBEAN.getCurrency() == null || validateRequiredBEAN.getAmount() == null || validateRequiredBEAN.getIntake() == null || validateRequiredBEAN.getInstitution() == null) {
            return "All Fields Required!";
        }
        return "true";
    }

    /**
     *
     * @param validateStudyPrgmSugestedList
     * @return
     */
    public int validateMasterStudyRequiredDetails(List<StudySuggestedRequiredBEAN> validateStudyPrgmSugestedList) {
        int count = 0;
        for (StudySuggestedRequiredBEAN studySuggestedRequiredBEAN : validateStudyPrgmSugestedList) {
            if (validateStudyRequiredDetails(studySuggestedRequiredBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }

        return count;
    }

    /*========================= Adding grid rows for Program suggested or required details ==========================*/
    /**
     *
     * @param rowStudySuggestedRequiredList
     */
    public void addStudyRequiredRows(List<StudySuggestedRequiredBEAN> rowStudySuggestedRequiredList) {
        gridStudy.getChildren().clear();
        for (StudySuggestedRequiredBEAN studySuggestedRequiredBEAN : rowStudySuggestedRequiredList) {
//            lblErrorStudng.setText("");
            studySuggestedRequiredBEAN.setEnquiry_id(ENQUIRY_ID);
            studySuggestedRequiredBEAN.setStudy("Study");

            //Required Controls
            ComboBox cmbStudy = new ComboBox();
            cmbStudy.setMinWidth(100);
            cmbStudy.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbStudy);
            ComboBox cmbCountryy = new ComboBox(countryDAO.retrieveMasterAllCountries());
            cmbCountryy.setMinWidth(100);
            cmbCountryy.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbCountryy);
            ComboBox cmbPrgmLevel = new ComboBox(programLevelList);
            cmbPrgmLevel.setMinWidth(100);
            cmbPrgmLevel.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbPrgmLevel);
            ComboBox cmbPrgmField = new ComboBox(programFieldList);
            cmbPrgmField.setMinWidth(99);
            cmbPrgmField.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbPrgmField);
            ComboBox cmbIntake = new ComboBox(intakeObsList);
//            cmbIntake.setMinWidth(98);
            cmbIntake.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbIntake);
            ComboBox cmbInstitution = new ComboBox(institution);
//            cmbInstitution.setMinWidth(130);
            cmbInstitution.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbInstitution);
            ComboBox cmbStudyLocations = new ComboBox(locationList);
//            cmbStudyLocations.setMinWidth(120);
            cmbStudyLocations.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbStudyLocations);
            ComboBox cmbStudyCurency = new ComboBox(currencyList);
            cmbStudyCurency.setMinWidth(50);
            cmbStudyCurency.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbStudyCurency);
            cmbStudyCurency.setMaxWidth(100);
            ComboBox cmbStudyAmnt = new ComboBox(approxiAmountObsList);
            cmbStudyAmnt.setMinWidth(50);
            cmbStudyAmnt.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbStudyAmnt);
            ComboBox cmbFeeType = new ComboBox(approxiFeeTypeObsList);
            cmbFeeType.setMinWidth(50);
            cmbFeeType.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbFeeType);
            ComboBox cmbProgmChoice = new ComboBox(choiceObsList);
            // cmbProgmChoice.setMinWidth(80);
            cmbProgmChoice.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbProgmChoice);

            GridPane.setHgrow(cmbPrgmLevel, Priority.ALWAYS);
            GridPane.setHgrow(cmbPrgmField, Priority.ALWAYS);
            GridPane.setHgrow(cmbCountryy, Priority.ALWAYS);
            GridPane.setHgrow(cmbStudyLocations, Priority.ALWAYS);
            GridPane.setHgrow(cmbFeeType, Priority.ALWAYS);
            GridPane.setHgrow(cmbStudyCurency, Priority.ALWAYS);
            GridPane.setHgrow(cmbStudyAmnt, Priority.ALWAYS);
            GridPane.setHgrow(cmbIntake, Priority.ALWAYS);
            GridPane.setHgrow(cmbInstitution, Priority.ALWAYS);
            GridPane.setHgrow(cmbProgmChoice, Priority.ALWAYS);

            cmbPrgmLevel.setMaxWidth(Double.MAX_VALUE);
            cmbPrgmField.setMaxWidth(Double.MAX_VALUE);
            cmbCountryy.setMaxWidth(Double.MAX_VALUE);
            cmbStudyLocations.setMaxWidth(Double.MAX_VALUE);
            cmbFeeType.setMaxWidth(Double.MAX_VALUE);
            cmbStudyCurency.setMaxWidth(Double.MAX_VALUE);
            cmbStudyAmnt.setMaxWidth(Double.MAX_VALUE);
            cmbIntake.setMaxWidth(Double.MAX_VALUE);
            cmbInstitution.setMaxWidth(Double.MAX_VALUE);
            cmbProgmChoice.setMaxWidth(Double.MAX_VALUE);

            ToggleGroup toggleGroupChild = new ToggleGroup();
            RadioButton childYes = new RadioButton();
            childYes.setText("Yes");
            childYes.setToggleGroup(toggleGroupChild);
            RadioButton childNo = new RadioButton();
            childNo.setText("No");
            childNo.setToggleGroup(toggleGroupChild);

            HBox hBoxChildRadio = new HBox(5);
            hBoxChildRadio.setAlignment(Pos.CENTER_LEFT);
            hBoxChildRadio.getChildren().addAll(childYes, childNo);

            ToggleGroup toggleGroupSpouse = new ToggleGroup();
            RadioButton spouseYes = new RadioButton();
            spouseYes.setText("Yes");

            spouseYes.setToggleGroup(toggleGroupSpouse);
            RadioButton spouseNo = new RadioButton();
            spouseNo.setText("No");
            spouseNo.setToggleGroup(toggleGroupSpouse);

            if (studySuggestedRequiredBEAN.getChildren_accompany() != null) {
                if (studySuggestedRequiredBEAN.getChildren_accompany().contains("Yes")) {
                    childYes.setSelected(true);
                } else if (studySuggestedRequiredBEAN.getChildren_accompany().contains("No")) {
                    childNo.setSelected(true);

                }
            }

            if (studySuggestedRequiredBEAN.getSpouse_accompany() != null) {
                if (studySuggestedRequiredBEAN.getSpouse_accompany().contains("Yes")) {
                    spouseYes.setSelected(true);
                } else if (studySuggestedRequiredBEAN.getSpouse_accompany().contains("No")) {
                    spouseNo.setSelected(true);

                }
            }
            toggleGroupSpouse.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                    if (newValue.toString().contains("Yes")) {
                        studySuggestedRequiredBEAN.setSpouse_accompany("Yes");
                    } else if (newValue.toString().contains("No")) {
                        studySuggestedRequiredBEAN.setSpouse_accompany("No");
                    }

                }
            });
            toggleGroupChild.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                    if (newValue.toString().contains("Yes")) {
                        studySuggestedRequiredBEAN.setChildren_accompany("Yes");
                    } else if (newValue.toString().contains("No")) {
                        studySuggestedRequiredBEAN.setChildren_accompany("No");
                    }

                }
            });

            HBox hBoxSpouseRadio = new HBox(5);
            hBoxSpouseRadio.setAlignment(Pos.CENTER_LEFT);
            hBoxSpouseRadio.getChildren().addAll(spouseYes, spouseNo);
            hBoxSpouseRadio.setMinWidth(100);
            hBoxChildRadio.setMinWidth(146);
            TextField txtStudyComents = new TextField();
            txtStudyComents.setMinWidth(134);
            txtStudyComents.setPromptText("Remarks");

            Button btnPlus = new Button();
            btnPlus.setText("");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
//            btnPlus.setPrefWidth(30);
            Button btnStudyClose = new Button();
            btnStudyClose.setText("");
            btnStudyClose.setPrefWidth(32);
            btnStudyClose.getStyleClass().add("close-button");

            HBox hBoxButtons = new HBox(5);
            hBoxButtons.getChildren().addAll(btnPlus, btnStudyClose);
            hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
            cmbPrgmLevel.valueProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                        ObservableList programFieldlList2 = FXCollections.observableArrayList();
                        for (String s : programField) {
                            programFieldlList2.add(s);
                        }
                        cmbPrgmField.setItems(programFieldlList2);
                        cmbPrgmField.getSelectionModel().selectFirst();
                    }
                }
            });
            cmbCountryy.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbStudyLocations.setItems(location);
                    cmbStudyLocations.getSelectionModel().selectFirst();

                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterStudyRequiredDetails(rowStudySuggestedRequiredList) <= 0) {
                        studySuggestedRequiredBEAN.setEnquiry_id(ENQUIRY_ID);
                        if (studySuggestedRequiredBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateStudyDetails(studySuggestedRequiredBEAN);

                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertStudyDetails(studySuggestedRequiredBEAN);
                        }
                        StudySuggestedRequiredBEAN emptyRequiredBEAN = new StudySuggestedRequiredBEAN();
                        rowStudySuggestedRequiredList.add(emptyRequiredBEAN);
                        addStudyRequiredRows(rowStudySuggestedRequiredList);
                        //      choiceObsList.clear();
                        for (int i = 1; i <= listStudySuggestedRequired.size(); i++) {
                            if (!choiceObsList.contains("" + i)) {
                                choiceObsList.add("" + i);
                            }
                        }
                        refreshTabPane(0);

                        gridStudy.requestFocus();
//                        tabPaneProgramRequired.setPrefHeight(tabPaneProgramRequired.getHeight() + 20);

                    } else {
                        /* ====================== validation messages ====================== */
                        //   showPopUpMessages("Please fill all the fields required in emquiry assessment study required section ..", cmbAge);
                        showAlerts.showError("All the fields required in study required section !", cmbAge);
                    }

                }
            });

            btnStudyClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (studySuggestedRequiredBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteStudyDetails(studySuggestedRequiredBEAN.getRowId());
                        }
                        rowStudySuggestedRequiredList.remove(studySuggestedRequiredBEAN);
                        if (rowStudySuggestedRequiredList.size() <= 0) {
                            StudySuggestedRequiredBEAN emptyRequiredBEAN = new StudySuggestedRequiredBEAN();
                            rowStudySuggestedRequiredList.add(emptyRequiredBEAN);
                        }
                        addStudyRequiredRows(rowStudySuggestedRequiredList);
                        refreshTabPane(0);

                        gridStudy.requestFocus();
                        if (rowStudySuggestedRequiredList.size() > 1) {
//                            tabPaneProgramRequired.setPrefHeight(tabPaneProgramRequired.getHeight() - 20);
                        }

                    }
                }
            }
            );

            //bind with bean
//            Bindings.bindBidirectional(cmbStudy.valueProperty(), studySuggestedRequiredBEAN.studyProperty());
            Bindings.bindBidirectional(cmbCountryy.valueProperty(), studySuggestedRequiredBEAN.countryProperty());
            Bindings.bindBidirectional(cmbPrgmLevel.valueProperty(), studySuggestedRequiredBEAN.program_levelProperty());
            Bindings.bindBidirectional(cmbPrgmField.valueProperty(), studySuggestedRequiredBEAN.program_fieldProperty());
            Bindings.bindBidirectional(cmbIntake.valueProperty(), studySuggestedRequiredBEAN.intakeProperty());
            Bindings.bindBidirectional(cmbInstitution.valueProperty(), studySuggestedRequiredBEAN.institutionProperty());
            Bindings.bindBidirectional(cmbStudyLocations.valueProperty(), studySuggestedRequiredBEAN.locationProperty());
            Bindings.bindBidirectional(cmbStudyCurency.valueProperty(), studySuggestedRequiredBEAN.currencyProperty());
            Bindings.bindBidirectional(cmbStudyAmnt.valueProperty(), studySuggestedRequiredBEAN.amountProperty());
            Bindings.bindBidirectional(cmbFeeType.valueProperty(), studySuggestedRequiredBEAN.sem_feesProperty());
            Bindings.bindBidirectional(cmbProgmChoice.valueProperty(), studySuggestedRequiredBEAN.choiceProperty());
//            Bindings.bindBidirectional(txtStudyComents.textProperty(), studySuggestedRequiredBEAN.remarksProperty());

            HBox hBoxApeProgramFee = new HBox();

            hBoxApeProgramFee.getChildren()
                    .addAll(cmbStudyCurency, cmbStudyAmnt, cmbFeeType);
            hBoxApeProgramFee.setStyle(
                    "-fx-padding:2");
            if (rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) == 0) {
                gridStudy.add(new Label("Qualification Level"), 0, 0);
                gridStudy.add(new Label("Qualification Field"), 1, 0);
                gridStudy.add(new Label("Country"), 2, 0);
                gridStudy.add(new Label("Location"), 3, 0);
                gridStudy.add(new Label("Fee Type"), 4, 0);
                gridStudy.add(new Label("Currency"), 5, 0);
                gridStudy.add(new Label("Amount"), 6, 0);
                gridStudy.add(new Label("Intake"), 7, 0);
                gridStudy.add(new Label("Institution"), 8, 0);
                gridStudy.add(new Label("Choice"), 9, 0);

            }
            gridStudy.add(cmbPrgmLevel, 0, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbPrgmField, 1, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbCountryy, 2, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbStudyLocations, 3, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbFeeType, 4, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbStudyCurency, 5, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbStudyAmnt, 6, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbIntake, 7, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbInstitution, 8, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(cmbProgmChoice, 9, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
            gridStudy.add(hBoxButtons, 10, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);

        }
    }

    //<<===========Method for initialize dynamic Controls======================>>
    /**
     *
     */
    public void initDynamicSpouseTertieryControls() {
//<--------------------------------------Initialize teritary controls------------------------------>

        listAssesmentSpouseTertiaryBEAN = SpouseCrudDAO.getSpouseQualDetails((counselorDetailsBEAN.getEnquiryID()));
        spouseQualGrid.getChildren().removeAll(spouseQualificationFieldCmb);
        spouseQualGrid.getChildren().removeAll(spousQualificationCmb);

        if (listAssesmentSpouseTertiaryBEAN.size() > 0) {
            addSpouseQualGridRows();
        } else {
            AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
            listAssesmentSpouseTertiaryBEAN.add(emptyBEAN);
            addSpouseQualGridRows();

        }
    }

    //<---------------------------Method for add dynamic row for spouse qulifications----------------------->
    /**
     *
     * @param assesmentTertiaryBEANs
     */
    public void addSpouseQualGridRows() {
        spouseQualGrid.getChildren().clear();
        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : listAssesmentSpouseTertiaryBEAN) {
            assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox spouseQualiFieldCmb = new ComboBox();
            spouseQualiFieldCmb.setMinWidth(160);
            spouseQualiFieldCmb.setMaxWidth(160);
            spouseQualiFieldCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(spouseQualiFieldCmb);
            ComboBox spouseQualiLevelCmb = new ComboBox();
            spouseQualiLevelCmb.setMinWidth(148);
            spouseQualiLevelCmb.setMaxWidth(148);
            spouseQualiLevelCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(spouseQualiLevelCmb);
            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);

            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterSpouseQualification(listAssesmentSpouseTertiaryBEAN) <= 0) {
                        assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentTertiaryBEAN.getRowId() != null) {
                            SpouseCrudDAO.updateSpouseQualDetails(assesmentTertiaryBEAN);
                        } else {
                            SpouseCrudDAO.insertSpouseQualDetails(assesmentTertiaryBEAN);
                        }
                        AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
                        listAssesmentSpouseTertiaryBEAN.add(emptyBEAN);
                        addSpouseQualGridRows();
                    } else {
                        showAlerts.showError("All Fields Required In Spouse Qualifications!", spouseQualGrid);
                    }
                    spouseQualGrid.requestFocus();
                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        listAssesmentSpouseTertiaryBEAN.remove(assesmentTertiaryBEAN);
                        if (assesmentTertiaryBEAN.getRowId() != null) {
                            SpouseCrudDAO.deleteSpouseQualDetails(assesmentTertiaryBEAN.getRowId());
                        }
                        if (listAssesmentSpouseTertiaryBEAN.size() > 0) {
                            addSpouseQualGridRows();
                        } else {
                            AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
                            listAssesmentSpouseTertiaryBEAN.add(emptyBEAN);
                            addSpouseQualGridRows();
                        }
                        spouseQualGrid.requestFocus();
                    }
                }
            });
            ObservableList listSpouseProgram = FXCollections.observableArrayList();

            listSpouseProgram.addAll(programLevelList);
            listSpouseProgram.addAll("SSLC", "PLUS2");
            spouseQualiLevelCmb.setItems(listSpouseProgram);
            spouseQualiLevelCmb.valueProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        if (newValue.toString().equalsIgnoreCase("SSLC") || newValue.toString().equalsIgnoreCase("PLUS2")) {
                            spouseQualiFieldCmb.setPromptText("Select Medium Of Instruction");
                            spouseQualiFieldCmb.getItems().clear();
                            spouseQualiFieldCmb.setItems(RetrieveStaticMasterDAO.getLanguage());
                        } else {
                            spouseQualiLevelCmb.setPromptText("Select Field");
                            List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                            ObservableList programFieldlList2 = FXCollections.observableArrayList();
                            for (String s : programField) {
                                programFieldlList2.add(s);
                            }
                            spouseQualiFieldCmb.setItems(programFieldlList2);
                            spouseQualiFieldCmb.getSelectionModel().selectFirst();
                        }
                    }
                }
            });

            spouseFieldCombos.add(spouseQualiFieldCmb);
            spouseLevelCombos.add(spouseQualiLevelCmb);

            if (listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN) == 0) {
                spouseQualGrid.add(new Label("Level"), 0, 0);
                spouseQualGrid.add(new Label("Field"), 1, 0);
            }

            Bindings.bindBidirectional(spouseFieldCombos.get(listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN)).valueProperty(), assesmentTertiaryBEAN.tertieryFieldProperty());
            Bindings.bindBidirectional(spouseLevelCombos.get(listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN)).valueProperty(), assesmentTertiaryBEAN.tertieryLevelProperty());

            spouseQualGrid.add(spouseLevelCombos.get(listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN)), 0, listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
            spouseQualGrid.add(spouseFieldCombos.get(listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN)), 1, listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            spouseQualGrid.add(hBox, 2, listAssesmentSpouseTertiaryBEAN.indexOf(assesmentTertiaryBEAN) + 1);
//            spouseQualGrid.add(btnClose, 3, ROW_COUNT_SPOUSE_QUAL);
            // JOptionPane.showMessageDialog(null, masterOtherQualifyList.size());
        }
    }

    //<---------------------------------Method for spouse qualification validation-------------------->
    /**
     *
     * @param tertiaryBEAN
     * @return
     */
    public boolean spouseQualificationValidation(AssesmentTertiaryBEAN tertiaryBEAN) {
        boolean f = true;
        if (tertiaryBEAN.getTertieryField() == null) {
            f = false;
        } else if (tertiaryBEAN.getTertieryLevel() == null) {
            f = false;
        }
        return f;
    }

    /**
     *
     * @param AssesmentTertiaryBEAN
     * @return
     */
    public int validateMasterSpouseQualification(List<AssesmentTertiaryBEAN> AssesmentTertiaryBEAN) {
        int count = 0;
        for (AssesmentTertiaryBEAN validateAN : AssesmentTertiaryBEAN) {
            if (spouseQualificationValidation(validateAN)) {

            } else {
                count++;
            }

        }
        return count;
    }

    //<-----------------------------Initialize spouse experience controls----------------------------->
    /**
     *
     */
    public void initDynamicSpouseExperience() {

        listAssesmentSpouseExpBEAN = SpouseCrudDAO.getSpouseExpDetails(ENQUIRY_ID);
        spouseWorkExpGrid.getChildren().removeAll(spouseProfessionCmb);
        spouseWorkExpGrid.getChildren().removeAll(durationCmb);

        if (listAssesmentSpouseExpBEAN.size() > 0) {
            addSpouseExpGridRows();
        } else {
            AssesmentSpouseExpBEAN emptyBEAN = new AssesmentSpouseExpBEAN();
            listAssesmentSpouseExpBEAN.add(emptyBEAN);
            addSpouseExpGridRows();
        }
    }
//<------------------------------Initialize spouse Other test controls-------------------------------------->

    /**
     *
     */
    public void initSpouseOtherTestControls() {
        listAssesmentLanguageTest = SpouseCrudDAO.getSpouseLanuageTestDetails(ENQUIRY_ID);
        spouseLangTestGrid.getChildren().clear();
        if (listAssesmentLanguageTest.size() > 0) {
            addSpouseLanguageTestGridRows();
        } else {
            AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
            listAssesmentLanguageTest.add(emptyBEAN);
            addSpouseLanguageTestGridRows();
        }
    }

    //<--------------------------------Initialize language test controls--------------------------------------->
    /**
     *
     */
    public void initTeritaryLanguageTestControls() {
        listLanguageTestBEAN = LanguageTestCrudDAO.getTeritaryLanuageTestData(ENQUIRY_ID);
        if (listLanguageTestBEAN.size() > 0) {
            addLanguageTestGridRows();
        } else {
            AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
            listLanguageTestBEAN.add(emptyBEAN);
            addLanguageTestGridRows();
        }
    }

    //<---------------------------Method for add dynamic row for Language Test Details----------------------->
    /**
     *
     * @param assesmentLanguageTestBEANs
     */
    public void addLanguageTestGridRows() {
        languageTestGrid.getChildren().clear();
        for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : listLanguageTestBEAN) {
            assesmentLanguageTestBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbTestOption = new ComboBox();
//            cmbTestOption.setMinWidth(145);
            cmbTestOption.getStyleClass().add("button-service");
            cmbTestOption.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbTestOption);
            ComboBox cmbTestStatus = new ComboBox();
//            cmbTestStatus.setMinWidth(145);
            cmbTestStatus.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbTestStatus);
            cmbTestStatus.getStyleClass().add("button-service");

            ComboBox cmbReading = new ComboBox();
//            cmbReading.setMinWidth(145);
            cmbReading.setPromptText("Reading");
            ComboBoxJumpToChar.jumpToChar(cmbReading);
            cmbReading.getStyleClass().add("button-service");
            ComboBox cmbWriting = new ComboBox();
//            cmbWriting.setMinWidth(145);
            cmbWriting.getStyleClass().add("button-service");
            cmbWriting.setPromptText("Writing");
            ComboBoxJumpToChar.jumpToChar(cmbWriting);
            ComboBox cmbSpeaking = new ComboBox();
//            cmbSpeaking.setMinWidth(145);
            cmbSpeaking.getStyleClass().add("button-service");
            cmbSpeaking.setPromptText("Speaking");
            ComboBoxJumpToChar.jumpToChar(cmbSpeaking);
            ComboBox cmbListening = new ComboBox();
//            cmbListening.setMinWidth(145);
            cmbListening.getStyleClass().add("button-service");
            cmbListening.setPromptText("Listening");

            ComboBoxJumpToChar.jumpToChar(cmbListening);

            ComboBox cmbOverall = new ComboBox();
//            cmbOverall.setMinWidth(145);
            cmbOverall.getStyleClass().add("button-service");
            cmbOverall.setPromptText("Overall");
            ComboBoxJumpToChar.jumpToChar(cmbOverall);

            cmbTestOption.setMaxWidth(Double.MAX_VALUE);
            cmbTestStatus.setMaxWidth(Double.MAX_VALUE);
            cmbReading.setMaxWidth(Double.MAX_VALUE);
            cmbWriting.setMaxWidth(Double.MAX_VALUE);
            cmbSpeaking.setMaxWidth(Double.MAX_VALUE);
            cmbListening.setMaxWidth(Double.MAX_VALUE);
            cmbOverall.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(cmbTestOption, Priority.ALWAYS);
            GridPane.setHgrow(cmbTestStatus, Priority.ALWAYS);
            GridPane.setHgrow(cmbReading, Priority.ALWAYS);
            GridPane.setHgrow(cmbWriting, Priority.ALWAYS);
            GridPane.setHgrow(cmbSpeaking, Priority.ALWAYS);
            GridPane.setHgrow(cmbListening, Priority.ALWAYS);
            GridPane.setHgrow(cmbOverall, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText("");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterSpouseAndTerLangTest(listLanguageTestBEAN) <= 0) {
                        if (assesmentLanguageTestBEAN.getRowId() != null) {
                            LanguageTestCrudDAO.updateLanguageTestDetails(ENQUIRY_ID, assesmentLanguageTestBEAN);

                        } else {
                            LanguageTestCrudDAO.insertLanguageTestDetails(assesmentLanguageTestBEAN);
                        }
                        AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                        listLanguageTestBEAN.add(emptyBEAN);
                        addLanguageTestGridRows();
                        languageTestGrid.requestFocus();
                    } else {
                        showAlerts.showError("All Fields Required In Teritiary Language Test!", languageTestGrid);
                    }

                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(cmbAssStatus.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        listLanguageTestBEAN.remove(assesmentLanguageTestBEAN);
                        if (listLanguageTestBEAN.size() > 0) {
                            addLanguageTestGridRows();
                        } else {
                            AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                            listLanguageTestBEAN.add(emptyBEAN);
                            addLanguageTestGridRows();
                        }
                        if (assesmentLanguageTestBEAN.getRowId() != null) {
                            LanguageTestCrudDAO.deleteTeritaryLanguageTestDetails(assesmentLanguageTestBEAN.getRowId());
                        }
                        languageTestGrid.requestFocus();
                    }
                }
            });

            cmbTestOption.setItems(otherTest);
            cmbTestStatus.setItems(testStatus);
            cmbReading.setItems(testScore);
            cmbWriting.setItems(testScore);
            cmbSpeaking.setItems(testScore);
            cmbListening.setItems(testScore);
            cmbOverall.setItems(testScore);

            Bindings.bindBidirectional(cmbTestOption.valueProperty(), assesmentLanguageTestBEAN.languageTestProperty());
            Bindings.bindBidirectional(cmbReading.valueProperty(), assesmentLanguageTestBEAN.readingProperty());
            Bindings.bindBidirectional(cmbWriting.valueProperty(), assesmentLanguageTestBEAN.writingProperty());
            Bindings.bindBidirectional(cmbSpeaking.valueProperty(), assesmentLanguageTestBEAN.speakingProperty());
            Bindings.bindBidirectional(cmbListening.valueProperty(), assesmentLanguageTestBEAN.listeningProperty());
            Bindings.bindBidirectional(cmbOverall.valueProperty(), assesmentLanguageTestBEAN.overallProperty());
            if (listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) == 0) {
                languageTestGrid.add(new Label("Test"), 0, 0);
                languageTestGrid.add(new Label("Reading"), 1, 0);
                languageTestGrid.add(new Label("Writing"), 2, 0);
                languageTestGrid.add(new Label("Speaking"), 3, 0);
                languageTestGrid.add(new Label("Listening"), 4, 0);
                languageTestGrid.add(new Label("Overall Score"), 5, 0);
            }
            languageTestGrid.add(cmbTestOption, 0, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
//            languageTestGrid.add(cmbTestStatus, 1, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN)+1);
            languageTestGrid.add(cmbReading, 1, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
            languageTestGrid.add(cmbWriting, 2, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
            languageTestGrid.add(cmbSpeaking, 3, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
            languageTestGrid.add(cmbListening, 4, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
            languageTestGrid.add(cmbOverall, 5, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            languageTestGrid.add(hBox, 6, listLanguageTestBEAN.indexOf(assesmentLanguageTestBEAN) + 1);
//            languageTestGrid.add(btnClose, 8, ROW_COUNT_LANGUAGE);
        }
    }

    //<-------------------------------Method for spouse Language test validation-------------------->
    /**
     *
     * @param languageTestBEAN
     * @return
     */
    public boolean validateSpouseAndTerteryLangTest(AssesmentLanguageTestBEAN languageTestBEAN) {
        boolean f = true;
        if (languageTestBEAN.getLanguageTest() == null) {
            f = false;
        } else if (languageTestBEAN.getOverall() == null) {
            f = false;
        }
        return f;
    }

    /**
     *
     * @param masterLanguageTestBEANList
     * @return
     */
    public int validateMasterSpouseAndTerLangTest(List<AssesmentLanguageTestBEAN> masterLanguageTestBEANList) {
        int count = 0;
        for (AssesmentLanguageTestBEAN languageTestBEAN : masterLanguageTestBEANList) {
            if (validateSpouseAndTerteryLangTest(languageTestBEAN)) {

            } else {
                count++;
            }
        }
        return count;

    }

//<------------------------------Initialize children and relation details-------------------------------------->
    /**
     *
     */
    public void initDynamicChildrenControls() {
        childBEANList = ChildrenDAO.retriveChildrenDetails(ENQUIRY_ID);
        gridChildren.getChildren().clear();
        if (childBEANList.size() > 0) {
            addChildrenRows();
        } else {
            AssessmentChildBEAN emptyBEAN = new AssessmentChildBEAN();
            childBEANList.add(emptyBEAN);
            addChildrenRows();
        }
//        btnChildRelativeSave.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                for (AssessmentChildBEAN saveChildBEAN : masterChildrenList) {
//                    if (validateChildren(saveChildBEAN).equalsIgnoreCase("true")) {
//                        if (saveChildBEAN.getRow_id() != null) {
//                            ChildrenDAO.updateChildrenDetails(saveChildBEAN);
//                        } else {
//                            ChildrenDAO.insertChildrenDetails(saveChildBEAN);
//                        }
//                    }
//                }
//
//            }
//        });

    }
    //<------------------------------Method for creating  children dynamic rows-------------------------------------->

    /**
     *
     * @param assessmentChildList
     */
    public void addChildrenRows() {
        gridChildren.getChildren().clear();
        for (AssessmentChildBEAN assessmentChildBEAN : childBEANList) {
            assessmentChildBEAN.setEnquiry_id(ENQUIRY_ID);
            TextField txtChildName = new CustomTextField();
            txtChildName.setMinWidth(301);
            txtChildName.setPromptText("Name");
            ComboBox cmbChild = new ComboBox(children);
            cmbChild.setPromptText("Select Child");
            ComboBoxJumpToChar.jumpToChar(cmbChild);
            ComboBox cmbGender = new ComboBox(gender);
            cmbGender.setMinWidth(150);
            cmbGender.setPromptText("gender");
            ComboBoxJumpToChar.jumpToChar(cmbGender);
            ComboBox cmbChildAge = new ComboBox(age);
            cmbChildAge.setPromptText("Age");
            ComboBoxJumpToChar.jumpToChar(cmbChildAge);
            cmbChildAge.setMinWidth(100);
            ComboBox cmbRelativCountry = new ComboBox(country);
            cmbRelativCountry.setMinWidth(120);
            cmbRelativCountry.setPromptText("Country");
            ComboBoxJumpToChar.jumpToChar(cmbRelativCountry);
            ComboBox cmbRelation = new ComboBox(relationShipList);
            cmbRelation.setMinWidth(120);
            cmbRelation.setPromptText("Relation");
            ComboBoxJumpToChar.jumpToChar(cmbRelation);
            GridPane.setHgrow(txtChildName, Priority.NEVER);
            GridPane.setHgrow(cmbRelativCountry, Priority.NEVER);
            GridPane.setHgrow(cmbRelation, Priority.NEVER);
            GridPane.setHgrow(cmbChild, Priority.NEVER);
            GridPane.setHgrow(cmbChildAge, Priority.NEVER);

            cmbChild.setMaxWidth(Double.MAX_VALUE);
            cmbChildAge.setMaxWidth(Double.MAX_VALUE);
            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnChildClose = new Button();
            btnChildClose.setText(" ");
            btnChildClose.setPrefWidth(32);
            btnChildClose.getStyleClass().add("close-button");

            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterChildren(childBEANList) <= 0) {
                        assessmentChildBEAN.setEnquiry_id(ENQUIRY_ID);
                        if (assessmentChildBEAN.getRow_id() != null) {
                            ChildrenDAO.updateChildrenDetails(assessmentChildBEAN);
                        } else {
                            ChildrenDAO.insertChildrenDetails(assessmentChildBEAN);
                        }
                        AssessmentChildBEAN emptyBEAN = new AssessmentChildBEAN();
                        childBEANList.add(emptyBEAN);
                        addChildrenRows();
                    } else {
                        showAlerts.showError("All Fields Required In Child Details!", gridChildren);
                    }
                    gridChildren.requestFocus();
                }
            });
            btnChildClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assessmentChildBEAN.getRow_id() != null) {
                            ChildrenDAO.deleteChildrenDetails(assessmentChildBEAN.getRow_id());
                        }
                        childBEANList.remove(assessmentChildBEAN);
                        if (childBEANList.size() > 0) {
                            addChildrenRows();
                        } else {
                            AssessmentChildBEAN emptyBEAN = new AssessmentChildBEAN();
                            childBEANList.add(emptyBEAN);
                            addChildrenRows();
                        }
                        gridChildren.requestFocus();

                    }
                }
            });

            if (childBEANList.indexOf(assessmentChildBEAN) == 0) {
                gridChildren.add(new Label("Child"), 0, 0);
                gridChildren.add(new Label("Age"), 1, 0);
            }

            Bindings.bindBidirectional(cmbChild.valueProperty(), assessmentChildBEAN.nameProperty());
            Bindings.bindBidirectional(cmbGender.valueProperty(), assessmentChildBEAN.sexProperty());
            Bindings.bindBidirectional(cmbChildAge.valueProperty(), assessmentChildBEAN.ageProperty());
            Bindings.bindBidirectional(cmbRelativCountry.valueProperty(), assessmentChildBEAN.country_relativeProperty());
            Bindings.bindBidirectional(cmbRelation.valueProperty(), assessmentChildBEAN.relationProperty());

            gridChildren.add(cmbChild, 0, childBEANList.indexOf(assessmentChildBEAN) + 1);
            gridChildren.add(cmbChildAge, 1, childBEANList.indexOf(assessmentChildBEAN) + 1);
//            gridChildren.add(cmbChildAge, 2, childBEANList.indexOf(assessmentChildBEAN) + 1);
//            gridChildren.add(cmbRelativCountry, 3, childBEANList.indexOf(assessmentChildBEAN) + 1);
//            gridChildren.add(cmbRelation, 4, childBEANList.indexOf(assessmentChildBEAN) + 1);
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(btnPlus, btnChildClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            gridChildren.add(hBox, 2, childBEANList.indexOf(assessmentChildBEAN) + 1);

        }

    }
    //<--------------------------Method for validating children details  ----------------------------------------->

    /**
     *
     * @param validateChildBEAN
     * @return
     */
    public String validateChildren(AssessmentChildBEAN validateChildBEAN) {
        if (validateChildBEAN.getAge() == null || validateChildBEAN.getName() == null) {
            return "All Fields Required in Child Information!";
        }
        return "true";
    }

    /**
     *
     * @param validateRelativeBEAN
     * @return
     */
    public String validateRelative(RelativeBEAN validateRelativeBEAN) {
        if (validateRelativeBEAN.getCountryOfRelative() == null || validateRelativeBEAN.getRelationship() == null) {
            return "All Fields Required in Relative Details!";
        }
        return "true";
    }

    /**
     *
     * @param validateLanguageSkillsBEAN
     * @return
     */
    public String validateLanguageSkills(LanguageSkillsBEAN validateLanguageSkillsBEAN) {
        if (validateLanguageSkillsBEAN.getLanguage() == null || validateLanguageSkillsBEAN.getRead() == null || validateLanguageSkillsBEAN.getWrite() == null || validateLanguageSkillsBEAN.getSpeak() == null) {
            return "All Fields Required in Language Skills!";
        }
        return "true";
    }

    /**
     *
     * @param validateLanguageSkillsBEAN
     * @return
     */
    public String validateTechnicalSkills(TechnicalSkillsBEAN validateLanguageSkillsBEAN) {
        if (validateLanguageSkillsBEAN.getTechnology() == null || validateLanguageSkillsBEAN.getKnowledgeLevel() == null) {
            return "All Fields Required in Technical Skills!";
        }
        return "true";
    }

    /**
     *
     * @param validateOtherSkillsBEAN
     * @return
     */
    public String validateOtherSkills(OtherSkillBEAN validateOtherSkillsBEAN) {
        if (validateOtherSkillsBEAN.getCategory() == null || validateOtherSkillsBEAN.getOtherSkill() == null) {
            return "All Fields Required in Other Skills!";
        }
        return "true";
    }

    /**
     *
     * @param validateAdmissionTestBEAN
     * @return
     */
    public String validateAdmissionTest(AdmissionTestBEAN validateAdmissionTestBEAN) {
        if (validateAdmissionTestBEAN.getTest() == null || validateAdmissionTestBEAN.getOverAllScore() == null || validateAdmissionTestBEAN.getReading() == null || validateAdmissionTestBEAN.getWriting() == null || validateAdmissionTestBEAN.getListening() == null || validateAdmissionTestBEAN.getSpeaking() == null) {
            return "All Fields Required in Admission Test!";
        }
        return "true";
    }

    /**
     *
     * @param validatelanguageTestBEAN
     * @return
     */
    public String validateLanguageTest(AssesmentLanguageTestBEAN validatelanguageTestBEAN) {
        String s = "true";
        if (validatelanguageTestBEAN.getLanguageTest() == null || validatelanguageTestBEAN.getOverall() == null || validatelanguageTestBEAN.getReading() == null || validatelanguageTestBEAN.getWriting() == null || validatelanguageTestBEAN.getListening() == null || validatelanguageTestBEAN.getSpeaking() == null) {
            s = "All fields required in Language Test!";
        }
        return s;
    }

    /**
     *
     * @param validateChildBEANs
     * @return
     */
    public int validateMasterChildren(List<AssessmentChildBEAN> validateChildBEANs) {
        int count = 0;
        for (AssessmentChildBEAN validateChildBEAN : validateChildBEANs) {
            if (validateChildren(validateChildBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param validateRelativeBEANs
     * @return
     */
    public int validateMasterRelative(List<RelativeBEAN> validateRelativeBEANs) {
        int count = 0;
        for (RelativeBEAN validateRelativeBEAN : validateRelativeBEANs) {
            if (validateRelative(validateRelativeBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param validateSkillsBEANs
     * @return
     */
    public int validateLanguageSkill(List<LanguageSkillsBEAN> validateSkillsBEANs) {
        int count = 0;
        for (LanguageSkillsBEAN languageSkillsBEAN : validateSkillsBEANs) {
            if (validateLanguageSkills(languageSkillsBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param validateSkillsBEANs
     * @return
     */
    public int validateTechnicalSkills(List<TechnicalSkillsBEAN> validateSkillsBEANs) {
        int count = 0;
        for (TechnicalSkillsBEAN technicalSkillsBEAN : validateSkillsBEANs) {
            if (validateTechnicalSkills(technicalSkillsBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param validateSkillsBEANs
     * @return
     */
    public int validateOtherSkills(List<OtherSkillBEAN> validateSkillsBEANs) {
        int count = 0;
        for (OtherSkillBEAN otherSkillBEAN : validateSkillsBEANs) {
            if (validateOtherSkills(otherSkillBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param validateListAdmissionTest
     * @return
     */
    public int validateAdmissionTest(List<AdmissionTestBEAN> validateListAdmissionTest) {
        int count = 0;
        for (AdmissionTestBEAN admissionTestBEAN : validateListAdmissionTest) {
            if (validateAdmissionTest(admissionTestBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /**
     *
     * @param languageTestBEANList
     * @return
     */
    public int validateMasterLanguageTest(List<AssesmentLanguageTestBEAN> languageTestBEANList) {
        int count = 0;
        for (AssesmentLanguageTestBEAN languageTestBEAN : languageTestBEANList) {
            if (validateLanguageTest(languageTestBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }

        }
        return count;

    }

    //<--------------------------Method for work experience of candidate  -------------------------------------->
    /**
     *
     */
    public void initWorkExperianceDetails() {
        gridWorkExp.getChildren().clear();
        listWorkExpBEAN = WorkExperienceDAO.getWorkExperience(ENQUIRY_ID);
        if (listWorkExpBEAN.size() > 0) {
            addWorkExperienceGridRows();
        } else {
            WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
            listWorkExpBEAN.add(emptyExperienceBEAN);
            addWorkExperienceGridRows();
        }

    }

    /*-----------------------------------Initiaalize the work details of the candidate--------------------------------------*/
    /**
     *
     */
    public void initWorkDetails() {
        workGrid.getChildren().clear();
        choiceObsListWork.clear();
        listWorkRequired = ProgramSuggestedRequiredDAO.getWorkDetails(ENQUIRY_ID);
        for (int i = 1; i <= listWorkRequired.size() + 1; i++) {
            if (!choiceObsListWork.contains("" + i)) {
                choiceObsListWork.add("" + i);
            }
        }

        if (listWorkRequired.size() > 0) {
            addWorkGridRows();
        } else {
            AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
            listWorkRequired.add(assesmentWorkBEAN);
            addWorkGridRows();
        }

    }

    /*----------------------Initialize the trainig details of the candidate--------------------------*/
    /**
     *
     */
    public void initTrainingDetails() {
        trainingGrid.getChildren().clear();
        choiceObsListTraining.clear();
        listTrainingRequired = ProgramSuggestedRequiredDAO.getTrainingDetails(ENQUIRY_ID);
        for (int i = 1; i <= listTrainingRequired.size() + 1; i++) {
            if (!choiceObsListTraining.contains("" + i)) {
                choiceObsListTraining.add("" + i);
            }
        }

        if (listTrainingRequired.size() > 0) {
            addTrainingGridRows();
        } else {
            AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
            listTrainingRequired.add(assesmentTrainingBEAN);
            addTrainingGridRows();
        }
    }

    /*-----------------Dynamically add rows for training details------------------------------*/
    /**
     *
     * @param assesmentTrainingBEANs
     */
    public void addTrainingGridRows() {
        trainingGrid.getChildren().clear();
        for (AssesmentTrainingBEAN assesmentTrainingBEAN : listTrainingRequired) {
            assesmentTrainingBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox trainingCmb = new ComboBox(training);
//            trainingCmb.setMinWidth(100);
            trainingCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(trainingCmb);
            ComboBox batchCmb = new ComboBox(baches);
//            batchCmb.setMinWidth(120);
            batchCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(batchCmb);
            ComboBox timingCmb = new ComboBox(timing);
//            timingCmb.setMinWidth(120);
            timingCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(trainingCmb);
            ComboBox trainingDurationCmb = new ComboBox(duration);
//            trainingDurationCmb.setMinWidth(162);
            trainingDurationCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(trainingDurationCmb);
            DatePicker joiningDatePicker = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(joiningDatePicker);
            joiningDatePicker.setPromptText("Proposed Joining");
//            joiningDatePicker.setMinWidth(120);

            DatePicker classFromDatePicker = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(classFromDatePicker);
            classFromDatePicker.setPromptText("Class From");
//            classFromDatePicker.setMinWidth(120);
            DatePicker classToDatePicker = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(classToDatePicker);
            classToDatePicker.setPromptText("Class To");
//            classToDatePicker.setMinWidth(120);
            ComboBox trainingChoiceCmb = new ComboBox(choiceObsListTraining);
//            trainingChoiceCmb.setMinWidth(80);
            trainingChoiceCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(trainingChoiceCmb);
            GridPane.setHgrow(trainingCmb, Priority.ALWAYS);
            GridPane.setHgrow(batchCmb, Priority.ALWAYS);
            GridPane.setHgrow(timingCmb, Priority.ALWAYS);
            GridPane.setHgrow(trainingDurationCmb, Priority.ALWAYS);
            GridPane.setHgrow(joiningDatePicker, Priority.ALWAYS);
            GridPane.setHgrow(classFromDatePicker, Priority.ALWAYS);
            GridPane.setHgrow(classToDatePicker, Priority.ALWAYS);
            GridPane.setHgrow(classToDatePicker, Priority.ALWAYS);
            GridPane.setHgrow(trainingChoiceCmb, Priority.ALWAYS);
//            fromDatePicker.setPrefWidth(200);
            classFromDatePicker.setMaxWidth(Double.MAX_VALUE);
            trainingCmb.setMaxWidth(Double.MAX_VALUE);
            batchCmb.setMaxWidth(Double.MAX_VALUE);
            timingCmb.setMaxWidth(Double.MAX_VALUE);
            trainingDurationCmb.setMaxWidth(Double.MAX_VALUE);
            joiningDatePicker.setMaxWidth(Double.MAX_VALUE);
            classToDatePicker.setMaxWidth(Double.MAX_VALUE);
            classToDatePicker.setMaxWidth(Double.MAX_VALUE);
            trainingChoiceCmb.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setPrefWidth(32);
            btnPlus.setText(" ");
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setPrefWidth(32);
            btnClose.setText(" ");
            btnClose.getStyleClass().add("close-button");

            joiningDatePicker.setEditable(false);
            joiningDatePicker.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactory
                    = new Callback<DatePicker, DateCell>() {
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
            joiningDatePicker.setDayCellFactory(dayCellFactory);
            classFromDatePicker.setEditable(false);
            classFromDatePicker.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactoryfrom
                    = new Callback<DatePicker, DateCell>() {
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
            classFromDatePicker.setDayCellFactory(dayCellFactoryfrom);

            classToDatePicker.setEditable(false);
            classToDatePicker.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactoryfroms
                    = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(
                                    classFromDatePicker.getValue().plusDays(1))) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };
            classToDatePicker.setDayCellFactory(dayCellFactoryfroms);
            classToDatePicker.setValue(classFromDatePicker.getValue().plusDays(1));
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterTrainingData(listTrainingRequired) <= 0) {
                        assesmentTrainingBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentTrainingBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.updateTrainingDetails(assesmentTrainingBEAN);
                            /* ====================== Update Training Required ====================== */
                        } else {
                            /* ====================== Insert Training Required ====================== */
                            ProgramSuggestedRequiredDAO.insertTrainingDetails(assesmentTrainingBEAN);

                        }
                        AssesmentTrainingBEAN emptyRequiredBEAN = new AssesmentTrainingBEAN();
                        listTrainingRequired.add(emptyRequiredBEAN);
                        addTrainingGridRows();
                        //   choiceObsListTraining.clear();
                        for (int i = 1; i <= listTrainingRequired.size(); i++) {
                            if (!choiceObsListTraining.contains("" + i)) {
                                choiceObsListTraining.add("" + i);
                            }
                        }
                        refreshTabPane(3);

                        trainingGrid.requestFocus();

                    } else {
                        /* ====================== validation messages ====================== */
                        showAlerts.showError("All the fields required in training required section !", cmbAge);
                    }
                }
            }
            );

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentTrainingBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteTrainingDetails(assesmentTrainingBEAN.getRowId());

                        }
                        listTrainingRequired.remove(assesmentTrainingBEAN);
                        if (listTrainingRequired.size() <= 0) {
                            AssesmentTrainingBEAN emptyRequiredBEAN = new AssesmentTrainingBEAN();
                            listTrainingRequired.add(emptyRequiredBEAN);
                        }
                        addTrainingGridRows();
                        refreshTabPane(3);

                        trainingGrid.requestFocus();

                    }
                    if (listTrainingRequired.size() > 1) {
//                        tabPaneProgramRequired.setPrefHeight(tabPaneProgramRequired.getHeight() - 20);
                    }

                }
            }
            );

            Bindings.bindBidirectional(trainingCmb.valueProperty(), assesmentTrainingBEAN.trainingProperty());
            Bindings.bindBidirectional(batchCmb.valueProperty(), assesmentTrainingBEAN.batchProperty());
            Bindings.bindBidirectional(timingCmb.valueProperty(), assesmentTrainingBEAN.timingProperty());
            Bindings.bindBidirectional(trainingDurationCmb.valueProperty(), assesmentTrainingBEAN.durationProperty());
            Bindings.bindBidirectional(joiningDatePicker.valueProperty(), assesmentTrainingBEAN.fromDateProperty());
            Bindings.bindBidirectional(classFromDatePicker.valueProperty(), assesmentTrainingBEAN.classFromDateProperty());
            Bindings.bindBidirectional(classToDatePicker.valueProperty(), assesmentTrainingBEAN.classToDateProperty());

            Bindings.bindBidirectional(trainingChoiceCmb.valueProperty(), assesmentTrainingBEAN.choiceProperty());
//            Bindings.bindBidirectional(txtTrainingRemarks.textProperty(), assesmentTrainingBEAN.remmarksProperty());
            //  Bindings.bindBidirectional(assesmentTrainingBEAN.fromDateProperty(), fromDatePicker.getEditor().textProperty());

            HBox btnHBox = new HBox(5);
            btnHBox.getChildren().setAll(btnPlus, btnClose);
            if (listTrainingRequired.indexOf(assesmentTrainingBEAN) == 0) {
                trainingGrid.add(new Label("Course"), 0, 0);
                trainingGrid.add(new Label("Batch"), 1, 0);
                trainingGrid.add(new Label("Timing"), 2, 0);
                trainingGrid.add(new Label("Duration"), 3, 0);
                trainingGrid.add(new Label("Proposed Joining Date"), 4, 0);
                trainingGrid.add(new Label("From"), 5, 0);
                trainingGrid.add(new Label("To"), 6, 0);
                trainingGrid.add(new Label("Choice"), 7, 0);

            }

            trainingGrid.add(trainingCmb, 0, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(batchCmb, 1, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(timingCmb, 2, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(trainingDurationCmb, 3, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(joiningDatePicker, 4, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(classFromDatePicker, 5, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(classToDatePicker, 6, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(trainingChoiceCmb, 7, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);
            trainingGrid.add(btnHBox, 8, listTrainingRequired.indexOf(assesmentTrainingBEAN) + 1);

        }

    }

    /* ========================= validate Training details ========= */
    /**
     *
     * @param assesmentTrainingBEAN
     * @return
     */
    public String validateTrainingDetails(AssesmentTrainingBEAN assesmentTrainingBEAN) {
        String s = "true";
        if (assesmentTrainingBEAN.getTraining() == null || assesmentTrainingBEAN.getBatch() == null || assesmentTrainingBEAN.getTiming() == null || assesmentTrainingBEAN.getDuration() == null || assesmentTrainingBEAN.getChoice() == null || assesmentTrainingBEAN.getFromDate() == null || assesmentTrainingBEAN.getClassFromDate() == null || assesmentTrainingBEAN.getClassToDate() == null) {
            s = "All fields required !";
        }
        return s;
    }

    /**
     *
     * @param validateTrainingBEANs
     * @return
     */
    public int validateMasterTrainingData(List<AssesmentTrainingBEAN> validateTrainingBEANs) {
        int count = 0;
        for (AssesmentTrainingBEAN validateBEAN : validateTrainingBEANs) {
            if (validateTrainingDetails(validateBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;
    }

    //<---------------------------------------Method for initialize assesment migration details------------------->
    /**
     *
     */
    public void initMigrateDetails() {
        migrationGrid.getChildren().clear();
        choiceObsListMigrate.clear();

        listMigrateRequired = ProgramSuggestedRequiredDAO.getMigrationDetails(ENQUIRY_ID);
        for (int i = 1; i <= listMigrateRequired.size() + 1; i++) {
            if (!choiceObsListMigrate.contains("" + i)) {
                choiceObsListMigrate.add("" + i);
            }
        }
        if (listMigrateRequired.size() > 0) {
            addMigrateGridRows();
        } else {
            AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
            listMigrateRequired.add(assesmentMigrateBEAN);
            addMigrateGridRows();
        }

    }

    //<-------------------------Method for add migrate grid rows------------------------------>
    /**
     *
     * @param assesmentMigrateBEANs
     */
    public void addMigrateGridRows() {
        migrationGrid.getChildren().clear();
        for (AssesmentMigrateBEAN assesmentMigrateBEAN : listMigrateRequired) {
            assesmentMigrateBEAN.setMigrate("Migrate");
            assesmentMigrateBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox migrateCmb = new ComboBox(migrationList);
            migrateCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(migrateCmb);
            migrateCmb.setMinWidth(120);
            migrateCmb.setMaxWidth(120);
            ComboBox cmbMigrationCountry = new ComboBox(countryDAO.retrieveMasterAllCountries());
            cmbMigrationCountry.setMinWidth(180);
            cmbMigrationCountry.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbMigrationCountry);
            ComboBox cmbMigrationLocation = new ComboBox();
            cmbMigrationLocation.setMinWidth(180);
            cmbMigrationLocation.setPromptText("Select Location");
            ComboBoxJumpToChar.jumpToChar(cmbMigrationLocation);
            ComboBox cmbMigrationProfession = new ComboBox(profession);
            cmbMigrationProfession.setMinWidth(180);
            cmbMigrationProfession.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbMigrationProfession);
            ComboBox cmbMigrationCategory = new ComboBox(migrateCategory);
            cmbMigrationCategory.setMinWidth(180);
            cmbMigrationCategory.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbMigrationCategory);
            ComboBox cmbMigrationChoice = new ComboBox(choiceObsListMigrate);
            cmbMigrationChoice.setMinWidth(80);
            cmbMigrationChoice.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbMigrationChoice);
            ComboBox cmbWorkDuration = new ComboBox(duration);
            cmbWorkDuration.setMinWidth(162);
            cmbWorkDuration.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkDuration);
            GridPane.setHgrow(migrateCmb, Priority.ALWAYS);
            GridPane.setHgrow(cmbMigrationProfession, Priority.ALWAYS);
            GridPane.setHgrow(cmbMigrationCategory, Priority.ALWAYS);
            GridPane.setHgrow(cmbMigrationCountry, Priority.ALWAYS);
            GridPane.setHgrow(cmbMigrationLocation, Priority.ALWAYS);

            migrateCmb.setMaxWidth(Double.MAX_VALUE);
            cmbMigrationProfession.setMaxWidth(Double.MAX_VALUE);
            cmbMigrationCategory.setMaxWidth(Double.MAX_VALUE);
            cmbMigrationCountry.setMaxWidth(Double.MAX_VALUE);
            cmbMigrationLocation.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            HBox hBoxButtons = new HBox(5);
            hBoxButtons.getChildren().addAll(btnPlus, btnClose);
            hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
            cmbMigrationCountry.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbMigrationLocation.setItems(location);
                    cmbMigrationLocation.getSelectionModel().selectFirst();

                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterMigrateData(listMigrateRequired) <= 0) {
                        assesmentMigrateBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentMigrateBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateMigrateDetails(assesmentMigrateBEAN);
                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertMigrateDetails(assesmentMigrateBEAN);
                        }
                        AssesmentMigrateBEAN emptyRequiredBEAN = new AssesmentMigrateBEAN();
                        listMigrateRequired.add(emptyRequiredBEAN);
                        addMigrateGridRows();
//                        tabPaneProgramRequired.setPrefHeight(tabPaneProgramRequired.getHeight() + 20);
                        //  choiceObsListMigrate.clear();
                        for (int i = 1; i <= listMigrateRequired.size(); i++) {
                            if (!choiceObsListMigrate.contains("" + i)) {
                                choiceObsListMigrate.add("" + i);
                            }
                        }
                        refreshTabPane(2);
                        migrationGrid.requestFocus();
                    } else {
                        /* ====================== validation messages ====================== */
                        showAlerts.showError("All the fields required in migration required section !", cmbAge);

                    }
                }
            }
            );

            btnClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event
                ) {
//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
//                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//                            stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
//                            stage.initOwner(workCmb.getScene().getWindow());
//                            alert.showAndWait();
//                            if (alert.getResult() == ButtonType.YES) {
//                                if (assesmentMigrateBEAN.getRowId() != null) {
//                                    EnquiryAssessmentPersonDAO.deleteMigrateDetails(assesmentMigrateBEAN.getRowId());
//                                    migrationGrid.getChildren().clear();
//                                    masterMigrateList.clear();
//
//                                    List<AssesmentMigrateBEAN> migrateBEANList = EnquiryAssessmentPersonDAO.getMigrationDetails(ENQUIRY_ID);
//                                    if (migrateBEANList.size() > 0) {
//                                        addMigrateGridRows(migrateBEANList);
//                                    } else {
//                                        AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
//                                        List<AssesmentMigrateBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(assesmentMigrateBEAN);
//                                        addMigrateGridRows(emptyList);
//                                    }
//
//                                } else {
//                                    migrationGrid.getChildren().clear();
//                                    masterMigrateList.clear();
//                                    List<AssesmentMigrateBEAN> migrateBEANList = EnquiryAssessmentPersonDAO.getMigrationDetails(ENQUIRY_ID);
//                                    if (migrateBEANList.size() > 0) {
//                                        addMigrateGridRows(migrateBEANList);
//                                    } else {
//                                        AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
//                                        List<AssesmentMigrateBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(assesmentMigrateBEAN);
//                                        addMigrateGridRows(emptyList);
//                                    }
//                                }
//                                migrationGrid.requestFocus();
//                            }
//                            
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentMigrateBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteMigrateDetails(assesmentMigrateBEAN.getRowId());

                        }
                        listMigrateRequired.remove(assesmentMigrateBEAN);
                        if (listMigrateRequired.size() <= 0) {
                            AssesmentMigrateBEAN emptyRequiredBEAN = new AssesmentMigrateBEAN();
                            listMigrateRequired.add(emptyRequiredBEAN);
                        }

                        addMigrateGridRows();
                        refreshTabPane(2);
                        migrationGrid.requestFocus();
                    }
                    if (listMigrateRequired.size() > 1) {
//                        tabPaneProgramRequired.setPrefHeight(tabPaneProgramRequired.getHeight() - 20);
                    }

                }
            }
            );

            Bindings.bindBidirectional(migrateCmb.valueProperty(), assesmentMigrateBEAN.migrateProperty());
            Bindings.bindBidirectional(cmbMigrationProfession.valueProperty(), assesmentMigrateBEAN.professionProperty());
            Bindings.bindBidirectional(cmbMigrationCategory.valueProperty(), assesmentMigrateBEAN.categoryProperty());
            Bindings.bindBidirectional(cmbMigrationCountry.valueProperty(), assesmentMigrateBEAN.countryProperty());
            Bindings.bindBidirectional(cmbMigrationLocation.valueProperty(), assesmentMigrateBEAN.locationProperty());
            Bindings.bindBidirectional(cmbMigrationChoice.valueProperty(), assesmentMigrateBEAN.choiceProperty());

//            masterMigrateList.add(assesmentMigrateBEAN);
            if (listMigrateRequired.indexOf(assesmentMigrateBEAN) == 0) {
                //   migrationGrid.add(new Label("Program Level"), 0, 0);
                migrationGrid.add(new Label("Profession"), 0, 0);
                migrationGrid.add(new Label("Category"), 1, 0);
                migrationGrid.add(new Label("Country"), 2, 0);
                migrationGrid.add(new Label("Location/Provisional"), 3, 0);
                migrationGrid.add(new Label("Choice"), 4, 0);
            }

            //     migrationGrid.add(migrateCmb, 0, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            migrationGrid.add(cmbMigrationProfession, 0, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            migrationGrid.add(cmbMigrationCategory, 1, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            migrationGrid.add(cmbMigrationCountry, 2, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            migrationGrid.add(cmbMigrationLocation, 3, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            migrationGrid.add(cmbMigrationChoice, 4, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);
            migrationGrid.add(hBoxButtons, 5, listMigrateRequired.indexOf(assesmentMigrateBEAN) + 1);

//            migrationGrid.add(btnClose,
//                    5, ROW_COUNT_MIGRATE);
//            masterWorkExpList.add(workExperienceBEAN);
            // JOptionPane.showMessageDialog(null, masterOtherQualifyList.size());
        }

    }

    /* ========================= validate work experience ========= */
    /**
     *
     * @param assesmentMigrateBEAN
     * @return
     */
    public String validateMigrateDetails(AssesmentMigrateBEAN assesmentMigrateBEAN) {

        if (assesmentMigrateBEAN.getCountry() == null || assesmentMigrateBEAN.getLocation() == null || assesmentMigrateBEAN.getProfession() == null
                || assesmentMigrateBEAN.getCategory() == null || assesmentMigrateBEAN.getChoice() == null) {
            return "All fields required !";
        }
        return "true";
    }

    /**
     *
     * @param assesmentMigrateBEANs
     * @return
     */
    public int validateMasterMigrateData(List<AssesmentMigrateBEAN> assesmentMigrateBEANs) {
        int count = 0;
        for (AssesmentMigrateBEAN validateAssesmentMigrateBEAN : assesmentMigrateBEANs) {
            if (validateMigrateDetails(validateAssesmentMigrateBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    //<------------------------Method for add work  grid rows -------------------------->
    /**
     *
     * @param workExpBEANList
     */
    public void addWorkGridRows() {
        workGrid.getChildren().clear();
        for (AssesmentWorkBEAN assesmentWorkBEAN : listWorkRequired) {
            assesmentWorkBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbworkDy = new ComboBox(workList);
            assesmentWorkBEAN.setWork("Work");
            cmbworkDy.setMinWidth(100);
            cmbworkDy.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbworkDy);
            ComboBox cmbWorkCountry = new ComboBox(countryDAO.retrieveMasterAllCountries());
            cmbWorkCountry.setMinWidth(100);
            cmbWorkCountry.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkCountry);
            ComboBox cmbWorkLocation = new ComboBox(locationList);
            cmbWorkLocation.setMinWidth(100);
            cmbWorkLocation.setPromptText("Location");
            ComboBoxJumpToChar.jumpToChar(cmbWorkLocation);
            ComboBox cmbWorkProfession = new ComboBox(profession);
            cmbWorkProfession.setMinWidth(100);
            cmbWorkProfession.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkProfession);

            ComboBox cmbWorkSkills = new ComboBox(profession);
            cmbWorkSkills.setMinWidth(120);
            cmbWorkSkills.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkSkills);

            ComboBox cmbWorkCurrency = new ComboBox(currencyList);
            cmbWorkCurrency.setMinWidth(30);

            cmbWorkCurrency.setPromptText("Select Currency");
            ComboBoxJumpToChar.jumpToChar(cmbWorkCurrency);
            ComboBox cmbWorkMin = new ComboBox(minSalaryList);
            cmbWorkMin.setMinWidth(50);
            cmbWorkMin.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkMin);
            ComboBox cmbWorkMax = new ComboBox(maxSalaryList);
            cmbWorkMax.setMinWidth(50);
            cmbWorkMax.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkMax);
            ComboBox cmbWorkIndustry = new ComboBox(industryList);
            cmbWorkIndustry.setMinWidth(120);
            cmbWorkIndustry.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbWorkIndustry);
            ComboBox cmbEmpChoice = new ComboBox(choiceObsListWork);
            cmbEmpChoice.setMinWidth(90);
            cmbEmpChoice.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbEmpChoice);

            GridPane.setHgrow(cmbworkDy, Priority.ALWAYS);

            GridPane.setHgrow(cmbWorkProfession, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkSkills, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkCurrency, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkMin, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkMax, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkCountry, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkLocation, Priority.ALWAYS);
            GridPane.setHgrow(cmbWorkIndustry, Priority.ALWAYS);
            GridPane.setHgrow(cmbEmpChoice, Priority.ALWAYS);

            cmbworkDy.setMaxWidth(Double.MAX_VALUE);

            cmbWorkProfession.setMaxWidth(Double.MAX_VALUE);
            cmbWorkSkills.setMaxWidth(Double.MAX_VALUE);
            cmbWorkCurrency.setMaxWidth(Double.MAX_VALUE);
            cmbWorkMin.setMaxWidth(Double.MAX_VALUE);
            cmbWorkMax.setMaxWidth(Double.MAX_VALUE);
            cmbWorkCountry.setMaxWidth(Double.MAX_VALUE);
            cmbWorkLocation.setMaxWidth(Double.MAX_VALUE);
            cmbWorkIndustry.setMaxWidth(Double.MAX_VALUE);
            cmbEmpChoice.setMaxWidth(Double.MAX_VALUE);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            HBox hBoxSalary = new HBox();

            HBox hBoxButtons = new HBox();
            hBoxButtons.getChildren().addAll(btnPlus, btnClose);
            hBoxButtons.setAlignment(Pos.TOP_CENTER);
            cmbWorkCountry.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    ObservableList location = RetrieveStaticMasterDAO.getLocation(newValue.toString());
                    cmbWorkLocation.setItems(location);
                    cmbWorkLocation.getSelectionModel().selectFirst();

                }
            });
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterWorkDetails(listWorkRequired) <= 0) {
                        assesmentWorkBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentWorkBEAN.getRowId() != null) {
                            /* ====================== Update Study Required ====================== */
                            ProgramSuggestedRequiredDAO.updateWorkDetails(assesmentWorkBEAN);
                        } else {
                            /* ====================== Insert Study Required ====================== */
                            ProgramSuggestedRequiredDAO.insertWorkDetails(assesmentWorkBEAN);
                        }
                        AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
                        listWorkRequired.add(assesmentWorkBEAN);
                        addWorkGridRows();
                        //      choiceObsListWork.clear();
                        for (int i = 1; i <= listWorkRequired.size(); i++) {
                            if (!choiceObsListWork.contains("" + i)) {
                                choiceObsListWork.add("" + i);
                            }
                        }
                        refreshTabPane(1);

                        workGrid.requestFocus();
                    } else {
                        /* ====================== validation messages ====================== */
                        showAlerts.showError("All the fields required in work required section !", cmbAge);
                    }
                }
            }
            );

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event
                ) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(cmbAssStatus.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentWorkBEAN.getRowId() != null) {
                            ProgramSuggestedRequiredDAO.deleteWorkDetailsByid(assesmentWorkBEAN.getRowId());
//                                    workGrid.getChildren().clear();
                        }
//                                    masterWorkList.clear(); 
//                                    List<AssesmentWorkBEAN> workExpBEANList = EnquiryAssessmentPersonDAO.getWorkDetails(ENQUIRY_ID);
//                                    if (workExpBEANList.size() > 0) {
//                                        addWorkGridRows(workExpBEANList);
//                                    } 
//                                    else {
//                                        AssesmentWorkBEAN emptyAssesmentWorkBEAN = new AssesmentWorkBEAN();
//                                        List<AssesmentWorkBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(emptyAssesmentWorkBEAN);
//                                        addWorkGridRows(emptyList);
//                                    }
                        listWorkRequired.remove(assesmentWorkBEAN);
                        if (listWorkRequired.size() <= 0) {
                            AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
                            listWorkRequired.add(assesmentWorkBEAN);
                        }
                        addWorkGridRows();
                        refreshTabPane(1);

                        gridStudy.requestFocus();

//                                else {
//                                    workGrid.getChildren().clear();
//                                    masterWorkList.clear();
//                                    List<AssesmentWorkBEAN> workExpBEANList = EnquiryAssessmentPersonDAO.getWorkDetails(ENQUIRY_ID);
//                                    if (workExpBEANList.size() > 0) {
//                                        addWorkGridRows(workExpBEANList);
//                                    } else {
//                                        AssesmentWorkBEAN emptyWorkBEAN = new AssesmentWorkBEAN();
//                                        List<AssesmentWorkBEAN> emptyList = new ArrayList<>();
//                                        emptyList.add(emptyWorkBEAN);
//                                        addWorkGridRows(emptyList);
//                                    }
//                                }
                        workGrid.requestFocus();
                    }
                }
            }
            );

            hBoxSalary.setMinWidth(155);
            hBoxSalary.getChildren().addAll(cmbWorkCurrency, cmbWorkMin, cmbWorkMax);
            GridPane.setHgrow(hBoxSalary, Priority.ALWAYS);
            hBoxSalary.setMaxWidth(Double.MAX_VALUE);
            hBoxSalary.setSpacing(3);
            hBoxSalary.setAlignment(Pos.TOP_CENTER);

            Bindings.bindBidirectional(cmbworkDy.valueProperty(), assesmentWorkBEAN.workProperty());
            Bindings.bindBidirectional(cmbWorkProfession.valueProperty(), assesmentWorkBEAN.professionProperty());
            Bindings.bindBidirectional(cmbWorkSkills.valueProperty(), assesmentWorkBEAN.skillAreaProperty());
            Bindings.bindBidirectional(cmbWorkCurrency.valueProperty(), assesmentWorkBEAN.currenyProperty());
            Bindings.bindBidirectional(cmbWorkMin.valueProperty(), assesmentWorkBEAN.min_salaryProperty());
            Bindings.bindBidirectional(cmbWorkMax.valueProperty(), assesmentWorkBEAN.max_salaryProperty());
            Bindings.bindBidirectional(cmbWorkCountry.valueProperty(), assesmentWorkBEAN.countryProperty());
            Bindings.bindBidirectional(cmbWorkLocation.valueProperty(), assesmentWorkBEAN.locationProperty());
            Bindings.bindBidirectional(cmbWorkIndustry.valueProperty(), assesmentWorkBEAN.industryProperty());
            Bindings.bindBidirectional(cmbEmpChoice.valueProperty(), assesmentWorkBEAN.employer_choiceProperty());
//            Bindings.bindBidirectional(cmbWorkChoice.valueProperty(), assesmentWorkBEAN.choiceProperty());
//            Bindings.bindBidirectional(txtMigrateComment.textProperty(), assesmentWorkBEAN.special_commentProperty());
            if (listWorkRequired.indexOf(assesmentWorkBEAN) == 0) {
                workGrid.add(new Label("Profession"), 0, 0);
                workGrid.add(new Label("Skill Area"), 1, 0);
                workGrid.add(new Label("Salary Per Month"), 2, 0);
                workGrid.add(new Label("Min"), 3, 0);
                workGrid.add(new Label("Max"), 4, 0);
                workGrid.add(new Label("Country"), 5, 0);
                workGrid.add(new Label("Location"), 6, 0);
                workGrid.add(new Label("Industry"), 7, 0);
                //    workGrid.add(new Label("Work"), 0, 0);

                workGrid.add(new Label("Choice"), 8, 0);

            }

            //    workGrid.add(cmbworkDy, 0, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkProfession, 0, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkSkills, 1, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkCurrency, 2, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkMin, 3, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkMax, 4, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkCountry, 5, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkLocation, 6, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbWorkIndustry, 7, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(cmbEmpChoice, 8, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
            workGrid.add(hBoxButtons, 9, listWorkRequired.indexOf(assesmentWorkBEAN) + 1);
//            workGrid.add(hBoxButtons, 5, rowStudySuggestedRequiredList.indexOf(studySuggestedRequiredBEAN) + 1);
        }

    }

    /* ============ validate master data work   */
    /**
     *
     * @param validateAssesmentWorkBEANs
     * @return
     */
    public int validateMasterWorkDetails(List<AssesmentWorkBEAN> validateAssesmentWorkBEANs) {
        int count = 0;

        for (AssesmentWorkBEAN validateAssesmentWorkBEAN : validateAssesmentWorkBEANs) {
            if (validateWorkDetails(validateAssesmentWorkBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }
        }
        return count;

    }

    /* ========================= validate work experience ========= */
    /**
     *
     * @param assesmentWorkBEAN
     * @return
     */
    public String validateWorkDetails(AssesmentWorkBEAN assesmentWorkBEAN) {
        String s = "true";
        if (assesmentWorkBEAN.getWork() == null || assesmentWorkBEAN.getCountry() == null || assesmentWorkBEAN.getProfession() == null
                || assesmentWorkBEAN.getCurreny() == null || assesmentWorkBEAN.getMin_salary() == null || assesmentWorkBEAN.getMax_salary() == null
                || assesmentWorkBEAN.getEmployer_choice() == null || assesmentWorkBEAN.getLocation() == null || assesmentWorkBEAN.getSkillArea() == null || assesmentWorkBEAN.getIndustry() == null) {
            s = "All fields required !";
        }
        return s;
    }

    //<--------------------------Method for add work experience grid rows ----------------------------------------->
    /**
     *
     * @param workExpBEANList
     */
    public void addWorkExperienceGridRows() {
        gridWorkExp.getChildren().clear();
        for (WorktExperienceBEAN workExperienceBEAN : listWorkExpBEAN) {
            workExperienceBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbProfession = new ComboBox(profession);
            cmbProfession.setMinWidth(203);
            cmbProfession.getStyleClass().add("button-service");
            cmbProfession.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbProfession);
            ComboBox cmbDuration = new ComboBox(duration);
            cmbDuration.setMinWidth(162);
            cmbDuration.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(cmbDuration);
            cmbDuration.getStyleClass().add("button-service");

            DatePicker datePickerFrom = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(datePickerFrom);
            DatePicker datePickerTo = new DatePicker();
            ChangeDateFormat.datePickerDateFormatter(datePickerTo);
            TextField txtEmployer = new TextField();
            txtEmployer.setPromptText("Employer Name");

            txtEmployer.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                            Bindings.unbindBidirectional(workExperienceBEAN.employerNameProperty(), txtEmployer.textProperty());
                            txtEmployer.setText(oldValue);
                            Bindings.bindBidirectional(workExperienceBEAN.employerNameProperty(), txtEmployer.textProperty());
                        } //                   
                        else {
                            Bindings.unbindBidirectional(workExperienceBEAN.employerNameProperty(), txtEmployer.textProperty());
                            txtEmployer.setText(newValue);
                            Bindings.bindBidirectional(workExperienceBEAN.employerNameProperty(), txtEmployer.textProperty());
                        }

                    }
                }

            });

            datePickerFrom.setPromptText("From");
            datePickerTo.setPromptText("To");
            datePickerFrom.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactory
                    = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(datePickerFrom.getValue().plusDays(1))) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };
            datePickerTo.setDayCellFactory(dayCellFactory);
            datePickerTo.setValue(datePickerFrom.getValue().plusDays(1));

            datePickerFrom.setMaxWidth(Double.MAX_VALUE);
            datePickerTo.setMaxWidth(Double.MAX_VALUE);
            cmbProfession.setMaxWidth(Double.MAX_VALUE);
            cmbDuration.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(datePickerFrom, Priority.ALWAYS);
            GridPane.setHgrow(datePickerTo, Priority.ALWAYS);
            GridPane.setHgrow(cmbProfession, Priority.ALWAYS);
            GridPane.setHgrow(cmbDuration, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterWorkexperience(listWorkExpBEAN) <= 0) {
                        workExperienceBEAN.setEnquiryId(ENQUIRY_ID);
                        if (workExperienceBEAN.getRowId() != null) {
                            WorkExperienceDAO.updateWorkExperience(workExperienceBEAN);
                        } else {
                            WorkExperienceDAO.insertWorkExperience(ENQUIRY_ID, workExperienceBEAN);
                        }
                        WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
                        listWorkExpBEAN.add(emptyExperienceBEAN);
                        addWorkExperienceGridRows();

                    } else {
                        showAlerts.showError("All Fields Required In Work Experience!", gridWorkExp);
                    }
                    gridWorkExp.requestFocus();
                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        listWorkExpBEAN.remove(workExperienceBEAN);
                        if (workExperienceBEAN.getRowId() != null) {
                            WorkExperienceDAO.deleteWrkExpDetailsByid(workExperienceBEAN.getRowId());
                            //   List<AssessmentExperienceBEAN> workExpBEANList = EnquiryAssessmentPersonDAO.getWorkExperience(ENQUIRY_ID);
                        }
                        if (listWorkExpBEAN.size() > 0) {
                            addWorkExperienceGridRows();
                        } else {
                            WorktExperienceBEAN emptyExperienceBEAN = new WorktExperienceBEAN();
                            listWorkExpBEAN.add(emptyExperienceBEAN);
                            addWorkExperienceGridRows();
                        }
                        gridWorkExp.requestFocus();
                    }
                }
            });

            if (listWorkExpBEAN.indexOf(workExperienceBEAN) == 0) {
                gridWorkExp.add(new Label("Profession"), 0, 0);
                gridWorkExp.add(new Label("Duration"), 1, 0);
                gridWorkExp.add(new Label("From"), 2, 0);
                gridWorkExp.add(new Label("To"), 3, 0);
                gridWorkExp.add(new Label("Employer Name"), 4, 0);
            }
            String pattern = "yyyy-MM-dd";
//        String timePattern = "HH:mm:ss";
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            StringConverter<LocalDate> localDateConverter = new LocalDateStringConverter(formatter, null);

            Bindings.bindBidirectional(cmbProfession.valueProperty(), workExperienceBEAN.professionProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), workExperienceBEAN.durationProperty());
            Bindings.bindBidirectional(datePickerFrom.valueProperty(), workExperienceBEAN.fromDateProperty());
            Bindings.bindBidirectional(datePickerTo.valueProperty(), workExperienceBEAN.toDateProperty());
            Bindings.bindBidirectional(txtEmployer.textProperty(), workExperienceBEAN.employerNameProperty());

            gridWorkExp.add(cmbProfession, 0, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            gridWorkExp.add(cmbDuration, 1, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            gridWorkExp.add(datePickerFrom, 2, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            gridWorkExp.add(datePickerTo, 3, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            gridWorkExp.add(txtEmployer, 4, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            gridWorkExp.add(btnPlus, 5, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            gridWorkExp.add(btnClose, 6, listWorkExpBEAN.indexOf(workExperienceBEAN) + 1);
            // JOptionPane.showMessageDialog(null, masterOtherQualifyList.size());
        }

    }

    /* ========================= validate work experience ========= */
    /**
     *
     * @param validateExperienceBEAN
     * @return
     */
    public String validateWorkExperience(WorktExperienceBEAN validateExperienceBEAN) {
        String s = "true";
        if (validateExperienceBEAN.getDuration() == null || validateExperienceBEAN.getProfession() == null || validateExperienceBEAN.getFromDate() == null || validateExperienceBEAN.getToDate() == null || validateExperienceBEAN.getEmployerName() == null) {
            s = "All fields required !";
        }
        return s;

    }

    /**
     *
     * @param masterWorkExpBEANList
     * @return
     */
    public int validateMasterWorkexperience(List<WorktExperienceBEAN> masterWorkExpBEANList) {
        int count = 0;
        for (WorktExperienceBEAN validateExperienceBEAN : masterWorkExpBEANList) {
            if (validateWorkExperience(validateExperienceBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }

        }
        return count;

    }

    /*================init SSLC and PLUSTWO Details=============================*/
    /**
     *
     */
    public void initSslcPlusTwoDetails() {
        assesmentSslcBEAN = AssessmentSSLCDAO.getSslcDetails(ENQUIRY_ID);
        Bindings.bindBidirectional(sslcBoardCombo.valueProperty(), assesmentSslcBEAN.sslcBoardProperty());
        Bindings.bindBidirectional(mediumCombo.valueProperty(), assesmentSslcBEAN.sslcMediumProperty());
        assesmentPlusTwoBEAN = Assesmentplus2DAO.getPlusDetails(ENQUIRY_ID);
        Bindings.bindBidirectional(plus2BoardCombo.valueProperty(), assesmentPlusTwoBEAN.plusTwoBoardProperty());
        Bindings.bindBidirectional(plus2MediumCombo.valueProperty(), assesmentPlusTwoBEAN.plusTwoMediumProperty());
    }

    /* =========================  Teritary qualifications || Highest Qualification ============================= */
    /**
     *
     */
    public void initTertiaryQualifications() {
        /* ======================== Initialize teritary controls ==================== */
        assesmentTertiaryBEANList = QualificationsCrudDAO.retrieveTeritaryQualifications((counselorDetailsBEAN.getEnquiryID()));
        gridTertiaryQualifications.getChildren().clear();
        if (assesmentTertiaryBEANList.size() > 0) {
            addTertiaryGridRows();
        } else {
            AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
            assesmentTertiaryBEANList.add(emptyBEAN);
            addTertiaryGridRows();

        }

    }

    /*    ======== Add teritary Qualification grid rows =====================*/
    /**
     *
     * @param gridTertiaryBEANList
     */
    public void addTertiaryGridRows() {
        gridTertiaryQualifications.getChildren().clear();
        for (AssesmentTertiaryBEAN assesmentTertiaryBEAN : assesmentTertiaryBEANList) {

            assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox teritaryQualiFieldCmb = new ComboBox();
            teritaryQualiFieldCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(teritaryQualiFieldCmb);

            teritaryQualiFieldCmb.getStyleClass().add("button-service");
            teritaryQualiFieldCmb.setMinWidth(162);
            ComboBox teritaryQualiLevelCmb = new ComboBox();
            teritaryQualiLevelCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(teritaryQualiLevelCmb);
//            teritaryQualiLevelCmb.setMinWidth(203);
            teritaryQualiLevelCmb.getStyleClass().add("button-service");
            ComboBox cmbInstitution = new ComboBox(institution);
//            cmbInstitution.setMinWidth(159);
            cmbInstitution.setPromptText("Select");
            cmbInstitution.getStyleClass().add("button-service");

            ComboBox cmbMarkPercentage = new ComboBox();
            for (int i = 1; i <= 100; i++) {
                cmbMarkPercentage.getItems().add(i + "%");
            }
            ComboBox cmbModeOfExam = new ComboBox(obsModeOfExam);
            ComboBox cmbExamRepeatAbsent = new ComboBox(obsExamRepeatAbsent);
            ComboBox cmbDuration = new ComboBox(duration);

            teritaryQualiFieldCmb.setMaxWidth(Double.MAX_VALUE);
            teritaryQualiLevelCmb.setMaxWidth(Double.MAX_VALUE);
            cmbInstitution.setMaxWidth(Double.MAX_VALUE);
            cmbMarkPercentage.setMaxWidth(Double.MAX_VALUE);
            cmbModeOfExam.setMaxWidth(Double.MAX_VALUE);
            cmbExamRepeatAbsent.setMaxWidth(Double.MAX_VALUE);
            cmbDuration.setMaxWidth(Double.MAX_VALUE);

            GridPane.setHgrow(teritaryQualiFieldCmb, Priority.ALWAYS);
            GridPane.setHgrow(teritaryQualiLevelCmb, Priority.ALWAYS);
            GridPane.setHgrow(cmbInstitution, Priority.ALWAYS);
            GridPane.setHgrow(cmbMarkPercentage, Priority.ALWAYS);
            GridPane.setHgrow(cmbModeOfExam, Priority.ALWAYS);
            GridPane.setHgrow(cmbExamRepeatAbsent, Priority.ALWAYS);
            GridPane.setHgrow(cmbDuration, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStylesheets().add(this.getClass().getResource("fxmlregistrationform.css").toExternalForm());
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterTertiaryQualifications(assesmentTertiaryBEANList) <= 0) {
                        assesmentTertiaryBEAN.setEnquiryId(ENQUIRY_ID);
                        if (assesmentTertiaryBEAN.getRowId() != null) {
                            QualificationsCrudDAO.updateTeritaryQualifications(assesmentTertiaryBEAN);

                        } else {
                            QualificationsCrudDAO.insertTeritaryQualifications(assesmentTertiaryBEAN);
                        }
                        AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
                        assesmentTertiaryBEANList.add(emptyBEAN);
                        addTertiaryGridRows();
                    } else {
                        showAlerts.showError("All Fields Required In Teritiary Qualifications!", gridTertiaryQualifications);
                    }
                    gridTertiaryQualifications.requestFocus();

                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        if (assesmentTertiaryBEAN.getRowId() != null) {
                            QualificationsCrudDAO.deleteTeritaryQualifications(assesmentTertiaryBEAN.getRowId());
                        }
                        teritaryFieldCombos.clear();
                        teritaryLevelCombos.clear();
                        assesmentTertiaryBEANList.remove(assesmentTertiaryBEAN);
                        if (assesmentTertiaryBEANList.size() > 0) {
                            addTertiaryGridRows();
                        } else {
                            AssesmentTertiaryBEAN emptyBEAN = new AssesmentTertiaryBEAN();
                            assesmentTertiaryBEANList.add(emptyBEAN);
                            addTertiaryGridRows();
                        }
                        gridTertiaryQualifications.requestFocus();
                    }
                }

            });

            teritaryQualiLevelCmb.setItems(programLevelList);
            teritaryQualiLevelCmb.valueProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (newValue != null) {
                        List<String> programField = RetrieveStaticMasterDAO.getProgramFields(newValue.toString());
                        ObservableList programFieldlList2 = FXCollections.observableArrayList();
                        for (String s : programField) {
                            programFieldlList2.add(s);
                        }
                        teritaryQualiFieldCmb.setItems(programFieldlList2);
                        teritaryQualiFieldCmb.getSelectionModel().selectFirst();
                    }
                }
            });

            teritaryFieldCombos.add(teritaryQualiFieldCmb);
            teritaryLevelCombos.add(teritaryQualiLevelCmb);

            /* ====================== Add Titles ====================== */
            if (assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) == 0) {
                gridTertiaryQualifications.add(new Label("Level"), 0, 0);
                gridTertiaryQualifications.add(new Label("Field"), 1, 0);
                gridTertiaryQualifications.add(new Label("% of Marks"), 2, 0);
//                gridTertiaryQualifications.add(new Label("Mode of Exam \t Exam Repeat/Absent \t Duration "), 3, 0);
                Label lblMode = new Label("Mode of Exam");
                Label lblExamRepAbs = new Label("Repeat/Absent");
                Label lblDuration = new Label("Duration");

                lblMode.setMaxWidth(Double.MAX_VALUE);
                lblExamRepAbs.setMaxWidth(Double.MAX_VALUE);
                lblDuration.setMaxWidth(Double.MAX_VALUE);

                HBox hboxModeDuration = new HBox(lblMode, lblExamRepAbs, lblDuration);
                HBox.setHgrow(lblMode, Priority.ALWAYS);
                HBox.setHgrow(lblExamRepAbs, Priority.ALWAYS);
                HBox.setHgrow(lblDuration, Priority.ALWAYS);
                gridTertiaryQualifications.add(hboxModeDuration, 3, 0);

            }
            Bindings.bindBidirectional(teritaryLevelCombos.get(assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN)).valueProperty(), assesmentTertiaryBEAN.tertieryLevelProperty());
            Bindings.bindBidirectional(teritaryFieldCombos.get(assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN)).valueProperty(), assesmentTertiaryBEAN.tertieryFieldProperty());
            Bindings.bindBidirectional(cmbInstitution.valueProperty(), assesmentTertiaryBEAN.universityProperty());
            Bindings.bindBidirectional(cmbMarkPercentage.valueProperty(), assesmentTertiaryBEAN.markPercentageProperty());
            Bindings.bindBidirectional(cmbModeOfExam.valueProperty(), assesmentTertiaryBEAN.modeExamProperty());
            Bindings.bindBidirectional(cmbExamRepeatAbsent.valueProperty(), assesmentTertiaryBEAN.examRepeatAbsentProperty());
            Bindings.bindBidirectional(cmbDuration.valueProperty(), assesmentTertiaryBEAN.durationProperty());

            /* ======================== Combine ModeOfExam ,ExamRepeat , Duration ==================== */
            HBox hboxModeExam = new HBox(cmbModeOfExam, cmbExamRepeatAbsent, cmbDuration);
            hboxModeExam.setStyle("-fx-spacing:2");
            HBox.setHgrow(cmbModeOfExam, Priority.ALWAYS);
            HBox.setHgrow(cmbExamRepeatAbsent, Priority.ALWAYS);
            HBox.setHgrow(cmbDuration, Priority.ALWAYS);

            cmbExamRepeatAbsent.setStyle("-fx-border-radius:0;-fx-background-radius:0");
            cmbModeOfExam.setStyle("-fx-background-radius:2 0 0 2");
            cmbDuration.setStyle("-fx-background-radius:0 2 2 0");

            gridTertiaryQualifications.add(teritaryLevelCombos.get(assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN)), 0, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 1);
            gridTertiaryQualifications.add(teritaryFieldCombos.get(assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN)), 1, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 1);
            gridTertiaryQualifications.add(cmbMarkPercentage, 2, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 1);
            gridTertiaryQualifications.add(hboxModeExam, 3, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 1);
            gridTertiaryQualifications.add(btnPlus, 4, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 1);
            gridTertiaryQualifications.add(btnClose, 5, assesmentTertiaryBEANList.indexOf(assesmentTertiaryBEAN) + 1);
        }

    }

    /**
     *
     * @param validateTertiaryBEAN
     * @return
     */
    public String validateTertiaryQualifications(AssesmentTertiaryBEAN validateTertiaryBEAN) {
        String s = "true";
        if (validateTertiaryBEAN.getTertieryField() == null || validateTertiaryBEAN.getTertieryLevel() == null || validateTertiaryBEAN.getMarkPercentage() == null || validateTertiaryBEAN.getExamRepeatAbsent() == null || validateTertiaryBEAN.getModeExam() == null || validateTertiaryBEAN.getDuration() == null) {
            s = "All fields required in Tertiary Qualifications!";
        }
        return s;
    }

    /**
     *
     * @param masterTertiaryBEANList
     * @return
     */
    public int validateMasterTertiaryQualifications(List<AssesmentTertiaryBEAN> masterTertiaryBEANList) {
        int count = 0;
        for (AssesmentTertiaryBEAN validateTertiaryBEAN : masterTertiaryBEANList) {
            if (validateTertiaryQualifications(validateTertiaryBEAN).equalsIgnoreCase("true")) {

            } else {
                count++;
            }

        }
        return count;

    }

    //<--------------------------Method for initialize Spouse Details --------------------------------------->
    /**
     *
     */
    public void initSpouseDetails() {
        spouseDetailsBEAN = SpouseCrudDAO.getSpouseDetails(ENQUIRY_ID);
//        if (spouseDetailsBEAN.getRowId() != null) {
//            txtSpouceName.setText(spouseDetailsBEAN.getSpouseName());
//            cmbSpouseAge.getSelectionModel().clearAndSelect(cmbSpouseAge.getItems().indexOf(spouseDetailsBEAN.getSpouseAge()));
//            if (spouseDetailsBEAN.getPlustwoStatus().equalsIgnoreCase("Yes")) {
//                yesRadioBtn.setSelected(true);
//                noRadioBtn.setSelected(false);
//            } else {
//                noRadioBtn.setSelected(true);
//                yesRadioBtn.setSelected(false);
//            }
//        }

        /* ====================== Bind All Spouse Details ====================== */
        //  Bindings.bindBidirectional(txtSpouceName.textProperty(), spouseDetailsBEAN.spouseNameProperty());
        Bindings.bindBidirectional(cmbSpouseAge.valueProperty(), spouseDetailsBEAN.spouseAgeProperty());
        Bindings.bindBidirectional(cmbSpousePlusTwo.valueProperty(), spouseDetailsBEAN.plusTwoBoardProperty());
//        Bindings.bindBidirectional(cmbSpSslcBoard.valueProperty(), spouseDetailsBEAN.sslcBoardProperty());
//        Bindings.bindBidirectional(cmbSpSslcMedium.valueProperty(), spouseDetailsBEAN.sslcMediumProperty());
//        Bindings.bindBidirectional(cmbSpPlus2Board.valueProperty(), spouseDetailsBEAN.plusTwoBoardProperty());
//        Bindings.bindBidirectional(cmbSpPlus2Medium.valueProperty(), spouseDetailsBEAN.plusTwoFieldProperty());
    }

    //<---------------------------Method for add dynamic row for spouse Work Experience----------------------->
    /**
     *
     * @param assesmentSpouseExpBEANs
     */
    public void addSpouseExpGridRows() {
        spouseWorkExpGrid.getChildren().clear();
        for (AssesmentSpouseExpBEAN assesmentSpouseExpBEAN : listAssesmentSpouseExpBEAN) {
            assesmentSpouseExpBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox spouseProfessionCmb = new ComboBox();
            spouseProfessionCmb.setMinWidth(148);
            spouseProfessionCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(spouseProfessionCmb);
            ComboBox durationCmb = new ComboBox();
            durationCmb.setMinWidth(160);
            durationCmb.setPromptText("Select");
            ComboBoxJumpToChar.jumpToChar(durationCmb);
            Button btnPlus = new Button();
            btnPlus.setText("");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterSpouseExperience(listAssesmentSpouseExpBEAN) <= 0) {
                        if (assesmentSpouseExpBEAN.getRowId() != null) {
                            SpouseCrudDAO.updateSpouseExpDetails(assesmentSpouseExpBEAN);

                        } else {
                            SpouseCrudDAO.insertSpouseExpDetails(assesmentSpouseExpBEAN);
                        }
                        AssesmentSpouseExpBEAN emptyBEAN = new AssesmentSpouseExpBEAN();
                        listAssesmentSpouseExpBEAN.add(emptyBEAN);
                        addSpouseExpGridRows();
                    } else {
                        showAlerts.showError("All Fields Required In Spouse Work Experience!", spouseWorkExpGrid);
                    }
                    spouseWorkExpGrid.requestFocus();
                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {

                        listAssesmentSpouseExpBEAN.remove(assesmentSpouseExpBEAN);
                        if (assesmentSpouseExpBEAN.getRowId() != null) {
                            SpouseCrudDAO.deleteSpouseExpDetails(assesmentSpouseExpBEAN.getRowId());
                        }

                        if (listAssesmentSpouseExpBEAN.size() > 0) {
                            addSpouseExpGridRows();
                        } else {
                            AssesmentSpouseExpBEAN emptyBEAN = new AssesmentSpouseExpBEAN();
                            listAssesmentSpouseExpBEAN.add(emptyBEAN);
                            addSpouseExpGridRows();
                        }
                        spouseWorkExpGrid.requestFocus();
                    }
                }
            });

            spouseProfessionCmb.setItems(profession);
            durationCmb.setItems(duration);

            if (listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN) == 0) {
                spouseWorkExpGrid.add(new Label("Profession"), 0, 0);
                spouseWorkExpGrid.add(new Label("Duration"), 1, 0);
            }

            spouseProfessionCombos.add(spouseProfessionCmb);
            durationCombos.add(durationCmb);

            Bindings.bindBidirectional(spouseProfessionCombos.get(listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN)).valueProperty(), assesmentSpouseExpBEAN.professionProperty());
            Bindings.bindBidirectional(durationCombos.get(listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN)).valueProperty(), assesmentSpouseExpBEAN.durationProperty());

            spouseWorkExpGrid.add(spouseProfessionCombos.get(listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN)), 0, listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN) + 1);
            spouseWorkExpGrid.add(durationCombos.get(listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN)), 1, listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN) + 1);
            HBox butnHbox = new HBox(5);
            butnHbox.getChildren().addAll(btnPlus, btnClose);
            spouseWorkExpGrid.add(butnHbox, 2, listAssesmentSpouseExpBEAN.indexOf(assesmentSpouseExpBEAN) + 1);
//            spouseWorkExpGrid.add(btnClose, 3, ROW_COUNT_SPOUSE_EXP);
            // JOptionPane.showMessageDialog(null, masterOtherQualifyList.size());
        }
    }
//<<======================Validation functions=========================================>>
    //<----------------------------Method for Spouse details validations----------------------------->

    /**
     *
     * @param assesmentSpouseExpBEAN
     * @return
     */
    public boolean spouseExpValidation(AssesmentSpouseExpBEAN assesmentSpouseExpBEAN) {
        boolean f = true;
        if (assesmentSpouseExpBEAN.getProfession() == null || assesmentSpouseExpBEAN.getDuration() == null) {
            f = false;
        }
        return f;
    }

    /**
     *
     * @param validateSpouseExpBEANs
     * @return
     */
    public int validateMasterSpouseExperience(List<AssesmentSpouseExpBEAN> validateSpouseExpBEANs) {
        int count = 0;
        for (AssesmentSpouseExpBEAN validateSpouseExpBEAN : validateSpouseExpBEANs) {
            if (spouseExpValidation(validateSpouseExpBEAN)) {

            } else {
                count++;
            }
        }
        return count;

    }

    //<---------------------------Method for add dynamic row for spouse Language Test Details----------------------->
    /**
     *
     * @param assesmentLanguageTestBEANs
     */
    public void addSpouseLanguageTestGridRows() {
        spouseLangTestGrid.getChildren().clear();
        for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : listAssesmentLanguageTest) {
            assesmentLanguageTestBEAN.setEnquiryId(ENQUIRY_ID);
            ComboBox cmbTestOption = new ComboBox();
            cmbTestOption.setMinWidth(100);
            cmbTestOption.setPromptText("Option");
            ComboBoxJumpToChar.jumpToChar(cmbTestOption);
            ComboBox cmbTestStatus = new ComboBox();
            cmbTestStatus.setMinWidth(100);
            cmbTestStatus.setPromptText("Status");
            ComboBoxJumpToChar.jumpToChar(cmbTestStatus);
            ComboBox cmbSpouseReading = new ComboBox();
            cmbSpouseReading.setMinWidth(100);
            cmbSpouseReading.setPromptText("Reading");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseReading);
            ComboBox cmbSpouseWriting = new ComboBox();
            cmbSpouseWriting.setMinWidth(100);
            cmbSpouseWriting.setPromptText("Writing");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseWriting);
            ComboBox cmbSpouseSpeaking = new ComboBox();
            cmbSpouseSpeaking.setMinWidth(100);
            cmbSpouseSpeaking.setPromptText("Speaking");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseSpeaking);
            ComboBox cmbSpouseListening = new ComboBox();
            cmbSpouseListening.setMinWidth(100);
            cmbSpouseListening.setPromptText("Listening");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseListening);
            ComboBox cmbSpouseOverall = new ComboBox();
            cmbSpouseOverall.setMinWidth(100);
            cmbSpouseOverall.setPromptText("Overall");
            ComboBoxJumpToChar.jumpToChar(cmbSpouseOverall);

            cmbSpouseOverall.setMaxWidth(Double.MAX_VALUE);
            cmbTestOption.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cmbSpouseOverall, Priority.ALWAYS);
            GridPane.setHgrow(cmbTestOption, Priority.ALWAYS);

            Button btnPlus = new Button();
            btnPlus.setText(" ");
            btnPlus.setPrefWidth(32);
            btnPlus.getStyleClass().add("plus-button");
            Button btnClose = new Button();
            btnClose.setText(" ");
            btnClose.setPrefWidth(32);
            btnClose.getStyleClass().add("close-button");
            btnPlus.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (validateMasterSpouseAndTerLangTest(listAssesmentLanguageTest) <= 0) {
                        if (assesmentLanguageTestBEAN.getRowId() != null) {
                            SpouseCrudDAO.updateSpouseLanguageTestDetails(assesmentLanguageTestBEAN);
                        } else {
                            SpouseCrudDAO.insertSpouseLanguageTestDetails(assesmentLanguageTestBEAN);
                        }
                        AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                        listAssesmentLanguageTest.add(emptyBEAN);
                        addSpouseLanguageTestGridRows();
                    } else {
                        showAlerts.showError("All Fields Required In Spouse Language Test!", spouseLangTestGrid);
                    }
                    spouseLangTestGrid.requestFocus();
                }
            });

            btnClose.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnAssessmentSave.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        listAssesmentLanguageTest.remove(assesmentLanguageTestBEAN);
                        if (assesmentLanguageTestBEAN.getRowId() != null) {
                            SpouseCrudDAO.deleteSpouseLanguageTestDetails(assesmentLanguageTestBEAN.getRowId());
                        }
                        if (listAssesmentLanguageTest.size() > 0) {
                            addSpouseLanguageTestGridRows();
                        } else {
                            AssesmentLanguageTestBEAN emptyBEAN = new AssesmentLanguageTestBEAN();
                            listAssesmentLanguageTest.add(emptyBEAN);
                            addSpouseLanguageTestGridRows();
                        }
                        spouseLangTestGrid.requestFocus();
                    }
                }
            });

            cmbTestOption.setItems(otherTest);
            cmbTestStatus.setItems(testStatus);
            cmbSpouseReading.setItems(testScore);
            cmbSpouseWriting.setItems(testScore);
            cmbSpouseSpeaking.setItems(testScore);
            cmbSpouseListening.setItems(testScore);
            cmbSpouseOverall.setItems(testScore);

            testOptionCombos.add(cmbTestOption);
            testStatusCombos.add(cmbTestStatus);
            spouseReadingCombos.add(cmbSpouseReading);
            spouseWritingCombos.add(cmbSpouseWriting);
            spouseSpeakingCombos.add(cmbSpouseSpeaking);
            spouseListeningCombos.add(cmbSpouseListening);
            spouseOverallCombos.add(cmbSpouseOverall);

            if (listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN) == 0) {
                spouseLangTestGrid.add(new Label("Test"), 0, 0);
                spouseLangTestGrid.add(new Label("Overall Score"), 1, 0);
            }

            Bindings.bindBidirectional(testOptionCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.languageTestProperty());
//            Bindings.bindBidirectional(testStatusCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.statusProperty());
//            Bindings.bindBidirectional(spouseReadingCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.readingProperty());
//            Bindings.bindBidirectional(spouseWritingCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.writingProperty());
//            Bindings.bindBidirectional(spouseSpeakingCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.speakingProperty());
//            Bindings.bindBidirectional(spouseListeningCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.listeningProperty());
            Bindings.bindBidirectional(spouseOverallCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)).valueProperty(), assesmentLanguageTestBEAN.overallProperty());

            spouseLangTestGrid.add(testOptionCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 0, listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN) + 1);
//            spouseLangTestGrid.add(testStatusCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 1, ROW_COUNT_SPOUSE_LANGUAGE + 1);
//            spouseLangTestGrid.add(spouseReadingCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 2, ROW_COUNT_SPOUSE_LANGUAGE + 1);
//            spouseLangTestGrid.add(spouseWritingCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 3, ROW_COUNT_SPOUSE_LANGUAGE + 1);
//            spouseLangTestGrid.add(spouseSpeakingCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 4, ROW_COUNT_SPOUSE_LANGUAGE + 1);
//            spouseLangTestGrid.add(spouseListeningCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 5, ROW_COUNT_SPOUSE_LANGUAGE + 1);
            spouseLangTestGrid.add(spouseOverallCombos.get(listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN)), 1, listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN) + 1);
            HBox btnHBox = new HBox(5);
            btnHBox.getChildren().addAll(btnPlus, btnClose);
            btnHBox.setAlignment(Pos.CENTER_LEFT);
            spouseLangTestGrid.add(btnHBox, 2, listAssesmentLanguageTest.indexOf(assesmentLanguageTestBEAN) + 1);
//            spouseLangTestGrid.add(btnClose, 8, ROW_COUNT_SPOUSE_LANGUAGE);
        }
    }

    /**
     *
     * @param message
     * @param node
     */
    public void showPopUpMessages(String message, Node node) {
        try {
            popup.setX(300);
            popup.setY(200);
            Label lblMessage = new Label(message);
            lblMessage.setStyle("-fx-text-fill:red;-fx-background-color:green");
            Pane myPane = new Pane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/common/error/FXMLErrorViewer.fxml"));
            Parent root = (Parent) loader.load();
            FXMLErrorViewerController controller = (FXMLErrorViewerController) loader.getController();
            controller.showError(message, popup);
            myPane.getChildren().add(root);
            popup.getContent().addAll(myPane);
            popup.show(node.getScene().getWindow(), node.localToScreen(0, 0).getX() - 6, node.localToScreen(0, 0).getY() + 30);
            popup.setAutoHide(true);
        } catch (IOException ex) {
            logger.fatal(ex.getStackTrace());
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param spouseDetailsBEAN
     * @return
     */
    public boolean validateSpouseDetails(SpouseDetailsBEAN spouseDetailsBEAN) {
        boolean f = true;
        if (spouseDetailsBEAN.getSpouseAge() == null) {
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbSpouseAge);
            showAlerts.showError("Please Choose Age!", cmbSpouseAge);
            f = false;

        } else if (spouseDetailsBEAN.getPlusTwoBoard() == null) {
            ScrollPaneFocus.requestFocus(scrollPaneMain, cmbSpousePlusTwo);
            showAlerts.showError("Please Choose Board!", cmbSpousePlusTwo);
            f = false;

        }
        return f;
    }

    //<--------------Method for work details validation----------------------------------------------->
    /**
     *
     * @param assesmentMigrateBEAN
     * @return
     */
    public boolean workValidation(AssesmentMigrateBEAN assesmentMigrateBEAN) {
        boolean f = true;
        if (assesmentMigrateBEAN.getMigrate() == null) {
            f = false;
        } else if (assesmentMigrateBEAN.getCountry() == null) {
            f = false;
        } else if (assesmentMigrateBEAN.getProfession() == null) {
            f = false;
        } else if (assesmentMigrateBEAN.getCategory() == null) {
            f = false;
        } else if (assesmentMigrateBEAN.getApplicationType() == null) {
            f = false;
        } else if (assesmentMigrateBEAN.getChoice() == null) {
            f = false;
        }
        return f;
    }

    /**
     *
     * @return
     */
    public boolean validateAssessmentStatus() {
        boolean flag = true;
        if (cmbAssStatus.getSelectionModel().isEmpty()) {
            flag = false;
            showAlerts.showError("Please select an assessment status to continue...!", cmbAssStatus);
        } else if (assessmentStatusBEAN.getRemarks() == null || assessmentStatusBEAN.getRemarks().equalsIgnoreCase("")) {
            flag = false;
            showAlerts.showError("Please Enter Remarks!", txtRemarks);
        } else if (assessmentStatusBEAN.getForwardFor() == null) {
            flag = false;
            showAlerts.showError("Please Choose Forward !", cmbForwardFor);
        } else if (assessmentStatusBEAN.getBranch() == null) {
            flag = false;
            showAlerts.showError("Please Choose Branch!", cmbAssignedBranch);
        } else if (assessmentStatusBEAN.getCounselor() == null) {
            flag = false;
            showAlerts.showError("Please Choose A Counsellor!", cmbAssignedCounselor);
        } else if (assessmentStatusBEAN.getDepartment() == null) {
            flag = false;
            showAlerts.showError("Please Choose Department!", cmbDepartment);
        } else if (assessmentStatusBEAN.getAppointmentDate() == null) {
            flag = false;
            showAlerts.showError("Choose An Appointment Date!", datePickerAppointment);
        } else if (assessmentStatusBEAN.getAppointmentTime() == null || assessmentStatusBEAN.getAppointmentDate().equals("")) {
            flag = false;
            showAlerts.showError("Choose An Appointment Time!", timePickerAppointmentTime);
        }

        return flag;
    }

    /**
     *
     * @param index
     */
    public void refreshTabPane(int index) {
        SingleSelectionModel<Tab> selectionModel = tabPaneProgramRequired.getSelectionModel();
        selectionModel.clearSelection();
        selectionModel.select(index);
    }

}
/* ======================== Load Invoice Generation Form ==================== */

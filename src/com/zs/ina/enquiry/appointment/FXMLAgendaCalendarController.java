/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.appointment;

import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.attendance.FXMLEventsAttendanceController;
import com.zs.ina.enquiry.appointment.cancelorpostpond.FXMLCancelOrPostponeController;
import com.zs.ina.enquiry.appointment.counts.AppoinmentCountsDAO;
import com.zs.ina.enquiry.appointment.counts.AppointmentCountsBEAN;
import com.zs.ina.enquiry.appointment.counts.AppointmentCountsIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.invitations.FXMLEventInvitationsController;

import com.zs.ina.enquiry.appointment.newevent.FXMLNewEventController;
import com.zs.ina.enquiry.appointment.reminder.FXMLRemainderController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLAgendaCalendarController implements Initializable {

    @FXML
    private Button btnNewEvent;
    @FXML
    private ComboBox<SearchPOJO> cmbSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private AnchorPane anchorCalendar;
    PopOver popOver = new PopOver();
    @FXML
    private TableView<AppointmentScheduleBEAN> tblEvents;
    @FXML
    private TableColumn<?, ?> colMeeting;
    @FXML
    private TableColumn<?, ?> colStartDate;
    @FXML
    private TableColumn<?, ?> colEndDate;
    @FXML
    private TableColumn<?, ?> colLocation;
    @FXML
    private TableColumn<?, ?> colAnchor;
    @FXML
    private Button btnScheduledEvents;
    @FXML
    private ImageView imgBtnScheduledEvents;
    @FXML
    private ImageView imgBtnCompletedEvents;
    @FXML
    private Button btnCompletedEvents;
    @FXML
    private ImageView imgBtnAttendance;
    @FXML
    private ImageView imgBtnNewEvent;
    @FXML
    private Button btnAttendance;
    @FXML
    private Button btnInvitations;
    @FXML
    private ImageView imgBtnInvitations;
    @FXML
    private VBox vboxCalendar;
    @FXML
    private VBox vboxTodayEvents;
    @FXML
    private ScrollPane scrollTodayEvents;

    Logger logger = Logger.getLogger(FXMLAgendaCalendarController.class);

    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    ObservableList<AppointmentScheduleBEAN> retrieveAppointmentsAcceptedList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> appointmentTableList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> scheduledEventsTableList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> completedEventsTableList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> todayEventsTableList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> reminderTodayEventsList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> cmbSearchList = FXCollections.observableArrayList();
    ObservableList<SearchPOJO> searchTextList = FXCollections.observableArrayList();
    Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = null;
    AppointmentCountsBEAN appointmentCountsBEAN = new AppointmentCountsBEAN();
    String CUR_USERNAME = "";
    String CUR_BRANCH = "";
    String CUR_ROLE = "";
    /* =============== Create Agenda ================ */
    Agenda agenda = new Agenda();
    @FXML
    private Button btnCountScheduled;
    @FXML
    private Button btnCountCompleted;
    @FXML
    private Button btnCountAttendance;
    @FXML
    private Button btnCountInvitation;
    @FXML
    private Button btnCountToday;
    AppoinmentCountsDAO appoinmentCountsDAO = new AppointmentCountsIMPL();

    /**
     *
     */
    public void displayAcceptedAppointments() {
        appointmentTableList.clear();
        appointmentTableList = appointmentScheduleSERVICE.retrieveApponitmentsAccepted(CUR_USERNAME, CUR_BRANCH);
        addAppointmentsIntoTable(appointmentTableList);

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        /* ======================== Show All Counts ==================== */
        addAppointmentCounts();
        /* ======================== End All Counts ==================== */

        searchTextList.addAll(new SearchPOJO("All", 1), new SearchPOJO("Next 7 Days", 7), new SearchPOJO("Next 2 Weeks", 14), new SearchPOJO("Next 3 Weeks", 21), new SearchPOJO("Next 30 Days", 30));
        cmbSearch.setItems(searchTextList);

        displayAcceptedAppointments();

        Context.getInstance().currentProfile().invitationAcceptanceProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    addAgendaDetails();
                    displayTodayEvents();
                    displayAcceptedAppointments();
                }
            }
        });
        Context.getInstance().currentProfile().eventCancelsProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    addAgendaDetails();
                    displayTodayEvents();
                    displayAcceptedAppointments();
                }
            }
        });
        CalendarPicker datePicker = new CalendarPicker();
        vboxCalendar.getChildren().add(datePicker);
        displayTodayEvents();
        btnNewEvent.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    if (popOver.isShowing() || popOver.isDetached()) {
                        popOver.hide();
                    }

                    popOver = new PopOver();
                    Context.getInstance().currentProfile().setPopOver(popOver);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/newevent/FXMLNewEvent.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLNewEventController newEventController = (FXMLNewEventController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(true);
                    popOver.setTitle("New Event Details");
                    popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
                    popOver.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            addAgendaDetails();
                            displayAcceptedAppointments();
                            displayTodayEvents();
                            setAllButtonColortoDefaultExceptClickedButton();
                            addAppointmentCounts();
                        }

                    });

                    popOver.show(btnNewEvent.getScene().getWindow(), btnNewEvent.getLayoutX() + 78, btnNewEvent.getLayoutY() + 20);

                    newEventController.passPopOverObject(popOver);
                    setAllButtonColortoDefaultExceptClickedButton();
                    /*=================== Change color of this button from Blue(Default) into Majenda==============*/

                    btnNewEvent.getGraphic().setBlendMode(BlendMode.GREEN);

                    /*=================== clear selected item from combo and table ==============*/
                    cmbSearch.getSelectionModel().clearSelection();
                    //  tblEvents.getItems().clear();

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        btnScheduledEvents.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    if (popOver.isShowing() || popOver.isDetached()) {
                        popOver.hide();
                    }

                    popOver = new PopOver();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/cancelorpostpond/FXMLCancelOrPostpone.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLCancelOrPostponeController controller = (FXMLCancelOrPostponeController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(true);
                    popOver.setTitle("Event Cancellation");
                    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                    popOver.show(btnScheduledEvents.getParent().getScene().getWindow(), btnScheduledEvents.getParent().getLayoutX(), btnScheduledEvents.getParent().getLayoutY() + 77);
                    controller.passPopOverObject(popOver);
                    setAllButtonColortoDefaultExceptClickedButton();
                    btnScheduledEvents.getGraphic().setBlendMode(BlendMode.GREEN);
                    cmbSearch.getSelectionModel().clearSelection();
                    popOver.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            addAppointmentCounts();
                            addAgendaDetails();
                            displayAcceptedAppointments();
                            setAllButtonColortoDefaultExceptClickedButton();
                            
                        }

                    });

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }

        });
        btnCompletedEvents.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (popOver.isShowing() || popOver.isDetached()) {
                    popOver.hide();
                }
                setAllButtonColortoDefaultExceptClickedButton();
                btnCompletedEvents.getGraphic().setBlendMode(BlendMode.GREEN);
                completedEventsTableList.clear();
                completedEventsTableList = appointmentScheduleSERVICE.retrieveCompletedEvents(CUR_USERNAME, CUR_BRANCH);
                addAppointmentsIntoTable(completedEventsTableList);
                System.out.println("completedEventsTableList  Table  List in controller");
                System.out.println(completedEventsTableList);
            }
        });
        cmbSearch.valueProperty().addListener(new ChangeListener<SearchPOJO>() {

            @Override
            public void changed(ObservableValue<? extends SearchPOJO> observable, SearchPOJO oldValue, SearchPOJO newValue) {
                if (popOver.isShowing() || popOver.isDetached()) {
                    popOver.hide();
                }
                setAllButtonColortoDefaultExceptClickedButton();

                if (newValue != null) {
                    int noOfDays = newValue.getDays();
                    cmbSearchList.clear();
                    if (noOfDays == 1) {

                        displayAcceptedAppointments();
                    } else {

                        cmbSearchList = appointmentScheduleSERVICE.retrieveWeekEvents(CUR_USERNAME, CUR_BRANCH, noOfDays);
                        System.out.println("list within combo :" + cmbSearchList.toString());
                        addAppointmentsIntoTable(cmbSearchList);
                    }

                }
            }
        });
        btnInvitations.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    if (popOver.isShowing() || popOver.isDetached()) {
                        popOver.hide();
                    }

                    popOver = new PopOver();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/invitations/FXMLEventInvitations.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLEventInvitationsController fXMLEventInvitationsController = (FXMLEventInvitationsController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(true);
                    popOver.setTitle("Event Invitations");
                    //  popOver.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_RIGHT);
                    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                    popOver.show(btnInvitations.getParent().getScene().getWindow(), btnInvitations.getParent().getLayoutX(), btnInvitations.getParent().getLayoutY() + 77);
                    fXMLEventInvitationsController.passPopOverObject(popOver);
                    setAllButtonColortoDefaultExceptClickedButton();
                    /*=================== Change color of button from Blue(Default) into Majenda==============*/

                    btnInvitations.getGraphic().setBlendMode(BlendMode.GREEN);

                    /*=================== clear selected item from combo and table ==============*/
                    cmbSearch.getSelectionModel().clearSelection();
                    // tblEvents.getItems().clear();

                    popOver.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            addAgendaDetails();
                            displayAcceptedAppointments();
                            setAllButtonColortoDefaultExceptClickedButton();
                        }

                    });

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        btnAttendance.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    if (popOver.isShowing() || popOver.isDetached()) {
                        popOver.hide();
                    }

                    popOver = new PopOver();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/attendance/FXMLEventsAttendance.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLEventsAttendanceController fXMLEventsAttendanceController = (FXMLEventsAttendanceController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(true);
                    popOver.setTitle("Events and Participants");
                    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                    popOver.show(btnAttendance.getParent().getScene().getWindow(), btnAttendance.getParent().getLayoutX(), btnAttendance.getParent().getLayoutY() + 77);
                    fXMLEventsAttendanceController.passPopOverObject(popOver);
                    setAllButtonColortoDefaultExceptClickedButton();
                    /*=================== Change color of button from Blue(Default) into Majenda==============*/

                    btnAttendance.getGraphic().setBlendMode(BlendMode.GREEN);

                    /*=================== clear selected item from combo and table ==============*/
                    cmbSearch.getSelectionModel().clearSelection();
                    //       tblEvents.getItems().clear();
                    popOver.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            addAgendaDetails();
                            displayAcceptedAppointments();
                            setAllButtonColortoDefaultExceptClickedButton();
                        }

                    });
                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
        lAppointmentGroupMap.put("group00", new Agenda.AppointmentGroupImpl().withStyleClass("group0"));
        lAppointmentGroupMap.put("group01", new Agenda.AppointmentGroupImpl().withStyleClass("group1"));
        lAppointmentGroupMap.put("group02", new Agenda.AppointmentGroupImpl().withStyleClass("group2"));
        lAppointmentGroupMap.put("group03", new Agenda.AppointmentGroupImpl().withStyleClass("group3"));
        lAppointmentGroupMap.put("group04", new Agenda.AppointmentGroupImpl().withStyleClass("group4"));
        lAppointmentGroupMap.put("group05", new Agenda.AppointmentGroupImpl().withStyleClass("group5"));
        lAppointmentGroupMap.put("group06", new Agenda.AppointmentGroupImpl().withStyleClass("group6"));
        lAppointmentGroupMap.put("group07", new Agenda.AppointmentGroupImpl().withStyleClass("group7"));
        lAppointmentGroupMap.put("group08", new Agenda.AppointmentGroupImpl().withStyleClass("group8"));
        lAppointmentGroupMap.put("group09", new Agenda.AppointmentGroupImpl().withStyleClass("group9"));
        lAppointmentGroupMap.put("group10", new Agenda.AppointmentGroupImpl().withStyleClass("group10"));
        lAppointmentGroupMap.put("group11", new Agenda.AppointmentGroupImpl().withStyleClass("group11"));
        lAppointmentGroupMap.put("group12", new Agenda.AppointmentGroupImpl().withStyleClass("group12"));
        lAppointmentGroupMap.put("group13", new Agenda.AppointmentGroupImpl().withStyleClass("group13"));
        lAppointmentGroupMap.put("group14", new Agenda.AppointmentGroupImpl().withStyleClass("group14"));
        lAppointmentGroupMap.put("group15", new Agenda.AppointmentGroupImpl().withStyleClass("group15"));
        lAppointmentGroupMap.put("group16", new Agenda.AppointmentGroupImpl().withStyleClass("group16"));
        lAppointmentGroupMap.put("group17", new Agenda.AppointmentGroupImpl().withStyleClass("group17"));
        lAppointmentGroupMap.put("group18", new Agenda.AppointmentGroupImpl().withStyleClass("group18"));
        lAppointmentGroupMap.put("group19", new Agenda.AppointmentGroupImpl().withStyleClass("group19"));
        lAppointmentGroupMap.put("group20", new Agenda.AppointmentGroupImpl().withStyleClass("group20"));
        lAppointmentGroupMap.put("group21", new Agenda.AppointmentGroupImpl().withStyleClass("group21"));
        lAppointmentGroupMap.put("group22", new Agenda.AppointmentGroupImpl().withStyleClass("group22"));
        lAppointmentGroupMap.put("group23", new Agenda.AppointmentGroupImpl().withStyleClass("group23"));
        addAgendaDetails();
    }

    private void addAppointmentCounts() {
        /* ======================== Show All Counts ==================== */
        appointmentCountsBEAN = appoinmentCountsDAO.retrieveAppointmentCounts(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
        btnCountScheduled.setText(appointmentCountsBEAN.getCountScheduled());
        btnCountCompleted.setText(appointmentCountsBEAN.getCountCompleted());
        btnCountAttendance.setText(appointmentCountsBEAN.getCountAttendance());
        btnCountToday.setText(appointmentCountsBEAN.getCountToday());
        btnCountInvitation.setText(appointmentCountsBEAN.getCountInvitations());
        System.out.println("test :: " + appointmentCountsBEAN.toString());
    }

    private void addAgendaDetails() {
        agenda = new Agenda();
        agenda.appointments().clear();
        /* =============== Add all accepted appointments into Agenda ================ */
        retrieveAppointmentsAcceptedList.clear();
        retrieveAppointmentsAcceptedList = appointmentScheduleSERVICE.retrieveApponitmentsAccepted(CUR_USERNAME, CUR_BRANCH);
        for (AppointmentScheduleBEAN appointmentScheduleBEAN : retrieveAppointmentsAcceptedList) {
            // System.out.println("start date:" + LocalDateTime.parse(appointmentScheduleBEAN.getStartDateTime()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String stringStartDate = appointmentScheduleBEAN.getStartDateTime();
            LocalDateTime starDateTime = LocalDateTime.parse(stringStartDate, formatter);
            String stringEndDate = appointmentScheduleBEAN.getEndDateTime();
            LocalDateTime endDateTime = LocalDateTime.parse(stringEndDate, formatter);
            /* ====================== Add Random Group ====================== */
            Random random = new Random();
            List<String> keys = new ArrayList<>(lAppointmentGroupMap.keySet());
            String randomKey = keys.get(random.nextInt(keys.size()));

            agenda.appointments().addAll(
                    new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(starDateTime)
                    .withEndLocalDateTime(endDateTime)
                    //  .withDescription("It's time1")
                    .withSummary(appointmentScheduleBEAN.getMeetingName() + "\n\nLocation : " + appointmentScheduleBEAN.getLocation() + "\n\nAnchor : " + appointmentScheduleBEAN.getAnchor())
                    // .withSummary(appointmentScheduleBEAN.getMeetingName())
                    // .withLocation("Locations")
                    // .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass(value)) // you should use a map of AppointmentGroups
                    .withAppointmentGroup(lAppointmentGroupMap.get(randomKey)) // you should use a map of AppointmentGroups
            );

        }
        anchorCalendar.getChildren().clear();
        anchorCalendar.getChildren().add(agenda);
        agenda.setPrefHeight(300);
        agenda.prefHeightProperty().bind(anchorCalendar.heightProperty());
        agenda.prefWidthProperty().bind(anchorCalendar.widthProperty());
    }

    private void addAppointmentsIntoTable(ObservableList<AppointmentScheduleBEAN> tableList) {

        // tblEvents.getItems().clear();
        colMeeting.setCellValueFactory(new PropertyValueFactory<>("meetingName"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("formattedStartDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("formattedEndDate"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAnchor.setCellValueFactory(new PropertyValueFactory<>("anchor"));
//         tblEvents.getItems().addAll(tableList);
        ObservableList<AppointmentScheduleBEAN> appointmentScheduleBEAN = FXCollections.observableList(tableList);
        FilteredList<AppointmentScheduleBEAN> filteredData = new FilteredList<AppointmentScheduleBEAN>(appointmentScheduleBEAN, appointment -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(appointment -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (appointment.getMeetingName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (appointment.getMeetingDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (appointment.getStartDateTime().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (appointment.getEndDateTime().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (appointment.getLocation().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (appointment.getAnchor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }// Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<AppointmentScheduleBEAN> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblEvents.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblEvents.setItems(sortedData);

        /* ====================== Single event details view when double click on the table row ====================== */
        tblEvents.setRowFactory(new Callback<TableView<AppointmentScheduleBEAN>, TableRow<AppointmentScheduleBEAN>>() {

            @Override
            public TableRow<AppointmentScheduleBEAN> call(TableView<AppointmentScheduleBEAN> param) {
                final TableRow<AppointmentScheduleBEAN> row = new TableRow<AppointmentScheduleBEAN>() {
                    @Override
                    protected void updateItem(AppointmentScheduleBEAN appointmentScheduleBEAN, boolean empty) {
                        super.updateItem(appointmentScheduleBEAN, empty);
                        final Tooltip tooltip = new Tooltip();

                        if (appointmentScheduleBEAN != null) {
                            tooltip.setText("Double click to see Event Details");
                            setTooltip(tooltip);
                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (event.getClickCount() == 2) {
                                try {

                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/FXMLEventFullView.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLEventFullViewController controller = (FXMLEventFullViewController) loader.getController();
                                    controller.viewEventDetails(row.getItem());
                                    Scene secondScene = new Scene(root);
                                    Stage secondStage = new Stage();
                                    secondStage.setTitle("Event Details");
                                    secondStage.setScene(secondScene);
                                    secondStage.initStyle(StageStyle.UNDECORATED);
                                    secondStage.initModality(Modality.WINDOW_MODAL);
                                    secondStage.initOwner(btnNewEvent.getScene().getWindow());
                                    secondStage.setScene(secondScene);
                                    secondStage.show();

                                } catch (IOException ex) {
                                    logger.error(ex.toString());
                                    ex.printStackTrace();
                                }
                            }
                        }

                    }
                });

                return row;

            }
        });

    }

    /**
     *
     */
    public void setAllButtonColortoDefaultExceptClickedButton() {
        btnNewEvent.setGraphic(new ImageView(new Image(FXMLAgendaCalendarController.class.getResourceAsStream("images/newevent_img.png"))));
        btnAttendance.setGraphic(new ImageView(new Image(FXMLAgendaCalendarController.class.getResourceAsStream("images/attendance_img.png"))));
        btnInvitations.setGraphic(new ImageView(new Image(FXMLAgendaCalendarController.class.getResourceAsStream("images/invitation_img.png"))));
        btnCompletedEvents.setGraphic(new ImageView(new Image(FXMLAgendaCalendarController.class.getResourceAsStream("images/completedevent_img.png"))));
        btnScheduledEvents.setGraphic(new ImageView(new Image(FXMLAgendaCalendarController.class.getResourceAsStream("images/scheduled_event_img.png"))));

    }

    /**
     *
     */
    public void remindTodayEvents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/reminder/FXMLRemainder.fxml"));
            Parent root = (Parent) loader.load();
            FXMLRemainderController controller = (FXMLRemainderController) loader.getController();
            //    controller.passReminderTodayEvents(reminderTodayEventsList);
            Scene scene = new Scene(root);
            Stage reminderStage = new Stage();
            reminderStage.setScene(scene);
            reminderStage.show();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    private void displayTodayEvents() {

        ObservableList<AppointmentScheduleBEAN> todayEventsList = FXCollections.observableArrayList();
        todayEventsList.clear();
        todayEventsList = appointmentScheduleSERVICE.viewTodayEvents(CUR_USERNAME, CUR_BRANCH);
        vboxTodayEvents.getChildren().clear();
        for (AppointmentScheduleBEAN appointmentScheduleBEAN : todayEventsList) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/FXMLTodaySingleEventView.fxml"));
                Parent root = (Parent) loader.load();
                FXMLTodaySingleEventViewController controller = (FXMLTodaySingleEventViewController) loader.getController();
                controller.passEventDetails(appointmentScheduleBEAN);
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(root);
                vboxTodayEvents.getChildren().add(anchorPane);

            } catch (IOException sqle) {
                logger.error(sqle.toString());
                sqle.printStackTrace();

            }
        }

    }


    /* ============= innner class SearchPOJO ============ */

    /**
     *
     */

    public class SearchPOJO {

        String searchText;
        int days;

        /**
         *
         * @return
         */
        public String getSearchText() {
            return searchText;
        }

        /**
         *
         * @param searchText
         */
        public void setSearchText(String searchText) {
            this.searchText = searchText;
        }

        /**
         *
         * @return
         */
        public int getDays() {
            return days;
        }

        /**
         *
         * @param days
         */
        public void setDays(int days) {
            this.days = days;
        }

        @Override
        public String toString() {
            return searchText;
        }

        /**
         *
         */
        public SearchPOJO() {
        }

        /**
         *
         * @param searchText
         * @param days
         */
        public SearchPOJO(String searchText, int days) {
            this.searchText = searchText;
            this.days = days;
        }

    }

}

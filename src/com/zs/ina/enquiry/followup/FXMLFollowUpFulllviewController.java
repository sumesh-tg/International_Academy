/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.followup;

import com.zs.ina.admin.master.enquirystatus.dao.EnquiryStatusBEAN;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.inbox.retrieve.InboxCountsBEAN;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.common.inbox.retrieve.InboxRetrievalService;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.EnquiryDetailsSearchPOJO;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.enquiry.followup.dao.FollowUpDAO;
import com.zs.ina.enquiry.followup.dao.FollowUpIMPL;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import jidefx.scene.control.hints.ListDataIntelliHints;

/**
 * FXML Controller class
 *
 * @author zscomp1
 */
public class FXMLFollowUpFulllviewController implements Initializable {

    @FXML
    private TableView<CounselorDetailsBEAN> tblFollowUpRegister;
    @FXML
    private TableColumn<CounselorDetailsBEAN, ?> clmFollowName;
    @FXML
    private TableColumn<CounselorDetailsBEAN, ?> clmFollowPhone;
    @FXML
    private TableColumn<CounselorDetailsBEAN, ?> clmFollowProgrms;
    @FXML
    private TableColumn<CounselorDetailsBEAN, ?> clmFollowDateTime;
    @FXML
    private ImageView btnClose;
    @FXML
    private Button btnClose1;
    FollowUpDAO followUpDAO = new FollowUpIMPL();
    private String CUR_USERNAME = null;
    private String ROLE = null;
    private String CUR_BRANCH = null;
    Region regionOverlay = new Region();
    ProgressIndicator progressIndicator = new ProgressIndicator();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    @FXML
    private StackPane stakPaneOuter;
    @FXML
    private TreeTableView<CounselorDetailsBEAN> treeTblFollowUp;
    final Image imgRootIcon = new Image(getClass().getResourceAsStream("man.png"));
    final ImageView iconuser = new ImageView(new Image(getClass().getResourceAsStream("man.png")));
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<?> cmbSearch;
    @FXML
    private VBox vboxHyperlinks;
    @FXML
    private Label lblUserdata;

    /**
     *
     * @param followuplist
     */
    public void initData(List<EnquiryDetailsSearchPOJO> followuplist) {
        clmFollowName.setCellValueFactory(
                new PropertyValueFactory<>("conatctName"));
        clmFollowPhone.setCellValueFactory(
                new PropertyValueFactory<>("contactNumber"));
        clmFollowProgrms.setCellValueFactory(
                new PropertyValueFactory<>("listOfPrograms"));
        clmFollowDateTime.setCellValueFactory(
                new PropertyValueFactory<>("followUpDateTime"));
//        tblFollowUpRegister.getItems().setAll(followuplist);

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbSearch.setVisible(false);
        txtSearch.setVisible(false);
        tblFollowUpRegister.setVisible(false);
        ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();
        System.out.println("FollowRole>>>>>>" + ROLE);
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        lblUserdata.setText("" + CUR_USERNAME + "(" + CUR_BRANCH + ")");
        regionOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
        progressIndicator.setMaxSize(150, 150);
        initHyperLinks();
        // List<CounselorDetailsBEAN> listFollowUp = followUpDAO.retrieveOfficeStaffFollowUpDetails(CUR_USERNAME, CUR_BRANCH);
        // loadFollowUpTableData(listFollowUp);
        // loadFollowUpTreeTableDataEnquiry(listFollowUp);
        // loadFollowUpTreeCount(listInboxCounts);
        btnClose1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }
        });
        btnClose.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }
        });
        /* ====================== Jidefx ====================== */
        List<String> contactNames = new ArrayList<>();
        contactNames = EnquiryDetailsDAO.getAllContactNames();
        ListDataIntelliHints intellihints = new ListDataIntelliHints(txtSearch, contactNames);
        intellihints.setCaseSensitive(false);
    }

    private void loadFollowUpTableData(List<CounselorDetailsBEAN> listFollowUp) {
        stakPaneOuter.getChildren().addAll(regionOverlay, progressIndicator);
        tblFollowUpRegister.getColumns().clear();
        tblFollowUpRegister.setTableMenuButtonVisible(true);
        TableColumn clmTabCname = new TableColumn("Name");
        clmTabCname.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        TableColumn<CounselorDetailsBEAN, String> clmTabPhone = new TableColumn("Contact No");
        clmTabPhone.setCellValueFactory(new PropertyValueFactory<>("contactNumber1"));
        TableColumn<CounselorDetailsBEAN, String> clmTabEmail = new TableColumn("Email");
        clmTabEmail.setCellValueFactory(new PropertyValueFactory<>("contact_email"));
        TableColumn<CounselorDetailsBEAN, String> clmTabBranch = new TableColumn("Branch");
        clmTabBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        TableColumn<CounselorDetailsBEAN, String> clmTabCountry = new TableColumn("Country");
        clmTabCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        TableColumn<CounselorDetailsBEAN, String> clmTabState = new TableColumn("State");
        clmTabState.setCellValueFactory(new PropertyValueFactory<>("state"));
        TableColumn<CounselorDetailsBEAN, String> clmTabDistrict = new TableColumn("District");
        clmTabDistrict.setCellValueFactory(new PropertyValueFactory<>("district"));
        TableColumn<CounselorDetailsBEAN, String> clmTabEnquirySource = new TableColumn("Enquiry Source");
        clmTabEnquirySource.setCellValueFactory(new PropertyValueFactory<>("enquiry_source"));
        TableColumn<CounselorDetailsBEAN, String> clmTabEnquiryAssigning = new TableColumn("Enquiry Assigning");
        clmTabEnquiryAssigning.setCellValueFactory(new PropertyValueFactory<>("enquiry_assigning"));
        TableColumn<CounselorDetailsBEAN, String> clmTabOtherEnquiry = new TableColumn("Other Enquiry");
        clmTabOtherEnquiry.setCellValueFactory(new PropertyValueFactory<>("other_enquiry"));
        tblFollowUpRegister.getColumns().addAll(clmTabCname, clmTabPhone, clmTabEmail, clmTabBranch, clmTabCountry, clmTabState, clmTabDistrict, clmTabEnquirySource, clmTabEnquiryAssigning, clmTabOtherEnquiry);
        tblFollowUpRegister.getItems().clear();
        //   tblFollowUpRegister.getItems().addAll(listFollowUp);
        /* ====================== Load Data To Tableview And Show Progress ====================== */
        Task<ObservableList<CounselorDetailsBEAN>> taskProgress = new GetEnquiryLoadTask(listFollowUp);
        progressIndicator.progressProperty().bind(taskProgress.progressProperty());
        regionOverlay.visibleProperty().bind(taskProgress.runningProperty());
        progressIndicator.visibleProperty().bind(taskProgress.runningProperty());
        tblFollowUpRegister.itemsProperty().bind(taskProgress.valueProperty());
        new Thread(taskProgress).start();
        tblFollowUpRegister.setVisible(false);
        tblFollowUpRegister.setManaged(true);
    }

    private void loadFollowUpTreeTableDataEnquiry(List<CounselorDetailsBEAN> listFollowUp) {
        final TreeItem<CounselorDetailsBEAN> root = new TreeItem<>(new CounselorDetailsBEAN("International Academy (" + CUR_USERNAME + "," + CUR_BRANCH + ")"), new ImageView(imgRootIcon));
        /* ====================== Filter ====================== */
//        FilterableTreeItem<CounselorDetailsBEAN> root6 = treeTblFollowUp.getTreeModel();
//root.predicateProperty().bind(Bindings.createObjectBinding(() -> {
//    if (txtSearch.getText() == null || txtSearch.getText().isEmpty())
//        return null;
//    return TreeItemPredicate.create(actor -> actor.toString().contains(txtSearch.getText()));
//}, txtSearch.textProperty()));
        /* ====================== Filter ends ====================== */
        /* ====================== Group Duplicates ====================== */
        Map<String, List<CounselorDetailsBEAN>> map = new HashMap<String, List<CounselorDetailsBEAN>>();
        for (CounselorDetailsBEAN temp : listFollowUp) {
            List<CounselorDetailsBEAN> listFollowUps = new ArrayList<CounselorDetailsBEAN>();
            if (map.get(temp.getEnquiryID()) == null) {
                listFollowUps.add(temp);
            } else {
                listFollowUps = map.get(temp.getEnquiryID());
                listFollowUps.add(temp);
            }
            map.put(temp.getEnquiryID(), listFollowUps);
        }
        for (Map.Entry<String, List<CounselorDetailsBEAN>> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().toString());
            String additionalInfo = "";
            String rootContactName = "";
            List<CounselorDetailsBEAN> listGroupedFollowUp = entry.getValue();
            for (CounselorDetailsBEAN detailsBEAN : listGroupedFollowUp) {
                additionalInfo = additionalInfo + " - Last Updated :" + detailsBEAN.getFollowUpDates() + "( " + listGroupedFollowUp.size() + "Items)";
                rootContactName = detailsBEAN.getContactName();
                break;
            }
            final TreeItem<CounselorDetailsBEAN> rootName = new TreeItem<>(new CounselorDetailsBEAN(rootContactName + additionalInfo), new ImageView(imgRootIcon));
            listGroupedFollowUp.stream().forEach((employee) -> {
                rootName.getChildren().add(new TreeItem<>(employee));
            });
            root.getChildren().add(rootName);
        }
        root.setExpanded(true);
        TreeTableColumn<CounselorDetailsBEAN, String> clmContactName = new TreeTableColumn<>("Contact Name");
        clmContactName.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getContactName()));
//        clmContactName.setCellFactory(new Callback<TreeTableColumn<CounselorDetailsBEAN, String>, TreeTableCell<CounselorDetailsBEAN, String>>() {
//            @Override
//            public TreeTableCell<CounselorDetailsBEAN, String> call(TreeTableColumn<CounselorDetailsBEAN, String> param) {
//                return new TreeTableCell<CounselorDetailsBEAN, String>() {
//                    private ImageView imageView;
//
//                    @Override
//                    protected void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//
//                        if (!empty && item != null) {
//                            setText(item);
//                            setGraphic(iconuser);
//                        } else {
//                            setText(null);
//                            setGraphic(null);
//                        }
//                    }
//                };
//            }
//        });
        clmContactName.setPrefWidth(400);
        clmContactName.setMinWidth(250);
        TreeTableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> clmEmail = new TreeTableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>("Email");
        clmEmail.setCellFactory(new Callback<TreeTableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TreeTableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

            @Override
            public TreeTableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TreeTableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                TreeTableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> cell = new TreeTableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>() {

                    @Override
                    protected void updateItem(CounselorDetailsBEAN item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

                        if (item != null) {

                            if (item.getContactEmail() != null) {
                                //  setText(item.getContactEmail());
                                Hyperlink hyperlink = new Hyperlink(item.getContactEmail());
                                hyperlink.setOnAction(new EventHandler<ActionEvent>() {
//                                
                                    @Override
                                    public void handle(ActionEvent event) {
                                        if (item.getFollowUpDates() != null) {
                                            if (Desktop.isDesktopSupported()) {
                                                Desktop desktop = Desktop.getDesktop();
                                                if (desktop.isSupported(Desktop.Action.MAIL)) {
                                                    try {
                                                        System.out.println("trr item>>>" + item.getFollowUpStatus());
                                                        EnquiryStatusBEAN enquiryStatusBEAN = followUpDAO.getStatusDetails(item.getFollowUpStatus());
                                                        String myUri = "" + enquiryStatusBEAN.getEmailBody() + "";
                                                        myUri = URLEncoder.encode(myUri, "UTF-8");
                                                        desktop.mail(new URI("mailto:" + item.getContactEmail() + "?subject=" + enquiryStatusBEAN.getEmailSubject().replaceAll("\\s", "%20") + "&Body=")); // alternately, pass a mailto: URI in here
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
                                                    } catch (URISyntaxException ex) {
                                                        Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                                //   choice.getSelectionModel().select();
                                //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
                                setGraphic(hyperlink);
                            } else {
                                setText(null);
                                setGraphic(null);
                            }
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }

                };
                return cell;
            }
        });

//        TreeTableColumn<CounselorDetailsBEAN, String> clmEmail
//                = new TreeTableColumn<>("Email");
//        clmEmail.setCellFactory(new Callback<TreeTableColumn<CounselorDetailsBEAN, String>, TreeTableCell<CounselorDetailsBEAN, String>>() {
//            
//            @Override
//            public TreeTableCell<CounselorDetailsBEAN, String> call(TreeTableColumn<CounselorDetailsBEAN, String> param) {
//                
//                TreeTableCell<CounselorDetailsBEAN, String> cell = new TreeTableCell<CounselorDetailsBEAN, String>() {
//                    @Override
//                    public void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (item != null) {
//                            Hyperlink hyperlink = new Hyperlink(item);
//                            Label lblCheck = new Label("hiiii");
//                            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
//                                
//                                @Override
//                                public void handle(ActionEvent event) {
//                                    if (Desktop.isDesktopSupported()) {
//                                        Desktop desktop = Desktop.getDesktop();
//                                        if (desktop.isSupported(Desktop.Action.MAIL)) {
//                                            try {
//                                          
//                                              //  System.out.println("trr item>>>"+treeTblFollowUp.getSelectionModel().getModelItem(treeTblFollowUp.getSelectionModel().getSelectedIndex()).getValue().getFollowUpStatus());
//                                     
//                                                List<EnquiryStatusBEAN>appStatusList=followUpDAO.getStatusDetails();
//                                                desktop.mail(new URI("mailto:" + item + "?subject=Hello&Body=need%20template")); // alternately, pass a mailto: URI in here
//                                            } catch (IOException ex) {
//                                                Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
//                                            } catch (URISyntaxException ex) {
//                                                Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
//                                            }
//                                        }
//                                    }
//                                }
//                            });
//                            //   choice.getSelectionModel().select();
//                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//                            setGraphic(hyperlink);
//                            
//                        } else {
//                            setText(null);
//                            setGraphic(null);
//                        }
//                    }
//                };
//                return cell;
//                
//            }
//        });
//        
        clmEmail.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, CounselorDetailsBEAN> param)
                -> new ReadOnlyProperty<CounselorDetailsBEAN>() {

                    @Override
                    public Object getBean() {
                        return param;
                    }

                    @Override
                    public String getName() {
                        return param.getValue().getValue().getContactEmail();
                    }

                    @Override
                    public void addListener(ChangeListener<? super CounselorDetailsBEAN> listener) {
                    }

                    @Override
                    public void removeListener(ChangeListener<? super CounselorDetailsBEAN> listener) {
                    }

                    @Override
                    public CounselorDetailsBEAN getValue() {
                        return param.getValue().getValue();
                    }

                    @Override
                    public void addListener(InvalidationListener listener) {
                    }

                    @Override
                    public void removeListener(InvalidationListener listener) {
                    }
                });
        TreeTableColumn<CounselorDetailsBEAN, String> clmPhone = new TreeTableColumn<>("Phone");
        clmPhone.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getContactNumber1()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmStatus = new TreeTableColumn<>("Enquiry Status");

        clmStatus.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param) {
                StringProperty enquiryStatus = new SimpleStringProperty();
                for (EnquirySatusPOJO statusPOJO : RetrieveStaticMasterDAO.retrieveEnquiryAppStatus()) {
                    if (statusPOJO != null) {
                        if (statusPOJO.getId().equalsIgnoreCase(param.getValue().getValue().getFollowUpStatus())) {
                            enquiryStatus.setValue(statusPOJO.getStatus());
                            break;
                        }
                    }
                }
                return enquiryStatus;
            }
        });
        TreeTableColumn<CounselorDetailsBEAN, String> clmRemarks = new TreeTableColumn<>("Remarks");
        clmRemarks.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getRemarks()));

        TreeTableColumn<CounselorDetailsBEAN, String> clmFollowUpDates = new TreeTableColumn<>("Follow Up");
        clmFollowUpDates.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getFollowUpDates()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmStaffHandled = new TreeTableColumn<>("Staff Handled");
        clmStaffHandled.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getStaffUsername()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmCounselor = new TreeTableColumn<>("Counselor");
        clmCounselor.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getEnquiryAssignedTo()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmStudyRequired = new TreeTableColumn<>("Study Required");
        clmStudyRequired.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getStudyRequired()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmWorkRequired = new TreeTableColumn<>("Work Required");
        clmWorkRequired.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getWorkRequired()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmMigrationRequired = new TreeTableColumn<>("Migration Required");
        clmMigrationRequired.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getMigrationRequired()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmTrainingRequired = new TreeTableColumn<>("Training Required");
        TreeTableColumn<CounselorDetailsBEAN, String> clmProgramRequired = new TreeTableColumn<>("Program Required");
        clmProgramRequired.getColumns().addAll(clmStudyRequired, clmWorkRequired, clmMigrationRequired, clmTrainingRequired);
        clmTrainingRequired.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getTrainingRequired()));
        treeTblFollowUp.setRoot(root);
        treeTblFollowUp.getColumns().setAll(clmContactName, clmFollowUpDates, clmPhone, clmEmail, clmStatus, clmRemarks, clmStaffHandled, clmProgramRequired);
    }

    private void initHyperLinks() {
        ObservableList<InboxCountsBEAN> listInboxCounts = InboxRetrievalService.retrieveInboxCountsFollowup(CUR_USERNAME, CUR_BRANCH, ROLE);
        vboxHyperlinks.getChildren().clear();
        for (InboxCountsBEAN inboxCountsBEAN : listInboxCounts) {
            //  String[] splitArray = inboxCountsBEAN.getConcatCount().split(",");
            Hyperlink hyperlink = new Hyperlink(inboxCountsBEAN.getStatus() + "(" + inboxCountsBEAN.getTotalCount() + ")");
            hyperlink.setTextFill(Color.web("#0d4f8b"));
            // hyperlink.setStyle("-fx-text-fill:#0d4f8b;");
            hyperlink.setWrapText(true);
            VBox.setVgrow(hyperlink, Priority.ALWAYS);
            List<CounselorDetailsBEAN> listFollowUp = followUpDAO.retrieveOfficeStaffFollowTotal(CUR_USERNAME, CUR_BRANCH, ROLE);
            loadFollowUpTreeTableDataEnquiry(listFollowUp);
            hyperlink.setMaxWidth(Double.MAX_VALUE);
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("test hyp click" + inboxCountsBEAN);
                    if (inboxCountsBEAN.getTableDiff() != null) {
                        if (inboxCountsBEAN.getTableDiff().equalsIgnoreCase("A")) {
//                            Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxCountsDAO.retrieveAppStatusData(CUR_BRANCH, CUR_USERNAME, ROLE, inboxCountsBEAN.getStatusId());
//                            loadInboxTabsEnquiries(primaryInboxMap);
                            List<CounselorDetailsBEAN> listFollowUp = followUpDAO.retrieveOfficeStaffFollowUpDetails(CUR_USERNAME, CUR_BRANCH, inboxCountsBEAN.getStatusId(), ROLE);
                            loadFollowUpTreeTableDataEnquiry(listFollowUp);
                            System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                        } else if (inboxCountsBEAN.getTableDiff().equalsIgnoreCase("T")) {
//                            Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = InboxCountsDAO.retrieveTaskStatusData(CUR_BRANCH, CUR_USERNAME, ROLE, inboxCountsBEAN.getStatusId());
//                            loadInboxTabsEnquiries(primaryInboxMap);
                        } else if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-1")) {
                            /* ====================== For Showing Total Enquiries ====================== */
                            System.out.println("Total enq");
                            List<CounselorDetailsBEAN> listFollowUp = followUpDAO.retrieveOfficeStaffFollowTotal(CUR_USERNAME, CUR_BRANCH, ROLE);
                            loadFollowUpTreeTableDataEnquiry(listFollowUp);
                        } else if (inboxCountsBEAN.getStatusId().equalsIgnoreCase("-2")) {
                            /* ====================== For Showing Starred Enquiries ====================== */
//                            Map<String, List<CounselorDetailsBEAN>> primaryDataMap = EnquirySearchDAO.getEReportPendingAsesed(CUR_BRANCH, CUR_USERNAME, "1", "rating");
//                            loadInboxTabsEnquiries(primaryDataMap);
//                            retrieveHyperlinkData(resultList);
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
    public class GetEnquiryLoadTask extends Task<ObservableList<CounselorDetailsBEAN>> {

        List<CounselorDetailsBEAN> enquiriesList = new ArrayList<>();

        private GetEnquiryLoadTask(List<CounselorDetailsBEAN> eList) {
            enquiriesList = eList;
        }

        @Override
        protected ObservableList<CounselorDetailsBEAN> call() throws Exception {
            for (int i = 0; i < 500; i++) {
                updateProgress(i, 500);
                Thread.sleep(5);
            }
            ObservableList<CounselorDetailsBEAN> enquiriesObsList = FXCollections.observableArrayList();
            for (CounselorDetailsBEAN counselorDetailsBEAN : enquiriesList) {
                enquiriesObsList.add(counselorDetailsBEAN);
            }
            return enquiriesObsList;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.inbox.followup;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.EnquiryDetailsSearchPOJO;
import com.zs.ina.enquiry.followup.dao.FollowUpDAO;
import com.zs.ina.enquiry.followup.dao.FollowUpIMPL;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private TreeTableView<CounselorDetailsBEAN> treeTableViewCounselor;

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
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        regionOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
        progressIndicator.setMaxSize(150, 150);
        List<CounselorDetailsBEAN> listFollowUp = followUpDAO.retrieveFollowUpDetails(CUR_BRANCH);
        loadFollowUpTableData(listFollowUp);
        loadFollowUpTreeTableDataEnquiry(listFollowUp);
        loadFollowUpTreeTableDataCounselor(listFollowUp);
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
        TreeTableColumn<CounselorDetailsBEAN, String> clmEmail
                = new TreeTableColumn<>("Email");
        clmEmail.setCellFactory(new Callback<TreeTableColumn<CounselorDetailsBEAN, String>, TreeTableCell<CounselorDetailsBEAN, String>>() {

            @Override
            public TreeTableCell<CounselorDetailsBEAN, String> call(TreeTableColumn<CounselorDetailsBEAN, String> param) {

                TreeTableCell<CounselorDetailsBEAN, String> cell = new TreeTableCell<CounselorDetailsBEAN, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            Hyperlink hyperlink = new Hyperlink(item);
                            Label lblCheck = new Label("hiiii");
                            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    if (Desktop.isDesktopSupported()) {
                                        Desktop desktop = Desktop.getDesktop();
                                        if (desktop.isSupported(Desktop.Action.MAIL)) {
                                            try {
                                                desktop.mail(new URI("mailto:" + item + "?subject=Hello&Body=need%20template")); // alternately, pass a mailto: URI in here
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (URISyntaxException ex) {
                                                Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                    }
                };
                return cell;

            }
        });

        clmEmail.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getContactEmail()));

        TreeTableColumn<CounselorDetailsBEAN, String> clmPhone = new TreeTableColumn<>("Phone");
        clmPhone.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getContactNumber1()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmStatus = new TreeTableColumn<>("Enquiry Status");
        clmStatus.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getFollowUpStatus()));
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

    private void loadFollowUpTreeTableDataCounselor(List<CounselorDetailsBEAN> listFollowUp) {
        final TreeItem<CounselorDetailsBEAN> root = new TreeItem<>(new CounselorDetailsBEAN("International Academy (" + CUR_USERNAME + "," + CUR_BRANCH + ")"), new ImageView(imgRootIcon));
        /* ====================== Group Duplicates ====================== */
        Map<String, List<CounselorDetailsBEAN>> map = new HashMap<String, List<CounselorDetailsBEAN>>();
        for (CounselorDetailsBEAN temp : listFollowUp) {
            List<CounselorDetailsBEAN> listFollowUps = new ArrayList<CounselorDetailsBEAN>();
            if (map.get(temp.getStaffUsername()) == null) {
                listFollowUps.add(temp);
            } else {
                listFollowUps = map.get(temp.getStaffUsername());
                listFollowUps.add(temp);
            }
            map.put(temp.getStaffUsername(), listFollowUps);
        }
        for (Map.Entry<String, List<CounselorDetailsBEAN>> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().toString());
            String additionalInfo = "";
            String rootContactName = "";
            List<CounselorDetailsBEAN> listGroupedFollowUp = entry.getValue();
            for (CounselorDetailsBEAN detailsBEAN : listGroupedFollowUp) {
                additionalInfo = additionalInfo + " - Last Updated :" + detailsBEAN.getFollowUpDates() + "( " + listGroupedFollowUp.size() + "Items)";
                rootContactName = detailsBEAN.getStaffUsername();
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
        TreeTableColumn<CounselorDetailsBEAN, String> clmEmail
                = new TreeTableColumn<>("Email");
        clmEmail.setCellFactory(new Callback<TreeTableColumn<CounselorDetailsBEAN, String>, TreeTableCell<CounselorDetailsBEAN, String>>() {

            @Override
            public TreeTableCell<CounselorDetailsBEAN, String> call(TreeTableColumn<CounselorDetailsBEAN, String> param) {

                TreeTableCell<CounselorDetailsBEAN, String> cell = new TreeTableCell<CounselorDetailsBEAN, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            Hyperlink hyperlink = new Hyperlink(item);
                            Label lblCheck = new Label("hiiii");
                            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    if (Desktop.isDesktopSupported()) {
                                        Desktop desktop = Desktop.getDesktop();
                                        if (desktop.isSupported(Desktop.Action.MAIL)) {
                                            try {
                                                desktop.mail(new URI("mailto:" + item + "?subject=Hello&Body=need%20template")); // alternately, pass a mailto: URI in here
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (URISyntaxException ex) {
                                                Logger.getLogger(FXMLFollowUpFulllviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                    }
                };
                return cell;

            }
        });

        clmEmail.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getContactEmail()));

        TreeTableColumn<CounselorDetailsBEAN, String> clmPhone = new TreeTableColumn<>("Phone");
        clmPhone.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getContactNumber1()));
        TreeTableColumn<CounselorDetailsBEAN, String> clmStatus = new TreeTableColumn<>("Enquiry Status");
        clmStatus.setCellValueFactory((TreeTableColumn.CellDataFeatures<CounselorDetailsBEAN, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getFollowUpStatus()));
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
        treeTableViewCounselor.setRoot(root);
        treeTableViewCounselor.getColumns().setAll(clmContactName, clmFollowUpDates, clmPhone, clmEmail, clmStatus, clmRemarks, clmStaffHandled, clmProgramRequired);
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

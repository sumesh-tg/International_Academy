/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.notifications;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.context.Context;
import com.zs.ina.notifications.dao.NewOffersPOJO;
import com.zs.ina.notifications.dao.NotificationsDAO;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLNotificationController implements Initializable {

    @FXML
    private Pane offerPane;
    @FXML
    private TextField offerTxt;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ComboBox<?> branchCmb;
    @FXML
    private ComboBox<?> roleCmb;
    @FXML
    private DatePicker offerExpiryDate;
    @FXML
    private Button saveBtn;
    ObservableList roles = FXCollections.observableArrayList();
    ObservableList branches = FXCollections.observableArrayList();
    @FXML
    private HBox offerHbox;
    static int offerId = 0;
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private Button deleteBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        getOfferDetails();
        List<String> privilage = AdminDAO.getAllRole();
        for (String s : privilage) {
            roles.add(s);
        }
        roleCmb.setItems(roles);
        roleCmb.getSelectionModel().selectFirst();

        List<String> branch = AdminDAO.getAllBranches();
        for (String s : branch) {
            branches.add(s);
        }
        branches.add("ALL");
        branchCmb.setItems(branches);
        branchCmb.getSelectionModel().selectLast();
        offerTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (newPropertyValue) {
                                    usernameValidator.hide();
                                }
                            }
                });
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        LocalDate dt = offerExpiryDate.getValue();
        Date date = Date.from(dt.atStartOfDay(ZoneId.systemDefault()).toInstant());
        NewOffersPOJO newOffersPOJO = new NewOffersPOJO();
        newOffersPOJO.setTitle(offerTxt.getText());
        newOffersPOJO.setDescription(descriptionTextArea.getText());
        newOffersPOJO.setUsers(roleCmb.getSelectionModel().getSelectedItem().toString());
        newOffersPOJO.setBranch(branchCmb.getSelectionModel().getSelectedItem().toString());
        newOffersPOJO.setExpiration_date(date);
        Context.getInstance().currentProfile().setNewOffersPOJO(newOffersPOJO);
        if (offerFormValidation()) {
            if (offerId > 0) {
                int row = NotificationsDAO.updateNewOffers(offerId);
                if (row > 0) {
                    clearScreen();
                }
            } else {
                int row = NotificationsDAO.insertNewOffers();
                if (row > 0) {
                    clearScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Oh Noooo");
                }
            }
        }
    }

    /**
     *
     */
    public void clearScreen() {
        offerTxt.setText("");
        descriptionTextArea.setText("");
        branchCmb.getSelectionModel().selectLast();
        roleCmb.getSelectionModel().selectLast();
        offerExpiryDate.setValue(null);
        offerId = 0;
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allBranches = NotificationsDAO.getAllOffers();
        offerTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(offerTxt, allBranches);
        offerTxt.setPrefWidth(200);
        offerHbox.getChildren().remove(0);
        offerHbox.getChildren().add(offerTxt);
    }

    /**
     *
     */
    public void getOfferDetails() {
        offerTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (!offerTxt.getText().equals("")) {
                                    List<NotificationsDAO.OfferDetails> offerList = NotificationsDAO.getOfferDetails(offerTxt.getText());
                                    for (NotificationsDAO.OfferDetails od : offerList) {
                                        offerId = od.getOfferId();
                                        offerTxt.setText(od.getOfferTitle());
                                        descriptionTextArea.setText(od.getDescription());
                                        roles.remove(od.getRole());
                                        roles.add(od.getRole());
                                        roleCmb.setItems(roles);
                                        roleCmb.getSelectionModel().selectLast();
                                        java.util.Date date = od.getOfferDate();
//                                        System.out.println("Year==>" + rs.getDate(6).getYear());
                                        offerExpiryDate.setValue(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
                                        branches.remove(od.getBranch());
                                    }
                                }
                            }
                });
    }

    //Method for offer form validation

    /**
     *
     * @return
     */
    public boolean offerFormValidation() {
        boolean f = true;
        if (offerTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter offer Title! "));
            usernameValidator.show(offerTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (descriptionTextArea.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter description! "));
            usernameValidator.show(descriptionTextArea, Side.RIGHT, 10, 0);
            f = false;
        } else if (offerExpiryDate == null) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please select! "));
            usernameValidator.show(offerExpiryDate, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        int row = NotificationsDAO.deleteOffer(offerId);
        if (row > 0) {
            clearScreen();
        }
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        clearScreen();
    }
}

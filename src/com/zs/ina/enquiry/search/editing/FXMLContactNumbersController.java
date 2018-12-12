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
package com.zs.ina.enquiry.search.editing;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.ContactNumberBEAN;
import com.zs.ina.enquiry.dao.ContactNumbersDAO;
import com.zs.ina.enquiry.dao.ContactNumbersIMPL;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G ZoftSolutions
 */
public class FXMLContactNumbersController implements Initializable {

    @FXML
    private TextField txtStdIsd;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private Button btnSave;
    @FXML
    private GridPane gridContactNumber;
    List<ContactNumberBEAN> contactNumberBEANList = new ArrayList<>();
    int CONTACT_LIMIT = 8;
    CounselorDetailsBEAN counselorDetailsBEAN;
    List<String> stdCodes = new ArrayList<>();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    ContactNumberBEAN contactPrimaryNumberBEAN = new ContactNumberBEAN();
    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();
    ContactNumbersDAO contactNumbersDAO = new ContactNumbersIMPL();
    @FXML
    private TextField txtStdIsd2;
    @FXML
    private TextField txtContactNumber2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        System.out.println("test phone no missing" + counselorDetailsBEAN.toString());
        stdCodes = RetrieveStaticMasterDAO.getMobCodes();
        initDynamicContactControlls();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (validateContactList(contactNumberBEANList)) {
                    counselorDetailsBEAN.setContactNumber1(contactPrimaryNumberBEAN.getContactNumber1());
                    counselorDetailsBEAN.setStdcode1(contactPrimaryNumberBEAN.getStdIsd1());
                    counselorDetailsBEAN.setContactNumber2(contactPrimaryNumberBEAN.getContactNumber2());
                    counselorDetailsBEAN.setStdcode2(contactPrimaryNumberBEAN.getStdIsd2());
                    //     inlineEditingDAO.updateEnquiryIntoDB(counselorDetailsBEAN);
                    contactNumbersDAO.updateContactNumber(counselorDetailsBEAN);
                    for (ContactNumberBEAN contactNumberBEAN : contactNumberBEANList) {
                        if (contactNumberBEAN.getRowId() != null) {
                            contactNumbersDAO.updateContactNumber(contactNumberBEAN);
                        } else {
                            contactNumbersDAO.insertContactNumber(contactNumberBEAN);
                        }
                    }
                   // Context.getInstance().currentProfile().getPopOver().hide();
                    PopOver popOver=(PopOver) btnSave.getScene().getWindow();
                    popOver.hide();
                } else {
                    showPopupMessages.showError("All Fields Required Or Invalid Phone Number!", txtStdIsd);
                }
            }
        }
        );
        contactPrimaryNumberBEAN.setContactNumber1(counselorDetailsBEAN.getContactNumber1());
        contactPrimaryNumberBEAN.setStdIsd1(counselorDetailsBEAN.getStdcode1());
        contactPrimaryNumberBEAN.setContactNumber2(counselorDetailsBEAN.getContactNumber2());
        contactPrimaryNumberBEAN.setStdIsd2(counselorDetailsBEAN.getStdcode2());
    }

    /**
     *
     */
    public void initDynamicContactControlls() {

        ContactNumberBEAN bEAN = new ContactNumberBEAN();
        System.out.println("Setted in master list outside");
        System.out.println("std2 :: " + counselorDetailsBEAN.getStdcode2());
        System.out.println("phone2 :: " + counselorDetailsBEAN.getContactNumber2());
        System.out.println("phone2 :: " + counselorDetailsBEAN.getContactNumber1());
        contactNumberBEANList = contactNumbersDAO.retrieveAllContactNumbers(counselorDetailsBEAN.getEnquiryID());
//        if (counselorDetailsBEAN.getStdcode2() != null && counselorDetailsBEAN.getContactNumber2() != null) {
//            bEAN.setContactNumber1(counselorDetailsBEAN.getContactNumber2());
//            bEAN.setStdIsd1(counselorDetailsBEAN.getStdcode2());
//            System.out.println("Setted in master list");
//            contactNumberBEANList.add(bEAN);
//        }
        addContactGridRows();
    }

    /**
     *
     */
    public void addContactGridRows() {
        gridContactNumber.getChildren().clear();
        System.out.println("test phone no missing2" + counselorDetailsBEAN.toString());

        /* ======================== Add Default Row ==================== */
        Button btnDftPlus = new Button();
        btnDftPlus.getStyleClass().add("plus-button");
        btnDftPlus.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_green.png"))));
        Button btnDftClose = new Button();
        btnDftClose.getStyleClass().add("close-button");
        btnDftClose.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_red.png"))));
        HBox boxDft = new HBox();
        boxDft.getChildren().addAll(btnDftPlus);
        boxDft.setSpacing(3);

        Label lblContactNo = new Label("Contact Number");
        Text txtMandatory = new Text("*");
        txtMandatory.setStyle("-fx-fill:red");
        lblContactNo.setGraphic(txtMandatory);
        lblContactNo.setContentDisplay(ContentDisplay.RIGHT);

//        AutoCompletionBinding<String> autoCompletionBindingStdCodes = TextFields.bindAutoCompletion(txtStdIsd, stdCodes);
        txtContactNumber.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.matches("[0-9]*")) {
                        System.out.println("Phone New VAluee" + newValue);
                        if (newValue.length() > 10) {
                            Bindings.unbindBidirectional(contactPrimaryNumberBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                            txtContactNumber.setText(oldValue);
                            Bindings.bindBidirectional(contactPrimaryNumberBEAN.contactNumber1Property(), txtContactNumber.textProperty());

                        } else {
                            txtContactNumber.setText(newValue);
                        }

                    } else {
                        Bindings.unbindBidirectional(contactPrimaryNumberBEAN.contactNumber1Property(), txtContactNumber.textProperty());
                        txtContactNumber.setText(oldValue);
                        Bindings.bindBidirectional(contactPrimaryNumberBEAN.contactNumber1Property(), txtContactNumber.textProperty());

                    }
                }
            }
        });
        txtContactNumber2.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.matches("[0-9]*")) {
                        System.out.println("Phone New VAluee" + newValue);
                        if (newValue.length() > 10) {
                            Bindings.unbindBidirectional(contactPrimaryNumberBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                            txtContactNumber2.setText(oldValue);
                            Bindings.bindBidirectional(contactPrimaryNumberBEAN.contactNumber2Property(), txtContactNumber2.textProperty());

                        } else {
                            txtContactNumber2.setText(newValue);
                        }

                    } else {
                        Bindings.unbindBidirectional(contactPrimaryNumberBEAN.contactNumber2Property(), txtContactNumber2.textProperty());
                        txtContactNumber2.setText(oldValue);
                        Bindings.bindBidirectional(contactPrimaryNumberBEAN.contactNumber2Property(), txtContactNumber2.textProperty());

                    }
                }
            }
        });
        txtStdIsd.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String stdArray[] = null;
                    stdArray = newValue.split("-");
                    if (!stdArray[0].equals("")) {
                        txtStdIsd.setText(stdArray[0]);
                        if (txtStdIsd.getText().length() >= 9) {
                            Bindings.unbindBidirectional(contactPrimaryNumberBEAN.stdIsd1Property(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(contactPrimaryNumberBEAN.stdIsd1Property(), txtStdIsd.textProperty());
                        } else if (stdArray[0].matches("[0-9,+]*")) {
                            txtStdIsd.setText(stdArray[0]);
                        } else {
                            Bindings.unbindBidirectional(contactPrimaryNumberBEAN.stdIsd1Property(), txtStdIsd.textProperty());
                            txtStdIsd.setText(oldValue);
                            Bindings.bindBidirectional(contactPrimaryNumberBEAN.stdIsd1Property(), txtStdIsd.textProperty());
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
                            Bindings.unbindBidirectional(contactPrimaryNumberBEAN.stdIsd2Property(), txtStdIsd2.textProperty());
                            txtStdIsd2.setText(oldValue);
                            Bindings.bindBidirectional(contactPrimaryNumberBEAN.stdIsd2Property(), txtStdIsd2.textProperty());
                        } else if (stdArray[0].matches("[0-9,+]*")) {
                            txtStdIsd2.setText(stdArray[0]);
                        } else {
                            Bindings.unbindBidirectional(contactPrimaryNumberBEAN.stdIsd2Property(), txtStdIsd2.textProperty());
                            txtStdIsd2.setText(oldValue);
                            Bindings.bindBidirectional(contactPrimaryNumberBEAN.stdIsd2Property(), txtStdIsd2.textProperty());
                        }
                    }
                }
            }
        });

        Bindings.bindBidirectional(txtContactNumber.textProperty(), contactPrimaryNumberBEAN.contactNumber1Property());
        Bindings.bindBidirectional(txtStdIsd.textProperty(), contactPrimaryNumberBEAN.stdIsd1Property());
        Bindings.bindBidirectional(txtContactNumber2.textProperty(), contactPrimaryNumberBEAN.contactNumber2Property());
        Bindings.bindBidirectional(txtStdIsd2.textProperty(), contactPrimaryNumberBEAN.stdIsd2Property());

        gridContactNumber.add(txtStdIsd, 0, 0);
        gridContactNumber.add(txtContactNumber, 1, 0);
        gridContactNumber.add(txtStdIsd2, 2, 0);
        gridContactNumber.add(txtContactNumber2, 3, 0);
        gridContactNumber.add(boxDft, 4, 0);

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
                        //   showValdatorMsg(gridContactNumber, "All fields required !", 2);
                        showPopupMessages.showError("All Fields Required!", gridContactNumber);
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
                stage.initOwner(Context.getInstance().currentProfile().getPopOver().getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                }
            }
        });

        /* ======================== End Default Row ==================== */
        for (ContactNumberBEAN contactNumberBEAN : contactNumberBEANList) {
            contactNumberBEAN.setEnquiryId(counselorDetailsBEAN.getEnquiryID());
            TextField txtDynStdIsd1 = new TextField() {
                @Override
                public void replaceText(int start, int end, String text) {
                    if (text.matches("[0-9,+]*")) {
                        super.replaceText(start, end, text);
                    }
                }

                @Override
                public void replaceSelection(String text) {
                    if (!text.matches("[0-9,+]*")) {
                        super.replaceSelection(text);
                    }
                }
            };
            txtDynStdIsd1.setPromptText("STD/ISD");
            txtDynStdIsd1.getStyleClass().add("txt-style");
            TextField txtDynStdIsd2 = new TextField() {
                @Override
                public void replaceText(int start, int end, String text) {
                    if (text.matches("[0-9,+]*")) {
                        super.replaceText(start, end, text);
                    }
                }

                @Override
                public void replaceSelection(String text) {
                    if (!text.matches("[0-9,+]*")) {
                        super.replaceSelection(text);
                    }
                }
            };
            txtDynStdIsd2.setPromptText("STD/ISD");
            txtDynStdIsd2.getStyleClass().add("txt-style");
            TextField txtDynContactNumber1 = new TextField() {
                @Override
                public void replaceText(int start, int end, String text) {
                    if (text.matches("[0-9,+]*")) {
                        super.replaceText(start, end, text);
                    }
                }

                @Override
                public void replaceSelection(String text) {
                    if (!text.matches("[0-9,+]*")) {
                        super.replaceSelection(text);
                    }
                }
            };
            txtDynContactNumber1.getStyleClass().add("txt-style");
            txtDynContactNumber1.setPromptText("Contact Number" + (contactNumberBEANList.indexOf(contactNumberBEAN) + 3));
            TextField txtDynContactNumber2 = new TextField() {
                @Override
                public void replaceText(int start, int end, String text) {
                    if (text.matches("[0-9,+]*")) {
                        super.replaceText(start, end, text);
                    }
                }

                @Override
                public void replaceSelection(String text) {
                    if (!text.matches("[0-9,+]*")) {
                        super.replaceSelection(text);
                    }
                }
            };
            txtDynContactNumber2.getStyleClass().add("txt-style");
            txtDynContactNumber2.setPromptText("Contact Number" + (contactNumberBEANList.indexOf(contactNumberBEAN) + 4));
            /* ======================== Autocompletion ==================== */
            //   AutoCompletionBinding<String> autoCompletionBindingStdCodes2 = TextFields.bindAutoCompletion(txtDynStdIsd1, stdCodes);
            /* ======================== Buttons ==================== */
            Button btnDynPlus = new Button();
            btnDynPlus.getStyleClass().add("plus-button");
            btnDynPlus.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_green.png"))));
            Button btnDynClose = new Button();
            btnDynClose.getStyleClass().add("close-button");
            btnDynClose.setCursor(new ImageCursor(new Image(INALoginForm.class.getResourceAsStream("images/cursor_red.png"))));
            HBox box = new HBox();
            box.getChildren().addAll(btnDynPlus);
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
                        showPopupMessages.showError("All Fields Required Or Invalid Phone Number!", gridContactNumber);
                    }
                }
            });

            btnDynClose.setOnAction((ActionEvent event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                // stage.initOwner(Context.getInstance().currentProfile().getNode().getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    contactNumberBEANList.remove(contactNumberBEAN);
                    addContactGridRows();
                    Context.getInstance().currentProfile().getPopOver().hide();
                    // Context.getInstance().currentProfile().getPopOver().show(Context.getInstance().currentProfile().getNode(), Context.getInstance().currentProfile().getNode().localToScreen(0, 0).getX() + 60, Context.getInstance().currentProfile().getNode().localToScreen(0, 0).getY() + 10, Duration.millis(100));
                    Event.fireEvent(Context.getInstance().currentProfile().getNode(), new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                            0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                            true, true, true, true, true, true, null));
                }
                System.out.println("Test Bean :: " + contactNumberBEAN.toString());
            });
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
            gridContactNumber.add(txtDynStdIsd1, 0, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynContactNumber1, 1, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynStdIsd2, 2, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(txtDynContactNumber2, 3, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
            gridContactNumber.add(box, 4, contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
//            gridContactNumber.add(btnDynClose, 6, contactNumberBEANList.indexOf(contactNumberBEAN));
            System.out.println("INDEX OF BEAN " + contactNumberBEANList.indexOf(contactNumberBEAN) + 1);
        }
    }

    /**
     *
     * @param node
     * @param msg
     * @param k
     */
    /**
     *
     * @param contactNumberBEANs
     * @return
     */
    public boolean validateContactList(List<ContactNumberBEAN> contactNumberBEANs) {
        boolean flag = true;

        System.out.println("enquiry bean :: " + contactPrimaryNumberBEAN.toString());
        if (contactPrimaryNumberBEAN.getStdIsd1() == null
                || contactPrimaryNumberBEAN.getContactNumber1() == null
                || contactPrimaryNumberBEAN.getStdIsd1().equals("")
                || contactPrimaryNumberBEAN.getContactNumber1().equals("")
                || contactPrimaryNumberBEAN.getStdIsd2() == null
                || contactPrimaryNumberBEAN.getContactNumber2() == null
                || contactPrimaryNumberBEAN.getStdIsd2().equals("")
                || contactPrimaryNumberBEAN.getContactNumber2().equals("")) {
            flag = false;
        }
        if (contactPrimaryNumberBEAN.getContactNumber1() != null) {
            if (contactPrimaryNumberBEAN.getContactNumber1().length() != 10) {
                flag = false;
            }
        }
        System.out.println("test 1 :: " + flag);
        for (ContactNumberBEAN numberBEAN : contactNumberBEANs) {
            if ((numberBEAN.getContactNumber1() == null || numberBEAN.getStdIsd1() == null)) {
                flag = false;
            }
        }
        System.out.println("test 2 :: " + flag);

        return flag;
    }
}

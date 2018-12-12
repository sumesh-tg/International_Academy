/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login;

import com.zs.ina.admin.mainView.MasterTableFXMLController;
import com.zs.ina.context.Context;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.enquiry.dao.EnquiryDetailsSearchPOJO;
import com.zs.ina.login.dao.LoginFormDAO;
import com.zs.ina.login.dao.ProfilePOJO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.apache.log4j.MDC;
import org.springframework.context.ApplicationContext;
import test.HelloWorld;

/**
 *
 * @author sumesh
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtuser_login;
    @FXML
    private PasswordField txtpassword_login;
    @FXML
    private Button btnlogin;
    @FXML
    private Label msglogin;
    @FXML
    private ImageView loginImageView;
    @FXML
    private Label ia_motto;
    @FXML
    private Pane ia_login_pane;
    @FXML
    private Pane ia_login_controls_pane;
    @FXML
    private Label lblpassword;
    @FXML
    private Label lbluser;
    @FXML
    private Pane ia_header_pane;
    @FXML
    private Pane copyright_pane;
    @FXML
    private ImageView zoftlogo;
    @FXML
    private Pane ia_address_pane;
    @FXML
    private Pane ia_address_pane2;
    @FXML
    private Pane disp_copyright_pane;
    @FXML
    private Label powered;
    Stage stage;
    private FXMLLoader fxmlLoader;
    private Parent rootParent;
    @FXML
    private ImageView imgClose;
    @FXML
    private ImageView imgMinimize;
    private Preferences prefRememberMe;
    @FXML
    private CheckBox chkRemeberMe;
    String CUR_USERNAME = "";
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();

    /**
     *
     */
    public void animateLogo() {

        TranslateTransition tt
                = new TranslateTransition(Duration.seconds(30), loginImageView);

        tt.setFromX(-(loginImageView.getFitWidth()));
        tt.setToX(50);

        tt.setCycleCount(Timeline.INDEFINITE);
        tt.play();

    }

    /**
     *
     */
    public void animateLogo2() {
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), loginImageView);
        st.setByX(0.3f);
        st.setByY(0.3f);
        st.setCycleCount(Timeline.INDEFINITE);
        st.cycleCountProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Test");
            }
        });
        st.setAutoReverse(true);
        st.play();
        loginImageView.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                st.pause();
            }
        });
        loginImageView.setOnMouseExited(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                st.play();
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animateLogo2();
        imageviewHandler();
        prefRememberMe = Preferences.userRoot();
        prefRememberMe = prefRememberMe.node("com.zs.ina.login.remeber");
        if (prefRememberMe.get("loginUser", "not found").equalsIgnoreCase("not found") || prefRememberMe.get("loginPass", "not found").equalsIgnoreCase("not found")) {

        } else {
            txtuser_login.setText(prefRememberMe.get("loginUser", "not found"));
            txtpassword_login.setText(prefRememberMe.get("loginPass", "not found"));
            chkRemeberMe.setSelected(true);

        }
    }

    @FXML
    private void handleloginButtonAction(ActionEvent event) throws IOException {
        /* ======================== Test Spring Integration  ==================== */
        HelloWorld helloWorld = springAppContext.getBean(HelloWorld.class);
        /* ======================== Store username and password ==================== */
        ProfilePOJO profilePOJO = springAppContext.getBean(ProfilePOJO.class);
        profilePOJO.setUsername(txtuser_login.getText().trim());
        profilePOJO.setPassword(txtpassword_login.getText().trim());
        Context.getInstance().currentProfile().setProfilePOJO(profilePOJO);
        CUR_USERNAME = profilePOJO.getUsername();
        switch (LoginFormDAO.login(profilePOJO)) {
            case 1: {
                if (chkRemeberMe.isSelected()) {
                    prefRememberMe.put("loginUser", txtuser_login.getText());
                    prefRememberMe.put("loginPass", txtpassword_login.getText());
                } else {
                    try {
                        prefRememberMe.clear();
                    } catch (BackingStoreException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                profilePOJO = EnquiryDetailsDAO.getUserProfile(profilePOJO);
                MDC.put("user", profilePOJO.getFname());
                MDC.put("branch", profilePOJO.getBranch());

                Context.getInstance().currentProfile().setProfilePOJO(profilePOJO);
                //auditor
                LoginFormDAO.insertLoginDetails(profilePOJO);
                Stage stage = (Stage) btnlogin.getScene().getWindow();
                stage.close();
                stage = new Stage();
                stage.setTitle("Home page");
                /* ====================== Clear Login Audit ====================== */
                stage.setOnHiding(new EventHandler<WindowEvent>() {

                    @Override
                    public void handle(WindowEvent event) {
                        LoginFormDAO.deleteLoginAudit(CUR_USERNAME);
                    }
                });
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                if (profilePOJO.getRole().equalsIgnoreCase("ROLE_OFFICE")) {
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/enquiry/EnquiryMainView.fxml"));
                    Scene scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                } else if (profilePOJO.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/mainView/masterTableFXML.fxml"));
                    Scene scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                } else if (profilePOJO.getRole().equalsIgnoreCase("ROLE_COUNSELOR")) {
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/counselor/FXMLCounselorMainView.fxml"));
                    Scene scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                } else if (profilePOJO.getRole().equalsIgnoreCase("ROLE_ACCOUNTANT")) {
                    Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/accounts/FXMLAccountsInbox.fxml"));
                    Scene scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                }
                break;
            }
            case 2:
                if (chkRemeberMe.isSelected()) {
                    prefRememberMe.put("loginUser", txtuser_login.getText());
                    prefRememberMe.put("loginPass", txtpassword_login.getText());
                } else {
                    try {
                        prefRememberMe.clear();
                    } catch (BackingStoreException ex) {
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                LoginFormDAO.updateLoginDetails(profilePOJO);
                msglogin.setText("Login failed");
                FadeTransition ft = new FadeTransition(Duration.millis(3000), msglogin);
                ft.setFromValue(1.0);
                ft.setToValue(0.3);
                ft.setCycleCount(4);
                ft.setAutoReverse(true);
                ft.play();
                break;
            case 3: {
                if (chkRemeberMe.isSelected()) {
                    prefRememberMe.put("loginUser", txtuser_login.getText());
                    prefRememberMe.put("loginPass", txtpassword_login.getText());
                } else {
                    try {
                        prefRememberMe.clear();
                    } catch (BackingStoreException ex) {
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Parent root1 = FXMLLoader.load(getClass().getResource("/com/zs/ina/login/changepass/FXMLchangepassword.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.setResizable(false);
                stage.initOwner(btnlogin.getScene().getWindow());
                stage.show();
                break;
            }
            default:
                LoginFormDAO.insertLoginDetails(profilePOJO);
                msglogin.setText("Login failed");
                break;
        }
        /* ======================== Serialize All the available enquiry details ==================== */
        Task taskSerialize = new Task() {
            @Override
            protected Object call() throws Exception {
                List<EnquiryDetailsSearchPOJO> enquiryList = EnquiryDetailsDAO.patternSearchEnquiryDetails("");
                try {
                    FileOutputStream fileOut = new FileOutputStream("Enquiry_details.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(enquiryList);
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                return null;
            }
        };
        taskSerialize.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("All enquiry details stored in Enquiry_details.ser file... ");
            }
        });
        new Thread(taskSerialize).start();
    }

    private void handleCloseButton(ActionEvent event
    ) {
        Stage stage = (Stage) btnlogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleZoftLogoDisp(MouseEvent event) throws IOException {
        System.out.println("clicked on image");

        StackPane secondaryLayout = new StackPane();
        Pane myPane = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/copyright/FXMLCopyRight.fxml"));
        Parent root = (Parent) loader.load();
        // System.out.println(loader.getController());
        secondaryLayout.getChildren().add(root);

        Scene secondScene = new Scene(secondaryLayout, 436, 408);

        Stage secondStage = new Stage();
        secondStage.setTitle("Copyright Notification");
        secondStage.setScene(secondScene);

        //Set position of second window, related to primary window.
        secondStage.setX(250);
        secondStage.setY(100);
        secondStage.initStyle(StageStyle.UNDECORATED);
        secondStage.show();
    }

    @FXML
    private void handleUserKeyPressed(KeyEvent event) {
        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.I,
                KeyCombination.CONTROL_DOWN);
        if (keyComb1.match(event)) {
//
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("/com/zs/ina/common/FXMLConfigurations.fxml"));
                rootParent = (Parent) fxmlLoader.load();
                stage = new Stage();
                stage.setTitle("Ip Config");
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.setScene(new Scene(rootParent));
                stage.setResizable(false);
                stage.initOwner(btnlogin.getScene().getWindow());
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MasterTableFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void handlePassKeyPressed(KeyEvent event) {
    }

    private void imageviewHandler() {
        imgClose.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) imgClose.getScene().getWindow();
                stage.close();
            }
        });
        imgMinimize.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) imgClose.getScene().getWindow();
                stage.setIconified(true);
            }
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login;

import com.ina.application.config.SpringConfig;
import com.zs.ina.context.Context;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author sumesh
 */
public class INALoginForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        /* ====================== Set Log4j log file name ====================== */
        System.setProperty("logfilename", System.getProperty("user.name"));
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/login/FXMLLogin.fxml"));
        synchronized (root) {
//            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
            stage.setTitle("International Academy");
            stage.setScene(scene);
        }
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* ======================== Add Spring Boot Spring Integration ==================== */
        ApplicationContext ctx = SpringApplication.run(SpringConfig.class, args);
        Context.getInstance().currentProfile().setSpringContext(ctx);
        launch(args);
    }

}

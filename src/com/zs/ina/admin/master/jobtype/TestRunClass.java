/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.jobtype;

import com.zs.ina.admin.mainView.*;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author sumesh
 */
public class TestRunClass extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane secondaryLayout = new StackPane();
        Pane myPane = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLJobtype.fxml"));
        Parent root = (Parent) loader.load();
//        System.out.println(loader.getController());
        secondaryLayout.getChildren().add(root);
        Scene secondScene = new Scene(secondaryLayout);
        Stage secondStage = new Stage();
        secondStage.setTitle("Master Table Settings");
        secondStage.setScene(secondScene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
      //  secondStage.initStyle(StageStyle.UNDECORATED);
       secondStage.setMaximized(true);
        secondStage.show();
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
        launch(args);
    }

}

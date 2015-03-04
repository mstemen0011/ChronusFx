/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chronusfx;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author matthew.g.stemen
 */
public class ChronusFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChronusFXML.fxml"));
        Image systemImage = new Image(getClass().getResourceAsStream("/chronusfx/images/chronusHourGlass_1_128.png"));
        Scene scene = new Scene(root);
        stage.getIcons().add(systemImage);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

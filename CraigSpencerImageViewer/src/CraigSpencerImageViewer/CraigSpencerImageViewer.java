/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CraigSpencerImageViewer;
//this is a test to change the version of the code to see if github recognizes the changes and offers to upload the new versrion

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class CraigSpencerImageViewer extends Application {

    ImageView myImageView;
    ScrollPane scrollPane;

    @Override
    public void start(Stage primaryStage) {

        // creates the tab called file
        Menu menu1 = new Menu("File");
        
        // creates menu items under file
        MenuItem menuItem1 = new MenuItem("Open Image");
        MenuItem menuItem2 = new MenuItem("Exit");

        // attaches
        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);

        
        menuItem1.setOnAction(menuLoadEventListener);
        // exits the app
        menuItem2.setOnAction(e -> {
            System.exit(0);
        });

        // creates the menu bar
        MenuBar menuBar = new MenuBar();

        menuBar.getMenus().add(menu1);

        VBox originalImageBox = new VBox();

        Label label = new Label("Original Image");

        myImageView = new ImageView();
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(myImageView);

        HBox originalImageButtonBox = new HBox();
        Button originalZoomIn = new Button("Zoom In");
        originalZoomIn.setOnAction(e -> {
            myImageView.setScaleX(2.0);
            myImageView.setScaleY(2.0);
        });
        Button originalZoomOut = new Button("Zoom Out");
        originalZoomOut.setOnAction(e -> {
            myImageView.setScaleX(0.4);
            myImageView.setScaleY(0.4);
        });
        Button originalOriginalSize = new Button("Original Size");
        originalOriginalSize.setOnAction(e -> {
            myImageView.setScaleX(1.0);
            myImageView.setScaleY(1.0);
        });

        originalImageButtonBox.getChildren().addAll(originalZoomIn, originalZoomOut, originalOriginalSize);
        originalImageBox.getChildren().addAll(label, scrollPane, originalImageButtonBox);
        VBox rootBox = new VBox();
        rootBox.getChildren().addAll(menuBar, originalImageBox);

        Scene scene = new Scene(rootBox, 800, 800);

        primaryStage.setTitle("Craig Spencer Image Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    EventHandler<ActionEvent> menuLoadEventListener
            = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.BMP");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                myImageView.setImage(image);
                scrollPane.setContent(null);
                scrollPane.setContent(myImageView);
            } catch (IOException ex) {
                Logger.getLogger(CraigSpencerImageViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
}

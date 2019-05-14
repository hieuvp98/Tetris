/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hieuv
 */
public class BeginController implements Initializable {

    @FXML
    private Text btnSingle;
    @FXML
    private Text btnMulti;
    @FXML
    private Text btnCopyRight;
    @FXML
    private Text btnExit;
    @FXML
    private ImageView imgLogo;
    @FXML
    private AnchorPane beginPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgLogo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
        mouseEnterHandle();
        clickHandle();
    }

    private void mouseEnterHandle() {
        btnSingle.setOnMouseEntered(e -> {
            btnSingle.setFont(new Font("Comic Sans MS", 35));
        });
        btnSingle.setOnMouseExited(e -> {
            btnSingle.setFont(new Font("Comic Sans MS", 24));
        });
        btnMulti.setOnMouseEntered(e -> {
            btnMulti.setFont(new Font("Comic Sans MS", 35));
        });
        btnMulti.setOnMouseExited(e -> {
            btnMulti.setFont(new Font("Comic Sans MS", 24));
        });
        btnCopyRight.setOnMouseEntered(e -> {
            btnCopyRight.setFont(new Font("Comic Sans MS", 35));
        });
        btnCopyRight.setOnMouseExited(e -> {
            btnCopyRight.setFont(new Font("Comic Sans MS", 24));
        });
        btnExit.setOnMouseEntered(e -> {
            btnExit.setFont(new Font("Comic Sans MS", 35));
        });
        btnExit.setOnMouseExited(e -> {
            btnExit.setFont(new Font("Comic Sans MS", 24));
        });
    }

    private void clickHandle() {
        btnExit.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });
        btnSingle.setOnMouseClicked(e -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("Frame.fxml"));
                Scene scene = new Scene(parent);
                scene.getRoot().requestFocus();
                scene.getStylesheets().add("css/main.css");
                Stage stage = (Stage) beginPane.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                System.out.println("load error: " + ex.getMessage());
            }
        });
        btnMulti.setOnMouseClicked(e -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("Frame2.fxml"));
                Scene scene = new Scene(parent);
                scene.getRoot().requestFocus();
                scene.getStylesheets().add("css/main.css");
                Stage stage = (Stage) beginPane.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                System.out.println("load error: " + ex.getMessage());
            }
        });
    }

}

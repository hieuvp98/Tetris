/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hieuv
 */
public class BeginController implements Initializable {

    private AudioClip audioClip;
    
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
    @FXML
    private Pane multiPane;
    @FXML
    private Text btnJoin;
    @FXML
    private Text btnBack;
    @FXML
    private Pane createServerPane;
    @FXML
    private Text txtIP;
    @FXML
    private Text btnBack1;
    @FXML
    private TextField txtfPort;
    @FXML
    private Text btnCreateServer1;
    @FXML
    private Text btnCreate;

    public static int port;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        multiPane.setVisible(false);
        createServerPane.setVisible(false);
        imgLogo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
        mouseEnterHandle();
        clickHandle();
        audioClip = new AudioClip(this.getClass().getResource("/sounds/themeA.mp3").toExternalForm());
        audioClip.setCycleCount(Animation.INDEFINITE);
        audioClip.play();
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
        btnCreateServer1.setOnMouseEntered(e -> {
            btnCreateServer1.setFont(new Font("Comic Sans MS", 35));
        });
        btnCreateServer1.setOnMouseExited(e -> {
            btnCreateServer1.setFont(new Font("Comic Sans MS", 24));
        });
        btnJoin.setOnMouseEntered(e -> {
            btnJoin.setFont(new Font("Comic Sans MS", 35));
        });
        btnJoin.setOnMouseExited(e -> {
            btnJoin.setFont(new Font("Comic Sans MS", 24));
        });
        btnBack.setOnMouseEntered(e -> {
            btnBack.setFont(new Font("Comic Sans MS", 35));
        });
        btnBack.setOnMouseExited(e -> {
            btnBack.setFont(new Font("Comic Sans MS", 24));
        });
        btnBack1.setOnMouseEntered(e -> {
            btnBack1.setFont(new Font("Comic Sans MS", 35));
        });
        btnBack1.setOnMouseExited(e -> {
            btnBack1.setFont(new Font("Comic Sans MS", 24));
        });
        btnCreate.setOnMouseEntered(e -> {
            btnCreate.setFont(new Font("Comic Sans MS", 35));
        });
        btnCreate.setOnMouseExited(e -> {
            btnCreate.setFont(new Font("Comic Sans MS", 24));
        });
    }

    private void clickHandle() {
        btnExit.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });
        btnSingle.setOnMouseClicked(e -> {
            audioClip.stop();
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
            audioClip.stop();
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
        btnBack.setOnMouseClicked(e -> {
            multiPane.setVisible(false);
        });
        btnCreateServer1.setOnMouseClicked(e -> {
            createServerPane.setVisible(true);
            try {
                Enumeration<NetworkInterface> nics = NetworkInterface
                        .getNetworkInterfaces();
                while (nics.hasMoreElements()) {
                    NetworkInterface nic = nics.nextElement();
                    Enumeration<InetAddress> addrs = nic.getInetAddresses();
                    while (addrs.hasMoreElements()) {
                        InetAddress addr = addrs.nextElement();
                        if (addr.getHostAddress().startsWith("192")) {
                            txtIP.setText(addr.getHostAddress());
                        }
                    }
                }
            } catch (SocketException ex) {
                Logger.getLogger(BeginController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        btnBack1.setOnMouseClicked(e -> {
            createServerPane.setVisible(false);
        });
        btnCreate.setOnMouseClicked(e ->{
            port = Integer.parseInt(txtfPort.getText());
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

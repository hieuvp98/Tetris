/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgLogo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author hieuv
 */
public class BeginMain {
    public static void main(String[] args) {
        new BeginMain().run();
    }
    
    public void run(){
        try {
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("Begin.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Tetris");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BeginMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

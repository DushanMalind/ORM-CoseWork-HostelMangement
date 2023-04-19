package lk.ijse.hostelManagement.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.util.Navigation;
import lk.ijse.hostelManagement.util.Routes;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadinProcessFromController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private AnchorPane roots;


    public void initialize(){
        progressBar.setStyle("-fx-accent:#00FF00;");
        new ShowScreen().start();
    }

    class ShowScreen extends Thread{
        @Override
        public void run() {
//            super.run();
            try {
                for (int i = 0; i <=10 ; i++) {
                    double x=i*(0.1);
                    progressBar.setProgress(x);
                    progressIndicator.setProgress(x);

                    if (i*10==100){
                        progressBar.setVisible(true);
                        progressIndicator.setProgress(1);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() ->{

                    Parent root=null;

                    try {
//                       root= FXMLLoader.load(getClass().getResource("../view/main_from.fxml"));
                        Navigation.navigation(Routes.LOGINFROM,roots);



                    }catch (Exception e){
                        Logger.getLogger(LoadinProcessFromController.class.getName()).log(Level.SEVERE, null, e);
                    }
                });

            }catch (Exception e){

            }
        }
    }

}

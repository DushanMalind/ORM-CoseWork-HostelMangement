package lk.ijse.hostelManagement.controller;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainFromController {

    public ImageView imgUser;
    public ImageView imgLogOut;
    public Label lblDateTime;
    @FXML
    private ImageView imgReservation;

    @FXML
    private ImageView imgRoom;

    @FXML
    private ImageView imgStudent;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblHead;

    @FXML
    private AnchorPane root;


    public  void initialize(){
        setDateAndTime();

        FadeTransition fadeTransition=new FadeTransition(Duration.millis(3000),root);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private void setDateAndTime(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("  yyyy-MM-dd  HH:mm:ss a");
                    lblDateTime.setText(LocalDateTime.now().format(formatter));
                }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    @FXML
    void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(2000), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblHead.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }


    @FXML
    void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()){
                case "imgStudent":
                    lblHead.setText("Student From");
                    lblDescription.setText("Click to add, edit, delete, search or view Student");
                    break;
                case "imgReservation":
                    lblHead.setText("Reservation From");
                    lblDescription.setText("Click to add, edit, delete, search or view Reservation");
                    break;
                case "imgRoom":
                    lblHead.setText("Room From");
                    lblDescription.setText("Click to add, edit, delete, search or view Room");
                    break;
                case "imgUser":
                    lblHead.setText("User From");
                    lblDescription.setText("Click to add, edit, delete, search or view User");
                    break;
                case "imgLogOut":
                    lblHead.setText("LogOut From");
                    lblDescription.setText("Click to add, edit, delete, search or view LogOut");
                    break;
            }
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(4000), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }

    }

    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView){
            ImageView icon=(ImageView) event.getSource();

            Parent root=null;

            switch (icon.getId()){
                case "imgStudent" :
                    root= FXMLLoader.load(this.getClass().getResource("../view/student_from.fxml"));
                    break;
                case "imgReservation" :
                    root= FXMLLoader.load(this.getClass().getResource("../view/reservation.fxml"));
                    break;
                case "imgRoom" :
                    root= FXMLLoader.load(this.getClass().getResource("../view/room_from.fxml"));
                    break;
                case "imgUser" :
                    root= FXMLLoader.load(this.getClass().getResource("../view/user_from.fxml"));
                    break;
                case "imgLogOut" :
                    root= FXMLLoader.load(this.getClass().getResource("../view/login_from.fxml"));
                    break;
                default:
                    new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
            }

            try {
                if (root!=null){
                    Stage primary=(Stage) this.root.getScene().getWindow();
                    Scene scene=new Scene(root);
                    primary.setScene(scene);
                    primary.centerOnScreen();

                    TranslateTransition tt=new TranslateTransition(Duration.millis(4000),scene.getRoot());
                    tt.setFromX(-scene.getWidth());
                    tt.setToX(0);
                    tt.play();

                }
            }catch (NullPointerException e){
//                new Alert(Alert.AlertType.WARNING, "Not suitable").show();
            }
        }
    }
}

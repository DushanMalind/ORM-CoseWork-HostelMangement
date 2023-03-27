package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ReservationController {

    @FXML
    private JFXButton btnAddNewReservation;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colReservation;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    @FXML
    private TableColumn<?, ?> colStaus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtReservationId;

    @FXML
    private TextField txtRoomTypeId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtStudentId;

    @FXML
    void btnClearOnReservationAction(ActionEvent event) {

    }

    @FXML
    void btnNewSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnReservationAction(ActionEvent event) {

    }

    @FXML
    void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/hostelManagement/view/main_from.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

}

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

public class RoomFromController {

    @FXML
    private JFXButton btnAddNewRoom;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRoomTypeId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtType;

    @FXML
    void btnClearOnRoomAction(ActionEvent event) {

    }

    @FXML
    void btnNewSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnRoomAction(ActionEvent event) {

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

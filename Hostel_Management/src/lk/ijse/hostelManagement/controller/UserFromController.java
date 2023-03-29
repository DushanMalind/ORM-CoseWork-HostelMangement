package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.UserBO;

import java.io.IOException;
import java.net.URL;

public class UserFromController {

    public AnchorPane root;
    @FXML
    private JFXButton btnAddNewUser;

    @FXML
    private JFXButton btnDelete;

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
    private TableView<?> tblUser;

    @FXML
    private TextField txtContact;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USERBO);

    public void initialize(){

    }

    @FXML
    void btnClearOnRoomAction(ActionEvent event) {

    }

    private String generateNewIds(){
        try {
            return userBO.generateUserId();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        }
        return "U00-001";
    }

    @FXML
    void btnNewSaveOnAction(ActionEvent event) {
        txtUserId.setText(generateNewIds());
    }


    @FXML
    void btnSaveOnUserAction(ActionEvent event) {
        String id=txtUserId.getText();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String contact=txtContact.getText();

       /* if (!userName.matches("")){
            new Alert(Alert.AlertType.ERROR, "Invalid userName").show();
            txtUserName.requestFocus();
        }else if ()*/
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

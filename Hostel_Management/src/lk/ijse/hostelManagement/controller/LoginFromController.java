package lk.ijse.hostelManagement.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.UserBO;
import lk.ijse.hostelManagement.model.UserDTO;
import lk.ijse.hostelManagement.util.Navigation;
import lk.ijse.hostelManagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoginFromController {


    public AnchorPane root;
    public Label lblUserName;
    public Label lblPassword;
    @FXML
    private Button btnLogin;

    @FXML
    private Button singin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USERBO);

    @FXML
    void btnNewSinginOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.SINGNFROM,root);

    }

    @FXML
    void btnSaveLoginOnAction(ActionEvent event) throws Exception {
        userBO.getAllUser();
        String email=txtUserName.getText();
        String password=txtPassword.getText();
        List<UserDTO> userDTOS= userBO.getAllUser();

        /*System.out.println(userDTOS);*/


        for (UserDTO userDTO:userDTOS){
            if (userDTO.getContact().equals(email) &&  userDTO.getPassword().equals(password)){
//                new Alert(Alert.AlertType.INFORMATION, "Your login").show();
                Navigation.navigation(Routes.MAINFROM,root);
            }else {
               /* new Alert(Alert.AlertType.INFORMATION, "Not login").show();*/
                lblUserName.setText("Invalid UserName");
                lblPassword.setText("Invalid Password");
            }
        }

    }

    public void btnShowPassowrd(ActionEvent event) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
    }
}

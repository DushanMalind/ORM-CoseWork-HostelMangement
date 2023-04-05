package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.UserBO;
import lk.ijse.hostelManagement.model.UserDTO;
import lk.ijse.hostelManagement.util.Navigation;
import lk.ijse.hostelManagement.util.Routes;

import java.io.IOException;

public class SignUpFromController {

    public TextField txtId;
    public Button btnNewSign;
    @FXML
    private Button btnSign;

    @FXML
    private JFXComboBox<?> cmdUsers;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserNames;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USERBO);


    private String generateNewIds(){
        try {
            return userBO.generateUserId();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "U00-001";
    }

    public void btnNewSignID(ActionEvent event) {
        txtId.setText(generateNewIds());
    }

    @FXML
    void btnSignnOnAction(ActionEvent event) throws Exception {

        String id=txtId.getText();
        String userName=txtUserNames.getText();
        String password=txtPassword.getText();
        String email=txtEmail.getText();


        if (!userName.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtUserNames.requestFocus();
            return;
        }else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            return;
        }else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            txtEmail.requestFocus();
            return;
        }


        if (btnSign.getText().equalsIgnoreCase("Sign")){
            userBO.saveUser(new UserDTO(id,userName,password,email));
            new Alert(Alert.AlertType.INFORMATION, "Your login").show();
        }

    }

    public void btnLoginOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.LOGINFROM,root);
    }


}

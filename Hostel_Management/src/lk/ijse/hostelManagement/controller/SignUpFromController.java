package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.UserBO;
import lk.ijse.hostelManagement.model.UserDTO;
import lk.ijse.hostelManagement.util.Navigation;
import lk.ijse.hostelManagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpFromController {

    public TextField txtId;
    public Button btnNewSign;
    public Button btnShow;
    public Label lblPassword;
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

    public void initialize(){
        iniUI();
    }

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
        txtId.setEditable(false);
        txtUserNames.setDisable(false);
        txtPassword.setDisable(false);
        txtEmail.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewIds());
        txtUserNames.clear();
        txtPassword.clear();
        txtEmail.clear();

    }

    public void iniUI(){
        txtId.clear();
        txtUserNames.clear();
        txtPassword.clear();
        txtEmail.clear();
        txtId.setDisable(true);
        txtUserNames.setDisable(true);
        txtPassword.setDisable(true);
        txtEmail.setDisable(true);
        lblPassword.setText("");

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


    public void btnShowAction(ActionEvent event) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
        lblPassword.setText(password);

    }

}

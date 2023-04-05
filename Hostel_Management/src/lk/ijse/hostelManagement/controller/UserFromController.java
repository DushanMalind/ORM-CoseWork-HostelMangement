package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.UserBO;
import lk.ijse.hostelManagement.model.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFromController {

    public AnchorPane root;
    @FXML
    private JFXButton btnAddNewUser;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> coluserName;

    @FXML
    private TableView<UserDTO> tblUser;

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

    public void initialize() {
        loadAllUsers();
        iniUI();

        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coluserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue==null);
            btnSave.setText(newValue !=null ? "update" : "save");
            btnSave.setDisable(newValue==null);

            if (newValue!=null){
                txtUserId.setText(newValue.getId());
                txtUserName.setText(newValue.getUserName());
                txtPassword.setText(newValue.getPassword());
                txtContact.setText(newValue.getContact());
            }
        });

    }


    private void loadAllUsers(){
        tblUser.getItems().clear();

        try {
            List<UserDTO>userDTOList= userBO.getAllUser();
            ObservableList<UserDTO>observableList= FXCollections.observableList(userDTOList);
            tblUser.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniUI() {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtContact.clear();
        txtUserId.setDisable(true);
        txtUserName.setDisable(true);
        txtPassword.setDisable(true);
        txtContact.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    @FXML
    void btnClearOnRoomAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            try {
                userBO.deleteUser(new UserDTO(txtUserId.getText(),txtUserName.getText(),txtPassword.getText(),
                        txtContact.getText()));
                tblUser.getItems().remove(tblUser.getSelectionModel().getSelectedItem());
                tblUser.getSelectionModel().clearSelection();
                iniUI();
            }catch (Exception e){

            }
        }

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
        txtUserId.setEditable(false);
        txtUserName.setDisable(false);
        txtPassword.setDisable(false);
        txtContact.setDisable(false);
        txtUserId.clear();
        txtUserId.setText(generateNewIds());
        txtUserName.clear();
        txtPassword.clear();
        txtContact.clear();
        btnSave.setDisable(false);
        btnSave.setText("Save User");
        tblUser.getSelectionModel().clearSelection();
    }


    @FXML
    void btnSaveOnUserAction(ActionEvent event) throws Exception {
        String id=txtUserId.getText();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String contact=txtContact.getText();

        if (!userName.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtUserName.requestFocus();
            return;
        }else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            return;
        }else if (!contact.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            txtContact.requestFocus();
            return;
        }

        if (btnSave.getText().equals("Save User")){
            try {
                userBO.saveUser(new UserDTO(id,userName,password,contact));
                tblUser.getItems().add(new UserDTO(id,userName,password,contact));
            }catch (Exception e){

            }

        }else {
            try {
                userBO.updateUser(new UserDTO(id,userName,password,contact));
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            }
            UserDTO userDTO=tblUser.getSelectionModel().getSelectedItem();
            userDTO.setUserName(userName);
            userDTO.setPassword(password);
            userDTO.setContact(contact);
            tblUser.refresh();
        }
        btnAddNewUser.fire();
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

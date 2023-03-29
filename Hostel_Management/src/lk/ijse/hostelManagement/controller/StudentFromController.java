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
import lk.ijse.hostelManagement.bo.custom.StudentBO;
import lk.ijse.hostelManagement.model.StudentDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class StudentFromController {

    public JFXButton btnDelete;
    @FXML
    private JFXButton btnAddNewStudent;

    @FXML
    private JFXButton btnSave;

    @FXML
    private ComboBox<String> cmdGender;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colStuId;

    @FXML
    private TableColumn<?, ?> colStuName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StudentDTO> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtStudentId;

    StudentBO studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENTBO);

    public void initialize(){
        setCmdGender();
        lordAllStudent();
        iniUI();
        colStuId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStuName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue==null);
            btnSave.setText(newValue !=null ?"update" : "save");
            btnSave.setDisable(newValue==null);

            if (newValue!=null){
                txtStudentId.setText(newValue.getStudentId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                txtDob.setText(newValue.getDob());


                if (newValue.getGender().equals("Male")){
                    setCmdGender();
                    cmdGender.getSelectionModel().select(0);
                }else {
                    cmdGender.getSelectionModel().select(1);
                }
            }
        });
    }

    public void iniUI(){
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        cmdGender.getSelectionModel().clearSelection();
        txtStudentId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDob.setDisable(true);
        cmdGender.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    private void lordAllStudent(){
        tblCustomer.getItems().clear();

        try {
            ArrayList<StudentDTO>allStudents= (ArrayList<StudentDTO>) studentBO.getAllStudent();
            for (StudentDTO s : allStudents){
                tblCustomer.getItems().add(new StudentDTO(s.getStudentId(),s.getName(),s.getAddress(),s.getContact(),s.getDob(),s.getGender()));
            }
        }catch (Exception e){

        }
    }

    @FXML
    void btnClearOnStudentAction(ActionEvent event) {
        StudentDTO studentDTO=new StudentDTO();
        String id=tblCustomer.getSelectionModel().getSelectedItem().getStudentId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            try {
                studentBO.deleteStudent(studentDTO);
                tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
                tblCustomer.getSelectionModel().clearSelection();
                iniUI();
            }catch (Exception e){

            }
        }
    }

    private String generateNewIds(){
        try {
           return studentBO.generateStudentId();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "S00-001";
    }

    @FXML
    void btnNewSaveOnAction(ActionEvent event) {
        txtStudentId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtDob.setDisable(false);
        cmdGender.setDisable(false);
        txtStudentId.clear();
        txtStudentId.setText(generateNewIds());
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        cmdGender.getSelectionModel().clearSelection();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save Student");
        tblCustomer.getSelectionModel().clearSelection();
    }

    private void setCmdGender(){
        ArrayList<String> genders=new ArrayList<>();
        genders.add("Male");
        genders.add("Female");

        ObservableList<String>observableList= FXCollections.observableList(genders);
        cmdGender.setItems(observableList);
    }

    @FXML
    void btnSaveOnStudentAction(ActionEvent event) {
        String id=txtStudentId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String dob=txtDob.getText();
        String gender=cmdGender.getSelectionModel().getSelectedItem();

        if (!name.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
        }else if (!address.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Address should not ").show();
            txtAddress.requestFocus();
        }else if (!contact.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")){
            new Alert(Alert.AlertType.ERROR, "Contact should not ").show();
        }/*else if (!dob.matches("")) {
            new Alert(Alert.AlertType.ERROR, "Dob should not ").show();
            txtDob.requestFocus();
            return;
        }*/

        if (btnSave.getText().equalsIgnoreCase("Save Student")){
            try {
                studentBO.saveStudent(new StudentDTO(id,name,address,contact,dob,gender));

                tblCustomer.getItems().add(new StudentDTO(id,name,address,contact,dob,gender));
            }catch (Exception e){

            }

        }else {
            try {
                studentBO.updateStudent(new StudentDTO(id,name,address,contact,dob,gender));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
                e.printStackTrace();
            }
            StudentDTO studentDTO=tblCustomer.getSelectionModel().getSelectedItem();
            studentDTO.setName(name);
            studentDTO.setAddress(address);
            studentDTO.setContact(contact);
            studentDTO.setDob(dob);
            studentDTO.setGender(gender);
            tblCustomer.refresh();
        }
        btnAddNewStudent.fire();
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

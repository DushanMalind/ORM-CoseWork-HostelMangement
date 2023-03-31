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
import lk.ijse.hostelManagement.bo.custom.ReservationBO;
import lk.ijse.hostelManagement.bo.custom.RoomBO;
import lk.ijse.hostelManagement.bo.custom.StudentBO;
import lk.ijse.hostelManagement.model.ReservationDTO;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.model.StudentDTO;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationController {

    public TableColumn colStudentName;
    public TableView <ReservationDTO>tblReservation1;
    public TableColumn colReservation1;
    public TableColumn colStudentId1;
    public TableColumn colRoomTypeId1;
    public TableColumn colStaus1;
    public TextField txtSearch1;
    public TableColumn colRoomQTY;
    public TableColumn colName;
    public CheckBox cbxStatus;
    public JFXButton btnClear;

    @FXML
    private TextField txtRoom;

    @FXML
    private TextField txtName;
    @FXML
    private JFXButton btnAddNewReservation;

    @FXML
    private JFXButton btnSave;

    @FXML
    private ComboBox<String>cmdRoomTypeId;

    @FXML
    private ComboBox<String>cmdStudentId;

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
    private TableView<StudentDetailsDTO> tblReservation;

    @FXML
    private TextField txtReservationId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtStatus;
    private StudentDTO studentData;
    private RoomDTO roomData;
    private String id;

    ReservationBO reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESERVATIONBO);

    public void initialize() throws Exception {
        loadAllStudents();
        loadAllRooms();
        loadAll();
        setAllProjection();
        setCheckStatus();
        iniUI();

        colReservation1.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colStudentId1.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomTypeId1.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomQTY.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStaus1.setCellValueFactory(new PropertyValueFactory<>("status"));


        colReservation.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));





    }

    void setCheckStatus(){
        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnClear.setDisable(newValue==null);
            btnSave.setText(newValue!=null ? "update" : "save");

            if (newValue!=null){
                if (newValue != null) {
                    cbxStatus.setDisable(false);
                    id= newValue.getResId();
                }
            }
        });
    }


    private void  loadAllStudents(){
        try {
            ArrayList<StudentDTO>allStudent= (ArrayList<StudentDTO>) reservationBO.geAllStudents();
            for (StudentDTO s : allStudent){
                cmdStudentId.getItems().add(s.getStudentId());
                if (s.getStudentId().equals(cmdStudentId.getValue())){
                    txtName.setText(s.getAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void  loadAllRooms(){
        try {
            ArrayList<RoomDTO>allRooms= (ArrayList<RoomDTO>) reservationBO.getAllRooms();
            for (RoomDTO r : allRooms){
                cmdRoomTypeId.getItems().add(r.getRoomTypeId());
                if (r.getRoomTypeId().equals(cmdRoomTypeId.getValue())){
                    txtRoom.setText(String.valueOf(r.getQty()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private StudentDTO getStudentDTO() throws Exception {
        String studentId=cmdStudentId.getSelectionModel().getSelectedItem();
        return reservationBO.getStudents(studentId);
    }

    private RoomDTO getRoomDTO() throws Exception {
        String roomId=cmdRoomTypeId.getSelectionModel().getSelectedItem();
        return reservationBO.getRooms(roomId);
    }


    @FXML
    void btnClearOnReservationAction(ActionEvent event) throws Exception {
        txtReservationId.clear();
        txtRoom.clear();
        cmdStudentId.getSelectionModel().clearSelection();
        cmdRoomTypeId.getSelectionModel().clearSelection();
        txtName.clear();


    }

    private void loadAll() throws Exception {
        List<ReservationDTO>reservationDTOList=reservationBO.getAllReservation();
        ObservableList<ReservationDTO>dtoObservableList=FXCollections.observableList(reservationDTOList);

        tblReservation1.setItems(dtoObservableList);

    }


    private String generateNewIds(){
        try {
            return reservationBO.generateReservationId();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "RE0-001";
    }

    public void iniUI(){
        cmdStudentId.setDisable(true);
        cmdRoomTypeId.setDisable(true);
        btnSave.setDisable(true);
        btnClear.setDisable(true);
        txtName.setEditable(false);
        txtRoom.setEditable(false);

    }

    @FXML
    void btnNewSaveOnAction(ActionEvent event) {
        txtReservationId.setEditable(false);
        txtName.setEditable(false);
        txtRoom.setEditable(false);
        cmdStudentId.setDisable(false);
        cmdRoomTypeId.setDisable(false);
        txtReservationId.setText(generateNewIds());
        btnSave.setText("Save Reservation");
        btnSave.setDisable(false);
        btnClear.setDisable(false);
    }



    @FXML
    void btnSaveOnReservationAction(ActionEvent event) throws Exception {

        if (btnSave.getText().equals("Save Reservation")){
            boolean isSaved=reserveRoom(getData());
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
                tblReservation1.getItems().clear();
                tblReservation.refresh();
                loadAll();

            }else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        }else if(btnSave.getText().equals("update")){
            if (cbxStatus.isSelected()){
                btnSave.setDisable(false);
                String status="paid";

                boolean isUpdated=reservationBO.checkStatusClick(id,status);
                tblReservation.refresh();
                if (isUpdated){
                    tblReservation.getItems().clear();
                    tblReservation1.getItems().clear();

                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();

                    loadAll();
                    setAllProjection();

                    cbxStatus.setDisable(true);
                    btnSave.setDisable(true);
                    tblReservation.refresh();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        }

/*

        boolean isSaved=reserveRoom(getData());
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
            tblReservation1.getItems().clear();
            loadAll();


        }else{
            if (cbxStatus.isSelected()){
                btnSave.setDisable(false);
                String status="paid";

                boolean isUpdated=reservationBO.checkStatusClick(id,status);
                if (isUpdated){
                    tblReservation.getItems().clear();
                    tblReservation1.getItems().clear();

                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();

                    loadAll();
                    setAllProjection();

                    cbxStatus.setDisable(true);
                    btnSave.setDisable(true);
                    tblReservation.refresh();
                }else if (){
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }else if (!cbxStatus.isSelected())btnSave.setDisable(true);
*/



    }

    private ReservationDTO getData() throws Exception {

        String status="unPaid";
        if (cbxStatus.isSelected()){
            status="paid";
        }

        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        StudentDTO studentData= getStudentDTO();
        RoomDTO roomData=getRoomDTO();

        return new ReservationDTO(
                                txtReservationId.getText(),
                                sqlDate,
                status,
                studentData,
                roomData
                        );
    }

    private boolean reserveRoom(ReservationDTO reservationDTO) throws Exception {
        boolean isSaved= Boolean.parseBoolean(reservationBO.saveReservation(reservationDTO));

        if (!isSaved){
            return false;
        }

        RoomDTO roomDTO=reservationDTO.getRoomDTO();
        roomDTO.setQty(roomDTO.getQty()-1);

        boolean isUpdate=reservationBO.updateRoomQty(roomDTO);

        if (!isUpdate){
            return false;
        }
        return true;
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

    public void cmdOnActionStudent(ActionEvent event) throws Exception {
        studentData=getStudentDTO();
        txtName.setText(studentData.getName());
    }

    public void cmdRoomOnAction(ActionEvent event) throws Exception {
        roomData=getRoomDTO();
        txtRoom.setText(String.valueOf(roomData.getQty()));
    }

    private void setAllProjection(){
        List<StudentDetailsDTO>list=reservationBO.getAllProjection();
        ObservableList<StudentDetailsDTO>studentDetailsDTOS=FXCollections.observableList(list);

        tblReservation.refresh();
        tblReservation.setItems(studentDetailsDTOS);

    }

    public void cbxStatusOnAction(ActionEvent event) {
        if (btnSave.getText().equals("Update")){
            if (cbxStatus.isSelected()){
                btnSave.setDisable(false);

                String status="paid";

            }else if (!cbxStatus.isSelected())btnSave.setDisable(true);
        }
    }
}




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
import lk.ijse.hostelManagement.bo.custom.RoomBO;
import lk.ijse.hostelManagement.model.RoomDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class RoomFromController {

    public JFXButton btnDelete;
    @FXML
    private JFXButton btnAddNewRoom;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<RoomDTO, String> colKeyMoney;

    @FXML
    private TableColumn<RoomDTO, String> colQTY;

    @FXML
    private TableColumn<RoomDTO, String> colRoomId;

    @FXML
    private TableColumn<RoomDTO, String> colType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RoomDTO> tblCustomer;

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

    RoomBO roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ROOMBO);


    public void initialize(){

        iniUI();
        lordAllRoom();

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue==null);
            btnSave.setText(newValue != null ? "update" : "save");
            btnSave.setDisable(newValue==null);

            if (newValue!=null){
                txtRoomTypeId.setText(newValue.getRoomTypeId());
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQty.setText(String.valueOf(newValue.getQty()));
            }
        });

    }

    @FXML
    void btnClearOnRoomAction(ActionEvent event) {
        RoomDTO roomDTO=new RoomDTO();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            try {
                roomBO.deleteRoom(roomDTO);
                tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
                tblCustomer.getSelectionModel().clearSelection();
                iniUI();
            }catch (Exception e){

            }
        }

    }

    public void iniUI(){
        txtRoomTypeId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtRoomTypeId.setDisable(true);
        txtType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }


    private void lordAllRoom(){
        tblCustomer.getItems().clear();

        try {
            ArrayList<RoomDTO>roomDTOArrayList= (ArrayList<RoomDTO>) roomBO.getAllRoom();
            System.out.println(roomDTOArrayList);
            for (RoomDTO  r : roomDTOArrayList){
                tblCustomer.getItems().add(new RoomDTO(r.getRoomTypeId(),r.getType(),r.getKeyMoney(),r.getQty()));
            }

        }catch (Exception e){

        }

    }

    private String generateNewIds() throws Exception {
       try {
          return roomBO.generateRoomId();
       }catch (Exception e){
           new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
           e.printStackTrace();
       }
       return "RES-001";
    }

    @FXML
    void btnNewSaveOnAction(ActionEvent event) throws Exception {
        txtRoomTypeId.setEditable(false);
        txtType.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);
        txtRoomTypeId.clear();
        txtRoomTypeId.setText(generateNewIds());
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtType.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save Room");
        tblCustomer.getSelectionModel().clearSelection();
    }



    @FXML
    void btnSaveOnRoomAction(ActionEvent event) {
        String roomId=txtRoomTypeId.getText();
        String type=txtType.getText();
        String keyMoney=txtKeyMoney.getText();
        int qty= Integer.parseInt(txtQty.getText());

        if (!type.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid Type").show();
            txtType.requestFocus();
            return;
        }else if (!keyMoney.matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR, "Invalid keyMoney").show();
            txtKeyMoney.requestFocus();
            return;
        }else if (!txtQty.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid keyMoney").show();
            txtQty.getText();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("Save Room")){
            try {
                roomBO.saveRoom(new RoomDTO(roomId,type,keyMoney,qty));

                tblCustomer.getItems().add(new RoomDTO(roomId,type,keyMoney,qty));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                e.printStackTrace();
            }

        }else {
            try {
                roomBO.updateRoom(new RoomDTO(roomId,type,keyMoney,qty));
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + roomId + e.getMessage()).show();
            }
            RoomDTO roomDTO=tblCustomer.getSelectionModel().getSelectedItem();
            roomDTO.setType(type);
            roomDTO.setKeyMoney(keyMoney);
            roomDTO.setQty(qty);
            tblCustomer.refresh();
        }
        btnAddNewRoom.fire();

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

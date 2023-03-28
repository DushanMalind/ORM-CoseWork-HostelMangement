package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> getAllRoom() throws Exception;

    String saveRoom(RoomDTO roomDTO) throws Exception;

    boolean updateRoom(RoomDTO roomDTO) throws Exception;

    boolean deleteRoom(RoomDTO roomDTO) throws Exception;

    String generateRoomId() throws Exception;;
}

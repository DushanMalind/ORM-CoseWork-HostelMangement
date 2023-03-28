package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.RoomBO;
import lk.ijse.hostelManagement.model.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    @Override
    public List<RoomDTO> getAllRoom() throws SQLException {
        return null;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        return false;
    }

    @Override
    public String generateRoomId() throws Exception {
        return null;
    }
}

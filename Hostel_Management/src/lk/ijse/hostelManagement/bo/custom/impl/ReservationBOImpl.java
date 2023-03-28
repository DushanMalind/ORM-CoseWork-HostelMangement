package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.ReservationBO;
import lk.ijse.hostelManagement.model.ReservationDTO;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    @Override
    public List<ReservationDTO> getAllReservation() throws SQLException {
        return null;
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) throws Exception {
        return false;
    }

    @Override
    public String generateReservationId() throws Exception {
        return null;
    }

    @Override
    public StudentDTO getStudent() throws Exception {
        return null;
    }

    @Override
    public RoomDTO getRoom() throws Exception {
        return null;
    }
}

package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.model.ReservationDTO;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    List<ReservationDTO> getAllReservation() throws SQLException;

    boolean saveReservation(ReservationDTO reservationDTO) throws Exception;

    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;

    boolean deleteReservation(ReservationDTO reservationDTO) throws Exception;

    String generateReservationId() throws Exception;;

    StudentDTO getStudent(String id) throws Exception;

    RoomDTO getRoom(String id) throws Exception;
}

package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.model.ReservationDTO;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.model.StudentDTO;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    List<ReservationDTO> getAllReservation() throws Exception;

    String saveReservation(ReservationDTO reservationDTO) throws Exception;

    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;

    boolean deleteReservation(ReservationDTO reservationDTO) throws Exception;

    String generateReservationId() throws Exception;;

    StudentDTO getStudents(String id) throws Exception;

    RoomDTO getRooms(String id) throws Exception;

    List<String> getStudentIds();

    List<String> getRoomIds();

    List<StudentDTO> geAllStudents() throws Exception;

    List<RoomDTO>getAllRooms() throws Exception;

    boolean updateRoomQty(RoomDTO roomDTO) throws Exception;

    List<StudentDetailsDTO>getAllProjection();

    boolean checkStatusClick(String id,String status);
}

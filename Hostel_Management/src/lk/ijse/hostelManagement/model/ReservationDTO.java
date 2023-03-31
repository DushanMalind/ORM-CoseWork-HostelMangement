package lk.ijse.hostelManagement.model;

import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.entity.Student;

import java.sql.Date;

public class ReservationDTO {
    private String resId;
    private Date date;
    private String status;

    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String studentId;
    private String roomId;



    public ReservationDTO(String resId, Date date, String status) {
        this.resId = resId;
        this.date = date;
        this.status = status;
    }

    public ReservationDTO(String resId, Date date, String status, StudentDTO studentDTO, RoomDTO roomDTO) {
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.studentDTO = studentDTO;
        this.roomDTO = roomDTO;
        studentId=studentDTO.getStudentId();
        roomId=roomDTO.getRoomTypeId();
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        studentId=studentDTO.getStudentId();
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
        roomId=roomDTO.getRoomTypeId();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", studentDTO=" + studentDTO +
                ", roomDTO=" + roomDTO +
                ", studentId='" + studentId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}

package lk.ijse.hostelManagement.model;

import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.entity.Student;

import java.sql.Date;

public class ReservationDTO {
    private String resId;
    private Date date;
    private String status;

    private Student student;
    private Room room;

    public ReservationDTO() {
    }

    public ReservationDTO(String resId, Date date, String status) {
        this.resId = resId;
        this.date = date;
        this.status = status;
    }

    public ReservationDTO(String resId, Date date, String status, Student student, Room room) {
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.student = student;
        this.room = room;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
}

package lk.ijse.hostelManagement.model;

import java.sql.Date;

public class ReservationDTO {
    private String resId;
    private Date date;
    private String studentId;
    private String roomTypeId;
    private String status;



    public ReservationDTO() {
    }

    public ReservationDTO(String resId, Date date, String studentId, String roomTypeId, String status) {
        this.resId = resId;
        this.date = date;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
        this.status = status;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", studentId='" + studentId + '\'' +
                ", roomTypeId='" + roomTypeId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

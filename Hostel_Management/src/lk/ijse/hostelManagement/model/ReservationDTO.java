package lk.ijse.hostelManagement.model;

import java.sql.Date;

public class ReservationDTO {
    private long resId;
    private Date date;
    private long studentId;
    private long roomTypeId;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(long resId, Date date, long studentId, long roomTypeId, String status) {
        this.resId = resId;
        this.date = date;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(long roomTypeId) {
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
                "resId=" + resId +
                ", date=" + date +
                ", studentId=" + studentId +
                ", roomTypeId=" + roomTypeId +
                ", status='" + status + '\'' +
                '}';
    }
}

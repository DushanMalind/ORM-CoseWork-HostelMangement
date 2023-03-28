package lk.ijse.hostelManagement.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @Column(name = "reservationId",length = 10)
    private String resId;
    @Column(name = "data")
    private Date date;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "studentId",referencedColumnName = "studentId",insertable = false,updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "roomTypeId",referencedColumnName = "roomTypeId",insertable = false,updatable = false)
    private Room room;



    public Reservation() {
    }

    public Reservation(String resId, Date date, String status) {
        this.resId = resId;
        this.date = date;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}

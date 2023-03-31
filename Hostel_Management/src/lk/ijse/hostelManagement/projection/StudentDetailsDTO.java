package lk.ijse.hostelManagement.projection;


import lk.ijse.hostelManagement.entity.Room;

import java.util.Date;

public class StudentDetailsDTO {
    private String studentId;
    private String name;
    private String contact;

    private Date date;
    private String resId;

    private String roomId;

    private Room room;


    public StudentDetailsDTO(String studentId, String name, String contact, Date date, String resId, Room room) {
        this.studentId = studentId;
        this.name = name;
        this.contact = contact;
        this.date = date;
        this.resId = resId;
        this.room = room;
        roomId=room.getRoomTypeId();
    }

    public StudentDetailsDTO() {
    }



    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        roomId=room.getRoomTypeId();
    }


}

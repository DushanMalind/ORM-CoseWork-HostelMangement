package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.dao.SuperDAO;
import lk.ijse.hostelManagement.entity.Room;
import org.hibernate.Session;

import java.util.List;

public interface RoomDAO extends SuperDAO,CrudDAO<Room,String> {
    void setSession(Session session);
    List<String> geIds() throws Exception;
}

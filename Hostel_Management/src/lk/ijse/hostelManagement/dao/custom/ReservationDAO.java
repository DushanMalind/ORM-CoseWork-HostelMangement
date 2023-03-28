package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.dao.SuperDAO;
import lk.ijse.hostelManagement.entity.Reservation;
import org.hibernate.Session;

public interface ReservationDAO extends SuperDAO,CrudDAO<Reservation,String> {
    void setSession(Session session);
}

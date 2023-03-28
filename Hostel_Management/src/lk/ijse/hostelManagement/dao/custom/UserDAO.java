package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.entity.Users;
import org.hibernate.Session;

public interface UserDAO extends CrudDAO<Users,String> {
    void setSession(Session session);
}

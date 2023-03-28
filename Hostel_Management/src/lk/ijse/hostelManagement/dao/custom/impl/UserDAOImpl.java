package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private  Session session;
    @Override
    public List<Users> getAll() throws SQLException, ClassNotFoundException {
        String sql="FROM Users";
        Query query=session.createQuery(sql);
        List list= query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Users dto) throws SQLException, ClassNotFoundException {
        return (String) session.save(dto);
    }

    @Override
    public void update(Users dto) throws SQLException, ClassNotFoundException {
        session.update(dto);
    }

    @Override
    public void delete(Users dto) throws SQLException, ClassNotFoundException {
        session.delete(dto);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Users users=null;
        try {
            String sql="FROM Reservation ORDER BY id desc";
            Query query=session.createQuery(sql);
            query.setMaxResults(1);
            users= (Users) query.uniqueResult();
        }catch (Exception e){

        }
        String lastId=users.getId();

        if (lastId!=null){
            int newCustomerId=Integer.parseInt(lastId.replace("U00-",""))+1;
            return String.format("U00-%03d",newCustomerId);
        }
        return "U00-001";
    }

    @Override
    public Users get(String id) throws SQLException, ClassNotFoundException {
        return session.get(Users.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}

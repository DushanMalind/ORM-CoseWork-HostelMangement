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
//        String sql="FROM Users WHERE userName=?1 and password=?1";
        String sql="FROM Users";
//        String sql=" FROM Users WHERE userName = '' AND password = 'userName'";
//        String sql="from Users u where u.userName=0? and u.password=?1";
        Query query=session.createQuery(sql);
        List<Users> list= query.list();
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
        String sql="FROM Users ORDER BY id DESC";
        Users student= (Users) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if (student!=null){
            String lastId=student.getId();
            int newCustomerId=Integer.parseInt(lastId.replace("U00-",""))+1;
            return String.format("U00-%03d",newCustomerId);
        }
        return "U00-001";
    }

    @Override
    public Users getObject(String id) throws SQLException, ClassNotFoundException {
        return session.get(Users.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}

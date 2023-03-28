package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.StudentDAO;
import lk.ijse.hostelManagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

  /*  private static StudentDAOImpl studentDAO;

    private StudentDAOImpl(){

    }
    public static StudentDAOImpl getInstance(){
        return null==studentDAO ? studentDAO=new StudentDAOImpl() : studentDAO;
    }*/

    private  Session session;

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        String sql="FROM Student";
        Query query=session.createQuery(sql);
        List list= query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Student dto) throws SQLException, ClassNotFoundException {
        return (String) session.save(dto);
    }

    @Override
    public void update(Student dto) throws SQLException, ClassNotFoundException {
        session.update(dto);
    }

    @Override
    public void delete(Student dto) throws SQLException, ClassNotFoundException {
        session.delete(dto);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Student get(String id) throws SQLException, ClassNotFoundException {
        return session.get(Student.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}

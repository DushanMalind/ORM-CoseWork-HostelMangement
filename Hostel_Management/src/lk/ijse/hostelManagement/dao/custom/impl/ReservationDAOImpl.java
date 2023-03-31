package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.ReservationDAO;
import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private  Session session;
    @Override
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        String sql="FROM Reservation";
        Query query=session.createQuery(sql);
        List list= query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Reservation dto) throws SQLException, ClassNotFoundException {
        return (String) session.save(dto);
    }

    @Override
    public void update(Reservation dto) throws SQLException, ClassNotFoundException {
        session.update(dto);
    }

    @Override
    public void delete(Reservation dto) throws SQLException, ClassNotFoundException {
        session.delete(dto);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String sql="FROM Reservation ORDER BY id DESC";
        Reservation reservation= (Reservation) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if (reservation!=null){
            String lastId=reservation.getResId();
            int newCustomerId=Integer.parseInt(lastId.replace("RE0-",""))+1;
            return String.format("RE0-%03d",newCustomerId);
        }
        return "RE0-001";
    }

    @Override
    public Reservation getObject(String id) throws SQLException, ClassNotFoundException {
        return session.get(Reservation.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public boolean changeCheckBOXValue(String id, String status) {
        String hql="update Reservation r set r.status=:sts Where r.resId=:rid";
        Query query=session.createQuery(hql);
        query.setParameter("sts",status);
        query.setParameter("rid",status);
        int value= query.executeUpdate();
        return value>=0;
    }
}

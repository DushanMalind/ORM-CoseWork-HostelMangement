package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.ReservationDAO;
import lk.ijse.hostelManagement.entity.Reservation;
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
        Reservation reservation=null;
        try {
            String sql="FROM Reservation ORDER BY id desc";
            Query query=session.createQuery(sql);
            query.setMaxResults(1);
            reservation= (Reservation) query.uniqueResult();
        }catch (Exception e){

        }
        String lastId=reservation.getResId();

        if (lastId!=null){
            int newCustomerId=Integer.parseInt(lastId.replace("RE0-",""))+1;
            return String.format("RE0-%03d",newCustomerId);
        }
        return "RE0-001";
    }

    @Override
    public Reservation get(String id) throws SQLException, ClassNotFoundException {
        return session.get(Reservation.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}

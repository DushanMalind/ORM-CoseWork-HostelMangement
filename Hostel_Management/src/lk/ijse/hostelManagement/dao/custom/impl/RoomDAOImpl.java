package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.RoomDAO;
import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private  Session session;
    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {
        String sql="FROM Room";
        Query query=session.createQuery(sql);
        List list= query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Room dto) throws SQLException, ClassNotFoundException {
        return (String) session.save(dto);
    }

    @Override
    public void update(Room dto) throws SQLException, ClassNotFoundException {
        session.update(dto);
    }

    @Override
    public void delete(Room dto) throws SQLException, ClassNotFoundException {
        session.delete(dto);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Room room=null;
        try {
            String sql="FROM Reservation ORDER BY id desc";
            Query query=session.createQuery(sql);
            query.setMaxResults(1);
            room= (Room) query.uniqueResult();
        }catch (Exception e){

        }
        String lastId=room.getRoomTypeId();

        if (lastId!=null){
            int newCustomerId=Integer.parseInt(lastId.replace("R00-",""))+1;
            return String.format("R00-%03d",newCustomerId);
        }
        return "R00-001";
    }

    @Override
    public Room get(String id) throws SQLException, ClassNotFoundException {
        return session.get(Room.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}

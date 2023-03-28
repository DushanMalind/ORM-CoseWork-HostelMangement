package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.RoomBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.RoomDAO;
import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    private Session session;
    @Override
    public List<RoomDTO> getAllRoom() throws Exception {
        List<Room>allRooms=roomDAO.getAll();
        List<RoomDTO>roomDTOS=new ArrayList<>();
        for (Room room : allRooms){
            roomDTOS.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public String saveRoom(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDAO.setSession(session);
            String id=(String) roomDAO.save(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),
                    roomDTO.getQty()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return "R00-001";
        }

    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.update(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),
                    roomDTO.getQty()));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }

    }

    @Override
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.delete(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),
                    roomDTO.getQty()));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }

    }

    @Override
    public String generateRoomId() throws Exception {
        return roomDAO.generateNewID();
    }
}

package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.ReservationBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.ReservationDAO;
import lk.ijse.hostelManagement.dao.custom.RoomDAO;
import lk.ijse.hostelManagement.dao.custom.StudentDAO;
import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.model.ReservationDTO;
import lk.ijse.hostelManagement.model.RoomDTO;
import lk.ijse.hostelManagement.model.StudentDTO;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESRVATIONDAO);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    private Session session;
    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        List<Reservation>allReservation= reservationDAO.getAll();
        List<ReservationDTO>reservationDTOList=new ArrayList<>();
        for (Reservation reservation : allReservation){
            reservationDTOList.add(new ReservationDTO(reservation.getResId(),reservation.getDate(),
                    reservation.getStatus()));
        }
        return reservationDTOList;
    }

    @Override
    public String saveReservation(ReservationDTO reservationDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            reservationDAO.setSession(session);
            String id=(String) reservationDAO.save(new Reservation(reservationDTO.getResId(),reservationDTO.getDate(),
                    reservationDTO.getStatus()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return "RE0-001";
        }

    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.update(new Reservation(reservationDTO.getResId(),reservationDTO.getDate(),
                    reservationDTO.getStatus()));
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
    public boolean deleteReservation(ReservationDTO reservationDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.delete(new Reservation(reservationDTO.getResId(),reservationDTO.getDate(),
                    reservationDTO.getStatus()));
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
    public String generateReservationId() throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        reservationDAO.setSession(session);
        return reservationDAO.generateNewID();
    }

    @Override
    public StudentDTO getStudents(String id) throws Exception {
        try {
            session= SessionFactoryConfiguaration.getInstance().getSession();
            studentDAO.setSession(session);
            Student student=studentDAO.get(id);
            session.close();
            return new StudentDTO(student.getStudentId(),student.getName(),student.getAddress(),student.getContact(),
                    student.getDob(),student.getGender());
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public RoomDTO getRooms(String id) throws Exception {
        try {
            session= SessionFactoryConfiguaration.getInstance().getSession();
            reservationDAO.setSession(session);
            Room room=roomDAO.get(id);
            return new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty());
        }catch (Exception e){
            throw e;
        }

    }
}

package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.UserBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.entity.Users;
import lk.ijse.hostelManagement.model.UserDTO;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USERDAO);
    private Session session;
    @Override
    public List<UserDTO> getAllUser() throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        userDAO.setSession(session);
        List<Users>allUsers=userDAO.getAll();
        System.out.println(allUsers);
        List<UserDTO>userDTOList=new ArrayList<>();
        for (Users users : allUsers){
            userDTOList.add(new UserDTO(users.getId(),users.getUserName(),users.getPassword(),users.getContact()));
        }
        System.out.println(userDTOList);
        return userDTOList;
    }

    @Override
    public String saveUser(UserDTO userDTO) throws Exception {
        session=SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            userDAO.setSession(session);
            String id=(String) userDAO.save(new Users(userDTO.getId(),userDTO.getUserName(),userDTO.getPassword(),
                    userDTO.getContact()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return "U00-001";
        }

    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        session=SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            userDAO.setSession(session);
            userDAO.update(new Users(userDTO.getId(),userDTO.getUserName(),userDTO.getPassword(),
                    userDTO.getContact()));
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
    public boolean deleteUser(UserDTO userDTO) throws Exception {
        session=SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            userDAO.setSession(session);
            userDAO.delete(new Users(userDTO.getId(),userDTO.getUserName(),userDTO.getPassword(),
                    userDTO.getContact()));
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
    public String generateUserId() throws Exception {
        session=SessionFactoryConfiguaration.getInstance().getSession();
        userDAO.setSession(session);
        return userDAO.generateNewID();
    }
}

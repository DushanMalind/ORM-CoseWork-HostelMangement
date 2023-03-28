package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.UserBO;
import lk.ijse.hostelManagement.model.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    @Override
    public List<UserDTO> getAllUser() throws SQLException {
        return null;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public String generateUserId() throws Exception {
        return null;
    }
}

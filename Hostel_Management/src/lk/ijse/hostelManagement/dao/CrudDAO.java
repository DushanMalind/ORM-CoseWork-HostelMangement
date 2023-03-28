package lk.ijse.hostelManagement.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T,ID>{
    List<T> getAll() throws SQLException, ClassNotFoundException;
    ID save(T dto) throws SQLException, ClassNotFoundException;
    void update(T dto) throws SQLException, ClassNotFoundException;
    void delete(T dto) throws SQLException, ClassNotFoundException;
    ID generateNewID() throws SQLException, ClassNotFoundException;
    T get(ID id) throws SQLException, ClassNotFoundException;

}

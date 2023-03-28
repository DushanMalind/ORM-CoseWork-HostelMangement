package lk.ijse.hostelManagement.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T,ID>{
    List<T> getAll() throws Exception;
    ID save(T dto) throws Exception;
    void update(T dto) throws Exception;
    void delete(T dto) throws Exception;
    ID generateNewID() throws Exception;
    T get(ID id) throws Exception;

}

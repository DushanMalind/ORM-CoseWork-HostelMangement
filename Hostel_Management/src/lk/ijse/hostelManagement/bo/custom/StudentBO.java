package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.model.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent() throws Exception;

    String saveStudent(StudentDTO studentDTO) throws Exception;

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    String generateStudentId() throws Exception;;

}

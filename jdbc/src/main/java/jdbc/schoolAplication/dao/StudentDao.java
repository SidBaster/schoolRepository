package jdbc.schoolAplication.dao;

import java.util.List;
import java.util.Optional;

import jdbc.schoolAplication.entity.Student;

public interface StudentDao {
    boolean delete(int id);
    
    boolean save(Student studentEntity);
    
    boolean update(int groupID, String firstName, String lastName, int studentID);
    
    List<Optional<Student>> findAll();
    
    List<String> findAllName();
    
    List<Optional<Student>> findStudentsByCourseName(String courseName);
    
    void saveAll(List<Student> students);
    
    int findByName(String studentName); 
}

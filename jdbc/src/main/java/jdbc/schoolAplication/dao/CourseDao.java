package jdbc.schoolAplication.dao;

import java.util.List;
import java.util.Optional;

import jdbc.schoolAplication.entity.Course;
import jdbc.schoolAplication.entity.Student;

public interface CourseDao {
    void saveAll(List<Course> courses);
    
    List<Optional<Student>> buildListStudents(String courseName);
    
    int findByName(String courseName);
    
    List<String> findAllName();
    
    List<Course> findAll();
    
    boolean save(Course course);
    
    boolean delete(int courseID);
    
    boolean update(String courseName, String courseDescription, int courseID);
 }

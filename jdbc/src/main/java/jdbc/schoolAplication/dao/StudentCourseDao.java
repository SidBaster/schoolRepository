package jdbc.schoolAplication.dao;

import java.util.List;

import jdbc.schoolAplication.entity.StudentCourse;

public interface StudentCourseDao {
    boolean save(StudentCourse studentCourse);
    
    boolean update(int courseID, int studentID);
    
    boolean addStudentToCourse(String studentName, String courseName);
    
    boolean delete(int studentID, int courseID);
    
    List<StudentCourse> findAll();
}

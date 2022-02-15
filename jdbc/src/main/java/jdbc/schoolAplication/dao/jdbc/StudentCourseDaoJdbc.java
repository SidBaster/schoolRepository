package jdbc.schoolAplication.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.schoolAplication.dao.ConnectionManager;
import jdbc.schoolAplication.dao.StudentCourseDao;
import jdbc.schoolAplication.entity.StudentCourse;
import jdbc.schoolAplication.exception.DaoException;

public class StudentCourseDaoJdbc implements StudentCourseDao{
    private static final StudentCourseDaoJdbc INSTANCE = new StudentCourseDaoJdbc();
    private static final String FIND_ALL_SQL_SCRIPT = "SELECT student_id, course_id FROM student_courses";
    private static final String INSERT_SQL_SCRIPT = "INSERT INTO student_courses(student_id, course_id) VALUES (?, ?)";
    private static final String UPDATE_SQL_SCRIPT = "UPDATE student_courses SET course_id = ? WHERE student_id = ?";
    private static final String DELETE_STUDENT_FROM_COURSE = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
    
    public void saveAll(List<StudentCourse> list) {
        list.stream().forEach(st -> save(st));
    }
    
    public List<StudentCourse> findAll() {
        List<StudentCourse> studentCourses = new ArrayList<>();
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL_SCRIPT)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                studentCourses.add(new StudentCourse(resultSet.getInt(1), resultSet.getInt(2)));
            }
            
            return studentCourses;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean save(StudentCourse studentCourse) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL_SCRIPT)) {
            statement.setInt(1, studentCourse.getStudendID());
            statement.setInt(2, studentCourse.getCourseID());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean update(int courseID, int studentID) {
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_SCRIPT)) {
            statement.setInt(1, courseID);
            statement.setInt(2, studentID);
            
            int rowsUpdated = statement.executeUpdate();
            
            return rowsUpdated > 0;  
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean addStudentToCourse(String studentName, String courseName) {
        StudentDaoJdbc studentDao = StudentDaoJdbc.getInstance();
        CourseDaoJdbc courseDao = CourseDaoJdbc.getInstance();
        StudentCourseDaoJdbc studentCourseDao = StudentCourseDaoJdbc.getInstance();
        
        int studentID = studentDao.findByName(studentName);
        int courseID = courseDao.findByName(courseName);
        
        if (studentID == 0 || courseID == 0) {
            return false; 
        } else {
            studentCourseDao.save(new StudentCourse(studentID, courseID));
            return true;
        }
    }
    
    public boolean delete(int studentID, int courseID) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_FROM_COURSE)) {
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);
            
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public static StudentCourseDaoJdbc getInstance() {
        return INSTANCE;
    }
    
    private StudentCourseDaoJdbc() {}
}

package jdbc.schoolAplication.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.schoolAplication.dao.ConnectionManager;
import jdbc.schoolAplication.dao.CourseDao;
import jdbc.schoolAplication.entity.Course;
import jdbc.schoolAplication.entity.Student;
import jdbc.schoolAplication.exception.DaoException;

public class CourseDaoJdbc implements CourseDao {
    private static final CourseDaoJdbc INSTANCE = new CourseDaoJdbc();
    private static final String INSERT_SQL_SCRIPT = "INSERT INTO courses(course_name, course_description) VALUES(?, ?)";
    private static final String DELETE_SQL_SCRIPT = "DELETE FROM courses WHERE course_id = ?";
    private static final String FIND_ALL_SCRIPT = "SELECT course_id, course_name, course_description FROM courses";
    private static final String UPDATE_SQL_SCRIPT = "UPDATE courses SET course_name = ?, course_description = ? WHERE course_id = ?";
    private static final String SELECT_ALL_NAME = "SELECT course_name FROM courses";
    private static final String FIND_COURSEID_BY_NAME_SCRIPT = "SELECT course_id FROM courses WHERE course_name = ?";
    
    public static CourseDaoJdbc getInstance() {
        return INSTANCE;
    }
    
    public void saveAll(List<Course> courses) {
        courses.stream().forEach(c -> save(c));
    }
    
    public List<Optional<Student>> buildListStudents(String courseName) {
        CourseDao courseDao = CourseDaoJdbc.getInstance();
        List<String> coursesName = courseDao.findAllName();
        
        StudentDaoJdbc studentDao = StudentDaoJdbc.getInstance();
        List<Optional<Student>> students = studentDao.findStudentsByCourseName(courseName);
        
        return students;
    }
    
    public boolean save(Course course) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL_SCRIPT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseDescription());
            
            ResultSet generatedKey = statement.getGeneratedKeys();
            
            if (generatedKey.next()) {
                course.setCourseId(generatedKey.getInt("course_id"));
            }
            
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean delete(int courseID) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL_SCRIPT)) {
            statement.setInt(1, courseID);
            
            int deletedrow = statement.executeUpdate();
            
            return deletedrow > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean update(String courseName, String courseDescription, int courseID) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_SCRIPT)) {
            statement.setString(1, courseName);
            statement.setString(2, courseDescription);
            statement.setInt(3, courseID);
            
            int updaterow = statement.executeUpdate();
            
            return updaterow > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public int findByName(String courseName) {
        int courseID = 0;
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_COURSEID_BY_NAME_SCRIPT)) {
            statement.setString(1, courseName);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                courseID = resultSet.getInt("course_id");
            }
            
            return courseID;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
     }
    
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SCRIPT)) {
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                courses.add(buildCourse(resultSet));
            }
            
            return courses;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public List<String> findAllName() {
        List<String> courseNames = new ArrayList<>();
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_NAME)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                courseNames.add(resultSet.getString(1));
            }
            
            return courseNames;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    private static Course buildCourse(ResultSet resultSet) throws SQLException {
        return new Course(resultSet.getInt("course_id"),
            resultSet.getString("course_name"),
            resultSet.getString("course_description"));
    }
    
    private CourseDaoJdbc() {}
}


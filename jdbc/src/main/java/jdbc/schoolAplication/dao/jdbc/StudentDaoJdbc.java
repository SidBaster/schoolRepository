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
import jdbc.schoolAplication.dao.StudentDao;
import jdbc.schoolAplication.entity.Student;
import jdbc.schoolAplication.exception.DaoException;

public class StudentDaoJdbc implements StudentDao{
    private static final StudentDaoJdbc INSTANCE = new StudentDaoJdbc();
    private static final String DELETE_SQL_SRIPT = "DELETE FROM students WHERE student_id = ?";
    private static final String SELECT_ALL_NAME = "SELECT first_name FROM students";
    private static final String FIND_STUDENTID_BY_NAME = "SELECT student_id FROM students WHERE first_name = ?";
    private static final String FIND_ALL_SCRIPT = "SELECT student_id, group_id, first_name, last_name FROM students";
    private static final String INSERT_SQL_SCRIPT = "INSERT INTO students (group_id, first_name, last_name) VALUES(?, ?, ?)";
    private static final String UPDATE_SQL_SCRIPT = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";
    private static final String FIND_STUDENTS_BY_COURSE_NAME_SCRIPT = "SELECT student_id, group_id, first_name, last_name FROM students "
        + "JOIN student_courses USING(student_id) "
        + "JOIN courses USING(course_id) "
        + "WHERE course_name = ?";
        
    public static StudentDaoJdbc getInstance() {
        return INSTANCE;
    }
    
    public boolean delete(int id) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL_SRIPT)) {
            statement.setInt(1, id);
            
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean update(int groupID, String firstName, String lastName, int studentID) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_SCRIPT)){
            statement.setInt(1, groupID);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setInt(4, studentID);
            
            int updaterow = statement.executeUpdate();
            
            return updaterow > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public List<Optional<Student>> findAll() {
        List<Optional<Student>> students = new ArrayList();
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SCRIPT);) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            
            return students;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public int findByName(String studentName) {
        int studentID = 0;
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENTID_BY_NAME)) {
            statement.setString(1, studentName);
                
            ResultSet resultSet = statement.executeQuery();
                
            while (resultSet.next()) {
                studentID = resultSet.getInt("student_id");
            }
            
            return studentID;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public List<String> findAllName() {
        List<String> studentNames = new ArrayList<>();
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_NAME)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                studentNames.add(resultSet.getString(1));
            }
            
            return studentNames;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    private static Optional<Student> buildStudent(ResultSet resultSet) throws SQLException {
        return Optional.of(new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4)));
    }
    
    public void saveAll(List<Student> students) {
        students.stream().forEach(s -> save(s));
    }
    
    public boolean save(Student student) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL_SCRIPT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, student.getGroupId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            
            ResultSet generatedKeys = statement.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                student.setStudentId(generatedKeys.getInt("student_id"));
            }
            
            return  statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public List<Optional<Student>> findStudentsByCourseName(String courseName) {
        List<Optional<Student>> students = new ArrayList<>();
        
        try(Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_BY_COURSE_NAME_SCRIPT)) {
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
    
    private StudentDaoJdbc() {}
}

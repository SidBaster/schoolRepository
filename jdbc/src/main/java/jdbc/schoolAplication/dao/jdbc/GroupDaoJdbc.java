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
import jdbc.schoolAplication.dao.GroupDao;
import jdbc.schoolAplication.entity.Group;
import jdbc.schoolAplication.exception.DaoException;

public class GroupDaoJdbc implements GroupDao{
    private static final GroupDaoJdbc INSTANCE = new GroupDaoJdbc();
    private static final String INSERT_SQL_SCRIPT = "INSERT INTO groups(group_name) VALUES(?)";
    private static final String FIND_ALL_SQL_SCRIPT = "SELECT group_id, group_name FROM groups";
    private static final String DELETE_SQL_SCRIPT = "DELETE FROM groups WHERE group_id = ?";
    private static final String UPDATE_SQL_SCRIPT = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    private static final String SELECTION_BY_STUDENTS_SQL_SCRIPT =
        "SELECT groups.group_id, groups.group_name, COUNT(students.student_id) " +
        "FROM groups " + 
        "LEFT JOIN students " + 
        "USING(group_id) " + 
        "GROUP BY groups.group_id " + 
        "HAVING COUNT(*) <= ?";
    
    public static GroupDaoJdbc getInstance() {
        return INSTANCE;
    }
    
    public void saveAll(List<Group> groups) {
        groups.stream().forEach(g -> save(g));
    }
    
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL_SCRIPT)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                groups.add(new Group(resultSet.getInt(1), resultSet.getString(2)));
            }
            
            return groups;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public boolean save(Group group) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL_SCRIPT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, group.getGroupName());
             
            ResultSet generatedKey = statement.getGeneratedKeys();
            
            if (generatedKey.next()) {
                group.setGroupId(generatedKey.getInt("group_id"));
            }
            
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    
    public boolean delete(int groupID) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL_SCRIPT)) {
            statement.setInt(1, groupID);
            
            int deletedrow = statement.executeUpdate();
            
            return deletedrow > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }  
    
    public boolean update(String groupName, int groupID) {  
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_SCRIPT)) {
            statement.setString(1, groupName);
            statement.setInt(2, groupID);
            
            int updaterow = statement.executeUpdate();
            
            return updaterow > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    public List<Optional<Group>> findByStudentsCountsLessEqual(int count) {
        List<Optional<Group>> groups = new ArrayList<>();

        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(SELECTION_BY_STUDENTS_SQL_SCRIPT);) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                groups.add(Optional.of(new Group(resultSet.getInt(1), resultSet.getString(2))));
            }
            return groups;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }
    
    private GroupDaoJdbc() {}
}

package jdbc.schoolAplication.dao;

import java.util.List;
import java.util.Optional;

import jdbc.schoolAplication.entity.Group;

public interface GroupDao {
    
    boolean save(Group group);
    
    List<Group> findAll();
    
    void saveAll(List<Group> groups);
    
    boolean update(String groupName, int groupID);
    
    boolean delete(int groupID);
    
    List<Optional<Group>> findByStudentsCountsLessEqual(int count);
}

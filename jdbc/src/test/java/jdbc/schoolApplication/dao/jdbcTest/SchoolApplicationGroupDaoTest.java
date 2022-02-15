package jdbc.schoolApplication.dao.jdbcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jdbc.schoolAplication.dao.GroupDao;
import jdbc.schoolAplication.dao.jdbc.GroupDaoJdbc;
import jdbc.schoolAplication.entity.Group;

public class SchoolApplicationGroupDaoTest {
    private GroupDao groupDao = GroupDaoJdbc.getInstance();
    
    @Test
    void saveShouldReturnTrueWhenInputObejectGroup() {
        Group group = new Group(1, "zx-34");
     
        boolean actual = groupDao.save(group);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
    
    @Test
    void updateShouldReturnTrueWhenInputStringAndInteger() {        
        boolean actual = groupDao.update("zx-12", 1);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
}

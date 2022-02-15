package jdbc.schoolApplication.dao.jdbcTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jdbc.schoolAplication.dao.CourseDao;
import jdbc.schoolAplication.dao.jdbc.CourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.GroupDaoJdbc;
import jdbc.schoolAplication.entity.Course;
import jdbc.schoolAplication.entity.Group;

class SchoolApplicationCourseDaoTest {
    private CourseDao courseDao = CourseDaoJdbc.getInstance();
    
    @Test
    void saveShouldReturnTrueWhenInputObjectCourse() {
        Course course = new Course(11, "test", "some text...");
     
        boolean actual = courseDao.save(course);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
    
    @Test
    void updateShouldReturnTrueWhenInputStringStringAndInteger() {
        boolean actual = courseDao.update("test", "test", 7);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
}

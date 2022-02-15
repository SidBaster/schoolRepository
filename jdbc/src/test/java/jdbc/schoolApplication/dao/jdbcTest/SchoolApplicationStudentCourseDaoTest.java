package jdbc.schoolApplication.dao.jdbcTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jdbc.schoolAplication.dao.StudentCourseDao;
import jdbc.schoolAplication.dao.StudentDao;
import jdbc.schoolAplication.dao.jdbc.StudentCourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.StudentDaoJdbc;
import jdbc.schoolAplication.entity.Student;
import jdbc.schoolAplication.entity.StudentCourse;

class SchoolApplicationStudentCourseDaoTest {
    private StudentCourseDao studentCourseDao = StudentCourseDaoJdbc.getInstance();
    
    @Test
    void saveShouldReturnTrueWhenInputObjectStudentCourse() {
        StudentCourse studentCourse = new StudentCourse(4,7);
    
        boolean actual = studentCourseDao.save(studentCourse);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
    
    @Test
    void updateShouldReturnTrueWhenInputIntegerAndInteger() {        
        boolean actual = studentCourseDao.update(3,1);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
}

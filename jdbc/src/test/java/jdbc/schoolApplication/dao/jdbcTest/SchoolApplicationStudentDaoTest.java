package jdbc.schoolApplication.dao.jdbcTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jdbc.schoolAplication.dao.StudentDao;
import jdbc.schoolAplication.dao.jdbc.StudentDaoJdbc;
import jdbc.schoolAplication.entity.Student;
 
class SchoolApplicationStudentDaoTest {
    private StudentDao studentDao = StudentDaoJdbc.getInstance();
    
    @Test
    void saveShouldReturnTrueWhenInputObjectStudent() {
        Student student = new Student(null, "Volodymyr", "Sikora");
    
        boolean actual = studentDao.save(student);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
    
    @Test
    void updateShouldReturnTrueWhenInputIntStringStringAndInt() {        
        boolean actual = studentDao.update(3, "Ivan", "Ivanov", 1);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
    
    @Test
    void deleteShouldReturnTrueWhenInputInteger() {        
        boolean actual = studentDao.delete(199);
        boolean excepted = true;
        
        assertEquals(excepted, actual);
    }
}

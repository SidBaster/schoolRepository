package jdbc.schoolApplication.utilTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import jdbc.schoolAplication.entity.Course;
import jdbc.schoolAplication.entity.Group;
import jdbc.schoolAplication.entity.Student;
import jdbc.schoolAplication.entity.StudentCourse;
import jdbc.schoolAplication.util.CourseGenerator;
import jdbc.schoolAplication.util.GroupGenerator;
import jdbc.schoolAplication.util.PropertiesUtil;
import jdbc.schoolAplication.util.StudentCourseGenerator;
import jdbc.schoolAplication.util.StudentGenerator;

class SchoolApplicationUtilTest {
    
    @Test
    void generateShouldReturnTwoHundredStudentsWhenWhenListWasGenerated() {
        StudentGenerator studentGenerator = new StudentGenerator();
        List<Student> students = studentGenerator.generate();
        
        int actual = students.size();
        int expected  = 200;
        
        assertEquals(expected, actual);
    }
    
    @Test
    void generateShouldReturnTenGroupsWhenWhenListWasGenerated() {
        GroupGenerator groupGenerator = new GroupGenerator();
        List<Group> groups = groupGenerator.generate();
        
        int actual = groups.size();
        int expected = 10;
        
        assertEquals(expected, actual);
    }
    
    @Test
    void generateShouldReturnTenCourseWhenListWasGenerated() throws IOException {
        CourseGenerator courseGenerator = new CourseGenerator();
        List<Course> courses = courseGenerator.generate();
        
        int actual = courses.size();
        int expected = 10;
        
        assertEquals(expected, actual);
    }
    
    @Test
    void generateShouldReturnNotMoreThanSixHundredCourseWhenListWasGenerated() {
        StudentCourseGenerator studentCourseGenerator = new StudentCourseGenerator();
        List<StudentCourse> studentCourses = studentCourseGenerator.generate();
        
        boolean actual = 900 > studentCourses.size();
        boolean expected = true;
        
        assertEquals(expected, actual);
    }
    
    @Test
    void openShouldReturnValueWhenInputKey() {
        String actualUrlValue = PropertiesUtil.get("db.url");
        String actualUserValue = PropertiesUtil.get("db.user");
        String actualPasswordValue = PropertiesUtil.get("db.password");
        
        String expectedUrlValue = "jdbc:postgresql://localhost:5432/school_repository";
        String expectedUserValue = "postgres";
        String expectedPasswordValue = "2420";
        
        assertEquals(expectedUrlValue, actualUrlValue);
        assertEquals(expectedUserValue, actualUserValue);
        assertEquals(expectedPasswordValue, actualPasswordValue);
    }
}

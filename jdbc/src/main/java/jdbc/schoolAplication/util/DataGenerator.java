package jdbc.schoolAplication.util;

import java.io.IOException;
import java.util.List;

import jdbc.schoolAplication.dao.jdbc.CourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.GroupDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.StudentCourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.StudentDaoJdbc;
import jdbc.schoolAplication.entity.Course;
import jdbc.schoolAplication.entity.Group;
import jdbc.schoolAplication.entity.Student;
import jdbc.schoolAplication.entity.StudentCourse;

public class DataGenerator {
    private GroupGenerator groupGenerator = new GroupGenerator();
    private StudentGenerator studentGenerator = new StudentGenerator();
    private CourseGenerator courseGenerator = new CourseGenerator();
    private StudentCourseGenerator studentCourseGenerator = new StudentCourseGenerator();
    
    private CourseDaoJdbc courseDao = CourseDaoJdbc.getInstance();
    private GroupDaoJdbc groupDao = GroupDaoJdbc.getInstance();
    private StudentDaoJdbc studentDao = StudentDaoJdbc.getInstance();
    private StudentCourseDaoJdbc studentCourseDao = StudentCourseDaoJdbc.getInstance();
     
    public void generateData() throws IOException {
        List<Group> groups = groupGenerator.generate();
        List<Student> students = studentGenerator.generate();
        List<Course> courses = courseGenerator.generate();
        List<StudentCourse> studentCourses = studentCourseGenerator.generate();
         
        groupDao.saveAll(groups);
        studentDao.saveAll(students);
        courseDao.saveAll(courses);
        studentCourseDao.saveAll(studentCourses);
     }
}

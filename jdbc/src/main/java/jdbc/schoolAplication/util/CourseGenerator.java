package jdbc.schoolAplication.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jdbc.schoolAplication.entity.Course;

public class CourseGenerator implements Generator{
    private static final String FileName = "src/main/resources/courses.txt";
    List<Course> courses = new ArrayList();

    public List<Course> generate() {
        List<String> lines;
        List<Course> courses = new ArrayList();

        try {
            lines = Files.lines(Paths.get(FileName)).collect(Collectors.toList());
            
            for (String line : lines) {
               String courseName = line.substring(0, line.indexOf('-'));
               String description = line.substring(line.indexOf('-') + 1, line.length());
               
               Course course = new Course();
               
               course.setCourseName(courseName);
               course.setCourseDescription(description);
               courses.add(course);
            }               
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
}

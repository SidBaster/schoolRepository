package jdbc.schoolAplication.UI;

import java.util.List;
import java.util.Scanner;

import jdbc.schoolAplication.dao.jdbc.CourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.GroupDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.StudentCourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.StudentDaoJdbc;
import jdbc.schoolAplication.entity.Student;

public class Menu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final StudentDaoJdbc STUDENTDAO = StudentDaoJdbc.getInstance();
    private static final GroupDaoJdbc GROUPDAO = GroupDaoJdbc.getInstance();
    private static final CourseDaoJdbc COURSEDAO = CourseDaoJdbc.getInstance();
    private static final Viewer QUERY = new Viewer();
    private static final StudentCourseDaoJdbc STUDENTCOURSEDAO = StudentCourseDaoJdbc.getInstance();
    private static final List<String> VARIABLES = List.of("a", "b", "c", "d", "e", "f");
    
    public void showMenu() {
        System.out.println("choose your variable (a, b, c, d, e, or f)");
        System.out.println();
        System.out.println("a. Find all groups with less or equals student count");
        System.out.println("b. Find all students related to course with given name");
        System.out.println("c. Add new student");
        System.out.println("d. Delete student by STUDENT_ID");
        System.out.println("e. Add a student to the course (from a list)");
        System.out.println("f. Remove the student from one of his or her courses");
        
        String symbol = SCANNER.next();
        
        if (!VARIABLES.contains(symbol)) {
            System.out.println("variables not found");
        } else if ("a".equalsIgnoreCase(symbol)) {
            System.out.println("Pleace write a student's count...");
            int studentCount = SCANNER.nextInt();
            
            QUERY.showGroups(studentCount);
        } else if ("b".equalsIgnoreCase(symbol)) {
            System.out.println("Pleace write course name...");
            String courseName = SCANNER.next();
            
            QUERY.showStudent(courseName);
        } else if ("c".equalsIgnoreCase(symbol)) {
            Student student = new Student();
            
            System.out.println("Pleace write group id (1-10)");
            int groupID = SCANNER.nextInt();
            
            System.out.println("Pleace write name...");
            String studentName = SCANNER.next();
            
            System.out.println("Pleace write last name...");
            String studentLastName = SCANNER.next();
            
            boolean result = STUDENTDAO.save(new Student(groupID, studentName, studentLastName));
            
            if (result) {
                System.out.println("Student was added");
            } else {
                System.out.println("Error, student did not created...");
            }
        } else if ("d".equalsIgnoreCase(symbol)) {
            System.out.println("Pleace write Student ID...");
            int studentID = SCANNER.nextInt();
            
            boolean result = STUDENTDAO.delete(studentID);
            
            if (result) {
                System.out.println("Student was deleted");
            } else {
                System.out.println("Error, student id not found");
            }
        } else if ("e".equalsIgnoreCase(symbol)) {
            System.out.println("Pleace write student name...");
            String studentName = SCANNER.next();
            
            System.out.println("Pleace write course name...");
            String courseName = SCANNER.next();
            
            boolean result = STUDENTCOURSEDAO.addStudentToCourse(studentName, courseName);
            
            if (result) {
                System.out.println("Student was added to course");
            } else {
                System.out.println("Error. Student or Courses not found");
            }
        } else if ("f".equalsIgnoreCase(symbol)) {
            System.out.println("Pleace write student ID...");
            int studentID = SCANNER.nextInt();
            
            System.out.println("Pleace write course ID...");
            int courseID = SCANNER.nextInt();
            
            boolean result = STUDENTCOURSEDAO.delete(studentID, courseID);
            
            if (result) {
                System.out.println("Student was deleted from course");
            } else {
                System.out.println("Error, student not found");
            }
        }
    }
}

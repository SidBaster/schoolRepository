package jdbc.schoolAplication.UI;

import java.util.List;
import java.util.Optional;

import jdbc.schoolAplication.dao.jdbc.CourseDaoJdbc;
import jdbc.schoolAplication.dao.jdbc.GroupDaoJdbc;
import jdbc.schoolAplication.entity.Group;
import jdbc.schoolAplication.entity.Student;

public class Viewer {
    public void showGroups(int count) {
        GroupDaoJdbc groupDao = GroupDaoJdbc.getInstance();
        List<Optional<Group>> groups = groupDao.findByStudentsCountsLessEqual(count);
        
        for (Optional<Group> group : groups) {
            System.out.println("group ID : " + group.get().getGroupId() + " group Name :" + group.get().getGroupName());
        }
    }
    
    public void showStudent(String courseName) {
        CourseDaoJdbc courseDaoJdbc = CourseDaoJdbc.getInstance();
        List<Optional<Student>> studentList = courseDaoJdbc.buildListStudents(courseName);
        
        for (Optional<Student> student : studentList) {
            System.out.println(student.get().getFirstName() + " " + student.get().getLastName());
            System.out.println("--------------------");
        }
    }
}

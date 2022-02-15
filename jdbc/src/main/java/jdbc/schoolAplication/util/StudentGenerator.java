package jdbc.schoolAplication.util;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;
import jdbc.schoolAplication.entity.Student;
 
public class StudentGenerator implements Generator{
       
    public List<Student> generate() {
        List<Student> students = new ArrayList();
        Faker faker = new Faker();
        
        for (int i = 0; i < 200; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            
            int group_id = (int)Math.floor(Math.random()*(11-1+1)+1);
            
            if (group_id == 11) {
                Student student = new Student(null, firstName, lastName);
                students.add(student);
            } else {
                Student student = new Student(group_id, firstName, lastName);
                students.add(student);
            }
        }
        return students;
    }
}

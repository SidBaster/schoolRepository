package jdbc.schoolAplication.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jdbc.schoolAplication.entity.StudentCourse;

public class StudentCourseGenerator implements Generator{
    private Random random = new Random();

    public List<StudentCourse> generate() {
        List<StudentCourse> result = new ArrayList<>();
         
        for (int studentID = 1; studentID <= 200; studentID++) {
            int courseQuantity = random.nextInt(3);
            List<Integer> range = IntStream.range(1, 11)
                .boxed()
                .collect(Collectors
                .toCollection(ArrayList::new));
            Collections.shuffle(range);
            
            for (int k = 0; k <= courseQuantity; k++) {
                int courseID = range.subList(0, 3).get(k);
                result.add(new StudentCourse(studentID, courseID));
            }
        }
        return result;
    }
}

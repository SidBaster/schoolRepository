package jdbc.schoolAplication.entity;

import java.util.Objects;

public class StudentCourse {
    private int StudendID;
    private int CourseID;
    
    public StudentCourse(int studendID, int courseID) {
        StudendID = studendID;
        CourseID = courseID;
    }

    public int getStudendID() {
        return StudendID;
    }

    public void setStudendID(int studendID) {
        StudendID = studendID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CourseID, StudendID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCourse other = (StudentCourse) obj;
        return CourseID == other.CourseID && StudendID == other.StudendID;
    }
}

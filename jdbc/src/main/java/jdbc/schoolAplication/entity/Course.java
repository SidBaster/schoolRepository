package jdbc.schoolAplication.entity;

import java.util.Objects;

public class Course {
    private int courseId;
    private String courseName;
    private String courseDescription;
    
    public Course(int courseId, String courseName, String courseDescription) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }
    
    public Course() {}
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseDescription() {
        return courseDescription;
    }
    
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
     
    @Override
    public String toString() {
        return "CoursesEntity [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
                + courseDescription + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseDescription, courseId, courseName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        return Objects.equals(courseDescription, other.courseDescription) && courseId == other.courseId
                && Objects.equals(courseName, other.courseName);
    }
}

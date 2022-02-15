package jdbc.schoolAplication.entity;

import java.util.Objects;

public class Student {
    private int studentId;
    private Integer groupId;
    private String firstName;
    private String lastName;
    
    public int getStudentId() {
        return studentId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Student(Integer groupId, String firstName, String lastName) {
        super();
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(int studentId, Integer groupId, String firstName, String lastName) {
        super();
        this.studentId = studentId;
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Student() {}
    
    @Override
    public String toString() {
        return "StudentsEntity [studentId=" + studentId + ", groupId=" + groupId + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, groupId, lastName, studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(firstName, other.firstName) && Objects.equals(groupId, other.groupId)
                && Objects.equals(lastName, other.lastName) && studentId == other.studentId;
    }
}

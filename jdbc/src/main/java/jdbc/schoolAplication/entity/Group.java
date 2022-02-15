package jdbc.schoolAplication.entity;

import java.util.Objects;

public class Group {
    private Integer groupId;
    private String groupName;
    
    public Group(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
    
    public Group() {}

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GrouopsEntity [groupId=" + groupId + ", groupName=" + groupName + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return Objects.equals(groupId, other.groupId) && Objects.equals(groupName, other.groupName);
    }
}

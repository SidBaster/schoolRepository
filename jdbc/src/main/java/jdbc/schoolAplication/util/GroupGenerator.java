package jdbc.schoolAplication.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jdbc.schoolAplication.entity.Group;

public class GroupGenerator implements Generator{
    private static final String GROUP_NAME_SEPARATOR = "-";

     public List<Group> generate() {
        List<Group> groups = new ArrayList();
        
        for (int i = 0; i < 10; i++) {
            Group group = new Group();
            group.setGroupName(generateRandomGroupName());
            groups.add(group);
        }
        return groups;
    }
    
    private static String generateRandomGroupName() {
        return RandomStringUtils.randomAlphabetic(2) + GROUP_NAME_SEPARATOR + RandomStringUtils.randomNumeric(2);
    }
}

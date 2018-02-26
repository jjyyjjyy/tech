package me.jy.list;

import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * @author jy
 * @date 2018/02/26
 */
public class RoleTest {

    private static final List<GlobalRole> ROLES = new ArrayList<>();

    static {
        GlobalRole role = new GlobalRole();
        role.setName("ADMIN");
        role.setChildren(Arrays.asList("MANAGER", "BOSS"));
        GlobalRole role1 = new GlobalRole();
        role1.setName("MANAGER");
        role1.setChildren(Arrays.asList("ACCOUNT"));
        GlobalRole role2 = new GlobalRole();
        role2.setName("BOSS");
        role2.setChildren(Arrays.asList("USER"));
        ROLES.add(role);
        ROLES.add(role1);
        ROLES.add(role2);
    }

    @Test
    public void testGetAllRoles() {
        Map<String, List<String>> result = new HashMap<>();
        ROLES.forEach(n -> result.put(n.getName(), n.getChildren()));

        Set<String> upRoles = result.keySet();
        upRoles
                .forEach(upRole -> {
                    List<String> subRoles = new ArrayList<>();
                    addReachableRole(result, upRole, subRoles);
                    result.put(upRole, subRoles);
                });

        System.out.println(result);
    }

    private void addReachableRole(Map<String, List<String>> result, String upRole, List<String> subRoles) {
        List<String> strings = result.get(upRole);
        if (strings != null) {
            subRoles.addAll(strings);
            strings.forEach(s -> addReachableRole(result, s, subRoles));
        }
    }


    @Data
    private static class GlobalRole {

        private String name;

        private List<String> children;
    }
}

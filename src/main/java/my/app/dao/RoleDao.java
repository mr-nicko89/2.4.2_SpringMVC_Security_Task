package my.app.dao;

import my.app.models.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRoleSet(Set<String> role);

    Role getDefaultRole();

    Role getRoleByName(String name);
}
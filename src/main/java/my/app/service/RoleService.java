package my.app.service;

import my.app.models.Role;
import my.app.models.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getAllRoles();

    Set<Role> getRoleSet(Set<String> roles);

    Role getDefaultRole();

    Role getAdminRole();

    void setRolesDefault();
}

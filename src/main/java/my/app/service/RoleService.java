package my.app.service;

import my.app.models.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getRoleSet(Set<String> roles);

    Role getDefaultRole();

    Role getAdminRole();
}

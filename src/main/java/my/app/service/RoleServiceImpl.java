package my.app.service;

import my.app.dao.RoleDao;
import my.app.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoleSet(Set<String> roles) {
        return roleDao.getRoleSet(roles);
    }

    @Override
    public Role getDefaultRole() {
        return roleDao.getDefaultRole();
    }

}

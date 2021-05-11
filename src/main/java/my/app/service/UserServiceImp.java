package my.app.service;

import my.app.dao.UserDAO;
import my.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional()
public class UserServiceImp implements UserService {


    @Qualifier("userDAOImp")
    @Autowired
    private UserDAO userDao;

    @Override
    public List<User> getAll() {
        return userDao.listAllPeople();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

}

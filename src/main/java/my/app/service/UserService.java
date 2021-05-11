package my.app.service;

import my.app.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getUserById(Long id);

    void save(User user);

    void update(Long id, User updatedUser);

    public void delete(Long id);
}

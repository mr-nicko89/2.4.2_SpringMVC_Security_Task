package my.app.service;

import my.app.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getUserById(int id);

    void save(User user);

    void update(int id, User updatedUser);

    public void delete(int id);
}

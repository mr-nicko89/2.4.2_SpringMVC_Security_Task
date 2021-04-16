package my.app.dao;

import my.app.models.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> listAllPeople();

    Person getUserById(int id);

    void save(Person person);

    void update(int id, Person updatedPerson);

    void delete(int id);
}


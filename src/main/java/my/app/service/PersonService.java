package my.app.service;

import my.app.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> index();

    Person getUserById(int id);

    void save(Person person);

    void update(int id, Person updatedPerson);

    public void delete(int id);
}

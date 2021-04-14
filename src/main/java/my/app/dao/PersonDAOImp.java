package my.app.dao;

import my.app.models.Person;

import java.util.List;

public interface PersonDAOImp {
    List<Person> index();

    Person show(int id);

    void save(Person person);

    void update(int id, Person updatedPerson);

    public void delete(int id);
}


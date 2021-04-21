package my.app.dao;

import my.app.models.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository


public class PersonDAOImp implements PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> listAllPeople() {
        return entityManager.createQuery("from Person", Person.class
        ).getResultList();
    }

    @Override
    public Person getUserById(int id) {
        TypedQuery<Person> q = entityManager.createQuery(
                "select person from Person person where person.id = :id", Person.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);

    }

    @Override
    public void update(int id, Person updatedPerson) {
        entityManager.merge(updatedPerson);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getUserById(id));
    }
}


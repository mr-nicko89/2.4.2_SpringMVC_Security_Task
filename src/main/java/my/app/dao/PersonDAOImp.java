package my.app.dao;

import my.app.models.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)

public class PersonDAOImp implements PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<Person> index() {

        List<Person> lPerson = entityManager.createQuery("from Person", Person.class
        ).getResultList();
        return lPerson;
    }

    @Override
    public Person show(int id) {
        TypedQuery<Person> q = entityManager.createQuery(
                "select person from Person person where person.id = :id", Person.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save(Person person) {
        entityManager.persist(person);

    }

    @Override
    public void update(int id, Person updatedPerson) {

    }

    @Override
    public void delete(int id) {

    }
}


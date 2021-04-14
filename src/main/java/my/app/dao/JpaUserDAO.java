//package my.app.dao;
//
//import my.app.models.Person;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Component
//@Transactional()
//public class JpaUserDAO implements PersonDAOImp{
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Person> index() {
//        return entityManager.createQuery("select person from Person person", Person.class
//        ).getResultList();
//    }
//
//    @Override
//    public Person show(int id) {
//        TypedQuery<Person> q = entityManager.createQuery(
//                "select person from Person person where person.id = :id", Person.class
//        );
//        q.setParameter("id",id);
//        return q.getResultList().stream().findAny().orElse(null);
//    }
//
//    @Override
//    @Transactional
//    public void save(Person person) {
//        entityManager.persist(person);
//
//    }
//
//    @Override
//    public void update(int id, Person updatedPerson) {
//
//    }
//
//    @Override
//    public void delete(int id) {
//
//    }
//}
//

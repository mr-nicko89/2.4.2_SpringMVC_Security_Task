package my.app.service;

import my.app.dao.PersonDAO;
import my.app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonServiceImp implements PersonService {


    @Qualifier("personDAOImp")
    @Autowired
    private PersonDAO personDao;

    @Override
    public List<Person> index() {
        return personDao.listAllPeople();
    }

    @Override
    public Person show(int id) {
        return personDao.getUserById(id);
    }

    @Override
    @Transactional
    public void save(Person person) {
        personDao.save(person);
    }

    @Override
    public void update(int id, Person updatedPerson) {
        personDao.update(id, updatedPerson);
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }
}

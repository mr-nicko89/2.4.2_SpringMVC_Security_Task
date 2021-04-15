package my.app.service;

import my.app.dao.JpaPersonDAOImp;
import my.app.dao.PersonDAO;
import my.app.dao.PersonDAOImp;
import my.app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

    @Autowired
    private JpaPersonDAOImp personDao;

    @Override
    public List<Person> index() {
        return personDao.index();
    }

    @Override
    public Person show(int id) {
        return personDao.show(id);
    }

    @Override
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

package serviceBD.app.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.RatinRepository;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Rating;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RatinRepository ratingRepository;

    public List<Person> getAllEmployees() {
        return personRepository.findAllEmp();
    }

    public List<Person> getEmployeeByCategory(String type, String category) {
        // if (category != null) {
        // return categoryRepository.findByCategory(category);
        // }
        return personRepository.findByCategoryAndType(type, category);
    }

    public Person getUserById(int id) {
        Optional<Person> personne = personRepository.findById(id);
        Person a = null;
        if (personne.isPresent()) {
            a = personne.get();

        } else {
            throw new RuntimeException("Article doesn't exist for id: " + id);
        }
        return a;
    }

    public boolean savePerson(Person person) {
        if (personRepository.save(person).equals(null)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean loginExists(String login) {
        List<String> logins = personRepository.findAllLogins();
        return logins.contains(login);
    }

    public int getAllRatingById(@PathVariable(value = "id") int id) {

        return ratingRepository.sumRatingById(id);
    }

    public int getSumColumnsRats(int id, int id_client) {
        if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return 0;
        }

        else if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return ratingRepository.sumColumnsRating(id);
        }
        return ratingRepository.sumColumnsRating(id);

    }

    public int getRatingByClient(int id_client, int id) {

        if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return 0;
        } else if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return ratingRepository.getRatingByClient(id_client, id);
        }
        return ratingRepository.getRatingByClient(id_client, id);
    }
}
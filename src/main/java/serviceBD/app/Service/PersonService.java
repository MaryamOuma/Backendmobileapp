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
    public List<Person> getEmployeeByCategory(String category) {
        return personRepository.findByCategoryAndType(category);
    }

    public Person getUserById(int id) {
        Optional<Person> personne = personRepository.findById(id);
        Person a = null;
        if (personne.isPresent()) {
            a = personne.get();

        } else {
            throw new RuntimeException("Person doesn't exist for id: " + id);
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

    // RATING AN EMPLOYEE
    public Rating createRating(Rating rating, int id, int id_client) {
        personRepository.findById(id_client).map(pers -> {
            rating.setClient(pers);
            return ratingRepository.save(rating);
        }).orElseThrow();

        personRepository.findById(id).map(personne -> {
            rating.setPerson(personne);
            return ratingRepository.save(rating);
        }).orElseThrow();
        return ratingRepository.save(rating);
    }

    public int getAllRatingById(int id, int id_client) {

        if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return 0;
        }
        return ratingRepository.sumRatingById(id);
    }

    public int getSumColumnsRats(int id, int id_client) {
        if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return 0;
        }
        return ratingRepository.sumColumnsRating(id);
    }

    public int getRatingByClient(@PathVariable(value = "id") int id, @PathVariable(value = "id_client") int id_client) {

        if (!ratingRepository.existsById(id) && !ratingRepository.existsById(id_client)) {
            return 0;
        }

        return ratingRepository.getRatingByClient(id, id_client);
    }
}
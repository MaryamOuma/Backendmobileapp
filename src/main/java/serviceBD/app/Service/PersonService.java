package serviceBD.app.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.RatinRepository;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Rating;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RatinRepository ratingRepository;

    public List<Person> getAllEmployees() {
        return personRepository.findAll();
    }
    public List<Person> getEmployeeByCategory(String category) {
        return personRepository.findByCategoryAndType(category);
    }

    public Person getUserById(int id) {
        Person person= personRepository.getReferenceById(id);
        return person;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public boolean loginExists(String login) {
        List<String> logins = personRepository.findAllLogins();
        return logins.contains(login);
    }
    public void deletePerson(int id) throws ChangeSetPersister.NotFoundException {
        if(!personRepository.existsById(id)){
            throw new ChangeSetPersister.NotFoundException();
        }
        else {
            personRepository.deleteById(id);
        }
    }

    // RATING AN EMPLOYEE
    public Rating createRating(Rating rating, int id, int id_client) {
        if (ratingRepository.getRatingByClient(id, id_client)!=null) {
            ratingRepository.updateRating(rating.getLabel(), id_client, id);
            return rating;
        }else{
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
    }

    public long getAllRatingById(int id, int id_client) {
        Long sum= ratingRepository.sumRatingById(id);
        if (sum.equals(null)){
            return 0;
        }else {
            return sum;
        }
    }

    public long getSumColumnsRats(int id, int id_client) {
        Long sum= ratingRepository.sumColumnsRating(id);
        if (sum.equals(null)){
            return 0;
        }else {
            return sum;
        }
    }

    public long getRatingByClient(@PathVariable(value = "id") int id, @PathVariable(value = "id_client") int id_client) {
        Long sum= ratingRepository.getRatingByClient(id, id_client);
        if (sum.equals(null)){
            return 0;
        }else {
            return sum;
        }
    }

    public PersonService(PersonRepository personRepository, RatinRepository ratingRepository) {
        this.personRepository = personRepository;
        this.ratingRepository=ratingRepository;
    }
}
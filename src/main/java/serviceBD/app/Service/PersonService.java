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
        if (ratingRepository.sumRatingById(id)==null){
            return 0;
        }else{
            return ratingRepository.sumRatingById(id);
        }
    }

    public long getSumColumnsRats(int id, int id_client) {
        if(ratingRepository.sumColumnsRating(id)==0){
            return 0;
        }else{
            return ratingRepository.sumColumnsRating(id);
        }
    }

    public long getRatingByClient(@PathVariable(value = "id") int id, @PathVariable(value = "id_client") int id_client) {
        if (ratingRepository.getRatingByClient(id, id_client)==null) {
            return 0;
        }
        return ratingRepository.getRatingByClient(id, id_client);
    }

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
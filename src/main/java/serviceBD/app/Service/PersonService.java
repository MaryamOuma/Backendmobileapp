package serviceBD.app.Service;

import java.util.List;
import java.util.Optional;

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
        return personRepository.findAll();
    }



    public List<Person> getEmployeeByCategory(String category) {
        return personRepository.findByCategoryAndType(category);

    }
    public Person getUserById(Long id) {
        Optional<Person> personne = personRepository.findById(id);
        Person a = null;
        if (personne.isPresent()) {
            a = personne.get();

        } else {
            throw new RuntimeException("Person doesn't exist for id: " + id);
        }
        return a;
    }

    
    public boolean savePerson(Person person){
        if(personRepository.save(person).equals(null)){
            return false;
         }
         else{
            return true;
         }
     }

    public boolean loginExists(String login) {
    		List<String> logins = personRepository.findAllLogins();
    		return logins.contains(login);
    }

    public Rating createRating(Rating rating, Long id) {
        return personRepository.findById(id).map(personne -> {
            rating.setPerson(personne);
            return ratingRepository.save(rating);
        }).orElseThrow();
    }

    public float getAllRatingById(@PathVariable(value = "id") int id) {
        if (!ratingRepository.existsById(id)) {
            return 0;
        }
        else if (ratingRepository.existsById(id)) {
            return ratingRepository.sumRatingById(id);
        }
        return 0;
    }

    public int getSumColumnsRats(@PathVariable(value = "id") int id) {
        if (!ratingRepository.existsById(id)) {
            return 0;
        }

        else if (ratingRepository.existsById(id)) {
            return ratingRepository.sumColumnsRating(id);
        }
        return ratingRepository.sumColumnsRating(id);

    }
}
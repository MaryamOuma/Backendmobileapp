package serviceBD.app.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Model.Person;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllEmployees() {
        return personRepository.findAllEmp();
    }

    public List<Person> getEmployeeByCategory(String type, String category) {
        // if (category != null) {
        // return categoryRepository.findByCategory(category);
        // }
        return personRepository.findByCategoryAndType(type, category);
    }

    public Person getUserById(Long id) {
        Optional<Person> personne = personRepository.findById(id);
        Person a = null;
        if (personne.isPresent()) {
            a = personne.get();

        } else {
            throw new RuntimeException("Article doesn't exist for id: " + id);
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
    
    
}
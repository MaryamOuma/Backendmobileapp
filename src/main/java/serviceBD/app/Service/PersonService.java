package serviceBD.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.PersonRepository;

@Service
@Data
public class PersonService {
	
	@Autowired
    private PersonRepository personRepository;
	
	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
        return savedPerson;
    }
}

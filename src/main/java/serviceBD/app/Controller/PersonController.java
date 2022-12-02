package serviceBD.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import serviceBD.app.Model.Person;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Service.PersonService;

@RestController
public class PersonController {
	
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
	private PersonService personService;
	
	/**
	 * Create - Add a new user / person 
	**/
    
	@PostMapping("/persons/register")
	public Person createPerson(@RequestBody Person newUser) {
		
		List<Person> users = personRepository.findAll();
        System.out.println("New user: " + newUser.toString());
        for (Person user : users) {
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                
            }
            else System.out.println("user saved !");
         
        }
        
		return personService.savePerson(newUser);
		
		
	}
}
    
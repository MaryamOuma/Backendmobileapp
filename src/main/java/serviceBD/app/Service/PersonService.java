package serviceBD.app.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.PersonRepository;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;



    public Person savePerson(Person person){
        return personRepository.save(person);

    }

}

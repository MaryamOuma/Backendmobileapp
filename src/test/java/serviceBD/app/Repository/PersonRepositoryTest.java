package serviceBD.app.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    Service service= new Service("Climatisation");
    Service service1= new Service("Peinture");

    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);



    @Test
    void testIfFindAllEmp() {
        personRepository.save(person);
        boolean exist= false;
        for (Person p:
             personRepository.findAllEmp()) {
            if(p.equals(person)){
                exist= true;
            }
        }
        assertThat(exist).isTrue();
    }
    @Test
    void testIfNotFindAllEmp() {
        boolean exist= false;
        for (Person p:
                personRepository.findAllEmp()) {
            if(p.equals(person)){
                exist= true;
            }
        }
        assertThat(exist).isFalse();
    }

    @Test
    void findByCategoryAndType() {
        List<Person> personList= new ArrayList<>();
        boolean exist= false;
        personRepository.save(person);
        personList=personRepository.findByCategoryAndType("Climatisation");
        for (Person p:
                personList) {
            if(p.equals(person)){
                exist= true;
            }
        }
        assertThat(exist).isTrue();
    }
    @Test
    void IfNotfindByCategoryAndType() {
        List<Person> personList= new ArrayList<>();
        boolean exist= false;
        person.setService(service1);
        personRepository.save(person);
        personList=personRepository.findByCategoryAndType("Climatisation");
        for (Person p:
                personList) {
            if(p.equals(person)){
                exist= true;
            }
        }
        assertThat(exist).isFalse();
    }

    @Test
    void findAllLogins() {
        List<String> logins= new ArrayList<>();
        personRepository.save(person);
        boolean exist= false;
        logins=personRepository.findAllLogins();
        for (String login:
                logins) {
            if(login.equals(person.getTel())){
                exist= true;
            }
        }
        assertThat(exist).isTrue();
    }

    @Test
    void IfNotfindAllLogins() {
        List<String> logins= new ArrayList<>();
        personRepository.save(person);
        boolean exist= false;
        logins=personRepository.findAllLogins();
        for (String login:
                logins) {
            if(login.equals(person.getTel())){
                exist= true;
            }
        }
        assertThat(exist).isTrue();
    }

    @BeforeEach
    void setUp(){
        serviceRepository.save(service);
        serviceRepository.save(service1);
    }

}
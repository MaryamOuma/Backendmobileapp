package serviceBD.app.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.ServiceRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private PersonService underTest;



    Service service= new Service("Climatisation");
    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);

    @Autowired
    private ServiceRepository serviceRepository;


    @BeforeEach
    void setUp() {
        underTest= new PersonService(personRepository);
        serviceRepository.save(service);
    }

    @Test
    void getAllEmployees() {
        underTest.getAllEmployees();
        verify(personRepository).findAll();
    }

    @Test
    void getEmployeeByCategory() {
    }

    @Test
    void getUserById() {
        person.setId(1);
       // personRepository.save(person);
        underTest.getUserById(1L);
        verify(personRepository).getReferenceById(1L);
    }

    @Test
    void savePerson() {
        underTest.savePerson(person);
        ArgumentCaptor<Person> personArgumentCaptor =
                ArgumentCaptor.forClass(Person.class);
        verify(personRepository)
                .save(personArgumentCaptor.capture());
        Person capturedPerson = personArgumentCaptor.getValue();
        assertThat(capturedPerson).isEqualTo(person);
    }

    @Test
    void loginExists() {
        underTest.loginExists("salma@gmail.com");
        verify(personRepository).findAllLogins();
       // Assertions.assertFalse(underTest.loginExists("salma@gmail.com"));
    }

    @Test
    void createRating() {
    }

    @Test
    void getAllRatingById() {
    }

    @Test
    void getSumColumnsRats() {
    }
}
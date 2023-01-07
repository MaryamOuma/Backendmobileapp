package serviceBD.app.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Rating;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.RatinRepository;
import serviceBD.app.Repository.ServiceRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RatinRepository ratinRepository;
    private PersonService underTest;



    Service service= new Service("Climatisation");
    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);
    Rating rating= new Rating(person, person, 3);

    @Autowired
    private ServiceRepository serviceRepository;



    @BeforeEach
    void setUp() {
        underTest= new PersonService(personRepository, ratinRepository);
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
        underTest.getUserById(1);
        verify(personRepository).getReferenceById(1);
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
    void deletePerson() throws ChangeSetPersister.NotFoundException {
        given(personRepository.existsById(person.getId()))
                .willReturn(true);
        underTest.deletePerson(person.getId());
        verify(personRepository).deleteById(person.getId());
    }

   /* @Test
    void createRating() {
       // underTest.savePerson(person);
        underTest.createRating(rating, 1, 2);
        ArgumentCaptor<Rating> ratingArgumentCaptor=
                ArgumentCaptor.forClass(Rating.class);
        verify(ratinRepository)
                .save(ratingArgumentCaptor.capture());
        Rating capturedRating = ratingArgumentCaptor.getValue();
        assertThat(capturedRating).isEqualTo(person);
    }*/

    @Test
    void getAllRatingById() {
        underTest.getAllRatingById(person.getId(), person.getId());
        verify(ratinRepository).sumRatingById(person.getId());
    }
    @Test
    void getRatingByClient() {
        underTest.getRatingByClient(person.getId(), person.getId());
        verify(ratinRepository).getRatingByClient(person.getId(), person.getId());
    }

    @Test
    void getSumColumnsRats() {
        underTest.getSumColumnsRats(person.getId(), person.getId());
        verify(ratinRepository).sumColumnsRating(person.getId());
    }
}
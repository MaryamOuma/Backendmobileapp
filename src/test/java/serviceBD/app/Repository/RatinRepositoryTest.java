package serviceBD.app.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Rating;
import serviceBD.app.Model.Service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RatinRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    Service service= new Service("Climatisation");

    @Autowired
    RatinRepository ratinRepository;

    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);
    Rating rating= new Rating("6", person);




    @BeforeEach
    void setUp() {
        serviceRepository.save(service);
        personRepository.save(person);
    }

   /* @Test
    void sumRatingById() {
        ratinRepository.save(rating);
        boolean exist= false;
        float sum= ratinRepository.sumRatingById(person.getId());
        if(sum==6){
            exist=true;
        }
        assertThat(exist).isTrue();
    }
*/
    @Test
    void sumColumnsRating() {
        ratinRepository.save(rating);
        boolean exist= false;
        int sum= ratinRepository.sumColumnsRating(person.getId());
        if(sum==1){
            exist=true;
        }
        assertThat(exist).isTrue();
    }
    @Test
    void sumColumnsRatingNull() {
        boolean exist= false;
        int sum= ratinRepository.sumColumnsRating(person.getId());
        if(sum==0){
            exist=true;
        }
        assertThat(exist).isTrue();
    }
}
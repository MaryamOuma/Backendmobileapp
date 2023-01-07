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
    Person person1= new Person("AE5889", "warda", "saadi", "Salé","warda@gmail.com", "Client", service);
    Rating rating= new Rating(person, person1, 3);


    @BeforeEach
    void setUp() {
        serviceRepository.save(service);
        personRepository.save(person);
        personRepository.save(person1);
    }

    @Test
    void sumRatingById() {
        ratinRepository.save(rating);
        boolean exist= false;
        long sum= ratinRepository.sumRatingById(person.getId());
        if(sum==3){
            exist=true;
        }
        assertThat(exist).isTrue();
    }
    @Test
    void sumColumnsRating() {
        ratinRepository.save(rating);
        boolean exist= false;
        long sum= ratinRepository.sumColumnsRating(person.getId());
        if(sum==1){
            exist=true;
        }
        assertThat(exist).isTrue();
    }

    @Test
    void sumColumnsRatingNull() {
        boolean exist= false;
        long sum= ratinRepository.sumColumnsRating(person.getId());
        if(sum==0){
            exist=true;
        }
        assertThat(exist).isTrue();
    }

    @Test
    void getRatingByClient() {
        rating.setLabel(2);
        ratinRepository.save(rating);
        boolean exist= false;
        long sum= ratinRepository.getRatingByClient(person.getId(),person1.getId());
        if(sum==2){
            exist=true;
        }
        assertThat(exist).isTrue();
    }

    @Test
    void updateRating(){
        boolean exist=false;
        ratinRepository.save(rating);
        ratinRepository.updateRating(5, person1.getId(), person.getId());
        long sum= ratinRepository.getRatingByClient(person.getId(),person1.getId());
        if(sum==5){
            exist=true;
        }
        assertThat(exist).isTrue();
    }
}
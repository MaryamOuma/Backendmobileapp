package serviceBD.app.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.ListFavoris;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ListFavRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ListFavRepository listFavRepository;

    Service service= new Service("Climatisation");
    Service service1= new Service("Client");

    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);
    Person person1= new Person("ZE5889", "maram", "saadi", "Salé","maram@gmail.com", "Client", service);
    Account account= new Account("salma@gmail.com", "", person );


    @BeforeEach
    void setUp() {
        serviceRepository.save(service);
        personRepository.save(person);
        personRepository.save(person1);
    }


    @Test
    void addFav() {
        Boolean exists= false;
        listFavRepository.addFav(person1.getId(), person.getId());
        for (ListFavoris listFavoris :
                listFavRepository.findAll()  ) {
            if(listFavoris.getEmp().equals(person) && listFavoris.getPerson().equals(person1)){
                exists= true;
            }
        }
        assertThat(exists).isTrue();
    }
    @Test
    void NotAddedInFav() {
        Boolean exists= false;
        for (ListFavoris listFavoris :
                listFavRepository.findAll()  ) {
            if(listFavoris.getEmp().equals(person) && listFavoris.getPerson().equals(person1)){
                exists= true;
            }
        }
        assertThat(exists).isFalse();
    }


    @Test
    void getFav() {
        Boolean exists= false;
        listFavRepository.addFav(person1.getId(), person.getId());
        List<ListFavoris > listFavoris= listFavRepository.getFav(person1.getId());
        for (ListFavoris favoris :
                listFavoris  ) {
            if(favoris.getEmp().equals(person)){
                exists= true;
            }
        }
        assertThat(exists).isTrue();
    }
    @Test
    void NotgetFav() {
        Boolean exists= false;
        List<ListFavoris > listFavoris= listFavRepository.getFav(person1.getId());
        for (ListFavoris favoris :
                listFavoris  ) {
            if(favoris.getEmp().equals(person)){
                exists= true;
            }
        }
        assertThat(exists).isFalse();
    }

    @Test
    void deleteFav() {
        Boolean exists= false;
        listFavRepository.addFav(person1.getId(), person.getId());
        listFavRepository.deleteFav(person1.getId(), person.getId());
        List<ListFavoris> listFavoris= listFavRepository.getFav(person1.getId());
        for (ListFavoris favoris :
                listFavoris  ) {
            if(favoris.getEmp().equals(person)){
                exists= true;
            }
        }
        assertThat(exists).isFalse();
    }
}
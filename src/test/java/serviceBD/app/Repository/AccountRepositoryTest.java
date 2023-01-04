package serviceBD.app.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

class AccountRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private  AccountRepository accountRepository;
    Service service= new Service("Climatisation");

    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);
    Account account= new Account("salma@gmail.com", "", person );


    @BeforeEach
    void setUp() {
        serviceRepository.save(service);
    }

    @Test
    void findByUsername() {
        personRepository.save(person);
        accountRepository.save(account);
        Boolean exists= false;
        Optional<Account> accountOptional=accountRepository.findByUsername(account.getUsername());
        if(accountOptional.get().equals(account)){
            exists=true;
        }
        assertThat(exists).isTrue();
    }
    @Test
    void IfNotFindByUsername() {
        Boolean exists= false;
        Optional<Account> accountOptional=accountRepository.findByUsername(account.getUsername());
        if(accountOptional.isPresent() && accountOptional.get().equals(account)){
            exists=true;
        }
        assertThat(exists).isFalse();
    }

    @Test
    void findPerson_id() {
        personRepository.save(person);
        accountRepository.save(account);
        Boolean exists= false;
        long person_id= accountRepository.findPerson_id(account.getId());
        if(person_id==1){
            exists=true;
        }
        assertThat(exists).isTrue();
    }

    @Test
    void IfNotfindPerson_id() {
        Boolean exists= false;
        if(accountRepository.findPerson_id(account.getId())!=null){
            exists=true;
        };
        assertThat(exists).isFalse();
    }
}
package serviceBD.app.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.ServiceRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Autowired
    @Mock
    AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ServiceRepository serviceRepository;


    private AccountService underTest;
    Service service= new Service("Climatisation");
    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);

    Account account=new Account("salma@gmail.com", "Salma2001",person);
    @BeforeEach
    void setUp() {
        underTest= new AccountService(accountRepository);
        serviceRepository.save(service);
        personRepository.save(person);
    }

    @Test
    void saveAccount() {
        underTest.saveAccount(account);
        ArgumentCaptor<Account> accountArgumentCaptor =
                ArgumentCaptor.forClass(Account.class);
        verify(accountRepository)
                .save(accountArgumentCaptor.capture());
        Account capturedAccount = accountArgumentCaptor.getValue();
        assertThat(capturedAccount).isEqualTo(account);
    }


    @Test
    void getAccounts() {
        underTest.getAccounts();
        verify(accountRepository).findAll();
    }

    @Test
    void getUserById() {
        account.setId(1);
        underTest.getUserById(1L);
        verify(accountRepository).getReferenceById(1L);
    }
}
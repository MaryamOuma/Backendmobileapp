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

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    void saveAccount() throws GeneralSecurityException, UnsupportedEncodingException {
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
    void getAccountById() {
        account.setId(1);
        underTest.getAccountById(1);
        verify(accountRepository).getReferenceById(1);
    }

    @Test
    void deleteAccount() throws AccountNotFoundException {
        given(accountRepository.existsById(account.getId()))
                .willReturn(true);
        underTest.deleteAccount(account.getId());
        verify(accountRepository).deleteById(account.getId());
    }
    @Test
    void verifyAccount() throws GeneralSecurityException, IOException {
       accountRepository.save(account);
       underTest.verifyAccount(account);
       verify(accountRepository).findByUsername(account.getUsername());
    }
}
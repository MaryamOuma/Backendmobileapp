package serviceBD.app.Controller;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.ServiceRepository;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static serviceBD.app.util.JsonUtil.toJson;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class AccountControllerTest {

    public static final String USERNAME = "username";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String PROFIL_TYPE = "profilType";

    public static final String URL_ACCOUNT_LIST = "/account/list";
    public static final String URL_ACCOUNT_LOGIN = "/account/login";
    public static final String URL_ACCOUNT_ID = "/account/{id}";
    public static final String URL_ACCOUNT_PROFILTYPE_USERNAME = "/account/profiltype/{username}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ServiceRepository serviceRepository;


    @AfterEach
    public void resetDb() {
        accountRepository.deleteAll();
        personRepository.deleteAll();
        serviceRepository.deleteAll();
    }

    @Test
    void return_ListOfAccounts() throws Exception {
        createAccounts();
        mockMvc.perform(get(URL_ACCOUNT_LIST).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    void return_Valid_Account() throws Exception {
        Account account = accountController.saveAcc(getAccount(TEST_PASSWORD, USERNAME, null));
        account.setPassword(TEST_PASSWORD);
        mockMvc.perform(post(URL_ACCOUNT_LOGIN).contentType(MediaType.APPLICATION_JSON).content(toJson(account)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void return_BadRequest_when_NoUsername() throws Exception {
        Account account = accountController.saveAcc(getAccount(TEST_PASSWORD, null, null));
        account.setPassword(TEST_PASSWORD);
        mockMvc.perform(post(URL_ACCOUNT_LOGIN).contentType(MediaType.APPLICATION_JSON).content(toJson(account)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void return_Account_ById() throws Exception {
        Account account = accountController.saveAcc(getAccount(TEST_PASSWORD, USERNAME, null));
        mockMvc.perform(get(URL_ACCOUNT_ID,account.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_delete_account_ById() throws Exception {
        Account account = accountController.saveAcc(getAccount(TEST_PASSWORD, USERNAME, null));
        mockMvc.perform(delete(URL_ACCOUNT_ID,account.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    void should_return_profilType() throws Exception {
        Account account = accountController.saveAcc(getAccount(TEST_PASSWORD, USERNAME, PROFIL_TYPE));

        mockMvc.perform(get(URL_ACCOUNT_PROFILTYPE_USERNAME,account.getUsername()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value(PROFIL_TYPE));
    }


    private Account getAccount(String password, String username, String profilType) {

        Account account1 = new Account();
        Person person = new Person();
        Service service = new Service();

        serviceRepository.save(service);
        person.setService(service);
        person.setTypeProfil(profilType);
        personRepository.save(person);

        account1.setPassword(password);
        account1.setPerson(person);
        account1.setUsername(username);
        accountRepository.save(account1);
        return account1;
    }


    private void createAccounts() {
        Account account1 = new Account();
        Account account2 = new Account();

        Person person = new Person();
        Person person2 = new Person();

        Service service = new Service();
        Service service2 = new Service();

        serviceRepository.save(service);
        person.setService(service);
        personRepository.save(person);
        serviceRepository.save(service2);
        person2.setService(service2);
        personRepository.save(person2);

        account1.setPerson(person);
        accountRepository.save(account1);

        account2.setPerson(person2);
        accountRepository.save(account2);
    }

}
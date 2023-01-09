package serviceBD.app.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Rating;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.ServiceRepository;
import serviceBD.app.Service.PersonService;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static serviceBD.app.util.JsonUtil.toJson;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class PersonControllerTest {

    private static final String URL_EMPLOYEES_LIST = "/employees/getAll";
    private static final String URL_EMPLOYEES_ID = "/employees/{id}";
    private static final String URL_EMPLOYEES_CATEGORY = "/employees/getByCateg/{cat}";
    private static final String URL_EMPLOYEES_CHECK_LOGIN = "/employees/checkLogin/{tel}";
    private static final String URL_EMPLOYEES_RATING = "/employees/{id}/ratings/{id_client}";
    private static final String URL_EMPLOYEES_SUM_RATING = "/employees/{id}/sumRatingByEmp/{id_client}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonService personService;

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
    void return_ListOfEmployees() throws Exception {
        createEmployees();
        mockMvc.perform(get(URL_EMPLOYEES_LIST).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    void return_Employees_ById() throws Exception {
        Person person = createEmployee();
        mockMvc.perform(get(URL_EMPLOYEES_ID,person.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(person.getId()));
    }

    @Test
    void return_Employees_ByCategory() throws Exception {
        Person person = createEmployee();
        mockMvc.perform(get(URL_EMPLOYEES_CATEGORY,person.getService().getService_title()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void check_Login_NotExist() throws Exception {
        mockMvc.perform(get(URL_EMPLOYEES_CHECK_LOGIN,"login").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value(Boolean.FALSE));
    }

    @Test
    void check_Login_Exist() throws Exception {
        Person person = createEmployee();
        mockMvc.perform(get(URL_EMPLOYEES_CHECK_LOGIN,person.getTel()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value(Boolean.TRUE));
    }

    @Test
    void rating_Employees() throws Exception {
        Person person = createEmployee();
        Person cli = createEmployee();
        Rating rating = new Rating(person, cli, 1);

        mockMvc.perform(post(URL_EMPLOYEES_RATING,person.getId(), cli.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(rating)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void get_Rating_Employees() throws Exception {
        Person person = createEmployee();
        Person cli = createEmployee();
        Rating rating1 = new Rating(person, cli, 3);
        personService.createRating(rating1, person.getId(), cli.getId());

        mockMvc.perform(get(URL_EMPLOYEES_RATING,person.getId(), cli.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value(3));
    }

    @Test
    void getSum_Rating_Employees() throws Exception {
        Person person = createEmployee();
        Person cli = createEmployee();
        Rating rating1 = new Rating(person, cli, 1);
        personService.createRating(rating1, person.getId(), cli.getId());

        mockMvc.perform(get(URL_EMPLOYEES_SUM_RATING,person.getId(), cli.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value(1));
    }

    private void createEmployees() {
        Person person = new Person();
        Person person2 = new Person();

        Service service = new Service();

        serviceRepository.save(service);
        person.setService(service);
        person2.setService(service);

        personRepository.save(person);
        personRepository.save(person2);
    }

    private Person createEmployee() {
        Person person = new Person();
        Service service = new Service();
        service.setService_title("category");
        serviceRepository.save(service);
        person.setService(service);
        person.setTypeProfil("Employé");
        person.setTel("login");
        personRepository.save(person);
        return person;
    }


}
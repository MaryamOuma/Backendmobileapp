package serviceBD.app.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.RestTemplate;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.ServiceRepository;
import serviceBD.app.Service.AccountService;
import serviceBD.app.Service.PersonService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class AccountControllerTest {


    private static RestTemplate restTemplate= new RestTemplate();

    @Autowired
    private PersonService personService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ServiceRepository serviceRepository;

    @LocalServerPort
            int port;

    Service service= new Service("Climatisation");
    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);

    Account account=new Account("salma@gmail.com", "Salma2001",person);

    @BeforeEach
    void setUp() throws GeneralSecurityException, UnsupportedEncodingException {

    }

    @Test
    void gatAcc(){
       // RequestBuilder request = get("/login");
        System.out.println(restTemplate.getForEntity("http://localhost:"+port+"/employees/getAll", Person.class));
        //assertEquals("salma@gmail.com", account1.getUsername());
        //assertEquals("Hello, World", result.getResponse().getContentAsString());
    }

    @Test
    void getEmployee() throws Exception {
        RequestBuilder request = get("/login");
        //MvcResult result = mvc.perform(request).andReturn();
        //assertEquals(, result.getResponse().getContentAsString());
    }

    @Test
    void deleteperson() {
    }

    @Test
    void getProfilType() {
    }
}
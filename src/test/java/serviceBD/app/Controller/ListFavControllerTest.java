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
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.ListFavRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.ServiceRepository;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class ListFavControllerTest {

    private static final String URL_ADD_FAV = "/favList/addFav";
    private static final String URL_LIST_FAV = "/favList/getFav/{id}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ListFavRepository listFavRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ServiceRepository serviceRepository;


    @AfterEach
    public void resetDb() {
        listFavRepository.deleteAll();
        personRepository.deleteAll();
        serviceRepository.deleteAll();
    }

    @Test
    void Add_Fav() throws Exception {

        Person person = addPerson();
        mockMvc.perform(post(URL_ADD_FAV).param("p1", "1").param("p2", String.valueOf(person.getId())).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    void get_ListFav() throws Exception {

        Person person = addPerson();
        Person cli = addPerson();
        Person person1 = addPerson();
        listFavRepository.addFav(cli.getId(),person.getId());
        listFavRepository.addFav(cli.getId(),person1.getId());

        mockMvc.perform(get(URL_LIST_FAV,cli.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    private Person addPerson() {
        Person person = new Person();
        Service service = new Service();

        serviceRepository.save(service);
        person.setService(service);
        personRepository.save(person);

        return person;
    }


}
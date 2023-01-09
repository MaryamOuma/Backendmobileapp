package serviceBD.app.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.ServiceRepository;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class ServiceControllerTest {


    private static final String URL_SERVICE_LIST = "/services/list";
    private static final String URL_SERVICE_LIST_STRING = "/services/listString";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ServiceRepository serviceRepository;

    @AfterEach
    public void resetDb() {
        serviceRepository.deleteAll();
    }

    @Test
    void return_ListOfServices() throws Exception {
        createServices();
        mockMvc.perform(get(URL_SERVICE_LIST).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    void return_ListOfStringServices() throws Exception {
        createServices();
        mockMvc.perform(get(URL_SERVICE_LIST_STRING).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    private void createServices() {

        Service service = new Service();
        service.setService_title("");

        Service service2 = new Service();
        service2.setService_title("");

        serviceRepository.save(service);
        serviceRepository.save(service2);

    }
}
package serviceBD.app.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import serviceBD.app.Model.Ville;
import serviceBD.app.Repository.VilleRepository;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class VilleControllerTest {

    private static final String URL_CITIES_LIST = "/villes/getAllcities";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    VilleRepository villeRepository;

    @AfterEach
    public void resetDb() {
        villeRepository.deleteAll();
    }

    @Test
    void return_ListOfCities() throws Exception {
        createCities();
        mockMvc.perform(get(URL_CITIES_LIST).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    private void createCities() {

        villeRepository.save(new Ville(1,"RABAT"));
        villeRepository.save(new Ville(2,"CASABLANCA"));

    }
}
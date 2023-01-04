package serviceBD.app.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Ville;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.VilleRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class VilleServiceTest {


    @Autowired
    @Mock
    private VilleRepository villeRepository;

    private VilleService underTest;

   // Ville ville= new Ville("Kénitra");


    @BeforeEach
    void setUp() {
        underTest= new VilleService(villeRepository);
    }

    @Test
    void getAllCities() {
        underTest.getAllCities();
        verify(villeRepository).findAll();
    }


}
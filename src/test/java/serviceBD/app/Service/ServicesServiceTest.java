package serviceBD.app.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Repository.ServiceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {

    @Autowired
    @Mock
    private ServiceRepository serviceRepository;

    private ServicesService underTest;

    @BeforeEach
    void setUp() {
        underTest= new ServicesService(serviceRepository);
    }

    @Test
    void getServices() {
        underTest.getServices();
        verify(serviceRepository).findAll();
    }
}
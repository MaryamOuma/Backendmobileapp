package serviceBD.app.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Message;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MessageRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    Service service= new Service("Climatisation");
    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);
    Message message= new Message(person, person, "Hello There", "22-12-2022");


    @Autowired
    MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        serviceRepository.save(service);
        personRepository.save(person);
        person.setFirstName("maram");
        personRepository.save(person);
    }

    @Test
    void getListMessages() {
        messageRepository.save(message);
        Boolean exists= false;
        if(messageRepository.getListMessages(person.getId(), person.getId())!=null){
            exists=true;
        }
        assertTrue(exists);
    }

  /*  @Test
    void NotgetListMessages() {
        Boolean exists= false;
        messageRepository.deleteById((long) message.getMessageId());
        if(messageRepository.getListMessages(person.getId(), person.getId())!=null){
            exists= true;
        }
        assertFalse(exists);
    }*/

    @Test
    void getAllConversations() {
        messageRepository.save(message);
        Boolean exists= false;
        if(messageRepository.getAllConversations(person.getId())!=null){
            exists=true;
        }
        assertTrue(exists);
    }
}
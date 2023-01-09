package serviceBD.app.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import serviceBD.app.Model.Message;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.MessageRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Repository.ServiceRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@DataJpaTest
@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Mock
    MessageRepository messageRepository;

    MessageService messageService;

    Service service= new Service("Climatisation");
    Person person= new Person("AE5889", "Salma", "saadi", "Salé","salma@gmail.com", "Employé", service);
    Message message= new Message(person, person, "Hello There", "22-12-2022");

    @BeforeEach
    void setUp() {
        messageService=new MessageService(messageRepository);
        serviceRepository.save(service);
        personRepository.save(person);
    }

    @Test
    void saveMessage() {
        messageRepository.save(message);
        ArgumentCaptor<Message> messageArgumentCaptor =
                ArgumentCaptor.forClass(Message.class);
        verify(messageRepository)
                .save(messageArgumentCaptor.capture());
        Message capturedMessage = messageArgumentCaptor.getValue();
        assertThat(capturedMessage).isEqualTo(message);
    }

    @Test
    void getListMessage() {
        messageService.getListMessage(person.getId(), person.getId());
        verify(messageRepository).getListMessages(person.getId(), person.getId());
    }

    @Test
    void fetchAll() {
        messageService.fetchAll(person.getId());
        verify(messageRepository).getAllConversations(person.getId());
    }
}
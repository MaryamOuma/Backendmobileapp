package serviceBD.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import serviceBD.app.Model.Account;
import serviceBD.app.Model.Message;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;
import serviceBD.app.Service.MessageService;
import serviceBD.app.Service.PersonService;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = {"*"})
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PersonService personService;


//    @MessageMapping("/privateMessage/{to}")

    @RequestMapping(path="/privateMessage/{to}", method=POST)
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestBody Message message, @PathVariable int to)  throws GeneralSecurityException, UnsupportedEncodingException {
        LocalDateTime now = LocalDateTime.now();

        Person personReceiver = personService.getUserById(to);

        message.setMessageTo(personReceiver);

        message.setCreatedDate(now);
        if(messageService.sendMessage(message)){

            System.out.println("handling send message:" + message + "to" + personReceiver.getFirstName() + "" + personReceiver.getLastName());

            simpMessagingTemplate.convertAndSend("/topic/privateMessage/" + to, message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }



    @GetMapping("/chat/listMessages/{from}/{to}")
    public List<Message> getListMssg(@PathVariable("from") int from,@PathVariable("to") int to){
        return messageService.getListMessage(from,to);
    }



    @GetMapping("/chat/listChats/{myId}")
    public List<Message> getChats(@PathVariable("myId") int myId){
        List<Message> listConversations = messageService.fetchAll(myId);

        return  listConversations;
    }

}
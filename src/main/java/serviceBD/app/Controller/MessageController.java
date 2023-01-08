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
@RequestMapping("/chat")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PersonService personService;
    
    @PostMapping("/privateMessage/save")
    @ResponseBody
    public Message saveMsg(@RequestBody Message message)
            throws GeneralSecurityException, UnsupportedEncodingException {
       // messageService.saveMessage(message.getPerson());
    	System.out.println("msg sent");
    	System.out.println(message.toString());
    	
    	// Find date and convert it :
    	LocalDateTime currentDateTime = LocalDateTime.now();
    	
    	DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");;
    	String formattedString = currentDateTime.format(customFormat);
    	System.out.println(formattedString);    //2022-12-09 18:25:58
    	
    	message.setDate(formattedString);
    	
        return messageService.saveMessage(message);
    }

    @GetMapping("/chat/listMessages/{from}/{to}")
    public List<Message> getListMssg(@PathVariable("from") int from,@PathVariable("to") int to){
    	List<Message> listMsgs = messageService.getListMessage(from,to);
    	
    	for (Message msg : listMsgs) {
    		if (msg.getMessageFrom().getId() == from)
    		{
    			msg.setViewType(1);
    		}
    		else msg.setViewType(2);
    	}
    	
       System.out.println(listMsgs.toString());
       System.out.println(from + " " + to);
       System.out.println("test");
    	
         return listMsgs;
    }



    @GetMapping("/chat/listChats/{myId}")
    public List<Message> getChats(@PathVariable("myId") int myId){
         List<Message> listConversations = messageService.fetchAll(myId);
         
         System.out.println("well am here");
 
         return  listConversations;
    }

}
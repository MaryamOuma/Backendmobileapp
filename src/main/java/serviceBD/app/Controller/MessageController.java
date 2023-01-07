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

    
    /*

//    @MessageMapping("/privateMessage/{to}")

   @RequestMapping(path="/privateMessage/{to}", method=POST)
   @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestBody Message message, @PathVariable Long to)  throws GeneralSecurityException, UnsupportedEncodingException {
       LocalDateTime now = LocalDateTime.now();

       Person personReceiver = personService.getUserById(to);

       message.setMessageTo(personReceiver);

      // message.setCreatedDate(now);
       if(messageService.sendMessage(message)){

            System.out.println("handling send message:" + message + "to" + personReceiver.getFirstName() + "" + personReceiver.getLastName());

            simpMessagingTemplate.convertAndSend("/topic/privateMessage/" + to, message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
            else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }

    }
    */
    
    @PostMapping("/privateMessage/save")
    @ResponseBody
    public Message saveMsg(@RequestBody Message message)
            throws GeneralSecurityException, UnsupportedEncodingException {
       // messageService.saveMessage(message.getPerson());
    	System.out.println("msg sent");
    	System.out.println(message.toString());
        return messageService.saveMessage(message);
    }

    @GetMapping("/chat/listMessages/{from}/{to}")
    public List<Message> getListMssg(@PathVariable("from") int from,@PathVariable("to") int to){
    	List<Message> listMsgs = messageService.getListMessage(from,to);
    	
    	for (Message msg : listMsgs) {
    		if (msg.getMessageFrom().getPerson_id() == from)
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

//    @GetMapping("/chat/listChats/{myId}")
//    public List<Message> getChats(@PathVariable("myId") int myId ){
//
//
//    	boolean foundYet = false;  // to check if the conversation with a specific person is already in the list to return
//    	int idRecherche;
//
//        List<Message> redondantListConversations = new ArrayList<>();
//        List<Message> listConversationsToReturn = new ArrayList<>();
//
//        redondantListConversations = messageService.fetchAll(myId);
//
//        Message firstMsg = redondantListConversations.get(0);
//
//        listConversationsToReturn.add(firstMsg);
//        int i = 0, j = 0;
//
//
//        /*
//        for (Message conversation : redondantListConversations) {
//
//        	idRecherche = (conversation.getMessageFrom().getId() != myId) ?
//        			conversation.getMessageFrom().getId() : conversation.getMessageTo().getId();
//
//        	for (Message conversationToKeep : redondantListConversations) {
//
//        		if (idRecherche == conversationToKeep.getWithPerson().getId()) {
//        			foundYet = true;
//        		}
//
//        	}
//
//        	if (!foundYet) {
//        		listConversationsToReturn.add(conversation);
//        	}
//        }
//        */
//
//        List<Integer> listInt1 = new ArrayList<>();
//        List<Integer> listInt2 = new ArrayList<>();
//
//        for ( i = 1 ; i < redondantListConversations.size() ; i++) {
//
//        	Message conversation = redondantListConversations.get(i);
//
//        	listInt1.add(conversation.getMessageFrom().getId());
//    		listInt1.add(conversation.getMessageTo().getId());
//
//        	for (j = 0 ; j < listConversationsToReturn.size() ; j++) {
//
//        		Message conversationToKeep = redondantListConversations.get(j);
//
//        		System.out.println("here");
//        		System.out.println(conversation.getMessageFrom().getId());
//        		System.out.println(myId);
//        		System.out.println(conversationToKeep.getMessageTo().getId());
//
//        		/*
//
//        		if ( conversation.getMessageFrom().getId() != myId
//        			&& conversation.getMessageTo().getId() != myId
//        			&& conversationToKeep.getMessageFrom().getId() != myId
//        			&& conversationToKeep.getMessageTo().getId() != myId
//        			&& ( conversation.getMessageFrom().getId() == conversationToKeep.getMessageFrom().getId()
//        			|| conversation.getMessageFrom().getId() == conversationToKeep.getMessageTo().getId()
//        			|| conversation.getMessageTo().getId() == conversationToKeep.getMessageFrom().getId()
//        			|| conversation.getMessageTo().getId() == conversationToKeep.getMessageTo().getId()
//        			)
//        			) {
//
//        			System.out.println("opa");
//        			foundYet = true;
//        		}
//
//        		*/
//
//        		listInt2.add(conversationToKeep.getMessageFrom().getId());
//        		listInt2.add(conversationToKeep.getMessageTo().getId());
//
//        		if ( (listInt1.get(0) != myId && !listInt2.contains(listInt1.get(0)))
//        			 || (listInt1.get(1) != myId && !listInt2.contains(listInt1.get(1)))  )
//        	    {
//        			foundYet = true;
//        		}
//
//
//
//        	}
//
//        	if (!foundYet) {
//    			System.out.println("one added");
//        		listConversationsToReturn.add(conversation);
//        	}
//
//
//
//        }
//
//
//        return listConversationsToReturn;
//
//    }

    @GetMapping("/chat/listChats/{myId}")
    public List<Message> getChats(@PathVariable("myId") int myId){
         List<Message> listConversations = messageService.fetchAll(myId);
 
         return  listConversations;
    }

}

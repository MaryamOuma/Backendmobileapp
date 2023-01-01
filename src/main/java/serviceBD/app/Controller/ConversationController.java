package serviceBD.app.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serviceBD.app.Model.Conversation;
import serviceBD.app.Model.Person;
import serviceBD.app.Service.ConversationService;
import serviceBD.app.Service.MessageService;

@RestController
@RequestMapping("/conversations")

@CrossOrigin
public class ConversationController {
	
	@Autowired
    ConversationService conversationService;
	
	/*
	@GetMapping("/getAll/{id}")
    public List<Conversation> getConversations(@PathVariable("id") int id) {
		
		boolean foundYet = false;  // to check if the conversation with a specific person is already in the list to return
		
        List<Conversation> redondantListConversations = new ArrayList<>();
        List<Conversation> listConversationsToReturn = new ArrayList<>();
        
        redondantListConversations = conversationService.getListConversations(id);
        
        for (Conversation conversation : redondantListConversations) {
        	
        	for (Conversation conversationToKeep : redondantListConversations) {
        		
        		if (conversation.getWithPerson().getId() == conversationToKeep.getWithPerson().getId()) {
        			foundYet = true;
        		}
        			
        	}
        	
        	if (!foundYet) {
        		listConversationsToReturn.add(conversation);
        	}
        }
        
        
        return redondantListConversations;
    }
    */

}

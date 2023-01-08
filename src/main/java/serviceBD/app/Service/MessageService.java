package serviceBD.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import serviceBD.app.Model.Message;
import serviceBD.app.Repository.MessageRepository;
import serviceBD.app.Repository.PersonRepository;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {


	@Autowired
    MessageRepository messageRepository;

/*
    public boolean sendMessage(Message message){
        if(messageRepository.save(message).equals(null)){
            return false;
        }
        return true;
    }
    */
    
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    
    public List<Message> getListMessage(int from,int to){

        return messageRepository.getListMessages(from, to);
    }
    
    public List<Message> fetchAll(int myId){
    	
        List <Message> getAllConversations = messageRepository.getAllConversations(myId);
        return getAllConversations;

    }


}
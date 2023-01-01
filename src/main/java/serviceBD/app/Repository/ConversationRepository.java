package serviceBD.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import serviceBD.app.Model.Person;
import serviceBD.app.Model.Message;
import serviceBD.app.Model.Conversation;

/*
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

	//list all conversations
    @Query(value = "select * from messages as m, person as p where (m.message_from = :from and p.id = m.message_to) or (m.message_to = :from and p.id = m.message_from) order by m.created_date", nativeQuery = true)
    List<Conversation> getAllConversations(int from);
    
}
*/

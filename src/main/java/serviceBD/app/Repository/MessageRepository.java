package serviceBD.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import serviceBD.app.Model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    //list messages d'une discussion
    @Query(value = "select * from messages as m where m.message_from = :from and m.message_to = :to or m.message_from = :to and m.message_to = :from order by m.created_date",nativeQuery = true)
    List<Message> getListMessages(int from, int to);

    //list all conversations
//    @Query(value = "select * from messages as m where m.message_from = :from or m.message_to = :from order by m.created_date", nativeQuery = true)
    @Query(value = "SELECT t1.* FROM messages t1 JOIN ( SELECT LEAST(message_from, message_to) user1, GREATEST(message_from, message_to) user2, MAX(created_date) created_date FROM messages t2 GROUP BY user1, user2 ) t3 ON t1.message_from IN (t3.user1, t3.user2) AND t1.message_to IN (t3.user1, t3.user2) AND t1.created_date = t3.created_date where t1.message_from = :from or t1.message_to = :from", nativeQuery = true)
    List<Message> getAllConversations(int from);
}
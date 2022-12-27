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
    @Query(value = "select * from messages as m where m.message_from = :from and m.message_to != :from", nativeQuery = true)
    List<Message> getAllConversations(int from);
}

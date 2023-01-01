package serviceBD.app.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@DynamicUpdate
public class Conversation {
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conversationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE )
    @JoinColumn(name = "person_id")
    private Person withPerson;


    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE )
    @JoinColumn(name = "messageId")
    private Message lastMessage;

}

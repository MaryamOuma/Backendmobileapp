package serviceBD.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@DynamicUpdate
@Entity
@Table(name = "messages")
public class Message {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int messageId;
  
  @Column(name = "viewType")
  private int viewType;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE )
  @JoinColumn(name = "message_to")
  private Person messageTo;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE )
  @JoinColumn(name = "message_from")
  private Person messageFrom;

  @Column(name = "message_text")
  private String messageText;

  @Column(name = "date")
  private String date;
  

}
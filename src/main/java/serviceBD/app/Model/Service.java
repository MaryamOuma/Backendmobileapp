package serviceBD.app.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@DynamicUpdate
@Table(name = "service")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Service{
    @Id
    @Column(name = "service_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long service_id;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Person> personnes;

    @Column(name = "service_title")
    private String service_title;
}

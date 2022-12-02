package serviceBD.app.Model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@DynamicUpdate
@Table(name = "service")
public class Service{
    @Id
    @Column(name = "service_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int service_id;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Person> personnes;

    @Column(name = "service_title")
    private String service_title;

    @Column(name="image")
    private String image;
}

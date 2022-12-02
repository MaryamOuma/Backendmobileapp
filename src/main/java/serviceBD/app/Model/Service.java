package serviceBD.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "service")
public class Service {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private Set<Person> personnes;

    @Column(name = "service_title")
    private String service_title;
    
    @Column(name="image")
    private String image;
}

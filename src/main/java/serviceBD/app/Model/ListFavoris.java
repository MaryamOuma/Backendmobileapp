package serviceBD.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "list_favoris")
public class ListFavoris {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name="employe_id", nullable=false)
    private Person emp;

}

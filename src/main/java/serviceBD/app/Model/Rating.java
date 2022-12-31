package serviceBD.app.Model;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Rating implements Serializable {
    @Id
    @Column(name = "id_rating", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rating;
    
    private int label;

    @ManyToOne
    @JoinColumn(name = "id")
    private Person person;

    public Rating(int label, Person person) {
        this.label = label;
        this.person = person;
    }

    public Rating() {

    }
}
package serviceBD.app.Model;

import java.io.Serializable;
import java.util.Optional;

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

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Person client;


    public int getId_rating() {
        return this.id_rating;
    }

    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public int getLabel() {
        return this.label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getClient() {
        return this.client;
    }

    public void setClient(Person client) {
        this.client = client;
    }


}
package serviceBD.app.Model;

import jakarta.persistence.*;
import lombok.Data;


import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@DynamicUpdate
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cin")
    private String cin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "tel")
    private String tel;

    @Column(name = "image")
    private String image;

    @Column(name = "type_profil")
    private String typeProfil;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonIgnore
    private Service service;

    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "service_id", nullable = false)
    // @JsonIgnore
    // private Service service;
}

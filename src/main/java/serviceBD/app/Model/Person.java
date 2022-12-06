package serviceBD.app.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImageP() {
		return imageP;
	}

	public void setImageP(String imageP) {
		this.imageP = imageP;
	}

	public String getTypeProfil() {
		return typeProfil;
	}

	public void setTypeProfil(String typeProfil) {
		this.typeProfil = typeProfil;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

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

    @Column(name = "imageP")
    private String imageP;

	@Column(name = "type_profil")
    private String typeProfil;
    
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade=CascadeType.DETACH)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
}

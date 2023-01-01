package serviceBD.app.Model;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ville")
public class Ville {
	  @Id
	    @Column(name = "id", nullable = false)
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	  @Column(name = "nom_ville")
	    private String nom_ville;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_ville() {
		return nom_ville;
	}
	public void setNom_ville(String nom_ville) {
		this.nom_ville = nom_ville;
	}
}

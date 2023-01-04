package serviceBD.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import serviceBD.app.Model.Person;
import serviceBD.app.Model.Ville;
@Repository
public interface VilleRepository extends  JpaRepository<Ville, Long> {
}

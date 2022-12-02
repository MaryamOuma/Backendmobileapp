package serviceBD.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serviceBD.app.Model.Person;
import serviceBD.app.Model.Service;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}

package serviceBD.app.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import serviceBD.app.Model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends  JpaRepository<Person, Long> {
    @Query(nativeQuery = true, value = "SELECT p.id, p.cin, p.first_name, p.city, p.description, p.type_profil, p.imageP, p.last_name, p.tel, s.service_title, s.image, s.service_id from person as p JOIN service as s  ON s.service_id = p.service_id")
    List<Person> findAllEmp();


    @Query(nativeQuery = true, value = "SELECT p.id, p.cin, p.first_name, p.city, p.description, p.type_profil, p.imageP, p.last_name, p.tel, s.service_title, s.image, s.service_id from person as p JOIN service as s  ON s.service_id = p.service_id where p.type_profil like '%Employé%' AND s.service_title like %:service_title%")
    List<Person> findByCategoryAndType(String service_title);

    @Query(nativeQuery = true, value = "SELECT tel from person")
    List<String> findAllLogins();
}
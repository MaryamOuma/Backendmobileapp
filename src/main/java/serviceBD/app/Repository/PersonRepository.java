package serviceBD.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serviceBD.app.Model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
  @Query(nativeQuery = true, value = "SELECT * from Category as c JOIN Personne as p ON c.category_id = p.category_id")
    List<Personne> findAllEmp();

    @Query(nativeQuery = true, value = "SELECT * from Category as c JOIN Personne as p ON c.category_id = p.category_id where p.type like %:type% AND c.label like %:category% ")
    List<Personne> findByCategoryAndType(String type, String category);


}

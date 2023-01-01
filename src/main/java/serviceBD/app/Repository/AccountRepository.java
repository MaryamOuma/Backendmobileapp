package serviceBD.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM Account a WHERE a.username = :username", nativeQuery = true)
    Optional<Account> findByUsername(String username);
    
    
    @Query(value = "SELECT  person_id  FROM Account a WHERE a.id = :id", nativeQuery = true)
     long  findPerson_id(Long id);

    @Query(value="SELECT p.type_profil FROM person as p join account as a WHERE p.id = a.person_id and a.username = :username", nativeQuery = true)
    String findTypeProfil(String username);

}
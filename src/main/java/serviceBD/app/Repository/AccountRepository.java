package serviceBD.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM Account a WHERE a.username = :username", nativeQuery = true)
    Optional<Account> findByUsername(String username);
    
  
    
    @Query(value = "SELECT  person_id  FROM Account a WHERE a.id = :id", nativeQuery = true)
     long  findPerson_id(Long id);
 
    /*@Transactional
    @Modifying
    @Query(value = "UPDATE Person p , Account a  SET p=:p and a=:a where p.id = a.person_id ", nativeQuery = true)
    void editprofile(Person person, Account account, long id);*/
	

}
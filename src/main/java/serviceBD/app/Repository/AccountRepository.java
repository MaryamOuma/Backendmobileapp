package serviceBD.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

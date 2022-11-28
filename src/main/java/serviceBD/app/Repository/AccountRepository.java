package serviceBD.app.Repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import serviceBD.app.Model.Account;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

}

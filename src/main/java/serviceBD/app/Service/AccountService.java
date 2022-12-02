package serviceBD.app.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceBD.app.Model.Account;
import serviceBD.app.Repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
 //   private final JwtEncoder encoder;

    public Account saveAccount(Account account){

        return  accountRepository.save(account);
    }
    public Optional<Account> getAccount(String username){
        return  accountRepository.findByUsername(username);
    }
    public List<Account> getAccounts(){
        return  accountRepository.findAll();
    }


}
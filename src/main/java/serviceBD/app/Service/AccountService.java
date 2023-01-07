package serviceBD.app.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Repository.PersonRepository;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import static serviceBD.app.Config.Encryption.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Optional<Account> getAccount(String username){
        return  accountRepository.findByUsername(username);
    }
    public List<Account> getAccounts(){
        return  accountRepository.findAll();
    }
	public Account getAccountById(int id) {
		 Account account= accountRepository.getReferenceById(id);
         return account;
	}
    public String getProfilType(String username){
        return accountRepository.findTypeProfil(username);
    }
    public Account saveAccount(Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey(account.getPassword().toCharArray(), salt, iterationCount, keyLength);
        account.setPassword(encrypt(account.getPassword(), key));
        return accountRepository.save(account);
    }

    public Account verifyAccount(Account account) throws GeneralSecurityException, IOException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        if (optionalAccount.isPresent()) {
            Account acc = optionalAccount.get();
            byte[] salt = new String("12345678").getBytes();
            int iterationCount = 40000;
            int keyLength = 128;
            String password= account.getPassword();
            SecretKeySpec key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
            String decryptedPassword = decrypt(acc.getPassword(), key);
            if (password.equals(decryptedPassword)) {
                return acc;
            } else {
                return null;
            }
        }else {
            return null;
        }
    }

    public void deleteAccount(int id) throws AccountNotFoundException {
        if(!accountRepository.existsById(id)){
            throw new AccountNotFoundException("id: "+ id);
        }
        else {
            int person_id = Math.toIntExact(accountRepository.findPerson_id(id));
            accountRepository.deleteById(id);
            personRepository.deleteById(person_id);
        }
    }

}

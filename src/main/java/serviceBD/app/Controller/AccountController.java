package serviceBD.app.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Service.AccountService;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.AccountNotFoundException;

import static serviceBD.app.config.Encryption.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/account")
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Account>> getAcc() throws GeneralSecurityException, UnsupportedEncodingException {
        List<Account> accounts = accountService.getAccounts();
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        String password="nouha2001";
        SecretKeySpec key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
        String originalPassword = password;
        String encryptedPassword = encrypt(originalPassword, key);
       System.out.println(encryptedPassword);
        return new ResponseEntity<>(accounts, HttpStatus.ACCEPTED);
    }
   // @PostMapping("/save")
    public Account saveAcc(Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey(account.getPassword().toCharArray(), salt, iterationCount, keyLength);
        account.setPassword(encrypt(account.getPassword(), key));
        Account acc = accountService.saveAccount(account);
       return acc;
    }
    @PostMapping ("/login")
    @ResponseBody
    public ResponseEntity<Account> gatAcc(@RequestBody Account account) throws GeneralSecurityException, IOException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        //System.out.println(account.getUsername());
        if (optionalAccount.isPresent()) {
            Account acc = optionalAccount.get();
            byte[] salt = new String("12345678").getBytes();
            int iterationCount = 40000;
            int keyLength = 128;
            String password= account.getPassword();
            SecretKeySpec key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
           // String encryptedPassword = encrypt(password, key);
            String decryptedPassword = decrypt(acc.getPassword(), key);
            if (password.equals(decryptedPassword)) {
                return new ResponseEntity<>(acc, HttpStatus.ACCEPTED);
            } else {
               return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getEmployee(@PathVariable(value = "id") int id) {
        Account a =  accountService.getUserById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteperson(@PathVariable (value = "id")  int id ) throws AccountNotFoundException {
        
        if(!accountRepository.existsById(id)){
        	throw new AccountNotFoundException("id: "+ id);

        }
        else {
        	int person_id =accountRepository.findPerson_id(id);
        	accountRepository.deleteById(id);
        	personRepository.deleteById(person_id);
    }
		
		
    }
}

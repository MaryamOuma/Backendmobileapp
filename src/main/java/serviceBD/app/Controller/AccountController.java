package serviceBD.app.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Repository.PersonRepository;
import serviceBD.app.Service.AccountService;
import serviceBD.app.Service.PersonService;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.AccountNotFoundException;

import static serviceBD.app.Config.Encryption.*;

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

    @Autowired
    PersonService personService;


    @PostMapping ("/login")
    @ResponseBody
    public ResponseEntity<Account> gatAcc(@RequestBody Account account) throws GeneralSecurityException, IOException {
        if (!accountService.verifyAccount(account).equals(null)) {
            return new ResponseEntity<>(accountService.verifyAccount(account), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getEmployee(@PathVariable(value = "id") int id) {
        Account a =  accountService.getAccountById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteperson(@PathVariable (value = "id")  int id ) throws AccountNotFoundException, ChangeSetPersister.NotFoundException {
        accountService.deleteAccount(id);
        personService.deletePerson(id);
    }
    @PutMapping("/user/login")
    public void updateAccount( @RequestBody Account account) throws UnsupportedEncodingException, GeneralSecurityException {
        Optional<Account> optional_account =accountRepository.findByUsername(account.getUsername());

        if (optional_account.isPresent()) {
            System.out.println("updated");
            Account acc = optional_account.get();
            byte[] salt = new String("12345678").getBytes();
            int iterationCount = 40000;
            int keyLength = 128;
            SecretKeySpec key = createSecretKey(account.getPassword().toCharArray(), salt, iterationCount, keyLength);
            acc.setPassword(encrypt(account.getPassword(), key));

            acc.setUsername(account.getUsername());
            accountRepository.save( acc);
            System.out.println("updated");
        } else {
            System.out.println("cette email n que vous voulez mettre a jour n 'existe pas");

        }

    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseEntity<Account> edit_profile(@RequestBody Account account) throws GeneralSecurityException, IOException {
        Person per= personService.getUserById(account.getPerson().getId());
        Account acc= accountRepository.getReferenceById(account.getPerson().getId());
        //System.out.println("person:" +account.getPerson().getId() + "  "+ account);
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        String password= account.getPassword();
        SecretKeySpec key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
        // String encryptedPassword = encrypt(password, key);
        String decryptedPassword = decrypt(acc.getPassword(), key);
        if (password.equals(decryptedPassword)) {
            per.setCity(account.getPerson().getCity());
            per.setTel(account.getPerson().getTel());
            per.setImageP(account.getPerson().getImageP());
            per.setCin(account.getPerson().getCin());
            per.setFirstName(account.getPerson().getFirstName());
            per.setLastName(account.getPerson().getLastName());
            per.setDescription(account.getPerson().getDescription());
            acc.setUsername(account.getUsername());
            personRepository.save(per);
            accountRepository.save(acc);
            return new ResponseEntity<>(acc, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/profiltype/{username}")
    public String getProfilType(@PathVariable(value="username") String username){
        return accountService.getProfilType(username);
    }
}




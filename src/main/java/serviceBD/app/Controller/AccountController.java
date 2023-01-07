package serviceBD.app.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.Account;
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

    @GetMapping("/profiltype/{username}")
    public String getProfilType(@PathVariable(value="username") String username){
        return accountService.getProfilType(username);
    }
}




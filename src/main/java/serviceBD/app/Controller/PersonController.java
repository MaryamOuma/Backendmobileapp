package serviceBD.app.Controller;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Service.AccountService;
import serviceBD.app.Service.PersonService;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static serviceBD.app.config.Encryption.createSecretKey;
import static serviceBD.app.config.Encryption.encrypt;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/person")
@RequiredArgsConstructor
@AllArgsConstructor
public class PersonController {
    @Autowired
    AccountController accountController;

    @Autowired
    PersonService personService;

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Account> saveAcc(@RequestBody Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        if(personService.savePerson(account.getPerson())) {
            System.out.println("dkhltt");
            return new ResponseEntity<>(accountController.saveAcc(account), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

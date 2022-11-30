package serviceBD.app.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.Account;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Service.AccountService;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

import static serviceBD.app.config.Encryption.createSecretKey;
import static serviceBD.app.config.Encryption.encrypt;

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
    @PostMapping("/save")
    public ResponseEntity<Account> saveAcc(@RequestBody Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey(account.getPassword().toCharArray(), salt, iterationCount, keyLength);
        account.setPassword(encrypt(account.getPassword(), key));
        Account acc = accountService.saveAccount(account);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }
    @PostMapping ("/login")
    public ResponseEntity<Account> gatAcc(@RequestParam("username") String username , @RequestParam("password") String password) throws GeneralSecurityException, IOException {
        //accountService.saveAccount(account);
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {
            Account acc = optionalAccount.get();
            byte[] salt = new String("12345678").getBytes();
            int iterationCount = 40000;
            int keyLength = 128;
            SecretKeySpec key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
            String originalPassword = password;
            String encryptedPassword = encrypt(originalPassword, key);
            // String decryptedPassword = decrypt(encryptedPassword, key);
            if (encryptedPassword.equals(acc.getPassword())) {
                System.out.println("sign in succesfull");
              return new ResponseEntity<>(acc, HttpStatus.ACCEPTED);
            } else {
               return new ResponseEntity<>(acc, HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //return  "accueil";
    }




}

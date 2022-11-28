package serviceBD.app.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.Account;
import serviceBD.app.Service.AccountService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<List<Account>> getAcc() {
        List<Account> accounts = accountService.getAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.ACCEPTED);
    }
    @PostMapping("/save")
    public ResponseEntity<Account> saveAcc(@RequestBody Account account) {
        Account acc = accountService.saveAccount(account);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<Account> gatAcc(@RequestBody String username) {
        Account acc = accountService.getAccount(username);
        return new ResponseEntity<>(acc, HttpStatus.ACCEPTED);
    }


}

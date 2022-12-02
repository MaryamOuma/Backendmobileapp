package serviceBD.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import serviceBD.app.Model.Account;
import serviceBD.app.Repository.AccountRepository;
import serviceBD.app.Service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
    AccountRepository accountRepository;
    
    @Autowired
	private AccountService accountService;
	
	/**
	 * Create - Add a new user / account 
	**/
    
	@PostMapping("/accounts/register")
	public Account createAccount(@RequestBody Account newAccount) {
		
		List<Account> users = accountRepository.findAll();
        System.out.println("New account: " + newAccount.toString());
        for (Account user : users) {
            System.out.println("Registered user: " + newAccount.toString());
            if (user.equals(newAccount)) {
                System.out.println("Account Already exists!");
                
            }
            else System.out.println("user saved !");
         
        }
        
		return accountService.saveAccount(newAccount);
		
		
	}

}

package serviceBD.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import serviceBD.app.Model.Account;
import serviceBD.app.Repository.AccountRepository;

@Service
@Data
public class AccountService {
	
	@Autowired
    private AccountRepository accountRepository;
	
	public Account saveAccount(Account account) {
		Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

}

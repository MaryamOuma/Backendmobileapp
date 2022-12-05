package serviceBD.app.Controller;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import serviceBD.app.Service.PersonService;
import serviceBD.app.Model.Account;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.PersonRepository;

@RestController
@RequestMapping("/employees")

@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private AccountController accountController;

    public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public AccountController getAccountController() {
		return accountController;
	}

	public void setAccountController(AccountController accountController) {
		this.accountController = accountController;
	}

	@GetMapping("/getAll")
    public List<Person> list() {
        return personService.getAllEmployees();
    }

    @GetMapping("/getPlombier")
    public List<Person> listPlombier(@Param("type") String type, @Param("category") String category) {
        return personService.getEmployeeByCategory("Employee", "Plomberie");
    }

    @GetMapping("/getPeinture")
    public List<Person> listPeinture(@Param("type") String type) {
        return personService.getEmployeeByCategory("Employee", "Peinture");
    }

    @GetMapping("/getElectricite")
    public List<Person> listElectricite(@Param("type") String type) {
        return personService.getEmployeeByCategory("Employee", "Electricité");
    }

    @GetMapping("/getClimatisation")
    public List<Person> listClimatisation(@Param("type") String type) {
        return personService.getEmployeeByCategory("Employee", "Climatisation");
    }

    @GetMapping("/getBricolage")
    public List<Person> listBricolage(@Param("type") String type) {
        return personService.getEmployeeByCategory("Employee", "Bricolage");
    }

    @GetMapping("/getFemmeMenage")
    public List<Person> listFemmeMenage(@Param("type") String type) {
        return personService.getEmployeeByCategory("Employee", "Femme de ménage");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getEmployee(@PathVariable(value = "id") Long id) {
        Person a = personService.getUserById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
   
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Account> saveAcc(@RequestBody Account account) throws GeneralSecurityException, UnsupportedEncodingException {
        if(personService.savePerson(account.getPerson())) {
            return new ResponseEntity<>(accountController.saveAcc(account), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
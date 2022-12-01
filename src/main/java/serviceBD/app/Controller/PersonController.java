package serviceBD.app.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceBD.app.Service.PersonService;
import serviceBD.app.Model.Person;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/getAll")
    public List<Person> list() {
        return personService.getAllEmployees();
    }

    @GetMapping("/getpLombier")
    public List<Person> listPlombier(@Param("type") String type, @Param("category") String category) {
        return personService.getEmployeeByCategory("Employee", "Plomberie");
    }

    @GetMapping("/getPeiture")
    public List<Person> listPeinture(@Param("type") String type) {
        return personService.getEmployeeByCategory("Employee", "Peinture");
    }

    @GetMapping("/getElectricie")
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


}

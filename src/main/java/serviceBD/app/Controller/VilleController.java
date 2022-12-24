package serviceBD.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serviceBD.app.Model.Person;
import serviceBD.app.Model.Ville;
import serviceBD.app.Service.VilleService;

@RequestMapping("/villes")


@CrossOrigin


@RestController
public class VilleController {
	@Autowired
 VilleService ville_service;
	@GetMapping("/getAllcities")
    public List<Ville> list() {
        return ville_service.getAllCities();
    }
}

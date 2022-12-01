package serviceBD.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceBD.app.Model.Service;
import serviceBD.app.Service.ServicesService;

import java.security.GeneralSecurityException;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RequestMapping("/services")
@RestController
public class ServiceController {

    @Autowired
    ServicesService serviceService;

    @GetMapping("/list")
    public List<Service> listServices() throws GeneralSecurityException {
        return serviceService.getServices();
    }
}

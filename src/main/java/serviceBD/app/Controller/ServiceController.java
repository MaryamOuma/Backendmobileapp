package serviceBD.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.ServiceRepository;
import serviceBD.app.Service.ServicesService;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RequestMapping("/services")
@RestController
public class ServiceController {

    @Autowired
    ServicesService serviceService;
    
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/list")
    public List<Service> listServices() throws GeneralSecurityException {
        List<Service> serviceList = new ArrayList<>();
        for (Service s:
                serviceService.getServices() ) {
            if(!s.getService_title().equals("Client")){
                serviceList.add(s);
            }
        }
        return serviceList;
    }
    
    @GetMapping("/listString")
    ResponseEntity<List<String>> findServices(){
        List<Service> serviceList=serviceRepository.findAll();
        List<String> list= new ArrayList<>();
        for (Service ser:
                serviceList
             ) {
            if(!ser.getService_title().equals("Client")) {
                list.add(ser.getService_title());
            }
        }
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
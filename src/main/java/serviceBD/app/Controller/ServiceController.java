package serviceBD.app.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceBD.app.Model.Service;
import serviceBD.app.Repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/service")
@RequiredArgsConstructor
@AllArgsConstructor
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/list")
    ResponseEntity<List<String>> findServices(){
        List<Service> serviceList=serviceRepository.findAll();
        List<String> list= new ArrayList<>();
        for (Service ser:
                serviceList
             ) {
            list.add(ser.getService_title());
        }
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

}

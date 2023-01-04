package serviceBD.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceBD.app.Repository.ServiceRepository;

import java.util.List;

@Service
public class ServicesService {
    @Autowired
    ServiceRepository serviceRepository;

    public List<serviceBD.app.Model.Service> getServices(){
        return serviceRepository.findAll();
    }

    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
}
package serviceBD.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceBD.app.Model.Person;
import serviceBD.app.Model.Ville;
import serviceBD.app.Repository.VilleRepository;
@Service
public class VilleService {
    @Autowired
	 VilleRepository villeRepository;

	public List<Ville> getAllCities() {
		// TODO Auto-generated method stub
		  return villeRepository.findAll();
	}

	public VilleService(VilleRepository villeRepository) {
		this.villeRepository = villeRepository;
	}
}

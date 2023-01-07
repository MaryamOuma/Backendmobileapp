package serviceBD.app.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceBD.app.Model.ListFavoris;
import serviceBD.app.Model.Person;
import serviceBD.app.Repository.ListFavRepository;
import serviceBD.app.Service.ListFavService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/favList")
@RequiredArgsConstructor
@AllArgsConstructor
public class ListFavController {

    @Autowired
    ListFavService listFavService;

    @Autowired
    ListFavRepository listFavRepository;

    @PostMapping("/addFav")
    public ResponseEntity<ListFavoris> addFav(@RequestParam("p1") int p1,@RequestParam("p2") int p2 ){
        listFavRepository.addFav(p1,p2);
        return  new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/deleteFav")
    public void deleteFav(@RequestParam("id1") int p1,@RequestParam("id2") int p2 ){
        listFavRepository.deleteFav(p1,p2);
    }

    @GetMapping("/getFav/{id}")
    public List<ListFavoris> getFav(@PathVariable("id") int id){
        List<ListFavoris> favorisList=listFavRepository.getFav(id);
        return favorisList;
    }
}

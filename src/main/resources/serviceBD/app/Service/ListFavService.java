package serviceBD.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceBD.app.Model.ListFavoris;
import serviceBD.app.Repository.ListFavRepository;

import java.util.List;

@Service
public class ListFavService {
    @Autowired
    ListFavRepository listFavRepository;

    public List<ListFavoris> getAll(){
        return listFavRepository.findAll();
    }

    public ListFavoris saveListFavRepository(ListFavoris listFav) {
        return  listFavRepository.save(listFav);
    }
}

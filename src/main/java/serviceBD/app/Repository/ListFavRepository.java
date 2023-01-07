package serviceBD.app.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import serviceBD.app.Model.ListFavoris;
import serviceBD.app.Model.Person;

import java.util.List;

@Repository
public interface ListFavRepository extends JpaRepository<ListFavoris, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO list_favoris (employe_id, client_id) VALUES (:p2, :p1)", nativeQuery = true)
    void addFav(int p1, int p2);

    @Query(value = "SELECT * FROM list_favoris WHERE client_id=:id", nativeQuery = true)
    List<ListFavoris> getFav(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM  list_favoris WHERE employe_id=:id2 AND  client_id=:id1", nativeQuery = true)
    void deleteFav(int id1, int id2);
}

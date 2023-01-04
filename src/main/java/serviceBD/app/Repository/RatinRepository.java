package serviceBD.app.Repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import serviceBD.app.Model.Rating;

public interface RatinRepository extends JpaRepository<Rating, Integer> {
    @Query(nativeQuery = true, value = "SELECT AVG(label) from rating r where r.id = :id ")
    Long sumRatingById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE rating r SET r.label=:label where r.id_client = :id_client and r.id=:id", nativeQuery = true)
    void updateRating(int label, int id_client, int id);

    @Query(nativeQuery = true, value = "SELECT COUNT(id_rating) from rating r where r.id = :id")
    Long sumColumnsRating(int id);

    @Query(nativeQuery = true, value = "SELECT label from rating r where r.id_client = :id_client AND r.id=:id")
    Long getRatingByClient(int id, int id_client);

}
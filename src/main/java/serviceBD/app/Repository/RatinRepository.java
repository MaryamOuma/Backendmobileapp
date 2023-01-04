package serviceBD.app.Repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serviceBD.app.Model.Rating;

public interface RatinRepository extends JpaRepository<Rating, Integer> {
    @Query(nativeQuery = true, value = "SELECT AVG(label) from rating r where r.id = :id ")
    int sumRatingById(int id);

    @Query(nativeQuery = true, value = "SELECT COUNT(id_rating) from rating r where r.id = :id")
    int sumColumnsRating(int id);

    @Query(nativeQuery = true, value = "SELECT label from rating r where r.id_client = :id_client AND r.id=:id")
    Long getRatingByClient(int id, int id_client);

}
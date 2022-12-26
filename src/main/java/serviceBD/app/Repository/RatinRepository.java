package serviceBD.app.Repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serviceBD.app.Model.Rating;

public interface RatinRepository extends JpaRepository<Rating, Integer> {
    @Query(nativeQuery = true, value = "SELECT AVG(label) from rating r where r.id = :id")
    float sumRatingById(Long id);
}
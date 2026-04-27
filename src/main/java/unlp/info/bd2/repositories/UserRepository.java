package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u JOIN u.purchaseList p JOIN p.route r WHERE p.totalPrice >= :amount")
    public List<User> getUserSpendingMoreThan(@Param("amount") float mount);

    @Query("SELECT DISTINCT g FROM TourGuideUser g JOIN g.routes r, Purchase p WHERE p.route = r AND p.review.rating = 1")
    public List<TourGuideUser> getTourGuidesWithRating1();
}

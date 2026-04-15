package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Review;
import java.util.List;

public interface ReviewRepository {
    public Review saveReview(Review review);

    public Review findById(Long id);

    public List<Review> findAll();

    public void delete(Review review);
}

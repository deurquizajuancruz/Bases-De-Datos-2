package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.Review;

@Repository
public class ReviewRepositoryImpl extends BaseRepositoryImpl<Review> implements ReviewRepository  {

    public ReviewRepositoryImpl(SessionFactory sessionFactory) {
        super(Review.class, sessionFactory);
    }

}

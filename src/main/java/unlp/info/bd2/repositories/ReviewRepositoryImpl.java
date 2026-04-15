package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.SessionFactory;
import unlp.info.bd2.model.Review;

public class ReviewRepositoryImpl implements ReviewRepository {
    private SessionFactory sessionFactory;

    public ReviewRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Review saveReview(Review review) {
        this.sessionFactory.getCurrentSession().merge(review);
        return review;
    }

    @Override
    public Review findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Review.class, id);
    }

    @Override
    public List<Review> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Review", Review.class).getResultList();
    }

    @Override
    public void delete(Review review) {
        this.sessionFactory.getCurrentSession().remove(review);
    }

}

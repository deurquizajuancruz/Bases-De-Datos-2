package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        String hql = "FROM User u WHERE u.username = :uname";
        return this.sessionFactory.getCurrentSession().createQuery(hql, User.class).setParameter("uname", username).uniqueResultOptional();
    }

    @Override
    public List<User> getUserSpendingMoreThan(float mount) {
        String hql = "SELECT DISTINCT u FROM User u JOIN u.purchaseList p WHERE p.totalPrice >= :amount";
        return this.sessionFactory.getCurrentSession().createQuery(hql, User.class)
                .setParameter("amount", mount).getResultList();
    }

    @Override
    public List<TourGuideUser> getTourGuidesWithRating1() {
        String hql = "SELECT DISTINCT g FROM TourGuideUser g "
                + "JOIN g.routes r, Purchase p "
                + "WHERE p.route = r "
                + "AND p.review.rating = 1";

        return this.sessionFactory.getCurrentSession()
                .createQuery(hql, TourGuideUser.class)
                .getResultList();
    }

}

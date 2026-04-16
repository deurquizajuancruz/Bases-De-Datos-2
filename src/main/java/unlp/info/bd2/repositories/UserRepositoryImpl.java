package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.SessionFactory;
import unlp.info.bd2.model.User;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User saveUser(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(this.sessionFactory.getCurrentSession().get(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Supplier", User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        this.sessionFactory.getCurrentSession().remove(user);
    }

}

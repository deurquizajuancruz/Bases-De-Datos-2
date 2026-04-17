package unlp.info.bd2.repositories;

import java.util.Optional;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.User;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        String hql = "from user where u.username = :uname";
        return this.sessionFactory.getCurrentSession().createQuery(hql, User.class).setParameter("uname", username).uniqueResultOptional();
    }

}

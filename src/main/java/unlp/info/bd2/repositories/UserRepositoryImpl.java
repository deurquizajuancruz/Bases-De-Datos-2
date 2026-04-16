package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.User;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

}

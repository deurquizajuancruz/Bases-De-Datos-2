package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.Stop;

public class StopRepositoryImpl extends BaseRepositoryImpl<Stop> implements StopRepository {

    public StopRepositoryImpl(SessionFactory sessionFactory) {
        super(Stop.class, sessionFactory);
    }

}

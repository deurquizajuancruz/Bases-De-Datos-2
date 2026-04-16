package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.Service;

public class ServiceRepositoryImpl extends BaseRepositoryImpl<Service> implements ServiceRepository {

    public ServiceRepositoryImpl(SessionFactory sessionFactory) {
        super(Service.class, sessionFactory);
    }
}

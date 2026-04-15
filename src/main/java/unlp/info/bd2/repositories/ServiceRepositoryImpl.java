package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.SessionFactory;
import unlp.info.bd2.model.Service;

public class ServiceRepositoryImpl implements ServiceRepository {
    private SessionFactory sessionFactory;

    public ServiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Service saveService(Service service) {
        this.sessionFactory.getCurrentSession().merge(service);
        return service;
    }

    @Override
    public Service findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Service.class, id);
    }

    @Override
    public List<Service> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Supplier", Service.class).getResultList();
    }

    @Override
    public void delete(Service service) {
        this.sessionFactory.getCurrentSession().remove(service);
    }
}

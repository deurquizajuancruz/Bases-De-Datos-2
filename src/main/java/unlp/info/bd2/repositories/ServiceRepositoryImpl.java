package unlp.info.bd2.repositories;

import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.Service;

@Repository
public class ServiceRepositoryImpl extends BaseRepositoryImpl<Service> implements ServiceRepository {

    public ServiceRepositoryImpl(SessionFactory sessionFactory) {
        super(Service.class, sessionFactory);
    }

    @Override
    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id) {
        String hql = "FROM Service s WHERE name = :nameS AND s.supplier.id = :supplierId";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Service.class)
                .setParameter("nameS", name)
                .setParameter("supplierId", id).uniqueResultOptional();
    }

    @Override
    public Service getMostDemandedService() {
        String hql = "SELECT i.service FROM ItemService i "
                + "GROUP BY i.service "
                + "ORDER BY SUM(i.quantity) DESC";

        return this.sessionFactory.getCurrentSession()
                .createQuery(hql, Service.class)
                .setMaxResults(1)
                .uniqueResult();
    }
}

package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.Supplier;

@Repository
public class SupplierRepositoryImpl extends BaseRepositoryImpl<Supplier> implements SupplierRepository {

    public SupplierRepositoryImpl(SessionFactory sessionFactory) {
        super(Supplier.class, sessionFactory);
    }

    @Override
    public Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber) {
        String hql = "FROM Supplier s WHERE s.authorizationNumber = :authNum";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Supplier.class).setParameter("authNum", authorizationNumber).uniqueResultOptional();
    }

    @Override
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        String hql = "SELECT s FROM ItemService i "
                + "JOIN i.service serv "
                + "JOIN serv.supplier s "
                + "GROUP BY s "
                + "ORDER BY COUNT(i) DESC";
        
        return this.sessionFactory.getCurrentSession().createQuery(hql, Supplier.class)
            .setMaxResults(n).getResultList();
    }

}

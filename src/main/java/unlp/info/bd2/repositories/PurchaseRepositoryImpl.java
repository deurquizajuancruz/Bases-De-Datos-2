package unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.model.Route;

@Repository
public class PurchaseRepositoryImpl extends BaseRepositoryImpl<Purchase> implements PurchaseRepository {

    public PurchaseRepositoryImpl(SessionFactory sessionFactory) {
        super(Purchase.class, sessionFactory);
    }

    @Override
    public boolean getAvailabilty(Route route, Date date) {
        String hql = "SELECT COUNT(p) FROM Purchase p WHERE p.route = :route AND p.date = :date";
        int count = this.sessionFactory.getCurrentSession().createQuery(hql, Long.class)
                .setParameter("route", route)
                .setParameter("date", date)
                .uniqueResult().intValue();
        return count < route.getMaxNumberUsers();
    }

    @Override
    public Optional<Purchase> getPurchaseByCode(String code) {
        String hql = "FROM Purchase p WHERE p.code = :codeP";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Purchase.class)
                .setParameter("codeP", code).uniqueResultOptional();
    }

    @Override
    public boolean hasPurchases(Long id) {
        String hql = "SELECT COUNT(p) FROM Purchase p WHERE p.route.id = :idR";
        int count = this.sessionFactory.getCurrentSession().createQuery(hql, Long.class)
                .setParameter("idR", id).uniqueResult().intValue();
        return count > 0;
    }

    @Override
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        String hql = "FROM Purchase p WHERE p.user.username = :uname";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Purchase.class)
                .setParameter("uname", username).getResultList();
    }

    @Override
    public long getCountOfPurchasesBetweenDates(Date start, Date end) {
        String hql = "SELECT COUNT(p) FROM Purchase p WHERE p.date >= :start AND p.date <= :end";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Long.class)
                .setParameter("start", start).setParameter("end", end)
                .uniqueResult();
    }

}

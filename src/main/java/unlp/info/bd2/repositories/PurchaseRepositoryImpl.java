package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.SessionFactory;
import unlp.info.bd2.model.Purchase;

public class PurchaseRepositoryImpl implements PurchaseRepository {
    private SessionFactory sessionFactory;

    public PurchaseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        this.sessionFactory.getCurrentSession().merge(purchase);
        return purchase;
    }

    @Override
    public Purchase findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Purchase.class, id);
    }

    @Override
    public List<Purchase> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Purchase", Purchase.class).getResultList();
    }

    @Override
    public void delete(Purchase purchase) {
        this.sessionFactory.getCurrentSession().remove(purchase);
    }

}

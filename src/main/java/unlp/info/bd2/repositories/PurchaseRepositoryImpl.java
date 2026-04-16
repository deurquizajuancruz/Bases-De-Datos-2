package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.Purchase;

public class PurchaseRepositoryImpl implements PurchaseRepository {
    private SessionFactory sessionFactory;

    public PurchaseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Purchase> savePurchase(Purchase purchase) {
        return Optional.ofNullable(this.sessionFactory.getCurrentSession().merge(purchase));
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

package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.Purchase;

public class PurchaseRepositoryImpl extends BaseRepositoryImpl<Purchase> implements PurchaseRepository {

    public PurchaseRepositoryImpl(SessionFactory sessionFactory) {
        super(Purchase.class, sessionFactory);
    }

}

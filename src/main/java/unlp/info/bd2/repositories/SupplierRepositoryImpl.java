package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.Supplier;

public class SupplierRepositoryImpl extends BaseRepositoryImpl<Supplier> implements SupplierRepository{

    public SupplierRepositoryImpl(SessionFactory sessionFactory) {
        super(Supplier.class, sessionFactory);
    }

}

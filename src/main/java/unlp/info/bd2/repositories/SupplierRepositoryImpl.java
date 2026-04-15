package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.SessionFactory;
import unlp.info.bd2.model.Supplier;

public class SupplierRepositoryImpl implements SupplierRepository{
    private SessionFactory sessionFactory;

    public SupplierRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        this.sessionFactory.getCurrentSession().merge(supplier);
        return supplier;
    }

    @Override
    public Supplier findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Supplier.class, id);
    }

    @Override
    public List<Supplier> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Supplier", Supplier.class).getResultList();
    }

    @Override
    public void delete(Supplier supplier) {
        this.sessionFactory.getCurrentSession().remove(supplier);
    }
    
}

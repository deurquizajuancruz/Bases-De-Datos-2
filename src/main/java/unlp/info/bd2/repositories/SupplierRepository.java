package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Supplier;
import java.util.List;

public interface SupplierRepository {
    public Supplier saveSupplier(Supplier supplier);

    public Supplier findById(Long id);

    public List<Supplier> findAll();

    public void delete(Supplier supplier);
}

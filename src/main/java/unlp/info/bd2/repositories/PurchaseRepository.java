package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Purchase;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    public Optional<Purchase> savePurchase(Purchase purchase);

    public Purchase findById(Long id);

    public List<Purchase> findAll();

    public void delete(Purchase purchase);
}

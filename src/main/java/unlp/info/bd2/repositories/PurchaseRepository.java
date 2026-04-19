package unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.model.Route;

public interface PurchaseRepository extends BaseRepository<Purchase> {

    public boolean getAvailabilty(Route route, Date date);
    
    public Optional<Purchase> getPurchaseByCode(String code);

    public boolean hasPurchases(Long id);

    public List<Purchase> getAllPurchasesOfUsername(String username);

    public long getCountOfPurchasesBetweenDates(Date start, Date end);
}

package unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.model.Route;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    public Optional<Purchase> findByCode(String code);

    public List<Purchase> findByUserUsername(String username);

    public long countByDateBetween(Date start, Date end);

    public boolean existsByRouteId(Long idR);

    public long countByRouteAndDate(Route route, Date date);
}

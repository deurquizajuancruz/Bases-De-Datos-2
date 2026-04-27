package unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.model.Route;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    public Optional<Purchase> findByCode(String code);

    public List<Purchase> findByUserUsername(String username);

    public long countByDateBetween(Date start, Date end);

    public boolean existsByRouteId(Long idR);

    @Query("SELECT COUNT(p) FROM Purchase p WHERE p.route = :route AND p.date = :date")
    public long countByRouteAndDate(@Param("route") Route route, @Param("date") Date date);
}

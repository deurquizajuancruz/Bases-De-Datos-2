package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unlp.info.bd2.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    public Optional<Supplier> findByAuthorizationNumber(String authorizationNumber);

    @Query("SELECT s FROM ItemService i JOIN i.service serv JOIN serv.supplier s GROUP BY s ORDER BY COUNT(i) DESC")
    public List<Supplier> findTopSuppliers(Pageable pageable);
}

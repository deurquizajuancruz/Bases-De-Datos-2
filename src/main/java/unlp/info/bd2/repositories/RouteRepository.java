package unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unlp.info.bd2.dto.RouteDTO;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

public interface RouteRepository extends CrudRepository<Route, Long> {

    public List<Route> findByPriceLessThan(float price);

    @Query("FROM Route r WHERE :stop MEMBER OF r.stops")
    public List<Route> getRoutesWithStop(@Param("stop") Stop stop);

    @Query("SELECT MAX(SIZE(r.stops)) FROM Route r")
    public Long getMaxStopOfRoutes();

    @Query("FROM Route r WHERE r NOT IN (SELECT p.route FROM Purchase p)")
    public List<Route> getRoutsNotSell();

    @Query("SELECT p.route FROM Purchase p WHERE p.review IS NOT NULL GROUP BY p.route ORDER BY AVG(p.review.rating) DESC")
    public List<Route> getTop3RoutesWithMaxRating(Pageable pageable);

    @Query("SELECT new unlp.info.bd2.dto.RouteDTO(r.name, COUNT(p), AVG(p.totalPrice)) " +
            "FROM Route r LEFT JOIN Purchase p ON p.route = r " +
            "GROUP BY r.name")
    public List<RouteDTO> getRouteSummary();
}

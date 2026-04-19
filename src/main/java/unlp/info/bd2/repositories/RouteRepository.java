package unlp.info.bd2.repositories;

import java.util.List;

import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

public interface RouteRepository extends BaseRepository<Route> {

    public List<Route> getRoutesBelowPrice(float price);

    public List<Route> getRoutesWithStop(Stop stop);

    public Long getMaxStopOfRoutes();

    public List<Route> getRoutsNotSell();

    public List<Route> getTop3RoutesWithMaxRating();
}

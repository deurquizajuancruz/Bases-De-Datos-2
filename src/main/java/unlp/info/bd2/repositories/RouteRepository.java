package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Route;
import java.util.List;

public interface RouteRepository {
    public Route saveRoute(Route route);

    public Route findById(Long id);

    public List<Route> findAll();

    public void delete(Route route);
}

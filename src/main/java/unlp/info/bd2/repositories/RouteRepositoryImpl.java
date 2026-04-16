package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;

import unlp.info.bd2.model.Route;

public class RouteRepositoryImpl extends BaseRepositoryImpl<Route> implements RouteRepository {

    public RouteRepositoryImpl(SessionFactory sessionFactory) {
        super(Route.class, sessionFactory);
    }

}

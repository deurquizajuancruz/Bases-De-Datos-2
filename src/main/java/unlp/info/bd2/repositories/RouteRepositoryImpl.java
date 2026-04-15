package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.SessionFactory;
import unlp.info.bd2.model.Route;

public class RouteRepositoryImpl implements RouteRepository {
    private SessionFactory sessionFactory;

    public RouteRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Route saveRoute(Route route) {
        this.sessionFactory.getCurrentSession().merge(route);
        return route;
    }

    @Override
    public Route findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Route.class, id);
    }

    @Override
    public List<Route> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Supplier", Route.class).getResultList();
    }

    @Override
    public void delete(Route route) {
        this.sessionFactory.getCurrentSession().remove(route);
    }

}

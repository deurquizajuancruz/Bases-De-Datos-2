package unlp.info.bd2.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

@Repository
public class RouteRepositoryImpl extends BaseRepositoryImpl<Route> implements RouteRepository {

    public RouteRepositoryImpl(SessionFactory sessionFactory) {
        super(Route.class, sessionFactory);
    }

    @Override
    public List<Route> getRoutesBelowPrice(float price) {
        String hql = "FROM Route r WHERE r.price < belowPrice";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Route.class).setParameter("belowPrice", price).getResultList();
    }

    @Override
    public List<Route> getRoutesWithStop(Stop stop) {
        String hql = "FROM Route r WHERE :stop MEMBER OF r.stops";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Route.class)
                .setParameter("stop", stop).getResultList();
    }

    @Override
    public Long getMaxStopOfRoutes() {
        String hql = "SELECT MAX(SIZE(r.stops)) FROM Route r";

        Integer result = this.sessionFactory.getCurrentSession()
                .createQuery(hql, Integer.class)
                .uniqueResult();

        return result != null ? result.longValue() : 0L;
    }

    @Override
    public List<Route> getRoutsNotSell() {
        String hql = "FROM Route r WHERE r NOT IN (SELECT p.route FROM Purchase p)";

        return this.sessionFactory.getCurrentSession()
                .createQuery(hql, Route.class)
                .getResultList();
    }

    @Override
    public List<Route> getTop3RoutesWithMaxRating() {
        String hql = "SELECT p.route FROM Purchase p WHERE p.review IS NOT NULL GROUP BY p.route ORDER BY AVG(p.review.rating) DESC";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Route.class).setMaxResults(3).getResultList();
    }

}

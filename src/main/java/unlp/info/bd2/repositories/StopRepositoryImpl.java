package unlp.info.bd2.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.Stop;

@Repository
public class StopRepositoryImpl extends BaseRepositoryImpl<Stop> implements StopRepository {

    public StopRepositoryImpl(SessionFactory sessionFactory) {
        super(Stop.class, sessionFactory);
    }

    @Override
    public List<Stop> getStopByName(String name) {
        String hql = "FROM Stop s WHERE name LIKE :name";
        return this.sessionFactory.getCurrentSession().createQuery(hql, Stop.class).setParameter("name", name + "%").getResultList();
    }

}

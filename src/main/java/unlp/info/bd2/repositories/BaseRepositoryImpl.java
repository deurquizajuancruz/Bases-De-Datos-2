package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {
    protected SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T save(T entity) {
        return this.sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(this.sessionFactory.getCurrentSession().get(this.entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from " + this.entityClass.getName(), this.entityClass).getResultList();
    }

    @Override
    public void delete(T entity) {
        this.sessionFactory.getCurrentSession().remove(entity);
    }
}

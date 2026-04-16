package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    public T save(T entity);

    public Optional<T> findById(Long id);

    public List<T> findAll();

    public void delete(T entity);
}
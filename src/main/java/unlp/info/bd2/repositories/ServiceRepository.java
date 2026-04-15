package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Service;
import java.util.List;

public interface ServiceRepository {
    public Service saveService(Service service);

    public Service findById(Long id);

    public List<Service> findAll();

    public void delete(Service service);
}

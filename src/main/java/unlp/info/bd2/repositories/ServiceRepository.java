package unlp.info.bd2.repositories;

import java.util.Optional;

import unlp.info.bd2.model.Service;

public interface ServiceRepository extends BaseRepository<Service>{

    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id);

    public Service getMostDemandedService();
}

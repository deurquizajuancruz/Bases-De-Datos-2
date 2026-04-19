package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import unlp.info.bd2.model.ItemService;

@Repository
public class ItemServiceRepositoryImpl extends BaseRepositoryImpl<ItemService> implements ItemServiceRepository{

    public ItemServiceRepositoryImpl(Class<ItemService> entityClass, SessionFactory sessionFactory) {
        super(entityClass, sessionFactory);
    }

}
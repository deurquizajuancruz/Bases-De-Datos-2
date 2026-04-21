package unlp.info.bd2.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import unlp.info.bd2.repositories.*;
import unlp.info.bd2.services.*;

@Configuration
public class AppConfig {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public PurchaseRepository purchaseRepository() {
        return new PurchaseRepositoryImpl(sessionFactory);
    }

    @Bean
    public SupplierRepository supplierRepository() {
        return new SupplierRepositoryImpl(sessionFactory);
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new ReviewRepositoryImpl(sessionFactory);
    }

    @Bean
    public RouteRepository routeRepository() {
        return new RouteRepositoryImpl(sessionFactory);
    }

    @Bean
    public ServiceRepository serviceRepository() {
        return new ServiceRepositoryImpl(sessionFactory);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(sessionFactory);
    }

    @Bean
    public StopRepository stopRepository() {
        return new StopRepositoryImpl(sessionFactory);
    }

    @Bean
    public ItemServiceRepository itemServiceRepository() {
        return new ItemServiceRepositoryImpl(sessionFactory);
    }

    @Bean
    @Primary
    public ToursService createService() {
        return new ToursServiceImpl(
                this.purchaseRepository(),
                this.supplierRepository(),
                this.reviewRepository(),
                this.routeRepository(),
                this.serviceRepository(),
                this.userRepository(),
                this.stopRepository(),
                this.itemServiceRepository());
    }
}
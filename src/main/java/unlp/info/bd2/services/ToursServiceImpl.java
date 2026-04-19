package unlp.info.bd2.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import unlp.info.bd2.model.DriverUser;
import unlp.info.bd2.model.ItemService;
import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.model.Review;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Service;
import unlp.info.bd2.model.Stop;
import unlp.info.bd2.model.Supplier;
import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;
import unlp.info.bd2.repositories.ItemServiceRepository;
import unlp.info.bd2.repositories.PurchaseRepository;
import unlp.info.bd2.repositories.ReviewRepository;
import unlp.info.bd2.repositories.RouteRepository;
import unlp.info.bd2.repositories.ServiceRepository;
import unlp.info.bd2.repositories.StopRepository;
import unlp.info.bd2.repositories.SupplierRepository;
import unlp.info.bd2.repositories.UserRepository;
import unlp.info.bd2.utils.ToursException;

public class ToursServiceImpl implements ToursService {

    private final PurchaseRepository purchaseRepository;
    private final ReviewRepository reviewRepository;
    private final RouteRepository routeRepository;
    private final ServiceRepository serviceRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final StopRepository stopRepository;
    private final ItemServiceRepository itemServiceRepository;

    public ToursServiceImpl(PurchaseRepository purchaseRepository, SupplierRepository supplierRepository,
            ReviewRepository reviewRepository, RouteRepository routeRepository, ServiceRepository serviceRepository,
            UserRepository userRepository, StopRepository stopRepository, ItemServiceRepository itemServiceRepository) {
        this.purchaseRepository = purchaseRepository;
        this.reviewRepository = reviewRepository;
        this.routeRepository = routeRepository;
        this.serviceRepository = serviceRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
        this.stopRepository = stopRepository;
        this.itemServiceRepository = itemServiceRepository;
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String fullName, String email, Date birthdate,
            String phoneNumber) {
        User user = new User(username, password, fullName, email, birthdate, phoneNumber);
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public DriverUser createDriverUser(String username, String password, String fullName, String email, Date birthdate,
            String phoneNumber, String expedient) {
        DriverUser driverUser = new DriverUser(username, password, fullName, email, birthdate, phoneNumber, expedient);
        return (DriverUser) this.userRepository.save(driverUser);
    }

    @Override
    @Transactional
    public TourGuideUser createTourGuideUser(String username, String password, String fullName, String email,
            Date birthdate, String phoneNumber, String education) {
        TourGuideUser tourGuideUser = new TourGuideUser(username, password, fullName, email, birthdate, phoneNumber, education);
        return (TourGuideUser) this.userRepository.save(tourGuideUser);
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public User updateUser(User user) throws ToursException {
        if (user.getId() == null || userRepository.findById(user.getId()).isEmpty()) {
            throw new ToursException("No se puede actualizar: El usuario no existe.");
        }
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    @Transactional
    public Stop createStop(String name, String description) {
        Stop stop = new Stop(name, description);
        return this.stopRepository.save(stop);
    }

    @Override
    @Transactional
    public List<Stop> getStopByNameStart(String name) {
        return this.stopRepository.getStopByName(name);
    }

    @Override
    @Transactional
    public Route createRoute(String name, float price, float totalKm, int maxNumberOfUsers, List<Stop> stops) {
        Route route = new Route(name, price, totalKm, maxNumberOfUsers, stops);
        return this.routeRepository.save(route);
    }

    @Override
    @Transactional
    public Optional<Route> getRouteById(Long id) {
        return this.routeRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Route> getRoutesBelowPrice(float price) {
        return this.routeRepository.getRoutesBelowPrice(price);
    }

    @Override
    @Transactional
    public void assignDriverByUsername(String username, Long idRoute) throws ToursException {
        DriverUser driver = (DriverUser) this.userRepository.getUserByUsername(username)
                .filter(u -> u instanceof DriverUser)
                .orElseThrow(() -> new ToursException("Driver with username" + username + " does not exist"));
        Route route = this.routeRepository.findById(idRoute).orElseThrow(() -> new ToursException("Route with id" + idRoute + " does not exist"));

        if (!route.getDriverList().contains(driver)) {
            route.addDriver(driver);
            this.routeRepository.save(route);
        }
    }

    @Override
    @Transactional
    public void assignTourGuideByUsername(String username, Long idRoute) throws ToursException {
        TourGuideUser tourGuide = (TourGuideUser) this.userRepository.getUserByUsername(username)
                .filter(u -> u instanceof TourGuideUser)
                .orElseThrow(() -> new ToursException("TourGuide with username " + username + " does not exist"));
        Route route = this.routeRepository.findById(idRoute).orElseThrow(() -> new ToursException("Route with id" + idRoute + " does not exist"));

        if (!route.getTourGuideList().contains(tourGuide)) {
            route.addTourGuide(tourGuide);
            this.routeRepository.save(route);
        }
    }

    @Override
    @Transactional
    public Supplier createSupplier(String businessName, String authorizationNumber) {
        Supplier supplier = new Supplier(businessName, authorizationNumber);
        return supplierRepository.save(supplier);
    }

    @Override
    @Transactional
    public Service addServiceToSupplier(String name, float price, String description, Supplier supplier) throws ToursException {
        Supplier foundSupplier = this.supplierRepository.findById(supplier.getId()).orElseThrow(() -> new ToursException("Supplier was not found"));
        boolean exists = foundSupplier.getServices().stream().anyMatch(s -> s.getName().equals(name));

        if (exists) {
            throw new ToursException("Supplier already has service with name " + name);
        }

        Service service = new Service(name, price, description, foundSupplier);

        foundSupplier.getServices().add(service);
        return this.serviceRepository.save(service);
    }

    @Override
    @Transactional
    public Service updateServicePriceById(Long id, float newPrice) throws ToursException {
        Service foundService = this.serviceRepository.findById(id).orElseThrow(() -> new ToursException("No service found with id " + id));
        foundService.setPrice(newPrice);
        return this.serviceRepository.save(foundService);
    }

    @Override
    @Transactional
    public Optional<Supplier> getSupplierById(Long id) {
        return this.supplierRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber) {
        return this.supplierRepository.getSupplierByAuthorizationNumber(authorizationNumber);
    }

    @Override
    @Transactional
    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id) throws ToursException {
        return this.serviceRepository.getServiceByNameAndSupplierId(name, id);
    }

    @Override
    @Transactional
    public Purchase createPurchase(String code, Route route, User user) throws ToursException {
        return this.createPurchase(code, new Date(), route, user);
    }

    @Override
    @Transactional
    public Purchase createPurchase(String code, Date date, Route route, User user) throws ToursException {
        if (!this.purchaseRepository.getAvailabilty(route, date)) {
            throw new ToursException("There's no availabilty for the route in the date specified");
        }

        Purchase purchase = new Purchase(code, date, user, route);

        return this.purchaseRepository.save(purchase);
    }

    @Override
    @Transactional
    public ItemService addItemToPurchase(Service service, int quantity, Purchase purchase) {
        ItemService itemService = new ItemService(service, quantity, purchase);
        return this.itemServiceRepository.save(itemService);
    }

    @Override
    @Transactional
    public Optional<Purchase> getPurchaseByCode(String code) {
        return this.purchaseRepository.getPurchaseByCode(code);
    }

    @Override
    @Transactional
    public void deletePurchase(Purchase purchase) {
        this.purchaseRepository.delete(purchase);
    }

    @Override
    @Transactional
    public Review addReviewToPurchase(int rating, String comment, Purchase purchase) throws ToursException {
        if (purchase.getReview() != null) {
            throw new ToursException("This purchase already has a review");
        }
        Review review = new Review(rating, comment, purchase);
        return this.reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void deleteRoute(Route route) throws ToursException {
        if (this.purchaseRepository.hasPurchases(route.getId())) {
            throw new ToursException("You cannot delete a route with associated purchases");
        }
        this.routeRepository.delete(route);
    }

    @Override
    @Transactional
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        return this.purchaseRepository.getAllPurchasesOfUsername(username);
        // User user = this.userRepository.getUserByUsername(username).orElseThrow(() -> new ToursException("Username " + username + " was not found"));
        // return user.getPurchaseList();
    }

    @Override
    @Transactional
    public List<User> getUserSpendingMoreThan(float mount) {
        return this.userRepository.getUserSpendingMoreThan(mount);
    }

    @Override
    @Transactional
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        return this.supplierRepository.getTopNSuppliersInPurchases(n);
    }

    @Override
    @Transactional
    public long getCountOfPurchasesBetweenDates(Date start, Date end) {
        return this.purchaseRepository.getCountOfPurchasesBetweenDates(start, end);
    }

    @Override
    @Transactional
    public List<Route> getRoutesWithStop(Stop stop) {
        return this.routeRepository.getRoutesWithStop(stop);
    }

    @Override
    @Transactional
    public Long getMaxStopOfRoutes() {
        return this.routeRepository.getMaxStopOfRoutes();
    }

    @Override
    @Transactional
    public List<Route> getRoutsNotSell() {
        return this.routeRepository.getRoutsNotSell();
    }

    @Override
    @Transactional
    public List<Route> getTop3RoutesWithMaxRating() {
        return this.routeRepository.getTop3RoutesWithMaxRating();
    }

    @Override
    @Transactional
    public Service getMostDemandedService() {
        return this.serviceRepository.getMostDemandedService();
    }

    @Override
    @Transactional
    public List<TourGuideUser> getTourGuidesWithRating1() {
        return this.userRepository.getTourGuidesWithRating1();
    }
}

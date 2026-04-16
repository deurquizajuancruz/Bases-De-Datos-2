package unlp.info.bd2.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import unlp.info.bd2.model.*;
import unlp.info.bd2.repositories.*;
import unlp.info.bd2.utils.ToursException;

public class ToursServiceImpl implements ToursService {
    private PurchaseRepository purchaseRepository;
    private ReviewRepository reviewRepository;
    private RouteRepository routeRepository;
    private ServiceRepository serviceRepository;
    private SupplierRepository supplierRepository;
    private UserRepository userRepository;

    public ToursServiceImpl(PurchaseRepository purchaseRepository, SupplierRepository supplierRepository,
            ReviewRepository reviewRepository, RouteRepository routeRepository, ServiceRepository serviceRepository,
            UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.reviewRepository = reviewRepository;
        this.routeRepository = routeRepository;
        this.serviceRepository = serviceRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String fullName, String email, Date birthdate,
            String phoneNumber) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(fullName);
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setPhoneNumber(phoneNumber);
        return userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public DriverUser createDriverUser(String username, String password, String fullName, String email, Date birthdate,
            String phoneNumber, String expedient) {
        DriverUser driverUser = new DriverUser();
        driverUser.setUsername(username);
        driverUser.setPassword(password);
        driverUser.setName(fullName);
        driverUser.setEmail(email);
        driverUser.setBirthdate(birthdate);
        driverUser.setPhoneNumber(phoneNumber);
        driverUser.setExpedient(expedient);
        return (DriverUser) userRepository.saveUser(driverUser);
    }

    @Override
    @Transactional
    public TourGuideUser createTourGuideUser(String username, String password, String fullName, String email,
            Date birthdate, String phoneNumber, String education) {
        TourGuideUser tourGuideUser = new TourGuideUser();
        tourGuideUser.setUsername(username);
        tourGuideUser.setPassword(password);
        tourGuideUser.setName(fullName);
        tourGuideUser.setEmail(email);
        tourGuideUser.setBirthdate(birthdate);
        tourGuideUser.setPhoneNumber(phoneNumber);
        tourGuideUser.setEducation(education);
        return (TourGuideUser) userRepository.saveUser(tourGuideUser);
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUsername'");
    }

    @Override
    public User updateUser(User user) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(User user) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public Stop createStop(String name, String description) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStop'");
    }

    @Override
    public List<Stop> getStopByNameStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStopByNameStart'");
    }

    @Override
    public Route createRoute(String name, float price, float totalKm, int maxNumberOfUsers, List<Stop> stops)
            throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRoute'");
    }

    @Override
    public Optional<Route> getRouteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRouteById'");
    }

    @Override
    public List<Route> getRoutesBelowPrice(float price) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoutesBelowPrice'");
    }

    @Override
    public void assignDriverByUsername(String username, Long idRoute) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignDriverByUsername'");
    }

    @Override
    public void assignTourGuideByUsername(String username, Long idRoute) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignTourGuideByUsername'");
    }

    @Override
    @Transactional
    public Supplier createSupplier(String businessName, String authorizationNumber) {
        Supplier supplier = new Supplier();
        supplier.setAuthorizationNumber(authorizationNumber);
        supplier.setBusinessName(businessName);
        return supplierRepository.saveSupplier(supplier);
    }

    @Override
    public Service addServiceToSupplier(String name, float price, String description, Supplier supplier)
            throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addServiceToSupplier'");
    }

    @Override
    public Service updateServicePriceById(Long id, float newPrice) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateServicePriceById'");
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSupplierById'");
    }

    @Override
    public Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSupplierByAuthorizationNumber'");
    }

    @Override
    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getServiceByNameAndSupplierId'");
    }

    @Override
    public Purchase createPurchase(String code, Route route, User user) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPurchase'");
    }

    @Override
    public Purchase createPurchase(String code, Date date, Route route, User user) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPurchase'");
    }

    @Override
    public ItemService addItemToPurchase(Service service, int quantity, Purchase purchase) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToPurchase'");
    }

    @Override
    public Optional<Purchase> getPurchaseByCode(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPurchaseByCode'");
    }

    @Override
    public void deletePurchase(Purchase purchase) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePurchase'");
    }

    @Override
    public Review addReviewToPurchase(int rating, String comment, Purchase purchase) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReviewToPurchase'");
    }

    @Override
    public void deleteRoute(Route route) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRoute'");
    }

    @Override
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPurchasesOfUsername'");
    }

    @Override
    public List<User> getUserSpendingMoreThan(float mount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserSpendingMoreThan'");
    }

    @Override
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopNSuppliersInPurchases'");
    }

    @Override
    public long getCountOfPurchasesBetweenDates(Date start, Date end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCountOfPurchasesBetweenDates'");
    }

    @Override
    public List<Route> getRoutesWithStop(Stop stop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoutesWithStop'");
    }

    @Override
    public Long getMaxStopOfRoutes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxStopOfRoutes'");
    }

    @Override
    public List<Route> getRoutsNotSell() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoutsNotSell'");
    }

    @Override
    public List<Route> getTop3RoutesWithMaxRating() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTop3RoutesWithMaxRating'");
    }

    @Override
    public Service getMostDemandedService() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMostDemandedService'");
    }

    @Override
    public List<TourGuideUser> getTourGuidesWithRating1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTourGuidesWithRating1'");
    }
}

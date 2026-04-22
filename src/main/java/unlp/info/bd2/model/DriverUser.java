package unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("Driver")
public class DriverUser extends User {

    @Column(nullable = true, length = 50)
    private String expedient;

    @ManyToMany(mappedBy = "driverList", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private List<Route> routes;

    public DriverUser() {

    }

    public DriverUser(String username, String password, String fullName, String email, Date birthdate,
            String phoneNumber, String expedient) {
        super(username, password, fullName, email, birthdate, phoneNumber);
        this.expedient = expedient;
        this.routes = new ArrayList<>();
    }

    public String getExpedient() {
        return expedient;
    }

    public void setExpedient(String expedient) {
        this.expedient = expedient;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routs) {
        this.routes = routs;
    }

    @Override
    public boolean canBeDeleted() {
        return this.routes.isEmpty();
    }
}

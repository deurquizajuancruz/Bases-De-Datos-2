package unlp.info.bd2.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Driver")
public class DriverUser extends User {

    @Column(nullable = false, length = 50)
    private String expedient;

    @ManyToMany(mappedBy = "driverList", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private List<Route> routes;

    public String getExpedient() {
        return expedient;
    }

    public void setExpedient(String expedient) {
        this.expedient = expedient;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRouts(List<Route> routs) {
        this.routes = routs;
    }
}

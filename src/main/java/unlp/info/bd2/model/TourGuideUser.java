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
@DiscriminatorValue("TourGuide")
public class TourGuideUser extends User {

    @Column(nullable = true)
    private String education;

    @ManyToMany(mappedBy = "tourGuideList", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    private List<Route> routes;

    public TourGuideUser() {

    }

    public TourGuideUser(String username, String password, String fullName, String email, Date birthdate,
            String phoneNumber, String education) {
        super(username, password, fullName, email, birthdate, phoneNumber);
        this.education = education;
        this.routes = new ArrayList<>();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean canBeDeleted() {
        return this.routes.isEmpty();
    }

}

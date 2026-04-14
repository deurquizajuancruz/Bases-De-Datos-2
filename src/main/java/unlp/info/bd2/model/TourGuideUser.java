package unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TourGuide")
public class TourGuideUser extends User {

    @Column(nullable = false)
    private String education;

    @ManyToMany(mappedBy = "tourGuideList", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    private List<Route> routes;

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

}

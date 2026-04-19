package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;

public interface UserRepository extends BaseRepository<User>{

    public Optional<User> getUserByUsername(String username);

    public List<User> getUserSpendingMoreThan(float mount);

    public List<TourGuideUser> getTourGuidesWithRating1();
}

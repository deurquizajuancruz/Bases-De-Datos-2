package unlp.info.bd2.repositories;

import java.util.Optional;

import unlp.info.bd2.model.User;

public interface UserRepository extends BaseRepository<User>{

    public Optional<User> getUserByUsername(String username);

}

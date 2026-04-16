package unlp.info.bd2.repositories;

import unlp.info.bd2.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public User saveUser(User user);

    public Optional<User> findById(Long id);

    public List<User> findAll();

    public void delete(User user);
}

package unlp.info.bd2.repositories;

import unlp.info.bd2.model.User;
import java.util.List;

public interface UserRepository {
    public User saveUser(User user);

    public User findById(Long id);

    public List<User> findAll();

    public void delete(User user);
}

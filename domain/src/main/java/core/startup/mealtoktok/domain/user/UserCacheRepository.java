package core.startup.mealtoktok.domain.user;

import java.util.Optional;

public interface UserCacheRepository {

    void cache(User user);

    Optional<User> getUser(UserId userId);

    void deleteUser(User user);
}

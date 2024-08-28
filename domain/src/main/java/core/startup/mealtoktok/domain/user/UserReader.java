package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;
    private final UserCacheManager userCacheManager;
    private final UserValidator userValidator;

    public User read(UserId userId) {
        User user =
                userCacheManager
                        .read(userId)
                        .orElseGet(
                                () -> {
                                    User findUser = userRepository.findById(userId);
                                    userCacheManager.cache(findUser);
                                    return findUser;
                                });
        userValidator.validate(user);
        return user;
    }

    public User read(String oid) {
        return userRepository.findByOAuthId(oid);
    }
}

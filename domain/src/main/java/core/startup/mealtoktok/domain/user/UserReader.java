package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;
    private final UserCacheManager userCacheManager;
    private final UserValidator userValidator;

    public User read(TargetUser targetUser) {
        User user =
                userCacheManager
                        .read(targetUser)
                        .orElseGet(
                                () -> {
                                    User findUser = userRepository.findById(targetUser);
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

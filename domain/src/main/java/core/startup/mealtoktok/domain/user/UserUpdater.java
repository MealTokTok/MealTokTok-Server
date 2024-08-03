package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;
    private final UserCacheManager userCacheManager;

    public TargetUser oAuthUpdate(User user, UserProfile userProfile, String deviceToken) {
        user.update(userProfile, deviceToken);
        return updateAndRefreshCache(user);
    }

    public TargetUser addDeliveryAddress(User user, DeliveryAddress deliveryAddress) {
        user.addDeliveryAddress(deliveryAddress);
        return updateAndRefreshCache(user);
    }

    public void removeDeliveryAddress(User user, TargetDeliveryAddress targetDeliveryAddress) {
        user.removeDeliveryAddress(targetDeliveryAddress);
        updateAndRefreshCache(user);
    }

    public TargetUser updateEmail(User user, String email) {
        user.updateEmail(email);
        return updateAndRefreshCache(user);
    }

    public TargetUser updateNickname(User user, String nickname) {
        user.updateNickname(nickname);
        return updateAndRefreshCache(user);
    }

    private TargetUser updateAndRefreshCache(User user) {
        User updatedUser = userRepository.update(user);
        userCacheManager.refresh(updatedUser);
        return TargetUser.from(updatedUser.getUserId());
    }
}

package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;
    private final UserCacheManager userCacheManager;

    public TargetUser update(User user, UserProfile userProfile, String deviceToken) {
        user.update(userProfile, deviceToken);
        return userRepository.save(user);
    }

    public TargetUser addDeliveryAddress(User user, DeliveryAddress deliveryAddress) {
        user.addDeliveryAddress(deliveryAddress);
        userCacheManager.refresh(user);
        return userRepository.save(user);
    }
}

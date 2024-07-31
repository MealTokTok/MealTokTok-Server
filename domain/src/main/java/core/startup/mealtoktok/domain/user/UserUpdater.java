package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public TargetUser update(User user, UserProfile userProfile, String deviceToken) {
        user.update(userProfile, deviceToken);
        return userRepository.save(user);
    }

    public TargetUser addAddress(User user, DeliveryAddress deliveryAddress) {
        user.addAddress(deliveryAddress);
        return userRepository.save(user);
    }
}

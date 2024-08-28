package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public UserId oAuthUpdate(User user, UserProfile userProfile, String deviceToken) {
        user.update(userProfile, deviceToken);
        return userRepository.update(user);
    }

    public UserId addDeliveryAddress(User user, DeliveryAddress deliveryAddress) {
        user.addDeliveryAddress(deliveryAddress);
        return userRepository.update(user);
    }

    public UserId removeDeliveryAddress(User user, TargetDeliveryAddress targetDeliveryAddress) {
        user.removeDeliveryAddress(targetDeliveryAddress);
        return userRepository.update(user);
    }

    public UserId updateEmail(User user, String email) {
        user.updateEmail(email);
        return userRepository.update(user);
    }

    public UserId updateNickname(User user, String nickname) {
        user.updateNickname(nickname);
        return userRepository.update(user);
    }

    public UserId configureDeliveryAddress(User user, TargetDeliveryAddress targetDeliveryAddress) {
        user.configureDeliveryAddress(targetDeliveryAddress);
        return userRepository.update(user);
    }
}

package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public TargetUser oAuthUpdate(User user, UserProfile userProfile, String deviceToken) {
        user.update(userProfile, deviceToken);
        return userRepository.update(user);
    }

    public TargetUser addDeliveryAddress(User user, DeliveryAddress deliveryAddress) {
        user.addDeliveryAddress(deliveryAddress);
        return userRepository.update(user);
    }

    public TargetUser removeDeliveryAddress(
            User user, TargetDeliveryAddress targetDeliveryAddress) {
        user.removeDeliveryAddress(targetDeliveryAddress);
        return userRepository.update(user);
    }

    public TargetUser updateEmail(User user, String email) {
        user.updateEmail(email);
        return userRepository.update(user);
    }

    public TargetUser updateNickname(User user, String nickname) {
        user.updateNickname(nickname);
        return userRepository.update(user);
    }

    public TargetUser configureDeliveryAddress(
            User user, TargetDeliveryAddress targetDeliveryAddress) {
        user.configureDeliveryAddress(targetDeliveryAddress);
        return userRepository.update(user);
    }
}

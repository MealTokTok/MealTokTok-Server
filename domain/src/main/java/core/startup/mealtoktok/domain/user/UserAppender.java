package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthInfo;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final UserRepository userRepository;

    public TargetUser append(
            OAuthInfo oAuthInfo,
            String deviceToken,
            UserProfile userProfile,
            DeliveryAddress deliveryAddress) {
        return userRepository.save(oAuthInfo, deviceToken, userProfile, deliveryAddress);
    }
}

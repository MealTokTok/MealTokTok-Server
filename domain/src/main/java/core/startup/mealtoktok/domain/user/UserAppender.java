package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {

  private final UserRepository userRepository;

  public TargetUser append(OAuthInfo oAuthInfo, String deviceToken, UserProfile userProfile,
      DeliveryAddress deliveryAddress) {
    return userRepository.save(oAuthInfo, deviceToken, userProfile, deliveryAddress);
  }

}

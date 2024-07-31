package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;

public interface UserRepository {

  User findById(TargetUser targetUser);

  User findByOAuthId(String email);

  TargetUser save(OAuthInfo oAuthInfo, String deviceToken, UserProfile userProfile,
      DeliveryAddress deliveryAddress);

  TargetUser save(User user);

  boolean existsByOAuthInfo(OAuthInfo oAuthInfo);
}

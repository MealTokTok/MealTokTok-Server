package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthProfile;

import java.time.LocalDate;

public record UserProfile(
    String username,
    String nickname,
    Gender gender,
    String phoneNumber,
    LocalDate birth,
    String email,
    String profileImageUrl
) {

  public static UserProfile of(String username,
      String nickname,
      Gender gender,
      String phoneNumber,
      LocalDate birth,
      String email,
      String profileImageUrl) {
    return new UserProfile(username, nickname, gender, phoneNumber, birth, email, profileImageUrl);
  }

  public static UserProfile from(OAuthProfile oAuthProfile) {
    return new UserProfile(
        oAuthProfile.getName(),
        oAuthProfile.getNickname(),
        (oAuthProfile.getGender() != null) ?
            Gender.valueOf(oAuthProfile.getGender().toUpperCase())
            : null,
        oAuthProfile.getPhoneNumber(),
        (oAuthProfile.getBirthdate() != null) ?
            LocalDate.parse(oAuthProfile.getBirthdate())
            : null,
        oAuthProfile.getEmail(),
        oAuthProfile.getPicture()
    );
  }
}

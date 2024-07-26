package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.OAuthProfile;

import java.time.LocalDate;

public record UserInfo(
        String username,
        String nickname,
        Gender gender,
        String phoneNumber,
        LocalDate birth,
        String email,
        String profileImageUrl,
        AddressWithCoordinate addressWithCoordinate
) {
    public static UserInfo of(String username,
                              String nickname,
                              Gender gender,
                              String phoneNumber,
                              LocalDate birth,
                              String email,
                              String profileImageUrl,
                              AddressWithCoordinate addressWithCoordinate) {
        return new UserInfo(username, nickname, gender, phoneNumber, birth, email, profileImageUrl, addressWithCoordinate);
    }

    public UserInfo updateBy(OAuthProfile oAuthProfile) {
        return new UserInfo(
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
                oAuthProfile.getPicture(),
                addressWithCoordinate
        );
    }

    public static UserInfo of(OAuthProfile oAuthProfile, AddressWithCoordinate addressWithCoordinate) {
        return new UserInfo(
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
                oAuthProfile.getPicture(),
                addressWithCoordinate
        );
    }
}

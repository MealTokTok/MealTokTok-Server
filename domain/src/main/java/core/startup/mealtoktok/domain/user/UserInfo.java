package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthUserInfo;

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

    public static UserInfo of(OAuthUserInfo oAuthUserInfo, AddressWithCoordinate addressWithCoordinate) {
        return new UserInfo(
                oAuthUserInfo.getName(),
                oAuthUserInfo.getNickname(),
                (oAuthUserInfo.getGender() != null) ?
                        Gender.valueOf(oAuthUserInfo.getGender().toUpperCase())
                        : null,
                oAuthUserInfo.getPhoneNumber(),
                (oAuthUserInfo.getBirthdate() != null) ?
                        LocalDate.parse(oAuthUserInfo.getBirthdate())
                        : null,
                oAuthUserInfo.getEmail(),
                oAuthUserInfo.getPicture(),
                addressWithCoordinate
        );
    }
}

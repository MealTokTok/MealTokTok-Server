package core.startup.mealtoktok.domain.user;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthProfile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {

    private String username;
    private String nickname;
    private Gender gender;
    private String phoneNumber;
    private LocalDate birth;
    private String email;
    private String profileImageUrl;

    public UserProfile(
            String username,
            String nickname,
            Gender gender,
            String phoneNumber,
            LocalDate birth,
            String email,
            String profileImageUrl) {
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public static UserProfile of(
            String username,
            String nickname,
            Gender gender,
            String phoneNumber,
            LocalDate birth,
            String email,
            String profileImageUrl) {
        return new UserProfile(
                username, nickname, gender, phoneNumber, birth, email, profileImageUrl);
    }

    public static UserProfile from(OAuthProfile oAuthProfile) {
        return new UserProfile(
                oAuthProfile.getName(),
                oAuthProfile.getNickname(),
                (oAuthProfile.getGender() != null)
                        ? Gender.valueOf(oAuthProfile.getGender().toUpperCase())
                        : null,
                oAuthProfile.getPhoneNumber(),
                (oAuthProfile.getBirthdate() != null)
                        ? LocalDate.parse(oAuthProfile.getBirthdate())
                        : null,
                oAuthProfile.getEmail(),
                oAuthProfile.getPicture());
    }

    public void update(UserProfile userProfile) {
        this.username = userProfile.getUsername();
        this.profileImageUrl = userProfile.getProfileImageUrl();
        this.birth = userProfile.getBirth();
        this.gender = userProfile.getGender();
        this.phoneNumber = userProfile.getPhoneNumber();
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}

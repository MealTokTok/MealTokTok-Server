package core.startup.mealtoktok.infra.user.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.user.Gender;
import core.startup.mealtoktok.domain.user.UserProfile;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserProfileVO {

    private String username;
    private String nickname;
    private String phoneNumber;
    private String email;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    public static UserProfileVO from(UserProfile userProfile) {
        return UserProfileVO.builder()
                .username(userProfile.getUsername())
                .nickname(userProfile.getNickname())
                .phoneNumber(userProfile.getPhoneNumber())
                .email(userProfile.getEmail())
                .profileImageUrl(userProfile.getProfileImageUrl())
                .gender(userProfile.getGender())
                .birth(userProfile.getBirth())
                .build();
    }

    public UserProfile toDomain() {
        return UserProfile.of(
                username, nickname, gender, phoneNumber, birth, email, profileImageUrl);
    }
}

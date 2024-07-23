package core.startup.mealtoktok.infra.user.entity;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.OAuthProfile;
import core.startup.mealtoktok.domain.auth.OAuthProvider;
import core.startup.mealtoktok.domain.user.*;
import core.startup.mealtoktok.infra.jpa.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
public class UserEntity extends BaseTimeEntity {

    //Embeddable 고려

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String email;
    private String username;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;
    private String oid;

    private String phoneNumber;

    private LocalDate birth;

    private Double latitude;
    private Double longitude;
    private String city;
    private String street;
    private String detail;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "device_token", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "device_token")
    private Set<String> deviceTokens;

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .nickname(user.getUserInfo().nickname())
                .gender(user.getUserInfo().gender())
                .userRole(user.getUserRole())
                .email(user.getUserInfo().email())
                .username(user.getUserInfo().username())
                .profileImageUrl(user.getUserInfo().profileImageUrl())
                .provider(user.getOAuthInfo().provider())
                .oid(user.getOAuthInfo().oid())
                .phoneNumber(user.getUserInfo().phoneNumber())
                .birth(user.getUserInfo().birth())
                .latitude(user.getUserInfo().addressWithCoordinate().coordinate().latitude())
                .longitude(user.getUserInfo().addressWithCoordinate().coordinate().longitude())
                .city(user.getUserInfo().addressWithCoordinate().address().city())
                .street(user.getUserInfo().addressWithCoordinate().address().street())
                .detail(user.getUserInfo().addressWithCoordinate().address().detail())
                .deviceTokens(user.getDeviceTokens())
                .createdAt(user.getUserDateInfo().createdAt())
                .modifiedAt(user.getUserDateInfo().modifiedAt())
                .build();
    }

    public static UserEntity from(OAuthInfo oAuthInfo, UserInfo userInfo) {
        return UserEntity.builder()
                .username(userInfo.username())
                .nickname(userInfo.nickname())
                .gender(userInfo.gender())
                .phoneNumber(userInfo.phoneNumber())
                .birth(userInfo.birth())
                .email(userInfo.email())
                .profileImageUrl(userInfo.profileImageUrl())
                .provider(oAuthInfo.provider())
                .oid(oAuthInfo.oid())
                .latitude(userInfo.addressWithCoordinate().coordinate().latitude())
                .longitude(userInfo.addressWithCoordinate().coordinate().longitude())
                .city(userInfo.addressWithCoordinate().address().city())
                .street(userInfo.addressWithCoordinate().address().street())
                .detail(userInfo.addressWithCoordinate().address().detail())
                .userRole(UserRole.USER)
                .build();
    }


    public User toDomain() {
        return User.builder()
                .userId(userId)
                .userRole(userRole)
                .deviceTokens(deviceTokens)
                .userInfo(UserInfo.of(username, nickname, gender, phoneNumber, birth, email, profileImageUrl, AddressWithCoordinate.of(latitude, longitude, city, street, detail)))
                .oAuthInfo(OAuthInfo.of(provider, oid))
                .userDateInfo(UserDateInfo.of(createdAt, modifiedAt))
                .build();
    }

    public void updateAddressInfo(AddressWithCoordinate addressWithCoordinate) {
        this.latitude = addressWithCoordinate.coordinate().latitude();
        this.longitude = addressWithCoordinate.coordinate().longitude();
        this.city = addressWithCoordinate.address().city();
        this.street = addressWithCoordinate.address().street();
        this.detail = addressWithCoordinate.address().detail();
    }

    public void oAuthUpdate(OAuthProfile profile) {
        this.username = profile.getName();
        this.nickname = profile.getNickname();
        this.profileImageUrl = profile.getPicture();
        this.gender = Gender.valueOf(profile.getGender());
        this.email = profile.getEmail();
        this.birth = LocalDate.parse(profile.getBirthdate());
        this.phoneNumber = profile.getPhoneNumber();

    }
}

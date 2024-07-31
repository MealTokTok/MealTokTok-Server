package core.startup.mealtoktok.infra.user.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.OAuthProvider;
import core.startup.mealtoktok.domain.user.*;
import core.startup.mealtoktok.infra.jpa.config.BaseTimeEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryAddressEntity> deliveryAddresses = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "device_token", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "device_token")
    private Set<String> deviceTokens = new HashSet<>();

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .nickname(user.getUserProfile().nickname())
                .gender(user.getUserProfile().gender())
                .userRole(user.getUserRole())
                .email(user.getUserProfile().email())
                .username(user.getUserProfile().username())
                .profileImageUrl(user.getUserProfile().profileImageUrl())
                .provider(user.getOAuthInfo().provider())
                .oid(user.getOAuthInfo().oid())
                .phoneNumber(user.getUserProfile().phoneNumber())
                .birth(user.getUserProfile().birth())
                .deliveryAddresses(
                        user.getDeliveryAddresses().stream()
                                .map(DeliveryAddressEntity::from)
                                .toList())
                .deviceTokens(user.getDeviceTokens())
                .createdAt(user.getUserDateTime().createdAt())
                .modifiedAt(user.getUserDateTime().modifiedAt())
                .build();
    }

    public static UserEntity from(
            OAuthInfo oAuthInfo,
            String deviceToken,
            UserProfile userProfile,
            DeliveryAddress deliveryAddress) {
        UserEntity userEntity =
                UserEntity.builder()
                        .username(userProfile.username())
                        .nickname(userProfile.nickname())
                        .gender(userProfile.gender())
                        .phoneNumber(userProfile.phoneNumber())
                        .birth(userProfile.birth())
                        .email(userProfile.email())
                        .profileImageUrl(userProfile.profileImageUrl())
                        .provider(oAuthInfo.provider())
                        .oid(oAuthInfo.oid())
                        .deviceTokens(Set.of(deviceToken))
                        .userRole(UserRole.USER)
                        .build();

        userEntity.deliveryAddresses.add(DeliveryAddressEntity.from(deliveryAddress));
        return userEntity;
    }

    public User toDomain() {
        return User.builder()
                .userId(userId)
                .userRole(userRole)
                .deviceTokens(deviceTokens)
                .userProfile(
                        UserProfile.of(
                                username,
                                nickname,
                                gender,
                                phoneNumber,
                                birth,
                                email,
                                profileImageUrl))
                .deliveryAddresses(
                        deliveryAddresses.stream().map(DeliveryAddressEntity::toDomain).toList())
                .oAuthInfo(OAuthInfo.of(provider, oid))
                .userDateTime(UserDateTime.of(createdAt, modifiedAt))
                .build();
    }
}

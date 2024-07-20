package core.startup.mealtoktok.infra.user.entity;

import core.startup.mealtoktok.domain.user.AddressInfo;
import core.startup.mealtoktok.domain.user.Gender;
import core.startup.mealtoktok.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private String username;

    private String phoneNumber;

    private LocalDateTime birth;

    private Double latitude;
    private Double longitude;
    private String city;
    private String street;
    private String detail;

    @ElementCollection
    @CollectionTable(name = "device_token", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "device_token")
    private Set<String> deviceTokens;

    private LocalDateTime createdAt;

    public User toDomain() {
        return User.builder()
                .userId(userId)
                .nickname(nickname)
                .gender(gender)
                .email(email)
                .username(username)
                .phoneNumber(phoneNumber)
                .birth(birth)
                .addressInfo(AddressInfo.of(latitude, longitude, city, street, detail))
                .build();
    }

    public void updateAddressInfo(AddressInfo addressInfo) {
        this.latitude = addressInfo.coordinate().latitude();
        this.longitude = addressInfo.coordinate().longitude();
        this.city = addressInfo.address().city();
        this.street = addressInfo.address().street();
        this.detail = addressInfo.address().detail();
    }
}

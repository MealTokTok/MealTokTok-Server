package core.startup.mealtoktok.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Builder
public class User {
    private Long userId;
    private String nickname;
    private Gender gender;
    private String email;
    private String username;
    private String phoneNumber;
    private LocalDateTime birth;
    private AddressInfo addressInfo;
    private Set<String> deviceTokens;
    private LocalDateTime createdAt;

    public User initAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
        return this;
    }

}

package core.startup.mealtoktok.infra.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthProfile;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
public class KakaoProfileResponse implements OAuthProfile {

    private String sub;
    private String name;
    private String nickname;
    private String picture;
    private String email;
    private String gender;
    private String birthdate;

    @JsonProperty("phone_number")
    private String phoneNumber;
}

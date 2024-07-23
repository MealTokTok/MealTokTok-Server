package core.startup.mealtoktok.domain.auth;

import java.time.LocalDate;

public interface OAuthUserInfo {

    String getSub();

    String getName();

    String getNickname();

    String getPicture();

    String getEmail();

    String getGender();

    String getBirthdate();

    String getPhoneNumber();
}

package core.startup.mealtoktok.domain.auth;

public interface OAuthProfile {

    String getSub();

    String getName();

    String getNickname();

    String getPicture();

    String getEmail();

    String getGender();

    String getBirthdate();

    String getPhoneNumber();
}

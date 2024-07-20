package core.startup.mealtoktok.domain.user;

public interface BanTokenRepository {

    void ban(String token);

    boolean isLoginUser(String token);
}

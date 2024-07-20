package core.startup.mealtoktok.domain.user;

public interface UserRepository {

    User findById(TargetUser targetUser);

    void updateAddressInfo(User user);

}

package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserUpdater userUpdater;
    private final userRemover userRemover;
    private final UserValidator userValidator;
    private final UserReader userReader;

    public UserId addDefaultDeliveryAddress(
            User currentUser, AddressWithCoordinate addressWithCoordinate) {
        return userUpdater.addDeliveryAddress(
                currentUser, DeliveryAddress.configure(addressWithCoordinate));
    }

    public UserId addDeliveryAddress(
            User currentUser, AddressWithCoordinate addressWithCoordinate) {
        return userUpdater.addDeliveryAddress(
                currentUser, DeliveryAddress.notConfigure(addressWithCoordinate));
    }

    public UserId removeDeliveryAddress(
            User currentUser, TargetDeliveryAddress targetDeliveryAddress) {
        return userUpdater.removeDeliveryAddress(currentUser, targetDeliveryAddress);
    }

    public void withdraw(User currentUser, String reason) {
        userRemover.remove(currentUser, reason);
    }

    public UserId changeNickname(User currentUser, String nickname) {
        return userUpdater.updateNickname(currentUser, nickname);
    }

    public UserId changeEmail(User currentUser, String email) {
        return userUpdater.updateEmail(currentUser, email);
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userValidator.isDuplicated(nickname);
    }

    public UserId configureDeliveryAddress(
            User currentUser, TargetDeliveryAddress targetDeliveryAddress) {
        return userUpdater.configureDeliveryAddress(currentUser, targetDeliveryAddress);
    }

    public User getUser(Long userId) {
        return userReader.read(UserId.from(userId));
    }
}

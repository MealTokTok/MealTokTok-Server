package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserUpdater userUpdater;
    private final userRemover userRemover;
    private final UserValidator userValidator;

    public TargetUser addDeliveryAddress(
            User currentUser, AddressWithCoordinate addressWithCoordinate) {
        return userUpdater.addDeliveryAddress(
                currentUser, DeliveryAddress.notConfigure(addressWithCoordinate));
    }

    public TargetUser removeDeliveryAddress(
            User currentUser, TargetDeliveryAddress targetDeliveryAddress) {
        return userUpdater.removeDeliveryAddress(currentUser, targetDeliveryAddress);
    }

    public void withdraw(User currentUser, String reason) {
        userRemover.remove(currentUser, reason);
    }

    public TargetUser changeNickname(User currentUser, String nickname) {
        return userUpdater.updateNickname(currentUser, nickname);
    }

    public TargetUser changeEmail(User currentUser, String email) {
        return userUpdater.updateEmail(currentUser, email);
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userValidator.isDuplicated(nickname);
    }

    public TargetUser configureDeliveryAddress(
            User currentUser, TargetDeliveryAddress targetDeliveryAddress) {
        return userUpdater.configureDeliveryAddress(currentUser, targetDeliveryAddress);
    }
}

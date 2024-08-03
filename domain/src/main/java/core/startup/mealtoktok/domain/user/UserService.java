package core.startup.mealtoktok.domain.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserUpdater userUpdater;
    private final userRemover userRemover;

    public TargetUser addDeliveryAddress(
            TargetUser targetUser, AddressWithCoordinate addressWithCoordinate) {
        User user = userReader.read(targetUser);
        return userUpdater.addDeliveryAddress(
                user, DeliveryAddress.notConfigure(addressWithCoordinate));
    }

    public List<DeliveryAddress> getDeliveryAddresses(TargetUser targetUser) {
        User user = userReader.read(targetUser);
        return user.getDeliveryAddresses();
    }

    public TargetUser removeDeliveryAddress(
            TargetUser targetUser, TargetDeliveryAddress targetDeliveryAddress) {
        User user = userReader.read(targetUser);
        userUpdater.removeDeliveryAddress(user, targetDeliveryAddress);
        return targetUser;
    }

    public void withdraw(TargetUser targetUser, String reason) {
        User user = userReader.read(targetUser);
        userRemover.remove(user, reason);
    }

    public TargetUser changeNickname(TargetUser targetUser, String nickname) {
        User user = userReader.read(targetUser);
        return userUpdater.updateNickname(user, nickname);
    }

    public TargetUser changeEmail(TargetUser targetUser, String email) {
        User user = userReader.read(targetUser);
        return userUpdater.updateEmail(user, email);
    }
}

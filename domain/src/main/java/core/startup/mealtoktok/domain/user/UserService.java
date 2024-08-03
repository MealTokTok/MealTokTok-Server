package core.startup.mealtoktok.domain.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserUpdater userUpdater;

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
}

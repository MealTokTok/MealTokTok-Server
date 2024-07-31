package core.startup.mealtoktok.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserReader userReader;
  private final UserUpdater userUpdater;

  public TargetUser addAddress(TargetUser targetUser, AddressWithCoordinate addressWithCoordinate) {
    User user = userReader.read(targetUser);
    return userUpdater.addAddress(user, DeliveryAddress.notConfigure(addressWithCoordinate));
  }

}
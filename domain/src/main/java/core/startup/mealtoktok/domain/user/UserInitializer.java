package core.startup.mealtoktok.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInitializer {

    private final UserRepository userRepository;

    public void init(User user, AddressInfo addressInfo) {
        user.initAddressInfo(addressInfo);
        userRepository.updateAddressInfo(user);
    }
}

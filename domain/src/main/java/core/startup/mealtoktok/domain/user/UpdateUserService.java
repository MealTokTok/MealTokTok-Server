package core.startup.mealtoktok.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserReader userReader;
    private final UserInitializer userInitializer;

    public void initAddress(TargetUser targetUser,
                       AddressInfo addressInfo) {
        User user = userReader.read(targetUser);
        userInitializer.init(user, addressInfo);
    }

}

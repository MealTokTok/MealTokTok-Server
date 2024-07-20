package core.startup.mealtoktok.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User read(TargetUser targetUser) {
        return userRepository.findById(targetUser);
    }

}

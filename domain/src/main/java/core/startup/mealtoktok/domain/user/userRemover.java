package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class userRemover {

    private final UserRepository userRepository;

    public void remove(User user, String reason) {
        if (reason.isBlank()) {
            userRepository.delete(user);
            return;
        }
        userRepository.deleteWithReason(
                user, WithDrawReason.of(user.getUserId().getValue(), reason));
    }
}

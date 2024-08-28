package core.startup.mealtoktok.api.user.dto;

import java.time.LocalDate;

import core.startup.mealtoktok.domain.user.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record UserResponse(
        Long userId,
        String username,
        String nickname,
        String email,
        String phoneNumber,
        String profileImageUrl,
        LocalDate birth) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUserId().getValue(),
                user.getUserProfile().getUsername(),
                user.getUserProfile().getNickname(),
                user.getUserProfile().getEmail(),
                user.getUserProfile().getPhoneNumber(),
                user.getUserProfile().getProfileImageUrl(),
                user.getUserProfile().getBirth());
    }
}

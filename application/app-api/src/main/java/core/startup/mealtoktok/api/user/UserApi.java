package core.startup.mealtoktok.api.user;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.auth.dto.SignupRequest.AddressInfoRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi implements UserApiDocs {

    private final UserService userService;

    @PatchMapping("/{userId}/address")
    public Response<TargetUser> addAddress(
            @PathVariable Long userId, AddressInfoRequest addressInfoRequest) {

        return Response.success(
                userService.addAddress(
                        TargetUser.from(userId), addressInfoRequest.toAddressWithCoordinate()));
    }
}

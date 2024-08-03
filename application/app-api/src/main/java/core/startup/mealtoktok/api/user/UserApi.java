package core.startup.mealtoktok.api.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.auth.dto.SignUpRequest.AddressInfoRequest;
import core.startup.mealtoktok.api.user.dto.DeliveryAddressResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi implements UserApiDocs {

    private final UserService userService;

    @PatchMapping("/{userId}/address")
    public Response<TargetUser> addDeliveryAddress(
            @PathVariable Long userId, AddressInfoRequest addressInfoRequest) {

        return Response.success(
                userService.addDeliveryAddress(
                        TargetUser.from(userId), addressInfoRequest.toAddressWithCoordinate()));
    }

    @GetMapping("/{userId}/address")
    public Response<List<DeliveryAddressResponse>> getDeliveryAddresses(@PathVariable Long userId) {
        return Response.success(
                userService.getDeliveryAddresses(TargetUser.from(userId)).stream()
                        .map(DeliveryAddressResponse::from)
                        .toList());
    }
}

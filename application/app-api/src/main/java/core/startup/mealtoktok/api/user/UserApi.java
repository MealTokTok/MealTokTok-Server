package core.startup.mealtoktok.api.user;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.auth.dto.SignUpRequest.AddressInfoRequest;
import core.startup.mealtoktok.api.user.dto.AvailabilityResponse;
import core.startup.mealtoktok.api.user.dto.DeliveryAddressResponse;
import core.startup.mealtoktok.api.user.dto.UserResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.TargetDeliveryAddress;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi implements UserApiDocs {

    private final UserService userService;

    @Override
    @GetMapping("/{userId}")
    public Response<UserResponse> getUser(@PathVariable Long userId) {
        return Response.success(UserResponse.from(userService.getUser(TargetUser.from(userId))));
    }

    @GetMapping("/nickname/change-available")
    public Response<AvailabilityResponse> checkNicknameDuplicate(String nickname) {
        boolean isDuplicated = userService.checkNicknameDuplicate(nickname);
        return Response.success(AvailabilityResponse.from(!isDuplicated));
    }

    @PatchMapping("/{userId}/nickname")
    public Response<TargetUser> changeNickname(@PathVariable Long userId, String nickname) {
        return Response.success(userService.changeNickname(TargetUser.from(userId), nickname));
    }

    @PatchMapping("/{userId}/email")
    public Response<TargetUser> changeEmail(@PathVariable Long userId, String email) {
        return Response.success(userService.changeEmail(TargetUser.from(userId), email));
    }

    @PostMapping("/{userId}/address")
    public Response<TargetUser> addDeliveryAddress(
            @PathVariable("userId") Long userId,
            @RequestBody AddressInfoRequest addressInfoRequest) {

        return Response.success(
                userService.addDeliveryAddress(
                        TargetUser.from(userId), addressInfoRequest.toAddressWithCoordinate()));
    }

    @DeleteMapping("/{userId}/address/{deliveryAddressId}")
    public Response<TargetUser> deleteDeliveryAddress(
            @PathVariable("userId") Long userId,
            @PathVariable("deliveryAddressId") Long deliveryAddressId) {
        return Response.success(
                userService.removeDeliveryAddress(
                        TargetUser.from(userId), TargetDeliveryAddress.from(deliveryAddressId)));
    }

    @DeleteMapping("/{userId}")
    public Response<Void> deleteUser(@PathVariable Long userId, String reason) {
        userService.withdraw(TargetUser.from(userId), reason);
        return Response.success("성공적으로 회원탈퇴 되었습니다.");
    }

    @GetMapping("/{userId}/address")
    public Response<List<DeliveryAddressResponse>> getDeliveryAddresses(@PathVariable Long userId) {
        return Response.success(
                userService.getDeliveryAddresses(TargetUser.from(userId)).stream()
                        .map(DeliveryAddressResponse::from)
                        .toList());
    }
}

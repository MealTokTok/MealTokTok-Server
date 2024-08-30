package core.startup.mealtoktok.api.user;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.auth.dto.SignUpRequest.AddressInfoRequest;
import core.startup.mealtoktok.api.global.dto.Response;
import core.startup.mealtoktok.api.user.dto.AvailabilityResponse;
import core.startup.mealtoktok.api.user.dto.DeliveryAddressResponse;
import core.startup.mealtoktok.api.user.dto.UserResponse;
import core.startup.mealtoktok.domain.user.TargetDeliveryAddress;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserId;
import core.startup.mealtoktok.domain.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi implements UserApiDocs {

    private final UserService userService;

    @GetMapping("/my")
    public Response<UserResponse> getUser(@AuthenticationPrincipal User currentUser) {
        return Response.success(UserResponse.from(currentUser));
    }

    @DeleteMapping("/my")
    public Response<Void> deleteUser(@AuthenticationPrincipal User currentUser, String reason) {
        userService.withdraw(currentUser, reason);
        return Response.success("성공적으로 회원탈퇴 되었습니다.");
    }

    @GetMapping("/nickname/change-available")
    public Response<AvailabilityResponse> checkNicknameDuplicate(String nickname) {
        boolean isDuplicated = userService.checkNicknameDuplicate(nickname);
        return Response.success(AvailabilityResponse.from(!isDuplicated));
    }

    @PatchMapping("/my/nickname")
    public Response<UserId> changeNickname(
            @AuthenticationPrincipal User currentUser, String nickname) {
        return Response.success(userService.changeNickname(currentUser, nickname));
    }

    @PatchMapping("/my/email")
    public Response<UserId> changeEmail(@AuthenticationPrincipal User currentUser, String email) {
        return Response.success(userService.changeEmail(currentUser, email));
    }

    @PatchMapping("/my/addresses/default")
    public Response<UserId> addDefaultDeliveryAddress(
            @AuthenticationPrincipal User currentUser,
            @RequestBody AddressInfoRequest addressInfoRequest) {

        return Response.success(
                userService.addDefaultDeliveryAddress(
                        currentUser, addressInfoRequest.toAddressWithCoordinate()));
    }

    @PatchMapping("/my/addresses")
    public Response<UserId> addDeliveryAddress(
            @AuthenticationPrincipal User currentUser,
            @RequestBody AddressInfoRequest addressInfoRequest) {

        return Response.success(
                userService.addDeliveryAddress(
                        currentUser, addressInfoRequest.toAddressWithCoordinate()));
    }

    @GetMapping("/my/addresses/{deliveryAddressId}")
    public Response<DeliveryAddressResponse> getDeliveryAddress(
            @AuthenticationPrincipal User currentUser, @PathVariable Long deliveryAddressId) {
        return Response.success(
                DeliveryAddressResponse.from(
                        currentUser.findDeliveryAddress(
                                TargetDeliveryAddress.from(deliveryAddressId))));
    }

    @DeleteMapping("/my/addresses/{deliveryAddressId}")
    public Response<UserId> deleteDeliveryAddress(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("deliveryAddressId") Long deliveryAddressId) {
        return Response.success(
                userService.removeDeliveryAddress(
                        currentUser, TargetDeliveryAddress.from(deliveryAddressId)));
    }

    @GetMapping("/my/addresses")
    public Response<List<DeliveryAddressResponse>> getDeliveryAddresses(
            @AuthenticationPrincipal User currentUser) {
        return Response.success(
                currentUser.getDeliveryAddresses().stream()
                        .map(DeliveryAddressResponse::from)
                        .toList());
    }

    @PatchMapping("/my/addresses/{deliveryAddressId}")
    public Response<UserId> configureDeliveryAddress(
            @AuthenticationPrincipal User currentUser, @PathVariable Long deliveryAddressId) {
        return Response.success(
                userService.configureDeliveryAddress(
                        currentUser, TargetDeliveryAddress.from(deliveryAddressId)));
    }
}

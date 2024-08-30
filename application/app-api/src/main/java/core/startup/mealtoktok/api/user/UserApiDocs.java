package core.startup.mealtoktok.api.user;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import core.startup.mealtoktok.api.auth.dto.SignUpRequest.AddressInfoRequest;
import core.startup.mealtoktok.api.global.dto.Response;
import core.startup.mealtoktok.api.user.dto.AvailabilityResponse;
import core.startup.mealtoktok.api.user.dto.DeliveryAddressResponse;
import core.startup.mealtoktok.api.user.dto.UserResponse;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserId;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "회원 API")
public interface UserApiDocs {

    @Operation(summary = "유저 정보 조회")
    Response<UserResponse> getUser(User currentUser);

    @Operation(summary = "닉네임 중복 확인")
    Response<AvailabilityResponse> checkNicknameDuplicate(String nickname);

    @Operation(summary = "닉네임 변경")
    Response<UserId> changeNickname(User currentUser, String nickname);

    @Operation(summary = "이메일 변경")
    Response<UserId> changeEmail(User currentUser, String email);

    @Operation(summary = "기본 배송지 추가")
    Response<UserId> addDefaultDeliveryAddress(
            User currentUser, AddressInfoRequest addressInfoRequest);

    @Operation(summary = "배송지 추가")
    Response<UserId> addDeliveryAddress(User currentUser, AddressInfoRequest addressInfoRequest);

    @Operation(summary = "배송지 삭제")
    Response<UserId> deleteDeliveryAddress(User currentUser, Long deliveryAddressId);

    @Operation(summary = "회원탈퇴")
    Response<Void> deleteUser(User currentUser, String reason);

    @Operation(summary = "배송지 조회")
    Response<DeliveryAddressResponse> getDeliveryAddress(User currentUser, Long deliveryAddressId);

    @Operation(summary = "배송지 목록 조회")
    Response<List<DeliveryAddressResponse>> getDeliveryAddresses(User currentUser);

    @Operation(summary = "기본 배송지 설정")
    Response<UserId> configureDeliveryAddress(
            @AuthenticationPrincipal User currentUser, Long deliveryAddressId);
}

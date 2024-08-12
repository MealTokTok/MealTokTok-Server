package core.startup.mealtoktok.api.user;

import java.util.List;

import core.startup.mealtoktok.api.auth.dto.SignUpRequest.AddressInfoRequest;
import core.startup.mealtoktok.api.user.dto.AvailabilityResponse;
import core.startup.mealtoktok.api.user.dto.DeliveryAddressResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.TargetUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "회원 API")
public interface UserApiDocs {

    Response<AvailabilityResponse> checkNicknameDuplicate(String nickname);

    @Operation(summary = "닉네임 변경")
    Response<TargetUser> changeNickname(Long userId, String nickname);

    @Operation(summary = "이메일 변경")
    Response<TargetUser> changeEmail(Long userId, String email);

    @Operation(summary = "배송지 추가")
    Response<TargetUser> addDeliveryAddress(Long userId, AddressInfoRequest addressInfoRequest);

    @Operation(summary = "배송지 삭제")
    Response<TargetUser> deleteDeliveryAddress(Long userId, Long deliveryAddressId);

    @Operation(summary = "회원탈퇴")
    Response<Void> deleteUser(Long userId, String reason);

    @Operation(summary = "배송지 목록 조회")
    Response<List<DeliveryAddressResponse>> getDeliveryAddresses(Long userId);
}

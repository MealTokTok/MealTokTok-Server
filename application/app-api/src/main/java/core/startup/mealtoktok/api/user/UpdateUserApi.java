package core.startup.mealtoktok.api.user;

import core.startup.mealtoktok.api.user.request.UpdateAddressRequest;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
@RequiredArgsConstructor
public class UpdateUserApi {

    private final UpdateUserService updateUserService;

    @PatchMapping("{userId}/address-info")
    public ResponseEntity<Void> initAddressInfo(@PathVariable Long userId,
                                          @RequestBody UpdateAddressRequest updateAddressRequest) {
        updateUserService.initAddress(TargetUser.from(userId), updateAddressRequest.toInfo());
        return ResponseEntity.ok().build();
    }

}

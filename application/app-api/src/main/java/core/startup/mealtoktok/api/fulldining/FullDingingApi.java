package core.startup.mealtoktok.api.fulldining;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.fulldining.dto.FullDiningResponse;
import core.startup.mealtoktok.api.global.dto.Response;
import core.startup.mealtoktok.domain.fulldining.CollectingState;
import core.startup.mealtoktok.domain.fulldining.FullDining;
import core.startup.mealtoktok.domain.fulldining.FullDiningId;
import core.startup.mealtoktok.domain.fulldining.FullDiningService;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;
import core.startup.mealtoktok.domain.user.User;

@RequestMapping("/api/v1/full-dinings")
@RestController
@RequiredArgsConstructor
public class FullDingingApi implements FullDiningApiDocs {

    private final FullDiningService fullDiningService;

    @PatchMapping("/full-dinings/{fullDiningId}/{collectingState}")
    public Response<Void> changeCollectingState(
            @PathVariable Long fullDiningId, @PathVariable CollectingState collectingState) {
        fullDiningService.changeCollectingState(FullDiningId.from(fullDiningId), collectingState);
        return Response.success("수거 상태 변경 성공");
    }

    @GetMapping("/full-dinings/collect-requested/count")
    public Response<Integer> countCollectRequestContainers(
            @AuthenticationPrincipal User currentUser) {
        return Response.success(
                fullDiningService.countCollectRequestContainers(Recipient.from(currentUser)));
    }

    @GetMapping("/full-dinings")
    public Response<List<FullDiningResponse>> fullDinings(
            @AuthenticationPrincipal User currentUser) {
        List<FullDining> fullDinings =
                fullDiningService.getFullDinings(Recipient.from(currentUser));
        return Response.success(
                fullDinings.parallelStream().map(FullDiningResponse::from).toList());
    }
}

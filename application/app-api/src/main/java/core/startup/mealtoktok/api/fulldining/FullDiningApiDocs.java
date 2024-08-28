package core.startup.mealtoktok.api.fulldining;

import java.util.List;

import core.startup.mealtoktok.api.fulldining.dto.FullDiningResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.fulldining.CollectingState;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "풀대접 API")
public interface FullDiningApiDocs {

    @Operation(summary = "풀대접 서비스 수거 상태 변경")
    Response<Void> changeCollectingState(Long fullDiningId, CollectingState collectingState);

    @Operation(summary = "풀대접 서비스 수거 요청된 다회용기 갯수 조회")
    Response<Integer> countCollectRequestContainers(User currentUser);

    @Operation(summary = "풀대접 서비스 7일 이내의 다회용기 납부 목록 조회")
    Response<List<FullDiningResponse>> fullDinings(User currentUser);
}

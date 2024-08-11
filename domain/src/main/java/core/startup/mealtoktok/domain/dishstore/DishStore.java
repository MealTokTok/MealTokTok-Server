package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DishStore {

    private Long storeId;
    private DishStoreInfo dishStoreInfo;
}

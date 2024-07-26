package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DishStore {

    private Long storeId;
    private String storeName;
    private String phoneNumber;
    private AddressWithCoordinate addressWithCoordinate;
    private OperatingHour operatingHour;
}

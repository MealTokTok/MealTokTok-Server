package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.domain.user.AddressWithCoordinate;

@Getter
@Builder
public class DishStore {

    private Long storeId;
    private String storeName;
    private String phoneNumber;
    private AddressWithCoordinate addressWithCoordinate;
    private OperatingHour operatingHour;
}

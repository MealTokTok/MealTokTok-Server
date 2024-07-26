package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DishStore {

    Long storeId;
    private String storeName;
    private String phoneNumber;
    AddressWithCoordinate addressWithCoordinate;
    OperatingHour operatingHour;
}

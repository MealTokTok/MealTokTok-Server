package core.startup.mealtoktok.domain.order;

import java.util.List;

public interface MealDeliveryReserver {

    void reserve(List<MealDeliveryReservationInfo> reserveInfo);
}

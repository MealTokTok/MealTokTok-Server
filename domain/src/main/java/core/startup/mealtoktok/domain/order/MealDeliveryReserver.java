package core.startup.mealtoktok.domain.order;

import java.util.List;

public interface MealDeliveryReserver {

    List<FullDiningInfo> reserve(List<MealDeliveryReservationInfo> reserveInfo);
}

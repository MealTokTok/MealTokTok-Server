package core.startup.mealtoktok.domain.order;

import java.time.LocalDate;

public record OrderedMeal(
        Long mealId,
        ReservedSchedule reservedSchedule,
        boolean includeRice,
        boolean hasFullDiningOption) {

    public static OrderedMeal create(
            Long mealId,
            LocalDate reservedDate,
            ReservedTime reservedTime,
            boolean includeRice,
            boolean hasFullDiningOption) {

        return new OrderedMeal(
                mealId,
                ReservedSchedule.schedule(reservedDate, reservedTime),
                includeRice,
                hasFullDiningOption);
    }
}

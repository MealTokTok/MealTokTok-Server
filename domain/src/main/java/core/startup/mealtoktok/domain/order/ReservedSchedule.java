package core.startup.mealtoktok.domain.order;

import java.time.LocalDate;

public record ReservedSchedule(LocalDate reservedDate, ReservedTime reservedTime) {

    public static ReservedSchedule schedule(LocalDate reservedDate, ReservedTime reservedTime) {
        return new ReservedSchedule(reservedDate, reservedTime);
    }
}

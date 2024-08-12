package core.startup.mealtoktok.api.user.dto;

public record AvailabilityResponse(boolean isAvailable) {

    public static AvailabilityResponse from(boolean isAvailable) {
        return new AvailabilityResponse(isAvailable);
    }
}

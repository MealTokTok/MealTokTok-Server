package core.startup.mealtoktok.api.user.request;

public record AddressInfo(
        String address,
        Double latitude,
        Double longitude
) {
}

package core.startup.mealtoktok.domain.user;

public record Address(
        String city,
        String street,
        String detail
) {
    public static Address of(String city,
                             String street,
                             String detail) {
        return new Address(city, street, detail);
    }
}


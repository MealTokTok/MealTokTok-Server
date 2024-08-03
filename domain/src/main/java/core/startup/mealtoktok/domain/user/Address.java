package core.startup.mealtoktok.domain.user;

public record Address(String address, String detailAddress) {

    public static Address of(String address, String detailAddress) {
        return new Address(address, detailAddress);
    }
}

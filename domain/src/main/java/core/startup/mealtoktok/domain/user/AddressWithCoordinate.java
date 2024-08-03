package core.startup.mealtoktok.domain.user;

public record AddressWithCoordinate(Address address, Coordinate coordinate) {

    public static AddressWithCoordinate of(Address address, Double latitude, Double longitude) {
        return new AddressWithCoordinate(address, new Coordinate(latitude, longitude));
    }

    public static AddressWithCoordinate of(Address address, Coordinate coordinate) {
        return new AddressWithCoordinate(address, coordinate);
    }
}

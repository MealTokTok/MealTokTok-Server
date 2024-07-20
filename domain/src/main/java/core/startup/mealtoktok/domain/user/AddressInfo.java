package core.startup.mealtoktok.domain.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record AddressInfo(
        Coordinate coordinate,
        Address address
) {
    public static AddressInfo of(Double latitude,
                                 Double longitude,
                                 String address) {
        return new AddressInfo(
                new Coordinate(latitude, longitude),
                parseAddress(address)
        );
    }

    public static AddressInfo of(Double latitude,
                                 Double longitude,
                                 String city,
                                 String street,
                                 String detail) {
        return new AddressInfo(
                new Coordinate(latitude, longitude),
                Address.of(city, street, detail)
        );
    }

    private static Address parseAddress(String address) {
        String pattern = "^(\\S+) (\\S+) (.+)$";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(address);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid address format");
        }

        return Address.of(matcher.group(1), matcher.group(2), matcher.group(3));
    }
}

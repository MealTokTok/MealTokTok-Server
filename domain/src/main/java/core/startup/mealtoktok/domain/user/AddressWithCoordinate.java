package core.startup.mealtoktok.domain.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record AddressWithCoordinate(
    Address address,
    Coordinate coordinate
) {

  public static AddressWithCoordinate of(String address,
      Double latitude,
      Double longitude) {
    return new AddressWithCoordinate(
        parseAddress(address),
        new Coordinate(latitude, longitude)
    );
  }

  public static AddressWithCoordinate of(Double latitude,
      Double longitude,
      String city,
      String street,
      String detail) {
    return new AddressWithCoordinate(
        Address.of(city, street, detail),
        new Coordinate(latitude, longitude)
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

package core.startup.mealtoktok.infra.global.vo;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;
    private String detailAddress;

    public core.startup.mealtoktok.domain.user.Address toDomain() {
        return new core.startup.mealtoktok.domain.user.Address(address, detailAddress);
    }

    public static Address from(core.startup.mealtoktok.domain.user.Address address) {
        return new Address(address.address(), address.detailAddress());
    }
}

package core.startup.mealtoktok.infra.global.vo;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {

    private String address;
    private String detailAddress;

    public core.startup.mealtoktok.domain.user.Address toDomain() {
        return new core.startup.mealtoktok.domain.user.Address(address, detailAddress);
    }

    public static AddressVO from(core.startup.mealtoktok.domain.user.Address address) {
        return new AddressVO(address.address(), address.detailAddress());
    }
}

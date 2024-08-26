package core.startup.mealtoktok.infra.global.entity;

import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.user.Address;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddressVO {

    private String address;
    private String detailAddress;

    public static AddressVO from(Address address) {
        return AddressVO.builder()
                .address(address.address())
                .detailAddress(address.detailAddress())
                .build();
    }

    public Address toDomain() {
        return Address.of(address, detailAddress);
    }
}

package core.startup.mealtoktok.infra.order.entity;

import java.math.BigDecimal;

import jakarta.persistence.AttributeConverter;

import core.startup.mealtoktok.common.dto.Money;

public class MoneyConverter implements AttributeConverter<Money, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(Money money) {
        return money == null ? null : money.amount();
    }

    @Override
    public Money convertToEntityAttribute(BigDecimal amount) {
        return amount == null ? null : Money.from(amount);
    }
}

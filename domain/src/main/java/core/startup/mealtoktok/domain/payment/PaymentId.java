package core.startup.mealtoktok.domain.payment;

import core.startup.mealtoktok.common.domain.BaseId;

public class PaymentId extends BaseId<Long> {

    public PaymentId(Long value) {
        super(value);
    }

    public static PaymentId from(Long value) {
        return new PaymentId(value);
    }
}

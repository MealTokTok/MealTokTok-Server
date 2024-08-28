package core.startup.mealtoktok.domain.fulldining;

import core.startup.mealtoktok.common.domain.BaseId;

public class FullDiningId extends BaseId<Long> {

    public FullDiningId(Long id) {
        super(id);
    }

    public static FullDiningId from(Long id) {
        return new FullDiningId(id);
    }
}

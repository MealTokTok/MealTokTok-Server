package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.common.domain.BaseId;

public class UserId extends BaseId<Long> {

    public UserId(Long id) {
        super(id);
    }

    public static UserId from(Long id) {
        return new UserId(id);
    }
}

package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DishCategory {
    private Long categoryId;
    private String categoryName;
}

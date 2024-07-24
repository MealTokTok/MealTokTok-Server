package core.startup.mealtoktok.domain.dishCategory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DishCategory{
    Long categoryId;
    String categoryName;
}



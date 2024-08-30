package core.startup.mealtoktok.domain.dishstore;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishImageReader {

    private final DishRepository dishRepository;

    public List<DishImage> read(TargetDish targetDish) {
        return dishRepository.findAllDishImageByDishId(targetDish);
    }
}

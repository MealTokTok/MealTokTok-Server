package core.startup.mealtoktok.domain.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishReader {

    private final DishRepository dishRepository;

    public Dish read(TargetDish targetDish){
        return dishRepository.findById(targetDish);
    }
}

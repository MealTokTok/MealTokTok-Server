package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishStoreReader {

    private final DishStoreRepository dishStoreRepository;

    public DishStore read(TargetDishStore targetStore){
        return dishStoreRepository.findById(targetStore);
    }
}

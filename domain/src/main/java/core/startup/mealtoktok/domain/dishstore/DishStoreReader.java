package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishStoreReader {

    private final DishStoreRepository dishStoreRepository;

    public DishStore read(TargetDishStore targetStore) {
        return dishStoreRepository.findById(targetStore);
    }
}

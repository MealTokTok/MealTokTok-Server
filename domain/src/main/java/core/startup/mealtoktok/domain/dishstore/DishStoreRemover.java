package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishStoreRemover {

    private final DishStoreRepository dishStoreRepository;

    public void remove(TargetDishStore targetDishStore) {
        dishStoreRepository.delete(targetDishStore);
    }
}

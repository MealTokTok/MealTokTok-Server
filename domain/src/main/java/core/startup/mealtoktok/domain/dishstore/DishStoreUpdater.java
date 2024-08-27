package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishStoreUpdater {

    private final DishStoreValidator dishStoreValidator;
    private final DishStoreRepository dishStoreRepository;

    public void update(DishStore dishStore, DishStoreInfo dishStoreInfo) {
        dishStoreValidator.validate(dishStore, dishStoreInfo.storeName());
        dishStoreRepository.update(dishStore, dishStoreInfo);
    }
}

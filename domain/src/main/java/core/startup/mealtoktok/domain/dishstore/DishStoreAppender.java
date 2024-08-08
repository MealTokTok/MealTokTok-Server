package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishStoreAppender {

    private final DishStoreValidator dishStoreValidator;
    private final DishStoreRepository dishStoreRepository;

    public void append(DishStoreInfo dishStoreInfo) {
        dishStoreValidator.validate(dishStoreInfo.storeName());
        dishStoreRepository.save(dishStoreInfo);
    }
}

package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.exception.DishStoreNameAlreadyExitsException;

@Component
@RequiredArgsConstructor
public class DishStoreValidator {

    private final DishStoreRepository dishStoreRepository;

    public void validate(String DishStoreName) {
        if (dishStoreRepository.existsByDishStoreName(DishStoreName)) {
            throw DishStoreNameAlreadyExitsException.EXCEPTION;
        }
    }
}

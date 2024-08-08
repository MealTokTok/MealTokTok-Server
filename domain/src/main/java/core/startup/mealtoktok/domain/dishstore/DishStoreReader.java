package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishStoreReader {

    private final DishStoreRepository dishStoreRepository;

    public DishStore read(TargetDishStore targetStore) {
        return dishStoreRepository.findById(targetStore);
    }

    public List<DishStore> readAll() {
        return dishStoreRepository.findAll();
    }
}

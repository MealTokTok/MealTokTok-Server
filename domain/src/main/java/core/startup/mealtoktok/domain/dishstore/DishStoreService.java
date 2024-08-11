package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DishStoreService {

    private final DishStoreAppender dishStoreAppender;
    private final DishStoreRemover dishStoreRemover;
    private final DishStoreReader dishStoreReader;
    private final DishStoreUpdater dishStoreUpdater;

    public void createDishStore(DishStoreInfo dishStoreInfo) {
        dishStoreAppender.append(dishStoreInfo);
    }

    public void deleteDishStore(TargetDishStore targetDishStore) {
        dishStoreReader.read(targetDishStore);
        dishStoreRemover.remove(targetDishStore);
    }

    public void updateDishStore(TargetDishStore targetDishStore, DishStoreInfo dishStoreInfo) {
        dishStoreReader.read(targetDishStore);
        dishStoreUpdater.update(targetDishStore, dishStoreInfo);
    }

    public DishStore readDishStore(TargetDishStore targetDishStore) {
        return dishStoreReader.read(targetDishStore);
    }

    public List<DishStore> readDishStores() {
        return dishStoreReader.readAll();
    }
}

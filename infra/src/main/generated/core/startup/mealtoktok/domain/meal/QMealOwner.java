package core.startup.mealtoktok.domain.meal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMealOwner is a Querydsl query type for MealOwner
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMealOwner extends BeanPath<MealOwner> {

    private static final long serialVersionUID = 1283603748L;

    public static final QMealOwner mealOwner = new QMealOwner("mealOwner");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QMealOwner(String variable) {
        super(MealOwner.class, forVariable(variable));
    }

    public QMealOwner(Path<? extends MealOwner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMealOwner(PathMetadata metadata) {
        super(MealOwner.class, metadata);
    }

}


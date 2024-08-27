package core.startup.mealtoktok.infra.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMealOwnerVO is a Querydsl query type for MealOwnerVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMealOwnerVO extends BeanPath<MealOwnerVO> {

    private static final long serialVersionUID = -1330331328L;

    public static final QMealOwnerVO mealOwnerVO = new QMealOwnerVO("mealOwnerVO");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QMealOwnerVO(String variable) {
        super(MealOwnerVO.class, forVariable(variable));
    }

    public QMealOwnerVO(Path<? extends MealOwnerVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMealOwnerVO(PathMetadata metadata) {
        super(MealOwnerVO.class, metadata);
    }

}


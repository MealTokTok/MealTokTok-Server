package core.startup.mealtoktok.infra.jpa.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import core.startup.mealtoktok.common.dto.Cursor;

public class PagingUtil {

    public static PageRequest toPageRequest(Cursor cursor) {
        // 여러 정렬 기준을 처리할 Sort 객체 생성
        Sort sort =
                Sort.by(
                        cursor.sortOrders().stream()
                                .map(
                                        order ->
                                                new Sort.Order(
                                                        Sort.Direction.fromString(
                                                                order.direction().name()),
                                                        order.key()))
                                .toList());

        return PageRequest.of(cursor.page(), cursor.size(), sort);
    }
}

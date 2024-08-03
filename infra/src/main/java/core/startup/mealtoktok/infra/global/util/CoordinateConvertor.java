package core.startup.mealtoktok.infra.global.util;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import core.startup.mealtoktok.domain.user.Coordinate;

public class CoordinateConvertor {

    public static Point convertToPoint(Coordinate coordinate) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(
                new org.locationtech.jts.geom.Coordinate(
                        coordinate.longitude(), coordinate.latitude()));
    }
}

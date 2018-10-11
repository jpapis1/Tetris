package tetris.logic.math;
import java.util.concurrent.Flow;

import static java.lang.Math.*;

public class RotateVector {
    public static Point rotate(Point point, int degrees) {
        double angle = Math.toRadians(degrees);
            double x = point.x * round(cos(angle)) - point.y * round(sin(angle));
            double y = point.x * round(sin(angle)) + point.y * round(cos(angle));
            point.x = (int)x;
            point.y = (int)y;
        return point;
    }
}

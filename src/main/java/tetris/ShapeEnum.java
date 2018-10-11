package tetris;
import tetris.logic.math.Point;
import tetris.logic.math.RotateVector;

import java.util.Arrays;
import java.util.Random;

public enum ShapeEnum {
    SQUARE(),
    LINE(),
    LEFTELBOW(),
    RIGHTELBOW(),
    LEFTTURN(),
    RIGHTTURN(),
    PYRAMID();



    public Point[] getMatrix(int rotateAngle) {
        Point[] shape = new Point[4];
        switch (this) { // y + 1
            case LINE:
                shape = new Point[]{
                    new Point(0, 1),
                    new Point(1, 1),
                    new Point(2, 1),
                    new Point(3, 1)
                };
                break;
            case SQUARE:
                shape = new Point[]{
                        new Point(0, 1),
                        new Point(0, 0),
                        new Point(1, 1),
                        new Point(1, 0)
                };
                break;
            case PYRAMID:
                shape = new Point[]{
                        new Point(0, 1),
                        new Point(1, 1),
                        new Point(1, 0),
                        new Point(2, 1)
                };
                break;
            case LEFTTURN:
                shape = new Point[]{
                        new Point(0, 0),
                        new Point(1, 0),
                        new Point(1, 1),
                        new Point(2, 1)
                };
                break;
            case LEFTELBOW:
                shape = new Point[]{
                        new Point(0, 1),
                        new Point(0, 0),
                        new Point(1, 1),
                        new Point(2, 1)
                };
                break;
            case RIGHTTURN:
                shape = new Point[]{
                        new Point(0, 1),
                        new Point(1, 1),
                        new Point(1, 0),
                        new Point(2, 0)
                };
                break;
            case RIGHTELBOW:
                shape = new Point[]{
                        new Point(2, 0),
                        new Point(2, 1),
                        new Point(1, 1),
                        new Point(0, 1)
                };
                break;
        }
        Arrays.stream(shape).forEach(point -> RotateVector.rotate(point,rotateAngle));
        return shape;

    }
    public static ShapeEnum getRandomShapeEnum() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}


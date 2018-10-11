package tetris.logic;

import tetris.ShapeEnum;
import tetris.graphics.shapes.*;
import tetris.graphics.shapes.Shape;
import tetris.logic.math.Point;

import java.awt.*;
import java.util.Random;

public class Stats {
    static Shape currentPiece;
    static Shape nextPiece;
    static Shape previousPiece;
    static Point previousPoint;
    static int points = 0;

    public static Shape getPreviousPiece() {
        return previousPiece;
    }

    public static void setPreviousPiece(Shape previousPiece) {
        Stats.previousPiece = previousPiece;
    }

    public static Point getPreviousPoint() {
        return previousPoint;
    }

    public static void setPreviousPoint(Point previousPoint) {
        Stats.previousPoint = previousPoint;
    }

    public static Shape generateRandomShape(Color color) {
        ShapeEnum myEnum = ShapeEnum.getRandomShapeEnum();
        switch (myEnum) {
            case LINE:
                return new Line(color);
            case RIGHTELBOW:
                return new RightElbow(color);
            case RIGHTTURN:
                return new RightTurn(color);
            case LEFTELBOW:
                return new LeftElbow(color);
            case LEFTTURN:
                return new LeftTurn(color);
            case PYRAMID:
                return new Pyramid(color);
            case SQUARE:
                return new Square(color);
        }
        return null;
    }
    public static Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        Stats.points = points;
    }

    public static Shape getCurrentPiece() {
        return currentPiece;
    }

    public static void setCurrentPiece(Shape currentPiece) {
        Stats.currentPiece = currentPiece;
    }

    public static Shape getNextPiece() {
        return nextPiece;
    }

    public static void setNextPiece(Shape nextPiece) {
        Stats.nextPiece = nextPiece;
    }
}

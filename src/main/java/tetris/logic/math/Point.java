package tetris.logic.math;

import tetris.graphics.shapes.Shape;

public class Point {
    public int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point convertToMatrixPoint() {
        return new Point(this.x/Shape.blockSize,this.y/Shape.blockSize);
    }
    public Point convertToGraphicsPoint() {
        return new Point(this.x*Shape.blockSize,this.y*Shape.blockSize);
    }
    public static Point add(Point a, Point b) {
        return new Point(a.x + b.x,a.y + b.y);
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}

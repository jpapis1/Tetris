package tetris.graphics.shapes;

import tetris.ShapeEnum;
import tetris.logic.math.Point;
import java.awt.*;

public abstract class Shape {
    public ShapeEnum shape;
    public static int blockSize = 20; // of one building block
    public Color color;

    public abstract void draw(Point point,int rotation, Graphics g);
    protected void drawShape(Point[] matrix, Graphics g, int x, int y) {
        for(Point myPoint: matrix) {
            g.drawRect((x + myPoint.x*blockSize),(y + myPoint.y*blockSize),blockSize,blockSize);
            g.setColor(color);
            g.fillRect((x + myPoint.x*blockSize),(y + myPoint.y*blockSize),blockSize,blockSize);
            g.setColor(Color.BLACK);
        }
        // f(zmienna) = x + zmienna*blockSize
        // -1 = x-blockSize
        // 0 = x
        // 1 = x+blockSize
        // 2 = x+2*blockSize ...
    }

}

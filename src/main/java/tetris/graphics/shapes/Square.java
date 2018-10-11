package tetris.graphics.shapes;

import tetris.ShapeEnum;
import tetris.logic.math.Point;

import java.awt.*;

public class Square extends Shape{

    public Square(Color color) {
        this.color = color;
        this.shape = ShapeEnum.SQUARE;
    }

    @Override
    public void draw(Point point,int rotation, Graphics g) {
        Point[] matrix = shape.getMatrix(rotation);
        drawShape(matrix,g,point.x,point.y);
    }

}

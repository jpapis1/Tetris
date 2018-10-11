package tetris.graphics.shapes;
import tetris.ShapeEnum;
import tetris.logic.math.Point;

import java.awt.*;

public class RightElbow extends Shape {

    public RightElbow(Color color) {
        this.color = color;
        this.shape = ShapeEnum.RIGHTELBOW;
    }


    @Override
    public void draw(tetris.logic.math.Point point,int rotation, Graphics g) {
        Point[] matrix = shape.getMatrix(rotation);
        drawShape(matrix,g,point.x,point.y);
    }
}

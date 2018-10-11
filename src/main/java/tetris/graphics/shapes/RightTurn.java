package tetris.graphics.shapes;
import tetris.ShapeEnum;
import tetris.logic.math.Point;

import java.awt.*;

public class RightTurn extends Shape {

    public RightTurn(Color color) {
        this.color = color;
        this.shape = ShapeEnum.RIGHTTURN;
    }

    @Override
    public void draw(Point point,int rotation, Graphics g) {
        Point[] matrix = shape.getMatrix(rotation);
        drawShape(matrix,g,point.x,point.y);
    }

}

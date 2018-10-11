package tetris.graphics.shapes;
import tetris.ShapeEnum;
import tetris.logic.math.Point;

import java.awt.*;

public class LeftElbow extends Shape {
    public LeftElbow(Color color) {
        this.color = color;
        this.shape = ShapeEnum.LEFTELBOW;
    }

    @Override
    public void draw(Point point, int rotation, Graphics g) {
        Point[] matrix = shape.getMatrix(rotation);
        drawShape(matrix,g,point.x,point.y);
        /*
        int x = point.x;
        int y = point.y;
        Point[] matrix = shape.getMatrix(rotation);
        g.drawRect(x, y, blockSize, blockSize);
        g.drawRect(x, y-blockSize, blockSize, blockSize);
        g.drawRect(x+blockSize, y, blockSize, blockSize);
        g.drawRect(x+blockSize*2, y, blockSize, blockSize);

        g.setColor(color);
        g.fillRect(x, y, blockSize, blockSize);
        g.fillRect(x, y-blockSize, blockSize, blockSize);
        g.fillRect(x+blockSize, y, blockSize, blockSize);
        g.fillRect(x+blockSize*2, y, blockSize, blockSize);
        g.setColor(Color.BLACK);
        */
    }

}

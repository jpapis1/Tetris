package tetris.logic;
import tetris.logic.math.Point;
import java.awt.*;
import java.util.Arrays;

public class Board {
    MiniSquare boardArray[][] = new MiniSquare[15][25];
    Point recentlyAddedPosition;
    Point[] recentlyAddedMatrix;
    public Level level = new Level();
    int blocksPassed;

    public Point[] getRecentlyAddedShape() {
        return recentlyAddedMatrix;
    }

    public Board() {
        for(int i = 0;i<15;i++) {
            for(int j = 0;j<25; j++) {
                boardArray[i][j] = new MiniSquare(null);
            }
        }
        blocksPassed = 0;
    }
    public void blockPassed() {
        blocksPassed++;
        level.addPoints();
    }
    public boolean isLimitReached() {
        if(blocksPassed>=10) {
            blocksPassed = 0;
            return true;
        } else {
            return false;
        }
    }
    public void nextLevel() {
        level.nextLevel();
    }

    public MiniSquare[][] getBoardArray() {
        return boardArray;
    }

    public synchronized void addSquare(Point point, Color color) {
        try {
            boardArray[point.x][point.y] = new MiniSquare(color);
        } catch (ArrayIndexOutOfBoundsException e ) { };
    }
    public synchronized void addShape(Point position,Point[] matrix, Color color) {
        recentlyAddedPosition = position;
        recentlyAddedMatrix = matrix;
//        System.out.println("ADDED");
//        System.out.println("-------------------------------------");
        Arrays.stream(matrix).forEach(point -> {
            point.x = point.x + position.x;
            point.y = point.y + position.y;
//            System.out.println("Added " + point.x + " " + point.y);
            addSquare(point,color);
        });
//        System.out.println("-------------------------------------");
    }
    public synchronized void dropSquare(Point point) {
        try {
            boardArray[point.x][point.y] = new MiniSquare(null);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }
    public synchronized void dropRecentShape() {
        System.out.println("DROPPED");
        System.out.println("-------------------------------------");
        Arrays.stream(recentlyAddedMatrix).forEach(point -> {
            System.out.println("Dropped " + point.x + " " + point.y);
            dropSquare(point);
        });
        System.out.println("-------------------------------------");
    }
    public boolean blockExists(Point point) {
        return boardArray[point.x][point.y].color!=null;
    }
    public boolean blockExistsInMatrix(Point block, Point[] matrix) {
        for (Point point: matrix ) {
            if(point.x==block.x && point.y==block.y) {
                return true;
            }
        }
        return false;
    }
    public synchronized boolean isColiding(Point position,Point[] matrix,Direction direction) { // only works for matrix (not graphics) points!! x: 0-14 y:0-24 LEFT RIGHT UP
        int i = 1;
        //System.out.println(position);
        Point[] matrixWithAddedPosition = new Point[4];
        int j = 0;
        for (Point point: matrix) {
            matrixWithAddedPosition[j] = Point.add(point,position);
            j++;
        }
        for (Point point: matrix ) {
            point = Point.add(point,position);
            //System.out.println("Point " + i + ": " + point);
            //if(point.x>=14 || point.x<=0 || point.y>=24 || point.y<=0) { // collision with board]
            //    System.out.println("BOARD COLLISION: " + point);
            //    return true;
            //}
            Point block;
            switch (direction) { // collision with blocks
                case UP: {
                    block = new Point(point.x, point.y - 1);
                    if(point.y<=0) return true;
                    if (blockExists(block) && !blockExistsInMatrix(block, matrixWithAddedPosition)) return true;
                    break;
                }
                case DOWN: {
                    block = new Point(point.x, point.y + 1);
                    if(point.y>=24){
                        System.out.println("BOARD COLLISION: " + point);
                        return true;
                    }
                    if (blockExists(block) && !blockExistsInMatrix(block, matrixWithAddedPosition)) {
                        System.out.println("COLLISION: " + block + " with " + point);
                        return true;
                    }
                    break;
                }
                case LEFT: {
                    block = new Point(point.x - 1, point.y);
                    if(point.x<=0) return true;
                    if (blockExists(block) && !blockExistsInMatrix(block, matrixWithAddedPosition)) return true;
                    break;
                }
                case RIGHT: {
                    block = new Point(point.x + 1, point.y);
                    if(point.x>=14) return true;
                    if (blockExists(block) && !blockExistsInMatrix(block, matrixWithAddedPosition)) return true;
                    break;
                }
            }
            //if(blockExists(block) && !blockExistsInMatrix(block,matrixWithAddedPosition)) {
             //   System.out.println("BLOCK COLLISION: " + block + " with " + point);
             //   return true;
            //}
            //System.out.println("Point " + i + " " + Point.add(point,position));
            i++;
        }
        return false;
    }

}

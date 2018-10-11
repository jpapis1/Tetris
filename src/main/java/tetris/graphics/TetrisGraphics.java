package tetris.graphics;

import tetris.graphics.shapes.Shape;
import tetris.logic.Board;
import tetris.logic.Direction;
import tetris.logic.Stats;
import tetris.logic.math.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;


public class TetrisGraphics extends JPanel implements Runnable, KeyListener{
    Thread thread;
    static Board board;
    static Point graphicsPoint = new Point(120,0);
    static int rotation = 0;
    static Timer timer = new Timer();

    static TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            TetrisGraphics.graphicsPoint.y = TetrisGraphics.graphicsPoint.y + Shape.blockSize;
            checkCollision();
        }
    };
    public void start() {
        if(thread == null) {
            thread = new Thread(this);
                Stats.setCurrentPiece(Stats.generateRandomShape(Stats.generateRandomColor()));
                Stats.setNextPiece(Stats.generateRandomShape(Stats.generateRandomColor()));
                this.addKeyListener(this);
                board = new Board();
                board.addShape(graphicsPoint.convertToMatrixPoint(),
                        Stats.getCurrentPiece().shape.getMatrix(rotation),
                        Stats.getCurrentPiece().color);
                board.addSquare(new Point(5,7),Color.BLUE);
            thread.start();
        }
    }
    public static synchronized void checkCollision() {
        if(board.isColiding(graphicsPoint.convertToMatrixPoint(),Stats.getCurrentPiece().shape.getMatrix(rotation), Direction.DOWN)) {
            board.addShape(new Point(graphicsPoint.convertToMatrixPoint().x,graphicsPoint.convertToMatrixPoint().y+1),
                    Stats.getCurrentPiece().shape.getMatrix(rotation),
                    Stats.getCurrentPiece().color);
             //refresh(g);
            System.out.println(graphicsPoint.convertToMatrixPoint());
        } else {
            board.dropRecentShape();
            board.addShape(graphicsPoint.convertToMatrixPoint(),
                    Stats.getCurrentPiece().shape.getMatrix(rotation),
                    Stats.getCurrentPiece().color);
        }
    }
    public synchronized void paint(Graphics g) {
        start();

        paintComponent(g);
        drawInfoText(g);
        //g.setColor(Color.black);
//        if (board.isColiding(graphicsPoint.convertToMatrixPoint(),Stats.getCurrentPiece().shape.getMatrix(rotation), Direction.DOWN)) { //
         if(graphicsPoint.y >= 480) {
            board.addShape(new Point(graphicsPoint.convertToMatrixPoint().x,graphicsPoint.convertToMatrixPoint().y+1),
                    Stats.getCurrentPiece().shape.getMatrix(rotation),
                    Stats.getCurrentPiece().color);
            System.out.println(graphicsPoint.convertToMatrixPoint());
            refresh(g);

        } else {
            drawBoard(g);
           // board.dropRecentShape();
            board.addShape(graphicsPoint.convertToMatrixPoint(),
                    Stats.getCurrentPiece().shape.getMatrix(rotation),
                    Stats.getCurrentPiece().color);
        }
        //} else {

        //    board.addShape(Stats.getPreviousPoint(), Stats.getPreviousPiece().shape.getMatrix(rotation), Stats.getPreviousPiece().color);
        //    System.out.println("Collision");
       //     graphicsPoint.y = 0;
       // }
        Stats.getNextPiece().draw(new Point(360, 100),0, g);
        g.setColor(Color.BLACK);
        for(int i = 0;i<320;i = i + 20) {
            g.drawLine(i, 0, i, 500);
        }
        for(int i = 0;i<520;i = i + 20) {
            g.drawLine(0, i, 300, i);
        }
    }
    public synchronized void refresh(Graphics g) {

        graphicsPoint.y = 0;
        graphicsPoint.x = 120;
        rotation = 0;
        Stats.setCurrentPiece(Stats.getNextPiece());
        Stats.setNextPiece(Stats.generateRandomShape(Stats.generateRandomColor()));
        board.blockPassed();
        if (board.isLimitReached()) {
            board.nextLevel();
            System.out.println("NEXT LEVEL : " + board.level.getLevel());
            System.out.print("SPEED: ");
            System.out.print(board.level.getSpeed());
            timer.cancel();
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    TetrisGraphics.graphicsPoint.y = TetrisGraphics.graphicsPoint.y + Shape.blockSize;
                    // timer.scheduleAtFixedRate(timerTask,0,1000);
                    checkCollision();
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, board.level.getSpeed());

        }
        drawBoard(g);
    }
    @Override
    public void run() {
        timer.scheduleAtFixedRate(timerTask,0,board.level.getSpeed());

        while (true) {
            this.repaint();
        }
    }
    public void drawInfoText(Graphics g) {
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.setColor(Color.red);

        g.drawString("LEVEL " + board.level.getLevel(),345,40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Helvetica", Font.PLAIN, 15));
        g.drawString("POINTS: " + board.level.getPoints(),340,60);
    }
    public void drawBoard(Graphics g) {
        int x = 0;
        int y = 0;
        for(int i = 0;i<500;i = i + Shape.blockSize) {
            for(int j = 0; j<300; j = j + Shape.blockSize) {
                if(board.getBoardArray()[x][y].getColor()!=null) {
                    g.setColor(board.getBoardArray()[x][y].getColor());
                    g.fillRect(j,i,Shape.blockSize,Shape.blockSize);
                }
                g.setColor(Color.BLACK);
                //g.setColor(Color.BLUE);
                //g.fillRect(i,j,Shape.blockSize,Shape.blockSize);
                x++;
            }
            y++;
            x = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT :
                if (!board.isColiding(graphicsPoint.convertToMatrixPoint(),Stats.getCurrentPiece().shape.getMatrix(rotation), Direction.RIGHT)) {
                    graphicsPoint.x = graphicsPoint.x + 20;
                }
                checkCollision();
                break;
            case KeyEvent.VK_LEFT :
                if (!board.isColiding(graphicsPoint.convertToMatrixPoint(),Stats.getCurrentPiece().shape.getMatrix(rotation), Direction.LEFT)) {

                    graphicsPoint.x = graphicsPoint.x - 20;
                }
                checkCollision();
                break;
            case KeyEvent.VK_SPACE :
                graphicsPoint.y = 480;
                checkCollision();
                break;
            case KeyEvent.VK_UP :
                    rotation = rotation + 90;
                    if (rotation == 360 || rotation == -360) {
                        rotation = 0;
                    }
                checkCollision();
                break;
            case KeyEvent.VK_DOWN :
                if (!board.isColiding(graphicsPoint.convertToMatrixPoint(),Stats.getCurrentPiece().shape.getMatrix(rotation), Direction.DOWN)) {
                    graphicsPoint.y = graphicsPoint.y + 20;
                } else {

                }
                checkCollision();
                break;
        }
    }

    @Override
    public synchronized void  keyReleased(KeyEvent e) {

    }
}

package tetris.graphics;
import javax.swing.*;
public class TetrisWindow extends JFrame {
    private TetrisWindow() {

        this.setTitle("Tetris - Jacek Papis 2018");
        TetrisGraphics  myTetris = new TetrisGraphics();
        this.getContentPane().add(new TetrisGraphics());
        //this.getContentPane().add(new InfoPanel());
        this.addKeyListener(myTetris);
        this.setSize(500, 550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    private static volatile TetrisWindow instance = null;

    public static TetrisWindow getInstance() {
        if (instance == null) {
            synchronized (TetrisWindow.class) {
                if (instance == null) {
                    instance = new TetrisWindow();
                }
            }
        }
        return instance;
    }

}
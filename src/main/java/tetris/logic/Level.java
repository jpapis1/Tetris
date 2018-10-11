package tetris.logic;

public class Level {
    int level;
    int points;
    public Level() {
        level = 1;
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }
    public void nextLevel() {
        if(level<10) {
            level++;
        }
    }
    public void addPoints() {
        points = points + 10;
    }
    public int getSpeed (){
        switch (level) {
            case 1: return 1000;
            case 2: return 900;
            case 3: return 800;
            case 4: return 700;
            case 5: return 600;
            case 6: return 500;
            case 7: return 400;
            case 8: return 300;
            case 9: return 200;
            case 10: return 100;
            default: return 0;
        }
    }

}

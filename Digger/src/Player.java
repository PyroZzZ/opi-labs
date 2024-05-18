import java.awt.*;

public class Player {

    private int x;
    private int y;
    private int direction; // 0 - вверх, 1 - вправо, 2 - вниз, 3 - влево

    private DiggerGame game;

    private Level level;

    public Player(Level level, DiggerGame game) {
        this.level = level;
        this.game = game;
        x = 8;
        y = 10;
        direction = 1;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int moveUp() {
        if (y <= 0) return 1;

        if (level.isCollidingWithBlock(x, y - 1)) {
            level.removeBlock(x, y - 1);
        }
        if (level.isCollidingWithGold(x, y - 1)) {
            level.removeBlock(x, y - 1);
            game.addScore(10);
        }
        if (level.isCollidingWithEnemy(x, y - 1)) {
            return -1;
        }

        y -= 1;
        direction = 0;
        return 1;
    }

    public int moveDown() {
        if (y >= level.getHeight() - 1) return 1;

        if (level.isCollidingWithBlock(x, y + 1)) {
            level.removeBlock(x, y + 1);
        }
        if (level.isCollidingWithGold(x, y + 1)) {
            level.removeBlock(x, y + 1);
            game.addScore(10);
        }
        if (level.isCollidingWithEnemy(x, y + 1)) {
            return -1;
        }

        y += 1;
        direction = 2;
        return 1;
    }

    public int moveLeft() {
        if (x <= 0) return 1;

        if (level.isCollidingWithBlock(x - 1, y)) {
            level.removeBlock(x - 1, y);
        }
        if (level.isCollidingWithGold(x - 1, y)) {
            level.removeBlock(x - 1, y);
            game.addScore(10);
        }
        if (level.isCollidingWithEnemy(x - 1, y)) {
            return -1;
        }

        x -= 1;
        direction = 3;
        return 0;
    }

    public int moveRight() {
        if (x >= level.getWidth() - 1) return 1;

        if (level.isCollidingWithBlock(x + 1, y)) {
            level.removeBlock(x + 1, y);
        }
        if (level.isCollidingWithGold(x + 1, y)) {
            level.removeBlock(x + 1, y);
            game.addScore(10);
        }
        if (level.isCollidingWithEnemy(x + 1, y)) {
            return -1;
        }

        x += 1;
        direction = 1;
        return 0;
    }
}
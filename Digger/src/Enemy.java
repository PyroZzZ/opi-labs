import java.awt.*;
import java.util.Random;

public class Enemy {

    private int x;
    private int y;
    private int direction; // 0 - вверх, 1 - вправо, 2 - вниз, 3 - влево
    private int speed;
    private Level level;

    private Random random = new Random();

    public Enemy(Level level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.speed = 1;
        this.direction = random.nextInt(4);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Движение врага
    public void move() {
        if (direction == 0 && !level.isCollidingWithBlock(x, y - speed) && !level.isCollidingWithEnemy(x, y - speed) && !level.isCollidingWithGold(x, y - speed)) {
            y -= speed;
        } else if (direction == 1 && !level.isCollidingWithBlock(x + speed, y) && !level.isCollidingWithEnemy(x + speed, y) && !level.isCollidingWithGold(x + speed, y)) {
            x += speed;
        } else if (direction == 2 && !level.isCollidingWithBlock(x, y + speed) && !level.isCollidingWithEnemy(x, y + speed) && !level.isCollidingWithGold(x, y + speed)) {
            y += speed;
        } else if (direction == 3 && !level.isCollidingWithBlock(x - speed, y) && !level.isCollidingWithEnemy(x - speed, y) && !level.isCollidingWithGold(x - speed, y)) {
            x -= speed;
        } else {
            direction = random.nextInt(4);
        }
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Level {
    public static final int EMPTY = 0;
    public static final int BLOCK = 1;
    public static final int GOLD = 2;
    public static final int ENEMY = 3;
    private int[][] levelData;
    private int width;
    private int height;
    private ArrayList<Enemy> enemies = new ArrayList<>(); // Список врагов
    public Level() {
        width = 16;
        height = 12;
        levelData = new int[height][width];
        //generateRandomLevel();
    }
    // Загрузка уровня из файла
    public void loadLevel(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                for (int x = 0; x < line.length() - 1; x++) {
                    char c = line.charAt(x);
                    switch (c) {
                        case '#':
                            levelData[y][x] = BLOCK;
                            break;
                        case '$':
                            levelData[y][x] = GOLD;
                            break;
                        case 'E':
                            levelData[y][x] = ENEMY;
                            enemies.add(new Enemy(this, x, y));
                            break;
                        default:
                            levelData[y][x] = EMPTY;
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateRandomLevel() {
        Random random = new Random();

        for (int i = 0; i < width; i++) {
            levelData[0][i] = BLOCK;
            levelData[height - 1][i] = BLOCK;
        }
        for (int i = 0; i < height; i++) {
            levelData[i][0] = BLOCK;
            levelData[i][width - 1] = BLOCK;
        }

        // Генерация случайных блоков внутри уровня
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (random.nextInt(5) == 0) {
                    levelData[y][x] = BLOCK;
                } else {
                    levelData[y][x] = EMPTY;
                }
            }
        }

        // Генерация золота
        for (int i = 0; i < 5; i++) {
            int y = random.nextInt(height - 2) + 1;
            int x = random.nextInt(width - 2) + 1;
            if (levelData[y][x] == EMPTY) {
                levelData[y][x] = GOLD;
            }
        }

        // Генерация врагов
        for (int i = 0; i < 3; i++) {
            int y = random.nextInt(height - 2) + 1;
            int x = random.nextInt(width - 2) + 1;
            if (levelData[y][x] == EMPTY) {
                levelData[y][x] = ENEMY;
            }
        }
    }
    public boolean isLevelCompleted() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (levelData[y][x] == GOLD) {
                    return false;
                }
            }
        }
        return true;
    }
    public int getBlock(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height ) {
            return levelData[y][x];
        } else {
            return BLOCK;
        }
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public boolean isCollidingWithEnemy(int x, int y) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y) {
                return true;
            }
        }
        return false;
    }
    public boolean isCollidingWithBlock(int x, int y) {
        return getBlock(x, y) == BLOCK;
    }
    public boolean isCollidingWithGold(int x, int y) {
        return getBlock(x, y) == GOLD;
    }
    public void removeBlock(int x, int y) {
        levelData[y][x] = EMPTY;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
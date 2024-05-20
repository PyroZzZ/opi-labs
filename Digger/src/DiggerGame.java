import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DiggerGame extends JFrame implements KeyListener {
    private static final int WIDTH = 784;
    private static final int HEIGHT = 660;
    private Level level;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int score;
    private int lives;
    private final JPanel gamePanel;
    private BufferedImage backgroundImage;
    private BufferedImage playerImage;
    private BufferedImage blockImage;
    private BufferedImage goldImage;
    private BufferedImage enemyImage;
    private double rotationRequired = Math.toRadians (0);
    private double locationX = 24;
    private double locationY = 24;
    private AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
    private AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
    private int currentLevel = 1;
    private Timer timer;
    private int delay = 800;
    public DiggerGame() {
        setTitle("Digger");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            backgroundImage = ImageIO.read(getClass().getResource("img/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                drawGame(g);
            }
        };
        add(gamePanel);
        addKeyListener(this);

        // Загрузка изображений
        try {
            playerImage = ImageIO.read(getClass().getResource("img/player.png"));
            blockImage = ImageIO.read(getClass().getResource("img/block.png"));
            goldImage = ImageIO.read(getClass().getResource("img/gold.png"));
            enemyImage = ImageIO.read(getClass().getResource("img/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer = new Timer(delay, e -> {
            updateGame();
        });
        timer.start();

        initGame();

        setVisible(true);
    }
    private void initGame() {
        level = new Level();
        player = new Player(level, this);
        score = 0;
        lives = 3;
        level.loadLevel("Digger\\src\\level1.txt");
        gamePanel.repaint();
    }
    private void drawGame(Graphics g) {
        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                int blockType = level.getBlock(x, y);
                switch (blockType) {
                    case Level.BLOCK:
                        g.drawImage(blockImage, x * 48, y * 48, null);
                        break;
                    case Level.GOLD:
                        g.drawImage(goldImage, x * 48, y * 48, null);
                        break;
                }
            }
        }

        enemies = level.getEnemies();
        enemies.forEach(enemy -> {
            g.drawImage(enemyImage, enemy.getX() * 48, enemy.getY() * 48, null);
        });

        g.drawImage(op.filter(playerImage, null), player.getX() * 48, player.getY() * 48, null);

        g.setColor(new Color(213, 105, 60));
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Очки: " + score, 10, getHeight() - 50);
        g.drawString("Жизни: " + lives, 200, getHeight() - 50);
    }
    private void updateGame() {
        enemies = level.getEnemies();
        enemies.forEach(enemy -> {
            enemy.move();
            if (level.isCollidingWithEnemy(player.getX(), player.getY())) {
                lives -= 1;
                if (lives >= 0) {
                    player.setPosition(8, 10);
                }
                else {
                    finalScreen();
                }
            }
        });

        if (level.isLevelCompleted()) {
            nextLevel();
        }

        gamePanel.repaint();
    }
    private void nextLevel() {
        currentLevel++;
        if (currentLevel > 3) {
            finalScreen();
        }

        enemies.clear();
        delay -= 300;
        level.loadLevel("Digger\\src\\level" + currentLevel + ".txt");
        player.setPosition(8, 10);
    }
    private void finalScreen() {
        timer.stop();

        JDialog dialog = new JDialog(this, "Победа!", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitle = new JLabel("Вы победили!");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelScore = new JLabel("Ваш счет: " + score);
        labelScore.setFont(new Font("Arial", Font.PLAIN, 18));
        labelScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (lives < 0) {
            dialog.setTitle("Увы");
            labelTitle.setText("Вы проиграли");
        }

        panel.add(labelTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(labelScore);

        dialog.add(panel);

        dialog.setVisible(true);
        StartScreen startScreen = new StartScreen();
        this.dispose();
    }
    public void addScore(int value) {
        score += value;
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (player.moveUp() == -1) {
                    lives -= 1;
                    if (lives >= 0) {
                        player.setPosition(8, 10);
                    }
                    else {
                        finalScreen();
                    }
                }
                rotationRequired = Math.toRadians(270);
                break;
            case KeyEvent.VK_DOWN:
                if (player.moveDown() == -1) {
                    lives -= 1;
                    if (lives >= 0) {
                        player.setPosition(8, 10);
                    }
                    else {
                        finalScreen();
                    }
                }
                rotationRequired = Math.toRadians(90);
                break;
            case KeyEvent.VK_LEFT:
                if (player.moveLeft() == -1) {
                    lives -= 1;
                    if (lives >= 0) {
                        player.setPosition(8, 10);
                    }
                    else {
                        finalScreen();
                    }
                }
                rotationRequired = Math.toRadians(180);
                break;
            case KeyEvent.VK_RIGHT:
                if (player.moveRight() == -1) {
                    lives -= 1;
                    if (lives >= 0) {
                        player.setPosition(8, 10);
                    }
                    else {
                        finalScreen();
                    }
                }
                rotationRequired = Math.toRadians(0);
                break;
        }
        tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        gamePanel.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) { }
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class StartScreen extends JFrame
{
    private static final long serialVersionUID = 1L;
    public StartScreen ()
    {
        JDialog dialog = new JDialog(this, "Digger", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitle = new JLabel("Игра Digger!");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 28));
        labelTitle.setForeground(new Color(213, 105, 60));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Начать игру");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(labelTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(startButton);
        dialog.add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    dialog.setVisible(false);
                    new DiggerGame();
                });
            }
        });

        dialog.setVisible(true);
    }
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new StartScreen();
    }
}
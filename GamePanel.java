import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private List<Shot> shots;
    private List<enemy> enemies;

    public GamePanel(List<Shot> shots, List<enemy> enemies) {
        this.shots = shots;
        this.enemies = enemies;
        setOpaque(false); // Optional: if you want transparency
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shot shot : shots) {
            shot.draw(g);
        }
        for (enemy enemy : enemies) {
            // Assuming enemy has a draw method
            enemy.draw(g);
        }
    }
}

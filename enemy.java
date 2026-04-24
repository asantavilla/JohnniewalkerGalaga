import java.awt.*;

public class enemy {
    private int x, y; // Position of the enemy
    private int speed = 2; // Speed of the enemy when it moves downwards
    private Image image; // Image of the enemy

    public enemy(int startX, int startY, Image enemyImage, int speed) {
        this.x = startX;
        this.y = startY;
        this.image = enemyImage;
        this.speed = speed;
    }
    public void update() 
    {
        y += speed;
    }
    public void draw(Graphics g) 
    {
        g.drawImage(image, x, y, null);
    }
    public boolean isOffScreen(int screenHeight) 
    {
        return y > screenHeight;
    }
    //getting the position of the enemy if needed
    public int getX() { return x; }
    public int getY() { return y; }
    // get dimensions for collision detection
    public int getWidth() { return image != null ? image.getWidth(null) : 0; }
    public int getHeight() { return image != null ? image.getHeight(null) : 0; }
}

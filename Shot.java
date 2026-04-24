import java.awt.Graphics;
import java.awt.Image;

public class Shot {
    private int x, y; // Position of the shot
    private int speed = 5;//speed of the shot when it moves upwards
    private Image image; //image of the shot that goes up

    public Shot(int startX, int startY, Image shotImage){
        this.x = startX;
        this.y = startY;
        this.image = shotImage;
    }
    //update the shots position when it is moving upwards
    public void update(){
        y -=speed;
    }
    //drawing the shot on the screen
    public void draw(Graphics g){
        g.drawImage(image, x, y,null);
    }
    //check if the shot has been moved off the screen
    public boolean isOffScreen(){
        return y +image.getHeight(null)<0;
    }
    //getters for the posotiion if needed
    public int getX(){return x;}
    public int getY(){return y;}
    // get dimensions for collision detection
    public int getWidth(){
        return image != null ? image.getWidth(null) : 0;
    }
    public int getHeight(){
        return image != null ? image.getHeight(null) : 0;
    }
}
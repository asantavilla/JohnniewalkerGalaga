//imports
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class App 
{
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("THE WINDOW");
        frame.setSize(1000, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new java.awt.Color(204, 166, 166));

        // Arrays for shots and enemies
        ArrayList<Shot> shots = new ArrayList<>();
        ArrayList<enemy> enemies = new ArrayList<>();
        // spawn counter (used to increase enemy speed) and shot cooldown tracker
        final int[] enemiesSpawned = new int[]{0};
        final long[] lastShotTime = new long[]{0L}; // millis

        // GamePanel for drawing shots and enemies
        GamePanel gamePanel = new GamePanel(shots, enemies);
        gamePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(gamePanel);

        // ... timers will be created later after images are loaded so we can use the image


        
        //button
        JButton button = new JButton();
        button.setSize(new Dimension(50, 50));
        button.setBounds(400, 50, 70, 30);
        frame.add(button);
        frame.setLayout(null);
        button.setText("Enter");


        //button 2
        //JButton button2 = new JButton();
       //// button2.setSize(new Dimension(50, 50));
        //button2.setBounds(600, 700, 150, 90);
        //frame.add(button2);
        //frame.setLayout(null);
       // button2.setText("is this working?");
        //image of luke belmer
        ImageIcon lukebelmer = new ImageIcon("C:\\Users\\fastf\\Programs\\first\\Test\\src\\images\\lukebelmer.jpg");
        JLabel label = new JLabel (lukebelmer);
        label.setBounds(400, 630, lukebelmer.getIconWidth()-100, lukebelmer.getIconHeight()-30);
        frame.add(label);
        label.setVisible(false);

        //Image of johnie walker
        ImageIcon jonnie = new ImageIcon("C:\\Users\\fastf\\Programs\\first\\Test\\src\\images\\johnnie walker.jpg");
        Image jonnieimmage = jonnie.getImage();
        JLabel label2 = new JLabel (jonnie);
        label2.setBounds(label.getX(), label.getY()-100, jonnie.getIconWidth()-150, jonnie.getIconHeight()-0);
        frame.add(label2);
        label2.setVisible(false);
        //frame.pack();
        frame.setVisible(true);
        //score keeping (use mutable holder so inner classes can modify)
        final int[] score = new int[]{0};
        //score label
        JLabel scorelabel = new JLabel("Current Score:"+score[0]);
        frame.getContentPane().add(scorelabel);
        scorelabel.setBounds(460, -70, 200, 200);
        scorelabel.setVisible(false); 
        Font Customfont = new Font("Arial",Font.BOLD,16);
        scorelabel.setFont(Customfont);
        //image of andrew tate
        ImageIcon andrew = new ImageIcon("C:\\Users\\fastf\\Programs\\first\\Test\\src\\images\\picture of andrew tate.jpg");
        Image originalImage = andrew.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel labela = new JLabel (scaledIcon);  
        labela.setBounds(label.getX() + 200, label.getY() - 100, 200, 150);
        frame.add(labela);
        
        labela.setVisible(false);
        
        // text box (moved here so timers can reference it)

        JTextField text = new JTextField("Enter Your Name");
        frame.add(text);
        text.setBounds(400, 00, 200, 50);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (text.getText().length()<0)
                {
                    text.setText("");
                }

            }

            @Override
            public void mouseExited (MouseEvent e)
            {
                if (text.getText().isEmpty())
                {
                    text.setText("Enter Your name");
                }
                if (!text.getText().isEmpty())
                {

                }
            }

            @Override
            public void mouseEntered (MouseEvent e)
            {
                if (text.getText().length()<0)
                {
                    text.setText("");
                }
                else
                {
                    
                }
            }
            
        });  

        // Create timers now that images are available (they won't be started yet)
        Timer enemySpawnTimer = new Timer(4000, new ActionListener() {
            Random random = new Random();

            @Override
            public void actionPerformed(ActionEvent e) {
                int randomX = random.nextInt(Math.max(1, frame.getWidth()));
                // compute enemy speed: increase by 1 every 2 spawned enemies
                enemiesSpawned[0]++;
                int speed = 2 + (enemiesSpawned[0] / 2);
                // use scaledImage (from Andrew) as the enemy image; replace with your enemy image if needed
                enemies.add(new enemy(randomX, 0, scaledImage, speed));
                gamePanel.repaint();
            }
        });

        Timer updateTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = shots.size() - 1; i >= 0; i--) {
                    Shot shot = shots.get(i);
                    shot.update();
                    if (shot.isOffScreen()) {
                        shots.remove(i);
                    }
                }
                // update enemies
                for (int i = enemies.size() - 1; i >= 0; i--) {
                    enemy en = enemies.get(i);
                    en.update();
                    if (en.isOffScreen(frame.getHeight())) {
                        enemies.remove(i);
                    }
                }
                // collision detection: shots vs enemies
                for (int ei = enemies.size() - 1; ei >= 0; ei--) {
                    enemy en = enemies.get(ei);
                    boolean enemyRemoved = false;
                    int ex = en.getX();
                    int ey = en.getY();
                    int ew = en.getWidth();
                    int eh = en.getHeight();

                    for (int si = shots.size() - 1; si >= 0; si--) {
                        Shot sh = shots.get(si);
                        int sx = sh.getX();
                        int sy = sh.getY();
                        int sw = sh.getWidth();
                        int shh = sh.getHeight();

                        boolean intersects = sx < ex + ew && sx + sw > ex && sy < ey + eh && sy + shh > ey;
                        if (intersects) {
                            // remove both
                            shots.remove(si);
                            enemies.remove(ei);
                            enemyRemoved = true;
                            // increment score
                            score[0]++;
                            scorelabel.setText("Current Score:" + score[0]);
                            break;
                        }
                    }
                    if (enemyRemoved) continue;

                    // check collision with player label (Luke Belmer)
                    int px = label.getX();
                    int py = label.getY();
                    int pw = label.getWidth();
                    int ph = label.getHeight();
                    boolean hitPlayer = ex < px + pw && ex + ew > px && ey < py + ph && ey + eh > py;
                    if (hitPlayer) {
                        // stop the game timers
                        enemySpawnTimer.stop();
                        ((Timer)e.getSource()).stop();

                        // show game over dialog (modal)
                        JOptionPane.showMessageDialog(frame, "Game Over! Score: " + score[0]);

                        // reset game state so player returns to name-entry screen
                        shots.clear();
                        enemies.clear();
                        score[0] = 0;
                        // reset spawn counter and cooldown so enemies return to base speed
                        enemiesSpawned[0] = 0;
                        lastShotTime[0] = 0L;
                        scorelabel.setText("Current Score:" + score[0]);

                        // hide game UI, show entry UI
                        label.setVisible(false); // player image
                        scorelabel.setVisible(false);
                        button.setVisible(true);
                        text.setVisible(true);
                        text.setText("Enter Your Name");

                        // reset player position to starting location
                        label.setLocation(400, 630);

                        // repaint to clear any remaining drawings
                        gamePanel.repaint();
                    }
                }

                gamePanel.repaint();
            }
        });


        
        button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
        String inputText = text.getText();
        JOptionPane.showMessageDialog(frame, "You entered: " + inputText);
        button.setVisible(false);
        text.setVisible(false);
        label.setVisible(true);
        scorelabel.setVisible(true);
        labela.setVisible(false); // will turn true when game starts and are at the top
        // start the game timers now that the player has entered name / started the game
        enemySpawnTimer.start();
        updateTimer.start();
        frame.requestFocus();
            }
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e){

            }
            @Override
            public void keyReleased(KeyEvent e){
           // System.out.println("You releaseed key char:"+e.getKeyChar());
            //System.out.println("You releaseed key char:"+e.getKeyCode());
            }
            @Override
            public void keyTyped(KeyEvent e){
            switch(e.getKeyChar()){
                case 'a':
                    // move left and clamp to frame
                    int newX = label.getX() - 10;
                    newX = Math.max(0, newX);
                    label.setLocation(newX, label.getY());
                    break;
                case 'd':
                    // move right and clamp to frame
                    int newXR = label.getX() + 10;
                    newXR = Math.min(frame.getWidth() - label.getWidth(), newXR);
                    label.setLocation(newXR, label.getY());
                    break;
                case 'w':
                    // move up and clamp
                    int newY = label.getY() - 10;
                    newY = Math.max(0, newY);
                    label.setLocation(label.getX(), newY);
                    break;
                case 's':
                    // move down and clamp
                    int newYD = label.getY() + 10;
                    newYD = Math.min(frame.getHeight() - label.getHeight(), newYD);
                    label.setLocation(label.getX(), newYD);
                    break;
                case 32:
                    // shoot (2 second cooldown)
                    long now = System.currentTimeMillis();
                    if (now - lastShotTime[0] >= 2000) {
                        lastShotTime[0] = now;
                        int shotX = label.getX() + label.getWidth() / 2;
                        int shotY = label.getY();
                        shots.add(new Shot(shotX, shotY, jonnieimmage));
                    }
                    break;    
            }
            }
        });
        //old list was here
    }
}

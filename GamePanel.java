import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;


public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16 by 16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;//48 by 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 Pixels
    final int screenHeight = tileSize * maxScreenRow;//576 Pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //Wizard Sprite
    BufferedImage wizardImage;
    BufferedImage[] walkDown = new BufferedImage[15];

    int animationCounter;
    int animationFrame;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        try {
            wizardImage = ImageIO.read(new File("assets/wizard/wizard.png"));
            for (int i = 0; i < walkDown.length; i++){
                String filename = String.format("assets/wizard/down/wizard_down_%02d.png",i+1);
                walkDown[i] = ImageIO.read(new File(filename));
            }
        }catch(IOException e){
            throw new RuntimeException("Could not load wizard image",e);
        }
    }

public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
}
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime= System.nanoTime() + drawInterval;

while(gameThread != null){

    // 1 Update: update information such as character position
    update();
    //2 Draw: draw the screen with the updated information
    repaint();

    try{
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime= remainingTime/1000000;

        if(remainingTime<0){
            remainingTime = 0;
        }
        Thread.sleep((long)remainingTime);

        nextDrawTime += drawInterval;
    }
    catch(InterruptedException e){
        //TODO Auto-generated catch block
        e.printStackTrace();
    }
}
    }
    public void update(){
        if(keyH.upPressed == true){
            playerY-=playerSpeed;
        }
        else if (keyH.downPressed == true){
            playerY+=playerSpeed;
        }
        else if (keyH.leftPressed == true){
            playerX-=playerSpeed;
        }
        else if (keyH.rightPressed == true){
            playerX+=playerSpeed;
        }
        if (keyH.downPressed) {
            animationCounter++;

            if (animationCounter >= 4) {
                animationCounter = 0;
                animationFrame++;

                if (animationFrame >= walkDown.length) {
                    animationFrame = 0;
                }
            }
        } else {
            animationCounter = 0;
            animationFrame = 0;
        }
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

       g2.drawImage(walkDown[animationFrame],playerX,playerY,tileSize,tileSize,null);
        g2.dispose();
    }
}

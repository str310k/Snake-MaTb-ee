package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 500;
    private final int DOT_SIZE = 50;
    private final int ALL_DOTS = 100;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

     public GameField() {

         setBackground(Color.black);
         LoadImages();
         InitGame();
         addKeyListener(new FieldKeyListener());
         setFocusable(true);
    }

    public void InitGame() {
         dots = 3;
         for (int i = 0; i< dots; i++) {
             x[i] = 150;
             y[i] = 150;
             right = true;
             left = false;
             up = false;
             down = false;
         }
         timer = new Timer(250,this);
         timer.start();
         createApple();
    }

        public void createApple() {
         appleX = new Random().nextInt(10)*DOT_SIZE;
         appleY = new Random().nextInt(10)*DOT_SIZE;
    }

    public void LoadImages() {
         ImageIcon iiApple = new ImageIcon("Apple.png");
         apple = iiApple.getImage();
         ImageIcon iiDot = new ImageIcon("Dot.png");
         dot = iiDot.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);
            }
        }       else {
            String str = "Похер";
            Font f = new Font("Arial", Font.BOLD, 50);
            g.setColor(Color.red);
            g.setFont(f);
            g.drawString(str,200,250);
        }
    }

    public void move() {
            for (int i = dots; i > 0 ; i--) {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
            if(left) {
                x[0] -=DOT_SIZE;
            }
            if(right) {
                x[0] +=DOT_SIZE;
            }
            if(up) {
                y[0] -=DOT_SIZE;
            }
            if(down) {
                y[0] +=DOT_SIZE;
            }
    }

    public void checkApple(){
         if (x[0] == appleX && y[0] == appleY){
             dots++;
             createApple();
         }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
            if(x[0] > SIZE){
                inGame = false;
            }
            if(x[0] < 0){
                inGame = false;
            }
            if(y[0] == SIZE){
                inGame = false;
            }
            if(y[0] < 0){
                inGame = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_ENTER) {
                timer.stop();
                InitGame();
                inGame = true;
            }
        }
     }
}
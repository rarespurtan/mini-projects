package BallGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements KeyListener {
    private int barWidth = 150, barHeight = 30;
    private int barX = Window.WIDTH/2 - barWidth/2, barY = 550;
    private int deltaX = 0;
    private int barSpeed = 25;

    private int enemyX = barX, enemyY = -15;

    private int ballRadius = 10;
    private int ballX = Window.WIDTH/2 - ballRadius, ballY=Window.HEIGHT/2 - ballRadius;
    private boolean isMoving = false;
    private double velX, velY;
    private Random random;
    private double angle = 0;
    private double ballSpeed = 15;

    private int playerScore, enemyScore;

    private Timer looper = new Timer(1000 / 30, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            update();
            repaint();
        }
    });



    public Game(){
        random = new Random();
        looper.start();
        angle = Math.PI/2;//getRandomAngle(Math.toRadians(150), Math.toRadians(30));
        velX = (Math.cos(angle)*ballSpeed);
        velY = (Math.sin(angle)*ballSpeed);
    }


    private double getRandomAngle(double angleA, double angleB){
        return (random.nextDouble()*(angleB-angleA)+angleA);
    }

    private void update(){
        barX += deltaX;

        if (isMoving){
            ballX += velX;
            ballY += velY;

            if (enemyX + barWidth/2 < ballX + ballRadius){
                enemyX += barSpeed/2 - 5;
            }
            if (enemyX + barWidth/2 > ballX + ballRadius){
                enemyX -= barSpeed/2 - 5;
            }
        } else {
            ballX = Window.WIDTH/2 - ballRadius;
            ballY = Window.HEIGHT/2 - ballRadius;
        }

        if (ballY + ballRadius*2> barY && (ballX > barX && ballX < barX + barWidth)){
            angle  = -(barWidth + barX - (ballX + ballRadius))*Math.toRadians(150)/barWidth;
            velX = Math.cos(angle) * ballSpeed;
            velY = Math.sin(angle) * ballSpeed;
        }
        if (ballY < enemyY + barHeight && (ballX > enemyX && ballX < enemyX + barWidth)){
            angle  = (barWidth + enemyX - (ballX + ballRadius))*Math.toRadians(150)/barWidth;
            velX = Math.cos(angle) * ballSpeed;
            velY = Math.sin(angle) * ballSpeed;
        }
        if (ballX + ballRadius*2 > Window.WIDTH || ballX<0){
            velX *= -1;
        }

        if (ballY < 0){
            isMoving = false;
            playerScore++;
        }
        if (ballY - ballRadius*2 > Window.HEIGHT){
            isMoving = false;
            enemyScore++;
        }


        if (barX + barWidth > Window.WIDTH){
            barX = Window.WIDTH - barWidth;
        }
        if (barX < 0){
            barX = 0;
        }
    }
    private Font font = new Font("Arial", Font.BOLD, 20);
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        g.fillRect(0,0, WIDTH, HEIGHT);

        g.fillRect(barX, barY, barWidth, barHeight);

        g.fillRect(enemyX, enemyY, barWidth, barHeight);

        g.setColor(Color.BLACK);

        g.fillOval(ballX, ballY, ballRadius *2, ballRadius *2 );

        g.setFont(font);

        if(!isMoving){
            g.drawString("Press SPACE to Start", Window.WIDTH/3, Window.HEIGHT/3);
        }
        g.drawString(""+playerScore, Window.WIDTH/2, Window.HEIGHT - 100);
        g.drawString(""+enemyScore, Window.WIDTH/2, Window.HEIGHT - 500);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            deltaX = -barSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            deltaX = barSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            isMoving = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            deltaX = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            deltaX = 0;
        }
    }
}

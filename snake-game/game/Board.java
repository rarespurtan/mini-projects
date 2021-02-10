package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Board extends JPanel implements KeyListener {

    private int width = 20, height = 20, gridSize = 30;

    private int size;

    private int dir;

    private ArrayList<Point> snake = new ArrayList<Point>();

    private Point food;

    private Timer looper = new Timer(1000 / 10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
            repaint();
        }
    });

    public Board(){
        start();
        looper.start();
    }

    private void start(){
        snake.clear();
        size=1;
        dir=0;
        snake.add(new Point(width/2, height/2));
        food = setFood();
    }

    private Point setFood(){
        Point f = null;
        whileLoop:
        while (f==null){
            int x = (int) (Math.random()*width);
            int y = (int) (Math.random()*height);
            for (int i = 0; i < size; i++){
                if (snake.get(i).x == x && snake.get(i).y == y){
                    continue whileLoop;
                }
            }
            f = new Point(x,y);
        }
        return f;
    }

    private void tick(){

        for (int i = size-1; i > 0; i--){
            snake.get(i).x = snake.get(i -1).x;
            snake.get(i).y = snake.get(i -1).y;
        }


        if (dir==0){
            snake.get(0).y --;
        }
        else if (dir==1){
            snake.get(0).y ++;
        }else if (dir==2){
            snake.get(0).x ++;
        }else if (dir==3){
            snake.get(0).x --;
        }

        if (snake.get(0).x == food.x && snake.get(0).y == food.y){
            snake.add(new Point(snake.get(size -1).x , snake.get(size -1).y));
            size++;
            food= setFood();
        }

        if (snake.get(0).x > width -1){
            snake.get(0).x = 0;
        }
        if (snake.get(0).x < 0){
            snake.get(0).x = width -1;
        }
        if (snake.get(0).y > height -1){
            snake.get(0).y = 0;
        }
        if (snake.get(0).y < 0){
            snake.get(0).y = height -1;
        }

        for (int i = 2; i < size; i++){
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y){
                start();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(Color.BLACK);

        g2d.fillRect(0,0, Window.WIDTH, Window.HEIGHT);

        g2d.setColor(Color.RED);

        g2d.fillRect(food.x*gridSize, food.y*gridSize, gridSize, gridSize);

        g2d.setColor(Color.GREEN);

        for (int i = 0; i < size; i++) {
            g2d.fillRect(snake.get(i).x*gridSize, snake.get(i).y*gridSize, gridSize, gridSize);
        }

        g2d.setColor(Color.WHITE);


        for (int i = 0; i < width; i++){
            for(int j = 0; j < height; j++ ){
                g.drawRect(i*gridSize, j*gridSize, gridSize, gridSize);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && dir != 1){
            dir=0;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN && dir != 0){
            dir=1;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && dir != 3){
            dir=2;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT && dir != 2) {
            dir=3;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

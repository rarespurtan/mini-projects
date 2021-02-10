package BallGame;

import javax.swing.*;

public class Window extends JFrame {

    public static final int WIDTH = 600, HEIGHT = 600;

    public Window(){
        setTitle("BallGame");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Game game = new Game();
        add(game);
        addKeyListener(game);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}

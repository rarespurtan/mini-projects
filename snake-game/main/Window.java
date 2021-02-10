package main;

import game.Board;

import javax.swing.*;

public class Window extends JFrame {

    public static final int WIDTH = 600, HEIGHT = 630;

    private Board board;

    public Window(){
        setTitle("Snake");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        board = new Board();

        add(board);
        addKeyListener(board);
        setVisible(true);
    }


    public static void main(String[] args) {
        new Window();
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Graphicizer extends Frame implements ActionListener {
    private BufferedImage bufferedImage, bufferedImageBackup;
    private Image image;
    private Menu menu;
    private MenuBar menuBar;
    private MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    private Button button1, button2, button3, button4, button5;
    private FileDialog dialog;

    public Graphicizer() {
        setSize(400, 360);
        setTitle("Image Graphicizer");
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
//        String[] buttonNames = new String[]{"Emboss", "Sharpen", "Brighten", "Blue", "Reduce"};
//        Button btn;
//        for (int i = 0, x = 30; i < 5; i++, x += 70) {
//            btn = new Button(buttonNames[i]);
//            btn.setBounds(x, getHeight() - 50, 60, 20);
//            add(btn);
//            btn.addActionListener(this);
//        }
//
        button1 = new Button("Emboss");
        button1.setBounds(30, getHeight() - 50, 60, 20);
        add(button1);
        button1.addActionListener(this);

        button2 = new Button("Sharpen");
        button2.setBounds(100, getHeight() - 50, 60, 20);
        add(button2);
        button2.addActionListener(this);

        button3 = new Button("Brighten");
        button3.setBounds(170, getHeight() - 50, 60, 20);
        add(button3);
        button3.addActionListener(this);

        button4 = new Button("Blur");
        button4.setBounds(240, getHeight() - 50, 60, 20);
        add(button4);
        button4.addActionListener(this);

        button5 = new Button("Reduce");
        button5.setBounds(310, getHeight() - 50, 60, 20);
        add(button5);
        button5.addActionListener(this);

        menuBar = new MenuBar();
        menu = new Menu("File");

        menuItem1 = new MenuItem("Open...");
        menu.add(menuItem1);
        menuItem1.addActionListener(this);

        menuItem2 = new MenuItem("Save As...");
        menu.add(menuItem2);
        menuItem2.addActionListener(this);

        menuItem3 = new MenuItem("Undo");
        menu.add(menuItem3);
        menuItem3.addActionListener(this);

        menuItem4 = new MenuItem("Exit");
        menu.add(menuItem4);
        menuItem4.addActionListener(this);

        menuBar.add(menu);
        setMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Graphicizer();
    }
}

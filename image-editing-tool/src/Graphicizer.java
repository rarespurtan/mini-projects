import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Graphicizer extends Frame implements ActionListener {
    private BufferedImage bufferedImage, bufferedImageBackup;
    private Image image;
    private Menu menu;
    MenuBar menuBar;
    MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    Button button1, button2, button3, button4, button5;
    FileDialog dialog;

    public Graphicizer(){
        setSize(400,400);
        setTitle("Image Graphicizer");
        setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Graphicizer();
    }
}

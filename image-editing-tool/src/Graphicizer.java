import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

public class Graphicizer extends Frame implements ActionListener {
    private BufferedImage bufferedImage = null, bufferedImageBackup;
    private Image image;
    private Menu menu;
    private MenuBar menuBar;
    private FileDialog dialog;
    private Button[] buttonArr = new Button[5];
    private String[] buttonNames = new String[] { "Embross", "Sharpen", "Brighten", "Blur", "Reduce" };
    private MenuItem[] menuItemArr = new MenuItem[4];
    private String[] menuItemNames = new String[] { "Open...", "Save As...", "Undo", "Exit" };

    public Graphicizer() {
        setSize(400, 360);
        setTitle("Image Graphicizer");
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setLayout(new BorderLayout());
        Panel p = new Panel();
        p.setLayout(new GridLayout(1, 5));

        for (int i = 0, x = 30; i < buttonArr.length; i++, x += 70) {
            buttonArr[i] = new Button(buttonNames[i]);
            buttonArr[i].setBounds(x, getHeight() - 50, 60, 20);
            p.add(buttonArr[i]);
            buttonArr[i].addActionListener(this);
        }
        add(p, BorderLayout.PAGE_END);

        menuBar = new MenuBar();
        menu = new Menu("File");

        for (int i = 0; i < menuItemArr.length; i++) {
            menuItemArr[i] = new MenuItem(menuItemNames[i]);
            menu.add(menuItemArr[i]);
            menuItemArr[i].addActionListener(this);
        }

        menuBar.add(menu);
        setMenuBar(menuBar);

        dialog = new FileDialog(this, "File Dialog");
    }

    public void paint(Graphics g) {
        if (bufferedImage != null) {
            g.drawImage(bufferedImage, getSize().width / 2 - bufferedImage.getWidth() / 2, getInsets().top + 20, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemArr[0]) {
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            try {
                if (!dialog.getFile().equals("")) {
                    System.out.println(dialog.getDirectory() + " " + dialog.getFile());
                    File input = new File(dialog.getDirectory() + dialog.getFile());
                    bufferedImage = ImageIO.read(input);
                    setSize(getInsets().left + getInsets().right + Math.max(400, bufferedImage.getWidth() + 60),
                            getInsets().top + getInsets().bottom + Math.max(340, bufferedImage.getHeight() + 60));
                    for (int i = 0, x = 30; i < buttonArr.length; i++, x += 70) {
                        buttonArr[i].setBounds(x, getHeight() - 50, 60, 20);
                    }
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            repaint();
        } else if (e.getSource() == menuItemArr[1]) {
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            try {
                if (!dialog.getFile().equals("")) {
                    String outfile = dialog.getFile();
                    File outputFile = new File(dialog.getDirectory() + outfile);
                    ImageIO.write(bufferedImage, outfile.substring(outfile.length() - 3, outfile.length()), outputFile);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } else if (e.getSource() == buttonArr[0]) {
            bufferedImageBackup = bufferedImage;
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            int pixels[] = new int[width * height];
            PixelGrabber pg = new PixelGrabber(bufferedImage, 0, 0, width, height, pixels, 0, width);
            try {
                pg.grabPixels();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            for (int x = 0; x <= 1; x++) {
                for (int y = 0; y < height; y++) {
                    pixels[x + y * width] = 0x88888888;
                }
            }
            for (int x = width - 2; x <= width - 1; x++) {
                for (int y = 0; y < height - 1; y++) {
                    pixels[x + y * width] = 0x88888888;
                }
            }
            for (int x = 0; x <= width - 1; x++) {
                for (int y = 0; y <= 1; y++) {
                    pixels[x + y * width] = 0x88888888;
                }
            }

            for (int x = 2; x < width - 1; x++) {
                for (int y = 2; y < height - 1; y++) {
                    int red = ((pixels[(x + 1) + y * width + 1] & 0xFF) - (pixels[x + y * width] & 0xFF)) + 128;
                    int green = (((pixels[(x + 1) + y * width + 1] & 0xFF00) / 0x100 % 0x100)
                            - ((pixels[x + y * width] & 0xFF00) / 0x100) % 0x100) + 128;
                    int blue = (((pixels[(x + 1) + y * width + 1] % 0xFF000) / 0x1000) % 0x100
                            - ((pixels[x + y * width] & 0xFF0000) / 0x1000) % 0x100) + 128;
                    int avg = (red + blue + green) / 3;
                    pixels[x + y * width] = (0xff000000 | avg << 16 | avg << 8 | avg);
                }
            }
            image = createImage(new MemoryImageSource(width, height, pixels, 0, width));
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            bufferedImage.createGraphics().drawImage(image, 0, 0, this);
            repaint();
        }
    }

    public static void main(String[] args) {
        new Graphicizer();
    }
}

// button1 = new Button("Emboss");
// button1.setBounds(30, getHeight() - 50, 60, 20);
// p.add(button1);
// button1.addActionListener(this);

// button2 = new Button("Sharpen");
// button2.setBounds(100, getHeight() - 50, 60, 20);
// p.add(button2);
// button2.addActionListener(this);

// button3 = new Button("Brighten");
// button3.setBounds(170, getHeight() - 50, 60, 20);
// p.add(button3);
// button3.addActionListener(this);

// button4 = new Button("Blur");
// button4.setBounds(240, getHeight() - 50, 60, 20);
// p.add(button4);
// button4.addActionListener(this);

// button5 = new Button("Reduce");
// button5.setBounds(310, getHeight() - 50, 60, 20);
// p.add(button5);
// // button5.addActionListener(this);
// button1.setBounds(30, getHeight() - 50, 60, 20);
// button2.setBounds(100, getHeight() - 50, 60, 20);
// button3.setBounds(170, getHeight() - 50, 60, 20);
// button4.setBounds(240, getHeight() - 50, 60, 20);
// button5.setBounds(310, getHeight() - 50, 60, 20);
// menuItem1 = new MenuItem("Open...");
// menu.add(menuItem1);
// menuItem1.addActionListener(this);

// menuItem2 = new MenuItem("Save As...");
// menu.add(menuItem2);
// menuItem2.addActionListener(this);

// menuItem3 = new MenuItem("Undo");
// menu.add(menuItem3);
// menuItem3.addActionListener(this);

// menuItem4 = new MenuItem("Exit");
// menu.add(menuItem4);
// menuItem4.addActionListener(this);

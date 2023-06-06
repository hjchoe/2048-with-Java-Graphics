package Projects.Project1JamesChoe;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D.Double;

/** JPanel extended object */
class Canvas extends JPanel {
    private KeySense ka;
    private Field map;
    private Interface score;
    private Boolean dead;
    private JLabel[][] display;

    /** Class that extends KeyAdapter for keyboard input logic */
    private class KeySense extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            if (!dead) {
                Frame frame;
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        dead = !map.move('w');
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        dead = !map.move('s');
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        dead = !map.move('d');
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        dead = !map.move('a');
                        break;
                    case KeyEvent.VK_R:
                        frame = (Frame) (JFrame) SwingUtilities.getWindowAncestor(getSelf());
                        frame.replay();
                        break;
                    case KeyEvent.VK_Q:
                        frame = (Frame) (JFrame) SwingUtilities.getWindowAncestor(getSelf());
                        frame.quit();
                        break;
                    default:
                        System.out.printf("move [ %s ] | invalid%n", KeyEvent.getKeyText(ke.getKeyCode()));
                        return;
                }
                score.update(map.getLargest(), map.getMoves());
                repaint();
                map.printArray();
            } else {
                System.out.println("Game Over");
                map.displayInfo();
            }
        }
    }

    /** Constructor */
    Canvas() {
        dead = false;
        map = new Field();
        score = new Interface();
        score.update(map.getLargest(), map.getMoves());
        display = new JLabel[4][4];
        this.ka = new KeySense();
        this.addKeyListener(this.ka);
        this.initUI();
        this.displayInit();
    }

    /** Initialization for Canvas UI */
    private void initUI() {
        this.setOpaque(false);
        this.setSize(new Dimension(400, 400));
        this.setLocation(150, 150);
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
    }

    /** Initialization for display */
    private void displayInit() {
        int x;
        int y = 0;
        for (int i = 0; i < 4; i++) {
            x = 0;
            for (int j = 0; j < 4; j++) {
                display[i][j] = new JLabel();
                display[i][j].setText("");
                display[i][j].setSize(100, 100);
                display[i][j].setFont(new Font("Serif", Font.PLAIN, 40));
                display[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                display[i][j].setVerticalAlignment(SwingConstants.CENTER);
                display[i][j].setLocation(x, y);
                display[i][j].setForeground(Color.BLACK);
                display[i][j].setVisible(true);
                display[i][j].setFocusable(false);
                add(display[i][j]);
                x += 100;
            }
            y += 100;
        }
    }

    /** Function to start game / reset */
    void start() {
        dead = false;
        map = new Field();
        score.update(map.getLargest(), map.getMoves());
        this.requestFocus();
    }

    /** Function to request focus to self */
    void focus() {
        this.requestFocus();
    }

    /**
     * Getter for self
     * @return Self
     */
    JPanel getSelf() {
        return this;
    }

    /**
     * Getter for Interface
     * @return Interface object
     */
    Interface getInterface() {
        return this.score;
    }

    /**
     * Overridden paintComponent method that draws all graphics for main content canvas
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Integer[][] field = map.getField();

        int x;
        int y = 0;
        for (int i = 0; i < 4; i++) {
            x = 0;
            for (int j = 0; j < 4; j++) {
                display[i][j].setText(field[i][j] == 0 ? "" : field[i][j].toString());
                int val = field[i][j];
                if (val == 0)
                    g2d.setColor(new Color(139, 139, 139));
                else if (val == 2)
                    g2d.setColor(new Color(230, 228, 216));
                else if (val == 4)
                    g2d.setColor(new Color(238, 227, 186));
                else if (val == 8)
                    g2d.setColor(new Color(255, 200, 115));
                else if (val == 16)
                    g2d.setColor(new Color(255, 160, 87));
                else if (val == 32)
                    g2d.setColor(new Color(255, 99, 41));
                else if (val == 64)
                    g2d.setColor(new Color(255, 73, 7));
                else if (val == 128 || val == 256)
                    g2d.setColor(new Color(255, 231, 71));
                else if (val == 512 || val == 1024)
                    g2d.setColor(new Color(255, 236, 57));
                else if (val >= 2048)
                    g2d.setColor(new Color(255, 230, 0));
                g2d.fill(new Double(x, y, 100, 100));
                g2d.setColor(Color.BLACK);
                g2d.draw(new Double(x, y, 100, 100));
                x += 100;
            }
            y += 100;
        }
    }
}

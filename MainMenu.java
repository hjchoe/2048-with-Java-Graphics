package Projects.Project1JamesChoe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Main menu */
class MainMenu extends JPanel {
    private ActionListener taskPerformer;
    private Timer timer;
    private int delay = 500; // milliseconds
    private Boolean borderState;
    private JButton start;
    private JLabel title;

    /** Constructor */
    MainMenu() {
        start = new JButton();
        title = new JLabel();
        this.initUI();

        borderState = true;
        taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                title.setBorder(BorderFactory
                        .createLineBorder(borderState ? new Color(251, 255, 107) : new Color(255, 206, 107), 5));
                repaint();
                borderState = !borderState;
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    /** Initialization for main menu UI */
    private void initUI() {
        this.setOpaque(false);
        this.setSize(new Dimension(700, 700));
        this.setLocation(0, 0);
        this.setBackground(new Color(74, 222, 128));
        this.setBorder(BorderFactory.createLineBorder(new Color(132, 252, 255)));
        this.setOpaque(true);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);

        title.setText("2048");
        title.setSize(500, 120);
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setLocation(100, 80);
        title.setForeground(Color.RED);
        title.setVisible(true);
        title.setFocusable(false);

        start.setText("start");
        start.setSize(100, 60);
        start.setFont(new Font("Serif", Font.PLAIN, 30));
        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setVerticalAlignment(SwingConstants.TOP);
        start.setLocation(300, 450);
        start.setBackground(new Color(198, 132, 255));
        start.setForeground(Color.WHITE);
        start.setBorderPainted(false);
        start.setVisible(true);
        start.setFocusable(false);

        this.add(title);
        this.add(start);
        repaint();
    }

    /**
     * Getter for start button
     * @return The main menu start button JButton object
     */
    JButton getStartButton() {
        return this.start;
    }

    /** 
     * Stops the timer object and therefore the action listener
     */
    void stopTimer() {
        timer.stop();
    }
}
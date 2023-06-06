package Projects.Project1JamesChoe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

/** JFrame extended object */
class Frame extends JFrame {
    private MainMenu menu;
    private Canvas panel;
    private ButtonTemplate replayButton;
    private ButtonTemplate quitButton;
    private ConfirmationTemplate confirmation;

    /** Constructor */
    Frame() {
        menu = new MainMenu();
        panel = new Canvas();
        replayButton = new ButtonTemplate("replay", 150, 60, 175, 585, new Color(198, 132, 255));
        quitButton = new ButtonTemplate("quit", 150, 60, 375, 585, Color.RED);

        this.setTitle("2048");
        this.setPreferredSize(new Dimension(700, 700));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(182, 255, 255));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(false);
        this.setLayout(null);

        this.add(menu);
        this.add(replayButton);
        this.add(quitButton);

        menu.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.stopTimer();
                remove(menu);
                add(panel);
                add(panel.getInterface().getScoreDisplay());
                add(replayButton);
                revalidate();
                repaint();
                panel.start();
            }
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replay();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
    }

    /**
     * Adds confirmation menu panel to self
     */
    private void addConfirm() {
        remove(panel);
        add(confirmation);
        revalidate();
        repaint();
    }

    /**
     * Removes confirmation menu panel from self
     */
    private void removeConfirm() {
        add(panel);
        remove(confirmation);
        panel.focus();
        revalidate();
        repaint();
    }

    /**
     * Getter for main Canvas object that extends JPanel
     * @return The main content panel Canvas object
     */
    Canvas getPanel() {
        return panel;
    }

    /**
     * Function for replaying the game, shows confirmation menu
     */
    void replay() {
        ButtonTemplate yes = new ButtonTemplate("yes", 130, 40, 65, 250, Color.GREEN);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(panel);
                add(panel.getInterface().getScoreDisplay());
                revalidate();
                repaint();
                panel.start();
                removeConfirm();
            }
        });

        ButtonTemplate no = new ButtonTemplate("no", 130, 40, 205, 250, Color.RED);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeConfirm();
            }
        });

        confirmation = new ConfirmationTemplate(yes, no);

        addConfirm();
    }

    /**
     * Function for quitting the game, shows confirmation menu
     */
    void quit() {
        ButtonTemplate yes = new ButtonTemplate("yes", 130, 40, 65, 250, Color.GREEN);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ButtonTemplate no = new ButtonTemplate("no", 130, 40, 205, 250, Color.RED);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeConfirm();
            }
        });

        confirmation = new ConfirmationTemplate(yes, no);

        addConfirm();
    }

    /**
     * ConfirmationTemplate class that extends JPanel for initializing JPanel for confirmation menu
     */
    class ConfirmationTemplate extends JPanel {
        ConfirmationTemplate(ButtonTemplate yes, ButtonTemplate no) {
            this.setOpaque(true);
            this.setSize(new Dimension(400, 400));
            this.setLocation(150, 150);
            this.setBackground(new Color(192, 192, 192));
            this.setFocusable(true);
            this.requestFocus();
            this.setLayout(null);

            JLabel text = new JLabel();
            text.setText("Are you sure?");
            text.setSize(700, 500);
            text.setFont(new Font("Serif", Font.PLAIN, 23));
            text.setLocation(135, -100);
            text.setVisible(true);

            this.add(text);
            this.add(yes);
            this.add(no);
        }
    }

    /**
     * ButtonTemplate class that extends JButton for initializing JButtons for confirmation menu
     */
    class ButtonTemplate extends JButton {
        ButtonTemplate(String text, int width, int height, int x, int y, Color background) {
            this.setText(text);
            this.setSize(width, height);
            this.setFont(new Font("Serif", Font.PLAIN, 30));
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.CENTER);
            this.setLocation(x, y);
            this.setBackground(background);
            this.setForeground(Color.BLACK);
            this.setVisible(true);
            this.setFocusable(false);
        }
    }
}

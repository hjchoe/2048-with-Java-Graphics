package Projects.Project1JamesChoe;

import java.awt.Font;
import javax.swing.JLabel;

/** Interface object for displaying score: maximum # and # of moves */
class Interface {
    private JLabel scoreDisplay;

    /** Constructor */
    Interface() {
        this.scoreDisplay = new JLabel("<html>maximum #: 0 | # of moves: 0</html>");
        this.scoreDisplay.setSize(700, 500);
        this.scoreDisplay.setFont(new Font("Serif", Font.PLAIN, 23));
        this.scoreDisplay.setLocation(150, -130);
        this.scoreDisplay.setVisible(true);
    }

    /**
     * Updates the maximum # and # of moves to the current versions to be displayed
     * @param largest The current maximum # on the board to be displayed
     * @param moves The current # of moves to be displayed
     */
    void update(int largest, int moves) {
        this.scoreDisplay.setText("<html>maximum #: " + largest + " | # of moves: " + moves);
    }

    /**
     * Getter for current score display
     * @return current score display JLabel object
     */
    JLabel getScoreDisplay() {
        return this.scoreDisplay;
    }
}
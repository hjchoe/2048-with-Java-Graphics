package Projects.Project1JamesChoe;

/** Coordinate object that contains x and y */
public class Coord {
    private int x;
    private int y;

    /** Constructors */
    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Coord(Coord c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    /**
     * Getter for coordinate x value
     * @return Integer value x
     */
    int getX() {
        return this.x;
    }

    /**
     * Getter for coordinate y value
     * @return Integer value y
     */
    int getY() {
        return this.y;
    }

    /**
     * Setter for coordinate x value
     * @param x New x Integer value to replace old to
     */
    void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for coordinate y value
     * @param y New y Integer value to replace old to
     */
    void setY(int y) {
        this.y = y;
    }

    /**
     * Function that moves x value of coordinate one left
     */
    void goLeft() {
        this.x--;
    }

    /**
     * Function that moves x value of coordinate one right
     */
    void goRight() {
        this.x++;
    }

    /**
     * Function that moves y value of coordinate one up
     */
    void goUp() {
        this.y--;
    }

    /**
     * Function that moves y value of coordinate one down
     */
    void goDown() {
        this.y++;
    }

    /**
     * Getter for the coordinate one right of self
     * @return New Coord object
     */
    Coord getRight() {
        return new Coord(this.x + 1, this.y);
    }

    /**
     * Getter for the coordinate one left of self
     * @return New Coord object
     */
    Coord getLeft() {
        return new Coord(this.x - 1, this.y);
    }

    /**
     * Getter for the coordinate one up of self
     * @return New Coord object
     */
    Coord getUp() {
        return new Coord(this.x, this.y - 1);
    }

    /**
     * Getter for the coordinate one down of self
     * @return New Coord object
     */
    Coord getDown() {
        return new Coord(this.x, this.y + 1);
    }

    /**
     * Function that overrides equals method
     * @param o Object to compare self to
     * @return Whether the Object o has the same coordinate as self
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coord))
            return false;
        Coord c = (Coord) o;
        return this.x == c.getX() && this.y == c.getY();
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "(" + this.x + " | " + this.y + ")";
    }
}

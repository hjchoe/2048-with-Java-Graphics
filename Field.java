package Projects.Project1JamesChoe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Field class for internal board management and moving logic */
public class Field {
    private Integer[][] field;
    private ArrayList<Coord> coords;
    private ArrayList<Coord> merged;
    private int moves;
    private int largest;
    private final int size = 4;
    private final String space = "     ";

    /** Constructor */
    Field() {
        this.field = new Integer[size][size];
        this.coords = new ArrayList<Coord>();
        this.merged = new ArrayList<Coord>();
        init();
    }

    /** Initialization / reset */
    void init() {
        coords.clear();
        merged.clear();
        this.moves = 0;
        this.largest = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                this.field[i][j] = 0;
                this.coords.add(new Coord(i, j));
            }

        largest = getVal(spawn());
        if (getVal(spawn()) > largest)
            largest = 4;
    }

    /** Function to insert an int into the field Integer 2D array */
    private void insert(int x, int y, int value) {
        this.field[y][x] = value;
        coords.remove(new Coord(x, y));
    }
    private void insert(Coord c, int value) {
        this.field[c.getY()][c.getX()] = value;
        coords.remove(new Coord(c.getX(), c.getY()));
    }

    /** Function to remove an int from the field Integer 2D array */
    private void remove(Coord c) {
        this.field[c.getY()][c.getX()] = 0;
        coords.add(new Coord(c.getX(), c.getY()));
    }

    /**
     * Function to check whether coordinate is within the bounds of the field Integer 2D array
     * @param c Coordinate to check
     * @return Boolean on whether the coordinate is in bounds or not
     */
    private Boolean inBound(Coord c) {
        return (c.getX() >= 0 && c.getX() < size) && (c.getY() >= 0 && c.getY() < size);
    }

    /**
     * Function to check whether the coordinate is in bounds and the space is empty ( 0 )
     * @param c Coordinate to check
     * @return Boolean whether the coordinate is in bounds and the space is empty
     */
    private Boolean checkSpace(Coord c) {
        return inBound(c) && field[c.getY()][c.getX()] == 0;
    }

    /**
     * Function to check whether values from two coordinates in the field Integer 2D array are equal
     * @param c First coordinate to compare
     * @param t Second coordinate to compare
     * @return Boolean whether the values are equal or not
     */
    private Boolean equalVal(Coord c, Coord t) {
        return field[c.getY()][c.getX()].equals(field[t.getY()][t.getX()]);
    }

    /**
     * Function that returns a Integer 2D array that is a copy of self field Integer 2D array
     * @return Copied Integer 2D array
     */
    private Integer[][] copySelf() {
        Integer[][] copy = new Integer[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                copy[i][j] = field[i][j];
        return copy;
    }

    /**
     * replaces self with the parameter Integer 2D array
     * @param arr Reference array to copy from
     */
    private void replaceField(Integer[][] arr) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.field[i][j] = arr[i][j];
    }

    /**
     * Function that checks whether the game is lost
     * @return Boolean on whether the game is lost or not
     */
    private Boolean checkLoss() {
        Boolean moved;
        Integer[][] old = copySelf();
        System.out.println(coords);
        if (coords.isEmpty()) {
            moved = moveRight();
            System.out.println(moved);
            replaceField(old);
            if (moved)
                return false;
            moved = moveLeft();
            System.out.println(moved);
            replaceField(old);
            if (moved)
                return false;
            moved = moveUp();
            System.out.println(moved);
            replaceField(old);
            if (moved)
                return false;
            moved = moveDown();
            System.out.println(moved);
            replaceField(old);
            if (moved)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Function to get the value from the field Integer 2D array at the parameter coordinate
     * @param c Coordinate to check
     * @return Integer value at the coordinate position in the field Integer 2D array
     */
    private int getVal(Coord c) {
        return field[c.getY()][c.getX()];
    }

    /**
     * Function that shifts values from one coordinate to another in the field Integer 2D array
     * @param from Coordinate to shift value from
     * @param to Coordinate to shift value to
     */
    private void shift(Coord from, Coord to) {
        insert(to, getVal(from));
        remove(from);
    }

    /**
     * Function that merges values from one coordinate to another in the field Integer 2D array
     * @param from Coordinate to merge value from
     * @param to Coordinate to merge value to
     */
    private void merge(Coord from, Coord to) {
        int newval = getVal(from) * 2;
        insert(to, newval);
        remove(from);
        merged.add(to);
        if (newval > largest)
            largest = newval;
    }

    /**
     * Function that continas moving logic to move up
     * @return Boolean on whether a move was able to be made or not
     */
    private Boolean moveUp() {
        Coord curr;
        Boolean moved = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                curr = new Coord(i, j);
                if (!checkSpace(curr)) {
                    while (checkSpace(curr.getUp())) {
                        shift(curr, curr.getUp());
                        curr.goUp();
                        moved = true;
                    }
                    if (inBound(curr.getUp()) && !merged.contains(curr) && !merged.contains(curr.getUp())
                            && equalVal(curr, curr.getUp())) {
                        merge(curr, curr.getUp());
                        curr.goUp();
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * Function that continas moving logic to move down
     * @return Boolean on whether a move was able to be made or not
     */
    private Boolean moveDown() {
        Coord curr;
        Boolean moved = false;

        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                curr = new Coord(i, j);
                if (!checkSpace(curr)) {
                    while (checkSpace(curr.getDown())) {
                        shift(curr, curr.getDown());
                        curr.goDown();
                        moved = true;
                    }
                    if (inBound(curr.getDown()) && !merged.contains(curr) && !merged.contains(curr.getDown())
                            && equalVal(curr, curr.getDown())) {
                        merge(curr, curr.getDown());
                        curr.goDown();
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * Function that continas moving logic to move left
     * @return Boolean on whether a move was able to be made or not
     */
    private Boolean moveLeft() {
        Coord curr;
        Boolean moved = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                curr = new Coord(j, i);
                if (!checkSpace(curr)) {
                    while (checkSpace(curr.getLeft())) {
                        shift(curr, curr.getLeft());
                        curr.goLeft();
                        moved = true;
                    }
                    if (inBound(curr.getLeft()) && !merged.contains(curr) && !merged.contains(curr.getLeft())
                            && equalVal(curr, curr.getLeft())) {
                        merge(curr, curr.getLeft());
                        curr.goLeft();
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * Function that continas moving logic to move right
     * @return Boolean on whether a move was able to be made or not
     */
    private Boolean moveRight() {
        Coord curr;
        Boolean moved = false;

        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                curr = new Coord(j, i);
                if (!checkSpace(curr)) {
                    while (checkSpace(curr.getRight())) {
                        shift(curr, curr.getRight());
                        curr.goRight();
                        moved = true;
                    }
                    if (inBound(curr.getRight()) && !merged.contains(curr) && !merged.contains(curr.getRight())
                            && equalVal(curr, curr.getRight())) {
                        merge(curr, curr.getRight());
                        curr.goRight();
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * Function that calculates which moving logic to use based on input character
     * @param d Character that corresponds to which direction the moving logic should go
     * @return Boolean on whether the game is lost or not
     */
    Boolean move(char d) {
        Boolean moved = false;
        merged.clear();
        switch (d) {
            case 'w':
                moved = moveUp();
                break;
            case 'a':
                moved = moveLeft();
                break;
            case 's':
                moved = moveDown();
                break;
            case 'd':
                moved = moveRight();
                break;
            default:
                break;
        }
        for (int i = 0; i < 10; i++)
            System.out.println("\n");
        if (moved) {
            moves++;
            spawn();
        }
        System.out.printf("move [ %c ] | %s%n", d, moved ? "valid" : "invalid");
        System.out.printf("maximum #: %d | # of moves: %d%n", largest, moves);
        return !checkLoss();
    }

    /**
     * Function that prints the current maximum # and # of moves
     */
    void displayInfo() {
        System.out.printf("maximum #: %d | # of moves: %d%n", largest, moves);
    }

    /**
     * Function that spawns a new value (either 2 or 4) in a random empty space
     * @return the Coord object on where the new value is spawned
     */
    Coord spawn() {
        int value = ThreadLocalRandom.current().nextInt(1, 11) <= 8 ? 2 : 4;
        Coord loc = coords.get(ThreadLocalRandom.current().nextInt(0, coords.size()));

        insert(loc.getX(), loc.getY(), value);

        return loc;
    }

    /**
     * Function that prints the current field Integer 2D array
     */
    void printArray() {
        StringBuilder output = new StringBuilder("-     -     -     -     -     -");
        for (int i = 0; i < size; i++) {
            output.append("\n|");
            for (int j = 0; j < size; j++)
                output.append(space + this.field[i][j]);
            output.append(space + "|");
        }
        output.append("\n-     -     -     -     -     -");
        System.out.println(output.toString());
    }

    /**
     * Getter for field Integer 2D array
     * @return Integer 2D array field
     */
    Integer[][] getField() {
        return this.field;
    }

    /**
     * Getter for maximum #
     * @return Integer of current maximum #
     */
    int getLargest() {
        return this.largest;
    }

    /**
     * Getter for # of moves
     * @return Integer of current # of moves
     */
    int getMoves() {
        return this.moves;
    }
}
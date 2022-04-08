import java.util.Random;

// Tiles for the river/board
public class Tile {
    private int position; // 0-99 or 1-100
    private char type; // current, trap, normal
    private int movement; // how many tiles the trap or current moves the player
    public static char[] typeList = { 'c', 't', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n' };

    // constructor
    public Tile(int position) {
        this.position = position;
        type = randomType(); // sets random type
        movement = randomMovement();

        if (position == 0 || position == 99) {
            // first and last tiles cannot be current/trap
            setType('n');
        }   
    }

    // getters (and one setter)
    public int getPosition() {
        return position;
    }
    public char getType() {
        return type;
    }
    public int getMovement() {
        return movement;
    }
    public void setType(char type) {
        this.type = type;
    }

    // change type frequency according to difficulty
    public static void changeType(char type) {
        typeList[9] = type;
    }

    // generating random type using the array
    private char randomType() {
        Random random = new Random();
        // length of array doesn't change, but it's there in case of modifications to the code
        int typeLength = typeList.length;
        return typeList[random.nextInt(typeLength)];
    }

    // traps and currents move the boat a random number of tiles
    private int randomMovement() {
        Random random = new Random();
        return (random.nextInt(5) + 3);
    }
}
// river/set of 100 tiles
public class Track {
    private Tile tiles[] = new Tile[100];
    private String typeString; // displays the river

    public Track() {
        for (int i = 0; i < 100; i++) {
            tiles[i] = new Tile(i);
        }
        checkTrack();
        tileToString();
    }

    // allow other classes to access the tiles in the track by their position number
    public Tile getTile(int position) {
        return tiles[position];
    }

    // check if tiles can cause an infinite loop
    private void checkTrack() {
        for (int i = 0; i < 100; i++) {
            char type = tiles[i].getType();
            int movement = tiles[i].getMovement();

            if (type == 'c') {
                // change to normal if:
                // a) current moves the player past tile 100
                // b) current moves the player into a trap or current
                if (((i + movement) >= 99) || (tiles[i + movement].getType() != 'n')) {
                    tiles[i].setType('n');
                }
            } else if (type == 't') {
                // change to normal if:
                // a) trap moves the player past tile 1
                // b) trap moves the player into a current or trap
                if (((i - movement) <= 0) || (tiles[i - movement].getType() != 'n')) {
                    tiles[i].setType('n');
                }
            }
        }
    }

    // converts the types of all tiles in the river into an array, then converts the array into a string
    private void tileToString() {
        char[] typeChars = new char[100];
        for (int i = 0; i < 100; i++) {
            if (tiles[i].getType() == 'n'){
                typeChars[i] = ' ';
            } else {
                typeChars[i] = tiles[i].getType();
            }
        }
        typeString = new String(typeChars);
    }

    @Override
    public String toString() {
        return typeString;
    }
}
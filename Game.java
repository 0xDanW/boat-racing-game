import java.util.Scanner;

// stores information about the game
public class Game {
    private String difficulty;
    private int numplayers;
    private PlayerBoat player1;
    private Boat player2;
    private final Scanner input = new Scanner(System.in);
    private Dice dice1 = new Dice();

    // sets up difficulty and player names
    public Game() {
        setDifficulty();
        setNumplayers();
        player1 = setPlayer1();
        player2 = setPlayer2();
    }

    //set difficulty
    public void setDifficulty() {
        while (true) {
            System.out.print("Select a difficulty (Easy, Normal, Hard): ");
            difficulty = input.next().toUpperCase();

            if (difficulty.equals("EASY") || difficulty.equals("E")) {
                difficulty = "Easy";
                Tile.changeType('c');
                break;
            } else if (difficulty.equals("NORMAL") || difficulty.equals("N")) {
                difficulty = "Normal";
                Tile.changeType('n');
                break;
            } else if (difficulty.equals("HARD") || difficulty.equals("H")) {
                difficulty = "Hard";
                Tile.changeType('t');
                break;
            } else {
                System.out.println("Invalid selection!");
            }
        }
    }

    //set number of players
    public void setNumplayers() {
        while (true) {
            System.out.print("How many players? (1 or 2) ");
            if (input.hasNextInt()) {
                numplayers = input.nextInt();
                if (numplayers == 1 || numplayers == 2){
                    break;
                } else {
                    System.out.println("Enter 1 or 2");
                }
            } else {
                System.out.println("Enter 1 or 2");
            }
        }
    }

    //input player names
    public PlayerBoat setPlayer1() {
        input.nextLine();
        while (true) {
            System.out.print("Enter player 1's name (10 letters max): ");
            String pname1 = input.nextLine();
            if ((pname1.length() > 0) && (pname1.length() < 11)) {
                PlayerBoat player1 = new PlayerBoat(pname1, 1); // Player 1
                return player1;
            }
        }
    }
    public Boat setPlayer2() {
        if (numplayers == 1) {
            CpuBoat player2 = new CpuBoat();
            return player2;
        } else {
            while (true) {
                System.out.print("Enter player 2's name (10 letters max): ");
                String pname2 = input.nextLine();
                if ((pname2.length() > 0) && (pname2.length() < 11)) {
                    PlayerBoat player2 = new PlayerBoat(pname2, 2);
                    return player2;
                }
            }
        }
    }

    // starts the game after setting up
    public boolean startGame() {
        Track river = new Track();
        int turn1 = 1;
        int turn2 = 1;
        player1.resetPosition();
        player2.resetPosition();

        // 50 turns maximum
        for (int i = 0; i < 50; i++) {
            System.out.println("\nPlayer 1 - Turn " + turn1);
            calculatePosition(player1, river);
            turn1++;
            input.nextLine();
            // repeat until a boat hits 100th tile
            if (player1.getPosition() == 99) {
                System.out.printf("Player 1, %s, has won the game!\n", player1.getBoatname());
                player1.setPlayerScore(turn1);
                addPlayerScore(player1);
                break;
            }
            System.out.println("Player 2 - Turn " + turn2);
            calculatePosition(player2, river);
            turn2++;
            input.nextLine();
            if (player2.getPosition() == 99) {
                System.out.printf("Player 2, %s, has won the game!\n", player2.getBoatname());
                if (player2 instanceof PlayerBoat) {
                    ((PlayerBoat) player2).setPlayerScore(turn2);
                    addPlayerScore((PlayerBoat) player2);
                }
                break;
            }
            System.out.println("Total number of turns: " + (i + 1));
        }
        // both players lose
        if ((turn1 == 50) && (turn2 == 50)) {
            System.out.println("Nobody reached the goal!");
        }
        return replay();
    }

    // calculates player position
    private void calculatePosition(Boat b, Track t) {
        String riverString = t.toString();
        int position = b.getPosition();
        int diceroll = dice1.roll();
        
        // displays initial boat position graphically
        String riverStringWithBoat = riverString.substring(0, b.getPosition()) + b.getBoatnum() + riverString.substring(b.getPosition() + 1);
        System.out.println("|" + riverStringWithBoat + "|");
        System.out.println("rolled " + diceroll);

        // moves the boat
        b.moveForward(diceroll);
        position = b.getPosition();

        // moves boat when hitting a current or trap
        if (t.getTile(position).getType() == 'c') {
            System.out.printf("\nThe boat hit a current! The boat moved %d tiles forward\n", t.getTile(position).getMovement());
            b.moveForward(t.getTile(position).getMovement());
        } else if (t.getTile(position).getType() == 't') {
            System.out.printf("\nThe boat hit a trap! The boat moved %d tiles backward\n", t.getTile(position).getMovement());
            b.moveBackward(t.getTile(position).getMovement());
        }

        //displays boat position after moving
        riverStringWithBoat = riverString.substring(0, b.getPosition()) + b.getBoatnum() + riverString.substring(b.getPosition() + 1);
        System.out.println("|" + riverStringWithBoat + "|");
    }

    // prompts user to replay game
    private boolean replay() {
        boolean replayBool = false;
        while (true) {
            System.out.print("Would you like to play again with the same settings? (Y/N): ");
            String replayString = input.nextLine().toUpperCase();
            if (replayString.equals("Y") || replayString.equals("YES")) {
                replayBool = true;
                break;
            } else if (replayString.equals("N") || replayString.equals("NO")) {
                replayBool = false;
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return replayBool;
    }

    // adds score to scorefile
    private void addPlayerScore(PlayerBoat player) {
        Score score = new Score();
        score.loadScore();
        score.addScore(player, difficulty);
        score.saveScore();
    }
}

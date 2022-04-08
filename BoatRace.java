import java.util.Scanner;

// main class, contains menu
public class BoatRace {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    // menu selection
    private static void menu() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("-------------------------- MAIN MENU --------------------------");
        System.out.println("---------------------------------------------------------------");
        System.out.println("");

        // Input user choice
        while (true) {
            System.out.println("1) Start Game\n2) Tutorial\n3) Scoreboard\n4) Quit Game");
            System.out.print("\nEnter your choice: ");
            switch (input.next()) {
                case "1": // starts game
                    boolean play = true;
                    Game game = new Game(); // settings

                    // replays as long as the user wants to
                    while (play) {
                        play = game.startGame();
                    }
                    break;
                case "2":
                    tutorial();
                    break;
                case "3":
                    score();
                    break;
                case "4":
                    System.out.println("Thanks for playing !!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter numbers 1-4 only!");
            }
        }
    }

    // displays tutorial
    private static void tutorial() {
        System.out.println("--------------------------------------------------");
        System.out.println("-------------------- TUTORIAL --------------------");
        System.out.println("--------------------------------------------------");
        System.out.println(
                "1. UNDERSTAND THE OBJECTS.\nThe game consists of 2 players with a boat each. There is a river with 100 tiles and a random number of currents and traps scattered around the river.\n");
        System.out.println(
                "2. ROLL THE DICE AND MOVE.\nStart the game by rolling the dice and move the boat forward according to the number.\n");
        System.out.println(
                "3. CURRENTS PROVIDE YOUR BOAT WITH AN ADDITIONAL BOOST FORWARD IN THE RIVER.\nA random number of currents placed in different tiles in the river would make the boat go forward. Each current may differ in magnitude, where a larger current will allow the boat to move more tiles forward than the other currents.\n");
        System.out.println(
                "4. TRAPS MAKE YOUR BOAT GOES BACKWARD IN THE RIVER.\nA random number of traps placed in different positions placed in different tiles in the river would make the boat go backward. Each trap may differ in magnitude, where a larger trap will make the boat move more tiles backward than the other traps.\n");
        System.out.println(
                "5. LAND EXACTLY ON THE FINAL TILE IN THE RIVER TO WIN THE GAME.\nIf the boat goes over the final tile in the river, the boat would go back the remaining number of tiles. For example, if a player rolls a five at the 98th tile, the player would go forward two tiles and then go back three tiles, ending up at the 97th tile.\n");
        System.out.println("Press Enter to go back to the menu");
        input.nextLine();
        input.nextLine();
    }

    // displays scores
    private static void score() {
        Score scores = new Score();
        scores.loadScore();
        scores.displayScore();
        System.out.println("Press Enter to go back to the menu");
        input.nextLine();
        input.nextLine();
    }
}
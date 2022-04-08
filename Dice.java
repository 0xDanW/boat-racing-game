import java.util.Random;

// dice to generate random numbers
public class Dice {
    private int dicenumber;

    public Dice() {
        dicenumber = roll();
    }

    // in case dice number is not stored when rolled
    public int getDiceNumber() {
        return dicenumber;
    }

    // rolls the dice to generate random number from 1-6
    public int roll() {
        Random random = new Random();
        dicenumber = random.nextInt(6) + 1;
        return dicenumber;
    }
}

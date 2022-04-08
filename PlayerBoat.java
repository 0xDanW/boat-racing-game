// player-controlled boat, has name and score (if they win)
public class PlayerBoat extends Boat{
    private int player_score;

    public PlayerBoat(String playername, int boatnum) {
        super(boatnum);
        setBoatname(playername);
    }

    // setter/getter
    public void setPlayerScore(int pscore) {
        if (pscore >= 0) {
            player_score = pscore;
        }
    }
    public int getPlayerScore() {
        return player_score;
    }

    // toString method
    public String toString() {
        return String.format("Player Name: %s\nPlayer Score: %d\n", getBoatname(), player_score);
    }
}

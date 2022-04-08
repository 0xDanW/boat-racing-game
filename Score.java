import java.util.*;
import java.io.*;

public class Score {
	private Scanner input;
	private Formatter output;
	private ArrayList<ScoreRecord> scoreList = new ArrayList<ScoreRecord>();

	public Score() {
		createFile();
	}

	// creates file if file does not exist
	public void createFile() {
		try {
            File file = new File("playerscore.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
          } catch (IOException e) {
            System.out.println("Error creating file.");
            e.printStackTrace();
          }
	}

	// load all data
	// 1. opens file
	// 2. reads file
	// 3. closes file
	public void loadScore() {
		input = openInputFile("playerscore.txt");
		readScoreFile();
		closeInputFile(input);
	}

	// stores all the data
	// 1. opens file
	// 2. writes to file
	// 3. closes file
	public void saveScore() {
		output = openOutputFile("playerscore.txt");
		writeScoreFile();
		closeOutputFile(output);
	}

	// opens file to read
	private Scanner openInputFile(String filename) {
		Scanner tempinput = null;
		try {
			tempinput = new Scanner(new File(filename));
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
		return tempinput;
	}

	// opens file to write
	private Formatter openOutputFile(String filename) {
		Formatter tempoutput = null;
		try {
			tempoutput = new Formatter(new File(filename));
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
		return tempoutput;
	}

	// reads file line
	// creates temporary object and assigns data to the object
	// adds object to the arraylist
	private void readScoreFile() {
		try {
			while (input.hasNext()) {
				ScoreRecord tempscore = new ScoreRecord();
				tempscore.setScore(input.nextInt());
				tempscore.setDifficulty(input.next());
				tempscore.setName(input.next());

				scoreList.add(tempscore);
			}
		} catch (NoSuchElementException elementException) {
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		} catch (IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		}
	}

	// loops the object array list and adds data from the array list into the file
	private void writeScoreFile() {
		sortScores();
		for (int i = 0; (i < scoreList.size()) && (i < 5); i++) {
			output.format("%d %s %s\n", scoreList.get(i).getScore(), scoreList.get(i).getDifficulty(), scoreList.get(i).getName());
		}
	}

	// closes the files
	private void closeInputFile(Scanner input) {
		if (input != null)
			input.close();
	}
	private void closeOutputFile(Formatter output) {
		if (output != null)
			output.close();
	}

	// creates temporary object with one record data
	// adds object into the array list
	public void addScore(PlayerBoat player, String difficulty) {
		ScoreRecord tempscore = new ScoreRecord();

		try {
			tempscore.setScore(player.getPlayerScore());
			tempscore.setDifficulty(difficulty);
			tempscore.setName(player.getBoatname());

			scoreList.add(tempscore);
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file.");
			return;

		} catch (NoSuchElementException elementException) {
			System.err.println("invalid input");
			return;
		}
	}

	// displays the scores in a table
	public void displayScore() {
		sortScores();
		System.out.println("----------------------------------------");
		System.out.println("| NO |   PLAYER   | SCORE | DIFFICULTY |");
		System.out.println("----------------------------------------");
		for (int i = 0; i < scoreList.size(); i++) {
			System.out.printf("| %2d | %10s | %5d | %10s |", (i + 1), scoreList.get(i).getName(), scoreList.get(i).getScore(), scoreList.get(i).getDifficulty());
			System.out.println("\n----------------------------------------");
		}
	}

	// sorts the scores
	private void sortScores() {
		Comparator<ScoreRecord> comparator = new Comparator<ScoreRecord>(){
			public int compare(ScoreRecord s1, ScoreRecord s2) {
				return s1.getScore() - s2.getScore();
			}
		};
		scoreList.sort(comparator);
	}
}
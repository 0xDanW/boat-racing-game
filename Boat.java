// superclass to the PlayerBoat and CpuBoat classes
public class Boat {
	private int position;
	private int boatnum; // player number; either 1 or 2
	private String boatname; // set by subclass

	// constructor
	public Boat(int boatnum) {
		this.boatnum = boatnum;
		position = 0;
	}

	// setter/getters
	public int getPosition() {
		return position;
	}
	public int getBoatnum() {
		return boatnum;
	}
	public void setBoatname(String boatname) {
		this.boatname = boatname;
	}
	public String getBoatname() {
		return boatname;
	}

	// moves the boat forwards
	// moves the boat backwards when it exceeds the river edge (100th tile)
	public void moveForward(int p) {
		if (position + p <= 99) {
			position += p;
		} else {
			System.out.println("\nThe boat hit the river edge! The boat moved backwards!");
			int temp = (position + p);
			position = (99 - (temp - 99));
		}
	}

	// moves the boat backwards
	public void moveBackward(int p) {
		position -= p;
	}

	// resets position (for replaying)
	public void resetPosition() {
		position = 0;
	}
}
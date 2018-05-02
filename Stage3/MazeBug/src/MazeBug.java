import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private static final int SIZE = 100;
	private static final int[] DIRS = 
		{ Location.AHEAD, Location.LEFT, Location.RIGHT, Location.HALF_CIRCLE };
	
	public int[] dirsCount = {1,1,1,1}; //direction chosen times
	
	public Location next;
	public boolean isVisited[][];
	public ArrayList<Location> path;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	
	public Integer stepCount = 0;
	public boolean isEnd = false;
	public boolean hasShown = false; //final message has been shown

	/**
	 * Construct a maze bug with green color.
	 */
	public MazeBug() {
		setColor(Color.GREEN);

		isVisited = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				isVisited[i][j] = false;
			}
		}

		path = new ArrayList<Location>();
		path.add(getLocation());
	}	

	/**
	 * Moves to the next location or go back.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd) {
		//to show step count when reach the goal
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			//visit the next node
			isVisited[next.getRow()][next.getCol()] = true;
			move();
			stepCount++;
		} else {
			// back to the cross location
			back();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * @param loc the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		for (int d : DIRS) {
			Location neighbor = loc.getAdjacentLocation(getDirection() + d);

			if (gr.isValid(neighbor)) {
				Actor a = gr.get(neighbor);
				if (!isVisited[neighbor.getRow()][neighbor.getCol()] &&
					(a == null || a instanceof Flower)) {
					valid.add(neighbor);
				} else if (a instanceof Rock && a.getColor().equals(Color.RED)) {
					isEnd = true;
				}
			}
		}
		return valid;
	}

	/*
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> loc = getValid(getLocation());
		if (loc.isEmpty()) {
			return false;
		} else {
			path.add(getLocation());
			if (loc.size() >= 2) {
				crossLocation.push(path);
				path = new ArrayList<Location>();
				next = betterDir(loc);
			}
			next = loc.get(0);
		}
		return true;
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}


	/**
	 * Find the better direction choic for maze bug to go.
	 */
	public Location betterDir(ArrayList<Location> loc) {
		int direction;
		int max = -1;
		int maxIndex = -1;
		for (int i = 0; i < loc.size(); ++i) {
			direction = getLocation().getDirectionToward(loc.get(i));
			if (direction == Location.HALF_CIRCLE && dirsCount[1] > max) {
				max = dirsCount[1];
				maxIndex = i;
			} else if (direction == Location.AHEAD && dirsCount[0] > max) {
				max = dirsCount[0];
				maxIndex = i;
			} else if (direction == Location.RIGHT && dirsCount[3] > max) {
				max = dirsCount[3];
				maxIndex = i;
			} else if (direction == Location.LEFT && dirsCount[2] > max) {
				max = dirsCount[2];
				maxIndex = i;
			}
		}
		for (int i = 0; i < 4; ++i) {
			if (max == dirsCount[i]) {
				dirsCount[i]++;
			}
		}
		return loc.get(maxIndex);
	}

	/**
	 * Go back to the last location if no way to move.
	 */
	public void back() {
		if (path.isEmpty()) {
			path = crossLocation.pop();
			Location loc = path.get(path.size() - 1);
			int dir = getLocation().getDirectionToward(loc);
			if (dir == Location.HALF_CIRCLE) {
				dirsCount[0]--;
			} else if (dir == Location.AHEAD) {
				dirsCount[1]--;
			} else if (dir == Location.RIGHT) {
				dirsCount[2]--;
			} else {
				dirsCount[3]--;
			}
		}
		next = path.remove(path.size() - 1);
		move();
	}
}

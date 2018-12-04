package wumpusWorld;

import java.util.ArrayList;

public class WumpusSolver {

	Maze maze;
	Agent agent;
	private Wumpus wumpus;
	private Node start, currNode;
	private boolean gameOver, wumpusKilled;
	private ArrayList<Node> path = new ArrayList<Node>();
	
	public WumpusSolver(Maze m, Agent a, Wumpus w) {
		this.maze = m;
		this.start = m.getNode(0, 0);
		this.agent = a;
		this.wumpus = w;
		agent.setDirection("East");
	}
	
	// Main method to repetitively check percepts and make decions for the agent to move forward
	public void solveMaze() {
		currNode = start;
		currNode.setHasAgent(true);
		currNode.setGuess('*');
		maze.printNodeMatrix();
		
		while (!gameOver) {
			if (checkPercepts(currNode)) // If it is safe enough to move forward, do so
				makeMove();
			maze.printNodeMatrix();
			maze.printKnowledgeBase();
		}
	}
	
    /* 
     * Method to check percepts. Makes updates to the agent's current guess as to if its neighboring nodes are safe.
     * Returns true if the agent can make a move forward, or false if it cannot.
     * [no stench, no breeze, no glitter, no bump, no scream] = can move forward
     * Nodes are given the following guesses:
     *  '*' = safe
     *  'P' = Possible pit
     *  'W' = Possible wumpus
     *  '?' = Unkown if pit or wumpus - but not safe
     *  '_' = No current guess
     */
	private boolean checkPercepts(Node n) {
		boolean stench = false, breeze = false, glitter = false, bump = false, scream = false;
		boolean canMove = false;
		path.add(n);
		if (n.containsPit()) { // Fall into pit - game ends
			agent.editPerformanceMeasure(-1000);
			gameOver = true;
			System.out.println("Fell in pit");
			System.out.println("Agent did not complete the task with " + path.size() + " moves.");
			System.out.println("Agent performance measure: " + agent.getPerformanceMeasure());
			return false;
		}
		if (n.containsWumpus()) { // Attacked by wumpus - game ends
			agent.editPerformanceMeasure(-1000);
			gameOver = true;
			System.out.println("Wumpus killed agent");
			System.out.println("Agent did not complete the task with " + path.size() + " moves.");
			System.out.println("Agent performance measure: " + agent.getPerformanceMeasure());
			return false;
		}
		currNode.setGuess('*'); // Since current node is not a pit or wumpus, it is safe to stand on
		
		if (n.containsGlitter()) { // The gold is in the same square as the glitter. have the agent grab the gold.
			glitter = true;
			System.out.println("Agent found gold");
			agent.grab();
			goToStart();
			gameOver = true;
			return false;
			//TODO: make the agent go back to 0,0
		}
		if (n.hasStench()) {
			stench = true;
		}
		if (n.hasBreeze()) {
			breeze = true;
		}
		
		if(agent.getDirection() == "East" && n.hasRightWall()) {
			bump = true;
			System.out.println("agent hit a wall");
			agent.nextDirection();
		}
		if(agent.getDirection() == "South" && n.hasBottomWall()) {
			bump = true;
			System.out.println("agent hit a wall");
			agent.nextDirection();
		}
		if(agent.getDirection() == "West" && n.hasLeftWall()) {
			bump = true;
			System.out.println("agent hit a wall");
			agent.nextDirection();
		}
		if(agent.getDirection() == "North" && n.hasTopWall()) {
			bump = true;
			System.out.println("agent hit a wall");
			agent.nextDirection();
		}
		//Check all percepts together and make decisions based on propositional logic
		System.out.println("Current position: "+ agent.getX() + "," + agent.getY());
		System.out.println("Current direction: "+ agent.getDirection());
		System.out.println("stench =" + stench + " breeze=" + breeze + " glitter="+ glitter + " bump=" + bump + " scream="+ scream);
		
		// It is safe to move forward
		if (!stench && !breeze) {
			agent.addSafe(currNode);
			for(Node neighbor: currNode.getNeighbors())
			{
				agent.addValid(neighbor);
				neighbor.setGuess('*');
			}
			canMove = true;
		}
		
		// The wumpus is in an adjacent Node
		else if (stench && !breeze) {
			System.out.println("Wumpus in adjacent node");
			
			for (Node neighbor : currNode.getNeighbors()) { // Guess where wumpus could be
				if(neighbor.getGuess() != '*') {
					neighbor.setGuess('W'); // Each neighbor could possibly be the wumpus
					agent.addUnknown(neighbor);
				}
			}
			if(agent.hasArrow() && agent.shootArrow(maze, wumpus)) {	// If we kill the wumpus, it is safe to move forwards.
				System.out.println("Killed Wumpus");
				for(Node neighbor: currNode.getNeighbors()) {
					if (neighbor.getGuess() == 'W') { // Nodes that were guessed to be wumpus are now safe
						neighbor.setGuess('*'); // Neighboring spots are safe, since there is no breeze perceived.
						agent.addValid(neighbor);
					}	
				}
				
				// Since wumpus is killed, remove its stench from its neighboring nodes
				for (Node neighbor : maze.getNode(wumpus.getX(), wumpus.getY()).getNeighbors()) {
					neighbor.setStench(false);
				}
				canMove = true; // It is now safe to move since the wumpus is dead
				wumpusKilled = true;
				
			}
			else {
				for (Node neighbor : currNode.getNeighbors()) { // Guess where wumpus could be
					agent.addWarningNode(currNode);
					if(neighbor.getGuess() == 'W') { // If there is likely a wumpus in the next square, change directions.
						agent.nextDirection();
					}
				}
				canMove = true; // Can attempt to move after turning directions
			}
		}
		
		// There is a pit in an adjacent Node
		else if (!stench && breeze) {
			System.out.println("Pit in adjacent node");

			agent.addWarningNode(currNode);
			for (Node neighbor : currNode.getNeighbors()) { // Guess where pit could be
				if (neighbor.getGuess() == '_' )
					neighbor.setGuess('P'); // Each neighbor could possibly be a pit
					agent.addUnknown(neighbor);
			}
			canMove = true; // Can attempt to move after setting neighbors to possible pits
		}
		
		// There is a pit and wumpus in adjacent node(s)
		else if(stench && breeze) {
			System.out.println("Pit and Wumpus in adjacenct node(s)");
			
			agent.addWarningNode(currNode);
			for(Node neighbor: currNode.getNeighbors())
			{
				if (neighbor.getGuess() == '_')
				{
					neighbor.setGuess('?'); // Don't know for sure if it is a pit, wumpus, or both in adjacent node
					agent.addUnknown(neighbor);
				}
			}
			canMove = true;
		}
		
		agent.findDanger(currNode);
		return canMove;
		//May need to add this to fix certain situations
		//agent.findDanger(currNode);
	}
	
	// Method for agent to move forward
	public void makeMove() {
		Node prev;
		agent.moveForward(maze);
		prev = currNode; 
		currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
		currNode.setPrev(prev); // Set previous Node
		currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
		currNode.setVisited();
		
		if (wumpusKilled)
			currNode.setWumpus(false); // Remove wumpus if it was just killed;
	}
	
	// Follow path back to starting point.
	public void goToStart() {
		for(int i = path.size() - 1; i >= 0; i--) {
			agent.setX(path.get(i).getX());
			agent.setY(path.get(i).getY());
			agent.editPerformanceMeasure(-1);
			if(path.get(i).getX() == 0 && path.get(i).getY() == 0) {
				System.out.println("Climbed out of cave with gold!");
				agent.editPerformanceMeasure(1000);
				break;
			}
		}
		System.out.println("Agent completed task with " + path.size() * 2 + " moves.");
		System.out.println("Agent performance measure: " + agent.getPerformanceMeasure());
	}
	
}

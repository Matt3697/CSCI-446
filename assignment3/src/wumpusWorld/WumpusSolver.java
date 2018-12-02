package wumpusWorld;

public class WumpusSolver {

	//private Node[][] maze;
	Maze maze;
	Agent agent;
	private Node start, currNode;
	private boolean gameOver, agentMoved;
	
	public WumpusSolver(Maze m, Agent a) {
		this.maze = m;
		this.start = m.getNode(0, 0);
		this.agent = a;
		agent.setDirection("East");
	}
	
	public void solveMaze() {
		currNode = start;
		currNode.setHasAgent(true);
		maze.printNodeMatrix();
		
		while (!gameOver) {
			checkPercepts(currNode);
			maze.printNodeMatrix();
		}
	}
	
	/* 
	 * [no stench, no breeze, no glitter, no bump, no scream] = can move forward
	 */
	
	private void checkPercepts(Node n) {
		boolean stench = false, breeze = false, glitter = false, bump = false, scream = false;
		Node prev;

		if (n.containsPit()) {
			// u ded -- game ends
			gameOver = true;
			return;
		}
		if (n.containsWumpus()) {
			// u ded -- game ends
			gameOver = true;
			return;
		}
		if (n.containsGlitter()) {
			glitter = true;
		}
		if (n.hasStench()) {
			stench = true;
		}
		if (n.hasBreeze()) {
			breeze = true;
		}
		// TODO: need to fix this one for specific wall?
		//for (Node neighbor : n.getNeighbors()) {
		//	if (neighbor.isWall())
		//		bump = true;
		//}
		
		//Check all percepts together and make decisions based on propositional logic
		System.out.println("stench =" + stench + " breeze=" + breeze + " glitter="+ glitter + " bump=" + bump + " scream="+ scream);
		
		// It is safe to move forward
		if (!stench && !breeze && !glitter && !bump && !scream) {
			agent.moveForward();
			prev = currNode; 
			currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
			currNode.setPrev(prev); // Set previous Node
			currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
			currNode.setVisited();
		}
		
		// There is a wall next to agent
		else if (!stench && !breeze && !glitter && bump && !scream) {
			
		}

		
		// The wumpus is in an adjacent Node
		else if (stench && !breeze && !glitter && !bump && !scream) {
			System.out.println("Wumpus in adjacent node");
			//TODO: finish logic
			
			for (Node neighbor : currNode.getNeighbors()) {
				neighbor.setGuess('W'); // each neighbor could possibly be the wumpus
			}
			
		}
		
		// There is a pit in an adjacent Node
		else if (!stench && breeze && !glitter && !bump && !scream) {
			System.out.println("Pit in adjacent node");
			//TODO: finish logic
		}
		
		//TODO: rest of the possible combinations...
		
		
		//TEMP: end game after first iteration
		gameOver = true;
	}
	
}

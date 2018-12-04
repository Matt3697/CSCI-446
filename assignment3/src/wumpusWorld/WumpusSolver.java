package wumpusWorld;

import java.util.ArrayList;

public class WumpusSolver {

	//private Node[][] maze;
	Maze maze;
	Agent agent;
	private Wumpus wumpus;
	private Node start, currNode;
	private boolean gameOver, isSafe;
	private ArrayList<Node> path = new ArrayList<Node>();
	private Node[][] kb;
	
	public WumpusSolver(Maze m, Agent a, Wumpus w) {
		this.maze = m;
		this.start = m.getNode(0, 0);
		this.agent = a;
		this.wumpus = w;
		//this.kb = m.getNodeMatrix();
		agent.setDirection("East");
	}
	
	public void solveMaze() {
		currNode = start;
		currNode.setHasAgent(true);
		currNode.setGuess('*');
		maze.printNodeMatrix();
		
		while (!gameOver) {
			checkPercepts(currNode);
			maze.printNodeMatrix();
			maze.printKnowledgeBase();
		}
	}
	
	/* 
	 * [no stench, no breeze, no glitter, no bump, no scream] = can move forward
	 */
	
	private void checkPercepts(Node n) {
		boolean stench = false, breeze = false, glitter = false, bump = false, scream = false;
		Node prev;
		path.add(n);
		if (n.containsPit()) {
			// u ded -- game ends
			agent.editPerformanceMeasure(-1000);
			gameOver = true;
			System.out.println("Fell in pit");
			System.out.println("Agent did not complete the task with " + path.size() + " moves.");
			System.out.println("Agent performance measure: " + agent.getPerformanceMeasure());
			return;
		}
		if (n.containsWumpus()) {
			// u ded -- game ends
			agent.editPerformanceMeasure(-1000);
			gameOver = true;
			System.out.println("Wumpus killed agent");
			System.out.println("Agent did not complete the task with " + path.size() + " moves.");
			System.out.println("Agent performance measure: " + agent.getPerformanceMeasure());
			return;
		}
		currNode.setGuess('*'); // Since current node is not a pit or wumpus, it is safe to stand on
		
		if (n.containsGlitter()) {//the gold is in the same square as the glitter. have the agent grab the gold.
			glitter = true;
			System.out.println("Agent found gold");
			agent.grab();
			goToStart();
			gameOver = true;
			return;
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
		System.out.println("stench =" + stench + " breeze=" + breeze + " glitter="+ glitter + " bump=" + bump + " scream="+ scream);
		
		// It is safe to move forward
		if (!stench && !breeze) {
			agent.moveForward(maze);
			prev = currNode; 
			currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
			currNode.setPrev(prev); // Set previous Node
			currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
			currNode.setVisited();
		}
		
		
		// The wumpus is in an adjacent Node
		else if (stench && !breeze) {
			System.out.println("Wumpus in adjacent node");
			
			for (Node neighbor : currNode.getNeighbors()) {//guess where wumpus could be
				if(neighbor.getGuess() != '*') {
					//wumpus can be here
					neighbor.setGuess('W'); // each neighbor could possibly be the wumpus
				}
			}
			if(agent.hasArrow() && agent.shootArrow(maze, wumpus)) {//if we kill the wumpus, it is safe to move forwards.
				System.out.println("Killed Wumpus");
				for(Node neighbor: currNode.getNeighbors()) {
					neighbor.setStench(false);
					neighbor.setGuess('*');//neighboring spots are safe, since there is no breeze perceived.
					//updateKB(neighbor.getX(), neighbor.getY(), '*');
					
				}
				agent.moveForward(maze);
				prev = currNode; 
				currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
				currNode.setPrev(prev); // Set previous Node
				currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
				currNode.setVisited();
				currNode.setWumpus(false);
			}
			else {
				for (Node neighbor : currNode.getNeighbors()) {//guess where wumpus could be
					//TODO: neighbor in direction we shot will not have the wumpus in it
					if(neighbor.getGuess() != 'W') {//if there is likely a wumpus in the next square, change directions.
						agent.moveForward(maze);
						prev = currNode; 
						currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
						currNode.setPrev(prev); // Set previous Node
						currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
						currNode.setVisited();
						break;
					}
				}
				
			}
		}
		
		// There is a pit in an adjacent Node
		else if (!stench && breeze) {
			System.out.println("Pit in adjacent node");
			//TODO: finish logic
			if(bump = true) {//can infer that there is no pit in the same direction, but there is a wall
				
			}
			for (Node neighbor : currNode.getNeighbors()) {//guess where pit could be
				if (neighbor.getGuess() == '_')
					neighbor.setGuess('P'); // each neighbor could possibly be a pit
			}
			agent.nextDirection();
			agent.moveForward(maze);
			prev = currNode; 
			currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
			currNode.setPrev(prev); // Set previous Node
			currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
			currNode.setVisited();
		}
		//There is a pit and wumpus in adjacent node(s)
		else if(stench && breeze) {
			System.out.println("Pit and Wumpus in adjacenct node(s)");
			agent.nextDirection();
			agent.moveForward(maze);
			prev = currNode; 
			currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
			currNode.setPrev(prev); // Set previous Node
			currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
			currNode.setVisited();
		}
		
	}
	public void goToStart() {//follow path back to starting point.
		for(int i = path.size() - 1; i >= 0; i--) {
			agent.setX(path.get(i).getX());
			agent.setY(path.get(i).getY());
			agent.editPerformanceMeasure(-1);
			if(path.get(i).getX() == 0 && path.get(i).getY() == 0) {
				System.out.println("Climbed out of cave with gold!");
				agent.editPerformanceMeasure(1000);
			}
		}
		System.out.println("Agent completed task with " + path.size() * 2 + " moves.");
		System.out.println("Agent performance measure: " + agent.getPerformanceMeasure());
	}
	
	/*
	 * Need to update Nodes based on current percepts. For example, if we have the current kb as:
	 * 	_*_P
	 *  	W_P_
	 *	____
	 *	____
	 *
	 * then we know that (0,1) must be the wumpus since the node (1,0) is marked safe (with '*') 
	 */
	public void updateKB(int x, int y, char chr) {
		Node[][] kb = maze.getNodeMatrix();
		kb[x][y].setValue(chr);
		
	}
}

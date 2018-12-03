package wumpusWorld;

import java.util.ArrayList;

public class WumpusSolver {

	//private Node[][] maze;
	Maze maze;
	Agent agent;
	private Wumpus wumpus;
	private Node start, currNode;
	private boolean gameOver, agentMoved;
	private ArrayList<Node> path = new ArrayList<Node>();
	
	public WumpusSolver(Maze m, Agent a, Wumpus w) {
		this.maze = m;
		this.start = m.getNode(0, 0);
		this.agent = a;
		this.wumpus = w;
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
		path.add(n);
		if (n.containsPit()) {
			// u ded -- game ends
			agent.editPerformanceMeasure(-1000);
			gameOver = true;
			System.out.println("Fell in pit");
			return;
		}
		if (n.containsWumpus()) {
			// u ded -- game ends
			agent.editPerformanceMeasure(-1000);
			gameOver = true;
			System.out.println("Wumpus killed agent");
			return;
		}
		if (n.containsGlitter()) {//the gold is in the same square as the glitter. have the agent grab the gold.
			glitter = true;
			System.out.println("Agent found gold");
			agent.grab();
			goToStart();
			gameOver = true;
			agent.editPerformanceMeasure(1000);
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
			agent.editPerformanceMeasure(-1);
			System.out.println("agent hit a wall");
			agent.setDirection("South");
		}
		if(agent.getDirection() == "South" && n.hasBottomWall()) {
			bump = true;
			agent.editPerformanceMeasure(-1);
			System.out.println("agent hit a wall");
			agent.setDirection("West");
		}
		if(agent.getDirection() == "West" && n.hasLeftWall()) {
			bump = true;
			agent.editPerformanceMeasure(-1);
			System.out.println("agent hit a wall");
			agent.setDirection("North");
		}
		if(agent.getDirection() == "North" && n.hasTopWall()) {
			bump = true;
			agent.editPerformanceMeasure(-1);
			System.out.println("agent hit a wall");
			agent.setDirection("East");
		}
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
		/*
		else if (!stench && !breeze && !glitter && bump && !scream) {
			System.out.println("agent hit a wall");
		}
		*/
		
		// The wumpus is in an adjacent Node
		else if (stench && !breeze && !glitter && !bump && !scream) {
			System.out.println("Wumpus in adjacent node");
			//TODO: finish logic
			if(agent.shootArrow(maze, wumpus)) {//if we kill the wumpus, it is safe to move forwards.
				System.out.println("Killed Wumpus");
				agent.moveForward();
				prev = currNode; 
				currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
				currNode.setPrev(prev); // Set previous Node
				currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
				currNode.setVisited();
				currNode.setWumpus(false);
				for(Node neighbor: currNode.getNeighbors()) {
					neighbor.setStench(false);
				}
			}
			else {
				System.out.println("Missed Wumpus");//also safe to move forward...
				for (Node neighbor : currNode.getNeighbors()) {//guess where wumpus could be
					neighbor.setGuess('W'); // each neighbor could possibly be the wumpus
				}
			}
		}
		
		// There is a pit in an adjacent Node
		else if (!stench && breeze && !glitter && !bump && !scream) {
			System.out.println("Pit in adjacent node");
			//TODO: finish logic
			if(bump = true) {//can infer that there is no pit in the same direction, but there is a wall
				
			}
			if(agent.getDirection() == "East") {
				agent.editPerformanceMeasure(-1);
				agent.setDirection("South");
			}
			else if(agent.getDirection() == "South") {
				agent.editPerformanceMeasure(-1);
				agent.setDirection("West");
			}
			else if(agent.getDirection() == "West") {
				agent.editPerformanceMeasure(-1);
				agent.setDirection("North");
			}
			else if(agent.getDirection() == "North") {
				agent.editPerformanceMeasure(-1);
				agent.setDirection("East");
			}
			agent.moveForward();
			prev = currNode; 
			currNode = maze.getNode(agent.getX(), agent.getY()); // Update current Node
			currNode.setPrev(prev); // Set previous Node
			currNode.setHasAgent(true); // The agent is on this Node -- used for printing the maze after each iteration
			currNode.setVisited();
		}
		
		//TODO: rest of the possible combinations...
		
		
		//TEMP: end game after first iteration
		//gameOver = true;
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
}

import java.util.ArrayList;
import java.util.Arrays;

//Purpose: Given an undirected weighted graph, find the shortest distance to all nodes given a random source node; also, given a directed weighted graph, find the shortest
//		   distance to every node for all nodes
public class GraphShortestPaths {	
	static ArrayList<Node> graph;									//ArrayList for storing the nodes of the graph
	static Node source;												//Node variable for storing the designated source node
	public static void main (String[] args){
		GraphShortestPaths p = new GraphShortestPaths();
		graph = p.makeGraph(true);									//initiates a custom, undirected, weighted graph
		source = graph.get((int)(Math.random() * graph.size()));	//makes the source node a random node in the graph
		p.findShortestPaths(source);								//given the source, finds the shortest distance to all nodes
		System.out.print("Undirected Graph with Random Source: ");	//clarification in console
		p.printDistances(false);									//prints the shortest distances
		System.out.println("");
		System.out.println("Directed Graph Showing every Pair: ");	//clarification in the console
		graph = p.makeGraph(false);									//remakes the graph into a directed, weighted graph
		p.printDistances(true);										//prints the shortest distances to all nodes for each node
	}
	
	public ArrayList<Node> makeGraph(boolean isUndirected){			//method for making a custom weighted graph, undirected if parameter is true
		Node one = new Node(1);										//creates new node, given the number of their order for simplicity, number parameter can be any int
		Node two = new Node(2);
		one.addConnection(two, 1, isUndirected);					//makes connection between two nodes with the weight, if isUndirected is false connection is one way
		Node three = new Node(3);
		one.addConnection(three, 2, isUndirected);
		two.addConnection(three, 3, isUndirected);
		Node four = new Node(4);
		three.addConnection(four, 4, isUndirected);
		Node five = new Node(5);
		four.addConnection(five, 5, isUndirected);
		Node six = new Node(6);
		five.addConnection(six, 7, isUndirected);
		four.addConnection(six, 6, isUndirected);
		Node seven = new Node(7);
		two.addConnection(seven, 8, isUndirected);
		five.addConnection(seven, 9, isUndirected);
		Node eight = new Node(8);
		four.addConnection(eight, 10, isUndirected);
		seven.addConnection(eight, 11, isUndirected);
		ArrayList<Node> Nodes = new ArrayList<Node>(Arrays.asList(one, two, three, four, five, six, seven, eight));		
		return Nodes; 																//returns the graph of custom made and custom connected nodes
	}
	
	public void findShortestPaths(Node current){		//recursive method for finding shortest path to all nodes, current starts as source
		int counter = 0;														
		source.shortestDistance = 0;												//sets source distance to itself as 0, starts at 99999999 for all nodes
		while (counter < current.connections.size()){								//while loop for processing each of a node's connections
			int oldDistance = current.connections.get(counter).shortestDistance;	//distance comparing variable, shortest distance of connection node
			if (current.connections.get(counter).shortestDistance > current.shortestDistance + current.weights.get(counter)){	//if connection node shortest distance > 
				current.connections.get(counter).shortestDistance = current.shortestDistance + current.weights.get(counter);	//current node shortest distance + weight
			}																													//to that connected node, set it as that
			if (current.shortestDistance + current.weights.get(counter) < oldDistance){		//if the new shortest distance for the connector node < the old one
				findShortestPaths(current.connections.get(counter));						//call the method again with the connected node as the new current
			}
			counter++;
		}
	}
	
	public void printDistances(boolean everyPair){									//recursive method for printing the shortest distances
		if (!everyPair){															//if undirected, only one source is required
			System.out.println("Source Node: " + source.number);					//prints the number of the source node
			for (int i = 0; i < graph.size(); i++){									//for each node, prints the shortest distance to the source
				System.out.println("Node " + graph.get(i).number + "'s Shortest Distance to Source: " + graph.get(i).shortestDistance);
			}
		}
		else{																		//if directed, printing the shortest distances to all nodes for all sources is required
			for (int i = 0; i < graph.size(); i++){									//for loop making each node in the graph the source
				source = graph.get(i);
				for (int j = 0; j < graph.size(); j++){								//resets the shortest distances of the nodes to prepare for a new run
					graph.get(j).shortestDistance = 999999999;
				}
				findShortestPaths(source);											//finds the shortest paths to all nodes for this source
				printDistances(false);												//prints the shortest distances to this source using recursion
			}
		}
	}
	
	public static class Node{														//node class
		int number;																	//is assigned a number in declaration
		int shortestDistance = 999999999;											//default shortest distance to source is very large
		ArrayList<Node> connections = new ArrayList<Node>();						//has an ArrayList for connected nodes
		ArrayList<Integer> weights = new ArrayList<Integer>();						//has an ArrayList for weights of vertices to connected nodes
		
		public Node(int number){													//constructor
			this.number = number;
		}
		public void addConnection(Node newConnect, int weight, boolean reverse){	//adds a connected node and a weight to the vertex to the node
			connections.add(newConnect);
			weights.add(weight);
			if (reverse){															//if reverse is true, the connection is 2 ways
				newConnect.addConnection(this, weight, false);
			}
		}
	}
}
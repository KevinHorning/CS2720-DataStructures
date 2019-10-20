// Makes a binary grid randomly filled with 1s and 0s, then finds the shortest path 
// to move from top-left to the right side only navigating on 0s

import java.util.ArrayList;
import java.util.Stack;

public class GridPath {
	static int size = 10;													//dimensions of map
	static Stack<Integer> coordinates = new Stack<Integer>();				//stores ints that are x,y coordinates
	static ArrayList<Integer> badCoordinates = new ArrayList<Integer>();	//stores coordinates that lead to dead end
	public static void main (String[] args){
		boolean[][] map = makeMap(size);									//makes boolean matrix of size*size dimension 
		int start = pickStart(map);											//picks a row to start from
		coordinates.push(start * 10);										//adds start coordinate to coordinate list
		printMap(map);														
		System.out.println("Start:  (" + start + "," + 0 + ")");			//beginning of output solution
		findShortestPath(map, start * 10);									//recursive method for finding rest of the solution
		printRoute(coordinates);											//prints solution
	}
	
	public static boolean[][] makeMap(int num){
		boolean[][] map = new boolean[num][num];
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				double random = Math.random();
				if (random > .75)						//determines how many sensors are in the matrix
					map[i][j] = false;
				else
					map[i][j] = true;
			}
		}
		return map;
	}
	
	public static int pickStart(boolean[][] map){
		int startingRow = (int)(Math.random() * 10);
		if (map[startingRow][0] == false)							//reruns if starting coordinate is a sensor
			return pickStart(map);
		else{
			return startingRow;
		}
	}
	
	public static void printMap(boolean[][] map){
		for (int i = 0; i < size; i++){
			System.out.print(i);
			for (int j = 0; j < size; j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void findShortestPath(boolean[][] map, int start){
		if (start % 10 != size - 1){						//checks coord at right (if not sensor and is not dead end)
			if (map[start / 10][start % 10 + 1] && !badCoordinates.contains(start + 1)){	
				coordinates.push(start + 1);				//adds the coordinate to the list of coordinates
				findShortestPath(map, start + 1);			//redoes method, with new coordinate
			}
			else{											//checks coord above (if exists, not sensor, and is not dead end)
				if ((start / 10 != 0) && map[(start / 10) - 1][start % 10] &&  !(coordinates.contains(start - 10)) && !badCoordinates.contains(start - 10)){
					coordinates.push(start - 10);			//adds the coordinate to the list of coordinates
					findShortestPath(map, start - 10);		//redoes method, with new coordinate
				}
				else{										//checks coord below (if exists, not sensor, and is not dead end)
					if ((start / 10 != 9) && map[start / 10 + 1][start % 10] &&  !(coordinates.contains(start + 10)) && !badCoordinates.contains(start + 10)){
						coordinates.push(start + 10);		//adds the coordinate to the list of coordinates
						findShortestPath(map, start + 10);	//redoes method, with new coordinate
					}
					else{
						badCoordinates.add(start);						//if nowhere to go, it is a bad coordinate
						coordinates.pop();								//is is removed from the coordinate list
						if (!coordinates.isEmpty())						//avoids empty stack error
							findShortestPath(map, coordinates.peek());	//either finds new route from last coordinate or
						else
							System.out.println("No Path");				//if no path, prints no path
					}
				}
			}
		}
	}
	
	public static void printRoute(Stack<Integer> coords){				//recursive method for printing stack in proper order
		if (coords.size() <= 1){
		}
		else{
			int popped = coords.pop();					
			printRoute(coords);
			System.out.println("Move to (" + (popped / 10) + "," + (popped % 10) + ")");
		}
	}
}
// Algorithm for solving the Towers of Hanoi puzzle
// Game prompt asks how many blocks there are, then it prints each block move until the algorithm solves the puzzle

import java.util.Scanner;
import java.util.Stack;
public class HanoiTowers {
	public static void main(String[] args){
		Scanner S = new Scanner(System.in);								//creates Scanner for input for the number of blocks
		while (true){
			System.out.println("Enter number of blocks (0 to exit): ");
			int numberOfBlocks = Integer.parseInt(S.next());
			if (numberOfBlocks == 0){									//if input is 0, program exits
				System.out.println("Goodbye");
				break;
			}
			HanoiTowers Towers = new HanoiTowers(numberOfBlocks);		//creates Hanoi Towers with an entered number of blocks (number must be a positive integer)
			Towers.transferTower(Tower1, Tower2, Tower3);				//transfers first tower blocks to the third Block, in the same order
		}
	}

	
	
	static Stack Tower1 = new Stack();									//creates stacks for the 3 towers
	static Stack Tower2 = new Stack();
	static Stack Tower3 = new Stack();
	Stack initialTower = new Stack();									//creates replica of the beginning first tower to determine when complete
	int numberOfBlocks;																	
	int moveNumber = 1;
	
	public HanoiTowers(int numberOfBlocks){							//constructor
		this.numberOfBlocks = numberOfBlocks;
		Tower1.clear();													//clears towers if not already empty from previous executions
		Tower2.clear();
		Tower3.clear();
		for (int i = numberOfBlocks; i > 0; i--){
			Tower1.push(i);												//creates Tower1 based on the number of blocks, Tower1 will change frequently
			initialTower.push(i);										//creates Initial Tower that does not change
		}
		
	}
	
	public void transferTower(Stack one, Stack two, Stack three){		//recursive algorithm for transferring the the first Tower stack to the third Tower
		if (three.equals(initialTower) || moveNumber > 9999){			//if transfer is complete or the program overflows, "completed" is printed
			System.out.println("Completed");
			System.out.println("");
		}
		else{													
			if (numberOfBlocks % 2 == 0){								//if the number of blocks is an even number, the algorithm will run in a certain order
				if (!Tower3.equals(initialTower)){						
					if (Tower1.isEmpty()){								//first move involves Tower1 and Tower2		
						Tower1.push(Tower2.pop());						//if either Tower is empty, the top block moves to from the other tower to the empty tower
						System.out.println("2 to 1");
					}
					else if (Tower2.isEmpty()){
						Tower2.push(Tower1.pop());
						System.out.println("1 to 2");
					}
					else{												//if neither are empty, the lowest top between the 2 towers moves to the top of the other tower
						if ((int)Tower1.peek() > (int)Tower2.peek()){
							Tower1.push(Tower2.pop());
							System.out.println("1 to 2");
						}
						else{
							Tower2.push(Tower1.pop());
							System.out.println("1 to 2");				//each move is printed, displaying to and from which tower
						}
					}													
				}														
				if (!Tower3.equals(initialTower)){						//checks to see if the puzzle is complete before every move (to avoid errors)
					if (Tower1.isEmpty()){								//same process but involving Tower1 and Tower3 this time
						Tower1.push(Tower3.pop());
						System.out.println("3 to 1");
					}
					else if (Tower3.isEmpty()){
						Tower3.push(Tower1.pop());
						System.out.println("1 to 3");
					}
					else{
						if ((int)Tower1.peek() > (int)Tower3.peek()){
							Tower1.push(Tower3.pop());
							System.out.println("3 to 1");
						}
						else{
							Tower3.push(Tower1.pop());
							System.out.println("1 to 3");
						}
					}
				}
				if (!Tower3.equals(initialTower)){						//same process involving Tower2 and Tower3
					if (Tower2.isEmpty()){								
						Tower2.push(Tower3.pop());
						System.out.println("3 to 2");
					}
					else if (Tower3.isEmpty()){
						Tower3.push(Tower2.pop());
						System.out.println("2 to 3");
					}
					else{
						if ((int)Tower2.peek() > (int)Tower3.peek()){
							Tower2.push(Tower3.pop());
							System.out.println("3 to 2");
						}
						else{
							Tower3.push(Tower2.pop());
							System.out.println("2 to 3");
						}
					}
				}
			}
			else{														//if the number of blocks is an odd number, the algorithm will run in a different order
				if (!Tower3.equals(initialTower)){						//same process involving Tower1 and Tower3
					if (Tower1.isEmpty()){								
						Tower1.push(Tower3.pop());
						System.out.println("3 to 1");
					}
					else if (Tower3.isEmpty()){
						Tower3.push(Tower1.pop());
						System.out.println("1 to 3");
					}
					else{
						if ((int)Tower1.peek() > (int)Tower3.peek()){
							Tower1.push(Tower3.pop());
							System.out.println("3 to 1");
						}
						else{
							Tower3.push(Tower1.pop());
							System.out.println("1 to 3");
						}
					}
				}
				if (!Tower3.equals(initialTower)){						//same process involving Tower1 and Tower2		
					if (Tower2.isEmpty()){								
						Tower2.push(Tower1.pop());
						System.out.println("1 to 2");
					}
					else if (Tower1.isEmpty()){
						Tower1.push(Tower2.pop());
						System.out.println("2 to 1");
					}
					else{
						if ((int)Tower2.peek() > (int)Tower1.peek()){
							Tower1.push(Tower1.pop());
							System.out.println("1 to 2");
						}
						else{
							Tower1.push(Tower1.pop());
							System.out.println("2 to 1");
						}
					}
				}
				if (!Tower3.equals(initialTower)){						//same process involving Tower2 and Tower3
					if (Tower2.isEmpty()){								
						Tower2.push(Tower3.pop());
						System.out.println("3 to 2");
					}
					else if (Tower3.isEmpty()){
						Tower3.push(Tower2.pop());
						System.out.println("2 to 3");
					}
					else{
						if ((int)Tower2.peek() > (int)Tower3.peek()){
							Tower2.push(Tower3.pop());
							System.out.println("3 to 2");
						}
						else{
							Tower3.push(Tower2.pop());
							System.out.println("2 to 3");
						}
					}
				}
			}
			transferTower(Tower1, Tower2, Tower3);
		}
	}
	
	public void printTowers(){											//prints the contents of each tower and the move number
		System.out.println("Move2 " + moveNumber);
		System.out.println("Tower 1 (Bottom to Top): "  + Tower1);
		System.out.println("Tower 2 (Bottom to Top): "  + Tower2);
		System.out.println("Tower 3 (Bottom to Top): "  + Tower3);
		moveNumber++;
	}
}
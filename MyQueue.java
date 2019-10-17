//Kevin Horning
//09/13/17
//Data Structures TR 2:30-3:45 Lab F 11-12

//Project Objective: To create a first-in-first-out data structure (a Queue) that utilizes the Stack data structure.  
//Project Solution: To use two separate stacks, one for storing the data added to the Queue and one for helping 
//	delete the oldest data values. Because Stacks can only access the newest data, two Stacks are necessary to access 
//	first in data and maintain organization of the rest of the data. To do this, the second Stack (that starts empty) 
//	pushes the data from the first Stack (in which the data is stored) while it is popped from the first Stack one by
//	one. As a result, the data will be stored in the second Stack from last to first. Then the second Stack top (the 
//	oldest data) will be popped. Finally, the second Stack will transfer its data back to the first Stack in the 
//	correct order (minus the first data value that was popped).
//Data Structures Used: Stack and Queue
//To Use: In the main method, create a MyQueue object and use the enQueue(String) method to add values to the end of 
//	the Queue. Use the deQueue() method to remove the first value from the Queue. Use the printQueue() method to
//	output the Queue to the console.

import java.util.*;

public class MyQueue {
	public static void main (String[] args){
		MyQueue s = new MyQueue();				//MyQueue object initialization
		s.enQueue("aa");						//MyQueue object method calls
		s.enQueue("bb");
		s.enQueue("cc");
		s.enQueue("dd");
		s.enQueue("eee");
		s.deQueue();
		s.printQueue();
	}
		
	Stack one, two;								//Initiation of both Stacks: Stack one for storage of the Queue, 
													//Stack two for helping remove the first/oldest data value											
	public MyQueue(){
		one = new Stack();
		two = new Stack();
	}
	
	public void enQueue(String s){				//Precondition: parameter must be a String
			one.push(s);						//Adds a String value to the end of the Queue
	}
	
	public String deQueue(){					//Precondition: Queue cannot be empty 
		while (!one.isEmpty()){					//Transfers one data to two, in opposite order
			two.push(one.pop());
		}
		String popped = (String) two.pop();		//Deletes the last value of the two (the first one value)
		while (!two.isEmpty()){					//Transfers the other two values back to one in correct order
			one.push(two.pop());		
		}
		return popped;
	}
	
	public void printQueue(){					//Prints the values of the Queue
		if (one.isEmpty()){
		}
		else{
			String popped = (String) one.pop();
			printQueue();						//Uses recursion to pop, print, and then push each Queue value
			one.push(popped);
			System.out.print(popped + " ");
		}
	}
}
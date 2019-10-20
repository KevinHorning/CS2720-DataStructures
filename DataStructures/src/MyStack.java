// Creates a Stack structure with push, pop, test empty, print, and reverse functions

import java.util.EmptyStackException;

public class MyStack {
	public static void main(String[] args){
		MyStack stack = new MyStack(5);
		stack.push("q");
		stack.push("g");
		stack.push("zdsaa");
		stack.push("g");
		stack.push("u");

		stack.printStack();
		stack.reverseStack().printStack();
	}
	
	String[] stringStack;
	int top; 
	int size;
	
	public MyStack(int size){								//constructor
		stringStack = new String[size];
		top = 0;
		this.size = size;
	}
	
	public void push(String New){						//push method
		stringStack[top] = New;
		top++;
	}
	
	public String pop(){								//pop method
		if (isEmpty()){
			throw new EmptyStackException();	
		}
		String popped = stringStack[top - 1];			   
		top--;
		return popped;
	}
	
	public boolean isEmpty(){							//is empty method
		if (top == 0)
			return true;
		else
			return false;
	}
	
	public void printStack(){							//prints Stacks in visible form
		String string = new String();
		for (int i = 0; i < top; i++){
			string += " " + this.stringStack[i];
		}
		System.out.println(string);
	}
	
	public MyStack reverseStack(){						//reverses Stack
		MyStack stack = new MyStack(size);
		for (int i = 0; i < size; i++){
				stack.push(this.pop());
		}
		return stack;
	}
}
  
// converts an expression from Postfix to Infix form

import java.util.*;

public class ToInFix {
	
	public static void main (String[] args){
		Scanner S = new Scanner(System.in);										//makes Scanner for input
		String postFix;
		while (true){															//can do multiple calculations in one run
			System.out.println("Enter Postfix expression (0 to exit): ");		
			postFix = S.next();													//postfix is input
			if (postFix.equals("0")){
				System.out.println("Goodbye");
				break;
			}
			System.out.println("Answer: ");
			System.out.println(calculate(postFix));								//prints result of calculate method with postifx input
		}
	}
	public static int calculate(String input){									//calculate method for getting answer
		Stack<Integer> math = new Stack<Integer>();								//stack for storing numbers to be used in operations
		for (int i = 0; i < input.length(); i++){								//does a Stack push or a calculation for every element in the input 									
			if (Character.isDigit(input.charAt(i))){							//if current input element is a number
				math.push(Character.getNumericValue(input.charAt(i)));			//then pushes the number to the stack
			}
			else if (input.charAt(i) == '+'){									//if element is addition sigh
				int firstNumber = math.pop();
				int secondNumber = math.pop();
				math.push(firstNumber + secondNumber);							//then adds the last 2 numbers of the stack and pushes total to the stack
			}
			else if (input.charAt(i) == '-'){									//if element is subtraction sign
				int firstNumber = math.pop();
				int secondNumber = math.pop();
				math.push(secondNumber - firstNumber);							//then subtracts last number in the stack from the second to last
			}
			else if (input.charAt(i) == '*'){									//if element is multiplication sign
				int firstNumber = math.pop();
				int secondNumber = math.pop();
				math.push(firstNumber * secondNumber);							//then multiplies the last two numbers of the stack and pushes total to stack
			}
			else if (input.charAt(i) == '/'){									//if element is division sign
				int firstNumber = math.pop();
				int secondNumber = math.pop();
				math.push(secondNumber / firstNumber);							//then divides last number in stack by second to last number and pushes total
			}
		}
		return math.pop();														//returns the last number in stack, which is the answer
	}
}
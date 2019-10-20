// Converts an expression from infix to postfix form

import java.util.*;

public class toPostFix {
	static String infix;
	
	public static void main (String[] args){
		Scanner S = new Scanner(System.in);
		while (true){
			System.out.println("Enter Infix expression (0 to exit): ");
			infix = S.next();
			if (infix.equals("0")){
				System.out.println("Goodbye");
				break;
			}
			System.out.println("Postfix expression");
			System.out.println(toPostFix(infix));
		}
	}

	public static String toPostFix(String infix){
		Stack operators = new Stack();
		String postfix = "";
		for (int i = 0; i < infix.length(); i++){
			if (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == 'x' || infix.charAt(i) == '.'){
				if (infix.charAt(i) == '.'){
					return "Error in expression! Cannot accept floating point numbers.";
				}
				if (i != 0){
					if (Character.isDigit(infix.charAt(i - 1)))
						return "Error in expression! No operator between operands. Also last token must be an operand." + i;
				}
				postfix += infix.charAt(i);
			}
			else if (infix.charAt(i) == '('){
				if (!(Character.isDigit(infix.charAt(i + 1)) || infix.charAt(i + 1) == 'x')){
					return "Error in expression! Either a digit or x must go after a left parenthesis.";
				}
				operators.push(infix.charAt(i));
			}
			else if (infix.charAt(i) == ')'){
				if (!(Character.isDigit(infix.charAt(i - 1)) || infix.charAt(i - 1) == 'x')){
					return "Error in expression! Either a digit or x must precede a right parenthesis.";
				}
				while (!operators.peek().equals('(')){
					postfix += operators.pop();
				}
				operators.pop();
			}
			else {
				if (operators.isEmpty()){
					operators.push(infix.charAt(i));
				}
				else {
					if (infix.charAt(i - 1) == '+' || infix.charAt(i - 1) == '-' || infix.charAt(i - 1) == '*' || infix.charAt(i - 1) == '/' || infix.charAt(i - 1) == '%'){
						return "Error in expresion! an operator cannot be preceded by another operator";
					}
					while (determinePrecedence(infix.charAt(i)) < determinePrecedence((char) operators.peek())){
						postfix += operators.pop();
					}
					operators.push(infix.charAt(i));
				}
			}
		}
		while (!operators.isEmpty()){
				postfix += operators.pop();
		}
		
		return postfix;
	}
	
	public static int determinePrecedence(char operand){
		if (operand == '+' || operand == '-'){
			return 1;
		}
		if (operand == '*' || operand == '/' || operand == '%')
			return 2;
		return 0;	
	}
}
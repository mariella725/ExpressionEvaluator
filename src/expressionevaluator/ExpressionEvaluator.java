/* 
	Button Software Engineer Coding Challenge 2018 
	Applicant: Mariella Sypa 

	A Java program that takes in a string expression composed of operators and numbers and does the following: 
		1) converts string to char array (each element is a "token")
		2) parses through the array and pushes each token to either the stack of numbers or operators depending on what it is 
		3) compares precedence of operands to handle rules of PEMDAS 
		4) evaluates and applies operators to operands of the expression 
		5) prints solution to expression

		Test Cases:
		"* 6 12" = 72
		"10 * 2 + 12" = 32
		"100 * ( 2 + 12 )" = 1400
		"* 2 3 + 12" = 18
		"+ 2 3 * 12" = 38
*/

package expressionevaluator;
import java.util.Stack;
public class ExpressionEvaluator {

	public static int evaluate(String expression){

		char[] tokens = expression.toCharArray();

		// stack for numbers
		Stack<Integer> numbers = new Stack<Integer>();

		// stack for operators
		Stack<Character> operators = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++){
			// continue with loop if current token is a whitespace
			if (tokens[i] == ' ')
				continue;

			// if our current token is a number, then we push it to stack of numbers
			if (tokens[i] >= '0' && tokens[i] <= '9'){
				StringBuffer sbuf = new StringBuffer();
				// check in case there is more than one number in a row
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				numbers.push(Integer.parseInt(sbuf.toString()));
			}

			// if current token is an opening brace, push it to stack of operators
			else if (tokens[i] == '(')
				operators.push(tokens[i]);
		
			// if current token is a closing brace, solve the expression within the braces
			else if (tokens[i] == ')') {
				while (operators.peek() != '(')
					numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
				operators.pop();
			}

			// if the current token is an operator 
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				// while the current operator has a higher or 
				// same precendent to rest of operators in the stack 
				// we evaluate the expression
				while (!operators.empty() && hasPrecedence(tokens[i], operators.peek()))
					numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
				// push the current token to stack of operators
				operators.push(tokens[i]);
			}
		}

		// since we have parsed through the whole expression 
		/// we can now evaluate the remaining 
		// operators and numbers
		while (!operators.empty())
			numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));

		// the top of the number stack is our result
		return numbers.pop();
	}

	// evaluates whether or not an op2 has precendence over op1 
	// uses rules of PEMDAS to determine this
	public static boolean hasPrecedence(char op1, char op2){
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// function to perform operations and return result
	public static int applyOperator(char op, int b, int a){
		switch (op){
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0){
				throw new
				UnsupportedOperationException("Cannot divide by zero");
			}
			else 
				return a / b;
		}
	return 0;
}

	// driver method to test above methods
	public static void main(String[] args) {
		System.out.println(ExpressionEvaluator.evaluate("* 6 12"));
		System.out.println(ExpressionEvaluator.evaluate("10 * 2 + 12"));
		System.out.println(ExpressionEvaluator.evaluate("100 * ( 2 + 12 )"));
		System.out.println(ExpressionEvaluator.evaluate("* 2 3 + 12 "));
		System.out.println(ExpressionEvaluator.evaluate("+ 2 3 * 12 "));
	}
}
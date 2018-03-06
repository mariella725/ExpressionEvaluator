# Expression Evaluator

Button Software Engineer Coding Challenge 2018 <br />
Applicant: Mariella Sypa <br />
	A Java program that takes in a string expression composed of operators and numbers and does the following: <br />
		1) converts string to char array (each element is a "token") <br />
		2) parses through the array and pushes each token to either the stack of numbers or operators depending on what it is <br />
		3) compares precedence of operands to handle rules of PEMDAS <br />
		4) evaluates and applies operators to operands of the expression <br />
		5) prints solution to expression<br />

		Test Cases:
		"* 6 12" = 72
		"10 * 2 + 12" = 32
		"100 * ( 2 + 12 )" = 1400
		"* 2 3 + 12" = 18
		"+ 2 3 * 12" = 38

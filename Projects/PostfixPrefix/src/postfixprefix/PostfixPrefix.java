package postfixprefix;

import java.util.Scanner;
import java.util.Stack;

import java.lang.Math;

import java.util.ArrayList;

public class PostfixPrefix {

    private Stack<String> expression = new Stack<>();

    PostfixPrefix() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Please provide your infix expression:");

        String expressionInput = userInput.nextLine();
        while (!expressionInput.matches("-?\\d+") && !expressionInput.equals("x") && !expressionInput.equals(")") && !expressionInput.equals("(")) {
            System.out.println("Error: The expression must start with either a number or variable. Please re-enter:");
            expressionInput = userInput.nextLine();
        }

        int parenthesesBalance = 0;
        boolean isPreviousDigit = false;

        while (!expressionInput.equals("$")) {
            if ((expressionInput.matches("-?\\d+") && !isPreviousDigit)
                    || (expressionInput.matches("[+\\-^*/]") && isPreviousDigit)
                    || (expressionInput.equals("x") && !isPreviousDigit)) {

                expression.push(expressionInput);
                System.out.println("Input next element or type '$' to finish:");
                isPreviousDigit = !isPreviousDigit;

            } else if (expressionInput.equals("(")) {
                expression.push(expressionInput);
                parenthesesBalance++;
                System.out.println("Input next element or type '$' to finish:");

            } else if (expressionInput.equals(")")) {
                if (parenthesesBalance > 0) {
                    expression.push(expressionInput);
                    parenthesesBalance--;
                } else {
                    System.out.println("Error: No matching open parentheses.");
                }
                System.out.println("Input next element or type '$' to finish:");

            } else if (expressionInput.matches("-?\\d+") && isPreviousDigit) {
                System.out.println("Error: After a number or closing parenthesis, an operator is required. Re-enter the element or type 'END' to exit:");

            } else if (expressionInput.matches("[+\\-^*/]") && !isPreviousDigit) {
                System.out.println("Error: After an operator or opening parenthesis, a number or variable is required. Re-enter the element or type 'END' to exit:");

            } else {
                System.out.println("Error: Invalid input. Type '$' if expression is complete or continue input:");
            }

            expressionInput = userInput.nextLine();
        }

        if (!isPreviousDigit) {
            System.out.println("Note: The last operator did not have an operand, so it has been removed.");
            expression.pop();
        }

        if (parenthesesBalance > 0) {
            for (int i = 0; i < parenthesesBalance; i++) {
                expression.push(")");
            }
        }
        System.out.println("Infix order : " + expression);
    }

// ========================================================================================================== 
    public Stack<String> convertToPostfix() {

        Stack<String> postfixQueue = new Stack<>();
        Stack<String> operators = new Stack<>();
        Stack<String> expressionClone = new Stack<>();

        expressionClone.addAll(expression);

        while (!expressionClone.isEmpty()) {
            String element = expressionClone.remove(0);
            if (element.matches("-?\\d+|x")) {
                postfixQueue.push(element);
            } else if (element.equals("(")) {
                operators.push(element);
            } else if (element.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    postfixQueue.push(operators.pop());
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && precedence(element) <= precedence(operators.peek())) {
                    postfixQueue.push(operators.pop());
                }
                operators.push(element);
            }
        }

        while (!operators.isEmpty()) {
            postfixQueue.push(operators.pop());
        }

        return postfixQueue;
    }

// ========================================================================================================== 
    public Stack<String> convertToPrefix() {
        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        Stack<String> clonedExpression = new Stack<>();

        clonedExpression.addAll(expression);

        while (!clonedExpression.isEmpty()) {
            String element = clonedExpression.pop();

            if (element.matches("-?\\d+|x")) {
                operands.push(element);
            } else if (element.equals(")")) {
                operators.push(element);
            } else if (element.equals("(")) {
                while (!operators.isEmpty() && !operators.peek().equals(")")) {
                    String operator = operators.pop();
                    String operand1 = operands.pop();
                    String operand2 = operands.pop();
                    String prefix = operator + " " + operand1 + " " + operand2;
                    operands.push(prefix);
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && precedence(element) < precedence(operators.peek())) {
                    String operator = operators.pop();
                    String operand1 = operands.pop();
                    String operand2 = operands.pop();
                    String prefix = operator + " " + operand1 + " " + operand2;
                    operands.push(prefix);
                }
                operators.push(element);
            }
        }

        while (!operators.isEmpty()) {
            String operator = operators.pop();
            String operand1 = operands.pop();
            String operand2 = operands.pop();
            String prefix = operator + " " + operand1 + " " + operand2;
            operands.push(prefix);
        }

        Stack<String> result = new Stack<>();
        for (String token : operands.pop().split(" ")) {
            result.push(token);
        }

        return result;
    }

// ==========================================================================================================
    private int precedence(String operator) {
        if (operator.equals("^")) {
            return 3;
        } else if (operator.equals("/") || operator.equals("*")) {
            return 2;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else {
            return -1;
        }
    }

// ========================================================================================================== 
    public double computePrefix() {
        Stack<String> prefix = convertToPrefix();
        Stack<Double> numbers = new Stack<>();

        while (!prefix.isEmpty()) {
            String element = prefix.pop();

            if (element.matches("-?\\d+(\\.\\d+)?")) {
                numbers.push(Double.parseDouble(element));
            } else if (element.matches("[+\\-*/^]")) {
                double firstOperand = numbers.pop();
                double secondOperand = numbers.pop();

                if (element.equals("+")) {
                    numbers.push(firstOperand + secondOperand);
                } else if (element.equals("-")) {
                    numbers.push(firstOperand - secondOperand);
                } else if (element.equals("*")) {
                    numbers.push(firstOperand * secondOperand);
                } else if (element.equals("/")) {
                    numbers.push(firstOperand / secondOperand);
                } else if (element.equals("^")) {
                    numbers.push(Math.pow(firstOperand, secondOperand));
                }

            }
        }
        return numbers.pop();
    }

// ========================================================================================================== 
    public double compute(double variableValue) {
        Stack<String> prefix = convertToPrefix();
        Stack<Double> operandStack = new Stack<>();

        while (!prefix.isEmpty()) {
            String element = prefix.pop();

            if (element.matches("-?\\d+(\\.\\d+)?")) {
                operandStack.push(Double.parseDouble(element));
            } else if (element.equals("x")) {
                operandStack.push(variableValue);
            } else if (element.matches("[+\\-*/^]")) {
                double firstOperand = operandStack.pop();
                double secondOperand = operandStack.pop();

                if (element.equals("+")) {
                    operandStack.push(firstOperand + secondOperand);
                } else if (element.equals("-")) {
                    operandStack.push(firstOperand - secondOperand);
                } else if (element.equals("*")) {
                    operandStack.push(firstOperand * secondOperand);
                } else if (element.equals("/")) {
                    operandStack.push(firstOperand / secondOperand);
                } else if (element.equals("^")) {
                    operandStack.push(Math.pow(firstOperand, secondOperand));
                }
            }
        }
        return operandStack.pop();
    }

// ========================================================================================================== 
    public double computePostfix() {
        Stack<String> postfix = convertToPostfix();
        Stack<Double> valueStack = new Stack<>();

        while (!postfix.isEmpty()) {
            String element = postfix.remove(0);

            if (element.matches("-?\\d+(\\.\\d+)?")) {
                valueStack.push(Double.parseDouble(element));
            } else if (element.matches("[+\\-*/^]")) {
                double firstValue = valueStack.pop();
                double secondValue = valueStack.pop();

                if (element.equals("+")) {
                    valueStack.push(firstValue + secondValue);
                } else if (element.equals("-")) {
                    valueStack.push(secondValue - firstValue);
                } else if (element.equals("*")) {
                    valueStack.push(firstValue * secondValue);
                } else if (element.equals("/")) {
                    valueStack.push(secondValue / firstValue);
                } else if (element.equals("^")) {
                    valueStack.push(Math.pow(secondValue, firstValue));
                }
            }
        }
        return valueStack.pop();
    }

// ========================================================================================================== 
    void plotGraph() {
        ArrayList<ArrayList<Double>> coordinates = new ArrayList<>();
        for (double x = -50; x <= 50; x += 0.1) {
            ArrayList<Double> point = new ArrayList<>();
            point.add(x);
            point.add(compute(x));
            coordinates.add(point);
        }
        Plotter plot = new Plotter(coordinates);
    }

//    public class Invalid_input extends RuntimeException{
//
//        public Invalid_input() {
//            super();
//        }
//        
//        public Invalid_input(String msg){
//            super(msg);
//        }        
//}
}

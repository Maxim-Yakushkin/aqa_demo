package com.yakushkin.calculator.service;

import com.yakushkin.calculator.exception.DivisionByZeroException;
import com.yakushkin.calculator.exception.NoOperatorException;
import com.yakushkin.calculator.helper.MathOperation;
import com.yakushkin.calculator.helper.MessageHelper;
import com.yakushkin.calculator.helper.RegexHelper;

import static com.yakushkin.calculator.helper.MathOperation.getDifference;
import static com.yakushkin.calculator.helper.MathOperation.getDivisionResult;
import static com.yakushkin.calculator.helper.MathOperation.getMathOperator;
import static com.yakushkin.calculator.helper.MathOperation.getMultiplicationResult;
import static com.yakushkin.calculator.helper.MathOperation.getSum;
import static com.yakushkin.calculator.helper.MessageHelper.RESULT_MESSAGE;
import static com.yakushkin.calculator.helper.ToolHelper.SCANNER;

/**
 * The main calculator logic class
 */
public class CalculatorService {

    public static double calculate() {

        double expressionResult = 0;
        double firstArgument;
        double secondArgument;
        MathOperation operator;

        final String[] expressionElements = getExpressionElements();

        try {
            firstArgument = Double.parseDouble(checkElement(expressionElements, 0));
        } catch (NumberFormatException | NoOperatorException ex) {
            if (ex instanceof NumberFormatException) {
                throw new NumberFormatException(MessageHelper.FIRST_ARGUMENT_MUST_BE_NUMBER_MESSAGE);
            } else {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
        }

        try {
            operator = getMathOperator(checkElement(expressionElements, 1));
        } catch (NoOperatorException e) {
            throw new RuntimeException(e);
        }

        try {
            secondArgument = Double.parseDouble(checkElement(expressionElements, 2));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException |
                 NoOperatorException ex) {
            if (ex instanceof NumberFormatException) {
                throw new NumberFormatException(MessageHelper.SECOND_ARGUMENT_MUST_BE_NUMBER_MESSAGE);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        switch (operator) {
            case PLUS -> expressionResult = getSum(firstArgument, secondArgument);
            case MINUS -> expressionResult = getDifference(firstArgument, secondArgument);
            case MULTIPLICATION -> expressionResult = getMultiplicationResult(firstArgument, secondArgument);
            case DIVISION -> {
                try {
                    expressionResult = getDivisionResult(firstArgument, secondArgument);
                } catch (DivisionByZeroException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return expressionResult;
    }

    /**
     * the method gets an element from the expression and checks it contains ',' and replace to '.' if it necessary
     */
    private static String checkElement(String[] expressionElements, int x) throws NoOperatorException {
        if (expressionElements.length == 1) {
            throw new NoOperatorException(MessageHelper.OPERATOR_SHOULD_BE_PRESENT_MESSAGE);
        }
        final String element = expressionElements[x];

        return element.contains(",") ? element.replace(",", ".") : element;
    }

    /**
     * divides the arguments and operator
     */
    private static String[] getExpressionElements() {
        final String expression = getExpression();

        return expression.split(RegexHelper.SPLIT_OPERATOR_AND_OPERAND_REGEX);
    }

    /**
     * get expression from console
     */
    private static String getExpression() {
        System.out.print(MessageHelper.PROVIDE_EXPRESSION_MESSAGE);

        return SCANNER.nextLine();
    }

    /**
     * print expression result
     */
    public static void printResult(double expressionResult) {
        System.out.print(RESULT_MESSAGE + expressionResult);
    }
}

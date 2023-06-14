package com.yakushkin.calculator.helper;

import com.yakushkin.calculator.exception.DivisionByZeroException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathOperationTest {

    private static final String PLUS_OPERATOR = "+";
    private static final String MINUS_OPERATOR = "-";
    private static final String MULTIPLICATION_OPERATOR = "*";
    private static final String DIVISION_OPERATOR = "/";
    private static final String INCORRECT_OPERATOR = "$";
    private static final String NO_SUCH_ELEMENT_EXCEPTION_NAME = "java.util.NoSuchElementException";
    private static final String DIVISION_BY_ZERO_EXCEPTION_NAME = "com.yakushkin.calculator.exception.DivisionByZeroException";

    @ParameterizedTest
    @EnumSource(MathOperation.class)
    void checkReturnCorrectMathOperatorByTypedSymbolToConsole(MathOperation mathOperation) {
        switch (mathOperation) {
            case PLUS -> assertEquals(PLUS_OPERATOR, MathOperation.getMathOperator(PLUS_OPERATOR).getSymbol());
            case MINUS -> assertEquals(MINUS_OPERATOR, MathOperation.getMathOperator(MINUS_OPERATOR).getSymbol());
            case MULTIPLICATION ->
                    assertEquals(MULTIPLICATION_OPERATOR, MathOperation.getMathOperator(MULTIPLICATION_OPERATOR).getSymbol());
            case DIVISION ->
                    assertEquals(DIVISION_OPERATOR, MathOperation.getMathOperator(DIVISION_OPERATOR).getSymbol());
        }
    }

    @Test
    void checkThrowsExceptionWithIncorrectTypedSymbolToConsole() {
        final Exception exception = assertThrows(
                NoSuchElementException.class,
                () -> MathOperation.getMathOperator(INCORRECT_OPERATOR));

        assertEquals(NO_SUCH_ELEMENT_EXCEPTION_NAME, exception.getClass().getName());
    }

    @ParameterizedTest
    @CsvSource({"1,1,2", "1.5,1.5,3", "1,1.5,2.5", "1.5,1,2.5", "-1,1,0", "1,-1,0", "-1.5,1.5,0", "1.5,-1.5,0",
            "-1,1.5,0.5", "1,-1.5,-0.5", "-1.5,1,-0.5", "1.5,-1,0.5"})
    void checkSumResult(double firstOperand, double secondOperand, double expectedResult) {
        final double sum = MathOperation.getSum(firstOperand, secondOperand);
        assertEquals(expectedResult, sum);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0", "1.5,1.5,0", "1,1.5,-0.5", "1.5,1,0.5", "-1,1,-2", "1,-1,2", "-1.5,1.5,-3", "1.5,-1.5,3",
            "-1,1.5,-2.5", "1,-1.5,2.5", "-1.5,1,-2.5", "1.5,-1,2.5"})
    void checkDifferenceResult(double firstOperand, double secondOperand, double expectedResult) {
        final double sum = MathOperation.getDifference(firstOperand, secondOperand);
        assertEquals(expectedResult, sum);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1", "1.5,1.5,2.25", "1,1.5,1.5", "1.5,1,1.5", "-1,1,-1", "1,-1,-1", "-1.5,1.5,-2.25", "1.5,-1.5,-2.25",
            "-1,1.5,-1.5", "1,-1.5,-1.5", "-1.5,1,-1.5", "1.5,-1,-1.5"})
    void checkMultiplicationResult(double firstOperand, double secondOperand, double expectedResult) {
        final double sum = MathOperation.getMultiplicationResult(firstOperand, secondOperand);
        assertEquals(expectedResult, sum);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1", "1.5,1.5,1", "1,1.5,0.6666666666666666", "1.5,1,1.5", "-1,1,-1", "1,-1,-1", "-1.5,1.5,-1", "1.5,-1.5,-1",
            "-1,1.5,-0.6666666666666666", "1,-1.5,-0.6666666666666666", "-1.5,1,-1.5", "1.5,-1,-1.5"})
    void checkDivisionResult(double firstOperand, double secondOperand, double expectedResult) throws DivisionByZeroException {
        final double sum = MathOperation.getDivisionResult(firstOperand, secondOperand);
        assertEquals(expectedResult, sum);
    }

    @Test
    void checkDivisionByZeroException() {
        final Exception exception = assertThrows(DivisionByZeroException.class,
                () -> MathOperation.getDivisionResult(1, 0));

        final String expectedExceptionMessage = MessageHelper.DIVISION_BY_ZERO_EXCEPTION_MESSAGE;
        final String actualExceptionMessage = exception.getMessage();

        assertEquals(DIVISION_BY_ZERO_EXCEPTION_NAME, exception.getClass().getName());
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
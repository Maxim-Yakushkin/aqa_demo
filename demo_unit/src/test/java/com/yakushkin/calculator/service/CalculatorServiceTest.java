package com.yakushkin.calculator.service;

import com.yakushkin.calculator.helper.MessageHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorServiceTest {

    private static final String NEW_LINE = "\n";
    private static final String DATA_ORDER_1 = "1+1";
    private static final String DATA_ORDER_2 = "1-1";
    private static final String DATA_ORDER_3 = "1*1";
    private static final String DATA_ORDER_4 = "1/1";
    private static final String DATA_ORDER_5 = "1,5+1,5";
    private static final String DATA_ORDER_6 = "abc+123";
    private static final String DATA_ORDER_7 = "123+abc";
    private static final String DATA_ORDER_8 = "123";
    private static final String DATA_ORDER_9 = "1/0";
    private static final int EXPECTED_RESULT_FOR_ORDER_1 = 2;
    private static final int EXPECTED_RESULT_FOR_ORDER_2 = 0;
    private static final int EXPECTED_RESULT_FOR_ORDER_3 = 1;
    private static final int EXPECTED_RESULT_FOR_ORDER_4 = 1;
    private static final double EXPECTED_RESULT_FOR_ORDER_5 = 3;
    private static final String EXPECTED_RESULT_FOR_ORDER_6 = "java.lang.NumberFormatException";
    private static final String EXPECTED_RESULT_FOR_ORDER_7 = "java.lang.NumberFormatException";
    private static final String EXPECTED_RESULT_EXCEPTION_FOR_ORDER_8 = "com.yakushkin.calculator.exception.NoOperatorException";
    private static final String EXPECTED_RESULT_MESSAGE_FOR_ORDER_8 = MessageHelper.OPERATOR_SHOULD_BE_PRESENT_MESSAGE;
    private static final String EXPECTED_RESULT_FOR_ORDER_9 = MessageHelper.DIVISION_BY_ZERO_EXCEPTION_MESSAGE;
    private static final String EXPECTED_RESULT_CHECK_PRINTING = "Result is: 5.0";
    private static final int INTENDED_EXPRESSION_RESULT = 5;

    @BeforeAll
    public static void prepareTestData() {
        prepareInputStreamDataForImitationInputToConsole();
    }

    @Test
    @Order(1)
    public void checkSumResultWithCorrectValues() {
        double actualResult = CalculatorService.calculate();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_1, actualResult);
    }

    @Test
    @Order(2)
    public void checkDifferenceResultWithCorrectValues() {
        double actualResult = CalculatorService.calculate();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_2, actualResult);
    }

    @Test
    @Order(3)
    public void checkMultiplicationResultWithCorrectValues() {
        double actualResult = CalculatorService.calculate();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_3, actualResult);
    }

    @Test
    @Order(4)
    public void checkDivisionResultWithCorrectValues() {
        final double actualResult = CalculatorService.calculate();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_4, actualResult);
    }

    @Test
    @Order(INTENDED_EXPRESSION_RESULT)
    public void checkOperationWhereOperandsContainsComaInsteadDot() {
        final double actualResult = CalculatorService.calculate();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_5, actualResult);
    }

    @Test
    @Order(6)
    public void checkExpressionWithIncorrectFirstOperand() {
        final Exception exception = assertThrows(
                NumberFormatException.class,
                CalculatorService::calculate);

        final String actualResult = exception.getClass().getName();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_6, actualResult);
    }

    @Test
    @Order(7)
    public void checkExpressionWithIncorrectSecondOperand() {
        final Exception exception = assertThrows(
                NumberFormatException.class,
                CalculatorService::calculate
        );

        final String actualResult = exception.getClass().getName();
        assertEquals(EXPECTED_RESULT_FOR_ORDER_7, actualResult);
    }

    @Test
    @Order(8)
    public void checkOperationWithoutOperator() {
        final Exception exception = assertThrows(
                RuntimeException.class,
                CalculatorService::calculate
        );

        final String actualException = exception.getCause().getClass().getName();
        final String actualExceptionMessage = exception.getCause().getMessage();

        assertEquals(EXPECTED_RESULT_EXCEPTION_FOR_ORDER_8, actualException);
        assertEquals(EXPECTED_RESULT_MESSAGE_FOR_ORDER_8, actualExceptionMessage);
    }

    @Test
    @Order(9)
    public void checkDivisionByZero() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));

        CalculatorService.calculate();
        assertTrue(outContent.toString().contains(EXPECTED_RESULT_FOR_ORDER_9));
    }

    @Test
    public void checkPrintResult() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));

        CalculatorService.printResult(INTENDED_EXPRESSION_RESULT);
        assertEquals(EXPECTED_RESULT_CHECK_PRINTING, outContent.toString());
    }

    private static void prepareInputStreamDataForImitationInputToConsole() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(DATA_ORDER_1);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_2);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_3);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_4);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_5);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_6);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_7);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_8);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(DATA_ORDER_9);

        try (InputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes())) {
            System.setIn(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
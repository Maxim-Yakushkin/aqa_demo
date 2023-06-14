package com.yakushkin.calculator.helper;

import com.yakushkin.calculator.exception.DivisionByZeroException;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum MathOperation {

    PLUS("+"),
    MINUS("-"),
    DIVISION("/"),
    MULTIPLICATION("*");

    private final String symbol;

    MathOperation(String symbol) {
        this.symbol = symbol;
    }

    public static MathOperation getMathOperator(String commandValue) {

        return Arrays.stream(MathOperation.values())
                .filter(value -> value.getSymbol().equals(commandValue))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public static double getSum(double a, double b) {
        return a + b;
    }

    public static double getDifference(double a, double b) {
        return a - b;
    }

    public static double getMultiplicationResult(double a, double b) {
        return a * b;
    }

    public static double getDivisionResult(double a, double b) throws DivisionByZeroException {
        if (b != 0) {
            return a / b;
        } else {
            throw new DivisionByZeroException(MessageHelper.DIVISION_BY_ZERO_EXCEPTION_MESSAGE);
        }
    }

    public String getSymbol() {
        return symbol;
    }
}

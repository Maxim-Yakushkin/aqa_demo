package com.yakushkin.calculator.helper;

public class RegexHelper {

    /**
     * The regex splits the arguments and operator to separate elements
     */
    public static final String SPLIT_OPERATOR_AND_OPERAND_REGEX = "(?<=[-+*/])|(?=[-+*/])";
}

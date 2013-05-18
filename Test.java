package com.shanth.calculator;

import java.util.HashMap;

public class Test {

    public static void main(String... args) {

	HashMap<String, Object> datamap = new HashMap<String, Object>();
	datamap.put("x", 2);
	datamap.put("y", 3);
	datamap.put("z", 4);
	datamap.put("expression", "(2+3)*(x+y*z+32)");
	test(datamap);
    }

    private static void test(HashMap<String, Object> test) {
	String expression = (String) test.get("expression");

	System.out.println("Expression: " + expression);

	try {
	    ExpressionParser ep = new ExpressionParser();
	    ep.setExpression(expression);
	    ep.parse();
	    Expression ex = ep.getExpression();
	    System.out.println("Ex: " + ex);
	    Object res = ex.evaluateExpression(test);
	    System.out.println("Result: " + res);

	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	System.out.println("---");
	System.out.println("");
    }
}

/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */
package com.shanth.calculator;


import java.util.HashMap;

/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */

public class NotOperator extends Expression{

	private final Expression expression;

	public NotOperator(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Object evaluateExpression(HashMap<String,Object> dataMap)
			throws Exception {
		Object object = this.expression.evaluateExpression(dataMap);
		if (object == null) {
			return null;
		}
		if (Boolean.class.isAssignableFrom(object.getClass())) {
			return !(Boolean) object;
		}
		throw new Exception("Value not boolean.");
	}
}

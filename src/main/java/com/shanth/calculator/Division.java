/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */
package com.shanth.calculator;

/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */

public class Division extends NumericLiteral {

	public Division(Expression numerator, Expression denominator) {
        super(Division.class, numerator, denominator);
    }
	
	@Override
	protected Double calcDouble(Double lhsexpression, Double rhsexpression) {

		return lhsexpression / rhsexpression;
	}

	@Override
	protected Integer calcInteger(Integer lhsexpression, Integer rhsexpression) {

		return lhsexpression / rhsexpression;
	}

	@Override
	public String toString() {
		return "divide" + super.toString();
	}
}

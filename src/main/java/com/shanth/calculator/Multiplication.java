/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 */
package com.shanth.calculator;

/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 */

public class Multiplication extends NumericLiteral {

	public Multiplication(Expression... expressions) {
		super(Multiplication.class, expressions);
	}

	@Override
	protected Double calcDouble(Double lhsexpression, Double rhsexpression) {

		return lhsexpression * rhsexpression;
	}

	@Override
	protected Integer calcInteger(Integer lhsexpression, Integer rhsexpression) {

		return lhsexpression * rhsexpression;
	}

	@Override
	public String toString() {
		return "multiply" + super.toString();
	}

}

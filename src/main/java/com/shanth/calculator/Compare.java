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
public class Compare extends CompoundExpression {

	private final Calculator calculator;
	private final WordType comparison;

	Compare(Expression lhsexpression, WordType comparison, Expression rhsexpression)
			throws Exception {
		super(lhsexpression, rhsexpression);
		this.comparison = comparison;
		switch (comparison) {
		case LESS_THAN: {
			this.calculator = new Calculator() {
				@Override
				boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression) {

					return lhsexpression.compareTo(rhsexpression) < 0;
				}
			};
			break;
		}

		case LESS_THAN_OR_EQUALS: {
			this.calculator = new Calculator() {
				@Override
				boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression) {

					return lhsexpression.compareTo(rhsexpression) <= 0;
				}
			};
			break;
		}
		case EQUALS: {
			this.calculator = new Calculator() {
				@Override
				boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression) {
					return lhsexpression.compareTo(rhsexpression) == 0;
				}
			};
			break;
		}
		case NOT_EQUALS: {
			this.calculator = new Calculator() {
				@Override
				boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression) {
					return lhsexpression.compareTo(rhsexpression) != 0;
				}
			};
			break;
		}

		case GREATER_THAN_OR_EQUALS: {
			this.calculator = new Calculator() {
				@Override
				boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression) {
					return lhsexpression.compareTo(rhsexpression) >= 0;
				}
			};
			break;
		}

		case GREATER_THAN: {
			this.calculator = new Calculator() {
				@Override
				boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression) {
					return lhsexpression.compareTo(rhsexpression) > 0;
				}
			};

			break;
		}
		default: {
			throw new Exception("Expected comparison :" + comparison.getValue());
		}
		}

	}

	@Override
	protected Object combine(Object finalResult, Object result)
			throws Exception {
		try {
			return this.calculator.compare((Comparable<Object>) finalResult,
					(Comparable<Object>) result);
		} catch (ClassCastException ex) {
			throw new Exception("Result not comparable", ex);
		}

	}

	private static abstract class Calculator {
		abstract boolean compare(Comparable<Object> lhsexpression, Comparable<Object> rhsexpression);
	}
}

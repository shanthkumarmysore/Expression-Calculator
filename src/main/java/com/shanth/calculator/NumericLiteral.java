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

public abstract class NumericLiteral extends CompoundExpression {

	protected NumericLiteral(Expression... expressions) {
		super(expressions);
	}

	protected NumericLiteral(Class<?> classType, Expression... expressions) {
		super(classType, expressions);
	}


	@Override
	protected Object combine(Object finalresult, Object result)
			throws Exception {
		if (Integer.class.isAssignableFrom(finalresult.getClass())
				|| Integer.class.isAssignableFrom(result.getClass())) {
			return calcInteger((Integer) finalresult, (Integer) result);
		}

		if (Double.class.isAssignableFrom(finalresult.getClass())
				|| Double.class.isAssignableFrom(result.getClass())) {

			return calcDouble((Double) finalresult, (Double) result);
		}

		return null;
	}

	@Override
	protected boolean validate(Object result) throws Exception {
		return (super.validate(result) && (Integer.class
				.isAssignableFrom(result.getClass()) || Double.class
				.isAssignableFrom(result.getClass())));
	}

	protected abstract Double calcDouble(Double lhsexpression, Double rhsexpression);

	protected abstract Integer calcInteger(Integer lhsexpression, Integer rhsexpression);
}

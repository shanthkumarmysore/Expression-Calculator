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
public class Addition extends NumericLiteral{

    public Addition(Expression ... expressions) {
        super(Addition.class, expressions);
    }
	@Override
	protected Double calcDouble(Double lhsexpression, Double rhsexpression) {
		
		return lhsexpression+rhsexpression;
	}

	@Override
    protected Integer calcInteger(Integer lhsexpression, Integer rhsexpression) {
		
		return lhsexpression+rhsexpression;
	}

	@Override
    public String toString() {
        return "add" + super.toString();
    }

}

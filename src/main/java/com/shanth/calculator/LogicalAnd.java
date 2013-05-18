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

public class LogicalAnd extends CompoundExpression{
	
	     LogicalAnd(Expression lhsexpression, Expression rhsexpression) {
	        super(LogicalAnd.class, lhsexpression, rhsexpression);
	    }
	 
	    @Override
	    protected boolean validate(Object result) throws Exception {
	        return super.validate(result) &&
	                Boolean.class.isAssignableFrom(result.getClass());
	    }
	    
	    @Override
	    protected boolean complete(Object overall) {
	        return !(Boolean)overall;
	    }
	    
	    @Override
	    protected Object combine(Object overall, Object result) throws Exception {
	        return result;
	    }

}

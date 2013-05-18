/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */
package com.shanth.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */

public abstract class CompoundExpression extends Expression {

	private final List<Expression> expressions;

	protected CompoundExpression(Expression... expressions) {
		this(null, expressions);
	}

	protected CompoundExpression(Class<?> classType, Expression... expressions) {
		this.expressions = new ArrayList<Expression>();
		boolean isCompoundExpression = classType != null
				&& CompoundExpression.class.isAssignableFrom(classType);

		for (int exprssionCounter = 0; exprssionCounter < expressions.length; exprssionCounter++) {
			if (isCompoundExpression
					&& expressions[exprssionCounter].getClass().equals(
							classType)) {
				this.expressions
						.addAll(((CompoundExpression) expressions[exprssionCounter]).expressions);
			} else {
				this.expressions.add(expressions[exprssionCounter]);
			}
		}

	}
	
	public Object evaluateExpression(HashMap<String, Object> dataMap) throws Exception{
		
		Object finalResult=null;
		
		for(Expression expression:this.expressions){
			Object result=expression.evaluateExpression(dataMap);
			
			if(!this.validate(result)){
				finalResult=null;
				break;
			}
			
			if(finalResult!=null){
				finalResult=this.combine(finalResult,result);
			}else{
				finalResult = result;
			}
			
			if(this.complete(finalResult)){
				break;
			}
		}
		
		  System.out.println(">" + this.toString() + " = " + (finalResult == null ? "null" : finalResult.toString()));
	        return finalResult;
	
	}
	
	  protected boolean complete(Object overall)
	            throws Exception {
	        return false;
	    }
	
	protected boolean validate(Object result) throws Exception{
		return (result!=null);
	}
	
	protected abstract Object combine(Object finalresult,Object result) throws Exception;
		

	
}

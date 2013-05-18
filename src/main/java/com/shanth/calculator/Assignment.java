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
public class Assignment extends Expression {
	
	private final String key;
	
	private final Expression expression;
	
	public Assignment(String key,Expression expression) throws Exception{
		Element element = new Element(key);
		if(element.isLiteral()){
			throw new Exception("Left hand side of Assignment expression should not be a constant value");
		}
		this.key = key;
        this.expression = expression;
	}
	
	@Override
    public Object evaluateExpression(HashMap<String,Object> dataMap)
            throws Exception {
        Object value = this.expression.evaluateExpression(dataMap);
        dataMap.put (this.key, value);
        return value;
    }

}

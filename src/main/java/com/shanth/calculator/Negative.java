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

public class Negative extends Expression {

 
    private final Expression expression;

   
    public Negative(Expression expression) {
        this.expression = expression;
    }

   
    @Override
    public Object evaluateExpression(HashMap<String,Object> dataMap)
            throws Exception {
        Object object = this.expression.evaluateExpression(dataMap);
        if (object == null) {
            return null;
        }
        if (Integer.class.isAssignableFrom(object.getClass())) {
            return -(Integer)object;
        }
        if (Double.class.isAssignableFrom(object.getClass())) {
            return -(Double)object;
        }
        throw new Exception("Value not a number.");
    }
}

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

public class Modulus
        extends NumericLiteral {

   
    public Modulus(Expression value, Expression modulo) {
        super(value, modulo);
    }

    
    @Override
    protected Double calcDouble(Double lhsexpression, Double rhsexpression) {
        if (rhsexpression <= 0.0) {
            return null;
        }
        return lhsexpression % rhsexpression;
    }

   
    @Override
    protected Integer calcInteger(Integer lhsexpression, Integer rhsexpression) {
        if (rhsexpression <= 0) {
            return null;
        }
        return lhsexpression % rhsexpression;
    }

  
    @Override
    public String toString() {
        return "mod" + super.toString();
    }
}

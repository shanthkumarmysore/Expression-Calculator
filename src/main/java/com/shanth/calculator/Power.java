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

public class Power
        extends NumericLiteral {

    public Power(Expression base, Expression exponent) {
        super(Power.class, base, exponent);
    }

   
    @Override
    protected Double calcDouble(Double lhs, Double rhs) {
        return Math.pow(lhs, rhs);
    }

   
    @Override
    protected Integer calcInteger(Integer lhsexpression, Integer rhsexpression) {
        if (rhsexpression < 0) {
           
            return null;
        }
        return iPow(lhsexpression, rhsexpression);
    }

    private int iPow (int base, int exp) {

        if (exp == 0) {
            return 1;
        }
        int mid = exp/2;
        int pow = iPow(base, mid);
        if (exp % 2 == 0) {
            return pow * pow;
        }
        return base * pow * pow;
    }

   @Override
    public String toString() {
        return "pow" + super.toString();
    }
}

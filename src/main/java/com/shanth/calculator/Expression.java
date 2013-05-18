/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */
package com.shanth.calculator;

import java.util.Date;
import java.util.HashMap;

/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */
public abstract class Expression {

	public abstract Object evaluateExpression(HashMap<String, Object> dataMap)
			throws Exception;

	@Override
	public String toString() {
		return "Expression";
	}

	protected static Boolean parseToBoolean(String value) {
		if ("true".equals(value)) {
			return true;
		} else if ("false".equals(value)) {
			return false;
		}

		return null;
	}

	protected static Double parseToDouble(String value) {

		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex) {

		}

		return null;
	}

	protected static Integer parseToInteger(String value) {

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex) {

		}
		return null;
	}

	protected static Date parseToDate(String string) {
		if (string.charAt(0) == '\''
				&& string.charAt(string.length() - 1) == '\'') {
			return new Date(string.substring(1, string.length() - 1));
		}
		return null;
	}

	protected static String parseToString(String string) {
	    		    	
	        if (string.charAt(0)=='"' && string.charAt(string.length()-1)=='"') {
	            return string.substring(1,string.length()-1).replaceAll("\"\"", "\"");
	        }
	        return null;
	    }
}

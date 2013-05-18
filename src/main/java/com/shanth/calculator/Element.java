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

public class Element extends Expression {

	private final Object value;
	private final boolean literal;

	public Element(String word) {
		Object object;

		if ((object = parseToString(word)) != null) {
			this.literal = true;
		} else if ((object = parseToDate(word)) != null) {
			this.literal = true;
		} else if ((object = parseToInteger(word)) != null) {
			this.literal = true;
		} else if ((object = parseToDouble(word)) != null) {
			this.literal = true;
		} else if ((object = parseToBoolean(word)) != null) {
			this.literal = true;
		} else {
			this.literal = false;
			object = word;
		}
		this.value = object;
	}

	private static Object getDataValue(String key,
			HashMap<String, Object> dataMap) {

		return dataMap.get(key);
	}

	@Override
	public Object evaluateExpression(HashMap<String, Object> dataMap)
			throws Exception {
		if (this.literal) {
			return this.value;
		}
		return Element.getDataValue((String) this.value, dataMap);
	}

	public boolean isLiteral() {
		return this.literal;
	}

	@Override
	public String toString() {
		if (this.isLiteral()) {
			return '(' + this.value.toString() + ')';
		}
		return this.value.toString();
	}
}

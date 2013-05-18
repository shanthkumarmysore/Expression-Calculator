
package com.shanth.calculator;

import java.util.Arrays;



/**
 * Expression calculator
 * author-Shantha Kumar.N
 * created: December 2012
 * 
 */
public class ExpressionParser {

	private String expressionText;
	private int[] words;
	private WordType[] types;
	private Expression expression;

	public ExpressionParser() {
		this.expressionText = null;
		this.words = null;
		this.types = null;
		this.expression = null;
	}

	public ExpressionParser(String expressionText) throws Exception {
		this.parse(expressionText);
	}

	private ReturnExpression compare(int start) throws Exception {
		ReturnExpression lhs = sum(start);
		WordType op = getType(lhs.end);

		if (op == WordType.LESS_THAN || op == WordType.LESS_THAN_OR_EQUALS
				|| op == WordType.NOT_EQUALS || op == WordType.EQUALS
				|| op == WordType.GREATER_THAN_OR_EQUALS
				|| op == WordType.GREATER_THAN) {
			ReturnExpression rhs = sum(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Compare(lhs.expression,
					op, rhs.expression));
		}
		return lhs;
	}

	public void parse(String expressionText) throws Exception {
		this.setExpression(expressionText);
		this.parse();
	}

	public void parse() throws Exception {
		if (this.expressionText == null) {
			return;
		}

		ReturnExpression build = this.expression(0);
		if (build.end < this.getWordCount()) {
			throw new Exception("Unexpected end of expression at "
					+ getPhraseAt(build.end));
		}
		this.expression = build.expression;
	}

	public void setExpression(final String expressionText) {
		// clear the internals, if anything goes wrong these need to be
		// out.
		this.expression = null;
		this.expressionText = null;
		this.words = null;
		this.types = null;
		if (expressionText == null || "".equals(expressionText)) {
			// empty expression
			return;
		}
		this.expressionText = expressionText.trim();
		this.words = new int[this.expressionText.length()];
		boolean inWord = false;
		boolean inLiteral = false;
		int w = 0;
		final String WHITE_SPACE = " \t\n";
		for (int c = 0; c < this.expressionText.length(); c++) {
			char ch = this.expressionText.charAt(c);
			if (inLiteral) {
				if ('\\' == ch) {
					// skip the next character;
					c++;
				} else if ('"' == ch) {
					inLiteral = false;
					inWord = false;
				}
			} else {
				// is this a special word?
				if ("+-/*()^%".indexOf(ch, 0) != -1) {
					// single character;
					this.words[w++] = c;
					inWord = false;
				} else if ("!=<>".indexOf(ch, 0) != -1) {
					this.words[w++] = c;
					inWord = false;
					if (c + 1 < this.expressionText.length()
							&& "=<>".indexOf(this.expressionText.charAt(c + 1),
									0) != -1) {
						c++;
					}
				} else if (inWord) {
					if (WHITE_SPACE.indexOf(ch, 0) != -1) {
						inWord = false;
					}
				} else if (WHITE_SPACE.indexOf(ch, 0) == -1) {
					if ('"' == ch) {
						inLiteral = true;
					}
					this.words[w++] = c;
					inWord = true;
				}
			}
		}
		// last word:
		// this.words[w++] = this.expressionText.length();
		
		
		this.words = Arrays.copyOf(this.words, w);
		this.types = new WordType[w];
		for (int t = 0; t < w; t++) {
			this.types[t] = WordType.getType(getWord(t));
		}

		for (int word : this.words) {
			System.out.println("word=" + word);
		}
		

		for (WordType wordtype : this.types) {
			System.out.println("word type=" + wordtype);
		}
		
	}


	private ReturnExpression expression(int start) throws Exception {
		if (this.getType(start) == WordType.LITERAL
				&& this.getType(start + 1) == WordType.ASSIGN) {
			ReturnExpression ex = element(start + 2);
			return new ReturnExpression(ex.end, new Assignment(
					this.getWord(start), ex.expression));
		}
		return logicalOr(start);
	}

	private ReturnExpression logicalOr(int start) throws Exception {
		ReturnExpression lhs = logicalAnd(start);
		if (this.getType(lhs.end) == WordType.OR) {
			ReturnExpression rhs = logicalOr(lhs.end + 1);
			return new ReturnExpression(rhs.end, new LogicalOr(lhs.expression,
					rhs.expression));
		}
		return lhs;
	}

	private ReturnExpression logicalAnd(int start) throws Exception {
		ReturnExpression lhs = compare(start);
		WordType op = this.getType(lhs.end);
		if (op == WordType.AND) {
			ReturnExpression rhs = logicalAnd(lhs.end + 1);
			return new ReturnExpression(rhs.end, new LogicalAnd(lhs.expression,
					rhs.expression));
		}
		return lhs;
	}

	private ReturnExpression sum(int start) throws Exception {
		ReturnExpression lhs = term(start);
		switch (this.getType(lhs.end)) {
		case ADD: {
			ReturnExpression rhs = sum(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Addition(lhs.expression,
					rhs.expression));
		}
		case SUBTRACT: {
			ReturnExpression rhs = sum(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Subtraction(lhs.expression,
					rhs.expression));
		}
		}
		return lhs;
	}


	private ReturnExpression term(int start) throws Exception {
		ReturnExpression lhs = factor(start);
		switch (this.getType(lhs.end)) {
		case MULTIPLY: {
			ReturnExpression rhs = term(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Multiplication(
					lhs.expression, rhs.expression));
		}
		case DIVIDE: {
			ReturnExpression rhs = term(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Division(lhs.expression,
					rhs.expression));
		}
		}
		return lhs;
	}

	private ReturnExpression factor(int start) throws Exception {
		ReturnExpression lhs = primary(start);
		switch (this.getType(lhs.end)) {
		case POWER: {
			ReturnExpression rhs = factor(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Power(lhs.expression,
					rhs.expression));
		}
		case MODULUS: {
			ReturnExpression rhs = factor(lhs.end + 1);
			return new ReturnExpression(rhs.end, new Modulus(lhs.expression,
					rhs.expression));
		}
		}
		return lhs;

	}

	private ReturnExpression primary(int start) throws Exception {
		switch (this.getType(start)) {
		case SUBTRACT: {
			ReturnExpression ex = element(start + 1);
			return new ReturnExpression(ex.end, new Negative(ex.expression));
		}
		case NOT: {
			ReturnExpression ex = element(start + 1);
			return new ReturnExpression(ex.end, new NotOperator(ex.expression));
		}
		}
		return element(start);
	}

	private ReturnExpression element(int start) throws Exception {
		switch (this.getType(start)) {
		case OPEN_BRACKET: {
			ReturnExpression ex = expression(start + 1);
			if (this.getType(ex.end) != WordType.CLOSE_BRACKET) {
				throw new Exception("Expected close bracket at "
						+ this.getPhraseAt(ex.end));
			}
			return new ReturnExpression(ex.end + 1, ex.expression);
		}
		case LITERAL: {
			return new ReturnExpression(start + 1, new Element(
					this.getWord(start)));
		}
		default: {
			throw new Exception("Invalid element at " + this.getPhraseAt(start));
		}
		}
	}

	private WordType getType(int word) {
		if (word < 0) {
			return getType(0);
		} else if (word > this.getWordCount()) {
			return WordType.NULL;
		}
		return this.types[word];
	}

	private int getWordCount() {
		return this.words.length - 1;
	}

	private final static class ReturnExpression {
		private final int end;
		private final Expression expression;

		ReturnExpression(int end, Expression expression) {
			this.end = end;
			this.expression = expression;
		}

	}

	private String getPhraseAt(int word) {
		return this.getPhraseAt(word, 1);
	}

	private String getWord(int word) {
		if (word < 0) {
			return getWord(0);
		} 
		 else if (word > this.getWordCount()) {

			return null;
		}
		else if (word == this.getWordCount()) {
			int length=this.expressionText.length();
			char c='\0';	
			String value=null;
			if(length==word){
			c = this.expressionText.charAt(this.words.length - 1);
			value = Character.toString(c);
			}else{
				value=this.expressionText.substring(this.words[word],
						length).trim();
			}
			 

			return value;
		}

		
		return this.expressionText.substring(this.words[word],
				this.words[word + 1]).trim();
	}


	private String getPhraseAt(int word, int added) {
		int start = word - added;
		int end = word + added;
		if (start < 0) {
			start = 0;
		} else {
			start = this.words[start];
		}

		if (end >= this.getWordCount()) {
			end = this.expressionText.length();
		} else {
			end = this.words[end + 1];
		}
		return this.expressionText.substring(start, end).trim();
	}
	
	 public Expression getExpression() {
		return this.expression;
	}

}

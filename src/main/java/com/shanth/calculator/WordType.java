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

public enum WordType {
  
    NULL(null),
    
    LITERAL(""),
   
    NOT("!"),
   
    OPEN_BRACKET("("),
    
    CLOSE_BRACKET(")"),
    
    AND("&&"),
   
    OR("||"),
   
    ADD("+"),
   
    SUBTRACT("-"),
    
    MULTIPLY("*"),
    
    DIVIDE("/"),
    
    ASSIGN("="),
    
    LESS_THAN("<"),
   
    LESS_THAN_OR_EQUALS("<="),
    
    EQUALS("=="),
   
    NOT_EQUALS("!="),
    
    GREATER_THAN(">"),
    
    GREATER_THAN_OR_EQUALS(">="),
    
    POWER ("^"),
   
    MODULUS ("%");

  
    private final String value;

  
    private WordType(String value) {
        this.value = value;
    }

   
    public String getValue() {
        if (this.equals(WordType.NULL)) {
            return "[null]";
        }
        if (this.equals(WordType.LITERAL)) {
            return "[literal]";
        }
        return this.value;
    }

    
    private static final HashMap<String, WordType> backRef = new HashMap<String, WordType>();

    public static WordType getType(String word) {
        if (word == null) {
            return WordType.NULL;
        }
        if (backRef.isEmpty()) {
            for (int w = 0;
                    w<WordType.values().length;
                    w++) {
                WordType type = WordType.values()[w];
                if (type != WordType.NULL) {
                    backRef.put (type.value ,type);
                }
            }
        }
        WordType type = backRef.get(word);
        if (type == null) {
            return WordType.LITERAL;
        }
        return type;
    }
}

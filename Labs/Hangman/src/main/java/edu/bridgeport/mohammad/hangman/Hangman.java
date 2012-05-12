package edu.bridgeport.mohammad.hangman;

import java.util.ArrayList;

/**
 * @author Mohammad El-Abid [mohammad at reliablerabbit.com]
 */
public class Hangman {
    private int strikes, score;
    private boolean over = false;
    private boolean won = false;
    private String word;
    private ArrayList usedLetters = new ArrayList();
    
    public Hangman(String newWord) {
        strikes = score = 0;
        word = newWord.toUpperCase();
        over = false;
    }
    
    public boolean guessLetter(char letter){
        if(over) return false;
        if(word.indexOf("" + letter) != -1){
            if(usedLetters.indexOf(letter) == -1) {
                usedLetters.add(letter);
                score += 10;
            }
            
            won = true;
            for(int i = 0; word.length() > i; i++) {
                if(usedLetters.indexOf(word.charAt(i)) == -1) {
                   won = false;
                   break;
                }
            }
            if(won) over = true;
            
            return true;
        }
        strikes++;
        over = strikes >= 5;
        return false;
    }
    
    public String getRawWord() {
        return word;
    }
    
    public char[] getWordCharArray() {
        char[] chars = word.toCharArray();
        for(int i = 0; chars.length > i; i++) {
            if(!isOver() && usedLetters.indexOf(chars[i]) == -1) {
                chars[i] = '?';
            }
        }
        return chars;
    }
    
    public int getScore() { return score; }
    public int getStrikes() { return strikes; }
    public boolean isOver() { return over; }
    public boolean didWin() { return won; }
}

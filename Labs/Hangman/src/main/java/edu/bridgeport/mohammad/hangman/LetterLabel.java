package edu.bridgeport.mohammad.hangman;

/**
 *
 * @author Mohammad Typaldos [mohammad at reliablerabbit.com]
 */
public class LetterLabel extends javax.swing.JLabel {
    private String letter;
    boolean shown = false;
    
    LetterLabel(char letter) {
        this(String.valueOf(letter));
    }
    
    LetterLabel(String letter) {
        this.letter = letter;
        setText("?");
    }
    
    public void setShown(boolean shown) {
        this.shown = shown;
        if(shown) {
            setText(letter);
        } else {
            setText("?");
        }
    }
}

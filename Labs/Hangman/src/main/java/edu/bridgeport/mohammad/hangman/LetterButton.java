package edu.bridgeport.mohammad.hangman;

public class LetterButton extends javax.swing.JButton {
    private boolean used;
    private char letter;
    
    public LetterButton(char letter) {
        this.letter = letter;
        this.used = false;
        this.setText("" + this.letter);
    }
        
    public boolean isUsed(){
        return used;
    }
    
    public void setLetter(char newLetter) {
        this.letter = newLetter;
    }
    
    public char getLetter(){
        return letter;
    }
}

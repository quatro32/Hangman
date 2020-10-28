/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.Databaseable;
import Interfaces.HangmanDataprovider;

/**
 *
 * @author shnva
 */
public abstract class Difficulty implements Databaseable {
    private String word; 
    private int id, timeInMinutes;
    
    protected int getTimeInMinutes() {
        return this.timeInMinutes;
    }
    
    public final void setWord(String word){
        this.word = word;
    }
    
    public final String getWord() {
        return word;
    }
     
    public boolean hasTimer() {
        return false;
    }
    
    public boolean hasHintAvailable() {
        return false;
    }
    
    public abstract Word getRandomWord(HangmanDataprovider repo);
}
